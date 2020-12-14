<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
	<div>
		<div>
			<form action="/join" method="post">
				<div><input type="text" name="user_id" placeholder="아이디"></div>
				<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
				<div><input type="password" name="user_pw_chk" placeholder="비밀번호 확인"></div>
				<div><input type="text" name="nm" placeholder="성함"></div>
				<div>
					성별 : 
					<label>여자<input type="radio" name="gender" value="0" checked></label>
					<label>남자<input type="radio" name="gender" value="1"></label>
				</div>
				<div>
					<input type="text" name="phone" placeholder="휴대폰 번호를 입력해주세요.">
				</div>
				<div><input type="submit" value="로그인"></div>
			</form>
			<a href="/login">로그인</a>
		</div>
	</div>
</body>
</html>