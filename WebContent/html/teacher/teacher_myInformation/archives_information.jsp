<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>教师档案信息</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<style>
    .myTableCell-one{
        width:15% ;
        background-color:#efefef;
        text-align: center;
    }
    .myTableCell-two{
        width:35% ;
        text-align: left;
        padding: 10px;

    }


</style>
<body>
    <fieldset class="layui-elem-field " style="margin-top: 50px;margin-left: 250px;margin-right: 250px;border-width: 1.3px">
        <legend>档案信息</legend>
        <div class="layui-tab layui-tab-card" style=" padding:20px 50px;margin-left: 100px;margin-right: 100px;margin-bottom: 50px" >
            <table  class="layui-table">
                <tr>
                    <td class="myTableCell-one">姓名</td> <td class="myTableCell-two">${sessionScope.user.teaName }</td>
                    <td class="myTableCell-one">教职工号</td> <td class="myTableCell-two">${sessionScope.user.teaNum}</td>
                </tr>
                <tr>
                    <td class="myTableCell-one">性别</td> <td class="myTableCell-two">${sessionScope.user.teaSex}</td>
                    <td class="myTableCell-one">出生日期</td> <td class="myTableCell-two">${sessionScope.user.teaBirthday}</td>
                </tr>

                <tr>
                    <td class="myTableCell-one">专业</td> <td class="myTableCell-two">${sessionScope.user.teaMajor}</td>
                    <td class="myTableCell-one">职称</td> <td class="myTableCell-two">${sessionScope.user.teaTitle}</td>
                </tr>

                <tr>
                    <td class="myTableCell-one">民族</td> <td class="myTableCell-two">${sessionScope.user.teaNation}</td>
                    <td class="myTableCell-one">政治面貌</td> <td class="myTableCell-two">${sessionScope.user.teaPolicy}</td>
                </tr>

                <tr>
                    <td class="myTableCell-one">入职时间</td> <td class="myTableCell-two">${sessionScope.user.teaInschoolTime}</td>
                    <td class="myTableCell-one">学历</td> <td class="myTableCell-two">${sessionScope.user.teaXl}</td>
                </tr>

                <tr>
                    <td class="myTableCell-one">家庭住址</td> <td class="myTableCell-two">${sessionScope.user.teaHome}</td>
                    <td class="myTableCell-one">电话</td> <td class="myTableCell-two">${sessionScope.user.teaPhone}</td>
                </tr>
            </table>
        </div>
    </fieldset>

<script src="../../../layui/layui.js"></script>
<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
    });
</script>
</body>
</html>