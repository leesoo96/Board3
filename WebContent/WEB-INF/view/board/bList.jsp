<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div> 
	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${list }" var="item">
			<tr class="pointer" onclick="clickArticle(${typ }, ${item.i_board });">
				<td>${item.i_board}</td>
				<td>${item.title}</td>
				<td>${item.r_dt}</td>
				<td>${item.hits }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageContainer">
		<c:forEach begin="1" end="${pageCnt}" var="i">
			<span class="page">
				<a href="/board/bList?typ=${typ}&page=${i}">${i}</a>
			</span>
		</c:forEach>
	</div>
	<div>        <!-- board앞에 /가 없으면 이동 전 주소 맨 끝에만 주소값이 바뀐다
					그렇기때문에 꼭 /를 붙여서 원 주소를 날려야한다 -->
		<a href="/board/bRegmod?typ=${typ }"><button>WRITE</button></a>
	</div>
</div>