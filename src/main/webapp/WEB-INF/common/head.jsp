<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reservationList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberInfo.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
	<c:if test="${bu_email != null}">
		<div class="container" style="margin-top:100px">
			<nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 60%; margin:0px auto;">
				<div style="margin: 0px auto;">
					<ul class="navbar-nav">
						<c:forEach var="menu" items="${menu}">
							<li class="nav-item">
								<a class="nav-link" href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</nav>
		</div>
	</c:if>
	
	<c:if test="${param.msg != null}">
		<div class="position-fixed end-0 p-3">
			<div class="toast show" style="margin-bottom: -101px;">
				<div class="toast-header">
					<strong class="me-auto">알림 메시지</strong>
					<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
				</div>
				<div class="toast-body">
					<p>${param.msg}</p>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>