ALTER table mail.mail add column message_id VARCHAR(400);

ALTER TABLE mail.mail RENAME COLUMN sent_date TO event_date;
