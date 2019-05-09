<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>TITLE</title>
</head>
<body>
<div id="wrapper">
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div id="page-wrapper">
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h1 class="page-header"> User Leave Page <small>Subheading</small></h1>
	      <ol class="breadcrumb">
	         <li><i class="fa fa-dashboard"></i> <a href="#">Dashboard</a></li>
	         <li class="active"><i class="fa fa-file"></i> Blank Page</li>
	      </ol>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	<div style="text-align: center;">
		<h1>탈퇴전 마지막 페이지입니다.</h1>
		
		<h2>아래 버튼을 누르면 모든 기록이 영구히 사라집니다.</h2>
		
		<h3>보존하고 싶으신 기록이 있다면 돌아가 백업해주세요.</h3>
	</div>
	<div style="width: 200px; text-align: center;">
		<form action="/user/actLeave" method="post">
			<input type="hidden" name="userid" value="${login.userid}">
			<div class="input-group">
				<button type="submit" class="btn btn-success">탈퇴</button>
			</div>
		</form>
	</div>
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->

</div> <!-- /.container-fluid -->
</div> <!-- /#page-wrapper -->
</div>
</body>
</html>
