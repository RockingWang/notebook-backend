package cn.rockingwang.notebook.biz.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * @Author: wangpeng
 * @Description: 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String salt;
    private String headUrl;
    private String role;

}
