function baseAjax(type, url, options, success, failure) {
    if (typeof options === 'function') {
        failure = success;
        success = options;
        options = undefined;
    }

    options = options || {};

    $.ajax({
        type: type,
        url: url,
        contentType: (function () {
            switch (options.contentType) {
                case 'form':
                    return 'application/x-www-form-urlencoded';
                default:
                    return 'application/json';
            }
        })(),
        xhrFields: {
            withCredentials: true
        },
        dataType: options.dataType || 'json',
        data: options.data || '',
        async: options.async === undefined ? true : options.async
    }).then(function (res) {
        if (!res.errorCode) {
            console.log("into if");
            if (success) {
                success(res)
            }
        } else {
            if (failure) {
                failure(res);
            }
            errorHandle(res);
        }
    }, function (err) {
        console.log(err)
    })
}

function errorHandle(err) {
    toastr.error(err.errorMessage, '错误信息', {
        timeOut: 3600000
    });
    console.log(err);
}

var http = {
    get: function (url, data, success, failure) {
        baseAjax('get', url, data, success, failure);
    },
    post: function (url, data, success, failure) {
        baseAjax('post', url, data, success, failure);
    }
};