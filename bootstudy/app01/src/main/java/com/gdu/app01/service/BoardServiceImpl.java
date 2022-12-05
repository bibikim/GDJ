package com.gdu.app01.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app01.domain.BoardDTO;
import com.gdu.app01.mapper.BoardMapper;

        
@Service  // 컴포넌트로 등록
public class BoardServiceImpl implements BoardService {

	
	// ★ Service는 DAO를 사용합니다. 언제나!!!
	@Autowired
	private BoardMapper boardMapper;   // ("가져올 빈의 이름 = 메소드의 이름", 타입)


	@Override
	public List<BoardDTO> findAllBoards() {   // 목록보기
		
		return boardMapper.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int boardNo) {
		
		return boardMapper.selectBoardByNo(boardNo);
	}

	@Override
	public int saveBoard(BoardDTO board) {  // save할 내용은 BoardDTO board에 들어있다아. 
		// TODO Auto-generated method stub
		return boardMapper.insertBoard(board);  // dao에 주입되어 있음.. 다오에 insert가 있으니까 그거 불러주면 된다.
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return boardMapper.updateBoard(board);
	}

	@Override
	public int removeBoard(int boardNo) {
		// TODO Auto-generated method stub
		return boardMapper.deleteBoard(boardNo);
	}
	
	@Override
	public void removeBoardList(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터
		String[] boardNoList = request.getParameterValues("boardNoList");  // .getParameterValues => 스트링배열로 넘어감 -> String[]을 List로 변경
		
		// 삭제
		int result = boardMapper.deleteBoardList(Arrays.asList(boardNoList));  // String 배열을 List<String>으로 변경해서 전달
		
		try {
			
			// 자바스크립트로 응답으로 만들어서 처리하는 방식
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('모두 삭제 성공');");
				out.println("location.href='" + request.getContextPath() + "/brd/list';");  //  /brd/list로 redirect
			} else {
				out.println("alert('모두 삭제 실패');");
				out.println("history.back();");  // 이전 화면으로 이동
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
