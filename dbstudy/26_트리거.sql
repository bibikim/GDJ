/*
    트리거
    
    1. Trigger
    2. DML(INSERT, UPDATE, DELETE) 수행 후 트리거가 자동으로 수행    -> EXECUTE 필요 없음
    3. DML 직전에 수행되는 BEFORE 트리거, 직후에 수행되는 AFTER 트리거가 있음(내가 정하는거염)
    4. 기본적으로 작업 수행 행(ROW) 단위로 트리거가 적용됨(행이 두 개다 > 트리거 두 번 동작. 1:1 매칭)
    5. 형식
        CREATE [OR REPLACE] TRIGGER 트리거_이름
        [ALTER | BEFORE]
        [INSERT OR UPDATE OR DELETE]            ->(셋 중에 하나) 이때 자동 수행
        [ON 테이블_이름]
        [FOR EACH ROW]                          ->(각각의 행마다)
        BEGIN
            트리거 작업
        END [트리거_이름];
*/

-- 트리거 TRIG1 정의
CREATE OR REPLACE TRIGGER TRIG1
    AFTER          -- AFTER, BEFORE
    UPDATE         -- INSERT OR UPDATE OR DELETE
    ON DEPARTMENT  -- DEPARTMENT 테이블을 UPDATE할 때 동작함
    FOR EACH ROW   -- UPDATE되는 행마다 트리거가 동작함 
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END TRIG1;


-- 트리거 TRIG1 동작 확인(호출X 자동 수행함)
UPDATE DEPARTMENT
   SET LOCATION = '부천'
 WHERE DEPT_NO = 1;
 

-- 트리거 TRIG2 정의
CREATE OR REPLACE TRIGGER TRIG2
    AFTER
    INSERT OR UPDATE OR DELETE    -- 작성 순서 없음
    ON DEPARTMENT
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        DBMS_OUTPUT.PUT_LINE('INSERT 이후 동작');
    ELSIF UPDATING THEN
        DBMS_OUTPUT.PUT_LINE('UPDATE 이후 동작');
    ELSIF DELETING THEN
        DBMS_OUTPUT.PUT_LINE('DELETE 이후 동작');
    END IF;
END TRIG2;

-- 트리거 TRIG2 동작 확인
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '제주');
UPDATE DEPARTMENT SET LOCATION = '경주' WHERE DEPT_NO = 2;
DELETE FROM DEPARTMENT WHERE DEPT_NO = 3; 

-- DEPARTMENT 테이블의 DML 작업 취소
ROLLBACK;     -- 이전 커밋까지만 돌아감


-- 트리거 TRIG3 정의

-- OLD 테이블
-- 1. INSERT, UPDATE, DELETE 수행 이전 정보가 저장되는 테이블
-- 2. 오라클에서는 :OLD로 호출
-- 3. AFTER 트리거와 함께 사용
--    1) INSERT 동작 이전 : NULL
--    2) UPDATE 동작 이전 : 수정 전 데이터
--    3) DELETE 동작 이전 : 삭제 전 데이터

CREATE OR REPLACE TRIGGER TRIG3
    AFTER
    UPDATE OR DELETE
    ON EMPLOYEE
    FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE(:OLD.NAME);  -- UPDATE/DELETE 이전엔 OLD인거니까! 동작 이전의 데이터를 :OLD테이블에 갖고 있는거
    DBMS_OUTPUT.PUT_LINE(:OLD.EMP_NO);
END TRIG3;

-- 트리거 TRIG3 동작 확인
UPDATE EMPLOYEE SET NAME = '한호열' WHERE EMP_NO = 1001;
DELETE FROM EMPLOYEE WHERE EMP_NO = 1001;

ROLLBACK;
-- 트리거 동작하고 나면 변환 전 값, 후의 값 다 확인해봐야 정상적인 동작이 됐는지 확인을 해봐야함!




-- 트리거 최종 실습
-- 목표 : 사원(EMPLOYEES) 테이블에서 삭제된 사원정보를 퇴사자(RETIRES) 테이블에 삽입하기
-- 나는 딜리트, 트리거는 인서트 수행

-- 1) 퇴사자 테이블 만들기(EMPLOYEES 테이블과 동일한 구조, 데이터 없이 복사)
DROP TABLE RETIRES;
CREATE TABLE RETIRES
AS (SELECT * FROM EMPLOYEES WHERE 1 = 2);


-- 2) RETIRE_ID, RETIRE_DATE 칼럼 추가하기
ALTER TABLE RETIRES ADD RETIRE_ID NUMBER NOT NULL;
ALTER TABLE RETIRES ADD RETIRE_DATE DATE NOT NULL;

-- 3) REITRE_ID 기본키 설정
ALTER TABLE RETIRES ADD CONSTRAINT PK_RETIRE_ID PRIMARY KEY(RETIRE_ID);

-- 4) RETIERE_SEQ 생성하기
DROP SEQUENCE RETIRE_SEQ;
CREATE SEQUENCE RETIRE_SEQ
    START WITH 1
    NOCACHE;

-- 5) RETIRE_TRIG 트리거 생성
CREATE OR REPLACE TRIGGER RETIRE_TRIG
    AFTER DELETE
    ON EMPLOYEES
    FOR EACH ROW
BEGIN
    INSERT INTO RETIRES(RETIRE_ID, RETIRE_DATE, EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
    VALUES (RETIRE_SEQ.NEXTVAL, SYSDATE, :OLD.EMPLOYEE_ID, :OLD.FIRST_NAME, :OLD.LAST_NAME, :OLD.EMAIL, :OLD.PHONE_NUMBER, :OLD.HIRE_DATE, :OLD.JOB_ID, :OLD.SALARY, :OLD.COMMISSION_PCT, :OLD.MANAGER_ID, :OLD.DEPARTMENT_ID);
                                 -- 퇴사일 = 동작한 날짜
                                 -- 삭제된 데이터는 :OLD테이블에 있고 그 데이터들을 RETIRES 테이블로 옮겨주는(삽입) 작업
END RETIRE_TRIG;

-- 6) EMPLOYEES 테이블의 데이터 DELETE 수행하기(RETIRE_TRIG 동작 확인)
DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = 100;  -- DELETE는 내가, 트리거에서는 INSERT로 트리거가 동작

ROLLBACK;

