<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<b class="large_text"> <span class="badge bg-warning"> <c:if test="${bu.bu_id == 1}">호텔</c:if> <c:if test="${bu.bu_id == 2}">모텔</c:if> <c:if test="${bu.bu_id == 3}">펜션</c:if> <c:if test="${bu.bu_id == 4}">리조트</c:if>
			</span> ${bu.bu_title}
			</b>

			<p class="gray_text">
				<img src="https://cdn4.iconfinder.com/data/icons/music-ui-solid-24px/24/location_map_marker_pin-2-512.png" style="width: 20px;"> ${bu.bu_address}
			</p>
			<div class="event-bg text-white rounded">
				<ul>
					<li>대표 이름 : ${bu.bu_name}</li>
					<li>대표 전화번호 : ${bu.bu_tel}</li>
				</ul>
			</div>
		</div>
		<!-- 숙소 소개 끝 -->

		<div class="container mt-5">
			<ul class="nav nav-tabs nav-justified">
				<li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" style="color:black;" href="#room-list">객실안내/예약</a></li>
				<li class="nav-item"><a class="nav-link" data-bs-toggle="tab" style="color:black;" href="#review">리뷰</a></li>
			</ul>
		</div>

		<div class="tab-content">
			
			<!-- 방 목록 -->
			<div class="tab-pane active" id="room-list">
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
								<img src="${roomPicMap[ro_num]}" class="rounded" style="width: 376px; height: 226px; object-fit: cover;">
							</p>
							<strong class="reserve_room_title">${room.ro_name}</strong>
							<div class="reserve_room_price row">
								<div class="col-sm-6">
									<strong class="medium_text">가격</strong>
								</div>
								<div class="col-sm-6 right_text">
									<b class="large_text">${room.ro_price}원</b>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<button type="button" class="reserve_room_btn default_btn medium_text" onclick="roomDetail('${room.ro_num}', '${bu.bu_email}')">방 정보</button>
								</div>
								<div class="col-sm-6">
									<input type="submit" class="reserve_room_btn default_btn medium_text" 
										<c:if test="${roomMap[ro_num] == true}">disabled value="예약된 방"</c:if> 
										<c:if test="${roomMap[ro_num] == false}">value="예약"</c:if>
									>
								</div>
							</div>
						</div>
					</form>
				</c:forEach>
			</div>
			<!-- 방 목록 끝 -->
			
			<!-- 리뷰 -->
			<div class="tab-pane fade" id="review">
			
			</div>
			<!-- 리뷰 끝 -->
		</div>
	</div>
</div>
</body>
</html>