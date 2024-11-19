package BDA.grupo1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetalleOrden {
    private Long id_detalle;
    private Integer id_orden;
    private Integer id_producto;
    private Integer cantidad;
    private Double precio_unitario;
}
