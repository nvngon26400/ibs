package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;

import lombok.Data;

/**
 * 担当顧客別手数料一覧取得リクエストモデル.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListSql001RequestModel {
    
    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<OutputFct030Dto.BrokerCharge> brokerChargeList;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コースリスト. */
    private List<String> courseList;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 集計単位. */
    private String chargeCustomerCountingUnit;
    
    /** 期間指定(From). */
    private String periodYmFrom;
    
    /** 期間指定(To). */
    private String periodYmTo;
    
    /** 証券種別リスト. */
    private List<String> securityClassList;
    
    /** 対象外証券種別. */
    private String excludeSecurityClass;

    /** 検索上限件数 */
    private String searchLimitRow;

    /** 権限コード */
    private String privId;
    
    /** 手数料種別「取引」含む */
    private Boolean isContainTrade;
}
