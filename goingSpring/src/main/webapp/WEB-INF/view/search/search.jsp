<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/common/style.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<script>
function dateChk(){ // 날짜 유효성 체크
	const today = new Date();   
	const year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	if(month<10) month = '0'+month
	let date = today.getDate();  // 날짜
	if(date<10) date = '0'+date
	const day=year+''+month+''+date
	console.log(day)
	
	let checkin = document.f.checkin.value
	console.log(checkin)
	let checkout = document.f.checkout.value
	
	checkin = checkin.replace('-',	'')
	checkin = checkin.replace('-',	'')
	checkout = checkout.replace('-', '')
	checkout = checkout.replace('-', '')
	
	console.log(checkin)
	
	if(checkin != '' && checkin < day){
		alert('지난 날짜 선택')
		document.f.checkin.value = null
	}
	if(checkout != '' && checkout < day){
		alert('지난 날짜 선택')
		document.f.checkout.value = null
	}
	if(checkin != '' && checkout != ''){
		if(checkin >= checkout){
			alert('최소 1박 2일의 일정을 선택해주세요')
			document.f.checkout.value = null
		}
	}
}
function NoMultiChk(chk) {
	var obj = document.getElementsByName("bu_id");
	for(var i=0; i<obj.length; i++) {
		if(obj[i] != chk) {
			obj[i].checked = false;
		}
	}
}
</script>
<body>
<div class=default_width>
	<div class="search_bottom_box mt-3">
	
		<div class="search_filter_box">
		<h3>상세조건</h3>
		<form action="${pageContext.request.contextPath}/search/search" method="get" name="f">
		<input type="hidden" name="bu_email" value="${bu.bu_email}">
		<input type="hidden" name="ro_count" value="${ro_count}">
		<div>
			<input type='date' id="checkin" class="main_checkin_1" name="checkin" onchange="dateChk()" required>
		</div>
		<div>
			<input type='date' id="checkout" class="main_checkout_1" name="checkout" onchange="dateChk()" style="border:none;" required>
		</div>
		<input type="hidden" name="ro_count" value="2"> <!-- select 저장용 -->
							<select id="select_ro_count" class="form-select form-select-lg" onchange="change_ro_count()" style="border: none;">
							     <option value="1">1</option>
							     <option value="2" selected>2</option>
							     <option value="3">3</option>
							     <option value="4">4</option>
						   	</select>
		<input type=search class=search_text placeholder=지역,숙소명 name="bu_address" required>
			<div class=search_button_box>
				<div>
				</div>
			</div>
			<div class=search_select_box>
			<div>
			<strong class="search_strong_font">숙소 유형</strong>
			<ul class="search_detail_ul">
			<li>
			<input type="checkbox" name="bu_id" value="1" onclick="NoMultiChk(this)" style=accent-color:#ffc107>
			<label class="search_label_font">호텔</label>
			</li>
			<li>
			<input type="checkbox" name="bu_id" value="2" onclick="NoMultiChk(this)" style=accent-color:#ffc107>
			<label class="search_label_font">모텔</label>
			</li >
			<li>
			<input type="checkbox" name="bu_id" value="3" onclick="NoMultiChk(this)" style=accent-color:#ffc107>
			<label class="search_label_font">펜션</label>
			</li>
			<li>
			<input type="checkbox" name="bu_id" value="4" onclick="NoMultiChk(this)" style=accent-color:#ffc107>
			<label class="search_label_font">리조트</label>
			</li>
			</ul>
			</div>
			</div>
			<button type=submit class=search_commit_button_size style="border:none;">적용</button>
		</form>
		</div>
		<div class="search_list_mainbox">
			<c:forEach var="bu" items="${bu_list}">
				<div class="search_list_box">
					<form action="${pageContext.request.contextPath}/reservation/detail" class="search_list_abox" method="get">
						<input type="hidden" name="bu_email" value="${bu.bu_email }">
						<input type="hidden" name="checkin" value="${checkin }">
						<input type="hidden" name="checkout" value="${checkout }">
						<input type="hidden" name="ro_count" value="${ro_count }">
						<div class="reserve_room" style="width:690px; margin-top: 0px; padding-left:385px;">
						  <p class="reserve_pic_view" style="width:330px;">
						    <img src="${picMap[bu.pic_num] }" class="rounded" style="width:330px; height:226px; object-fit: cover;">
						  </p>
						  <div style="margin:0 auto">
						  </div>
						  <strong class="reserve_room_title" style="width:300px;">${bu.bu_title}</strong>
						  <div class="reserve_room_price row" style="width:300px;">
						    <div class="col-sm-6">
						      <strong class="medium_text">가격</strong>
						    </div>
						    <div class="col-sm-6 right_text">
						      <b class="large_text">${minPriceMap[bu.pic_num] }원</b>
						    </div>
						  </div>
				  		  <input type="submit" class="reserve_room_btn default_btn medium_text rounded" value="숙소 살펴보기" style="margin-left:-5px;">
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>