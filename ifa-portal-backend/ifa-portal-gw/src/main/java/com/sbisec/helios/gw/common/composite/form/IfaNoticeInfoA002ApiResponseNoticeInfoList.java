package com.sbisec.helios.gw.common.composite.form;

import lombok.Data;

@Data
public class IfaNoticeInfoA002ApiResponseNoticeInfoList {

	/** 分類（全角半角）. */
	private String cautionKind;

	/** メモ（全角半角）. */
	private String memo;

	/** 発生日. */
	private String accuralDate;

	/** 取扱者（全角半角）. */
	private String dealtPerson;

}
