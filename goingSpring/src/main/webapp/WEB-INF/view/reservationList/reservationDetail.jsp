<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/memberInfo.css">
<style>
tr {
	width:300px !important;
}
td {
	width:300px !important;
}
table{
	width:600px !important;
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

<body>
<div class="default_width ">
  <div class="row">
    <div class="col-sm-2" id="memberinfo_left_nav" >
	    <a href="${pageContext.request.contextPath}/reservation/reservationList">예약내역<br><br></a>
	    <a href="${pageContext.request.contextPath}/member/memberInfo">내정보관리<br><br></a>
    </div>
    <div class="col-sm-10">
	    <c:choose>
	    	<c:when test="${bookingDetail.status == 1 }">
	    		<p class="reservationDetail_head">예약완료</p>
	    	</c:when>
	    	<c:when test="${bookingDetail.status == 2 }">
	    		<p class="reservationDetail_head" style="background-color: red;">취소완료</p>
	    	</c:when>
	    	<c:otherwise>
	    		<p class="reservationDetail_head">이용완료</p>
	    	</c:otherwise>
	    </c:choose>
    	
    	<p class="reservationDetail_medium_text"><b>${bookingDetail.bu_title}</b></p>
      	<p class="reservationDetail_small_text" >${bookingDetail.ro_name}<b>ㆍ</b>  </p><br>
    	
    	<div class="mb-3" style="">
			<div class="reservationDetail_medium_text mt-3 mb-3"><strong>객실기본정보</strong></div>
			<table class="table table-borderless reservationDetail_small_text">
				<tr>
					<td><strong>체크인</strong></td>
					<td>${bookingDetail.checkin}</td>
				</tr>
				<tr>
					<td><strong>체크아웃</strong></td>
					<td>${bookingDetail.checkout}</td>
				</tr>
				<tr>
					<td><strong>예약번호</strong></td>
					<td>${bookingDetail.bo_num}</td>
				</tr>
				<tr>
					<td><strong>예약자 이름</strong></td>
					<td>${member.name}</td>
				</tr>
				<tr>
					<td><strong>전화번호</strong></td>
					<td>${member.tel}</td>
				</tr>
			</table>
			
			<div class="reservationDetail_medium_text mt-5 mb-3"><strong>결제 정보</strong></div>
			<table class="table table-borderless reservationDetail_small_text">
				<tr>
					<td><strong>결제 수단</strong></td>
					<td>${bookingDetail.payment}</td>
				</tr>
				
				<c:choose>
					<c:when test="${bookingDetail.status == 2 }">
						<tr>
							<td><strong>환불 금액</strong></td>
							<td>${bookingDetail.price}원</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<tr>
							<td><strong>결제 금액</strong></td>
							<td>${bookingDetail.price}원</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="mt-5">
				<button class = "default_btn rounded" type="button"  style="width:80px; height:30px;"
       				onclick="location.href='${pageContext.request.contextPath}/reservation/reservationList'">확인</button>
			</div>
		</div>
       <br>
       <br>
       
       
       
</div>
</div>
</div>

</body>
</html>