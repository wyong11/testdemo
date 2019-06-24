<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>教学管理</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript" src="js/Chart.min.js"></script>
    <script type="text/javascript" src="js/echarts.min.js"></script>
    <script type="text/javascript" src="js/utils.js"></script>
    <style>
    #chart-area{
    	width:200px!important;
    	height:200px!important;
    	
    	margin-left:100px!important;
    }
		#canvas-holder {
			width: 100%;
			margin-top: 50px;
			text-align: center;
		}
		#chartjs-tooltip {
			opacity: 1;
			position: absolute;
			background: rgba(0, 0, 0, .7);
			color: white;
			border-radius: 3px;
			-webkit-transition: all .1s ease;
			transition: all .1s ease;
			pointer-events: none;
			-webkit-transform: translate(-50%, 0);
			transform: translate(-50%, 0);
		}

		.chartjs-tooltip-key {
			display: inline-block;
			width: 10px;
			height: 10px;
			margin-right: 10px;
		}
	</style>
</head>

<%
int examin = (int)request.getAttribute("examIn");
int examout = (int)request.getAttribute("examOut");
int g1 = (int)request.getAttribute("g1");
int g2 = (int)request.getAttribute("g2");
int g3 = (int)request.getAttribute("g3");
int g4 = (int)request.getAttribute("g4");
int g5 = (int)request.getAttribute("g5");
String classname = (String) request.getAttribute("classname");
String examname = (String) request.getAttribute("examname");
%>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			<p><a href="">考试统计表</a></p>
        </div>

    </div>
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   
 <div class="wrapper wrapper-content animated fadeInRight" style="overflow:scroll; height:400px;">
        
        <div class="row">
        	<%-- <div class="col-sm-6" style="background-color:white">
                <div class="ibox float-e-margins">
                    <div id="canvas-holder" style="width: 300px;">
                    	<h5>环形图</h5>
						<canvas id="chart-area" width="200px!important" height="200px!important"></canvas>
						<div class="right-box" >
							<table style="position: absolute;margin-left:400px!important;margin-bottom:500px!important">
								<tbody>
									<tr>
										<td class="classbox">
											<div style="border:1px solid #ccc;padding:1px">
												<div style="width:4px;height:0;border:5px solid red;overflow:hidden"></div>
											</div>
										</td>
										<td>缺到人数</td>
									</tr>
									<tr>
										<td class="classbox">
											<div style="border:1px solid #ccc;padding:1px">
												<div style="width:4px;height:0;border:5px solid yellow;overflow:hidden"></div>
											</div>
										</td>
										<td>已到人数</td>
									</tr>
									<tr>
										<td class="classbox">
											<div style="border:1px solid #ccc;padding:1px">
												<div style="width:4px;height:0;border:5px solid blue;overflow:hidden"></div>
											</div>
										</td>
										<td>已到人数</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="chartjs-tooltip">
							<table></table>
						</div>
						
					</div>
                    <div class="ibox-content">
                        <div id="morris-line-chart"></div>
                    </div>
                </div>
            </div> --%>
            <div class="col-sm-6" style="background-color:white">
                <div class="ibox float-e-margins">
                	<div id="main" style="width: 400px;height:300px;"></div>
                </div>
            </div>
            <div class="col-sm-6" style="background-color:white">
                <div class="ibox float-e-margins">
                	<div id="main2" style="width: 400px;height:300px;"></div>
                </div>
            </div>
    	</div>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var sin ='<%=examin%>';
        var sout ='<%=examout%>';
        var classname = '<%=classname%>';
        // 指定图表的配置项和数据
        var option = {
    title : {
        text: classname+'考试统计',
        subtext: '测试数据',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['参加考试','未参加考试']
    },
    series : [
        {
            name: '考试情况',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:sin, name:'参加考试'},
                {value:sout, name:'未参加考试'}
                
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main2'));
        var sin ='<%=examin%>';
        var sout ='<%=examout%>';
        var g1 ='<%=g1%>';
        var g2 ='<%=g2%>';
        var g3 ='<%=g3%>';
        var g4 ='<%=g4%>';
        var g5 ='<%=g5%>';
        var classname = '<%=classname%>'
        
        // 指定图表的配置项和数据
        var option = {
    title : {
        text: classname+'考试成绩统计',
        subtext: '测试数据',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['90分以上','80分~90分','70分~80分','60分~70分','60分以下']
    },
    series : [
        {
            name: '考试情况',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:g1, name:'90分以上'},
                {value:g2-g1, name:'80分~90分'},
                {value:g3-g2, name:'70分~80分'},
                {value:g4-g3, name:'60分~70分'},
                {value:g5, name:'60分以下'}
                
                
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    
    <script>
		Chart.defaults.global.tooltips.custom = function(tooltip) {
			// Tooltip Element
			var tooltipEl = document.getElementById('chartjs-tooltip');

			// Hide if no tooltip
			if (tooltip.opacity === 0) {
				tooltipEl.style.opacity = 0;
				return;
			}

			// Set caret Position
			tooltipEl.classList.remove('above', 'below', 'no-transform');
			if (tooltip.yAlign) {
				tooltipEl.classList.add(tooltip.yAlign);
			} else {
				tooltipEl.classList.add('no-transform');
			}

			function getBody(bodyItem) {
				return bodyItem.lines;
			}

			// Set Text
			if (tooltip.body) {
				var titleLines = tooltip.title || [];
				var bodyLines = tooltip.body.map(getBody);

				var innerHtml = '<thead>';

				titleLines.forEach(function(title) {
					innerHtml += '<tr><th>' + title + '</th></tr>';
				});
				innerHtml += '</thead><tbody>';

				bodyLines.forEach(function(body, i) {
					var colors = tooltip.labelColors[i];
					var style = 'background:' + colors.backgroundColor;
					style += '; border-color:' + colors.borderColor;
					style += '; border-width: 2px';
					var span = '<span class="chartjs-tooltip-key" style="' + style + '"></span>';
					innerHtml += '<tr><td>' + span + body + '</td></tr>';
				});
				innerHtml += '</tbody>';

				var tableRoot = tooltipEl.querySelector('table');
				tableRoot.innerHTML = innerHtml;
			}

			var positionY = this._chart.canvas.offsetTop;
			var positionX = this._chart.canvas.offsetLeft;

			// Display, position, and set styles for font
			tooltipEl.style.opacity = 1;
			tooltipEl.style.left = positionX + tooltip.caretX + 'px';
			tooltipEl.style.top = positionY + tooltip.caretY + 'px';
			tooltipEl.style.fontFamily = tooltip._bodyFontFamily;
			tooltipEl.style.fontSize = tooltip.bodyFontSize;
			tooltipEl.style.fontStyle = tooltip._bodyFontStyle;
			tooltipEl.style.padding = tooltip.yPadding + 'px ' + tooltip.xPadding + 'px';
		};

		var config = {
			type: 'pie',
			data: {
				datasets: [{
					data: [300, 50, 100],
					backgroundColor: [
						window.chartColors.red,
						
						window.chartColors.yellow,
						
						window.chartColors.blue,
					],
				}],
				labels: [
					'Red',
					
					'Yellow',
					
					'Blue'
				]
			},
			options: {
				responsive: true,
				legend: {
					display: false
				},
				tooltips: {
					enabled: false,
				}
			}
		};

		window.onload = function() {
			var ctx = document.getElementById('chart-area').getContext('2d');
			window.myPie = new Chart(ctx, config);
		};
	</script>
        
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/demo/flot-demo.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>

