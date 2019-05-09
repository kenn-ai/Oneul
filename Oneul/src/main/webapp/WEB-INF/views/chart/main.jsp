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
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/resources/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="/resources/dist/css/skins/_all-skins.min.css">

<!-- jQuery 2.2.3 -->
<script src="/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- ChartJS 1.0.1 -->
<script src="/resources/plugins/chartjs/Chart.min.js"></script>
<!-- ChartJS HorizontalBar plugin -->
<script src="/resources/plugins/chartjs/Chart.HorizontalBar.js"></script>
<!-- FastClick -->
<script src="/resources/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/resources/dist/js/demo.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/nav.jsp"></jsp:include>
	<div class="page">
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h4 class="page-header"> 당신의 오늘을 기록하기 시작한지 <b style="color: green">${ dday }</b> 일 째 입니다. </h4>
			</div>
		</div>
		<!-- /.row -->

		<div id="main-contents">
			<!-- ########################## Start Body ########################## -->
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">월별 일기 작성 통계</h3>

						</div>
						<div class="box-body" align="center">
							<canvas id="lineChart" height="10%" width="100%"></canvas>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
			</div> <!-- row end -->
			
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">날씨 분포도</h3>
						</div>
						<div class="box-body">
							<canvas id="barChart_Weather" height="30%" width="50%"></canvas>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
				<div class="col-md-6 col-sm-12">
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">나의 감정 분포도</h3>
						</div>
						<div class="box-body">
							<canvas id="barChart_Emotion" height="30%" width="50%"></canvas>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
			</div> <!-- row end -->
			
			<!-- ########################## End Body ########################## -->
		</div>
		<!-- /#main-contents -->
	</div>

	<script type="text/javascript">
	$( document ).ready(function() {
		// 데이터 불러오는곳
		var userid = '${login.userid}';
		
		//날씨
		var weather_default = 0;
		var weather_sun = 0;
		var weather_moon = 0;
		var weather_star = 0;
		var weather_cloudy = 0;
		var weather_rain = 0;
		var weather_snow = 0;
		var weather_lightening = 0;
		var weather_windy = 0;
		var weather_hurricane = 0;
		var weather_rainbow = 0;
		var weather_snowman = 0;
		var weather_earthquake = 0;
		var weather_volcano = 0;
		var weather_snowflake = 0;
		var weather_dusty = 0;
		
		//감정
		var emotion_default = 0;
		var emotion_sad = 0;
		var emotion_good = 0;
		var emotion_happy = 0;
		var emotion_deep_in_love = 0;
		var emotion_tears = 0;
		var emotion_embarrassed = 0;
		var emotion_crying = 0;
		var emotion_surprised = 0;
		var emotion_shocked = 0;
		var emotion_bad = 0;
		var emotion_sour = 0;
		var emotion_angry = 0;
		var emotion_annoy = 0;
		var emotion_irritation = 0;
		var emotion_sleepy = 0;
		var emotion_cadaverousness = 0;
		var emotion_vomit = 0;
		var emotion_death = 0;
		var emotion_devil = 0;
		
		//날짜
		var date;
		var month = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			
		// 날씨
		$.ajax({
			type : 'POST',
			url : '/chart/weatherBar',
			data : {
				"userid" : userid
			},
			success : function(data) {
				for(var i=0; i<data.length; i++) {
					switch (data[i].weather) {
			            case "default":
			                weather_default++;
			            	break;
	
			            case "sun":
			                weather_sun++;
	
			            case "moon":
			                weather_moon++;
	
			            case "star":
			                weather_star++;
			            	break;
	
			            case "cloudy":
			                weather_cloudy++;
			            	break;
	
			            case "rain":
			                weather_rain++;
			            	break;
	
			            case "snow":
			                weather_snow++;
			            	break;
	
			            case "lightening":
			                weather_lightening++;
			            	break;
	
			            case "windy":
			                weather_windy++;
			            	break;
	
			            case "hurricane":
			                weather_hurricane++;
			            	break;
	
			            case "rainbow":
			                weather_rainbow++;
			            	break;
	
			            case "snowman":
			                weather_snowman++;
			            	break;
	
			            case "earthquake":
			                weather_earthquake++;
			            	break;
	
			            case "volcano":
			                weather_volcano++;
			            	break;
	
			            case "snowflake":
			                weather_snowflake++;
			            	break;
	
			            case "dusty":
			                weather_dusty++;
			            	break;
			            	
			            default :
			            	weather_default++;
			            	break;
			            	
					}
					
				}
				//바 차트 (날씨)
				var barChartCanvas = $("#barChart_Weather").get(0).getContext("2d");
				var barChart = new Chart(barChartCanvas);
				var myBar;

				var bar_data = {

					labels : [ "무지개", "미세먼지", "화산", "지진",
								"꽁꽁", "첫눈", "태풍", "바람", 
								"번개", "눈", "비", "흐림", 
								"별똥별", "예쁜달", "맑음", "그냥" ],
					datasets : [ {
						fillColor : "rgba(97,171,126,0.5)",
						strokeColor : "rgba(76,140,97,1)",
						pointColor : "rgba(76,140,97,1)",
						pointStrokeColor : "#fff",
						data : [ weather_dusty, weather_snowflake, weather_volcano, 
							weather_earthquake, weather_snowman, weather_rainbow, weather_hurricane, 
							weather_windy, weather_lightening, weather_snow, weather_rain, 
							weather_cloudy, weather_star, weather_moon, weather_sun, weather_default ]			
					} ]

				};

				var bar_options = {
					responsive : true,
					barShowStroke : false
				};

				myBar = barChart.HorizontalBar(bar_data, bar_options);
				//--------------
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
			}
		});
		
		// 감정
		$.ajax({
			type : 'POST',
			url : '/chart/emotionBar',
			data : {
				"userid" : userid
			},
			success : function(data) {
				for(var i=0; i<data.length; i++) {
					switch (data[i].emotion) {
						case "default":
							emotion_default++;
							break;
	
			            case "sad":
			                emotion_sad++;
			                break;
	
			            case "good":
			                emotion_good++;
			                break;
	
			            case "happy":
			                emotion_happy++;
			                break;
	
			            case "deep_in_love":
			                emotion_deep_in_love++;
			                break;
	
			            case "tears":
			                emotion_tears++;
			                break;
	
			            case "embarrassed":
			                emotion_embarrassed++;
			                break;
	
			            case "crying":
			                emotion_crying++;
			                break;
	
			            case "surprised":
			                emotion_surprised++;
			                break;
	
			            case "shocked":
			                emotion_shocked++;
			                break;
	
			            case "bad":
			                emotion_bad++;
			                break;
	
			            case "sour":
			                emotion_sour++;
			                break;
	
			            case "angry":
			                emotion_angry++;
			                break;
	
			            case "annoy":
			                emotion_annoy++;
			                break;
	
			            case "irritation":
			                emotion_irritation++;
			                break;
	
			            case "sleepy":
			                emotion_sleepy++;
			                break;
	
			            case "cadaverousness":
			                emotion_cadaverousness++;
			                break;
	
			            case "vomit":
			                emotion_vomit++;
			                break;
	
			            case "death":
			                emotion_death++;
			                break;
	
			            case "devil":
			                emotion_devil++;
			                break;
			                
			            default :
			            	emotion_default++;
			            	break;
					}
					
				}
				//바 차트 (감정)
				var barChartCanvas = $("#barChart_Emotion").get(0).getContext("2d");
				var barChart = new Chart(barChartCanvas);
				var myBar;

				var bar_data = {

					labels : [ "크크크", "죽기직전", "만신창이", "꽥", "졸림", 
								"불쾌", "빠직", "버럭", "떨떠름", "흐음", 
								"뜨억", "놀람", "눈물", "당황", "우울", 
								"사랑", "행복", "좋음", "나쁨", "그냥" ],
					datasets : [ {
						fillColor : "rgba(97,171,126,0.5)",
						strokeColor : "rgba(76,140,97,1)",
						pointColor : "rgba(76,140,97,1)",
						pointStrokeColor : "#fff",
						data : [ emotion_devil, emotion_death, emotion_vomit, emotion_cadaverousness, emotion_sleepy, 
							emotion_irritation, emotion_annoy, emotion_angry, emotion_sour, emotion_bad, 
							emotion_shocked, emotion_surprised, emotion_crying, emotion_embarrassed, emotion_tears, 
							emotion_deep_in_love, emotion_happy, emotion_good, emotion_sad, emotion_default ]					
					} ]

				};

				var bar_options = {
					responsive : true,
					barShowStroke : false
				};

				myBar = barChart.HorizontalBar(bar_data, bar_options);
				//--------------
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
			}
		});
		
		//월별 통계 데이터 통신
		$.ajax({
			type : 'POST',
			url : '/chart/line',
			data : {
				"userid" : userid
			},
			success : function(data) {
				
				
 				for(var i=0; i<data.length; i++) {
 					date = new Date(data[i].regdate);
 					
					switch (date.getMonth()) {
						//여기부터 1월
						case 0:
							month[0]++;
							break;
						case 1:
							month[1]++;
							break;
						case 2:
							month[2]++;
							break;
						case 3:
							month[3]++;
							break;
						case 4:
							month[4]++;
							break;
						case 5:
							month[5]++;
							break;
						case 6:
							month[6]++;
							break;
						case 7:
							month[7]++;
							break;
						case 8:
							month[8]++;
							break;
						case 9:
							month[9]++;
							break;
						case 10:
							month[10]++;
							break;
						case 11:
							month[11]++;
							break;
					}
					
				}
				//선 차트 (다이어리 통계)
 				var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
				var lineChart = new Chart(lineChartCanvas);
				var myLine;

				var line_data = {

					labels : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
					datasets : [ {
						fillColor : "rgba(97,171,126,0.5)",
						strokeColor : "rgba(97,171,126,0.5)",
						pointColor : "rgba(76,140,97,1)",
						pointStrokeColor : "#fff",
						data : [ month[0], month[1], month[2], month[3], month[4], month[5], month[6], month[7], month[8], month[9], month[10], month[11] ]
					} ]

				};

				var line_options = {
					responsive : true,
					datasetFill: false,
					bezierCurve: false
				};

				myLine = lineChart.Line(line_data, line_options); 
				//--------------
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
			}
		});
		
	});
	
	</script>
</body>
</html>
