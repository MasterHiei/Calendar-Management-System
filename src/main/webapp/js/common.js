// Ajax调用模块
function doAjax (params) {
    // 定义Ajax属性
    var ajaxParams = {
        type : 'POST',
        contentType : 'application/json;charset=utf8',
        dataType : 'JSON',
        error : function () {
            doDynamicFormSubmit({'action' : '404.html'});
        }
    };
    // 动态添加Ajax属性
    for (var key in params) {
        if (key === 'data') {
            ajaxParams[key] = JSON.stringify(params[key]);
        } else {
            ajaxParams[key] = params[key];
        }
    }
    // 执行Ajax
    $.ajax(ajaxParams);
}

// 动态生成表单并提交
function doDynamicFormSubmit (params) {
    // 如果表单存在则删除表单
    removeElem($('#dynamicForm'));
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

// 动态生成模态框
function doDynamicModalShow(params) {
    // 添加模态框要素
    for (var key in params) {
        switch (key) {
            case 'header':
                $('.modal-title').html(params[key]);
                break;
            case 'body':
                $('.modal-body').html(params[key]);
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

// 删除指定元素
function removeElem(obj) {
    if ($(obj).length > 1) {
        $(obj).remove();
    }
}