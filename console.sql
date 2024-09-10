select * from tbl_todo;

-- 데이터베이스 인코딩 변경
ALTER DATABASE apidb CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 테이블 인코딩 변경
ALTER TABLE tbl_todo CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 컬럼 인코딩 변경
ALTER TABLE tbl_todo CHANGE content content TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;