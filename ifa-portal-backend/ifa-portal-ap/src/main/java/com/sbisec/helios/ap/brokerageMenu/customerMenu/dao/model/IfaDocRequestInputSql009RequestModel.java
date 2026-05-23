package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql9リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql009RequestModel {

    /** 分類コード */
    private String shoruiBunruiCd;

    /** 書類コード */
    private String shoruiCd;

    /** 仲介業者種別 */
    private String brokerShubetsu;
}
