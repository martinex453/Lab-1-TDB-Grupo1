package BDA.grupo1.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Orden {
    private Long id_orden;
    private Timestamp fecha;
    private String estado;
    private Integer id_cliente;
    private Double total;
}
