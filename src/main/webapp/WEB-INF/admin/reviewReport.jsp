<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 리뷰 -->
<div class="admin-box">
	<h2>리뷰 신고 관리</h2>
	<hr>
	<div>
		<c:forEach var="review" items="${reviewList}">
			<div id="review-box" style="padding: 20px 20px 20px 20px; margin:0 auto;
				width:60%; border : 1px solid rgba(0, 0, 0, 0.1); border-radius: 5px;">
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
						<span>No : ${review.rev_num}</span> <br> 
						<span>작성자 ID : ${review.email}</span> <br> 
						<span>업체명 : ${review.bu_title}</span>
					</div>
				</div>
				
				<hr style="color: rgba(0, 0, 0, 0.5);">
				
				<div class="review_content mt-1">
					<span>${review.content}</span>
					<c:if test="${review.content_reply ne null}">
						<br><br>&nbsp;&nbsp;<span class="material-icons">subdirectory_arrow_right</span>
						${review.content_reply}
					</c:if>
				</div>
				<hr style="color: rgba(0, 0, 0, 0.5);">
				<button type="button" onclick="reviewDelete(${review.rev_num})" id="${review.rev_num}" class="btn btn-outline-danger">삭제</button>
				<button type="button" onclick="cancel(${review.rev_num})" id="${review.rev_num}Cancel" class="btn btn-outline-success">유지</button>
			</div>
			<!-- 리뷰 끝 -->
		</c:forEach>
	</div>
</div>

<script>
function reviewDelete(rev_num){
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/admin/reviewDelete',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{rev_num:rev_num},
		success : function(result){
			if(result == true){
				alert('실패했습니다.')
			} else {
				const btn1 = document.getElementById(rev_num)
				const btn2 = document.getElementById(rev_num+"Cancel")
				btn1.disabled = true
				btn2.disabled = true
				
				btn1.style.backgroundColor = "red"
				btn1.style.color = "white"
				
				openToast("[Review No : "+rev_num+"] "+"리뷰를 삭제했습니다")
			}
		}
	})
}
function cancel(rev_num){
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/admin/reportCancel',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{rev_num:rev_num},
		success : function(result){
			if(result == true){
				alert('실패했습니다.')
			} else {
				const btn1 = document.getElementById(rev_num)
				const btn2 = document.getElementById(rev_num+"Cancel")
				btn1.disabled = true
				btn2.disabled = true
				
				btn2.style.backgroundColor = "green"
				btn2.style.color = "white"
				
				openToast("[Review No : "+rev_num+"] "+"신고를 반려합니다")
			}
		}
	})
}
</script>

</body>
</html>