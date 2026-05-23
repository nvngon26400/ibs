package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:口座分類
 * @author xiu.yan
 * @date: 02/23/2022
 */
public enum JrNisaAccountStatus {
	NOT_JR_ACCOUNT("NOT_JR_ACCOUNT", "ジュニアNISA対象外口座(一度もジュニアNISAを空けていない)"), 
	RESTRICTED_RELEASE_BEFORE("RESTRICTED_RELEASE_BEFORE", "払出制限解除前"),
	PAYOUT_CLOSE("PAYOUT_CLOSE", "要件外払出による閉鎖"),
	RESTRICTED_RELEASE_AFTER("RESTRICTED_RELEASE_AFTER", "払出制限解除後未成年"),
	JR_BUY_END("JR_BUY_END", "ジュニア非課税買付終了"),
	JR_SELL_END("JR_SELL_END", "ジュニア非課税売却終了"),
	JR_SELL_HISTORY_END("JR_SELL_HISTORY_END", "ジュニア非課税売却履歴表示期限終了"),
	PAYOUT_CLOSE_TWO_YEARS_OVER("PAYOUT_CLOSE_TWO_YEARS_OVER", "要件外払出による閉鎖から2年以上経過");

	JrNisaAccountStatus(String id, String name) {
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

	public static JrNisaAccountStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		JrNisaAccountStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
