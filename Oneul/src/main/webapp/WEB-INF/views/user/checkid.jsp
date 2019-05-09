<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/css/styles.css" rel="stylesheet" />
<link href="/resources/css/bootstrap.css?ver=3" rel="stylesheet" />
<link href="/resources/css/animate.min.css" rel="stylesheet" />
<link href="/resources/css/ionicons.min.css" rel="stylesheet" />
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.easing.min.js"></script>
<script src="/resources/js/wow.js"></script>
<script src="/resources/js/scripts.js"></script>
<title>ID CHECK</title>
</head>
<body>

	<script type="text/javascript">
	
	function check() {

		var emailtext = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

		if (emailtext.test(checkid.userid.value) == false) {
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
			if (checkid.userid.value == "") {
				alert("이메일을 입력하세요");
				join.userid.focus();
				return false;
			} else {
				alert("이 메일형식이 올바르지 않습니다.");
				checkid.userid.focus();
				return false;
				/* exit; */
			}
		}
		return true;
	}
	
	
		function result(){
			opener.document.join.checkIDValue.value = document.checkok.checkIDValue.value;
			opener.document.join.userid.value = document.checkok.userid.value;
			window.close();
		}
	
	</script>

	<div class="container-fluid">

		<div id="main-contents">

			<!-- ####################	 시작		#################### -->

			<input type="hidden" value="${checkid}" id="test">

			<c:choose>
				<c:when test="${result == null}">
				<div align="center" style="padding-bottom: 10px;">
					<h3>${checkid}은(는)<br><br>사용가능한 아이디 입니다.</h3>
					<h3>이 아이디를 사용하시겠습니까?</h3>
					</div>
					<div style="width: 300px; margin: auto;">
						<form name="checkok" onsubmit="return check()">
							<div class="input-group" style="padding-bottom: 10px;">
								<span class="input-group-addon" id="basic-addon1">아이디</span> <input
									type="text" name="userid" class="form-control"
									placeholder="아이디" aria-describedby="basic-addon2" id="userid">
							</div>

							<div class="input-group" align="center" style="width: 300px;">
								<input type="hidden" name="checkIDValue" value="1">
								<button class="btn btn-success" onclick="result();">사용하기</button>
							</div>
						</form>
					</div>

				</c:when>
				<c:when test="${result != null}">
				<div align="center" style="padding-bottom: 10px;">
					<h3>${checkid}은(는)<br><br>이미 사용되고 있는 아이디 입니다.</h3>
					<h3>다른 아이디를 입력해주세요.</h3>
					</div>
					<div style="width: 300px; margin: auto;">
						<form action="/user/actCheckid" method="post" name="checkid">
							<div class="input-group" style="padding-bottom: 10px;">
								<span class="input-group-addon" id="basic-addon1">아이디</span> <input
									type="text" name="userid" class="form-control"
									placeholder="아이디" aria-describedby="basic-addon2" id="userid">
							</div>

							<div class="input-group" align="center" style="width: 300px;">
								<button type="submit" class="btn btn-success">중복확인</button>
							</div>
						</form>
					</div>

				</c:when>
			</c:choose>




			<script type="text/javascript">
				var id = document.getElementById("test").value;
				var input_userid = document.getElementById("userid");
				//value를 쓸때는 문자열로 ㅇㅅㅇ
				input_userid.value = id;
			</script>



			<!-- ####################	   끝     	#################### -->

		</div>
		<!-- /#main-contents -->

	</div>
	<!-- /.container-fluid -->

</body>
</html>