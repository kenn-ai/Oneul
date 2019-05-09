<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>그룹목록</title>
<style type="text/css">
#createGroup {
	width: 100%; 
	margin: 5px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 그룹 목록</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<h4><b>${ login.nickname }</b>님의 그룹 목록</h4>
	
	<div>
		<table class="table">
			<col width="80%">
			<col width="20%">
			<thead>
				<tr>
					<th style="text-align: center">그룹이름</th>
					<th style="text-align: center">생성일</th>
				</tr>
			</thead>
			<c:forEach var="groupVO" items="${ groupList }">
				<tr>
					<td><i class="fa fa-fw fa-leaf"></i><a href="/group/info?group_no=${ groupVO.group_no }" style="margin-left: 20px">${ groupVO.name }</a></td>
					<td align="center"><fmt:formatDate value="${ groupVO.regdate }" type="date" pattern="yy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
		<form action="/group/make" method="get">
			<button style="width: 100%" type="submit" class="btn btn-success" id="createGroup"> 새 그룹 만들기</button>
		</form>
		<hr/>
	</div>   
   	
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>
</body>
</html>
