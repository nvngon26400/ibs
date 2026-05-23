package com.sbisec.helios.gw;

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
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = { "com.sbisec.helios.gw, com.sbisec.helios.common" })
@EnableScheduling
public class App extends SpringBootServletInitializer {
    
    /**
     * WebApplication初期化処理
     *
     * @param args 開始パラメータ
     */
    public static void main(String[] args) {
        
        // Redisでのデシリアライズで問題が発生するため、下記の機能を無効にする
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(App.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        
    }
}
