<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"></meta>
    <title>登录界面</title>
    <link rel="stylesheet" href="/SelectCourse/layui/css/layui.css"></link>
	<script type="text/javascript" src="/SelectCourse/js/jquery-3.1.1.min.js"></script>
    <script src="/SelectCourse/js/jquery.js"></script>

    <script type="text/javascript">
		$(document).ready(function() {
			if("${msg}" == "nouser"){
				alert("用户不存在");
			}
			if("${msg}" == "passwdwrong" ){
				alert("密码错误");
			}
			if("${sessionScope.user}" == null){
				alert("请登录");
			}
		});
	</script>
    <style>

        .login_top{

            background-image: url(/SelectCourse/images/login.jpg);
            background-size: cover;
            z-index:1;
            background-repeat: no-repeat; /*不平埔*/
        }


        .login_top:before{
            position:absolute;
            content: " ";
            top: 0;
            left:0;
            width:100%;
            height: 100%;
            background:rgba(0,0,0,.6);
            z-index: -1;
        }

        .myLogin_window{
            width:400px;
            height: 460px;
            position: absolute;
            z-index: 10;
            background: #ffffff;
            border-radius: 10px;
        }
        .mylogin_style{
            margin-left: 30px;
            margin-right: 30px
        }
        .mylogin_btn_style{
            margin-left: 30px;
            margin-right: 30px;
            margin-top: 23px;

        }
    </style>
</head>
<body class="login_top">
<div  style="width: 100%;height: 800px">
    <div class="layui-col-md4 layui-col-md-offset8 myLogin_window" style="margin-top:10%;margin-bottom:10%;padding: 20px">
        <div class="layui-col-md4 layui-col-md-offset4" style="text-align: center;font-size: x-large;margin-bottom: 30px;margin-top: 20px">
            登&nbsp;&nbsp;&nbsp;&nbsp;录
        </div>
        <form id = "id" action="${pageContext.request.contextPath }/LoginServlet" class="layui-form" method="post">
            <div class="layui-form-item mylogin_style" >
                <!--<label class="layui-form-label">用户名</label>-->
                <p style="margin-bottom:10px;font-size: medium">用户名:</p>
                <input type="text" name="username" required="required"  lay-verify="required" placeholder="请输入用户名"  class="layui-input"/>
            </div>
            <div class="layui-form-item mylogin_style" >
                <!--<label class="layui-form-label">用户名</label>-->
                <p style="margin-bottom:10px;font-size: medium">密码:</p>
                <input type="password" name="passwd" required  lay-verify="required" placeholder="请输入密码"  class="layui-input"/>
            </div>
            <div class="layui-form-item mylogin_style">
                <div class="layui-col-md4">
                    <input  type="radio" name="role" value="student" title="学生"/>
                </div>
                <div class="layui-col-md4">
                    <input type="radio" name="role" value="teacher" title="教师" />
                </div>
                <div class="layui-col-md4">
                    <input type="radio" name="role" value="manager" title="管理员"/>
                </div>
            </div>
            <div class="layui-form-item mylogin_btn_style">
                <button class="layui-btn layui-btn-fluid layui-btn-lg" style=" background: #5a84fd;" lay-submit lay-filter="formDemo">登&nbsp;&nbsp;&nbsp;录</button>
            </div>
        </form>
    </div>
</div>



<script src="/SelectCourse/layui/layui.js"></script>
<script src="/SelectCourse/layui/layui.all.js"></script>
<script>

    layui.use('form', function(){
        var form = layui.form;
        
        form.on('submit(formDemo)', function (data) {
            if (!data.field.role){
                layer.msg('必填项不能为空',{icon:5,anim:6});
                return false;
            }
            
            form.submit();
        });

    });

</script>

</body>
</html>
