package BDA.grupo1.model;

import lombok.Data;

@Data
public class Categoria {
    private Long id_categoria;
    private String nombre;

    public Categoria(String nombre, Long id_categoria) {
        this.nombre = nombre;
        this.id_categoria = id_categoria;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


