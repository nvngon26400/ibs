package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * IPOPO銘柄一覧 A004リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaIpoPoBrandListA004ApiRequest {

    /** 銘柄コード */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 12, message = "銘柄コード")
    private String bbProductCode;
    
    /** ブックビルディング申込期間（開始） */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    private String bbPresentationFrom;
}
