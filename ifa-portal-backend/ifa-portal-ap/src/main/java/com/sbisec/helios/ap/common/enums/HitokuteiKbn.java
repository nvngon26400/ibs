package com.sbisec.helios.ap.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum HitokuteiKbn {

	TOKUTEI               ("tokutei",               "特定預り",                         "0",     "2",    "特定預り"),
	HITOKUTEI             ("hitokutei",             "非特定預り",                       "1",     "1",    "一般預り"),
	SEIDOTAISYOGAI        ("seidotaisyogai",        "制度対象外商品",                   "2",     "1",    "一般預り"),
	HITOKUTEIORMITOUROKU  ("hitokuteiormitouroku",  "非特定口座預り又は未登録口座預り",     "-",     "1",    "一般預り"),
	NISA                  ("nisa",                  "NISA預り",                        "4",     "4",    "NISA預り"),
	TUMITATENISA         ("tumitatenisa",         "つみたてNISA預り",                  "8",     "9",    "つみたてNISA預り"),
	JNISA_TOKUTEI         ("jnisa_tokutei",         "ジュニアNISA口座-特定預り",           "5",     "6",    "ジュニア特定預り"),
	JNISA_HITOKUTEI       ("jnisa_hitokutei",       "ジュニアNISA口座-一般預り",           "6",     "5",    "ジュニア一般預り"),
	JNISA_NISA            ("jnisa_nisa",            "ジュニアNISA口座-NISA預り",           "7",     "8",    "ジュニアNISA預り");

	private final String id;
	private final String title;
	private final String code;
	private final String ifaCode;
	private final String shortName;

	private HitokuteiKbn(String id, String title, String code, String ifaCode, String shortName) {
		this.id         = id;
		this.title      = title;
		this.code       = code;
		this.ifaCode    = ifaCode;
		this.shortName  = shortName;
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

	public String getIfaCode() {
		return ifaCode;
	}

	public String getShortName() {
		return shortName;
	}

	/** code格納用 */
	private static Map<String,HitokuteiKbn> codeMap = new HashMap<String,HitokuteiKbn>(8);
	/** ifaCode格納用 */
	private static Map<String,HitokuteiKbn> ifaCodeMap = new HashMap<String,HitokuteiKbn>(8);
	/**
	 * 初期化
	 *
	 */
	static{
		codeMap.put("0",TOKUTEI);
		codeMap.put("1",HITOKUTEI);
		codeMap.put("2",SEIDOTAISYOGAI);
		codeMap.put("-",HITOKUTEIORMITOUROKU);
		codeMap.put("4",NISA);
		codeMap.put("8",TUMITATENISA);
		codeMap.put("5",JNISA_TOKUTEI);
		codeMap.put("6",JNISA_HITOKUTEI);
		codeMap.put("7",JNISA_NISA);

		ifaCodeMap.put("1",HITOKUTEI);
		ifaCodeMap.put("2",TOKUTEI);
		ifaCodeMap.put("4",NISA);
		ifaCodeMap.put("9",TUMITATENISA);
		ifaCodeMap.put("5",JNISA_HITOKUTEI);
		ifaCodeMap.put("6",JNISA_TOKUTEI);
		ifaCodeMap.put("8",JNISA_NISA);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static HitokuteiKbn getInstanceByCode(String id){
		return codeMap.get(id);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static HitokuteiKbn getInstanceByIfaCode(String id){
		return ifaCodeMap.get(id);
	}



	public static HitokuteiKbn valueOfId(String id) {

		if (id == null) return null;

		HitokuteiKbn[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
