<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <% 
request.setAttribute("dateType1", "20220101");
request.setAttribute("dateType2", "2022-01-01");
request.setAttribute("dateType3", "2022.01.01");
%>
<label>yyyyMMdd</label>
<input type="date" value="${dateType1}"> <br><br>

<label>yyyy-MM-dd</label>
<input type="date" value="${dateType2}"> <br><br>

<label>yyyy.MM.dd</label>
<input type="date" value="${dateType3}">
 --%>
<%
request.setAttribute("minDate", "2022-04-10");
request.setAttribute("maxDate", "2022-04-25");
%>
<label>min & max</label>
<input type="date" min="${minDate}" max="${maxDate}">
</body>
</html>