package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ExternalLinkTargetPath {

    /** URLID：47 チャート 遷移先path */
    PATH_47("47", "foreign/invest/us/stock/{ticker}?resource=chart", "foreign-invest"),

    /** URLID：48 株価参照 遷移先path */
    PATH_48("48", "foreign/invest/us/stock/{ticker}?resource=price", "foreign-invest"),

    /** URLID：49 株価チャート 遷移先path */
    PATH_49("49", "foreign/invest/us/stock/{ticker}?resource=chart", "foreign-invest"),

    /** URLID：50 株価チャート 遷移先path */
    PATH_50("50", "foreign/invest/us/stock/{ticker}?resource=chart", "foreign-invest"),

    /** URLID：51 株価チャート 遷移先path */
    PATH_51("51", "foreign/invest/us/stock/{ticker}?resource=chart", "foreign-invest"),

    /** URLID：110 投信 口座サマリー 遷移先path */
    PATH_110("110", "fund/account-cs", "fund/account-cs"),

    /** URLID：116 円貨建債券　新発債(買付) 遷移先path */
    PATH_116("116", "bond/search/domestic/new", "bond/search/domestic/new"),

    /** URLID：117 円貨建債券　国債(買付) 遷移先path */
    PATH_117("117", "bond/search/domestic/new/gov", "bond/search/domestic/new/gov"),

    /** URLID：118 円貨建債券　既発債(買付) 遷移先path */
    PATH_118("118", "bond/search/domestic/exist", "bond/search/domestic/exist"),

    /** URLID：119 外貨建債券　新発債(買付) 遷移先path */
    PATH_119("119", "bond/search/foreign/new", "bond/search/foreign/new"),

    /** URLID：120 外貨建債券　既発債(買付) 遷移先path */
    PATH_120("120", "bond/search/foreign/exist", "bond/search/foreign/exist"),

    /** URLID：121 注文履歴(取消) 遷移先path */
    PATH_121("121", "bond/refer/order-history", "bond/refer/order-history"),

    /** URLID：122 取引履歴 遷移先path */
    PATH_122("122", "bond/refer/statements", "bond/refer/statements"),

    /** URLID：123 保有銘柄一覧(売却) 遷移先path */
    PATH_123("123", "bond/account/assets", "bond/account/assets"),

    /** URLID：124 利金・償還金シミュレーション 遷移先path */
    PATH_124("124", "bond/account/payment/simulator", "bond/account/payment/simulator"),

    /** URLID：125 利金・償還受取方法指定 遷移先path */
    PATH_125("125", "service-settings/bond/repayment", "service-settings/bond/repayment"),

    /** URLID：126 償還乗換優遇 遷移先path */
    PATH_126("126", "service-settings/bond/transfer-preference", "service-settings/bond/transfer-preference"),

    /** URLID：127 お気に入り 遷移先path */
    PATH_127("127", "bond/favorite", "bond/favorite"),

    /** URLID：156 銀行引落サービス 遷移先path */
    PATH_156("156", "/service-settings/debit/status", "/service-settings/debit/status"),

    /** URLID：158 入出金明細（円建） 遷移先path */
    PATH_158("158", "banking/yen/detail-history", "banking/yen/detail-history"),

    /** URLID：159 入出金明細（外貨建） 遷移先path */
    PATH_159("159", "banking/fc/detail-history", "banking/fc/detail-history"),

    /** URLID：160 入出金・振替・操作履歴 遷移先path */
    PATH_160("160", "banking/fc/activity-history", "banking/fc/activity-history"),

    /** URLID：168 外株注文照会 遷移先path */
    PATH_168("168", "foreign/refer/us/stock", "foreign"),

    /** URLID：169 口座サマリー(外国株式取引サイト) 遷移先path */
    PATH_169("169", "foreign/account/summary", "foreign"),

    /** URLID：170 お気に入り（外国株式ポートフォリオ） 遷移先path */
    PATH_170("170", "foreign/portfolio", "foreign"),

    /** URLID：171 米株積立一覧 遷移先path */
    PATH_171("171", "foreign/trade/subscription/us/settings", "foreign"),

    /** URLID：172 米株信用建余力詳細 遷移先path */
    PATH_172("172", "foreign/admin/margin/wallet", "foreign"),

    /** URLID：205 入出金・振替・操作履歴（円貨） 遷移先path */
    PATH_205("205", "banking/yen/activity-history", "banking/yen/activity-history"),

    /** URLID：210 外株注文履歴 遷移先path */
    PATH_210("210", "foreign/refer/us/order-history", "foreign"),

    /** URLID：217 サービス設定 遷移先path */
    PATH_217("217", "service-settings/top", "service-settings/top");

    /** URLID */
    private final String urlId;

    /** 遷移先path */
    private final String path;

    /** 認可path */
    private final String authorizedPath;

    private ExternalLinkTargetPath(final String urlId, final String path, final String authorizedPath) {

        this.urlId = urlId;
        this.path = path;
        this.authorizedPath = authorizedPath;
    }

    public static ExternalLinkTargetPath valueOfId(String urlId) {

        ExternalLinkTargetPath[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getUrlId(), urlId))
                return enums[i];
        }

        return null;
    }

    /**
     * 認可path取得
     *
     * @param urlId URLID
     * @return 認可path
     */
    public static String getAuthorizedPath(String urlId) {
        ExternalLinkTargetPath targetPath = valueOfId(urlId);
        if (targetPath == null) return null;
        return targetPath.authorizedPath;
    }

    /**
     * 遷移先path取得（path内の「{ticker}」を銘柄コード（brandCode）に置換します）
     *
     * @param brandCode 銘柄コード
     * @return 置換後の遷移先path
     */
    public String getFormattedPath(String brandCode) {
        if (StringUtils.isEmpty(brandCode)) {
            return this.path.replace("{ticker}", "");
        }
        return this.path.replace("{ticker}", brandCode);
    }

}
