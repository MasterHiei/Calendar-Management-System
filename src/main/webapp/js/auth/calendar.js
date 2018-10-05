$(function () {
   'use strict';
   
    $(document).ready(function () {
        // 显示日历
        setDateToNow();
        getEventList(USE_SESSION_YES);

        // 设置今日标签提示信息
        var today = new Date();
        $('#to-today').attr('data-original-title', (today.getMonth() + 1) + '月' + today.getDate() + '日');

        // 使event div宽度自适应屏幕
        window.addEventListener('resize', function () {
            resizeEventDiv();
        })
    });

    // 用户信息页面跳转
    $('#avatar-container').on('click', function () {
        // TODO when user-profile page is done is done
        // window.location.href = 'profile.html';
    });
    
    // 显示日期选择控件
    $('#now-date').on('click', function () {
        $('#date-picker').datetimepicker({
            format : 'yyyy-mm-dd',
            startView : 'year',
            minView : 'year',
            maxView : 'decade',
            language : 'zh-CN',
            autoclose : true
        }).on('changeMonth', function (ev) {
            var date = new Date(ev.date);
            $('#date').val(date.getFullYear() + '-' + (date.getMonth() + 1) + '-1');

            getEventList(USE_SESSION_NO);
        });
        // ISSUE: Bootstrap icon was unusable. Change to Font Awesome support.
        $('.datetimepicker').find('.prev').empty()
            .html('<i class="fa fa-angle-left"></i>');
        $('.datetimepicker').find('.next').empty()
            .html('<i class="fa fa-angle-right"></i>');

        $('#date-picker').trigger('click');
    });

    // 切换月份（上一月）
    $('#to-prev-month').on('click', function () {
        var dateElem = $('#date'),
            prevMonth = new Date(new Date(dateElem.val()).getFullYear(), new Date(dateElem.val()).getMonth() - 1, 1);
        dateElem.val(prevMonth.getFullYear() + '-' + (prevMonth.getMonth() + 1) + '-1');

        getEventList(USE_SESSION_NO);
    });

    // 切换月份（下一月）
    $('#to-next-month').on('click', function () {
        var dateElem = $('#date'),
            nextMonth = new Date( new Date(dateElem.val()).getFullYear(),  new Date(dateElem.val()).getMonth() + 1, 1);
        dateElem.val(nextMonth.getFullYear() + '-' + (nextMonth.getMonth() + 1) + '-1');

        getEventList(USE_SESSION_NO);
    });

    // 切换月份（当前月）
    $('#to-today').on('click', function () {
        setDateToNow();
        getEventList(USE_SESSION_NO);
    });

    // 点击事件显示详细内容
    $('td').on('click', '.event-div', function () {
        $(this).addClass('shadow');

        if ($(this).attr('class').indexOf('event-period') !== -1) {
            var eventId = $(this).attr('eventId');
            $('.event-div[eventId=' + eventId + ']').addClass('shadow');
        }
        showEventDetail(this);
    });

    // 点击X关闭事件详细模态框
    $('#event-close').on('click', function () {
        $('#eventInfoModal').modal('hide');
    });

    // 模态框关闭后取消选中阴影效果
    $('#eventInfoModal, #eventMoreModal').on('hidden.bs.modal', function () {
        $('.shadow').removeClass('shadow');
    });

    // 点击更多显示事件列表
    $('td').on('click', '.event-more', function () {
        $(this).addClass('shadow');
        showMoreEvent(this);
    });

    // 点击X关闭事件列表模态框
    $('#more-close').on('click', function () {
        $('#eventMoreModal').modal('hide');
    });

    // 点击logo时重新获取事件列表
    $('.logo').find('strong').on('click', function () {
        getEventList(USE_SESSION_NO);
    });

    // 绑定所有提示工具
    $('[data-toggle="tooltip"]').tooltip();
});

// 毫秒/天
const MS_OF_DAY = 1000 * 60 * 60 * 24;
// 最大天数
const MAX_DAY = 6 * 7;
// 页面初始化
const USE_SESSION_YES = 1;
// 非页面初始化
const USE_SESSION_NO = 0;

// 设置页面日期
function setDate() {
    // 定义参数
    var now = new Date(),
        targetDate = new Date($('#date').val());

    // 设置导航栏日期标签
    $('#now-date').find('span').text(targetDate.getFullYear() + '年' + (targetDate.getMonth()+ 1)  + '月');

    // 移除今日标识
    $('.badge').removeClass('badge');
    $('.today-td').removeClass('today-td');

    // 循环设置日期
    var firstDayOfWeek = new Date(targetDate.setDate(1)).getDay(),
        lengthOfMonth = getMonthLength(targetDate),
        lengthOfPrevMonth = getPrevMonthLength(targetDate),
        rollingDate = 1,
        isMonthRolling = false,
        isThisMonth = checkIsSameMonth(targetDate, now);

    if (firstDayOfWeek > 0) {
        rollingDate = lengthOfPrevMonth - firstDayOfWeek + 1;
        isMonthRolling = true;
    }
    for (var i = 1; i < 7; i++) {
        var targetTD = $('#line-' + i).find('.sun');

        for (var j = 0; j < 7; j++) {
            if (i === 1 && isMonthRolling && rollingDate > lengthOfPrevMonth) {
                rollingDate = 1;
                isMonthRolling = false;
            }

            if (i > 1 && !isMonthRolling && rollingDate > lengthOfMonth) {
                rollingDate = 1;
                isMonthRolling = true;
            }

            if (!isMonthRolling && isThisMonth && rollingDate === now.getDate()) {
                targetTD.addClass('today-td');
                targetTD.find('.date-line').addClass('badge');
            }

            if (rollingDate === 1) {
                if (!isMonthRolling) {
                    targetTD.find('.date-line').text(targetDate.getMonth() + 1 + '月1日');
                } else {
                    var month = (targetDate.getMonth() + 2) === 13 ? 1 : (targetDate.getMonth() + 2);
                    targetTD.find('.date-line').text( month + '月1日');
                }
            } else {
                targetTD.find('.date-line').text(rollingDate);
            }

            targetTD = targetTD.next();
            rollingDate++;
        }
    }
}

// 获取事件列表
function getEventList(isUseSession) {
    var date = new Date($('#date').val()),
        params = {
        url : 'getEventList.html',
        data : {
            year : date.getFullYear(),
            month : date.getMonth() + 1,
            isUseSession : isUseSession
        },
        beforeSend : function () {
            // 显示提示框
            $('#get-event-alert').slideToggle();
        },
        success : function (jsonObj) {
            // 处理返回值
            if (jsonObj['type'] === 'success') {
                var data = $.parseJSON(jsonObj['data']);
                // 更改日期
                $('#date').val(data['year'] + '-' + data['month'] + '-1');
                setDate();
                // 删除原有事件
                $('.event-remove').remove();
                // 隐藏提示框
                $('#get-event-alert').slideToggle();
                // 设置事件
                if (!$.isEmptyObject(data)) {
                    setEvent(data['eventList']);
                }
            } else if (jsonObj['type'] === 'error') {
                // 跳转至错误页面
                doDynamicFormSubmit({'action' : jsonObj['url']});
            }
        }
    };
    // 执行AJAX
    doAjax(params);
}

// 设置事件
function setEvent(eventList) {
    var date = new Date($('#date').val()),
        firstDay = new Date(date - (MS_OF_DAY * date.getDay())),
        lastDay = new Date(firstDay.getTime() + ((MAX_DAY - 1) * MS_OF_DAY));

    // 期间（优先显示）
    $.each(eventList ,function () {
        var item = this;

        if (typeof item['eventEndDate'] !== 'undefined') {
            var eventStartDate = new Date(item['eventStartDate']),
                eventEndDate = new Date(item['eventEndDate']);

            if (firstDay > eventStartDate) {
                eventStartDate = firstDay;
            }
            if (eventEndDate > lastDay) {
                eventEndDate = lastDay;
            }
            eventStartDate.setHours(0);
            eventEndDate.setHours(0);

            var intervalDayStart = Math.ceil((eventStartDate - firstDay) / MS_OF_DAY),
                intervalDayBTW = (eventEndDate - eventStartDate) / MS_OF_DAY,
                lineNoStart = Math.floor(intervalDayStart / 7) + 1,
                lineNoEnd = Math.floor(intervalDayBTW / 7) + lineNoStart,
                classNameStart = getClassByDayOfWeek(intervalDayStart % 7),
                weekInterval = (intervalDayStart % 7) + (intervalDayBTW % 7),
                endDayOfWeek = weekInterval > 6 ? (weekInterval % 6) - 1 : weekInterval,
                classNameEnd = getClassByDayOfWeek(endDayOfWeek),
                startTD = $('#line-' + lineNoStart).find(classNameStart),
                endTD = $('#line-' + lineNoEnd).find(classNameEnd);

            addEventOfPeriod(startTD, endTD, item);
        }
    });

    // 单日
    $.each(eventList ,function () {
        var item = this;

        if (typeof item['eventEndDate'] === 'undefined') {
            var eventStartDate = new Date(item['eventStartDate']),
                intervalDayStart = Math.ceil((eventStartDate - firstDay) / MS_OF_DAY),
                lineNo = Math.floor(intervalDayStart / 7) + 1,
                className = getClassByDayOfWeek(intervalDayStart % 7),
                targetTD = $('#line-' + lineNo).find(className);

            if (targetTD.find('.event-div').length < 3) {
                targetTD.append('<div class="event-div event-remove" eventId="' + item['eventId'] + '">' +
                    '<div class="event-sign"></div><span class="event-info text-hidden"></span></div>');

                var eventDiv = targetTD.find('[eventId=' + item['eventId'] + ']');
                addEventInfo(eventDiv, item);
                eventDiv.find('.event-sign').css('background-color', item['eventColor']);
            } else {
                // 不显示超出部分的事件，改为显示剩余数量
                var eventMore = targetTD.find('.event-more');
                if (eventMore.length === 0) {
                    targetTD.append('<div class="event-more event-remove"><span>剩余 1 项</span></div>');
                    eventMore = targetTD.find('.event-more');
                } else {
                    var eventMoreText = eventMore.find('span').text();
                    eventMore.find('span').text('剩余 ' + (Number(eventMoreText.split(' ')[1]) + 1) + ' 项');
                }
                eventMore.append('<div class="event-hidden" id="' + item['eventId'] + '">'
                    + JSON.stringify(item) + '</div>');
            }
        }
    });
}

// 添加事件（期间）
function addEventOfPeriod(startTD, endTD, eventItem) {
    var isEnd = false,
        startLineNo = Number(startTD.parents('tr').attr('lineNo')),
        endLineNo = Number(endTD.parents('tr').attr('lineNo'));

    endTD.attr('isEnd', '1');

    for (var i = startLineNo; i <= endLineNo; i++) {
        if (isEnd) return;

        var targetTD = i === startLineNo ? startTD : $('#line-' + i).find('.sun'),
            ergodicTD = targetTD,
            rangeCoef = 1;

        targetTD.append('<div class="event-div event-period event-remove" eventId="' + eventItem['eventId'] + '">' +
            '<span class="event-info"></span></div>');

        for (var j = 0; j < MAX_DAY; j++) {
            if (ergodicTD.attr('isEnd') === '1') {
                isEnd = true;
                break;
            }

            if (j > 0) {
                ergodicTD.append('<div class="event-div event-div-tmp event-remove"></div>');
            }
            if (ergodicTD.attr('class').indexOf('sat') !== -1) break;

            ergodicTD = ergodicTD.next();
            rangeCoef++;
        }
        var eventDiv = targetTD.find('[eventId=' + eventItem['eventId'] + ']');
        addEventInfo(eventDiv, eventItem);
        eventDiv.attr('rangeCoef', rangeCoef);
        eventDiv.width(rangeCoef * ($(document.body).width() / 7) - 12);
        eventDiv.css('background-color', eventItem['eventColor']);
    }
}

// 添加事件信息
function addEventInfo(eventDiv, eventItem) {
    if (typeof eventItem['eventStartTime'] !== 'undefined') {
        var startTime = new Date(eventItem['eventStartTime']);
        eventDiv.find('.event-info').text(formatToTimeStr(startTime) + ' ' + eventItem['eventTitle']);
    } else {
        eventDiv.find('.event-info').text(eventItem['eventTitle']);
    }
    eventDiv.append('<div class="event-hidden" id="' + eventItem['eventId'] + '">'
        + JSON.stringify(eventItem) + '</div>');
}

// 重置event div宽度
function resizeEventDiv() {
    $.each($('.event-period'), function () {
        $(this).width(Number($(this).attr('rangeCoef')) * ($(document.body).width() / 7) - 12);
    });
}

// 显示事件详细信息
function showEventDetail(elem) {
    var eventInfoStr = $('#' + $(elem).attr("eventId")).text(),
        eventInfo = $.parseJSON(eventInfoStr);

    var periodDIV = $('#period-c'),
        startDate = new Date(eventInfo['eventStartDate']);

    if (typeof eventInfo['eventEndDate'] === 'undefined') {
        // 单日
        periodDIV.html('<span id="event-date"></span>');
        periodDIV.append('<div><span id="event-time"></span></div>');
        $('#event-date').text(startDate.getFullYear() + '年 '
            + (startDate.getMonth() + 1) + '月 ' + startDate.getDate() + '日（'
            + getNameByDayOfWeek(startDate.getDay()) + '）');

        if (typeof eventInfo['eventStartTime'] !== 'undefined') {
            var startTime = new Date(eventInfo['eventStartTime']),
                endTime = new Date(eventInfo['eventEndTime']);
            $('#event-time').text(formatToTimeStr(startTime) + ' ~ ' + formatToTimeStr(endTime));
        } else {
            $('#event-time').text('全天');
        }
        $('#period-i').css('top', '-25%');
    } else {
        // 期间
        periodDIV.html('<span id="event-date-start"></span>');
        periodDIV.append('<div><span id="event-date-end"></span></div>');

        // 设置日期信息
        var endDate = new Date(eventInfo['eventEndDate']),
            startDateStr = '开始：' + startDate.getFullYear() + '年 '
            + (startDate.getMonth() + 1) + '月 ' + startDate.getDate() + '日（'
            + getNameByDayOfWeek(startDate.getDay()) + '）',
            endDateStr = '结束：' + endDate.getFullYear() + '年 '
                + (endDate.getMonth() + 1) + '月 ' + endDate.getDate() + '日（'
                + getNameByDayOfWeek(endDate.getDay()) + '）';

        if (typeof eventInfo['eventStartTime'] !== 'undefined') {
            var startTime = new Date(eventInfo['eventStartTime']),
                endTime = new Date(eventInfo['eventEndTime']);
            startDateStr = startDateStr + ', ' + formatToTimeStr(startTime);
            endDateStr = endDateStr + ', ' + formatToTimeStr(endTime);
        } else {
            startDateStr = startDateStr;
        }
        $('#period-i').css('top', '-30%');

        $('#event-date-start').text(startDateStr);
        $('#event-date-end').text(endDateStr);
    }

    // 设置其他信息
    $('#event-title').text(eventInfo['eventTitle']);
    $('#event-desc').text(eventInfo['eventContent']);
    $('#event-owner').text(eventInfo['eventOwnerName']);

    $('#event-modal-header').css('background-color', eventInfo['eventColor']);
    // 显示模态框
    $('#eventInfoModal').modal('show');
}

// 显示更多事件
function showMoreEvent(elem) {
    var listDiv = $('#more-event');
    listDiv.html($(elem).parent().html());
    listDiv.find('.event-more, .date-line').remove();

    // 添加日期
    var moreEventList = $(elem).find('.event-hidden'),
        eventInfo = $.parseJSON(moreEventList.first().text()),
        date = new Date(eventInfo['eventStartDate']);
    $('#more-date').html('<span id="more-day">' + date.getDate() + '</span>')
        .append('<span id="more-weekday">' + getNameByDayOfWeek(date.getDay()) + '</span>');

    // 添加事件列表
    $.each(moreEventList, function () {
        var item = $.parseJSON($(this).text());

        listDiv.append('<div class="event-div event-remove" eventId="' + item['eventId'] + '">' +
            '<div class="event-sign"></div><span class="event-info text-hidden"></span></div>');
        var eventDiv = listDiv.find('[eventId=' + item['eventId'] + ']');
        addEventInfo(eventDiv, item);
        eventDiv.find('.event-sign').css('background-color', item['eventColor']);
    })
    listDiv.find('.event-div').addClass('event-more-div').removeClass('event-remove');

    // 显示模态框
    $('#eventMoreModal').modal('show');
}

// 根据星期数（0-6）返回对应class名
function getClassByDayOfWeek(dayOfWeek) {
    switch (dayOfWeek) {
        case 0:
            return '.sun';
        case 1:
           return '.mon';
        case 2:
           return '.tues';
        case 3:
           return '.wed';
        case 4:
           return '.thur';
        case 5:
           return '.fri';
        case 6:
           return '.sat';
    }
}

// 根据星期数（0-6）返回对应星期名
function getNameByDayOfWeek(dayOfWeek) {
    switch (dayOfWeek) {
        case 0:
            return '星期日';
        case 1:
            return '星期一';
        case 2:
            return '星期二';
        case 3:
            return '星期三';
        case 4:
            return '星期四';
        case 5:
            return '星期五';
        case 6:
            return '星期六';
    }
}

// 将日期设置为当前月份
function setDateToNow() {
    var now = new Date();
    $('#date').val(now.getFullYear() + '-' + (now.getMonth() + 1) + '-1');
}

// 获取当月天数
function getMonthLength(targetDate) {
    var firstDayOfNextMonth = new Date(targetDate.getFullYear(), targetDate.getMonth() + 1, 1);
    return new Date(firstDayOfNextMonth - MS_OF_DAY).getDate();
}

// 获取前月天数
function getPrevMonthLength(targetDate) {
    return new Date(targetDate.setDate(1) - MS_OF_DAY).getDate();
}

// 判断是否为同年同月的日期
function checkIsSameMonth(targetDate, now) {
    return (targetDate.getFullYear() === now.getFullYear() && (targetDate.getMonth() === now.getMonth()));
}

// 将Date格式化为HH:mm形式的字符串
function formatToTimeStr(time) {
    var hours = time.getHours() < 10 ? '0' + time.getHours() : time.getHours(),
        minutes = time.getMinutes() < 10 ? '0' + time.getMinutes() : time.getMinutes();
    return hours + ':' + minutes;
}