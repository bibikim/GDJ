package com.gdu.app01.xml05;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) throws Exception {
		
		// 프로젝트의 Build Path에 ojdbc6.jar 등록하고 실행합니다.
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml05/appCtx.xml");
		MyConnection myCon = ctx.getBean("conn", MyConnection.class);
		Connection con = myCon.getConnection();

		if(con != null) {
			con.close();     // 커넥션 닫을 때 try-catch 만들어야 되는데 만들기 귀찮아서 throws Exception. main은 예외를 던질 수 있어서!
			System.out.println("Connection 해제 완료");
		}
		
		ctx.close();
	}

}