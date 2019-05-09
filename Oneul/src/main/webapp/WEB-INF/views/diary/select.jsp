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
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 다이어리 선택</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<div class="panel panel-default">
	<div class="panel-body">
   
	<div>
		<table id="board_table" class="table">
			<col width="20%">
			<col width="80%">
			<thead id="myDiary">
				<tr>
					<td style="text-align: center; vertical-align: middle;"><i class="fa fa-fw fa-user"></i> 개인</td>
					<td>
						<form action="/diary/list" method="post">
					        <p><a href="javascript:;" onclick="parentNode.parentNode.submit();">${ login.nickname }님의 다이어리</a></p>
					    </form>				
					</td>
				</tr>
			</thead>
			<tbody id="groupDiary">
				<tr>
					<td style="text-align: center; vertical-align: middle;"><i class="fa fa-fw fa-users"></i> 그룹</td>
					<td>
					<c:forEach var="groupVO" items="${ groupList }">
						<form action="/diary/list" method="post">
					        <p><a href="javascript:;" onclick="parentNode.parentNode.submit();">${ groupVO.name } 다이어리</a></p>
					        <input type="hidden" name="group_no" value=${ groupVO.group_no } />
					    </form>
					</c:forEach>
					</td>
				</tr>
			</tbody>
			<tfoot id="randomDiary">
				<tr>
					<td style="text-align: center; vertical-align: middle;"><i class="fa fa-fw fa-random"></i> 랜덤</td>
					<td><a href="/diary/random"> 누군가의 다이어리</a></td>
				</tr>
			</tfoot>
		</table>
	</div>
	
	</div>
	</div>
		
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->

</div>
</body>
</html>
