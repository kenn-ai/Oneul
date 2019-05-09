<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>당신의 오늘 - Oneul</title>
<meta name="description" content="This is a free Bootstrap landing page theme created for BootstrapZero. Feature video background and one page design." />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Codeply">
<link href="/resources/css/styles.css" rel="stylesheet" />
<link href="/resources/css/bootstrap.css?ver=2" rel="stylesheet" />
<link href="/resources/css/animate.min.css" rel="stylesheet" />
<link href="/resources/css/ionicons.min.css" rel="stylesheet" />
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.easing.min.js"></script>
<script src="/resources/js/wow.js"></script>
<script src="/resources/js/scripts.js"></script>

<script type="text/javascript">
	window.onload = function() {
		$("#exampleModal").on('hide.bs.modal', function(e) {
			$("#joinform").each(function() {
				this.reset();
			});
		})
	}
</script>
</head>
<body>

<!-- alert -->
	<c:choose>
		<c:when test="${ leaveResult == 1 }">
			<script type="text/javascript">
				alert("탈퇴가 완료되었습니다.");
			</script>
		</c:when>
		<c:when test="${ logoutResult == 1 }">
			<script type="text/javascript">
				alert("로그아웃 하셨습니다.");
			</script>
		</c:when>
		<c:when test="${ loginResult == 'fail' }">
			<script type="text/javascript">
				alert("로그인에 실패했습니다.");
			</script>
		</c:when>
	</c:choose>

	<script type="text/javascript">
		function check() {

			var emailtext = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			var passwordtest = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
			var phonenum = /^(01[016789]{1})?([0-9]{3,4})?([0-9]{4})$/;

			//^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

			if (emailtext.test(join.userid.value) == false) {
				//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
				if (join.userid.value == "") {
					alert("이메일을 입력하세요");
					join.userid.focus();
					return false;
				} else {
					alert("이 메일형식이 올바르지 않습니다.");
					join.userid.focus();
					return false;
					/* exit; */
				}

			} else if (passwordtest.test(join.userpw.value) == false) {
				//6자리 이상 20자리 이하, 숫자 혹은 특수 문자를 반드시 포함

				if (join.userpw.value == "") {
					alert("비밀번호를 입력하세요");
					join.userpw.focus();
					return false;
					/* exit; */
				} else {
					alert("비밀번호 형식이 올바르지 않습니다.");
					join.userpw.focus();
					return false;
					/* exit; */
				}
			} else if (join.userpw.value != join.userpw2.value) {
				//비밀번호와 비밀번호확인의 값이 다를 경우

				if (join.userpw2.value == "") {
					alert("비밀번호확인을 위해 입력하세요");
					join.userpw2.focus();
					return false;
					/* exit; */
				} else {
					alert("입력한 2개의 비밀번호가 일치하지 않습니다.");
					join.userpw2.focus();
					return false;
					/* exit; */
				}
			} else if (join.nickname.value == "") {
				alert("닉네임을 입력하세요");
				join.nickname.focus();
				return false;
				/* exit; */

			} else if (phonenum.test(join.phone.value) == false) {

				if (join.phone.value == "") {
					alert("전화번호를 입력하세요");
					join.phone.focus();
					return false;
					/* exit; */
				} else {
					alert("잘못된 휴대폰 번호입니다.");
					join.phone.focus();
					return false;
					/* exit; */
				}
			}

			var checkIDValue = document.join.checkIDValue.value;
			if (checkIDValue == 0) {
				alert("ID 중복체크를 해야합니다.");
				return false;
			}

			return true;
			/* join.submit(); */
		}

		function openCheckID() {

			var emailtext = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

			if (emailtext.test(join.userid.value) == false) {
				//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
				if (join.userid.value == "") {
					alert("이메일을 입력하세요");
					join.userid.focus();
					return false;
				} else {
					alert("형식이 올바르지 않습니다.");
					join.userid.focus();
					return false;
				}

			} else {

				var check = document.createElement("form");

				window.open("", "checkform", "width=400 height=300");

				check.method = "post";
				check.action = "/user/actCheckid";
				check.target = "checkform";

				var checkinput = document.createElement("input");
				checkinput.setAttribute("type", "hidden");
				checkinput.setAttribute("name", "userid");
				checkinput.setAttribute("id", "userid");
				checkinput.setAttribute("value", join.userid.value);
				check.appendChild(checkinput);

				check.submit();
			}

		}
	</script>

	<header id="first">
	<div class="header-content">
		<div class="inner">
			<h1 class="cursive">Oneul</h1>
			<h4>A daily record of your today</h4>
			<div style="width: 30%; margin: auto;">
				<form action="/user/actLogin" method="post">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"
							style="width: 80px;">아이디</span> <input type="text" name="userid"
							class="form-control" placeholder="아이디"
							aria-describedby="basic-addon1" id="userid"
							value="${faildto.userid}">
					</div>
					<div class="input-group" style="margin-top: 5px;">
						<span class="input-group-addon" id="basic-addon1"
							style="width: 80px;">비밀번호</span> <input type="password"
							name="userpw" class="form-control" placeholder="비밀번호"
							aria-describedby="basic-addon2" id="userpw">
					</div>
					<br />

					<div class="input-group centered" style="width: 100%;">
						<button type="submit" class="btn btn-login btn-lg">LOGIN</button>
						&nbsp; <a><button type="button" class="btn btn-login btn-lg"
								data-toggle="modal" data-target="#exampleModal"
								data-whatever="@mdo">JOIN</button></a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 모달 시작 -->
	<div align="center">
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">회원가입</h4>
					</div>
					<div class="modal-body">
						<div style="margin: auto;">
							<form action="/user/actJoin" method="post" id="joinform"
								name="join" onsubmit="return check()">

								<div class="input-group" style="width: 500px;">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 150px;">E-mail</span> <input type="text"
										name="userid" class="form-control" placeholder="E-mail"
										aria-describedby="basic-addon1" id="userid"
										style="width: 250px" value="${faildto.userid}">
									
									<input type="hidden" name="checkIDValue" value="0"> <input class="btn-success"
										type="button" value="IDCheck" onclick="openCheckID();"
										style="width: 100px; height: 34px; vertical-align: middle;">
									
								</div>

								<div class="input-group" style="width: 500px; margin-top: 10px;">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 150px;">비밀번호</span> <input type="password"
										name="userpw" class="form-control" placeholder="6~20자리. 영문 + 숫자 or 특수문자 형식"
										aria-describedby="basic-addon2" id="userpw">
								</div>
									<!-- <h5 style="color: black;">6자리 이상 20자리 이하, 영문과 숫자 혹은 특수 문자를 반드시 포함해서</h5> -->
								<div class="input-group" style="width: 500px; margin-top: 10px;">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 150px;">비밀번호 재확인</span> <input type="password"
										name="userpw2" class="form-control" placeholder="비밀번호 재확인"
										aria-describedby="basic-addon2" id="userpw2">
								</div>

								<div class="input-group" style="width: 500px; margin-top: 10px;">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 150px;">닉네임</span> <input type="text"
										name="nickname" class="form-control" placeholder="닉네임"
										aria-describedby="basic-addon1" id="nickname"">
								</div>

								<div class="input-group" style="width: 500px; margin-top: 10px;">
									<span class="input-group-addon" id="basic-addon1"
										style="width: 150px;">전화번호</span> <input type="tel"
										name="phone" class="form-control" placeholder="전화번호"
										aria-describedby="basic-addon1" id="phone"">
								</div>
								<div class="input-group">
									<button type="submit" class="btn btn-success"
										style="margin-top: 10px;">JOIN</button>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<div>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 모달 끝 --> <video autoplay="" loop=""
		class="fillWidth fadeIn wow collapse in" data-wow-delay="0.5s"
		poster="https://s3-us-west-2.amazonaws.com/coverr/poster/Traffic-blurred2.jpg"
		id="video-background"> <source
		src="https://s3-us-west-2.amazonaws.com/coverr/mp4/Traffic-blurred2.mp4"
		type="video/mp4">Your browser does not support the video tag.
	I suggest you upgrade your browser. </video> </header>
</body>
</html>

