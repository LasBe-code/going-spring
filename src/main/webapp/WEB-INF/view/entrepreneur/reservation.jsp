<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.page-item.active .page-link { 
	background-color: #ffc107!important;
    border-color: #ffc107!important;
}
</style>
</head>
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
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/todayCheckin">체크인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/room/map">지도</a>
	    </li>
	  </ul>
	  </div>
</nav>
<div class="container" style="margin-top: 50px;">
  <h2 style="text-align: center;">예약 내역</h2>
<div style="margin-bottom: 100px;">
<form style=" margin:30px 0px auto; float: right;">
  <select name="searchName" id="searchName">
  	<option value="ro_name" ${searchName == 'ro_name' ? 'selected' : '' }>방이름</option>
  	<option value="name" ${searchName == 'name' ? 'selected' : '' }>예약자이름</option>
  	<option value="checkin" ${searchName == 'checkin' ? 'selected' : '' }>체크인</option>
  	<option value="checkout" ${searchName == 'checkout' ? 'selected' : '' }>체크아웃</option>
  	<option value="status" ${searchName == 'status' ? 'selected' : '' } >예약상태</option>
  </select>
  <input type="text" id="search" name="search" ${searchName == 'status' ? 'placeholder ="예약완료, 결제취소, 이용완료 , 입실완료 입력하세요"' : '' } style="width:450px;">
  <input type="submit" value="검색">
</form>
</div>
  <table class="table table-hover" style="margin-top: 30px; text-align: center;">
    <thead>
      <tr>
     	<th>객실 이름</th>
        <th>이용인원수</th>
        <th>체크인</th>
        <th>체크아웃</th>
        <th>예약자이름</th>
        <th>핸드폰번호</th>
        <th>Email</th>
        <th>예약상태</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="b" items="${bk}" varStatus="i">
      <tr>
     	<td>${b.ro_name}</td>
        <td>${b.ro_count}</td>
        <td>${b.checkin}</td>
        <td>${b.checkout}</td>
     	<td>${b.name}</td>
        <td>${b.tel}</td>
        <td>${b.email}</td>
        <td>
        	<c:choose>
        		<c:when test="${b.status == '1'}">예약 완료</c:when>
        		<c:when test="${b.status == '2'}">결제 취소</c:when>
        		<c:when test="${b.status == '3'}">이용 완료</c:when>
        	</c:choose>
        </td>
      </tr>
    </c:forEach>  
    </tbody>
  </table>
</div>

<div class="container"  >
<ul class="pagination justify-content-center"  >
	<li class='page-item <c:if test="${startNum <= bottomLine}">disabled </c:if>'>
	<a class="page-link" href="${pageContext.request.contextPath}/room/reservation?pageNum=${startPage-bottomLine}&searchName=${searchName}&search=${search}">Previous</a></li>
	<c:forEach var="i" begin="${startNum}" end="${endNum}">
		<li class='page-item <c:if test="${i==pageInt}">active</c:if>'>
		<a class="page-link" href="${pageContext.request.contextPath}/room/reservation?pageNum=${i}&searchName=${searchName}&search=${search}">${i}</a></li>
	</c:forEach>
	<li class='page-item <c:if test="${endNum >= maxNum}">disabled </c:if>'>
	<a class="page-link" href="${pageContext.request.contextPath}/room/reservation?pageNum=${startPage+bottomLine}&searchName=${searchName}&search=${search}">Next</a></li>
</ul>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var inputTag = document.querySelector('#search');  // id가 search인 태그를 선택
    $('#searchName').change(function(){
        var searchName = $('#searchName').val(); //id가 searchName인 select box의 값 추출하여 저장
        if(searchName == 'status'){ 	// searchName의 값이 status면 
        								// inputTag의 placeholder값을 변경
            inputTag.placeholder = "예약완료, 결제취소, 이용완료 , 입실완료 입력하세요";
        }
        else{
        	// searchName의 값이 status가 아니면 공백으로 변경
        	inputTag.placeholder = "";
        }
    });
});
</script>
</div>
</body>
</html>