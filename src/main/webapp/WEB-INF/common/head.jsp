<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reservationList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberInfo.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<style type="text/css">
.header .nav-link {
	color : #c8c8c8 !important;
}
.header .nav-link:hover {
	color : #ffc107 !important;
}
.header-image:hover {
	opacity: 0.6;
}

</style>
</head>
<body>
	<nav class="header navbar navbar-expand-sm navbar-dark" style="margin-bottom: 0px !important; box-shadow: 0px 2px 3px 0px rgb(0 0 0 / 20%);">
      <div class="default_width container-fluid ">
        	
        <c:choose>
        	<c:when test="${bu_email != null}"> <!-- 사업자 로그인 -->
        		<img class="header-image" alt="logo" src="${pageContext.request.contextPath}/resources/image/colorlogo.png" style="width:120px;"
        		onclick="location.href='${pageContext.request.contextPath}/room/roomlist'">
        	</c:when>
        	<c:otherwise> <!-- 그 외 -->
        		<img class="header-image" alt="logo" src="${pageContext.request.contextPath}/resources/image/colorlogo.png" style="width:120px;"
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
		<div class="container" style="margin-top:25px">
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
	
	
</body>
</html>