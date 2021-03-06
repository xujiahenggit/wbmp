-- Determine if the table exists, and if so, delete it
DECLARE NUM NUMBER;
BEGIN
	SELECT
		COUNT( 1 ) INTO NUM
	FROM
		ALL_TABLES
	WHERE
		TABLE_NAME = 'SYS_USER'
		AND OWNER = 'BANK';
	IF
		NUM = 1 THEN
			EXECUTE IMMEDIATE 'DROP TABLE SYS_USER';

	END IF;
END;
/
-- CREATE TABLE  创建系统用户信息表
CREATE TABLE SYS_USER (
	ID NUMBER ( 16 ) NOT NULL,
	NAME VARCHAR2 ( 16 ) NOT NULL,
	PASSWORD VARCHAR2 ( 32 ),
	SALT VARCHAR2 ( 32 ),
	CREATE_TIME NUMBER ( 16 ) NOT NULL,
	UPDATE_TIME NUMBER ( 16 ) NOT NULL,
	CONSTRAINT SYS_USER_UNIQUE UNIQUE ( NAME )
);
-- Add comments to the table
COMMENT ON TABLE SYS_USER IS '系统用户信息表';
-- Add comments to the columns
COMMENT ON COLUMN SYS_USER.ID IS '主键';
COMMENT ON COLUMN SYS_USER.NAME IS '用户名';
COMMENT ON COLUMN SYS_USER.PASSWORD IS '密码';
COMMENT ON COLUMN SYS_USER.SALT IS '加密盐值';
COMMENT ON COLUMN SYS_USER.CREATE_TIME IS '创建时间戳';
COMMENT ON COLUMN SYS_USER.UPDATE_TIME IS '更新时间戳';
-- Set the primary key
ALTER TABLE SYS_USER ADD CONSTRAINT PK_SYS_USER PRIMARY KEY ( ID );
-- Determine if the sequence exists, and if so, delete it
DECLARE NUM NUMBER;
BEGIN
	SELECT
		COUNT( 1 ) INTO NUM
	FROM
		USER_SEQUENCES
	WHERE
		SEQUENCE_NAME = 'SEQ_SYS_USER';
	IF
		NUM = 1 THEN
			EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_SYS_USER';

	END IF;
END;
/
-- Create an autoincrement sequence
CREATE SEQUENCE SEQ_SYS_USER MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;
-- Insert test data
INSERT INTO SYS_USER ( ID, NAME, PASSWORD, SALT, CREATE_TIME, UPDATE_TIME )
VALUES
    (SEQ_SYS_USER.NEXTVAL, 'admin', 'b0247017f492967cb74294a08267e0ec', 'admin1572685577475', '1572685577475', '1572685577475' );
INSERT INTO SYS_USER ( ID, NAME, PASSWORD, SALT, CREATE_TIME, UPDATE_TIME )
VALUES
    (SEQ_SYS_USER.NEXTVAL, 'guest', '429e75b4dacf63d7b3289bd24d3d27bb', 'guest1572685616837', '1572685616837', '1572685616837' );

--------------------------------------------------分割线-----------------------------------------------------------------

--------------------------------------------------分割线-----------------------------------------------------------------
COMMIT;