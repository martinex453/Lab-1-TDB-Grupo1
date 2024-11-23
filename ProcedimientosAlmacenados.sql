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

CREATE OR REPLACE PROCEDURE registrar_orden(
    p_id_cliente INT,
    lista_detalleOrden JSON
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_orden INT;
    v_total DECIMAL(10, 2) := 0;
    detalleOrden JSONB;
    v_id_producto INT;
    v_cantidad INT;
    v_precio_unitario DECIMAL(10, 2);
    v_stock_actual INT;
BEGIN
    -- Se crea la orden
    INSERT INTO orden (id_cliente, total, estado)
    VALUES (p_id_cliente, 0, 'pagada')
    RETURNING id_orden INTO v_id_orden;
    
    -- Iterar sobre cada detalle de producto del JSON
    FOR detalleOrden IN SELECT * FROM jsonb_array_elements(lista_detalleOrden::jsonb)
    LOOP
	-- Extraer información del detalle
        v_id_producto := (detalleOrden->>'id_producto')::INT;
        v_cantidad := (detalleOrden->>'cantidad')::INT;
        v_precio_unitario := (detalleOrden->>'precio_unitario')::DECIMAL;
        
        -- Buscar el producto y verificar existencia de stock
        SELECT stock INTO v_stock_actual
        FROM producto
        WHERE id_producto = v_id_producto;
        
        -- Verificar que se haya encontrado el producto y que tenga stock suficiente
        -- Si no se puede realizar la compra debido al stock no se realiza el procedimiento
        IF v_stock_actual IS NULL THEN
            RAISE EXCEPTION 'El producto con ID % no existe.', v_id_producto;
        ELSIF v_stock_actual < v_cantidad THEN
            RAISE EXCEPTION 'Stock insuficiente para el producto con ID %.', v_id_producto;
        END IF;
        
        -- Actualizar el stock del producto antes de crear el detalle del orden
        UPDATE producto
        SET stock = stock - v_cantidad
        WHERE id_producto = v_id_producto;
        
        -- Insertar detalle en la tabla detalle_orden
        INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario)
        VALUES (v_id_orden, v_id_producto, v_cantidad, v_precio_unitario);
        
        -- Calcular el total de la orden
        v_total := v_total + (v_cantidad * v_precio_unitario);
    END LOOP;
        
    -- Actualizar el total de la orden que se ha efectuado
    UPDATE orden
    SET total = v_total
    WHERE id_orden = v_id_orden;

END;
$$;
    



