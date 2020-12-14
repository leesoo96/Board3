package com.koreait.board3.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.model.UserModel;

public class UserService {

	public static int join(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		int gender = Utils.getIntParam(request, "gender");
		String phone = request.getParameter("phone");
		
		byte[] salt = SecurityUtils.getSalt();
//		암호화된 비밀번호 - 복호화 불가
		String enryptPw = SecurityUtils.getSecurePassword(user_pw, salt);
		
		System.out.println("salt:" + salt);
		System.out.println("salt.toString:"+new String(salt));
		System.out.println("enryptPw:" + enryptPw);
		
		UserModel um = new UserModel();
		um.setUser_id(user_id);
		um.setUser_pw(enryptPw);
		um.setSalt(salt.toString());
		um.setNm(nm);
		um.setGender(gender);
		um.setPhone(phone);
		
		return 0;
	}
}
