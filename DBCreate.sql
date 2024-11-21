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

-- 5. Finalmente, crear la tabla detalle_orden 
CREATE TABLE detalle_orden (
    id_detalle SERIAL PRIMARY KEY,
    id_orden INTEGER NOT NULL,
    id_producto INTEGER NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_orden) REFERENCES orden(id_orden) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE SET NULL
);
