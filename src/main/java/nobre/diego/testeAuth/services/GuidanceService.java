package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.EmployeeType;
import nobre.diego.testeAuth.domain.Guidance;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Guidance.CreateGuidanceDTO;
import nobre.diego.testeAuth.dtos.Guidance.GetResponseGuidanceDTO;
import nobre.diego.testeAuth.repositories.GuidanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuidanceService {

    @Autowired
    private GuidanceRepository guidanceRepository;

    public void createOrientation(CreateGuidanceDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        EmployeeType employeeType = dto.type();
        Guidance guidance = new Guidance();
        guidance.setStringTitle(dto.title());
        guidance.setDescricao(dto.descricao());
        guidance.setType(employeeType);
        guidance.setTimestamp(LocalDateTime.now());
        guidance.setUsers(user);
        guidance.setStringCallback("Aguardando atualizações");

        guidanceRepository.save(guidance);
    }

    public List<GetResponseGuidanceDTO> getGuidanceByFuncionario(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Guidance> guidanceList = guidanceRepository.listGuidance(user.getEmployeeType());

        return guidanceList.stream()
                .map(guidance -> new GetResponseGuidanceDTO(
                        guidance.getId(),
                        guidance.getUsers().getName(),
                        guidance.getUsers().getAdress(),
                        guidance.getUsers().getPhoneNumber(),
                        guidance.getStringTitle(),
                        guidance.getDescricao(),
                        guidance.getUserFuncionario() != null ? guidance.getUserFuncionario().getName()
                                : "Aguardando atualizações",
                        guidance.getType(),
                        guidance.getTimestamp(),
                        guidance.getStringCallback()
                ))
                .collect(Collectors.toList());

    }

    public List<GetResponseGuidanceDTO> getGuidanceUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Guidance> guidanceList = guidanceRepository.listGuidanceUser(user);

        return guidanceList.stream()
                .map(guidance -> new GetResponseGuidanceDTO(
                        guidance.getId(),
                        guidance.getUsers().getName(),
                        guidance.getUsers().getAdress(),
                        guidance.getUsers().getPhoneNumber(),
                        guidance.getStringTitle(),
                        guidance.getDescricao(),
                        guidance.getUserFuncionario() != null ? guidance.getUserFuncionario().getName()
                                : "Aguardando atualizações",
                        guidance.getType(),
                        guidance.getTimestamp(),
                        guidance.getStringCallback()
                ))
                .collect(Collectors.toList());
    }

    public void putGuidanceFun (Long id, Authentication authentication, String stringCallback) {
        Optional<Guidance> optionalGuidance = guidanceRepository.findById(id);
        User userFun = (User) authentication.getPrincipal();
        if (optionalGuidance.isPresent()) {
            Guidance guidance = optionalGuidance.get();
            guidance.setUserFuncionario(userFun);
            guidance.setView(true);
            guidance.setTimestamp(LocalDateTime.now());
            guidance.setStringCallback(stringCallback);
            guidanceRepository.save(guidance);

        }
    }

    public void editGuidanceUser(CreateGuidanceDTO dto) {
        Optional<Guidance> optionalGuidance = guidanceRepository.findById(dto.id());

        if (optionalGuidance.isPresent()) {
            Guidance guidance = optionalGuidance.get();
            if (dto.title() != null) {
                guidance.setStringTitle(dto.title());
            }
            if (dto.descricao() != null) {
                guidance.setDescricao(dto.descricao());
            }
            guidanceRepository.save(guidance);
        }
    }

    public void deleteGuidanceByUser (CreateGuidanceDTO dto) {
        Optional<Guidance> optionalGuidance = guidanceRepository.findById(dto.id());
        if (optionalGuidance.isPresent()) {
            Guidance guidance = optionalGuidance.get();
            guidanceRepository.delete(guidance);
        }
    }

    public List<String> getEnum() {
        return Arrays.stream(EmployeeType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    
}
