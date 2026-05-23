package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 追証アラートステータス
 * 
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum RemainingPowerAlertStatus {
	MARGIN_SHORTFALL("MARGIN_SHORTFALL", "新規建時の保証金不足"), MARGIN_CALL_OCCURRED("MARGIN_CALL_OCCURRED", "追証発生時（朝・概算、夕方・確定）"), MARGIN_CALL_OCCURRED_CAVEAT("MARGIN_CALL_OCCURRED_CAVEAT", "追証発生警告"),
	DEPOSIT_INSUFFICIENT_CONFIRM("DEPOSIT_INSUFFICIENT_CONFIRM", "預り金不足確定"), DEPOSIT_INSUFFICIENT_IN_PROGRESS("DEPOSIT_INSUFFICIENT_IN_PROGRESS", "預り金不足受渡日以降"),
	DEPOSIT_INSUFFICIENT_CONFIRM_AND_IN_PROGRESS("DEPOSIT_INSUFFICIENT_CONFIRM_AND_IN_PROGRESS", "預り金不足確定と預り金不足受渡日以降が同時に発生"),
	MARGIN_CALL_COVERED_FORCED_CLOSE("MARGIN_CALL_COVERED_FORCED_CLOSE", "追証期限後未解消で強制決済後"), MARGIN_CALL_OCCURRED_DEPOSIT_EXPIRED("MARGIN_CALL_OCCURRED_DEPOSIT_EXPIRED", "追証期限後未解消で強制決済前"),
	MARGIN_CALL_OCCURRED_FORCED_CLOSE("MARGIN_CALL_OCCURRED_FORCED_CLOSE", "追証期限後未解消で強制決済注文中"), MARGIN_CALL_COVERED_DEPOSIT_EXPIRED("MARGIN_CALL_COVERED_DEPOSIT_EXPIRED", "追証期限後解消");

	RemainingPowerAlertStatus(String id, String name) {
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

	public static RemainingPowerAlertStatus getById(String id) {
		if (ObjectUtils.isEmpty(id)) {
			return null;
		}

		RemainingPowerAlertStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) {
				return enums[i];
			}
		}

		return null;
	}
}
