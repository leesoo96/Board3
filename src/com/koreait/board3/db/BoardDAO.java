package com.koreait.board3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board3.model.BoardModel;
import com.koreait.board3.model.BoardParam;
import com.koreait.board3.model.BoardSEL;

public class BoardDAO extends CommonDAO{

//	글 목록
	public static List<BoardSEL> showListAll(BoardParam param){
		List<BoardSEL> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT A.i_board, A.seq, A.title, A.r_dt, A.hits, A.i_user, B.nm "
					 + " FROM t_board A "
					 + " INNER JOIN t_user B "
					 + " ON A.i_user = B.i_user "
					 + " WHERE A.typ = ? "
					 + " ORDER BY A.seq DESC ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardSEL m = new BoardSEL();
				m.setTyp(param.getTyp());
				m.setI_board(rs.getInt("i_board"));
				m.setSeq(rs.getInt("seq"));
				m.setTitle(rs.getNString("title"));
				m.setR_dt(rs.getString("r_dt"));
				m.setHits(rs.getInt("hits"));
				m.setI_user(rs.getInt("i_user"));
				m.setNm(rs.getString("nm"));
				
				list.add(m);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
//	글 읽기
	public static BoardSEL readCtnt(BoardParam param) {
		BoardSEL sel = new BoardSEL();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.seq, A.title, A.ctnt, "
					+ " A.r_dt, A.hits, A.i_user, B.nm "
					+ " FROM t_board A "
					+ " INNER JOIN t_user B "
					+ " ON A.i_user = B.i_user "
					+ " WHERE A.i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_board());
			
			rs = pstmt.executeQuery();

			if(rs.next()) {			
				sel.setSeq(rs.getInt("seq"));
				sel.setTitle(rs.getNString("title"));
				sel.setCtnt(rs.getNString("ctnt"));
				sel.setR_dt(rs.getString("r_dt"));
				sel.setHits(rs.getInt("hits"));
				sel.setNm(rs.getString("nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return sel;
	}
	
}
