package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListSql001ResponseModel {

	/** 機能ID（全角半角）. */
	private String functionId;

	/** カテゴリID（数字）. */
	private String t9nInfoCat;

	/** ファイルディレクトリ（全角半角）. */
	private String fileDirectory;

}
