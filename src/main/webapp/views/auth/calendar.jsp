<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TMHI-日程管理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/calendar.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/auth/calendar.js"></script>
</head>
<body>
<div class="container">
    <div class="header">
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <div class="navbar-brand line-center">
                        <p class="logo"><strong>型月重工</strong><cite class="logo-site"> - TYPE-MOON Heavy Industries Fleet - </cite></p>
                    </div>
                </div>
                <div class="nav navbar-nav navbar-left line-center nav-left-container">
                    <div class="btn-group navbar-item line-center date-adjust-group" id="to-prev-month"
                         data-toggle="tooltip" data-placement="bottom" title="上一月">
                        <a class="btn btn-default"><i class="fa fa-angle-left fa-fw fa-fw-lg"></i></a>
                    </div>
                    <div class="btn-group navbar-item line-center date-adjust-group" id="now-date"
                         data-toggle="tooltip" data-placement="bottom" title="选择月份">
                        <a class="btn btn-default"><span id="date-picker"></span></a>
                    </div>
                    <div class="btn-group navbar-item line-center date-adjust-group" id="to-next-month"
                         data-toggle="tooltip" data-placement="bottom" title="下一月">
                        <a class="btn btn-default"><i class="fa fa-angle-right fa-fw fa-fw-lg"></i></a>
                    </div>
                    <div class="btn-group navbar-item line-center" id="to-today"
                         data-toggle="tooltip" data-placement="bottom">
                        <a class="btn btn-default">
                            <span>今天</span>
                        </a>
                    </div>
                </div>
                <div class="nav navbar-nav navbar-right line-center nav-right-container">
                    <div class="btn-group navbar-item">
                        <a class="btn btn-default" id="periods-selected" data-toggle="dropdown"><i class="fa fa-calendar fa-fw fa-fw-nm"></i><span>&nbsp;月份</span></a>
                        <ul class="dropdown-menu">
                            <li id="periods-month"><a><i class="fa fa-calendar fa-fw-nm"></i><span class="menu-text">&nbsp;&nbsp;日历</span></a></li>
                            <li id="periods-list"><a><i class="fa fa-list-alt fa-fw fa-fw-nm"></i><span class="menu-text">&nbsp;列表</span></a></li>
                        </ul>
                    </div>
                    <div class="navbar-item avatar-container">
                        <img src="${pageContext.request.contextPath}/img/avatar/default/default-avatar.png" id="user-avatar" draggable="false">
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="table-container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="sun"><p class="week-label text-info">星期日</p></th>
                <th class="mon"><p class="week-label text-info">星期一</p></th>
                <th class="tues"><p class="week-label text-info">星期二</p></th>
                <th class="wed"><p class="week-label text-info">星期三</p></th>
                <th class="thur"><p class="week-label text-info">星期四</p></th>
                <th class="fri"><p class="week-label text-info">星期五</p></th>
                <th class="sat"><p class="week-label text-info">星期六</p></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach begin="1" end="6" step="1" var="i">
                <tr class="row-6-md-1" id="line-${i}" lineNo="${i}">
                    <td class="col-7-md-1 sun">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 mon">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 tues">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 wed">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 thur">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 fri">
                        <span class="date-line"></span>
                    </td>
                    <td class="col-7-md-1 sat">
                        <span class="date-line"></span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="add-schedule" data-toggle="tooltip" data-placement="left" title="创建新日程">
            <i class="fa fa-plus fa-create"></i>
        </div>
    </div>
    <div class="alert alert-success" id="get-event-alert">
        <i class="fa fa-spinner fa-spin"></i>
        <span>正在获取日程信息</span>
    </div>
    <div class="modal fade" id="eventInfoModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header" id="event-modal-header">
                    <div>
                        <div class="modal-tool">
                            <div id="event-close" data-toggle="tooltip" data-placement="bottom" title="关闭">
                                <i class="fa fa-times"></i>
                            </div>
                        </div>
                        <div class="modal-tool">
                            <div id="event-edit" data-toggle="tooltip" data-placement="bottom" title="编辑">
                                <i class="fa fa-edit"></i>
                            </div>
                        </div>
                    </div>
                    <div class="modal-title" id="event-modal-title">
                        <span id="event-title" class="text-hidden"></span>
                    </div>
                </div>
                <div class="modal-body">
                    <div id="modal-period">
                        <div class="body-i" id="period-i"><i class="fa fa-calendar"></i></div>
                        <div class="body-c" id="period-c"></div>
                    </div>
                    <div id="modal-desc">
                        <div class="body-i"><i class="fa fa-file-text-o"></i></div>
                        <div class="body-c">
                            <textarea id="event-desc" class="text-hidden" disabled rows="2"></textarea>
                        </div>
                    </div>
                    <div id="modal-owner">
                        <div class="body-i"><i class="fa fa-user"></i></div>
                        <div class="body-c">
                            <span id="event-owner"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="date">
</div>
</body>
</html>
