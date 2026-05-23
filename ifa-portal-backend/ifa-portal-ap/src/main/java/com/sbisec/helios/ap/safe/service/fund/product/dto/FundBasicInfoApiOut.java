package com.sbisec.helios.ap.safe.service.fund.product.dto;

import java.math.BigDecimal;

/**
 * Dtoクラス 銘柄基本情報出力項目
 */
public class FundBasicInfoApiOut extends FundProductDtoOut {

    /** 投資信託協会コード（投資信託コード） */
    private String fundCode;

    /** 投資信託協会名（投資信託名） */
    private String fundName;

    /** 愛称 */
    private String nickName;

    /** 金額買付可否 */
    private String amountBuyType;

    /** 口数可買付可否 */
    private String unitBuyType;

    /** 積立買付可否 */
    private String tsumitateBuyType;

    /** NISA（成長投資枠）対象銘柄 */
    private Boolean nisaGrowthBuyType;

    /** NISA（つみたて投資枠）対象銘柄 */
    private Boolean nisaReserveBuyType;

    /** 当社からのお知らせ */
    private String sbiInformation;

    /** 委託会社からのお知らせ */
    private String investmentCompanyInformation;

    /** モーニングスター社コメント */
    private String morningstarComment;

    /** 基準価額 */
    private BigDecimal standardPrice;

    /** 基準価額単位 */
    private BigDecimal standardPriceUnit;

    /** 基準価額増減区分 */
    private String previousStatus;

    /** 基準価額対象日付 */
    private String priceDate;

    /** 前日比 */
    private BigDecimal previousChange;

    /** 前日比率 */
    private String previousRatio;

    /** 純資産 */
    private BigDecimal netAsset;

    /** 設定来高値 */
    private BigDecimal higherPrice;

    /** 設定来高値対象日付 */
    private String higherPriceDate;

    /** 設定来安値 */
    private BigDecimal lowerPrice;

    /** 設定来安値対象日付 */
    private String lowerPriceDate;

    /** 設定来分配金 */
    private BigDecimal totalDividend;

    /** 年間分配金累計 */
    private BigDecimal yearDividend;

    /** 次回決算日 */
    private String nextDividendDate;

    /** 権利取り最終申込日 */
    private String limitDate;

    /** 分配金利回り */
    private BigDecimal yield;

    /** 直近分配金(税引前) */
    private BigDecimal latestDividend;

    /** 直近分配金対象日付 */
    private String latestDate;

    /** 速報フラグ */
    private boolean preliminaryReportFlag;

    /** 代用掛目 */
    private BigDecimal assessmentRate;

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

    /**
     * 投資信託協会名（投資信託名）を取得する。
     * @return 投資信託協会名（投資信託名）
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * 投資信託協会名（投資信託名）を設定する。
     * @param fundName 投資信託協会名（投資信託名）
     */
    public void setFundName(final String fundName) {
        this.fundName = fundName;
    }

    /**
     * 愛称を取得する。
     * @return 愛称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 愛称を設定する。
     * @param nickName 愛称
     */
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    /**
     * 金額買付可否を取得する。
     * @return 金額買付可否
     */
    public String getAmountBuyType() {
        return amountBuyType;
    }

    /**
     * 金額買付可否を設定する。
     * @param amountBuyType 金額買付可否
     */
    public void setAmountBuyType(final String amountBuyType) {
        this.amountBuyType = amountBuyType;
    }

    /**
     * 口数可買付可否を取得する。
     * @return 口数可買付可否
     */
    public String getUnitBuyType() {
        return unitBuyType;
    }

    /**
     * 口数可買付可否を設定する。
     * @param unitBuyType 口数可買付可否
     */
    public void setUnitBuyType(final String unitBuyType) {
        this.unitBuyType = unitBuyType;
    }

    /**
     * 積立買付可否を取得する。
     * @return 積立買付可否
     */
    public String getTsumitateBuyType() {
        return tsumitateBuyType;
    }

    /**
     * 積立買付可否を設定する。
     * @param tsumitateBuyType 積立買付可否
     */
    public void setTsumitateBuyType(final String tsumitateBuyType) {
        this.tsumitateBuyType = tsumitateBuyType;
    }

    /**
     * NISA（成長投資枠）対象銘柄を取得する。
     * @return NISA（成長投資枠）対象銘柄
     */
    public Boolean getNisaGrowthBuyType() {
        return nisaGrowthBuyType;
    }

    /**
     * NISA（成長投資枠）対象銘柄を設定する。
     * @param nisaGrowthBuyType NISA（成長投資枠）対象銘柄
     */
    public void setNisaGrowthBuyType(final Boolean nisaGrowthBuyType) {
        this.nisaGrowthBuyType = nisaGrowthBuyType;
    }

    /**
     * NISA（つみたて投資枠）対象銘柄を取得する。
     * @return NISA（つみたて投資枠）対象銘柄
     */
    public Boolean getNisaReserveBuyType() {
        return nisaReserveBuyType;
    }

    /**
     * NISA（つみたて投資枠）対象銘柄を設定する
     * @param nisaReserveBuyType NISA（つみたて投資枠）対象銘柄
     */
    public void setNisaReserveBuyType(final Boolean nisaReserveBuyType) {
        this.nisaReserveBuyType = nisaReserveBuyType;
    }

    /**
     * 当社からのお知らせを取得する。
     * @return 当社からのお知らせ
     */
    public String getSbiInformation() {
        return sbiInformation;
    }

    /**
     * 当社からのお知らせを設定する。
     * @param sbiInformation 当社からのお知らせ
     */
    public void setSbiInformation(final String sbiInformation) {
        this.sbiInformation = sbiInformation;
    }

    /**
     * 委託会社からのお知らせを取得する。
     * @return 委託会社からのお知らせ
     */
    public String getInvestmentCompanyInformation() {
        return investmentCompanyInformation;
    }

    /**
     * 委託会社からのお知らせを設定する。
     * @param investmentCompanyInformation 委託会社からのお知らせ
     */
    public void setInvestmentCompanyInformation(final String investmentCompanyInformation) {
        this.investmentCompanyInformation = investmentCompanyInformation;
    }

    /**
     * モーニングスター社コメントを取得する。
     * @return モーニングスター社コメント
     */
    public String getMorningstarComment() {
        return morningstarComment;
    }

    /**
     * モーニングスター社コメントを設定する。
     * @param morningstarComment モーニングスター社コメント
     */
    public void setMorningstarComment(final String morningstarComment) {
        this.morningstarComment = morningstarComment;
    }

    /**
     * 基準価額を取得する。
     * @return 基準価額
     */
    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    /**
     * 基準価額を設定する。
     * @param standardPrice 基準価額
     */
    public void setStandardPrice(final BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    /**
     * 基準価額単位を取得する。
     * @return 基準価額単位
     */
    public BigDecimal getStandardPriceUnit() {
        return standardPriceUnit;
    }

    /**
     * 基準価額単位を設定する。
     * @param standardPriceUnit 基準価額単位
     */
    public void setStandardPriceUnit(final BigDecimal standardPriceUnit) {
        this.standardPriceUnit = standardPriceUnit;
    }

    /**
     * 基準価額増減区分を取得する。
     * @return 基準価額増減区分
     */
    public String getPreviousStatus() {
        return previousStatus;
    }

    /**
     * 基準価額増減区分を設定する。
     * @param previousStatus 基準価額増減区分
     */
    public void setPreviousStatus(final String previousStatus) {
        this.previousStatus = previousStatus;
    }

    /**
     * 基準価額対象日付を取得する。
     * @return 基準価額対象日付
     */
    public String getPriceDate() {
        return priceDate;
    }

    /**
     * 基準価額対象日付を設定する。
     * @param priceDate 基準価額対象日付
     */
    public void setPriceDate(final String priceDate) {
        this.priceDate = priceDate;
    }

    /**
     * 前日比を取得する。
     * @return 前日比
     */
    public BigDecimal getPreviousChange() {
        return previousChange;
    }

    /**
     * 前日比を設定する。
     * @param previousChange 前日比
     */
    public void setPreviousChange(final BigDecimal previousChange) {
        this.previousChange = previousChange;
    }

    /**
     * 前日比率を取得する。
     * @return 前日比率
     */
    public String getPreviousRatio() {
        return previousRatio;
    }

    /**
     * 前日比率を設定する。
     * @param previousRatio 前日比率
     */
    public void setPreviousRatio(final String previousRatio) {
        this.previousRatio = previousRatio;
    }

    /**
     * 純資産を取得する。
     * @return 純資産
     */
    public BigDecimal getNetAsset() {
        return netAsset;
    }

    /**
     * 純資産を設定する。
     * @param netAsset 純資産
     */
    public void setNetAsset(final BigDecimal netAsset) {
        this.netAsset = netAsset;
    }

    /**
     * 設定来高値を取得する。
     * @return 設定来高値
     */
    public BigDecimal getHigherPrice() {
        return higherPrice;
    }

    /**
     * 設定来高値を設定する。
     * @param higherPrice 設定来高値
     */
    public void setHigherPrice(final BigDecimal higherPrice) {
        this.higherPrice = higherPrice;
    }

    /**
     * 設定来高値対象日付を取得する。
     * @return 設定来高値対象日付
     */
    public String getHigherPriceDate() {
        return higherPriceDate;
    }

    /**
     * 設定来高値対象日付を設定する。
     * @param higherPriceDate 設定来高値対象日付
     */
    public void setHigherPriceDate(final String higherPriceDate) {
        this.higherPriceDate = higherPriceDate;
    }

    /**
     * 設定来安値を取得する。
     * @return 設定来安値
     */
    public BigDecimal getLowerPrice() {
        return lowerPrice;
    }

    /**
     * 設定来安値を設定する。
     * @param lowerPrice 設定来安値
     */
    public void setLowerPrice(final BigDecimal lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    /**
     * 設定来安値対象日付を取得する。
     * @return 設定来安値対象日付
     */
    public String getLowerPriceDate() {
        return lowerPriceDate;
    }

    /**
     * 設定来安値対象日付を設定する。
     * @param lowerPriceDate 設定来安値対象日付
     */
    public void setLowerPriceDate(final String lowerPriceDate) {
        this.lowerPriceDate = lowerPriceDate;
    }

    /**
     * 設定来分配金を取得する。
     * @return 設定来分配金
     */
    public BigDecimal getTotalDividend() {
        return totalDividend;
    }

    /**
     * 設定来分配金を設定する。
     * @param totalDividend 設定来分配金
     */
    public void setTotalDividend(final BigDecimal totalDividend) {
        this.totalDividend = totalDividend;
    }

    /**
     * 年間分配金累計を取得する。
     * @return 年間分配金累計
     */
    public BigDecimal getYearDividend() {
        return yearDividend;
    }

    /**
     * 年間分配金累計を設定する。
     * @param yearDividend 年間分配金累計
     */
    public void setYearDividend(final BigDecimal yearDividend) {
        this.yearDividend = yearDividend;
    }

    /**
     * 次回決算日を取得する。
     * @return 次回決算日
     */
    public String getNextDividendDate() {
        return nextDividendDate;
    }

    /**
     * 次回決算日を設定する。
     * @param nextDividendDate 次回決算日
     */
    public void setNextDividendDate(final String nextDividendDate) {
        this.nextDividendDate = nextDividendDate;
    }

    /**
     * 権利取り最終申込日を取得する。
     * @return 権利取り最終申込日
     */
    public String getLimitDate() {
        return limitDate;
    }

    /**
     * 権利取り最終申込日を設定する。
     * @param limitDate 権利取り最終申込日
     */
    public void setLimitDate(final String limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * 分配金利回りを取得する。
     * @return 分配金利回り
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 分配金利回りを設定する。
     * @param yield 分配金利回り
     */
    public void setYield(final BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 直近分配金(税引前)を取得する。
     * @return 直近分配金(税引前)
     */
    public BigDecimal getLatestDividend() {
        return latestDividend;
    }

    /**
     * 直近分配金(税引前)を設定する。
     * @param latestDividend 直近分配金(税引前)
     */
    public void setLatestDividend(final BigDecimal latestDividend) {
        this.latestDividend = latestDividend;
    }

    /**
     * 直近分配金対象日付を取得する。
     * @return 直近分配金対象日付
     */
    public String getLatestDate() {
        return latestDate;
    }

    /**
     * 直近分配金対象日付を設定する。
     * @param latestDate 直近分配金対象日付
     */
    public void setLatestDate(final String latestDate) {
        this.latestDate = latestDate;
    }

    /**
     * 速報フラグを取得する。
     * @return 速報フラグ
     */
    public boolean isPreliminaryReportFlag() {
        return preliminaryReportFlag;
    }

    /**
     * 速報フラグを設定する。
     * @param preliminaryReportFlag 速報フラグ
     */
    public void setPreliminaryReportFlag(final boolean preliminaryReportFlag) {
        this.preliminaryReportFlag = preliminaryReportFlag;
    }

    /**
     * 代用掛目を取得する。
     * @return 代用掛目
     */
    public BigDecimal getAssessmentRate() {
        return assessmentRate;
    }

    /**
     * 代用掛目を設定する。
     * @param assessmentRate 代用掛目
     */
    public void setAssessmentRate(final BigDecimal assessmentRate) {
        this.assessmentRate = assessmentRate;
    }
}
