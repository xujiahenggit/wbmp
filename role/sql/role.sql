-- Determine if the table exists, and if so, delete it
DECLARE NUM NUMBER;
BEGIN
	SELECT
		COUNT( 1 ) INTO NUM
	FROM
		ALL_TABLES
	WHERE
		TABLE_NAME = 'SYS_ROLE'
		AND OWNER = 'BANK';
	IF
		NUM = 1 THEN
			EXECUTE IMMEDIATE 'DROP TABLE SYS_ROLE';

	END IF;
END;
/
-- CREATE TABLE  创建系统角色信息表
CREATE TABLE SYS_ROLE (
	ID NUMBER ( 16 ) NOT NULL,
	NAME VARCHAR2 ( 32 ) NOT NULL,
	CODE VARCHAR2 ( 32 ) NOT NULL,
	DESCRIBE CLOB,
	CREATE_TIME NUMBER ( 16 ) NOT NULL,
	UPDATE_TIME NUMBER ( 16 ) NOT NULL
);
-- Add comments to the table
COMMENT ON TABLE SYS_ROLE IS '系统角色信息表';
-- Add comments to the columns
COMMENT ON COLUMN SYS_ROLE.ID IS '主键';
COMMENT ON COLUMN SYS_ROLE.NAME IS '角色名';
COMMENT ON COLUMN SYS_ROLE.CODE IS '角色标识码';
COMMENT ON COLUMN SYS_ROLE.DESCRIBE IS '角色描述';
COMMENT ON COLUMN SYS_ROLE.CREATE_TIME IS '创建时间戳';
COMMENT ON COLUMN SYS_ROLE.UPDATE_TIME IS '更新时间戳';
-- Set the primary key
ALTER TABLE SYS_ROLE ADD CONSTRAINT PK_SYS_ROLE PRIMARY KEY ( ID );
-- Determine if the sequence exists, and if so, delete it
DECLARE NUM NUMBER;
BEGIN
	SELECT
		COUNT( 1 ) INTO NUM
	FROM
		USER_SEQUENCES
	WHERE
		SEQUENCE_NAME = 'SEQ_SYS_ROLE';
	IF
		NUM = 1 THEN
			EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_SYS_ROLE';

	END IF;
END;
/
-- Create an autoincrement sequence
CREATE SEQUENCE SEQ_SYS_ROLE MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;
-- Insert test data
INSERT INTO SYS_ROLE ( ID, NAME, CODE, DESCRIBE, CREATE_TIME, UPDATE_TIME )
VALUES
	( SEQ_SYS_ROLE.NEXTVAL, '系统管理员', 'SUPER_ADMIN', '全局唯一，不可删除的角色，拥有所有权限', 1572421507441, 1572421507441 );
INSERT INTO SYS_ROLE ( ID, NAME, CODE, DESCRIBE, CREATE_TIME, UPDATE_TIME )
VALUES
	( SEQ_SYS_ROLE.NEXTVAL, '银行管理员', 'BANK_MANAGER', '银行管理员，可以查看银行相关的业务', 1572421834441, 1572421841443 );

--------------------------------------------------分割线-----------------------------------------------------------------

-- Determine if the table exists, and if so, delete it
DECLARE NUM NUMBER;
BEGIN
	SELECT
		COUNT( 1 ) INTO NUM
	FROM
		ALL_TABLES
	WHERE
		TABLE_NAME = 'SYS_USER_ROLE'
		AND OWNER = 'BANK';
	IF
		NUM = 1 THEN
			EXECUTE IMMEDIATE 'DROP TABLE SYS_USER_ROLE';

	END IF;
END;
/
-- CREATE TABLE  创建系统用户角色映射表
CREATE TABLE SYS_USER_ROLE (
	USER_ID NUMBER ( 16 ) NOT NULL,
	ROLE_ID NUMBER ( 16 ) NOT NULL,
    CREATE_TIME NUMBER ( 16 ) NOT NULL,
    UPDATE_TIME NUMBER ( 16 ) NOT NULL
);
-- Add comments to the table
COMMENT ON TABLE SYS_USER_ROLE IS '系统用户角色映射表';
-- Add comments to the columns
COMMENT ON COLUMN SYS_USER_ROLE.USER_ID IS '用户ID';
COMMENT ON COLUMN SYS_USER_ROLE.ROLE_ID IS '角色ID';
COMMENT ON COLUMN SYS_USER_ROLE.CREATE_TIME IS '创建时间戳';
COMMENT ON COLUMN SYS_USER_ROLE.UPDATE_TIME IS '更新时间戳';
-- Set the primary key
ALTER TABLE SYS_USER_ROLE ADD CONSTRAINT PK_SYS_USER_ROLE PRIMARY KEY ( USER_ID,ROLE_ID );
-- Insert test data
INSERT INTO SYS_USER_ROLE ( USER_ID, ROLE_ID, CREATE_TIME, UPDATE_TIME )
VALUES
( 2, 2, 1572421507441, 1572421507441 );
INSERT INTO SYS_USER_ROLE ( USER_ID, ROLE_ID, CREATE_TIME, UPDATE_TIME )
VALUES
( 3, 3, 1572421507441, 1572421507441 );

--------------------------------------------------分割线-----------------------------------------------------------------
COMMIT;