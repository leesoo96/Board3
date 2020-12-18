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
	<a href="bRegmod?i_board=${contents.i_board}">
		<button>수정하기</button>
	</a>
</c:if>

	<div style="margin:10px;">
		<div>
			<form action="/cmt" method="post">
				<input type="hidden" name="typ" value="${contents.typ }">
				<input type="hidden" name="i_board" value="${contents.i_board }">
				댓글 : <input type="text" name="cmt_ctnt">
				<input type="submit" value="댓글쓰기">
			</form>
		</div>
		
		<div>
			<table>
				<tr>
					<th>댓글 목록</th>
				</tr>
				<c:forEach items="${cmtContents }" var="item">
					<tr>
						<td>${item.ctnt }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<a href="list?typ=${contents.typ}">돌아가기</a>

<script>
	function delConfirm(){
		var alert = confirm('정말 삭제하시겠습니까?');
		if(!alert){
			return false;
		}
	}
	
	<c:if test="${msg != null}">
		alert('${msg}');
	</c:if>
</script>