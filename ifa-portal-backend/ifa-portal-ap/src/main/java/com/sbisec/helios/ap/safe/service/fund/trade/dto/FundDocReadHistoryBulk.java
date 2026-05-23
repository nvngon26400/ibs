package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * DTOクラス ファンド一覧
 */
public class FundDocReadHistoryBulk {

    /** 取得可否 */
    private String getFlag;

    /** ファンドタイプ */
    private String fundType;

    /** 協会コード */
    private String fundCode;

    /** 銘柄名(漢字) */
    private String secName;

    /** 目論見書閲覧区分 */
    private String prospectus;

    /** 検索件数 */
    private int totalNumber;

    /** 目論見書・運用レポート一覧 */
    private List<FundDocReadHistoryDoc> documents;

    public String getGetFlag() {
        return getFlag;
    }

    public String getFundType() {
        return fundType;
    }

    public String getFundCode() {
        return fundCode;
    }

    public String getSecName() {
        return secName;
    }

    public String getProspectus() {
        return prospectus;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<FundDocReadHistoryDoc> getDocuments() {
        return documents;
    }

    public void setGetFlag(final String getFlag) {
        this.getFlag = getFlag;
    }

    public void setFundType(final String fundType) {
        this.fundType = fundType;
    }

    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

    public void setSecName(final String secName) {
        this.secName = secName;
    }

    public void setProspectus(final String prospectus) {
        this.prospectus = prospectus;
    }

    public void setTotalNumber(final int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setDocuments(final List<FundDocReadHistoryDoc> documents) {
        this.documents = documents;
    }
}
