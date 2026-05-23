package com.sbisec.helios.gw.common.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class CustomConfigration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        
        // 遷移先のエラーページをHTTPステータスに応じて設定
        factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error?page=400"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/error?page=403"),
                new ErrorPage(HttpStatus.NOT_FOUND, "/error?page=404"),
                new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error?page=405"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error?page=500"));
    }
    
}
