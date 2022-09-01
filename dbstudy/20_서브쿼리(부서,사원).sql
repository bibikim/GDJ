
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
           (2) PK나 UNIQUE 칼럼의 동등 비교(=) 결과, 함수(SUM, AVG, MAX, MIN 등)의 결과
           (3) 단일 행 연산자를 사용(=, !=, >, >=, <, <=)
        2) 다중 행 서브쿼리
           (1) 서브쿼리 결과가 2개 이상
           (2) FROM절(인라인뷰), WHERE절에서 사용
           (3) 다중 행 연산자를 사용(IN, ANY, ALL 등)  -> 동등비교가 안 되면서 함수도 아니면 여러개의 결과(가능성↑). 따라서 다중행
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
                    FROM DEPARTMENT     -- 서브쿼리(부서번호가 2인 부서의 지역 조회) 결과값이 (서울);이면 되는거임
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
                     ---> IN으로 처리하면 '대리 직급 OR 부장 직급'이 되기 때문에 가능해짐
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
         
         
/*
    가상 칼럼
    
    1. PSEUDO 수도..~ P는 묵음이야
    2. 존재하지만 저장되어있지 않은 칼럼
    3. 사용할 수 있으나 일부 제약이 있음
    4. 종류
        1) ROWID  : 행(ROW)의 ID, 어떤 행의 물리적 저장 위치
        2) ROWNUM : 행(RWO)의 NUMBER, 어떤 행의 번호
*/

-- ROWID
SELECT ROWID, EMP_NO, NAME
  FROM EMPLOYEE;

-- 현존하는 가장 빠른 조회 방식
-- ROWID를 직접 사용하는 것은 어렵기 때문에 인덱스(INDEX)를 사용
SELECT EMP_NO, NAME
  FROM EMPLOYEE
 WHERE ROWID = 'AAAFBPAABAAALDBAAC';

-- ROUNUM
SELECT ROWNUM, EMP_NO, NAME
  FROM EMPLOYEE;
  
-- ROWNUM 사용 방법
-- 1. ROWNUM은 1을 포함하는 범위는 조건으로 사용할 수 있음(1을 포함해서 조회해야 함 BETWEEN 1 AND N)
-- 2. ROWNUM은 1을 포함하지 않는 범위는 조건으로 사용할 수 없음
SELECT EMP_NO, NAME
  FROM EMPLOYEE
 WHERE ROWNUM = 2;

-- ROWNUM을 1 이외의 범위를 조건으로 사용하는 방법
-- ROWNUM에 별명을 지정하고 해당 별명을 사용하면 됨
-- 3 : SELECT 
-- 1 :   FROM (ROWNUM의 별명 지정하기)
-- 2 :  WHERE ROWNUM의 별명 사용하기
SELECT A.ROW_NUM, A.EMP_NO, A.NAME
  FROM (SELECT ROWNUM AS ROW_NUM, EMP_NO, NAME
          FROM EMPLOYEE) A
 WHERE A.ROW_NUM = 2;


-- 1. 연봉 기준으로 가장 높은 연봉을 받는 사원 조회하기

-- 1) WHERE절의 서브쿼리 이용
SELECT EMP_NO, NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)
                   FROM EMPLOYEE);

-- 2) 정렬과 ROWNUM 이용
--    (1) 연봉의 내림차순 정렬을 수행(가장 높은 연봉이 1번째 행이 됨)
--    (2) 정렬 결과에서 ROWNUM이 1인 행을 조회
SELECT ROWNUM, A.EMP_NO, A.NAME, A.SALARY   -- 정렬한 다음에 정렬한 순서를 바탕으로 번호를 붙여야 되므로 서브쿼리가 아닌 메인쿼리에 ROWNUM을 붙여야 한다
  FROM (SELECT EMP_NO, NAME, SALARY
          FROM EMPLOYEE
         ORDER BY SALARY DESC) A   -- 서브쿼리에서는 SALARY 내림차순 정렬.
 WHERE ROWNUM = 1;
 
-- 2. 2번째로 높은 연봉을 받는 사원 조회하기

-- 1) ROWNUM 칼럼 ★★ 중요
--    인라인뷰 A : 연봉으로 정렬한 테이블
--    인라인뷰 B : 정렬이 끝난 테이블에 행 번호(ROW_NUM)를 추가한 테이블
--    실제 행번호를 쓰려면 별명까지 지정해야 하므로 WHERE절에서 B라는 이름을 줌
-- 2개의 인라인뷰와 1개의 메인쿼리
SELECT B.ROW_NUM, B.EMP_NO, B.NAME, B.SALARY
  FROM (SELECT ROWNUM AS ROW_NUM, A.EMP_NO, A.NAME, A.SALARY
          FROM (SELECT EMP_NO, NAME, SALARY
                  FROM EMPLOYEE
                 ORDER BY SALARY DESC) A) B
 WHERE B.ROW_NUM = 2;  -- (=) B.ROW_NUM BETWEEN 2 AND 2;
-- * EX) 웹툰페이지 회차 목록 가져오기 *
--      1. 회차로 DESC (최신회차 순으로 내림)
--      2. 정렬한 결과에 ROWNUM(행 번호) 붙여주기
--      3. ROWNUM의 1~10을 가져오면 1페이지, 11~20을 가져오면 2페이지, ...
--                      BETWEEN 1 AND 10   , BETWEEN 11 AND 20
SELECT B.RW, B.EMP_NO, B.NAME, B.SALARY
  FROM (SELECT ROWNUM AS RW, EMP_NO, NAME, SALARY
          FROM (SELECT EMP_NO, NAME, SALARY
                  FROM EMPLOYEE
                 ORDER BY SALARY DESC) A) B
 WHERE B.RW BETWEEN 2 AND 2;  -- B.RW 
 
-- 2) ROW_NUMBER() 함수 ★★                           >>  목록보기 추천하는 쿼리
--    정렬과 행 번호 추가를 동시에 진행하는 함수
SELECT A.RW, A.EMP_NO, A.NAME, A.SALARY
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS RW, EMP_NO, NAME, SALARY  -- ROW_NUBER함수
          FROM EMPLOYEE) A
 WHERE A.RW BETWEEN 2 AND 2;
 
-- 3) RANK() 함수
--    정렬 후 순위 매기는 함수
--    목록 가져오기에서는 부적절(동점자 처리 때문에 가져오는 목록의 수가 매번 달라질 수 있다.)
SELECT R.EMP_NO, R.NAME, R.SALARY
  FROM (SELECT RANK() OVER(ORDER BY SALARY DESC) AS 순위, EMP_NO, NAME, SALARY
          FROM EMPLOYEE) R
 WHERE R.순위 = 2;  -- 같은 연봉을 가지고 있으면 값을 두 개 가져올 수도 있기 때문에 부적절.. 1), 2)는 결과를 1개만 주기 때문
 
 
-- 3.  3~4번째로 입사한 사원 조회하기
-- 인라인뷰 2개 사용
-- 제일 안쪽 인라인뷰(A)에서는 정렬을 하고 그 쿼리자체(가상의 테이블)에 별명A을 지정
-- 그 바깥 인라인뷰(B)에서는 정렬한 순에다가 ROWNUM 추가
SELECT B.ROW_NUM, B.EMP_NO, B.NAME, B.HIRE_DATE
  FROM (SELECT ROWNUM AS ROW_NUM, A.EMP_NO, A.NAME, A.HIRE_DATE
          FROM (SELECT EMP_NO, NAME, HIRE_DATE
                  FROM EMPLOYEE
                 ORDER BY HIRE_DATE) A) B
 WHERE B.ROW_NUM BETWEEN 3 AND 4; 

-- ROW_NUMBER() 함수 사용
SELECT A.입사순번, A.EMP_NO, A.NAME, A.HIRE_DATE
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY HIRE_DATE) AS 입사순번, EMP_NO, NAME, HIRE_DATE
          FROM EMPLOYEE) A
 WHERE A.입사순번 BETWEEN 3 AND 4;
 
 
/* 기타 서브쿼리 : CREATE, UPDATE, DELETE 등에서 활용 */

-- CREATE와 서브쿼리
-- 1. 서브쿼리 결과 집합을 테이블로 저장
-- 2. 테이블 복사할 때 사용
-- 3. NOT NULL 제약조건을 제외한 제약조건은 복사되지 않음(기본키, 외래키, UNIQUE, CHECK 이런 것들은 복사 안 됨)
-- 4. 형식
--    CREATE TABLE 테이블_이름 AS (서브쿼리)

-- 1. EMPLOYEE 테이블 복사하기
CREATE TABLE EMPLOYEE2 
    AS (SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
          FROM EMPLOYEE);
          
-- USER_CONSTRAINTS : 현재 사용자(SCOTT)가 사용가능한 제약조건들은 저 테이블(데이터사전)에 만들어져 있음
-- 제약조건이 복사되지 않았는지 확인
DESC USER_CONSTRAINTS;
SELECT CONSTRAINT_NAME
  FROM USER_CONSTRAINTS
 WHERE TABLE_NAME = 'EMPLOYEE2';

-- EMPLOYEE2 테이블에 PK 제약조건을 추가하고 싶다면?
ALTER TABLE EMPLOYEE2
    ADD CONSTRAINT PK_EMPLOYEE2 PRIMARY KEY(EMP_NO);

-- 2. DEPARTMENT 테이블의 구조만 복사하기(모든 행은 제외하고 복사하기)
CREATE TABLE DEPARTMENT2
    AS (SELECT DEPT_NO, DEPT_NAME, LOCATION
          FROM DEPARTMENT
         WHERE 1 = 2);
-- 절대 만족하지 않는 조건을 WHERE절에 넣어준다. 조회되는 행이 없기 때문에 행은 제외하고 구조만 복사

-- 행 제외하고 복사되었는지 확인
SELECT DEPT_NO, DEPT_NAME, LOCATION FROM DEPARTMENT2;

-- INSERT와 서브쿼리
-- 1. VALUES절 대신 서브쿼리 이용
-- 2. 서브쿼리의 결과 집합이 INSERT됨
-- 3. 형식
--    INSERT INTO 테이블_이름(칼럼1, 칼럼2, ...) (서브쿼리)

-- 3. DEPARTMENT 테이블의 모든 행(ROW)을 DEPARTMENT2 테이블에 삽입
INSERT INTO DEPARTMENT2(DEPT_NO, DEPT_NAME, LOCATION)
(SELECT DEPT_NO, DEPT_NAME, LOCATION
   FROM DEPARTMENT);
COMMIT;

-- UPDATE와 서브쿼리
-- SET절이나 WHERE절에서 서브쿼리 활용
UPDATE EMPLOYEE2
   SET NAME = '김한비'
     , GENDER = 'F'
 WHERE EMP_NO = 1002;
 
UPDATE EMPLOYEE2
   SET NAME = (SELECT NAME FROM EMPLOYEE WHERE EMP_NO = 1004)     -- 1004번의 사원의 NAME을 가져와서 그걸 넘겨라
     , GENDER = (SELECT GENDER FROM EMPLOYEE WHERE EMP_NO = 1004)
 WHERE EMP_NO = 1002;
 
UPDATE EMPLOYEE2
   SET (NAME, GENDER) = (SELECT NAME, GENDER                      -- SELECT절의 결과와 SET절의 조건을 비교하는 쿼리는
                           FROM EMPLOYEE                          -- DELETE WHERE절에서도 가넝..
                          WHERE EMP_NO = 1003)
 WHERE EMP_NO = 1002;
 
COMMIT;

SELECT EMP_NO, NAME, GENDER FROM EMPLOYEE2;


-- DELETE와 서브쿼리
-- WHERE절에서 주로 사용
-- 부서이름이 영업부인 사원제거
DELETE  
  FROM EMPLOYEE2
 WHERE DEPART IN (SELECT DEPT_NO                  -- 부서번호 IN (영업부의 부서번호) 영업부는 PK가 아님. 따라서 영업부 조건의 결과는 2개 이상일 수 있으니 다중행
                    FROM DEPARTMENT2
                   WHERE DEPT_NAME = '영업부');
COMMIT;
-- 영업부 사라졌는지 확인
SELECT E.EMP_NO, D.DEPT_NAME
  FROM DEPARTMENT2 D INNER JOIN EMPLOYEE2 E
    ON D.DEPT_NO = E.DEPART;
    
    