package com.sbisec.helios.gw.common.config;

import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbisec.helios.gw.common.interceptor.HttpPostCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.HttpSessionCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.IfaCustomerCommonCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.IfapExtApiInterceptor;
import com.sbisec.helios.gw.common.interceptor.ParameterLogInterceptor;
import com.sbisec.helios.gw.common.interceptor.PermissionCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.RequestRestrictionCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.ServiceStatusCheckInterceptor;
import com.sbisec.helios.gw.common.interceptor.SetRequestTimeInterceptor;
import com.sbisec.helios.gw.common.lisenter.SessionListener;
import com.sbisec.helios.gw.common.util.EmailUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Service configuration.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Configuration
@Import({ SessionListener.class })
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PropertyHolder prop;
    
    @Autowired
    private ServiceStatusCheckInterceptor serviceStatusCheckInterceptor;
    @Autowired
    private HttpSessionCheckInterceptor httpSessionCheckInterceptor;
    
    @Autowired
    private PermissionCheckInterceptor permissionCheckInterceptor;
    
    @Autowired
    private IfaCustomerCommonCheckInterceptor ifaCustomerCommonCheckInterceptor;

    @Autowired
    private ParameterLogInterceptor parameterLogInterceptor;
    
    @Autowired
    private HttpPostCheckInterceptor httpPostCheckInterceptor;
    
    @Autowired
    private SetRequestTimeInterceptor setRequestTimeInterceptor;
    
    @Autowired
    private IfapExtApiInterceptor ifapExtApiInterceptor;
    
    @Autowired
    private RequestRestrictionCheckInterceptor requestRestrictionCheckInterceptor;
    
    private static final int MAX_UPLOAD_SIZE = 100 * 1024 * 1024;
    
    /**
     * Configure the password policy of the service.
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure cross-domain access filters.
     * 
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Arrays.asList(prop.getEnvCrossOrigins()));
        cors.setAllowedMethods(Arrays.asList(prop.getAllowedMethods()));
        cors.setAllowCredentials(prop.isAllowCredentials());
        cors.addExposedHeader(HttpHeaders.AUTHORIZATION);
        cors.setAllowedHeaders(Arrays.asList(prop.getAllowedHeaders()));
        cors.setMaxAge(prop.getMaxAge());
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(prop.getAddMapping())
                .allowedOrigins(prop.getEnvCrossOrigins())
                .allowedMethods(prop.getAllowedMethods())
                .allowCredentials(prop.isAllowCredentials())
                .allowedHeaders(prop.getAllowedHeaders())
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                .maxAge(prop.getMaxAge());
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry.addInterceptor(requestRestrictionCheckInterceptor);
        registry.addInterceptor(setRequestTimeInterceptor);
        registry.addInterceptor(parameterLogInterceptor);
        registry.addInterceptor(ifapExtApiInterceptor);
        registry.addInterceptor(httpSessionCheckInterceptor);
        registry.addInterceptor(permissionCheckInterceptor).excludePathPatterns("/error");
        registry.addInterceptor(ifaCustomerCommonCheckInterceptor);
        registry.addInterceptor(httpPostCheckInterceptor);
        registry.addInterceptor(serviceStatusCheckInterceptor);
    }

    /**
     * Configure the service mail server.
     * 
     * @return
     */
    @Bean
    public JavaMailSender mailSender() {
        log.info("Build mail sender.");
        return EmailUtil.mailSender(prop.getMailProtocol(),
                                    prop.getEnvMailHost(),
                                    Integer.valueOf(prop.getEnvMailPort()),
                                    prop.getMailTimeout());
    }

    /**
     * Configure dispatcherServlet.
     * 
     * @return
     */
    @Bean
    public ServletRegistrationBean<HttpServlet> dispatcherServletRegistration() {
        ServletRegistrationBean<HttpServlet> registration = new ServletRegistrationBean<>(dispatcherServlet(), "/");
        registration.setLoadOnStartup(1);
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        
        // multipart設定
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(null, MAX_UPLOAD_SIZE,
                (long)(MAX_UPLOAD_SIZE * 4.5), MAX_UPLOAD_SIZE / 2);
        registration.setMultipartConfig(multipartConfigElement);
        return registration;
        
    }
    
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean() {
        return new DispatcherServletRegistrationBean(dispatcherServlet(), "/");
    }
    
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     * Configure message source.
     * 
     * @return
     */
    @Bean
    public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasename("classpath:i18n/messages");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
    }

    /**
     * Configure validator.
     * 
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }
}
