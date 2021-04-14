-- 회원 데이터
INSERT INTO users(id, name) VALUES('test01', '테스트01');
INSERT INTO users(id, name) VALUES('test02', '테스트02');
INSERT INTO users(id, name) VALUES('test03', '테스트03');
INSERT INTO users(id, name) VALUES('test04', '테스트04');

-- 메시지 데이터
INSERT INTO messages(sender, chat_room_id) VALUES(1, 1);
INSERT INTO message_text(message_id, content) VALUES(1, "Hello");

-- 채팅방 데이터
INSERT INTO chat_room(type) VALUES(1);
INSERT INTO chat_user(chat_room_id, user_seq_id) VALUES(1, 1); 
INSERT INTO chat_user(chat_room_id, user_seq_id) VALUES(1, 2); 
INSERT INTO chat_user(chat_room_id, user_seq_id) VALUES(2, 1); 
INSERT INTO chat_user(chat_room_id, user_seq_id) VALUES(2, 3);

