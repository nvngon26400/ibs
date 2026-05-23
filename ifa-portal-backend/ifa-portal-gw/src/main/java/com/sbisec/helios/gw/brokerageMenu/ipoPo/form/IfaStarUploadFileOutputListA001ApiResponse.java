package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-05
 * 画面名：STARアップロードファイル出力一覧
 *
 * @author SCSK
 * 
 */
@Data
@JsonSerialize
public class IfaStarUploadFileOutputListA001ApiResponse {
    /** ファイル出力リスト **/
    private List<IfaStarUploadFileOutputListA001FileOutputListApiResponse> fileOutputList;
    
    /** システム日付 **/
    private String sysDate;
    
    /** MIN_KEY **/
    private String minKey;
    
    /** MAX_KEY **/
    private String maxKey;
    
    /** 画面表示明細件数 **/
    private int dataCnt;
}