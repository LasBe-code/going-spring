<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function(){
	var mySwiper = new Swiper('.swiper-container', {
        slidesPerView: 4,
        slidesPerGroup: 4,
        observer: true,
        observeParents: true,
        spaceBetween: 24,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        breakpoints: {
            1280: {
                slidesPerView: 3,
                slidesPerGroup: 3,
            },
            720: {
                slidesPerView: 1,
                slidesPerGroup: 1,
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
					<div class="main_search_button_box btn-group">
						<button type=submit class="btn main_search_button">검색</button>
						<button type=button class="btn main_search_button" onclick="location.href='<%=request.getContextPath()%>/search/map'">지도검색</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 상단 검색 창 끝 -->
	
	<!-- 숙소 종류별 즉시 검색 기능 -->
	<div class="main_box rounded mt-3">
		<h4 class="main_head_text">🧳 바로 검색해보세요!</h4>
		<div class="row">
			<div class="col-sm-3 main_category_box"
			onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=1&ro_count=2'">
				<div class="main_image_size rounded" 
				style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2020/05/07/5eb3bc0914a83.jpg')"></div>
				<div class="main_image_text"><span>호텔</span></div>
			</div>
			
			<div class="col-sm-3 main_category_box" 
			onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=2&ro_count=2'">
				<div class="main_image_size rounded" 
				style="background-image: url('https://image.goodchoice.kr/resize_490x348/adimg_new/69259/19858/50673a58fdf6cdca824912c4e506125f.jpg')"></div>
				<div class="main_image_text"><span>모텔</span></div>
			</div>
			
			<div class="col-sm-3 main_category_box" 
			onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=3&ro_count=2'">
				<div class="main_image_size rounded" 
				style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2021/12/28/19/884c4786801d4340b54509af09d02e36.jpg')"></div>
				<div class="main_image_text"><span>펜션</span></div>
			</div>
			
			<div class="col-sm-3 main_category_box" 
			onclick="location.href='${pageContext.request.contextPath}/search/search?bu_id=4&ro_count=2'">
				<div class="main_image_size rounded" 
				style="background-image: url('https://image.goodchoice.kr/resize_490x348/affiliate/2020/06/12/5ee30235adb4c.jpg')"></div>
				<div class="main_image_text"><span>리조트</span></div>
			</div>
		</div>
	</div>
	<!-- 숙소 종류별 즉시 검색 기능 끝 -->
	
	<!-- 최신 리뷰로 detail.jsp 이동하기 -->
	<div class="main_box rounded mt-3">
		<h4 class="main_head_text">📌 최신 리뷰가 작성된 숙소에요!</h4>
		
		<div class="">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<!-- Slides ::슬라이더 -->
					<div class="swiper-slide rounded">
						<img class="swiper_img" src="https://image.goodchoice.kr/resize_490x348/affiliate/2021/12/28/19/884c4786801d4340b54509af09d02e36.jpg">
						<span class="large_text">고잉호텔</span> <br>
						<span class="small_text">대박 좋아요 ㄷㄷ overflow hidden</span>
					</div>
					<div class="swiper-slide">Slide 2</div>
				</div>
				<!-- If we need navigation buttons ::양옆 좌우 버튼(선택) -->
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</div>
		
	</div>
	<!-- 최신 리뷰로 detail.jsp 이동하기 끝 -->
	
	<div class="main_box rounded mt-3">
	</div>
	
</div>
</body>
</html>