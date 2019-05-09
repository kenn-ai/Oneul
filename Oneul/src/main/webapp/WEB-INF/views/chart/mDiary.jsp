<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Chart List</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
	<div id="chart_div" style="width: 100%; height: 390px;"></div>
	<script type="text/javascript">
		

		google.load("visualization", "1", {
			packages : [ "corechart" ]
		});
		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var userid="${userid}";
			
			var jsonData = $.ajax({
		          url: "/m/chart/diary",
		          data: { "userid" : userid } ,
		          dataType: "json",
		          async: false
		          }).responseText;
			var data = new google.visualization.DataTable(jsonData);


			var options = {
				title : '다이어리 작성량',
				legend : {
					position : 'top',
					alignment : 'center'
				},
				hAxis : {
				}
			};

			var chart = new google.visualization.LineChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
</body>
</html>
