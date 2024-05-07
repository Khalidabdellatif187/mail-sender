ALTER TABLE mail.mail_link
ALTER COLUMN click_count TYPE int4 USING click_count::integer;

ALTER TABLE mail.mail_link
ALTER COLUMN click_count SET DEFAULT 0;
