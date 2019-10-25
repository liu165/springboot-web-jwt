package com.hehe.entity;

import java.util.ArrayList;
import java.util.List;

import com.hehe.config.JwtHelper;
import com.hehe.config.TokenProvider;
import com.hehe.interceptor.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入属性
 */


@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private long tokenValidityInSeconds;

    @Value("${jwt.authorised-urls}")
    private String[] authorisedUrls;

    @Bean
    public TokenProvider okenProviderBean() {
        return new TokenProvider(secret, tokenValidityInSeconds);
    }

    @Bean
    public FilterRegistrationBean basicFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtFilter filter = new JwtFilter(okenProviderBean(), authorisedUrls);
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");//匹配所有
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
