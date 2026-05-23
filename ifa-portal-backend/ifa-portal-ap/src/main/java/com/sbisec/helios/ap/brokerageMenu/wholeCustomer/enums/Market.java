package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 新規市場
 * （現行から移植）
 * @author 松田
 *
 */
public enum Market {

	TKY				("TKY",		"TKY",		"東証",		"0",		"T"),
	OSK				("OSK",		"OSK",		"大証",		"1",		null),
	NGY				("NGY",		"null",		"名証",		"2",		"M"),
	FKO				("FKO",		"null",		"福証",		"6",		"FK"),
	JNX				("JNX",		"null",		"PTS",		"7",		null),
	SPR				("SPR",		"null",		"札証",		"8",		"S"),
	OTC				("OTC",		"JDQ",		"JASDAQ",	"9",		"T"),
	JNF				("JNF",		"null",		"PTS(X)",	"X",		null);


	private final String id;
	private final String nricontract;
	private final String title;
	private final String nriCode;
	private final String exchCode;

	private Market(String id, String nricontract, String title, String nirCode, String exchCode) {
		this.id          = id;
		this.nricontract = nricontract;
		this.title       = title;
		this.nriCode     = nirCode;
		this.exchCode    = exchCode;
	}

	public String getId() {
		return id;
	}

	public String getNricontract() {
		return nricontract;
	}

	public String getTitle() {
		return title;
	}

	public String getNriCode() {
		return nriCode;
	}

	public String getExchCode() {
		return exchCode;
	}


	/** nri格納用 */
	private static Map<String, Market> nriMap = new HashMap<String, Market>(8);

	/** nricontract格納用 */
	private static Map<String, Market> nricontractMap = new HashMap<String, Market>(8);

	/** exchCode格納用 */
	private static Map<String, Market> exchCodeMap = new HashMap<String, Market>(8);
	/**
	 * 初期化
	 *
	 */
	static{
		nriMap.put("0",TKY);
		nriMap.put("1",OSK);
		nriMap.put("2",NGY);
		nriMap.put("6",FKO);
		nriMap.put("7",JNX);
		nriMap.put("8",SPR);
		nriMap.put("9",OTC);
		nriMap.put("X",JNF);

		nricontractMap.put("TKY",TKY);
		nricontractMap.put("OSK",OSK);
		nricontractMap.put("JDQ",OTC);

		exchCodeMap.put("T",  TKY);
		exchCodeMap.put("M",  NGY);
		exchCodeMap.put("FK", FKO);
		exchCodeMap.put("S",  SPR);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static Market getInstanceByNri(String id){
		return (Market)nriMap.get(id);
	}

	/**
	 * NRIcontractからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static Market getInstanceByNricontract(String id){
		return (Market)nricontractMap.get(id);
	}

	/**
	 * IDからインスタンス取得。
	 * @param id
	 * @return
	 */
	public static Market getInstanceByExchCode(String id){
		return (Market)exchCodeMap.get(id);
	}

	public static Market valueOfId(String id) {

		if (id == null) return null;

		Market[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
