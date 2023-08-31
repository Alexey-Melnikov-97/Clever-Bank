--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-08-31 13:49:58

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16413)
-- Name: bank; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank (
    id smallint NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.bank OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16422)
-- Name: bank_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank_account (
    id smallint NOT NULL,
    user_id integer NOT NULL,
    bank_id integer NOT NULL,
    amount numeric NOT NULL
);


ALTER TABLE public.bank_account OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 219
-- Name: COLUMN bank_account.amount; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.bank_account.amount IS 'AS num_round';


--
-- TOC entry 218 (class 1259 OID 16421)
-- Name: bank_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bank_account_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bank_account_id_seq OWNER TO postgres;

--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 218
-- Name: bank_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bank_account_id_seq OWNED BY public.bank_account.id;


--
-- TOC entry 216 (class 1259 OID 16412)
-- Name: bank_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bank_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bank_id_seq OWNER TO postgres;

--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 216
-- Name: bank_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bank_id_seq OWNED BY public.bank.id;


--
-- TOC entry 215 (class 1259 OID 16406)
-- Name: bank_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank_user (
    id smallint NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(50) NOT NULL
);


ALTER TABLE public.bank_user OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16405)
-- Name: bank_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bank_user_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bank_user_id_seq OWNER TO postgres;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 214
-- Name: bank_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bank_user_id_seq OWNED BY public.bank_user.id;


--
-- TOC entry 221 (class 1259 OID 16437)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    id bigint NOT NULL,
    datetime timestamp without time zone NOT NULL,
    transaction_type character varying NOT NULL,
    amount numeric NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16436)
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 220
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- TOC entry 3189 (class 2604 OID 16416)
-- Name: bank id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank ALTER COLUMN id SET DEFAULT nextval('public.bank_id_seq'::regclass);


--
-- TOC entry 3190 (class 2604 OID 16425)
-- Name: bank_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_account ALTER COLUMN id SET DEFAULT nextval('public.bank_account_id_seq'::regclass);


--
-- TOC entry 3188 (class 2604 OID 16409)
-- Name: bank_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_user ALTER COLUMN id SET DEFAULT nextval('public.bank_user_id_seq'::regclass);


--
-- TOC entry 3191 (class 2604 OID 16440)
-- Name: transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- TOC entry 3345 (class 0 OID 16413)
-- Dependencies: 217
-- Data for Name: bank; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank (id, name) FROM stdin;
1	Sber
2	Alfa
3	BPS
4	Belarus
5	Clever
\.


--
-- TOC entry 3347 (class 0 OID 16422)
-- Dependencies: 219
-- Data for Name: bank_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank_account (id, user_id, bank_id, amount) FROM stdin;
2	4	2	5211
6	3	5	7246
7	2	2	8646
8	7	4	180
10	10	3	6017
11	7	5	5704
12	9	2	3612
14	4	5	1175
16	7	1	8582
17	2	5	2260
19	11	5	8981
20	6	2	9996
22	6	5	5698
23	9	5	7645
24	7	2	1686
25	7	3	6516
26	10	1	6867
27	14	4	6370
29	11	3	696
30	8	4	9035
31	9	4	8425
33	10	2	307
34	6	2	9244
36	16	1	2973
38	10	5	3868
40	19	1	9374
39	20	5	4901
35	19	5	3371
37	17	5	2942
9	16	5	7760
3	15	5	4594
5	14	5	5658
32	13	5	6547
4	12	5	5870
15	8	5	5729
21	5	5	7336
13	18	5	4942
28	18	2	7661
18	1	5	2000
1	11	1	8666.9
\.


--
-- TOC entry 3343 (class 0 OID 16406)
-- Dependencies: 215
-- Data for Name: bank_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank_user (id, first_name, last_name, email) FROM stdin;
2	Karina	Trustrie	ktrustrie1@de.vu
3	Claire	Dollin	cdollin2@unesco.org
4	Dave	Androsik	dandrosik3@privacy.gov.au
5	Rodie	Farrow	rfarrow4@wunderground.com
6	Alyda	Whiteland	awhiteland5@exblog.jp
7	Stefan	Chell	schell6@vkontakte.ru
8	Kerrie	Shaddick	kshaddick7@nih.gov
9	Tades	Eversley	teversley8@wired.com
10	Tammi	Walicki	twalicki9@admin.ch
11	Bobbie	Bullivent	bbulliventa@goo.ne.jp
12	Pavel	Seiffert	pseiffertb@slashdot.org
13	Paddy	Sarney	psarneyc@java.com
14	Vanya	Hanlin	vhanlind@nyu.edu
15	Mata	Lunt	mlunte@addtoany.com
16	Hallsy	Kenchington	hkenchingtonf@tiny.cc
17	Perl	Bredbury	pbredburyg@wsj.com
18	Marion	Phibb	mphibbh@oracle.com
19	Delcina	Chiplen	dchipleni@github.io
20	Gray	Hughland	ghughlandj@opensource.org
1	Алексей	Мельников	alexey.melnikov.97@gmail.com
\.


--
-- TOC entry 3349 (class 0 OID 16437)
-- Dependencies: 221
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (id, datetime, transaction_type, amount) FROM stdin;
\.


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 218
-- Name: bank_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_account_id_seq', 40, true);


--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 216
-- Name: bank_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_id_seq', 5, true);


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 214
-- Name: bank_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_user_id_seq', 20, true);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 220
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_id_seq', 1, false);


--
-- TOC entry 3197 (class 2606 OID 16427)
-- Name: bank_account bank_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_account
    ADD CONSTRAINT bank_account_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 16420)
-- Name: bank bank_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank
    ADD CONSTRAINT bank_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 16411)
-- Name: bank_user bank_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_user
    ADD CONSTRAINT bank_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 16444)
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);


-- Completed on 2023-08-31 13:49:58

--
-- PostgreSQL database dump complete
--

