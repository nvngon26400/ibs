package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ErrorLevel {

	SUCCESS (0, "正常"),
	INFO (1, "情報"),
	WARNING (2, "警告"),
	FATAL   (-1, "致命的" ),
    SYSTEM_ERROR   (-2, "システムエラー" );

	private final int id;
	private final String label;

	private ErrorLevel(int id, String label) {
		this.id    = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static ErrorLevel valueOfId(int id) {

		//if (id == null) return null;

		ErrorLevel[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId() == id) return enums[i];
		}

		return null;
	}

}