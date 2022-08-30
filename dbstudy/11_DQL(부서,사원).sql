/*
    DQL

    1. Data Query Language
    2. 데이터 질의어
    3. 테이블의 데이터를 조회/검색
    4. 데이터베이스에 변화가 없으므로 COMMIT 없음
      (트랜잭션에 속하지 않기 때문에 대상이 아님)
    5. 형식
        SELECT 칼럼1, 칼럼2, ...   
          FROM 테이블
        [WHERE 조건식]             -- []은 생략가능
        [GROUP BY 그룹화]          -- 특정기준을 기준으로 그룹화 하고자 할 때
        [HAVING 그룹화_조건식]
        [ORDER BY 정렬기준]        -- 오름차순, 내림차순. 2가지 이상의 기준을 줄 수도 있다
    6. 실행 순서(①~⑥) ★★
       ⑤  SELECT 칼럼
       ①    FROM 테이블           -- ①테이블이 없다? 오류
       ②   WHERE 조건식
       ③   GROUP BY 그룹화
       ④  HAVING 그룹화_조건식
       ⑥   ORDER BY 정렬기준
        └> 6개의 모든 절이 있다는 가정 하의 실행 순서.
    7. SELECT의 결과는 테이블로 나온다.
      
      ①FROM-특정 테이블로부터 ②WHERE-특정 칼럼의 조건에 해당하는 Row들을 찾고 ③GROUP BY-Row들을 그룹으로 묶고
      ④HAVING-그룹을 또 조건에 따라 묶은 다음에 ⑤SELECT-칼럼에서 고르고 ⑥ORDER BY- 마지막으로 모든 조건식을 만족하는 Row들을 정렬기준에 따라 출력.

*/


-- 1. 사원 테이블에서 사원명 조회하기
SELECT NAME
  FROM EMPLOYEE;
  
-- 1) 테이블에 오너(OWNER) 명시
SELECT NAME
  FROM SCOTT.EMPLOYEE;

-- 2) 칼럼에 테이블 명시
SELECT EPLOYEE.NAME
  FROM EMPLOYEE;
  
-- 3)★★ 테이블에 별명 지정 
SELECT EMP.NAME        -- 칼럼에 테이블 별명 명시 가능
  FROM EMPLOYEE EMP;   -- 별명 EMP 지정 _↑
  
-- 4)★ 칼럼에 별명 지정(ALIAS) 지정
SELECT NAME AS 사원명  -- 별명 사원명 지정
  FROM EMPLOYEE;
  

-- 2. 사원 테이블의 모든 칼럼 조회하기
--    모든 칼럼 : *
--    중요 : 실무에서 * 사용 금지(성능 문제가 있어서)
SELECT *
  FROM EMPLOYEE;

-- 모든 칼럼이 필요하면 모두 명시
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE;

-- 3. 부서 테이블에서 지역명 조회하기
--    단, 동일한 지역은 한 번만 조회하기
--    중복 제거 : DISTINCT
--    일반적으로 칼럼 하나만 조회하는 경우만 있다. 2개 이상은 데이터 설계부터가 잘못 된 것..
SELECT DISTINCT LOCATION
  FROM DEPARTMENT;

SELECT DISTINCT DEPT_NAME, LOCATION
  FROM DEPARTMENT;                       -- DEPT_NAME, LOCATION이 둘 다 같으면 한번만 조회


/* WHERE절 활용하기 */

-- 4. 사원 테이블에서 직급이 '과장'인 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = '과장';

-- 5. 사원 테이블에서 급여가 200000~5000000인 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 2000000
   AND SALARY <= 5000000;   -- AND 연산자 이용
   
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY BETWEEN 2000000 AND 5000000;
   
-- 6. 사원 테이블에서 소속부서가 1,2인 사원 조회하기
--    1) OR 연산자
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART = 1
    OR DEPART = 2;          -- OR 연산자 이용. 1 또는 2
    
--    2) IN 연산자 : 여러 개의 조건값을 하나의 조건으로 처리
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART IN(1, 2);     -- IN 연산자 이용. (() 안에 들어있으면 된다.)

-- 7. 사원 테이블에서 성별이 없는 사원 조회하기
--    ★NULL 유무
--    1) NULL이다   : IS NULL
--    2) NULL아니다 : IS NOT NULL
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE GENDER IS NULL;

-- 8. 사원 테이블에서 김씨 조회
--    1) 만능문자(WILD CARD)                  -- 검색 기능 때 활용 가능
--      (1) % : 모든 문자, 글자 수 제한 없음
--      (2) _ : 모든 문자, 한 글자로 제한
--    2) 예시
--      (1) 김으로 시작하는 이름 찾기 : 김%
--      (2) 김으로 끝나는 이름 찾기   : %김
--      (3) 김을 포함하는 이름 찾기   : %김%
--    3) 만능문자 연산자
--       LIKE, NOT LIKE
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE NAME LIKE '김%';
 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE NAME NOT LIKE '김%';      -- 김씨 빼고 나머지 조회
 
 SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE NAME LIKE '%석';          -- 석으로 끝나는 이름 조회
 
-- 9. 사원 테이블에서 사원번호가 1로 시작하는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE EMP_NO LIKE '1%';         -- EMP_NO 타입은 NUMBER인데 '1%'는 텍스트. 넘버와 텍스트를 오라클이 스스로 캐스팅을 해서 실행한다.
                                 -- 그러나 좋은 것은 아니다.
 
/* ORDER BY절 활용하기 */        -- 6개의 절 중에 가장 나중에 실행되는 절
-- ASC  : 오름차순 정렬, 생략 가능
-- DESC : 내림차순 정렬

-- 10. 사원 테이블에서 사원명을 가나다순으로 조회하기(오름차순)
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY NAME ASC;              -- ASC 생략 가능

-- 11. 사원 테이블에서 급여가 높은 순으로 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC;
 
-- 12. 사원 테이블에서 성별을 가나다순으로 조회하기
--     오름차순 정렬에서 NULL값은 마지막에 배치
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY GENDER;                -- NULL값은 맨 마지막에 조회
                                 -- DESC으로 조회하면 맨 위에 조회
-- 13. 사원 테이블에서 먼저 고용된 순으로 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY HIRE_DATE;             -- 먼저 입사한 사람부터 출력. TIMESTAMP 값 생각해보면 됨
                                 --  날짜가 날짜로서 인식 됨. 날짜가 ''로 묶여있다고 문자(텍스트)는 아니다.
-- 14. 사원 테이블에서 소속부서의 오름차순 정렬로 조회하되,
--     같은 소속부서 내에서는 먼저 고용된 순으로 조회하기
--     1차 정렬기준 : 소속부서
--     2차 정렬기준 : 고용일자
--     정렬은 N개 사용도 가능하다
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY DEPART ASC, HIRE_DATE ASC;   -- 키워드를 적을 때, 각 정렬기준마다 적어주면 됨. 먼저 들어온 순은 오름차순

-- 퀴이이이즈 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY DEPART, HIRE_DATE DESC;   -- DEPART는 오름, HIRE_DATE는 내림.  ASC는 생략이 가능하니까. 둘다 내림 아님!


/* WHERE절과 ORDER BY절 함께 사용 */
-- 15. 사원 테이블에서 급여가 5000000 이상인 사원들을 고용된 순으로 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 5000000
 ORDER BY HIRE_DATE;


/* SECECLT문 순처리 순서 */  -- ★완벽히 이해하기★
-- 정상 실행
/* ③ */ SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE AS HD, SALARY  -- HIRE_DATE의 별명 HD
/* ① */   FROM EMPLOYEE
/* ② */  WHERE SALARY >= 5000000 
/* ④ */  ORDER BY HD;                                                           -- 별명 주고 별명 사용
-- EMPLOYEE 테이블에서 
-- SALARY가 500만 이상인 사람을 
-- SELECT절에서 사원 조회 및 조건절에 맞는 사원을 선택하고 그와 동시에 HIRE_DATE에 HD라고 별명을 지정,
-- 그러고 난 후에 HD 기준으로 오름차순으로 보여달라

-- 실행 오류
/* ③ */ SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY AS S   -- SALARY의 별명 S
/* ① */   FROM EMPLOYEE
/* ② */  WHERE S >= 5000000
/* ④ */  ORDER BY HIRE_DATE;
-- EMPLOYEE 테이블에서
-- S가 500만 이상인 사원을 조회하려는데 S가 없음. SELECT절 실행 전(별명 지정 전)이니까 당연함.
-- 따라서 S라는 칼럼을 테이블에서 찾을 수 없음에 실행 오류. 실행순서를 보고 이해하면 된다.
