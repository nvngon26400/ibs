package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

@Data
public class IfaSubscriptInputConfirmSql010_2RequestModel {

	/** リクエスト.銘柄コード. */
	private String brandCode;

	/** リクエスト.ブックビルディング申込期間（開始）. */
	private String bookBuildingPresentationFrom;

	/** リクエスト.部店コード. */
	private String butenCode;

	/** リクエスト.口座番号. */
	private String accountNumber;

    /** 募集入力者. */
    private String orderUser;

	/** リクエスト.数量. */
	private String quantity;

	/** リクエスト.約定金額. */
	private String contractAmount;

	/** リクエスト.預り区分. */
	private String depositType;

	/** リクエスト.勧誘区分. */
	private String kanyuKbn;

	/** リクエスト.受注方法. */
	private String jutyuKbn;

	/** リクエスト.目論見書の交付方法. */
	private String mokuromiKoufuKbn;

	/** リクエスト.最重要事項の説明. */
	private String importantMatterType;

	/** リクエスト.コンプラランクチェック確認. */
	private String invitationCheck;

    /** ユーザID */
    private String userId;

    /** コンプラランクチェック.チェックボックス文言 */
    private String complianceCheckMsg;
}
