package com.sbisec.helios.ap.common.enums.ipopo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum OrderStatus {

	NULL("", "-"),
	NOT_ORDER("0", "未注文"),
	OFFERING_INPUT("1", "募集入力済"),
	RECEPTION("2", "受付済"),
	APPOINTMENT("3","約定済"),
	UNSATISFACTORY("4", "不出来"),
	MANUAL_INPUT("5", "手入力");

	private final String id;
	private final String label;

	private OrderStatus(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static OrderStatus valueOfId(String id) {

		if (id == null)
			return null;

		OrderStatus[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return null;
	}
}
