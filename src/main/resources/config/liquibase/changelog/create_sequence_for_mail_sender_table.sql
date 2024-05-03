CREATE SEQUENCE mail_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE mail.mail
    ALTER COLUMN id SET DEFAULT nextval('mail_id_seq');
