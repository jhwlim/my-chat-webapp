-- 회원 데이터
INSERT INTO users(id, name) VALUES('test01', '테스트01');
INSERT INTO users(id, name) VALUES('test02', '테스트02');
INSERT INTO users(id, name) VALUES('test03', '테스트03');
INSERT INTO users(id, name) VALUES('test04', '테스트04');

-- 메시지 데이터
INSERT INTO messages(sender, receiver) VALUES(1, 2);
INSERT INTO message_text(msg_id, content) VALUES(1, "Hello");
INSERT INTO messages(sender, receiver) VALUES(1, 2);
INSERT INTO message_text(msg_id, content) VALUES(2, "Hi");
INSERT INTO messages(sender, receiver) VALUES(1, 2);
INSERT INTO message_file(msg_id, path) VALUES(3, "/test.png");

-- 메시지 조회
-- 만약, content가 null 이라면, file
SELECT
	msg.*,
    text.content,
    file.path
FROM
	messages msg
    LEFT JOIN message_text text
    ON msg.seq_id = text.msg_id
	LEFT JOIN message_file file
    ON msg.seq_id = file.msg_id;
