<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>培养计划</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>培养计划</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <!--多条件查询的form表单开始-->
        <form action="" class="layui-form" method="post" id="search">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">专业：</label>
                    <div class="layui-input-block">
                        <select name="majorName" id="majorName">
                            <option value=""></option>
                            <option value="计算机科学与技术">计算机科学与技术</option>
                            <option value="通信工程">通信工程</option>
                            <option value="物联网工程">物联网工程</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年级：</label>
                    <div class="layui-input-block">
                        <select name="grade" id="grade">
                            <option value=""></option>
                            <option value="2015">2015</option>
                            <option value="2017">2017</option>
                            <option value="2019">2019</option>
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
        <!--多条件查询的form表单结束-->

        <!--表格上方按钮开始-->
        <div  style="display: none" id="train_plan_ToolBar" lay-filter="train_plan_ToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
        </div>
        <!--表格上方按钮结束-->

        <!--动态表格开始-->
        <table id="train_plan_table" lay-filter="train_plan_table"> </table>
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
            id:'train_plan'
            ,elem: '#train_plan_table'

            , url: '/SelectCourse/SearchTrainPlanServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#train_plan_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    //{ type: 'checkbox', fixe: 'left'}
					, {field: 'majorName', title: '专业名',align:'center'}
                    , {field: 'tpGrade', title: '所属年级',align:'center'}
                    , {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '类型',align:'center'}
                    , {field: 'courRedit', title: '学分',align:'center'}
                    , {field: 'courXs', title: '总学时',align:'center'}                    
                    , {field: 'tpTerm', title: '建议开设学期',align:'center'}
                ]
            ]
        });

        //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var majorName = $('#majorName');//学年
            var grade = $('#grade');//学期
            table.reload('train_plan', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	majorName:majorName.val(),//逗号隔开
                    grade:grade.val(),
                }
            });
            $('#search')[0].reset();
        }

        //监听头部
        table.on('toolbar(train_plan_table)',function (obj) {    //table的lay-filter: course_selecting_table
            var checkStatus = table.checkStatus('train_plan'); //获取选中的行 //course_selecting为table.render的id
            data = checkStatus.data;
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