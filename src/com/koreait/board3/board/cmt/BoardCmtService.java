package com.koreait.board3.board.cmt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.koreait.board3.common.Utils;
import com.koreait.board3.db.BoardCmtDAO;
import com.koreait.board3.db.BoardDAO;
import com.koreait.board3.db.SQLInterUpdate;
import com.koreait.board3.model.BoardCmtSEL;
import com.koreait.board3.model.BoardParam;

public class BoardCmtService {

//	댓글 목록 확인
	public static List<BoardCmtSEL> showListCmt(HttpServletRequest request){
		int i_cmt = Utils.getIntParam(request, "i_cmt");
		
		BoardParam param = new BoardParam();
		param.setI_cmt(i_cmt);
		
		return BoardCmtDAO.showListCmt(param);
	}
	
//	댓글 쓰기
	public static int writeCmt(HttpServletRequest request) {
		int i_cmt = Utils.getIntParam(request, "i_cmt");
		int i_board = Utils.getIntParam(request, "i_board");
		int i_user = Utils.getIntParam(request, "i_user");
		String ctnt = request.getParameter("ctnt");
		String r_dt = request.getParameter("r_dt");
		
		String sql = " INSERT INTO t_board_cmt "
				+ " (i_cmt, i_board, i_user, ctnt, r_dt) "
				+ " VALUES (?, ?, ?, ?, ?) ";
		
		return BoardDAO.executeUpdate(sql, new SQLInterUpdate() {
			
			@Override
			public void proc(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
