create table mail.mail_link(
id int8 PRIMARY KEY,
url TEXT,
click_count VARCHAR(200),
clicked_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
mail_id int8,
FOREIGN KEY (mail_id) REFERENCES mail.mail(id)
);

CREATE SEQUENCE mail_link_id_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

ALTER TABLE mail.mail_link
    ALTER COLUMN id SET DEFAULT nextval('mail_link_id_seq');