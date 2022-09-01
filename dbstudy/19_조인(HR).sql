/* INNER JOIN */

-- 1. 부서위치(LOCATION_ID)가 1700인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
-- DEPARTMENTS 테이블 : LOCATION_ID, DEPARTMENT_NAME
-- EMPLOYEES 테이블   : EMPLOYEE_ID, LAST_NAME
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID     -- 두 테이블의 조인 조건(앞: 기본키, 뒤: 외래키) 성능을 위해
 WHERE D.LOCATION_ID = 1700;                 -- 일반 조건
 
-- 조인 안 쓰고 풀이
 SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME
  FROM DEPARTMENTS D, EMPLOYEES E
 WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID      -- 조인 조건이 일반 조건으로!
   AND D.LOCATION_ID = 1700;                  -- 일반 조건 2개 만족해야하므로 AND연산자

 
-- 2. 부서명이 'Executive'인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 WHERE D.DEPARTMENT_NAME = 'Executive';


-- 3. 직업아이디(JOB_ID)가 변하지 않은 사원들의 EMPLOYEE_ID, LAST_NAME, JOB_ID를 조회하시오.
--    현재 JOB_ID(EMPLOYEES)와 과거 JOB_ID(JOB_HISTORY)가 일치하는 사원을 조회하시오.
SELECT E.EMPLOYEE_ID, E.LAST_NAME, JH.JOB_ID
  FROM EMPLOYEES E INNER JOIN JOB_HISTORY JH
    ON E.EMPLOYEE_ID = JH.EMPLOYEE_ID
 WHERE E.JOB_ID = JH.JOB_ID;
    

-- 4. 부서별(GROUP BY)로 사원수와 평균연봉을 DEPARTMENT_NAME과 함께 조회하시오.
--    평균연봉은 정수로 절사(TRUNC)하고, 사원수의 오름차순(ASC) 정렬하시오.
-- *은 행을 카운트하기 때문에 안전. 
-- 특정칼럼을 지정해서 카운트하면 간혹 그 칼럼에 데이터가 없을 수 있는 경우 온전히 모두 카운트를 하지 못할 수 있기 때문에!
SELECT COUNT(*) AS 사원수, TRUNC(AVG(E.SALARY)), D.DEPARTMENT_NAME
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID  -- 조인 조건
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME
 ORDER BY 사원수;
-- ID로 그룹화 하는 것이 안전. ID가 다르면 이름이 같아도 다른 부서이기 때문에 ID를 그룹화 기준으로 잡는 것이 좋다
-- NAME을 함께 조회해야하니 ID와 NAME 전부 그룹화해서 쿼리를 짜주면 좋다.


-- 5. CITY가 'S'로 시작하는 지역에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY를 조회하시오.
--    EMPLOYEE_ID의 오름차순 정렬로 조회하시오.
-- DEPARTMENTS 테이블 : DEPARTMENT_NAME
-- EMPLOYEES   테이블 : EMPLOYEE_ID, LAST_NAME
-- LOCATIONS   테이블 : CITY
-- D와 E를 먼저 조인, D와 E의 조인조건에 LOCATIONS를 조인
-- 조인이 또 들어갔으니 밑에 두번째 조인조건 ON을 또 달아서 적어준다 (3단 조인)
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID INNER JOIN LOCATIONS L
    ON L.LOCATION_ID = D.LOCATION_ID
 WHERE L.CITY LIKE 'S%'
 ORDER BY E.EMPLOYEE_ID;

-- 조인 안 쓰고 풀이    
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY
  FROM LOCATIONS L, DEPARTMENTS D, EMPLOYEES E
 WHERE L.LOCATION_ID = D.LOCATION_ID
   AND D.DEPARTMENT_ID = E.DEPARTMENT_ID 
   AND L.CITY LIKE 'S%'
 ORDER BY E.EMPLOYEE_ID;


-- 6. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY, COUNTRY_NAME을 조회하시오. (테이블4개 필요)
--    단, DEPARTMENT_ID가 없는 사원은 조회하지 마시오.  --> 쿼리로 구현 안 해줘도 됨
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY, C.COUNTRY_NAME
  FROM COUNTRIES C INNER JOIN LOCATIONS L
    ON C.COUNTRY_ID = L.COUNTRY_ID INNER JOIN DEPARTMENTS D
    ON L.LOCATION_ID = D.LOCATION_ID INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID;  -- 조인조건은 NULL값이 제외된 상태로 처리)
-- WHERE D.DEPARTMENT_ID IS NOT NULL;



/* OUTER JOIN */

-- 7. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
--    부서번호(DEPARTMENT_ID)가 없는 사원도 조회하고, EMPLOYEE_ID순으로 오름차순 정렬하시오.
--    부서번호(DEPARTMENT_ID)가 없는 사원의 부서명(DEPARTMENT_NAME)은 'None'으로 조회하시오.
--      부서(PK)                 사원(FK)
-- 일치하는정보포함(+)         모든정보포함
-- 오른쪽에 있는 사원 테이블의 모든 정보 포함을 위해 오른쪽 외부 조인
SELECT E.EMPLOYEE_ID, E.LAST_NAME, NVL(D.DEPARTMENT_NAME, 'None')
  FROM DEPARTMENTS D RIGHT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 ORDER BY E.EMPLOYEE_ID;

-- 조인 없이
SELECT E.EMPLOYEE_ID, E.LAST_NAME, NVL(D.DEPARTMENT_NAME, 'None')
  FROM DEPARTMENTS D, EMPLOYEES E
 WHERE D.DEPARTMENT_ID(+) = E.DEPARTMENT_ID
 ORDER BY E.EMPLOYEE_ID;

-- 8. 부서별 근무하는 사원수를 조회하시오.
--    단, 근무하는 사원이 없으면 0으로 조회하시오.
SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME, COUNT(E.DEPARTMENT_ID) AS 사원수  
  FROM DEPARTMENTS D LEFT OUTER JOIN EMPLOYEES E  -- 무조건 모두 포함시켜야 하는 데이터는 부서
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME
 ORDER BY DEPARTMENT_ID;
-- (*)로 하게 되면 행단위로 데이터의 갯수를 세기 때문에 근무하는 사원이 없어도 부서번호가 카운트 되면서 있는 것처럼 나옴
-- 사원테이블의 부서번호로 카운트를 하면 해당 부서에 근무하는 사원만 카운트 하기 때문에 특정 테이블의 특정 칼럼을 지정해주어야 정답

-- 조인 없이
SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME, COUNT(E.DEPARTMENT_ID) AS 사원수  
  FROM DEPARTMENTS D, EMPLOYEES E  -- 무조건 모두 포함시켜야 하는 데이터는 부서
 WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID(+) -- 왼쪽 테이블에 조인이니까 오른쪽에 (+)
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME
 ORDER BY DEPARTMENT_ID;



/* SELF JOIN */
-- 셀프조인하는 테이블이 2개라고 생각하면 된다. A_TBL C INNER JOIN A_TBL D

-- 9. MANAGER보다 먼저 입사한 사원들의 EMPLOYEE_ID, LAST_NAME, HIRE_DATE과 MANAGER의 HIRE_DATE를 조회하시오.
--    사원의 HIRE_DATE가 MANAGER의 HIRE_DATE보다 작은 사원을 조회하시오. (MANAGER보다 먼저 입사한 사원)
-- 비교 대상
-- 1) 모든 사원 정보 : EMPLOYEE E  
-- 2) MANAGER의 정보 : EMPLOYEE M

-- 관계
-- PK                    FK
-- EMPLOYEE_ID           MANAGER_ID

-- 조인조건
-- E.MANAGER_ID = M.EMPLOYEE_ID > 사원들의 매니저 정보와 매니저의 사원번호가 같다
SELECT
       E.EMPLOYEE_ID -- 사원번호
     , E.LAST_NAME   -- 사원명
     , E.HIRE_DATE   -- 사원입사일
     , M.LAST_NAME   -- 상사이름
     , M.HIRE_DATE   -- 상사입사일
  FROM 
       EMPLOYEES E INNER JOIN EMPLOYEES M
    ON 
       E.MANAGER_ID = M.EMPLOYEE_ID
 WHERE 
       E.HIRE_DATE < M.HIRE_DATE;    -- 날짜는 크다작다 크기비교 가능! E, M 전부 TO_DATE 붙여줘도 됨.

-- 10. 같은 부서의 사원들 중에서 나보다 늦게 입사하였으나 연봉을 더 많이 받는 사원이 있는 사원들의
--     DEPARTMENT_ID, LAST_NAME, SALARY, HIRE_DATE와 높은 연봉을 받는 사원의 LAST_NAME, SALARY, HIRE_DATE를 조회하시오.
-- 비교 대상
-- 1) 나 : EMPLOYEES E1
-- 2) 남 : EMPLOYEES E2

-- 조인 조건
-- E1.DEPARTMENT_ID = E2.DEPARTMENT_ID
SELECT 
       E1.DEPARTMENT_ID   -- 내 부서번호
     , E1.LAST_NAME       -- 내 이름
     , E1.SALARY          -- 내 급여
     , E1.HIRE_DATE       -- 내 입사일
     , E2.DEPARTMENT_ID   -- 남 부서번호
     , E2.LAST_NAME       -- 남 이름
     , E2.SALARY          -- 남 급여
     , E2.HIRE_DATE       -- 남 입사일
  FROM EMPLOYEES E1 INNER JOIN EMPLOYEES E2
    ON E1.DEPARTMENT_ID = E2.DEPARTMENT_ID
 WHERE TO_DATE(E1.HIRE_DATE) < TO_DATE(E2.HIRE_DATE)
   AND E1.SALARY < E2.SALARY;


