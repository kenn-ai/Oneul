<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>사용자 정보 확인</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>

<div class="page">

<!-- alert -->
	<c:choose>
		<c:when test="${ Code == 'update' }">
			

			<!-- Page Heading -->
			<div class="row">
			   <div class="col-lg-12">
			      <h4 class="page-header"> 사용자 정보 확인</h4>
			   </div>
			</div><!-- /.row -->
			
			<div id="main-contents">
				
			<!-- ####################	 시작		#################### -->
			
			
			
				<div style="width: 300px; margin: auto;">
					<form action="/user/actCheck" method="post">
						<input type="hidden" name="userid" value="${login.userid}">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">비밀번호</span> <input
								type="password" name="userpw" class="form-control"
								placeholder="비밀번호" aria-describedby="basic-addon2" id="userpw">
						</div>
		
						<div class="input-group" style="padding-top: 20px;">
							<button style="width: 100px; margin-left: 100px; margin-rigth: 100px;" type="submit" class="btn btn-success">본인확인</button>
						</div>
					</form>
				</div>
		
			
			
			<!-- ####################	   끝     	#################### -->
				
			</div><!-- /#main-contents -->
	
		</c:when>
		<c:when test="${ Code == 'leave' }">
			
			<!-- Page Heading -->
			<div class="row">
			   <div class="col-lg-12">
			      <h4 class="page-header"> 회원탈퇴</h4>
			   </div>
			</div><!-- /.row -->
			
			<div id="main-contents">
				
			<!-- ####################	 시작		#################### -->
			<div style="width: 300px; margin: auto;">
				<form action="/user/last" method="post">
					<input type="hidden" name="userid" value="${login.userid}">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">비밀번호</span> <input
							type="password" name="userpw" class="form-control"
							placeholder="비밀번호" aria-describedby="basic-addon2" id="userpw">
					</div>
		
					<div class="input-group"  style="padding-top: 20px;">
						<button type="submit" style="width: 100px; margin-left: 100px; margin-rigth: 100px;" class="btn btn-success">본인확인</button>
					</div>
				</form>
			</div>
			<!-- ####################	   끝     	#################### -->	
				
			</div><!-- /#main-contents -->
		</c:when>
	</c:choose>

</div>
</body>
</html>