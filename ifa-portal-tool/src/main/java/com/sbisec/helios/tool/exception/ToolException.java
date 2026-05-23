package com.sbisec.helios.tool.exception;

import com.sbisec.helios.tool.helper.LoggerHelper;
import com.sbisec.helios.tool.utils.ApplicationContextUtils;

/**
 * ツール例外クラス。
 * 
 * @author SCSK宮坂
 */
public class ToolException extends IllegalStateException {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ。
	 * 
	 * @param message メッセージ。
	 */
	protected ToolException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ。
	 * 
	 * @param message メッセージ。
	 * @param cause   原因となる例外。
	 */
	protected ToolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 例外オブジェクトを構築する。
	 * 
	 * @param messageCode メッセージコード。
	 * @param messageArgs メッセージ埋め込み情報。
	 * @return 例外オブジェクト。
	 */
	public static ToolException build(String messageCode, Object... messageArgs) {
		return new ToolException(ApplicationContextUtils.getBean(LoggerHelper.class).getMessage(messageCode, messageArgs));
	}

	/**
	 * 例外オブジェクトを構築する。
	 * 
	 * @param messageCode メッセージコード。
	 * @param cause       原因となる例外。
	 * @param messageArgs メッセージ埋め込み情報。
	 * @return 例外オブジェクト。
	 */
	public static ToolException build(String messageCode, Throwable cause, Object... messageArgs) {
		return new ToolException(ApplicationContextUtils.getBean(LoggerHelper.class).getMessage(messageCode, messageArgs), cause);
	}
}