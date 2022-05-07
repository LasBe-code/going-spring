<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<script>
function openToast(msg){
	let toastElList = [].slice.call(document.querySelectorAll('.toast'))
	let toastList = toastElList.map(function(toastEl) {
	  return new bootstrap.Toast(toastEl)
	})
	toastList.forEach(toast => toast.show()) 
	const toastMsg = document.getElementById("toast-msg")
	toastMsg.innerHTML = msg	
}
</script>

</head>
<body>

	<c:if test="${param.msg != null}">
		<div class="position-fixed end-0 p-3" style="margin: 56px 30px 0 0;">
			<div class="toast show" style="margin-bottom: -101px;">
				<div class="toast-header">
					<strong class="me-auto">알림 메시지</strong>
					<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
				</div>
				<div class="toast-body">
					<p>${param.msg}</p>
				</div>
			</div>
		</div>
	</c:if>
	
	<div class="position-fixed end-0 top-0" style="margin: 56px 30px 0 0; opacity: none !important;">
		<div class="toast">
			<div class="toast-header">
				<strong class="me-auto">알림 메세지</strong>
				<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
			</div>
			<div class="toast-body">
				<p id="toast-msg"></p>
			</div>
		</div>
	</div>
	
</body>
</html>