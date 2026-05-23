package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL004　リクエスト

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql004RequestModel {
    
    /** シーケンス番号（大）. */
    private int seqNo;
    
    /** 表示項目コード. */
    private String displayItemCode;
    
}
