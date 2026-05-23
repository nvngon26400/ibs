package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 預り区分.
 * 
 * @author shuchen.xin
 * @date 06/04/2021
 */
public enum SpecificAccount {
	// 1:一般,2:特定,4:NISA,5:ジュニア一般,6:ジュニア特定,7:ジュニアNISA口座
	GENERAL("GENERAL", "一般", "1", "一般預り"),
	SPECIFIC("SPECIFIC", "特定", "2", "特定預り"),
	SPECIFIC_MANAGED("SPECIFIC_MANAGED", "特定管理", "3", "特定管理預り"),
	NISA("NISA", "NISA", "4", "NISA預り"),
	JR_GENERAL("JR_GENERAL", "Jr一般", "5", "JrNISA－一般預り"),
	JR_SPECIFIC("JR_SPECIFIC", "Jr特定", "6", "JrNISA－特定預り"),
	JR_NISA("JR_NISA", "JrNISA", "7", "JrNISA－NISA預り"),
	GROWTH_INVESTMENT("GROWTH_INVESTMENT", "成長投資枠", "H", "NISA"),
	CONTINUOUS_MANAGEMENT("CONTINUOUS_MANAGEMENT", "継続管理勘定", "J", "JrNISA継続");

	SpecificAccount(String id, String name, String ifaCd, String ifaNm) {
		this.id = id;
		this.name = name;
		this.ifaCd = ifaCd;
		this.ifaNm = ifaNm;
	}

	private String id;
	private String name;
	private String ifaCd;
	private String ifaNm;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIfaCd() {
		return ifaCd;
	}

	public String getIfaNm() {
		return ifaNm;
	}

	public static SpecificAccount getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		SpecificAccount[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}

	public static SpecificAccount getByIfaCd(String ifaCd) {
		if (ObjectUtils.isEmpty(ifaCd)) {
			return null;
		}

		SpecificAccount[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getIfaCd().equals(ifaCd)) {
				return enums[i];
			}
		}
		return null;
	}
}
