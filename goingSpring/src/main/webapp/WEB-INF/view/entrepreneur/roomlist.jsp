<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 4 Website Example</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/common/style.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

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
	  </ul>
	  </div>
</nav>
<table class="table" style="width: 75%; margin:10px auto; margin-top: 50px;">
    <tbody>
    <c:forEach var="l" items="${list}">
	      <tr style="width: 100%">
	        <td style="width: 60%"><a href="${pageContext.request.contextPath}/room/roominfo?ro_num=${l.ro_num}">
	        <input type="hidden" name="pic_num" value="${l.pic_num}">
	        	<c:set var="ro_num" value="${l.ro_num}" />
	        	<script>console.log(${l.ro_num}.toString+", "+${picMap}'</script>
	        	<img class="roomlist_main-img" src="${picMap[ro_num]}">
	        </a></td>
	        <td class= "roomlist_box" style="width: 40%">
				<div>
					<h3>${l.ro_name}</h3>
					<h6>이용인원 : ${l.ro_count}명</h6>
				</div>
				<div class="roomlist_bottom">
					<h4>${l.ro_price}원</h4>
				</div>
			</td>
	      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
