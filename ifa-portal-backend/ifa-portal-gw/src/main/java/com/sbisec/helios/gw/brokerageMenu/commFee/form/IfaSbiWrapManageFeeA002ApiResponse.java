package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaSbiWrapManageFeeA002ApiResponse {

	/** SBIラップ管理報酬情報リスト. */
	private List<IfaSbiWrapManageFeeApiSbiWrapManageFeeInfoList> sbiWrapManageFeeInfoList;

}
