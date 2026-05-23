package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginUserManageManagerLookupSql001ResponseModel {

	/** ユーザーID（全角半角）. */
	private String userId;

	/** ユーザー名. */
	private String userName;

	/** パスワード（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')）. */
	private String password;

	/** 最後パスワード更新日時. */
	private String passwordUpdateTime;

	/** 権限コード（全角半角）. */
	private String privId;

	/** SBI証券支店コード（数字）. */
	private String sbiSecurityCode;

	/** 仲介業者コード（数字）. */
	private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
	/** 仲介業者支店コード（数字）. */
	private String subBrokerId;

	/** 仲介業者担当者コード（数字）. */
	private String employeeId;

	/** 仲介業者担当者名（全角半角）. */
	private String employeeName;

	/** 管理者フラグ（全角）. */
	private String governorFlag;

	/** ログイン状況. */
	private String loginStatus;

	/** パートナーサイトログイン用ID. */
	private String loginId;

	/** パートナーサイトログイン用パスワード. */
	private String loginPassword;

	/** CCSログイン用ID. */
	private String ccsId;

	/** CCSログイン用パスワード. */
	private String ccsPassword;

	/** 作成者. */
	private String createUser;

	/** 作成日時. */
	private String createTime;

	/** 更新者. */
	private String updateUser;

	/** 更新日時. */
	private String updateTime;

	/** 仲介業者支店名. */
	private String branchName;

	/** 本支店名. */
	private String headOfficeBranchName;

	/** メールアドレス（メールアドレス）. */
	private String mailAddress;

	/** 担当数（数値(整数)）. */
	private String managerCount;

}
