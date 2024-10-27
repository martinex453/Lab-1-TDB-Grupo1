package BDA.grupo1.service;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}