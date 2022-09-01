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
      INTO V_SALARY
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
