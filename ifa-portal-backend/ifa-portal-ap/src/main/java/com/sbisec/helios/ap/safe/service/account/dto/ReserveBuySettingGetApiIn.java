package com.sbisec.helios.ap.safe.service.account.dto;

/**
 * 積立買付利用設定取得InDto
 */
public class ReserveBuySettingGetApiIn extends AccountDtoIn {

    /** 商品区分 */
    private String productType;

    /**
     * コンストラクタ
     */
    public ReserveBuySettingGetApiIn() {
        super("account.point.get_reserve_buy_setting");
    }

    /**
     * 商品区分を取得する。
     * @return productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 商品区分を設定する。
     * @param productType 商品区分
     */
    public void setProductType(final String productType) {
        this.productType = productType;
    }
}
