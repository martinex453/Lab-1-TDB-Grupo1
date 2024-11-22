package BDA.grupo1.service;

import BDA.grupo1.model.ClienteSesion;
import BDA.grupo1.repository.ClienteSesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteSesionService {

    @Autowired
    private ClienteSesionRepository clienteSesionRepository;

    public Integer crear(Integer id) {
        return clienteSesionRepository.crear(id);
    }

    public void delete(Integer id) {
        clienteSesionRepository.delete(id);
    }
}
