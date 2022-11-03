package com.gdu.contact.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gdu.contact.domain.ContactVO;

public class ContactDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	
	private Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");	
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return con;
	}
	
	private void close() {
		
		try {
			
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}	
	}
	
	
	// 목록보기
	public List<ContactVO> findAllContacts() {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		
		try {
			
			con = getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				ContactVO contact = new ContactVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				contacts.add(contact);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return contacts;
	}
	
	
	// 연락처 등록하기
	public int regiContact(ContactVO contact) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO CONTACT(NO, NAME, TEL, ADDR, EMAIL, NOTE) VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getAddr());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getNote());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return result;
	}
	

	
	
	
	
	
	
}
