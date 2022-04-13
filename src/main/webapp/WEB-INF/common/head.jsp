<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/common/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/reservationList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
	
	<nav class="header navbar navbar-expand-sm bg-warning navbar-dark" style="margin-bottom: 0px !important;">
      <div class="default_width container-fluid ">
        	
        <c:choose>
        	<c:when test="${bu_email != null}"> <!-- 사업자 로그인 -->
        		<img alt="logo" src="${pageContext.request.contextPath}/image/logo.png" style="width:120px;"
        		onclick="location.href='${pageContext.request.contextPath}/room/roomlist'">
        	</c:when>
        	<c:otherwise> <!-- 그 외 -->
        		<img alt="logo" src="${pageContext.request.contextPath}/image/logo.png" style="width:120px;"
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
 

</body>
</html>