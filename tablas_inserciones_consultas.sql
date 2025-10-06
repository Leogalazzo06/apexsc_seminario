-- TABLA USUARIO
CREATE TABLE Usuario (
    id_usuario SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

-- TABLA SOCIO 
CREATE TABLE Socio (
    id_socio SERIAL PRIMARY KEY,
    dni VARCHAR(15) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    id_usuario INT NOT NULL REFERENCES usuario(id_usuario)
);

-- TABLA EVENTO  
CREATE TABLE Evento (
    id_evento SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    aforo INT NOT NULL,
    id_usuario INT NOT NULL REFERENCES usuario(id_usuario)
);

-- TABLA ENTRADA  
CREATE TABLE entrada (
    id_entrada SERIAL PRIMARY KEY,
    codigo_qr VARCHAR(100) NOT NULL UNIQUE,
    precio NUMERIC(10,2) NOT NULL,
    estado_uso VARCHAR(20) NOT NULL,
    id_evento INT NOT NULL REFERENCES evento(id_evento),
    id_socio INT NOT NULL REFERENCES socio(id_socio)
);

-- TABLA CUOTA 
CREATE TABLE cuota (
    id_cuota SERIAL PRIMARY KEY,
    periodo VARCHAR(20) NOT NULL,
    monto NUMERIC(10,2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    id_socio INT NOT NULL REFERENCES socio(id_socio)
);

-- TABLA PAGO 
CREATE TABLE pago (
    id_pago SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    monto NUMERIC(10,2) NOT NULL,
    metodo VARCHAR(20) NOT NULL,
    id_cuota INT NOT NULL REFERENCES cuota(id_cuota)
);

-- TABLA ABONOPILETA  
CREATE TABLE abonopileta (
    id_abono SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    id_socio INT NOT NULL REFERENCES socio(id_socio)
);

-- INSERCIÓN DE DATOS

-- Insertar datos en «Usuario» 
INSERT INTO Usuario (id_usuario, username, password_hash, rol)
VALUES
(1, 'admin', 'hash_admin', 'Administrador'),
(2, 'cajero1', 'hash_cajero1', 'Cajero');

-- Insertar datos en «Socio» c
INSERT INTO Socio (id_socio, dni, nombre, apellido, estado, id_usuario)
VALUES
(1, '44555666', 'Cole', 'Palmer', 'Activo', 1),
(2, '44888999', 'Vitor', 'Roque', 'Activo', 1);

-- Insertar datos en «Evento» 
INSERT INTO Evento (id_evento, nombre, fecha_hora, aforo, id_usuario)
VALUES
(1, 'Apex SC vs. Crystal Oaks', '2025-10-05 16:00:00', 5000, 1);

-- Insertar datos en «Cuota» 
INSERT INTO Cuota (id_cuota, periodo, monto, estado, id_socio)
VALUES
(1, 'Octubre 2025', 5000.00, 'Pendiente', 1),
(2, 'Octubre 2025', 5000.00, 'Pagada', 2);

-- Insertar datos en «Pago» 
INSERT INTO Pago (id_pago, fecha, monto, metodo, id_cuota)
VALUES
(1, '2025-10-01', 5000.00, 'Efectivo', 2);

-- Insertar datos en «AbonoPileta» 
INSERT INTO AbonoPileta (id_abono, tipo, fecha_inicio, fecha_fin, id_socio)
VALUES
(1, 'Mensual', '2025-10-01', '2025-10-31', 1);

-- Insertar datos en «Entrada» 
INSERT INTO Entrada (id_entrada, codigo_qr, precio, estado_uso, id_evento, id_socio)
VALUES
(1, 'QR1234567890', 2000.00, 'No usada', 1, 1),
(2, 'QR9876543210', 2000.00, 'No usada', 1, 2);

--CONSULTA DE DATOS 
SELECT id_socio, nombre, apellido, dni
FROM Socio 
WHERE dni = '44555666'; 

SELECT p.id_pago, s.nombre, s.apellido, p.monto, p.metodo
FROM Pago p
JOIN Cuota c ON p.id_cuota = c.id_cuota
JOIN Socio s ON c.id_socio = s.id_socio
WHERE p.fecha =  '2025-10-01'; 

SELECT e.nombre AS evento, s.nombre AS socio, s.apellido, en.codigo_qr, en.estado_uso
FROM Entrada en 
JOIN Evento e ON en.id_evento = e.id_evento
JOIN Socio s ON en.id_socio = s.id_socio
WHERE e.nombre LIKE '%Apex SC%'; 