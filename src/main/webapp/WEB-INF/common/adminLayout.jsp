<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
	<c:if test="${param.msg != null}">
		<div class="position-fixed end-0 top-0" style="margin: 30px 30px 0 0;">
			<div class="toast show">
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

	<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<li class="sidebar-brand border-bottom">
				<a href="#" style="font-size: 25px; font-weight: 900 !important;">GOING ADMIN</a>
			</li>
			<li class="nav-menu">
				<a href="#">
					<span class="material-icons nav-icon"> assessment </span>
					Analytics
				</a>
				<!-- <ul class="sidebar-subnav">
					<li class="nav-menu">
						<a>월별 매출</a>
					</li>
					<li class="nav-menu">
						<a>지역별 매출</a>
					</li>
				</ul> -->
			</li>
			<li class="nav-menu">
				<a href="#">
					<span class="material-icons nav-icon"> how_to_reg </span>
					Business Approval
				</a>
			</li>
			<li class="nav-menu">
				<a href="#">
					<span class="material-icons nav-icon"> rate_review </span>
					Review Report
				</a>
			</li>
		</ul>
	</div>

</body>
</html>