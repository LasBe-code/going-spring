<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dateCheck.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		var mySwiper = new Swiper('.swiper-container', {
			slidesPerView : 3,
			slidesPerGroup : 3,
			observer : true,
			observeParents : true,
			spaceBetween : 24,
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
			breakpoints : {
				1280 : {
					slidesPerView : 3,
					slidesPerGroup : 3,
				},
				720 : {
					slidesPerView : 1,
					slidesPerGroup : 1,
				}
			}
		});
	});
</script>
</head>
<body>

	<div style="background-color: #f5f5f5">

		<!-- 상단 검색 창 -->
		<div class=main_first_box>
			<form action="${pageContext.request.contextPath}/search/search" name="f" method="get">
				<div class=main_twice_box>

					<!-- 숙소 종류 선택 탭 -->
					<ul class="nav nav-tabs nav-justified">
						<li class="nav-item">
							<a class="nav-link active" data-bs-toggle="tab" style="color: black; cursor: pointer" onclick="document.getElementById('bu_id').value='1'">호텔</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" data-bs-toggle="tab" style="color: black; cursor: pointer" onclick="document.getElementById('bu_id').value='2'">모텔</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" data-bs-toggle="tab" style="color: black; cursor: pointer" onclick="document.getElementById('bu_id').value='3'">펜션</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" data-bs-toggle="tab" style="color: black; cursor: pointer" onclick="document.getElementById('bu_id').value='4'">리조트</a>
						</li>
					</ul>
					<!-- 숙소 종류 선택 탭 -->

					<input type="hidden" name="bu_id" id="bu_id" value="1">
					<div class="main_search_all" style="padding-top: 10px;">
						<div class=main_checkinout>
							<div role=button>
								<div class="main_checkinout_buttonbox">
									<div>
										<input type='date' id="checkin" min="${today }" value="${today }" class="main_checkin_1" name="checkin" onchange="dateChk()" required>
									</div>
									<div>
										<input type='date' id="checkout" min="${tomorrow }" value="${tomorrow }" class="main_checkout_1" name="checkout" onchange="dateChk()" required>
									</div>
								</div>
							</div>
						</div>
						<div class=main_count_box>
							<div role="button" style="border-left: 1px solid #c8c8c8;">
								<input type="hidden" name="ro_count" value="2">
								<!-- select 저장용 -->
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
						<div class="main_search_button_box">
							<button type=submit style="border-left: 1px solid #c8c8c8;" class="btn main_search_button">GOING</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- 상단 검색 창 끝 -->

		<!-- 숙소 종류별 즉시 검색 기능 -->
		<div class="main_box rounded mt-3">
			<h4 class="main_head_text">⚡️ 클릭해서 원하는 숙소를 찾아보세요!</h4>
			<div class="row">
				<div class="col-sm-3 main_category_box" onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=1&ro_count=2'">
					<div class="main_image_size rounded" style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2020/05/07/5eb3bc0914a83.jpg')"></div>
					<div class="main_image_text">
						<span>호텔</span>
					</div>
				</div>

				<div class="col-sm-3 main_category_box" onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=2&ro_count=2'">
					<div class="main_image_size rounded" style="background-image: url('https://image.goodchoice.kr/resize_490x348/adimg_new/69259/19858/50673a58fdf6cdca824912c4e506125f.jpg')"></div>
					<div class="main_image_text">
						<span>모텔</span>
					</div>
				</div>

				<div class="col-sm-3 main_category_box" onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=3&ro_count=2'">
					<div class="main_image_size rounded" style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2021/12/28/19/884c4786801d4340b54509af09d02e36.jpg')"></div>
					<div class="main_image_text">
						<span>펜션</span>
					</div>
				</div>

				<div class="col-sm-3 main_category_box" onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=4&ro_count=2'">
					<div class="main_image_size rounded" style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2020/06/12/5ee30235adb4c.jpg')"></div>
					<div class="main_image_text">
						<span>리조트</span>
					</div>
				</div>
			</div>
		</div>
		<!-- 숙소 종류별 즉시 검색 기능 끝 -->

		<!-- 최신 리뷰로 detail.jsp 이동하기 -->
		<div class="main_box rounded mt-3">
			<h4 class="main_head_text">📌 최근 가장 많이 예약된 숙소에요!</h4>

			<div class="">
				<div class="swiper-container">
					<div class="swiper-wrapper">

						<c:forEach var="business" items="${hotBusinessList}">

							<!-- Slides ::슬라이더 -->
							<div class="swiper-slide" 
							onclick="location.href='${pageContext.request.contextPath}/reservation/detail?bu_email=${business.bu_email}&checkin=${today}&checkout=${tomorrow}' ">
								<!-- 사진 -->
								<div class="main_hot_image rounded" style="background-image: url('${fn:trim(business.picLocation)}')"></div>

								<div class="main_hot_box rounded">
									<div class="row">
										<div class="col-sm-6" style="padding-right: 0;">
											<span class="badge bg-danger">
												<c:choose>
													<c:when test="${business.bu_id == 1}">호텔</c:when>
													<c:when test="${business.bu_id == 2}">모텔</c:when>
													<c:when test="${business.bu_id == 3}">펜션</c:when>
													<c:when test="${business.bu_id == 4}">리조트</c:when>
												</c:choose>
											</span>
											<br>
											<!-- 숙소 이름 -->
											<span class="main_hot_text">${business.bu_title}</span>
											<br>

											<!-- 별점 -->
											<span class="main_hot_star">
												<c:choose>
													<c:when test="${business.avgScore < 1}">☆☆☆☆☆</c:when>
													<c:when test="${business.avgScore >=1 && business.avgScore < 2}">★☆☆☆☆</c:when>
													<c:when test="${business.avgScore >=2 && business.avgScore < 3}">★★☆☆☆</c:when>
													<c:when test="${business.avgScore >=3 && business.avgScore < 4}">★★★☆☆</c:when>
													<c:when test="${business.avgScore >=4 && business.avgScore < 5}">★★★★☆</c:when>
													<c:when test="${business.avgScore >= 5}">★★★★★</c:when>
												</c:choose>
											</span>
											<span style="color: white; font-size: 12px; font-weight: bold;">(${business.avgScore})</span>
											<!-- 별점 끝 -->
										</div>


										<!-- 추천 가격 -->
										<div class="col-sm-6" style="text-align: end; padding-left: 0; margin-top:25px;">
											<span class="main_hot_price_sub">1박 기준</span>
											<br>
											<span class="main_hot_price">
												<fmt:formatNumber value="${business.minPrice}" pattern="#,###" />원
											</span>
										</div>
										<!-- 추천 가격 끝-->

									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- If we need navigation buttons ::양옆 좌우 버튼(선택) -->
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
				
			</div>
		</div>
		<!-- 최신 리뷰로 detail.jsp 이동하기 끝 -->

		<div class="main_box rounded mt-3"></div>

	</div>
</body>
</html>