package com.sbisec.helios.ap.athena.utils;

/**
 * COMET API Util.
 * 
 * @author SCSK
 * @date 01/03/2024
 */
public class CometApiUtil {
    
    /**
     * 余力サービス - 円貨金銭残高スケジュール取得API
     */
    private static String acc_schedule_cash_balances = "/account/balance/cash_balances";
    
    /**
     * 余力サービス - 外貨金銭残高スケジュール取得API
     */
    private static String acc_foreign_schedule_cash_balances = "/account/balance/cash_balances/foreign";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文確認API
     */
    private static String fs_confirm_created_order = "/fstock/order/orders:confirmCreatedOrder";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文登録API
     */
    private static String fs_created_order = "/fstock/order/orders";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文訂正登録API
     */
    private static String fs_update_foreign_stock_order = "/fstock/order/orders";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消登録API
     */
    private static String fs_delete_foreign_stock_order = "/fstock/order/orders/{order_sub_no}";
    
    /**
     * 外国株式取引サービス - 外国株式信用新規建注文登録API
     */
    private static String fs_created_margin_order = "/fstock/order/margin_orders";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文訂正登録API
     */
    private static String fs_update_foreign_margin_order = "/fstock/order/margin_orders";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文取消登録API
     */
    private static String fs_delete_foreign_margin_order = "/fstock/order/margin_orders/{order_sub_no}";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API
     */
    private static String fs_get_foreign_deleted_initialization = "/fstock/order/orders/{order_sub_no}:getDeletedInitialization";
    
    /**
     * 余力サービス - 外貨建商品保有証券一覧取得API
     */
    private static String acc_balance_securities_balances = "/account/balance/securities_balances";
    
    /**
     * 余力サービス - 代用有価証券振替可能一覧取得API
     */
    private static String acc_balance_possible_collateral_securities_balances = "/account/balance/collateral_securities:listPossibleTransfers";
    
    /**
     * 口座情報サービス - 外貨建口座NISA口座開設判定API
     */
    private static String acc_accounts_check_nisa_open = "/account/accounts:checkNisaOpen";
    
    /**
     * 口座情報サービス - アカウントプロファイル取得API
     */
    private static String acc_accounts_profiles_profile_name = "/account/accounts/profiles/{profile_name}";
    
    /**
     * 口座情報サービス - 外国株式取引制限チェックAPI
     */
    private static String acc_accounts_check_trade_restriction = "/account/accounts:checkTradeRestriction";
    
    /**
     * 口座情報サービス - 外貨建口座JrNISA口座開設判定API
     */
    private static String acc_accounts_check_jrNisa_open = "/account/accounts:checkJrNisaOpen";
    
    /**
     * 口座情報サービス - 信用口座開設判定API
     */
    private static String acc_accounts_check_margin_account_open = "/account/accounts:checkMarginAccountOpen";
    
    /**
     * 口座情報サービス - 委託保証金振替確認API
     */
    private static String acc_client_entry_margin_transfers_confirm = "/account/client_entry/margin_transfers:confirm";
    
    /**
     * 口座情報サービス - 委託保証金振替登録API
     */
    private static String acc_client_entry_margin_transfers = "/account/client_entry/margin_transfers";
    
    /**
     * 口座情報サービス - 代用有価証券振替確認API
     */
    private static String acc_client_entry_collateral_securities_transfers_confirm = "/account/client_entry/collateral_securities_transfers:confirm";
    
    /**
     * 口座情報サービス - 代用有価証券振替登録API
     */
    private static String acc_client_entry_collateral_securities_transfers = "/account/client_entry/collateral_securities_transfers";
    
    /**
     * 外国株式銘柄サービス - 外国株式銘柄マスタ取得API
     */
    private static String fs_securities_countries_country_code_securities_securities_code = "/fstock/securities/countries/{country_code}/securities/{securities_code}";
    
    /**
     * 外国株式銘柄サービス - 外国株式本日注意銘柄取得API
     */
    private static String fs_securities_countries_securities_attention_securities = "/fstock/securities/countries/{country_code}/securities/{securities_code}/attention_securities";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文詳細取得API
     */
    private static String fs_order_orders_orderNo_getDetail = "/fstock/order/orders/{order_no}:getDetail";
    
    /**
     * 外国株式銘柄サービス - 外国株式銘柄検索API
     */
    private static String fs_securities = "/fstock/securities";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文初期情報取得API
     */
    private static String fs_order_orders_get_created_order_initialization = "/fstock/order/orders:getCreatedOrderInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文訂正初期情報取得API
     */
    private static String fs_order_orders_order_sub_no_get_updated_order_initialization = "/fstock/order/orders/{order_sub_no}:getUpdatedOrderInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文訂正確認API
     */
    private static String fs_order_orders_confirm_updated_order = "/fstock/order/orders:confirmUpdatedOrder";
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API
     */
    private static String fs_order_orders_order_sub_no_get_deleted_initialization = "/fstock/order/orders/{order_sub_no}:getDeletedInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文詳細取得API
     */
    private static String fs_order_orders_margin_orders_order_sub_no_get_detail = "/fstock/order/margin_orders/{order_sub_no}:getDetail";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文初期情報取得API
     */
    private static String fs_order_margin_orders_get_created_order_initialization = "/fstock/order/margin_orders:getCreatedOrderInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式信用新規建注文確認API
     */
    private static String fs_order_margin_orders_confirm_created_order = "/fstock/order/margin_orders:confirmCreatedOrder";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文訂正初期情報取得API
     */
    private static String fs_order_margin_orders_order_sub_no_get_updated_order_initialization = "/fstock/order/margin_orders/{order_sub_no}:getUpdatedOrderInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文訂正確認API
     */
    private static String fs_order_margin_orders_confirm_updated_order = "/fstock/order/margin_orders:confirmUpdatedOrder";
    
    /**
     * 外国株式取引サービス - 外国株式信用注文取消初期情報取得API
     */
    private static String fs_order_margin_orders_order_sub_no_get_deleted_order_initialization = "/fstock/order/margin_orders/{order_sub_no}:getDeletedOrderInitialization";
    
    /**
     * 外国株式取引サービス - 外国株式現地営業日取得API（日数指定版）
     */
    private static String fs_order_countries_country_code_base_dates_base_date_business_dates = "/fstock/order/countries/{country_code}/base_dates/{base_date}/business_dates";
    
    /**
     * 外国株式取引サービス - 外国株式信用返済注文確認API
     */
    private static String fs_order_margin_orders_confirm_closed_order = "/fstock/order/margin_orders:confirmClosedOrder";
    
    /**
     * 外国株式取引サービス - 外国株式注文一覧取得API
     */
    private static String fs_order_orders = "/fstock/order/orders";
    
    /**
     * マーケット価格情報サービス - マーケット価格取得API
     */
    private static String info_market_price_prices = "/information/market_price/prices";
    
    /**
     * マーケット価格情報サービス - マーケット価格取得用チケット登録API
     */
    private static String info_market_price_tickets = "/information/market_price/tickets";
    
    /**
     * マーケット価格情報サービス - マーケット価格ハッシュ取得API
     */
    private static String info_market_price_countries_country_code_price_hashes = "/information/market_price/countries/{country_code}/price_hashes";
    
    /**
     * 外国株式取引サービス - 外国株式信用返済注文登録API
     */
    private static String fs_order_margin_orders_close_order = "/fstock/order/margin_orders:closeOrder";
    
    /**
     * 外国株式信用銘柄サービス - 外国株式信用売建可能銘柄一覧取得API
     */
    private static String fs_securities_shortable_stocks = "/fstock/securities/shortable_stocks";
    
    /**
     * 余力サービス - 外国株式信用建余力取得API
     */
    private static String acc_account_balance_margin_powers_getHeadline = "/account/balance/margin_powers:getHeadline";
    
    /**
     * 余力サービス - 外国株式信用建余力サマリ取得API
     */
    private static String acc_account_balance_margin_powers_getSummary = "/account/balance/margin_powers:getSummary";
    
    /**
     * 余力サービス - リアルタイム委託保証金率取得API
     */
    private static String acc_account_balance_margin_powers_listDepositRateBasis = "/account/balance/margin_powers:listDepositRateBasis";
    
    /**
     * 余力サービス - 外国株式信用建余力取得API
     */
    private static String acc_account_balance_positions_listDetails = "/account/balance/positions:listDetails";
    
    /**
     * 余力サービス - 外国株式信用建玉明細取得API
     */
    private static String acc_balance_positions_getDetail = "/account/balance/positions:getDetail";
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API
     */
    private static String acc_balance_positions_getSummary = "/account/balance/positions:getSummary";
    
    /**
     * 追証サービス - 追証請求明細一覧取得API
     */
    private static String acc_margin_call_listDetails = "/account/margin_call:listDetails";
    
    /**
     * 余力サービス - 外貨建商品保有証券取得API
     */
    private static String acc_balance_securities = "/account/balance/securities/{securities_code}/specific_account_codes/{specific_account_code}/securities_balances";
    
    /**
     * 余力サービス - 外国株式参考信用建余力取得API
     */
    private static String acc_account_balance_margin_powers_getReference = "/account/balance/margin_powers:getReference";
    
    /**
     * 口座情報サービス - 信用口座自動振替設定取得API
     */
    private static String acc_margin_accounts_getSetting = "/account/margin_accounts:getAutoTransferSetting";
    
    /**
     * 口座情報サービス - 信用口座自動振替設定登録API
     */
    private static String acc_margin_accounts_createSetting = "/account/margin_accounts:createAutoTransferSetting";
    
    /**
     * 外国株式貸株サービス - 外国株式貸株サービス加入判定API
     */
    private static String fs_lending_stock_lendings_getStatus = "/fstock/lending/stock_lendings:getSubscribedStatus";
    
    /**
     * 余力サービス - 外貨信用保証金残高スケジュール取得API
     */
    private static String acc_balance_margin_cash_balance = "/account/balance/margin_cash_balances/foreign";
    
    /**
     * 余力サービス - 外国株式信用建余力詳細取得API
     */
    private static String acc_balance_margin_powers_getDetail = "/account/balance/margin_powers:getDetail";
    
    /**
     * @return 認証サービス - オペレータ権限一覧取得API
     */
    private static String list_operator_scopes = "/account/authentication/operators/{operator_id}/operator_scopes";
    
    /**
     * @return 為替取引サービス - 通貨別サービスステータスチェックAPI
     */
    public static String check_exchange_currency_service_status = "/exchange/order/currency_service_status";
    
    /**
     * @return 為替マスタサービス - 為替取引通貨マスタ取得API
     */
    public static String get_exchange_trade_currency = "/exchange/master/exchange_trade_currencies/{currency_code}";
    
    /**
     * @return 為替マスタサービス - 為替取引レート情報一覧取得API（リテール向け）
     */
    public static String list_exchange_trade_rates = "/exchange/master/exchange_trade_rates";
    
    /**
     * @return 為替マスタサービス - 為替取引レート情報一覧取得API（IFA向け）
     */
    public static String list_ifa_exchange_trade_rates = "/exchange/master/ifa/exchange_trade_rates";
    
    /**
     * @return 為替取引サービス - 営業日情報取得API（IFA向け）
     */
    public static String get_ifa_exchange_business_date = "/exchange/order/ifa/business_dates/{exchange_group}";
    
    /**
     * @return 為替取引サービス - 営業日情報取得API（リテール向け）
     */
    public static String get_exchange_business_date = "/exchange/order/business_dates/{exchange_group}";
    
    /**
     * @return 入出金・入出庫サービス - 出金可能金額一括取得API 
     */
    public static String multi_get_possible_withdrawals = "/account/client_entry/cashing/withdrawals:multiGetPossibleWithdrawals";
    
    /**
     * @return 為替取引サービス - 為替注文確認API（IFA向け） 
     */
    public static String confirm_ifa_exchange_created_order = "/exchange/order/ifa/orders:confirmCreatedOrder";
    
    /**
     * @return 為替取引サービス - 為替注文確認API（リテール向け） 
     */
    public static String confirm_exchange_created_order = "/exchange/order/orders:confirmCreatedOrder";
    
    /**
     * @return 為替取引サービス - 為替注文取消初期情報取得API（リテール向け） 
     */
    public static String get_exchange_cancelled_order_initialization = "/exchange/order/orders:getCancelledInitialization";
    
    /**
     * @return 為替取引サービス - 為替注文取消登録API（リテール向け） 
     */
    public static String cancel_exchange_order = "/exchange/order/orders:cancel";
    
    /**
     * 為替取引サービス - 為替注文一覧取得API
     */
    private static String exchange_order_orders = "/exchange/order/orders";
    
    /**
     * 為替マスタサービス - 為替取引通貨マスタ一覧取得API
     */
    private static String exchange_master_exchange_trade_currencies = "/exchange/master/exchange_trade_currencies";
    
    /**
     * @return 為替取引サービス - 為替注文登録（IFA向け）API
     */
    public static String create_ifa_exchange_order = "/exchange/order/ifa/orders";
    
    /**
     * @return 為替取引サービス - 為替注文登録（リテール向け）API
     */
    public static String create_exchange_order = "/exchange/order/orders";
    
    /**
     * @return アカウントサービス - 外貨建口座JrNISA口座開設ステータス取得API
     */
    public static String get_jrnisa_account_status = "/account/accounts:getJrNisaAccountStatus";
    
    /**
     * 外国株式取引サービス - 外国株式ロシア株呼値情報取得API
     */
    private static String fs_order_countries_ru_ticksizes_securities = "/fstock/order/countries/RU/tick_sizes/securities/{securities_code}";
    
    /**
     * 預り金一括取得API
     */
    private static String multi_get_cash_deposits = "/account/balance/cash_deposits:multiGet";
    
    /**
     * @return 余力サービス - 円貨金銭残高スケジュール取得API
     */
    public static String getAcc_schedule_cash_balances() {
        
        return acc_schedule_cash_balances;
    }
    
    /**
     * @return 余力サービス - 外貨金銭残高スケジュール取得API
     */
    public static String getAcc_foreign_schedule_cash_balances() {
        
        return acc_foreign_schedule_cash_balances;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文確認API
     */
    public static String getFs_confirm_created_order() {
        
        return fs_confirm_created_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文登録API
     */
    public static String getFs_created_order() {
        
        return fs_created_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文訂正登録API
     */
    public static String getFs_update_foreign_stock_order() {
        
        return fs_update_foreign_stock_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文取消登録API
     */
    public static String getFs_delete_foreign_stock_order() {
        
        return fs_delete_foreign_stock_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用新規建注文登録API
     */
    public static String getFs_created_margin_order() {
        
        return fs_created_margin_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文訂正登録API
     */
    public static String getFs_update_foreign_margin_order() {
        
        return fs_update_foreign_margin_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文取消登録API
     */
    public static String getFs_delete_foreign_margin_order() {
        
        return fs_delete_foreign_margin_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文取消初期情報取得API
     */
    public static String getFs_get_foreign_deleted_initialization() {
        
        return fs_get_foreign_deleted_initialization;
    }
    
    /**
     * @return 余力サービス - 外貨建商品保有証券一覧取得API
     */
    public static String getAcc_balance_securities_balances() {
        
        return acc_balance_securities_balances;
    }
    
    /**
     * @return 余力サービス - 代用有価証券振替可能一覧取得API
     */
    public static String getAcc_balance_possible_collateral_securities_balances() {
        
        return acc_balance_possible_collateral_securities_balances;
    }
    
    /**
     * @return 口座情報サービス - 外貨建口座NISA口座開設判定API
     */
    public static String getAcc_accounts_check_nisa_open() {
        
        return acc_accounts_check_nisa_open;
    }
    
    /**
     * @return 口座情報サービス - アカウントプロファイル取得API
     */
    public static String getAcc_accounts_profiles_profile_name() {
        
        return acc_accounts_profiles_profile_name;
    }
    
    /**
     * @return 口座情報サービス - 外国株式取引制限チェックAPI
     */
    public static String getAcc_accounts_check_trade_restriction() {
        
        return acc_accounts_check_trade_restriction;
    }
    
    /**
     * @return 口座情報サービス - 外貨建口座JrNISA口座開設判定API
     */
    public static String getAcc_accounts_check_jrNisa_open() {
        
        return acc_accounts_check_jrNisa_open;
    }
    
    /**
     * @return 口座情報サービス - 信用口座開設判定API
     */
    public static String getAcc_accounts_check_margin_account_open() {
        
        return acc_accounts_check_margin_account_open;
    }
    
    /**
     * @return 口座情報サービス - 委託保証金振替確認API
     */
    public static String getAcc_client_entry_margin_transfers_confirm() {
        
        return acc_client_entry_margin_transfers_confirm;
    }
    
    /**
     * @return 口座情報サービス - 委託保証金振替登録API
     */
    public static String getAcc_client_entry_margin_transfers() {
        
        return acc_client_entry_margin_transfers;
    }
    
    /**
     * @return 口座情報サービス - 代用有価証券振替確認API
     */
    public static String getAcc_client_entry_collateral_securities_transfers_confirm() {
        
        return acc_client_entry_collateral_securities_transfers_confirm;
    }
    
    /**
     * @return 口座情報サービス - 代用有価証券振替登録API
     */
    public static String getAcc_client_entry_collateral_securities_transfers() {
        
        return acc_client_entry_collateral_securities_transfers;
    }
    
    /**
     * @return 外国株式銘柄サービス - 外国株式銘柄マスタ取得API
     */
    public static String getFs_securities_countries_country_code_securities_securities_code() {
        
        return fs_securities_countries_country_code_securities_securities_code;
    }
    
    /**
     * @return 外国株式銘柄サービス - 外国株式本日注意銘柄取得API
     */
    public static String getFs_securities_countries_securities_attention_securities() {
        
        return fs_securities_countries_securities_attention_securities;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文詳細取得API
     */
    public static String getFs_order_orders_orderNo_getDetail() {
        
        return fs_order_orders_orderNo_getDetail;
    }
    
    /**
     * @return 外国株式銘柄サービス - 外国株式銘柄検索API
     */
    public static String getFs_securities() {
        
        return fs_securities;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文初期情報取得API
     */
    public static String getFs_order_orders_get_created_order_initialization() {
        
        return fs_order_orders_get_created_order_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文訂正初期情報取得API
     */
    public static String getFs_order_orders_order_sub_no_get_updated_order_initialization() {
        
        return fs_order_orders_order_sub_no_get_updated_order_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文訂正確認API
     */
    public static String getFs_order_orders_confirm_updated_order() {
        
        return fs_order_orders_confirm_updated_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現物注文取消初期情報取得API
     */
    public static String getFs_order_orders_order_sub_no_get_deleted_initialization() {
        
        return fs_order_orders_order_sub_no_get_deleted_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文詳細取得API
     */
    public static String getFs_order_orders_margin_orders_order_sub_no_get_detail() {
        
        return fs_order_orders_margin_orders_order_sub_no_get_detail;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文初期情報取得API
     */
    public static String getFs_order_margin_orders_get_created_order_initialization() {
        
        return fs_order_margin_orders_get_created_order_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用新規建注文確認API
     */
    public static String getFs_order_margin_orders_confirm_created_order() {
        
        return fs_order_margin_orders_confirm_created_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文訂正初期情報取得API
     */
    public static String getFs_order_margin_orders_order_sub_no_get_updated_order_initialization() {
        
        return fs_order_margin_orders_order_sub_no_get_updated_order_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文訂正確認API
     */
    public static String getFs_order_margin_orders_confirm_updated_order() {
        
        return fs_order_margin_orders_confirm_updated_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用注文取消初期情報取得API
     */
    public static String getFs_order_margin_orders_order_sub_no_get_deleted_order_initialization() {
        
        return fs_order_margin_orders_order_sub_no_get_deleted_order_initialization;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式現地営業日取得API（日数指定版）
     */
    public static String getFs_order_countries_country_code_base_dates_base_date_business_dates() {
        
        return fs_order_countries_country_code_base_dates_base_date_business_dates;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用返済注文確認API
     */
    public static String getFs_order_margin_orders_confirm_closed_order() {
        
        return fs_order_margin_orders_confirm_closed_order;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式注文一覧取得API
     */
    public static String getFs_order_orders() {
        
        return fs_order_orders;
    }
    
    /**
     * @return マーケット価格情報サービス - マーケット価格取得API
     */
    public static String getInfo_market_price_prices() {
        
        return info_market_price_prices;
    }
    
    /**
     * @return マーケット価格情報サービス - マーケット価格取得用チケット登録API
     */
    public static String getInfo_market_price_tickets() {
        
        return info_market_price_tickets;
    }
    
    /**
     * @return マーケット価格情報サービス - マーケット価格ハッシュ取得API
     */
    public static String getInfo_market_price_countries_country_code_price_hashes() {
        
        return info_market_price_countries_country_code_price_hashes;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式信用返済注文登録API
     */
    public static String getFs_order_margin_orders_close_order() {
        
        return fs_order_margin_orders_close_order;
    }
    
    /**
     * @return 外国株式信用銘柄サービス - 外国株式信用売建可能銘柄一覧取得API
     */
    public static String getFs_securities_shortable_stocks() {
        
        return fs_securities_shortable_stocks;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建余力取得API
     */
    public static String getAcc_account_balance_margin_powers_getHeadline() {
        
        return acc_account_balance_margin_powers_getHeadline;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建余力サマリ取得API
     */
    public static String getAcc_account_balance_margin_powers_getSummary() {
        
        return acc_account_balance_margin_powers_getSummary;
    }
    
    /**
     * @return 余力サービス - リアルタイム委託保証金率取得API
     */
    public static String getAcc_account_balance_margin_powers_listDepositRateBasis() {
        
        return acc_account_balance_margin_powers_listDepositRateBasis;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建玉明細取得API
     */
    public static String getAcc_balance_positions_getDetail() {
        
        return acc_balance_positions_getDetail;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建玉明細一覧取得API
     */
    public static String getAcc_account_balance_positions_listDetails() {
        
        return acc_account_balance_positions_listDetails;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建玉サマリ取得API
     */
    public static String getAcc_balance_positions_getSummary() {
        
        return acc_balance_positions_getSummary;
    }
    
    /**
     * @return 追証サービス - 追証請求明細一覧取得API
     */
    public static String getAcc_margin_call_listDetails() {
        
        return acc_margin_call_listDetails;
    }
    
    /**
     * @return 余力サービス - 外貨建商品保有証券取得API
     */
    public static String getAcc_balance_securities() {
        
        return acc_balance_securities;
    }
    
    /**
     * @return 余力サービス - 外国株式参考信用建余力取得API
     */
    public static String getAcc_account_balance_margin_powers_getReference() {
        
        return acc_account_balance_margin_powers_getReference;
    }
    
    /**
     * @return 口座情報サービス - 信用口座自動振替設定取得API
     */
    public static String getAcc_margin_accounts_getSetting() {
        
        return acc_margin_accounts_getSetting;
    }
    
    /**
     * @return 口座情報サービス - 信用口座自動振替設定登録API
     */
    public static String getAcc_margin_accounts_createSetting() {
        
        return acc_margin_accounts_createSetting;
    }
    
    /**
     * @return 外国株式貸株サービス - 外国株式貸株サービス加入判定API
     */
    public static String getFs_lending_stock_lendings_getStatus() {
        
        return fs_lending_stock_lendings_getStatus;
    }
    
    /**
     * @return 余力サービス - 外貨信用保証金残高スケジュール取得API
     */
    public static String getAcc_balance_margin_cash_balance() {
        
        return acc_balance_margin_cash_balance;
    }
    
    /**
     * @return 余力サービス - 外国株式信用建余力詳細取得API
     */
    public static String getAcc_balance_margin_powers_getDetail() {
        
        return acc_balance_margin_powers_getDetail;
    }
    
    /**
     * @return 認証サービス - オペレータ権限一覧取得API
     */
    public static String list_operator_scopes() {
        
        return list_operator_scopes;
    }
    
    /**
     * @return 為替取引サービス - 通貨別サービスステータスチェックAPI
     */
    public static String check_exchange_currency_service_status() {
        
        return check_exchange_currency_service_status;
    }
    
    /**
     * @return 為替マスタサービス - 為替取引通貨マスタ取得API
     */
    public static String get_exchange_trade_currency() {
        
        return get_exchange_trade_currency;
    }
    
    /**
     * @return 為替マスタサービス - 為替取引レート情報一覧取得API（IFA向け）
     */
    public static String list_ifa_exchange_trade_rates() {
        
        return list_ifa_exchange_trade_rates;
    }
    
    /**
     * @return 為替マスタサービス - 為替取引レート情報一覧取得API（リテール向け）
     */
    public static String list_exchange_trade_rates() {
        
        return list_exchange_trade_rates;
    }
    
    /**
     * @return 為替取引サービス - 営業日情報取得API（IFA向け）
     */
    public static String get_ifa_exchange_business_date() {
        
        return get_ifa_exchange_business_date;
    }
    
    /**
     * @return 為替取引サービス - 営業日情報取得API（リテール向け）
     */
    public static String get_exchange_business_date() {
        
        return get_exchange_business_date;
    }
    
    /**
     * @return 入出金・入出庫サービス - 出金可能金額一括取得API 
     */
    public static String multi_get_possible_withdrawals() {
        
        return multi_get_possible_withdrawals;
    }
    
    /**
     * @return 為替取引サービス - 為替注文確認API（IFA向け） 
     */
    public static String confirm_ifa_exchange_created_order() {
        
        return confirm_ifa_exchange_created_order;
    }
    
    /**
     * @return 為替取引サービス - 為替注文確認API（リテール向け） 
     */
    public static String confirm_exchange_created_order() {
        
        return confirm_exchange_created_order;
    }
    
    /**
     * @return 為替取引サービス - 為替注文取消初期情報取得API（リテール向け） 
     */
    public static String get_exchange_cancelled_order_initialization() {
        
        return get_exchange_cancelled_order_initialization;
    }
    
    /**
     * @return 為替取引サービス - 為替注文取消登録API（リテール向け） 
     */
    public static String cancel_exchange_order() {
        
        return cancel_exchange_order;
    }
    
    /**
     * @return 為替取引サービス - 為替注文一覧取得API
     */
    public static String getExchange_order_orders() {
        
        return exchange_order_orders;
    }
    
    /**
     * @return 為替マスタサービス - 為替取引通貨マスタ一覧取得API
     */
    public static String getExchange_master_exchange_trade_currencies() {
        
        return exchange_master_exchange_trade_currencies;
    }
    
    /**
     * @return 外国株式取引サービス - 外国株式ロシア株呼値情報取得API
     */
    public static String getFs_order_countries_ru_ticksizes_securities() {
        
        return fs_order_countries_ru_ticksizes_securities;
    }
    
    /**
     * @return 為替取引サービス - 為替注文登録（IFA向け）API
     */
    public static String create_ifa_exchange_order() {
        
        return create_ifa_exchange_order;
    }
    
    /**
     * @return 為替取引サービス - 為替注文登録（リテール向け）API
     */
    public static String create_exchange_order() {
        
        return create_exchange_order;
    }
    
    /**
     * @return アカウントサービス - 外貨建口座JrNISA口座開設ステータス取得API
     */
    public static String get_jrnisa_account_status() {
        
        return get_jrnisa_account_status;
    }
    
    /**
     * 
     * @return 預り金一括取得API
     */
    public static String multi_get_cash_deposits() {
        return multi_get_cash_deposits;
    }
}
