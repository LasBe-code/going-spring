<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.page-item.active .page-link { 
	background-color: #ffc107!important;
    border-color: #ffc107!important;
}
</style>
</head>
<body>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
//		구글 시각화 API를 로딩하는 메소드
      google.charts.load('current', {'packages':['corechart']});
      
// 		구글 시각화 API가 로딩이 완료되면,

// 		인자로 전달된 콜백함수를 내부적으로 호출하여 차트를 그리는 메소드

// 		화면이 실행될때 함께 실행된다.
      google.charts.setOnLoadCallback(columnChart1);

      function columnChart1() {

        var data = google.visualization.arrayToDataTable([
          ['월별', '매출'],
          ${result}
        ]);

        var options = {
          title: '월 별 매출'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('ColumnChart'));

        chart.draw(data, options);
      }
    </script>
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
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/areaSales">지역별 월매출</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/todayCheckin">체크인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/todayCheckOut">체크아웃</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/roomReview">리뷰</a>
	    </li>
	  </ul>
	  </div>
</nav>
</div>
<div id="ColumnChart" style="width: 1200px; height: 600px; margin: 0px auto; margin-top: 100px;"></div>
</body>
</html>