package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 弁済期限
 * （現行から移植）
 * @author 松田
 *
 */
public enum PaymentLimitStar {

	SIX_MONTH            ("six_month",            "６ヶ月（制度信用）",       "6",        "6ヶ月"),
	UNLIMITED            ("unlimited",            "無期限（一般信用）",      "9",        "無期限"),
	ONEDAY               ("oneday",               "１日（一般信用）",        "A",        "1日"),
	FIVEDAYS             ("fivedays",             "５日（一般信用）",        "B",        "5日"),
	FIFTEENDAYS          ("fifteendays",          "１５日（一般信用）",       "D",        "15日");

	private final String id;
	private final String title;
	private final String code;
	private final String shortTitle;

	private PaymentLimitStar(String id, String title, String code, String shortTitle) {
		this.id         = id;
		this.title      = title;
		this.code       = code;
		this.shortTitle = shortTitle;
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

	public String getShortTitle() {
		return shortTitle;
	}


	/** code格納用 */
	private static Map<String, PaymentLimitStar> codeMap = new HashMap<String, PaymentLimitStar>(5);

	/**
	 * 初期化
	 */
	static{
		codeMap.put("6",SIX_MONTH);
		codeMap.put("9",UNLIMITED);
		codeMap.put("A",ONEDAY);
		codeMap.put("B",FIVEDAYS);
		codeMap.put("D",FIFTEENDAYS);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static PaymentLimitStar getInstanceByCode(String id){
		return (PaymentLimitStar)codeMap.get(id);
	}

	public static PaymentLimitStar valueOfId(String id) {

		if (id == null) return null;

		PaymentLimitStar[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
