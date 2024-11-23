-- Poblado de tablas para probar el procedimiento almacenado 3

-- Insertar categorías
INSERT INTO categoria (nombre) VALUES
('Electrónica'),
('Ropa'),
('Hogar');

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio, stock, estado, id_categoria) VALUES
('Smartphone', 'Smartphone de última generación', 500.00, 50, 'disponible', 1),
('Laptop', 'Laptop para juegos', 1200.00, 30, 'disponible', 1),
('Camiseta', 'Camiseta de algodón', 20.00, 100, 'disponible', 2),
('Chaqueta', 'Chaqueta impermeable', 60.00, 40, 'disponible', 2),
('Sofá', 'Sofá de tres cuerpos', 350.00, 20, 'disponible', 3),
('Mesa', 'Mesa de comedor de madera', 200.00, 15, 'disponible', 3);

-- Insertar clientes
INSERT INTO cliente (nombre, direccion, email, telefono, contrasena) VALUES
('Juan Pérez', 'Calle Falsa 123', 'juan@example.com', '123456789', 'password123'),
('Ana López', 'Avenida Siempre Viva 456', 'ana@example.com', '987654321', 'password456');

-- Insertar órdenes
INSERT INTO orden (fecha_orden, estado, id_cliente, total) VALUES
('2024-09-01 10:00:00', 'pagada', 1, 500.00),  -- Producto comprado hace más de 30 días
('2024-08-20 15:30:00', 'pagada', 2, 200.00);  -- Producto comprado hace más de 30 días

-- Insertar detalles de órdenes
INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES
(1, 1, 1, 500.00),  -- Smartphone comprado por Juan Pérez
(2, 5, 1, 350.00);  -- Sofá comprado por Ana López
