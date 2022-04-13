<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/memberInfo.css">
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/ajax.js"></script>
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
    <a href="${pageContext.request.contextPath}/member/buInfo">내정보<br><br></a>
    <a href="${pageContext.request.contextPath}/member/buUpdateForm">정보수정<br><br></a>
    </div>
    <div class="col-sm-8">
      <p class="memberInfo_large_text"><b>내 정보 관리</b></p>
  <div>
    <p class="memberInfo_defalut_btn rounded" >이메일</p>
    <p class="memberInfo_default_text">${mem.bu_email}</p>
  </div>
  
   <br><br>
  <div>
  <p class="memberInfo_defalut_btn rounded" >이름</p><p class="memberInfo_default_text">${mem.bu_name}</p>
  </div>
  
   <br><br>
   <div>
  <p class="memberInfo_defalut_btn rounded" >주소</p><p class="memberInfo_default_text">${mem.bu_address}</p>
  </div>
  
   <br><br>  
  <div>
  <p class="memberInfo_defalut_btn rounded" >전화번호</p><p class="memberInfo_default_text">${mem.bu_tel }</p>
  <p class="memberInfo_small_text">개인 정보 보호를 위해 내 정보는 모두 안전하게 암호화됩니다.</p>
 
  </div>
  
  <br>
  <hr>
  <br>
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