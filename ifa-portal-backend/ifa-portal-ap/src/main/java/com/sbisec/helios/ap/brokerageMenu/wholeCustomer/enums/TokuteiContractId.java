package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 信用建玉明細の預り
 * （現行から移植）
 * @author 松田
 *
 */
public enum TokuteiContractId {

	HITOKUTEIORMITOUROKU  ("hitokuteiormitouroku",   "非特定口座/未登録口座建玉",       "-",        "一般預り"),
	HITOKUTEI             ("hitokutei",              "非特定建玉（特定口座）",           "0",        "一般預り"),
	TOKUTEI               ("tokutei",                "特定建玉（特定口座）",             "1",        "特定預り");

	private final String id;
	private final String title;
	private final String code;
	private final String shortTitle;

	private TokuteiContractId(String id, String title, String code, String shortTitle) {
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
	private static Map<String, TokuteiContractId> codeMap = new HashMap<String, TokuteiContractId>(3);

	/**
	 * 初期化
	 */
	static{
		codeMap.put("-",HITOKUTEIORMITOUROKU);
		codeMap.put("0",HITOKUTEI);
		codeMap.put("1",TOKUTEI);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static TokuteiContractId getInstanceByCode(String id){
		return (TokuteiContractId)codeMap.get(id);
	}

	public static TokuteiContractId valueOfId(String id) {

		if (id == null) return null;

		TokuteiContractId[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
