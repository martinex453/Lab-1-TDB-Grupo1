package BDA.grupo1.service;

import BDA.grupo1.model.Orden;
import BDA.grupo1.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public Orden crear(Orden orden) {
        return ordenRepository.crear(orden);
    }

    public List<Orden> findAll(){
        return ordenRepository.getAll();
    }

    public String update(Orden orden, Integer id) {
        return ordenRepository.update(orden, id);
    }

    public void delete(Integer id){
        ordenRepository.delete(id);
    }

    public List<Orden> getOrdersPage(Integer page, Integer pageSize){
        return ordenRepository.getOrdenPages(page,pageSize);
    }

    public List<Orden> getOrdenByUserId(Integer id){
        return ordenRepository.getOrdenByUserId(id);
    }

    public Orden getOrdenById(Integer id){
        return ordenRepository.getOrdenById(id);
    }

    public Integer getOrdenIDByTimestamp(){
        return ordenRepository.getOrdenIDByTimestamp();
    }

}
