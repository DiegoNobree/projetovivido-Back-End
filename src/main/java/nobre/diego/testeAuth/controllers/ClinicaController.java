package nobre.diego.testeAuth.controllers;

import nobre.diego.testeAuth.domain.Clinica;
import nobre.diego.testeAuth.dtos.ClinicaDTO;
import nobre.diego.testeAuth.repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return new ResponseEntity<>(clinicaRepository.findAll(),
                HttpStatus.OK);
    }
    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody ClinicaDTO clinicaDTO){

        Clinica clinica = new Clinica();
        clinica.setName(clinicaDTO.name());
        clinica.setDescricao(clinicaDTO.descricao());
        return new ResponseEntity<>(clinicaRepository.save(clinica),
                HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return new ResponseEntity<>(clinicaRepository.findById(id),
                HttpStatus.OK);
    }
}
