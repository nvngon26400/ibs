package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * DTOクラス  目論見書電子書面取得出力DTO
 */
public class FundDocReadHistoryBulkApiOut extends FundTradeDtoOut {

    /** データ出力区分 */
    private int fundNumber;

    /** ファンド一覧 */
    private List<FundDocReadHistoryBulk> funds;

    public int getFundNumber() {
        return fundNumber;
    }

    public List<FundDocReadHistoryBulk> getFunds() {
        return funds;
    }

    public void setFundNumber(final int fundNumber) {
        this.fundNumber = fundNumber;
    }

    public void setFunds(final List<FundDocReadHistoryBulk> funds) {
        this.funds = funds;
    }
}
