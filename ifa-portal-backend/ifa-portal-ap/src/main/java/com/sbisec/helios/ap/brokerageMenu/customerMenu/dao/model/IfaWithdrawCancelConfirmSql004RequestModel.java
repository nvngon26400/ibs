package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金取消履歴Req004
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmSql004RequestModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;

    /** 取消ステータスコード */
    private String torikeshiStsCd;

    /** エラーコード */
    private String errorCd;

    /** エラーメッセージ */
    private String errorMessage;

    /** 計上日 */
    private String keijoubi;

    /** 種別 */
    private String shubetsu;

    /** 取消受付経路区分 */
    private String torikeshiUketsukeKeiroKbn;

    /** 取消出金可能金額 */
    private String torikeshiKingaku;

    /** 取消受付後出金可能金額 */
    private String torikeshiGoKingaku;

    /** 出金予定日 */
    private String shukkinYoteibi;

    /** 更新者 */
    private String updateUser;

}
