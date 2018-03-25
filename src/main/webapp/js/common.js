// Ajax调用模块
function doAjax (params) {
    var url = '',
        data = '',
        beforeSend = '',
        success = '';
    
    for (var key in params) {
        if (key === 'url') {
            url = params[key];
        }
        if (key === 'data') {
            data = params[key];
        }
        if (key === 'beforeSend') {
            beforeSend = params[key];
        }
        if (key === 'success') {
            success = params[key];
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