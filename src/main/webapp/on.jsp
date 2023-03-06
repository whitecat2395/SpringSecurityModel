<%--
  Created by IntelliJ IDEA.
  User: zhouzheng
  Date: 2023/2/3
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<div class="login">
    <div class="title">
        华夏云智能监控服务管理平台
    </div>
    <form class="layui-form" action="login/admin" method="post">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="text" name="username" required
                       lay-reqText="请输入账号"
                       lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="password"
                       lay-reqText="请输入密码"
                       name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center">
                <button class="layui-btn layui-btn-primary layui-border-blue" lay-submit lay-filter="formDemo">登陆
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
