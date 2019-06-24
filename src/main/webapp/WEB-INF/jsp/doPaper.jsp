<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-1.7.2.min.js"></script>
</head>

        <%
	        String examname = (String)request.getAttribute("examname");	
	        String classname = (String)request.getSession().getAttribute("classname");
        	String username = (String)request.getAttribute("username");
        	String coursename = (String)request.getSession().getAttribute("coursename");
       		out.println(examname);
        	
        %>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<script>

	$(document).ready(function(){        
	var str = document.getElementById("content").innerText;
	//var reg = RegExp(/import/);
	if(str.search("demo") != -1){
		setTimeout(function(){$(".content").hide();},3000);  
	}
		     
	
	
	
	}); 
	/* $(document).ready(function(){        //页面加载完之后，自动执行该方法

		setTimeout(function(){$(".content").hide();},3000);        //2秒后执行该方法

		}); */
	
	

</script>

    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			
        </div>
    </div>
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   
 <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
        	<div class="col-sm-12">
        		<h5 style="float:left">考试详情</h5>
        		<!-- <h5 style="margin-left:80px"><a href="addexam">发布考试</a></h5> -->
        	</div>
        	<div class="col-sm-12" style="overflow:scroll; height:400px;">
        		<form class="m-t" role="form" action="addanswers" method="post" >
        		<table border="0" cellpadding="10" cellspacing="0" class="table table-striped">
					<thead>
						<tr>
							<td>考试内容</td>
						</tr>
					</thead>
			 	<%-- 	<p><%=request.getSession().getAttribute("tempString") %></p> --%> 
					 <%-- <pre>${tempString }</pre>  --%>
					<c:forEach items="${files }" var="paper">
				        <tr>
				        	<td class="content" style="padding:0px;border-top:0px,padding:4px">
					        	<c:choose>
									<c:when test="${fn:contains(paper.filecontent,'user')}">
											<input type="text" name="answers" class="form-control" style="border:0px;" placeholder="//">
											<input type="hidden" class="form-control" name="a1" value="${paper.filecontent}" >
									</c:when>
									<c:otherwise>
										${paper.filecontent}
									</c:otherwise>
					        	</c:choose>
				        	</td>
				        </tr>
				    </c:forEach>
				</table>
				
		                <div class="form-group">
	                    
	                    
	                    <input type="hidden" class="form-control" name="examstatus" value=1 >
	                    <input type="hidden" class="form-control" name="score" value=1 >
	                </div>
		                <div class="form-group">
		                	<!-- <textarea rows="10" cols="80" name="answers" placeholder="补充文本内容"></textarea> -->
		                   <input type="text" class="form-control" name="examname" value="${grade.examname }" >
		                   <input type="text" class="form-control" name="username" value="${grade.username }" >
		                   <input type="text" class="form-control" name="coursename" value="${grade.coursename }" >
		                </div>
		                <button type="submit" class="btn btn-primary block full-width m-b" style="width:30%!important">提交答案</button>
					</form>
        	</div>
        	
        	<!-- <div class="col-sm-12" style="overflow:scroll; height:400px;">
        		<form class="m-t" role="form" action="" method="post" >
		                
		                <div class="form-group">
		                	<textarea rows="10" cols="20" name="answer" placeholder="补充文本内容"></textarea>
		                   
		                </div>
		                <button type="submit" class="btn btn-primary block full-width m-b" style="width:30%!important">提交答案</button>
					</form>
        		
        	</div> -->
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

