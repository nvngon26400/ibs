package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql2レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql002ResponseModel {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;

    /** BM交付番号 */
    private String bmDeliveryNo;

    /** 書類請求日時 */
    private String shoruiSeikyuuNichiji;

    /** 書類分類 */
    private String shoruiBunruiMei;

    /** 書類名 */
    private String shoruimei;

    /** 部数 */
    private String busuu;

    /** 取扱者 */
    private String userNm;

    /** 取消区分 */
    private String torikeshiKbn;
}
