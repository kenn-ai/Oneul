<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">
	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 공지사항</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<!-- alert -->
	<c:choose>
		<c:when test="${ noticeWriteResult == 1 }">
			<script type="text/javascript">
				alert("작성하신 글이 등록되었습니다!")
			</script>
		</c:when>
		<c:when test="${ noticeUpdateResult == 1 }">
			<script type="text/javascript">
				alert("글이 수정되었습니다!")
			</script>
		</c:when>
		<c:when test="${ noticeDeleteResult == 1 }">
			<script type="text/javascript">
				alert("글이 삭제되었습니다!")
			</script>
		</c:when>
	</c:choose>

	<!-- Table -->
	
	<div class="panel panel-default">
	<div class="panel-body">
	
	<div>
		<table class="table">
			<col width="7%">
			<col width="70%">
			<col width="7%">
			<col width="16%">

			<thead>
				<tr>
					<th style="text-align: center">글 번호</th>
					<th style="text-align: center">제목</th>
					<th style="text-align: center">조회수</th>
					<th style="text-align: center">작성일</th>
				</tr>
			</thead>
			<c:forEach var="noticeVO" items="${ noticeList }">
				<tr>
					<td align="center">${ noticeVO.notice_no }</td>
					<td style="margin-left: 5px"><a href="/notice/read${pageMaker.makeQuery(pageMaker.cri.page)}&notice_no=${ noticeVO.notice_no }">${ noticeVO.title }</a></td>
					<td align="center">${ noticeVO.hit }</td>
					<td align="center"><fmt:formatDate value="${ noticeVO.regdate }" type="date" pattern="yy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</div>

	<!-- 페이지네이션 시작 -->
	<div class="text-center">
	<ul class="pagination">
		<c:if test="${pageMaker.prev}">
			<li><a href="list?page=${pageMaker.startPage - 1}">&laquo;</a></li>
		</c:if>
		
		<c:forEach begin="${ pageMaker.startPage}" end="${ pageMaker.endPage }" var="idx">
			<li 
				<c:out value="${pageMaker.cri.page == idx?'class=active':''}"/>>
				<a href="list?page=${idx}">${idx} </a>			
			</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="list?page=${pageMaker.endPage +1 }">&raquo;</a></li>
		</c:if>
	</ul>
	</div>
	<!-- 페이지네이션 끝 -->
	
	<div>
		<div class="form-group">
			<c:choose>
				<c:when test="${ login.authority == 'ADMIN' }">
					<button id="write_btn" type="button" class="btn btn-primary" onclick="location.href='/notice/write'">
						<span>글쓰기</span>
					</button>
				</c:when>
			</c:choose>
		</div>
	</div>

	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>
</body>
</html>
