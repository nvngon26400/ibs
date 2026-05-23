package com.sbisec.helios.tool.service;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbisec.helios.tool.GlobalConstants;
import com.sbisec.helios.tool.GlobalConstants.TOOL_RESULT;
import com.sbisec.helios.tool.GlobalConstants.TOOL_TYPE;
import com.sbisec.helios.tool.exception.ToolException;
import com.sbisec.helios.tool.helper.LoggerHelper;

/**
 * ツールサービス基底クラス。
 * 
 * @author SCSK宮坂
 */
@Service
public abstract class ToolServiceBase {
	/** ツール種別 */
	private TOOL_TYPE toolType = null;

	/** ロガーヘルパー */
	@Autowired
	private LoggerHelper loggerHelper;

	/**
	 * コンストラクタ。
	 * 
	 * @param toolType
	 */
	public ToolServiceBase(TOOL_TYPE toolType) {
		this.toolType = toolType;
	}

	/**
	 * ツールを起動する。
	 * 
	 * @param commandArgs コマンド引数。
	 * @return ツール実行結果区分。
	 * @throws Exception 想定外のエラーが発生した場合。
	 */
	public TOOL_RESULT run(String... commandArgs) throws Exception {
		TOOL_RESULT toolResult = TOOL_RESULT.UNKNOWN;

		try {
			// ログ項目キーを設定
			MDC.put(GlobalConstants.LOG_ITEM_KEY_TOOL_TYPE, toolType.getValue());

			// ツールロジックを呼び出し
			toolResult = execute(commandArgs);

			// この段階でツール実行結果が不明の場合は警告終了とみなす
			if (toolResult == TOOL_RESULT.UNKNOWN) {
				toolResult = TOOL_RESULT.WARNING;
			}
		} catch (ToolException e) {
			loggerHelper.printErrorLog(e);

			toolResult = TOOL_RESULT.FAIRULE;
		}

		return toolResult;
	}

	/**
	 * ツールを実行する。
	 * 
	 * @param commandArgs コマンド引数。
	 * @return ツール実行結果区分。
	 * @throws Exception ツール例外が発生した場合、想定外のエラーが発生した場合。
	 */
	protected abstract TOOL_RESULT execute(String... commandArgs) throws Exception;
}
