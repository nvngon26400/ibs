package com.sbisec.helios.tool.helper;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sbisec.helios.tool.exception.ToolException;

import lombok.Data;

/**
 * コマンドラインヘルパークラス。
 * 
 * @author SCSK宮坂
 */
@Component
public class CommandLineHelper {
	/**
	 * コマンドライン引数クラス。
	 */
	@Data
	public static class CommandLineArgs {
		/** ショートオプションリスト */
		private LinkedList<String> shortOptionList = new LinkedList<String>();
		/** ロングオプションマップ */
		private Map<String, String> longOptionMap = new LinkedHashMap<String, String>();
		/** 単純引数値リスト */
		private LinkedList<String> simpleArgList = new LinkedList<String>();

		/**
		 * コンストラクタ。
		 */
		public CommandLineArgs() {
		}
	}

	/** 空のオプション値 */
	public static final String OPTION_VALUE_EMPTY = "";

	/** ショートオプション接頭辞 */
	private static final String SHORT_OPTION_PREFIX = "-";
	/** ロングオプション接頭辞 */
	private static final String LONG_OPTION_PREFIX = "--";
	/** ロングオプションセパレータ */
	private static final String LONG_OPTION_SEP = "=";
	/** 引数値セパレータ */
	private static final char ARG_VALUE_SEP = ',';
	/** 引数値エスケープ文字 */
	private static final char ARG_VALUE_ESCAPE_CHAR = '\\';
	/** 複数コマンド引数値分割パターン */
	private static final String MULTI_VALUE_SPLIT_PATTERN = "(?<!" + ARG_VALUE_ESCAPE_CHAR + ")" + ARG_VALUE_SEP;

	/**
	 * コンストラクタ。
	 */
	public CommandLineHelper() {
	}

	/**
	 * コマンド引数をパースする。
	 * 
	 * @param commandArgs コマンド引数。
	 * @return コマンドライン引数。
	 * @throws ToolException ロングオプションのフォーマットがおかしい場合。
	 */
	public CommandLineArgs parseCommandArgs(String[] commandArgs) throws ToolException {
		CommandLineArgs commandLineArgs = new CommandLineArgs();

		for (String arg : commandArgs) {
			if (arg.length() > 2 && arg.startsWith(LONG_OPTION_PREFIX)) {
				// ロングオプションの場合
				String[] optionAndValue = arg.substring(LONG_OPTION_PREFIX.length()).split(LONG_OPTION_SEP, 2);

				if (optionAndValue.length == 2) {
					// オプション値がある場合
					commandLineArgs.getLongOptionMap().put(optionAndValue[0], optionAndValue[1]);
				} else {
					// オプション値がない場合
					commandLineArgs.getLongOptionMap().put(optionAndValue[0], OPTION_VALUE_EMPTY);
				}
			} else if (arg.length() == 2 && arg.startsWith(SHORT_OPTION_PREFIX)) {
				// ショートオプションの場合
				commandLineArgs.getShortOptionList().add(arg.substring(SHORT_OPTION_PREFIX.length()));
			} else {
				// それ以外の場合は単純な引数値とみなす
				commandLineArgs.getSimpleArgList().add(arg);
			}
		}

		return commandLineArgs;
	}

	/**
	 * エスケープを考慮して、カンマ区切りの複数コマンド引数値をパースする。
	 * 
	 * @param commandArgValue コマンド引数値。
	 * @return 引数値リスト。
	 */
	public List<String> parseArgValue(String commandArgValue) {
		return List.of(commandArgValue.split(MULTI_VALUE_SPLIT_PATTERN, -1));
	}
}
