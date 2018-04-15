$(function () {
   'use strict';
   
    $(document).ready(function () {
        // 获取参数
        var params = {
            targetYear : Number($('#targetYear').val()),
            targetMonth : Number($('#targetMonth').val()),
            targetDay : Number($('#targetDay').val()),
            firstDayOfWeek : Number($('#firstDayOfWeek').val()),
            lengthOfMonth : Number($('#lengthOfMonth').val()),
            lengthOfPrevMonth : Number($('#lengthOfPrevMonth').val()),
            isToday : $('#isToday').val()
        };
        // 设置页面日期
        setDate(params);
    });
    
    // 切换月份
    $('#prev-month').on('click', function () {
        doDateChange('prev');
    });
    
});

// 设置页面日期
function setDate(params) {
    // 获取参数
    var targetYear = params['targetYear'],
        targetMonth = params['targetMonth'],
        targetDay = params['targetDay'],
        firstDayOfWeek = params['firstDayOfWeek'],
        lengthOfMonth = params['lengthOfMonth'],
        lengthOfPrevMonth = params['lengthOfPrevMonth'],
        isToday = params['isToday'];
    
    // 显示当前日期
    $('#now-date').text(targetYear + '年' + targetMonth + '月' + targetDay + '日');
    
    // 获取月初星期数
    var dayOfWeek = '';
    switch (firstDayOfWeek) {
        case 1:
            dayOfWeek = '.mon';
            break;
        case 2:
            dayOfWeek = '.tues';
            break;
        case 3:
            dayOfWeek = '.wed';
            break;
        case 4:
            dayOfWeek = '.thur';
            break;
        case 5:
            dayOfWeek = '.fri';
            break;
        case 6:
            dayOfWeek = '.sat';
            break;
        case 7:
            dayOfWeek = '.sun';
            break;
    }
    
    var day = 1;
    // 设置月初日期
    var firstDayTD = $('tr[lineNo=1]').find(dayOfWeek);
    firstDayTD.find('.date-line').text(day);

    // 设置第一周的日期
    var prevDayTD = firstDayTD;
    for (var i = firstDayOfWeek; i > 1; i--) {
        prevDayTD = prevDayTD.prev();
        prevDayTD.find('.date-line').text(lengthOfPrevMonth);
        lengthOfPrevMonth--;
    }
    var nextDayTD = firstDayTD;
    for (var j = firstDayOfWeek === 7 ? 1 : firstDayOfWeek; j < 7; j++) {
        day++;
        nextDayTD = nextDayTD.next();
        nextDayTD.find('.date-line').text(day);
    }
    
    // 设置剩余周的日期
    for (var k = 2; k < 7; k++) {
        var targetTD = $('tr[lineNo=' + k + ']').find(dayOfWeek);
        for (var l = 0; l < 7; l++) {
            var isMonthRolling = false;
            if (day < lengthOfMonth) {
                day++;
            } else {
                day = 1;
                isMonthRolling = true;
            }
            targetTD.find('.date-line').text(day);
            
            if (isToday === '1' && !isMonthRolling && day === targetDay) {
                targetTD.find('.date-line').addClass('badge');
            }
            
            targetTD = targetTD.next();
        }
    }
}

// 切换日期显示
function doDateChange(mode) {
    var params = {
        url : 'doDateChange.html',
        date : JSON.stringify({
            targetYear : $('#targetYear').val(),
            targetMonth : $('#targetMonth').val(),
            targetDay : $('#targetDay').val(),
            mode : mode
        }),
        success : function (jsonObj) {
            // 处理返回值
            if (jsonObj['type'] === 'success') {
                // 获取参数
                var params = {
                    targetYear : jsonObj['targetYear'],
                    targetMonth : jsonObj['targetMonth'],
                    targetDay : jsonObj['targetDay'],
                    firstDayOfWeek : jsonObj['firstDayOfWeek'],
                    lengthOfMonth : jsonObj['lengthOfMonth'],
                    lengthOfPrevMonth : jsonObj['lengthOfPrevMonth'],
                    isToday : jsonObj['isToday']
                };
                // 设置日期
                setDate(params);
            } else if (jsonObj['type'] === 'error') {
                // 跳转至错误页面
                doDynamicFormSubmit({'action' : jsonObj['url']});
            }
        }
    };
    // 执行AJAX
    doAjax(params);
}