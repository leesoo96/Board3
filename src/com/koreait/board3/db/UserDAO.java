package com.koreait.board3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.model.UserModel;

public class UserDAO extends CommonDAO{
	
	public static UserModel selUser(UserModel um) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_user, nm, user_pw, salt "
					 + " FROM t_user WHERE user_id = ? ";
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, um.getUser_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				UserModel model = new UserModel();
				model.setI_user(rs.getInt("i_user"));
				model.setNm(rs.getString("nm"));
				model.setUser_pw(rs.getString("user_pw"));
				model.setSalt(rs.getString("salt"));
				
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		return null;
	}
/*	
	public static int login(UserModel um) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_user, nm, user_pw, salt "
					 + " FROM t_user WHERE user_id = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, um.getUser_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 쿼리문을 실행하여 select된 아이디가 있다면 
				String dbPw = rs.getString("user_pw"); // 이미 DB에서 암호화된 비밀번호 
				String dbSalt = rs.getString("salt"); 
				String encryptPw = SecurityUtils.getSecurePassword(um.getUser_pw(), dbSalt);
				
				if(encryptPw.equals(dbPw)) { // 암호화한 비밀번호와 입력한 비밀번호가 같으면
					return 1; // 로그인 성공
				}else {
					return 3; // 비밀번호 틀림
				}
			}else {
				return 2; // 아이디 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return 0; // 로그인 에러
	} => 사용X */ 
}
