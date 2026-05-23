package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListSql004RequestModel {	
	/** ログインID. */
	private String loginId;
	
	/** 個人情報管理ID（画面非表示）. */
	private String personalInfoManageId;

	/** 保管_送付媒体. */
	private String storageSendingMedium;

	/** 預託先. */
	private String depositDestinations;
	
	/** 提供先. */
	private String destination;

	/** 保管場所. */
	private String storageSpace;

	/** 保管期間（全角半角）. */
	private String preservePeriod;
	
	/** 廃棄方法. */
	private String disposeMethod;
	
	/** 破棄なし. */
	private String notDispose;
	
	/** 廃棄年月日. */
	private String disposeDateYmd;

	/** 摘要_預託先. */
	private String corDepositOutline;

	/** 摘要_提供先. */
	private String corDonationOutline;

	/** 摘要_保管場所. */
	private String corDepositoryOutline;	
}
