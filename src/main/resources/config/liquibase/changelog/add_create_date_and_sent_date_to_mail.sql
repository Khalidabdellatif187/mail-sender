ALTER TABLE mail.mail
ADD COLUMN created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP;


ALTER TABLE mail.mail
ADD COLUMN sent_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP;