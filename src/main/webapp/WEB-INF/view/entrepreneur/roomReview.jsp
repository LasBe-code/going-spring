<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<div class="container" style="margin-top:100px">
<nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 60%; margin:0px auto;">
  	<div style="margin: 0px auto;">
	  <ul class="navbar-nav">
		<li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomInsert">객실 등록</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomlist">객실 정보</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/reservation">예약 확인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/sales">매출</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/areaSales">지역별 월매출</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/todayCheckin">체크인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/todayCheckOut">체크아웃</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomReview">리뷰</a>
	    </li>
	  </ul>
	  </div>
</nav>
	<div>
		<c:forEach var="review" items="${reviewList}">
			<div id="review-box" style="padding: 20px 60px 20px 60px;">
				<div style="color:#c8c8c8; font-size: 14px">
					<div class="row">
						<div class="col-sm-6 review_star">
							<c:choose>
								<c:when test="${review.score == '1' }">⭐</c:when>
								<c:when test="${review.score == '2' }">⭐ ⭐</c:when>
								<c:when test="${review.score == '3' }">⭐ ⭐ ⭐</c:when>
								<c:when test="${review.score == '4' }">⭐ ⭐ ⭐ ⭐</c:when>
								<c:when test="${review.score == '5' }">⭐ ⭐ ⭐ ⭐ ⭐</c:when>
							</c:choose>	
						</div>
						<div class="col-sm-6 review_email" style="text-align: right;">
							<span>
							${fn:substring(review.review_date,0,4)} -
							${fn:substring(review.review_date,4,6)} -
							${fn:substring(review.review_date,6,8)}</span>
						</div>
					</div>
	
					<div class="review_email">
						<span>${review.email}</span> - <span>${review.ro_name}</span>
					</div>
				</div>
				
				<div class="review_content mt-1">
					<span>${review.content}</span>
					<%-- <span>${review.content_reply}</span> --%>
				</div>
				<div class="reply" style="margin-top: 20px;">
					<div class="reply_input">
						<form action="${pageContext.request.contextPath}/room/roomReview">
							<textarea name="content_reply" rows="2" style="width: 90%;"></textarea>
							<input class="default_btn rounded mt-1 btnAdd" type="submit" value="댓글작성" 
							 style="float: right; height:30px; width:80px;"/>
						</form>
					</div>
					<div class="reply_text">
					</div>
				</div>
			</div>
			<hr style="border: 1px solid #dadada; margin:0 40px 0 40px; ">
			<!-- 리뷰 끝 -->
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
$(document).ready (function () {                
    $('.btnAdd').on('click',function () {                                        
    }); // end click                                            
}); // end ready  

</script>
</body>
</html>