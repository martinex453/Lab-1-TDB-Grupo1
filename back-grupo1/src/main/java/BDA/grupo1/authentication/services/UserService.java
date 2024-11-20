package BDA.grupo1.authentication.services;


import BDA.grupo1.authentication.entities.User;
import BDA.grupo1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    ClienteService ClienteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    public User getUserByEmail(String email) {
        return User.voluntarioToUser(ClienteService.getClienteByCorreo(email));
    }
}
