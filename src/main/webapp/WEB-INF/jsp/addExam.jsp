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

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   
 <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        	<div class="col-sm-12">
        		<div class="ibox-title">
        			<form class="m-t" role="form" action="addExam" method="post" >
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="考试名称" name="examName" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="班级名称" name="classname" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="课程名称" name="coursename" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="考试人数" name="stotal" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="datetime-local" class="form-control" placeholder="开考时间" name="startTime" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="datetime-local" class="form-control" placeholder="结束时间" name="endTime" style="width:30%!important">
		                </div>
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="考试时长" name="timelength" style="width:30%!important">
		                </div>
		                
		                <button type="submit" class="btn btn-primary block full-width m-b" style="width:30%!important">确认发布</button>
					</form>
        		</div>
        	</div>
    	</div>
   </div>
    
        
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/hplus.min.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>

