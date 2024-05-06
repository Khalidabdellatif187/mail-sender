create table mail.user(
id int8 primary key,
user_name varchar(250),
email varchar(250),
password varchar(500)
);



CREATE SEQUENCE user_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE mail.user
    ALTER COLUMN id SET DEFAULT nextval('user_id_seq');