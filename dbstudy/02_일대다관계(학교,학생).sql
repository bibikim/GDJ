/*
    일대다 관계의 테이블 생성
    
     1. 명칭
        1) 부모 테이블 : 일(1), 중복이 없는 PK를 가진 테이블
        2) 자식 테이블 : 다(M), 중복이 가능한 FK를 가진 테이블
     2. 생성
        부모 테이블을 먼저 생성하고, 자식 테이블을 나중에 생성. 자식 테이블을 먼저 생성할 수 없다.
     3. 삭제
        자식 테이블을 먼저 삭제하고, 부모 테이블을 나중에 삭제. (생성과 삭제는 역순)
     
*/
-- 학교 테이블(부모 테이블 : PK를 가진 테이블)
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL PRIMARY KEY,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL -- 학교 이름은 필수 NOT NULL
);

DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,    
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL, -- 학교 이름은 필수 NOT NULL
    PRIMARY KEY(SCH_CODE)                -- PRIMARY KEY 지정할 칼럼을 () 안에 써준다
);

DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL CONSTRAINT PK_SCHOOL PRIMARY KEY, -- SCHOOL테이블의 PK 라는 이름을 줌(CONSTRAINT 이름)
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL  -- NOT NULL에는 이름을 주지 않는 것이 통상적
);

DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,    
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT PK_SCHOOL PRIMARY KEY(SCH_CODE)
);


-- 학생 테이블(자식 테이블 : FK를 가진 테이블)
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL PRIMARY KEY,
    SCH_CODE NUMBER(1) NOT NULL REFERENCES SCHOOL(SCH_CODE), 
            -- 외래키 지정 방법: REFERENCES 참조하는 테이블이름(참조할 칼럼)
            -- 학교테이블의 PK를 참조하므로 타입과 크기를 똑같이 맞춰줘야 한다.
    STU_NAME VARCHAR2(20 BYTE) NULL
);
    
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL, 
    STU_NAME VARCHAR2(20 BYTE) NULL,
    PRIMARY KEY(STU_NO),
    FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)  -- 제약조건을 밑에다 몰아 쓸 때는 FK 명시가 추가로 들어간다.
);

DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL, 
    STU_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO),
    CONSTRAINT FK_STUDENT_SCHOOL FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)
            -- FK_해당테이블_참조테이블 FOREIGN KEY(해당테이블 칼럼) REFERENCES 참조테이블(참조테이블 칼럼)
            -- STU테이블과 SCH테이블을 연결하는 외래키라고 참조하는 테이블의 이름까지 적어준다.
);

-- 최종 정리
-- 생성 : 부모 먼저, 자식 나중
-- 삭제 : 자식 먼저, 부모 나중
DROP TABLE STUDENT;
DROP TABLE SCHOOL;    -- 일대다, 다대다 관계의 테이블을 생성할 때는 DROP을 몰아서 써주는게 좋다. 물론 자식 먼저, 부모는 나중에 적는다.
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT PK_SCHOOL PRIMARY KEY(SCH_CODE)
);
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL,
    STU_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO),
    CONSTRAINT FK_STDUENT_SCHOOL FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)
);






