package com.sbisec.helios.ap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * WebApplication初期化処理
 *
 * @author SCSK
 *
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = { "com.sbisec.helios.ap, com.sbisec.helios.common" })
@EnableScheduling
public class App extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        
        SpringApplication.run(App.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        
    }
}
