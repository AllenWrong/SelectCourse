<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>公告管理</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>发布公告</legend>
</fieldset>
<div class="layui-tab layui-tab-card">
    <!--公告管理的相关内容-->
    <div class="layui-tab-content">
        <form action="" class="layui-form" method="post">
            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">发布时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nianji"  autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">查询</button>
                    </div>
                </div>
            </div>
        </form>

        <div  id="userToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="getSelect">修改选中行</button>
            <button type="button" class="layui-btn layui-btn-sm" lay-event="reloadTable">刷新数据</button>
        </div>

        <table class="layui-table">
            <colgroup>
            </colgroup>
            <thead>
            <tr>
                <th style="text-align: center" ><input type="checkbox"></th>
                <th style="text-align: center">发布时间</th>
                <th style="text-align: center">发布的信息</th>

            </tr>
            </thead>
            <tbody>
  			<c:forEach var="notice" items="${sessionScope.noticeList }" begin="0" end="${sessionScope.size }" step="1">
            	<tr align="center">
            		<td></td>
            		<td>${notice.noticeTime }</td>
            		<td>${notice.noticeContent }</td>
            	</tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<script src="../../../layui/layui.js"></script>
<script>

    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;

    });
</script>

</body>
</html>