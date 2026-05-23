package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般信用売弁済期限年月日区分
 * （現行から移植）
 * @author 松田
 *
 */
public enum IppanMgPaymentKbn {

	YEAR            ("year",             "年",            "Y"),
	MONTH           ("month",            "月",           "M"),
	DAY             ("day",              "日",           "D"),
	UNLIMITED       ("unlimited",        "無期限",        " ");

	private final String id;
	private final String title;
	private final String code;

	private IppanMgPaymentKbn(String id, String title, String code) {
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
	private static Map<String, IppanMgPaymentKbn> codeMap = new HashMap<String, IppanMgPaymentKbn>(4);

	/**
	 * 初期化
	 */
	static{
		codeMap.put("Y",YEAR);
		codeMap.put("M",MONTH);
		codeMap.put("D",DAY);
		codeMap.put(" ",UNLIMITED);
	}

	public static IppanMgPaymentKbn getInstanceByCode(String id){
		return (IppanMgPaymentKbn)codeMap.get(id);
	}

	public static IppanMgPaymentKbn valueOfCode(String code) {

		if (code == null) return null;

		IppanMgPaymentKbn[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(code)) return enums[i];
		}

		return null;
	}
}
