<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>开课管理</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>开课管理</legend>
</fieldset>

<div class="layui-tab layui-tab-card">
    <!--选项名称（选课/查看选课）-->
    <ul class="layui-tab-title">
        <li>开课</li>
        <li>查看开课</li>
    </ul>
    <!--每一个选项下对应的内容-->
    <div class="layui-tab-content">
        <!--"开课"对应的内容-->
        <div class="layui-tab-item layui-show">

            <!--多条件查询form表单的开始
            <form action="" class="layui-form" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">教师姓名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="teacherName"  autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名称:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName"  autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="dosearch" id="dosearch">查询</button>
                        </div>
                    </div>
                </div>
            </form>-->
            <!--多条件查询form表单的结束-->

            <!--表格上方按钮开始-->
            <div  id="course_opening_ToolBar" style="display: none" >
                <button type="button" class="layui-btn layui-btn-sm" lay-event="determineCourseOpening">确定开课</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="course_opening_table" lay-filter="course_opening_table"> </table>
            <!--动态表格结束-->

        </div>


        <!--"查看开课"对应的内容-->
        <div class="layui-tab-item">

            <!--多条件查询form表单的开始-->
            <form action="" class="layui-form" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">教师号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="teacherNum1" id="teacherNum1" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courNum1" id="courNum1" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-submit lay-filter="dosearch1" id="dosearch1">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <!--多条件查询form表单的结束-->

            <!--表格上方按钮开始-->
            <div  id="course_opening_ToolBar1" style="display: none" >
                <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelet">删除</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->


            <!--动态表格开始-->
            <table id="course_opening_table1" lay-filter="course_opening_table1"> </table>
            <!--动态表格结束-->
        </div>
    </div>
</div>

<script src="/SelectCourse/layui/layui.js"></script>

<script>
    layui.use(['element','form','table', 'layer', 'laypage'],function(){
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var  layer = layui.layer;//弹层
        var table = layui.table;//表格
        var  laypage = layui.laypage;

        //------------------------------------------------开课-----------------------------------------------------------
        //渲染表格
        var tableIns = table.render({
            id:'course_opening'
            ,elem: '#course_opening_table'
            , url: '/SelectCourse/ViewAllAskServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#course_opening_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox',sfixe: 'left'}
                    , {field: 'courNum', title: '课程号',align:"center"}
                    , {field: 'courName', title: '课程名',align:"center"}
                    , {field: 'teacherNum', title: '教师工号',align:"center"}
                    , {field: 'teacherName', title: '教师姓名',align:"center"}
                    , {field: 'state', title: '处理状态',align:"center"}
                ]
            ]
        });

        /*  //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var teacherName = $('#teacherName');//教师姓名
            var courName = $('#courName');//课程名称
            //layer.confirm(coursename.val());
            console.log(coursename.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('course_opening', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    teacherName:teacherName.val(),
                    courName:courName.val()
                }
            });
        }*/

        //监听头部
        table.on('toolbar(course_opening_table)',function (obj) {
            var checkStatus = table.checkStatus('course_opening'); //获取选中的行 course_opening为table.render的id
            data = checkStatus.data;
            switch (obj.event) {
                case 'determineCourseOpening':
                    determineCourseOpening(data);
                    break;
                case 'reloadTable':
                    tableIns.reload();
                    break;
            }
        });


        //确定开课
        function determineCourseOpening(data) {
            console.log(data);
            couNum = [];
            teaNum = [];
            if (data.length > 0) {
                for (var i in data) {
                    couNum.push(data[i].courNum);//此id为table,render表格中的field值，应是表格主键，（无实际意义，需自己指定)
                    teaNum.push(data[i].teacherNum);
                }
                var jsonObj = {
                	"couNum":couNum,"teaNum":teaNum
                }
                var datastr = JSON.stringify(jsonObj);
                layer.confirm('确定开设选择的课程？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('/SelectCourse/AgreeAskServlet',datastr,function (data) {   //url为请求路径,需要自己指定
                        var data = JSON.parse(data);
                    	if(data.msg = "true"){
                            layer.msg("确定开课成功!",{icon:1});
                    	}else{
                            layer.msg("确定开课失败!",{icon:5});
                    	}
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择需要开设的课程");
            }
        }

        //----------------------------------------------查看开课-------------------------------------------------------
        //渲染表格
        var tableIns1 = table.render({
            id:'course_opening1'
            ,elem: '#course_opening_table1'
            ,url: '/SelectCourse/ViewAllTeaCouServlet'
            ,defaultToolbar:[]
            , toolbar: '#course_opening_ToolBar1'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox',sfixe: 'left'}
                    , {field: 'courNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'teacherNum', title: '教师工号'}
                    , {field: 'teacherName', title: '教师姓名'}
                ]
            ]
        });

        //多条件查询（后台要自己写）
        $("#dosearch1").click(function () {
            fuzzy_search1();
        });

        //多条件查询函数
        function fuzzy_search1(){
            var teacherName1 = $('#teacherNum1');//教师姓名
            var courName1 = $('#courNum1');//课程名称
            console.log(teacherName1.val());
            console.log(courName1);
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('course_opening1', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    teacherName1:teacherName1.val(),//逗号隔开
                    courName1:courName1.val()
                }
            });
        }

        //监听头部
        table.on('toolbar(course_opening_table1)',function (obj) {
            var checkStatus = table.checkStatus('course_opening1'); //获取选中的行 course_opening1为table.render的id
            data = checkStatus.data;
            //console.log(data.length);
            //console.log(data);
            switch (obj.event) {
                case 'batchDelet':
                    batchDelet(data);
                    break;
                case 'reloadTable':
                    tableIns1.reload();
                    break;
            }
        });

        //批量删除
        function batchDelet(data) {
            console.log(data);
            couNum = [];
            teaNum = [];
            if (data.length > 0) {
                for (var i in data) {
                    couNum.push(data[i].courNum);//此id为table,render表格中的field值，应是表格主键，（无实际意义，需自己指定)
                    teaNum.push(data[i].teacherNum);
                }
                layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('url',{'couNum':couNum,'teaNum':teaNum},function (data) { //url为请求路径
                        tableIns1.reload();
                        layer.close(index);
                    });
                    layer.msg("删除成功!",{icon:1});
                })
            } else {
                layer.msg("请选择需要删除的数据");
            }
        }
    });
</script>
</body>
</html>