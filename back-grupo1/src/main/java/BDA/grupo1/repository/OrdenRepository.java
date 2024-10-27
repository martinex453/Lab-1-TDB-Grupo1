package BDA.grupo1.repository;

import BDA.grupo1.model.Orden;


import java.util.List;

public interface OrdenRepository {
    public Orden crear(Orden orden);
    public List<Orden> getAll();
    public String update(Orden orden, int id);
    public void delete(int id);
}
