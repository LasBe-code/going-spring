<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
function requestPay() {
  IMP.init('imp49486608'); //iamport 대신 자신의 "가맹점 식별코드"를 사용
  IMP.request_pay({
    pg: "inicis",
    pay_method: "card",
    merchant_uid : 'merchant_'+new Date().getTime(),
    name : 'Going 결제',
    amount : ${booking.price},
    buyer_email : '${email}',
    buyer_name : '${member.name}',
    buyer_tel : '${member.tel}',
    buyer_postcode : '123-456',
    m_redirect_url : 'http://localhost:8080/reservation/reservePro'
  }, function (rsp) { // callback
      if (rsp.success) {
    	  $.ajax({
	        	type : "GET",
	        	url : "${pageContext.request.contextPath}/reservation/reservePro?bo_num=" + rsp.imp_uid + "&payment=" + rsp.pay_method
	        }).done(function(data){
	        	location.href='${pageContext.request.contextPath}/reservation/reservationList'
	        })
      } else {
     	alert('결제 실패')
      }
  });
}

function chk(){
	const check1 = document.querySelector('#check1')
	const check2 = document.querySelector('#check2')
	const check3 = document.querySelector('#check3')
	const btn = document.querySelector('#btn')
	
	if(check1.checked == true && check2.checked == true && check3.checked == true) {
		btn.disabled = false
	} else {
		btn.disabled = true
	}
}
</script>
<style>
button:disabled {
    background-color: -internal-light-dark(rgba(239, 239, 239, 0.3), rgba(19, 1, 1, 0.3)) !important;
    color: -internal-light-dark(rgba(16, 16, 16, 0.3), rgba(255, 255, 255, 0.3));
    border-color: -internal-light-dark(rgba(118, 118, 118, 0.3), rgba(195, 195, 195, 0.3));
}
</style>
</head>
<body>

<div class="default_width mt-5">
	<div class="row" style="margin: 0 auto !important;">
	  <div class="col-sm-8 reserve_left">
	    <b class="large_text">예약자 정보</b>
	      <div class="mt-5"><strong class="user_profile">예약자 이름</strong></div>
	      <input type="text" class="form-control form-control-lg mt-3" value="${member.name}" readonly>
	
	      <div class="mt-5">
	        <strong class="user_profile">휴대폰 번호</strong> <br>
	        <input type="text" class="form-control form-control-lg mt-3" value="${member.tel}" readonly>
	      </div>
	
	      <!-- <div class="mt-5">
	        <strong class="user_profile">결제방식</strong> <br>
	        <select class="form-select form-select-lg mt-3" style="width: 200px;">
	          <option>카카오페이</option>
	          <option>네이버페이</option>
	          <option>신용카드</option>
	        </select>
	      </div> -->
	
	      <div class="mt-5" onclick="chk()">
	        <div class="form-check">
	          <input type="checkbox" class="form-check-input" id="check1" name="option1" value="something" required>
	          <label class="form-check-label" for="check1">숙소이용규칙 및 취소/환불규정 동의</label>
	        </div>
	        <div class="form-check">
	          <input type="checkbox" class="form-check-input" id="check2" name="option2" value="something" required>
	          <label class="form-check-label" for="check2">개인정보 수집 및 이용 동의</label>
	        </div>
	        <div class="form-check">
	          <input type="checkbox" class="form-check-input" id="check3" name="option2" value="something" required>
	          <label class="form-check-label" for="check2">개인정보 제 3자 제공 동의</label>
	        </div>
	        
	      </div>
	  </div>
	
	  <div class="col-sm-4 reserve rounded">
	    <div class="reserve_body">
	      <p class="reserve_info">
	        <strong class="reserve_name">숙소이름</strong> <br>
	        ${booking.bu_title}
	      </p>
	      <p class="reserve_info">
	        <strong class="reserve_name">객실타입</strong> <br>
	        ${booking.ro_name }
	      </p>
	      <p class="reserve_info">
	        <strong class="reserve_name">체크인</strong> <br>
	        ${booking.checkin}
	      </p>
	      <p class="reserve_info">
	        <strong class="reserve_name">체크아웃</strong> <br>
	        ${booking.checkout}
	      </p>
	    </div>
	
	    <div class="reserve_body">
	      <strong class="reserve_name" style="color: black;">총 결제 금액</strong> <br>
	      <strong class="reserve_name" style="color: #ffc107; font-size: 30px;">${booking.price}</strong><strong class="reserve_name" style="color: black; font-size: 30px;"> 원</strong> <br>
	    </div>
	    <button class="btn" type="button" onclick="requestPay()" id="btn" disabled
	    	style="width: 100%; height: 56px; background: #ffc107; color: white; border: none;">결제하기</button>
	  </div>
	  
	</div>
</div>
</body>
</html>