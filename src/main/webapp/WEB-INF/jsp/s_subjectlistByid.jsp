<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="css/main.css" rel="stylesheet" type="text/css" />
	<link href="css/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="css/test.css" rel="stylesheet" type="text/css" />
    
</head>

        <%
        	int examid = (int)request.getAttribute("examid");
        	String username = (String) request.getSession().getAttribute("username");
        	String examname = (String) request.getSession().getAttribute("examname");
        	
        %>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			<p><a href="#">以下为考试题目</a></p>
        </div>
    </div>
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   
 <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
        	<div class="col-sm-12" >
        		<h5 style="float:left">考试详情</h5>
        		<p class="test_time" style="float:clear;margin-left:500px">
								<b class="alt-1">02:00</b>
							</p>
        	</div>
        	<div class="col-sm-12" style="overflow:scroll; height:400px;">
        	<div class="test_content_nr" style="background-color:white;">
        		<ul>
        		<form action="commitpaper" method="post">
        			<c:forEach items="${subject }" var="subject">
        			<li id="qu_0_0">
						<div class="test_content_nr_tt">
						<div style="margin-left:-750px;">
							<span>${subject.subjectNum}、</span><font>${subject.subjectName }</font>
						</div>	
						</div>				

											<div class="test_content_nr_main">
												<ul>
														<li class="option">
																<input type="radio" class="radioOrCheck" value="A" name="subject${subject.id }"
																	id="0_answer_1_option_1"
																/>
															<label for="0_answer_1_option_1" style="margin-left:-700px;">
																A.
																<p class="ue" style="display: inline;">${subject.optionA }</p>
															</label>
														</li>
													
														<li class="option">
																<input type="radio" class="radioOrCheck" value="B" name="subject${subject.id }"
																	id="0_answer_1_option_2"
																/>
															<label for="0_answer_1_option_2" style="margin-left:-700px;">
																B.
																<p class="ue" style="display: inline;">${subject.optionB }</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" value="C" name="subject${subject.id }"
																	id="0_answer_1_option_3"
																/>
															<label for="0_answer_1_option_3" style="margin-left:-700px;">
																C.
																<p class="ue" style="display: inline;">${subject.optionC }</p>
															</label>
														</li>
														<li class="option">
																<input type="radio" class="radioOrCheck" value="D" name="subject${subject.id }"
																	id="0_answer_1_option_4"
																/>
															<label for="0_answer_1_option_4" style="margin-left:-700px;">
																D.
																<p class="ue" style="display: inline;">${subject.optionD }</p>
															</label>
														</li>
														
														
												</ul>
												<input type="hidden" name="answer${subject.id }" value="${subject.answer }">
											</div>
										</li>
										
        		 		</c:forEach>
        		 		<input type="hidden" name="examstatus" value=1>
						<input type="hidden" class="form-control" name="score" value=1 >
						<input type="hidden" name="username" value="<%=username%>">
						<input type="hidden" name="examname" value="<%=examname%>">
        		 	<button type="submit" class="btn btn-primary block full-width m-b" style="width: 30% !important;margin-left:400px">提交试卷</button>
        		 </form>
        		</ul>
        		</div>
        		<div align="center"> 
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第 
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=1">首页</a> 
        <c:choose> 
            <c:when test="${page.pageNow - 1 > 0}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.pageNow - 1}">上一页</a> 
            </c:when> 
            <c:when test="${page.pageNow - 1 <= 0}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=1">上一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.pageNow}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 < page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.pageNow + 1}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.totalPageCount}">下一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.pageNow}">尾页</a> 
            </c:when> 
            <c:otherwise> 
                <a href="${pageContext.request.contextPath}/t_subjectByEid?examid=<%=examid %>&pageNow=${page.totalPageCount}">尾页</a> 
            </c:otherwise> 
        </c:choose> 
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
    <script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/jquery.easy-pie-chart.js"></script>
	<!--时间js-->
	<script src="js/jquery.countdown.js"></script>
	
	<script>
		window.jQuery(function($) {
			"use strict";
			
			$('time').countDown({
				with_separators : false
			});
			$('.alt-1').countDown({
				css_class : 'countdown-alt-1'
			});
			$('.alt-2').countDown({
				css_class : 'countdown-alt-2'
			});
			
		});
		
	</script>
    
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>

