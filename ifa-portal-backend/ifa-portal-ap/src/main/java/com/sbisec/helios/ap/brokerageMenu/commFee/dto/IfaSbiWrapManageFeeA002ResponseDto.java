package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaSbiWrapManageFeeA002ResponseDto {

	/** SBIラップ管理報酬情報リスト. */
	private List<IfaSbiWrapManageFeeSbiWrapManageFeeInfoList> sbiWrapManageFeeInfoList;

}
