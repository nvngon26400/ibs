package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 外国現物取引注文取消確認SQL004レスポンスモデル
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 勧誘区分（全角半角）. */
    private String invitationType;
    
    /** 受注方法区分（全角半角）. */
    private String orderMethodType;
    
    /** ワーニング申請取引（全角半角）. */
    private String warningApplyType;
    
    /** 重要事項説明区分（全角半角）. */
    private String explanationInfoType;
    
    /** 乗換え勧誘(ETF)区分（全角半角）. */
    private String transferInvitationType;
    
    /** 英文開示銘柄説明区分（全角半角）. */
    private String engPubBrandExpType;
    
}
