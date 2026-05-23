package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail;

import lombok.Data;

/**
 * 投信積立設定解除確認 SQL001 リクエストパラメータ
 *
 * @author WJL
 *
 *     2025/04/13 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel {
	// 解除対象積立設定リスト
	private List<IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail> sql001DetailList;
}
