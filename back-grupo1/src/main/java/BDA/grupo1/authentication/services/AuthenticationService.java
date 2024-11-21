package BDA.grupo1.authentication.services;


import BDA.grupo1.authentication.entities.AuthenticationResponse;
import BDA.grupo1.authentication.entities.LoginRequest;
import BDA.grupo1.authentication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    public AuthenticationResponse authenticate(LoginRequest request) {
        try {
            System.out.println("Llegue al AUTH");
            System.out.println(request.getEmail());
            System.out.println(request.getContrasena());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getContrasena()
                    )
            );

            System.out.println("SALIDEL AUTHENTICATE");
            User user = userService.getUserByEmail(request.getEmail());
            System.out.println("Salid del getuseremail");

            System.out.println(user);
            String jwtToken = jwtService.generateToken(user.generateExtraClaims(), user);
            System.out.println(jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
            throw new RuntimeException("Autenticación fallida");
        }
    }
}
