--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: certs; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE certs (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE certs OWNER TO "Guest";

--
-- Name: certs_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE certs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE certs_id_seq OWNER TO "Guest";

--
-- Name: certs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE certs_id_seq OWNED BY certs.id;


--
-- Name: hoods; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE hoods (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE hoods OWNER TO "Guest";

--
-- Name: hoods_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE hoods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hoods_id_seq OWNER TO "Guest";

--
-- Name: hoods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE hoods_id_seq OWNED BY hoods.id;


--
-- Name: ratings; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE ratings (
    id integer NOT NULL,
    roaster_id integer,
    rater character varying,
    rating integer,
    comment character varying
);


ALTER TABLE ratings OWNER TO "Guest";

--
-- Name: ratings_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE ratings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ratings_id_seq OWNER TO "Guest";

--
-- Name: ratings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE ratings_id_seq OWNED BY ratings.id;


--
-- Name: roasters; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE roasters (
    id integer NOT NULL,
    name character varying,
    location character varying,
    cert_id integer,
    hood_id integer,
    website character varying,
    description character varying,
    average integer
);


ALTER TABLE roasters OWNER TO "Guest";

--
-- Name: roasters_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE roasters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roasters_id_seq OWNER TO "Guest";

--
-- Name: roasters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE roasters_id_seq OWNED BY roasters.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY certs ALTER COLUMN id SET DEFAULT nextval('certs_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY hoods ALTER COLUMN id SET DEFAULT nextval('hoods_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY ratings ALTER COLUMN id SET DEFAULT nextval('ratings_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY roasters ALTER COLUMN id SET DEFAULT nextval('roasters_id_seq'::regclass);


--
-- Data for Name: certs; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY certs (id, name) FROM stdin;
1	organic
2	fair-trade
3	shade grown
4	rainforest alliance
5	smithsonian bird friendly
6	utz
7 none
\.


--
-- Name: certs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('certs_id_seq', 1, false);


--
-- Data for Name: hoods; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY hoods (id, name) FROM stdin;
1	North Portland
2	Northeast
3	East Portland
4	Southeast
5	Southwest
6	Downtown
7	Northwest
\.


--
-- Name: hoods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('hoods_id_seq', 1, false);


--
-- Data for Name: ratings; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY ratings (id, roaster_id, rater, rating, comment) FROM stdin;
\.


--
-- Name: ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('ratings_id_seq', 8, true);


--
-- Data for Name: roasters; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY roasters (id, name, location, cert_id, hood_id, website, description, average) FROM stdin;
\.


--
-- Name: roasters_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('roasters_id_seq', 3, true);


--
-- Name: certs_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY certs
    ADD CONSTRAINT certs_pkey PRIMARY KEY (id);


--
-- Name: hoods_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY hoods
    ADD CONSTRAINT hoods_pkey PRIMARY KEY (id);


--
-- Name: ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);


--
-- Name: roasters_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY roasters
    ADD CONSTRAINT roasters_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
