<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/reservationList.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>

<script>
	function cancel(bo_num) {
		var flag = confirm('예약을 취소하시겠습니까?');
		if (flag) {
			location.href = "${pageContext.request.contextPath}/reservation/cancel?bo_num="
					+ bo_num;
		} else {
			return;
		}
	}
	
	function bookingDetail(bo_num) {
		const url = '${pageContext.request.contextPath}/reservation/reservationDetail?flag=true&bo_num='
				+ bo_num
		const op = 'width=450, height=730, location=no, toolbar=no'

		open(url, '예약정보', op)
	}
	
	function review(bo_num) {
		const url = '${pageContext.request.contextPath}/reservation/review?bo_num='
				+ bo_num
		const op = 'width=450, height=500, location=no, toolbar=no'

		popup=open(url, '리뷰 작성', op)
		
		popup.addEventListener('beforeunload', function() {
			  	setTimeout(() => {
					window.location.reload();
			  	}, 1000)
			});
	}
	
</script>

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
					검색 기간 정할 수 있도록 수정
			</div>

			<div class="col-sm-10 mt-5">
				<ul class="nav nav-tabs nav-justified">
					<li class="nav-item">
						<a class="nav-link active" data-bs-toggle="tab" href="#status_1" style="color: black;">예약완료</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-bs-toggle="tab" href="#status_2" style="color: black;">예약취소</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-bs-toggle="tab" href="#status_3" style="color: black;">이용완료</a>
					</li>
				</ul>

				<div class="tab-content">


					<!-- 예약완료 -->
					<div class="tab-pane active" id="status_1">
						<c:forEach var="booking" items="${bookingList}" varStatus="s">
							<c:if test="${booking.status == 1}">
								<div class="reserveMember_room">
									<p class="reserveMember_pic_view">
										<img src="${booking.location}" class="reserveMember_pic rounded">
									</p>
									<span class="reserveMember_room_title">${booking.bu_title}</span>
									<span class="reserveMember_room_name">${booking.ro_name}</span>

									<div class="mt-2">
										<span class="reserveMember_room_date"> ${fn:substring(booking.checkin,0,4)}. ${fn:substring(booking.checkin,4,6)}. ${fn:substring(booking.checkin,6,8)} ~ ${fn:substring(booking.checkout,0,4)}. ${fn:substring(booking.checkout,4,6)}. ${fn:substring(booking.checkout,6,8)} </span>
									</div>

									<hr class="gray_line">

									<div class="mt-2">
										<span class="reserveMember_room_name">
											<b><fmt:formatNumber value="${booking.price}" pattern="#,###" />원</b>
										</span>
										<span class="reserveMember_room_date mt-2"> ${fn:substring(booking.reg_date,0,4)}년 ${fn:substring(booking.reg_date,4,6)}월 ${fn:substring(booking.reg_date,6,8)}일 ㆍ ${booking.payment} </span>
									</div>

									<div class="row">
										<div class="col-sm-6 center_box">
											<button type="button" class="reserveMember_room_btn default_btn medium_text rounded" onclick="bookingDetail(${booking.bo_num})">예약 정보</button>
										</div>
										<div class="col-sm-6 center_box">
											<button type="button" onclick="cancel('${booking.bo_num}')" class="reserveMember_room_btn default_btn medium_text rounded" style="background-color: red;">예약 취소</button>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<!-- 예약완료 끝 -->

					<!-- 이용완료 -->
					<div class="tab-pane fade" id="status_2">
						<c:forEach var="booking" items="${bookingList}" varStatus="s">
							<c:if test="${booking.status == 2}">
								<div class="reserveMember_room">
									<p class="reserveMember_pic_view">
										<img src="${booking.location}" class="reserveMember_pic rounded">
									</p>
									<span class="reserveMember_room_title">${booking.bu_title}</span>
									<span class="reserveMember_room_name">${booking.ro_name}</span>

									<div class="mt-2">
										<span class="reserveMember_room_date"> ${fn:substring(booking.checkin,0,4)}. ${fn:substring(booking.checkin,4,6)}. ${fn:substring(booking.checkin,6,8)} ~ ${fn:substring(booking.checkout,0,4)}. ${fn:substring(booking.checkout,4,6)}. ${fn:substring(booking.checkout,6,8)} </span>
									</div>

									<hr class="gray_line">

									<div class="mt-2">
										<span class="reserveMember_room_name">
											<b><fmt:formatNumber value="${booking.price}" pattern="#,###" />원</b>
										</span>
										<span class="reserveMember_room_date mt-2"> ${fn:substring(booking.reg_date,0,4)}년 ${fn:substring(booking.reg_date,4,6)}월 ${fn:substring(booking.reg_date,6,8)}일 ㆍ ${booking.payment} </span>
									</div>

									<div class="center_box">
										<button type="button" class="reserveMember_room_btn default_btn medium_text rounded" onclick="bookingDetail(${booking.bo_num})">예약 정보</button>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<!-- 이용완료 끝 -->

					<!-- 예약취소 -->
					<div class="tab-pane fade" id="status_3">
						<c:forEach var="booking" items="${bookingList}" varStatus="s">
							<c:if test="${booking.status == 3}">
								<div class="reserveMember_room">
									<p class="reserveMember_pic_view">
										<img src="${booking.location}" class="reserveMember_pic rounded">
									</p>
									<span class="reserveMember_room_title">${booking.bu_title}</span>
									<span class="reserveMember_room_name">${booking.ro_name}</span>

									<div class="mt-2">
										<span class="reserveMember_room_date"> ${fn:substring(booking.checkin,0,4)}. ${fn:substring(booking.checkin,4,6)}. ${fn:substring(booking.checkin,6,8)} ~ ${fn:substring(booking.checkout,0,4)}. ${fn:substring(booking.checkout,4,6)}. ${fn:substring(booking.checkout,6,8)} </span>
									</div>

									<hr class="gray_line">

									<div class="mt-2">
										<span class="reserveMember_room_name">
											<b><fmt:formatNumber value="${booking.price}" pattern="#,###" />원</b>
										</span>
										<span class="reserveMember_room_date mt-2"> ${fn:substring(booking.reg_date,0,4)}년 ${fn:substring(booking.reg_date,4,6)}월 ${fn:substring(booking.reg_date,6,8)}일 ㆍ ${booking.payment} </span>
									</div>

									<div class="row">
										<div class="col-sm-6 center_box">
											<button type="button" class="reserveMember_room_btn default_btn medium_text rounded" onclick="bookingDetail(${booking.bo_num})">예약 정보</button>
										</div>
										<div class="col-sm-6 center_box">
											<c:choose>
												<c:when test="${booking.revNum != 0}">
													<button type="button"
														class="reserveMember_room_btn default_btn medium_text rounded" disabled>
														후기 작성 완료</button>
												</c:when>
												
												<c:otherwise>
													<button type="button" onclick="review('${booking.bo_num}')" 
														class="reserveMember_room_btn default_btn medium_text rounded">
														후기 작성</button>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<!-- 예약취소 끝 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html> --%>