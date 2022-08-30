-- 그룹(GROUP BY)
-- 1. GROUP BY절에서 지정한 칼럼의 데이터는 하나로 모아서 한 번만 조회가 됨
-- 2. SELECT절에서 조회할 칼럼은 "반드시" GROUP BY절에 존재해야 함. 1) 예시처럼
--    GROUP BY절과 SELECT절은 종속관계 


-- EMPLOYEE 테이블

-- 1. 동일한 부서번호(DEPARTMENT_ID)로 그룹화하여 조회
SELECT DEPARTMENT_ID
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;   -- DEPT_ID가 동일한게 여러개 있는데 그걸 하나로 모아서 하나만 조회

-- 2. 그룹화 실패
-- EMPLOYEE_ID를 조회하려면 GROUP BY절에 EMPLOYEE_ID가 있어야 함
SELECT EMPLOYEE_ID
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;  -- not a GROUP BY expression
 
-- 3. 그룹화 함수 사용
-- 집계함수(그룹함수)는 GROPU BY절에 해당 칼럼이 없어도 SELECT절에서 조회 가능함
SELECT 
       DEPARTMENT_ID
     , SUM(SALARY) AS 부서별연봉합계
     , FLOOR(AVG(SALARY)) AS 부서별연봉평균
     , MAX(SALARY) AS 부서별최대연봉
     , MIN(SALARY) AS 부서별최소연봉
     , COUNT(*) AS 부서별사원수
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;
-- 함수들은 SALARY 참조중
-- GROUP BY절에 있는 것만 조회 가능하다!
 
-- 4. 조건 지정
--    1) GROUP BY로 처리할 행(ROW)은 적을수록 성능에 유리함
--    2) WHERE절  : GROUP BY 이전에 처리하므로 가능한 모든 조건은 WHERE절에서 처리함. 조건은 최대한 WHERE절에 두는 것이 좋다.
--                  그룹화할 대상을 그룹화 이전에 미리 줄이기 위함. 성능 향상을 위해서
--    3) HAVING절 : WHERE절로 처리할 수 없는 조건만 HAVING절에서 처리함

-- 1) 부서번호가 100 미만인 부서들의 연봉평균 조회하기(WHERE절, HAVING절 모두 가능한 조건)
-- WHERE절 : 성능이 더 우수한 쿼리
-- 과정 : 조건을 만족하지 않는 불필요한 ID는 WHERE절에서 미리 빼놓고 그룹화
SELECT
       DEPARTMENT_ID
     , FLOOR(AVG(SALARY)) AS 부서별연봉평균
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID < 100
 GROUP BY DEPARTMENT_ID;

-- HAVING절 : 가능하지만 성능이 떨어지는 쿼리
-- 과정 : 일단 모든 ID를 다 그룹화해놓고 HAVING절 만족하는 조건으로 불필요한 ID 빼놓음
SELECT
       DEPARTMENT_ID
     , FLOOR(AVG(SALARY)) AS 부서별연봉평균
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
HAVING DEPARTMENT_ID < 100;

-- WHERE절, HAVING절 모두 조건을 다는 절이기 때문에 같은 결과가 나오지만, 
-- 그룹화(GROUP BY) 이전에 작업하는 WHERE절을 사용하는 것이 훨씬 좋다

-- 2) 소속된 사원수가 10명 이상인 부서의 연봉평균 조회하기(HAVING절만 가능)
-- 소속된 사원수는 GROUP BY 이후에만 알 수 있기 때문에 WHERE절로 처리가 불가능
SELECT
       DEPARTMENT_ID
     , COUNT(*) AS 부서별사원수
     , FLOOR(AVG(SALARY)) AS 부서별연봉평균
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
HAVING COUNT(*) >= 10;
-- 부서번호(ID)에서 먼저 그룹화 작업을 한 후에 10명 이상인 부서라는 조건을 걸 수 있는 상황이기때문에 HAVING
-- WHERE COUNT(*) >= 10
--  └> WHERE절로 하면 나오는 오류 "group function is not allowed here"
 
-- JOB_ID가 같은 사원수
SELECT
       JOB_ID
     , AVG(SALARY)
  FROM EMPLOYEES
 WHERE MANAGER_ID <= 100
 GROUP BY JOB_ID;
 

-- EMPLOYEES 테이블 연습.

-- 1. 급여 평균이 10000 이상인 부서의 부서번호과 급여평균을 조회하기
SELECT
       DEPARTMENT_ID
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
 HAVING AVG(SALARY) >= 10000;

-- 2. 동일한 DEPARTMENT_ID로 그룹화하기
--    동일한 부서번호를 가진 사원들을 JOB_ID로 다시 그룹화하기
--    즉, 부서별, JOB_ID별로 그룹화하기
--    부서별, 직업아이디별로 그룹화하여 각 그룹의 사원수 조회하기
--    부서번호가 없는 사원은 제외하기

SELECT
       DEPARTMENT_ID
     , JOB_ID
     , COUNT(*) AS 사원수
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY DEPARTMENT_ID, JOB_ID     -- 1차, 2차 순서로 진행
 ORDER BY 사원수;
-- 조회하고 싶은 칼럼은 GROUP BY절과 SELECT절이 같아야한다. SELECT절에 그룹화대상 칼럼 이외에는 함수만 올 수 있음
-- GROUP BY가 2개 이상일 때 DEPARTMENT_ID만 넣어도 되고, JOB_ID만 넣어도 되고, 두개 다 넣어도 됨
-- SELECT절 다음에 마지막으로 ORDER BY절이 오기때문에 사원수라고 넣기 가능


-- DEPARTMENTS 테이블 연습.

-- 1. 동일한 지역(LOCATION_ID)으로 그룹화하여 조회하기
SELECT LOCATION_ID
  FROM DEPARTMENTS
 GROUP BY LOCATION_ID;
 
-- 2. 동일한 지역으로 그룹화하여 각 지역별 존재하는 부서수 조회하기
--    부서수가 2 이상인 지역만 조회하기
SELECT
       LOCATION_ID
     , COUNT(*) AS 지역별부서수
  FROM DEPARTMENTS
 GROUP BY LOCATION_ID
HAVING COUNT(*) >= 2;
-- 조회하고 싶은 칼럼은 GROUP BY절과 SELECT절이 같아야한다. SELECT절에 그룹화대상 칼럼 이외에는 함수만 올 수 있음
 
-- 3. 동일한 지역으로 그룹화하여 각 지역별 존재하는 부서수 조회
--    MANAGER_ID가 없는 지역은 제외하고 조회학
SELECT
       LOCATION_ID
     , COUNT(*)
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NOT NULL
 GROUP BY LOCATION_ID;
 
-- 4. 부서명(DEPARTMENT_NAME)의 첫 2글자로 그룹화하여 해당그룹의 개수 조회하기
SELECT
       SUBSTR(DEPARTMENT_NAME, 1, 2)
     , COUNT(*)
  FROM DEPARTMENTS
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);
-- GROUP BY절에 함수가 들어가면 SELECT절에도 함수가 그대로 들어가야 한다.
 
-- 5. 부서명의 첫 2글자로 그룹화하여 해당그룹의 개수 조회하기
--    부서명의 첫 2글자가 'IT', 'CO'인 부서만 조회하기
SELECT SUBSTR(DEPARTMENT_NAME, 1, 2)
     , COUNT(*)
  FROM DEPARTMENTS
 WHERE SUBSTR(DEPARTMENT_NAME, 1, 2) IN('IT', 'Co')       -- '김%' '이%'는 WHERE OR로 해줘야함 IN으로 불가
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);
       