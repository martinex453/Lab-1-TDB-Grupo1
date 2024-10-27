package BDA.grupo1.service;

import BDA.grupo1.model.Producto;
import BDA.grupo1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crear(Producto producto) {
        return productoRepository.crear(producto);
    }

    public List<Producto> findAll() {
        return productoRepository.getAll();
    }

    public String update(Producto producto, Integer id) {
        return productoRepository.update(producto, id);
    }

    public void delete(Integer id) {
        productoRepository.delete(id);
    }
}
