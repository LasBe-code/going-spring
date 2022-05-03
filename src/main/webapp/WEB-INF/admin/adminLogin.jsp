<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login_form_width" style="padding:50px 10px 20px 10px; margin-top:100px;">
		<div class="large_text active" style="text-align: center;">
			<img alt="logo" src="${pageContext.request.contextPath}/resources/image/colorlogo.png" style="width: 200px;"><br>
		</div>
			
		<div id="business-login" class="container mt-5">
			<form action="${pageContext.request.contextPath}/admin/adminLoginPro" method="post">
				
				<input type="text" name="email" class="form-control form-control-lg mt-3" placeholder="이메일"> 
				<input type="password" name="password" class="form-control form-control-lg mt-1" placeholder="비밀번호"> 
				<input type="submit" class="default_btn rounded mt-3" value="로그인">
			</form>
		</div>
	</div>
</body>
</html>