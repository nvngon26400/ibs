package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

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
public class IfaStarUploadFileOutputListA002aApiRequest {
    
    // システム日付
    private String sysDate;

    // MIN_KEY
    private String minKey;
    
    // MAX_KEY
    private String maxKey;
    
    // 画面表示明細件数
    private int dataCnt;
    
}