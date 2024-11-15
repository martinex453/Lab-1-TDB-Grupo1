package BDA.grupo1.repository;

import BDA.grupo1.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ProductoRepositoryImp implements ProductoRepository{

    @Autowired
    private Sql2o sql2o;

    public Producto crear(Producto producto) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO producto (id_producto, nombre, descripcion, precio, stock, estado, id_categoria) " +
                    "VALUES (:id_producto, :nombre, :descripcion, :precio, :stock, :estado, :id_categoria)";
            con.createQuery(sql)
                    .addParameter("id_producto", producto.getId_producto())
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria())
                    .executeUpdate();
            return producto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Producto> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM producto";
            return con.createQuery(sql).executeAndFetch(Producto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Producto> getProductoPage(int page, int pageSize){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM producto ORDER BY id_producto LIMIT :pageSize OFFSET :offset";
            int offset = (page - 1) * pageSize;

            try(Connection con2 = sql2o.open()) {
                return con.createQuery(sql)
                        .addParameter("pageSize",pageSize)
                        .addParameter("offset",offset)
                        .executeAndFetch(Producto.class);
            }
        }
    }

    public String update(Producto producto, Integer id_producto) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE producto SET nombre = :nombre, descripcion = :descripcion, precio = :precio, " +
                    "stock = :stock, estado = :estado, id_categoria = :id_categoria " +
                    "WHERE id_producto = :id_producto";
            con.createQuery(sql)
                    .addParameter("id_producto", id_producto)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria())
                    .executeUpdate();
            return "Se actualizó el producto con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Fallo al actualizar el producto";
        }
    }

    public void delete(Integer id_producto) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM producto WHERE id_producto = :id_producto";
            con.createQuery(sql)
                    .addParameter("id_producto", id_producto)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
