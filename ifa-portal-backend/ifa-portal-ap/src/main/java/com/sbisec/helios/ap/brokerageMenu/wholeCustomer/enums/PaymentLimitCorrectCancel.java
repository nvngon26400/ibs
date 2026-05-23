package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 弁済期限（訂正・取消用）
 * @author SCSK
 *
 */
public enum PaymentLimitCorrectCancel {

	SIX_MONTH            ("six_month",            "６ヶ月（制度信用）",       "6",        "6ヶ月"),
	UNLIMITED            ("unlimited",            "無期限（一般信用）",      "9",        "無期限"),
	LONG_NOLIMIT         ("long_nolimit",         "長期(一般信用売り-長期在庫制限無し銘柄)", "C",        "長期"),
	ONEDAY_NORMAL        ("oneday_normal",        "１日（一日信用-通常銘柄）","D",        "1日"),
	ONEDAY_PREMIUM       ("oneday_premium",       "１日（一日信用-プレミアム空売り銘柄）", "E", "1日"),
	SHORT       		 ("short",       		  "短期(一般信用売り-短期銘柄)", "A", "短期"),
	LONG_LIMIT           ("long_limit",       	  "長期(一般信用売り-長期在庫制限有り銘柄)", "B", "長期");

	private final String id;
	private final String title;
	private final String code;
	private final String shortTitle;

	private PaymentLimitCorrectCancel(String id, String title, String code, String shortTitle) {
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
	private static Map<String, PaymentLimitCorrectCancel> codeMap = new HashMap<String, PaymentLimitCorrectCancel>(5);

	/**
	 * 初期化
	 */
	static{
		codeMap.put("6",SIX_MONTH);
		codeMap.put("9",UNLIMITED);
		codeMap.put("C",LONG_NOLIMIT);
		codeMap.put("D",ONEDAY_NORMAL);
		codeMap.put("E",ONEDAY_PREMIUM);
		codeMap.put("A",SHORT);
		codeMap.put("B",LONG_LIMIT);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static PaymentLimitCorrectCancel getInstanceByCode(String id){
		return (PaymentLimitCorrectCancel)codeMap.get(id);
	}

	public static PaymentLimitCorrectCancel valueOfId(String id) {

		if (id == null) return null;

		PaymentLimitCorrectCancel[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
