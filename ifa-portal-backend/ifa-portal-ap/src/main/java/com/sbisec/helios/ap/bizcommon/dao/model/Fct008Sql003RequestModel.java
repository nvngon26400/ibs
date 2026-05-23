package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct008Sql003RequestModel {
    
    // SQL002.取引所/委託会社ｺｰﾄﾞ
    private String ipmSeInvestmentsCode;
    // 期間対象市場
    private String periodTargetMarket;
    // sql002抽出件数
    private int sql002ResSize;

}
