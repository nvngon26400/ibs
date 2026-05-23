package com.sbisec.helios.ap.common.enums.ipopo;

public enum FinancialAssets {

	NULL("", "-"),
	LESS_THREE_MILLION("01", "300万円未満"),
	THREE_MILLION_TO_FIVE_MILLION("02", "300～500万円"),
	FIVE_MILLION_TO_TEN_MILLION("03", "500～1000万円"),
	TEN_MILLION_TO_TWENTY_MILLION("04", "1000～2000万円"),
	TWENTY_MILLION_TO_THIRTY_MILLION("05", "2000～3000万円"),
	THIRTY_MILLION_TO_FIFTY_MILLION("06", "3000～5000万円"),
	FIVE_MILLION_TO_ONE_HUNDRED_MILLION("07", "5000万円～1億円"),
    ONE_HUNDRED_MILLION_TO_FIVE_HUNDRED_MILLION("08", "1億～5億円"),
	LESS_FIVE_MILLION_UNUSED("11", "500万円未満(未使用)"),
	LESS_TEN_MILLION_UNUSED("12", "1000万円未満(未使用)"),
	LESS_THIRTY_MILLION_UNUSED("13", "3000万円未満(未使用)"),
	LESS_FIFTY_MILLION_UNUSED("14", "5000万円未満(未使用)"),
	LESS_ONE_HUNDRED_MILLION_UNUSED("15", "1億円未満(未使用)"),
	LESS_FIVE_HUNDRED_MILLION_UNUSED("16", "5億円未満(未使用)"),
	OVER_FIVE_HUNDRED_MILLION_UNUSED("17", "5億円以上(未使用)"),
	OVER_FIVE_HUNDRED_MILLION("99", "5億円以上");

	private final String id;
	private final String label;

	private FinancialAssets(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static FinancialAssets valueOfId(String id) {

		if (id == null)
			return NULL;

		FinancialAssets[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return NULL;
	}
}
