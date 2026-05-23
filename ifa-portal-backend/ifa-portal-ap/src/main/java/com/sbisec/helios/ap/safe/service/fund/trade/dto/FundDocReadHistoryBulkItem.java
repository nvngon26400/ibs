package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * DTOクラス ファンド一覧
 */
public class FundDocReadHistoryBulkItem {

    /** ファンドタイプ */
    private String fundType;

    /** 協会コード */
    private String fundCode;

    public String getFundType() {
        return fundType;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundType(final String fundType) {
        this.fundType = fundType;
    }

    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

}
