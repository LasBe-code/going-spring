<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
    .wrap {left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>
<body>
<div class="container" style="margin-top:100px">
  <nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 60%; margin:0px auto;">
  	<div style="margin: 0px auto;">
	  <ul class="navbar-nav">
	  	<li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomInsert">객실 등록</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomlist">객실 정보</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/reservation">예약 확인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/sales">매출</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/map">지도</a>
	    </li>
	  </ul>
	  </div>
</nav>
<div class="default_width">
	<div class="search_bottom_box mt-3" style="float: left; width: 15%;">
	
		<div class="search_filter_box">
		<h3>상세조건</h3>
		<form action="${pageContext.request.contextPath}/search/search" method="get" name="f">
		<input type="hidden" id="bu_email" name="bu_email" value="${bu.bu_email}">
		<input type="hidden" name="ro_count" value="${ro_count}">
		<div>
			<input type='date' id="checkin" class="main_checkin_1" name="checkin" onchange="dateChk()" required>
		</div>
		<div>
			<input type='date' id="checkout" class="main_checkout_1" name="checkout" onchange="dateChk()" style="border:none;" required>
		</div>
		<input type="hidden" id="ro_count" name="ro_count2" value="2"> <!-- select 저장용 -->
		<select id="select_ro_count" class="form-select form-select-lg" style="border: none;">
			<option value="1" onclick="document.getElementById('bu_id').value='1'">1</option>
			<option value="2" selected  onclick="document.getElementById('bu_id').value='2'">2</option>
			<option value="3" onclick="document.getElementById('bu_id').value='3'">3</option>
			<option value="4" onclick="document.getElementById('bu_id').value='4'">4</option>
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
						<input type="hidden" id="bu_email" name="bu_email" value="${bu.bu_email}">
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
<div id="map" style="height:600px; float: left; width: 50%; margin-left: 120px; margin-top: 15px;"></div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35d71e137b481a1c8d8befd339cf5e29&libraries=services"></script>
<script type="text/javascript">
let checkin
let checkout
// 상세조건 유효성 체크 스크립트
function dateChk(){ // 날짜 유효성 체크
	const today = new Date();   
	const year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	if(month<10) month = '0'+month
	let date = today.getDate();  // 날짜
	if(date<10) date = '0'+date
	const day=year+''+month+''+date
	console.log(day)
	
	checkin = document.f.checkin.value
	checkout = document.f.checkout.value
	
	checkin = checkin.replace('-',	'')
	checkin = checkin.replace('-',	'')
	checkout = checkout.replace('-', '')
	checkout = checkout.replace('-', '')
	
	console.log(checkin)
	console.log(checkout)
	
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
	const obj = document.getElementsByName("bu_id");
	for(let i=0; i<obj.length; i++) {
		if(obj[i] != chk) {
			obj[i].checked = false;
		}
	}
}

// 지도 스크립트
// 상세조건의 값을 가져옵니다


//지도를 생성합니다    
const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.5094362, 127.043265), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    }; 
const map = new kakao.maps.Map(mapContainer, mapOption); 

//마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
const infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 지도에 출력할 주소
const resultAddress = ${resultAddress}
// 지도에 출력할 타이틀
const roomTitle = ${roomTitle}
// 지도에 출력할 사진
const roomPic = ${roomPic}
const bu_email = ${bu_email}
for (let i = 0; i < resultAddress.length; i++) {
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(resultAddress[i], function(result, status) {
		// 정상적으로 검색이 완료됐으면 
		 if (status === kakao.maps.services.Status.OK) {
			 const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			const marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});
			let content = 
			        '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title">' + 
		            				roomTitle[i]+ 
		            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
		            '        </div>' + 
		            '        <div class="body">' + 
		            '            <div class="img">' +
		            '                <img src="'+roomPic[i]+'" width="73" height="70">' +
		            '           </div>' + 
		            '            <div class="desc">' + 
		            '                <div class="ellipsis">'+resultAddress[i]+'</div>' + 
		            '                <div><a href="${pageContext.request.contextPath}/reservation/detail?bu_email='+bu_email[i]+'&checkin='+checkin+'&checkout='+checkout+'&ro_count='+ro_count+'" target="_blank" class="link">객실 보러가기</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		            
		    // 마커에 클릭이벤트를 등록합니다
		    kakao.maps.event.addListener(marker, 'click', function() {
		        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
		        let ro_count = document.f.ro_count2.value
		        content = 
			        '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title">' + 
		            				roomTitle[i]+ 
		            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
		            '        </div>' + 
		            '        <div class="body">' + 
		            '            <div class="img">' +
		            '                <img src="'+roomPic[i]+'" width="73" height="70">' +
		            '           </div>' + 
		            '            <div class="desc">' + 
		            '                <div class="ellipsis">'+resultAddress[i]+'</div>' + 
		            '                <div><a href="${pageContext.request.contextPath}/reservation/detail?bu_email='+bu_email[i]+'&checkin='+checkin+'&checkout='+checkout+'&ro_count='+ro_count+'" target="_blank" class="link">객실 보러가기</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>'
		   		infowindow.setContent(content)
		        infowindow.open(map, marker);
		    });
		} 

	});   
}
//커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
	infowindow.close();     
}

        
 
</script>
</body>
</html>