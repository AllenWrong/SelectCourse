<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>成绩管理</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>成绩管理</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
        <!--成绩管理的相关内容-->
        <div class="layui-tab-item layui-show">
            <!--模糊查询开始-->
            <form action="" class="layui-form" method="post" >
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName"  id="courName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">教师姓名：</label>
                        <!--
                        <div class="layui-input-inline">
                            <input type="text" name="coursename"  autocomplete="off" class="layui-input">
                        </div>
                        -->
                        <div class="layui-input-inline">
                            <input type="text" name="teacherName"  id="teacherName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名：</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentName"  id="studentName" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" lay-filter="dosearch" id="dosearch">查询</button>
                        </div>
                    </div>
                </div>
            </form>
            <!--模糊查询结束-->

            <!--表格上方按钮开始-->
            <div  style="display: none" id="grade_ToolBar" lay-filter="grade_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="grade_table" lay-filter="grade_table"> </table>
            <!--动态表格结束-->


            <!-- 修改的弹出层开始 -->
            <div style="display: none;padding: 20px" id="updateDiv">
                <form class="layui-form " action="" lay-filter="updateGrade" id="updateGrade">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">课程号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courNum"  lay-verify="required" autocomplete="off"
                                       class="layui-input" readonly="readonly"> <!--readonly 属性设置只读-->
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courName" autocomplete="off"
                                       class="layui-input" readonly="readonly"> <!--disabled 属性设置禁用，建议用readonly-->
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">类型:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courType"  lay-verify="required" autocomplete="off"
                                       class="layui-input" readonly="readonly">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">教师工号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="teacherNum" lay-verify="required" autocomplete="off"
                                       class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">教师姓名:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="teacherName" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">学号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentNum" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生姓名:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentName" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学分:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courRedit" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">成绩:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courseGrades" autocomplete="off" class="layui-input" >
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn layui-btn-normal " lay-filter="updateSubmit" lay-submit="">提交</button>
                            <button type="button" class="layui-btn layui-btn-warm "  lay-filter="cancelSubmit" lay-submit="">取消</button>
                        </div>
                    </div>
                </form>

            </div>
            <!-- 修改的弹出层结束 -->
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
            id:'grade'
            ,elem: '#grade_table'
            , url: '../../../json/grade.json' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#grade_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox',sfixe: 'left'}
                    , {field: 'courNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'courType', title: '类型'}
                    , {field: 'teacherNum', title: '教师工号'}
                    , {field: 'teacherName', title: '教师姓名'}
                    , {field: 'studentNum', title: '学号'}
                    , {field: 'studentName', title: '学生姓名'}
                    , {field: 'courRedit', title: '学分'}
                    , {field: 'courseGrades', title: '成绩'}

                ]
            ]

        });

        //模糊查询（后台要自己写）
        $("#dosearch").click(function () {

            fuzzy_search();
        });

        //模糊查询函数
        function fuzzy_search(){
            var courName = $('#courName');//课程名
            var teacherName = $('#teacherName');//教师姓名
            var studentName= $('#studentName');//学生姓名
            //layer.confirm(coursename.val());
            console.log(coursename.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('grade', {

                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,
                where: {
                    courName:courName.val(),//逗号隔开
                    teacherName:teacherName.val(),
                    studentName:studentName.val()
                }
            });
        }

        //监听头部
        table.on('toolbar(grade_table)',function (obj) {

            var checkStatus = table.checkStatus('grade'); //获取选中的行 table.render里的id对应的值

            data = checkStatus.data;
            //console.log(data.length);
            //console.log(data);
            switch (obj.event) {

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
        //打开修改页面
        function openUpdateCourse(data){
            updateIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#updateDiv"),//修改------------------------------
                area:['800px','450px'],
                success:function(index){
                    form.val("updateGrade",data);
                    url="user/updateUser.action"; //修改选课的请求路径，与提交修改表单的
                }
            });
        }

        //提交修改表单
        form.on("submit(updateSubmit)",function(obj){

            //序列化表单数据
            //var params=$("#updateCourse_arranging").serialize();

            var params = obj.field;
            var jsonObj = {

                'courNum': params.courNum,
                'courName': params.courName,
                'courType': params.courType,
                'teacherNum': params.teacherNum,
                'teacherName': params.teacherName,
                'studentNum': params.studentNum,
                'studentName': params.studentName,
                'courRedit': params.courRedit,
                'courseGrades': params.courseGrades

            };

            var dataStr = JSON.stringify(jsonObj);
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url,dataStr,function(obj){ //提交数据，function中的obj是后台返回的数据
                layer.msg(obj);
                //关闭弹出层
                layer.close(updateIndex1)
                //刷新数据表格
                tableIns.reload(); //===================修改====================================
            })
        });
        //取消修改表单
        form.on("submit(cancelSubmit)",function(obj){

            layer.close(updateIndex);

        });

        //批量删除
        function batchDelet(data) {
            console.log(data);
            newsId = []; //存储每一行数据的id
            if (data.length > 0) {
                for (var i in data) {
                    newsId.push(data[i].id);//id为table.render中field域中对应的值（应为表格主键，可以唯一标识一行数据）
                }
                console.log(newsId);
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('url',{'newsId':newsId},function (data) { //url为请求路径
                        tableIns.reload();
                        layer.close(index);
                    });

                    layer.msg("删除成功!",{icon:1});

                })
            } else {
                layer.msg("请选择需要删除的用户");
            }
        }






    });
</script>

</body>
</html>