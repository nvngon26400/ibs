package com.sbisec.helios.ap.safe.service.fund.product.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Dtoクラス 銘柄詳細情報出力項目
 */
public class FundDetailInfoApiOut extends FundProductDtoOut {

    /** 信託報酬（数値） */
    private BigDecimal trustChargeNum;

    /** 信託報酬（テキスト） */
    private String trustCharge;

    /** 信託財産留保額 */
    private String reservedAsset;

    /** 解約手数料説明文 */
    private String cancellationCommissionText;

    /** 締切時間 */
    private String limitTime;

    /** 約定日日数 */
    private String tradeDate;

    /** 約定日説明文1 */
    private String tradeDateText;

    /** 約定日説明文2 */
    private String tradeDateExtraText;

    /** 受渡日日数 */
    private String deliveryDate;

    /** 受渡日説明文 */
    private String deliveryDateText;

    /** 売却最小金額 */
    private BigDecimal amountCancellationMin;

    /** 金額売却単位 */
    private BigDecimal amountCancellationUnit;

    /** 金額売却単位説明文 */
    private String amountCancellationUnitText;

    /** 売却最小口数 */
    private BigDecimal unitCancellationMin;

    /** 口数売却単位 */
    private BigDecimal unitCancellationUnit;

    /** 口数売却単位説明文 */
    private String unitCancellationText;

    /** 金額買付単位 */
    private BigDecimal amountOrderUnit;

    /** 金額買付単位価格 */
    private BigDecimal amountOrderPrice;

    /** 金額買付単位説明文 */
    private String amountOrderUnitText;

    /** 口数買付単位 */
    private BigDecimal unitOrderUnit;

    /** 口数買付単位価格 */
    private BigDecimal unitOrderPrice;

    /** 口数買付単位説明文 */
    private String unitOrderUnitText;

    /** 積立買付単位 */
    private String reserveOrderUnit;

    /** 積立買付単位説明文 */
    private String reserveOrderUnitText;

    /** 委託会社名 */
    private String familyName;

    /** MSカテゴリー大分類名称 */
    private String morningstarMainCategoryName;

    /** MSカテゴリー名称 */
    private String morningstarCategoryName;

    /** インデックスファンドフラグ */
    private boolean indexFundFlag;

    /** ベンチマーク */
    private String benchmark;

    /** 決算日 */
    private String accountingDate;

    /** 決算頻度 */
    private String dividendSchedule;

    /** 設定日 */
    private String settingDate;

    /** 当初一口あたり元本説明文 */
    private String principalText;

    /** 金額分配金受取方法説明文 */
    private String amountTypeCourseText;

    /** 口数分配金受取方法説明文 */
    private String unitTypeCourseText;

    /** 償還優遇率 */
    private BigDecimal redemptionRate;

    /** 口数償還優遇の適用説明文 */
    private String unitRedemptionText;

    /** 償還日 */
    private String maturityDate;

    /** 償還日説明文 */
    private String maturityDateText;

    /** 当月休場日 */
    private List<HolidayInfoApi> currentMonthHoliday;

    /** 翌月休場日 */
    private List<HolidayInfoApi> nextMonthHoliday;

    /** 運用方針 */
    private String policy;

    /** ポイント付与率（通常） */
    private BigDecimal pointRate1;

    /** ポイント付与率（1000万以上） */
    private BigDecimal pointRate2;

    /** 現在の注文締切日時 */
    private String orderDeadlineCurrent;

    /** 次の注文締切日時 */
    private String orderDeadlineNext;

    /** 地域コード */
    private String regionCode;

    /** 売却方法 */
    private String sellWay;

    /** お気に入り登録数のレーティング */
    private String favoriteRating;

    /**
     * 信託報酬（数値）を取得する。
     * @return 信託報酬（数値）
     */
    public BigDecimal getTrustChargeNum() {
        return trustChargeNum;
    }

    /**
     * 信託報酬（数値）を設定する。
     * @param trustChargeNum 信託報酬（数値）
     */
    public void setTrustChargeNum(final BigDecimal trustChargeNum) {
        this.trustChargeNum = trustChargeNum;
    }

    /**
     * 信託報酬（テキスト）を取得する。
     * @return 信託報酬（テキスト）
     */
    public String getTrustCharge() {
        return trustCharge;
    }

    /**
     * 信託報酬（テキスト）を設定する。
     * @param trustCharge 信託報酬（テキスト）
     */
    public void setTrustCharge(final String trustCharge) {
        this.trustCharge = trustCharge;
    }

    /**
     * 信託財産留保額を取得する。
     * @return 信託財産留保額
     */
    public String getReservedAsset() {
        return reservedAsset;
    }

    /**
     * 信託財産留保額を設定する。
     * @param reservedAsset 信託財産留保額
     */
    public void setReservedAsset(final String reservedAsset) {
        this.reservedAsset = reservedAsset;
    }

    /**
     * 解約手数料説明文を取得する。
     * @return 解約手数料説明文
     */
    public String getCancellationCommissionText() {
        return cancellationCommissionText;
    }

    /**
     * 解約手数料説明文を設定する。
     * @param cancellationCommissionText 解約手数料説明文
     */
    public void setCancellationCommissionText(final String cancellationCommissionText) {
        this.cancellationCommissionText = cancellationCommissionText;
    }

    /**
     * 締切時間を取得する。
     * @return 締切時間
     */
    public String getLimitTime() {
        return limitTime;
    }

    /**
     * 締切時間を設定する。
     * @param limitTime 締切時間
     */
    public void setLimitTime(final String limitTime) {
        this.limitTime = limitTime;
    }

    /**
     * 約定日日数を取得する。
     * @return 約定日日数
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 約定日日数を設定する。
     * @param tradeDate 約定日日数
     */
    public void setTradeDate(final String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 約定日説明文1を取得する。
     * @return 約定日説明文1
     */
    public String getTradeDateText() {
        return tradeDateText;
    }

    /**
     * 約定日説明文1を設定する。
     * @param tradeDateText 約定日説明文1
     */
    public void setTradeDateText(final String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    /**
     * 約定日説明文2を取得する。
     * @return 約定日説明文2
     */
    public String getTradeDateExtraText() {
        return tradeDateExtraText;
    }

    /**
     * 約定日説明文2を設定する。
     * @param tradeDateExtraText 約定日説明文2
     */
    public void setTradeDateExtraText(final String tradeDateExtraText) {
        this.tradeDateExtraText = tradeDateExtraText;
    }

    /**
     * 受渡日日数を取得する。
     * @return 受渡日日数
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 受渡日日数を設定する。
     * @param deliveryDate 受渡日日数
     */
    public void setDeliveryDate(final String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 受渡日説明文を取得する。
     * @return 受渡日説明文
     */
    public String getDeliveryDateText() {
        return deliveryDateText;
    }

    /**
     * 受渡日説明文を設定する。
     * @param deliveryDateText 受渡日説明文
     */
    public void setDeliveryDateText(final String deliveryDateText) {
        this.deliveryDateText = deliveryDateText;
    }

    /**
     * 売却最小金額を取得する。
     * @return 売却最小金額
     */
    public BigDecimal getAmountCancellationMin() {
        return amountCancellationMin;
    }

    /**
     * 売却最小金額を設定する。
     * @param amountCancellationMin 売却最小金額
     */
    public void setAmountCancellationMin(final BigDecimal amountCancellationMin) {
        this.amountCancellationMin = amountCancellationMin;
    }

    /**
     * 金額売却単位を取得する。
     * @return 金額売却単位
     */
    public BigDecimal getAmountCancellationUnit() {
        return amountCancellationUnit;
    }

    /**
     * 金額売却単位を設定する。
     * @param amountCancellationUnit 金額売却単位
     */
    public void setAmountCancellationUnit(final BigDecimal amountCancellationUnit) {
        this.amountCancellationUnit = amountCancellationUnit;
    }

    /**
     * 金額売却単位説明文を取得する。
     * @return 金額売却単位説明文
     */
    public String getAmountCancellationUnitText() {
        return amountCancellationUnitText;
    }

    /**
     * 金額売却単位説明文を設定する。
     * @param amountCancellationUnitText 金額売却単位説明文
     */
    public void setAmountCancellationUnitText(final String amountCancellationUnitText) {
        this.amountCancellationUnitText = amountCancellationUnitText;
    }

    /**
     * 売却最小口数を取得する。
     * @return 売却最小口数
     */
    public BigDecimal getUnitCancellationMin() {
        return unitCancellationMin;
    }

    /**
     * 売却最小口数を設定する。
     * @param unitCancellationMin
     */
    public void setUnitCancellationMin(final BigDecimal unitCancellationMin) {
        this.unitCancellationMin = unitCancellationMin;
    }

    /**
     * 口数売却単位を取得する。
     * @return 口数売却単位
     */
    public BigDecimal getUnitCancellationUnit() {
        return unitCancellationUnit;
    }

    /**
     * 口数売却単位を設定する。
     * @param unitCancellationUnit 口数売却単位
     */
    public void setUnitCancellationUnit(final BigDecimal unitCancellationUnit) {
        this.unitCancellationUnit = unitCancellationUnit;
    }

    /**
     * 口数売却単位説明文を取得する。
     * @return 口数売却単位説明文
     */
    public String getUnitCancellationText() {
        return unitCancellationText;
    }

    /**
     * 口数売却単位説明文を設定する。
     * @param unitCancellationText 口数売却単位説明文
     */
    public void setUnitCancellationText(final String unitCancellationText) {
        this.unitCancellationText = unitCancellationText;
    }

    /**
     * 金額買付単位を取得する。
     * @return 金額買付単位
     */
    public BigDecimal getAmountOrderUnit() {
        return amountOrderUnit;
    }

    /**
     * 金額買付単位を設定する。
     * @param amountOrderUnit 金額買付単位
     */
    public void setAmountOrderUnit(final BigDecimal amountOrderUnit) {
        this.amountOrderUnit = amountOrderUnit;
    }

    /**
     * 金額買付単位価格を取得する。
     * @return 金額買付単位価格
     */
    public BigDecimal getAmountOrderPrice() {
        return amountOrderPrice;
    }

    /**
     * 金額買付単位価格を設定する。
     * @param amountOrderPrice 金額買付単位価格
     */
    public void setAmountOrderPrice(final BigDecimal amountOrderPrice) {
        this.amountOrderPrice = amountOrderPrice;
    }

    /**
     * 金額買付単位説明文を取得する。
     * @return 金額買付単位説明文
     */
    public String getAmountOrderUnitText() {
        return amountOrderUnitText;
    }

    /**
     * 金額買付単位説明文を設定する。
     * @param amountOrderUnitText 金額買付単位説明文
     */
    public void setAmountOrderUnitText(final String amountOrderUnitText) {
        this.amountOrderUnitText = amountOrderUnitText;
    }

    /**
     * 口数買付単位を取得する。
     * @return 口数買付単位
     */
    public BigDecimal getUnitOrderUnit() {
        return unitOrderUnit;
    }

    /**
     * 口数買付単位を設定する。
     * @param unitOrderUnit 口数買付単位
     */
    public void setUnitOrderUnit(final BigDecimal unitOrderUnit) {
        this.unitOrderUnit = unitOrderUnit;
    }

    /**
     * 口数買付単位価格を取得する。
     * @return 口数買付単位価格
     */
    public BigDecimal getUnitOrderPrice() {
        return unitOrderPrice;
    }

    /**
     * 口数買付単位価格を設定する。
     * @param unitOrderPrice 口数買付単位価格
     */
    public void setUnitOrderPrice(final BigDecimal unitOrderPrice) {
        this.unitOrderPrice = unitOrderPrice;
    }

    /**
     * 口数買付単位説明文を取得する。
     * @return 口数買付単位説明文
     */
    public String getUnitOrderUnitText() {
        return unitOrderUnitText;
    }

    /**
     * 口数買付単位説明文を設定する。
     * @param unitOrderUnitText 口数買付単位説明文
     */
    public void setUnitOrderUnitText(final String unitOrderUnitText) {
        this.unitOrderUnitText = unitOrderUnitText;
    }

    /**
     * 積立買付単位を取得する。
     * @return 積立買付単位
     */
    public String getReserveOrderUnit() {
        return reserveOrderUnit;
    }

    /**
     * 積立買付単位を設定する。
     * @param reserveOrderUnit 積立買付単位
     */
    public void setReserveOrderUnit(final String reserveOrderUnit) {
        this.reserveOrderUnit = reserveOrderUnit;
    }

    /**
     * 積立買付単位説明文を取得する。
     * @return 積立買付単位説明文
     */
    public String getReserveOrderUnitText() {
        return reserveOrderUnitText;
    }

    /**
     * 積立買付単位説明文を設定する。
     * @param reserveOrderUnitText 積立買付単位説明文
     */
    public void setReserveOrderUnitText(final String reserveOrderUnitText) {
        this.reserveOrderUnitText = reserveOrderUnitText;
    }

    /**
     * 委託会社名を取得する。
     * @return 委託会社名
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 委託会社名を設定する。
     * @param familyName 委託会社名
     */
    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    /**
     * MSカテゴリー大分類名称を取得する。
     * @return MSカテゴリー大分類名称
     */
    public String getMorningstarMainCategoryName() {
        return morningstarMainCategoryName;
    }

    /**
     * MSカテゴリー大分類名称を設定する。
     * @param morningstarMainCategoryName MSカテゴリー大分類名称
     */
    public void setMorningstarMainCategoryName(final String morningstarMainCategoryName) {
        this.morningstarMainCategoryName = morningstarMainCategoryName;
    }

    /**
     * MSカテゴリー名称を取得する。
     * @return MSカテゴリー名称
     */
    public String getMorningstarCategoryName() {
        return morningstarCategoryName;
    }

    /**
     * MSカテゴリー名称を設定する。
     * @param morningstarCategoryName MSカテゴリー名称
     */
    public void setMorningstarCategoryName(final String morningstarCategoryName) {
        this.morningstarCategoryName = morningstarCategoryName;
    }

    /**
     * インデックスファンドフラグを取得する。
     * @return インデックスファンドフラグ
     */
    public boolean isIndexFundFlag() {
        return indexFundFlag;
    }

    /**
     * インデックスファンドフラグを設定する。
     * @param indexFundFlag インデックスファンドフラグ
     */
    public void setIndexFundFlag(final boolean indexFundFlag) {
        this.indexFundFlag = indexFundFlag;
    }

    /**
     * ベンチマークを取得する。
     * @return ベンチマーク
     */
    public String getBenchmark() {
        return benchmark;
    }

    /**
     * ベンチマークを設定する。
     * @param benchmark ベンチマーク
     */
    public void setBenchmark(final String benchmark) {
        this.benchmark = benchmark;
    }

    /**
     * 決算日を取得する。
     * @return 決算日
     */
    public String getAccountingDate() {
        return accountingDate;
    }

    /**
     * 決算日を設定する。
     * @param accountingDate 決算日
     */
    public void setAccountingDate(final String accountingDate) {
        this.accountingDate = accountingDate;
    }

    /**
     * 決算頻度を取得する。
     * @return 決算頻度
     */
    public String getDividendSchedule() {
        return dividendSchedule;
    }

    /**
     * 決算頻度を設定する。
     * @param dividendSchedule 決算頻度
     */
    public void setDividendSchedule(final String dividendSchedule) {
        this.dividendSchedule = dividendSchedule;
    }

    /**
     * 設定日を取得する。
     * @return 設定日
     */
    public String getSettingDate() {
        return settingDate;
    }

    /**
     * 設定日を設定する。
     * @param settingDate 設定日
     */
    public void setSettingDate(final String settingDate) {
        this.settingDate = settingDate;
    }

    /**
     * 当初一口あたり元本説明文を取得する。
     * @return 当初一口あたり元本説明文
     */
    public String getPrincipalText() {
        return principalText;
    }

    /**
     * 当初一口あたり元本説明文を設定する。
     * @param principalText 当初一口あたり元本説明文
     */
    public void setPrincipalText(final String principalText) {
        this.principalText = principalText;
    }

    /**
     * 金額分配金受取方法説明文を取得する。
     * @return 金額分配金受取方法説明文
     */
    public String getAmountTypeCourseText() {
        return amountTypeCourseText;
    }

    /**
     * 金額分配金受取方法説明文を設定する。
     * @param amountTypeCourseText 金額分配金受取方法説明文
     */
    public void setAmountTypeCourseText(final String amountTypeCourseText) {
        this.amountTypeCourseText = amountTypeCourseText;
    }

    /**
     * 口数分配金受取方法説明文を取得する。
     * @return 口数分配金受取方法説明文
     */
    public String getUnitTypeCourseText() {
        return unitTypeCourseText;
    }

    /**
     * 口数分配金受取方法説明文を設定する。
     * @param unitTypeCourseText 口数分配金受取方法説明文
     */
    public void setUnitTypeCourseText(final String unitTypeCourseText) {
        this.unitTypeCourseText = unitTypeCourseText;
    }

    /**
     * 償還優遇率を取得する。
     * @return 償還優遇率
     */
    public BigDecimal getRedemptionRate() {
        return redemptionRate;
    }

    /**
     * 償還優遇率を設定する。
     * @param redemptionRate 償還優遇率
     */
    public void setRedemptionRate(final BigDecimal redemptionRate) {
        this.redemptionRate = redemptionRate;
    }

    /**
     * 口数償還優遇の適用説明文を取得する。
     * @return 口数償還優遇の適用説明文
     */
    public String getUnitRedemptionText() {
        return unitRedemptionText;
    }

    /**
     * 口数償還優遇の適用説明文を設定する。
     * @param unitRedemptionText 口数償還優遇の適用説明文
     */
    public void setUnitRedemptionText(final String unitRedemptionText) {
        this.unitRedemptionText = unitRedemptionText;
    }

    /**
     * 償還日を取得する。
     * @return 償還日
     */
    public String getMaturityDate() {
        return maturityDate;
    }

    /**
     * 償還日を設定する。
     * @param maturityDate 償還日
     */
    public void setMaturityDate(final String maturityDate) {
        this.maturityDate = maturityDate;
    }

    /**
     * 償還日説明文を取得する。
     * @return 償還日説明文
     */
    public String getMaturityDateText() {
        return maturityDateText;
    }

    /**
     * 償還日説明文を設定する。
     * @param maturityDateText 償還日説明文
     */
    public void setMaturityDateText(final String maturityDateText) {
        this.maturityDateText = maturityDateText;
    }

    /**
     * 当月休場日説明文を取得する。
     * @return 当月休場日説明文
     */
    public List<HolidayInfoApi> getCurrentMonthHoliday() {
        return currentMonthHoliday;
    }

    /**
     * 当月休場日説明文を設定する。
     * @param currentMonthHoliday 当月休場日説明文
     */
    public void setCurrentMonthHoliday(final List<HolidayInfoApi> currentMonthHoliday) {
        this.currentMonthHoliday = currentMonthHoliday;
    }

    /**
     * 翌月休場日説明文を取得する。
     * @return 翌月休場日説明文
     */
    public List<HolidayInfoApi> getNextMonthHoliday() {
        return nextMonthHoliday;
    }

    /**
     * 翌月休場日説明文を設定する。
     * @param nextMonthHoliday 翌月休場日説明文
     */
    public void setNextMonthHoliday(final List<HolidayInfoApi> nextMonthHoliday) {
        this.nextMonthHoliday = nextMonthHoliday;
    }

    /**
     * 運用方針を取得する。
     * @return 運用方針
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * 運用方針を設定する。
     * @param policy 運用方針
     */
    public void setPolicy(final String policy) {
        this.policy = policy;
    }

    /**
     * ポイント付与率（通常）を取得する。
     * @return ポイント付与率（通常）
     */
    public BigDecimal getPointRate1() {
        return pointRate1;
    }

    /**
     * ポイント付与率（通常）を設定する。
     * @param pointRate1 ポイント付与率（通常）
     */
    public void setPointRate1(final BigDecimal pointRate1) {
        this.pointRate1 = pointRate1;
    }

    /**
     * ポイント付与率（1000万以上）を取得する。
     * @return ポイント付与率（1000万以上）
     */
    public BigDecimal getPointRate2() {
        return pointRate2;
    }

    /**
     * ポイント付与率（1000万以上）を設定する。
     * @param pointRate2 ポイント付与率（1000万以上）
     */
    public void setPointRate2(final BigDecimal pointRate2) {
        this.pointRate2 = pointRate2;
    }

    /**
     * 現在の注文締切日時を取得する。
     * @return 現在の注文締切日時
     */
    public String getOrderDeadlineCurrent() {
        return orderDeadlineCurrent;
    }

    /**
     * 現在の注文締切日時を設定する。
     * @param orderDeadlineCurrent 現在の注文締切日時
     */
    public void setOrderDeadlineCurrent(final String orderDeadlineCurrent) {
        this.orderDeadlineCurrent = orderDeadlineCurrent;
    }

    /**
     * 次の注文締切日時を取得する。
     * @return 次の注文締切日時
     */
    public String getOrderDeadlineNext() {
        return orderDeadlineNext;
    }

    /**
     * 次の注文締切日時を設定する。
     * @param orderDeadlineNext 次の注文締切日時
     */
    public void setOrderDeadlineNext(final String orderDeadlineNext) {
        this.orderDeadlineNext = orderDeadlineNext;
    }

    /**
     * 地域コードを取得する。
     * @return 地域コード
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * 地域コードを設定する。
     * @param regionCode 地域コード
     */
    public void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * 売却方法を取得する。
     * @return 売却方法
     */
    public String getSellWay() {
        return sellWay;
    }

    /**
     * 売却方法を設定する。
     * @param sellWay 売却方法
     */
    public void setSellWay(final String sellWay) {
        this.sellWay = sellWay;
    }

    /**
     * お気に入り登録数のレーティングを取得する。
     * @return お気に入り登録数のレーティング
     */
    public String getFavoriteRating() {
        return favoriteRating;
    }

    /**
     * お気に入り登録数のレーティングを設定する。
     * @param favoriteRating お気に入り登録数のレーティング
     */
    public void setFavoriteRating(final String favoriteRating) {
        this.favoriteRating = favoriteRating;
    }
}
