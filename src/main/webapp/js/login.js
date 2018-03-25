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
    var url = 'login.do',
        data = JSON.stringify({
            userName : $('#userName').val(),
            password : $('#password').val(),
            rememberMe : $('#rememberCheck').prop('checked') ? 1 : 0
        });
    
    $.ajax({
        url : url,
        type : 'POST',
        data : data,
        dataType : 'JSON',
        contentType : 'application/json;charset=utf8',
        beforeSend : function () {
            $('#loginBtn').html('<i class="fa fa-spinner fa-pulse"></i>');
        },
        success : function (jsonStr) {
            var jsonObj = $.parseJSON(jsonStr);
            
            if (jsonObj['type'] === 'success') {
                window.location.href = jsonObj['url'];
            } else if (jsonObj['type'] === 'message') {
                alert(jsonObj['message']);
                $('#loginBtn').html('登&nbsp;录');
            } else if (jsonObj['type'] === 'error') {
                window.location.href = jsonObj['url'];
            }
        }
    });
    
    // TODO 模块化实现
    //doAjax(JSON.stringify({url : url, data : data, beforeSend : beforeSend(), success : success()}));
}

// 输入验证
function checkInput () {
    // 用户名空值验证
    if (!$('#userName').val()) {
        alert("请输入用户名。");
        return false;
    }
    // 密码空值验证
    if (!$('#password').val()) {
        alert("请输入密码。");
        return false;
    }
    // 密码长度验证
    if ($('#password').val().length > 12) {
        alert("请输入12位以内的密码。");
        return false;
    }
    return true;
}