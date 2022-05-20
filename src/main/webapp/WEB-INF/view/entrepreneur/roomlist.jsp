<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 4 Website Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
</head>
<body>

<div class="container" style="margin-top:100px">
<table class="table" style="width: 75%; margin:10px auto; margin-top: 50px;">
    <tbody>
    <c:forEach var="l" items="${list}">
	      <tr style="width: 100%">
	        <td style="width: 60%"><a href="${pageContext.request.contextPath}/room/roominfo?ro_num=${l.ro_num}">
	        <input type="hidden" name="pic_num" value="${l.pic_num}">
	        	<c:set var="ro_num" value="${l.ro_num}" />
	        	<img class="roomlist_main-img" src="${l.location}">
	        </a></td>
	        <td class= "roomlist_box" style="width: 40%">
				<div>
					<h3>${l.ro_name}</h3>
					<h6>이용인원 : ${l.ro_count}명</h6>
				</div>
				<div class="roomlist_bottom">
					<h4><fmt:formatNumber value="${l.ro_price}" pattern="#,###" />원</h4>
				</div>
			</td>
	      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
