package com.sbisec.helios.ap.common.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * 通貨コード
 * @author 松田
 */
public enum CurrencyCode {
    USD("USD","米ドル"),
    HKD("HKD","香港ドル"),
    EUR("EUR","ユーロ"),
    AUD("AUD","オーストラリアドル"),
    NZD("NZD","ニュージーランドドル"),
    CAD("CAD","カナダドル"),
    ZAR("ZAR","ランド"),
    MXN("MXN","メキシコペソ"),
    TRY("TRY","トルコリラ"),
    SGD("SGD","シンガポールドル"),
    KRW("KRW","ウォン"),
    RUB("RUB","ロシアルーブル"),
    VND("VND","ドン"),
    IDR("IDR","ルピア"),
    THB("THB","バーツ"),
    MYR("MYR","マレーシアリンギット"),
    CNY("CNY","人民元")
	;

	CurrencyCode(String id, String name) {
		this.id = id;
		this.name = name;
	}

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static CurrencyCode getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		CurrencyCode[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
