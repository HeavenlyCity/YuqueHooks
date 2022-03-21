package cn.ridup.tool.yuquehooks.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ridup.tool.yuquehooks.config.filter.LogParamFilter;

/**
 * 过滤器配置
 *
 * @author ridup.cn
 * @version V0.1
 * @since 2022/3/20 17:56
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogParamFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogParamFilter");
        registration.setOrder(1);
        return registration;
    }

}
