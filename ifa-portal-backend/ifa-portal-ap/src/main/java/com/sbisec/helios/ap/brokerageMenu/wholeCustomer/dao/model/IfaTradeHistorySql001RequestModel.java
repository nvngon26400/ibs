package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryDtoRequestSelected;

import lombok.Data;

/**
 * 取引履歴 SQL001リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class IfaTradeHistorySql001RequestModel {

    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（全角半角）. */
    private String customerName;
    
    /** 顧客名検索オプション. */
    private String customerNameSearchType;
    
    /** 取引コース（全角半角）. */
    private List<IfaTradeHistoryDtoRequestSelected> course;
    
    /** 閲覧可能部店（半角英数字）. */
    private List<String> visibleButenCodeList;
    
    /** 期間指定From. */
    private String periodDateFrom;
    
    /** 期間指定To. */
    private String periodDateTo;
    
    /** 証券種別. */
    private List<IfaTradeHistoryDtoRequestSelected> securityClass;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode12;
    
    /** 取得最大件数（閾値）. */
    private int rownum;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<BrokerCharge> brokerChargeList;

    /** 証券種別が選択されているか否か（true：選択あり、false：選択無し） */
    private Boolean isSecurityClassSelected;

}
