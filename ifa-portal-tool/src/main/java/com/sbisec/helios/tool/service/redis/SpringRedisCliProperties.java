package com.sbisec.helios.tool.service.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

/**
 * spring Redis CLIプロパティクラス。
 * 
 * @author SCSK宮坂
 */
@Getter
@Configuration
public class SpringRedisCliProperties {
	/** ホスト名 */
	@Value("${redis.host:localhost}")
	private String hostName;
	/** ポート番号 */
	@Value("${redis.port:6379}")
	private int portNo;
	/** パスワード */
	@Value("${redis.password:#{null}}")
	private String password;
	/** DBインデックス番号 */
	@Value("${redis.database:0}")
	private int databaseIndexNo;
	/** キャッシュ有効期間（秒） */
	@Value("${redis.ttl:0}")
	private long cacheTtlSec;
}
