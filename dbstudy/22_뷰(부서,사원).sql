/*
    뷰
    
    1. VIEW
    2. 가상 테이블
    3. 디스크에 저장된 테이블이 아님(쿼리만 저장해 둔 형태) > 디비 입장에서는 진짜 테이블은 아니고 쿼리만 있는..
    4. 자주 사용하는 복잡한 쿼리가 있다면 뷰로 저장해 둠
    5. 저장된 뷰는 테이블처럼 SELECT문을 활용하면 됨
    6. 뷰로 인한 성능상 이점은 없고, 보안상 이점은 있음(숨겨놓았으니까)
    7. 뷰 생성
       CREATE VIEW 뷰_이름 AS (쿼리문)  -- (쿼리문)을 뷰_이름으로 지정해서 저장하것다
*/

-- SCOTT에 DBA권한 주기(VIEW 생성 위해)
--   GRANT DBA TO SCOTT;


-- 1. 뷰 생성
--    대개 조인을 뷰로 생성 많이 하는 편. 뷰끼리도 조인도 함
CREATE VIEW VIEW_EMP
    AS (SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME, E.GENDER, E.POSITION, E.HIRE_DATE, E.SALARY
          FROM DEPARTMENT D INNER JOIN EMPLOYEE E
            ON D.DEPT_NO = E.DEPART);

-- 2. 뷰 확인
SELECT * FROM VIEW_EMP;

-- 3. 뷰 삭제
DROP VIEW VIEW_EMP;

-- 사용자가 작성한 VIEW 확인하려면 USER_VIEWS 데이터 사전을 확인
DESC USER_VIEWS;
SELECT VIEW_NAME  -- 뷰 이름
     , TEXT       -- TEXT : 뷰가 저장하고 있는 쿼리문이 나옴
  FROM USER_VIEWS;
