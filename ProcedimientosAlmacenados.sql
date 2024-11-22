CREATE OR REPLACE PROCEDURE reporte_top_usuarios_querys()
LANGUAGE plpgsql
AS $$
DECLARE
    v_cliente_id INTEGER;
    v_nombre_cliente VARCHAR(255);
    v_total_operaciones INTEGER;
    v_query TEXT;
    v_fecha TIMESTAMP;
BEGIN
    -- Obtener los usuarios que realizaron más queries
    FOR v_cliente_id, v_nombre_cliente, v_total_operaciones IN
        SELECT 
            dq.cliente_id, 
            c.nombre, 
            COUNT(*) AS total_operaciones
        FROM 
            detalles_querys dq
        JOIN 
            cliente c ON dq.cliente_id = c.id_cliente
        GROUP BY 
            dq.cliente_id, c.nombre
        ORDER BY 
            total_operaciones DESC
        LIMIT 3  -- Mostrar el top 3 de usuarios más activos
    LOOP
        -- Mostrar el resumen del usuario
        RAISE NOTICE 'Cliente: %, Total Queries: %', 
                     v_nombre_cliente, v_total_operaciones;

        -- Mostrar las consultas realizadas por este usuario
        FOR v_query, v_fecha IN
            SELECT 
                dq.consulta, 
                dq.fecha
            FROM 
                detalles_querys dq
            WHERE 
                dq.cliente_id = v_cliente_id
            ORDER BY 
                dq.fecha DESC
        LOOP
            RAISE NOTICE '    Consulta: %, Fecha: %', v_query, v_fecha;
        END LOOP;
    END LOOP;
END;
$$;

CREATE OR REPLACE PROCEDURE aplicar_descuento_a_categoria(
    p_id_categoria INT,
    p_descuento DECIMAL
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