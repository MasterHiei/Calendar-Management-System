$(function () {
   'use strict';
   
    $(document).ready(function () {
        setDateToNow();
        setDate();
        getEventList();
    });
    
    // 显示日期选择控件
    $('#now-date').on('click', function () {
        $('#date-picker').datetimepicker({
            format : 'yyyy-mm-dd',
            startView : 'year',
            minView : 'year',
            maxView : 'decade',
            language : 'zh-CN'
        }).on('changeMonth', function (ev) {
            $('#date').val(ev.date);
            $('#date-picker').datetimepicker('hide');
            
            setDate();
            getEventList();
        });
        // ISSUE: Bootstrap icon was unusable. Change to Font Awesome support. 
        $('.datetimepicker th.prev').empty();
        $('.datetimepicker th.prev').html('<i class="fa fa-angle-left"></i>');
        $('.datetimepicker th.next').empty();
        $('.datetimepicker th.next').html('<i class="fa fa-angle-right"></i>');
        
        $('#date-picker').trigger('click');
    });
    
    // 切换月份（上一月）
    $('#to-prev-month').on('click', function () {
        var prevMonth = new Date(new Date($('#date').val()).getFullYear(), new Date($('#date').val()).getMonth() - 1, 1);
        $('#date').val(prevMonth.getFullYear() + '-' + (prevMonth.getMonth() + 1) + '-1');
        
        setDate();
        getEventList();
    });

    // 切换月份（下一月）
    $('#to-next-month').on('click', function () {
        var nextMonth = new Date( new Date($('#date').val()).getFullYear(),  new Date($('#date').val()).getMonth() + 1, 1);
        $('#date').val(nextMonth.getFullYear() + '-' + (nextMonth.getMonth() + 1) + '-1');
        
        setDate();
        getEventList(); 
    });

    // 切换月份（当前月）
    $('#to-today').on('click', function () {
        setDateToNow();
        
        setDate();
        getEventList();
    });
    
});

// 设置页面日期
function setDate() {
    // 定义参数
    var now = new Date(),
        targetDate = new Date($('#date').val()),
        firstDayOfWeek = new Date(targetDate.setDate(1)).getDay(),
        lengthOfMonth = getMonthLength(targetDate),
        lengthOfPrevMonth = getPrevMonthLength(targetDate),
        isThisMonth = checkIsSameMonth(targetDate, now);
    
    // 设置导航栏日期标签
    $('#now-date span').text(targetDate.getFullYear() + '年' + (targetDate.getMonth()+ 1)  + '月');

    // 移除今日标识
    $('.badge').removeClass('badge');
    $('.today-td').removeClass('today-td');
    
    // 循环设置日期
    var rollingDate = 1,
        isMonthRolling = false;
    
    if (firstDayOfWeek > 0) {
        rollingDate = lengthOfPrevMonth - firstDayOfWeek + 1;
        isMonthRolling = true;
    }
    for (var i = 1; i < 7; i++) {
        var targetTD = $('tr[lineNo=' + i + ']').find('.sun');
        
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

// 切换日期显示
function getEventList() {
    var params = {
        url : 'getEventList.html',
        data : {
            year : new Date($('#date').val()).getFullYear(),
            month : new Date($('#date').val()).getMonth() + 1
        },
        success : function (jsonObj) {
            // 处理返回值
            if (jsonObj['type'] === 'success') {
                // 设置事件
                var eventList = $.parseJSON(jsonObj['data']);
                if (!$.isEmptyObject(eventList)) {
                    setEvent(eventList['eventList']);
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
        msOfDay = 1000 * 60 * 60 * 24,
        firstDay = new Date(date - (msOfDay * date.getDay()));
    
    console.log(eventList);
    
    eventList.forEach(function (item) {
        if (typeof item['eventEndDate'] === 'undefined') {
            // 单日
            var eventStartDate = new Date(item['eventStartDate']),
                intervalDay = (eventStartDate - firstDay) / msOfDay,
                lineNo = Math.floor(intervalDay / 7) + 1,
                className = getClassByDayOfWeek(intervalDay % 7),
                targetTD = $('tr[lineNo=' + lineNo + ']').find(className);

            addEventOfSingle(targetTD, item);
        } else {
            // 期间
        }
    });
}

// 显示事件
function addEventOfSingle(targetTD, eventItem) {
    targetTD.append('<div class="event-div" id="' + eventItem['eventId'] + '"><div class="event-sign"></div><span class="event-info"></span></div>');
    var targetElem = targetTD.find('#' + eventItem['eventId']);

    targetElem.find(' .event-sign').css('background-color', eventItem['eventColor']);
    
    var startTime = new Date(eventItem['eventStartTime']),
        startHours = startTime.getHours() < 10 ? '0' + startTime.getHours() : startTime.getHours(),
        startMinutes = startTime.getMinutes() < 10 ? '0' + startTime.getMinutes() : startTime.getMinutes();

    targetElem.find('.event-info').text(startHours + ':' + startMinutes + ' ' + eventItem['eventTitle']);
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
        default:
            return '';
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
    return new Date(firstDayOfNextMonth - (1000 * 60 * 60 * 24)).getDate();
}

// 获取前月天数
function getPrevMonthLength(targetDate) {
    return new Date(targetDate.setDate(1) - (1000 * 60 * 60 * 24)).getDate();
}

// 判断是否为同年同月的日期
function checkIsSameMonth(targetDate, now) {
    return (targetDate.getFullYear() === now.getFullYear() && (targetDate.getMonth() === now.getMonth()));
}