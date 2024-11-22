package BDA.grupo1.controller;

import BDA.grupo1.model.Producto;
import BDA.grupo1.service.ClienteSesionService;
import BDA.grupo1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteSesionService clienteSesionService;

    @PostMapping("/producto/crear")
    public Producto crearProducto(@RequestBody Producto producto, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return productoService.crear(producto);
    }

    @GetMapping("/producto/All")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @PostMapping("/producto/update/{id}")
    public String updateProducto(@RequestBody Producto producto, @PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        return productoService.update(producto,id);
    }

    @GetMapping("/producto/get/{id}")
    public Producto getproductoById(@PathVariable Integer id){
        return productoService.getproductoById(id);
    }

    @DeleteMapping("/producto/delete/{id}")
    public void deleteProducto(@PathVariable Integer id, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        productoService.delete(id);
    }

    @GetMapping("/producto/pagina/{page}/{pageSize}")
    public List<Producto> productoPages(@PathVariable Integer page, @PathVariable Integer pageSize) {
        return productoService.getProductoPage(page,pageSize);
    }

    @GetMapping("/producto/updateStock/{id}/{stock}")
    public void updateProductoStock(@PathVariable Integer id, @PathVariable Integer stock, @RequestParam Integer id_cliente) {
        clienteSesionService.crear(id_cliente);
        productoService.updateProductoStock(id,stock);
    }
}
