$(function () {
    'use strict';
    
    // 用户登录
    $('#loginBtn').on('click', function () {
        if (checkInput()) {
            doLogin();
        }
    });
    
    // 用户注册
    $('#registerBtn').on('click', function () {
        var params = {
            header : '公告',
            body : '用户注册功能将于系统正式上线后开放',
            footer : 'alert',
            confirmFunc : function () {
                $('#dynamicModal').modal('hide');
            }
        };
        // 动态生成模态框
        doDynamicModalShow(params);
    });
});

// 登录处理
function doLogin () {
    var params = {
        url : 'doLogin.html',
        data : {
            userName : $('#userName').val(),
            password : $('#password').val(),
            rememberMe : $('#rememberCheck').prop('checked') ? 1 : null
        },
        beforeSend : function () {
            $('#loginBtn').attr('disabled', true);
            $('#loginBtn').html('<i class="fa fa-spinner fa-pulse"></i>');
        },
        success : function (jsonObj) {
            // 处理返回值
            if (jsonObj['type'] === 'transition') {
                // 跳转至目标页面
                doDynamicFormSubmit({'action' : jsonObj['url']});
            } else if (jsonObj['type'] === 'message') {
                // 显示提示信息
                doAlertModalShow(jsonObj['code']);
                // 恢复按钮状态
                $('#loginBtn').html('登&nbsp;录');
                $('#loginBtn').attr('disabled', false);
            } else if (jsonObj['type'] === 'error') {
                // 跳转至错误页面
                doDynamicFormSubmit({'action' : jsonObj['url']});
            }
        }
    };
    // 执行AJAX
    doAjax(params);
}

// 输入验证
function checkInput () {
    // 用户名空值验证
    if (!$('#userName').val()) {
        // 显示提示信息
        doAlertModalShow('E001-0001');
        return false;
    }
    // 密码空值验证
    if (!$('#password').val()) {
        // 显示提示信息
        doAlertModalShow('E001-0002');
        return false;
    }
    // 密码长度验证
    if ($('#password').val().length > 16 || $('#password').val().length < 6) {
        // 显示提示信息
        doAlertModalShow('E001-0003');
        return false;
    }
    return true;
}