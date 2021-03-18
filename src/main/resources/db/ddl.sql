DROP TABLE messages;
DROP SEQUENCE message_seq;
DROP TABLE users;
DROP SEQUENCE user_seq;

CREATE TABLE users (
    seq_id NUMBER PRIMARY KEY,
    id VARCHAR2(30) UNIQUE,
    name VARCHAR2(30),
    image VARCHAR2(255)
);

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE TABLE messages (
    seq_id NUMBER PRIMARY KEY,
    sender NUMBER
        CONSTRAINT msg_sender_fk REFERENCES users(seq_id),
    receiver NUMBER
        CONSTRAINT msg_receiver_fk REFERENCES users(seq_id),
    send_date TIMESTAMP
);

CREATE SEQUENCE message_seq START WITH 1 INCREMENT BY 1 NOCACHE;