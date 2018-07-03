package cn.rockingwang.notebook.util;

import java.util.UUID;

/**
 * @Author: wangpeng
 * @Description:
 */
public class StringUtils {

    public final static String EMPTY = "";

    /**
     * 获取没有-的UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
