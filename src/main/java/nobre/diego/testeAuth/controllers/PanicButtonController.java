package nobre.diego.testeAuth.controllers;

import nobre.diego.testeAuth.domain.PanicButton;
import nobre.diego.testeAuth.services.PanicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/panic")
public class PanicButtonController {

    @Autowired
    private PanicSerivce serivce;

    @PostMapping("/post")
    public void postPanic (Authentication authentication) {
        serivce.postPanic(authentication);
    }   
}
