package BDA.grupo1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Orden {
    private Long id_orden;
    private Timestamp fecha_orden;
    private String estado;
    private Integer id_cliente;
    private Double total;

    public Orden(Long id_orden, Timestamp fecha_orden, String estado, Integer id_cliente, Double total) {
        this.id_orden = id_orden;
        this.fecha_orden = fecha_orden;
        this.estado = estado;
        this.id_cliente = id_cliente;
        this.total = total;
    }

    public Long getId_orden() {
        return id_orden;
    }

    public void setId_orden(Long id_orden) {
        this.id_orden = id_orden;
    }

    public Timestamp getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Timestamp fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

