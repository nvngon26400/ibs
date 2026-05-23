package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 保有商品一覧 外国債券情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListApiResponseForeignBonds {
    
    /** 預り銘柄数. */
    private String numberOfDepositedIssues;
    
    /** 評価額合計（数値(整数)）. */
    private String valuationTotal;
    
    /** 口座区分. */
    private String depositBalanceAccountTypeName;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 預り明細リスト. */
    private List<IfaHoldingSecurityListApiResponseForeignBondsdepositDetail> depositDetailList;
    
}
