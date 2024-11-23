-- \connect cambia la conexión a la nueva base de datos
\c lab1tbd

-- Insertar categorías
INSERT INTO categoria (nombre) VALUES
('Tecnologia'),
('Ropa'),
('Hogar');

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio, stock, estado, id_categoria) VALUES
('Smartphone', 'Smartphone de ultima generacion', 500000, 50, 'disponible', 1),
('Laptop', 'Laptop para juegos', 1200000, 30, 'disponible', 1),
('Camiseta', 'Camiseta de algodon', 20000, 100, 'disponible', 2),
('Chaqueta', 'Chaqueta impermeable', 60000, 40, 'disponible', 2),
('Sofa', 'Sofa de tres cuerpos', 350000, 20, 'disponible', 3),
('Mesa', 'Mesa de comedor de madera', 200000, 15, 'disponible', 3);

-- Insertar clientes
INSERT INTO cliente (nombre, direccion, email, telefono, contrasena, rol) VALUES
('Administrador', 'Calle Admin 101', 'admin@example.com', '987654321', 'admin123', 'admin'),
('Juan Perez', 'Calle Falsa 123', 'juan@example.com', '123456789', 'password123', 'cliente'),
('Ana Lopez', 'Avenida Siempre Viva 456', 'ana@example.com', '987654321', 'password456', 'cliente'),
('Carlos Garcia', 'Calle Real 789', 'carlos@example.com', '543216789', 'password789', 'cliente'),
('Lucia Martinez', 'Calle Luna 321', 'lucia@example.com', '321654987', 'password321', 'cliente'),
('Pedro Rodriguez', 'Calle Sol 456', 'pedro@example.com', '654987123', 'password987', 'cliente'),
('Marta Fernandez', 'Calle Estrella 789', 'marta@example.com', '876543210', 'password654', 'cliente'),
('Felipe Sanchez', 'Calle Río 101', 'felipe@example.com', '112233445', 'password101', 'cliente');

-- Insertar órdenes
INSERT INTO orden (fecha_orden, estado, id_cliente, total) VALUES
('2024-09-01 10:00:00', 'pagada', 2, 500000),
('2024-08-20 15:30:00', 'pagada', 3, 350000),
('2024-11-20 09:00:00', 'pagada', 4, 1700000),
('2024-11-20 12:30:00', 'pagada', 5, 80000),
('2024-11-21 15:45:00', 'pagada', 4, 1700000),
('2024-11-22 14:00:00', 'pagada', 5, 300000),
('2024-11-23 17:30:00', 'pagada', 4, 200000),
('2024-11-23 18:45:00', 'pagada', 5, 150000),
('2024-11-24 10:00:00', 'pagada', 6, 1700000),
('2024-11-24 11:30:00', 'pagada', 7, 1700000),
('2024-11-25 09:30:00', 'pagada', 8, 1700000); 

-- Insertar detalles de órdenes
INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES
(1, 1, 1, 500000),
(2, 5, 1, 350000),
(3, 1, 1, 500000),
(3, 2, 1, 1200000),
(4, 5, 1, 350000),
(5, 1, 1, 500000),
(5, 2, 1, 1200000),
(6, 5, 1, 350000),
(7, 3, 1, 20000),
(8, 1, 1, 500000),
(8, 3, 1, 20000),
(9, 1, 1, 500000),
(9, 2, 1, 1200000),
(10, 1, 1, 500000),
(10, 2, 1, 1200000),
(11, 1, 1, 500000),
(11, 2, 1, 1200000);
