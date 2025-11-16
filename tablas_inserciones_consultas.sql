-- TABLA USUARIO
CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    rol character varying(20) NOT NULL
);

-- TABLA SOCIO 
CREATE TABLE public.socio (
    id_socio integer NOT NULL,
    dni character varying(15) NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido character varying(50) NOT NULL,
    estado character varying(20) NOT NULL,
    id_usuario_creador integer,
    categoria character varying(20),
    periodicidad character varying(20) DEFAULT 'Mensual'::character varying,
    foto_ruta character varying(255)
);

-- TABLA EVENTO  
CREATE TABLE public.evento (
    id_evento integer NOT NULL,
    nombre character varying(100) NOT NULL,
    fecha_hora timestamp without time zone NOT NULL,
    aforo integer NOT NULL,
    id_usuario integer NOT NULL,
    lugar character varying(100),
    precio_general numeric(10,2),
    precio_socio numeric(10,2),
    precio_invitado numeric(10,2)
);


-- TABLA ENTRADA  
CREATE TABLE public.entrada (
    id_entrada integer NOT NULL,
    id_evento integer NOT NULL,
    nombre_comprador character varying(100),
    dni character varying(20),
    tipo character varying(30),
    precio numeric(10,2),
    codigo_qr text NOT NULL,
    usado boolean DEFAULT false,
    fecha_compra timestamp without time zone DEFAULT now()
);


-- TABLA CUOTA 
CREATE TABLE public.cuota (
    id_cuota integer NOT NULL,
    periodo character varying(20) NOT NULL,
    monto numeric(10,2) NOT NULL,
    estado character varying(20) NOT NULL,
    id_socio integer NOT NULL,
    pagada boolean DEFAULT false
);


-- TABLA PAGO 
CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    fecha date NOT NULL,
    monto numeric(10,2) NOT NULL,
    metodo character varying(20) NOT NULL,
    id_cuota integer NOT NULL
);


-- TABLA ABONOPILETA  
CREATE TABLE public.abono_pileta (
    id_abono integer NOT NULL,
    id_socio integer NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL,
    precio numeric(10,2) NOT NULL,
    estado character varying(20) DEFAULT 'Vigente'::character varying NOT NULL,
    tipo character varying(20) DEFAULT 'Individual'::character varying,
    estado_pago character varying(20) DEFAULT 'Pendiente'::character varying
);

--Inserción tablas 

--abono_pileta
COPY public.abono_pileta (id_abono, id_socio, fecha_inicio, fecha_fin, precio, estado, tipo, estado_pago) FROM stdin;
9	40	2025-04-06	2025-12-08	5000.00	Vigente	FAMILIAR	Pagado
\.

--cuota
COPY public.cuota (id_cuota, periodo, monto, estado, id_socio, pagada) FROM stdin;
19	2025-01-01	15000.00	Pagada	37	t
20	2025-01-01	15000.00	Pagada	44	t
21	2025-01-01	15000.00	Pendiente	33	f
\.

--entrada
COPY public.entrada (id_entrada, id_evento, nombre_comprador, dni, tipo, precio, codigo_qr, usado, fecha_compra) FROM stdin;
12	11	Leonel	45103729	Palco	9000.00	66b7d776-1354-4083-916d-e1f3ef672368	t	2025-11-16 18:47:15.400254
\.

--evento
COPY public.evento (id_evento, nombre, fecha_hora, aforo, id_usuario, lugar, precio_general, precio_socio, precio_invitado) FROM stdin;
11	Argentina Vs. Brasil	2025-12-23 00:00:00	52000	9	La Bombonera	7900.00	7000.00	9000.00
\.

--pago
COPY public.pago (id_pago, fecha, monto, metodo, id_cuota) FROM stdin;
\.

--socio
COPY public.socio (id_socio, dni, nombre, apellido, estado, id_usuario_creador, categoria, periodicidad, foto_ruta) FROM stdin;
33	35678987	Cristian	Romero	Activo	9	Adulto	Mensual	NULL
36	31234567	Rodrigo	De Paul	Activo	9	Adulto	Mensual	NULL
37	34789654	Alexis	Mac Allister	Activo	9	Adulto	Mensual	NULL
38	44234432	Enzo	Fernandez	Activo	9	Adulto	Mensual	NULL
39	32123456	Julián	Alvarez	Activo	9	Adulto	Mensual	NULL
40	33245678	Ángel	Di Maria	Activo	9	Adulto	Mensual	NULL
43	32456789	Lionel	Messi	Activo	9	Adulto	Mensual	NULL
44	45103729	Leonel	Galazzo	Activo	9	Adulto	Mensual	NULL
\.

--usuario
COPY public.usuario (id_usuario, username, password, rol) FROM stdin;
9	Admin	admin	Administrador
13	Caja	1234	Cajero
\.

