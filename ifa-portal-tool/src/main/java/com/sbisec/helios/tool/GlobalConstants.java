package com.sbisec.helios.tool;

import lombok.Getter;

/**
 * グローバル定数定義クラス。
 * 
 * @author SCSK宮坂
 */
public final class GlobalConstants {
	/**
	 * 文字列値保持enum用インターフェース。
	 */
	public interface StringValueEnum {
		/**
		 * 文字列enum値を取得する。
		 *
		 * @return 文字列enum値。
		 */
		public String getValue();

		/**
		 * 文字列値から該当するenumオブジェクトを取得する。
		 * 
		 * @param <T>         文字列値保持enum。
		 * @param enumObjects 文字列値保持enumオブジェクト配列。
		 * @param textValue   文字列値。
		 * @return 文字列値保持enum。存在しない場合はnull。
		 */
		public static <T extends StringValueEnum> T parse(T[] enumObjects, String value) {
			T returnEnumObject = null;

			if (value != null) {
				// コマンドと一致するenumを取得
				for (T enumObject : enumObjects) {
					if (enumObject.getValue().equals(value)) {
						returnEnumObject = enumObject;

						break;
					}
				}
			}

			return returnEnumObject;
		}
	}

	/**
	 * 区分値保持enum用インターフェース。
	 */
	public interface TypeValueEnum {
		/**
		 * 区分グループ名を取得する。
		 *
		 * @return 区分グループ名。
		 */
		public String getGroupName();

		/**
		 * 区分名を取得する。
		 *
		 * @return 区分名。
		 */
		public String getName();

		/**
		 * 区分値を取得する。
		 *
		 * @return 区分値。
		 */
		public int getValue();

		/**
		 * 区分値から該当するenumオブジェクトを取得する。
		 * 
		 * @param <T>         区分値保持enum。
		 * @param enumObjects 区分値保持enumオブジェクト配列。
		 * @param textValue   区分値。
		 * @return 区分値保持enum。存在しない場合はnull。
		 */
		public static <T extends TypeValueEnum> T parse(T[] enumObjects, int value) {
			T returnEnumObject = null;

			// コマンドと一致するenumを取得
			for (T enumObject : enumObjects) {
				if (enumObject.getValue() == value) {
					returnEnumObject = enumObject;

					break;
				}
			}

			return returnEnumObject;
		}
	}

	/** ツール種別 */
	@Getter
	public enum TOOL_TYPE implements StringValueEnum {
		/** spring Redis CLIツール */
		SPRING_REDIS_CLI("spring-redis-cli", "spring Redis CLIツール"),
		/** ツールメイン */
		TOOL_MAIN("ifa-portal-tool", "IFAポータル CLIツール");

		/** 値 */
		private String value = null;
		/** 説明 */
		private String description = null;

		/**
		 * コンストラクタ。
		 *
		 * @param value      値。
		 * @param descrption 説明。
		 */
		private TOOL_TYPE(String value, String description) {
			this.value = value;
			this.description = description;
		}
	}

	/** ツール実行結果区分 */
	@Getter
	public enum TOOL_RESULT implements TypeValueEnum {
		/** 不明 */
		UNKNOWN("不明", -1),
		/** 正常終了 */
		SUCCESS("正常終了", 0),
		/** 警告終了 */
		WARNING("警告終了", 1),
		/** 異常終了 */
		FAIRULE("異常終了", 9);

		/** 区分グループ名 */
		private String groupName = null;
		/** 区分名 */
		private String name = null;
		/** 区分値 */
		private int value = 0;

		/**
		 * コンストラクタ
		 *
		 * @param name  区分名。
		 * @param value 区分値。
		 */
		private TOOL_RESULT(String name, int value) {
			this.groupName = "ツール実行結果区分";
			this.name = name;
			this.value = value;
		}
	}

	/** logbackログ項目キー（ツール名） */
	public static final String LOG_ITEM_KEY_TOOL_TYPE = "tool";
}
