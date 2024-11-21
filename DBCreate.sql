-- 1. Crear tabla categoría
CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- 2. Crear tabla cliente
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    contrasena VARCHAR(90) NOT NULL
);

-- 3. Crear tabla producto
CREATE TABLE producto (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    estado VARCHAR(50) CHECK (estado IN ('disponible', 'agotado')) NOT NULL,
    id_categoria INTEGER,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE SET NULL
);

-- 4. Crear tabla orden
CREATE TABLE orden (
    id_orden SERIAL PRIMARY KEY,
    fecha_orden TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) CHECK (estado IN ('pendiente', 'pagada', 'enviada')) NOT NULL,
    id_cliente INTEGER,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL
);

-- 5. Crear la tabla detalle_orden 
CREATE TABLE detalle_orden (
    id_detalle SERIAL PRIMARY KEY,
    id_orden INTEGER NOT NULL,
    id_producto INTEGER NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_orden) REFERENCES orden(id_orden) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE SET NULL
);

-- 6. Tabla trigger
CREATE TABLE detalles_querys (
    id_detalle SERIAL PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL,       -- Usuario que realizó la operación
    operacion VARCHAR(10) NOT NULL,      -- Tipo de operación (INSERT, UPDATE, DELETE)
    tabla_afectada VARCHAR(100) NOT NULL,-- Nombre de la tabla afectada
    consulta TEXT NOT NULL,              -- Query ejecutada
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Fecha y hora de la operación
);

CREATE OR REPLACE FUNCTION registrar_querys()
RETURNS TRIGGER AS $$
BEGIN
    -- Insertar información en la tabla de auditoría
    INSERT INTO detalles_querys (usuario, operacion, tabla_afectada, consulta)
    VALUES (
        session_user,                    -- Usuario que ejecutó la consulta
        TG_OP,                           -- Operación (INSERT, UPDATE, DELETE)
        TG_TABLE_NAME,                   -- Tabla afectada
        current_query()                  -- Query ejecutada
    );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_categoria
AFTER INSERT OR UPDATE OR DELETE ON categoria
FOR EACH ROW
EXECUTE FUNCTION registrar_querys();

CREATE TRIGGER trigger_cliente
AFTER INSERT OR UPDATE OR DELETE ON cliente
FOR EACH ROW
EXECUTE FUNCTION registrar_querys();

CREATE TRIGGER trigger_producto
AFTER INSERT OR UPDATE OR DELETE ON producto
FOR EACH ROW
EXECUTE FUNCTION registrar_querys();

CREATE TRIGGER trigger_orden
AFTER INSERT OR UPDATE OR DELETE ON orden
FOR EACH ROW
EXECUTE FUNCTION registrar_querys();

CREATE TRIGGER trigger_detalle_orden
AFTER INSERT OR UPDATE OR DELETE ON detalle_orden
FOR EACH ROW
EXECUTE FUNCTION registrar_querys();



