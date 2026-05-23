package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;


import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql003_2RequestModel {

	/** リクエスト.注文状況. */
	private String orderStatus;

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

	/** リクエスト.更新時間(注文排他用). */
	private String updateTimeForOrderExclusivity;

    /** ユーザID */
    private String userId;

}
