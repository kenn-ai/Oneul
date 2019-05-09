<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>그룹목록</title>
<style type="text/css">
.btn {
	width: 100%; 
	margin: 5px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

	<!-- Page Heading -->
	<div class="row">
	   <div class="col-lg-12">
	      <h4 class="page-header">그룹 멤버</h4>

	   </div>
	</div><!-- /.row -->
	
	<div id="main-contents">
		
	<!-- ####################	 시작		#################### -->
	
<!-- 그룹번호 숨겼음 <span>${ group_no }</span> -->
<%-- 
<table border="1">
<c:forEach var="memberVO" items="${ memberList }">

	<tr>
		<td>${memberVO.nickname }</td>
	</tr>

</c:forEach>
</table> --%>
			<div>
				<table class="table">
					<col width="80%">
					<col width="20%">
					<thead>
						<tr>
							<th style="text-align: center">참여중인 사람</th>
						</tr>
					</thead>
					<c:forEach var="memberVO" items="${ memberList }">
						<tr>
							<td align="center"><i class="fa fa-fw fa-leaf"></i> ${ memberVO.nickname }</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 모달 시작 -->
				<div align="center">
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">그룹 초대</button>
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="exampleModalLabel">그룹 멤버 초대</h4>
					      </div>
					      <div class="modal-body">
					        <form onsubmit="return false">
					          <div class="form-group">
					            <label for="recipient-name" class="control-label">초대받으시는분의  ID를 입력해주세요.</label>
					            <input type="text" class="form-control" id="receiver">
					          </div>
					        </form>
					      </div>
					      <div class="modal-footer">
						      <div>
							      <button type="button" class="btn btn-success" id="invite">초대하기</button>
								  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						      </div>
					      </div>
					    </div>
					  </div>
					</div>
					</div>				
				<!-- 모달 끝 -->
				<form action="/group/leave" method="get">
					<input type="hidden" name="userid" value="${login.userid}"/>
					<input type="hidden" name="group_no" value="${ group_no }"/>
					<button type="submit" class="btn btn-default">그룹 탈퇴</button>
				</form>
				<hr />
			</div>
			<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
</div>

	<script type="text/javascript">
		var nickname = '${login.nickname }';
		var userid = '${login.userid}';
		var regid = '';
	
		function nokey() {    
			/* CTRL + N키 막음. */     
			if ((event.keyCode == 13)) {         
				return false;     
			}
		}
		
		/* 초대하기 이벤트 */
		$('#invite').on("click", function(){
			/* var receiver = $('#receiver').serialize();
			var group_no = '${group_no}'; */
			var receiver = $('#receiver').val();
			var group_no = '${group_no}';
			var allData = { "receiver":receiver, "group_no":group_no};
			
			$.ajax({
		        type:'POST',
		        url:'/m/fcm/getToken',
		        data :{"userid" : receiver},
		        success:function(data){
		            regid = data.token;
		
		        },
		        error:function(jqXHR, textStatus, errorThrown){
		        	  alert("토큰얻어오기 실패. \n" + textStatus + " : " + errorThrown);
		        }
		    });
			
			
			$.ajax({
		        type:'POST',
		        url:'/group/invite',
		        data :allData,
		        success:function(data){
		            alert("초대장을 발송했습니다.");
		            var data2 = {
		            		"to" : regid,
		            		"notification" : {"title" : "오늘", "text":nickname+"님 으로부터 그룹초대가 도착했습니다."},
		            		"data" : {"userid" : receiver, "nickname" : nickname}
		            	};
		            $.ajax({
				        type:'POST',
				        url: "https://fcm.googleapis.com/fcm/send",
				        data : JSON.stringify(data2),
				        success:function(data){
				        	$("#exampleModal").modal('hide');
				        },
				        beforeSend : function(xhr){
				            xhr.setRequestHeader("Authorization", "key=AAAAxRvWeMM:APA91bF5I-4GttBw0GnWl0wpP79TXcWkQtERfyTuoox9EmyY2C6c0lBUUXAzdjNssYa0TAKFHPW0MKp9GGYYwEqbkONnrl15nKE51mud-ZlqHQIj1zySXkg9_crzQMEY47O3CdhYZv_1"); 
				            xhr.setRequestHeader("Content-type","application/json");
				        },
				        error:function(jqXHR, textStatus, errorThrown){
				        	  alert("FCM을 보내지 못함 \n" + textStatus + " : " + errorThrown);
				        }
				    });


		        },
		        error:function(jqXHR, textStatus, errorThrown){
		        	  alert("초대장 발송에 실패했습니다. ID를 다시 한번 확인해 주시기 바랍니다. \n" + textStatus + " : " + errorThrown);
		        }
		    });
			
		});
		
	</script>
</body>
</html>
