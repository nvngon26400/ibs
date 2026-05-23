package com.sbisec.helios.ap.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

@Configuration
public class MessageConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public MessageSourceAccessor getMessageSource() {
        return new MessageSourceAccessor(messageSource);
    }
}
