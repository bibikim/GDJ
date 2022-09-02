
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

-- 기본키
ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

-- 외래키
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) REFERENCES DEPARTMENT(DEPT_NO)
    ON DELETE SET NULL;

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
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(DEPARTMENT_SEQ.NEXTVAL, '영업부', '대구');
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구'); 
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울'); 

COMMIT;


-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE; 

-- 사원 테이블에 행(Row) 삽입
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '구교환', 2, '과장', 'M', '20/07/19', 5000000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '이종석', 1, '대리', 'M', '21-05-01', 3000000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '김태리', 2, '사원', 'F', '21-11-10', 2200000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '김윤석', 3, '부장', NULL, '16/04/01', 7000000);
COMMIT;

-- 참조무결성 위배 데이터 삽입을 위해서 외래키 일시 중지
ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;
-- 참조무결성 위배 데이터 삽입
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '신세경', 5, '대리', 'F', '19/11/03', 3200000); 
COMMIT;

/******************************************************************************************************/

/*
    조인
   
    1. JOIN
    2. 2개 이상의 테이블을 조회하는 방법
    3. 조회할 테이블들은 관계를 줄 수 있어야 함
    4. 종류
       1) 크로스 조인 : 카테젼 곱(각 테이블의 모든 행을 조인) 행 갯수 = A테이블과 B테이블의 행 곱 / 열 갯수 = A테이블과 B테이블의 열 합
          - CROSS JOIN
          - 조인 조건이 없으면 됨
          - 많은 행을 순식간에 만들 수 있음(기초데이터 작성용)
          - 조인 조건을 잘못 지정한 경우(조인이 안돼서 크로스 조인이 될 수 있음)
       2) 내부 조인
          - INNER JOIN
          - 각 테이블에 일치하는 모든 행을 조인. 가장 일반적인 조인
       3) 외부 조인
          - OUTER JOIN
          - 한 테이블은 일치하는 행을 조인, 한 테이블은 일치하지 않아도 조인
          - 왼쪽 외부 조인(LEFT OUTER JOIN), 오른쪽 외부 조인(RIGHT OUTER JOIN)
       4) 셀프 조인
          - SELF JOIN
          - 한 테이블에 참조 관계가 있는 경우
          - 한 테이블의 특정 칼럼과 다른 특정 칼럼을 조인
    5. 형식
       1) JOIN 문법
           SELECT 칼럼
             FROM 테이블1 JOIN 테이블2
               ON 조인조건
       2) 콤마(,) 문법
           SELECT
             FROM 테이블1, 테이블2
            WHERE 조인조건      
*/
/*
    드라이브(DRIVE) 테이블과 드리븐(DRIVEN) 테이블 (운전 하는 테이블[기준]과 운전 당하는 테이블)
    
    1. 드라이브 테이블
       1) 조인에서 검색할 때 사용하는 테이블
       2) 관계에서 PK를 가진 테이블
       3) 대부분 행(ROW)의 개수가 적은 테이블(Cardinality가 적은 테이블)
    2. 드리븐 테이블
       1) 관계에서 FK를 가진 테이블
       2) 대부분 행(ROW)의 개수가 많은 테이블
    3. 조인할 때 드라이브 테이블을 드리븐 테이블보다 먼저 작성(조인 조건도 위치를 맞춰주기) ★
       (행의 개수만큼 조회검색을 하기 때문에 행의 갯수가 적은 테이블을 적어줘야 적게 조회검색한다)
    
    
*/


-- 1. 크로스 조인 확인
SELECT E.EMP_NO, E.NAME, E.SALARY, D.DEPT_NO, D.DEPT_NAME   -- 어느 테이블의 칼럼이다라고 별명으로 명시를 해주는 것이 일반적.
  FROM DEPARTMENT D CROSS JOIN EMPLOYEE E;
-- 부서4, 직원5 => 20개의 데이터 조회(불필요하게 조회시켜주는... 별로인 조인)

-- 2. 내부 조인 확인(INNER JOIN)
--    사원번호, 사원명, 부서명을 조회하기
--    두 테이블에 모두 존재(일치)하는 데이터만 조회. (따라서 부서테이블엔 없는 부서번호 5 소속의 사원은 조회되지 X)
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;

-- 3. 외부 조인 확인(OUTER JOIN)
--    사원번호, 사원명, 부서명을 조회하기
--    모든 사원을 반드시 조회하기
--      사원        -          부서
--    모두포함      -    일치하는부서만포함

--    모두 포함시킬 사원테이블을 OUTER JOIN의 왼쪽/오른쪽에 두느냐에 따라 
--    LEFT / RIGHT 로 구분함

-- DRIVE/DRIVEN 테이블이 잘못 지정된 조인
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM EMPLOYEE E LEFT OUTER JOIN DEPARTMENT D  -- 왼쪽의 EMPLOYEE 테이블은 모두 조회
    ON E.DEPART = D.DEPT_NO;
    
--★★ DRIVE/DRIVEN 테이블이 잘 지정된 조인 ★★
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E  -- 오른쪽의 EMPLOYEE 테이블은 모두 조회
    ON D.DEPT_NO = E.DEPART;                     -- 부서테이블 입장에서는 5번 부서는 없는 부서번호이기 때문에 NULL이 뜬다
-- > 둘 다 같은 결과가 나오는데 성능은 아래쪽이 더 좋음.
-- > PK를 가진 DEPARTMENT 테이블이 DRIVE테이블, 아닌 EMPLOYEE 테이블이 DRIVEN테이블

-- 4. 셀프 조인 확인
-- HR 계정에서 EMPLOYEES 테이블 참고
-- EMPLOYEE_ID와 MANAGER_ID 


