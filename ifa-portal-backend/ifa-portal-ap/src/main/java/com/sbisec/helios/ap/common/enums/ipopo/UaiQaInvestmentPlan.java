package com.sbisec.helios.ap.common.enums.ipopo;

public enum UaiQaInvestmentPlan {

	NULL("", "-", ""),
	YIELD_EMPHASIZE_TABILITY("01", "利回り・安定重視", "0"),
	YIELD_EMPHASIS_PROFIT_GROWTH("02", "利回り・値上り益重視", "0"),
	INDIVIDUAL_FOCUS_ON_PROFIT_GROWTH("03", "値上り益重視", "0"),
	ACTIVELY_FOCUS_ON_PROFIT_GROWTH("04", "積極的値上り益重視", "0"),
	FOCUS_ON_SOUND_INVESTMENT("05", "その他", "0"),
	INDIVIDUAL_OTHER("09", "その他", "0"),

	POLICY_INVESTMENT("1", "政策投資", "1"),
	USE_FUNDS("2", "資金運用", "1"),
	FOCUS_ON_PRINCIPAL("3", "元本重視", "1"),
	FOCUS_ON_INTEREST_DIVIDEND("4", "利子・配当重視", "1"),
	FOCUS_ON_INTEREST_DIVIDEND_BALANCE("5", "利子・配当と値上がりのバランスを重視", "1"),
	CORPORATION_FOCUS_ON_PROFIT_GROWTH("6", "値上り益重視", "1"),
	ACTIVELY_PURSUE_PRICE_INCREASES("7", "積極的値上り追求", "1"),
	CORPORATION_OTHER("9", "その他", "1");

	private final String id;
	private final String label;
	private final String code;

	private UaiQaInvestmentPlan(String id, String label, String code) {
		this.id = id;
		this.label = label;
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getCode() {
		return code;
	}

	public static UaiQaInvestmentPlan valueOfId(String id) {

		if (id == null)
			return NULL;

		UaiQaInvestmentPlan[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return NULL;
	}
}