ALTER TABLE mail.mail_link
ALTER COLUMN click_count TYPE int4;

ALTER TABLE mail.mail_link
ALTER COLUMN click_count SET DEFAULT 0;
