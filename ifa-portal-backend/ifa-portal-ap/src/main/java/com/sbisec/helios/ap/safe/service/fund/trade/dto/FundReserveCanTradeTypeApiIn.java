package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * DTOクラス 設定可能な預かり区分の取得（現金とクレカ）のinDto
 */
public class FundReserveCanTradeTypeApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public FundReserveCanTradeTypeApiIn() {
        super("fund.reserve.setting.get_trade_types");
    }

    /** 投資信託協会コード（投資信託コード） */
    private String fundCode;

    /**
     * 投資信託協会コード（投資信託コード）を取得する。
     * @return 投資信託協会コード（投資信託コード）
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 投資信託協会コード（投資信託コード）を設定する。
     * @param fundCode 投資信託協会コード（投資信託コード）
     */
    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

}
