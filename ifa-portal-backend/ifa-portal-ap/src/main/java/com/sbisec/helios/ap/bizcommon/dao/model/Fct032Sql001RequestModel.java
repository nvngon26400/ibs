package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * ユーザ権限情報取得（仲介業者支店）リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class Fct032Sql001RequestModel extends ModelBase {
    
    // ユーザーID
    private String userId;
    
    // 仲介業者コード  
    private String brokerId;

    // 仲介業者支店コード  
    private String subBrokerId;
    
}
