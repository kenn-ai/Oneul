<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" /> <!-- pages -->
<link href="/resources/css/bootstrap.css?ver=1" rel="stylesheet" /> <!-- button -->
<link href="/resources/css/ct-navbar.css" rel="stylesheet" /> <!-- nav -->
<link href="/resources/css/sb-admin.css" rel="stylesheet"> <!-- pages -->
<link href="/resources/css/pe-icon-7-stroke.css" rel="stylesheet" />
<link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="/resources/js/jquery-1.10.2.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script src="/resources/js/ct-navbar.js"></script>
<style type="text/css">
body {
	background-color: white;
}
.page {
	margin-top: 50px;
	margin-left: 10%;
	margin-right: 10%;
	padding: 3%;
}
.ellip {
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
}
.line-break {
	word-break:keep-all; 
	word-wrap:break-word; 
	white-space: normal;
}
.caption-hr {
  margin-top: 0px;
  margin-bottom: 0px;
  border: 0;
  border-top: 1px solid #dddddd;
}
.diaryImg {
	max-width: 100%; 
	display: block;
}
</style>
</head>
<body>
	<nav class="navbar navbar-ct-oneul navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav navbar-left" style="left: -200px;">

					<li><a href="/home">
						<img style="width: 52px; height: 52px;" src="/resources/image/logo/lief_logo_01.png"><p>오늘</p></i>
					</a></li>
				
					<li><a href="/notice/list">
						<i class="pe-7s-speaker"><p>공지사항</p></i>
					</a></li>
					
					<li><a href="/diary/select">
						<i class="pe-7s-notebook"><p>다이어리</p></i>
					</a></li>
						
					<li><a href="/group/list">
						<i class="pe-7s-users"><p>그룹관리</p></i>
					</a></li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="pe-7s-graph"></i><p>통계</p><b class="caret"></b>
						</a>
						<ul class="dropdown-menu alert-dropdown">
							<li><a href="/map/todaymarker"><i class="fa fa-fw fa-map"></i> 지도</a></li>
							<li><a href="/chart/main"><i class="fa fa-fw fa-area-chart"></i> 도표</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="pe-7s-help1"></i>
						<p>도움말</p><b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/help/faq"><i class="fa fa-fw fa-question-circle"></i> 자주 묻는 질문</a></li>
							<li><a href="/help/qna"><i class="fa fa-fw fa-question-circle-o"></i> 문의하기</a></li>
						</ul>
					</li>
				</ul>	
			
				<ul class="nav navbar-nav navbar-right">

					<li class="dropdown"  id="testtest">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<i class="pe-7s-mail"> 
							<!-- ## 알림 개수에따라 갱신되어야할 부분 ## -->
							<span class="label" id="testnum"></span>
							</i><p>알림</p>
						</a>
						<ul class="dropdown-menu" id="alarmList" style="width:300px">
							<!-- 알람 메세지가 append되는 공간  -->
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="pe-7s-user"></i>
						<p>${ login.nickname }</p><b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/user/check?code=update"><i class="fa fa-fw fa-user"></i> 개인정보수정</a></li>
							<li class="divider"></li>
							<li><a href="/user/logout"><i class="fa fa-fw fa-power-off"></i> 로그 아웃</a></li>
						</ul>
					</li>
					
				</ul>
				
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--  end navbar -->
	
	<script type="text/javascript">
		var a_alarm_no;
		var a_reader;
		var a_group_no;
		var a_writer;
		var result;
		var allData;
		var parent;
		
		$('#alarmList').on("click", ".accept",function(){
			
			parent = $(this).parent();
			
			a_alarm_no = $(this).nextAll('.a_alarm_no').val();
			a_reader = $(this).nextAll('.a_reader').val();
			a_group_no = $(this).nextAll('.a_group_no').val();
			a_writer = $(this).nextAll('.a_writer').val();
			result = "accept";
			allData = { "alarm_no":a_alarm_no, "reader":a_reader, "group_no":a_group_no, "result":"accept", "writer":a_writer };
			
			$.ajax({
		        type:'POST',
		        url:'/group/accept',
		        data : allData,
		        success:function(data){
		        	$('#testnum').text($('#testnum').text()-1);
		        	/* divider를 지우기 위해서 작성 */
					parent.next().remove();
		        	/* 알람메시지를 지우기 위해서 작성 */
					parent.empty();
					if($('#testnum').text() == 0) {
						$('#alarmList').append("<li id='noAlarm' align='center'>"+"<b>"+"도착한 알림이 없습니다."+"</b>"+"</li>");
					}
		        },
		        error:function(jqXHR, textStatus, errorThrown){
		        	  alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
		        }
		    });
			
		});
		
		
		$('#alarmList').on("click", ".reject",function(){
			
			parent = $(this).parent();
			a_alarm_no = $(this).nextAll('.a_alarm_no').val();
			a_reader = $(this).nextAll('.a_reader').val();
			a_group_no = $(this).nextAll('.a_group_no').val();
			a_writer = $(this).nextAll('.a_writer').val();
			result = "reject";
			allData = { "alarm_no":a_alarm_no, "reader":a_reader, "group_no":a_group_no, "result":result, "writer":a_writer };
			
			if($('#testnum').text() == 0) {
				$('#alarmList').append("<li id='noAlarm' align='center'>"+"<b>"+"도착한 알림이 없습니다."+"</b>"+"</li>");
			}
			
			$.ajax({
		        type:'POST',
		        url:'/group/accept',
		        data : allData,
		        success:function(data){
		        	$('#testnum').text($('#testnum').text()-1);
		        	/* divider를 지우기 위해서 작성 */
					parent.next().remove();
		        	/* 알람메시지를 지우기 위해서 작성 */
					parent.empty();
					if($('#testnum').text() == 0) {
						$('#alarmList').append("<li id='noAlarm' align='center'>"+"<b>"+"도착한 알림이 없습니다."+"</b>"+"</li>");
					}
		        },
		        error:function(jqXHR, textStatus, errorThrown){
		        	  alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
		        }
		    });
			
		});
	 		
		 $( document ).ready(function() {
		    var userid = '${login.userid}';
		    var count=0;
		    /* alert(userid); */
		   $.ajax({
	        type:'POST',
	        url:'/alarm/count',
	        data :{ "userid" : userid } ,
	        dataType : 'json',
	        success:function(data){
	            $('#testnum').html(data.length);
	            /* $.each(data, function(key, value) {
	            	alert(key + ':' + value);
	            });  */
	            if(data.length != 0) {
	            	$(data).each( function() {
		            	$('#noAlarm').remove();
		            	$('#alarmList').append("<li class='alarmLi' align='center'>"+"<b>"+this.writer+"</b>"+this.contents+"<br/>"
		            			+"<button class='btn btn-success accept'>수락</button>"
		            			+"<button class='btn btn-danger reject'>거절</button>"
		            			+"<input type='hidden' class='a_alarm_no'  value='"+this.alarm_no+"'/>"
		            			+"<input type='hidden' class='a_reader' value='"+this.reader+"'/>"
		            			+"<input type='hidden' class='a_group_no'  value='"+this.group_no+"'/>"
		            			+"<input type='hidden' class='a_writer'  value='"+this.writer+"'/>"
		            			+"</li>");
		            	count++;
		            	if (count !== data.length) {
		            		$('#alarmList').append("<li class='divider'></li>");
		            	}
		            	
		            });
	            } else {
					$('#alarmList').append("<li id='noAlarm' align='center'>"+"<b>"+"도착한 알림이 없습니다."+"</b>"+"</li>");
	            }
	        },
	        error:function(jqXHR, textStatus, errorThrown) {
	        	  alert("네비게이션 에러 발생~~ \n" + textStatus + " : " + errorThrown);
	        }
	    });
		   
		  
		}); /* END document.ready */
	 		
	</script>
</body>
</html>
