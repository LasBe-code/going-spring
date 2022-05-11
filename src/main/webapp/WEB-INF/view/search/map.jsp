<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
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
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
</head>
<body>

<div class="default_width row" style="margin-bottom:200px;">
	<div class="search_bottom_box col-sm-4 mt-3">
	
		<div class="search_filter_box">
		<h3>상세조건</h3>
		<form action="${pageContext.request.contextPath}/search/map" method="get" name="f">
		<input type="hidden" id="ro_count" name="ro_count" value="${searchDTO.ro_count}">
		<div>
			<input type='date' id="checkin" class="main_checkin_1" name="checkin" value="${searchDTO.checkin}" onchange="dateChk()" required>
		</div>
		<div>
			<input type='date' id="checkout" class="main_checkout_1" name="checkout" value="${searchDTO.checkout}" onchange="dateChk()" style="border:none;" required>
		</div>
		<hr class="gray_line">
		<strong class="search_strong_font">인원 수</strong>
		<select id="selectRo_count" class="form-select form-select-lg" style="border: none;" >
			<option value="none">인원수 선택</option>
			<option value="1" ${searchDTO.ro_count == '1' ? 'selected' : '' }>1</option>
			<option value="2" ${searchDTO.ro_count == '2' ? 'selected' : '' }>2</option>
			<option value="3" ${searchDTO.ro_count == '3' ? 'selected' : '' }>3</option>
			<option value="4" ${searchDTO.ro_count == '4' ? 'selected' : '' }>4</option>
		</select>
		
		<hr class="gray_line">
		<strong class="search_strong_font">검색</strong> <br>
		<input type=search class=search_text placeholder=지역,숙소명 name="bu_address" id="bu_address" value="${searchDTO.bu_address}" required>
		<div class=search_button_box>
			<div>
			</div>
		</div>
		<div class=search_select_box>
		
		<hr class="gray_line">
		
		<div>
			
			
			<strong class="search_strong_font">숙소 유형</strong>
				<ul class="search_detail_ul">
					<li>
						<input type="checkbox" id="bu_id" name="bu_id" value="1" onclick="NoMultiChk(this)" 
						style=accent-color:#ffc107 ${searchDTO.bu_id == '1' ? 'checked' : '' }>
						<label class="search_label_font">호텔</label>
					</li>
					<li>
						<input type="checkbox" id="bu_id" name="bu_id" value="2" onclick="NoMultiChk(this)" 
						style=accent-color:#ffc107 ${searchDTO.bu_id == '2' ? 'checked' : '' }>
						<label class="search_label_font">모텔</label>
					</li >
					<li>
						<input type="checkbox" id="bu_id" name="bu_id" value="3" onclick="NoMultiChk(this)" 
						style=accent-color:#ffc107 ${searchDTO.bu_id == '3' ? 'checked' : '' }>
						<label class="search_label_font">펜션</label>
					</li>
					<li>
						<input type="checkbox" id="bu_id" name="bu_id" value="4" onclick="NoMultiChk(this)" 
						style=accent-color:#ffc107 ${searchDTO.bu_id == '4' ? 'checked' : '' }>
						<label class="search_label_font">리조트</label>
					</li>
				</ul>
			</div>
			</div>
			<button type=submit class=search_commit_button_size style="border:none;">적용</button>
		</form>
		</div>
	</div>
	<div class="col-sm-10 rounded" id="map" style="height:700px; width:670px; margin-top: 15px; margin-right:0;"></div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35d71e137b481a1c8d8befd339cf5e29&libraries=services"></script>
<script type="text/javascript">


// select box에서 변경시 input태그로 값을 넘긴다
$(document).ready(function(){
	const inputTag = document.querySelector('#ro_count');  // id가 inputRo_count인 태그를 선택
    $('#selectRo_count').change(function(){
    	const selectRo_count = $('#selectRo_count').val(); //id가 searchName인 select box의 값 추출하여 저장
        
        inputTag.value = selectRo_count
        
    });
    const checkin ='';
    const checkout ='';
    const ro_count = '';
    
	if(document.querySelector('#checkout') == null ||document.querySelector('#checkin') == null || document.querySelector('#ro_count') == null){
		 checkin = '';
		 checkout = '';
		 ro_count = '';
	}
	else{
		 checkin  = document.querySelector('#checkin').val;
		 checkout = document.querySelector('#checkout').val;
		 ro_count = document.querySelector('#ro_count').val;
	}
    
    
});


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
        level: 6 // 지도의 확대 레벨
    }; 
const map = new kakao.maps.Map(mapContainer, mapOption); 

// 지도에 출력할 주소
const resultAddress = ${resultAddress}
// 지도에 출력할 타이틀
const roomTitle = ${roomTitle}
// 지도에 출력할 사진
const roomPic = ${roomPic}
const bu_email = ${business_email}


//지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
var bounds = new kakao.maps.LatLngBounds();


for (let i = 0; i < resultAddress.length; i++) {
	// 주소-좌표 변환 객체를 생성합니다
	const geocoder = new kakao.maps.services.Geocoder();
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(resultAddress[i], function(result, status) {
		// 정상적으로 검색이 완료됐으면 
		 if (status === kakao.maps.services.Status.OK) {
			 const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			 
			// LatLngBounds 객체에 좌표를 추가합니다
		        bounds.extend(coords);
			 
			// 결과값으로 받은 위치를 마커로 표시합니다
			const marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});
			let content = '';
			
	     //마커 위에 커스텀오버레이를 표시합니다
	     // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	     var overlay = new kakao.maps.CustomOverlay({
			         position: marker.getPosition()       
			     });
			
			
			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'click', function() {
				
				content = 
				'<div class="wrap">' + 
		        '    <div class="info">' + 
		        '        <div class="title">' + 
		        				roomTitle[i]+ 
		        '        </div>' + 
		        '        <div class="body">' + 
		        '            <div class="img">' +
		        '                <img src="'+roomPic[i]+'" width="73" height="70">' +
		        '           </div>' + 
		        '            <div class="desc">' + 
		        '                <div class="ellipsis">'+resultAddress[i]+'</div>' + 
		        '                <div><a href="${pageContext.request.contextPath}/reservation/detail?bu_email='+bu_email[i]+'&checkin=${checkin}&checkout=${checkout}&ro_count=${ro_count}" target="_self" class="link">객실 보러가기</a></div>' + 
		        '            </div>' + 
		        '        </div>' + 
		        '    </div>' +    
		        '</div>'
				
				overlay.setContent(content);
			    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			    if(overlay.getMap() == null)
			    	overlay.setMap(map);
			    else
			    	overlay.setMap(null);
			    
			    map.setCenter(coords);
			    
			});
			map.setBounds(bounds);
		} 
	});   
}

</script>
<div style="margin : 300px 0 0 0;"></div>
</body>
</html>