<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome! :)</title>
<style type="text/css">
iframe{
	width: 0px;
	height: 0px;
	border: 0px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
	<div class="page" >

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h4 class="page-header">오늘, 나의 발자취</h4>
			</div>
		</div>
		<!-- /.row -->

		<div id="main-contents" style="height: 600px; margin: auto;">

<div id="map" style="width: 100%; height: 100%; margin: auto;"></div>

         <script type="text/javascript">
            function myMap() {
               var mapCanvas = document.getElementById("map");
               
               var todayMarkerMap = new Array();               
               
               <c:forEach items="${marker}" var="item">
               
               var todayMarker = new Array();
               todayMarker.push("${item.x}");
               todayMarker.push("${item.y}");
               todayMarker.push("${item.address}");
               todayMarkerMap.push(todayMarker);
      
               </c:forEach>
               
               if("${marker}" == "[]"){
                  var mapOptions = {
                        center : new google.maps.LatLng('36.635817', '127.485837'),
                        zoom : 7
                     }   
               }else{
                  var mapOptions = {
                        center : new google.maps.LatLng(todayMarkerMap[0][0], todayMarkerMap[0][1]),
                        zoom : 14
                     }
               }
               
               var map = new google.maps.Map(mapCanvas, mapOptions);
               
               var infowindow = new google.maps.InfoWindow();
               
               for (var i = 0; i < todayMarkerMap.length; i++) {
                  
                  var posi = new google.maps.LatLng(todayMarkerMap[i][0], todayMarkerMap[i][1]);
                  
                  var markerOptions = {
                        position : posi,
                        map : map,
                        title : todayMarkerMap[i][2]
                  }   
                  
                  var marker = new google.maps.Marker(markerOptions);
                  
                  google.maps.event.addListener(marker, 'click', function() {
                        infowindow.setContent(this.title);
                        infowindow.open(map, this);
                    });   
               }
            }
            
         </script>
         <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgYErBNws9TUQlrUeWqluz7DsuJ_DHDZs&callback=myMap&language=ko"></script>
		
	<!-- ####################	 시작		#################### -->
		
	<!-- 새로운 페이지를 생성할 시 이 페이지를 복사하여 시작할 것 (UTF-8, c:, jQuery삽입 통일) -->
	<!-- 구역별로 div를 많이 활용하여 잘 나눌 것 -->
		
	<!-- ####################	   끝     	#################### -->	
		
	</div><!-- /#main-contents -->
	
</div>
</body>
</html>
