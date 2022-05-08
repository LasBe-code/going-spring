<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container" style="margin-top:100px">
	<div>
		<c:forEach var="review" items="${reviewList}">
			<div id="review-box" style="padding: 20px 60px 20px 60px;">
				<div style="color:#c8c8c8; font-size: 14px">
					<div class="row">
					<c:choose>
						<c:when test="${review.report == '0' }">
							<div id="reportApproval">
								<a style="font-size: 30px; background-color: white; border-radius: 100px; cursor: pointer;" onclick='reportApproval(${review.email}, ${review.rev_num})'>🚨</a>
							</div>
						</c:when>
						<c:when test="${review.report == '1' }">
							<div id="reportCancle">
								<a style="font-size: 30px; background-color: red; border-radius: 100px; cursor: pointer;" onclick='reportCancle(${review.email}, ${review.rev_num})'>🚨</a>
							</div>
						</c:when>
					</c:choose>
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
					<c:if test="${review.content_reply ne null}">
						<br><br><span>&nbsp;&nbsp;ㄴ&nbsp;${review.content_reply}</span>
					</c:if>
				</div>
				<div class="reply" style="margin-top: 20px;">
					<div class="reply_input">
						<form action="${pageContext.request.contextPath}/room/roomReview">
							<input type="hidden" name="rev_num" value="${review.rev_num}">
							<textarea name="content_reply" rows="2" style="width: 90%;"></textarea>
							<c:choose>
								<c:when test="${review.content_reply == null}">
									<input class="default_btn rounded mt-1 btnAdd" type="submit" value="댓글작성" 
									 style="float: right; height:30px; width:80px; align-content: center;"/>
								 </c:when>
								 <c:when test="${review.content_reply != null}">
									 <div style="float: right;">
										 <input class="default_btn rounded mt-1 btnAdd" type="submit" value="수정" 
											 style=" height:30px; width:50px;"/>
										 <input class="default_btn rounded mt-1 btnAdd" type="button"
										 onclick="location.href='${pageContext.request.contextPath}/room/roomReviewDelete?rev_num=${review.rev_num}'" 
										 value="삭제" style=" height:30px; width:50px;"/>
									 </div>
								 </c:when>
							</c:choose>
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
function reportApproval(email, rev_num){
	const confirm1 = confirm(email+'님의 리뷰를 신고하시겠습니까??')
	if(confirm1){
		location.href = '${pageContext.request.contextPath}/room/reviewApproval?rev_num='+rev_num
		alert('신고가 완료되었습니다.')
	}
}

function reportCancle(email, rev_num){
	const confirm1 = confirm(email+'님의 리뷰 신고를 취소하시겠습니까??')
	if(confirm1){
		location.href = '${pageContext.request.contextPath}/room/reportCancle?rev_num='+rev_num
		alert('신고가 취소되었습니다.')
	}
}
</script>
</body>
</html>