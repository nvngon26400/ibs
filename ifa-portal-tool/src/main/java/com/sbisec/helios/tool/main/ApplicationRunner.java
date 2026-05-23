package com.sbisec.helios.tool.main;

import java.util.Arrays;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sbisec.helios.tool.GlobalConstants;
import com.sbisec.helios.tool.GlobalConstants.StringValueEnum;
import com.sbisec.helios.tool.GlobalConstants.TOOL_RESULT;
import com.sbisec.helios.tool.GlobalConstants.TOOL_TYPE;
import com.sbisec.helios.tool.helper.CommandLineHelper;
import com.sbisec.helios.tool.helper.CommandLineHelper.CommandLineArgs;
import com.sbisec.helios.tool.helper.LoggerHelper;
import com.sbisec.helios.tool.service.redis.SpringRedisCliService;

/**
 * アプリケーション実行クラス。
 * 
 * @author SCSK宮坂
 */
@SpringBootApplication(scanBasePackages = { "com.sbisec.helios.tool" })
public class ApplicationRunner implements CommandLineRunner {
	/** ツール実行結果 */
	private static TOOL_RESULT toolResult = TOOL_RESULT.UNKNOWN;

	/** spring Redis CLIサービス */
	@Autowired
	private SpringRedisCliService springRedisCliService;

	/** ロガーヘルパー */
	@Autowired
	private LoggerHelper loggerHelper;
	/** コマンドラインヘルパー */
	@Autowired
	private CommandLineHelper commandLineHelper;

	static {
		// ログ項目キーにデフォルト値を設定
		MDC.put(GlobalConstants.LOG_ITEM_KEY_TOOL_TYPE, TOOL_TYPE.TOOL_MAIN.getValue());
	}

	/**
	 * コンストラクタ。
	 */
	public ApplicationRunner() {
	}

	/**
	 * 処理メイン。
	 * 
	 * @param args 実行コマンド引数。
	 */
	public static void main(String[] args) {
		// バッチアプリとして設定
		SpringApplication springApplication = new SpringApplication(ApplicationRunner.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);

		// ツールメインを実行
		springApplication.run(args);

		// 終了コードを設定
		System.exit(toolResult.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(String... args) throws Exception {
		try {
			// コマンドライン引数をパース
			CommandLineArgs commandLineArgs = commandLineHelper.parseCommandArgs(args);

			// ツール種別名を取得
			String toolTypeName = commandLineArgs.getSimpleArgList().pollFirst();

			// ツール種別に変換
			TOOL_TYPE toolType = StringValueEnum.parse(TOOL_TYPE.values(), toolTypeName);

			// ツール種別が指定されている場合
			if (toolType != null) {
				// 先頭引数をシフト
				String[] toolArgs = Arrays.copyOfRange(args, 1, args.length);

				// ツール種別により処理分岐
				if (toolType == TOOL_TYPE.SPRING_REDIS_CLI) {
					toolResult = springRedisCliService.run(toolArgs);
				}
			} else {
				loggerHelper.printErrorLog("TOOL_NAME_UNSPECIFIED_OR_NOT_FOUND", toolTypeName);
				System.out.println();
				printUsage();

				toolResult = TOOL_RESULT.FAIRULE;
			}
		} catch (Throwable t) {
			loggerHelper.printErrorLog("UNEXPECTED_ERROR", t);

			toolResult = TOOL_RESULT.FAIRULE;
		}
	}

	/**
	 * 使用方法を標準出力する。
	 */
	private void printUsage() {
		System.out.println("＜" + TOOL_TYPE.TOOL_MAIN.getDescription() + "＞");
		System.out.println();
		System.out.println("java ([システムプロパティ]...) -jar ifa-portal-tool-x.x.x.jar [ツール名] ([ツールオプション]...)");
		System.out.println();
		System.out.println("■ツール名");
		System.out.println("・" + TOOL_TYPE.SPRING_REDIS_CLI.getValue());
		System.out.println();
		System.out.println("■設定可能なシステムプロパティ");
		System.out.println("・log.level");
		System.out.println("    logback設定に基づく（デフォルト: info）");
	}
}
