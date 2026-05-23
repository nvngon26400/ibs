package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求入力A007リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputA007ApiRequest {

    /** BM交付種別 */
    private String bmKoufuShubetsu;

    /** 協会コード */
    private String fundCode;

    /** 購入可否判定区分 */
    private String fundMdBuyKubun;

    /** 銘柄コード */
    private String meigaraCd;

    /** 交付区分 */
    private String hassouSts;
}
