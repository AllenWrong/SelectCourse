<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员主页</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <%
    	String loadPath = "admin_teachManage/course_arranging.jsp";
    %>
</head>
<body class="layui-layout-body" >
    <div class="layui-layout layui-layout-admin">

        <!--头部水平导航栏-->
        <div class="layui-header">
            <!--logo,用项目名代替"学生选课系统"-->
            <div class="layui-logo" style="color: white ;font-size: large;font-weight: bold">学生选课系统</div>
            <!-- 头部水平导航栏左侧相关信息 -->
            <div class="layui-nav layui-layout-left">
                <li class="layui-nav-item" >
                    <a style="font-size: larger" href="javascript:admin_index_reload()">首页</a>
                </li>
            </div>
            <!-- 头部水平导航栏右侧相关信息 -->
            <ul class="layui-nav layui-layout-right" >
                <!--管理员下拉菜单-->
                <li class="layui-nav-item">
                    <a href="javascript:;">
                       <!--  <img src="http://t.cn/RCzsdCq" class="layui-nav-img">-->
                        管理员
                    </a>
               <!--   <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                -->    
                </li>
                <!--退出按钮-->
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath }/LogoutServlet">退出</a>
                </li>
            </ul>
        </div>

        <!--侧边导航栏-->
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <!--基础信息管理-->
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:">基础信息管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:load_major_plan()">专业计划管理</a></dd>
                            <dd><a href="javascript:load_term_plan()">学期计划管理</a></dd>
                            <dd><a href="javascript:load_course_information()">课程信息管理</a></dd>
                        </dl>
                    </li>
                    
                    <!--学生管理-->
                    <!--  
                    <li class="layui-nav-item">
                        <a href="javascript:">学生管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:load_student_status()">学生学籍管理</a></dd>
                            <dd><a href="javascript:load_graduation()">毕业管理</a></dd>
                        </dl>
                    </li>-->
                    <!--教师管理-->
                    <!--  
                    <li class="layui-nav-item">
                        <a href="javascript:">教师管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:load_teacher_information()">教师信息管理</a></dd>
                            <dd><a href="javascript:load_scientific_research()">科研管理</a></dd>
                            <dd><a href="javascript:load_award_and_punishment()">奖惩管理</a></dd>

                        </dl>
                    </li>   -->
                
                    <!--教学管理-->
                    <li class="layui-nav-item">
                        <a href="javascript:">教学管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:load_course_arranging()">排课管理</a></dd>
                            <dd><a href="javascript:load_course_selecting()">选课管理</a></dd>
                            <dd><a href="javascript:load_course_opening()">开课管理</a></dd>
                            <!--  <dd><a href="javascript:load_grade()">成绩管理</a></dd>-->
                        </dl>
                    </li>
                    <!--教务办公管理-->
                    <li class="layui-nav-item">
                        <a href="javascript:">教务办公管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:load_announcement()">发布公告</a></dd>

                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!--右侧主体内容-->
        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;" id="admin_index_body">
                <!-- 内容主体区域 -->
                <fieldset class="layui-elem-field" style="margin: 30px 50px;border-width: 1.5px" >
                        <legend>项目信息</legend>
                        <div class="layui-field-box">

                        </div>
                    </fieldset>

            </div>
        </div>

        <!--定义文档或文档部分区域的页脚-->
        <div class="layui-footer" style="text-align: center">
            <!-- 底部固定区域 -->
            © sdnu.com
        </div>
    </div>
    <script src="../../layui/layui.js"></script>
    <script>
    	function loadXmlReq(){
            //定义异步请求对象
            var xmlReq;
            //检测浏览器是否直接支持ajax
            if(window.XMLHttpRequest){//直接支持ajax
              xmlReq=new XMLHttpRequest();
            }else{//不直接支持ajax
              xmlReq=new ActiveObject('Microsoft.XMLHTTP');
            }
            return xmlReq;
    	}
    	
    	function send(xmlReq,url){
            xmlReq.open("POST",url,true);
            xmlReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xmlReq.send();
    	}
    
        //JavaScript代码区域
        function loadNotice(){
			var xmlReq = loadXmlReq();
             //创建异步Post请求
             var url="${pageContext.request.contextPath}/NoticeServlet";
             send(xmlReq,url);
        }
        function loadMajorPlan(){
        	var xmlReq = loadXmlReq();
        	
        	var url="${pageContext.request.contextPath}/MajorPlanServlet";
        	send(xmlReq,url);
        }
        function loadCourseInfo(){
        	var xmlReq = loadXmlReq();
        	var url = "${pageContext.request.contextPath}/CourseInfoServlet";
        	send(xmlReq,url);
        }
        
        /************上面是根据需求额外加的代码*************/
        
        layui.use('element', function(){
            var element = layui.element;

        });
        function admin_index_reload() {
            window.location.reload()
        }

        <!--==================================利用javascript动态加载页面=============================-->
        <!--加载排课管理页面-->
        function load_course_arranging() {
            document.getElementById("admin_index_body").innerHTML = '<iframe type="text/html" frameborder="0" src="admin_teachManage/course_arranging.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!--加载选课管理页面-->
        function load_course_selecting() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0"  src="admin_teachManage/course_selecting.jsp" ' +
                'width="100%" height="600px"></iframe>';}

        <!--加载开课管理页面-->
        function load_course_opening() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0" src="admin_teachManage/course_opening.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!--加载成绩管理页面-->
        function load_grade() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0"  src="admin_teachManage/grade.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!-------------------------------------------------------------------------------------------------------------------1---->
        <!--加载专业计划管理页面-->
        function load_major_plan() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0" src="admin_baseInformation/major_plan.jsp" ' +
                'width="100%" height="600px"></iframe>';
            loadMajorPlan();        
        }
        <!--加载学期计划管理页面-->
        function load_term_plan() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0"  src="admin_baseInformation/term_plan.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!--加载课程信息管理页面-->
        function load_course_information() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0" src="admin_baseInformation/course_information.jsp" ' +
                'width="100%" height="600px"></iframe>';
            loadCourseInfo();       
        }
        <!---------------------------------------------------------------------------------------------------------------------1-->
        <!--加载学生学籍管理页面-->
        function load_student_status() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0" src="admin_studentManage/student_status.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!--加载毕业管理页面-->
        function load_graduation() {
            document.getElementById("admin_index_body").innerHTML = '<iframe frameborder="0" src="admin_studentManage/graduation.jsp" ' +
                'width="100%" height="600px"></iframe>';}
        <!---------------------------------------------------------------------------------------------------------------------1-->
        <!--加载教师信息管理页面-->
        function load_teacher_information() {
            document.getElementById("admin_index_body").innerHTML = '<object type="text/html" data="admin_teacherManage/teacher_information.html" ' +
                'width="100%" height="600px"></object>';}
        <!--加载科研管理页面-->
        function load_scientific_research() {
            document.getElementById("admin_index_body").innerHTML = '<object type="text/html" data="admin_teacherManage/scientific_research.html" ' +
                'width="100%" height="600px"></object>';}
        <!--加载奖惩管理页面-->
        function load_award_and_punishment() {
            document.getElementById("admin_index_body").innerHTML = '<object type="text/html" data="admin_teacherManage/award_and_punishment.html" ' +
                'width="100%" height="600px"></object>';}
        <!---------------------------------------------------------------------------------------------------------------------1-->
        <!--加载发布公告页面-->
        function load_announcement() {
            document.getElementById("admin_index_body").innerHTML = '<iframe type="text/html" frameborder="0" src="admin_officeManage/announcement.jsp" ' +
                'width="100%" height="600px"></iframe>';
            loadNotice();        
        }
    </script>
</body>
</html>