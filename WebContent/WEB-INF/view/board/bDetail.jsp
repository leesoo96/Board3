<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/bDetail.css">

<div id="detailWrap">
<form action="bDetail" method="post" onsubmit="return delConfirm();">
	<div>
	번호 : ${contents.seq} 조회수 : ${contents.hits }<br/>
	작성자 : ${contents.nm } 제목 : ${contents.title } <br/>
	내용 : ${contents.ctnt } <br/>
	작성날짜 : ${contents.r_dt } <br/>
	
	<input type="hidden" name="typ" value="${contents.typ }">
	<input type="hidden" name="i_board" value="${contents.i_board }">
	</div>
</form>

<c:if test="${contents.i_user == loginUser.i_user }">
	<button onclick="clickDel(${contents.i_board},${contents.typ });">삭제하기</button>
	<a href="bRegmod?typ=${contents.typ }&i_board=${contents.i_board}">
		<button>수정하기</button>
	</a>
</c:if>

	<div style="margin:10px;">
		<div>
			<form action="cmt/reg" method="post">
				<input type="hidden" name="i_board" value="${contents.i_board }">
				댓글 : <input type="text" name="ctnt">
				<input type="submit" value="댓글쓰기">
			</form>
		</div>
		
		<div>
			<table>
				<tr>
					<th>[댓글목록]</th>
				</tr>
				
				<c:forEach items="${cmtCtnt }" var="item">
					<tr>
						<td>${item.ctnt }</td>
						<td>${item.user_nm }</td>		
						<td>${item.r_dt }</td>
						<td>
					  	<c:if test="${item.i_user == loginUser.i_user }">
								<button>수정</button>	
								<a href="cmt/del?i_board=${contents.i_board }&i_cmt=${item.i_cmt}"><button>삭제</button></a>		
					  	</c:if>	
						</td>	
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<a href="list?typ=${contents.typ}">돌아가기</a>

<script>
	<c:if test="${msg != null}">
		alert('${msg}');
	</c:if>
</script>