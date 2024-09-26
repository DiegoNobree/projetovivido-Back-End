package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.PanicButton;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.repositories.GuardianRepository;
import nobre.diego.testeAuth.repositories.PanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class PanicSerivce {

    private static final String API_URL = "https://api.ultramsg.com/{instance-id}/messages/chat?token={token}";

    @Value("${ultramsg.instance-id}")
    private String instanceId;

    @Value("${ultramsg.token}")
    private String token;


    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PanicRepository panicRepository;

    public void postPanic (Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        List<Guardians> guardiansList = guardianRepository.getGuardians(user);

        for (Guardians guardian : guardiansList) {
            String phoneNumber = guardian.getPhoneNumber();
            sendMessage(phoneNumber, "ME AJUDA PELO AMOR DEUS");
        }
        PanicButton panicButton = new PanicButton();
        panicButton.setTime(LocalDateTime.now());
        panicButton.setUsers(user);
        panicRepository.save(panicButton);
    }

    public String sendMessage (String toPhone, String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        Map<String, String> body = new HashMap<>();
        body.put("to", toPhone);
        body.put("body", message);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        String url = API_URL.replace("{instance-id}", instanceId).replace("{token}", token);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
