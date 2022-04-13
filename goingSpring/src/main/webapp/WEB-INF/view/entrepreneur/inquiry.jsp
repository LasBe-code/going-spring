<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
</head>
<body>
<div class="container" style="margin-top:100px">
  <nav class="navbar navbar-expand-sm bg-light navbar-light" style="width: 60%; margin:0px auto;">
  	<div style="margin: 0px auto;">
	  <ul class="navbar-nav">
	  	<li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/view/entrepreneur/roomInsert.jsp">객실 등록</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/view/entrepreneur/roomlist.jsp">객실 정보</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/view/entrepreneur/reservation.jsp">예약 확인</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/view/entrepreneur/inquiry.jsp">문의 내역</a>
	    </li>
	  </ul>
	  </div>
</nav>
<div class="container" style="margin-top: 50px;">
  <h2 style="text-align: center;">문의 내역</h2>
  <table class="table table-striped inquiry_table" style="margin-top: 30px;">
    <thead>
      <tr>
     	<th class = "inquiry_table_writer">작성자</th>
        <th class = "inquiry_table_subject">제목</th>
        <th class = "inquiry_table_date">등록일</th>
      </tr>
    </thead>
    <tbody>
      <tr>
     	<td class = "inquiry_table_writer">김철수</td>
        <td class = "inquiry_table_subject">조식문의드립니다</td>
        <td class = "inquiry_table_date">2022/02/15</td>
      </tr>
      <tr>
      	<td class = "inquiry_table_writer">김영희</td>
        <td class = "inquiry_table_subject">객실문의드립니다</td>
        <td class = "inquiry_table_date">2022/02/19</td>
      </tr>
      <tr>
   		<td class = "inquiry_table_writer">김영호</td>
        <td class = "inquiry_table_subject">주차문의드려요</td>
        <td class = "inquiry_table_date">2022/03/03</td>
      </tr>
    </tbody>
  </table>
</div>

</div>
</body>
</html>