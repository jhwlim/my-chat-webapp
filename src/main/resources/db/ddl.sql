DROP TABLE users;
DROP TABLE messages;
DROP TABLE message_text;
DROP TABLE message_file;

CREATE TABLE users (
    seq_id INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(30) UNIQUE,
    name VARCHAR(30),
    image VARCHAR(255)
);

CREATE TABLE messages (
    seq_id INT PRIMARY KEY AUTO_INCREMENT,
    sender INT,
    CONSTRAINT msg_sender_fk FOREIGN KEY (sender)
        REFERENCES users (seq_id),
    receiver INT,
    CONSTRAINT msg_receiver_fk FOREIGN KEY (receiver)
        REFERENCES users (seq_id),
    send_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE message_text (
	msg_id INT PRIMARY KEY,
    CONSTRAINT msgtext_id_fk FOREIGN KEY (msg_id)
		REFERENCES messages (seq_id),
    content VARCHAR(255)
);

CREATE TABLE message_file (
	msg_id INT PRIMARY KEY,
    CONSTRAINT msgfile_id_fk FOREIGN KEY (msg_id)
		REFERENCES messages (seq_id),
	path VARCHAR(255)
);
