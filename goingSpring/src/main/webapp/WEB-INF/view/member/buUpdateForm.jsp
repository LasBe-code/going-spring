<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="signup_form_width">
		<div style="text-align : center;">
			<b class="large_text" style="color:#ffc107;">정보수정</b>
		</div>
	<form name="f" action="${pageContext.request.contextPath}/member/buUpdatePro" method="post" onsubmit="return inputChk(this)">
	
		<div class="form-floating mt-3">
			<input type="tel" name="bu_tel" id="input_tel" class="form-control form-control-lg" 
				id="tel" placeholder="전화번호(- 빼고 작성해주세요)" pattern="[0-9]{11}" value="${mem.bu_tel}" readonly="readonly">
		</div>
		
		<div class="form-floating mt-3">
		  <input type="email" name="bu_email" class="form-control" id="bu_email" onkeyup="emailChk()" value="${mem.bu_email}" readonly="readonly">
		  <label><span id="result">이메일</span></label>
		</div>
		
		<div class="form-floating mt-3">
		  <input type="password" name="bu_password" class="form-control form-control-lg mt-3" onkeyup="passChk()" pattern="^(?=.*[a-zA-z])(?=.*[0-9]).{6,12}$" >
		  <label><span id="passResult">비밀번호</span></label>
		</div>
		
		<div class="mt-3">
			<input type="text" name="bu_name" class="form-control form-control-lg" placeholder="이름" value="${mem.bu_name}" >
		</div>
		
		<div class="row mt-3">
			<div class="col-sm-4">
				<input type="hidden" name="bu_id" value="${mem.bu_id}"> <!-- select 저장용 -->
				<select id="select_bu_id" class="form-select form-select-lg" onchange="change_bu_id()">
				     <option value="1">호텔</option>
				     <option value="2">모텔</option>
				     <option value="3">펜션</option>
				     <option value="4">리조트</option>
			   	</select>
			</div>
			
			<div class="col-sm-8">
				<input type="text" name="bu_title" class="form-control form-control-lg" placeholder="업체 이름" value="${mem.bu_title}" required>
			</div>
		</div> 
		
		<input type="text" name="bu_address" class="form-control form-control-lg mt-3" placeholder="업체 주소" value="${mem.bu_address}"  required>
		<input type="hidden" value="${pic_num}" name="pic_num">
		<label style=" margin-bottom: 10px;">객실사진등록&nbsp;&nbsp;&nbsp;</label>
    	<textarea class="form-control form-control-lg" rows="10" cols="100" name="picLocation">${location}</textarea>
		<input type="submit" id="submit" class="default_btn rounded mt-3" value="정보수정">
		
	</form>
</div>

</body>
</html>