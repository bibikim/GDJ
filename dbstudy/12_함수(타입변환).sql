/*
        DUAL 테이블
        
        1. DUMMY 칼럼에 'X' 값을 하나 가지고 있는 테이블
        2. 아무 의미 없는 테이블
        3. 오라클에서는 FROM절이 필수이기 때문에 테이블이 필요없는 단순 조회문에서 DUAL 테이블을 사용함
*/

SELECT 1 + 2                    -- 꼭 테이블에 있는걸 써서 조회해야되는 건 아니다.
  FROM DUAL;                    -- 테이블하고 상관없는걸 넣어도 된다
                                -- SELECT절을 쓰려면 DUAL이라는 아무 의미없는 테이블이 만들어져 있어서 그걸 이용하면 된다.                      
DESC DUAL;

/*
    타입 변환 함수
    1. TO_NUMBER('문자열')       : 문자열 형식의 숫자를 숫자 형식으로 변환. 자바의 INTEGER.PARSEINT 같은 것
    2. TO CHAR(데이터, ['형식'])   : 지정된 데이터(주로 숫자나 날짜)를 형식에 맞는 문자열로 변환. [ ] 생략 가능
    3. TO_DATE('문자열', '형식') : 지정된 문자열을 날짜 형식으로 변환
*/

-- 1. 숫자로 변환(TO_NUMBER)
SELECT '100', TO_NUMBER('100')
  FROM DUAL;                    -- 왼쪽은 문자열로, 오른쪽은 숫자로 출력됨.
SELECT '1.5', TO_NUMBER('1.5')
  FROM DUAL;
  
-- 숫자와 '문자' 연산은 오라클에 의해서 숫자와 숫자 연산으로 수정된 뒤 처리됨.
-- '문자' -> TO_NUMBER('문자') 방식으로 자동으로 처리함
SELECT 1 + '1'   -- SELECT 1 + TO_NUMBER('1') >> 오라클이 TO_NUMBER로 알아서 개입
  FROM DUAL;
  
-- '문자'와 '문자' 연산도 모두 숫자로 바꿔서 처리
SELECT '1' + '1'  -- SELECT TO_NUMBER('1') + TO_NUMBER('1')
  FROM DUAL;
  
SELECT *
  FROM EMPLOYEE
 WHERE EMP_NO = '1001';  -- EMP_NO(숫자) =(연산) '1001'(문자) >> 숫자와 문자의 연산이라 오라클이 TO_NUMBER로 자동 개입
 
 /*
 EMP_NO는 문자열. 가장 성능이 낮은 것은? ①

 ①TO_NUMBER(EMP_NO) = 1
 ②EMP_NO = 1
 ③EMP_NO = '1'               문자열 = 문자열    
 ④EMP_NO = TO_CHAR(1)
  >>> 연산자(=)의 왼쪽은 가급적 함수가 들어가지 않는 것이 성능에 좋다.
      타입이 맞는 ③번이 제일 성능이 좋다.
 */


-- 2. 문자로 변환(TO_CHAR)
--  1) 숫자 -> 문자로 변환
SELECT
       TO_CHAR(1234)                -- '1234'
     , TO_CHAR(1234,  '999999')     -- '  1234' 숫자를 6자리 폭에 맞춰서 쓰세요. 9는 숫자가 부족하면 공백으로,
     , TO_CHAR(1234,  '000000')     -- '123400' 0은 숫자가 부족하면 0으로 채운다
     , TO_CHAR(1234,  '9,999')      -- '1,234'
     , TO_CHAR(12345, '9,999')      --  ##### (4자리로 지정하였으나 값은 5자리이기 때문에 표시할 수 없음)
     , TO_CHAR(12345, '99,999')     -- '12,345'ㅇㅇ
     , TO_CHAR(1.4,   '9')          -- '1' 형식은 정수 1자리 표기(소수 이하는 반올림 처리를 함)
     , TO_CHAR(1.5,   '9')          -- '2' 형식은 정수 1자리 표기(소수 이하 반올림)
     , TO_CHAR(0.123, '0.00')       -- '0.12', 소수 이하 2자리 표기(반올림)
     , TO_CHAR(0.129, '0.00')       -- '0.13', 소수 이하 2자리 표기(반올림)
  FROM
       DUAL;

-- 2) 날짜 -> 문자로 변환
-- 현재 날짜와 시간
-- DATE 타입의 SYSDATE
-- TIMESTAMP 타입의 SYSTIMESTAMP
SELECT SYSDATE FROM DUAL;         -- YY/MM/DD 형식으로 표시하지만 ^시간 데이터^도 가지고 있음
SELECT SYSTIMESTAMP FROM DUAL;    -- 날짜와 시간까지. (+09:00 UTC 기준으로 +9분이다)
  
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')
     , TO_CHAR(SYSDATE, 'HH:MI:SS') FROM DUAL;  
-- 자바에서 현재날짜를 구해서 사용하기보다 DB에서 구해 사용하는 것이 일반적


-- 3) 날짜로 변환(TO_DATE)

-- '05/06/07' 날짜는 언제인가? 알려주기 전에는 모름
-- 지정된 형식으로 해석해야하는 함수
-- 예시1) 'YY/MM/DD' : 05년 06월 07일
-- 예시2) 'MM/DD/YY' : 07년 05월 06일
-- 어떤 날짜를 어떻게 해석할 것인지 알려주는 함수

SELECT 
       TO_DATE('05/06/07', 'YY/MM/DD')
     , TO_DATE('05/06/07', 'MM/DD/YY')      -- 실행 결과는 년/월/일 순
  FROM DUAL;
  
-- 현재 날짜를 YYYY-MM-DD 형식으로 조회 (-> TO_DATE 사용 불가)
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL;

-- 사원 테이블에서 15/01/01 ~ 19/12/31 사이에 입사한 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE TO_DATE(HIRE_DATE, 'YY/MM/DD') BETWEEN TO_DATE('15/01/01' ,'YY/MM/DD') AND TO_DATE('31/12/19', 'DD/MM/YY');
       -- 실제로 날짜를 문자(VARCHAR2)로 저장해놓는 경우가 훨씬 많기 때문에 TO_DATE 함수를 쓸 일이 많다
 
CREATE TABLE SAMPLE(
    DT1 DATE,
    DT2 TIMESTAMP,
    DT3 VARCHAR2(10 BYTE)   -- YYYY-MM-DD 문자열로 넣을 땐 하이픈 포함해서 넣깅
);

-- DT1과 DT2, DT3 칼럼에 현재 날짜 넣기
INSERT INTO SAMPLE(DT1, DT2, DT3) 
  VALUES(SYSDATE, SYSTIMESTAMP, TO_CHAR(SYSDATE, 'YYYY-MM-DD'));          
                                -- DT3은 VARCHAR2타입이기 때문에 문자로 저장해주는 TO_CHAR 함수를 사용해서 날짜를 문자로 바꿔주는 것이 필요.
SELECT DT1, DT2, DT3 FROM SAMPLE;

-- 날짜 비교 연산은 TO_DATE 함수가 필요
-- DT1이 오늘(22/08/26)이냐? 비교 연산
-- 안 됨
SELECT DT1, DT2, DT3
  FROM SAMPLE
 WHERE DT1 = TO_DATE('22/08/26', 'YY/MM/DD');

-- 안 됨
SELECT DT1, DT2, DT3
  FROM SAMPLE
 WHERE DT1 = '22/08/26';  -- DT1은 SYSDATE가 삽입되어 있기 때문에 시간 데이터까지 포함 되어있음. 날짜로만 비교는 그래서 안 되는 것.

-- 됨
SELECT DT1, DT2, DT3
  FROM SAMPLE
 WHERE TO_DATE(DT1, 'YY/MM/DD') = TO_DATE('22/08/26', 'YY/MM/DD');  -- 날짜 비교는 TO_DATE를 양쪽에 사용하고 연산
 