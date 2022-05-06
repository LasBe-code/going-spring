<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
<body>

	<div class="admin-box">
		<div id="ColumnChart" style="width: 1200px; height: 600px; margin: 0px auto; margin-top: 100px;"></div>
	</div>

</body>
</html>