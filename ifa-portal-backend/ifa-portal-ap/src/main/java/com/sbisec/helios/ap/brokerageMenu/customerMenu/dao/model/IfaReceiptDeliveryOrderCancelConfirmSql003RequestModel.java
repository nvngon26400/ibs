package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 現引現渡注文取消確認　SQL003リクエスト
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel {

    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;

    /** 顧客共通情報.部店コード */
    private String butenCode;
    
    /** 受注日時 */
    private String orderDayTime;
    
    /** 顧客共通情報.口座番号 */
    private String accountNumber;
    
    /** 顧客共通情報.顧客コード */
    private String kokyakuId;
    
    /** 顧客共通情報.特定口座区分 */
    private String tokuteiKouzaKbn;
    
    /** API002入力.取消ユーザーＩＤ */
    private String userId;
    
}
