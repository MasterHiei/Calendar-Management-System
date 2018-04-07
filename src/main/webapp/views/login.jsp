<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TMHI-日程管理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/messages.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3">
            <div class="col-md-8 login-form">
                <div class="form-group">
                    <h3 class="form-title">用户登录</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control form-input" id="userName" placeholder="请输入用户名...">
                    <i class="fa fa-user fa-lg user-fa"></i>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control form-input" id="password" placeholder="请输入密码...">
                    <i class="fa fa-lock fa-lg user-fa"></i>
                </div>
                <div class="form-group">
                    <label for="rememberCheck" class="label-remember">
                        <input type="checkbox" id="rememberCheck">记住我
                    </label>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-success col-md-2 pull-left" id="loginBtn">登&nbsp;录</button>
                    <button type="button" class="btn btn-danger col-md-2 pull-right" id="registerBtn">注&nbsp;册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<%@include file="modal.jsp"%>
</body>
</html>
