CREATE OR REPLACE PROCEDURE reporte_top_usuarios_querys()
LANGUAGE plpgsql
AS $$
BEGIN
    -- Crear la tabla temporal
    CREATE TEMP TABLE IF NOT EXISTS tmp_reporte_top_usuarios (
        nombre_cliente VARCHAR(255),
        consulta_realizada TEXT,
        fecha_consulta TIMESTAMP
    );

    -- Insertar los datos en la tabla temporal
    INSERT INTO tmp_reporte_top_usuarios (nombre_cliente, consulta_realizada, fecha_consulta)
    SELECT 
        c.nombre AS nombre_cliente,
        dq.consulta AS consulta_realizada,
        dq.fecha AS fecha_consulta
    FROM 
        detalles_querys dq
    JOIN 
        cliente c ON dq.cliente_id = c.id_cliente
    WHERE 
        dq.cliente_id IN (
            SELECT cliente_id
            FROM (
                SELECT 
                    dq.cliente_id, 
                    COUNT(*) AS total_operaciones
                FROM 
                    detalles_querys dq
                GROUP BY 
                    dq.cliente_id
                ORDER BY 
                    total_operaciones DESC
                LIMIT 3
            ) top_clientes
        )
    ORDER BY 
        c.nombre, 
        dq.fecha DESC;
    
    -- Esto termina el procedimiento sin necesidad de un RETURN
END;
$$;

CREATE OR REPLACE PROCEDURE aplicar_descuento_a_categoria(
    p_id_categoria INT,
	p_descuento NUMERIC(10,4)

)
LANGUAGE plpgsql
AS $$
DECLARE
    v_fecha_ultima_venta DATE;
	v_producto RECORD;
BEGIN
    -- Iterar sobre todos los productos de la categoría
    FOR v_producto IN
        SELECT p.id_producto
        FROM producto p
        WHERE p.id_categoria = p_id_categoria
    LOOP
        -- Obtener la fecha de la última venta de cada producto
        SELECT MAX(o.fecha_orden)
        INTO v_fecha_ultima_venta
        FROM detalle_orden deto
        JOIN orden o ON deto.id_orden = o.id_orden
        WHERE deto.id_producto = v_producto.id_producto;

        -- Si no se ha vendido en los últimos 30 días, aplicar el descuento
        IF v_fecha_ultima_venta IS NULL OR v_fecha_ultima_venta < CURRENT_DATE - INTERVAL '30 days' THEN
            -- Actualizar el precio del producto aplicando el descuento
            UPDATE producto
            SET precio = precio * (1 - p_descuento)
            WHERE id_producto = v_producto.id_producto;
        END IF;
    END LOOP;
END;
$$;