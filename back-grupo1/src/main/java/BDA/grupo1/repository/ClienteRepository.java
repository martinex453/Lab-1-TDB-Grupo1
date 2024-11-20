package BDA.grupo1.repository;

import BDA.grupo1.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    public Cliente crear(Cliente cliente);
    public List<Cliente> getAll();
    public String update(Cliente cliente, Integer id);
    public void delete(Integer id);
    public Cliente findByEmail(String email);
    public Optional<Cliente> getClienteByCorreo(String correo);
}
