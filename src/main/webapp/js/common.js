// Ajax调用模块
function doAjax (params) {
    // 定义Ajax属性
    var ajaxParams = {
        contentType : 'application/json;charset=utf8',
        dataType : 'JSON',
        error : function () {
            doDynamicFormSubmit({'action' : '404.html'});
        }
    };
    // 动态添加Ajax属性
    if (typeof ajaxParams['type'] === 'undefined') {
        ajaxParams['type'] = 'POST';
    }
    for (var key in params) {
        if (key === 'data' && ajaxParams['type'] === 'POST') {
            ajaxParams[key] = JSON.stringify(params[key]);
        } else {
            ajaxParams[key] = params[key];
        }
    }
    // 执行Ajax
    $.ajax(ajaxParams);
}

// 动态生成表单并提交（默认method=get，可指定为其他提交方式）
function doDynamicFormSubmit (params) {
    var dynamicForm = $('#dynamicForm');
    // 如果表单存在则删除表单
    dynamicForm.remove();
    // 创建表单
    $(document.body).append('<form id="dynamicForm" method="POST"></form>');
    // 添加表单元素
    for (var key in params) {
        switch (key) {
            case 'action':
                dynamicForm.attr('action', params[key]);
                break;
            case 'method':
                dynamicForm.attr('method', params['method']);
                break;
            default :
                dynamicForm.append('<input type="hidden" name="' + key + '" value="'+ params[key] +'" />');
                break;
        }
    }
    // 提交表单
    dynamicForm.submit();
}

// 动态生成模态框
function doDynamicModalShow(params) {
    // 添加模态框要素
    for (var key in params) {
        switch (key) {
            case 'header':
                $('.modal-title').text(params[key]);
                break;
            case 'body':
                $('.modal-body').text(params[key]);
                break;
            case 'footer':
                if (params[key] === 'alert') {
                    $('#modalConfirmBtn').removeClass('pull-left');
                    $('#modalCancelBtn').hide();
                } else if (params[key] === 'confirm') {
                    $('#modalConfirmBtn').addClass('pull-left');
                    $('#modalCancelBtn').show();
                }
                break;
            case 'confirmFunc':
                $('#modalConfirmBtn').on('click', params[key]);
                break;
            case 'cancelFun':
                $('#modalCancelBtn').on('click', params[key]);
                break;
        }
    }
    // 显示模态框
    $('#dynamicModal').modal('show');
}

// 提示信息用模态框
function doAlertModalShow(code) {
    // 显示提示信息
    var params = {
        header : '提示',
        body : message[code],
        footer : 'alert',
        confirmFunc : function () {
            $('#dynamicModal').modal('hide');
        }
    };
    // 动态生成模态框
    doDynamicModalShow(params);
}