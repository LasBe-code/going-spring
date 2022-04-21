<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property='title' /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reservationList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberInfo.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script>
function change_ro_count(){
	const id = document.getElementById("select_ro_count");
	const value = id.options[id.selectedIndex].value;
	document.f.ro_count.value = value
}
function dateChk(){ // 날짜 유효성 체크
	const today = new Date();   
	const year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	if(month<10) month = '0'+month
	let date = today.getDate();  // 날짜
	if(date<10) date = '0'+date
	const day=year+''+month+''+date
	console.log(day)
	
	let checkin = document.f.checkin.value
	let checkout = document.f.checkout.value
	
	checkin = checkin.replace('-',	'')
	checkin = checkin.replace('-',	'')
	checkout = checkout.replace('-', '')
	checkout = checkout.replace('-', '')
	
	if(checkin != '' && checkout != ''){
		if(checkin >= checkout){
			alert('최소 1박 2일의 일정을 선택해주세요')
			document.f.checkout.value = null
		}
	}
}
</script>
</head>
<body>
	<nav class="header navbar navbar-expand-sm bg-warning navbar-dark" style="margin-bottom: 0px !important;">
      <div class="default_width container-fluid ">
        	
        <c:choose>
        	<c:when test="${bu_email != null}"> <!-- 사업자 로그인 -->
        		<img alt="logo" src="${pageContext.request.contextPath}/resources/image/logo.png" style="width:120px;"
        		onclick="location.href='${pageContext.request.contextPath}/room/roomlist'">
        	</c:when>
        	<c:otherwise> <!-- 그 외 -->
        		<img alt="logo" src="${pageContext.request.contextPath}/resources/image/logo.png" style="width:120px;"
        		onclick="location.href='${pageContext.request.contextPath}/search/main'">
        	</c:otherwise>
        </c:choose>
        
        <ul class="navbar-nav  justify-content-end">
        	<c:if test="${email != null}"> <!-- 고객 로그인 -->
        		<li class="nav-item">
		           <a class="nav-link" href="${pageContext.request.contextPath}/member/memberInfo">내 정보</a>
		        </li>
		        <li class="nav-item">
		           <a class="nav-link" href="${pageContext.request.contextPath}/reservation/reservationList">예약 내역</a>
		        </li>
		        <li class="nav-item">
		           <a class="nav-link" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		        </li>
        	</c:if>
        	<c:if test="${bu_email != null}"> <!-- 사업자 로그인 -->
        		<li class="nav-item">
		           <a class="nav-link" href="${pageContext.request.contextPath}/member/buInfo">사업자 정보</a>
		        </li>
        		<li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/room/reservation">예약내역</a>
		        </li>
		        <li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		        </li>
        	</c:if>
          <c:if test="${bu_email == null && email == null}"> <!-- 비로그인 -->
          	<li class="nav-item">  
            	<a class="nav-link" href="${pageContext.request.contextPath}/member/loginForm">예약내역</a>
            </li>
            <li class="nav-item">
            	<a class="nav-link" href="${pageContext.request.contextPath}/member/loginForm">로그인</a>
            </li>
          </c:if>
        </ul>
      </div>
    </nav>
 
	<sitemesh:write property='body' />
</body>
</html>