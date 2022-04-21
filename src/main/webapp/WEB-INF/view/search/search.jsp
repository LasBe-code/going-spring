<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
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
						<input type='date' id="checkin" min="${today}" value="${checkin }" class="main_checkin_1" name="checkin" onchange="dateChk()" style="padding-left:0px; width:100%;" required>
					</div>
					<div>
						<input type='date' id="checkout" min="${tomorrow }" value="${checkout }" class="main_checkout_1" name="checkout" onchange="dateChk()" style="padding-left:0px;border: none;width:100%;" required>
					</div>
					
					<hr class="gray_line">
					<strong class="search_strong_font">인원 수</strong>
					<input type="hidden" name="ro_count" value="${ro_count}">
					<select id="select_ro_count" class="form-select form-select-lg" onchange="change_ro_count()" style="border: none; padding-left:0px;">
						<option value="1" <c:if test="${ro_count == 1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${ro_count == 2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${ro_count == 3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${ro_count == 4}">selected</c:if>>4</option>
					</select>
					
					<hr class="gray_line">
					<strong class="search_strong_font">검색</strong> <br>
					<input type=search class=search_text placeholder=지역,숙소명 name="bu_address" value="${search}" style="padding-left:0px;" required>
					
					<hr class="gray_line">
					<div class=search_button_box>
						<div></div>
					</div>
					<div class=search_select_box>
						<div>
							<strong class="search_strong_font">숙소 유형</strong>
							<ul class="search_detail_ul">
								<li><input type="checkbox" name="bu_id" value="1" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${bu_id == 1}">checked</c:if>> 
									<label class="search_label_font">호텔</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="2" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${bu_id == 2}">checked</c:if>> 
									<label class="search_label_font">모텔</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="3" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${bu_id == 3}">checked</c:if>> 
									<label class="search_label_font">펜션</label>
								</li>
								<li><input type="checkbox" name="bu_id" value="4" onclick="NoMultiChk(this)" 
									style="accent-color: #ffc107" <c:if test="${bu_id == 4}">checked</c:if>> 
									<label class="search_label_font">리조트</label>
								</li>
							</ul>
						</div>
					</div>
					<button type=submit class=search_commit_button_size style="border: none;">적용</button>
				</form>
			</div>
			<!-- 상세조건 사이드바 끝 --> 
			
			<!-- 숙소 리스트 -->
			<div class="search_list_mainbox">
				<c:forEach var="bu" items="${bu_list}">
					<div class="search_list_box">
						<form action="${pageContext.request.contextPath}/reservation/detail" class="search_list_abox" method="get">
							<input type="hidden" name="bu_email" value="${bu.bu_email }">
							<input type="hidden" name="checkin" value="${checkin }">
							<input type="hidden" name="checkout" value="${checkout }">
							<input type="hidden" name="ro_count" value="${ro_count }">
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
									<div class="col-sm-6">
										<strong class="medium_text">가격</strong>
									</div>
									<div class="col-sm-6 right_text">
										<b class="large_text"><fmt:formatNumber value="${bu.minPrice}" pattern="#,###" />원</b>
									</div>
								</div>
								<input type="submit" class="reserve_room_btn default_btn medium_text rounded" value="숙소 살펴보기" style="margin-left: -5px;">
							</div>
						</form>
					</div>
				</c:forEach>
			</div>
			<!-- 숙소 리스트 끝 -->
			
		</div>
	</div>
</body>
</html>