<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>教师主页</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
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
            <a href="javascript:teacher_index_reload()">首页</a>
        </li>

        <li class="layui-nav-item">
            <a href="javascript:">我的信息</a>
            <dl class="layui-nav-child">
                <!--<dd><a href="javascript:load_base_information()">基本信息</a></dd>-->
                <dd><a href="javascript:load_archives_information()">档案管理</a></dd>
            </dl>
        </li>

        <li class="layui-nav-item">
            <a href="javascript:">教学管理</a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:load_teacher_applyCourese()">申请开课</a></dd>
                <dd><a href="javascript:load_garde_entering()">成绩管理</a></dd>
                <dd><a href="javascript:load_grade_query()">成绩查询</a></dd>
                <dd><a href="javascript:load_teacher_schedules()" onclick="doSearch()">课表查询</a></dd>
                <dd><a href="javascript:load_check_courseTeaching_information()">查询授课信息</a></dd>
            </dl>
        </li>
        <!--  
        <li class="layui-nav-item">
            <a href="javascript:;">科研成果</a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:load_scientificResearch_item()">科研项目</a></dd>
                <dd><a href="javascript:load_award_and_punishment()">奖惩信息</a></dd>
                <dd><a href="javascript:load_paper_and_patent()">论文专利</a></dd>
            </dl>
        </li>
        -->
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <!--  <img src="http://t.cn/RCzsdCq" class="layui-nav-img">--> 
                教师
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

<div id="teacher_index_body">
    <!-- 内容主体区域 -->
    <div style="width:100%;height: 700px">
        <fieldset class="layui-elem-field" style="margin: 30px 50px">
            <legend>通知公告</legend>
            <div class="layui-field-box">

            </div>
        </fieldset>
    </div>
</div>

<!--======放在admin里没效果========-->
<div id="teacher_footer" class="myfooter" style="text-align: center">
    <!-- 底部固定区域 -->
    © sdnu.com
</div>

<script src="../../layui/layui.js"></script>
<script>
	/***************请求***************/
	function loadXmlReq(){
		var xmlReq;
		if(window.XMLHttpRequest){
			xmlReq = new XMLHttpRequest();
		}else{
			xmlReq = new ActiveObject('Microsoft.XMLHTTP');
		}
		return xmlReq;
	}
	function sended(xmlReq,url){
		xmlReq.open("POST",url,true);
        xmlReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlReq.send();
	}
	
	function doSearch(){
		var req = loadXmlReq();
		var url="${pageContext.request.contextPath}/TeacherSchedulesServlet";
		sended(req,url);
	}


	/********后续添加的代码区*********/
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
	
    function teacher_index_reload() {
        window.location.reload()
    }
    

    <!--加载基本信息页面-->
    function load_base_information() {
        document.getElementById("teacher_index_body").innerHTML = '<object type="text/html" data="teacher_myInformation/base_information.html" ' +
            'width="100%" height="700px"></object>';}
    <!--加载档案管理页面-->
    function load_archives_information() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_myInformation/archives_information.jsp" ' +
            'width="100%" height="700px"></iframe>';}
    <!----------------------------------------------------------------------------------------------------------->
    <!--加载成绩录入页面-->
    function load_garde_entering() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_teachManage/garde_entering.jsp" ' +
            'width="100%" height="700px"></iframe>';}
    <!--加载成绩查询页面-->
    function load_grade_query() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_teachManage/grade_query.jsp" ' +
            'width="100%" height="700px"></iframe>';}
    <!--加载课表查询页面-->
    function load_teacher_schedules() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_teachManage/teacher_schedules.jsp" ' +
            'width="100%" height="700px"></iframe>';
    }
    <!--加载查询授课信息页面-->
    function load_check_courseTeaching_information() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_teachManage/check_courseTeaching_information.jsp" ' +
            'width="100%" height="700px"></iframe>';}
    <!--加载申请开课信息页面-->
    function load_teacher_applyCourese() {
        document.getElementById("teacher_index_body").innerHTML = '<iframe frameborder="0" src="teacher_teachManage/teacher_applyCourese.jsp" ' +
            'width="100%" height="700px"></iframe>';}
    <!---------------------------------------------------------------------------------------------------------1-->
    <!--加载奖惩信息页面-->
    function load_award_and_punishment() {
        document.getElementById("teacher_index_body").innerHTML = '<object type="text/html" data="teacher_scientificResearch/award_and_punishment.html" ' +
            'width="100%" height="700px"></object>';}
    <!--加载科研项目页面-->
    function load_scientificResearch_item() {
        document.getElementById("teacher_index_body").innerHTML = '<object type="text/html" data="teacher_scientificResearch/scientificResearch_item.html" ' +
            'width="100%" height="700px"></object>';}
    <!--加载论文专利页面-->
    function load_paper_and_patent() {
        document.getElementById("teacher_index_body").innerHTML = '<object type="text/html" data="teacher_scientificResearch/paper_and_patent.html" ' +
            'width="100%" height="700px"></object>';}

</script>

</body>


