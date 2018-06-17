<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TMHI-日程管理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/profile.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/auth/profile.js"></script>
</head>
<body>
<div class="container">
    <header class="header">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <div class="navbar-brand line-center">
                        <p class="logo"><strong>型月重工</strong><cite class="logo-site"> - TYPE-MOON Heavy Industries Fleet - </cite></p>
                    </div>
                </div>
                <div class="nav navbar-nav navbar-right line-center nav-right-container">
                    <div class="btn-group navbar-item">
                        <a class="btn btn-default" id="user-message">
                            <i class="fa fa-bell"></i>
                        </a>
                    </div>
                    <div class="navbar-item" id="avatar-container">
                        <img src="${pageContext.request.contextPath}/${profile.userAvatar}" id="user-avatar" draggable="false">
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <div class="body">
        <div class="user-info">
            <div class="section">
                <div class="inline-group label-group">
                    <span class="info-label info-group">用户名</span>
                </div>
                <div class="inline-group input-group">
                    <span class="info-input info-group" id="user-name">${profile.userName}</span>
                </div>
                <div class="inline-group arrow-group">
                    <i class="fa fa-chevron-right"></i>
                </div>
            </div>
            <div class="section">
                <div class="inline-group label-group">
                    <span class="info-label info-group">用户邮箱</span>
                </div>
                <div class="inline-group input-group">
                    <span class="info-input info-group" id="user-mail">${profile.mailAddress}</span>
                </div>
                <div class="inline-group arrow-group">
                    <i class="fa fa-chevron-right"></i>
                </div>
            </div>
            <div class="section">
                <div class="inline-group label-group">
                    <span class="info-label info-group">个人状态</span>
                </div>
                <div class="inline-group input-group">
                    <span class="info-input info-group" id="user-action">${profile.nowAction}</span>
                </div>
                <div class="inline-group arrow-group">
                    <i class="fa fa-chevron-right"></i>
                </div>
            </div>
        </div>
        <div class="user-event">
            <div></div>
        </div>
    </div>
</div>
</body>
</html>
