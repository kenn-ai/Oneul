<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>누군가의 다이어리</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">
	
	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 누군가의 다이어리</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<div class="panel panel-default">
	<div class="panel-body">
   
	<div>
		<table id="board_table" class="table">
			<col width="10%">
			<col width="58%">
			<col width="8%">
			<col width="8%">
			<col width="8%">
			<col width="8%">
			<thead>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px;"><i class="fa fa-fw fa-leaf"></i> 글쓴이</td>
					<td style="align: left; vertical-align: middle;"><b>${ diaryVO.nickname }</b></td>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px;"><i class="fa fa-calendar fa-fw"></i> 날짜</td>
					<td style="align: left; vertical-align: middle;"><fmt:formatDate value="${ diaryVO.regdate }" type="date" pattern="yyyy년 MM월 dd일 E요일 HH:mm" /></td>
					<td style="text-align: left; vertical-align: middle;">날씨</td>
					<td style="align: left; vertical-align: middle;"><img src="/resources/image/weather/weather_${ diaryVO.weather }.png" onerror="this.src='/resources/image/icon/question_mark_black.png'"></td>
					<td style="text-align: left; vertical-align: middle;">기분</td>
					<td style="align: left; vertical-align: middle;"><img src="/resources/image/emotion/emotion_${ diaryVO.emotion }.png" onerror="this.src='/resources/image/icon/question_mark_black.png'"></td>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px"><i class="fa fa-map-marker fa-fw"></i>장소</td>
					<td colspan="5" style="align: left; vertical-align: middle;">${ diaryVO.location }</td>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px"><i class="fa fa-quote-left fa-fw"></i> 제목</td>
					<td colspan="5" style="align: left; vertical-align: middle;">${ diaryVO.title }</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6">
					<pre style="min-height:500px; margin-top:10px; padding: 20px; background-color: white; border: none;">${ diaryVO.contents }</pre>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px"><i class="fa fa-paperclip fa-fw"></i> 첨부</td>
					<td colspan="5" style="align: left; vertical-align: middle;">
						<ul class="mailbox-attachments clearfix uploadedList"></ul>
					</td>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px"><i class="fa fa-link fa-fw"></i> 링크</td>
					<td colspan="5" style="align: left; vertical-align: middle;">${ diaryVO.link }</td>
				<tr>
					<td style="text-align: left; vertical-align: middle; padding-left: 20px"><i class="fa fa-tag fa-fw"></i> 태그</td>
					<td colspan="5" style="align: left; vertical-align: middle;">${ diaryVO.tag }</td>
				</tr>
			</tfoot>
		</table>
		
	</div>
	</div>
	</div>
	
	<!-- buttons -->
	<div>
		<button class="btn btn-default" id="list_btn" type="button"
			onclick="location.href='/home';">
			<span>홈</span>
		</button>
		<button class="btn btn-success" id="list_btn" type="button"
			onclick="location.reload();">
			<span>다른 글 보기</span>
		</button>
	</div>
			
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>
</body>
</html>
