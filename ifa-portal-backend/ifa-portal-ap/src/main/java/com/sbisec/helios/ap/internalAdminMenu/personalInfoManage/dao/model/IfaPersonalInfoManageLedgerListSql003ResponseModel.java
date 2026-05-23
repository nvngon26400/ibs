package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListSql003ResponseModel {

	/** 個人情報管理ID（画面非表示）. */
	private String personalInfoManageId;

	/** コード名称（ FUNCTION_ID = '015'）. 書類名*/
	private String docNameFileName;

	/** コード名称（ FUNCTION_ID = '016'）. 処理内容*/
	private String processContent;

	/** ユーザーID（全角半角）. */
	private String loginId;

	/** 個人情報取得者（全角半角）. */
	private String personalInfoAcquire;

	/** 処理日時. */
	private String processDayTime;

	/** 名前. */
	private String name;

	/** 住所（全角半角）. */
	private String adress;

	/** TEL. */
	private String tel;

	/** 勤務先. */
	private String office;

	/** 生年月日. */
	private String corBirthFlg;

	/** 性別. */
	private String gender;

	/** 資産. */
	private String assets;

	/** 職業. */
	private String profession;

	/** 部店口座. */
	private String butenAccount;

	/** Eメール. */
	private String email;

	/** 取得出金口座. */
	private String withdrawAccount;

	/** 取得データ数. */
	private String acquireDataCustomerCount;

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

	/** Cordysユーザー情報. */
	private String chargeName;

}
