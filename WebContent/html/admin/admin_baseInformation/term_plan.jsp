<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学期计划管理</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学期计划管理</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <!--学期计划管理的相关内容-->
        <div class="layui-tab-item layui-show">
            <form action="" class="layui-form" method="post">
                <div class="layui-form-item">
                    <!--<div class="layui-inline">-->
                        <!--<label class="layui-form-label">年级:</label>-->
                        <!--<div class="layui-input-inline">-->
                            <!--<input type="text" name="nianji"  autocomplete="off" class="layui-input">-->
                        <!--</div>-->
                    <!--</div>-->
                    <div class="layui-inline">
                        <label class="layui-form-label">学期：</label>
                        <div class="layui-input-block">
                            <select name="term" id="term">
                                <option value=""></option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            	<option value="6">6</option>
                            	<option value="7">7</option>
                            	<option value="8">8</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业：</label>
                        <div class="layui-input-block">
                            <select name="major" id="major" >
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-filter="dosearch" id="dosearch">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <div style="display: none" id="userToolBar" lay-filter="userToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
 			
 			<!--动态表格开始-->
        	<table id="termplan_table" lay-filter="termplan_table"> </table>
        	<!--动态表格结束-->
 			
 			<!-- 添加和修改的弹出层开始 -->
        	<div style="display: none;padding: 20px" id="addOrUpdateDiv">
            <form class="layui-form " action="" lay-filter="addTermpPlanInfo" id="addTermpPlanInfo">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpCourseNum"  lay-verify="required" autocomplete="off"  class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">专业:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpMajorName" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpMajorNum" lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学年:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpYear" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpTerm" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">所属年级:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpGrade" autocomplete="off" class="layui-input" >
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
    </div>
</div>

<!---<div  align="center">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">提交</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">重置</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">排课</button>
</div>--->

<script src="/SelectCourse/layui/layui.js"></script>
<script>

    layui.use(['element','form', 'layer', 'table', 'laypage'],function(){
        var $ = layui.jquery;
    	var element = layui.element;
        var form = layui.form;
        var layer = layui.layer;
        var table = layui.table;//表格
        var laypage = layui.laypage;     
        
        //渲染表格
		var tableIns = table.render({
            id:'termplan_id',
            elem: '#termplan_table'
            , url: '${pageContext.request.contextPath}/TermPlanServlet' //请求路径
            ,defaultToolbar:[]
            ,page:true
            , toolbar: '#userToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [ // 表头
                [
                    { type: 'checkbox', width: 120, sort: false, fixe: 'dleft'}
                    , {field: 'ID', title: '序号',sort:true}
                    , {field: 'tpTerm', title: '学期'}
                    , {field: 'tpYear', title: '学年'}
                    , {field: 'tpMajorName', title: '专业'}
                    , {field: 'tpMajorNum', title: '专业号'}
                    , {field: 'tpCourseNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'tpGrade', title: '所属年级'}
                ]
            ]
        });	


        //动态从数据库获取下拉列表
        $.ajax({
            url: '${pageContext.request.contextPath}/TPDropDownServlet',//请求路径
            //dataType: 'json',
            data:{'state': 0},	//查询状态为正常的所有机构类型
            type: 'POST',
            success: function (data) {
                //$不能遍历json字符串，可以遍历json数组，（可能需要将后台传来的json字符串转化为json对象）
                // 例如 这些后台返回的json对象（如果是字符串需要转化）
                // var data.data=[{id:'0',name:18},{id:'1',name:20},{id:'2',name:22}];
                //
                var data = JSON.parse(data);
                $.each(data, function (index, item) {
                    $('#major').append(new Option(item.name, item.majornum));// 下拉菜单添加元素，new Option(text,value);
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
        	var term = $('#term');
            var majorNum = $('#major'); // 专业号
            console.log(term.val());
            console.log(majorNum.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('termplan_id', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	term:term.val(),
                	majorNum:majorNum.val()
                }
            });
        }


        //监听头部
        table.on('toolbar(termplan_table)',function (obj) {

            var checkStatus = table.checkStatus('termplan_table'); //获取选中的行

            data = checkStatus.data;
            console.log(data.length);
            console.log(data);
            switch (obj.event) {
                case 'add':
                    //监听复选框选择
                    openAddCourse();
                    break;

                case 'batchDelete':
                    batchDelet(data);
                    break;

                case 'getSelect':
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
                title:'添加学期计划信息',
                content:$("#addOrUpdateDiv"),
                area:['800px','400px'],
                success:function(){
                    //清空表单数据
                    $("#addTermpPlanInfo")[0].reset();
                    url="${pageContext.request.contextPath }/AddTermPlanServlet"; //请求路径
                }
            });
        }

       	// 打开修改页面
        function openUpdateCourse(data){
            mainIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#addOrUpdateDiv"),
                area:['800px','400px'],
                success:function(index){
                	form.val("addTermpPlanInfo",data);
                	url = "${pageContext.request.contextPath }/UpdateTermServlet";
                	// var form = document.getElementById("addCourseInfo");
                	//form.action = "${pageContext.request.contextPath }/UpdateCorInfoServlet";
                }
            });
        }


        //1.保存（添加功能、修改功能）
        //2.form.on是表单监听，submit表示监听监听提交按钮，doSubmit是提交按钮中的button中lay-filter值
        form.on("submit(doSubmit)",function(obj){
			// 拿到表单数据
        	var params=obj.field;
			var jsonObj = {
				"tpTerm":params.tpTerm,
				"tpYear" : params.tpYear,
				"tpMajorName": params.tpMajorName,
				"tpCourseNum" : params.tpCourseNum,
				"courName" : params.courName,
				"tpGrade" : params.tpGrade,
				"tpMajorNum" : params.tpMajorNum
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

        //批量删除
        // data是一个数组
        function batchDelet(data) {
            termplanArr = []; //存储每一行数据的id
            if (data.length > 0) {
                for (var i in data) {
                	termplanArr.push(data[i]);
                }
                var jsonObj = {"termplanArr":termplanArr};
                var requestObj = JSON.stringify(jsonObj);
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('${pageContext.request.contextPath }/DeleteTermPlanServlet',requestObj,function (data) { //url为请求路径
                     	var jsonobj = JSON.parse(data);
                    	if(jsonObj.msg == "true"){
                    		//layer.msg("删除成功!",{icon:1});
                    		alert("删除成功");
                    	}
                    	if(jsonObj.msg == "false"){
                    		//layer.msg("删除失败!",{icon:1});
                    		alert("删除失败");
                    	}
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择需要删除的用户");
            }
        }

    });
</script>

</body>
</html>