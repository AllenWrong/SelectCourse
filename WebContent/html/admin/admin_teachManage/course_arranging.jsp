<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>排课管理</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
        <style type="text/css">
	.laytable-cell-radio {
		padding: 10px 15px 0 15px;
	} 
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>排课管理</legend>
</fieldset>
<!--下面利用了一个选项卡（排课/查看排课）-->
<div class="layui-tab layui-tab-card">
        <!--选项名称（排课/查看排课）-->
        <ul class="layui-tab-title">
            <li>排课</li>
            <li>查看排课</li>
        </ul>
        <!--每一个选项下对应的内容-->
        <div class="layui-tab-content">
            <!--"排课" 对应的内容-->
            <div class="layui-tab-item layui-show">

                <!--模糊查询开始   每个元素的name和id根据table.render中的field值修改-->
                <form action="" class="layui-form" method="post" >
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">年级:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="tpGrade"  id="tpGrade" autocomplete="off" class="layui-input">
                            </div>
                        </div>
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
                            <!--
                            <div class="layui-input-inline">
                                <input type="text" name="coursename"  autocomplete="off" class="layui-input">
                            </div>
                            -->
                            <!------------------------可以根据表中的内容动态添加-------------------------->
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
                                <button type="button" class="layui-btn" lay-filter="dosearch" id="dosearch">查询</button>
                            </div>
                        </div>
                    </div>
                </form>
                <!--模糊查询结束-->

                <!--对表格进行操作的几个按钮(这里只有 "刷新数据")-->
                <div  style="display: none" id="course_arranging_ToolBar" lay-filter="course_arranging_ToolBar">
                    <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
                </div>

                <!--动态表格开始  table.render中的field根据javabean中的数据更改-->
                <table id="course_arranging_table" lay-filter="course_arranging_table"> </table>
                <!--动态表格结束-->



                <!--"参数设置"-->
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>参数设置</legend>
                </fieldset>
                <!--"参数设置"表单开始-->
                <div  style="padding:10px" id="course_arrangingDiv">
                    <form action="" class="layui-form" method="post" lay-filter="course_arrangingForm" id="course_arrangingForm">
                        <!--表单第一行（教学班名称/上课时间/上课地点）-->
                        <div class="layui-form-item">
                            <!--上课老师-->
                            <div class="layui-inline">
                                <label class="layui-form-label">上课老师：</label>
                                <div class="layui-input-block">
                                    <select name="teachName" id="teachName" lay-search="">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <!--上课星期-->
                            <div class="layui-inline">
                                <label class="layui-form-label">星期：</label>
                                <div class="layui-input-block">
                                    <select name="week" id="week" lay-filter="shangkeshijian">
                                        <option value=""></option>
                                        <option value="1">星期一</option>
                                        <option value="2">星期二</option>
                                        <option value="3">星期三</option>
                                        <option value="4">星期四</option>
                                        <option value="5">星期五</option>
                                    </select>
                                </div>
                            </div>

                            <!--上课时间-->
                            <div class="layui-inline">
                                <label class="layui-form-label">上课时间：</label>
                                <div class="layui-input-block">
                                    <select name="course_time" id="course_time" lay-filter="shangkeshijian">
                                        <option value=""></option>
                                        <option value="1">第一大节</option>
                                        <option value="2">第二大节</option>
                                        <option value="3">第三大节</option>
                                        <option value="4">第四大节</option>
                                        <option value="5">第五大节</option>
                                    </select>
                                </div>
                            </div>

                        </div>
                        <!--表单第二行（上课老师/上课班级）-->
                        <div class="layui-form-item">
                            <!--上课地点-->
                            <div class="layui-inline">
                                <label class="layui-form-label">上课地点：</label>
                                <div class="layui-input-block">
                                    <select name="course_place" id="course_place" >
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <!--课程容量-->
                            <div class="layui-inline">
                                <label class="layui-form-label">课程容量：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="course_size"  id="course_size" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            &nbsp;
                            <!--起止周-->
                            <div class="layui-inline">
                                <label class="layui-form-label">起止周：</label>
                                <div class="layui-input-block">
                                    <select name="startAndEnd" id="startAndEnd" >
                                        <option value=""></option>
                                        <option value="1-18" selected >1-18</option>
                                    </select>
                                </div>
                            </div>


                        </div>
                        <!--表单按钮(提交/排课) ？？？这里好像一个按钮就行-->

                        <div class="layui-form-item" style="text-align: right;margin-right: 100px" >
                            <div class="layui-input-block">
                                <button type="button" class="layui-btn layui-btn-normal " lay-filter="doSubmit" lay-submit="">提交</button>
                                <button type="reset" class="layui-btn layui-btn-warm  " >重置</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!--"参数设置"表单结束-->

            </div>

            <!--”查看排课" 对应的内容-->
            <div class="layui-tab-item">

                <!--模糊查询开始   每个元素的name和id根据table.render中的field值修改-->
                <form action="" class="layui-form" method="post" >
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">年级:</label>
                            <div class="layui-input-inline">
                                <input type="text" name="tpGrade1"  id="tpGrade1" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学期：</label>
                            <!------------------------可以根据表中的内容动态添加-------------------------->
                            <div class="layui-input-block">
                                <select name="tpTerm1" id="tpTerm1" lay-search="" >
                                    <option value=""></option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">学年：</label>
                            <div class="layui-input-block">
                                <select name="tpYear1" id="tpYear1" lay-search="">
                                    <option value=""></option>
                                    <option value="2017-2018">2017-2018</option>
                                    <option value="2018-2019">2018-2019</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-block">
                                <button type="button" class="layui-btn" lay-filter="dosearch" id="dosearch1">查询</button>
                            </div>
                        </div>
                    </div>
                </form>
                <!--模糊查询结束-->


                <!--表格上方按钮开始-->
                <div  style="display: none" id="course_arranging_ToolBar1" lay-filter="course_arranging_ToolBar1">
                    <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
                    <!--<button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>-->
                    <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
                </div>
                <!--表格上方按钮结束-->

                <!--动态表格开始-->
                <table id="course_arranging_table1" lay-filter="course_arranging_table1"> </table>
                <!--动态表格结束-->

            </div>
        </div>

        <!-- 修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="updateDiv">
            <form class="layui-form " action="" lay-filter="updateCourse_arranging" id="updateCourse_arranging">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpMajorNum"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
					<div class="layui-inline">
                        <label class="layui-form-label">专业号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpMajorNum"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tpCourseNum" autocomplete="off" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">课程名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程学时</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courXs" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学分</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courRedit" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">类型</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courType" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">上课老师</label>
                        <div class="layui-input-inline">
                            <input type="text" name="teachName" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">星期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="week" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">上课时间</label>
                        <div class="layui-input-inline">
                            <input type="text" name="course_time" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">上课地点</label>
                        <div class="layui-input-inline">
                            <input type="text" name="course_place" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程容量</label>
                        <div class="layui-input-inline">
                            <input type="text" name="course_size" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">起止周</label>
                        <div class="layui-input-inline">
                            <input type="text" name="startAndEnd" autocomplete="off" class="layui-input" >
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

<script src="/SelectCourse/layui/layui.js"></script>
<script>
    layui.use(['element','form','table', 'layer', 'laypage'],function(){
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var  layer = layui.layer;//弹层
        var table = layui.table;//表格
        var  laypage = layui.laypage;

//----------------------------------------------------------排课-------------------------------------------------------------
        //渲染表格
        var tableIns = table.render({
            id:'course_arranging'
            ,elem: '#course_arranging_table'
            , url: '${pageContext.request.contextPath}/ArrCourseServlet' //请求路径
            ,defaultToolbar:[]
            , toolbar: '#course_arranging_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'radio', width: 120, fixe: 'dleft'}
                    , {field: 'tpTerm', title: '学期'}
                    , {field: 'tpYear', title: '学年'}
                    , {field: 'tpMajorNum', title: '专业号'}
                    , {field: 'tpGrade', title: '年级'}
                    , {field: 'tpCourseNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'courXs', title: '课程学时'}
                    , {field: 'courRedit', title: '学分'}
                    , {field: 'courType', title: '类型'}
                    //--别忘了加选修，别忘了修改下面提交那个js-------------------
                ]
            ]

        });

        //模糊查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        //模糊查询函数
        function fuzzy_search(){
            var tpGrade = $('#tpGrade');//年级
            var tpTerm = $('#tpTerm');//学期
            var tpYear= $('#tpYear');//学年
            //layer.confirm(coursename.val());
            console.log(tpGrade.val());
            
            //执行重载 （发送了两次请求。上面table.render 向后台发送了一次请求，这里table.reload还会向后台放松一次请求，两次请求路径相同）
            table.reload('course_arranging', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,
                where: {
                    tpGrade:tpGrade.val(),//逗号隔开
                    tpTerm:tpTerm.val(),
                    tpYear:tpYear.val()
                }
            });
        }

        //监听头部
        table.on('toolbar(course_arranging_table)',function (obj) {
            switch (obj.event) {
                case 'reloadTable':
                    tableIns.reload();
                    break;
            }
        });

        //监听单选框(实现当点击单选框进行排课时，"参数设置"的下拉框根据选中数据进行动态加载) 这里只做了上课老师的
        table.on('radio(course_arranging_table)',function (obj) {
            dataChecked = obj.data;
            console.log(dataChecked.tpCourseNum);

            //动态从数据库获取下拉列表(教师名称)
            $.ajax({
                url: '${pageContext.request.contextPath}/DPDWTeaServelt',//请求路径
                dataType: 'json',
                data:{'courNum': dataChecked.tpCourseNum},	//查询状态为正常的所有机构类型
                type: 'post',
                success: function (data) {
                    $.each(data.data, function (index, item) {
                        $('#teachName').append(new Option(item.teaName, item.teaNum));// 下拉菜单里添加元素
                    });
                    //重新渲染下拉菜单
                    layui.form.render("select");
                }
            });

            //动态从数据库获取下拉列表(地点)
            $.ajax({
                url: '${pageContext.request.contextPath}/DPDWPlaceServlet',//请求路径
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $.each(data.data, function (index, item) {
                        $('#course_place').append(new Option(item.place, item.place));// 下拉菜单里添加元素
                    });
                    //重新渲染下拉菜单
                    layui.form.render("select");
                }
            });
        });

        var url;
        //提交"参数设置"表单
        form.on("submit(doSubmit)",function(obj){
            var params = obj.field; // 表单中的数据
            console.log("-------");
            console.log(params);
            var jsonObj = {
                //表格中的数据
                'tpTerm': dataChecked.tpTerm,
                'tpYear': dataChecked.tpYear,
                'tpMajorNum': dataChecked.tpMajorNum,
                'tpGrade': dataChecked.tpGrade,
                'tpCourseNum': dataChecked.tpCourseNum,
                'courName': dataChecked.courName,
                'courXs': dataChecked.courXs,
                'courRedit': dataChecked.courRedit,
                'courType': dataChecked.courType,

                //表单中的数据
                'teachName':params.teachName,
                'week':params.week,
                'course_time':params.course_time,
                'course_place':params.course_place,
                'course_size':params.course_size,
                'startAndEnd':params.startAndEnd
            };
            console.log(jsonObj);
            var datastr = JSON.stringify(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post('${pageContext.request.contextPath}/AddArrCourseServlet',datastr,function(obj){ //提交数据，function中的obj是后台返回的数据
               	var obj = JSON.parse(obj);
            	if(obj.msg="true"){
                	alert("操作成功");
                }else if(obj.msg="false") {
                	alert("操作失败");	
                }
            	//layer.msg(obj);
                //关闭弹出层
                //layer.close(addIndex);
                //刷新数据表格
                tableIns.reload();
            });
        });
//-------------------------------------------查看排课-------------------------------------------------------------------
        //渲染表格
        var tableIns1 = table.render({
            id:'course_arranging1'
            ,elem: '#course_arranging_table1'
            , url: '${pageContext.request.contextPath}/SearchArrCouServlet'
            ,defaultToolbar:[]
            , toolbar: '#course_arranging_ToolBar1'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [
                [
                    { type: 'checkbox', width: 120, fixe: 'dleft'}
                    , {field: 'tpMajorNum', title: '专业号'}
                    , {field: 'tpMajorName', title: '专业名'}
                    , {field: 'tpCourseNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'teacherNum', title: '教师号'}
                    , {field: 'teachName', title: '上课老师'}
                    , {field: 'course_time', title: '上课时间'}
                    , {field: 'course_place', title: '上课地点'}
                    , {field: 'course_size', title: '课程容量'}
                    , {field: 'startAndEnd', title: '起止周'}
                    //--加其他的项，别忘了修改下面提交那个js-------------------
                ]
            ]
        });

        
        //模糊查询（后台要自己写）
        $("#dosearch1").click(function () {
            fuzzy_search1();
        });

        //模糊查询函数
        function fuzzy_search1(){
            var tpGrade1 = $('#tpGrade1');//年级
            var tpTerm1 = $('#tpTerm1');//学期
            var tpYear1= $('#tpYear1');//学年
            console.log(tpGrade1.val());
            console.log(tpTerm1.val());
            console.log(tpYear1.val());
            table.reload('course_arranging1', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	// 通过request获得参数，逗号隔开
                    tpGrade1:tpGrade1.val(),
                    tpTerm1:tpTerm1.val(),
                    tpYear1:tpYear1.val()
                }
            });
        }

        //监听头部
        table.on('toolbar(course_arranging_table1)',function (obj) {
            var checkStatus = table.checkStatus('course_arranging1'); //获取选中的行
            data = checkStatus.data;
            switch (obj.event) {
                case 'delete':
                    batchDelet(data);
                    break;
                case 'edit':
                    if (data.length<1){
                        layer.msg("请选择一个需要修改的数据");
                    }
                    else if(data.length>1){
                        layer.msg("只能选择一个需要修改的数据");
                    }
                    else {
                        openUpdateCourse(data[0]);
                    }
                    break;
                case 'reloadTable':
                    tableIns1.reload();
                    break;
            }
        });

        //批量删除
        function batchDelet(data) {
            console.log(data);
			grade = [];
			term = [];
			year = [];
			couNum = [];
			teaNum = [];
			teaTime = []
            if (data.length > 0) {
                for (var i in data) {
                    grade.push($('#tpGrade1').val()); //这个id改成"查看排课"这个表的主键值（table.render里field对应的）{这个表好像没主键}
                	term.push($('#tpTerm1').val());
                	year.push($('#tpYear1').val());
                	couNum.push(data[i].tpCourseNum);
                	teaNum.push(data[i].teacherNum);
                	teaTime.push(data[i].course_time)
                }
                console.log(grade);
                var jsonObj = {
                		'grade':grade,'term':term,'year':year,'couNum':couNum,'teaNum':teaNum,"teaTime":teaTime
                }
                var datastr = JSON.stringify(jsonObj);
                layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('${pageContext.request.contextPath}/DeleteArrCouServlet',datastr,function (data) {
                        var data = JSON.parse(data);
                    	if(data.msg == "true"){
                        	layer.msg("删除成功!", {icon:1});
                        }else{
                        	layer.msg("删除失败!", {icon:5});
                        }
                    	tableIns1.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择需要删除的记录");
            }
        }

        var url1;
        //打开修改页面
        function openUpdateCourse(data){
            updateIndex1=layer.open({
                type:1,
                title:'修改排课信息',
                content:$("#updateDiv"),//修改------------------------------
                area:['800px','600px'],
                success:function(index){
                    form.val("updateCourse_arranging",data);
                    url1="/SelectCourse/UpdateArrCouServlet";
                }
            });
        }

        // 排课提交表单
        form.on("submit(updateSubmit)",function(obj){
            var params1 = obj.field;
            var jsonObj = {
                //表格中的数据
                'majorNum':params1.tpMajorNum,
                'tpGrade': $('tpGrade1').val(),
                'tpYear': $('tpYear1').val(),
                'tpTerm': $('tpTerm1').val(),
                'tpCourseNum': params1.tpCourseNum,
                'teacherNum':params1.teacherNum,
                'course_size':params1.course_size,
                'startAndEnd':params1.startAndEnd,
                'course_time':params1.course_time,
                'course_place':params1.course_place
            };

            var dataStr1 = JSON.stringify(jsonObj);
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url1,dataStr1,function(obj){ //提交数据，function中的obj是后台返回的数据
                var data = JSON.parse(obj);
            	if(data.msg == "true"){
                	layer.msg("修改成功!", {icon:1});
                }else{
                	layer.msg("修改失败!", {icon:5});
                }
            	layer.msg(obj);
                //关闭弹出层
                layer.close(updateIndex1)
                //刷新数据表格
                tableIns1.reload();
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