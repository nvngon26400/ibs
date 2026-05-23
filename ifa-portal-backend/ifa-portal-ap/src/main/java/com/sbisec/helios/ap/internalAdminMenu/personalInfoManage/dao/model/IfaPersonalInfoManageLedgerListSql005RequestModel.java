package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model;

import lombok.Data;
import java.util.List;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListSql005RequestModel {

	/** 処理対象期間（From）. */
	private String processTargetPeriodFrom;

	/** 処理対象期間（To）. */
	private String processTargetPeriodTo;

	/** 仲介業者営業員. */
	private List<IfaPersonalInfoManageLedgerListRequestModelBrokerCharge> brokerChargeList;

	/** 権限ID. */
    private String privId;

}
