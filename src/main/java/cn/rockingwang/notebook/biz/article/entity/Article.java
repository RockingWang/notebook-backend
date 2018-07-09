package cn.rockingwang.notebook.biz.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wangpeng
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String title;
    private String describes;
    private String content;
    private Date createTime;
    private String category;

}
