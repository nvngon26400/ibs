package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum AccControlId {

	ENABLE		(1, "編集可"),
	DISABLED 	(2, "編集不可"),
	HIDE		(3, "非表示"),
	READ_ONLY	(4, "読取専用" );

	private final int id;
	private final String label;

	private AccControlId(int id, String label) {
		this.id    = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static AccControlId valueOfId(int id) {

		//if (id == null) return null;

		AccControlId[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId() == id) return enums[i];
		}

		return null;
	}

}
