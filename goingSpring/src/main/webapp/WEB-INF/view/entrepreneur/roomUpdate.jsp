<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container" style="width: 800px; margin-top: 100px;">
<h2 style="text-align: center;">객실정보수정</h2>
  <form action="${pageContext.request.contextPath}/room/roomUpdatePro?pic_num=${pic_num}&ro_num=${ro_num}" method="post" enctype="multipart/form-data">
    <div class="mb-3 mt-3">
      <label>객실이름</label>
      <input type="text" class="form-control" id="subject" name="roName" value="${room.ro_name}">
    </div>
    <div class="mb-3">
      <label>가격</label>
      <input type="text" class="form-control" id="cal" name="roPrice" value="${room.ro_price}">
    </div>
	    <div style="width: 200px; float: left;">
	    	<label>체크인 시간</label>
	    	<input type="time" class="form-control" id="checkIn" name="checkIn" value="${room.checkin}">
	    </div>
	    <div style="width: 200px; float:left; margin-bottom: 20px; margin-left: 20px;">
	    	<label>체크아웃 시간</label>
	    	<input type="time" class="form-control" id="checkOut" name="checkOut" value="${room.checkout}">
    	</div>
    <div style="clear:both;"></div>
    
    <div class="mb-3" style="margin-top: 20px;">
    <label>객실이용인원</label>
    <select id="people" name="roCount">
    	<option>1</option>
    	<option>2</option>
    	<option>3</option>
    	<option>4</option>
    </select>
    </div>
    	<div class="mb-3" >
    	<label style=" margin-bottom: 10px;">객실기본정보&nbsp;&nbsp;&nbsp;</label><span id="byteInfo" style="display: inline;">0</span> /3900bytes
   		<textarea rows="10" cols="100" name="roomInfo" onKeyUp="javascript:fnChkByte(this,'3900')">${room.ro_info}</textarea>
    </div>
    
    <label style=" margin-bottom: 10px;">객실사진등록&nbsp;&nbsp;&nbsp;</label>
    <textarea rows="10" cols="100" name="picLocation">${pic}</textarea>
    
    <input type="hidden" name="pic_num" value="${room.pic_num}">
	<input type="hidden" name="ro_num" value="${room.ro_num}">
    <button type="submit" class="default_btn rounded mt-1" style="width:100px;margin:auto; display:block;">등록</button>
  </form>

</div>
<script type="text/javascript">

//Byte 수 체크 제한
function fnChkByte(obj, maxByte)
{
    var str = obj.value;
    var str_len = str.length;


    var rbyte = 0;
    var rlen = 0;
    var one_char = "";
    var str2 = "";


    for(var i=0; i<str_len; i++)
    {
        one_char = str.charAt(i);
        if(escape(one_char).length > 4) {
            rbyte += 3;                                         //한글3Byte
        }else{
            rbyte++;                                            //영문 등 나머지 1Byte
        }
        if(rbyte <= maxByte){
            rlen = i+1;                                          //return할 문자열 갯수
        }
     }
     if(rbyte > maxByte)
     {
        // alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
        alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
        str2 = str.substr(0,rlen);                                  //문자열 자르기
        obj.value = str2;
        fnChkByte(obj, maxByte);
     }
     else
     {
        document.getElementById('byteInfo').innerText = rbyte;
     }
}
	
</script>
</body>
</html>