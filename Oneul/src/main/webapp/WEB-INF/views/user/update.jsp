<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>회원정보수정</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>

<script type="text/javascript">
	
		function check() {

			var passwordtest = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
			var phonenum = /^(01[016789]{1})?([0-9]{3,4})?([0-9]{4})$/;

			if (passwordtest.test(userupdate.userpw.value) == false) {
				//6자리 이상 20자리 이하, 숫자 혹은 특수 문자를 반드시 포함

				if (userupdate.userpw.value == "") {
					alert("비밀번호를 입력하세요");
					userupdate.userpw.focus();
					return false;
					/* exit; */
				} else {
					alert("비밀번호 형식이 올바르지 않습니다.");
					userupdate.userpw.focus();
					return false;
					/* exit; */
				}
			} else if (userupdate.userpw.value != userupdate.userpw2.value) {
					//비밀번호와 비밀번호확인의 값이 다를 경우

					if (userupdate.userpw2.value == "") {
						alert("비밀번호확인을 위해 입력하세요");
						userupdate.userpw2.focus();
						return false;
						/* exit; */
					} else {
						alert("입력한 2개의 비밀번호가 일치하지 않습니다.");
						userupdate.userpw2.focus();
						return false;
						/* exit; */
					}	
			} else if (userupdate.nickname.value == "") {
				alert("닉네임을 입력하세요");
				userupdate.nickname.focus();
				return false;
				/* exit; */

			} else if (phonenum.test(userupdate.phone.value) == false) {

				if (userupdate.phone.value == "") {
					alert("전화번호를 입력하세요");
					userupdate.phone.focus();
					return false;
					/* exit; */
				} else {
					alert("잘못된 휴대폰 번호입니다.");
					userupdate.phone.focus();
					return false;
					/* exit; */
				}
			}

			return true;
		}
		</script>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 회원정보수정</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
		<div style="margin: auto;" align="center">
			<form action="/user/actUpdate" method="post" name="userupdate" onsubmit="return check()">
				<div class="input-group" style="width: 500px;">
					<span class="input-group-addon" id="basic-addon1" style="width: 150px;">E-mail</span> 
					<input type="text" name="userid" class="form-control" aria-describedby="basic-addon1" id="userid" readonly="readonly" value="${userinfo.userid}">
						<!-- <span>알파벳+숫자@알파벳+숫자.알파벳 형식</span> -->
				</div>
	
				<div class="input-group" style="width: 500px; margin-top: 10px;">
					<span class="input-group-addon" id="basic-addon1" style="width: 150px;">비밀번호</span> 
					<input type="password" name="userpw" class="form-control" placeholder="비밀번호" aria-describedby="basic-addon2" id="userpw">
						
						<!-- <span>6자리 이상 20자리 이하, 영문과 숫자 혹은 특수 문자를 반드시 포함</span> -->
				</div>
	
				<div class="input-group" style="width: 500px; margin-top: 10px;">
					<span class="input-group-addon" id="basic-addon1" style="width: 150px;">비밀번호 재확인</span> 
					<input type="password" name="userpw2" class="form-control" placeholder="비밀번호 재확인" aria-describedby="basic-addon2" id="userpw2">
				</div>
	
				<div class="input-group" style="width: 500px; margin-top: 10px;">
					<span class="input-group-addon" id="basic-addon1" style="width: 150px;">닉네임</span> 
					<input type="text" name="nickname" class="form-control" placeholder="닉네임" aria-describedby="basic-addon1" id="nickname"">
				</div>
	
				<div class="input-group" style="width: 500px; margin-top: 10px;">
					<span class="input-group-addon" id="basic-addon1" style="width: 150px;">전화번호</span> 
					<input type="tel" name="phone" class="form-control" placeholder="전화번호" aria-describedby="basic-addon1" id="phone"">
				</div>
				<div class="input-group" align="center" style="width: 500px;">
					<button type="submit" class="btn btn-success" style="margin-top: 10px; width: 200px;">수정</button>					
				</div>
			</form>
				<div class="input-group" align="center" style="width: 500px;">
					<a href="/user/check?code=leave"><button type="submit" class="btn btn-success" style="margin-top: 10px; width: 200px;">탈퇴</button></a>
				</div>
		</div>

			<script type="text/javascript">
				var input_nickname = document.getElementById("nickname");
				var input_phone = document.getElementById("phone");
				//value를 쓸때는 문자열로 ㅇㅅㅇ
				input_nickname.value = '${userinfo.nickname}';
				input_phone.value = '${userinfo.phone}';
			</script>
		
	
	<!-- ####################	   끝     	#################### -->
		
	</div><!-- /#main-contents -->
	
</div>
</body>
</html>
