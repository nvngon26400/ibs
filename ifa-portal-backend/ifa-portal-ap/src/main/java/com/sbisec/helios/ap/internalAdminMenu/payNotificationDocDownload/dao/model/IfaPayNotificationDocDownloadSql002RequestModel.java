package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadSql002RequestModel {
    
    /** 対象年月From */
    private String targetDateYmFrom;
    
    /** 対象年月To */
    private String targetDateYmTo;
    
    /** 仲介業者コードリスト */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** ユーザ共通情報.権限コード. */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
}
