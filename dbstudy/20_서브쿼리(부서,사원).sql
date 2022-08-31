
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
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '이종석', 1, '과장', 'M', '21-05-01', 3000000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '김태리', 2, '대리', 'F', '21-11-10', 2200000);
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '김윤석', 1, '부장', NULL, '16/04/01', 7000000);
COMMIT;


/****************************************************************************************************************/

/*
    서브쿼리
    
    1. SUB QUERY
    2. 메인쿼리(MAIN QUERY)에 포함하는 하위쿼리(SUB QUERY)
    3. 서브쿼리는 메인쿼리에 괄호()를 이용해서 포함
    4. 항상 서브쿼리를 먼저 실행하고, 서브쿼리 실행 결과를 메인쿼리에서 사용함
    5. 사용되는 절에 따른 구분
        1) SELECT절 : 스칼라 서브쿼리
        2) FROM절   : 인라인뷰         
        3) WHERE절  : 서브쿼리
    6. 서브쿼리 결과에 따른 구분
        1) 단일 행 서브쿼리(SINGLE ROW SUB QUERY)
           (1) 서브쿼리 결과가 1개
           (2) PK나 UNIQUE 칼럼의 동등 비교(=) 결과, 함수(SUM, AVG, MAX, MIN 등)의 결과    -> 동등비교가 안되면서 함수도 아니면 여러개의 결과. 따라서 다중행 
           (3) 단일 행 연산자를 사용(=, !=, >, >=, <, <=)
        2) 다중 행 서브쿼리
           (1) 서브쿼리 결과가 2개 이상
           (2) FROM절(인라인뷰*), WHERE절에서 사용
           (3) 다중 행 연산자를 사용(IN, ANY, ALL  등)
*/

/* WHERE절의 서브쿼리 */

-- 1. 사원번호가 1001인 사원과 같은 직급(POSITION)을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = (SELECT POSITION         -- 단일 행 서브쿼리이므로 연산자 =를 사용, 하위쿼리는 메인쿼리와 동등비교(=)되므로 반드시 POSITION을 반환
                     FROM EMPLOYEE
                    WHERE EMP_NO = 1001);  -- EMP_NO는 PK이므로 단일 행 서브쿼리
                    -- EMP_NO = 1001이라는건 결과값은 1개라는 것.
                    -- WHERE절에서 포지션(포지션 비교)이 궁금한거. 따라서 서브쿼리에서도 포지션으로 반환해줘야 한다

-- 2. 급여(SALARY)가 가장 높은 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)   -- 함수는 SELECT~FROM 필수
                   FROM EMPLOYEE);    -- 서브쿼리가 함수이므로 단일 행 서브쿼리

-- 3. 부서번호가 2인 부서와 같은 지역에 있는 부서 정보를 조회
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE LOCATION = (SELECT LOCATION
                    FROM DEPARTMENT     -- 서브쿼리 결과값이 (서울); 상태이면 되는거임
                   WHERE DEPT_NO = 2);  -- DEPT_NO는 PK이므로 단일 행 서브쿼리

-- 4. 평균급여 이상을 받는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)    -- 서브쿼리가 함수이므로 단일 행 서브쿼리, 크기비교 연산자 사용 가능
                   FROM EMPLOYEE);

-- 5. 평균근속기간 이상을 근무한 사원 조회하기
-- 근속기간 구하기
-- 1) 일수 계산   : SYSDATE - HIRE_DATE (날짜는 - 처리가 가능하다)
-- 2) 개월수 계산 : MONTHS_BETWEEN(SYSDATE, HIRE_DATE)
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
                                                 FROM EMPLOYEE);
                                                
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE (SYSDATE - HIRE_DATE) >= (SELECT AVG(SYSDATE - HIRE_DATE)  -- 서브쿼리가 함수 -> 단일행 서브쿼리
                                   FROM EMPLOYEE);                            

-- 6. 부서번호가 1인 부서에 근무하는 사원들의 직급과 일치하는 직급을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE POSITION IN (SELECT POSITION      -- 다중행 서브쿼리의 동등 비교는 IN연산으로 수행해야 함
                     FROM EMPLOYEE
                    WHERE DEPART = 1);   -- DEPART가 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리 --> 등호 사용 불가
                     ---> 서브쿼리 결과가 (대리, 부장);이 나오는데 2개 이상의 결과(대리, 부장)가 나와서 등호 연산으로 처리 불가
                     ---> IN으로 처리하면 대리이거나 부장인 포지션이 되기 때문에 가능해짐
                     -- IN이 대표적인 다중행 서브쿼리 연산

-- TIP) 단일 행/다중 행 상관 없이 동등 비교는 IN 연산으로 수행 가능
--        POSITION = '과장'
--    (=) POSITION IN('과장')

-- 7. 부서명이 '영업부'인 부서에 근무하는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART IN (SELECT DEPT_NO
                   FROM DEPARTMENT
                  WHERE DEPT_NAME = '영업부');  -- DEPT_NAME은 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리. 결과가 하나뿐이라도 단일행 서브쿼리가 아님
-- WHERE DEPART IN(영업부의 부서번호);

-- 7번 조인으로 풀기
SELECT E.EMP_NO, E.NAME, E.DEPART, E.POSITION, E.GENDER, E.HIRE_DATE, E.SALARY
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.DEPT_NAME = '영업부';

-- 8. 직급이 '과장'인 사원들이 근무하는 부서 조회하기
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NO IN(SELECT DEPART
                    FROM EMPLOYEE
                   WHERE POSITION = '과장');  -- DEPT_NAME은 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리.
-- WHERE DEPT_NO IN(과장들의 부서번호);  과장들의 부서번호가 여러개 나올 수 있기 때문에 다중행

-- 8번 조인으로 풀기 
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE E.POSITION = '과장';

-- 9. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 어떤 급여든(3000000, 7000000) 하나의 급여보다 많이 받으면 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE SALARY > ANY(SELECT SALARY
                     FROM EMPLOYEE
                    WHERE DEPART = 1);   -- DEPART가 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리
-- WHERE SALARY > ANY(3000000, 7000000)
-- SALARY가 3000000보다 크거나, 7000000보다 크면 됨(ANY => OR개념). 잘 안 씀
-- 따라서 최소급여 3000000보다 크면 만족하는 상황
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
WHERE SALARY > (SELECT MIN(SALARY)
                  FROM EMPLOYEE
                 WHERE DEPART = 1);  -- PK/UNIQUE가 아니지만 (집계)함수 MIN를 썼기 때문에 비교연산(>) 가능 -> 단일행 서브쿼리

-- 10. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 모든 급여(3000000, 7000000)와 비교해서 많이 받으면 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE SALARY > ALL(SELECT SALARY
                      FROM EMPLOYEE
                     WHERE DEPART = 1);   -- DEPART가 PK/UNIQUE가 아니기 때문에 다중 행 서브쿼리
-- WHERE SALARY > ALL(3000000, 7000000)
-- SALARY가 3000000보다 크고 7000000보다 크면 됨(ALL => AND개념). 잘 안 씀
-- 따라서 최대급여 7000000보다 크면 만족하는 상황
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
WHERE SALARY > (SELECT MAX(SALARY)
                  FROM EMPLOYEE
                 WHERE DEPART = 1);  -- PK/UNIQUE가 아니지만 (집계)함수 MAX를 썼기 때문에 비교연산(>) 가능 -> 단일행 서브쿼리



/* SELECT절의 서브쿼리(스칼라 서브쿼리: 단일행) */ 

-- 1. 전체 사원의 인원수, 급여 합계/평균/최대/최소 조회하기
SELECT 
       (SELECT COUNT(*) FROM EMPLOYEE)
      , (SELECT SUM(SALARY) FROM EMPLOYEE)
      , (SELECT AVG(SALARY) FROM EMPLOYEE)
      , (SELECT MAX(SALARY) FROM EMPLOYEE)
  FROM 
       DUAL;    -- SELECT절에서 EMPLOYEE 다 뽑아 썼으니까 따로 굳이 뽑아쓸게 없음.

-- 2. 부서번호가 1인 부서와 같은 지역에서 근무하는 사원 조회하기
-- 서브쿼리와 조인 모두 사용
--    사원번호, 사원명, 부서번호, 부서명 출력
SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION       -- (부서번호가 1인 부서의 지역이름); 메인쿼리에서 LOCATION을 찾으니까 서브쿼리에서도 LOCATION을 반환해줘야 함
                       FROM DEPARTMENT
                      WHERE DEPT_NO = 1);  -- 단일행(=를 사용했으니까) DEPT_NO가 기본키. 기본키의 동등비교는 답이 1개. 따라서 단일행
                      
-- 스칼라 서브쿼리 접근

-- 스칼라 서브쿼리는 일치하지 않는 정보를 NULL로 처리해서 가져옴
-- 따라서 스칼라 서브쿼리와 동일한 방식의 조인은 '외부 조인' 
SELECT
       E.EMP_NO
     , E.NAME
     , E.DEPART
     , (SELECT D.DEPT_NAME
          FROM DEPARTMENT D   
         WHERE D.DEPT_NO = E.DEPART    -- EMP테이블과 DEPT테이블을 관계지어서 일치하는 정보 조건 달고 가져오기.
           AND D.DEPT_NO = 1)
  FROM
       EMPLOYEE E;  
                                       -- 사원테이블에서 가져오는 정보들은 NO NAME DEPART는 그냥 FROM EMPLOYEE로 하고 
                                       -- 부서테이블에서 가져와야하는 정보만 별도의 SELECT절을 가지고 오는 방식
-- 조인 접근
SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION       -- (부서번호가 1인 부서의 지역이름); 메인쿼리에서 LOCATION을 찾으니까 서브쿼리에서도 LOCATION을 반환해줘야 함
                       FROM DEPARTMENT
                      WHERE DEPT_NO = 1); 
                      
                     
                      
/* FROM절의 서브쿼리(인라인뷰: 다중행) */  
-- 뷰(복사본) = 테이블(원본) 따라서 뷰를 가지고 SELECT도 가능

/*
    인라인뷰(Inline View)
    
    1. FROM절에서 사용하는 서브쿼리를 의미함
    2. 인라인뷰는 주로 테이블 형식임(테이블을 반환하는 서브쿼리다.)
    3. 인라인뷰에 별명을 주고 사용함
    4. 인라인뷰에서 조회한 칼럼만 메인쿼리에서 조회할 수 있음(FROM절에 들어가있는 칼럼만 메인쿼리에서 조회 가능하단 얘기)
    5. SELECT문의 실행순서를 바꿀 때 사용(ORDER BY절 정렬을 먼저 하고 싶을 때)
        └> 목록보기 구현할 때 쓰임. ex) 웹툰 회차 목록 -> 정렬부터 한 후에 조회해야함.
*/

SELECT A.EMP_NO, A.NAME, A.POSITION  -- 인라인뷰가 조회한 칼럼만 작성할 수 있다.
  FROM (SELECT EMP_NO, NAME, POSITION
          FROM EMPLOYEE
         WHERE DEPART = 1) A;  -- 인라인뷰의 별명은 A







