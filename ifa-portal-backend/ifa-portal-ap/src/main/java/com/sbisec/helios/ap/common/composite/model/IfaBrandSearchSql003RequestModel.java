package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandSearchSql003RequestModel {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 信用区分. */
    // TODO 項目辞書未登録
    private String domesticMarginAccountType;
}
