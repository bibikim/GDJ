 -- 주석
 /* 주석 */

-- 테이블 이름 규칙
-- 1. 영문자로 시작하고 특수문자 사용 가능, 30자 이내, 대소문자 구별 X
-- 2. 서로 다른 테이블에서 동일한 데이터를 저장하는 칼럼의 이름은 가능하면 같은 이름을 사용
-- 3. 완성된 설계도(엑셀)에 의해 테이블 생성하는 것을 권장
-- 4. 테이블 이름이 끝나면 (); 닫아주기 // 칼럼_이름 데이터_타입 [제약조건] -> NOT NULL은 칼럼 이름에 같이 명시

/*
    오라클 데이터 타입
     1. CHAR(size) : 고정 길이 문자 타입(1 ~ 2000바이트)
     2. VARCHAR2(size) : 가변 길이 문자 타입(1 ~ 4000바이트)
     3. NUMBER(p,s) : 정밀도(p), 스케일(s)로 표현되는 숫자 타입
        - 정밀도(p) : 정수 + 소수점 모두 포함하는 전체 유효 숫자(0은 제외)
        - 스케일(s) : 소수점 자릿수 
        EX)
        NUMBER : 최대 38자리 숫자(22바이트)
        NUMBER(3) : 최대 3자리 정수 (스케일이 없는 경우엔 그냥 정수)
        NUMBER(5,2) : 최대 전체 5자리 정수, 그 중 소수점 2자리 실수(122.61)
        NUMBER(2,2) : 1 미만의 소수점 2자리 실수(0.48) -> 전체 2자리, 그 중 소수점 2자리
*/

/* 
    테이블 생성
     1. 제약조건의 이름을 지정하지 않는 방법(SYS로 시작하는 임의의 제약조건이름이 지정)
     2. 제약조건의 이름을 지정하는 방법
 */    
 
 /*
    제약조건 생성
     1. 테이블 생성할 때 함께 지정
     2. 테이블 생성한 뒤 테이블 수정하면서 지정
 */
 
 /*
    제약조건 - 데이터 사전
     1. DBA_CONSTRAINTS 테이블
     2. USER_CONSTRAINTS 테이블 (현재 접속한 사용자가 만든 제약조건만 확인 가능)
     3. ALL_CONSTRAINTS 테이블
 */
 
 -- USER_CONSTRAINTS 테이블의 구조 확인
DESCRIBE SYS.USER_CONSTRAINTS;  -- SYS가 가지고 있는 USER_CONSTRAINTS테이블. SYS는 OWNER(SYS. 은 생략 가능)
 
 -- USER_CONSTRAINTS 테이블의 CONSTRAINTS_NAME 칼럼 확인
SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS; -- 현재 USER인 SCOTT의 제약조건 확인

-- 1. 제약조건이름 없이 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL PRIMARY KEY, -- USER_ID는 2개의 제약조건을 가지게 된다. NOT NULL과 기본키
    USER_PW VARCHAR2(30 BYTE) NOT NULL,             -- PW는 필수니까 NOT NULL 
    USER_NAME VARCHAR2(30 BYTE) NULL,               -- 필수 입력 아닌 것은 그냥 NULL
    USER_AGE NUMBER(3) NULL CHECK(USER_AGE BETWEEN 0 AND 100),   -- CHECK
    USER_ADDR VARCHAR2(30 BYTE) NULL,               -- 제약조건 사이사이 ,는 찍으면 X! 제약조건이 다 끝나고 마지막에 ,
    USER_TEL CHAR(13 BYTE) NULL UNIQUE,             -- 핸드폰 번호 고정 길이. 하이픈 포함 13. UNIQUE(중복 불허 - 전화번호는 중복일 리가 없으니까)
    USER_GEN CHAR(1 BYTE) NULL
);
-- 칼럼_이름 데이터타입_이름 제약조건 N개
-- 제약조건(constraint) - NOT NULL/NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY, CHECK(값의 유효성 검사)   >  5가지
-- 한번 만들어 놓으면 두 번은 실행이 안 됨. 그래서 지우는 쿼리문도 함께 따라다님(만드는 쿼리 위에 DROP TABLE ~)


-- 2. 제약조건이름 없이 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL, 
    USER_PW VARCHAR2(30 BYTE) NOT NULL, 
    USER_NAME VARCHAR2(30 BYTE) NULL,
    USER_AGE NUMBER(3) NULL,
    USER_ADDR VARCHAR2(30 BYTE) NULL,
    USER_TEL CHAR(13 BYTE) NULL,
    USER_GEN CHAR(1 BYTE) NULL,
    PRIMARY KEY(USER_ID),
    CHECK(USER_AGE BETWEEN 0 AND 100),
    UNIQUE(USER_TEL)
);
 -- NULL 유무만 데이터타입 옆에 적어주고 나머지 제약조건은 밑에다 한 곳에 모아 처리할 수 있다.
 
 
 -- 3. 제약조건이름 지정하며 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL CONSTRAINT PK_USER_TBL PRIMARY KEY,  -- 테이블 대상으로 이름줌. USER_TBL의 PK
    USER_PW VARCHAR2(30 BYTE) NOT NULL,                                     -- NOT NULL은 이름을 주지 않음 > SYS_C0070NN
    USER_NAME VARCHAR2(30 BYTE) NULL,
    USER_AGE NUMBER(3) NULL CONSTRAINT CK_USER_AGE CHECK(USER_AGE BETWEEN 0 AND 100),
    USER_ADDR VARCHAR2(30 BYTE) NULL,
    USER_TEL CHAR(13 BYTE) NULL CONSTRAINT UQ_USER_TEL UNIQUE,              -- 칼럼 대상으로 이름줌. USER_TEL 칼럼의 UQ
    USER_GEN CHAR(1 BYTE) NULL
);
-- 칼럼_이름 데이터타입(바이트) NULL여부 CONSTRAINT 제약조건이름 제약조건


-- 4. 제약조건이름 지정하며 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL, 
    USER_PW VARCHAR2(30 BYTE) NOT NULL, 
    USER_NAME VARCHAR2(30 BYTE) NULL,
    USER_AGE NUMBER(3) NULL,
    USER_ADDR VARCHAR2(30 BYTE) NULL,
    USER_TEL CHAR(13 BYTE) NULL,
    USER_GEN CHAR(1 BYTE) NULL,
    CONSTRAINT PK_USER_TBL PRIMARY KEY(USER_ID),    -- USER_TBL의 PK
    CONSTRAINT CK_USER_AGE CHECK(USER_AGE BETWEEN 0 AND 100),
    CONSTRAINT UQ_USER_TEL UNIQUE(USER_TEL)
);