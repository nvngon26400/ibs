package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql011RequestModel {

	/** リクエスト.備考. */
	private String bbRemark;

    /** ユーザID. */
	private String userId;

    /** ユーザ名. */
	private String userNm;

	// /** SQL009.セクションID. */
	private String sectionId;

	// /** SQL009.支店名. */
	private String branchName;

	// /** リクエスト.銘柄コード. */
	private String brandCode;

	// /** リクエスト.部店コード. */
	private String butenCode;

	// /** リクエスト.口座番号. */
	private String accountNumber;

	// /** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

}
