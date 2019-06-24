<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%> 

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
    <script src="js/jquery-1.7.2.min.js"></script>
    <style>
.box > div {
  width: 50px;
  height: 50px;
  background: gray;
  color: #fff;
  margin: 10px;
  float: left;
  cursor: pointer;
  text-align:center;
  line-height:50px;
}
.box > div.on {
  background: lightsalmon;
}

input[type="radio"] {
    display: none;
}
input[type="radio"]+span {
    display: inline-block;
    width: 50px;
    height: 50px;
    font-size: 15px;
    line-height:50px;
    text-align: center;
    border: 0px;

}
input[type="radio"]:checked+span {
   
    background-color: lightsalmon;
}
</style>
</head>
<%
	String signname = (String) request.getAttribute("signname");
%>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
			<p><a href="addsign">以往签到</a></p>
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
   
 <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
        	<div class="col-sm-12">
        		<h5 style="float:left">签到历史</h5>
        		<h5 style="margin-left:80px"><a href="addsign">发布签到</a></h5>
        	</div>
        	<div class="col-sm-12">
	        	<form class="m-t" role="form" action="updateSignStatus" method="post" >
	        		<div class="box" align="center">
						<div class="i1"><label><input type="radio" name="seatnum" value=01 /><span>01</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=02 /><span>02</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=03 /><span>03</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=04 /><span>04</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=05 /><span>05</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=06 /><span>06</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=07 /><span>07</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=08 /><span>08</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=09 /><span>09</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=10 /><span>10</span></label></div><br /><br /><br /><br /><br />
						<div class="i1"><label><input type="radio" name="seatnum" value=11 /><span>11</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=12 /><span>12</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=13 /><span>13</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=14 /><span>14</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=15 /><span>15</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=16 /><span>16</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=17 /><span>17</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=18 /><span>18</span></label></div>
						<div class="i1"><label><input type="radio" name="seatnum" value=19 /><span>19</span></label></div>
						<div class="i2"><label><input type="radio" name="seatnum" value=20 /><span>20</span></label></div>
		  
					</div> 
					<%-- <div class="form-group">
	                    <input type="hidden" class="form-control" placeholder="签到id" name="signId" value="${sign.id }">
	                </div> --%>
	                <div class="form-group">
	                    <input type="hidden" class="form-control" placeholder="签到名称" name="signName" value="<%=signname %>">
	                </div>
					<div class="form-group">
	                    
	                    <input type="hidden" class="form-control" placeholder="签到状态" name="status" value=1>
	                </div>
	                
					<div style="margin-top:80px;margin-left:1 0px">
						<input type="text" class="form-control" placeholder="学号" name="username" style="width:30%!important">
	        			<button type="submit" class="btn btn-primary block full-width m-b" style="width:30%!important;margin-top:20px">确认签到</button>
	        		</div>
	        	</form>
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
    <script>
$(function () {
  $('.box > div').click(function () {
    $(this).toggleClass('on');
    var str = '';
    $('.box > div').each(function () {
      if ($(this).hasClass('on')) {
        str += $(this).text() + '位,';
      };
    });
    var newstr = str.substring(0, str.length - 1);
    $('.text').text(newstr);
  });
});
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

