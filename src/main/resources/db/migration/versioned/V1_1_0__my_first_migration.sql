CREATE SEQUENCE public.user_details_table_id_seq1
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.user_details_table_id_seq1
  OWNER TO postgres;


CREATE TABLE public.user_table1
(
  id bigint NOT NULL DEFAULT nextval('user_details_table_id_seq1'::regclass),
  username character varying(255),
  email character varying(255),
  password character varying(255),
  active boolean,
  user_details_id bigint
  
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_table1
  OWNER TO postgres;
