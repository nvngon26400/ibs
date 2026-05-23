package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

public enum StRightId {

	BEFORE_SPLIT               ("before_split",         "分割前基準",        " "),
	ABNORMAL_PRICE             ("abnormal_price",       "株価異常状態",      "1"),
	AFTER_SPLIT2               ("after_split2",         "分割後基準（2）",     "2"),
	AFTER_SPLIT3               ("after_split3",         "分割後基準（3）",     "3"),
	AFTER_SPLIT4               ("after_split4",         "分割後基準（4）",     "4"),
	AFTER_SPLIT5               ("after_split5",         "分割後基準（5）",     "5");

	private final String id;
	private final String title;
	private final String code;

	private StRightId(String id, String title, String code) {
		this.id         = id;
		this.title      = title;
		this.code       = code;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCode() {
		return code;
	}
	/** code格納用 */
	private static Map<String, StRightId> codeMap = new HashMap<String, StRightId>(6);


	/**
	 * 初期化
	 *
	 */
	static{
		codeMap.put(" ",BEFORE_SPLIT);
		codeMap.put("1",ABNORMAL_PRICE);
		codeMap.put("2",AFTER_SPLIT2);
		codeMap.put("3",AFTER_SPLIT3);
		codeMap.put("4",AFTER_SPLIT4);
		codeMap.put("5",AFTER_SPLIT5);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static StRightId getInstanceByCode(String id){
		return (StRightId)codeMap.get(id);
	}

	public static StRightId valueOfId(String id) {

		if (id == null) return null;

		StRightId[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
