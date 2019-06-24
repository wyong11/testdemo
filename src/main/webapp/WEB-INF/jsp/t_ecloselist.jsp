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
</head>

        <%
        	String classname = (String)request.getAttribute("classname");
        	
        %>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			<p><a href="#">以下为<%=classname %>班考试信息</a></p>
        </div>
    </div>
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   
 <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
        	<div class="col-sm-12">
        		<h5 style="float:left">考试详情</h5>
        	</div>
        	<div class="col-sm-12">
        		<table border="0" cellpadding="10" cellspacing="0" class="table table-striped">
					<thead>
						<tr>
							
							<td>考试名称</td>
							<td>班级名称</td>
							<td>课程名称</td>
							<td>开考时间</td>
							<td>结束时间</td>
							<td>考试时长</td>
							<td>操作</td>
							
						</tr>
					</thead>
					<c:forEach items="${examClose }" var="examClose">
				        <tr>
				        	<td>
				        		${examClose.examName }
				        		
				        	</td>
				            <td>${examClose.classname }</td>
				            <td>${examClose.coursename }</td>
				            <td>${examClose.startTime }</td>
				            <td>${examClose.endTime }</td>
				            <td>${examClose.timelength }分钟</td>
				            <td>
				            <fmt:parseDate value="${examClose.endTime}" pattern="yyyy-MM-dd'T'HH:mm" var="endDate"></fmt:parseDate>
          					<fmt:parseDate value="${examClose.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="startDate"></fmt:parseDate>
				            <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
							<c:choose>
								<c:when test="${nowDate - endDate.getTime() > 0}">
									<span class="STYLE1">考试已结束</span>
									
								</c:when>
								<c:when test="${nowDate - startDate.getTime() < 0}">
									<span class="STYLE1">考试时间未到</span>
									
								</c:when>
								<c:otherwise>
									<span class="STYLE2">考试进行中</span>
									<a type="button" href="s_subjectByEid?examid=${examClose.id }&examname=${examClose.examName}" class="btn btn-info btn-sm">
                                		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                                                          进入考试
                                	</a>
								</c:otherwise>
							</c:choose>
                            </td>
				            
				        </tr>
				    </c:forEach>
				</table>
        		<div align="center"> 
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第 
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=1">首页</a> 
        <c:choose> 
            <c:when test="${page.pageNow - 1 > 0}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.pageNow - 1}">上一页</a> 
            </c:when> 
            <c:when test="${page.pageNow - 1 <= 0}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=1">上一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.pageNow}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 < page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.pageNow + 1}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.totalPageCount}">下一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.pageNow}">尾页</a> 
            </c:when> 
            <c:otherwise> 
                <a href="${pageContext.request.contextPath}/eCloseByClass?classname=<%=classname %>&pageNow=${page.totalPageCount}">尾页</a> 
            </c:otherwise> 
        </c:choose> 
    </div> 
        	</div>
        	
        	<%-- <c:forEach items="${signlist}" var="data" varStatus="status"> 
	            <div class="col-sm-6">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5><a href="getmembers?id=${data.username}">${data.username }</a></h5>
	                        <h5>${data.name }</h5>
	                        <div class="ibox-tools">
	                            <a class="collapse-link">
	                                <i class="fa fa-chevron-up"></i>
	                            </a>
	                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
	                                <i class="fa fa-wrench"></i>
	                            </a>
	                            <ul class="dropdown-menu dropdown-user">
	                                <li><a href="graph_flot.html#">选项1</a>
	                                </li>
	                                <li><a href="graph_flot.html#">选项2</a>
	                                </li>
	                            </ul>
	                            <a class="close-link">
	                                <i class="fa fa-times"></i>
	                            </a>
	                        </div>
	                    </div>
	                </div>
	            </div>
           
        	</c:forEach> --%>
        	
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

