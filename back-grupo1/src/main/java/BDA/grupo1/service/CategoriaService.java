package BDA.grupo1.service;

import BDA.grupo1.model.Categoria;
import BDA.grupo1.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria crear(Categoria categoria) {
        return categoriaRepository.crear(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.getAll();
    }

    public String update(Categoria categoria, Integer id) {
        return categoriaRepository.update(categoria, id);
    }

    public void delete(Integer id) {
        categoriaRepository.delete(id);
    }
}
