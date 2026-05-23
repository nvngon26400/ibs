package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql002_2RequestModel {

	/** リクエスト.銘柄コード. */
	private String brandCode;

	/** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

	/** リクエスト.部店コード. */
	private String butenCode;

	/** リクエスト.口座番号. */
	private String accountNumber;

	/** リクエスト.注文状況. */
	private String orderStatus;

    /** ユーザID */
    private String userId;

}
