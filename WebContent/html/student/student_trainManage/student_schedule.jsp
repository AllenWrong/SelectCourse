<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生课表</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
    <script src="/SelectCourse/js/schdtool.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>课表查询</legend>
</fieldset>
<div class="layui-tab layui-tab-card" style=" padding:20px 50px;margin-left: 100px;margin-right: 100px;margin-bottom: 50px" >
        <table class="layui-table" style="text-align: center">
            <thead>
            <tr align = center>
                <th width="10%" colspan="2"></th>
    
                <th style="text-align: center" width="18%">星期一</th>
                <th style="text-align: center" width="18%">星期二</th>
                <th style="text-align: center" width="18%">星期三</th>
                <th style="text-align: center" width="18%">星期四</th>
                <th style="text-align: center" width="18%">星期五</th>
            </tr>
            </thead>
            <tbody >
            <!--上午-->
            <tr><td rowspan="2" align="center">上<br>午<br></td>
                <td height = "100px">1</td>
                <td rowspan="1" id="1-1">周一1</td>
                <td rowspan="1" id="2-1">周二1</td>
                <td rowspan="1" id="3-1">周三1</td>
                <td rowspan="1" id="4-1">周四1</td>
                <td rowspan="1" id="5-1">周五1</td>
            </tr>
    
            <tr>
                <td height = "100px">2</td>
                <td rowspan="1" id="1-2">周一2</td>
                <td rowspan="1" id="2-2">周二2</td>
                <td rowspan="1" id="3-2">周三2</td>
                <td rowspan="1" id="4-2">周四2</td>
                <td rowspan="1" id="5-2">周五2</td>
            </tr>

            <!--下午-->
            <tr><td rowspan="2" align="center">下<br>午<br></td>
                <td height = "100px">3</td>
                <td rowspan="1" id="1-3">周一3</td>
                <td rowspan="1" id="2-3">周二3</td>
                <td rowspan="1" id="3-3">周三3</td>
                <td rowspan="1" id="4-3">周四3</td>
                <td rowspan="1" id="5-3">周五3</td>
            </tr>
        
            <tr>
                <td height = "100px">4</td>
                <td rowspan="1" id="1-4">周一4</td>
                <td rowspan="1" id="2-4">周二4</td>
                <td rowspan="1" id="3-4">周三4</td>
                <td rowspan="1" id="4-4">周四4</td>
                <td rowspan="1" id="5-4">周五4</td>
            </tr>

            <tr><td rowspan="1" align="center">晚<br>上<br></td>
                <td height = "100px">3</td>
                <td rowspan="1" id="1-5">周一3</td>
                <td rowspan="1" id="2-5">周二3</td>
                <td rowspan="1" id="3-5">周三3</td>
                <td rowspan="1" id="4-5">周四3</td>
                <td rowspan="1" id="5-5">周五3</td>
            </tr>
            </tbody>
        </table>
</div>
	<script src="/SelectCourse/layui/layui.js"></script>
	<script>
	    layui.use(['element','form'],function(){
	        var element = layui.element;
	        var form = layui.form;
	    });
	</script>
	
   	<c:forEach var="obj" items="${sessionScope.stuSchdl }" begin="0" end="${fn:length(sessionScope.stuSchdl) }" step="1">
		<script type="text/javascript">
       		var name = '${obj.selectCourseNum}';
       		var time = formatTime("${obj.selTeaTime}");
       		var teaName = "${obj.selectTeaNum}";
       		var place = "${obj.selTeaPlace}";
       		var startend = "1-18";
       		var res = name+"<br/>"+time+"<br/>"+teaName+"<br/>"+place+"{"+startend+"周"+"}";
       		
			document.getElementById("${obj.selTeaTime}").innerHTML= res;
		</script>	
	</c:forEach>
</body>
</html>