var loginBtn = $('#loginBtn');
var signUpBtn = $('#signUpBtn');
var loginForm = $('#loginForm');

loginBtn.on('click', function () {
    var username = loginForm.find('.username').val().trim();
    var password = loginForm.find('.password').val().trim();
    http.post(config.api.user.login, {
        data: {
            'username': username,
            'password': password
        },
        contentType: 'form'
    }, function (res) {
        if (res.errorCode === 0) {
            toastr.success("登录成功！");
        } else {
            toastr.error("登录失败！具体原因如下：" + res.errorMessage);
        }
    });
});