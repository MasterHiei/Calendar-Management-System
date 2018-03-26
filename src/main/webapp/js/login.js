$(function () {
    'use strict';
    
    // 用户登录
    $('#loginBtn').on('click', function () {
        if (checkInput()) {
            doLogin();
        }
    })
});

// 登录处理
function doLogin () {
    var params = {
        url : 'login.do',
        data : JSON.stringify({
            userName : $('#userName').val(),
            password : $('#password').val(),
            rememberMe : $('#rememberCheck').prop('checked') ? 1 : 0
        }),
        beforeSend : function () {
            $('#loginBtn').html('<i class="fa fa-spinner fa-pulse"></i>');
        },
        success : function (jsonStr) {
            var jsonObj = $.parseJSON(jsonStr);

            if (jsonObj['type'] === 'success') {
                doDynamicFormSubmit({'action' : jsonObj['url'], 'mode' : 'init'});
            } else if (jsonObj['type'] === 'message') {
                // TODO Bootstrap警告框
                alert(jsonObj['message']);
                // 恢复按钮状态
                $('#loginBtn').html('登&nbsp;录');
            } else if (jsonObj['type'] === 'error') {
                doDynamicFormSubmit({'action' : jsonObj['url'], 'mode' : 'init'});
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
        // TODO Bootstrap警告框
        alert("请输入用户名。");
        return false;
    }
    // 密码空值验证
    if (!$('#password').val()) {
        // TODO Bootstrap警告框
        alert("请输入密码。");
        return false;
    }
    // 密码长度验证
    if ($('#password').val().length > 16) {
        // TODO Bootstrap警告框
        alert("请输入16位以内的密码。");
        return false;
    }
    return true;
}