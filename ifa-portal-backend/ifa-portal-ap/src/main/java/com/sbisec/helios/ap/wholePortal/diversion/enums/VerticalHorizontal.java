package com.sbisec.helios.ap.wholePortal.diversion.enums;

public enum VerticalHorizontal {
	VERTICAL("V", "縦"),
	HORIZONTAL("H", "横");

	private final String id;
	private final String label;

	private VerticalHorizontal(String id, String label) {
		this.id    = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static VerticalHorizontal valueOfId(String id) {

		if (id == null) return null;

		VerticalHorizontal[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}

}
