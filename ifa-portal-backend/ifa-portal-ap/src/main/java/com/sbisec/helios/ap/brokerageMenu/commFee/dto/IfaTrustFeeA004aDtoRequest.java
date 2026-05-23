package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
public class IfaTrustFeeA004aDtoRequest {
    
    /** 仲介業者コードリスト */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 支店コード */
    private String branchCode;
    
    /** 営業員コード */
    private String empCode;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 顧客名(漢字/カナ) */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース */
    private List<String> courseList;
    
    /** 集計単位(日次/月次累計). */
    private String dailyMonthlyCountingUnitTotal;
    
    /** 集計単位(明細/顧客/通貨毎). */
    private String detailCustomerCurrencyCountingUnit;
    
    /** 期間指定From */
    private String periodFrom;
    
    /** 期間指定To */
    private String periodTo;
    
    /** 証券種別. */
    private List<String> securityClassList;
    
    /** 銘柄コード */
    private String brandCode;
    
}
