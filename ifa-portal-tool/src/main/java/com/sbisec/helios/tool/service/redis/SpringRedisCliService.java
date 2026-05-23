package com.sbisec.helios.tool.service.redis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.tool.GlobalConstants.StringValueEnum;
import com.sbisec.helios.tool.GlobalConstants.TOOL_RESULT;
import com.sbisec.helios.tool.GlobalConstants.TOOL_TYPE;
import com.sbisec.helios.tool.exception.ToolException;
import com.sbisec.helios.tool.helper.CommandLineHelper;
import com.sbisec.helios.tool.helper.CommandLineHelper.CommandLineArgs;
import com.sbisec.helios.tool.helper.LoggerHelper;
import com.sbisec.helios.tool.service.ToolServiceBase;

import lombok.Getter;

/**
 * spring Redis CLIサービスクラス。
 * 
 * @author SCSK宮坂
 */
@Service
public class SpringRedisCliService extends ToolServiceBase {
	/** コマンド種別 */
	@Getter
	public enum COMMAND_TYPE implements StringValueEnum {
		/** 照会 */
		SHOW("show", "照会"),
		/** 設定 */
		SET("set", "設定"),
		/** 削除 */
		DELETE("delete", "削除"),
		/** 一括クリア */
		CLEAR_ALL("clear", "一括クリア"),
		/** 一括無効化 */
		INVALIDATE_ALL("invalidate", "一括無効化"),
		/** ヘルプ表示 */
		HELP("help", "ヘルプ表示");

		/** 値 */
		private String value = null;
		/** 説明 */
		private String description = null;

		/**
		 * コンストラクタ。
		 *
		 * @param value       値。
		 * @param description 説明。
		 */
		private COMMAND_TYPE(String value, String description) {
			this.value = value;
			this.description = description;
		}
	}

	/** オプション種別 */
	@Getter
	public enum COMMAND_OPTION_TYPE implements StringValueEnum {
		/** 照会（JSONフォーマット） */
		SHOW_JSON("json"),
		/** 設定（テキストファイル入力） */
		SET_TEXT_FILE("text"),
		/** 設定（JSONファイル入力） */
		SET_JSON_FILE("json");

		/** 値 */
		private String value = null;

		/**
		 * コンストラクタ。
		 *
		 * @param value 値。
		 */
		private COMMAND_OPTION_TYPE(String value) {
			this.value = value;
		}
	}

	/** 環境設定キャッシュグループ */
	private static final String REDIS_CACHE_GROUP_ENV_CONFIG = "environment";
	/** 執行問い合わせ認証文字 */
	private static final char EXECUTION_CONFIRMATION_AUTH_CHAR = 'y';

	/** spring Redis CLIプロパティ */
	@Autowired
	@Qualifier(SpringRedisCliServiceConfig.BEAN_NAME_PROPERTIES)
	private SpringRedisCliProperties springRedisCliProperties;
	/** キャッシュマネージャ */
	@Autowired
	@Qualifier(SpringRedisCliServiceConfig.BEAN_NAME_CACHE_MANAGER)
	private CacheManager cacheManager;

	/** ロガーヘルパー */
	@Autowired
	private LoggerHelper loggerHelper;
	/** コマンドラインヘルパー */
	@Autowired
	private CommandLineHelper commandLineHelper;

	/**
	 * コンストラクタ。
	 */
	public SpringRedisCliService() {
		super(TOOL_TYPE.SPRING_REDIS_CLI);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TOOL_RESULT execute(String... commandArgs) throws Exception {
		// ツール引数をパース
		CommandLineArgs commandLineArgs = commandLineHelper.parseCommandArgs(commandArgs);

		// １つめの単純引数をツール種別とみなし、コマンド種別に変換
		String commandName = commandLineArgs.getSimpleArgList().pollFirst();
		COMMAND_TYPE commandType = StringValueEnum.parse(COMMAND_TYPE.values(), commandName);

		// コマンド種別により処理分岐
		if (commandType != null) {
			// ショートオプション指定がある場合はエラー
			if (!commandLineArgs.getShortOptionList().isEmpty()) {
				throw ToolException.build("INVALID_COMMAND_OPTION");
			}

			try {
				if (commandType == COMMAND_TYPE.SHOW || commandType == COMMAND_TYPE.SET || commandType == COMMAND_TYPE.DELETE) {
					// ２つめの単純引数をキャッシュキーとみなす
					String cacheKey = commandLineArgs.getSimpleArgList().pollFirst();

					if (commandType == COMMAND_TYPE.SET) {
						if (commandLineArgs.getSimpleArgList().isEmpty()) {
							if (commandLineArgs.getLongOptionMap().containsKey(COMMAND_OPTION_TYPE.SET_JSON_FILE.getValue())) {
								// ロングオプション引数をJSONファイルパスとみなす
								String inputFilePathText = commandLineArgs.getLongOptionMap().remove(COMMAND_OPTION_TYPE.SET_JSON_FILE.getValue());

								// 他にロングオプション指定がある場合はエラー
								if (!commandLineArgs.getLongOptionMap().isEmpty()) {
									throw ToolException.build("INVALID_COMMAND_OPTION");
								}

								putValueFromFile(REDIS_CACHE_GROUP_ENV_CONFIG, cacheKey, inputFilePathText, true);
							} else if (commandLineArgs.getLongOptionMap().containsKey(COMMAND_OPTION_TYPE.SET_TEXT_FILE.getValue())) {
								// ロングオプション引数をテキストファイルパスとみなす
								String inputFilePathText = commandLineArgs.getLongOptionMap().remove(COMMAND_OPTION_TYPE.SET_TEXT_FILE.getValue());

								// 他にロングオプション指定がある場合はエラー
								if (!commandLineArgs.getLongOptionMap().isEmpty()) {
									throw ToolException.build("INVALID_COMMAND_OPTION");
								}

								putValueFromFile(REDIS_CACHE_GROUP_ENV_CONFIG, cacheKey, inputFilePathText, false);
							} else {
								throw ToolException.build("INVALID_COMMAND_OPTION");
							}
						} else {
							// ３つめの単純引数をキャッシュ値とみなす
							String cacheValue = commandLineArgs.getSimpleArgList().pollFirst();

							// 他にオプション指定がある場合はエラー
							if (!commandLineArgs.getSimpleArgList().isEmpty() || !commandLineArgs.getLongOptionMap().isEmpty()) {
								throw ToolException.build("INVALID_COMMAND_OPTION");
							}

							putValue(REDIS_CACHE_GROUP_ENV_CONFIG, cacheKey, cacheValue);
						}
					} else if (commandType == COMMAND_TYPE.DELETE) {
						// 他にオプション指定がある場合はエラー
						if (!commandLineArgs.getSimpleArgList().isEmpty() || !commandLineArgs.getLongOptionMap().isEmpty()) {
							throw ToolException.build("INVALID_COMMAND_OPTION");
						}

						deleteValue(REDIS_CACHE_GROUP_ENV_CONFIG, cacheKey);
					} else {
						// ロングオプション引数をJSONフォーマット整形フラグとみなす
						boolean prettyJson = commandLineArgs.getLongOptionMap().containsKey(COMMAND_OPTION_TYPE.SHOW_JSON.getValue());
						commandLineArgs.getLongOptionMap().remove(COMMAND_OPTION_TYPE.SHOW_JSON.getValue());

						// 他にオプション指定がある場合はエラー
						if (!commandLineArgs.getSimpleArgList().isEmpty() || !commandLineArgs.getLongOptionMap().isEmpty()) {
							throw ToolException.build("INVALID_COMMAND_OPTION");
						}

						printValue(REDIS_CACHE_GROUP_ENV_CONFIG, cacheKey, prettyJson);
					}
				} else {
					// 他にオプション指定がある場合はエラー
					if (!commandLineArgs.getSimpleArgList().isEmpty() || !commandLineArgs.getLongOptionMap().isEmpty()) {
						throw ToolException.build("INVALID_COMMAND_OPTION");
					}

					if (commandType == COMMAND_TYPE.CLEAR_ALL) {
						clearAll(REDIS_CACHE_GROUP_ENV_CONFIG);
					} else if (commandType == COMMAND_TYPE.INVALIDATE_ALL) {
						invalidateAll(REDIS_CACHE_GROUP_ENV_CONFIG);
					} else {
						// 上記以外はすべてヘルプとみなす
						printUsage();
					}
				}
			} catch (RedisConnectionFailureException e) {
				throw ToolException.build("REDIS_CONNECTION_FAILURE", e,
						springRedisCliProperties.getHostName(), springRedisCliProperties.getPortNo(), springRedisCliProperties.getDatabaseIndexNo());
			}
		} else {
			loggerHelper.printErrorLog("UNSPECIFIED_OR_NOT_FOUND_COMMAND", commandType);
			System.out.println();
			printUsage();

			return TOOL_RESULT.FAIRULE;
		}

		return TOOL_RESULT.SUCCESS;
	}

	/**
	 * キャッシュを標準出力する。
	 * 
	 * @param cacheGroup キャッシュグループ。
	 * @param cacheKey   キャッシュキー。
	 * @param prettyJson JSONフォーマット整形フラグ（true：整形する、false：整形しない）。
	 * @throws ToolException 引数エラーが発生した場合、該当するキャッシュが存在しない場合。
	 */
	private void printValue(String cacheGroup, String cacheKey, boolean prettyJson) throws ToolException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		} else if (StringUtils.isEmpty(cacheKey)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュキー");
		}

		// キャッシュを取得
		ValueWrapper valueWrapper = cacheManager.getCache(cacheGroup).get(cacheKey);

		if (valueWrapper != null) {
			if (!prettyJson) {
				// 文字列としてそのまま標準出力
				loggerHelper.printInfoLog("GOT_PLAIN_TEXT", cacheGroup, cacheKey);
				System.out.println(valueWrapper.get().toString());
			} else {
				try {
					// JSONフォーマットとして整形して標準出力
					Map<String, Object> map = new ObjectMapper().readValue(valueWrapper.get().toString(), new TypeReference<Map<String, Object>>() {
					});
					String jsonText = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);

					loggerHelper.printInfoLog("GOT_JSON_TEXT", cacheGroup, cacheKey);
					System.out.println(jsonText);
				} catch (JsonProcessingException e) {
					// JSONフォーマット整形が失敗した場合はそのまま標準出力
					loggerHelper.printInfoLog("GOT_PRETTY_JSON_FAILURE", cacheGroup, cacheKey);
					System.out.println(valueWrapper.get().toString());
				}
			}
		} else {
			throw ToolException.build("NOT_FOUND_CACHE_KEY", cacheGroup, cacheKey);
		}
	}

	/**
	 * 文字列を指定してキャッシュを設定する。
	 * 
	 * @param cacheGroup キャッシュグループ。
	 * @param cacheKey   キャッシュキー。
	 * @param cacheValue キャッシュ値。
	 * @throws ToolException 引数エラーが発生した場合。
	 */
	private void putValue(String cacheGroup, String cacheKey, String cacheValue) throws ToolException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		} else if (StringUtils.isEmpty(cacheKey)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュキー");
		} else if (cacheValue == null) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュ値");
		}

		// キャッシュを設定
		cacheManager.getCache(cacheGroup).put(cacheKey, cacheValue);

		loggerHelper.printInfoLog("SET_PLAIN_TEXT", cacheGroup, cacheKey);
	}

	/**
	 * ファイル内容を指定してキャッシュを設定する。
	 * 
	 * @param cacheGroup        キャッシュグループ。
	 * @param cacheKey          キャッシュキー。
	 * @param inputFilePathText キャッシュ値を示す入力ファイルのパス。
	 * @param isJsonFormat      入力ファイルJSONフォーマットフラグ（true：JSONフォーマットである、false：JSONフォーマットではない）。
	 * @throws ToolException 引数エラーが発生した場合、入力ファイルの読み込みに失敗した場合、入力ファイルのフォーマットが不正の場合。
	 */
	private void putValueFromFile(String cacheGroup, String cacheKey, String inputFilePathText, boolean isJsonFormat) throws ToolException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		} else if (StringUtils.isEmpty(cacheKey)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュキー");
		} else if (StringUtils.isEmpty(inputFilePathText)) {
			throw ToolException.build("SET_UNAVAILABLE_INPUT_FILE");
		}

		// 入力ファイルパスを取得
		Path inputFilePath = Path.of(inputFilePathText);

		// 読み込めない場合はエラー
		if (!Files.isReadable(inputFilePath)) {
			throw ToolException.build("SET_UNAVAILABLE_INPUT_FILE", inputFilePath.toAbsolutePath().toString());
		}

		try {
			if (!isJsonFormat) {
				// テキストファイルの内容でキャッシュ値を設定
				cacheManager.getCache(cacheGroup).put(cacheKey, Files.readString(inputFilePath, StandardCharsets.UTF_8));

				loggerHelper.printInfoLog("SET_TEXT_FILE", cacheGroup, cacheKey);
			} else {
				try {
					// JSONフォーマット判定とストリップをかける目的でいったんデシリアライズ
					Map<String, Object> jsonMap = new ObjectMapper().readValue(inputFilePath.toFile(), new TypeReference<Map<String, Object>>() {
					});

					// JSONフォーマットファイルの内容でキャッシュを設定
					cacheManager.getCache(cacheGroup).put(cacheKey, new ObjectMapper().writeValueAsString(jsonMap));

					loggerHelper.printInfoLog("SET_JSON_FILE", cacheGroup, cacheKey);
				} catch (JsonProcessingException e) {
					throw ToolException.build("SET_INVALID_FILE_FORMAT", e, inputFilePath.toAbsolutePath().toString());
				}
			}
		} catch (IOException e) {
			throw ToolException.build("SET_UNAVAILABLE_INPUT_FILE", e, inputFilePath.toAbsolutePath().toString());
		}
	}

	/**
	 * キャッシュを削除する。
	 * 
	 * @param cacheGroup キャッシュグループ。
	 * @param cacheKey   キャッシュキー。
	 * @throws ToolException 引数エラーが発生した場合、該当するキャッシュが存在しない場合。
	 */
	private void deleteValue(String cacheGroup, String cacheKey) throws ToolException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		} else if (StringUtils.isEmpty(cacheKey)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュキー");
		}

		// キャッシュの存在確認
		if (cacheManager.getCache(cacheGroup).get(cacheKey) == null) {
			throw ToolException.build("NOT_FOUND_CACHE_KEY", cacheGroup, cacheKey);
		}

		// キャッシュを削除
		cacheManager.getCache(cacheGroup).evict(cacheKey);

		loggerHelper.printInfoLog("DELETED", cacheGroup, cacheKey);
	}

	/**
	 * 一括クリアを実行する。
	 * 
	 * @param cacheGroup キャッシュグループ。
	 * @throws ToolException 引数エラーが発生した場合。
	 * @throws IOException   標準入力が失敗した場合。
	 */
	private void clearAll(String cacheGroup) throws ToolException, IOException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		}

		System.out.print(loggerHelper.getMessage("CLEAR_CONFIRMATION"));
		char answer = (char) System.in.read();
		System.out.println();

		if (answer == EXECUTION_CONFIRMATION_AUTH_CHAR) {
			// キャッシュをクリア
			cacheManager.getCache(cacheGroup).clear();

			loggerHelper.printInfoLog("CLEARED", cacheGroup);
		} else {
			loggerHelper.printInfoLog("STOPPED_CLEAR", cacheGroup);
		}
	}

	/**
	 * 一括無効化を実行する。
	 * 
	 * @param cacheGroup キャッシュグループ。
	 * @throws ToolException 引数エラーが発生した場合。
	 * @throws IOException   標準入力が失敗した場合。
	 */
	private void invalidateAll(String cacheGroup) throws ToolException, IOException {
		if (StringUtils.isEmpty(cacheGroup)) {
			throw ToolException.build("INVALID_ARGUMENT", "キャッシュグループ");
		}

		System.out.print(loggerHelper.getMessage("INVALIDATE_CONFIRMATION"));
		char answer = (char) System.in.read();
		System.out.println();

		if (answer == EXECUTION_CONFIRMATION_AUTH_CHAR) {
			// キャッシュを無効化
			cacheManager.getCache(cacheGroup).invalidate();

			loggerHelper.printInfoLog("INVALIDATED", cacheGroup);
		} else {
			loggerHelper.printInfoLog("STOPPED_INVALIDATE", cacheGroup);
		}
	}

	/**
	 * 使用方法を標準出力する。
	 */
	private void printUsage() {
		System.out.println("＜" + TOOL_TYPE.SPRING_REDIS_CLI.getDescription() + "＞");
		System.out.println();
		System.out.println("java ([システムプロパティ]...) -jar ifa-portal-tool-x.x.x.jar " + TOOL_TYPE.SPRING_REDIS_CLI.getValue() + " [コマンド] ([コマンド引数]...) ([オプション]...)");
		System.out.println();
		System.out.println("■コマンドとオプション");
		System.out.println();
		System.out.println("・照会");
		System.out.println("    " + COMMAND_TYPE.SHOW.getValue() + " [キャッシュキー]");
		System.out.println("      該当キャッシュを文字列としてそのまま標準出力する。");
		System.out.println("    " + COMMAND_TYPE.SHOW.getValue() + " [キャッシュキー] (--" + COMMAND_OPTION_TYPE.SHOW_JSON.getValue() + ")");
		System.out.println("      該当キャッシュをJSONフォーマットとみなし可能な限り整形して出力する。正系に失敗した場合は文字列としてそのまま標準出力する。");
		System.out.println("・変更");
		System.out.println("    " + COMMAND_TYPE.SET.getValue() + " [キャッシュキー] [キャッシュ値]");
		System.out.println("      文字列としてそのまま上書き設定する。");
		System.out.println("    " + COMMAND_TYPE.SET.getValue() + " [キャッシュキー] --" + COMMAND_OPTION_TYPE.SET_JSON_FILE.getValue() + "=[入力JSONファイルパス]");
		System.out.println("      入力ファイルの内容をJSONフォーマットとみなし、可能な限りストリップ（改行コードなど不要な文字を削除）して上書き設定する。");
		System.out.println("    " + COMMAND_TYPE.SET.getValue() + " [キャッシュキー] --" + COMMAND_OPTION_TYPE.SET_TEXT_FILE.getValue() + "=[入力テキストファイルパス]");
		System.out.println("      入力ファイルの内容をそのまま上書き設定する。改行コードも含めて設定されるため注意。");
		System.out.println("・削除");
		System.out.println("    " + COMMAND_TYPE.DELETE.getValue() + " [キャッシュキー]");
		System.out.println("      該当キャッシュを削除する。");
		System.out.println("    " + COMMAND_TYPE.CLEAR_ALL.getValue());
		System.out.println("      environmentキャッシュグループの全キャッシュを削除する。");
		System.out.println("    " + COMMAND_TYPE.INVALIDATE_ALL.getValue());
		System.out.println("      environmentキャッシュグループの全キャッシュを削除し即座に無効化する。");
		System.out.println();
		System.out.println("■設定可能なシステムプロパティ");
		System.out.println("・redis.host");
		System.out.println("    ホスト名（デフォルト: localhost）");
		System.out.println("・redis.port");
		System.out.println("    ポート番号（デフォルト: 6379）");
		System.out.println("・redis.password");
		System.out.println("    パスワード（デフォルト: なし）");
		System.out.println("・redis.database");
		System.out.println("    DBインデックス番号（デフォルト: 0）");
		System.out.println("・redis.ttl");
		System.out.println("    キャッシュ有効期間（秒）（デフォルト: 0 = 永続化）");
		System.out.println();
		System.out.println("■補足事項");
		System.out.println("・キャッシュグループは環境設定用のenvironmentのみサポート。");
	}
}
