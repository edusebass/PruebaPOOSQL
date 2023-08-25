CREATE DATABASE tienda;

USE tienda;

CREATE TABLE Proveedores (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             nombre_empresa VARCHAR(255) NOT NULL,
                             direccion VARCHAR(255),
                             telefono VARCHAR(20)
);

CREATE TABLE Productos (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(255) NOT NULL,
                           precio DECIMAL(10, 2) NOT NULL,
                           descripcion TEXT,
                           id_proveedor INT,
                           FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id)
);

INSERT INTO Proveedores (nombre_empresa, direccion, telefono)
VALUES
    ('Proveedor A', 'Calle 123, Ciudad', '123-456-7890'),
    ('Proveedor B', 'Avenida 456, Ciudad', '987-654-3210');

INSERT INTO Productos (nombre, precio, descripcion, id_proveedor)
VALUES
    ('Producto 1', 10.99, 'Descripción del Producto 1', 1),
    ('Producto 2', 24.99, 'Descripción del Producto 2', 2),
    ('Producto 3', 8.49, 'Descripción del Producto 3', 1);


SELECT * FROM Proveedores;

SELECT * FROM Productos;


SELECT
    p.nombre,
    p.precio,
    p.descripcion,
    pr.nombre_empresa,
    pr.direccion,
    pr.telefono
FROM
    Productos p
        JOIN
    Proveedores pr ON p.id_proveedor = pr.id
WHERE
        p.nombre = 'Llanta';


UPDATE Productos
SET precio = 15.99,
    descripcion = 'Nueva descripción del Producto 1'
WHERE nombre = 'Producto 1';

UPDATE Proveedores
SET nombre_empresa = 'Nuevo Nombre de Proveedor',
    direccion = 'Nueva  ',
    telefono = '324234324'
WHERE id = (SELECT id_proveedor FROM Productos WHERE nombre = 'Producto 1');