package cn.rockingwang.notebook.biz.article.dao;

import cn.rockingwang.notebook.biz.article.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wangpeng
 * @Description:
 */
@Mapper
@Component
public interface ArticleDao {

    String TABLE_NAME = "t_article";
    String TABLE_FIELDS = "id, title, describes, content, createTime, category";

    @Insert({"insert into ", TABLE_NAME, "(", TABLE_FIELDS, ") values (#{id}, #{title}, #{describes}, #{content}, #createTime}, #{category})"})
    int insertArticle(Article article);

    @Select({"select ", TABLE_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Article selectById(String id);

    @Select({"select ", TABLE_FIELDS, " from ", TABLE_NAME, " order by createTime desc limit (#{page} - 1) * #{limit}, #{limit}"})
    List<Article> selectPageable(@Param("page") int page, @Param("size") int size);

    @Select({"select ", TABLE_FIELDS, " from ", TABLE_NAME, " where category=#{category} order by createTime desc limit (#{page} - 1) * #{limit}, #{limit}"})
    List<Article> selectPageableByCategory(@Param("category") String category, @Param("page") int page, @Param("size") int size);

    @Select({"select count(id) from ", TABLE_NAME, " where category=#{category}"})
    int countByCategory(@Param("category") String category);

    @Select({"select count(id) from ", TABLE_NAME})
    int count();

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(@Param("id") String id);


}
