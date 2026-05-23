package com.sbisec.helios.ap.wholePortal.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Data
public class IfaCustomerAlertCsvOutputSql002RequestModel {
    
    /** ユーザ共通情報.ユーザーID. */
    private String userId;
    
    /** 権限コード. */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<BrokerCharge> brokerChargeList;
    
    /** 画面.仲介業者コードリスト. */
    private List<String> brokerCodeList;
    
    /** 画面.取引コースリスト. */
    private List<String> tradeCourseList;
    
    /** 画面.アラート分類リスト. */
    private List<Integer> requestAlertClassIdList;
    
    /** SQL001.アラート分類IDリスト. */
    private List<Integer> sql001AlertClassIdList;
    
    /** FCT007.指定日. */
    private String designatedDate;
    
    /** CSV取得上限. */
    private int maxCsvRowNum;

    /** SQL001.年月日(アラート分類ID: "6", "7", "12"). */
    private String dateYmdMarginDue;

    /** SQL001.年月日(アラート分類ID: "13"). */
    private String dateYmdMutualFundPrice;

    /** SQL001.年月日(アラート分類ID: "16", "17"). */
    private String dateYmdMaturityRedemption;

    /** SQL001.年月日(アラート分類ID: "18"). */
    private String dateYmdDepositWithdraw;

    /** SQL001.年月日(アラート分類ID: "19"). */
    private String dateYmdDeliverInOut;
}
