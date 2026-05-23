package com.sbisec.helios.ap.safe.utils;



/**
 * Safe API URL
 * 
 * @author nicksen.li
 * @date 03/31/2025
 */
public class SafeApiUtil {

    /**
     * 部店口座を利用し、tokenを取得する。
     * 
     * @param butenCode 部店
     * @param accountNo 口座
     * @return {部店}3桁 + "-" + {口座}7桁
     */
    public static String getToken(String butenCode, String accountNo) {

        // 口座：指定の7桁文字数になるまで左側に文字'0'を追加する
        String accountNoFill = com.sbibits.earth.util.StringUtil.fillLeft(accountNo, '0', 7);

        return String.join("-", butenCode, accountNoFill);
    }

    /**
     * @return 積立設定一覧取得API:対象顧客の投信積立設定済みの銘柄情報を取得する
     */
    public static String getSafe_fundTrade_fund_reserve_setting_data_list() {
        return "/fundTrade/fund/reserve/setting_data_list";
    }

    /**
     * @return 積立設定サマリAPI:積立設定サマリ情報を取得する
     */
    public static String getSafe_fundTrade_fund_reserve_setting_data_summary() {
        return "/fundTrade/fund/reserve/setting_data_summary";
    }

    /**
     * @return ポイント_積立買付利用設定取得API:積立買付ポイント利用状況を取得する
     */
    public static String getSafe_account_account_point_get_reserve_buy_setting() {
        return "/account/account/point/get_reserve_buy_setting";
    }

    /**
     * @return 積立設定可能な預り区分の取得API:積立設定可能な預り区分の取得をする
     */
    public static String getSafe_fundTrade_fund_reserve_setting_get_trade_types() {
        return "/fundTrade/fund/reserve/setting/get_trade_types";
    }

    /**
     * @return 投信基本・詳細の取得API:投信基本・詳細情報(basic)の取得を取得する
     */
    public static String getSafe_fundProduct_fund_basic() {
        return "/fundProduct/fund/basic";
    }

    /**
     * @return 投信基本・詳細の取得API:投信基本・詳細情報(detail)の取得を取得する
     */
    public static String getSafe_fundProduct_fund_detail() {
        return "/fundProduct/fund/detail";
    }

    /**
     * @return 積立設定入力確認API:積立買付設定の新規入力確認
     */
    public static String postSafe_fundTrade_fund_reserve_setting_input_confirm() {
        return "/fundTrade/fund/reserve/setting/input/confirm";
    }

    /**
     * @return 積立設定入力受付API:投信積立設定登録を行う
     */
    public static String postSafe_fundTrade_fund_reserve_setting_input_recept() {
        return "/fundTrade/fund/reserve/setting/input/recept";
    }

    /**
     * @return 投信積立設定変更確認API:投信積立設定変更の確認を行う
     */
    public static String postSafe_fundTrade_fund_reserve_setting_change_confirm() {
        return "/fundTrade/fund/reserve/setting/change/confirm";
    }

    /**
     * @return 積立設定変更受付API:投信積立設定変更を行う
     */
    public static String postSafe_fundTrade_fund_reserve_setting_change_recept() {
        return "/fundTrade/fund/reserve/setting/change/recept";
    }

    /**
     * @return 積立設定一覧取得(一括変更用)API:投信積立設定解除の確認を行う
     */
    public static String postSafe_fundTrade_fund_reserve_get_reserve_setting_for_bulk_update() {
        return "/fundTrade/fund/reserve/get-reserve-setting-for-bulk-update";
    }

    /**
     * @return 積立設定一括解除API:投信積立設定解除を行う
     */
    public static String postSafe_fundTrade_fund_reserve_setting_release_recept() {
        return "/fundTrade/fund/reserve/setting/release/recept";
    }

    /**
     * @return 投信閲覧履歴一括照会API:対象顧客の目論見書閲覧状況を取得する
     */
    public static String getSafe_fundTrade_fund_fund_doc_read_history_bulk() {
        return "/fundTrade/fund/fund_doc_read_history/bulk";
    }

    /**
     * @return 積立設定一括変更確認API:積立買付設定一括変更による確認
     */
    public static String postSafe_fundTrade_fund_reserve_setting_bulk_change_confirm() {
        return "/fundTrade/fund/reserve/setting/bulk/change/confirm";
    }

    /**
     * @return 積立設定一括変更受付API:積立買付設定の一括変更
     */
    public static String postSafe_fundTrade_fund_reserve_setting_bulk_change_recept() {
        return "/fundTrade/fund/reserve/setting/bulk/change/recept";
    }

}
