<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/memberInfo.css">
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/ajax.js"></script>
</head>
<script>
function logout() {
	
	if (confirm('로그아웃 하시겠습니까?') == true) {
		alert('로그아웃 되었습니다.')
		location.href = "${pageContext.request.contextPath}/view/search/main.jsp";
	} else {
		return;
	}
}
</script>
<body>
<div class="default_width container-fluid">
  <div class="row">
    <div class="col-sm-2" id="memberinfo_left_nav" >
    <a href="${pageContext.request.contextPath}/reservation/reservationList">예약내역<br><br></a>
    <a href="${pageContext.request.contextPath}/member/memberInfo">내정보관리<br><br></a>
    </div>
    
    
    <div class="col-sm-8">
      <p class="memberInfo_large_text"><b>내 정보 관리</b></p>
  <div class="mt-3"> 
    <p class="memberInfo_defalut_btn rounded" >이메일</p>
    <p class="memberInfo_default_text">${mem.email}</p>
  </div>
  
  <div class="mt-3">
  <p class="memberInfo_defalut_btn rounded" >이름</p><p class="memberInfo_default_text">${mem.name }</p>
  </div>
  
  <div class="mt-3">
  <p class="memberInfo_defalut_btn rounded" >전화번호</p><p class="memberInfo_default_text">${mem.tel }</p>
 
  </div>
  
  <hr>
  <div id="memberinfo_bottom_text">
  <p class="memberInfo_small_text"> GOING을 이용하고 싶지 않으신가요?&nbsp;&nbsp;
  <a href="#" onclick="logout()">로그아웃</a>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <a href="${pageContext.request.contextPath}/view/search/main.jsp">회원탈퇴</a>
 
  </div>
  
  </div>
</div>
</div>

</body>
</html>