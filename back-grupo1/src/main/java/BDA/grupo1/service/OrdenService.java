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

    // servicio para crear una orden
    public Orden crear(Orden orden) {
        return ordenRepository.crear(orden);
    }

    // servicio para encontrar todas las ordenes de la tabla
    public List<Orden> findAll(){
        return ordenRepository.getAll();
    }

    // servicio para actualizar los datos de una orden
    public String update(Orden orden, Integer id) {
        return ordenRepository.update(orden, id);
    }

    // servicio para eliminar una orden según su identificador
    public void delete(Integer id){
        ordenRepository.delete(id);
    }

    // servicio para obtener las ordenes sgún el tamaño de la página
    public List<Orden> getOrdersPage(Integer page, Integer pageSize){
        return ordenRepository.getOrdenPages(page,pageSize);
    }

    // servicio para obtener las ordenes de un usuario según el tamaño de la página
    public List<Orden> getOrdersPageUser(Integer User, Integer page, Integer pageSize){
        return ordenRepository.getOrdersPageUser(User,page,pageSize);
    }

    // servicio para obtener las ordenes de un usuario
    public List<Orden> getOrdenByUserId(Integer id){
        return ordenRepository.getOrdenByUserId(id);
    }

    // servicio para obtener una orden según su identificador
    public Orden getOrdenById(Integer id){
        return ordenRepository.getOrdenById(id);
    }

    // servicio para obtener el identificador de una orden según su Timestamp
    public Integer getOrdenIDByTimestamp(){
        return ordenRepository.getOrdenIDByTimestamp();
    }

}
