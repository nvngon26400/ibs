package com.sbisec.helios.ap.common.enums.ipopo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum LotteryResult {

	NOT_LOTTERY("0", "未抽選"), WINNING("1", "当選"), NOT_WINNING("2", "落選"), WINNING_UP("3", "当選(繰上)"),
	SAIRYOU_ALLOCATION("4", "裁量配分"), APPLY_CANCEL("5", "申込取消");

	private final String id;
	private final String label;

	private LotteryResult(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static LotteryResult valueOfId(String id) {

		if (id == null)
			return null;

		LotteryResult[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return null;
	}
}
