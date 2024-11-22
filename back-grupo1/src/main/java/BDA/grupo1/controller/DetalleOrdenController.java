package BDA.grupo1.controller;

import BDA.grupo1.model.DetalleOrden;
import BDA.grupo1.service.ClienteSesionService;
import BDA.grupo1.service.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @Autowired
    private ClienteSesionService clienteSesionService;

    @PostMapping("/detalleOrden/crear")
    public DetalleOrden crearDetalleOrden(@RequestBody DetalleOrden detalleOrden, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return detalleOrdenService.crear(detalleOrden);
    }

    @GetMapping("/detalleOrden/All")
    public List<DetalleOrden> getAllDetalleOrden() {
        return detalleOrdenService.findAll();
    }

    @PostMapping("/detalleOrden/update/{id}")
    public String updateDetalleOrden(@RequestBody DetalleOrden detalleOrden, @PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return detalleOrdenService.update(detalleOrden, id);
    }

    @DeleteMapping("/detalleOrden/delete/{id}")
    public void deleteDetalleOrden(@PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        detalleOrdenService.delete(id);
    }

}
