package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * ユーザ権限情報取得（仲介業者）リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class Fct032Sql002RequestModel extends ModelBase {
    
    // ユーザーID
    private String userId;
    
    // 仲介業者コード  
    private String brokerId;
    
}
