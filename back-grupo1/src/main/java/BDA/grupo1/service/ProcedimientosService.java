package BDA.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.math.BigDecimal;
import java.sql.Types;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcedimientosService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Sql2o sql2o;

    public Map<String, List<String>> obtenerReporteAgrupado() {
        // Llamar al procedimiento almacenado para crear la tabla temporal
        jdbcTemplate.execute("CALL reporte_top_usuarios_querys()");

        // Consultar la tabla temporal generada por el procedimiento
        List<Map<String, Object>> filas = jdbcTemplate.queryForList("SELECT * FROM tmp_reporte_top_usuarios");

        // Agrupar los resultados por cliente
        Map<String, List<String>> reporte = new LinkedHashMap<>();
        for (Map<String, Object> fila : filas) {
            String nombreCliente = (String) fila.get("nombre_cliente");
            String consultaRealizada = (String) fila.get("consulta_realizada");
            String fechaConsulta = fila.get("fecha_consulta").toString();

            reporte.putIfAbsent(nombreCliente, new ArrayList<>());
            reporte.get(nombreCliente).add("[" + fechaConsulta + "] " + consultaRealizada);
        }

        return reporte;
    }

    public String generarReporteAgrupado() {
        Map<String, List<String>> reporteAgrupado = obtenerReporteAgrupado();

        StringBuilder reporteString = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : reporteAgrupado.entrySet()) {
            String nombreCliente = entry.getKey();
            List<String> consultas = entry.getValue();

            // Encabezado del usuario
            reporteString.append("Cliente: ").append(nombreCliente).append("\n");

            // Consultas realizadas
            for (String consulta : consultas) {
                reporteString.append("    ").append(consulta).append("\n");
            }
            reporteString.append("\n");
        }
        return reporteString.toString();
    }

    public void aplicarDescuentoACategoria(int idCategoria, float descuento) {
        // Usar la sintaxis adecuada para invocar procedimientos almacenados
        String sql = "CALL aplicar_descuento_a_categoria(:idCategoria, :descuento)";  // Sintaxis correcta

        BigDecimal nuevoDescuento = new BigDecimal(descuento);
        // Ejecutar el procedimiento con los parámetros correspondientes
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
            .addParameter("idCategoria", idCategoria)
                    .addParameter("descuento", nuevoDescuento.setScale(4, BigDecimal.ROUND_HALF_UP))
                    .executeUpdate();
        }
    }

}