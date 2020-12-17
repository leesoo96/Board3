package com.koreait.board3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;

@WebServlet("/main")
public class MainSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SecurityUtils.isLogout(request)) {  // 로그인 상태가 아니라면
			response.sendRedirect("/login"); // login으로
			return;
		}
		
		Utils.forwardTemp("메인페이지", "temp/basic_temp", "main", request, response);
	}
}
