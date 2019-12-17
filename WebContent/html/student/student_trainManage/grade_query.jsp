<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生学习成绩查询</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生学习成绩查询</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <!--成绩管理的相关内容-->
        <div class="layui-tab-item layui-show">
            <!--多条件查询的form表单的开始-->
            <form action="" class="layui-form" method="post" id="search">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学年：</label>
                        <div class="layui-input-block">
                            <select name="tpYear" id="tpYear">
                                <option value=""></option>
                                <option value="2017-2018">2017-2018</option>
                                <option value="2018-2019">2018-2019</option>
                                <option value="2019-2020">2019-2020</option>
                                <option value="2020-2021">2020-2021</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">学期：</label>
                        <div class="layui-input-block">
                            <select name="tpTerm" id="tpTerm" >
                                <option value=""></option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-submit lay-filter="dosearch" id="dosearch">查询</button>
                        </div>
                    </div>

                </div>
            </form>
            <!--多条件查询的form表单的结束-->

            <!--表格上方按钮开始-->
            <div  style="display: none" id="grade_query_ToolBar" lay-filter="grade_query_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="grade_query_table" lay-filter="grade_query_table"> </table>
            <!--动态表格结束-->
        </div>
    </div>
</div>

<script src="../../../layui/layui.js"></script>
<script>

    layui.use(['element','form','table', 'layer', 'laypage'],function(){
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var  layer = layui.layer;//弹层
        var table = layui.table;//表格
        var  laypage = layui.laypage;

        //渲染表格
        var tableIns = table.render({
            id:'grade_query'
            ,elem: '#grade_query_table'
            , url: '/SelectCourse/StuViewGradeServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#grade_query_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '课程类型',align:'center'}
                    , {field: 'courRedit', title: '成绩',align:'center'}
                    , {field: 'studentNum', title: '学分',align:'center'}
                    , {field: 'sumGrade', title: '总分',align:'center'}
                    //--加其他的项，别忘了修改下面提交那个js-------------------
                ]
            ]
        });
        //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var tpYear = $('#tpYear');//学年
            var tpTerm = $('#tpTerm');//学期

            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('grade_query', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	stuNum:"${sessionScope.user.stuNum}",
                    year:tpYear.val(),//逗号隔开
                    term:tpTerm.val(),
                }
            });
        }

        //监听头部
        table.on('toolbar(grade_query_table)',function (obj) {
            var checkStatus = table.checkStatus('grade_entering'); //获取选中的行 table.render里的id对应的值
            data = checkStatus.data;
            //console.log(data.length);
            console.log(data);
            switch (obj.event) {
                case 'reloadTable':
                    tableIns.reload();
                    break;
            }
        });
    });
</script>
</body>
</html>