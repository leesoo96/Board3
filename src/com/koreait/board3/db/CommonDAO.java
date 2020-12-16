package com.koreait.board3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDAO {
//  insert, update, delete 에서 사용
	public static int executeUpdate(String sql, SQLInterUpdate siu) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 
		 try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			siu.proc(pstmt);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt);
		}
		 
		 return 0;
	}
}
