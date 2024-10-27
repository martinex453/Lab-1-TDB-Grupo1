package BDA.grupo1.model;

import lombok.Data;

@Data
public class Producto {
    private Long id_producto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String estado;
    private Integer id_categoria;
}
