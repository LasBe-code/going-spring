<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td {
	font-size: 0.9em;
}
.btn {
 	font-size : 0.9em;
 	padding-top : 0;
 	padding-bottom : 0;
}
</style>
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
	<div class= "row" style="margin: 50px auto; padding:0 100px 0 100px;">

		<div class="col-sm-6" style="margin-top: 25px;">
			<h4 style="text-align: center;">퇴실 전</h4>
			<table class="table table-hover" style="margin-top: 30px; text-align: center;">
				<thead>
					<tr>
						<th>번호</th>
						<th>객실 이름</th>
						<th>체크인</th>
						<th>체크아웃</th>
						<th>이름</th>
						<th>핸드폰번호</th>
						<th>예약상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="nc" items="${notCheckOut}" varStatus="i">
						<tr>
							<td class="over-td">${nc.bo_num }</td>
							<td class="over-td">${nc.ro_name}</td>
							<td>${nc.checkin}</td>
							<td>${nc.checkout}</td>
							<td>${nc.name}</td>
							<td>${nc.tel}</td>
							<td>
								<input type="button" class="update btn btn-outline-warning" value="체크아웃">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

		<div class="col-sm-6" style="margin-top: 25px;">
			<h4 style="text-align: center;">퇴실완료</h4>
			<table class="table table-hover" style="margin-top: 30px; text-align: center;">
				<thead>
					<tr>
						<th>번호</th>
						<th>객실 이름</th>
						<th>체크인</th>
						<th>체크아웃</th>
						<th>이름</th>
						<th>핸드폰번호</th>
						<th>예약상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="co" items="${checkOutOk}" varStatus="i">
						<tr>
							<td class="over-td">${co.bo_num }</td>
							<td class="over-td">${co.ro_name}</td>
							<td>${co.checkin}</td>
							<td>${co.checkout}</td>
							<td>${co.name}</td>
							<td>${co.tel}</td>
							<td>이용완료</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		// 버튼 클릭시 Row 값 가져오기
		$(".update").click(function() {
			const str = ""
			const tdArr = new Array(); // 배열 선언
			const checkBtn = $(this);

			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			const tr = checkBtn.parent().parent();
			const td = tr.children();

			//	테이블의 td에서 0번째위치한 td의 값 가져오기
			const no = td.eq(0).text();
			const result = confirm(no + '번의 예약상태를 변경하시겠습니까?')
			if (result) {
				location.href = '${pageContext.request.contextPath}/room/updateTodayCheckOut?bo_num='+ no;
			}
			console.log(no)
		})
	</script>
</body>
</html>