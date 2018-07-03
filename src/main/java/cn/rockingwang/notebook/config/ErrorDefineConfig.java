package cn.rockingwang.notebook.config;

import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: wangpeng
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "error")
@PropertySource("classpath:errorDefine.properties")
@Component
public class ErrorDefineConfig {

    public static Map<Integer, String> define = Maps.newHashMap();

    public Map<Integer, String> getDefine() {
        return ErrorDefineConfig.define;
    }

    public static void setDefine(Map<Integer, String> define) {
        ErrorDefineConfig.define = define;
    }
}
