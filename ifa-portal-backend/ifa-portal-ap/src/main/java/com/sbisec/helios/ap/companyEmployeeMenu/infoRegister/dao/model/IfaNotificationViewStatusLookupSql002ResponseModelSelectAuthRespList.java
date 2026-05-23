package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;

@Data
public class IfaNotificationViewStatusLookupSql002ResponseModelSelectAuthRespList {

	/** ユーザーID */
	private String userId;

	/** ユーザー名 */
	private String userNm;

	/** パスワード */
	private String password;

	/** 最後パスワード更新日時 */
	private String lastpwuptime;

	/** 権限コード */
	private String privId;

	/** SBI証券支店コード */
	private String branchId;

	/** 仲介業者コード */
	private String brokerId;

	/** 仲介業者支店コード */
	private String subBrokerId;

	/** 仲介業者担当者コード */
	private String employeeId;

	/** 仲介業者担当者名 */
	private String employeeName;

	/** 管理者フラグ */
	private String governorFlag;

	/** ログイン状況 */
	private String loginStatus;

	/** パートナーサイトログイン用ID */
	private String partnerUserId;

	/** パートナーサイトログイン用パスワード */
	private String partnerUserPw;

	/** CCSログイン用ID */
	private String ccsUserId;

	/** CCSログイン用パスワード */
	private String ccsUserPw;

	/** 作成者 */
	private String createUser;

	/** 作成日時 */
	private String createTime;

	/** 更新者 */
	private String uptimestampUser;

	/** 更新日時 */
	private String uptimestampTime;

	/** メールアドレス */
	private String mailAddress;

	/** お知らせ既読ID */
	private String t5Id;

	/** お知らせID */
	private String t5InfoId;

	/** 担当者ID */
	private String t5FaId;

	/** 既読フラグ */
	private String t5ReadFlg;

	/** 閲覧日 */
	private String t5ReadAt;

	/** ログインID */
	private String t5Login;

	/** 仲介業者コード */
	private String brokerCode;

	/** 仲介業支店コード */
	private String brokerBranchCode;

	/** 仲介業者支店種別 */
	private String brokerBranchKind;

	/** 仲介業者支店名 */
	private String branchName;

	/** 作成日時 */
	private String createDate;

	/** 作成者 */
	private String createBy;

	/** 更新日時 */
	private String updateDate;

	/** 更新者 */
	private String updateBy;

	/** 削除日時 */
	private String deleteDate;

	/** 削除フラグ */
	private String deleteFlg;

	/** 既読フラグ（数字）. */
	private String t5nReadFlg;

	/** 固定値「REMOVE」. */
	private String remove;

}
