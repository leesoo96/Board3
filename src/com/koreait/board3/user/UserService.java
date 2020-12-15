package com.koreait.board3.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.SQLInterUpdate;
import com.koreait.board3.db.UserDAO;
import com.koreait.board3.model.UserModel;

public class UserService {

//	회원가입
	public static int join(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		int gender = Utils.getIntParam(request, "gender");
		String phone = request.getParameter("phone");
		
		String salt = SecurityUtils.getSalt();
		
//		암호화된 비밀번호 - 복호화 불가
		String enryptPw = SecurityUtils.getSecurePassword(user_pw, salt);
		
		System.out.println("salt:" + salt);
		System.out.println("enryptPw:" + enryptPw);
		
		UserModel um = new UserModel();
		um.setUser_id(user_id);
		um.setUser_pw(enryptPw);
		um.setSalt(salt);
		um.setNm(nm);
		um.setGender(gender);
		um.setPhone(phone);
		
		String sql = " INSERT INTO t_user "
					 + " (user_id, user_pw, salt, nm, gender, phone) "
					 + " VALUES (?, ?, ?, ?, ?, ?) ";
				
		return UserDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, um.getUser_id());
				pstmt.setString(2, um.getUser_pw());
				pstmt.setString(3, um.getSalt());
				pstmt.setString(4, um.getNm());
				pstmt.setInt(5, um.getGender());
				pstmt.setString(6, um.getPhone());
			}
		});
	}
	
//	로그인 - 0 = 에러 / 1 = 성공 / 2 = 아이디 없음 / 3 = 비밀번호 틀림
	public static int login(HttpServletRequest request) {

		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		UserModel um = new UserModel();
		um.setUser_id(user_id);
		um.setUser_pw(user_pw);
		
		UserModel model = UserDAO.selUser(um);
		if(model == null) {
			return 2;
		}
		
		String encryptPw = SecurityUtils.getSecurePassword(user_pw, model.getSalt());
		if(encryptPw.equals(model.getUser_pw())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", model);
			model.setSalt(null);
			model.setUser_pw(null);
			
			return 1;
		}
		
		return 3;
	}
}