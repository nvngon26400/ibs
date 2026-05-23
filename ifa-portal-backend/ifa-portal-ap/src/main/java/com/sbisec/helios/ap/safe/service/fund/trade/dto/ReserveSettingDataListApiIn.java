package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.util.List;

/**
 * ApiInクラス 設定済みの積立一覧を取得するパラメータ
 *
 */
public class ReserveSettingDataListApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public ReserveSettingDataListApiIn() {
        super("fund.reserve.setting_data_list");
    }

    /** 取得開始位置 */
    private int offset;

    /** 取得件数 */
    private int limit;

    /** 協会コード */
    private String fundCode;

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private List<String> accountTypes;

    /** コース */
    private List<String> courseTypes;

    /** ボーナス設定有無 */
    private String settingBonus;

    /** 並び順 */
    private String sortOrder;

    /** ソート項目 */
    private String sortField;

    /**
     * 取得開始位置を取得する。
     * @return 取得開始位置
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 取得開始位置を設定する。
     * @param offset 取得開始位置
     */
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    /**
     * 取得件数を取得する。
     * @return 取得件数
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 取得件数を設定する。
     * @param limit 取得件数
     */
    public void setLimit(final int limit) {
        this.limit = limit;
    }

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
     * 決済方法を取得する。
     * @return 決済方法
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 決済方法を設定する。
     * @param paymentMethod 決済方法
     */
    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 預り区分を取得する。
     * @return 預り区分
     */
    public List<String> getAccountTypes() {
        return accountTypes;
    }

    /**
     * 預り区分を設定する。
     * @param accountTypes 預り区分
     */
    public void setAccountTypes(final List<String> accountTypes) {
        this.accountTypes = accountTypes;
    }

    /**
     * コースを取得する。
     * @return コース
     */
    public List<String> getCourseTypes() {
        return courseTypes;
    }

    /**
     * コースを設定する。
     * @param courseTypes コース
     */
    public void setCourseTypes(final List<String> courseTypes) {
        this.courseTypes = courseTypes;
    }

    /**
     * ボーナス設定有無を取得する。
     * @return ボーナス設定有無
     */
    public String getSettingBonus() {
        return settingBonus;
    }

    /**
     * ボーナス設定有無を設定する。
     * @param settingBonus ボーナス設定有無
     */
    public void setSettingBonus(final String settingBonus) {
        this.settingBonus = settingBonus;
    }

    /**
     * 並び順を取得する。
     * @return 並び順
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * 並び順を設定する。
     * @param sortOrder 並び順
     */
    public void setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * ソート項目を取得する。
     * @return ソート項目
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * ソート項目を設定する。
     * @param sortField ソート項目
     */
    public void setSortField(final String sortField) {
        this.sortField = sortField;
    }
}
