<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>课程信息管理</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css">
    <link rel="stylesheet" href="/SelectCourse/layui/css/autocomplete.css"/>
    <script type="text/javascript" src="/SelectCourse/js/jquery-3.1.1.min.js"></script>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>课程信息管理</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
        <!--课程信息管理的相关内容-->
        <!--模糊查询开始-->
        <form action="" class="layui-form" method="post" >
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">课程类型：</label>
                    <div class="layui-input-block">
                        <select name="courType" id="courType">
                            <option value=""></option>
                            <option value="必修">必修课</option>
                            <option value="选修">选修课</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">课程名:</label>
                    <div class="layui-input-inline">
                       <select name="coursename" id="coursename">
                            <option value=""></option>
                        </select>
                    </div>
                    <div id="recommander"></div>
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
        <div  style="display: none" id="course_information_ToolBar" lay-filter="course_information_ToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
        </div>
        <!--表格上方按钮结束-->

        <!--动态表格开始-->
        <table id="course_information_table" lay-filter="course_information_table"> </table>
        <!--动态表格结束-->


        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="addOrUpdateDiv">
            <form class="layui-form " action="" lay-filter="addCourseInfo" id="addCourseInfo">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courNum"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">课程名:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">课程学时:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courXs"  lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">学分:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courRedit" lay-verify="required" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">类型:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courType" autocomplete="off" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courTerm" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">先行课程号:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="courXxkNum" autocomplete="off" class="layui-input" >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal " lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm  " >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加和修改的弹出层结束 -->
        
        <!-- 修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="updateDiv">
        <form class="layui-form " action="" lay-filter="updateCourseInfo" id="updateCourseInfo">
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
                               class="layui-input" disabled="true"> <!--disabled 属性设置禁用，建议用readonly-->
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">课程学时:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="courXs"  lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">学分:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="courRedit" lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">类型:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="courType" autocomplete="off" class="layui-input" >
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">学期:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="courTerm" autocomplete="off" class="layui-input" >
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">先行课程号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="courXxkNum" autocomplete="off" class="layui-input" >
                    </div>
                </div>
            </div>

            <div class="layui-form-item" style="text-align: right;margin-right: 80px" >
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-normal " lay-filter="doSubmit" lay-submit="">提交</button>
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

        //渲染表格
		var tableIns = table.render({
            id:'course_info'
            ,elem: '#course_information_table'
            , url: '${pageContext.request.contextPath}/CInformationServlet' //请求路径
            ,defaultToolbar:[],
            page:true
            , toolbar: '#course_information_ToolBar'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [ // 表头
                [
                    { type: 'checkbox', width: 120, sort: false, fixe: 'dleft'}
                    , {field: 'courID', title: '序号',sort:true}
                    , {field: 'courNum', title: '课程号'}
                    , {field: 'courName', title: '课程名'}
                    , {field: 'courXs', title: '课程学时'}
                    , {field: 'courRedit', title: '学分'}
                    , {field: 'courType', title: '类型'}
                    , {field: 'courTerm', title: '学期'}
                    , {field: 'courXxkNum', title: '先行课程号'}
                ]
            ]
        });	


        //动态从数据库获取下拉列表
        $.ajax({
            url: '${pageContext.request.contextPath}/CourseInfoServlet',//请求路径
            //dataType: 'json',
            data:{'state': 0},	//查询状态为正常的所有机构类型
            type: 'POST',
            success: function (data) {
                //$不能遍历json字符串，可以遍历json数组，（可能需要将后台传来的json字符串转化为json对象）
                // 例如 这些后台返回的json对象（如果是字符串需要转化）
                // var data.data=[{id:'0',name:18},{id:'1',name:20},{id:'2',name:22}];
                //
                var data = JSON.parse(data);
                $.each(data, function (index, item) {
                    $('#coursenum').append(new Option(item.id, item.name));// 下拉菜单添加元素，new Option(text,value);
                    $('#coursename').append(new Option(item.name,item.id));
                });
                //重新渲染下拉菜单
                layui.form.render("select");
            },
            error:function(ajaxContext){
            	alert("404");
            }
        });



        //模糊查询（后台要自己写）
        $("#dosearch").click(function () {
            fuzzy_search();
        });

        
        
    	$("#courType").change(function (){
    		var select = document.getElementById("courType");
    		while(select.firstChild){
    			select.removeChild(select.firstChild);
    		}
    		
            $.ajax({
                url: '${pageContext.request.contextPath}/CourseInfoServlet',//请求路径
                //dataType: 'json',
                data:{'state': 0},	//查询状态为正常的所有机构类型
                type: 'POST',
                success: function (data) {
                    //$不能遍历json字符串，可以遍历json数组，（可能需要将后台传来的json字符串转化为json对象）
                    // 例如 这些后台返回的json对象（如果是字符串需要转化）
                    // var data.data=[{id:'0',name:18},{id:'1',name:20},{id:'2',name:22}];
                    //
                    var data = JSON.parse(data);
                    $('#coursename').append(new Option());
                    $.each(data, function (index, item) {
                    	if(item.type = select){
                            $('#coursenum').append(new Option(item.id, item.name));// 下拉菜单添加元素，new Option(text,value);
                            $('#coursename').append(new Option(item.name,item.id));
                    	}
                    });
                    //重新渲染下拉菜单
                    layui.form.render("select");
                },
                error:function(ajaxContext){
                	alert("404");
                }
            });
    	});
    	
        //模糊查询函数
        function fuzzy_search(){
            var coursename = $('#coursename');//课程名
            var coursenum = $('#coursenum');//课程号
            var courType= $('#courType');//课程性质
            table.reload('course_info', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    coursename:coursename.val(),//逗号隔开
                    coursenum:coursenum.val(),
                    courType:courType.val()
                }
            });
        }


        //监听头部
        table.on('toolbar(course_information_table)',function (obj) {

            var checkStatus = table.checkStatus('course_info'); //获取选中的行

            data = checkStatus.data;
            console.log(data.length);
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
        var mainIndex;
        //打开添加页面
        function openAddCourse(){
            mainIndex=layer.open({
                type:1,
                title:'添加课程信息',
                content:$("#addOrUpdateDiv"),
                area:['800px','400px'],
                success:function(){
                    //清空表单数据
                    $("#addCourseInfo")[0].reset();
                    url="/SelectCourse/AddCourseServlet"; //请求路径
                    //var form = document.getElementById("addCourseInfo");
                    //form.action="${pageContext.request.contextPath }/AddCourseServlet"
                }
            });
        }

       	/* 打开修改页面
        function openUpdateCourse(data){
            mainIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#addOrUpdateDiv"),
                area:['800px','400px'],
                success:function(index){
                	form.val("addCourseInfo",data);
                	url = "${pageContext.request.contextPath }/UpdateCorInfoServlet";
                }
            });
        }*/
       	
        var url;
        var updateIndex;
        //打开修改页面
        function openUpdateCourse(data){
            updateIndex=layer.open({
                type:1,
                title:'修改用户',
                content:$("#updateDiv"),//修改------------------------------
                area:['800px','400px'],
                success:function(index){
                    form.val("updateCourseInfo",data);
                    url="${pageContext.request.contextPath }/UpdateCorInfoServlet"; //请求路径
                }
            });
        }


        //1.保存（添加功能、修改功能）
        //2.form.on是表单监听，submit表示监听监听提交按钮，doSubmit是提交按钮中的button中lay-filter值
        form.on("submit(doSubmit)",function(obj){
			// 拿到表单数据
        	var params=obj.field;
			var jsonObj = {
				"courName":params.courName,
				"courNum" : params.courNum,
				"courRedit": params.courRedit,
				"courTerm" : params.courTerm,
				"courType" : params.courType,
				"courXs" : params.courXs,
				"courXxkNum" : params.courXxkNum
			};
			var datastr = JSON.stringify(jsonObj);
			
            console.log(jsonObj);
            //url：请求路径，params：弹出表单中的数据
            $.post(url,datastr,function(obj){
            	var jsonObj = JSON.parse(obj);
            	if(jsonObj.msg == "true"){
            		alert("操作成功");
            	}
            	if(jsonObj.msg == "false"){
            		alert("操作失败");
            	}
                //关闭弹出层
                layer.close(mainIndex);
                layer.close(updateIndex);
                //刷新数据表格
                tableIns.reload();
            })
        });

        //批量删除
        // data是一个数组
        function batchDelet(data) {
            courseArr = []; //存储每一行数据的id
            if (data.length > 0) {
                for (var i in data) {
                	courseArr.push(data[i]);
                }
                var jsonObj = {"courseArr":courseArr};
                var requestObj = JSON.stringify(jsonObj);
                layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('/SelectCourse/DeleteCourseServlet',requestObj,function (data) { //url为请求路径
                     	var jsonobj = JSON.parse(data);
                    	if(jsonObj.msg == "true"){
                    		//layer.msg("删除成功!",{icon:1});
                    		alert("删除成功");
                    	}
                    	if(jsonObj.msg == "false"){
                    		//layer.msg("删除失败!",{icon:1});
                    		alert("删除失败");
                    	}
                    	tableIns.reload();
                        layer.close(index);
                    });
                })
            } else {
                layer.msg("请选择需要删除的用户");
            }
        }

    });
</script>
</body>
</html>