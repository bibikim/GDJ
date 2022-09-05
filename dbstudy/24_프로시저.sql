/*
    프로시저
    
    1. PROCEDURE
    2. 여러 개의 쿼리문을 한 번에 실행
       (이체 : UPDATE문 2개)
    3. 작성된 프로시저는 EXECUTE문으로 실행
       EXCUTE 프로시저();
    4. 형식
        CREATE [OR REPLACE] PROCEDURE 프로시저_이름[(매개변수)]  -- REPLACE : 새로 만들 때 써도 되고, 만들어있는거 수정할 때 써도 됨. 매개변수가 필요 없으면 생략 가능
        IS               -- AS 가능                              -- OR REPLACE : 기존의 프로시저를 언제나 새로 바꿔주는 단어(DROP - CREATE 느낌)
            변수선언
        BEGIN
            프로시저본문
        [EXCEPTION                  -- 예외가 있다면 여기서 예외처리
            예외처리]
        END [프로시저_이름];        -- []은 생략 가능
*/

-- 프로시저 PROC1 정의(만들기)
CREATE OR REPLACE PROCEDURE PROC1
IS  -- 또는 AS
    NAME VARCHAR2(10 BYTE);   -- 변수선언
BEGIN  -- BEGIN/END 내부에 할 일을 넣어준다
    NAME := '김한비';
    DBMS_OUTPUT.PUT_LINE(NAME);
END;

-- 프로시저 PROC1 호출(실행) / 정의랑 호출은 따로따로 돌린다~
EXECUTE PROC1();              -- EXEC PROC1();도 가능(CREATE 만들어주고 EXECUTE 실행한다)


-- 사원번호 100번 조회하기
-- 프로시저 PROC2 정의              -> 프로시저는 한번만 만들어놓으면 됨. 할 때마다 만드는게 아니고! 
CREATE OR REPLACE PROCEDURE PROC2
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;   -- 참조타입 변수 선언. FIRST_NMAE을 저장할 변수
BEGIN
    SELECT FIRST_NAME                  -- FIRS_NAME 칼럼 값을 INTO FNAME변수에 저장
      INTO FNAME                       -- INTO 변수
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;          -- 100으로 고정. 
     DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC2;

-- 프로시저 PROC2 호출              -> 호출은 여러번 가능
EXEC PROC2(); 


-- 프로시저 PROC3 정의
-- 사원번호를 전달하면 해당 사원의 FIRST_NAME을 서버메시지로 출력하기

-- 입력 파라미터 : 
-- 1. 프로시저로 전달하는 값을 저장할 변수
-- 2. 형식 : 변수명 IN 타입 
CREATE OR REPLACE PROCEDURE PROC3(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
    DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC3;

-- 프로시저 PROC3 호출
EXECUTE PROC3(109);  -- 사원번호를 외부에서 전달하겠다. 100이 정의에서 선언한 변수EMP_ID로 전달, 전달된 EMP_ID를 WHERE조건절에서 사용하는 것!
                     -- 없는 사원 번호를 넣으면 NO DATA FOUND 오류 발생


-- 프로시저 PROC4 정의
-- 사원번호=100인 사원의 FIRST_NAME을 출력 파라미터 FNAME에 저장하기
-- 1. 프로시저의 결과(반환) 값을 저장하는 변수. 
-- 2. 형식 :  변수명 OUT 타입
CREATE OR REPLACE PROCEDURE PROC4(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS       -- 적을거 없으면 생략 가능
BEGIN
    SELECT FIRST_NAME
      INTO FNAME        -- 프로시저를 호출하는 영역에서 보기 위한 작업
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
END PROC4;

-- 프로시저 PROC4 호출(프로시저가 아닌 영역)
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;  -- 출력 파라미터로 사용할 변수
BEGIN
    PROC4(FNAME);    -- PLSQL(프로그래밍) 내부에서 프로시저를 호출할 땐 EXECUTE 생략
    DBMS_OUTPUT.PUT_LINE(FNAME);    -- 1. FNAME 선언 2. PROC4의 FNNAME
END;    


-- 프로시저 PROC5 정의
-- 사원번호가 있으면 FIRST_NAME을 출력 파라미터로 전달, 없으면 'NoName'을 출력 파라미터로 전달
CREATE OR REPLACE PROCEDURE PROC5(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 500;
EXCEPTION
    WHEN OTHERS THEN  -- 모든 예외를 처리함( WHEN NO_DATA_FOUND THEN 도 가능!)
        FNAME := 'NoName';
END;

-- 프로시저 PROC5 호출
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC5(FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;


-- 연습1. 입력 파라미터에 사원번호 전달, 출력 파라미터에 FIRST_NAME 반환받기
-- 존재하지 않는 사원번호는 출력 파라미터에 NONAME 반환하기
-- 프로시저 PROC6 정의
CREATE OR REPLACE PROCEDURE PROC6(EMP_ID IN EMPLOYEES.FIRST_NAME%TYPE, FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS  -- 선언할 거 없으면 생략
BEGIN 
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        FNAME := 'NoName';
END PROC6;

-- 프로시저 PROC6 호출
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC6(160, FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
    PROC6(211, FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;




-- 최종 실습 환경

DROP TABLE BUY;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;

-- 제품 테이블
CREATE TABLE PRODUCT(
    PROD_CODE  NUMBER             NOT NULL,  -- 제품코드
    PROD_NAME  VARCHAR2(10 BYTE),            -- 제품명
    PROD_PRICE NUMBER,                       -- 제품가격
    PROD_STOCK NUMBER                        -- 재고
);
-- 제품 기본키
ALTER TABLE PRODUCT
    ADD CONSTRAINT PK_PRODUCT PRIMARY KEY(PROD_CODE);
-- 제품 입력
INSERT INTO PRODUCT VALUES(1000, '진라면', 500, 100);
INSERT INTO PRODUCT VALUES(1001, '신라면', 600, 100);
COMMIT;


-- 고객 테이블
CREATE TABLE CUSTOMER(
    CUST_NO    NUMBER             NOT NULL,   -- 고객번호
    CUST_NAME  VARCHAR2(10 BYTE),             -- 고객명
    CUST_POINT NUMBER                         -- 고객포인트
);
-- 고객 기본키
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(CUST_NO);
-- 고객 입력
INSERT INTO CUSTOMER VALUES(1, '철수', 0);
INSERT INTO CUSTOMER VALUES(2, '영희', 0);
COMMIT;

-- 구매 테이블
CREATE TABLE BUY(
    BUY_NO     NUMBER NOT NULL,  -- 구매번호
    CUST_NO    NUMBER NOT NULL,  -- 고객번호(FK)
    PROD_CODE  NUMBER NOT NULL,  -- 제품코드(FK)
    BUY_AMOUNT NUMBER            -- 구매수량
);
ALTER TABLE BUY
    ADD CONSTRAINT PK_BUY PRIMARY KEY(BUY_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_CUSTOMER FOREIGN KEY(CUST_NO)
        REFERENCES CUSTOMER(CUST_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_PRODUCT FOREIGN KEY(PROD_CODE)
        REFERENCES PRODUCT(PROD_CODE);

-- 구매 테이블 시퀀스
DROP SEQUENCE BUY_SEQ;
CREATE SEQUENCE BUY_SEQ NOCACHE;



-- 구매 프로시저
-- 1. BUY_PROC(고객번호, 제품코드, 구매수량)     -> 입력 파라미터 처리
-- 2. 진행해야 할 쿼리
--    1) 구매 테이블에 구매 내역을 INSERT 한다.
--    2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립)
--    3) 제품 테이블의 재고를 UPDATE 한다.

CREATE OR REPLACE PROCEDURE BUY_PROC
(   C_NO    IN CUSTOMER.CUST_NO%TYPE,
    P_CODE  IN PRODUCT.PROD_CODE%TYPE,
    BUY_AMT IN BUY.BUY_AMOUNT%TYPE
)    -- 어떤 고객이 어떤 제품을 N개만큼 구매
IS
BEGIN
    -- 1) 구매 테이블에 구매 내역을 INSERT 한다.
    INSERT INTO BUY(BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT)
    VALUES(BUY_SEQ.NEXTVAL, C_NO, P_CODE, BUY_AMT);
    -- 2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립, 정수로 올림CEIL/내림FLOOR 처리)
    UPDATE CUSTOMER
       SET CUST_POINT = CUST_POINT + CEIL((SELECT PROD_PRICE  -- 기존포인트에 새로 생긴 포인트() 누적
                                             FROM PRODUCT
                                            WHERE PROD_CODE = P_CODE) * BUY_AMT * 0.1) 
                                            -- P_CODE와 PROD_CODE(제품번호)가 같을 때 PRODUCT 테이블에서 제품가격 가져오기, 그리고 구매수량 곱하고 10% 구하기
     WHERE CUST_NO = C_NO;   -- 고객번호가 C_NO인 사람의 포인트를 업데이트
    -- 3) 제품 테이블의 재고를 UPDATE 한다.
    UPDATE PRODUCT
       SET PROD_STOCK = PROD_STOCK - BUY_AMT
     WHERE PROD_CODE = P_CODE;
    -- 커밋
    COMMIT; -- INSERT, UPDATE 했으니 COMMIT! 
EXCEPTION
    -- 예외 처리(예외 발생 시 아무 일도 없었던 것으로)
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외코드 ' || SQLCODE);   -- 예외코드 찍어주는 키워드
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);
        -- 롤백
        ROLLBACK;
END BUY_PROC;

-- 구매 프로시저 호출
EXECUTE BUY_PROC(1, 1000, 10);  -- 고객번호1, 제품코드1000, 구매수량10

-- 확인
SELECT PROD_CODE, PROD_NAME, PROD_PRICE, PROD_STOCK
  FROM PRODUCT;
SELECT CUST_NO, CUST_NAME, CUST_POINT
  FROM CUSTOMER;
SELECT BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT
  FROM BUY;
  
