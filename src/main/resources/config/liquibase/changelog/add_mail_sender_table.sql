create table mail.mail(
id int8 PRIMARY KEY,
body TEXT,
subject VARCHAR(250),
sender VARCHAR(250),
recipient VARCHAR(250),
mail_status VARCHAR(250)
);