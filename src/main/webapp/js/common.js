// Ajax调用模块
function doAjax (params) {
    'use strict';
    
    var url = '',
        data = '',
        beforeSend = '',
        success = '';
    
    for (var key in params) {
        switch (key) {
            case 'url':
                url = params[key];
                break;
            case 'data':
                data = params[key];
                break;
            case 'beforeSend':
                beforeSend = params[key];
                break;
            case 'success':
                success = params[key];
                break;
            default:
                break;
        }
    }
    
    $.ajax({
        url : url,
        type : 'POST',
        data : data,
        dataType : 'JSON',
        contentType : 'application/json;charset=utf8',
        beforeSend : beforeSend,
        success : success
    })
}

// 动态生成表单并提交
function doDynamicFormSubmit (params) {
    'use strict';
    
    // 如果表单存在则删除表单
    if ($('#dynamicForm').length > 1) {
        $('#dynamicForm').remove();
    }
    // 创建表单
    $(document.body).append('<form id="dynamicForm" method="post"></form>');
    // 添加表单元素
    for (var key in params) {
        switch (key) {
            case 'action':
                $('#dynamicForm').attr('action', params[key]);
                break;
            default :
                $('#dynamicForm').append('<input type="hidden" name="' + key + '" value="'+ params[key] +'" />');
                break;
        }
    }
    // 提交表单
    $('#dynamicForm').submit();
}