package com.sbisec.helios.ap.safe.service.fund.product.dto;

/**
 * Dtoクラス 銘柄詳細情報入力項目
 */
public class FundDetailInfoApiIn extends FundProductDtoIn {

    /**
     * コンストラクタ
     */
    public FundDetailInfoApiIn() {
        super("fund.detail");
    }

    /** 協会コード */
    private String fundCode;

    /** 金額指定買付預りの口数指定売却不可フラグ */
    private boolean cannotSellByUnitAfterAmount;

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

    /**
     * 金額指定買付預りの口数指定売却不可フラグを取得する。
     * @return 金額指定買付預りの口数指定売却不可フラグ
     */
    public boolean getCannotSellByUnitAfterAmount() {
        return cannotSellByUnitAfterAmount;
    }
    /**
     * 金額指定買付預りの口数指定売却不可フラグを設定する。
     * @param cannotSellByUnitAfterAmount 金額指定買付預りの口数指定売却不可フラグ
     */
    public void setCannotSellByUnitAfterAmount(final boolean cannotSellByUnitAfterAmount) {
        this.cannotSellByUnitAfterAmount = cannotSellByUnitAfterAmount;
    }

}
