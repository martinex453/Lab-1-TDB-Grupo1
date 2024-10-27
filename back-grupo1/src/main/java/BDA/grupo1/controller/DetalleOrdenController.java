package BDA.grupo1.controller;

import BDA.grupo1.model.DetalleOrden;
import BDA.grupo1.service.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @PostMapping("/detalleOrden/crear")
    public DetalleOrden crearDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        return detalleOrdenService.crear(detalleOrden);
    }

    @GetMapping("/detalleOrden/All")
    public List<DetalleOrden> getAllDetalleOrden() {
        return detalleOrdenService.findAll();
    }

    @PostMapping("/detalleOrden/update/{id}")
    public String updateDetalleOrden(@RequestBody DetalleOrden detalleOrden, @PathVariable Integer id) {
        return detalleOrdenService.update(detalleOrden, id);
    }

    @DeleteMapping("/detalleOrden/delete/{id}")
    public void deleteDetalleOrden(@PathVariable Integer id) {
        detalleOrdenService.delete(id);
    }

}
