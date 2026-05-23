package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaNotificationViewStatusLookupCsvItems extends ModelBase {

    /** シリアルバージョンID */
    private static final long serialVersionUID = 1L;

    /** 仲介業者支店名. */
	private String branchName;

	/** 仲介業者担当者名（全角半角）. */
	private String employeeName;

	/** 既読フラグ（数字）. */
	private String t5nReadFlg;

	/** 閲覧日. */
	private String readDate;

}
