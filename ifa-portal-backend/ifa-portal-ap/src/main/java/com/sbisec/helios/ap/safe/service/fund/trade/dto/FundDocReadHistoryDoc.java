package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * DTOクラス  目論見書・運用レポート一覧
 */
public class FundDocReadHistoryDoc {

    /** 資料番号 */
    private String documentNo;

    /** 目論見書区分 */
    private String prospectusKbn;

    /** 文書日付 */
    private String prospectusDate;

    /** 有効期間From（開始日） */
    private String limitFrom;

    /** 有効期間To（廃止日） */
    private String limitTo;

    /** 電子交付区分 */
    private String pdfIssue;

    /** URL */
    private String url;

    /** サイズ */
    private int pdfSize;

    /** 閲覧種別 */
    private String readingType;

    /** 買付可能日 */
    private String buyDate;

    /** 閲覧区分 */
    private String readingKbn;

    public String getDocumentNo() {
        return documentNo;
    }

    public String getProspectusKbn() {
        return prospectusKbn;
    }

    public String getProspectusDate() {
        return prospectusDate;
    }

    public String getLimitFrom() {
        return limitFrom;
    }

    public String getLimitTo() {
        return limitTo;
    }

    public String getPdfIssue() {
        return pdfIssue;
    }

    public String getUrl() {
        return url;
    }

    public int getPdfSize() {
        return pdfSize;
    }

    public String getReadingType() {
        return readingType;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public String getReadingKbn() {
        return readingKbn;
    }

    public void setDocumentNo(final String documentNo) {
        this.documentNo = documentNo;
    }

    public void setProspectusKbn(final String prospectusKbn) {
        this.prospectusKbn = prospectusKbn;
    }

    public void setProspectusDate(final String prospectusDate) {
        this.prospectusDate = prospectusDate;
    }

    public void setLimitFrom(final String limitFrom) {
        this.limitFrom = limitFrom;
    }

    public void setLimitTo(final String limitTo) {
        this.limitTo = limitTo;
    }

    public void setPdfIssue(final String pdfIssue) {
        this.pdfIssue = pdfIssue;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setPdfSize(final int pdfSize) {
        this.pdfSize = pdfSize;
    }

    public void setReadingType(final String readingType) {
        this.readingType = readingType;
    }

    public void setBuyDate(final String buyDate) {
        this.buyDate = buyDate;
    }

    public void setReadingKbn(final String readingKbn) {
        this.readingKbn = readingKbn;
    }
}
