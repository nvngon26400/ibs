package com.sbisec.helios.ap.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.logger.LogFilter;

/**
 * Configure filters.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Configuration
public class FilterConfig {
    /**
     * Configure log filters.
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean<LogFilter> logbackFilter() {
        FilterRegistrationBean<LogFilter> reg = new FilterRegistrationBean<LogFilter>();
        reg.setFilter(new LogFilter());
        reg.addUrlPatterns(AppConstants.URL_ALL);
        reg.setOrder(21);
        return reg;
    }
}
