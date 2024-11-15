package BDA.grupo1.controller;

import BDA.grupo1.model.Producto;
import BDA.grupo1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/producto/crear")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crear(producto);
    }

    @GetMapping("/producto/All")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @PostMapping("/producto/update/{id}")
    public String updateProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        return productoService.update(producto,id);
    }

    @DeleteMapping("/producto/delete/{id}")
    public void deleteProducto(@PathVariable Integer id) {
        productoService.delete(id);
    }

    @GetMapping("/producto/pagina/{page}/{pageSize}")
    public List<Producto> productoPages(@PathVariable Integer page, @PathVariable Integer pageSize) {
        return productoService.getProductoPage(page,pageSize);
    }
}
