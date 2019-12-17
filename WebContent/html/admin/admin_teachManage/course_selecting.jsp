<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>选课管理</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>选课管理</legend>
</fieldset>
<!--下面利用了一个选项卡（选课/查看选课/查看学生选课信息）-->
<div class="layui-tab layui-tab-card">
    <!--选项名称（选课/查看选课）-->
    <ul class="layui-tab-title">
        <li>查看学生选课信息</li>
    </ul>
    <!--每一个选项下对应的内容-->
    <div class="layui-tab-content">

        <!--"查看选课学生选课"对应的内容-->
        <div class="layui-tab-item layui-show">
            <!--多条件查询开始   每个元素的name和id根据table.render中的field值修改-->
            <form action="" class="layui-form" method="post" >
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">年级:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpGrade2"  id="tpGrade2" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名称:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName" id="courName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">上课老师:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="teachName"  id="teachName" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentName" id="studentName" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-filter="dosearch2" id="dosearch2">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <!--多条件查询结束-->


            <!--表格上方按钮开始-->
            <div  style="display: none" id="course_selecting_ToolBar2" lay-filter="course_selecting_ToolBar2">
               <!-- <button type="button" class="layui-btn layui-btn-sm" lay-event="cancelCourseSelect">删除选课</button>-->
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="course_selecting_table2" lay-filter="course_selecting_table2"> </table>
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
//----------------------------------------------查看学生选课信息--------------------------------------------------------
        //渲染表格
        var tableIns2 = table.render({
            id:'course_selecting2'
            ,elem: '#course_selecting_table2'
            , url: '/SelectCourse/ViewAllSelCouServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#course_selecting_ToolBar2'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    , {field: 'tpTerm', title: '学期'}
                    , {field: 'tpYear', title: '学年'}
                    , {field: 'tpMajorName', title: '专业名'}
                    , {field: 'tpGrade', title: '年级'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'teachName', title: '上课老师'}
                    , {field: 'stuName', title: '学生名'}
                    //--加其他的项，别忘了修改下面提交那个js-------------------
                ]
            ]

        });

        //多条件查询（后台要自己写）
        $("#dosearch2").click(function () {
            fuzzy_search2();
        });

        //多条件查询函数
        function fuzzy_search2(){
            var tpGrade2 = $('#tpGrade2');//年级
            var courName = $('#courName');//课程名称
            var teachName = $('#teachName');//上课老师
            var studentName = $('#studentName');//学生姓名

            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('course_selecting2', { //course_selecting2是table.render的id值
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,
                where: {
                    tpGrade2:tpGrade2.val(),//逗号隔开
                    courName:courName.val(),
                    teachName:teachName.val(),
                    studentName:studentName.val()
                }
            });
        }

        //监听头部
        table.on('toolbar(course_selecting_table2)',function (obj) {    //table的lay-filter: course_selecting_table
            var checkStatus = table.checkStatus('course_selecting2'); //获取选中的行 //course_selecting为table.render的id
            data = checkStatus.data;
            switch (obj.event) {
                case 'reloadTable':
                    tableIns2.reload();
                    break;
            }
        });

        // //删除选课功能
        // function cancelCourseSelect(data) {
        //     console.log(data);
        //     newsId = []; //存储每一行数据的id
        //     if (data.length > 0) {
        //         for (var i in data) {
        //             newsId.push(data[i].id);//此id为table,render表格中的field值，应是表格主键，（无实际意义，需自己指定）
        //         }
        //
        //         layer.confirm('确定删除指定的课程？', {icon: 3, title: '提示信息'}, function (index) {
        //             console.log(newsId);
        //             $.post('url',{'newsId':newsId},function (data) { //url为请求路径---------------------------
        //                 tableIns.reload();
        //                 layer.close(index);
        //                 layer.msg("删除选课成功!",{icon:1});
        //             });
        //
        //         })
        //     } else {
        //         layer.msg("请选择需要删除的选课");
        //     }
        // }
    });
</script>
</body>
</html>