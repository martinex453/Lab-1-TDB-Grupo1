package BDA.grupo1.repository;

import BDA.grupo1.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class CategoriaRepositoryImp implements CategoriaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Categoria crear(Categoria categoria) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO categoria (id_categoria, nombre) VALUES (:id_categoria, :nombre)";
            conn.createQuery(sql)
                    .addParameter("id_categoria", categoria.getId_categoria())
                    .addParameter("nombre",categoria.getNombre())
                    .executeUpdate();
            return categoria;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Categoria> getAll(){
        try(Connection conn = sql2o.open()) {
            String sql = "SELECT * FROM categoria";
            return conn.createQuery(sql).executeAndFetch(Categoria.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String update(Categoria categoria, Integer id) {
        try(Connection conn = sql2o.open()) {
            String updatesql = "update categoria set nombre = :nombre where id_categoria = :id_categoria";
            conn.createQuery(updatesql)
                    .addParameter("id_categoria", id)
                    .addParameter("nombre", categoria.getNombre())
                    .executeUpdate();
            return "Categoria actualizada";
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try(Connection conn = sql2o.open()) {
            String sql = "DELETE FROM categoria WHERE id_categoria = :id_categoria";
            conn.createQuery(sql)
                    .addParameter("id_categoria", id)
                    .executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
