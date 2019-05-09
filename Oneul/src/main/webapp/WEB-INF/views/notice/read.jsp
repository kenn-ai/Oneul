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

	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 공지사항</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<div class="panel panel-default">
	<div class="panel-body">
	
	<div>
		<table id="board_table" class="table">
			<col width="10%">
			<col width="90%">
			<thead>
				<tr>
					<td style="text-align: center;">글번호</td>
					<td align="left">${ noticeVO.notice_no }</td>
				<tr>
					<td style="text-align: center;">조회수</td>
					<td align="left">${ noticeVO.hit }</td>
				<tr>
					<td style="text-align: center;">제목</td>
					<td align="left">${ noticeVO.title }</td>
				<tr>
					<td style="text-align: center;">날짜</td>
					<td align="left"><fmt:formatDate value="${ noticeVO.regdate }" type="date" pattern="yy-MM-dd HH:mm" /></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">
					<pre style="min-height:500px; margin-top:10px; padding: 20px; background-color: white; border: none;">${ noticeVO.contents }</pre>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	</div>
	</div>
	
	<!-- buttons -->
	
		<button class="btn btn-default" id="list_btn" type="button"
			onclick="location.href='/notice/list';">
			<span>목록</span>
		</button>
		
		<c:choose>
			<c:when test="${ login.authority == 'ADMIN' }">
				<button class="btn btn-default" id="btn_write" type="button"
					onclick="location.href='/notice/write';">
					<span>쓰기</span>
				</button>
				<button class="btn btn-default" id="btn_update" type="button"
					onclick="location.href='/notice/update?notice_no=${ noticeVO.notice_no}';">
					<span>수정</span>
				</button>
				<button class="btn btn-default" id="btn_delete" type="button"
					onclick="location.href='/notice/delete?notice_no=${ noticeVO.notice_no }';">
					<span>삭제</span>
				</button>
			</c:when>
		</c:choose>
	
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>
</body>
</html>
