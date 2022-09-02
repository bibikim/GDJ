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
        END [프로시저_이름];
*/

-- 프로시저 PROC1 정의(만들기)
CREATE OR REPLACE PROCEDURE PROC1
IS 
    NAME VARCHAR2(10 BYTE);   -- 변수선언
BEGIN
    NAME := '김한비';
    DBMS_OUTPUT.PUT_LINE(NAME);
END;

-- 프로시저 PROC1 호출(실행)
EXECUTE PROC1();              -- EXEC PROC1();도 가능


