$(function () {
   'use strict';
   
    $(document).ready(function () {
        setDateToNow();
        setDate();
        getEventList();

        window.addEventListener('resize', function () {
            resizeEventDiv();
        })
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

    // 点击logo时重新获取事件
    $('.logo > strong').on('click', function () {
        getEventList();
    })
    
});

// 毫秒/天
const msOfDay = 1000 * 60 * 60 * 24;
// 最大天数
const maxDay = 6 * 7;

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
    // 删除原有事件
    $('.event-div').remove();
    // 执行AJAX
    doAjax(params);
}

// 设置事件
function setEvent(eventList) {
    var date = new Date($('#date').val()),
        firstDay = new Date(date - (msOfDay * date.getDay())),
        lastDay = new Date(firstDay.getTime() + ((maxDay - 1) * msOfDay));
    
    console.log(eventList);

    // 期间（优先显示）
    eventList.forEach(function (item) {
        if (typeof item['eventEndDate'] !== 'undefined') {
            var eventStartDate = new Date(item['eventStartDate']),
                eventEndDate = new Date(item['eventEndDate']);

            if (firstDay > eventStartDate) {
                eventStartDate = firstDay;
            }
            if (eventEndDate > lastDay) {
                eventEndDate = lastDay;
            }

            var intervalDayStart = Math.ceil((eventStartDate - firstDay) / msOfDay),
                intervalDayBTW = (eventEndDate - eventStartDate) / msOfDay,
                lineNoStart = Math.floor(intervalDayStart / 7) + 1,
                lineNoEnd = Math.floor(intervalDayBTW / 7) + lineNoStart,
                classNameStart = getClassByDayOfWeek(intervalDayStart % 7),
                weekInterval = (intervalDayStart % 7) + (intervalDayBTW % 7),
                endDayOfWeek = weekInterval > 6 ? (weekInterval % 6) - 1 : weekInterval,
                classNameEnd = getClassByDayOfWeek(endDayOfWeek),
                startTD = $('tr[lineNo=' + lineNoStart + ']').find(classNameStart),
                endTD = $('tr[lineNo=' + lineNoEnd + ']').find(classNameEnd);

            addEventOfPeriod(startTD, endTD, item);
        }
    });

    // 单日
    eventList.forEach(function (item) {
        if (typeof item['eventEndDate'] === 'undefined') {
            var eventStartDate = new Date(item['eventStartDate']),
                intervalDayStart = Math.ceil((eventStartDate - firstDay) / msOfDay),
                lineNo = Math.floor(intervalDayStart / 7) + 1,
                className = getClassByDayOfWeek(intervalDayStart % 7),
                targetTD = $('tr[lineNo=' + lineNo + ']').find(className);

            if (targetTD.find('.event-div').length < 3) {
                targetTD.append('<div class="event-div" eventId="' + item['eventId'] + '">' +
                    '<div class="event-sign"></div><span class="event-info"></span></div>');

                var eventDiv = targetTD.find('[eventId=' + item['eventId'] + ']');
                addEventInfo(eventDiv, item);
                eventDiv.find('.event-sign').css('background-color', item['eventColor']);
            } else {
                // 不显示超出部分的事件，改为显示剩余数量
                var eventMore = targetTD.find('.event-more');
                if (eventMore.length === 0) {
                    targetTD.append('<div class="event-more"><span>剩余 1 项</span></div>');
                } else {
                    var eventMoreText = eventMore.find('span').text();
                    eventMore.find('span').text('剩余 ' + (Number(eventMoreText.split(' ')[1]) + 1) + ' 项');
                }
            }
            targetTD.append('<div class="event-hidden">' + JSON.stringify(item) + '</div>');
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

        var targetTD = i === startLineNo ? startTD : $('tr[lineNo=' + i + ']').find('.sun'),
            ergodicTD = targetTD,
            rangeCoef = 1;

        targetTD.append('<div class="event-div event-period" eventId="' + eventItem['eventId'] + '">' +
            '<span class="event-info"></span></div>');

        for (var j = 0; j < maxDay; j++) {
            if (ergodicTD.attr('isEnd') === '1') {
                isEnd = true;
                break;
            }

            ergodicTD.append('<div class="event-div event-div-tmp"></div>');
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
        var startTime = new Date(eventItem['eventStartTime']),
            startHours = startTime.getHours() < 10 ? '0' + startTime.getHours() : startTime.getHours(),
            startMinutes = startTime.getMinutes() < 10 ? '0' + startTime.getMinutes() : startTime.getMinutes();

        eventDiv.find('.event-info').text(startHours + ':' + startMinutes + ' ' + eventItem['eventTitle']);
    } else {
        eventDiv.find('.event-info').text(eventItem['eventTitle']);
    }
}

function resizeEventDiv() {
    $('.event-period').each(function () {
        $(this).width(Number($(this).attr('rangeCoef')) * ($(document.body).width() / 7) - 12);
    });
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
    return new Date(firstDayOfNextMonth - msOfDay).getDate();
}

// 获取前月天数
function getPrevMonthLength(targetDate) {
    return new Date(targetDate.setDate(1) - msOfDay).getDate();
}

// 判断是否为同年同月的日期
function checkIsSameMonth(targetDate, now) {
    return (targetDate.getFullYear() === now.getFullYear() && (targetDate.getMonth() === now.getMonth()));
}