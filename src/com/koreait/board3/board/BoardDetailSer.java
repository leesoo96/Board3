package com.koreait.board3.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.board.cmt.BoardCmtService;
import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.model.BoardModel;
import com.koreait.board3.model.BoardParam;
import com.koreait.board3.model.BoardSEL;

@WebServlet("/board/bDetail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SecurityUtils.isLogout(request)) {  
			response.sendRedirect("/login"); 
			return;
		}

		int err = Utils.getIntParam(request, "err");
		switch (err) {
		case 1:
			request.setAttribute("err", "댓글 내용이 너무 깁니다.");
			break;
		}
		
		request.setAttribute("jsList", new String[]{"board"});
		
		BoardSEL contents = BoardService.readCtnt(request);
		request.setAttribute("contents", contents);

		Utils.forwardTemp(contents.getTitle(), "temp/basic_temp", "board/bDetail", request, response);
	}
}
