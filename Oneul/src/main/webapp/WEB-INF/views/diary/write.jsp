<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/write-material/write-material.css?ver=2" rel="stylesheet" />
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<title>글 남기기</title>
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
<!-- 글감 버튼 -->
<div id="floatMenu">
	<img id="news"  alt="사진없음" src="/resources/image/icon/menu_news.png"  style="margin:3px; width:64px">
	<img id="photo" alt="사진없음" src="/resources/image/icon/menu_photo.png" style="margin:3px; width:64px">
	<img id="video" alt="사진없음" src="/resources/image/icon/menu_video.png" style="margin:3px; width:64px">
</div>
<script type="text/javascript">
	var currentPosition = parseInt($("#floatMenu").css("top"));
	$(window).scroll(function() {
		var position = $(window).scrollTop();
		$("#floatMenu").stop().animate({"top":position+currentPosition+"px"},1000);
	});
</script>
<!--  초희 글감 버튼 끝!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
<!-- 이미지 불러오기 스크립트 -->
<script type="text/javascript">
$(function(){
	$.ajax({
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : "/writeMaterial/imageMaterial",
		success : function(xml){
			xml=$(xml);
			
			xml.find("item").each(function(){
				
				for(var i=0; i<1; i++){
					var src=$(this).find("description").text();
					var title=$(this).find("title").text();
					/* alert(a); */
					var rE=/<a[^>]*href=['"]?([^'"]+)['"]?[^'"]?><img[^>]*src=['"]?([^'"]+)['"]* alt=['"]?([^'"]+)['"]?[^'"]+<\/a>/;
					var cutSrc=src.match(rE);
					var resultCutSrc=cutSrc[2];
					
					var addCutSrc = resultCutSrc.substring(0, resultCutSrc.lastIndexOf("?"));
					$(".photo-Content").attr("src",addCutSrc );
					$("#photoCaption").text(title);
					
				}
				return false;
				
			});
		}
	});
});
</script>
<!--이미지 몸 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  -->
<div id="photoModal" class="photoModal2">
   <img class="photo-Content">
  <div id="photoCaption"></div>
</div>
<br/>
<!-- 이미지 몸 끝  -->
<!-- 이미지 몸 클릭 스크립트 -->
<script type="text/javascript">
//Get the modal
var photoModal = document.getElementById('photoModal');
// Get the image and insert it inside the modal - use its "alt" text as a caption
var photoImg = document.getElementById('photo');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
photoImg.onclick = function(){
	photoModal.style.display = "block";
	/* $("#caption").text($("#photoModal img").attr("alt")); */
	$("#photoModal a").attr("target","_blank");
}

</script>
<!-- 이미지 몸 클릭 스트립트 끝 -->
<!-- 뉴스 불러오기 스크립트 -->
<script type="text/javascript">
$(function(){
	$.ajax({
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : "/writeMaterial/newsMaterial",
		success : function(xml){
					
			xml=$(xml);
			
			xml.find("item").each(function(){
				var a=$(this).find("title").text();
				var b=$(this).find("description").text();
				var c=$(this).find("pubDate").text();
				var res=c.substring(0,16);
				var d=$(this).find("link").text();
				$(".newsModal-header").append("<h4>"+a+"<h4>");
				$(".newsModal-body").append(b); 
				$(".newsModal-body").append("<br/><br/><a  href='"+d+"' target='_blank'>+원본보기<a/>");
				$(".newsModal-body").append("<br/><br/>")
				$(".newsModal-footer").append(res);
				
				return false;
	
			});
		}
	});
});
</script>
<!-- 뉴스 불러오기 스크립트  끝-->
<!-- 뉴스 몸~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div id="newsMyModal" class="newsMyModal2">
  <!-- Modal content -->
  <div class="newsModal-content">
    <div class="newsModal-header">
        <!-- <h2>Modal Header</h2> -->
    </div>
    <div class="newsModal-body">
    <!--   <p>Some text in the Modal Bodyddddddddddddddddddddddddddddd</p>
      <p>Some other text...</p> -->
    </div>
    <div class="newsModal-footer">
      <!-- <h3>Modal Footer</h3> -->
    </div>
  </div>
</div>
<!--  뉴스 몸 끝-->
<!-- 뉴스 클릭 자바스크립트 -->
<script type="text/javascript">
var newsModal = document.getElementById('newsMyModal');
//Get the button that opens the modal
var newsBtn = document.getElementById("news");
//Get the <span> element that closes the modal

//When the user clicks the button, open the modal 
newsBtn.onclick = function() {
	newsModal.style.display = "block";
}
//When the user clicks on <span> (x), close the modal

</script>
<!-- 뉴스 클릭 자바스크립트 끝 -->
<!-- 동영상 몸 -->
<div id="videoMyModal" class="videoModal">
  <!-- <span class="videoClose">&times;</span> -->
 
<%--  <embed  class="videoModal-content" width="640" height="360" src="http://www.youtube.com/v/${ movieId }&vq=hd720&autoplay=1&start=0&rel=0" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true"></embed> --%>
 <iframe class="videoModal-content" width="500" height="315" name="srcName" src="http://www.youtube.com/embed/${movieId}?enablejsapi=1;" frameborder="0" allowfullscreen></iframe>
</div>
<!--  동영상 몸 끝-->
<!--  동영상 클릭 스크립트-->
<script type="text/javascript">
//Get the modal
var videoModal = document.getElementById('videoMyModal');
// Get the image and insert it inside the modal - use its "alt" text as a caption
var videoImg = document.getElementById('video');
videoImg.onclick = function(){
	videoModal.style.display = "block";
}
//Get the <span> element that closes the modal
/* var videoSpan = document.getElementsByClassName("videoClose")[0];
// When the user clicks on <span> (x), close the modal

videoSpan.onclick = function() { 
	videoModal.style.display = "none";
	var div = document.getElementById("videoMyModal");
    var iframe = div.getElementsByTagName("iframe")[0].contentWindow;
    iframe.postMessage('{"event":"command","func":"pauseVideo","args":""}','*');

	
} */
window.onclick = function(event) {
    if (event.target == videoModal) {
    	videoModal.style.display = "none";
    	var div = document.getElementById("videoMyModal");
        var iframe = div.getElementsByTagName("iframe")[0].contentWindow;
        iframe.postMessage('{"event":"command","func":"pauseVideo","args":""}','*');
         document.link.value="http://www.youtube.com/embed/"+${movieId}+"?enablejsapi=1;";
        
    }else if(event.target==newsModal){
    	newsModal.style.display="none";
    }else if(event.target==photoModal){
    	photoModal.style.display="none";
    }
}
</script>
<!--  동영상 클릭 스크립트 끝-->
<div class="page">
<div class="popup back" style="display:none;"></div>
<div id="popup_front" class="popup front" style="display:none;">
	<img id="popup_img">
</div>
	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header"> 글 남기기</h4>
	   </div>
	</div><!-- /.row -->
	<div id="main-contents">
	<!-- ####################	 시작		#################### -->
	<form id="writeForm" class="form-horizontal" action="/diary/actWrite" method="post">
		<input type="hidden" name="userid" value="${ login.userid }">
		<input type="hidden" name="nickname" value="${ login.nickname }">
		<input type="hidden" name="group_no" value="${ login.group_no }">
		<input type="hidden" id="weather" name="weather" value="default">
		<input type="hidden" id="emotion" name="emotion" value="default">
		
		<div class="form-group">
			<div class="col-sm-11">
				<label for="regdate" class="col-sm-1 control-label">날짜</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="location" name="regdate" value="<fmt:formatDate value='${today}' type='date' pattern='yyyy년 MM월 dd일 E요일' />" disabled>
				</div>

				<!-- 날씨 -->
				<div class="col-sm-2">
				<label style="margin-right: 20px" for="weather" class="control-label">날씨</label>
					<button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#modal_weather">
					<img id="img_weather" src="/resources/image/weather/weather_default.png"></button>
					
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
					<button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#modal_emotion">
					<img id="img_emotion" src="/resources/image/emotion/emotion_default.png"></button>
					
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
				<input type="text" class="form-control" id="location" name="location" placeholder="지역">
			</div>
		</div>	
		
		<div class="form-group">
			<label for="title" class="col-sm-1 control-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목">
			</div>
		</div>
		
		<div class="form-group">
			<label for="contents" class="col-sm-1 control-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="contents" name="contents" rows="40" placeholder="내용" style="width: 100%"></textarea>
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
				<input type="text" class="form-control" id="link" name="link" placeholder="링크">
			</div>
		</div>
		
		<div class="form-group">
			<label for="tag" class="col-sm-1 control-label">태그</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="tag" name="tag" placeholder="태그">
			</div>
		</div>
		
		
		<c:choose>
			<c:when test="${ login.group_no == null}">
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
			<script type="text/javascript">
				$("#btn_submit").on("click", function(e) {
					e.preventDefault();
					
					if($("#title").val() == "") {
						alert("글에 제목을 붙여주세요!");
					} else {
						oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
						
						var that = $(this);
						var str ="";
						
						$(".uploadedList .delbtn").each(function(index) {
							 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
						});
						
						that.append(str);
						$("#writeForm").submit();			
					}
				});
			</script>
		</button>
		<button id="btn_back"class="btn btn-default">
			<span>취소</span>
			<script type="text/javascript">
				$("#btn_back").on("click", function(e) {
					e.preventDefault();
					
					var arr = [];
					$(".uploadedList li").each(function(index) {
						arr.push($(this).attr("data-src"));
					});
					
					if(arr.length > 0) {
						$.post("/file/deleteAllFiles", {files:arr}, function() {
							
						});
					}
					
					$("#backtolist").submit();
				});
			</script>	
		</button>							
		</div>
	</form>
	
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
	
	if (confirm("정말 삭제하시겠습니까?") == true){
		var that = $(this);
	 
		$.ajax({
		   url:"/file/deleteFile",
		   type:"post",
		   data: {fileName:$(this).attr("href")},
		   dataType:"text",
		   success:function(result){
			   if(result == 'deleted'){
				   that.closest("li").remove();
			   }
		   }
	   });
	} else {
		return;
	}
	
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

</script>
</body>
</html>
