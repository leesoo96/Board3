<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
<link rel="stylesheet" href="/res/css/common.css">
<c:forEach items="${jsList }" var="item">
	<script defer src="/res/js/${item }.js"></script>
</c:forEach>
<script defer src="/res/js/common.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<ul>
			  <li>${loginUser.nm } 님, 환영합니다!</li>
			  <li><a href="/logout">로그아웃</a></li>
			  
			  			<!-- typ값은 여기서 정해줬다! -->
			  <li><a href="/board/list?typ=1">게임</a></li>
			  <li><a href="/board/list?typ=2">스포츠</a></li>
			  <li><a href="/board/list?typ=3">연예/방송</a></li>
			</ul>
		</header>
		<section>
			<jsp:include page="${page}"/>
		</section>
		<footer>
		
		</footer>
	</div>
</body>
</html>