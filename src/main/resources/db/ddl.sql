DROP TABLE users;
DROP TABLE messages;
DROP TABLE message_text;
DROP TABLE message_file;
DROP TABLE chat_room;
DROP TABLE chat_user;

CREATE TABLE users (
    seq_id INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(30) UNIQUE,
    name VARCHAR(30),
    image VARCHAR(255)
);

CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender INT,
    CONSTRAINT msg_sender_fk FOREIGN KEY (sender)
        REFERENCES users (seq_id),
    chat_room_id INT,
    CONSTRAINT msg_chatroom_fk FOREIGN KEY (chat_room_id)
        REFERENCES chat_room (id),
    send_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE message_text (
	message_id INT PRIMARY KEY,
    CONSTRAINT messagetext_id_fk FOREIGN KEY (message_id)
		REFERENCES messages (id),
    content VARCHAR(255)
);

CREATE TABLE message_file (
	message_id INT PRIMARY KEY,
    CONSTRAINT messagefile_id_fk FOREIGN KEY (message_id)
		REFERENCES messages (id),
	path VARCHAR(255)
);

CREATE TABLE chat_room (
	id INT PRIMARY KEY AUTO_INCREMENT,  -- 채팅방 고유번호(PK)
    type TINYINT NOT NULL				-- 채팅방 타입, `0` : 일대일 채팅, '1' : 그룹 채팅
);

CREATE TABLE chat_user (
	chat_room_id INT,                   -- 채팅방 고유번호(PK, FK)
    user_seq_id INT,                    -- 회원 고유번호(PK, FK)
	PRIMARY KEY(chat_room_id, user_seq_id),
    CONSTRAINT chat_user_room_fk FOREIGN KEY (chat_room_id)
		REFERENCES chat_room (id),    
	CONSTRAINT chat_user_user_fk FOREIGN KEY (user_seq_id)
		REFERENCES users (seq_id)
);
