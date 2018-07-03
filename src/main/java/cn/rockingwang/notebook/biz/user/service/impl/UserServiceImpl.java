package cn.rockingwang.notebook.biz.user.service.impl;

import cn.rockingwang.notebook.exception.BizException;
import cn.rockingwang.notebook.biz.user.dao.UserDao;
import cn.rockingwang.notebook.biz.user.entity.User;
import cn.rockingwang.notebook.biz.user.service.UserService;
import cn.rockingwang.notebook.util.EncryptionUtils;
import cn.rockingwang.notebook.util.PasswordUtils;
import cn.rockingwang.notebook.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: wangpeng
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User register(String username, String password) {
        Random random = new Random();
        User u = userDao.selectByUsername(username);
        if (u != null) {
            throw new BizException("用户名已经被占用");
        }

        User user = new User();
        user.setId(StringUtils.uuid());
        user.setUsername(username);
        user.setSalt(EncryptionUtils.generateSalt());
        user.setPassword(PasswordUtils.encryptPassword(password, user.getSalt()));
        user.setHeadUrl(String.format("https://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
        user.setRole("user");
        userDao.insertUser(user);

        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new BizException("用户名或密码不正确");
        }
        if (!PasswordUtils.checkPassword(password, user.getPassword(), user.getSalt())) {
            throw new BizException("用户名或密码不正确");
        }
        return user;
    }

}
