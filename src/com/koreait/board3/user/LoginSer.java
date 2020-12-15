package com.koreait.board3.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board3.common.Utils;
import com.koreait.board3.db.UserDAO;
import com.koreait.board3.model.UserModel;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Utils.forward("로그인", "user/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		로그인 처리
		int result = UserService.login(request);
		
		if(result == 1) {
			response.sendRedirect("/loginChk.jsp");
			return;
		}
		
		System.out.println("result = " + result);
	}
}
