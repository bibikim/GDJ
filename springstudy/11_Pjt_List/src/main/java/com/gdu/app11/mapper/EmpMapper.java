package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;

@Mapper
public interface EmpMapper {
	public int selectAllEmployeesCount();  // resultType=Int, select id, 파라미터 없으니까 () 빈칸.
	//public List<EmpDTO> selectEmployeesByPage(int begin, int end); // 데이터베이스로 값을 묶지 않고 보내는 게 인터페이스에선 편한 방법.
	public List<EmpDTO> selectEmployeesByPage(Map<String, Object> map); 
	public int selectFindEmployeesCount(Map<String, Object> map);
	public List<EmpDTO> selectFindEmployees(Map<String, Object> map);
	public List<EmpDTO> selectAutoCompleteList(Map<String, Object> map);  // 받아오는게 param이므로~
	
	// public List<EmpDTO> selectEmployeesByPage(int begin, int end); 
	// 데이터베이스로 값을 묶지 않고 보내는 게 인터페이스에선 편한 방법.
}
