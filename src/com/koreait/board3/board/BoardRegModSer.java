package com.koreait.board3.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;

@WebServlet("/board/bRegmod")
public class BoardRegModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ");
		
		if(SecurityUtils.isLogout(request)) {  
			response.sendRedirect("/login"); 
			return;
		}
		
		int i_board = Utils.getIntParam(request, "i_board");
		if(i_board > 0) { // 글 수정
			request.setAttribute("data", BoardService.readCtnt(request));
		}
		
		request.setAttribute("typ", typ);
		request.setAttribute("jsList", new String[]{"board"});
		
		Utils.forwardTemp("등록/수정", "temp/basic_temp", "board/bRegMod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		int result = BoardService.regMod(request);
		int i_board = Utils.getIntParam(request, "i_board");

		if(i_board > 0) {
			response.sendRedirect("bDetail?i_board=" + i_board);
		}else {
			int typ = Utils.getIntParam(request, "typ");
			response.sendRedirect("list?typ=" + typ);
		}
*/		// 한 줄로 코드 수정!
		response.sendRedirect(BoardService.regMod(request));
	}
}
