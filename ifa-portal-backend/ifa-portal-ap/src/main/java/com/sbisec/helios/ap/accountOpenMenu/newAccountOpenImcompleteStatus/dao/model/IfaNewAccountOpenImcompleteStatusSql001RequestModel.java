package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model;

import lombok.Data;
import java.util.List;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */
@Data
public class IfaNewAccountOpenImcompleteStatusSql001RequestModel {
    /** 上限件数 */
    private int maxRow;
    
    /** FCT030.仲介業者営業員リスト */
    List<IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus> brokerChargeList;

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

    /** 仲介業者除外フラグ. */
    private String chkBrokerCodeExclude;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 登録日From. */
    private String dispatchScheduleDateFrom;

    /** 登録日To. */
    private String dispatchScheduleDateTo;

    /** 権限コード */
    private String privId;

}
