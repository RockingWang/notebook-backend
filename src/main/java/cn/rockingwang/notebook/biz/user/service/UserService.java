package cn.rockingwang.notebook.biz.user.service;

import cn.rockingwang.notebook.biz.user.entity.User;

/**
 * @Author: wangpeng
 * @Description:
 */
public interface UserService {

    User register(String username, String password);

    User login(String username, String password);

}
