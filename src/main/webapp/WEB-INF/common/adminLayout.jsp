<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">

</head>
<body>
	
	<div id="sidebar-wrapper" style="position: fixed; z-index: 10;">
		<ul class="sidebar-nav">
			<li class="sidebar-brand border-bottom">
				<a href="#" style="font-size: 25px; font-weight: 900 !important;">GOING ADMIN</a>
			</li>
			<li class="nav-menu">
				<a href="${pageContext.request.contextPath}/admin/monthlySales">
					<span class="material-icons nav-icon"> assessment </span>
					월별 매출
				</a>
			</li>
			<li class="nav-menu">
				<a href="${pageContext.request.contextPath}/admin/areaSales">
					<span class="material-icons nav-icon"> map </span>
					지역별 매출
				</a>
			</li>
			<li class="nav-menu">
				<a href="${pageContext.request.contextPath}/admin/categorySales">
					<span class="material-icons nav-icon"> business </span>
					숙소 카테고리별 매출
				</a>
			</li>
			<li class="nav-menu">
				<a href="${pageContext.request.contextPath}/admin/businessApproval">
					<span class="material-icons nav-icon"> how_to_reg </span>
					사업자 가입 승인
				</a>
			</li>
			<li class="nav-menu">
				<a href="${pageContext.request.contextPath}/admin/reviewReport">
					<span class="material-icons nav-icon"> rate_review </span>
					리뷰 신고 관리
				</a>
			</li>
		</ul>
	</div>

</body>
</html>