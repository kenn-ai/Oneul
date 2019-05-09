<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
<title>자주 묻는 질문</title>
<style>
.faq_title{
	cursor:pointer;
}
.faq_contents{
	display:none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$(".faq_title").click(function(){
		var contents = $(this).next("tr");
		if(contents.is(":visible")) {
			contents.hide();
		} else {
			contents.show();
		}
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 자주 묻는 질문</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->

	<div class="panel panel-default">
	<div class="panel-body">
	
	<div>
		<table class="table table-hover">
			<col width="8%">
			<col width="70%">
			<col width="20%">
			<thead>
				<tr>
					<th style="text-align: center">글 번호</th>
					<th style="text-align: center">제목</th>
					<th style="text-align: center">작성일</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="faqVO" items="${ faqList }">
				<tr class="faq_title">
					<td align="center">${ faqVO.faq_no }</td>
					<td style="margin-left: 5px">${ faqVO.title }</td>
					<td align="center"><fmt:formatDate value="${ faqVO.regdate }" type="date" pattern="yy-MM-dd HH:mm" /></td>
				</tr>
				<tr class="faq_contents">
					<td></td>
					<td colspan="2">
					${ faqVO.contents }
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	</div>
	</div>
		
	<!-- ####################	   끝     	#################### -->	

	</div><!-- /#main-contents -->

</div>
</body>
</html>
