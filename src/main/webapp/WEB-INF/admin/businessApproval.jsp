<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function approval(bu_email, tel, title){
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/admin/approval',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{bu_email:bu_email},
		success : function(result){
			if(result == true){
				alert('실패했습니다.')
			} else {
				const btn1 = document.getElementById(tel)
				const btn2 = document.getElementById(tel+"Cancel")
				btn1.disabled = true
				btn1.style.backgroundColor = "#ffc107"
				btn2.disabled = true
				
				openToast("[가입 승인] "+title)
			}
		}
	})
}
function cancel(bu_email, tel, title){
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/admin/approvalCancel',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{bu_email:bu_email},
		success : function(result){
			if(result == true){
				alert('실패했습니다.')
			} else {
				const btn1 = document.getElementById(tel)
				const btn2 = document.getElementById(tel+"Cancel")
				btn1.disabled = true
				btn2.disabled = true
				btn2.style.backgroundColor = "red"
				
				openToast("[가입 취소] "+title)
			}
		}
	})
}
</script>

</head>
<body>

<div class="admin-box over-table">
	<h2>사업자 가입 승인</h2>
	<hr>
	
	<div class="over-table mt-5">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>사업자</th>
					<th>전화번호</th>
					<th>숙소 이름</th>
					<th>주소</th>
					<th>승인</th>
					<th>가입 취소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="business" items="${businessList}">
					<tr>
						<td>${business.bu_name}</td>
						<td>${business.bu_tel}</td>
						<td>${business.bu_title}</td>
						<td>${business.bu_address}</td>
						<td><button class="ok_btn btn btn-outline-warning" 
							onclick="approval('${business.bu_email}', '${business.bu_tel}', '${business.bu_title}')" id="${business.bu_tel}" type="button"></button></td>
						<td><button class="ok_btn btn btn-outline-danger" 
							onclick="cancel('${business.bu_email}', '${business.bu_tel}', '${business.bu_title}')" id="${business.bu_tel}Cancel" type="button"></button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<div class="container">
			<ul class="pagination justify-content-center">
				<li class='page-item <c:if test="${startNum <= bottomLine}">disabled </c:if>'>
					<a class="page-link" href="${pageContext.request.contextPath}/admin/businessApproval?pageNum=${startPage-bottomLine}">Previous</a>
				</li>
				<c:forEach var="i" begin="${startNum}" end="${endNum}">
					<li class='page-item <c:if test="${i==pageInt}">active</c:if>'>
						<a class="page-link" href="${pageContext.request.contextPath}/admin/businessApproval?pageNum=${i}">${i}</a>
					</li>
				</c:forEach>
				<li class='page-item <c:if test="${endNum >= maxNum}">disabled </c:if>'>
					<a class="page-link" href="${pageContext.request.contextPath}/admin/businessApproval?pageNum=${startPage+bottomLine}">Next</a>
				</li>
			</ul>
		</div>
	</div>
</div>

</body>
</html>