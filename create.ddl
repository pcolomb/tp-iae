CREATE TABLE B (ID INTEGER NOT NULL, B_1 VARCHAR(255), B_2 VARCHAR(255), C_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE C (ID INTEGER NOT NULL, C_1 VARCHAR(255), C_2 VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE STUDENT (ID INTEGER NOT NULL, MARK INTEGER, NAME VARCHAR(255), SCHOOL_ID INTEGER NOT NULL, PRIMARY KEY (ID, SCHOOL_ID))
CREATE TABLE A (ID INTEGER NOT NULL, A_1 VARCHAR(255), A_2 VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE SCHOOL (ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
ALTER TABLE B ADD CONSTRAINT FK_B_C_ID FOREIGN KEY (C_ID) REFERENCES C (ID)
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_SCHOOL_ID FOREIGN KEY (SCHOOL_ID) REFERENCES SCHOOL (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)