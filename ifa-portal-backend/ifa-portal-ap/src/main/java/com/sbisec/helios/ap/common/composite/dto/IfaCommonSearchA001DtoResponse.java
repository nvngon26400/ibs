package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

@Data
public class IfaCommonSearchA001DtoResponse {

	/** 仲介業者コード自動表示フラグ. */
	private String brokerCodeAutoDispFlag;

	/** 支店コード自動表示フラグ. */
	private String branchCodeAutoDispFlag;

	/** 営業員コード自動表示フラグ. */
	private String empCodeAutoDispFlag;

}
