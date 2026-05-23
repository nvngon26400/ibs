package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum PrivId {

    HEAD_OFFICE            ( "1", null,         "本店"),
    BRANCH                 ( "2", null,         "支店"),
    B_INNER_MANAGEMENT     ( "3", "仲介業者",    "内管"),
    B_SALES_EXECUTIVE      ( "4", "仲介業者",    "営責"),
    B_SALES_REPRESENTATIVE ( "5", "仲介業者",    "外務"),
    BB_INNER_MANAGEMENT    ( "6", "仲介業者支店", "内管"),
    BB_SALES_EXECUTIVE     ( "7", "仲介業者支店", "営責"),
    BB_SALES_REPRESENTATIVE( "8", "仲介業者支店", "外務"),
    RESPONSIBLE            ( "9", null,         "担当"),
    ALL_RESPONSIBLE        ("10", null,         "全担当"),
    ES_BUSSINESS           ("11", "外株",        "業務部"),
    ES_MANAGEMENT          ("12", "外株",        "管理部"),
    B_OUT_SALES_REPRESENTATIVE           ("13", "仲介業者",        "外務(外出先アクセス専用)"),
    BB_OUT_SALES_REPRESENTATIVE           ("14", "仲介業者支店",        "外務(外出先アクセス専用)"),
    OUT_RESPONSIBLE          ("15", null,        "担当(外出先アクセス専用)");

	private final String id;
	private final String groupLabel;
	private final String label;

	private PrivId(String id, String groupLabel, String label) {
		this.id         = id;
		this.groupLabel = groupLabel;
		this.label      = label;
	}

	public String getId() {
		return id;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public String getLabel() {
		return label;
	}

	public static PrivId valueOfId(String id) {

		if (id == null) return null;

		PrivId[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id)) return enums[i];
		}

		return null;
	}
}
