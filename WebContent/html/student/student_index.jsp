<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生主页</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
    <script src="/SelectCourse/js/schdtool.js"></script>
    <style>
        .myfooter{
            position: fixed;
            left: 0px;
            right: 0;
            bottom: 0;
            height: 44px;
            line-height: 44px;
            padding: 0 15px;
            background-color: #eee;
        }
    </style>
</head>
<body>

    <div class="layui-header">
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav">

            <li class="layui-nav-item">
                <a href="javascript:student_index_reload()">首页</a>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:">我的信息</a>
                <dl class="layui-nav-child">
                    <!--<dd><a href="javascript:load_base_information()">基本信息</a></dd>-->
                    <dd><a href="javascript:load_studentStatus_information()">学籍信息</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="">培养管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:load_student_schedule()" onclick="doSearch()">学生课表</a></dd>
                    <dd><a href="javascript:load_train_plan()">培养计划</a></dd>
                    <dd><a href="javascript:load_grade_query()">成绩查询</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">学生选课</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:load_onlineSelection_course()">选课</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                   <!--  <img src="http://t.cn/RCzsdCq" class="layui-nav-img">--> 
                    学生
                </a>
                <!-- 
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
                 -->
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath }/LogoutServlet">退出</a></li>
        </ul>
    </div>

    <div id="student_index_body">
        <!-- 内容主体区域 -->
        <div id="first_index_body" style="width: 100%;height: 700px">
            <fieldset class="layui-elem-field" style="margin: 30px 50px;border-width: 1.5px" >
                <legend>通知公告</legend>
                <div class="layui-field-box">
					<table class="layui-table">
			            <colgroup>
			            </colgroup>
			            <thead>
			            <tr>
			                <th style="text-align: center">发布时间</th>
			                <th style="text-align: center">发布的信息</th>
			
			            </tr>
			            </thead>
			            <tbody>
			  			<c:forEach var="notice" items="${sessionScope.noticeList }" begin="0" end="5" step="1">
			            	<tr align="center">
			            		<td>${notice.noticeTime }</td>
			            		<td>${notice.noticeContent }</td>
			            	</tr>
			            </c:forEach>
			            </tbody>
		        	</table>
                </div>
            </fieldset>
        </div>
    </div>

    <!--======放在admin里没效果========-->
    <div id="student_footer" class="myfooter" style="text-align: center">
        <!-- 底部固定区域 -->
        © sdnu.com
    </div>


    <script src="/SelectCourse/layui/layui.js"></script>
    <script>
		function doSearch(){
			var req = loadXmlReq();
			var url="${pageContext.request.contextPath}/StudentSchduleServlet";
			sended(req,url);
		}
    
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });

        function student_index_reload() {
            window.location.reload()
        }


        <!--加载基本信息页面-->
        function load_base_information() {
            document.getElementById("student_index_body").innerHTML = '<object type="text/html" data="student_myInformation/base_information.html" ' +
                'width="100%" height="700px"></object>';}
        <!--加载联系方式页面-->
        function load_studentStatus_information() {
            document.getElementById("student_index_body").innerHTML = '<object type="text/html" data="student_myInformation/studentStatus_information.html" ' +
                'width="100%" height="700px"></object>';}
        <!---------------------------------------------------------------------------------------------------------1-->
        <!--加载学生课表页面-->
        function load_student_schedule() {
            document.getElementById("student_index_body").innerHTML = '<iframe frameborder="0" src="student_trainManage/student_schedule.jsp" ' +
                'width="100%" height="700px"></iframe>';}
        <!--加载培养计划页面-->
        function load_train_plan() {
            document.getElementById("student_index_body").innerHTML = '<iframe frameborder="0" src="student_trainManage/train_plan.jsp" ' +
                'width="100%" height="700px"></iframe>';}
        <!--加载成绩查询页面-->
        function load_grade_query() {
            document.getElementById("student_index_body").innerHTML = '<iframe frameborder="0" src="student_trainManage/grade_query.jsp" ' +
                'width="100%" height="700px"></iframe>';}
        <!--加载查询选课页面-->
        function load_check_courseSelection_information() {
            document.getElementById("student_index_body").innerHTML = '<iframe frameborder="0" src="student_trainManage/check_courseSelection_information.jsp" ' +
                'width="100%" height="700px"></iframe>';}
        <!---------------------------------------------------------------------------------------------------------1-->
        <!--加载选课页面-->
        function load_onlineSelection_course() {
            document.getElementById("student_index_body").innerHTML = '<iframe frameborder="0" src="student_onlineSelection/onlineSelection_course.jsp" ' +
                'width="100%" height="700px"></iframe>';}

    </script>

</body>


</html>