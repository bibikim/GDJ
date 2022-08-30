-- 1. 순위
--    1) RANK() OVER(ORDER BY 순위구할칼럼 ASC)  : 오름차순 순위(낮->높), 낮은 값이 1등, ASC는 생략 가능
--    2) RANK() OVER(ORDER BY 순위구할칼럼 DESC) : 내림차순 순위(높->낮), 높은 값이 1등
--    3) 같은 값이면 같은 등수(동점)로 처리 (1등 2등 2등 4등)

-- 1) EMPLOYEES 테이블의 사원 정보를 연봉이 높은 순으로 조회하기
--    연봉 순위를 함께 조회하기   -> RANK()만 넣으면 정렬까지 된다.
SELECT 
       RANK() OVER(ORDER BY SALARY DESC, LAST_NAME ASC) AS 연봉순위   -- 같은 순위(동점) 내에서도 정렬을 하고자 하면 정렬할 칼럼을 정하고
     , EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY                     -- , 찍고 칼럼 ASC/DESC 넣어주면 된다.
  FROM EMPLOYEES;

-- 2) EMPLOYEES 
SELECT
       RANK() OVER(ORDER BY HIRE_DATE ASC) AS 입사순위
     , EMPLOYEE_ID, FIRST_NAME, LAST_NAME, HIRE_DATE
  FROM EMPLOYEES;
  
-- 2. 그룹화
--    OVER(PARTITION BY 그룹화칼럼)
--    그룹화작업을 수행하므로 집계함수(그룹함수-SUM,AVG,MAX,MIN,COUNT)와 함께 사용할 수 있다.
SELECT 
       DISTINCT DEPARTMENT_ID  -- 중복ID는 한번만
     , SUM(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별연봉합계  -- 동일 부서는 모아서
     , FLOOR(AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID)) AS 부서별연봉평균 -- 소수점 버리기(FLOOR로 그룹화 전체를 묶어준다)
     , MAX(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최대연봉
     , MIN(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최저연봉
     , COUNT(8)    OVER(PARTITION BY DEPARTMENT_ID) AS 부서별사원수
  FROM 
       EMPLOYEES
 WHERE 
       DEPARTMENT_ID IS NOT NULL;   -- NULL값 빼기 (빠진 NULL값은 인덱스로)

-- RANK() 함수와 PARTITION BY를 함께 사용하면 그룹 내 순위 구하기 가능
-- RANK() OVER(PARTITION BY 그룹화칼럼 ORDER_BY 순위칼럼 차순)
SELECT
       RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS 부서내연봉순위
     , EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID
  FROM 
       EMPLOYEES
 ORDER BY
       DEPARTMENT_ID ASC;
       

-- 3. 분기 처리(IF)
--    DECODE(표현식
--         , 값1, 결과1
--         , 값2, 결과2
--         , 값3, 결과3
--         , ...)
--    표현식의 결과가 값1이면 결과1 반환, 값2이면 결과2 반환, ...
--    표현식의 결과와 값의 비교는 동등 비교(=)만 가능하다. 크다작다X
--    테이블을 하나의 테이블만 볼 수 있게 할 수 있어 성능의 이점이 있음. (JOIN과 다른점 - 테이블을 2개나 보기때문에 성능 겁나 떨어짐)

-- JOIN 없이 EMPLOYEES 테이블만 이용하여 EMPLOYEE_ID, DEPARTMENT_NAME 조회하기
SELECT
       EMPLOYEE_ID 
     , DECODE(DEPARTMENT_ID
       , 10, 'Administration'
       , 20, 'Marketing'
       , 30, 'Purchasing'
       , 40, 'Human Resources'
       , 50, 'Shipping'
       , 60, 'IT') AS 부서명
  FROM EMPLOYEES;               -- NULL은 나머지 부서의 값들을 넣어주지 않아서 뜨는 것

-- PHONE_NUMBER 
SELECT
       EMPLOYEE_ID
     , PHONE_NUMBER
     , DECODE(SUBSTR(PHONE_NUMBER, 1, 3)  -- SUBSTR 1번째글자부터 3글자 추출. 
       , '011', 'MOBILE'  -- SUBSTR의 결과는 문자열!!! 따라서 '' 붙여주깅
       , '515', 'EAST'
       , '590', 'WEST'
       , '603', 'SOUTH'
       , '650', 'NORTH') AS REGION   -- 011%는 불가. %는 WHERE절에서 LIKE랑 쓰는 거임
  FROM EMPLOYEES
 ORDER BY REGION ASC;
       
       
-- 4. 분기 표현식(함수는 X)
-- CASE
--      WHEN 조건식1 THEN 결과값1      조건식을 만족할 때 결과값이 나옴. WHEN이 IF라고 생각하면 됨
--      WHEN 조건식2 THEN 결과값2      
--      ...
--      ELSE 결과값N
-- END

-- SALARY < 10000  : C
-- SALARY < 20000  : B
-- SALARY >= 20000 : A  (나머지결과값)
SELECT
       EMPLOYEE_ID
     , SALARY
     , CASE
            WHEN SALARY < 10000 THEN 'C'
            WHEN SALARY < 20000 THEN 'B'
            ELSE 'A'
       END AS 구분
  FROM EMPLOYEES
 ORDER BY 구분 ASC;

-- EMPLOYEE_ID, HIRE_DATE(YYYY-MM-DD), 근무개월수를 계산해서(MONTHS), 퇴직금정산대상유무 조회
-- 퇴직금정산대상 : 근무개월수가 240개월 이상이면 '정산대상', 아니면 빈 문자열''(null)

SELECT
        EMPLOYEE_ID AS 사원번호
      , TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') AS 입사일
      , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) AS 근무개월수
      , CASE
             WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= 240 THEN '정산대상'
             ELSE ''
        END 퇴직금정산대상유무
  FROM EMPLOYEES
 ORDER BY 근무개월수 DESC;
-- ★ RECHECK
-- 날짜를 YYYY-MM-DD 형식으로 바꾸려면  ★ TO_CHAR(날짜칼럼, 'YYYY-MM-DD')
-- 개월수를 구하려면                    ★ MONTHS_BETWEEN(SYSDATE, 개월수구하고자하는칼럼) // SYSDATE는 오늘!
SELECT MONTHS_BETWEEN('2010-07-31', '2003-12-26')
  FROM DUAL;


