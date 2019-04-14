<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 首页，用户已登陆 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>签到打卡</title>
</head>
<body>
    <div >
        <style type="text/css">
            *{margin:0;padding:0;list-style-type:none;}
            a,img{border:0;text-decoration:none;}
            /*今日签到*/
            .singer{border:1px solid #DCDBDB;padding:10px;height:45px;line-height:45px;width:290px;margin:20px auto;}
            .singer_l_cont, .singer_r_img{float:left;}
            .singer_l_cont{width:145px;background:url(../../images/sing_per.gif) no-repeat left 12px;text-indent:23px;font-size:12px;}
            .singer_r_img{display:block;width:114px;height:52px;background:url(../../images/sing_week.gif) right 2px no-repeat;vertical-align:middle;float:right;*margin-bottom:-10px;}
            .singer_r_img:hover{background-position:right -53px;text-decoration:none;}
            .singer_r_img span{margin-left:14px;font-size:16px;font-family:'Hiragino Sans GB','Microsoft YaHei',sans-serif !important;font-weight:700;color:#165379;}
            .singer_r_img.current{background:url(../../images/sing_sing.gif) no-repeat 0 2px;}
        </style>
<table >
    <tr><td> <div class="singer">
                <div class="singer_l_cont">
                <span>每天签到赢取PK币</span>
            </div>
            <div class="singer_r_r">
                <a class="singer_r_img" href="#">
                    <span id="sing_for_number"></span>
                </a>
            </div>
        </div><!--singer end--></td>
        <td><div class="tcoin">
            <span ><font color="#ffd700" id="jingbi">金币:${money}</font></span>
        </div></td></tr>
</table>
        <script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
            /*签到模块日期捕捉：*/
            function week(){
                var objDate= new Date();
                var week = objDate.getDay();
                switch(week)
                {
                    case 0:
                        week="1";
                        break;
                    case 1:
                        week="2";
                        break;
                    case 2:
                        week="3";
                        break;
                    case 3:
                        week="4";
                        break;
                    case 4:
                        week="5";
                        break;
                    case 5:
                        week="6";
                        break;
                    case 6:
                        week="7";
                        break;
                }
                $("#sing_for_number").html( week );
            }

            $(document).ready(function(){
                week();
                $(".singer_r_img").click(function(){
                    $(this).addClass("current");
					//这里使用了Ajax使得签到后还保持登录状态
                    var url="<%=request.getContextPath()%>/daka.html";  // 请求的url
                    $.post(
                        url,
                        {'name':${name}},
                        function (data)
                        {
                            if(data=="true")
                            {
                                alert("已签到，请不要重复签到")
                            }
                            else {
                                location.reload();//刷新页面，因为签到后金币数量改变需要刷新页面才能正确显示
                                alert("签到成功")
                            }

                        }
                    );

                })
            })
        </script>
    </div>
    </body>
</html>
