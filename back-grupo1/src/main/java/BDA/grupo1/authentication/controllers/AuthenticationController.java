package BDA.grupo1.authentication.controllers;

import BDA.grupo1.authentication.entities.AuthenticationResponse;
import BDA.grupo1.authentication.entities.LoginRequest;
import BDA.grupo1.authentication.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        System.out.println("controlador");
        System.out.println(request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

