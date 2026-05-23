package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@Data
public class IfaSelfInspectBlotterRegisterConfirmSql002RequestModel {
    
    /** 自己点検ID. */
    private Integer nextSelfCheckId;
    
    /** 登録年月. */
    private String registerDate;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** ユーザID. */
    private String userId;
}
