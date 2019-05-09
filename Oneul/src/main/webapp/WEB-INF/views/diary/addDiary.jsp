<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<!-- infinite scroll -->
	<c:forEach var="diaryVO" items="${ diaryList }">
	<form action="/diary/read" method="post">
		<input type="hidden" name="diary_no" value="${ diaryVO.diary_no }">
		<div class="col-sm-6 col-md-3" onclick="parentNode.submit();">
		    <div class="thumbnail">
		    	<div class="caption">
					<b class="line-break" style="vertical-align: middle;"><i class="fa fa-fw fa-leaf"></i> ${ diaryVO.nickname }</b>
				</div>
				<hr class="caption-hr"/>
			    <c:choose>
			    	<c:when test="${ diaryVO.backgroundImg != null }">
			    		<div style="width: 100%; height: 100%; background-image: url('http://localhost/file/displayFile?fileName=${ diaryVO.backgroundImg }'); background-position: center; background-size: cover; background-repeat: no-repeat;">
			    			<img style="max-width: 100%; max-height:100%;" src="/resources/image/base.png" alt="..."></img>
			    		</div>
			    	</c:when>
			    	<c:otherwise>
			    		<div style="width: 100%; height: 100%; background-image: url('/resources/image/logo/lief_logo.png'); background-position: center; background-size: 50%; background-repeat: no-repeat;">
			    			<img style="max-width: 100%; max-height:100%;" src="/resources/image/base.png" alt="..."></img>
			    		</div>
			    	</c:otherwise>
			    </c:choose>
		      <div class="caption">
		      <hr class="caption-hr"/>
		        <h5><i class="fa fa-calendar fa-fw"></i> <fmt:formatDate value="${ diaryVO.regdate }" type="date" pattern="yyyy년 MM월 dd일 E요일" /></h5>
     			<h5><i class="fa fa-calendar fa-fw"></i> <fmt:formatDate value="${ diaryVO.regdate }" type="date" pattern="yyyy년 MM월 dd일 E요일" /></h5>
		        <p class="line-break"><i class="fa fa-pencil fa-fw"></i> ${ diaryVO.title }</p>
		        <h5 class="line-break"><i class="fa fa-map-marker fa-fw"></i> ${ diaryVO.location }</h5>
		      </div>
		    </div>
		  </div>	
		</form>
	</c:forEach>
</body>
</html>

