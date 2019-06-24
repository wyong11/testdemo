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
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			<p><a href="">文件列表</a></p>
        </div>
        <!-- <div class="col-sm-4">
			<p><a href="addmember">添加成员</a></p>
        </div>
        <div class="col-sm-4">
			<p><a href="addmember">添加团队</a></p>
        </div> -->

    </div>
		<!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div class="gray-bg" style="margin-left: 0px">
   <%
   	String classname = (String)request.getAttribute("classname");
   String coursename = (String)request.getAttribute("coursename");
   String username = (String)request.getSession().getAttribute("username");
   %>
 <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
        	<div class="col-sm-12">
        		<h5 style="float:left"><%=coursename %>课程作业列表</h5>
        		
        	</div>
        	<div class="col-sm-12">
        		<table border="0" cellpadding="10" cellspacing="0" class="table table-striped">
					<thead>
						<tr>
							<td>班级名</td>
							<td>课程名称</td>
							<td>作业名称</td>
							<td>作业内容</td>
							<td>操作</td>
						</tr>
					</thead>
					<c:forEach items="${work }" var="work">
				        <tr>
				            <td>${work.classname }</td>
				            <td>${work.coursename }</td>
				            <td>${work.workname }</td>
				            <td>${work.workcontent }</td>
				            <td>
									
									<%-- <a type="file" href="filecontent?username=${files.username }&filename=${files.filename }" class="btn btn-info btn-sm">
                                		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                                                          提交作业
                                	</a>
                                	<input type="file" name="workfile" > --%>
                                	<form action="commitFile2" method="post" enctype="multipart/form-data">
				                        
											<input type="hidden" name="username" value="<%=username %>">
											<input type="hidden" name="workname" value="${work.workname }">
											<input type="hidden" name="status" value=1 >
										    <input type="file" name="file" size="50"/>
										    <input type="text" name="note" placeholder="备注">
											<button type="submit">提交作业</button>
										</span>
				                </form>

				            </td>
				        </tr>
				    </c:forEach>
				</table>
        		<div align="center"> 
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第 
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=1">首页</a> 
        <c:choose> 
            <c:when test="${page.pageNow - 1 > 0}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.pageNow - 1}">上一页</a> 
            </c:when> 
            <c:when test="${page.pageNow - 1 <= 0}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=1">上一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.pageNow}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 < page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.pageNow + 1}">下一页</a> 
            </c:when> 
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.totalPageCount}">下一页</a> 
            </c:when> 
        </c:choose> 
        <c:choose> 
            <c:when test="${page.totalPageCount==0}"> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.pageNow}">尾页</a> 
            </c:when> 
            <c:otherwise> 
                <a href="${pageContext.request.contextPath}/workByCC?classname=<%=classname %>&coursename=<%=coursename %>&pageNow=${page.totalPageCount}">尾页</a> 
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
    <script type="text/javascript">
    function upload() {
        var files = $('input[name="fileField"]').prop('files');//获取到文件列表

        if (files.length == 0) {
            alert('请选择文件');
            return;
        } else {
            var reader = new FileReader();//新建一个FileReader
            reader.readAsText(files[0], "UTF-8");//读取文件
            reader.onload = function (evt) { //读取完文件之后会回来这里
                var fileString = evt.target.result;
                
                var date = {"file": fileString};
                $.post("commitFile", date).success(function (result) {
                    alert("ok");
                });
            }
        }
    }
</script>

        
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

