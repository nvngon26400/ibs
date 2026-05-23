package com.sbisec.helios.ap.safe.service.fund.product.dto;

/**
 * Dtoクラス 銘柄基本情報入力項目
 */
public class FundBasicInfoApiIn extends FundProductDtoIn {

    /**
     * コンストラクタ
     */
    public FundBasicInfoApiIn() {
        super("fund.basic");
    }

    /** 協会コード */
    private String fundCode;

    /**
     * 協会コードを取得する。
     * @return 協会コード
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 協会コードを設定する。
     * @param fundCode 協会コード
     */
    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

}
