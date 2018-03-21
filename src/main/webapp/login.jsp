<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TMHI-日程管理</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-4">
            <div class="col-md-7 login-form">
                <div class="form-group">
                    <h3 class="form-title">用户登录</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control required" id="userName" placeholder="请输入用户名...">
                    <i class="fa fa-user fa-lg"></i>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control required" id="password" placeholder="请输入密码...">
                    <i class="fa fa-lock fa-lg"></i>
                </div>
                <div class="form-group">
                    <label for="rememberCheck" class="label-remember">
                        <input type="checkbox" id="rememberCheck">记住我
                    </label>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-success col-md-3 pull-left" id="loginBtn">登&nbsp;录</button>
                    <button type="button" class="btn btn-danger col-md-3 pull-right" id="registerBtn" 
                            data-toggle="modal" data-target="#forbiddenAlert">注&nbsp;册</button>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="footer navbar-fixed-bottom ">
    <div class="container text-center">
        <p><strong>-TYPE-MOON Heavy Industries- Fleet &copy;</strong><br><small>All Rights Reserved.</small></p>
    </div>
</footer>

<div class="modal fade" id="forbiddenAlert" tabindex="-1" role="dialog" 
     aria-labelledby="forbiddenAlertTitle" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <strong>
                    <p class="modal-title h3" id="forbiddenAlertTitle">提示</p>
                </strong>
            </div>
            <div class="modal-body">
                用户注册功能将在系统正式上线后开放。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
