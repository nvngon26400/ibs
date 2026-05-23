package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * DTOクラス  目論見書電子書面取得入力DTO
 */
public class FundDocReadHistoryBulkApiIn extends FundTradeDtoIn {

    /** データ出力区分 */
    private String dataOutputKbn;

    /** ファンド一覧 */
    private List<FundDocReadHistoryBulkItem> funds;

    /**
     * コンストラクタ
     */
    public FundDocReadHistoryBulkApiIn() {
        super("fund.fund_doc_read_history.bulk");
    }

    public String getDataOutputKbn() {
        return dataOutputKbn;
    }

    public List<FundDocReadHistoryBulkItem> getFunds() {
        return funds;
    }

    public void setDataOutputKbn(final String dataOutputKbn) {
        this.dataOutputKbn = dataOutputKbn;
    }

    public void setFunds(final List<FundDocReadHistoryBulkItem> funds) {
        this.funds = funds;
    }
}
