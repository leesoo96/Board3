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
		
		if(!Utils.isLogout(request)) {  // 로그인 상태일 경우
			response.sendRedirect("/main"); // main으로 
			return;
		}
		
		Utils.forward("로그인", "user/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		로그인 처리
		int result = UserService.login(request);
		
		if(result == 1) {
			response.sendRedirect("/main");
			return;
		}
		
		switch (result) {
		case 2:
			request.setAttribute("msg", "아이디를 확인해주세요.");
			break;
		case 3:
			request.setAttribute("msg", "비밀번호가 다릅니다.");
			break;
		}
		
//		로그인 실패 시 입력한 아이디 값 유지
		String user_id = request.getParameter("user_id");
		request.setAttribute("id", user_id);
		
		doGet(request, response);
		
		System.out.println("result = " + result);
	}
}
