<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>批量添加用户</title>
    <style type="text/css">
        .tab{
            text-align: center;
            height: 40px;
            line-height: 40px;
        }
        .tab{
            background-color: #F8F8F8;
        }
    </style>
</head>
<body>
    <table >
        <thead>
        <tr>
            <th>账号</th><th>密码</th>
        </tr>
        </thead>
        <tbody id="tbody01">
        <tr>
            <td><input type="text" name="username"/></td>
            <td><input type="text" name="password"/></td>
        </tr>
        </tbody>
    </table>
    <input type="button" id="addAll" value="批量添加" style="display: block;width: 100px;height: 40px; margin: 0 auto" />

    <table class="tab" border="1" align="center" width="60%" bordercolor="#E2E2E2">
    <tr class="th">
        <th>账号</th><th>密码</th>
    </tr>
    <c:forEach var="emp" items="${emps}">
        <tr>
            <td>${emp.username}</td>
            <td>${emp.password}</td>
        </tr>
    </c:forEach>
</table>
</body>
<script src="../../js/jquery.js"></script>
<script type="text/javascript">

    $(function () {
        var tbody = $("#tbody01");
        trNode = tbody.clone();
        tbody.on("click", " .empAdd", function () {
            $("#tbody01").append(trNode.clone());
        });

        tbody.on("click",".empRem",function () {
           var num = $("tr",tbody).length;
           if( num === 1){
               alert("最后一行不能删除");
               return false;
           }
           $(this).parent().parent().remove();
        });
    });
    /*数组*/
   $("#addAll").click(function(){
        var list = [];
        $("#tbody01 tr").each(function (i,obj) {
            list.push(
                {
                    username:$("input[name=username]",obj).val(),
                    password:$("input[name=password]",obj).val(),
                    /* 
                    education:$("select[name=education]",obj).val(),
                    monthly:$("input[name=monthly]",obj).val() */
                }
            );
        });
        console.log(list);
        $.post({
            url:"/emp01",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(list)
        }).done(function (data) {
            if(data.msg === "success"){
                window.location.href = "/success";
            }
        });
   });



</script>
</html> --%>


<%@ page language="java" import="com.sy.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
    <form action="registerInfo" method="post">
    	 
   		<table>
			<tr>
				<td>默认账号：</td>
				<td><input type="text" id="username" name="username"></input></td>
			</tr>
			<tr>
				<td>班级人数：</td>
				<td><input type="userNum" id="userNum" name="userNum"></input></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册"></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
    	
      </form>
  </body>
</html>
