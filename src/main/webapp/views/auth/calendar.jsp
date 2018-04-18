<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TMHI-日程管理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lib/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/calendar.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
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
                    <div class="btn-group navbar-item line-center date-adjust-group" id="to-prev-month">
                        <a class="btn btn-default"><i class="fa fa-angle-left fa-fw fa-fw-lg"></i></a>
                    </div>
                    <div class="btn-group navbar-item line-center date-adjust-group" id="now-date">
                        <a class="btn btn-default"><span></span></a>
                    </div>
                    <div class="btn-group navbar-item line-center date-adjust-group" id="to-next-month">
                        <a class="btn btn-default"><i class="fa fa-angle-right fa-fw fa-fw-lg"></i></a>
                    </div>
                    <div class="btn-group navbar-item line-center" id="to-today">
                        <a class="btn btn-default"><span>今天</span></a>
                    </div>
                </div>
                <div class="nav navbar-nav navbar-right line-center nav-right-container">
                    <div class="btn-group navbar-item">
                        <a class="btn btn-default" id="periods-selected" data-toggle="dropdown"><i class="fa fa-calendar-check-o fa-fw fa-fw-md"></i><span> 月</span></a>
                        <ul class="dropdown-menu">
                            <li><a id="periods-month"><i class="fa fa-calendar-check-o fa-fw-nm"></i><span class="menu-text"> 月</span></a></li>
                            <li><a id="periods-list"><i class="fa fa-list-alt fa-fw fa-fw-nm"></i><span class="menu-text"> 列表</span></a></li>
                        </ul>
                    </div>
                    <div class="navbar-item avatar-container">
                        <img src="${pageContext.request.contextPath}/img/avatar/default/default-avatar.png" id="user-avatar" draggable="false">
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div class="body">
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
                <tr class="row-6-md-1" lineNo="${i}">
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
    </div>
    <input type="hidden" id="targetYear" value="${calendarForm.targetYear}">
    <input type="hidden" id="targetMonth" value="${calendarForm.targetMonth}">
    <input type="hidden" id="targetDay" value="${calendarForm.targetDay}">
    <input type="hidden" id="firstDayOfWeek" value="${calendarForm.firstDayOfWeek}">
    <input type="hidden" id="lengthOfMonth" value="${calendarForm.lengthOfMonth}">
    <input type="hidden" id="lengthOfPrevMonth" value="${calendarForm.lengthOfPrevMonth}">
    <input type="hidden" id="isToday" value="${calendarForm.isToday}">
</div>
</body>
</html>
