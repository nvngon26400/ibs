package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql010_1RequestModel {

	/** リクエスト.銘柄コード. */
	private String brandCode;

	/** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

	/** リクエスト.部店コード. */
	private String butenCode;

	/** リクエスト.口座番号. */
	private String accountNumber;

	/** リクエスト.仲介業者コード. */
	private String brokerCode;

	/** リクエスト.仲介業者営業員コード. */
	private String brokerChargeCode;

	/** リクエスト.扱者コード. */
	private String dealerNumber;

	/** リクエスト.抽選結果. */
	private String lotteryResult;

	/** リクエスト.当選株数. */
	private String bbQuantityAlloc;

	/** リクエスト.発行価格. */
	private String issueBbPrice;

    /** ユーザID */
    private String userId;

}
