<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
function change_ro_count(){
	const id = document.getElementById("select_ro_count");
	const value = id.options[id.selectedIndex].value;
	document.f.ro_count.value = value
}
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
</script>
<body>
	<div class=main_first_box>
		<div>
		<form action="${pageContext.request.contextPath}/search/search" name="f" method="get">
			<div class=main_twice_box>
				<ul class="nav nav-tabs nav-justified">
				  <li class="nav-item">
				    <a class="nav-link active" data-bs-toggle="tab" style="color:black; cursor:pointer"
				    	onclick="document.getElementById('bu_id').value='1'">호텔</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" data-bs-toggle="tab" style="color:black; cursor:pointer"
				    	onclick="document.getElementById('bu_id').value='2'">모텔</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" data-bs-toggle="tab" style="color:black; cursor:pointer"
				    	onclick="document.getElementById('bu_id').value='3'">펜션</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" data-bs-toggle="tab" style="color:black; cursor:pointer"
				    	onclick="document.getElementById('bu_id').value='4'">리조트</a>
				  </li>
				</ul>
				<input type="hidden" name="bu_id" id="bu_id" value="1">
				<div class="main_search_all" style="padding-top:10px;">
					<div class=main_checkinout>
						<div role=button>
							<div class="main_checkinout_buttonbox">
								<div>
									<input type='date' id="checkin" min="today" class="main_checkin_1" name="checkin" onchange="dateChk()" required>
								</div>
								<div>
									<input type='date' id="checkout" class="main_checkout_1" name="checkout" onchange="dateChk()" required>
								</div>
							</div>
						</div>
					</div>
					<div class=main_count_box>
						<div role="button" style="border-left: 1px solid #c8c8c8;">
							<input type="hidden" name="ro_count" value="2"> <!-- select 저장용 -->
							<select id="select_ro_count" class="form-select form-select-lg" onchange="change_ro_count()" style="border: none;">
							     <option value="1">1</option>
							     <option value="2" selected>2</option>
							     <option value="3">3</option>
							     <option value="4">4</option>
						   	</select>
						</div>
					</div>
					<div style="border-left: 1px solid #c8c8c8;">
					<input type=search class=main_search_text placeholder=지역,숙소명 name="bu_address" required>
					</div>
				<div class=main_search_button_box>
				<button type=submit class="main_search_button">검색</button>
				</div>
				</div>
			</div>
		</form>
		</div>
	</div>
	<div class="main_categorybox">
		<div class=main_floor>
		<div class="main_categoryone">
			<h3 class="medium_text mt-1" style="color:dark gray; font-weight: 300 !important;">호텔</h3>
			<a href="${pageContext.request.contextPath}/search/search?bu_id=1&ro_count=2">
			<img class="main_image_size rounded" src=https://pix10.agoda.net/hotelImages/5442478/0/59658259652dae9c04f3229b87fdedd4.jpg?ca=23&ce=0&s=1024x768>
			</a>
			
		</div>
		<div class="main_categoryone">
			<h3 class="medium_text mt-1" style="color:dark gray; font-weight: 300 !important;">모텔</h3>
			<a href="${pageContext.request.contextPath}/search/search?bu_id=2&ro_count=2">
			<img class="main_image_size rounded" src=https://i.travelapi.com/hotels/17000000/16730000/16727700/16727640/42a70b89_z.jpg>
			</a>
		</div>
		</div>
		<div class=main_floor>
		<div class="main_categoryone">
			<h3 class="medium_text mt-1" style="color:dark gray; font-weight: 300 !important;">펜션</h3>
			<a href="${pageContext.request.contextPath}/search/search?bu_id=3&ro_count=2">
			<img class="main_image_size rounded" src=https://uploads-ssl.webflow.com/5e5cad32512f4ebf86ae2fa1/5e942f6e5f867827a4659114_mrp_6140-hdr.jpeg>
			</a>
		</div>
		<div class="main_categoryone">
			<h3 class="medium_text mt-1" style="color:dark gray; font-weight: 300 !important;">리조트</h3>
			<a href="${pageContext.request.contextPath}/search/search?bu_id=4&ro_count=2">
			<img class="main_image_size rounded" src=https://images.chosun.com/resizer/z171wP8hn0uIczVRiOpLA2t8pRI=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/7UICRHCEMBBJNKPPY263O3AMEY.jpg>
			</a>
		</div>
		</div>
	</div>
</body>
</html>