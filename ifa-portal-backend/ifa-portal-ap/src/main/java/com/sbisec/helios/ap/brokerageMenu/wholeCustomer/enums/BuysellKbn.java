package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 新規売買区分
 * （現行から移植）
 * @author 松田
 *
 */
public enum BuysellKbn {

	BUY               ("buy",         "買",        "K",      "0"),
	SELL              ("sell",        "売",        "U",      "1");

	private final String id;
	private final String title;
	private final String code;
	private final String nriCode;

	private BuysellKbn(String id, String title, String code, String nriCode) {
		this.id         = id;
		this.title      = title;
		this.code       = code;
		this.nriCode    = nriCode;
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

	public String getNriCode() {
		return nriCode;
	}

	/** code格納用 */
	private static Map<String, BuysellKbn> codeMap = new HashMap<String, BuysellKbn>(2);
	private static Map<String, BuysellKbn> nriCodeMap = new HashMap<String, BuysellKbn>(2);


	/**
	 * 初期化
	 *
	 */
	static{
		codeMap.put("K",BUY);
		codeMap.put("U",SELL);
		nriCodeMap.put("0",BUY);
		nriCodeMap.put("1",SELL);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static BuysellKbn getInstanceByCode(String id){
		return (BuysellKbn)codeMap.get(id);
	}
	/**
	 * nriCodeからインスタンス取得。
	 * @param nriCode
	 * @return
	 */
	public static BuysellKbn getInstanceByNriCode(String nriCode){
		return (BuysellKbn)nriCodeMap.get(nriCode);
	}

	public static BuysellKbn valueOfId(String id) {

		if (id == null) return null;

		BuysellKbn[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
