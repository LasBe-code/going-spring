<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/reservationList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>

<style type="text/css">
#myform fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	border: 0; /* 필드셋 테두리 제거 */
}

#myform input[type=radio] {
	display: none; /* 라디오박스 감춤 */
}

#myform label {
	font-size: 3em; /* 이모지 크기 */
	color: transparent; /* 기존 이모지 컬러 제거 */
	text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}

#myform label:hover {
	text-shadow: 0 0 0 #ffda6a; /* 마우스 호버 */
}

#myform label:hover ~ label {
	text-shadow: 0 0 0 #ffda6a; /* 마우스 호버 뒤에오는 이모지들 */
}

#myform fieldset {
	display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	direction: rtl; /* 이모지 순서 반전 */
	border: 0; /* 필드셋 테두리 제거 */
}

#myform fieldset legend {
	text-align: left;
}

#myform input[type=radio]:checked ~ label {
	text-shadow: 0 0 0 #ffc107; /* 마우스 클릭 체크 */
}
</style>
</head>
<body>
<div class="mt-3" style="margin:0 auto; text-align: center; width:400px; height:440px;">
	<form name="myform" id="myform" method="post" action="${pageContext.request.contextPath}/reservation/reviewPro">
		<input type="hidden" name="bo_num" value="${booking.bo_num}">
		
		<div>
			<span class="large_text">${booking.bu_title}</span> <br>
			<span class="medium_text">${booking.ro_name}</span> <br>
		</div>
	
		<fieldset>
			<input type="radio" name="score" value="5" id="rate1">
			<label for="rate1">⭐</label>
			<input type="radio" name="score" value="4" id="rate2">
			<label for="rate2">⭐</label>
			<input type="radio" name="score" value="3" id="rate3">
			<label for="rate3">⭐</label>
			<input type="radio" name="score" value="2" id="rate4">
			<label for="rate4">⭐</label>
			<input type="radio" name="score" value="1" id="rate5">
			<label for="rate5">⭐</label>
		</fieldset>
		 
		<textarea class="small_text" name="content" style="width:100%; height: 200px; border-color: #dadada; padding: 10px 10px 10px 10px" placeholder="리뷰를 작성해주세요."></textarea>
		<input class="default_btn" type="submit">
	</form>
</div>
</body>
</html>