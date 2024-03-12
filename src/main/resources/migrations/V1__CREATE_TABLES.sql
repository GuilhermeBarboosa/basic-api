--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2024-03-12 09:40:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 214 (class 1259 OID 35292)
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissao (
    id bigint NOT NULL,
    permissao character varying(50)
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 35295)
-- Name: id_seq_permissao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_permissao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_permissao OWNER TO postgres;

--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 215
-- Name: id_seq_permissao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_permissao OWNED BY public.permissao.id;


--
-- TOC entry 216 (class 1259 OID 35296)
-- Name: id_seq_role_tela; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_role_tela
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_role_tela OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 35297)
-- Name: id_seq_roles; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_roles
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_roles OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 35298)
-- Name: situacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.situacao (
    id bigint NOT NULL,
    situacao character varying(20),
    created timestamp with time zone,
    updated timestamp with time zone,
    actived boolean
);


ALTER TABLE public.situacao OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 35301)
-- Name: id_seq_situacao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_situacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_situacao OWNER TO postgres;

--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 219
-- Name: id_seq_situacao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_situacao OWNED BY public.situacao.id;


--
-- TOC entry 220 (class 1259 OID 35302)
-- Name: telas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telas (
    id bigint NOT NULL,
    identificador character varying(255),
    descricao character varying(255),
    unica boolean
);


ALTER TABLE public.telas OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 35307)
-- Name: id_seq_tela; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_tela
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_tela OWNER TO postgres;

--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 221
-- Name: id_seq_tela; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_tela OWNED BY public.telas.id;


--
-- TOC entry 222 (class 1259 OID 35308)
-- Name: id_seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_user OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 35309)
-- Name: role_tela; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_tela (
    id bigint NOT NULL,
    tela bigint,
    role bigint,
    permissao bigint
);


ALTER TABLE public.role_tela OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 35312)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    role character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 35315)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(255),
    email character varying(80),
    password character varying(255),
    role bigint,
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone,
    data_de_nascimento timestamp with time zone,
    rua character varying(255),
    bairro character varying(255),
    telefone character varying(20),
    cpf character varying(30),
    cep character varying(30),
    cidade character varying(30),
    numero character varying(7),
    complemento character varying(50),
    estado character varying(10),
    nome_responsavel character varying(100),
    email_responsavel character varying(100),
    celular_responsavel character varying(50)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 35292)
-- Dependencies: 214
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissao (id, permissao) FROM stdin;
1	Somente leitura
2	Leitura e escrita
3	Leitura, escrita e edição
4	Controle total
\.


--
-- TOC entry 3365 (class 0 OID 35309)
-- Dependencies: 223
-- Data for Name: role_tela; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_tela (id, tela, role, permissao) FROM stdin;
325	1	2	4
326	24	2	1
\.


--
-- TOC entry 3366 (class 0 OID 35312)
-- Dependencies: 224
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, role) FROM stdin;
1	ADMIN
2	USUARIO
\.


--
-- TOC entry 3360 (class 0 OID 35298)
-- Dependencies: 218
-- Data for Name: situacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.situacao (id, situacao, created, updated, actived) FROM stdin;
1	Aguardando	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	t
2	Aprovado	2024-01-30 00:00:00-03	2024-01-30 00:00:00-03	t
\.


--
-- TOC entry 3362 (class 0 OID 35302)
-- Dependencies: 220
-- Data for Name: telas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.telas (id, identificador, descricao, unica) FROM stdin;
1	user	Usuários	f
5	dashboard	Dashboard	t
24	role	Permissão	f
\.


--
-- TOC entry 3367 (class 0 OID 35315)
-- Dependencies: 225
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role, actived, created, updated, data_de_nascimento, rua, bairro, telefone, cpf, cep, cidade, numero, complemento, estado, nome_responsavel, email_responsavel, celular_responsavel) FROM stdin;
1	Root	root@codiub.com.br	$2a$10$6FQr1U2zGloNMuYpE27BBuIQ60N7uJfwoNe5TPERuQqXE5swHIeou	1	t	2024-03-20 00:00:00-03	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
60	Guilherme Barbosa Rocha	guilherme.rocha@codiub.com.br	$2a$10$LaEHemq4rwLo/.f0vw3pt.nCsstuj1v2JoPluX8pLQn5SiQuXhsmi	2	f	2024-03-12 09:33:08.616-03	2024-03-12 09:33:08.616-03	2000-11-29 00:00:00-02	Rua Arnaldo Augusto dos Reis	Jardim	(34) 98403-9344	019.756.946-85	38035-205	Uberaba	\N	\N	\N	\N	\N	\N
59	Guilherme Barbosa Rocha	guilherme.strg@gmail.com	$2a$10$CIf0PQwpBkp746iSbIQdkeaxzHgRtELqXXmm.o5eA1FqDNGHsrZ6K	2	t	2024-03-08 11:26:46.402-03	2024-03-12 09:36:07.298-03	2010-12-29 00:00:00-02	Rua Arnaldo Augusto dos Reis	Jardim	(34) 98403-9344	019.756.946-85	019.756.946-85	Uberaba	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 215
-- Name: id_seq_permissao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_permissao', 1, false);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 216
-- Name: id_seq_role_tela; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_role_tela', 326, true);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 217
-- Name: id_seq_roles; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_roles', 8, true);


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 219
-- Name: id_seq_situacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_situacao', 1, false);


--
-- TOC entry 3380 (class 0 OID 0)
-- Dependencies: 221
-- Name: id_seq_tela; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_tela', 1, false);


--
-- TOC entry 3381 (class 0 OID 0)
-- Dependencies: 222
-- Name: id_seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_user', 60, true);


--
-- TOC entry 3209 (class 2606 OID 35321)
-- Name: users id_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT id_user PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 35323)
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 35325)
-- Name: roles pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_id PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3205 (class 2606 OID 35327)
-- Name: role_tela role_tela_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_pkey PRIMARY KEY (id);


--
-- TOC entry 3201 (class 2606 OID 35329)
-- Name: situacao situacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacao
    ADD CONSTRAINT situacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 35331)
-- Name: telas tela_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.telas
    ADD CONSTRAINT tela_pkey PRIMARY KEY (id);


--
-- TOC entry 3213 (class 2606 OID 35332)
-- Name: users fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role) REFERENCES public.roles(id);


--
-- TOC entry 3210 (class 2606 OID 35337)
-- Name: role_tela role_tela_permissao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_permissao_fkey FOREIGN KEY (permissao) REFERENCES public.permissao(id) NOT VALID;


--
-- TOC entry 3211 (class 2606 OID 35342)
-- Name: role_tela role_tela_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_role_fkey FOREIGN KEY (role) REFERENCES public.roles(id);


--
-- TOC entry 3212 (class 2606 OID 35347)
-- Name: role_tela role_tela_tela_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_tela_fkey FOREIGN KEY (tela) REFERENCES public.telas(id);


-- Completed on 2024-03-12 09:40:26

--
-- PostgreSQL database dump complete
--

