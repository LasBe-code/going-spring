<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/reservationList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
tr {
	width: 320px !important;
}

td {
	width: 160px !important;
}

table {
	width: 100% !important;
}
</style>
</head>
<script>
	function deleteDetail() {

		if (confirm('예약내역을 삭제 하시겠습니까?') == true) {
			alert('예약내역이 삭제 되었습니다.')
			location.href = "${pageContext.request.contextPath}/member/deletePro";
		} else {
			return;
		}
	}
</script>

<body style="width: 400px;">
	<div style="margin: 0 auto; margin-left: 40px; margin-top: 30px; text-align: center;">
		<c:choose>
			<c:when test="${bookingDetail.status == 1 }">
				<h3 class="badge bg-warning">예약완료</h3>
			</c:when>
			<c:when test="${bookingDetail.status == 2 }">
				<h3 class="badge bg-danger">취소완료</h3>
			</c:when>
			<c:otherwise>
				<h3 class="badge bg-secondary">이용완료</h3>
			</c:otherwise>
		</c:choose>

		<span class="reserveMember_room_title">${bookingDetail.bu_title}</span>
		<span class="reserveMember_room_name">${bookingDetail.ro_name}</span>
		
		<hr class="gray_line mt-5 mb-5">
	</div>

	<div style="margin: 0 auto; margin-left: 40px; text-align: left;">
		<div>
			<div class="reservationDetail_medium_text mt-3 mb-3">
				<h4><strong>객실기본정보</strong></h4>
			</div>
			<table class="table table-borderless reservationDetail_small_text">
				<tr>
					<td>
						<strong>체크인</strong>
					</td>
					<td>${bookingDetail.checkin}</td>
				</tr>
				<tr>
					<td>
						<strong>체크아웃</strong>
					</td>
					<td>${bookingDetail.checkout}</td>
				</tr>
				<tr>
					<td>
						<strong>예약번호</strong>
					</td>
					<td>${bookingDetail.bo_num}</td>
				</tr>
				<tr>
					<td>
						<strong>예약자 이름</strong>
					</td>
					<td>${member.name}</td>
				</tr>
				<tr>
					<td>
						<strong>전화번호</strong>
					</td>
					<td>${member.tel}</td>
				</tr>
			</table>

			<hr class="gray_line mt-5">

			<div class="reservationDetail_medium_text mt-5 mb-3">
				<h4><strong>결제 정보</strong></h4>
			</div>
			<table class="table table-borderless reservationDetail_small_text">
				<tr>
					<td>
						<strong>결제 수단</strong>
					</td>
					<td>${bookingDetail.payment}</td>
				</tr>

				<c:choose>
					<c:when test="${bookingDetail.status == 2 }">
						<tr>
							<td>
								<strong>환불 금액</strong>
							</td>
							<td>${bookingDetail.price}원</td>
						</tr>
					</c:when>

					<c:otherwise>
						<tr>
							<td>
								<strong>결제 금액</strong>
							</td>
							<td>${bookingDetail.price}원</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="mb-5"></div>
		</div>
	</div>
</body>
</html>