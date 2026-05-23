package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 銘柄別建玉一覧A001リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaBrandPositionListA001ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 新規売買区分（全角半角）. */
    @NotEmpty(message = "新規売買区分")
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    private String paymentDeadline;
    
    /** 並替順序. */
    @NotEmpty(message = "並替順序")
    private String sortOrder;
    
}
