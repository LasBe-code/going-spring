<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/js/dateCheck.js"></script>
<script>
	function NoMultiChk(chk) {
		var obj = document.getElementsByName("bu_id");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i] != chk) {
				obj[i].checked = false;
			}
		}
	}
</script>
</head>

<body>
	<div class=default_width>
		<div class="search_bottom_box mt-3">
			
			<!-- 상세조건 사이드바 -->
			<div class="search_filter_box">
				<h3>상세조건</h3>
				<hr class="gray_line">
				<form action="${pageContext.request.contextPath}/search/search" method="get" name="f">
					<input type="hidden" name="bu_email" value="${bu.bu_email}">
					<div>
						<input type='date' id="checkin" min="${today}" value="${searchDTO.checkin }" class="main_checkin_1" name="checkin" onchange="dateChk()" style="padding-left:0px; width:100%;" required>
					</div>
					<div>
						<input type='date' id="checkout" min="${tomorrow }" value="${searchDTO.checkout }" class="main_checkout_1" name="checkout" onchange="dateChk()" style="padding-left:0px;border: none;width:100%;" required>
					</div>
					
					<hr class="gray_line">
					<strong class="search_strong_font">인원 수</strong>
					<input type="hidden" name="ro_count" value="${searchDTO.ro_count}">
					<select id="select_ro_count" class="form-select form-select-lg" onchange="change_ro_count()" style="border: none; padding-left:0px;">
						<option value="1" <c:if test="${searchDTO.ro_count == 1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${searchDTO.ro_count == 2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${searchDTO.ro_count == 3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${searchDTO.ro_count == 4}">selected</c:if>>4</option>
					</select>
					
					<hr class="gray_line">
					<strong class="search_strong_font">검색</strong> <br>
					<input type=search class=search_text placeholder=지역,숙소명 name="bu_address" value="${searchDTO.bu_address}" style="padding-left:0px;" required>
					
					<hr class="gray_line">
					<div class=search_button_box>
						<div></div>
					</div>
					<div class=search_select_box>
						<div>
							<strong class="search_strong_font">숙소 유형</strong>
							<ul class="search_detail_ul">
								<li><input type="checkbox" name="bu_id" value="1" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${searchDTO.bu_id == 1}">checked</c:if>> 
									<label class="search_label_font">호텔</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="2" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${searchDTO.bu_id == 2}">checked</c:if>> 
									<label class="search_label_font">모텔</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="3" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${searchDTO.bu_id == 3}">checked</c:if>> 
									<label class="search_label_font">펜션</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="4" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${searchDTO.bu_id == 4}">checked</c:if>> 
									<label class="search_label_font">리조트</label>
								</li>
							</ul>
						</div>
					</div>
					<div class="pricerangebox"> 
					<strong>가격 : </strong><p class="rangeresult"><fmt:formatNumber type="number" maxFractionDigits="0" value="${searchDTO.lowprice / 10000 }" /></p>만원 ~ 
					<p class="rangeresult2"><fmt:formatNumber type="number" maxFractionDigits="0" value="${searchDTO.highprice / 10000}" /></p>만원
					</div>
						<div class="middle">
							<div class="multi-range-slider">
								<input type="range" class="leftrangevalue" name="lowprice" id="input-left" min="10000" max="1500000" step="10000" value="${searchDTO.lowprice}">
								<input type="range" class="rightrangevalue" name="highprice" id="input-right" min="10000" max="1500000" step="10000" value="${searchDTO.highprice}">
								
								<div class="slider">
									<div class="track"></div>
									<div class="range"></div>
									<div class="thumb left"></div>
									<div class="thumb right"></div>
								</div>
							</div>
						</div>
						<div class="pricerangetextbox">
						<p class="pricerangetext">1만원</p><p class="pricerangetext2">150만원</p>
						</div>
					<button type=submit class=search_commit_button_size style="border: none;">적용</button>
				</form>
			</div>
			<!-- 상세조건 사이드바 끝 --> 
			
			<!-- 숙소 리스트 -->
			<div class="search_list_mainbox">
				<c:forEach var="bu" items="${bu_list}">
					<c:if test="${bu.minPrice != null}">
						<div class="search_list_box">
							<form action="${pageContext.request.contextPath}/reservation/detail" class="search_list_abox" method="get">
								<input type="hidden" name="bu_email" value="${bu.bu_email }">
								<input type="hidden" name="checkin" value="${searchDTO.checkin }">
								<input type="hidden" name="checkout" value="${searchDTO.checkout }">
								<input type="hidden" name="ro_count" value="${searchDTO.ro_count }">
								<div class="reserve_room" style="width: 690px; margin-top: 0px; padding-left: 385px;">
									<p class="reserve_pic_view" style="width: 330px;">
										<img src="${bu.picLocation}" class="rounded" style="width: 330px; height: 226px; object-fit: cover;">
									</p>
									<div style="margin: 0 auto"></div>
									
									<div class="search_room_title" style="width: 300px; margin-bottom: -2px;">
										<strong >${bu.bu_title}</strong>
										<p class="gray_text" style="font-size: 14px; margin-top:10px;">
											<img src="https://cdn4.iconfinder.com/data/icons/music-ui-solid-24px/24/location_map_marker_pin-2-512.png" style="width: 20px;"> ${bu.bu_address}
										</p>
									</div>
									<div class="reserve_room_price row" style="width: 300px;">
										<div class="col-sm-3">
											<strong class="medium_text">가격</strong>
										</div>
										<div class="col-sm-9 right_text">
											<b class="large_text"><fmt:formatNumber value="${bu.minPrice}" pattern="#,###" />원</b>
										</div>
									</div>
									<input type="submit" class="reserve_room_btn default_btn medium_text rounded" value="숙소 살펴보기" style="margin-left: -5px;">
								</div>
							</form>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<!-- 숙소 리스트 끝 -->
			
		</div>
	</div>
</body>
<script>
// 가격 검색 범위 설정 (바디 위에 쓸 경우 오류)
var inputLeft = document.getElementById("input-left");
var inputRight = document.getElementById("input-right");

var thumbLeft = document.querySelector(".thumb.left");
var thumbRight = document.querySelector(".thumb.right");
var range = document.querySelector(".range");

function setLeftValue() {
	var _this = inputLeft,
		min = parseInt(_this.min),
		max = parseInt(_this.max);
	_this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 50000);
	var percent = ((_this.value - min) / (max - min)) * 100
	
	thumbLeft.style.left = percent + "%";
	range.style.left = percent + "%";
}
setLeftValue();

function setRightValue() {
	var _this = inputRight,
		min = parseInt(_this.min),
		max = parseInt(_this.max);
	_this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 50000);
	var percent = ((_this.value - min) / (max - min)) * 100
	
	thumbRight.style.right = (100 - percent) + "%";
	range.style.right = (100 - percent) + "%";
}
setRightValue();

inputLeft.addEventListener("input", setLeftValue);
inputRight.addEventListener("input", setRightValue);
// 가격 검색 범위 설정 끝
// 검색 실시간 텍스트표시
var result = $(".rangeresult");
var slider = $(".leftrangevalue")
slider.on('input', function() {
    result.html( $(this).val() / 10000);
});
var result2 = $(".rangeresult2");
var slider2 = $(".rightrangevalue")
slider2.on('input', function() {
    result2.html( $(this).val() / 10000);
});
// 검색 실시간 텍스트 표시 끝
</script>
</html>