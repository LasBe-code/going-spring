<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>Insert title here</title>
<style type="text/css">
.page-item.active .page-link { 
	background-color: #ffc107!important;
    border-color: #ffc107!important;
}
</style>
</head>
<body>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
//		구글 시각화 API를 로딩하는 메소드
      google.charts.load('current', {'packages':['corechart']});
      
// 		구글 시각화 API가 로딩이 완료되면,

// 		인자로 전달된 콜백함수를 내부적으로 호출하여 차트를 그리는 메소드

// 		화면이 실행될때 함께 실행된다.
      google.charts.setOnLoadCallback(columnChart1);

      function columnChart1() {

        var data = google.visualization.arrayToDataTable([
          ['지역', '매출'],
          ${result}
        ]);

        var options = {
          title: '지역별 월 매출'
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
			      <a class="nav-link" href="${pageContext.request.contextPath}/room/map">지도</a>
			    </li>
			</ul>
		</div>
	</nav>
</div>
<div class="container"  style=" margin: 0px auto; margin-top: 100px;">
	<form action="<%=request.getContextPath()%>/room/areaSales">
		<select name="month" id="month">
			<option value="01" ${month == '01' ? 'selected' : '' }>1월</option>
			<option value="02" ${month == '02' ? 'selected' : '' }>2월</option>
			<option value="03" ${month == '03' ? 'selected' : '' }>3월</option>
			<option value="04" ${month == '04' ? 'selected' : '' }>4월</option>
			<option value="05" ${month == '05' ? 'selected' : '' }>5월</option>
			<option value="06" ${month == '06' ? 'selected' : '' }>6월</option>
			<option value="07" ${month == '07' ? 'selected' : '' }>7월</option>
			<option value="08" ${month == '08' ? 'selected' : '' }>8월</option>
			<option value="09" ${month == '09' ? 'selected' : '' }>9월</option>
			<option value="10" ${month == '10' ? 'selected' : '' }>10월</option>
			<option value="11" ${month == '11' ? 'selected' : '' }>11월</option>
			<option value="12" ${month == '12' ? 'selected' : '' }>12월</option>
		</select>
		<input type="submit" value="검색">
	</form>
	<div id="ColumnChart" style="width: 1200px; height: 600px;"></div>
</div>
<script type="text/javascript">
	console.log(typeof(${month}))
</script>
</body>
</html>