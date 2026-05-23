package com.sbisec.helios.ap.common.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * オブジェクトダンプユーティリティクラス。<br>
 * デバッグ向けの機能であるため、業務ロジックとしては使用しないことを推奨する。
 * 
 * @author SCSK宮坂
 */
public class ObjectDumpUtil {
	/** オブジェクト文字列化スタイルクラス */
	private static class ObjectStringStyle extends ToStringStyle {
		/** シリアルバージョンUID */
		private static final long serialVersionUID = 1L;

		/** 日時フォーマット */
		private String dateTimeFormat = null;

		/**
		 * コンストラクタ。
		 */
		public ObjectStringStyle(String dateTimeFormat) {
			super();

			this.dateTimeFormat = dateTimeFormat;

			// 項目開始文字
			setContentStart(CONTENT_START_TEXT);
			// 項目終了文字
			setContentEnd(CONTENT_END_TEXT);
			// フィールド間セパレータ
			setFieldSeparator(FIELD_SEP);
			// 短縮形のクラス名
			setUseShortClassName(false);
			// ハッシュコード出力
			setUseIdentityHashCode(true);
			// 項目開始時の項目間セパレータ
			setFieldSeparatorAtStart(false);
			// NULLオブジェクト表現
			setNullText(NULL_OBJECT_TEXT);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected void appendDetail(final StringBuffer buffer, final String fieldName, final Object value) {
			// 変換フォーマットをあわせるため呼び出し
			String valueText = convertStandardObject(value, dateTimeFormat);

			if (valueText != null) {
				buffer.append(valueText);
			} else {
				// 変換対象外の場合はスーパークラスに委譲
				super.appendDetail(buffer, fieldName, value);
			}
		}
	}

	/** 項目開始文字列 */
	private static final String CONTENT_START_TEXT = " [";
	/** 項目終了文字列 */
	private static final String CONTENT_END_TEXT = "]";
	/** 項目間セパレーター */
	private static final String FIELD_SEP = " ";
	/** NULLオブジェクト表現 */
	private static final String NULL_OBJECT_TEXT = "<null>";
	/** デフォルト日時フォーマット */
	private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";
	/** デフォルトオブジェクトダンプフォーマット */
	private static final String DEFAULT_OBJECT_DUMP_FORMAT = "{0}@{1}{2}{3}{4}";
	/** nullオブジェクトダンプフォーマット */
	private static final String NULL_OBJECT_DUMP_FORMAT = "{0}";

	/**
	 * オブジェクトが保持するすべてのフィールドをtoStringメソッドにより文字列変換する。
	 * 
	 * @param object オブジェクト。
	 * @return オブジェクトの文字列表現。
	 */
	public static String reflectionToString(Object object) {
		return reflectionToString(object, DEFAULT_DATE_TIME_FORMAT);
	}

	/**
	 * オブジェクトが保持するすべてのフィールドをtoStringメソッドにより文字列変換する。
	 * 
	 * @param object         オブジェクト。
	 * @param dateTimeFormat 日時フォーマット。
	 * @return オブジェクトの文字列表現。
	 */
	public static String reflectionToString(Object object, String dateTimeFormat) {
		String objectText = null;

		if (object != null) {
			// プロジェクト使用のJava標準型を文字列変換
			objectText = convertStandardObject(object, dateTimeFormat);

			if (objectText != null) {
				// トップレベルがJava標準型の場合はエラーになるので直接変換
				objectText = MessageFormat.format(DEFAULT_OBJECT_DUMP_FORMAT, object.getClass().getName(), object.hashCode(), CONTENT_START_TEXT, objectText, CONTENT_END_TEXT);
			} else {
				// トップレベルがJava標準型ではない場合はcommonsに委譲
				objectText = ToStringBuilder.reflectionToString(object, new ObjectStringStyle(dateTimeFormat));
			}
		} else {
			// トップレベルがnullの場合はエラーになるので考慮
			objectText = MessageFormat.format(NULL_OBJECT_DUMP_FORMAT, NULL_OBJECT_TEXT);
		}

		return objectText;
	}

	/**
	 * プロジェクトで使用するJava標準型を文字列化する。
	 * 
	 * @param object         オブジェクト。
	 * @param dateTimeFormat 日時フォーマット。
	 * @return オブジェクトの文字列表現。プロジェクトで使用するJava標準型ではない場合はnull。
	 */
	private static String convertStandardObject(Object object, String dateTimeFormat) {
		String objectText = null;

		if (object instanceof BigDecimal) {
			// 小数点を考慮して文字列化
			objectText = ((BigDecimal) object).toPlainString();
		} else if (object instanceof Date) {
			// 指定フォーマットで文字列化
			objectText = new SimpleDateFormat(dateTimeFormat).format(((Date) object));
		} else if (object instanceof String || object instanceof Character || object instanceof Number || object instanceof Boolean ||
				object instanceof Collection<?> || object instanceof Map<?, ?>) {
			// Java標準、またはlombok依存でそのまま文字列化
			objectText = object.toString();
		}

		return objectText;
	}

	/**
	 * コンストラクタ。
	 */
	private ObjectDumpUtil() {
	}
}
