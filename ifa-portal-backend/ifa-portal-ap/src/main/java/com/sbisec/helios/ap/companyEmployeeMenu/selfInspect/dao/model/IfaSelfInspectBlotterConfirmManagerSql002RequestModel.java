package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *　SUB0506_01-01_自己点検記録簿確認（管理者用）SQL002要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IfaSelfInspectBlotterConfirmManagerSql002RequestModel {
    
    /** 取得最大件数 */
    private int maxRows;
    
    /** 仲介業者コードリスト. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 登録年月YYYYMM */
    private String registerDate;
    
    /** 回答状況 */
    private String answerStatus;
    
    /** 回答結果　*/
    private String answerResult;
    
}
