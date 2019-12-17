<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>网上选课</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生选课</legend>
</fieldset>

<div class="layui-tab layui-tab-card">

    <ul class="layui-tab-title">
        <li>选课</li>
        <li>查看选课</li>
    </ul>

    <div class="layui-tab-content">

        <div class="layui-tab-item layui-show">
            <!--多条件查询的form表单开始-->
            <form action="" class="layui-form" method="post">
                <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学年：</label>
                            <div class="layui-input-block">
                                <select name="tpYear" id="tpYear" lay-search="">
                                    <option value=""></option>
                                    <option value="2017-2018">2017-2018</option>
                                    <option value="2018-2019">2018-2019</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学期：</label>
                            <div class="layui-input-block">
                                <select name="tpTerm" id="tpTerm" lay-search="" >
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
            <!--多条件查询的form表单结束-->

            <!--表格上方按钮开始-->
            <div  style="display: none" id="onlineSelection_course_ToolBar" lay-filter="course_selecting_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="determineCourseSelect">确定选课</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="onlineSelection_course_table" lay-filter="onlineSelection_course_table"> </table>
            <!--动态表格结束-->
        </div>

        <div class="layui-tab-item">
            <!--多条件查询的form表单开始-->
            <form action="" class="layui-form" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学年:</label>
                        <div class="layui-input-inline">
                              <select name="tpYear1" id="tpYear1" lay-search="">
                                    <option value=""></option>
                                    <option value="2017-2018">2017-2018</option>
                                    <option value="2018-2019">2018-2019</option>
                              </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-inline">
                               <select name="couTerm1" id="couTerm1" lay-search="" >
                                    <option value=""></option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-submit lay-filter="dosearch1" id="dosearch1">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <!--多条件查询的form表单结束-->

            <!--表格上方按钮开始-->
            <div  style="display: none" id="onlineSelection_course_ToolBar1" lay-filter="course_selecting_ToolBar1">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="cancelCourseSelect">删除选课</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="onlineSelection_course_table1" lay-filter="onlineSelection_course_table1"> </table>
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

//------------------------------------------------学生选课---------------------------------------------------------------
        //渲染表格
        var tableIns = table.render({
            id:'onlineSelection_course'
            ,elem: '#onlineSelection_course_table'
            , url: '/SelectCourse/SearchCanSelCouSerlvet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#onlineSelection_course_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    , {field: 'tpYear', title: '学年',hide:true} //隐藏字段
                    , {field: 'tpTerm', title: '学期',hide:true}  //隐藏字段
                    , {field: 'tpGrade', title: '年级',hide:true}  //隐藏字段
                    , {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '类型',align:'center'}
                    , {field: 'courRedit', title: '学分',align:'center'}
                    , {field: 'courXs', title: '学时',align:'center'}
                    , {field: 'teachName', title: '上课老师',align:'center'}
                    , {field: 'teachNum', title: '教师编号',align:'center'}
                    , {field: 'course_size', title: '课程容量',align:'center'}
                ]
            ]
        });

        //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var tpYear = $('#tpYear');
            var couTerm = $('#tpTerm');
            console.log(tpYear.val());
            table.reload('onlineSelection_course', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	tpYear:tpYear.val(),//逗号隔开
                	couTerm:couTerm.val(),
                	grade:"${sessionScope.user.stuGrade}"
                }
            });
        }

        //监听头部
        table.on('toolbar(onlineSelection_course_table)',function (obj) {    //table的lay-filter: course_selecting_table
            var checkStatus = table.checkStatus('onlineSelection_course'); //获取选中的行 //course_selecting为table.render的id
            data = checkStatus.data;
            switch (obj.event) {
                case 'determineCourseSelect':
                    determineCourseSelect(data);
                    break;
                case 'reloadTable':
                    tableIns.reload();
                    break;
            }
        });

        //确认选课功能
        function determineCourseSelect(data) {
            console.log(data);
            console.log(data);
			grade = [];
            year = [];
			term = [];
			couNum = [];
			teaNum = [];
            if (data.length > 0) {
                for (var i in data) {
                    grade.push("${sessionScope.user.stuGrade}");
                    year.push($('#tpYear').val());
                    term.push($('#tpTerm').val());
                    couNum.push(data[i].tpCourseNum);
                    teaNum.push(data[i].teachNum);
                }
                layer.confirm('确定选择指定的课程？', {icon: 3, title: '提示信息'}, function (index) {
                    var jsonObj = {
                    		"grade":grade,"year":year,"term":term,"couNum":couNum,"teaNum":teaNum
                    }
                    var data = JSON.stringify(jsonObj);
                    $.post('/SelectCourse/SelectCourseServlet',data,function (data) {
                        var data = JSON.parse(data);
                        if(data.msg == "true"){
                            layer.msg("选课成功!",{icon:1});
                        }else{
                            layer.msg("选课失败!",{icon:5});
                        }
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择课程");
            }
        }

        //------------------------------------------------学生查看选课--------------------------------------------------------
        //渲染表格
        var tableIns1 = table.render({
            id:'onlineSelection_course1'
            ,elem: '#onlineSelection_course_table1'
            , url: '/SelectCourse/SearchSelectedCouServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#onlineSelection_course_ToolBar1'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    , {field: 'tpYear', title: '学年',hide:true} //隐藏字段
                    , {field: 'tpTerm', title: '学期',hide:true}  //隐藏字段
                    , {field: 'tpGrade', title: '年级',hide:true}  //隐藏字段
                    , {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '类型',align:'center'}
                    , {field: 'courRedit', title: '学分',align:'center'}
                    , {field: 'courXs', title: '学时',align:'center'}
                    , {field: 'teachNum', title: '老师号',align:'center'}
                    , {field: 'teachName', title: '上课老师',align:'center'}
                    //, {field: 'course_time', title: '上课时间',align:'center'}
                    //, {field: 'course_place', title: '上课地点',align:'center'}
                    //--加其他的项，别忘了修改下面提交那个js-------------------
                ]
            ]
        });

        //多条件查询（后台要自己写）
        $("#dosearch1").click(function () {
            fuzzy_search1();
        });

        //多条件查询函数
        function fuzzy_search1(){
            var tpYear1 = $('#tpYear1');
            var couTerm1 = $('#couTerm1');
            table.reload('onlineSelection_course1', {
                page: {
                    curr: 1
                },
                where: {
                	tpYear1:tpYear1.val(),
                	couTerm1:couTerm1.val(),
                	stuNum:"${sessionScope.user.stuNum}"
                }
            });
        }

        //监听头部
        table.on('toolbar(onlineSelection_course_table1)',function (obj) {    //table的lay-filter: course_selecting_table
            var checkStatus = table.checkStatus('onlineSelection_course1'); //获取选中的行 //course_selecting为table.render的id
            data = checkStatus.data;
            //console.log(data.length);
            //console.log(data);
            switch (obj.event) {
                case 'cancelCourseSelect':
                    cancelCourseSelect(data);
                    break;
                case 'reloadTable':
                    tableIns1.reload();
                    break;
            }
        });

        //删除选课功能
        function cancelCourseSelect(data) {
            console.log(data);
			couNum = [];
			teaNum = [];
            if (data.length > 0) {
                for (var i in data) {
                    couNum.push(data[i].tpCourseNum);
                    teaNum.push(data[i].teachNum);
                }
                layer.confirm('确定退选吗？', {icon: 3, title: '提示信息'}, function (index) {
                    var jsonObj = {
                    	"couNum":couNum,"teaNum":teaNum
                    }
                    var data = JSON.stringify(jsonObj);
                    $.post('/SelectCourse/DeleteSeleCouServlet',data,function (data) {
                        var data = JSON.parse(data);
                        if(data.msg == "true"){
                            layer.msg("退选成功!",{icon:1});
                        }else{
                            layer.msg("退选失败!",{icon:5});
                        }
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择课程");
            }
        }
    });
</script>
</body>
</html>