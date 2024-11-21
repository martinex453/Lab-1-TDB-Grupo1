package BDA.grupo1.repository;

import BDA.grupo1.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImp implements ClienteRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Cliente crear(Cliente cliente) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO cliente (nombre,direccion,email,telefono,contrasena)"
                    + "VALUES (:nombre,:direccion,:email,:telefono,:contrasena)";
            con.createQuery(sql)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("contrasena", cliente.getContrasena())
                    .executeUpdate();
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> getAll(){
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM cliente";
            return con.createQuery(sql).executeAndFetch(Cliente.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public String update(Cliente cliente, Integer id_cliente) {
        try (Connection con = sql2o.open()) {
            String sql = "update categoria set nombre = :nombre, direccion = :direccion, email = :email, telefono = :telefono, contrasena = :contrasena"
                    + " WHERE id_cliente = :id_cliente";
            con.createQuery(sql)
                    .addParameter("id_cliente", id_cliente)
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("contrasena", cliente.getContrasena())
                    .executeUpdate();
            return "Se actualizo el cliente con exito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Fallo al actualizar el cliente";
        }
    }

    @Override
    public void delete(Integer id_cliente){
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM cliente WHERE id_cliente = :id_cliente";
            con.createQuery(sql)
                    .addParameter("id_cliente", id_cliente)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Optional<Cliente> getClienteByCorreo(String email) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM cliente WHERE email = :email";
            return Optional.ofNullable(con.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(Cliente.class));
        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener el cliente: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente findByEmail(String email) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM cliente WHERE email = :email";
            return con.createQuery(sql)
                    .addParameter("email", email)
                    .executeAndFetchFirst(Cliente.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
