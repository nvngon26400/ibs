package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
public class IfaTrustFeeSql001ToSql006RequestModel {
    
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

    /** ヒント句使用フラグ */
    private Boolean useHintFlag;
}
