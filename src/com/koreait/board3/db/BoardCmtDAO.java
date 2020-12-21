package com.koreait.board3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board3.model.BoardCmtSEL;
import com.koreait.board3.model.BoardParam;

public class BoardCmtDAO extends CommonDAO{

//	댓글 목록 읽기
	public static List<BoardCmtSEL> showListCmt(BoardParam param){
		List<BoardCmtSEL> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_cmt, i_board, i_user, ctnt, r_dt "  
					   + " FROM t_board_cmt " 
					   + " WHERE i_cmt = ? "
					   + " ORDER BY i_board DESC ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_cmt());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardCmtSEL cs = new BoardCmtSEL();
				
				cs.setI_cmt(rs.getInt("i_cmt"));
				cs.setI_board(rs.getInt("i_board"));
				cs.setI_user(rs.getInt("i_user"));
				cs.setCtnt(rs.getNString("ctnt"));
				cs.setR_dt(rs.getString("r_dt"));

				list.add(cs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
}
