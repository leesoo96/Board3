package com.koreait.board3.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.common.Utils;
// ajax는 데이터만 리턴하는 것뿐 페이지를 리로드하지않는다!!!!!
@WebServlet("/board/ajax_favorite")
public class BoardFavoriteSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int state = Utils.getIntParam(request, "state");
		
//		System.out.println(state); 1이 출력됨 - 좋아요 안누름 / 0이면 좋아요 누름상태 
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
//		String result = "{ \"result\" : 1}"; // data: {result: 1} -> 확인완료
		
		response.getWriter().write(BoardService.ajax_favorite(request));
	}
}
