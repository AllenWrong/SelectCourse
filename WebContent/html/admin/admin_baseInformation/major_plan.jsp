<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>专业计划管理</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>专业计划管理</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <!--专业计划管理的相关内容-->
        <div class="layui-tab-item layui-show">
            <form action="" id="find" class="layui-form" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">年级：</label>
                        <div class="layui-input-block">
                            <select id="nianji" name="nianji" >
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业：</label>
                        <div class="layui-input-block">
                            <select id="zhuanye" name="major" >
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <!--<div class="layui-inline">-->
                        <!--<label class="layui-form-label">学年：</label>-->
                        <!--<div class="layui-input-block">-->
                            <!--<select name="term" >-->
                                <!--<option value=""></option>-->
                                <!--<option value="0">2017-2018</option>-->
                                <!--<option value="1">2018-2019</option>-->
                                <!--<option value="2">2019-2020</option>-->
                                <!--<option value="3">2020-2021</option>-->
                            <!--</select>-->
                        <!--</div>-->
                    <!--</div>-->
                <div class="layui-inline">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn" lay-filter="dosearch" id="dosearch">查询</button>
                    </div>
                </div>
                </div>
            </form>
            <div style="display: none" id="userToolBar" lay-filter="userToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            
        </div>
    </div>
         <table class="layui-table" align="center" id="majorplan_table" lay-filter="majorplan_table"></table>
    
    <div style="display: none;padding: 20px" id="addOrUpdateDiv">
            <form class="layui-form " action="" lay-filter="addmajorplan" id="addmajorplan">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业计划编号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="majorPlanID"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="majorPlanName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">所属年级:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="majorPlanGrade"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业必修学分:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="majorPlanComcre" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业选修学分:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="majorPlanElcre" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal " lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm  " >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加和修改的弹出层结束 -->
    
</div>
<script src="../../../layui/layui.js"></script>
<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
    });
</script>

<script type="text/javascript">

layui.use(['element','form','table', 'layer', 'laypage'],function(){
    var $ = layui.jquery;
    var element = layui.element;
    var form = layui.form;
    var  layer = layui.layer;//弹层
    var table = layui.table;//表格
    var  laypage = layui.laypage;

    //渲染表格
	var tableIns = table.render({
        id:'majorplan_id'
        ,elem: '#majorplan_table'
        , url: '${pageContext.request.contextPath}/MajorPInfoServlet' //请求路径
        ,defaultToolbar:[],
        page:true
        , toolbar: '#userToolBar'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [ // 表头
            [
                { type: 'checkbox', width: 120, sort: false, fixe: 'dleft'}
                , {field: 'majorPlanID', title: '序号',sort:true}
                , {field: 'majorPlanNum', title: '专业计划编号'}
                , {field: 'majorPlanName', title: '专业名'}
                , {field: 'majorPlanGrade', title: '所属年级'}
                , {field: 'majorPlanComcre', title: '专业必修学分'}
                , {field: 'majorPlanElcre', title: '专业选修学分'}
            ]
        ]
    });	
    
    //动态从数据库获取下拉列表
    $.ajax({
        url: '${pageContext.request.contextPath}/MPMajorServlet',//请求路径
        //dataType: 'json',
        data:{'state': 0},	//查询状态为正常的所有机构类型
        type: 'POST',
        success: function (data) {
            var data = JSON.parse(data);
            $.each(data, function (index, item) {
                $('#zhuanye').append(new Option(item.majorName, item.majorName));// 下拉菜单添加元素，new Option(text,value);
            });
            //重新渲染下拉菜单
            layui.form.render("select");
        },
        error:function(ajaxContext){
        	alert("404");
        }
    });
    
    $.ajax({
        url: '${pageContext.request.contextPath}/MPGradeServlet',//请求路径
        //dataType: 'json',
        data:{'state': 0},	//查询状态为正常的所有机构类型
        type: 'POST',
        success: function (data) {
            var data = JSON.parse(data);
            $.each(data, function (index, item) {
                $('#nianji').append(new Option(item.majorGrade, item.majorGrade));// 下拉菜单添加元素，new Option(text,value);
            });
            //重新渲染下拉菜单
            layui.form.render("select");
        },
        error:function(ajaxContext){
        	alert("404");
        }
    });

    //模糊查询（后台要自己写）
    $("#dosearch").click(function () {
        fuzzy_search();
    });

    //模糊查询函数
    function fuzzy_search(){
		// 获取变量
    	var grade = $("#nianji");
		var major = $("#zhuanye");
        table.reload('majorplan_id', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	// 定义请求的json数据，逗号隔开
                grade:grade.val(),
 	            major:major.val()
            }
        });
    }


    //监听头部
    table.on('toolbar(majorplan_table)',function (obj) {

        var checkStatus = table.checkStatus('majorplan_id'); //获取选中的行

        data = checkStatus.data;
        console.log(data.length);
        console.log(data);
        switch (obj.event) {
            case 'add':
                //监听复选框选择
                openAddCourse();
                break;

            case 'delete':
                batchDelet(data);
                break;

            case 'edit':
                if (data.length<1){
                    layer.msg("请选择一个需要修改的用户");
                }
                else if(data.length>1){
                    layer.msg("只能选择一个需要修改的用户");
                }
                else {
                    openUpdateCourse(data[0]);
                }
                break;

            case 'reloadTable':
                tableIns.reload();
                break;
        }
    });

    var url;
    var mainIndex;
    //打开添加页面
    function openAddCourse(){
        mainIndex=layer.open({
            type:1,
            title:'添加专业计划信息',
            content:$("#addOrUpdateDiv"),
            area:['800px','400px'],
            success:function(){
                //清空表单数据
                $("#addmajorplan")[0].reset();
                url="/SelectCourse/AddMajorPServlet"; //请求路径
            }
        });
    }

   	// 打开修改页面
    function openUpdateCourse(data){
        mainIndex=layer.open({
            type:1,
            title:'修改专业计划信息',
            content:$("#addOrUpdateDiv"),
            area:['800px','400px'],
            success:function(index){
            	form.val("addmajorplan",data);
            	url = "${pageContext.request.contextPath }/AddMajorPServlet";
            }
        });
    }

    //1.保存（添加功能、修改功能）
    //2.form.on是表单监听，submit表示监听监听提交按钮，doSubmit是提交按钮中的button中lay-filter值
    form.on("submit(doSubmit)",function(obj){
		// 拿到表单数据
    	var params=obj.field;
		var jsonObj = {
			'majorPlanID':params.majorPlanID,
			'majorPlanNum':params.majorPlanNum,
			'majorPlanName': params.majorPlanName,
			'majorPlanGrade': params.majorPlanGrade,
			'majorPlanComcre': params.majorPlanComcre,
			'majorPlanElcre': params.courType
		};
		var datastr = JSON.stringify(jsonObj);
		
        console.log(jsonObj);
        //url：请求路径，params：弹出表单中的数据
        $.post(url,datastr,function(obj){
        	var jsonObj = JSON.parse(obj);
        	if(jsonObj.msg == "true"){
        		alert("操作成功");
        	}
        	if(jsonObj.msg == "false"){
        		alert("操作失败");
        	}
            //关闭弹出层
            layer.close(mainIndex)
            //刷新数据表格
            tableIns.reload();
        })
    });

});
</script>
</body>
</html>