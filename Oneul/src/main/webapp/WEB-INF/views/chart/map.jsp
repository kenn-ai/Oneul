<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>지도</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
<div class="page">

   <!-- Page Heading -->
   <div class="row">
      <div class="col-lg-12">
         <h4 class="page-header"> 지도</h4>
      </div>
   </div><!-- /.row -->
   
   <div id="main-contents" style="height: 600px; margin: auto;">
   
   <div id="searchForm" class="row" style="margin: auto;">
   <div class="col-md-12">
      <form action="/map/search" method="post">
         <input type="hidden" name="userid" value="${ login.userid }">
         <select class="form-control" id="year" name="year" style="width:120px; float: left; margin-right: 5px; margin-bottom: 5px;">
            <option>년</option>
            <option>2016</option>
            <option>2017</option>
            <option>2018</option>
         </select> 
         <select class="form-control" id="month" name="month" style="width:100px; float: left; margin-right: 5px; margin-bottom: 5px;">
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
         <select class="form-control" id="day" name="day" style="width:100px; float: left; margin-right: 5px; margin-bottom: 5px;">
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
            <input class="btn btn-success" type="submit" name="search" value="검색" style="float: left; margin-right: 5px">
      </form>
   </div>
   </div>
   
   <div style="clear: both;"></div>
   
   <div id="map" style="width: 100%; height: 100%; margin: auto; margin-top: 15px"></div>

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
                           zoom : 1
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
      
   <!-- ####################    시작      #################### -->
      
   <!-- ####################      끝        #################### -->        
      
   </div><!-- /#main-contents -->

</div>
</body>
</html>