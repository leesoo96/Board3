package com.koreait.board3.board.cmt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardCmtDAO;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.db.SQLInterUpdate;
import com.koreait.board3.model.BoardCmtModel;
import com.koreait.board3.model.BoardCmtSEL;
import com.koreait.board3.model.BoardParam;

public class BoardCmtService {

//	댓글 쓰기
	public static String regCmt(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int i_user = SecurityUtils.getLoginI_User(request);
		String ctnt = request.getParameter("ctnt");
		
		String sql = " INSERT INTO t_board_cmt "
					 + " (i_board, i_user, ctnt) "
					 + " VALUES (?, ?, ?) ";
		
		BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, i_board);
				pstmt.setInt(2, SecurityUtils.getLoginI_User(request));
				pstmt.setNString(3, ctnt);
			}
		});
//	  return "/board/bDetail?i_board=" + i_board;
	  return "../bDetail?i_board=" + i_board;
	}
	
//	댓글 수정
	public static String mod(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int i_cmt = Utils.getIntParam(request, "i_cmt");
		int i_user = SecurityUtils.getLoginI_User(request);
		String ctnt = request.getParameter("ctnt");
		
		String sql = " UPDATE t_board_cmt "
					 + " SET ctnt = ? "
					 + " WHERE i_cmt = ? "
					 + " AND i_user = ? ";
		
		BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setNString(1, ctnt);
				pstmt.setInt(2, i_cmt);
				pstmt.setInt(3, i_user);
			}
		});
	  return "../bDetail?i_board=" + i_board;
	}
	
//	댓글 삭제
	public static String del(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int i_cmt = Utils.getIntParam(request, "i_cmt");
		
		String sql = " DELETE FROM t_board_cmt "
					 + " WHERE i_cmt = ? "
					 + " AND i_user = ? ";
		
		BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, i_cmt);
				pstmt.setInt(2, SecurityUtils.getLoginI_User(request));
			}
		});
		
		return "../bDetail?i_board=" + i_board;
	}
}
