<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
let phoneAuth = true;
let passCheck = true;
let emailCheck = true;

function auth() {
	const telNum = document.f.tel.value;
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/member/phoneAuth',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{tel:telNum},
		success : function(result){
			if(result == true){
				alert('이미 가입된 전화번호 입니다.')
			} else {
				alert('인증 번호를 전송했습니다.')
				
				const code_btn = document.getElementById("code_btn")
				code_btn.disabled = false
			}
		}
	})
}

function authCheck(){
	const code = document.f.code.value;
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/member/phoneAuthOk',
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{code:code},
		success : function(result){
			if(result == true){
				alert('인증 번호가 다릅니다.')
				phoneAuth=true
				inputCheck()
			} else {
				alert('인증되었습니다')
				const input_tel = document.getElementById("input_tel")
				input_tel.readOnly = true
				
				const input_code = document.getElementById("input_code")
				input_code.readOnly = true
				
				const tel_btn = document.getElementById("tel_btn")
				tel_btn.disabled = true
				
				const code_btn = document.getElementById("code_btn")
				code_btn.disabled = true
				
				phoneAuth=false
				inputCheck()
			}
		}
	})
}

function bu_emailChk(){
	const bu_email = document.f.bu_email.value
	const display = document.querySelector("#display")
	let resultText = document.querySelector("#result")
	
	if(bu_email.indexOf('@') != -1 && bu_email.indexOf('.com') != -1){
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/member/readId',
			header:{"Content-Type":"application/json"},
			dateType:'json',
			data:{bu_email:bu_email},
			success : function(result){
				if(result == true){
					display.style.display = 'block'
					emailCheck = false
					inputCheck()
				} else {
					alert('일치하는 이메일이 없습니다.')
					emailCheck = true
					inputCheck()
				}
			}
		})
	}
}


function passChk() {
	const pass = document.f.password.value
	const passChk = document.f.passC.value
	let result = document.querySelector("#passResult")
	if(pass == passChk){
		result.style.color = 'blue'
		result.innerHTML = '일치하는 비밀번호'
		
		passCheck = false
		inputCheck()
		
	} else {
		result.style.color = 'red'
		result.innerHTML = '일치하지 않는 비밀번호'
		
		passCheck = true
		inputCheck()
	}
}

function inputCheck() {
	if(phoneAuth == false && passCheck == false && emailCheck == false){
		const submit = document.getElementById("submit")
		submit.disabled = false
	} else {
		submit.disabled = true
	}
}
</script>


<div class="signup_form_width">
		<div style="text-align : center;">
			<b class="large_text" style="color:#ffc107;">사업자 비밀번호 변경</b>
		</div>
	<form name="f" action="${pageContext.request.contextPath}/member/passwordResetBusinessPro" method="post">
		<div class="row mt-5">
			  <label><span id="result">이메일</span></label>
			<div class="col-sm-8">
			  <input type="email" name="bu_email" class="form-control form-control-lg" id="email" placeholder="GOING 아이디" required>
			</div>
			<div class="col-sm-4">
				<button type="button" id="email_btn" class="default_btn rounded" style="height: 48px;" onclick="bu_emailChk()">아이디 확인</button>
			</div>
		</div>
		
		<div style="display: none;" id="display">
		<!-- <input type="text" name="rand" id="rand"> 인증번호 확인용 -->
		<div class="row mt-5">
			<div class="col-sm-8">
				<input type="tel" name="tel" id="input_tel" class="form-control form-control-lg" 
					id="tel" placeholder="전화번호(- 빼고 작성해주세요)" pattern="[0-9]{11}" required>
			</div>
			<div class="col-sm-4">
				<button type="button" id="tel_btn" class="default_btn rounded" style="height: 48px;" onclick="auth()">인증코드 전송</button>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-sm-8">
				<input type="text" name="code" id="input_code" class="form-control form-control-lg" placeholder="전송받은 코드" pattern="[0-9]{6}" required>
			</div>
			<div class="col-sm-4">
				<button type="button" id="code_btn" class="default_btn rounded" style="height: 48px;" onclick="authCheck()" disabled>코드 확인</button>
			</div>
		</div>
		
		<div class="form-floating mt-3">
		  <input type="password" name="password" id="password" class="form-control form-control-lg mt-3" pattern="^(?=.*[a-zA-z])(?=.*[0-9]).{6,12}$" required>
		  <label><span>비밀번호 - 대문자 혹은 소문자와 숫자를 합해 6자리 이상 12자리 이하</span></label>
		</div>
		<div class="form-floating mt-3">
		  <input type="password" name="passC" class="form-control form-control-lg mt-3" onkeyup="passChk()" pattern="^(?=.*[a-zA-z])(?=.*[0-9]).{6,12}$" required>
		  <label><span id="passResult">비밀번호 확인</span></label>
		</div>
		
		<input type="submit" id="submit" class="default_btn rounded mt-3" value="비밀번호변경" disabled>
		</div>
	</form>
</div>

</body>
</html>