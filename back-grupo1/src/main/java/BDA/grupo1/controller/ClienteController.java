package BDA.grupo1.controller;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crear(cliente);
    }

    @GetMapping("/cliente/All")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @PostMapping("/cliente/update/{id}")
    public String updateCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
        return clienteService.update(cliente,id);
    }

    @DeleteMapping("/cliente/delete/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.delete(id);
    }

    @PostMapping("/cliente/crear_cuenta")
    public Cliente crearCuenta(@RequestBody Cliente cliente) {
        return clienteService.crear_cuenta(cliente);
    }
}
