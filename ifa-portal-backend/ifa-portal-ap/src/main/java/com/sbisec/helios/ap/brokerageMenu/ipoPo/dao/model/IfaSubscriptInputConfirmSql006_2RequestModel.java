package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql006_2RequestModel {

	/** リクエスト.銘柄コード. */
	private String brandCode;

	/** リクエスト.部店コード. */
	private String butenCode;

	/** リクエスト.口座番号. */
	private String accountNumber;

	/** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

	/** リクエスト.明細番号. */
	private String detailNumber;

    /** ユーザID */
    private String userId;

}
