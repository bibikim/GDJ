package com.gdu.app13.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;

@Mapper
public interface UserMapper {
	
	/* selectUserByMap으로 통합된 쿼리들 */
	// public UserDTO selectUserById(String id);
	// public UserDTO selectUserByEmail(String email);
	public UserDTO selectUserByIdPw(UserDTO user);
	public UserDTO selectUserByMap(Map<String, Object> map);
	
	public RetireUserDTO selectRetireUserById(String id);
	public int insertUser(UserDTO user);
	public int updateAccessLog(String id);
	public int insertAccessLog(String id);
	public int deleteUser(int userNo);
	public int insertRetireUser(RetireUserDTO user);
	public int updateSessionInfo(UserDTO user);
	public int updateUserPassword(UserDTO user); // user에 userNo와 pw 들어있어야 함!
	public int insertSleepUser();
	public int deleteUserForSleep();
	public SleepUserDTO selectSleepUserId(String id); // 반환되는 건 유저1명 아이디는 하나니깐 SleepUserDTO
	public int insertRestoreUser(String id);
	public int deleteSleepUser(String id);
}
