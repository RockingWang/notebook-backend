package cn.rockingwang.notebook.biz.user.controller;

import cn.rockingwang.notebook.common.base.BaseController;
import cn.rockingwang.notebook.common.base.BaseResponse;
import cn.rockingwang.notebook.biz.user.entity.User;
import cn.rockingwang.notebook.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wangpeng
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param callback 回调地址
     */
    @RequestMapping("/register")
    public ResponseEntity<BaseResponse> register(HttpServletResponse httpServletResponse, @RequestParam String username, @RequestParam String password, @RequestParam(value = "callback", required = false) String callback) {
        User user = userService.register(username, password);
        setLoginUser(user.getId());
        BaseResponse response = new BaseResponse();
        response.setCallback(callback);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param callback 回调地址
     */
    @RequestMapping("/login")
    public ResponseEntity<BaseResponse> login(HttpServletResponse httpServletResponse, @RequestParam String username, @RequestParam String password, @RequestParam(value = "callback", required = false) String callback) {
        User user = userService.login(username, password);
        setLoginUser(user.getId());
        BaseResponse response = new BaseResponse();
        response.setCallback(callback);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 将 ticket 设置到 cookie
     *
     * @param response HttpServletResponse
     * @param ticket   登录后访问ticket
     */
    private void setTicket(HttpServletResponse response, String ticket) {
        Cookie cookie = new Cookie("ticket", ticket);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
