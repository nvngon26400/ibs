package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Data
public class IfaJointSubscriptTrustFeeA004aApiRequest {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 共募支店コード */
    private String jointBranchCode;
    
    /** 営業員コード */
    private String empCode;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 顧客名(漢字/カナ) */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)_条件 */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース */
    private List<IfaJointSubscriptTrustFeeMultiSelectApiRequest> course;
    
    /** 集計単位(日次/月次累計) */
    private String dailyMonthlyCountingUnitTotal;
    
    /** 集計単位(明細/顧客/通貨毎) */
    private String detailCustomerCurrencyCountingUnit;
    
    /** 期間指定 */
    private List<String> period;
    
    /** 証券種別 */
    private List<IfaJointSubscriptTrustFeeMultiSelectApiRequest> securityClass;
    
    /** 銘柄コード */
    private String brandCode;
    
}
