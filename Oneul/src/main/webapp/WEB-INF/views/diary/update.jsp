<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<title>글 고치기</title>
<style type="text/css">
.fileDrop {width: 100%; height: 100px; border: 1px dotted gray; margin: auto;}
.popup {position:absolute;}
.back {background-color:#000; opacity: 0.8; top:0; left:0; width: 100%; overflow: hidden; z-index: 1101;}
.front {z-index: 1110; left:0; top:0; opacity: 1; boarder: 1px; top:50%; left:50%; transform:translate(-50%, -50%)}
.diaryImg {	max-width: 50%; display: block;}
</style>
</head>
<body>
<jsp:useBean id="today" class="java.util.Date" />
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

<div class="popup back" style="display:none;"></div>
<div id="popup_front" class="popup front" style="display:none;">
	<img id="popup_img">
</div>

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 글 고치기</h4>
	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->   	
   
	<form id="updateForm" class="form-horizontal" action="/diary/actUpdate" method="post">
		<input type="hidden" name="diary_no" value="${ diaryVO.diary_no }">
		<input type="hidden" name="userid" value="${ login.userid }">
		<input type="hidden" name="nickname" value="${ login.nickname }">
		<input type="hidden" name="group_no" value="${ diaryVO.group_no }">
		<input type="hidden" id="weather" name="weather" value="${ diaryVO.weather }">
		<input type="hidden" id="emotion" name="emotion" value="${ diaryVO.emotion }">
		
		<div class="form-group">
			<div class="col-sm-11">
				<label for="regdate" class="col-sm-1 control-label">날짜</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="location" name="regdate" value="<fmt:formatDate value='${today}' type='date' pattern='yyyy년 MM월 dd일 E요일 HH:mm' />" disabled>
				</div>
				
				<!-- 날씨 -->
				<div class="col-sm-2">
				<label style="margin-right: 20px" for="weather" class="control-label">날씨</label>
					<button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#modal_weather">
					<img id="img_weather" src="/resources/image/weather/weather_${ diaryVO.weather}.png"></button>
					
					<div class="col-sm-10">
						<div class="modal fade" id="modal_weather" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" style="width: auto;">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">×</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">오늘, 날씨는 어땠나요?</h4>
									</div>
									<div class="modal-body">
										<table style="margin: auto;">
											<col width="20%">
											<col width="20%">
											<col width="20%">
											<col width="20%">
											<tbody>
												<tr>
													<td style="text-align: center;"><img id="default" class="weather" src="/resources/image/weather/weather_default.png"><h6>그냥</h6></td>
													<td style="text-align: center;"><img id="sun" class="weather" src="/resources/image/weather/weather_sun.png"><h6>맑음</h6></td>
													<td style="text-align: center;"><img id="moon" class="weather" src="/resources/image/weather/weather_moon.png"><h6>예쁜달</h6></td>
													<td style="text-align: center;"><img id="star" class="weather" src="/resources/image/weather/weather_star.png"><h6>별똥별</h6></td>
												<tr>
													<td style="text-align: center;"><img id="cloudy" class="weather" src="/resources/image/weather/weather_cloudy.png"><h6>흐림</h6></td>
													<td style="text-align: center;"><img id="rain" class="weather" src="/resources/image/weather/weather_rain.png"><h6>비</h6></td>
													<td style="text-align: center;"><img id="snow" class="weather" src="/resources/image/weather/weather_snow.png"><h6>눈</h6></td>
													<td style="text-align: center;"><img id="lightening" class="weather" src="/resources/image/weather/weather_lightening.png"><h6>번개</h6></td>	
												<tr>
													<td style="text-align: center;"><img id="windy" class="weather" src="/resources/image/weather/weather_windy.png"><h6>바람</h6></td>
													<td style="text-align: center;"><img id="hurricane" class="weather" src="/resources/image/weather/weather_hurricane.png"><h6>태풍</h6></td>
													<td style="text-align: center;"><img id="snowman" class="weather" src="/resources/image/weather/weather_snowman.png"><h6>첫눈</h6></td>
													<td style="text-align: center;"><img id="snowflake" class="weather" src="/resources/image/weather/weather_snowflake.png"><h6>꽁꽁</h6></td>	
												<tr>
													<td style="text-align: center;"><img id="earthquake" class="weather" src="/resources/image/weather/weather_earthquake.png"><h6>지진</h6></td>
													<td style="text-align: center;"><img id="volcano" class="weather" src="/resources/image/weather/weather_volcano.png"><h6>화산</h6></td>
													<td style="text-align: center;"><img id="dusty" class="weather" src="/resources/image/weather/weather_dusty.png"><h6>미세먼지</h6></td>
													<td style="text-align: center;"><img id="rainbow" class="weather" src="/resources/image/weather/weather_rainbow.png"><h6>무지개</h6></td>
												</tr>
											</tbody>
										</table>
										<script type="text/javascript">
											$(".weather").on("click", function() {
												var value_weather = $(this).attr("id");
												var src_weather = $(this).attr("src");
												$("#img_weather").attr("src", src_weather);
												$("#weather").val(value_weather);
												$("#modal_weather").modal("hide");
											});
										</script>
									</div>
									<div class="modal-footer">
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				<!-- 날씨끝 -->
				
				<!-- 기분 -->
				<div class="col-sm-2">
				<label style="margin-right: 20px" for="emotion" class="control-label">기분</label>
					<button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#modal_emotion">
					<img id="img_emotion" src="/resources/image/emotion/emotion_${ diaryVO.emotion }.png"></button>
					
					<div class="col-sm-10">
						<div class="modal fade" id="modal_emotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">×</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">오늘, 기분은 어땠나요?</h4>
									</div>
									<div class="modal-body">
										<table style="margin: auto;">
											<col width="20%">
											<col width="20%">
											<col width="20%">
											<col width="20%">
											<col width="20%">
											<tbody>
												<tr>
													<td style="text-align: center;"><img id="default" class="emotion" src="/resources/image/emotion/emotion_default.png"><h6>그냥</h6></td>
													<td style="text-align: center;"><img id="sad" class="emotion" src="/resources/image/emotion/emotion_sad.png"><h6>나쁨</h6></td>
													<td style="text-align: center;"><img id="good" class="emotion" src="/resources/image/emotion/emotion_good.png"><h6>좋음</h6></td>
													<td style="text-align: center;"><img id="happy" class="emotion" src="/resources/image/emotion/emotion_happy.png"><h6>행복</h6></td>	
													<td style="text-align: center;"><img id="deep_in_love" class="emotion" src="/resources/image/emotion/emotion_deep_in_love.png"><h6>사랑</h6></td>									
												<tr>
													<td style="text-align: center;"><img id="tears" class="emotion" src="/resources/image/emotion/emotion_tears.png"><h6>우울</h6></td>
													<td style="text-align: center;"><img id="embarrassed" class="emotion" src="/resources/image/emotion/emotion_embarrassed.png"><h6>당황</h6></td>
													<td style="text-align: center;"><img id="crying" class="emotion" src="/resources/image/emotion/emotion_crying.png"><h6>눈물</h6></td>
													<td style="text-align: center;"><img id="surprised" class="emotion" src="/resources/image/emotion/emotion_surprised.png"><h6>놀람</h6></td>	
													<td style="text-align: center;"><img id="shocked" class="emotion" src="/resources/image/emotion/emotion_shocked.png"><h6>뜨억</h6></td>	
												<tr>
													<td style="text-align: center;"><img id="bad" class="emotion" src="/resources/image/emotion/emotion_bad.png"><h6>흐음</h6></td>
													<td style="text-align: center;"><img id="sour" class="emotion" src="/resources/image/emotion/emotion_sour.png"><h6>떨떠름</h6></td>
													<td style="text-align: center;"><img id="angry" class="emotion" src="/resources/image/emotion/emotion_angry.png"><h6>버럭</h6></td>
													<td style="text-align: center;"><img id="annoy" class="emotion" src="/resources/image/emotion/emotion_annoy.png"><h6>빠직</h6></td>	
													<td style="text-align: center;"><img id="irritation" class="emotion" src="/resources/image/emotion/emotion_irritation.png"><h6>불쾌</h6></td>	
												<tr>
													<td style="text-align: center;"><img id="sleepy" class="emotion" src="/resources/image/emotion/emotion_sleepy.png"><h6>졸림</h6></td>
													<td style="text-align: center;"><img id="cadaverousness" class="emotion" src="/resources/image/emotion/emotion_cadaverousness.png"><h6>꽥</h6></td>
													<td style="text-align: center;"><img id="vomit" class="emotion" src="/resources/image/emotion/emotion_vomit.png"><h6>만신창이</h6></td>
													<td style="text-align: center;"><img id="death" class="emotion" src="/resources/image/emotion/emotion_death.png"><h6>죽기직전</h6></td>	
													<td style="text-align: center;"><img id="devil" class="emotion" src="/resources/image/emotion/emotion_devil.png"><h6>크크크</h6></td>	
												</tr>
											</tbody>
										</table>
										<script type="text/javascript">
											$(".emotion").on("click", function() {
												var value_emotion = $(this).attr("id");
												var src_emotion = $(this).attr("src");
												$("#img_emotion").attr("src", src_emotion);
												$("#emotion").val(value_emotion);
												$("#modal_emotion").modal("hide");
											});
										</script>
									</div>
									<div class="modal-footer">
									</div>
								</div>
							</div>
						</div>
					</div>
							
				</div>
				<!-- 기분끝 -->
			</div>
		</div>
		<!--  -->
		
		<div class="form-group">
			<label for="location" class="col-sm-1 control-label">지역</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="location" name="location" placeholder="지역" value="${ diaryVO.location }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="title" class="col-sm-1 control-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목" value="${ diaryVO.title }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="contents" class="col-sm-1 control-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="contents" name="contents" rows="40" placeholder="내용" style="width: 100%">${ diaryVO.contents }</textarea>
				<!-- smarteditor -->
				<script type="text/javascript">
				var oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
				    oAppRef: oEditors,
				    elPlaceHolder: "contents",
				    sSkinURI: "/resources/smarteditor/SmartEditor2Skin.html",
				    fCreator: "createSEditor2"
				});
				</script>
				<!-- end of smarteditor -->
			</div>
		</div>
		
		<!-- File Upload start -->
		<div class="form-group">
			<label for="fileDrop" class="col-sm-1 control-label">파일 첨부</label>
			<div class="col-sm-10">
				<div class="fileDrop"></div>
			</div>
		</div>
		
		<div><hr/></div>
		
		<div class="form-group">
			<label for="uploadedList" class="col-sm-1 control-label">첨부 목록</label>
			<div class="col-sm-10">
				<ul class="mailbox-attachments clearfix uploadedList"></ul>
			</div>
		</div>
		<!-- end of File Upload -->
		
		<div class="form-group">
			<label for="link" class="col-sm-1 control-label">링크</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="link" name="link" placeholder="링크" value="${ diaryVO.link }">
			</div>
		</div>
		
		<div class="form-group">
			<label for="tag" class="col-sm-1 control-label">태그</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="tag" name="tag" placeholder="태그" value="${ diaryVO.tag }">
			</div>
		</div>
		
		
		<c:choose>
			<c:when test="${ login.group_no == null }">
				<div class="form-group">
					<label for="priv" class="col-sm-1 control-label">공개여부</label>
					<div class="col-sm-10">
						<select class="form-control" name="priv">
							<option>비공개</option>
							<option>공개</option>
						</select>
					</div>
				</div>	
			</c:when>
		</c:choose>

		<br />
		
		<div>
		<button type="submit" id="btn_submit" class="btn btn-success">
			<span>등록</span>
		</button>
		<button id="back_btn"class="btn btn-default">
			<span>취소</span>
		</button>
			<script type="text/javascript">
				$("#back_btn").on("click", function(e) {
					e.preventDefault();
					$("#backtolist").submit();
				});
			</script>								
		</div>
	</form>
	<form id="backtolist" action="/diary/list" method="post" style="display: none">
		<input type="hidden" name="userid" value="${ diaryVO.userid }" />
		<input type="hidden" name="group_no" value="${ diaryVO.group_no }" />
	</form>

	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 
	{{getLink}} : /yyyy/MM/dd/오라클에 저장된 원본 파일이름.확장자
	{{fullName}} : 파일 이름.확장자 (오라클에 저장된 이름)
-->
<script id="template" type="text/x-handlebars-template">
<li data-src="{{fullName}}">
  <div class="mailbox-attachment-icon has-img"><img class="diaryImg" src="{{imgsrc}}" alt="deleted"></div>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name line-break">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>   

<script>
var template = Handlebars.compile($("#template").html());
var arr = [];

$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});

$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];
	
	var formData = new FormData();
	
	formData.append("file", file);	
	
	$.ajax({
		  url: '/file/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  
			  var html = template(fileInfo);
			  
			  $(".uploadedList").append(html);
		  }
	});	
});

$(".uploadedList").on("click", ".delbtn", function(event){
	
	event.preventDefault();
	
	var that = $(this);
	
	if (confirm("정말 삭제하시겠습니까?") == true){	
		arr.push($(this).attr("href"));
		that.closest("li").remove();
	} else {
		return;
	}
});

var diary_no = ${diaryVO.diary_no};
var template = Handlebars.compile($("#template").html());

$.getJSON("/diary/getAttach/"+diary_no,function(list){
	$(list).each(function(){
		
		var fileInfo = getFileInfo(this);
		
		var html = template(fileInfo);
		
		 $(".uploadedList").append(html);
		
	});
});

$(".uploadedList").on("click", ".mailbox-attachment-name", function(event) {

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

$("#btn_submit").on("click", function(e) {
	e.preventDefault();
	
	if($("#title").val() == "") {
		alert("글에 제목을 붙여주세요!");
	} else {
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
		
		var that = $(this);
		
		var str ="";
		$(".uploadedList .delbtn").each(function(index){
			 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
		});
		
		that.append(str);
	
		console.log(str);
		
		if(arr.length > 0) {
			$.post("/file/deleteAllFiles", {files:arr}, function() {
				
			});
		}
		
		$("#updateForm").submit();
	}
});

</script>
</body>
</html>
