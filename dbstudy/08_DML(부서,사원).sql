
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
    DML
    1. Data Manipulation Language
    2. 데이터 조작어
    3. 행(Row, Record, Tuple) 단위 삽입, 수정, 삭제 작업
    4. 트랜잭션(작업) 완료를 위해 COMMIT이 필요
    5. 트랜잭션(작업) 취소를 위해 ROLLBACK 사용 가능(DDL과 달리 작업 취소 가능)
    6. 종류
       1) INSERT INTO VALUES
       2) UPDATE SET WHERE
       3) DELETE FROM WHERE
*/

/*
    행 삽입
   1. 지정한 칼럼에 데이터 삽입
      INSERT INTO 테이블(칼럼1, 칼럼2) VALUES(값1, 값2);    칼럼-값 갯수는 맞아들어가야 함
   2. 모든 칼럼에 데이터 삽입(칼럼 리스트 생략)
      INSERT INTO 테이블 VALUES(값1, 값2);                  
*/


-- 부서 테이블에 행(Row) 삽입
-- 부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 한다.
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION) 
VALUES
    (1, '영업부', '대구');            -- text는 ''로 적는다.
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (2, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (3, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (4, '기획부', '서울');            -- COMMIT 전까지는 완벽하게 저장된 것이 아니다. 데이터에서 보여도 완벽히 저장된 것 X
-- 실행문 4개 -> 취소(롤백)가 들어오면 다 취소가 된다.

-- 작업의 완료
COMMIT;

/* 오류 사유 정리

INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME) VALUES(5, '개발부');             
--▶ 칼럼과 값에 지정을 안 하게 되면 NULL이 들어가는건데 제약조건이 NOT NULL이면 실행이 되지 않는다.
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '부에노스아이리스');   
--▶ LOCATION의 데이터타입 15바이트를 넘는 값이 들어왔기 때문에 실행 오류
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(4, '개발부', '인천');
--▶ UNIQUE CONSTRAINT VIOLATED 중복 오류. PK는 NOT NULL과 UNIQUE가 디폴트인데 DEPT_NO가 PK인 상황에서 이미 들어가 있는 값을 또 삽입을 하려 했기 때문에 오류.   
-- PK = NOT NULL(NULL값 가질수 X) + UNIQUE(중복값 가질 수 X)

*/


-- 사원 테이블에 행(Row) 삽입
-- 자식 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는다. 
-- 부서(부서번호) - 사원(소속부서)
-- PK             - FK
-- 1,2,3,4        - 1,2,3,4중 하나만 가능
INSERT INTO
    EMPLOYEE -- 칼럼리스트를 생략하면 만들어져 있는 칼럼 순서에 맞춰 값을 다 넣어줘야 한다.
VALUES
    (1001, '구교환', 2, '과장', 'M', '20/07/19', 5000000);  -- 날짜는 '/'나 '-'으로 표시한다.
INSERT INTO
    EMPLOYEE
VALUES
    (1002, '이종석', 1, '대리', 'M', '21-05-01', 3000000);
INSERT INTO
    EMPLOYEE
VALUES
    (1003, '김태리', 2, '사원', 'F', '21-11-10', 2200000);
INSERT INTO
    EMPLOYEE
VALUES
    (1004, '김윤석', 3, '부장', NULL, '16/04/01', 7000000);  -- NULL이 가능한 칼럼은 NULL이라고 적어주면 된다.
    
COMMIT;

/* 오류 사유 정리
INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART) VALUES(1005, '한호열', 5);
--▶ 외래키 참조 무결성 위배. 자식테이블의 DEPART가 참조하는 부모테이블의 PK DEPT_NO에 5번 부서는 없으므로 실행 오류
*/


-- 외부 데이터 import
-- 엑셀 데이터(시트마다 테이블 1개)
-- 테이블 선택 후 우클릭 - [데이터 임포트] 



-- 테이블 수정
-- 부서번호가 1인 부서의 지역을 '인천'으로 수정
UPDATE DEPARTMENT 
   SET LOCATION = '인천'  -- =(대입, 저장의 개념)
 WHERE DEPT_NO = 1;     -- =(같다의 개념)

-- 부서번호가 3인 부서명을 '전략부', 지역을 '부산'으로 수정
UPDATE DEPARTMENT
   SET DEPT_NAME = '전략부'
     , LOCATION = '부산' -- 이 줄은 포함돼서 실행이 될 수도, 안 될 수도 있어서(if문처럼) ','를 칼럼 앞에 쓰고 줄 맞춰준다
 WHERE DEPT_NO = 3;

-- 부서번호가 3인 부서의 부서번호를 6으로 수정
-- DEPARTMENT의 부서번호를 EMPLOYEE가 참조 중이므로 수정이 안 됨
/*
        부서   -   사원
         1     -    1
         2     -    1
         3     -    2
         4     -    2
         5     -    3
3번부서를 6번부서로 바꿀 때
부서 자체만 봤을 때는 문제 없지만, 사원이 부서의 부서 번호를 참조하므로 FK의 특성인 참조 무결성에 위배된다.
*/

-- 해결책
-- 1. 외래키 일시중지
-- 2. 수정(FK 테이블 수정 -> PK 테이블 수정)
-- 3. 외래키 재시작

ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 중지

--------------------
UPDATE EMPLOYEE
   SET DEPART = 6
 WHERE DEPART = 3;

UPDATE DEPARTMENT
   SET DEPT_NO = 6
 WHERE DEPT_NO = 3;
--------------------> 두 개의 실행문도 하나의 트랜잭션

ALTER TABLE EMPLOYEE
    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 재시작

-- 부서번호 1인 사원들의 월급을 100000 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;

-- 직급이 '과장'인 사원들의 월급을 10% 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY * 1.1
 WHERE POSITION = '과장';



-- 테이블 삭제
-- 부서번호가 4인 부서를 삭제
-- 부서번호가 4인 행(ROW)을 참조하는 사원이 없으므로 정상 삭제
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 4;  -- 부서번호가 4인 사원은 아무도 없었기 때문에 문제없이 작업 실행.

-- 부서번호가 1인 부서를 삭제
-- 부서번호가 1인 행(ROW)의 소속부서가 NULL 값으로 함께 변경(외래키 옵션을 ON DELETE SET NULL 처리했기 때문에)
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 1;  -- FK 설정에 [ON DELETE SET NULL]을 해놨기 때문에 부서 테이블의 DEPT_NO가 1인 행 삭제, DEPART가 1인 사원의 부서번호는 NULL 처리가 된다.
                     -- 설정을 [ON DELETE CASCADE]로 하면 부서번호 1이 삭제 되면서 DEPART가 1인 사원 자체도 같이 삭제된다.
COMMIT;
