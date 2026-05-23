package com.sbisec.helios.tool.helper;

import java.util.Locale;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.sbisec.helios.tool.exception.ToolException;

import lombok.extern.slf4j.Slf4j;

/**
 * ロガーヘルパークラス。
 * 
 * @author SCSK宮坂
 */
@Component
@Slf4j
public class LoggerHelper {
	/** メッセージソース */
	@Autowired
	private MessageSource messageSource;

	/**
	 * コンストラクタ。
	 */
	public LoggerHelper() {
	}

	/**
	 * ログメッセージを取得する。
	 * 
	 * @param code メッセージコード。
	 * @param args メッセージ埋め込み情報。
	 * @return ログメッセージ。
	 */
	public String getMessage(String code, Object... args) {
		String message = null;

		try {
			// メッセージフォーマットを取得
			String messageFormat = messageSource.getMessage(code, null, Locale.JAPAN);

			// メッセージ埋め込み情報を適用
			FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messageFormat, args);

			// メッセージを取得
			message = formattingTuple.getMessage();
		} catch (NoSuchMessageException e) {
			// ログ出力できなくてもエラー扱いとしない
			log.error(e.getMessage());
		}

		return message;
	}

	/**
	 * 情報ログを出力する。
	 * 
	 * @param code メッセージコード。
	 * @param args メッセージ埋め込み情報。
	 */
	public void printInfoLog(String code, Object... args) {
		log.info(getMessage(code, args));
	}

	/**
	 * エラーログを出力する。
	 * 
	 * @param code メッセージコード。
	 * @param args メッセージ埋め込み情報。
	 */
	public void printErrorLog(String code, Object... args) {
		printErrorLog(code, null, args);
	}

	/**
	 * エラーログを出力する。
	 * 
	 * @param code      メッセージコード。
	 * @param throwable 例外オブジェクト。
	 * @param args      メッセージ埋め込み情報。
	 */
	public void printErrorLog(String code, Throwable throwable, Object... args) {
		log.error(getMessage(code, args), throwable);
	}

	/**
	 * エラーログを出力する。
	 * 
	 * @param toolException ツール例外。
	 */
	public void printErrorLog(ToolException toolException) {
		// 原因となる例外が存在する場合のみスタックトレースを出力
		if (toolException.getCause() == null) {
			log.error(toolException.getMessage());
		} else {
			log.error(toolException.getMessage(), toolException);
		}
	}
}
