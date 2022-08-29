-- 함수 확인용 기초데이터
DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NAME VARCHAR2(20 BYTE),
    KOR NUMBER(3),
    ENG NUMBER(3),
    MATH NUMBER(3)
);

INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES(NULL, 100, 100, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('영우', NULL, 100, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('채원', 100, NULL, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('제니', 100, 100, NULL);
COMMIT; 

-- NULL값의 연산에서 사용되면 결과가 NULL이다.
SELECT 1 + NULL FROM DUAL;  -- 사실상 테이블에서 가지고 오는 데이터는 없고 순수하게 1+NULL 값을 조회하고자 하는 쿼리문!


-- NULL 처리 함수

-- 1. NVL 함수
--    NVL(칼럼, 칼럼값이 NULL일 때 대신 사용할 값)

-- NAME이 없으면 '아무개', KOR/ENG/MATH가 없으면 0으로 조회(0점처리)
SELECT 
       NVL(NAME, '아무개') AS STU_NAME -- ② 
     , NVL(KOR, 0)
     , NVL(ENG, 0)
     , NVL(MATH, 0)
 FROM
       SAMPLE                          -- ①
 --WHERE                               -- '아무개' 제외하고 조회/ WHERE절이 들어가면 실행 순서상 FROM절 다음으로 실행 하는데
 --      STU_NAME != '아무개'          -- WHERE절에 STU_NAME은 이름을 주기 전(SELECT절 전)이기 때문에 오류가 뜬다.
 ORDER BY
       STU_NMAE ASC;                   -- ③
       -- NVL(NAME, '아무개') ASC; >> 아무개를 포함해서 오름차순 정리하고 싶을 땐 이렇게.
       -- 그러나 SELECT절에서 NAME 칼럼에 이름(별명)을 주고 ORDER BY절에서 이름으로 적으면 편해짐. AS를 쓰는 이유
       -- NAME ASC; -- 어쨌든 NULL값이 들어가 있기 때문에 NULL인 아무개가 가장 밑으로 조회가 됨

-- 이름과 총점을 조회하기
-- 이름이 없으면 '아무개', 점수가 없으면 0점 처리

SELECT
       NVL(NAME, '아무개') AS 이름
     , NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH, 0) AS 총점
     -- , KOR + ENG + MATH   안되니는 이유 : NULL값이 포함되어 있어 무조건 NULL이 나오기 때문에
FROM SAMPLE;

-- 2. NVL2 함수(IF재질..)
--    NVL2(칼럼, NULL이 아닐 때 사용할 값, NULL일 때 사용할 값)
SELECT 
       NVL2(NAME, NAME || '님', '아무개')      -- NULL이 아니면 NAME 그대로 써라, NULL이면 아무개로 써라.
                    -- 문자열 연결 연산자 || (JAVA에서 + 연산기호 혹은 CONCAT(NAME, '님')과 같음)
     , NVL2(KOR, '응시', '결시')
     , NVL2(ENG, '응시', '결시')
     , NVL2(MATH, '응시', '결시')              -- 타입이 섞여있으면 조회 안 됨.
  FROM 
       SAMPLE;


-- 집계함수 (그룹함수)
-- 1. 통계(합계, 평균, 최대, 최소, 개수 등)를 낼 때 사용 - 칼럼 단위 대상 집계
-- 2. NULL값을 연산에서 스스로 제외
-- 3. 종류
--    1) SUM(칼럼)   : 칼럼 합계
--    2) AVG(칼럼)   : 칼럼 데이터의 평균
--    3) MAX(칼럼)   : 칼럼 최대값
--    4) MIN(칼럼)   : 칼럼 최소값
--    5) COUNT(칼럼) : 칼럼에 입력된 데이터 갯수

-- 각 칼럼(KOR, ENG, MATH)의 합계(SUM은 칼럼 대상 집계)
SELECT 
       SUM(KOR)    -- 칼럼단위로 더하기
     , SUM(ENG)    -- 칼럼단위로 더하기
     , SUM(MATH)   -- 칼럼단위로 더하기
--   , SUM(KOR, ENG, MATH)  인수(arguments)가 3개이므로 불가능함(집계함수들은 인수가 1개여야 한다. 따라서 ,로 나열하는건 불가)
     , SUM(KOR + ENG + MATH)  -- KOR + ENG + MATH와 같은 연산(SUM 함수를 잘못 사용한 예시)
                              -- 왜냐하면 KOR + ENG + MATH는 ROW단위. ROW단위 연산하려고 SUM을 쓰진 않는다.
     , SUM(KOR) + SUM(ENG) + SUM(MATH)  -- 국어합 + 영어합 + 수학합
 FROM
       SAMPLE; -- 학생4명, null값 빼고 다 더해서 300점

-- 각 칼럼(KOR, ENG, MATH)의 평균
SELECT
       AVG(KOR)   -- NULL을 제외한 KOR의 평균
     , AVG(ENG)   -- NULL을 제외한 ENG의 평균
     , AVG(MATH)  -- NULL을 제외한 MATH의 평균
  FROM
       SAMPLE;

-- NULL값은 결시를 의미하므로 0점 처리함
SELECT
       AVG(NVL(KOR, 0))
     , AVG(NVL(ENG, 0))
     , AVG(NVL(MATH, 0))
  FROM
       SAMPLE;

-- 각 칼럼(KOR, ENG, MATH)의 최대값
SELECT
       MAX(KOR)
     , MAX(ENG)
     , MAX(MATH)
  FROM
       SAMPLE;

-- 각 칼럼(KOR, ENG, MATH)의 최소값
-- NULL값은 결시를 의미하므로 0점 처리
SELECT
       MIN(NVL(KOR, 0))
     , MIN(NVL(ENG, 0))
     , MIN(NVL(MATH, 0))
  FROM
       SAMPLE;

-- 국어 시험을 응시한 학생이 몇 명인가?
SELECT
        COUNT(KOR)
  FROM SAMPLE;
  
-- 전체 학생은 몇 명인가? (전체 ROW의 개수)
-- 특정 칼럼을 지정하지 않고 전체 칼럼(*)을 이용해서 전체 Row 개수를 구함
SELECT
        COUNT(*)        -- 칼럼을 전체 다 보고 데이터가 하나라도 있으면 COUNT
  FROM SAMPLE;
  
  
-- 성명     국어    영어    수학   평균
-- 아무개   100     100     100    100
-- 영우     0       100     100    66.67
-- 채원     100     0       100    66.67
-- 제니     100     100     0      66.67
-- SUM과 AVG는 사용X ( 표에서 평균은 ROW단위의 결과를 원하는 것이므로 사용 불가)

SELECT
       NVL(NAME, '아무개') AS 성명
     , NVL(KOR, 0) AS 국어
     , NVL(ENG, 0) AS 영어
     , NVL(MATH, 0) AS 수학
     , NVL(KOR, 0) +  NVL(ENG, 0) + NVL(MATH, 0) AS 합계
     , (NVL(KOR, 0) +  NVL(ENG, 0) + NVL(MATH, 0)) / 3 AS 평균
  FROM SAMPLE;
  


--INSERT INTO SAMPLE
--    VALUES(NVL(NAME, '아무개'), 100, 100, 100, SUM(KOR, ENG, MATH)/3);


