package BDA.grupo1.controller;

import BDA.grupo1.model.DetalleOrden;
import BDA.grupo1.service.ProcedimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcedimientosController {

    @Autowired
    private ProcedimientosService procedimientoService;

    @GetMapping("/top_usuarios")
    public ResponseEntity<String> obtenerReporteTopUsuarios() {
        try {
            String reporte = procedimientoService.generarReporteAgrupado();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar el reporte: " + e.getMessage());
        }
    }

    @PostMapping("/aplicar_descuento")
    public String aplicarDescuento(@RequestParam int idCategoria, @RequestParam float descuento) {
        try {
            // Llamar al servicio para aplicar el descuento
            procedimientoService.aplicarDescuentoACategoria(idCategoria, descuento);
            return "Descuento aplicado correctamente.";
        } catch (Exception e) {
            return "Error al aplicar el descuento: " + e.getMessage();
        }
    }

    @PostMapping("/crearOrdenCompra/{id_cliente}")
    public String crearOrden(@PathVariable int id_cliente, @RequestBody List<DetalleOrden> detalles) {
       try{
           procedimientoService.crearOrdenCompra(id_cliente, detalles);
           return "Orden de compra realizada con exito.";
       } catch (Exception e) {
           return "Error al crear la orden de compra: " + e.getMessage();
       }
    }
}

