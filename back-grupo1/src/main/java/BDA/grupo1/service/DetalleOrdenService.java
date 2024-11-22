package BDA.grupo1.service;

import BDA.grupo1.model.Cliente;
import BDA.grupo1.model.DetalleOrden;
import BDA.grupo1.repository.ClienteRepository;
import BDA.grupo1.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public DetalleOrden crear(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.crear(detalleOrden);
    }

    public List<DetalleOrden> findAll() {
        return detalleOrdenRepository.getAll();
    }

    public String update(DetalleOrden detalleOrden, Integer id) {
        return detalleOrdenRepository.update(detalleOrden, id);
    }

    public void delete(Integer id) {
        detalleOrdenRepository.delete(id);
    }

    public List<DetalleOrden> getdetalleOrdenByOrdenId(Integer id_orden) {
        return detalleOrdenRepository.getdetalleOrdenByOrdenId(id_orden);
    }
}
