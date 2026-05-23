package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金確認履歴Req002
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmSql002RequestModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;

    /** 受注日時 */
    private String juchuuNichiji;

    /** ステータスコード */
    private String stsCd;

    /** エラーコード */
    private String errorCd;

    /** エラーメッセージ */
    private String errorMessage;

    /** EC入出金番号 */
    private String ecNyushukkinNo;

    /** 計上日 */
    private String keijoubi;

    /** 種別 */
    private String shubetsu;

    /** 出金可能金額 */
    private String shukkinkanouKingaku;

    /** 受付後の出金可能金額 */
    private String uketsukeShukkinkanouKingaku;

    /** 出金予定日 */
    private String shukkinYoteibi;

    /** 更新者 */
    private String updateUser;

}
