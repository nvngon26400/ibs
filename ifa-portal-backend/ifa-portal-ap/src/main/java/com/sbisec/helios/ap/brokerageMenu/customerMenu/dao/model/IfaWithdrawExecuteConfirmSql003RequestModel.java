package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金確認履歴Req003
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmSql003RequestModel {

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

    /** 種別 */
    private String shubetsu;

    /** 更新者 */
    private String updateUser;

}
