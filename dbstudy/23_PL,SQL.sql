/*
    PL/SQL
    1. 오라클의 프로그래밍 처리가 가능한 SQL
    2. 프로그래밍 형식
       [DECLARE]
           [변수 선언]
       BEGIN
           수행할 PL/SQL
       END; 
    3. PL/SQL의 데이터(변수, 상수)는 서버메시지로 출력
    4. 서버메시지 출력을 위해서 최초 1회 설정이 필요(디폴트가 SET SERVEROUPUT OFF라서) 
        SET SERVEROUTPUT ON;
*/

-- 기초 데이터 준비
-- HR 계정의 EMPLOYEES 테이블을 SCOTT 계정으로 복사해 오기
CREATE TABLE EMPLOYEES
    AS (SELECT *
          FROM HR.EMPLOYEES);

-- 기본키
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT PK_EMPLOYEES PRIMARY KEY(EMPLOYEE_ID);
    

-- 서버메시지 출력 가능 상태로 변경
SET SERVEROUTPUT ON;

-- 서버메시지 출력
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO');  -- HELLO 출력 후 줄 바꿈(오라클에서의 SYSOUT이라고 보면 됨)
END;

-- 1. 스칼라 변수 선언
-- 스칼라 변수 : 직접 타입을 명시하는 변수
-- 대입 연산자 : 콜론등호(:=)
-- 변수 선언은 DECLARE에서 처리
DECLARE 
    -- 스칼라 변수 선언
    NAME VARCHAR2(20 BYTE);
    AGE NUMBER(3);
BEGIN
    NAME := '김한비';
    AGE := 31;
    DBMS_OUTPUT.PUT_LINE('내 이름은 ' || NAME || '입니다.');
    DBMS_OUTPUT.PUT_LINE('내 나이는 ' || AGE || '살입니다.');  -- || 오라클 문자열 연결 연산자
END;


-- 2. 참조 변수 선언
-- 참조 변수 : 특정 칼럼의 타입을 그대로 사용하는 변수
-- 선언 방법
-- 테이블명.칼럼명%TYPE
-- 특정칼럼하고 같은 타입으로 할게요 하고 다른 테이블에 특정 칼럼을 명시
DECLARE
    -- 참조 변수 선언
    NAME EMPLOYEES.FIRST_NAME%TYPE; 
BEGIN
    NAME := 'Hanbi';
    DBMS_OUTPUT.PUT_LINE('나의 이름은 ' || NAME || '입니다.');
END;

-- 참조 변수 활용
-- 테이블의 데이터를 읽어 참조 변수에 저장
-- SELECT 칼럼 INTO 변수 FROM 테이블 WHERE 조건식    > 특정칼럼을 읽어서 변수에 저장하는 법


-- 문제. EMPLOYEE_ID가 100인 회원의 FIRST_NAME, LAST_NMAE, SALARY 정보를 읽어서 참조변수에 저장
DECLARE
    F_NAME EMPLOYEES.FIRST_NAME%TYPE;
    L_NAME EMPLOYEES.LAST_NAME%TYPE;
    V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT
           FIRST_NAME, LAST_NAME, SALARY 
      INTO
           F_NAME, L_NAME, V_SALARY
      FROM 
           EMPLOYEES
     WHERE 
           EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(F_NAME || L_NAME || V_SALARY);
END;


-- 3. 레코드 변수 선언
-- 레코드 : 필드(칼럼)의 모임
-- DB에서 레코드란? 행(ROW) 
-- 레코드 변수 : 여러 칼럼을 (필요한 것만 골라서) 동시에 저장하는 변수 (JAVA의 Class타입같은 거라고 보면 됨)
-- 레코드 변수 정의와 선언 과정으로 진행
-- 선언 방법
-- TYPE 타입명 IS RECORD(필드목록);
-- 레코드변수명 타입명;
DECLARE
    -- 레코드 변수 타입 정의(타입 만들기)
    TYPE MY_TYPE IS RECORD(
        V_FNAME EMPLOYEES.FIRST_NAME%TYPE, 
        V_LNAME EMPLOYEES.LAST_NAME%TYPE,
        V_SALARY EMPLOYEES.SALARY%TYPE
    );
    -- 레코드 변수 선언(변수 만들기)
    V_ROW MY_TYPE;   -- V.ROW(레코드변수명) -> 변수 3개(V_F,LNAME,SALARY)가 모이는 것
BEGIN
    SELECT
           FIRST_NAME, LAST_NAME, SALARY
      INTO 
           V_ROW
      FROM
           EMPLOYEES
     WHERE
           EMPLOYEE_ID = 100;
     DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME || V_ROW.V_LNAME || V_ROW.V_SALARY);  -- 행 단위(원하는 데이터만 골라서)로 저장
END;


-- 4. 행 변수 선언하기
-- 행(ROW) 전체를 저장할 수 있는 타입 ROWTYPE
-- SELECT문에 칼럼의 값을 전부 가져와서 조회해야 한다.
-- 선언 방법
-- 테이블%ROWTYPE
DECLARE 
    -- 행 변수 선언
    V_ROW EMPLOYEES%ROWTYPE;  -- 변수 이름 : V_ROW
BEGIN
    SELECT *                  -- SELECT문에 칼럼의 값을 전부 가져와서 조회해야 한다.(실무에서는 *말고 칼럼명 다 쓰기)
      INTO V_ROW
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(V_ROW.FIRST_NAME || V_ROW.LAST_NAME || V_ROW.SALARY);
END;


-- 5. IF
/*
    IF 조건식 THEN
        실행문
    ELSIF 조건식 THEN
        실행문
    ELSE
        실행문
    END IF;
*/
-- 1) 성인/미성년자
DECLARE
    AGE NUMBER(3);
    RESULT VARCHAR2(20 BYTE);
BEGIN
    AGE := 10;
    IF AGE >= 20 THEN
        RESULT := '성인';
    ELSE
        RESULT := '미성년자';
    END IF;
    DBMS_OUTPUT.PUT_LINE(AGE || '살은 ' || RESULT || '입니다.');
END;

-- 2) 학점(A,B,C,D,F)
DECLARE
    SCORE NUMBER;
    GRADE VARCHAR2(2 BYTE);
BEGIN
    SCORE := 79;
    IF SCORE >= 90 THEN
        GRADE := 'A';
    ELSIF SCORE >= 80 THEN
        GRADE := 'B';
    ELSIF SCORE >= 70 THEN
        GRADE := 'C';
    ELSIF SCORE >= 60 THEN
        GRADE := 'D';
    ELSE 
        GRADE := 'F';
    END IF;
    DBMS_OUTPUT.PUT_LINE(SCORE || '점은 ' || GRADE || '학점입니다.');
END;

-- 3) EMPLOYEE_ID가 150인 사원의 연봉을 참조하여
-- 15000 이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 '저연봉'
DECLARE 
        V_SALARY EMPLOYEES.SALARY%TYPE;  -- V_SALARY의 타입은 EMPLOYEES의 SALARY 타입을 가져옴
        YEARSLR VARCHAR2(20 BYTE);
BEGIN
    SELECT SALARY
      INTO V_SALARY                      -- SELECT에서 가져온 걸 변수 V_SALARY에 저장(INTO)
      FROM EMPLOYEES  
     WHERE EMPLOYEE_ID = 150;            -- EMPLOYEES에서 150번 사원의 SALARY를 참조하기 위해 가져오기
    IF V_SALARY >= 15000 THEN
        YEARSLR := '고연봉';
    ELSIF V_SALARY >= 10000 THEN
        YEARSLR := '중연봉';
    ELSE
        YEARSLR := '저연봉';
    END IF;
    DBMS_OUTPUT.PUT_LINE(V_SALARY || '은 ' || YEARSLR || '입니다');
END;


-- 6. CASE문
/*
    CASE
        WHEN 조건식 THEN
            실행문
        WHEN 조건식 THEN
            실행문
        ELSE
            실행문
    END CASE;
*/

-- 주민번호를 이용해 성별 조회하기
DECLARE
    SNO VARCHAR2(14 BYTE);
    GENDER_NUM VARCHAR2(1 BYTE);
    GENDER VARCHAR2(1 BYTE);
BEGIN
    SNO := '821219-1268521';
    SELECT SUBSTR(SNO, 8, 1)
      INTO GENDER_NUM
      FROM DUAL;
    CASE
        WHEN GENDER_NUM = '1' THEN                         -- SELECT SUBSTR(SNO, 8, 1) = SUBSTR(SNO, -7, 1) FROM DUAL; >> 뒤에서부터 7글자 
            GENDER := 'M';
        WHEN GENDER_NUM = '2' THEN
            GENDER := 'F';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('성별은 ' || GENDER || '입니다.');
END;

-- 7. WHILE문
/*
    WHILE 조건식 LOOP
        실행문
    END LOOP;
*/

-- 1) 1 ~ 5 출력하기
DECLARE 
    N NUMBER(1);
BEGIN
    N := 1;
    WHILE N <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
        N := N + 1;
    END LOOP;
END;

-- 2) EMPLOYEES 테이블의 모든 사원의 FIRST_NAME, LAST_NAME 조회
-- FIRST_NAME과 LAST_NAME을 레코드 변수에 저장하고 해당 값을 조회
-- 레코드 변수는 사원 1명의 정보만 저장할 수 있으므로 한 명씩 저장 후 출력
-- 사원번호는 100 ~ 206 값을 가짐
DECLARE
    -- 참조 변수 선언
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;     -- 사원번호를 저장할 변수(EMP_NO) 선언
    -- 레코드 변수 정의
    TYPE NAME_TYPE IS RECORD(
        F_NAME EMPLOYEES.FIRST_NAME%TYPE,
        L_NAME EMPLOYEES.LAST_NAME%TYPE
        );
    -- 레코드 변수 선언
    EMP_NAME NAME_TYPE; 

BEGIN
    -- 사원번호 기준으로 WHILE LOOP
    EMP_NO := 100;                         -- 사원번호 100부터 
    WHILE EMP_NO <= 206 LOOP               -- 최대 206까지 LOOP
        SELECT FIRST_NAME, LAST_NAME       
          INTO EMP_NAME                    -- EMPLOYEES 테이블에서 FIRST/LAST_NAME을 가져와서 EMP_NAME에 저장
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID = EMP_NO;       -- EMPLOYEE_ID와 EMP_NO가 같다는 조건 하에.
        DBMS_OUTPUT.PUT_LINE(EMP_NAME.F_NAME || ' ' || EMP_NAME.L_NAME);
        EMP_NO := EMP_NO + 1;              -- 1씩 증가
    END LOOP;
END;


-- 8. FOR문
/*
    FOR 변수 IN 시작..종료 LOOP
        실행문
    END LOOP;
*/

-- 1) 1 ~ 5
DECLARE 
    N NUMBER(1); 
BEGIN
    FOR N IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
    END LOOP;
END;

-- 2) 짝수/홀수
DECLARE
    N NUMBER;
    MODULAR NUMBER(1);   -- 2로 나눈 나머지 값을 저장
    RESULT VARCHAR2(10 BYTE);
BEGIN
    FOR N IN 1..10 LOOP
     SELECT MOD(N, 2)     -- N을 2로 나눈 나머지
       INTO MODULAR
       FROM DUAL;         -- 사용할 테이블 없으니까 DUAL
       IF MODULAR = 0 THEN  -- MODULAR값이 0과 같다면
           RESULT := '짝수';
       ELSE
           RESULT := '홀수';
      END IF;
        DBMS_OUTPUT.PUT_LINE(N || '은 ' || RESULT || '입니다.');
    END LOOP;
END;

-- 3) EMPLOYEES 테이블의 모든 사원의 연봉 합계/평균 조회하기
DECLARE
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;  -- EMPLOYEE_ID 기준으로 해야하니 EMP_NO에 같은 타입으로 변수 만들기(선언)
    ESALARY EMPLOYEES.SALARY%TYPE;
    S_SALARY NUMBER;    -- 합계 변수
    CNT NUMBER;         -- 사원수
    A_SALARY NUMBER;    -- 평균 변수

BEGIN
     S_SALARY := 0;
     CNT := 0;          -- 합계와 사원수 값 초기화
     FOR EMP_NO IN 100..206 LOOP
        SELECT SALARY
          INTO ESALARY
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID = EMP_NO;
         S_SALARY := S_SALARY + ESALARY;
         CNT := CNT + 1;
     END LOOP;
     A_SALARY := S_SALARY / CNT;
     DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || S_SALARY);
     DBMS_OUTPUT.PUT_LINE('연봉평균 : ' || A_SALARY);
END;


DECLARE
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;  -- EMPLOYEE_ID 기준으로 해야하니 EMP_NO에 같은 타입으로 변수 만들기(선언)
    ESALARY EMPLOYEES.SALARY%TYPE;
    S_SALARY NUMBER;    -- 합계 변수
    A_SALARY NUMBER;    -- 평균 변수

BEGIN
    S_SALARY := 0;
    FOR EMP_NO IN 100..206 LOOP
        SELECT SUM(SALARY), AVG(SALARY)
          INTO S_SALARY, A_SALARY
          FROM EMPLOYEES;
          DBMS_OUTPUT.PUT_LINE('합계는' || S_SALARY || ' 평균은' || A_SALARY);
    END LOOP;
END;

-- 4) DEPARTMENT_ID가 50인 사원정보를 DEPT50 테이블에 복사하기
--    (1) EMPLOYEES와 구조가 동일한 DEPT50 테이블 생성
--    (2) 행 변수를 이용해 EMPLOYEES 정보 읽기
--    (3) 행 변수의 DEPARTMENT_ID가 50이면 DEPT50에 INSERT
CREATE TABLE DEPT50
    AS (SELECT * FROM EMPLOYEES WHERE 1 = 2);    -- 만족하지 않는 조건을 넣어주면 테이블 구조만 복사해온다
    
DECLARE
    V_ROW EMPLOYEES%ROWTYPE;
BEGIN
    FOR V_ROW IN(SELECT * FROM EMPLOYEES) LOOP
        IF V_ROW.DEPARTMENT_ID = 50 THEN
            INSERT INTO DEPT50 VALUES V_ROW;
        END IF;
    END LOOP;
    COMMIT;  -- INSERT한거니까 커밋 필요
END;
    



-- 9. EXIT문
-- LOOP 종료
DECLARE
    N NUMBER;
BEGIN
    N := 1;
    WHILE TRUE LOOP  -- 무한루프 표현법
        IF N > 100 THEN
            EXIT;    -- 101 에서 종료
        END IF;
        N := N+1;    -- 계속해서 누적시킴
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(N);
END;


-- 10. CONTINUE문
-- LOOP문의 시작부터 다시 시작

-- DEPARTMENT_ID가 50인 사원을 제외하고 연봉 합계 조회하기

DECLARE
    EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE;
    SAL EMPLOYEES.SALARY%TYPE;
    DEPT_ID EMPLOYEES.DEPARTMENT_ID%TYPE;
    TOTAL NUMBER;
BEGIN
    TOTAL := 0;
    FOR EMP_ID IN 100..206 LOOP
        SELECT SALARY, DEPARTMENT_ID
          INTO SAL, DEPT_ID
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID = EMP_ID;
        IF DEPT_ID = 50 THEN   -- 만약 DEPT_ID에 저장된 값이 50이다?
           CONTINUE;           -- 다시 FOR LOOP로 돌아가서 다음 번호로 진행하라
        END IF;
        TOTAL := TOTAL + SAL;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || TOTAL);             -- 부서번호 NULL 포함
END;

SELECT SUM(SALARY) FROM EMPLOYEES WHERE DEPARTMENT_ID != 50;  -- 집계함수 SUM은 NULL값 무시


-- 11. 배열 선언하기
-- 테이블 타입 : 특정 칼럼의 모든 데이터를 배열에 저장

DECLARE

    -- SALARY 칼럼의 모든 값을 저장할 배열(SALARIES) 생성
    
    -- 배열의 타입 정의
    TYPE SALARY_TYPE IS TABLE OF EMPLOYEES.SALARY%TYPE INDEX BY BINARY_INTEGER; 
    
    -- 배열의 선언
    SALARIES SALARY_TYPE;
    
    -- 인덱스 선언
    I BINARY_INTEGER;
    
    -- 행(ROW) 변수 선언
    V_ROW EMPLOYEES%ROWTYPE;
    
BEGIN 
    I := 0;
    FOR V_ROW IN(SELECT * FROM EMPLOYEES) LOOP    -- 모든 사원의 연봉정보를 SAL로 가져와라.   IN은 IN 연산자처럼도 사용 가능
                                                  -- 서브쿼리의 결과가 행단위로 가져오기 때문에 행 변수 선언
        SALARIES(I) := V_ROW.SALARY;              -- V_ROW에서의 SALARY.   PL/SQL에서의 인덱스 표현 ()
        I := I + 1;
    END LOOP;

    FOR I IN 0..106 LOOP
        DBMS_OUTPUT.PUT_LINE(SALARIES(I)); 
    END LOOP;
END;


-- 12. 예외처리
/*
    EXCEPTION
        WHEN 예외 THEN
            예외처리
        WHEN 예외 THEN
            예외처리
        WHEN OTHERS THEN   -- 모든 경우의 예외others를 처리(자바의 Exception 같은)
            예외처리
*/
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
--     WHERE EMPLOYEE_ID = 0;  -- 존재하지 않는 사원 조회(예외를 보기 위해)
      WHERE DEPARTMENT_ID = 50;  -- 변수에 저장할 데이터가 너무 많음
EXCEPTION 
--    WHEN OTHERS THEN         -- OTHERS 자리에 예외메시지인 NO_DATA_FOUND를 넣어줘도 됨
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외코드 ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);
END;
