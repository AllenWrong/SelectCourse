<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生学籍管理</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生学籍管理</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <div class="layui-tab-content">

        <form action="" class="layui-form" method="post" id="search">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">年级：</label>
                    <div class="layui-input-block">
                        <select name="grade" id="grade">
                            <option value=""></option>
                            <option value="1">2016</option>
                            <option value="2">2017</option>
                            <option value="3">2018</option>
                            <option value="4">2019</option>
                        </select>
                    </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">专业：</label>
                <div class="layui-input-block">
                    <select name="major" id="major">
                        <option value=""></option>
                        <option value="mjp11001">计算机科学与技术</option>
                        <option value="mjp11002">通信工程</option>
                        <option value="mjp11003">物联网工程</option>
                    </select>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">学号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="studentNum" id="studentNum" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <div class="layui-input-block">
                    <button class="layui-btn" type="button" lay-submit lay-filter="dosearch" id="dosearch">查询</button>
                </div>
            </div>
        </div>
    </form>


        <!--表格上方按钮开始-->
        <div  style="display: none" id="student_status_ToolBar" lay-filter="student_status_ToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
        </div>
        <!--表格上方按钮结束-->


        <!--动态表格开始-->
        <table id="student_status_table" lay-filter="student_status_table"> </table>
        <!--动态表格结束-->

        <!-- 添加的弹出层开始 -->
        <div style="display: none;padding: 20px;margin-top: 30px" id="addDiv">
            <form class="layui-form " action="" lay-filter="addStudent_status" id="addStudent_status">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentNum"  lay-verify="required" autocomplete="off"
                                   class="layui-input"> <!--readonly 属性设置只读-->
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentName" autocomplete="off"
                                   class="layui-input"> <!--disabled 属性设置禁用，建议用readonly-->
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性别:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="sex"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学生电话:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentTel" lay-verify="required" autocomplete="off"
                                   class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">生日:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="birthday"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">家庭住址:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="address" lay-verify="required" autocomplete="off"
                                   class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="major"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">民族:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="nation" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">政治面貌:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="politicCountenance"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入学时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="schoolTime" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">在籍状态:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="status"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal " lay-filter="addSubmit" id="addSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm" lay-filter="resetSubmit" >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加的弹出层结束 -->

        <!-- 修改的弹出层开始 -->
        <div style="display: none;padding: 20px;margin-top: 30px" id="updateDiv">
            <form class="layui-form " action="" lay-filter="updateStudent_status" id="updateStudent_status">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentNum"  lay-verify="required" autocomplete="off"
                                   class="layui-input" readonly="readonly"> <!--readonly 属性设置只读-->
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学生姓名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentName" autocomplete="off"
                                   class="layui-input"  readonly="readonly"> <!--disabled 属性设置禁用，建议用readonly-->
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性别:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="sex"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学生电话:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="studentTel" lay-verify="required" autocomplete="off"
                                   class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">生日:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="birthday"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">家庭住址:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="address" lay-verify="required" autocomplete="off"
                                   class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="major"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">民族:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="nation" lay-verify="required" autocomplete="off"
                                   class="layui-input" readonly="readonly">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">政治面貌:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="politicCountenance"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入学时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="schoolTime" lay-verify="required" autocomplete="off"
                                   class="layui-input" readonly="readonly">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">在籍状态:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="status"  lay-verify="required" autocomplete="off"
                                   class="layui-input changeDate">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal " lay-filter="updateSubmit" id="updateSubmit" lay-submit="">提交</button>
                        <button type="button" class="layui-btn layui-btn-warm "  lay-filter="cancelUpdateSubmit" id="cancelUpdateSubmit" lay-submit="">取消</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- 修改的弹出层结束 -->
    </div>
</div>
<script src="/SelectCourse/layui/layui.js"></script>
<script>

    layui.use(['element','form','table', 'layer', 'laypage','laydate'],function(){
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var  layer = layui.layer;//弹层
        var table = layui.table;//表格
        var  laypage = layui.laypage;
        var  laydate = layui.laydate;

        //渲染表格
        var tableIns = table.render({
            id:'student_status'
            ,elem: '#student_status_table'
            , url: '/SelectCourse/ShowAllStudentServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#student_status_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox',fixe: 'left'}
                    , {field: 'studentNum', title: '学号',align:'center'}
                    , {field: 'studentName', title: '学生姓名',align:'center'}
                    , {field: 'sex', title: '性别',align:'center'}
                    , {field: 'studentTel', title: '学生电话',align:'center'}
                    , {field: 'birthday', title: '生日',align:'center'}
                    , {field: 'address', title: '家庭住址',align:'center'}
                    , {field: 'major', title: '专业',align:'center'}
                    , {field: 'nation', title: '民族',align:'center'}
                    , {field: 'politicCountenance', title: '政治面貌',align:'center'}
                    , {field: 'schoolTime', title: '入学时间',align:'center'}
                    , {field: 'status', title: '在籍状态 ',align:'center'}

                ]
            ]

        });

        //多条件查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //多条件查询函数
        function fuzzy_search(){
            var grade = $('#grade');//入学时间
            var major = $('#major');
            var studentNum = $('#studentNum');
            console.log(schoolTime.val());
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('student_status', {
                page: {
                    curr: 1 //重新从第 1页开始
                },
                where: {
                	grade:grade.val(),
                	major:major.val(),
                    studentNum:studentNum.val()
                }
            });
            $('#search')[0].reset();
        }

        //监听头部
        table.on('toolbar(student_status_table)',function (obj) {
            var checkStatus = table.checkStatus('student_status'); //获取选中的行 table.render里的id对应的值
            data = checkStatus.data;
            console.log(data);
            switch (obj.event) {
                case 'add':
                    //监听复选框选择
                    openAddCourse();
                    break;
                case 'delete':
                    batchDelet(data);
                    break;
                case 'edit':
                    if (data.length<1){
                        layer.msg("请选择一条需要修改的数据");
                    }
                    else if(data.length>1){
                        layer.msg("只能选择一条需要修改的数据");
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
        var url1;
        //打开添加页面
        function openAddCourse(){
            addIndex=layer.open({
                type:1,
                title:'添加奖惩信息',
                content:$("#addDiv"),//修改------------------------------
                area:['800px','550px'],
                success:function(index){
                    //清空表单数据
                    $("#addStudent_status")[0].reset();
                    url="user/addUser.action"; //请求路径
                }
            });
        }

        //打开修改页面
        function openUpdateCourse(data){
            updateIndex=layer.open({
                type:1,
                title:'修改奖惩信息',
                content:$("#updateDiv"),//修改------------------------------
                area:['800px','550px'],
                success:function(index){
                    form.val("updateStudent_status",data);
                    url1="user/updateUser.action"; //修改选课的请求路径，与提交修改表单的
                }
            });
        }

        //提交添加表单
        form.on("submit(addSubmit)",function(obj){
            var params = obj.field;
            var jsonObj = {
                'studentNum': params.studentNum,
                'studentName': params.studentName,
                'sex': params.sex,
                'studentTel': params.studentTel,
                'birthday': params.birthday,
                'address': params.address,
                'major': params.major,
                'nation': params.nation,
                'politicCountenance': params.politicCountenance,
                'schoolTime': params.schoolTime,
                'status': params.status
            };

            var dataStr = JSON.stringify(jsonObj);
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url,dataStr,function(obj){ //提交数据，function中的obj是后台返回的数据
                layer.msg(obj);
                //关闭弹出层
                layer.close(addIndex)
                //刷新数据表格
                tableIns.reload(); //===================修改====================================
            })
        });

        //提交修改表单
        form.on("submit(updateSubmit)",function(obj){
            var params1 = obj.field;
            var jsonObj1 = {
                'studentNum': params1.studentNum,
                'studentName': params1.studentName,
                'sex': params1.sex,
                'studentTel': params1.studentTel,
                'birthday': params1.birthday,
                'address': params1.address,
                'major': params1.major,
                'nation': params1.nation,
                'politicCountenance': params1.politicCountenance,
                'schoolTime': params1.schoolTime,
                'status': params1.status
            };

            var dataStr1 = JSON.stringify(jsonObj1);
            console.log(jsonObj1);
            //url：请求路径，params：弹出表单中的数据
            $.post(url1,dataStr1,function(obj){ //提交数据，function中的obj是后台返回的数据
                layer.msg(obj);
                //关闭弹出层
                layer.close(updateIndex1)
                //刷新数据表格
                tableIns.reload(); //===================修改====================================
            })
        });

        //取消修改表单
        form.on("submit(cancelUpdateSubmit)",function(obj){
            layer.close(updateIndex);
        });

        //批量删除
        function batchDelet(data) {
            console.log(data);
            stuNum = []; //存储每一行数据的id
            if (data.length > 0) {
                for (var i in data) {
                    stuNum.push(data[i].studentNum);
                }
                console.log(stuNum);
                layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('url',{'newsId':newsId},function (data) { //url为请求路径
                        tableIns.reload();
                        layer.close(index);
                        layer.msg("删除成功!",{icon:1});
                    });
                })
            } else {
                layer.msg("请选择需要删除的数据");
            }
        }
    });
</script>
</body>
</html>