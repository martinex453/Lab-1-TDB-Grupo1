package BDA.grupo1.model;

import lombok.Data;

@Data
public class Cliente {
    private Long id_cliente;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
}
