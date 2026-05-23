package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 *
 * @author BASE李
 *
 */
@Data
public class IfaOrderListA004ApiRequest {
    
    /** EC受注番号（国内株式）. */
    @NotEmpty(message = "EC受注番号（国内株式）")
    private String ecOrderNo;
    
    /** 発注区分（国内株式）. */
    @NotEmpty(message = "発注区分（国内株式）")
    private String hattyuuKbn;
    
    /** EC受注番号（国内投信）. */
    @NotEmpty(message = "EC受注番号（国内投信）")
    private String domesticMutualFundOrderStatusEcOrderNo;
    
    /** 銘柄コード（募集）. */
    @NotEmpty(message = "銘柄コード（募集）")
    private String brandCode;
    
    /** 預り区分（募集）. */
    @NotEmpty(message = "預り区分（募集）")
    private String depositType;
    
    /** 注文番号（外株委託）. */
    @NotEmpty(message = "注文番号（外株委託）")
    private String orderNumber;
    
    /** 注文SUB番号（外株委託）. */
    @NotEmpty(message = "注文SUB番号（外株委託）")
    private String orderSubNumber;
    
    /** 管理番号（店頭取引）. */
    @NotEmpty(message = "管理番号（店頭取引）")
    private String manageNumber;

    /** 商品区分 */
    private String orderType;
}
