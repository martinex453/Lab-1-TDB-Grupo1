package BDA.grupo1.service;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.repository.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crear(Cliente cliente) {
        return clienteRepository.crear(cliente);
    }

    public List<Cliente> findAll(){
        return clienteRepository.getAll();
    }

    public String update(Cliente cliente, Integer id) {
        return clienteRepository.update(cliente, id);
    }

    public void delete(Integer id) {
        clienteRepository.delete(id);
    }

    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
    public Cliente crear_cuenta(Cliente cliente) {
        String correo = cliente.getEmail();
        Cliente buscar_cliente = clienteRepository.findByEmail(correo);
        if (buscar_cliente == null) {
            return clienteRepository.crear(cliente);
        } else {
            return null;
        }
    }
    // Relacionado al Authentication
    public Cliente getClienteByCorreo(@NonNull String email){
        return clienteRepository
                .getClienteByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el voluntario"));
    }

}
