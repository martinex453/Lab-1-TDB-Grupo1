package BDA.grupo1.repository;

import BDA.grupo1.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    public Cliente crear(Cliente cliente);
    public List<Cliente> getAll();
    public String update(Cliente cliente, int id);
    public void delete(int id);
}
