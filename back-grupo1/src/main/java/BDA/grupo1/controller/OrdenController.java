package BDA.grupo1.controller;

import BDA.grupo1.model.Orden;
import BDA.grupo1.service.ClienteSesionService;
import BDA.grupo1.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private ClienteSesionService clienteSesionService;

    @PostMapping("/orden/crear")
    public Orden crearOrden(@RequestBody Orden orden, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return ordenService.crear(orden);
    }

    @GetMapping("/orden/All")
    public List<Orden> getAllOrden() {
        return ordenService.findAll();
    }

    @PostMapping("/orden/update/{id}")
    public String updateOrden(@RequestBody Orden orden, @PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return ordenService.update(orden,id);
    }

    @DeleteMapping("/orden/delete/{id}")
    public void deleteOrden(@PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        ordenService.delete(id);
    }

    @GetMapping("/orden/pagina/{page}/{pageSize}")
    public List<Orden> OrdenPages(@PathVariable Integer page, @PathVariable Integer pageSize) {
        return ordenService.getOrdersPage(page,pageSize);
    }
}
