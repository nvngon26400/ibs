package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * CSVダウンロードMAX件数取得
 *
 * @author SCSK
 *
 */
@Data
public class Fct038Sql001RequestModel {
    
    //画面ID
    private String screenId;
    
    //ユーザ権限
    private String userPriv;
    
}
