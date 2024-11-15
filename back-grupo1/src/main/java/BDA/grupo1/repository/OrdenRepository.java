package BDA.grupo1.repository;

import BDA.grupo1.model.Orden;


import java.util.List;

public interface OrdenRepository {
    public Orden crear(Orden orden);
    public List<Orden> getAll();
    public String update(Orden orden, Integer id);
    public void delete(Integer id);
    public List<Orden> getOrdenPages(int page, int pageSize);
}
