package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB020301_03-02,SUB020301_03-03
 * 画面名：ノックイン銘柄保有一覧,ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge {

    //仲介業者コード
    private String brokerCode;
    
    //営業員コード
    private String empCode;

}
