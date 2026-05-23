package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB0207_02
 * 画面名：共同店舗　信託報酬
 *
 * @author SBI大連　董
 2024/12/16 新規作成
 */
@Data
public class IfaJointMarketTrustFeeSql001RequestModel {
    
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
    
    /** 期間指定From */
    private String periodFrom;
    
    /** 期間指定To */
    private String periodTo;
    
    /** 証券種別. */
    private List<String> securityClassList;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
    /** 最大取得件数 */
    private int maxRowNum;
    
    /** パターンNo */
    private String patternNo;
    
    /** ユーザ共通情報.権限コード */
    private String privId;
 
    /**集計単位2  0：明細、1：顧客・商品分類・通貨毎*/
    private String detailCustomerCurrencyCountingUnit;
 
    /**集計単位1 (日次/月次累計). */
    private String dailyMonthlyCountingUnitTotal;
}
