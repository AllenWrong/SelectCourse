<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>成绩管理</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>成绩管理</legend>
</fieldset>
<!--下面利用了一个选项卡（录入/查看成绩）-->
<div class="layui-tab layui-tab-card">

    <!--每一个选项下对应的内容-->
    <div class="layui-tab-content">
        <!--"录入成绩"对应的内容-->
        <div class="layui-tab-item layui-show">
            <!--多条件查询的form表单的开始-->
            <form action="" class="layui-form" method="post" id="search">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="couNum" id="couNum" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">学年:</label>
                        <div class="layui-input-inline">
                           <select name="year" id="year">
                            <option value=""></option>
                            <option value="2017-2018">2017-2018</option>
                            <option value="2018-2019">2018-2019</option>
                          </select>
                        </div>
                    </div>

	               <div class="layui-inline">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-inline">
                          <select name="couTerm" id="couTerm">
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
            <div  style="display: none" id="grade_entering_ToolBar" lay-filter="grade_entering_ToolBar">
                <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改成绩</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
            </div>
            <!--表格上方按钮结束-->

            <!--动态表格开始-->
            <table id="grade_entering_table" lay-filter="grade_entering_table"> </table>
            <!--动态表格结束-->


            <!-- （录入成绩）修改的弹出层开始 -->
            <div style="display: none;padding: 20px" id="updateDiv">
                <form class="layui-form " action="" lay-filter="updateGrade" id="updateGrade">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生学号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentNum" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学生姓名:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentName" autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="courName"  lay-verify="required" autocomplete="off"
                                       class="layui-input" readonly="readonly">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程号:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="couNum" autocomplete="off" class="layui-input" >
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
            <!-- （录入成绩）修改的弹出层结束 -->
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
            id:'grade_entering'
            ,elem: '#grade_entering_table'
            , url: '/SelectCourse/SearchBySelCouServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#grade_entering_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', fixe: 'left'}
                    , {field: 'tpGrade', title: '年级',align:'center'}  //隐藏字段
                    , {field: 'tpTerm', title: '学期',align:'center'}
                    , {field: 'tpYear', title: '学年',align:'center'}
                    , {field: 'couNum', title: '课程号',align:'center'}
                    , {field: 'courName', title: '课程名',align:'center'}
                    , {field: 'courType', title: '类型',align:'center'}
                    , {field: 'courRedit', title: '学分',align:'center'}
                    , {field: 'studentNum', title: '学生学号',align:'center'}
                    , {field: 'studentName', title: '学生姓名',align:'center'}
                    , {field: 'courseGrades', title: '成绩',align:'center'}
                ]
            ]

        });

        //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var couNum = $('#couNum');
            var year = $('#year');
            var couTerm = $('#couTerm');
            console.log(couTerm.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('grade_entering', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	teaNum:"${sessionScope.user.teaNum}",//逗号隔开
                	couNum:couNum.val(),
                	couTerm:couTerm.val(),
                	year:year.val()
                }
            });
            //$('#search')[0].reset();
        }

        //监听头部
        table.on('toolbar(grade_entering_table)',function (obj) {
            var checkStatus = table.checkStatus('grade_entering'); //获取选中的行 table.render里的id对应的值
            data = checkStatus.data;
            //console.log(data.length);
            console.log(data);
            switch (obj.event) {
                case 'delete':
                    batchDelet(data);
                    break;
                case 'edit':
                    if (data.length<1){
                        layer.msg("请选择一个需要录入成绩的课程");
                    }
                    else if(data.length>1){
                        layer.msg("只能选择一个需要录入成绩的课程");
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
                area:['800px','400px'],
                success:function(index){
                    form.val("updateGrade",data);
                    url="/SelectCourse/AddGradeServlet"; //修改选课的请求路径，与提交修改表单的
                }
            });
        }

        //提交修改表单
        form.on("submit(updateSubmit)",function(obj){
            var params = obj.field;
            var jsonObj = {
                'couNum': params.couNum,
                'studentNum': params.studentNum,
                'courseGrades': params.courseGrades
            };

            var dataStr = JSON.stringify(jsonObj);
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url,dataStr,function(obj){ //提交数据，function中的obj是后台返回的数据
                var data = JSON.parse(obj);
            	if(data.msg == "true"){
              		alert("操作成功");
                }else{
                	alert("操作失败");
                }
                //关闭弹出层
                layer.close(updateIndex);
                //刷新数据表格
                tableIns.reload(); //===================修改====================================
            })
        });
        //取消修改表单
        form.on("submit(cancelSubmit)",function(obj){
            layer.close(updateIndex);
        });
    });
</script>
</body>
</html>