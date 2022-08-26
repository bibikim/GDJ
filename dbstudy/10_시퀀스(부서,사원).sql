
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

CREATE TABLE DEPARTMENT(
    DEPT_NO   NUMBER            NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION  VARCHAR2(15 BYTE) NOT NULL
);

CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER,
    POSITION  VARCHAR2(20 BYTE),
    GENDER    CHAR(2),
    HIRE_DATE DATE,
    SALARY    NUMBER
);

ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) REFERENCES DEPARTMENT(DEPT_NO)
    ON DELETE SET NULL;

    
/******************************************************************/

/*
    시퀀스
  
    1. SEQUENCE
    2. 일련번호를 생성하는(은행 번호표같은) 데이터베이스 객체 (DB객체이기 때문에 CREATE, DROP을 이용해 생성 및 삭제)
    3. 자동으로 증가하는 번호를 생성
    4. 기본키(PK)에서 주로 사용(인공키 ARITIFICIAL KEY : 회원이 원래 가지고 있는게 아닌데 부여해준 값)
    5. NEXTVAL를 이용하면 새로운 번호가 생성(번호표를 뽑으면 새 번호표가 나오는 시스템  next value)
    6. CURRVAL를 이용하면 현재 번호를 확인(현재 번호표가 몇 번까지 나갔는지 확인하는 용도 current value)
    7. 테이블 하나 당 시퀀스 하나 사용
    
*/

/*
    시퀀스 생성 형식
  +  CREATE SEQUENCE 시퀀스_이름
       START WITH 시작값              -- 생략하면 1, 생성 이후 수정 불가 (첫 번호표의 시작)  시퀀스를 삭제 후 다시 만드는 방법뿐..
       INCREMENT BY 증가값            -- 생략하면 1  (그 다음 번호, 숫자가 몇 씩 증가할 건지)
       MINVALUE 최소값                -- 시작과 같다고 보면 되기 때문에 사용할 일이 거의 없다.
       MAXVALUE 최대값
  +    CACHE사용유무                  -- NOCACHE 권장 (메모리 캐시 사용할거냐의 여부)
       CYCLE사용유무;                 -- 생략하면 NOCYCLE, PK에서 사용한다면 NOCYCLE(왜냐면 PK는 UNIQUE 조건을 가지니까) (번호 순환 여부)

      시퀀스_이름.NEXTVAL             -- INSERT INTO 테이블(칼럼...) VALUES(값...) 안에 시퀀스를 사용하고자 하는 값 위치에 적는 형식
*/

-- 부서 테이블에서 사용할 부서_시퀀스
DROP SEQUENCE DEPARTMENT_SEQ;
-- 생성하고 나면 수정할 수 없는 시퀀스의 특성에 따라, 테이블 생성할 때처럼 DROP 쿼리를 생성 쿼리 전에 적어준다.
CREATE SEQUENCE DEPARTMENT_SEQ 
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NOMAXVALUE --(최대값 X)
    NOCACHE
    NOCYCLE;

-- 부서 테이블에 행(Row) 삽입
-- 부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 한다.
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION) 
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '영업부', '대구');            -- text는 ''로 적는다.
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구'); 
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울');            -- COMMIT 전까지는 완벽하게 저장된 것이 아니다. 데이터에서 보여도 완벽히 저장된 것 X
-- 실행문 4개 -> 취소(롤백)가 들어오면 다 취소가 된다.

-- 작업의 완료
COMMIT;


-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE;                    -- 이 두개는 꼭 적어줘야 하는 쿼리(START WITH 경우에는 원래 생략 가능하지만 사원번호가 1001부터 시작하므로)

-- 사원 테이블에 행(Row) 삽입
INSERT INTO
    EMPLOYEE -- 칼럼리스트를 생략하면 만들어져 있는 칼럼 순서에 맞춰 값을 다 넣어줘야 한다.
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '구교환', 2, '과장', 'M', '20/07/19', 5000000);  -- 날짜는 '/'나 '-'으로 표시한다.
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '이종석', 1, '대리', 'M', '21-05-01', 3000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '김태리', 2, '사원', 'F', '21-11-10', 2200000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '김윤석', 3, '부장', NULL, '16/04/01', 7000000);  -- NULL이 가능한 칼럼은 NULL이라고 적어주면 된다.

-- 오류가 발생하는 INSERT
-- INSERT는 실패하였으나 SEQUENCE의 번호는 사용했음(1005번은 사용해버림). 생성 실패는 하지만 시퀀스는 사용한다.
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '신세경', 5, '대리', 'F', '19/11/03', 3200000);   -- 부서번호 5번이 없는 상태에서는 실행 오류

-- 정상 데이터 다시 INSERT
-- 1006번 번호 사원이 됨. 버려지는 번호가 생길 수 있다. (시퀀스는 생성 이후 수정이 불가하므로 1005번의 사원은 만들 수 없다)
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '신세경', 3, '대리', 'F', '19/11/03', 3200000);   -- 1006번 사원
    
COMMIT;


/**************************************************/


DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NO1 NUMBER,
    NO2 NUMBER
);

DROP SEQUENCE SAMPLE_SEQ;
CREATE SEQUENCE SAMPLE_SEQ 
       NOCACHE;

-- 최초 1번은 NEXTVAL를 사용해야 번호표가 뽑혀 나오니까 CURRVAL도 사용 가능하다. (NEXTVAL 없이 CURRVAL만 사용하는건 불가)
INSERT INTO SAMPLE(NO1) VALUES(SAMPLE_SEQ.CURRVAL);   -- 오류: sequence SAMPLE_SEQ.CURRVAL is not yet defined in this session

-- NEXTVAL, CURRVAL 함께 사용
-- 작업은 왼->오이기 때문에 나란히 써줘도 NEXTVAL이 먼저 작동하기 때문에 CURRVAL도 실행된다.
INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL, SAMPLE_SEQ.CURRVAL);    
-- 계층형 게시판 구현이 NEXT,CURR이 함께 사용하는 대표적 예

COMMIT;

