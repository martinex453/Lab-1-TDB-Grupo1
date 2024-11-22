package BDA.grupo1.controller;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.service.ClienteService;
import BDA.grupo1.service.ClienteSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteSesionService clienteSesionService;

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

    @GetMapping("/cliente/findByEmail")
    public Cliente findByEmail(@RequestParam String email) {
        return clienteService.findByEmail(email);
    }

    @PostMapping("/cliente/crear_cuenta")
    public Cliente crearCuenta(@RequestBody Cliente cliente) {
        return clienteService.crear_cuenta(cliente);
    }

    @GetMapping("/cliente/getTop5")
    public List<Cliente> top5Clientes() { return clienteService.top5Clientes(); }
}
