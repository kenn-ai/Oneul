<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
<title>글 목록</title>
<c:choose>
	<c:when test="${ login.group_no == null }">
		<script type="text/javascript">
			$(document).ready(function(){
				
				var start = ${diaryPageMaker.startPage};
				var end = ${diaryPageMaker.endPage};
				var idx = start+1;
				
				$(window).scroll(function(){
					
					if(($(document).height()-$(window).height()) == $(window).scrollTop()) {
						setTimeout(function(){
							
							if(idx <= end) {
								$.post("/diary/addList?&page="+idx, {}, function(data) {
									$("#addList").append(data);
									idx++;
								});
							}	
							
						}, 300)
					}
				});
			});
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			$(document).ready(function(){
				
				var start = ${diaryPageMaker.startPage};
				var end = ${diaryPageMaker.endPage};
				var idx = start+1;
				var gno = ${login.group_no};
				
				$(window).scroll(function(){
					
					if(($(document).height()-$(window).height()) == $(window).scrollTop()) {
						setTimeout(function(){
							
							if(idx <= end) {
								$.post("/diary/addList?&page="+idx+"&group_no="+gno, {}, function(data) {
									$("#addList").append(data);
									idx++;
								});
							}	
							
						}, 300)
					}		
				});
			});
		</script>
	</c:otherwise>
</c:choose>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
			<!-- group -->			
			<c:choose>
				<c:when test="${ login.group_no != null}">
					<h4 class="page-header">${ groupVO.name } 다이어리</h4>
				</c:when>
				<c:otherwise>
					<h4 class="page-header"> ${ login.nickname }님의 다이어리</h4>
				</c:otherwise>
			</c:choose>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
	<!-- alert -->
	<c:choose>
		<c:when test="${ writeResult == 1 }">
			<script type="text/javascript">
				alert("작성하신 글이 등록되었습니다!")
			</script>
		</c:when>
		<c:when test="${ updateResult == 1 }">
			<script type="text/javascript">
				alert("글이 수정되었습니다!")
			</script>
		</c:when>
		<c:when test="${ deleteResult == 1 }">
			<script type="text/javascript">
				alert("글이 삭제되었습니다!")
			</script>
		</c:when>
	</c:choose>

	<!-- search -->
		<div id="searchForm" class="row" style="margin: auto;">
		<div class="col-md-12">
			<form action="/diary/search" method="post">
				<input type="hidden" name="userid" value="${ login.userid }">
				<input type="hidden" name="group_no" value="${ login.group_no }">
				<select class="form-control" id="year" name="year" style="width:120px; float: left; margin-right: 5px; margin-bottom: 5px">
					<option>년</option>
					<option>2016</option>
					<option>2017</option>
					<option>2018</option>
				</select> 
				<select class="form-control" id="month" name="month" style="width:100px; float: left; margin-right: 5px; margin-bottom: 5px">
					<option>월</option>
					<option>01</option>
					<option>02</option>
					<option>03</option>
					<option>04</option>
					<option>05</option>
					<option>06</option>
					<option>07</option>
					<option>08</option>
					<option>09</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
				</select> 
				<select class="form-control" id="day" name="day" style="width:100px; float: left; margin-right: 5px; margin-bottom: 5px">
					<option>일</option>
					<option>01</option>
					<option>02</option>
					<option>03</option>
					<option>04</option>
					<option>05</option>
					<option>06</option>
					<option>07</option>
					<option>08</option>
					<option>09</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
					<option>13</option>
					<option>14</option>
					<option>15</option>
					<option>16</option>
					<option>17</option>
					<option>18</option>
					<option>19</option>
					<option>20</option>
					<option>21</option>
					<option>22</option>
					<option>23</option>
					<option>24</option>
					<option>25</option>
					<option>26</option>
					<option>27</option>
					<option>28</option>
					<option>29</option>
					<option>30</option>
					<option>31</option>
				</select>
				<c:choose>
					<c:when test="${ login.group_no != null}">
					<select class="form-control" id="nickname" name="nickname" style="width:200px; float: left; margin-right: 5px; margin-bottom: 5px">
						<option>멤버</option>
						<c:forEach var="memberVO" items="${ memberList }">
							<option>${ memberVO.nickname }</option>
						</c:forEach>
					</select>
					</c:when>
				</c:choose>
					<input class="form-control" type="text" id="searchWord" name="searchWord" placeholder="검색어를 입력하세요" style="width:320px; float: left; margin-right: 5px; margin-bottom: 5px">
					<input class="btn btn-success" type="submit" name="search" value="검색" style="float: left; margin-right: 5px; margin-bottom: 5px">
			</form>
		</div>
	</div>
	
	<div style="clear: both;">
	<div class="row" style="margin: auto; margin-top: 15px">
		<div class="col-md-12">
		<button id="btn_write" style="width: 100%;" type="button" class="btn btn-success"><i class="fa fa-plus fa-fw"></i> 새 글 남기기</button>
		<form id="gotowrite" action="/diary/write" method="post" style="display: none">
			<input type="hidden" name="group_no" value="${ login.group_no }" />
		</form>	
		<script type="text/javascript">
				$("#btn_write").on("click", function(e) {
					e.preventDefault();
					
					var group_no = ${empty login.group_no ? 0 : login.group_no };
					
					$.ajax({
						url : "/diary/check",
						data : {group_no:group_no},
						type: 'POST',
						success : function(result){
							if(result == 0) {
								$("#gotowrite").submit();
							} else {
								alert("하루에 한 개의 글만 작성할 수 있습니다");
							}
						}
					});
				});
			</script>
		</div>
	</div><!-- /.row -->      
	 
	<br/>
	 
	 	<!-- infinite scroll -->
		<div id="listBox">
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
					    		<div style="width: 100%; height: 100%; background-image: url('/resources/image/logo/lief_logo_01.png'); background-position: center; background-size: 50%; background-repeat: no-repeat;">
					    			<img style="max-width: 100%; max-height:100%;" src="/resources/image/base.png" alt="..."></img>
					    		</div>
					    	</c:otherwise>
					    </c:choose>
				      <div class="caption">
				      <hr class="caption-hr"/>
				        <h5><i class="fa fa-calendar fa-fw"></i> <fmt:formatDate value="${ diaryVO.regdate }" type="date" pattern="yyyy년 MM월 dd일 E요일" /></h5>
				        <p class="line-break"><i class="fa fa-pencil fa-fw"></i> ${ diaryVO.title }</p>
				        <h5 class="line-break"><i class="fa fa-map-marker fa-fw"></i> ${ diaryVO.location }</h5>
				      </div>
					</div>
				</div>
			</form>
			</c:forEach>
		</div><!-- infinite scroll -->
		<div id="addList">
		
		</div>
			
	</div>
	
	<!-- ####################	   끝     	#################### -->	
	  	
	</div><!-- /#main-contents -->

</div>
</body>
</html>
