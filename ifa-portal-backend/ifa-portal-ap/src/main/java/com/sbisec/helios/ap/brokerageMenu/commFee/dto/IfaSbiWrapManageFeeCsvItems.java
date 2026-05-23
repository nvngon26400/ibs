package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaSbiWrapManageFeeCsvItems extends ModelBase {

    /** シリアルナンバー */
    private static final long serialVersionUID = -2347170022899237513L;

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 営業員コード. */
    private String empCode;
    
    /** 営業員名. */
    private String brokerChargeName;
    
    /** 部店. */
    private String buten;

    /** 口座番号. */
    private String accountNumber;

    /** 扱者コード. */
    private String dealerNumber;

    /** 顧客名(漢字). */
    private String customerNameKanji;

    /** 手数料徴収日. */
    private String feeCollectionDate;

    /** 手数料番号. */
    private String feeNumber;

    /** 運用サービスID. */
    private String operationServiceId;

    /** 徴収額(税抜). */
    private String collectionAmount;
}
