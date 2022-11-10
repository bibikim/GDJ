package com.gdu.app12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app12.domain.BbsDTO;

// interface는 private 불가!!! -> public만 가능. 그래서 생략도 가능. 풀네임: public abstract <- 둘다 생략 가능. 그래서 추상메소드라고 한거야!!!
@Mapper
public interface BbsMapper {     // 매퍼니까 = db에서 가져오는거! 다 가져오는거 아니고 골라서 가져오는거.. 따라서 begin과 end값
	/*목록*/
	public int selectAllBbsCount();
	public List<BbsDTO> selectAllBbsList(Map<String, Object> map);   // begin과 end 전달할 Map

	/*삽입*/ // 게시글 작성할 때, 댓글 작성할 때 경우 다 만들기
	public int insertBbs(BbsDTO bbs);    // 원댓 삽입
	// insertReply할 때 이전에 있었던 대댓들은 update 해줘야 함(순서맞추기 위해) -> insert할 때, update도 동시에 돌아가야기 때문에 트랜잭션 필요
	public int updatePreviousReply(BbsDTO bbs);  // 댓글 삽입 전 기존 댓글의 group_order 값을 업데이트 해야함 -> order가 변해야 줄을 세울 수 있기 때문
	// └ 기존 댓글은 +1, 새로 들어가는 애가 1 .. 먼말이고
	public int insertReply(BbsDTO bbs);  // 대댓 삽입 (같은 BbsDTO지만 원댓과 대댓을 분리해야줘야 한다)
	
	/*삭제*/
	public int delete(int bbsNo); // 삭제할 땐 번호만 있으면 된다
}
