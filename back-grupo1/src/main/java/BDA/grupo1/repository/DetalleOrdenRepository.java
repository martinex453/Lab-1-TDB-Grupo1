package BDA.grupo1.repository;

import BDA.grupo1.model.DetalleOrden;

import java.util.List;

public interface DetalleOrdenRepository {
    public DetalleOrden crear(DetalleOrden detalleOrden);
    public List<DetalleOrden> getAll();
    public String update(DetalleOrden detalleOrden, int id);
    public void delete(int id);
}
