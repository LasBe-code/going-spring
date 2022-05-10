<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="default_width container-fluid">
	<div class="row">

		<div class="col-sm-2" id="memberinfo_left_nav">
			<a href="${pageContext.request.contextPath}/reservation/reservationList">
				예약내역<br> <br>
			</a>
			<a href="${pageContext.request.contextPath}/member/memberInfo">
				내정보관리<br> <br>
			</a>
			<a href="${pageContext.request.contextPath}/member/myReview">
				내가 작성한 리뷰<br> <br>
			</a>
			<a href="${pageContext.request.contextPath}/member/myWish">
				찜한 숙소<br> <br>
			</a>
		</div>

		<div class="col-sm-10 mt-3">
			<c:forEach var="bu" items="${businessList}">
				<div class="row wishlist-box">
					<div class="col-sm-3 wishlist-img rounded" style="background-image: url(${bu.location})"
					onclick="location.href='${pageContext.request.contextPath}/reservation/detail?bu_email=${bu.bu_email}&checkin=${today}&checkout=${tomorrow}'">
					</div>
					<div class="col-sm-7">
						<b class="large_text"> 
							<span class="badge bg-warning"> 
								<c:if test="${bu.bu_id == 1}">호텔</c:if> 
								<c:if test="${bu.bu_id == 2}">모텔</c:if> 
								<c:if test="${bu.bu_id == 3}">펜션</c:if> 
								<c:if test="${bu.bu_id == 4}">리조트</c:if>
							</span> ${bu.bu_title}
						</b>
						<br>
						<p class="gray_text" style="margin-bottom: 10px;">
						<img src="https://cdn4.iconfinder.com/data/icons/music-ui-solid-24px/24/location_map_marker_pin-2-512.png" style="width: 20px;"> 
							${bu.bu_address}
						</p>
					</div>
					<div class="col-sm-2" style="vertical-align: middle;">
						<button onclick="wishChange('${bu.bu_email}', ${bu.pic_num})" style="float:right; border:none;background:none;">
							<span class="material-icons like_icon" id="${bu.pic_num}"> 
								favorite 
							</span>
						</button>
					</div>
				</div>
			
			</c:forEach>
		</div>
	</div>
</div>
<script>
	function wishChange(bu_email, pic_num) {
		let likeCheck = document.getElementById(pic_num);
		let url;
		let msg;
		let heart;
		if(likeCheck.innerHTML.trim() == 'favorite_border'){
			url = '${pageContext.request.contextPath}/wish/insert'
			msg = '이 숙소를 찜했습니다'
			heart = 'favorite'
		} else {
			url = '${pageContext.request.contextPath}/wish/delete'
			msg = '찜을 취소했습니다'
			heart = 'favorite_border'
		}
		$.ajax({
			type:'POST',
			url:url,
			header:{"Content-Type":"application/json"},
			dateType:'json',
			data:{bu_email:bu_email},
			success : function(result){
				if(result == true){
				} else {
					likeCheck.innerHTML = heart;
					openToast(msg)
				}
			}
		})
	}
</script>
</body>
</html>