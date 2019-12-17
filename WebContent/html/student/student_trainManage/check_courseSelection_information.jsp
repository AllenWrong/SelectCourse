<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生选课信息查询</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生选课信息查询</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <!--多条件查询的form表单开始-->
            <form action="" class="layui-form" method="post" id="search">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学期：</label>
                        <div class="layui-input-block">
                            <select name="tpYear" id="tpYear">
                                <option value=""></option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学年：</label>
                        <div class="layui-input-block">
                            <select name="tpTerm" id="tpTerm">
                                <option value=""></option>
                                <option value="1">2017-2018</option>
                                <option value="2">2018-2019</option>
                                <option value="3">2019-2020</option>
                                <option value="4">2020-2021</option>
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
            <div  style="display: none" id="check_courseSelection_information_ToolBar" lay-filter="check_courseSelection_information_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="check_courseSelection_information_table" lay-filter="check_courseSelection_information_table"> </table>
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
            id:'check_courseSelection_information'
            ,elem: '#check_courseSelection_information_table'

            , url: '../../../json/onlineSelection.json' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#check_courseSelection_information_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    {field: 'tpYear', title: '学年',align:'center'}
                    , {field: 'tpTerm', title: '学期',align:'center'}
                    , {field: 'tpGrade', title: '年级',hide:true}  //隐藏字段
                    , {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '类型',align:'center'}
                    , {field: 'courRedit', title: '学分',align:'center'}
                    , {field: 'teachName', title: '上课老师',align:'center'}
                    , {field: 'week', title: '星期',align:'center'}
                    , {field: 'course_time', title: '上课时间',align:'center'}
                    , {field: 'course_place', title: '上课地点',align:'center'}
                    , {field: 'course_size', title: '课程容量',align:'center',hide:true}//隐藏字段
                    , {field: 'selectedNum', title: '已选人数',align:'center',hide:true}//隐藏字段

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
            //layer.confirm(coursename.val());
            console.log(tpTerm.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('check_courseSelection_information', {

                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,
                where: {
                    tpYear:tpYear.val(),//逗号隔开
                    tpTerm:tpTerm.val(),
                }
            });
            $('#search')[0].reset();
        }

        //监听头部
        table.on('toolbar(check_courseSelection_information_table)',function (obj) {    //table的lay-filter: course_selecting_table
            var checkStatus = table.checkStatus('check_courseSelection_information'); //获取选中的行 //course_selecting为table.render的id
            data = checkStatus.data;
            //console.log(data.length);
            //console.log(data);
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