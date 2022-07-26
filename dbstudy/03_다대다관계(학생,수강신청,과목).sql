/*
-- 다대다관계(제약조건 칼럼 옆에 나열)
DROP TABLE RESISTER; -- 수강신청(자식)
DROP TABLE COURSE; -- 과목(부모)
DROP TABLE STUDENT; -- 학생(부모)
CREATE TABLE STUDENT(
   STU_NO CHAR(5 BYTE) NOT NULL PRIMARY KEY,
   STU_NAME VARCHAR2(20 BYTE) NOT NULL,
   STU_AGE NUMBER(3) NULL
);

CREATE TABLE COURSE(
    COUR_CODE CHAR(4 BYTE) NOT NULL PRIMARY KEY,
    COUR_NAME VARCHAR2(10 BYTE) NOT NULL,
    COUR_TEACH VARCHAR2(20 BYTE) NULL
);

CREATE TABLE RESISTER(
    RES_NO NUMBER(2) NOT NULL PRIMARY KEY,
    STU_NO CHAR(5 BYTE) NOT NULL REFERENCES STUDENT(STU_NO),
    COUR_CODE CHAR(4 BYTE) NOT NULL REFERENCES COURSE(COUR_CODE)
);
*/
/*
    참고. 외래키 제약조건을 무시하고 테이블 삭제는
          순서에 상관없이 삭제할 수 있다.
          즉, FK와 PK가 있는 상황에서는 자식->부모 순으로 DROP을 해야하는데 
          CASCADE CONSTRAINTS를 붙이면 제약조건인 FK, PK를 무시하는 상황이 되니까 삭제에 순서를 지키지 않아도 되는 것.
          DROP TABLE STUDENT CASCADE CONSTRAINTS;
          DROP TABLE RESISTER CASCADE CONSTRAINTS;
          DROP TABLE COURSE CASCADE CONSTRAINTS;      ->(자식먼저 지워야되는) 순서 무시
*/


-- 다대다관계(제약조건 이름 몰아서 주기)
DROP TABLE RESISTER; -- 수강신청(자식)
DROP TABLE COURSE;   -- 과목(부모)
DROP TABLE STUDENT;  -- 학생(부모)    -> 부모끼리는 생성/삭제에 있어서 순서는 상관이 없다. 관계가 맺어진 게 아니기 때문.
CREATE TABLE STUDENT(
   STU_NO CHAR(5 BYTE) NOT NULL,
   STU_NAME VARCHAR2(20 BYTE) NOT NULL,
   STU_AGE NUMBER(3) NULL,
   CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO)
);

CREATE TABLE COURSE(
    COUR_CODE CHAR(4 BYTE) NOT NULL,
    COUR_NAME VARCHAR2(10 BYTE) NOT NULL,
    COUR_TEACH VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_COURSE PRIMARY KEY(COUR_CODE)
);

CREATE TABLE RESISTER(
    RES_NO NUMBER(2) NOT NULL,
    STU_NO CHAR(5 BYTE) NOT NULL,
    COUR_CODE CHAR(4 BYTE) NOT NULL,
    CONSTRAINT PK_RESISTER PRIMARY KEY(RES_NO),
    CONSTRAINT FK_RESISTER_STUDENT FOREIGN KEY(STU_NO) REFERENCES STUDENT(STU_NO),
    CONSTRAINT FK_RESISTER_COURSE FOREIGN KEY(COUR_CODE) REFERENCES COURSE(COUR_CODE)
);

