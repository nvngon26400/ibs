package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 非同期スレッド設定ファイル - Spring非同期タスク実行器を設定する
 * 
 * @author lianhua.xia
 * @date 2025-01-22
 */

@Configuration
@EnableAsync
public class AsyncConfig {

	@Value("${spring.task.execution.pool.core-size:64}")
	private int coreSize;

	/**
	 * 非同期タスク実行器を設定する
	 * 
	 * @return Executor スレッドプール実行器
	 */
	@Bean("taskExecutor")
	Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		// 基本的なパラメータ設定
		executor.setCorePoolSize(coreSize);

		executor.setWaitForTasksToCompleteOnShutdown(true);

		executor.initialize();
		return executor;
	}
}
