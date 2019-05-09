<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".btn_delete").on("click", function(e){
		e.preventDefault();
		if (confirm("정말 삭제하시겠습니까?") == true) {
			$("#comment_delete_form").submit();
		} else {
			return;
		} 
	});
	
	$(".btn_update").click(function(e){
		e.preventDefault();
		var update_form = $(this).parent().parent().next("tr");
		var pre_value = $(this).parent().prev().prev().html();
		var pre_cno = $(this).parent().prev().prev().prev().prev().html();
		
			str = "";
			str += 			
				"<td class='extd'>${ login.nickname }</td>"
					+"<td colspan='2'><input type='text' class='form-control' name='contents' placeholder='댓글을 입력하세요' value='"+pre_value+"'></td>"
					+"<td>"
					+"<button type='submit' class='btn btn-default'>확인</button>"
				+"</td>";
			update_form.html(str);
	});	
});
</script>
</head>
<body>
	<form id="comment_write_form" action="/comment/write" method="post">
	 	<input type="hidden" name="diary_no" value="${ diary_no }">
		<input type="hidden" name="userid" value="${ login.userid }">
		<input type="hidden" name="nickname" value="${ login.nickname }">
				
		<table id="board_table" class="table">
			<col width="10%">
			<col width="70%">
			<col width="10%">
			<col width="10%">								
			<tr>	
			<td><i class="fa fa-fw fa-leaf"></i> ${ login.nickname }</td>
			<td colspan="2"><input type="text" class="form-control" name="contents" placeholder="댓글을 입력하세요"></td>
			<td>
				<button type="submit" class="btn btn-success">등록</button>
			</td>
			</tr>
		</table>
	</form>

	<c:forEach var="commentVO" items="${ commentList }">
		<form id="comment_update_form" action="/comment/update" method="post">
		 	<input type="hidden" name="diary_no" value="${ diary_no }">
			<input type="hidden" name="userid" value="${ login.userid }">
			<input type="hidden" name="nickname" value="${ login.nickname }">
			<input type="hidden" name="comment_no" value="${ commentVO.comment_no }">
		 		
			<table id="board_table" class="table">
				<col width="10%">
				<col width="70%">
				<col width="10%">
				<col width="10%">							
					<tr>
						<td style="display: none">${ commentVO.comment_no }</td>
						<td><i class="fa fa-fw fa-leaf"></i> ${ commentVO.nickname }</td>
						<td>${ commentVO.contents }</td>
						<td align="left"><small><fmt:formatDate value="${ commentVO.regdate }" type="date" pattern="yy-MM-dd(E) HH:mm" /></small></td>
						<td>
						<c:choose>
							<c:when test="${ login.userid == commentVO.userid }">
								<button id="btnUpdate" class="btn btn-default btn_update" type="button">
									<span>수정</span>
								</button>
								
								<button class="btn btn-default btn_delete" type="button">
									<span>삭제</span>
								</button>
							</c:when>
						</c:choose>
						</td>
					</tr>				
					<tr>
	
					</tr>
			</table>
		</form>
		<form id="comment_delete_form" action="/comment/delete" method="post" style="display: none;">
		 	<input type="hidden" name="diary_no" value="${ diary_no }">
			<input type="hidden" name="comment_no" value="${ commentVO.comment_no }">
		</form>	
	</c:forEach>
		
</body>
</html>
