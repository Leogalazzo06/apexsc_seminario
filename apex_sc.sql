--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-11-16 20:27:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 228 (class 1259 OID 24877)
-- Name: abono_pileta; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.abono_pileta OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24876)
-- Name: abono_pileta_id_abono_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abono_pileta_id_abono_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.abono_pileta_id_abono_seq OWNER TO postgres;

--
-- TOC entry 4930 (class 0 OID 0)
-- Dependencies: 227
-- Name: abono_pileta_id_abono_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abono_pileta_id_abono_seq OWNED BY public.abono_pileta.id_abono;


--
-- TOC entry 222 (class 1259 OID 24790)
-- Name: cuota; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuota (
    id_cuota integer NOT NULL,
    periodo character varying(20) NOT NULL,
    monto numeric(10,2) NOT NULL,
    estado character varying(20) NOT NULL,
    id_socio integer NOT NULL,
    pagada boolean DEFAULT false
);


ALTER TABLE public.cuota OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24789)
-- Name: cuota_id_cuota_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuota_id_cuota_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cuota_id_cuota_seq OWNER TO postgres;

--
-- TOC entry 4931 (class 0 OID 0)
-- Dependencies: 221
-- Name: cuota_id_cuota_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuota_id_cuota_seq OWNED BY public.cuota.id_cuota;


--
-- TOC entry 230 (class 1259 OID 24892)
-- Name: entrada; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.entrada OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24891)
-- Name: entrada_id_entrada_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.entrada_id_entrada_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.entrada_id_entrada_seq OWNER TO postgres;

--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 229
-- Name: entrada_id_entrada_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.entrada_id_entrada_seq OWNED BY public.entrada.id_entrada;


--
-- TOC entry 226 (class 1259 OID 24836)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24835)
-- Name: evento_id_evento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.evento_id_evento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.evento_id_evento_seq OWNER TO postgres;

--
-- TOC entry 4933 (class 0 OID 0)
-- Dependencies: 225
-- Name: evento_id_evento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.evento_id_evento_seq OWNED BY public.evento.id_evento;


--
-- TOC entry 224 (class 1259 OID 24802)
-- Name: pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    fecha date NOT NULL,
    monto numeric(10,2) NOT NULL,
    metodo character varying(20) NOT NULL,
    id_cuota integer NOT NULL
);


ALTER TABLE public.pago OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24801)
-- Name: pago_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pago_id_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pago_id_pago_seq OWNER TO postgres;

--
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 223
-- Name: pago_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pago_id_pago_seq OWNED BY public.pago.id_pago;


--
-- TOC entry 220 (class 1259 OID 24776)
-- Name: socio; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.socio OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24775)
-- Name: socio_id_socio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.socio_id_socio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.socio_id_socio_seq OWNER TO postgres;

--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 219
-- Name: socio_id_socio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.socio_id_socio_seq OWNED BY public.socio.id_socio;


--
-- TOC entry 218 (class 1259 OID 24767)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    rol character varying(20) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24766)
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 4732 (class 2604 OID 24880)
-- Name: abono_pileta id_abono; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abono_pileta ALTER COLUMN id_abono SET DEFAULT nextval('public.abono_pileta_id_abono_seq'::regclass);


--
-- TOC entry 4728 (class 2604 OID 24793)
-- Name: cuota id_cuota; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota ALTER COLUMN id_cuota SET DEFAULT nextval('public.cuota_id_cuota_seq'::regclass);


--
-- TOC entry 4736 (class 2604 OID 24895)
-- Name: entrada id_entrada; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada ALTER COLUMN id_entrada SET DEFAULT nextval('public.entrada_id_entrada_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 24839)
-- Name: evento id_evento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento ALTER COLUMN id_evento SET DEFAULT nextval('public.evento_id_evento_seq'::regclass);


--
-- TOC entry 4730 (class 2604 OID 24805)
-- Name: pago id_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago ALTER COLUMN id_pago SET DEFAULT nextval('public.pago_id_pago_seq'::regclass);


--
-- TOC entry 4726 (class 2604 OID 24779)
-- Name: socio id_socio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio ALTER COLUMN id_socio SET DEFAULT nextval('public.socio_id_socio_seq'::regclass);


--
-- TOC entry 4725 (class 2604 OID 24770)
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- TOC entry 4922 (class 0 OID 24877)
-- Dependencies: 228
-- Data for Name: abono_pileta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.abono_pileta (id_abono, id_socio, fecha_inicio, fecha_fin, precio, estado, tipo, estado_pago) FROM stdin;
9	40	2025-04-06	2025-12-08	5000.00	Vigente	FAMILIAR	Pagado
\.


--
-- TOC entry 4916 (class 0 OID 24790)
-- Dependencies: 222
-- Data for Name: cuota; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cuota (id_cuota, periodo, monto, estado, id_socio, pagada) FROM stdin;
19	2025-01-01	15000.00	Pagada	37	t
20	2025-01-01	15000.00	Pagada	44	t
21	2025-01-01	15000.00	Pendiente	33	f
\.


--
-- TOC entry 4924 (class 0 OID 24892)
-- Dependencies: 230
-- Data for Name: entrada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.entrada (id_entrada, id_evento, nombre_comprador, dni, tipo, precio, codigo_qr, usado, fecha_compra) FROM stdin;
12	11	Leonel	45103729	Palco	9000.00	66b7d776-1354-4083-916d-e1f3ef672368	t	2025-11-16 18:47:15.400254
\.


--
-- TOC entry 4920 (class 0 OID 24836)
-- Dependencies: 226
-- Data for Name: evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evento (id_evento, nombre, fecha_hora, aforo, id_usuario, lugar, precio_general, precio_socio, precio_invitado) FROM stdin;
11	Argentina Vs. Brasil	2025-12-23 00:00:00	52000	9	La Bombonera	7900.00	7000.00	9000.00
\.


--
-- TOC entry 4918 (class 0 OID 24802)
-- Dependencies: 224
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pago (id_pago, fecha, monto, metodo, id_cuota) FROM stdin;
\.


--
-- TOC entry 4914 (class 0 OID 24776)
-- Dependencies: 220
-- Data for Name: socio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.socio (id_socio, dni, nombre, apellido, estado, id_usuario_creador, categoria, periodicidad, foto_ruta) FROM stdin;
33	35678987	Cristian	Romero	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\cuti.png
36	31234567	Rodrigo	De Paul	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\rodrigo-de-paul.jpg
37	34789654	Alexis	Mac Allister	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\alexis.jpg
38	44234432	Enzo	Fernandez	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\enzo.jpg
39	32123456	Julián	Alvarez	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\julian.jpg
40	33245678	Ángel	Di Maria	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\dimaria.jpeg
43	32456789	Lionel	Messi	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\messi.jpeg
44	45103729	Leonel	Galazzo	Activo	9	Adulto	Mensual	C:\\Users\\leone\\OneDrive\\Desktop\\Universidad 3er año\\Seminario-Práctica\\messi.jpeg
\.


--
-- TOC entry 4912 (class 0 OID 24767)
-- Dependencies: 218
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, username, password, rol) FROM stdin;
9	Admin	admin	Administrador
13	Caja	1234	Cajero
\.


--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 227
-- Name: abono_pileta_id_abono_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abono_pileta_id_abono_seq', 10, true);


--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 221
-- Name: cuota_id_cuota_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuota_id_cuota_seq', 21, true);


--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 229
-- Name: entrada_id_entrada_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.entrada_id_entrada_seq', 13, true);


--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 225
-- Name: evento_id_evento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.evento_id_evento_seq', 12, true);


--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 223
-- Name: pago_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pago_id_pago_seq', 1, true);


--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 219
-- Name: socio_id_socio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.socio_id_socio_seq', 44, true);


--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 13, true);


--
-- TOC entry 4754 (class 2606 OID 24883)
-- Name: abono_pileta abono_pileta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abono_pileta
    ADD CONSTRAINT abono_pileta_pkey PRIMARY KEY (id_abono);


--
-- TOC entry 4748 (class 2606 OID 24795)
-- Name: cuota cuota_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota
    ADD CONSTRAINT cuota_pkey PRIMARY KEY (id_cuota);


--
-- TOC entry 4756 (class 2606 OID 24903)
-- Name: entrada entrada_codigo_qr_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_codigo_qr_key UNIQUE (codigo_qr);


--
-- TOC entry 4758 (class 2606 OID 24901)
-- Name: entrada entrada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_pkey PRIMARY KEY (id_entrada);


--
-- TOC entry 4752 (class 2606 OID 24841)
-- Name: evento evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 4750 (class 2606 OID 24807)
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 4744 (class 2606 OID 24783)
-- Name: socio socio_dni_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_dni_key UNIQUE (dni);


--
-- TOC entry 4746 (class 2606 OID 24781)
-- Name: socio socio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_pkey PRIMARY KEY (id_socio);


--
-- TOC entry 4740 (class 2606 OID 24772)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 4742 (class 2606 OID 24774)
-- Name: usuario usuario_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- TOC entry 4761 (class 2606 OID 24796)
-- Name: cuota cuota_id_socio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota
    ADD CONSTRAINT cuota_id_socio_fkey FOREIGN KEY (id_socio) REFERENCES public.socio(id_socio);


--
-- TOC entry 4765 (class 2606 OID 24904)
-- Name: entrada entrada_id_evento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_id_evento_fkey FOREIGN KEY (id_evento) REFERENCES public.evento(id_evento);


--
-- TOC entry 4763 (class 2606 OID 24842)
-- Name: evento evento_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 4764 (class 2606 OID 24884)
-- Name: abono_pileta fk_abono_socio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abono_pileta
    ADD CONSTRAINT fk_abono_socio FOREIGN KEY (id_socio) REFERENCES public.socio(id_socio);


--
-- TOC entry 4762 (class 2606 OID 24808)
-- Name: pago pago_id_cuota_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_id_cuota_fkey FOREIGN KEY (id_cuota) REFERENCES public.cuota(id_cuota);


--
-- TOC entry 4759 (class 2606 OID 24784)
-- Name: socio socio_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_id_usuario_fkey FOREIGN KEY (id_usuario_creador) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 4760 (class 2606 OID 24911)
-- Name: socio socio_usuario_creador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_usuario_creador_fkey FOREIGN KEY (id_usuario_creador) REFERENCES public.usuario(id_usuario);


-- Completed on 2025-11-16 20:27:41

--
-- PostgreSQL database dump complete
--

