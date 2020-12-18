package com.koreait.board3.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.board3.common.SecurityUtils;
import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.db.CommonDAO;
import com.koreait.board3.db.SQLInterUpdate;
import com.koreait.board3.model.BoardParam;
import com.koreait.board3.model.BoardSEL;

public class BoardService {
	
//	글 목록 확인
	public static List<BoardSEL> showListAll(HttpServletRequest request){
		int typ = Utils.getIntParam(request, "typ");
		BoardParam param = new BoardParam();
		param.setTyp(typ);
		
		return BoardDAO.showListAll(param);
	}
	
// 	글 읽기
	public static BoardSEL readCtnt(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
	
		BoardParam param = new BoardParam();
		param.setI_board(i_board);
		
		return BoardDAO.readCtnt(param);
	}
	
//	글 등록 / 글 수정
	public static int regMod(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int typ = Utils.getIntParam(request, "typ");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		int i_user = SecurityUtils.getLoginI_User(request);
		
		if(i_board == 0) { // 등록
			String sql = " INSERT INTO t_board "
						 + " (typ, seq, title, ctnt, i_user) "
						 + " SELECT "
//						 	    seq가 NULL이면 0으로 하고 +1(seq - null : 글이 하나도 없을때)
						 + " ?, IFNULL(MAX(seq), 0) + 1, ?, ?, ? " 
						 + " FROM t_board "
						 + " WHERE typ = ? ";
			
			BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
				
				@Override
				public void proc(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, typ);
					pstmt.setNString(2, title);
					pstmt.setNString(3, ctnt);
					pstmt.setInt(4, i_user);
					pstmt.setInt(5, typ);
				}
			});
		}else { // 수정
			
		}
		
		return 0;
	}
	
//	글 삭제
	public static int delBoard(HttpServletRequest request) {
		int i_board = Utils.getIntParam(request, "i_board");
		int i_user = Utils.getIntParam(request, "i_user");
		
		String sql = " DELETE FROM t_board WHERE i_board = ? "
					 + " AND i_user = ? ";
		
		return CommonDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, i_board);
				pstmt.setInt(2, i_user);
			}
		});
	}
}
