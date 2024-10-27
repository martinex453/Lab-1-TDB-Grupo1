package BDA.grupo1.controller;

import BDA.grupo1.model.Categoria;
import BDA.grupo1.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria/All")
    public List<Categoria> getAll() {
        return categoriaService.findAll();
    }

    @PostMapping("/categoria/crear")
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaService.crear(categoria);
    }

    @PostMapping("/categoria/update/{id}")
    public String update( @RequestBody Categoria categoria,@PathVariable Integer id) {
        return categoriaService.update(categoria,id);
    }

    @DeleteMapping("/categoria/delete/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }
}
