<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>그룹 만들기</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 그룹 만들기</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->		
		
	<form action="/group/actMake" method="post">
	<div class="col-sm-10">
		<input type="text" class="form-control" name="name" id="name" placeholder="새로운 그룹이름"/>
	</div>
	<div class="col-sm-1">
		<button type="submit" class="btn btn-success">그룹 생성</button>
	</div>
	</form>
		
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
	
</div>
</body>
</html>
