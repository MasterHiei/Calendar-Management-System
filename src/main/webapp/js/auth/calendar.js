$(function () {
   'use strict';
   
    $(document).ready(function () {
        // 设置页面日期
        setDate();
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
            var targetDate = new Date(ev.date);
            $('#targetYear').val(targetDate.getFullYear());
            $('#targetMonth').val(targetDate.getMonth() + 1);
            doDateChange('jump');
        });
        $('#date-picker').trigger('click');
    });
    
    // 切换月份（上一月）
    $('#to-prev-month').on('click', function () {
        doDateChange('prev');
    });

    // 切换月份（下一月）
    $('#to-next-month').on('click', function () {
        doDateChange('next');
    });

    // 切换月份（当前月）
    $('#to-today').on('click', function () {
        doDateChange('today');
    });
    
});

// 设置页面日期
function setDate() {
    // 获取参数
    var targetYear = Number($('#targetYear').val()),
        targetMonth = Number($('#targetMonth').val()),
        targetDay = Number($('#targetDay').val()),
        firstDayOfWeek = Number($('#firstDayOfWeek').val()),
        lengthOfMonth = Number($('#lengthOfMonth').val()),
        lengthOfPrevMonth = Number($('#lengthOfPrevMonth').val()),
        isToday = Number($('#isToday').val());
    
    // 显示当前日期
    $('#now-date span').text(targetYear + '年' + targetMonth + '月');
    
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
    for (var i = firstDayOfWeek; i > 0; i--) {
        prevDayTD = prevDayTD.prev();
        prevDayTD.find('.date-line').text(lengthOfPrevMonth);
        lengthOfPrevMonth--;
    }
    var nextDayTD = firstDayTD;
    for (var j = firstDayOfWeek === 7 ? 0 : firstDayOfWeek; j < 6; j++) {
        day++;
        nextDayTD = nextDayTD.next();
        nextDayTD.find('.date-line').text(day);
    }
    
    // 设置剩余周的日期
    for (var k = 2; k < 7; k++) {
        var targetTD = $('tr[lineNo=' + k + ']').find('.sun');
        for (var l = 0; l < 7; l++) {
            var isMonthRolling = false;
            if (day < lengthOfMonth) {
                day++;
            } else {
                day = 1;
                isMonthRolling = true;
            }
            targetTD.find('.date-line').text(day);
            
            if (isToday === '1') {
                if (!isMonthRolling && day === targetDay) {
                    targetTD.find('.date-line').addClass('badge');
                }
            } else {
                $('.badge').removeClass('badge');
            }
            
            targetTD = targetTD.next();
        }
    }
}

// 切换日期显示
function doDateChange(mode) {
    var params = {
        url : 'doDateChange.html',
        data : {
            targetYear : $('#targetYear').val(),
            targetMonth : $('#targetMonth').val(),
            targetDay : '1',
            mode : mode
        },
        success : function (jsonObj) {
            // 处理返回值
            if (jsonObj['type'] === 'success') {
                // 获取参数
                var data = $.parseJSON(jsonObj['data']);
                $('#targetYear').val(data['targetYear']);
                $('#targetMonth').val(data['targetMonth']);
                $('#targetDay').val(data['targetDay']);
                $('#firstDayOfWeek').val(data['firstDayOfWeek']);
                $('#lengthOfMonth').val(data['lengthOfMonth']);
                $('#lengthOfPrevMonth').val(data['lengthOfPrevMonth']);
                $('#isToday').val(data['isToday']);
                // 设置日期
                setDate();
                // 关闭DateTimePicker
                if (mode === 'jump') {
                    $('#date-picker').datetimepicker('hide');
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