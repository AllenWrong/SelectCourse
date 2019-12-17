<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>教师申请开课</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>申请开课</legend>
</fieldset>

<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">
            <!--表格上方按钮开始-->
            <div  style="display: none;text-align: left" id="teacher_applyCourse_ToolBar" lay-filter="teacher_applyCourse_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="add">申请</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">取消申请</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
             <div style="margin-right: 20%;margin-left: 20%" align="">
                 <div class="layui-inline">
                     <table id="teacher_applyCourse_table" lay-filter="teacher_applyCourse_table"> </table>
                 </div>
             </div>
            <!--动态表格结束-->


            <!-- （申请开课）修改的弹出层开始 -->
            <div style="display: none;padding: 20px;margin-top: 50px" id="addDiv" >
                <form class="layui-form " action="" lay-filter="addApplyCourse" id="addApplyCourse">
                    <div class="layui-form-item">
                        <!-- <div class="layui-inline">
                            <label class="layui-form-label">教师工号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="teachNum" id="teachNum" lay-verify="required" autocomplete="off"
                                       class="layui-input" >
                            </div>
                        </div>-->
                        <div class="layui-inline">
                            <label class="layui-form-label">课程号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="tpCourseNum" id="tpCourseNum" autocomplete="off"  lay-verify="required"
                                       class="layui-input" > <!--disabled 属性设置禁用，建议用readonly-->
                            </div>
                        </div>
                    </div>


                    <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn layui-btn-normal " lay-filter="addSubmit" lay-submit="">提交</button>
                            <button type="button" class="layui-btn layui-btn-warm "  lay-filter="cancelSubmit" lay-submit="">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- （申请开课）修改的弹出层结束 -->
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


        //渲染表格
        var tableIns = table.render({
            id:'teacher_applyCourse'
            ,elem: '#teacher_applyCourse_table'
            , url: '/SelectCourse/ViewAskServlet' //请求路径
            ,width:1000
            ,defaultToolbar:[]
            , toolbar: '#teacher_applyCourse_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    , {field: 'teachNum', title: '教师工号',align:'center'}
                    , {field: 'teachName', title: '教师名',align:'center'}
                    , {field: 'tpCourseNum', title: '课程号',align:'center'}
                    , {field: 'tpCourseName', title: '课程名',align:'center'}
                    , {field: 'state', title: '状态',align:'center',text:'未处理'}
                ]
            ]
        });

        //监听头部
        table.on('toolbar(teacher_applyCourse_table)',function (obj) {
            var checkStatus = table.checkStatus('teacher_applyCourse'); //获取选中的行 table.render里的id对应的值
            data = checkStatus.data;
            //console.log(data.length);
            //console.log(data);
            switch (obj.event) {
                case 'add':
                    openAddCourse();
                    break;
                case 'delete':
                    batchDelet(data);
                    break;
                case 'reloadTable':
                    tableIns.reload();
                    break;
            }
        });


        var url;
        //打开添加页面
        function openAddCourse(){
            addIndex=layer.open({
                type:1,
                title:'申请开课信息',
                content:$("#addDiv"),//修改------------------------------
                area:['800px','300px'],
                success:function(index){
                    //清空表单数据
                    $("#addApplyCourse")[0].reset();
                    url="/SelectCourse/AskCouServlet"; //请求路径
                }
            });
        }

        //批量删除
        function batchDelet(data) {
            console.log(data);
			teaNum = [];
			couNum = [];
            if (data.length > 0) {
                for (var i in data) {
                    teaNum.push(data[i].teachNum);
                    couNum.push(data[i].tpCourseNum);
                }
				var jsonObj = {
					"teaNum":teaNum,"couNum":couNum
				}
				var datastr = JSON.stringify(jsonObj);
                layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('/SelectCourse/DeleteAskServlet',datastr,function (data) {
                        var data = JSON.parse(data);
                        if(data.msg == "true"){
                            layer.msg("删除成功!",{icon:1});
                        }else{
                            layer.msg("删除失败!",{icon:5});
                        }
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择需要删除的数据");
            }
        }

        //提交添加表单
        form.on("submit(addSubmit)",function(obj){
            var params = obj.field;
            var jsonObj = {
                'teachNum': "${sessionScope.user.teaNum}",
                'tpCourseNum': params.tpCourseNum
            };
            var dataStr = JSON.stringify(jsonObj);
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url,dataStr,function(obj){ //次url是 openAddCourse()定义的url
                var data = JSON.parse(obj);
                if(data.msg == "true"){
                    layer.msg("添加成功!",{icon:1});
                }else{
                    layer.msg("添加失败!",{icon:5});
                }
                //关闭弹出层
                layer.close(addIndex);
                //刷新数据表格
                tableIns.reload();
            })
        });
        //取消修改表单
        form.on("submit(cancelSubmit)",function(obj){
            layer.close(addIndex);
        });
    });
</script>
</body>
</html>