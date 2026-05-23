package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;


import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql012_1RequestModel {

	/** リクエスト.発行価格. */
	private String issueBbPrice;

	/** リクエスト.銘柄コード. */
	private String brandCode;

	/** リクエスト.部店コード. */
	private String butenCode;

	/** リクエスト.口座番号. */
	private String accountNumber;

	/** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

    /** ユーザID */
    private String userId;

}
