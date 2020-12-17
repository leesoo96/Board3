package com.koreait.board3.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.model.BoardParam;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ");
		int i_board = Utils.getIntParam(request, "i_board");
		
		if(SecurityUtils.isLogout(request)) {  
			response.sendRedirect("/login"); 
			return;
		}
		
		String[] jsList = {"board"};
		
		request.setAttribute("typ", typ);
		request.setAttribute("jsList", new String[]{"board"});
		request.setAttribute("list", BoardService.showListAll(request));
	
		Utils.forwardTemp("메인페이지", "temp/basic_temp", "board/bList", request, response);
	}
}
