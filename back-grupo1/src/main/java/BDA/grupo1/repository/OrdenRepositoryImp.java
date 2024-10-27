package BDA.grupo1.repository;

import BDA.grupo1.model.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class OrdenRepositoryImp implements OrdenRepository{

    @Autowired
    private Sql2o sql2o;

    public Orden crear(Orden orden) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO orden (id_orden, fecha, estado, id_cliente, total) " +
                    "VALUES (:id_orden, :fecha, :estado, :id_cliente, :total)";
            con.createQuery(sql)
                    .addParameter("id_orden", orden.getId_orden())
                    .addParameter("fecha", orden.getFecha())
                    .addParameter("estado", orden.getEstado())
                    .addParameter("id_cliente", orden.getId_cliente())
                    .addParameter("total", orden.getTotal())
                    .executeUpdate();
            return orden;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Orden> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM orden";
            return con.createQuery(sql).executeAndFetch(Orden.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String update(Orden orden, Integer id_orden) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE orden SET fecha = :fecha, estado = :estado, id_cliente = :id_cliente, total = :total " +
                    "WHERE id_orden = :id_orden";
            con.createQuery(sql)
                    .addParameter("id_orden", id_orden)
                    .addParameter("fecha", orden.getFecha())
                    .addParameter("estado", orden.getEstado())
                    .addParameter("id_cliente", orden.getId_cliente())
                    .addParameter("total", orden.getTotal())
                    .executeUpdate();
            return "Se actualizó la orden con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Fallo al actualizar la orden";
        }
    }

    public void delete(Integer id_orden) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM orden WHERE id_orden = :id_orden";
            con.createQuery(sql)
                    .addParameter("id_orden", id_orden)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
