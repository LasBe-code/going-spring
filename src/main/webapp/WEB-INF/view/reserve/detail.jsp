<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35d71e137b481a1c8d8befd339cf5e29&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/dateCheck.js"></script>
<script>
	function roomDetail(ro_num, bu_email) {
		const url = '${pageContext.request.contextPath}/reservation/roomDetail?ro_num='
				+ ro_num + '&bu_email=' + bu_email
		console.log(url + ', ' + ro_num)
		const op = 'width=550, height=1000, location=no, toolbar=no'

		open(url, '방 정보', op)
	}
</script>

</head>
<body>


	<div class="default_width mt-5">
		<div class="top row">
			<!-- 숙소 사진 목록 -->
			<div id="demo" class="carousel col-sm-6" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<c:forEach var="pic" items="${buPicList}" varStatus="i">
						<button type="button" data-bs-target="#demo" data-bs-slide-to="${i.index}" <c:if test="${i.index == 0}"> class="active"</c:if>></button>
					</c:forEach>
				</div>

				<div class="carousel-inner ">
					<c:forEach var="pic" items="${buPicList}" varStatus="i">
						<div class="carousel-item  <c:if test="${i.index == 0}">active</c:if>">
							<img class="top-img  rounded" src="${pic.location}" class="d-block" style="width: 490px">
						</div>
					</c:forEach>
				</div>

				<button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>
				</button>
			</div>
			<!-- 숙소 사진 목록 끝 -->

			<!-- 숙소 소개 -->
			<div class="col-sm-6">
				<b class="large_text"> 
					<span class="badge bg-warning"> 
						<c:if test="${bu.bu_id == 1}">호텔</c:if> 
						<c:if test="${bu.bu_id == 2}">모텔</c:if> 
						<c:if test="${bu.bu_id == 3}">펜션</c:if> 
						<c:if test="${bu.bu_id == 4}">리조트</c:if>
					</span> ${bu.bu_title}
				</b>
				
				<div class="mt-1 mb-1 bu_review_box">
					<li><span class="bu_review_star">⭐</span> <span class="bu_review_avg"> ${bu.avgScore}</span>
					<span class="bu_review_avg_sub">/5</span></li>
					<li><span class="bu_review_count">후기 <span style="color:red; font-size: 19px; font-weight: bold;">${bu.revCount}</span>개</span></li>
				</div>
				
				<p class="gray_text" style="margin-bottom: 10px;">
				<img src="https://cdn4.iconfinder.com/data/icons/music-ui-solid-24px/24/location_map_marker_pin-2-512.png" style="width: 20px;"> 
					${bu.bu_address}
				</p>
				
				<div class="event-bg text-white rounded">
					<ul>
						<li>대표 이름 : ${bu.bu_name}</li>
						<li>대표 전화번호 : ${bu.bu_tel}</li>
					</ul>
				</div>
				<!-- 지도 -->
				<div class="rounded" id="map" style="margin-top: 15px; height: 140px;"></div>
			</div>
			<!-- 숙소 소개 끝 -->

			<!-- 탭 -->
			<div class="container mt-5">
				<ul class="nav nav-tabs nav-justified">
					<li class="nav-item">
						<a class="nav-link active" data-bs-toggle="tab" style="color: black;" href="#room-list">객실안내/예약</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-bs-toggle="tab" style="color: black;" href="#review">리뷰</a>
					</li>
				</ul>
			</div>
			<!-- 탭  끝 -->

			<div class="tab-content">

				<!-- 방 목록 -->
				<div class="tab-pane active" id="room-list">
					<!-- 체크인 체크아웃 재설정 -->
					<form action="${pageContext.request.contextPath}/reservation/detail" name="f" method="get">
						<div class="row" style="text-align: center; margin:0 auto; margin-bottom: -12px;">
							<input type="hidden" name="ro_count" value="${ro_count}">
							<input type="hidden" name="bu_email" value="${bu.bu_email}">
							<div class="col-sm-5" style="border-left: 1px solid rgba(0,0,0,0.08);">
								<input type='date' id="checkin" min="${today }" value="${checkin }" class="detail_checkin_1" name="checkin" onchange="dateChk()" required>
							</div>
							<div class="col-sm-5" style="border-left: 1px solid rgba(0,0,0,0.08);">
								<input type='date' id="checkout" min="${tomorrow }" value="${checkout }" class="detail_checkin_1" name="checkout" onchange="dateChk()" style="border: none;" required>
							</div>
							<div class="col-sm-2" style="border-right: 1px solid rgba(0,0,0,0.08); padding:0 0 0 0 !important;">
								<input type="submit" class="default_btn" value="날짜 변경">
							</div>
						</div>
					</form>
					<!-- 체크인 체크아웃 재설정 끝 -->
					
					<c:forEach var="room" items="${roomList}">
						<form action="${pageContext.request.contextPath}/reservation/reserve" method="get">
							<input type="hidden" name="bu_title" value="${bu.bu_title}">
							<input type="hidden" name="ro_count" value="${ro_count}">
							<input type="hidden" name="checkin" value="${checkin}">
							<input type="hidden" name="checkout" value="${checkout}">
							<input type="hidden" name="ro_name" value="${room.ro_name}">
							<input type="hidden" name="price" value="${room.ro_price}">
							<c:set var="ro_num" value="${room.ro_num}" />
							<input type="hidden" name="ro_num" value="${ro_num}">

							<div class="reserve_room">
								<p class="reserve_pic_view">
									<img src="${room.location}" class="rounded" style="width: 376px; height: 226px; object-fit: cover;">
								</p>
								<span class="reserve_room_title large_text">${room.ro_name}</span>
								<div class="gray_subtext right_text">
									<span>최대 ${room.ro_count}인</span> <br>
									<span>입실 : ${room.checkin} / </span>
									<span> 퇴실 : ${room.checkout}</span>
								</div>
								
								<div class="reserve_room_price row">
									<div class="col-sm-6">
										<strong class="medium_text">가격</strong>
									</div>
									<div class="col-sm-6 right_text">
										<span>1박 기준 &nbsp</span>
										<b class="large_text"><fmt:formatNumber value="${room.ro_price}" pattern="#,###" />원</b>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<button type="button" class="reserve_room_btn default_btn medium_text" onclick="roomDetail('${room.ro_num}', '${bu.bu_email}')">방 정보</button>
									</div>
									<div class="col-sm-6">
										<input type="submit" class="reserve_room_btn default_btn medium_text" <c:if test="${room.overlap != 0}">disabled value="예약된 방"</c:if> <c:if test="${room.overlap == 0}">value="예약"</c:if>>
									</div>
								</div>
							</div>
						</form>
					</c:forEach>
				</div>
				<!-- 방 목록 끝 -->

				<div class="tab-pane fade" id="review">
					
					<!-- 리뷰 -->
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
							</div>
						</div>
						<hr style="border: 1px solid #dadada; margin:0 40px 0 40px; ">
						<!-- 리뷰 끝 -->
					</c:forEach>
				</div>
			</div>
			<!-- 리뷰 끝 -->
		</div>
	</div>
<script type="text/javascript">
//지도 스크립트
//상세조건의 값을 가져옵니다
//지도를 생성합니다    
const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
 mapOption = {
     center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
     level: 4 // 지도의 확대 레벨
 };  

//지도를 생성합니다    
const map = new kakao.maps.Map(mapContainer, mapOption); 
const address = '${bu.bu_address}';
//주소-좌표 변환 객체를 생성합니다
const geocoder = new kakao.maps.services.Geocoder();
//주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

 // 정상적으로 검색이 완료됐으면 
  if (status === kakao.maps.services.Status.OK) {

	  const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

     // 결과값으로 받은 위치를 마커로 표시합니다
     const marker = new kakao.maps.Marker({
         map: map,
         position: coords
     });

     // 인포윈도우로 장소에 대한 설명을 표시합니다
     const infowindow = new kakao.maps.InfoWindow({
         content: '<div style="width:150px;text-align:center;padding:6px 0;">${bu.bu_title}</div>'
     });
     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
     map.setCenter(coords);
     infowindow.open(map, marker);
 } 
});
</script>
</body>
</html>