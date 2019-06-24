<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="layui/css/layui.css" rel="stylesheet">


<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="js/contabs.min.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>
<script src="layui/layui.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">

	<!--右侧部分结束-->
	<!--右侧边栏开始-->
	<div class="gray-bg" style="margin-left: 0px">

		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
				<div class="col-sm-12" style="overflow:scroll; height:600px;">
					<div class="ibox-title">
						<form class="m-t" role="form" action="addSubject" method="post">
							<h2>试题编号</h2>
							<textarea id="subjectNum" style="display: none;" name="subjectNum"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									//layedit.build('demo'); //建立编辑器
									layedit.build('subjectNum',{
										height:100
									});
								 }); 
							</script>
							<h2>试题题干</h2>
							<textarea id="subjectName" style="display: none;" name="SubjectName"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									//layedit.build('demo'); //建立编辑器
									layedit.build('subjectName',{
										height:100
									});
								 }); 
							</script>
							<h2>答案选项</h2><br>
							<h3>(A)</h3>
							
							<textarea id="optionA" style="display: none;" name="optionA"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									layedit.build('optionA',{
										height:80
										
									}); //建立编辑器
								 }); 
							</script>
							<h3>(B)</h3>
							
							<textarea id="optionB" style="display: none;" name="optionB"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									layedit.build('optionB',{
										height:80
										
									}); //建立编辑器
								 }); 
							</script>
							<h3>(C)</h3>
							
							<textarea id="optionC" style="display: none;" name="optionC"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									layedit.build('optionC',{
										height:80
										
									}); //建立编辑器
								 }); 
							</script>
							<h3>(D)</h3>
							
							<textarea id="optionD" style="display: none;" name="optionD"></textarea>
							<script>
								layui.use('layedit', function() { 
									var layedit = layui.layedit;
									layedit.build('optionD',{
										height:80
										
									}); //建立编辑器
								 }); 
							</script>
							<h2>正确答案</h2><br>
							<input type="text" class="form-control" aria-describedby="sizing-addon1" name="answer">
							<input type="hidden" class="form-control" aria-describedby="sizing-addon1" name="examid" value="<%=request.getSession().getAttribute("examid") %>">
							<h2>题目分数</h2><br>
							<input type="text" class="form-control" aria-describedby="sizing-addon1" name="subjectScore">
							<h2>题目类型</h2><br>
							<select class="form-control" name="subjectType">
								<option value=1>单选题</option>
								<option value=2>多选题</option>
								<option value=3>简答题</option>
							</select><br><br>
							<button type="submit" class="btn btn-primary block full-width m-b" style="width: 30% !important">确认发布</button>
						<br><br><br><br><br>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>
<script>
layui.use(['element','form'], function(){
	  var element = layui.element;
	  
	});
</script>

