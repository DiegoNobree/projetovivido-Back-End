package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.EmployeeType;
import nobre.diego.testeAuth.domain.Guidance;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Guidance.CreateGuidanceDTO;
import nobre.diego.testeAuth.dtos.Guidance.GetResponseGuidanceDTO;
import nobre.diego.testeAuth.repositories.GuidanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
        guidance.setDescricao(dto.descricao());
        guidance.setType(employeeType);
        guidance.setTimestamp(LocalTime.now());
        guidance.setUsers(user);

        guidanceRepository.save(guidance);
    }

    public List<GetResponseGuidanceDTO> getGuidanceBy(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Guidance> guidanceList = guidanceRepository.listGuidance(user.getEmployeeType());

        return guidanceList.stream()
                .map(guidance -> new GetResponseGuidanceDTO(
                        guidance.getId(),
                        guidance.getUsers().getName(),
                        guidance.getUsers().getAdress(),
                        guidance.getUsers().getPhoneNumber(),
                        guidance.getDescricao(),
                        guidance.getUserFuncionario() != null ? guidance.getUserFuncionario().getName()
                                : "Aguardando atualizações",
                        guidance.getType()
                ))
                .collect(Collectors.toList());
    }

    //método para filtrar por usário cliente
    //criar método de deletar
    public List<GetResponseGuidanceDTO> getGuidanceUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Guidance> guidanceList = guidanceRepository.listGuidance(user.getEmployeeType());

        return guidanceList.stream()
                .map(guidance -> new GetResponseGuidanceDTO(
                        guidance.getId(),
                        guidance.getUsers().getName(),
                        guidance.getUsers().getAdress(),
                        guidance.getUsers().getPhoneNumber(),
                        guidance.getDescricao(),
                        guidance.getUserFuncionario() != null ? guidance.getUserFuncionario().getName()
                                : "Aguardando atualizações",
                        guidance.getType()
                ))
                .collect(Collectors.toList());
    }

    public void putGuidanceFun (Long id,String descricao, Authentication authentication) {
        Optional<Guidance> optionalGuidance = guidanceRepository.findById(id);
        User userFun = (User) authentication.getPrincipal();
        if (optionalGuidance.isPresent()) {
            Guidance guidance = optionalGuidance.get();
            guidance.setUserFuncionario(userFun);
            guidance.setDescricao(descricao);
            guidance.setView(true);
            guidance.setTimestamp(LocalTime.now());
            guidanceRepository.save(guidance);
        }
    }
}
