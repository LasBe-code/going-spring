<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class = "roominfo_div_box" style="margin-top: 100px;">
<form action="${pageContext.request.contextPath}/room/roomDeletePro" method="post">
	<div class="mb-3 mt-3">
	      <label>비밀번호</label>
	      <input type="text" class="form-control"  name="pwd">
	      <input type="hidden" name = "ro_num" value="${ro_num}">
	</div>
	<input type="submit" class="default_btn rounded mt-1" value="객실삭제" onclick="location.href='${pageContext.request.contextPath}/room/roomDeletePro?ro_num=${ro_num}'">
	</form>
</div>
</body>
</html>