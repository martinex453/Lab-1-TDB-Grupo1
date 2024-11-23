package BDA.grupo1.service;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.repository.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public String get_rol_cliente(Integer id_cliente) {
        Cliente c = clienteRepository.get_rol_usuario(id_cliente);
        return c.getRol();
    }

    public Cliente crear_cuenta(Cliente cliente) {
        String correo = cliente.getEmail();
        Cliente buscar_cliente = clienteRepository.findByEmail(correo);
        String encodedPasssword = generateEncodedPassword(cliente.getContrasena());

        System.out.println("CREANDO CLIENTE");
        Cliente NuevoCliente =  new Cliente(cliente.getId_cliente(), cliente.getNombre(), cliente.getDireccion(),
                cliente.getEmail(), cliente.getTelefono(), encodedPasssword, cliente.getRol());

        System.out.println(NuevoCliente);
        if (buscar_cliente == null) {
            System.out.println("No hay cliente");
            return clienteRepository.crear(NuevoCliente);
        } else {
            System.out.println("Ya lo hay");
            return null;
        }
    }

    private String generateEncodedPassword(String passsword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(passsword);
    }

    // Relacionado al Authentication
    public Cliente getClienteByCorreo(@NonNull String email){
        return clienteRepository
                .getClienteByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el cliente"));
    }

    public List<Cliente> top5Clientes() { return clienteRepository.top5Clientes(); }
}
