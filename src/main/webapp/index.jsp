<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String appPath = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
</head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript">     
function countDown(secs,surl){         
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
     }     
 else{       
     location.href=surl;     
	 -ma
     }     
 }     
</script>

<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header text-center">
					<h1>欢迎使用登录</h1>
					
					<span id="jumpTo">3</span>秒后系统自动跳转
					<script type="text/javascript">
	countDown(3,'<%=appPath%>/login');
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
 --%>
 
 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base herf="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>教学工具</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name" style="margin-left: -100px;">Teach</h1>

            </div>
            <h3>欢迎使用教学工具</h3>

            <form class="m-t" role="form" action="checkLogin" method="post" >
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" id="name" name="name">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" id="email" name="email">
                </div>
                <div class="form-group text-left">
                    <div class="checkbox i-checks">
                        
                            <input type="radio" name="isAdmin" value=1><i></i>教师
                        
                            <input type="radio" name="isAdmin" value=0 style="margin-left:30px"><i></i>学生
                    </div>
                    
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="regist">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
   <!--  <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script> -->
</body>
</html>