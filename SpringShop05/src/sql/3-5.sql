CREATE TABLE USER_ACCOUNT(
	USER_ID VARCHAR2(20),
	USER_NAME VARCHAR2(20),
	PASSWORD VARCHAR2(20),
	POSTCODE VARCHAR2(8),
	ADDRESS VARCHAR2(50),
	EMAIL VARCHAR2(50),
	JOB VARCHAR2(30),
    BIRTHDAY DATE,
	CONSTRAINT USER_ID_PK PRIMARY KEY (USER_ID)
);

DELETE FROM USER_ACCOUNT;

INSERT INTO USER_ACCOUNT VALUES('USERID01','USERNAME01','PASSWD01','111-111' ,'서울시 서초구' ,'user01@korea.net','사회인','1979-01-01');
INSERT INTO USER_ACCOUNT VALUES('USERID02','USERNAME02','PASSWD02','222-222','부산시 남구' ,'user02@korea.net','사회인','1979-06-30');
INSERT INTO USER_ACCOUNT VALUES('USERID03','USERNAME03','PASSWD03','333-333' ,'인천시 북구','user03@korea.net','학생','1985-12-31');

COMMIT;

CREATE TABLE ITEM(
	ITEM_ID NUMBER,
	ITEM_NAME VARCHAR2(20),
	PRICE NUMBER,
	DESCRIPTION VARCHAR2(100),
	PICTURE_URL VARCHAR2(20),
	CONSTRAINT ITEM_ID_PK PRIMARY KEY (ITEM_ID)
);

CREATE SEQUENCE ITEM_ID_SEQ;

DELETE FROM ITEM;

INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'레몬',50,'레몬에 포함된 구연산은 피로회복에 좋습니다. 비타민 C도 풍부합니다.','lemon.jpg');
INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'오렌지',100,'비타민 C가 풍부합니다. 생과일 주스로 마시면 좋습니다.','orange.jpg');
INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'키위',200,'비타민 C가 매우 풍부합니다. 다이어트나 미용에 좋습니다.','kiui.jpg');
INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'포도',300,'폴리페놀을 다량 함유하고 있어 항산화 작용을 합니다.' ,'budou.jpg');
INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'딸기',800,'비타민 C나 플라보노이드를 다량 함유하고 있습니다.','ichigo.jpg');
INSERT INTO ITEM VALUES(ITEM_ID_SEQ.NEXTVAL,'귤',1000,'시네피린을 함유하고 있어 감기 예방에 좋다고 합니다.','mikan.jpg');

CREATE TABLE SALE(
	SALE_ID NUMBER,
	USER_ID VARCHAR2(20),
	UPDATE_TIME TIMESTAMP,
	CONSTRAINT SALE_SALE_ID_PK PRIMARY KEY (SALE_ID),
	CONSTRAINT SALE_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USER_ACCOUNT (USER_ID) 
);

CREATE TABLE SALE_LINE(
	SALE_ID NUMBER,
	SALE_LINE_ID NUMBER,
	ITEM_ID NUMBER,
	QUANTITY NUMBER,
	UPDATE_TIME TIMESTAMP,
	CONSTRAINT SALE_LINE_SALE_ID_PK PRIMARY KEY (SALE_ID),
	CONSTRAINT SALE_LINE_SALE_ID_FK FOREIGN KEY (SALE_ID) REFERENCES SALE (SALE_ID),
	CONSTRAINT SALE_LINE_ITEM_ID_FK FOREIGN KEY (ITEM_ID) REFERENCES ITEM (ITEM_ID)	
);
