package BDA.grupo1.repository;

import BDA.grupo1.model.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrdenRepositoryImp implements OrdenRepository{

    @Autowired
    private Sql2o sql2o;

    public Orden crear(Orden orden) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO orden (fecha_orden, estado, id_cliente, total) " +
                    "VALUES (:fecha_orden, :estado, :id_cliente, :total)";
            con.createQuery(sql)
                    .addParameter("fecha_orden", orden.getFecha_orden())
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
            String sql = "UPDATE orden SET fecha_orden = :fecha_orden, estado = :estado, id_cliente = :id_cliente, total = :total " +
                    "WHERE id_orden = :id_orden";
            con.createQuery(sql)
                    .addParameter("id_orden", id_orden)
                    .addParameter("fecha_orden", orden.getFecha_orden())
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

    public List<Orden> getOrdenPages(int page, int pageSize){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM orden ORDER BY CASE WHEN estado = 'pagada' THEN 1 ELSE 2 END, id_orden LIMIT :pageSize OFFSET :offset";
            Integer offset = (page - 1) * pageSize;

            try (Connection con2 = sql2o.open()) {
                return con.createQuery(sql)
                        .addParameter("pageSize",pageSize)
                        .addParameter("offset",offset)
                        .executeAndFetch(Orden.class);
            }
        }
    }
    public List<Orden> getOrdenByUserId(int id) {
        String sql = "SELECT * FROM orden WHERE id_cliente = :id_cliente";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id_cliente", id)
                    .executeAndFetch(Orden.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Orden getOrdenById(int id) {
        String sql = "SELECT * FROM orden WHERE id_orden = :id_orden";

        try (Connection con = sql2o.open()) {
            List<Orden> result = con.createQuery(sql)
                    .addParameter("id_orden", id)
                    .executeAndFetch(Orden.class);
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null en caso de error
        }
    }

    public Integer getOrdenIDByTimestamp(){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT MAX(id_orden) FROM orden";
            return con.createQuery(sql)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Orden> getOrdersPageUser(Integer User, int page, int pageSize){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM orden WHERE id_cliente = :id_cliente ORDER BY id_orden LIMIT :pageSize OFFSET :offset ";
            Integer offset = (page - 1) * pageSize;

            try (Connection con2 = sql2o.open()) {
                return con.createQuery(sql)
                        .addParameter("pageSize",pageSize)
                        .addParameter("offset",offset)
                        .addParameter("id_cliente", User)
                        .executeAndFetch(Orden.class);
            }

        }
    }


}
