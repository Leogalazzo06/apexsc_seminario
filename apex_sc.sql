--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-10-05 21:50:38

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
-- TOC entry 226 (class 1259 OID 24814)
-- Name: abonopileta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.abonopileta (
    id_abono integer NOT NULL,
    tipo character varying(50) NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL,
    id_socio integer NOT NULL
);


ALTER TABLE public.abonopileta OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24813)
-- Name: abonopileta_id_abono_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abonopileta_id_abono_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.abonopileta_id_abono_seq OWNER TO postgres;

--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 225
-- Name: abonopileta_id_abono_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abonopileta_id_abono_seq OWNED BY public.abonopileta.id_abono;


--
-- TOC entry 222 (class 1259 OID 24790)
-- Name: cuota; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuota (
    id_cuota integer NOT NULL,
    periodo character varying(20) NOT NULL,
    monto numeric(10,2) NOT NULL,
    estado character varying(20) NOT NULL,
    id_socio integer NOT NULL
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
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 221
-- Name: cuota_id_cuota_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuota_id_cuota_seq OWNED BY public.cuota.id_cuota;


--
-- TOC entry 230 (class 1259 OID 24848)
-- Name: entrada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entrada (
    id_entrada integer NOT NULL,
    codigo_qr character varying(100) NOT NULL,
    precio numeric(10,2) NOT NULL,
    estado_uso character varying(20) NOT NULL,
    id_evento integer NOT NULL,
    id_socio integer NOT NULL
);


ALTER TABLE public.entrada OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24847)
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
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 229
-- Name: entrada_id_entrada_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.entrada_id_entrada_seq OWNED BY public.entrada.id_entrada;


--
-- TOC entry 228 (class 1259 OID 24836)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evento (
    id_evento integer NOT NULL,
    nombre character varying(100) NOT NULL,
    fecha_hora timestamp without time zone NOT NULL,
    aforo integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24835)
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
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 227
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
-- TOC entry 4927 (class 0 OID 0)
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
    id_usuario integer NOT NULL
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
-- TOC entry 4928 (class 0 OID 0)
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
    password_hash character varying(255) NOT NULL,
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
-- TOC entry 4929 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 4729 (class 2604 OID 24817)
-- Name: abonopileta id_abono; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonopileta ALTER COLUMN id_abono SET DEFAULT nextval('public.abonopileta_id_abono_seq'::regclass);


--
-- TOC entry 4727 (class 2604 OID 24793)
-- Name: cuota id_cuota; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota ALTER COLUMN id_cuota SET DEFAULT nextval('public.cuota_id_cuota_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 24851)
-- Name: entrada id_entrada; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada ALTER COLUMN id_entrada SET DEFAULT nextval('public.entrada_id_entrada_seq'::regclass);


--
-- TOC entry 4730 (class 2604 OID 24839)
-- Name: evento id_evento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento ALTER COLUMN id_evento SET DEFAULT nextval('public.evento_id_evento_seq'::regclass);


--
-- TOC entry 4728 (class 2604 OID 24805)
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
-- TOC entry 4913 (class 0 OID 24814)
-- Dependencies: 226
-- Data for Name: abonopileta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.abonopileta (id_abono, tipo, fecha_inicio, fecha_fin, id_socio) FROM stdin;
\.


--
-- TOC entry 4909 (class 0 OID 24790)
-- Dependencies: 222
-- Data for Name: cuota; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cuota (id_cuota, periodo, monto, estado, id_socio) FROM stdin;
\.


--
-- TOC entry 4917 (class 0 OID 24848)
-- Dependencies: 230
-- Data for Name: entrada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.entrada (id_entrada, codigo_qr, precio, estado_uso, id_evento, id_socio) FROM stdin;
\.


--
-- TOC entry 4915 (class 0 OID 24836)
-- Dependencies: 228
-- Data for Name: evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evento (id_evento, nombre, fecha_hora, aforo, id_usuario) FROM stdin;
\.


--
-- TOC entry 4911 (class 0 OID 24802)
-- Dependencies: 224
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pago (id_pago, fecha, monto, metodo, id_cuota) FROM stdin;
\.


--
-- TOC entry 4907 (class 0 OID 24776)
-- Dependencies: 220
-- Data for Name: socio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.socio (id_socio, dni, nombre, apellido, estado, id_usuario) FROM stdin;
\.


--
-- TOC entry 4905 (class 0 OID 24767)
-- Dependencies: 218
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, username, password_hash, rol) FROM stdin;
\.


--
-- TOC entry 4930 (class 0 OID 0)
-- Dependencies: 225
-- Name: abonopileta_id_abono_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abonopileta_id_abono_seq', 1, false);


--
-- TOC entry 4931 (class 0 OID 0)
-- Dependencies: 221
-- Name: cuota_id_cuota_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuota_id_cuota_seq', 1, false);


--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 229
-- Name: entrada_id_entrada_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.entrada_id_entrada_seq', 1, false);


--
-- TOC entry 4933 (class 0 OID 0)
-- Dependencies: 227
-- Name: evento_id_evento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.evento_id_evento_seq', 1, false);


--
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 223
-- Name: pago_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pago_id_pago_seq', 1, false);


--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 219
-- Name: socio_id_socio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.socio_id_socio_seq', 1, false);


--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 1, false);


--
-- TOC entry 4745 (class 2606 OID 24819)
-- Name: abonopileta abonopileta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonopileta
    ADD CONSTRAINT abonopileta_pkey PRIMARY KEY (id_abono);


--
-- TOC entry 4741 (class 2606 OID 24795)
-- Name: cuota cuota_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota
    ADD CONSTRAINT cuota_pkey PRIMARY KEY (id_cuota);


--
-- TOC entry 4749 (class 2606 OID 24855)
-- Name: entrada entrada_codigo_qr_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_codigo_qr_key UNIQUE (codigo_qr);


--
-- TOC entry 4751 (class 2606 OID 24853)
-- Name: entrada entrada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_pkey PRIMARY KEY (id_entrada);


--
-- TOC entry 4747 (class 2606 OID 24841)
-- Name: evento evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 4743 (class 2606 OID 24807)
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 4737 (class 2606 OID 24783)
-- Name: socio socio_dni_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_dni_key UNIQUE (dni);


--
-- TOC entry 4739 (class 2606 OID 24781)
-- Name: socio socio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_pkey PRIMARY KEY (id_socio);


--
-- TOC entry 4733 (class 2606 OID 24772)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 4735 (class 2606 OID 24774)
-- Name: usuario usuario_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- TOC entry 4755 (class 2606 OID 24820)
-- Name: abonopileta abonopileta_id_socio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abonopileta
    ADD CONSTRAINT abonopileta_id_socio_fkey FOREIGN KEY (id_socio) REFERENCES public.socio(id_socio);


--
-- TOC entry 4753 (class 2606 OID 24796)
-- Name: cuota cuota_id_socio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuota
    ADD CONSTRAINT cuota_id_socio_fkey FOREIGN KEY (id_socio) REFERENCES public.socio(id_socio);


--
-- TOC entry 4757 (class 2606 OID 24856)
-- Name: entrada entrada_id_evento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_id_evento_fkey FOREIGN KEY (id_evento) REFERENCES public.evento(id_evento);


--
-- TOC entry 4758 (class 2606 OID 24861)
-- Name: entrada entrada_id_socio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entrada
    ADD CONSTRAINT entrada_id_socio_fkey FOREIGN KEY (id_socio) REFERENCES public.socio(id_socio);


--
-- TOC entry 4756 (class 2606 OID 24842)
-- Name: evento evento_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evento
    ADD CONSTRAINT evento_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 4754 (class 2606 OID 24808)
-- Name: pago pago_id_cuota_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_id_cuota_fkey FOREIGN KEY (id_cuota) REFERENCES public.cuota(id_cuota);


--
-- TOC entry 4752 (class 2606 OID 24784)
-- Name: socio socio_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


-- Completed on 2025-10-05 21:50:39

--
-- PostgreSQL database dump complete
--

