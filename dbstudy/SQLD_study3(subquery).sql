/* 서브쿼리 연습문제 */

-- 테이블 삭제
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- DEPARTMENT 테이블 생성
CREATE TABLE DEPARTMENT(
    DEPT_NO   NUMBER            NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION  VARCHAR2(15 BYTE) NOT NULL
);

-- EMPLOYEE 테이블 생성
CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER            NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER            NULL,
    POSITION  VARCHAR2(20 BYTE) NULL,
    GENDER    CHAR(2)           NULL,
    HIRE_DATE DATE              NULL, 
    SALARY    NUMBER            NULL
);

-- 기본키
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

-- 외래키
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO)
            ON DELETE SET NULL;

-- 부서 테이블에서 사용할 부서_시퀀스
DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100
    NOCACHE
    NOCYCLE;

-- 부서 테이블에 행(Row) 삽입
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
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '구창민', 1, '과장', 'M', '95/05/01', 5000000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '김민서', 1, '사원', 'F', '17/09/01', 2000000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '이은영', 2, '부장', NULL, '90-09-01', 5500000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01', 5000000);
COMMIT;



-----------------------------------------------------문제 시작!

/* WHERE절의 서브쿼리 */

/*
    단일행 서브쿼리 - 서브쿼리의 결과가 1개인 경우. PK/UNIQUE 칼럼의 동등비교 결과 혹은 집계함수(SUM, AVG, MAX, MIN)의 결과
    
    다중행 서브쿼리 - 서브쿼리의 결과가 2개 이상인 경우. FROM절, WHERE절에서 사용. 다중행 연산자(IN, ANY, ALL, EXISTS)의 결과
                                                                                            IN
*/

-- 1. 사원번호가 1001인 사원과 같은 직급(POSITION)을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = (SELECT POSITION         -- 단일 행 서브쿼리이므로 연산자 =를 사용. 하위쿼리는 메인쿼리와 동등비교 되므로 반드시 POSITION을 반환
                     FROM EMPLOYEE 
                    WHERE EMP_NO = 1001);  -- EMP_NO는 PK로, 중복값을 갖지 않기 때문에 결과값이 1건임. 따라서 단일행 서브쿼리

-- 2. 급여(SALARY)가 가장 높은 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)  -- WHERE절에서는 집계함수(SUM, AVG, COUNT, MAX, MIN 등) 사용불가 -> 따라서 서브쿼리로 조회해주고 동등비교해서 메인쿼리 실행
                   FROM EMPLOYEE);   -- 함수는 SELECT~FROM절 필수다.

-- 3. 부서번호가 2인 부서와 같은 지역에 있는 부서 정보를 조회
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE LOCATION = (SELECT LOCATION
                    FROM DEPARTMENT    -- 부서번호가 2인 부서의 지역 조회하는 서브쿼리 결과값을 메인쿼리 WHERE절과 동등비교
                   WHERE DEPT_NO = 2); -- DEPT_NO는 PK니까 단일 행 서브쿼리
                   
-- 4. 평균급여 이상을 받는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)    -- 서브쿼리가 함수면 단일 행 서브쿼리! 크기비교 연산자 사용 가능
                   FROM EMPLOYEE); 
                   
-- 5. 평균근속기간 이상을 근무한 사원 조회하기
-- 근속기간 구하기
-- 1) 일수 계산   : SYSDATE - HIRE_DATE (날짜는 - 처리가 가능하다)
-- 2) 개월수 계산 : MONTHS_BETWEEN(SYSDATE, HIRE_DATE)
SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
                                                FROM EMPLOYEE);

SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SYSDATE - HIRE_DATE >= (SELECT AVG(SYSDATE - HIRE_DATE)
                                 FROM EMPLOYEE);
                                 
-- 6. 부서번호가 1인 부서에 근무하는 사원들의 직급과 일치하는 직급을 가진 사원 조회하기  / 최종적으로 과장이나 사원 직급을 가진 사원이 조회됨
SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION IN (SELECT POSITION       -- 다중행 서브쿼리의 동등비교는 IN연산으로 수행해야 한다.
                     FROM EMPLOYEE
                    WHERE DEPART = 1);    -- DEPART가 PK나 UNIQUE가 아니기 때문에 다중행 서브쿼리 => 등호(=) 사용 불가
                                          -- 서브쿼리 결과가 과장, 사원이 나옴. 2개 이상의 결과기 때문에 등호가 불가!
                                          -- 이럴 때, IN으로 처리하면 '과장 OR 부장 직급'이 되기 때문에 모두 조회
                                          -- IN이 대표적인 다중행 서브쿼리 연산(단일행에서도 사용 가능)
                                          
-- 7. 부서명이 '영업부'인 부서에 근무하는 사원 조회하기
SELECT E.EMP_NO, E.NAME, E.DEPART, E.POSITION, E.HIRE_DATE, E.SALARY
  FROM EMPLOYEE E INNER JOIN DEPARTMENT D
    ON D.DEPT_NO = E.DEPART
 WHERE DEPT_NAME = '영업부';

SELECT EMP_NO, NAME, DEPART, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART IN (SELECT DEPT_NO
                    FROM DEPARTMENT
                   WHERE DEPT_NAME = '영업부');  -- DEPT_NAME가 PK나 UNIQUE가 아니기때문에 다중행
                   
-- 8. 직급이 '과장'인 사원들이 근무하는 부서 조회하기
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NO IN (SELECT DEPART                 -- 다른 테이블의 정보도 필요할 때 서브쿼리에서 공통된 칼럼값을 갖는 칼럼으로
                     FROM EMPLOYEE               -- IN연산자 통해서 비교해보면 되는 듯함..
                    WHERE POSITION = '과장');
                    
-- 9. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 어떤(ANY) 급여든(2000000, 5000000) 하나의 급여보다 많이 받으면 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ANY(SELECT SALARY               -- ANY를 썼기 때문에 다중행 서브쿼리
                      FROM EMPLOYEE
                     WHERE DEPART = 1); 
  -- WHERE SALARY > ANY(2000000, 5000000)
  -- SALARY가 200보다 크거나, 500보다 크면 됨(ANY => OR개념)
  -- 따라서 최소급여 200보다 크면 만족하는 상황이기 때문에, MIN()을 사용하는 것도 방법

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MIN(SALARY)             -- MIN을 사용했기 때문에 단일행
                   FROM EMPLOYEE
                  WHERE DEPART = 1);            -- PK나 UNIQUE가 아니지만 집계함수 MIN을 사용해서 비교연산(>)이 가능 -> 단일행
                  
-- 10. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 모든(ALL) 급여(2000000, 5000000)와 비교해서 보다 많이 받으면 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > ALL (SELECT SALARY              -- ALL을 썼기 때문에 다중행
                    FROM EMPLOYEE
                   WHERE DEPART = 1);
 -- WHERE SALARY > ALL(2000000, 5000000)
 -- SALARY가 최대급여 500보다 크면 됨(ALL => AND개념)
 
 SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MAX(SALARY)             -- MAX집계함수를 썼기 때문에 단일행
                   FROM EMPLOYEE
                  WHERE DEPART = 1);

