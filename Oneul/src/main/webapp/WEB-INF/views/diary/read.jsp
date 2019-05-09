<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var dno = ${ diaryVO.diary_no };	
	$.post("/comment/list?diary_no="+dno, {}, function(data) {
		$("#addComment").append(data);
	});
});
</script>
<title>글 읽기</title>
<style type="text/css">
.popup {position:absolute;}
.back {background-color:#000; opacity: 0.8; top:0; left:0; width: 100%; overflow: hidden; z-index: 1101;}
.front {z-index: 1110; left:0; top:0; opacity: 1; boarder: 1px; top:50%; left:50%; transform:translate(-50%, -50%)}
.diaryImg {	max-width: 50%; display: block;}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

<div class="popup back" style="display:none;"></div>
<div id="popup_front" class="popup front" style="display:none;">
	<img id="popup_img">
</div>

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 글 읽기</h4>
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
	
	<!-- comment alert -->
	<c:choose>
		<c:when test="${ commentWirteResult == 1 }">
			<script type="text/javascript">
				alert("작성하신 댓글이 등록되었습니다!")
			</script>
		</c:when>
		<c:when test="${ commentUpdateResult == 1 }">
			<script type="text/javascript">
				alert("댓글이 수정되었습니다!")
			</script>
		</c:when>
		<c:when test="${ commentDeleteResult == 1 }">
			<script type="text/javascript">
				alert("댓글이 삭제되었습니다!")
			</script>
		</c:when>
	</c:choose>
   
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
				<c:choose>
					<c:when test="${ login.group_no == null }">
						<tr>
							<td style="text-align: left; margin-left: 20px; padding-left: 20px"><i class="fa fa-lock fa-fw"></i> 공개여부</td>
							<td colspan="5" style="align: left; vertical-align: middle;">
							<c:choose>
								<c:when test="${ diaryVO.priv == 'T' }">비공개</c:when>
								<c:otherwise>공개</c:otherwise>
							</c:choose>					
							</td>
						</tr>
					</c:when>
				</c:choose>
			</tfoot>
		</table>
		
	</div>
	</div>
	</div>
	
	<!-- buttons -->
	<div>
		<button class="btn btn-default" id="list_btn" type="button">
			<span>목록</span>
		</button>
		<form id="backtolist" action="/diary/list" method="post" style="display: none">
			<input type="hidden" name="userid" value="${ diaryVO.userid }" />
			<input type="hidden" name="group_no" value="${ diaryVO.group_no }" />
		</form>
		<script type="text/javascript">
			$("#list_btn").on("click", function(e) {
				e.preventDefault();
				$("#backtolist").submit();
			});
		</script>
		
		<button id="btn_write" type="button" class="btn btn-success">
			<span>쓰기</span>
		</button>
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
								$("#gotowrite").submit();;
							} else {
								alert("하루에 한 개의 글만 작성하실 수 있습니다.");
							}
						}
					});
				});
		</script>
		
		<button class="btn btn-default" id="update_btn" type="button"
			onclick="location.href='/diary/update?diary_no=${ diaryVO.diary_no }';">
			<span>수정</span>
		</button>
		<form id="gotoupdate" action="/diary/update" method="post" style="display: none">
			<input type="hidden" name="diary_no" value="${ diaryVO.diary_no }" />
		</form>
		<script type="text/javascript">
			$("#update_btn").on("click", function(e) {
				e.preventDefault();
				$("#gotoupdate").submit();
			});
		</script>
		
		<button class="btn btn-default" id="delete_btn" type="button" onclick="delete_diary();">
			<span>삭제</span>
		</button>
		<form id="gotodelete" action="/diary/delete" method="post" style="display: none">
			<input type="hidden" name="userid" value="${ diaryVO.userid }" />
			<input type="hidden" name="group_no" value="${ diaryVO.group_no }" />
			<input type="hidden" name="diary_no" value="${ diaryVO.diary_no }" />
		</form>
		<script type="text/javascript">
			function delete_diary(){
				if (confirm("정말 삭제하시겠습니까?") == true){
					
					var diary_no = ${diaryVO.diary_no};
					var arr = [];
					$(".uploadedList li").each(function(index) {
						arr.push($(this).attr("data-src"));
					});
					
					if(arr.length > 0) {
						$.post("/file/deleteAllFiles", {files:arr}, function() {
							
						});
					}
					
  						$("#gotodelete").submit();
				} else {
   					return;
				}
			}
		</script>
	</div>
	
	<br /><br />

	<!-- Comments -->
	<c:choose>
		<c:when test="${ login.group_no != null }">		
			<div >
			    <div class="panel panel-green">
			        <div class="panel-heading">
			            <h3 class="panel-title"><i class="fa fa-commenting-o fa-fw"></i> Comments</h3>
			        </div>
			        <div class="panel-body">

						<div id="addComment">
						</div>

					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
	<!-- comment -->

	</div><!-- /#main-contents -->
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 
	{{getLink}} : /yyyy/MM/dd/오라클에 저장된 원본 파일이름.확장자
	{{fullName}} : 파일 이름.확장자 (오라클에 저장된 이름)
-->
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src="{{fullName}}">
  <span class="mailbox-attachment-icon has-img"><img class="diaryImg" src="{{imgsrc}}" alt="deleted"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name line-break">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	var diary_no = ${diaryVO.diary_no};
	var template = Handlebars.compile($("#templateAttach").html());

	$.getJSON("/diary/getAttach/" + diary_no, function(list) {
		$(list).each(function() {

			var fileInfo = getFileInfo(this);
			var html = template(fileInfo);

			$(".uploadedList").append(html);
		});
	});

	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event) {

		var fileLink = $(this).attr("href");

		if (checkImageType(fileLink)) {

			event.preventDefault();

			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);

			console.log(imgTag.attr("src"));
			
			var body = document.body,
			html = document.documentElement;

			var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
			$(".back").css("height", height);
			
			var marginTop = $(window).scrollTop();
			$(".front").css("margin-top", marginTop);
			
			$(".popup").show();
			imgTag.addClass("show");
		}
	});

	$(".popup").on("click", function() {
		$(".popup").hide();
	});

});
</script>
</body>
</html>