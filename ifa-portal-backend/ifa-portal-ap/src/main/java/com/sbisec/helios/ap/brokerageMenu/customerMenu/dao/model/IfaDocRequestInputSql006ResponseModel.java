package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql6レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql006ResponseModel {

    /** 投信銘柄名 */
    private String fundName;

    /** 協会コード */
    private String fundCode;

    /** 購入可否判定区分 */
    private String fundMdBuyKubun;
}
