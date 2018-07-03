package cn.rockingwang.notebook.biz.user.dao;

import cn.rockingwang.notebook.biz.user.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: wangpeng
 * @Description:
 */
@Mapper
@Component
public interface UserDao {

    String TABLE_NAME = "t_user";
    String TABLE_FIELDS = "id, username, password, salt, headUrl, role";

    @Insert({"insert into ", TABLE_NAME, "(", TABLE_FIELDS, ") values (#{id}, #{username}, #{password}, #{salt}, #{headUrl}, #{role})"})
    int insertUser(User user);

    @Select({"select ", TABLE_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(String id);

    @Select({"select ", TABLE_FIELDS, " from ", TABLE_NAME, " where username=#{username}"})
    User selectByUsername(String username);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(String id);

}
