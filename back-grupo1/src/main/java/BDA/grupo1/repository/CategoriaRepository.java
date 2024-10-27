package BDA.grupo1.repository;

import BDA.grupo1.model.Categoria;


import java.util.List;

public interface CategoriaRepository {
    public Categoria crear(Categoria categoria);
    public List<Categoria> getAll();
    public String update(Categoria categoria, int id);
    public void delete(int id);
}
