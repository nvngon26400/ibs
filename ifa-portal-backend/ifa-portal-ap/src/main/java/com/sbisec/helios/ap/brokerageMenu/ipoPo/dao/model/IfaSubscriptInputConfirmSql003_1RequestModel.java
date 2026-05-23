package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql003_1RequestModel {

	/** リクエスト.抽選結果. */
	private String lotteryResult;

	/** リクエスト.当選株数. */
	private String bbQuantityAlloc;

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
