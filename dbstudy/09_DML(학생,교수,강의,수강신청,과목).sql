/*

 강의 - 수강신청 ( 자식FK - 부모PK )
 
 강의 - 교수 ( 자식FK - 부모PK )
 
 수강신청 - 과목 ( 자식FK - 부모PK )
 
 학생 - 교수 ( 자식FK - 부모PK)

*/

DROP TABLE LECTURE;
DROP TABLE ENROLL;
DROP TABLE STUDENT;
DROP TABLE COURSE;
DROP TABLE PROFESSOR;

CREATE TABLE PROFESSOR(
    PROF_NO    NUMBER            NOT NULL,
    PROF_NAME  VARCHAR2(15 BYTE) NOT NULL,
    PROF_MAJOR VARCHAR2 (30 BYTE)
);

CREATE TABLE COURSE(
    COU_NO   NUMBER             NOT NULL,
    COU_NAME VARCHAR2(30 BYTE),
    COU_COM  NUMBER(1) 
);

CREATE TABLE STUDENT(
    STU_NO    NUMBER(9)         NOT NULL,
    STU_NAME  VARCHAR2(30 BYTE) NOT NULL,
    STU_ADDR  VARCHAR2(150 BYTE),
    STU_GRADE NUMBER(1),
    PROF_NO   NUMBER
);

CREATE TABLE ENROLL(
    ENR_NUM  NUMBER(7) NOT NULL,
    ENR_NO   NUMBER,
    COU_NO   NUMBER,
    ENR_DATE DATE
);

CREATE TABLE LECTURE(
    LEC_NO    NUMBER(5)         NOT NULL,
    PROF_NO   NUMBER,
    COU_NO    NUMBER,
    LEC_NAME  VARCHAR2(40 BYTE) NOT NULL,
    LEC_CLASS VARCHAR2(20 BYTE)
);


ALTER TABLE PROFESSOR 
    ADD CONSTRAINT PK_PROFESSOR PRIMARY KEY(PROF_NO);
ALTER TABLE COURSE
    ADD CONSTRAINT PK_COURSE PRIMARY KEY(COU_NO);
ALTER TABLE STUDENT
    ADD CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO);
ALTER TABLE ENROLL
    ADD CONSTRAINT PK_ENROLL PRIMARY KEY(ENR_NUM);
ALTER TABLE LECTURE
    ADD CONSTRAINT PK_LECTURE PRIMARY KEY(LEC_NO);
    
ALTER TABLE STUDENT
    ADD CONSTRAINT FK_STUDENT_PROFESSOR FOREIGN KEY(PROF_NO) REFERENCES PROFESSOR(PROF_NO);
ALTER TABLE ENROLL
    ADD CONSTRAINT FK_ENROLL_COURSE FOREIGN KEY(COU_NO) REFERENCES COURSE(COU_NO);
ALTER TABLE LECTURE
    ADD CONSTRAINT FK_LECTURE_PROFESSOR FOREIGN KEY(PROF_NO) REFERENCES PROFESSOR(PROF_NO);
ALTER TABLE LECTURE
    ADD CONSTRAINT FK_LECTURE_COURSE FOREIGN KEY(COU_NO) REFERENCES COURSE(COU_NO);







