package com.sbisec.helios.ap.common.enums.ipopo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum BBGestureValue {

	PRICE("1", "PRICE"),
	DISCOUNT("2", "DISCOUNT");

	private final String id;
	private final String label;

	private BBGestureValue(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static BBGestureValue valueOfId(String id) {

		if (id == null)
			return null;

		BBGestureValue[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return null;
	}
}
