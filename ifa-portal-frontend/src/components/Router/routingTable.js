/**
 * menuId: 遷移先画面の画面ID
 * url: 遷移先画面のURL
 * target: 遷移先画面のタブID､メニューがある場合はメニューIDをコロン(':')で連結する
 * exclude: true の場合､IfaInit から遷移先として除外する
*/
export const routingTable = function() {
  return [
    // [Side] ホーム
    { menuId: 'SUB01-01', url: '/wholePortal', target: '' }, // ホーム > [C] 総合ポータル_ホーム

    // [Side] オンライン口座開設 > オンライン口座開設
    { menuId: 'SUB0207_0201', url: '/onLineAccountOpen', target: 'tab-ifa-on-line-account-open' }, // [Tab] オンライン口座開設 > [C] オンライン口座開設

    // [Side] 新規口座開設不備状況 > 新規口座開設不備状況
    { menuId: 'SUB020305', url: '/newAccountOpenImcompleteStatus', target: 'tab-ifa-new-account-open-imcomplete-status' }, // [Tab] 新規口座開設不備状況 > [C] 新規口座開設不備状況

    // [Side] 仲介業メニュー > 顧客一覧
    { menuId: 'SUB0201_01', url: '/brokerageMenu/customerList', target: 'tab-ifa-customer-list' }, // [Tab] 顧客一覧 > [C} 顧客一覧
    { menuId: 'SUB0201_02', url: '/brokerageMenu/customerList', target: 'tab-ifa-customer-list-margin' }, // [Tab] 顧客一覧信用 > [C] 顧客一覧・信用
    { menuId: 'SUB0201_03', url: '/brokerageMenu/customerList', target: 'tab-ifa-customer-list-futures-options' }, // [Tab] 顧客一覧先OP > [C] 顧客一覧・先OP
    // [Side] 仲介業メニュー > 顧客別メニュー
    { menuId: 'SUB0202', url: '/brokerageMenu/customerMenu', target: '' }, // [C} 顧客検索
    { menuId: 'SUB0202_0101', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-portfolio', exclude: true }, // [Tab] 口座管理 > [Menu] 資産状況 > [C] ポートフォリオ
    { menuId: 'SUB0202_010201', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-holding-security-list', exclude: true }, // [Tab] 口座管理 > [Menu] 残高情報 > [C] 保有商品一覧
    { menuId: 'SUB0202_010202', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-domestic-position-list', exclude: true }, // [Tab] 口座管理 > [Menu] 残高情報 > [C] 国内建玉一覧
    { menuId: 'SUB0202_010203', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-foreign-position-list', exclude: true }, // [Tab] 口座管理 > [Menu] 残高情報 > [C] 外国建玉一覧
    { menuId: 'SUB0202_010204', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-collateral-security-list', exclude: true }, // [Tab] 口座管理 > [Menu] 残高情報 > [C] 代用有価証券一覧
    { menuId: 'SUB0202_010301', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-buying-power-domestic', exclude: true }, // [Tab] 口座管理 > [Menu] 余力情報 > [C] 買付余力(国内)
    { menuId: 'SUB0202_010303', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-buying-power-foreign', exclude: true }, // [Tab] 口座管理 > [Menu] 余力情報 > [C] 買付余力(外国)
    { menuId: 'SUB0202_010302', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-margin-power-domestic', exclude: true }, // [Tab] 口座管理 > [Menu] 余力情報 > [C] 信用余力(国内)
    { menuId: 'SUB0202_010304', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-margin-power-foreign', exclude: true }, // [Tab] 口座管理 > [Menu] 余力情報 > [C] 信用余力(外国)
    { menuId: 'SUB0202_0104', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-order-status-list', exclude: true }, // [Tab] 口座管理 > [Menu] 注文状況 > [C] 注文状況一覧
    { menuId: 'SUB0202_0105', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:Ifa-domestic-trade-status-list', exclude: true }, // [Tab] 口座管理 > [Menu] 約定状況 > [C] 国内約定状況一覧
    { menuId: 'SUB0202_0108', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-foreign-currency-deposit-withdraw', exclude: true }, // [Tab] 口座管理 > [Menu] 外貨入出金> [IFA-LINK] 外貨入出金
    { menuId: 'SUB0202_0109', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-customer-trade-history', exclude: true }, // [Tab] 口座管理 > [Menu] 取引履歴> [C] 取引履歴
    { menuId: 'SUB0202_0106', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-contact', exclude: true }, // [Tab] 口座管理 > [Menu] 接触履歴> [CCS-SSO] 顧客属性詳細
    { menuId: 'SUB0202_0107', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-inquiry', exclude: true }, // [Tab] 口座管理 > [Menu] 問合せ> [CCS-SSO] 問合せ一覧
    { menuId: 'SUB0202_0110', url: '/brokerageMenu/customerMenu', target: 'tab-account-management:ifa-other-remain-power-restrain', exclude: true }, // [Tab] 口座管理 > [Menu] その他余力拘束

    { menuId: 'SUB0202_0208', url: '/brokerageMenu/customerMenu', target: 'tab-domestic-stock-order:ifa-domestic-stock-order-input', exclude: true }, // [Tab] 国内株式 > [Menu] 現物取引 > [C] 国内株式注文入力
    { menuId: 'SUB0202_0209', url: '/brokerageMenu/customerMenu', target: 'tab-domestic-stock-order:ifa-off-floor-distribution', exclude: true }, // [Tab] 国内株式 > [Menu] 立会外分売 > [CCS-SSO] 国内株式注文入力
    { menuId: 'SUB0202_0210', url: '/brokerageMenu/customerMenu', target: 'tab-domestic-stock-order:ifa-off-floor-trd', exclude: true }, // [Tab] 国内株式 > [Menu] 立会外TRD > [CCS-SSO] 国内株式注文入力
    { menuId: 'SUB0202_0211', url: '/brokerageMenu/customerMenu', target: 'tab-domestic-stock-order:ifa-fraction-stock', exclude: true }, // [Tab] 国内株式 > [Menu] 単元未満 > [CCS-SSO] 国内株式注文入力
    { menuId: 'SUB0202_0212', url: '/brokerageMenu/customerMenu', target: 'tab-domestic-stock-order:ifa-credit-order-wrapper', exclude: true }, // [Tab] 国内株式 > [Menu] 信用取引

    { menuId: 'SUB0202_0301', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-spot-transaction-order', exclude: true }, // [Tab] 外国株式 > [Menu] 現物取引 > [C] 外国現物取引注文入力
    { menuId: 'SUB0202_0302', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-price-view-lookup-foreign-stock-brand-list', exclude: true }, // [Tab] 外国株式 > [Menu] 米国店頭 > [C} 単価表照会
    { menuId: 'SUB0202_0303', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-foreign-stock-credit-wrapper', exclude: true }, // [Tab] 外国株式 > [Menu] 信用取引
    { menuId: 'SUB0202_0304', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-foreign-margin-deposit-transfer-input', exclude: true }, // [Tab] 外国株式 > [Menu] 保証金振替 > [C] 外国信用保証金振替入力
    { menuId: 'SUB0202_0305', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-foreign-margin-collateral-transfer-input', exclude: true }, // [Tab] 外国株式 > [Menu] 代用振替 > [C] 米株信用代用振替入力
    { menuId: 'SUB0202_0306', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-stocks:ifa-foreign-margin-auto-transfer-setting-input', exclude: true }, // [Tab] 外国株式 > [Menu] 自動振替設定 > [C] 米株信用自動振替設定入力
    { menuId: 'SUB0202_0401', url: '/brokerageMenu/customerMenu', target: 'tab-investment-trust:ifa-domestic-mutual-fund-buy-ableList', exclude: true }, // [Tab] 投資信託 > [Menu] 国内投信 > [C] 国内投信購入可能一覧
    { menuId: 'SUB0202_0403', url: '/brokerageMenu/customerMenu', target: 'tab-investment-trust:ifa-mutual-fund-accumulate', exclude: true }, // [Tab] 投資信託 > [Menu] 定期積立 > [CCS-SSO] 口座サマリ
    { menuId: 'SUB0202_0404', url: '/brokerageMenu/customerMenu', target: 'tab-investment-trust:ifa-foreign-currency-mmf', exclude: true }, // [Tab] 投資信託 > [Menu] 外貨建MMF> [IFA-LINK] 外貨建MMF
    
    { menuId: 'SUB0202_0502', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-exchange-transactions:ifa-currency-dealt-list', exclude: true }, // [Tab] 為替取引 > [Menu] 為替取引 > [C] 取扱通貨一覧
    { menuId: 'SUB0202_0503', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-exchange-transactions:ifa-ifa-currency-dealt-list', exclude: true }, // [Tab] 為替取引 > {menu] 【IFA】 為替取引 > [C] 【IFA】取扱通貨一覧
    { menuId: 'SUB0202_0501', url: '/brokerageMenu/customerMenu', target: 'tab-foreign-exchange-transactions:ifa-fx-trade-order-lookup', exclude: true }, // [Tab] 為替取引 > [menu] 注文照会 > 為替取引注文照会

    { menuId: 'SUB0202_0601', url: '/brokerageMenu/customerMenu', target: 'tab-withdraw:ifa-withdraw', exclude: true }, // [Tab] 出金 > [menu] 出金 > [CCS-SSO] 出金計上日指定

    { menuId: 'SUB0202_0701', url: '/brokerageMenu/customerMenu', target: 'tab-customer-manage:ifa-register-info', exclude: true }, // [Tab] 顧客管理 > [menu] 登録情報 > [CCS-SSO] 口座サマリ
    // { menuId: 'SUB0202_0702', url: '/brokerageMenu/customerMenu', target: 'tab-customer-manage:ifa-customer-profile', exclude: true }, // [Tab] 顧客管理 > [menu] 顧客属性 > [CCS-SSO] 顧客属性詳細
    { menuId: 'SUB0202_0703', url: '/brokerageMenu/customerMenu', target: 'tab-customer-manage:ifa-send-receive-status', exclude: true }, // [Tab] 顧客管理 > [menu] 受発信状況 > [CCS-SSO] 顧客属性詳細
    { menuId: 'SUB0202_0704', url: '/brokerageMenu/customerMenu', target: 'tab-customer-manage:ifa-doc-request', exclude: true }, // [Tab] 顧客管理 > [menu] 書類請求 > [CCS-SSO] 書類請求一覧

    { menuId: 'SUB0202_08', url: '/brokerageMenu/customerMenu', target: 'tab-external-link:ifa-external-link', exclude: true }, // [Tab] 外部リンク > [menu] 外部リンク > [CCS-SSO] 口座サマリ
    { menuId: 'SUB0202_09', url: '/brokerageMenu/customerMenu', target: 'tab-ccs-link:ifa-customer-profile', exclude: true }, // [Tab] CCS > [menu] CCS > [CCS-SSO] 顧客属性詳細

    // [Side] 仲介業メニュー > 全顧客メニュー > アラート情報
    { menuId: 'SUB020301_01-01', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-spare-capacity-alert:ifa-jpy-amount-unpaid-overdraft-alert-list' }, // [Tab] 未済・信用担保不足アラート > [Menu] 円貨未入金・赤残アラート一覧 > [C] 円貨未入金・赤残アラート一覧
    { menuId: 'SUB020301_01-02', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-spare-capacity-alert:ifa-domestic-margin-collateral-deficient-alert-list' }, // [Tab] 未済・信用担保不足アラート > [Menu] 国内信用担保不足アラート一覧 > [C] 国内信用担保不足アラート一覧
    { menuId: 'SUB020301_01-03', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-spare-capacity-alert:ifa-foreign-amount-unpaid-overdraft-alert-list' }, // [Tab] 未済・信用担保不足アラート > [Menu] 外貨未入金・赤残アラート一覧 > [C] 外貨未入金・赤残アラート一覧
    { menuId: 'SUB020301_01-04', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-spare-capacity-alert:ifa-foreign-margin-collateral-deficient-alert-list' }, // [Tab] 未済・信用担保不足アラート > [Menu] 外国信用担保不足アラート一覧 > [C] 外国信用担保不足アラート一覧
    { menuId: 'SUB020301_02-01', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-due-date-alert:ifa-domestic-margin-position-due-date-alert' }, // [Tab] 期日アラート > [Menu] 国内信用建玉期日アラート > [C] 国内信用建玉期日アラート一覧
    { menuId: 'SUB020301_02-02', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-due-date-alert:ifa-foreign-margin-position-due-date-alert' }, // [Tab] 期日アラート > [Menu] 外国信用建玉期日アラート > [C] 外国信用建玉期日アラート一覧
    { menuId: 'SUB020301_03-01', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-stock-alert:ifa-mutual-fund-price-change-brand-holding-list' }, // [Tab] 銘柄アラート > [Menu] 投信基準価額変動の銘柄保有一覧 > [C] 投信基準価額変動の銘柄保有一覧
    { menuId: 'SUB020301_03-02', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-stock-alert:ifa-knock-in-brand-holding-list' }, // [Tab] 銘柄アラート > [Menu] ノックイン銘柄保有一覧 > ノックイン銘柄保有一覧
    { menuId: 'SUB020301_03-03', url: '/brokerageMenu/wholeCustomer/alertInfo', target: 'tab-stock-alert:ifa-knock-out-brand-holding-list' }, // [Tab] 銘柄アラート > [Menu] ノックアウト銘柄保有一覧 > [C] ノックアウト銘柄保有一覧
    // [Side] 仲介業メニュー > 全顧客メニュー > 取引状況/残高検索・照会
    { menuId: 'SUB020302_0101', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-trading-conditions:ifa-order-list' }, // [Tab] 取引状況 > [Menu] 注文一覧 > [C] 注文一覧
    { menuId: 'SUB020302_0102', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-trading-conditions:ifa-today-trade-list' }, // [Tab] 取引状況 > [Menu] 当日約定一覧 > [C] 当日約定一覧
    { menuId: 'SUB020302_0104', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-trading-conditions:ifa-coupon-redemption-payment-schedule-list' }, // [Tab] 取引状況 > [Menu] 利金・償還金支払予定一覧 > [C] 利金・償還金支払予定一覧
    { menuId: 'SUB020302_0201', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-withdrawals:ifa-trade-history' }, // [Tab] 取引履歴 > [Menu] 取引履歴 > [C] 取引履歴
    { menuId: 'SUB020302_0202', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-withdrawals:ifa-fx-trade-history' }, // [Tab] 取引履歴 > [Menu] 為替取引履歴 > [C] 為替取引履歴
    { menuId: 'SUB020302_0203', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-withdrawals:ifa-deposit-withdraw-detail' }, // [Tab] 取引履歴 > [Menu] 入出金明細 > [C] 入出金明細
    { menuId: 'SUB020302_0204', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-withdrawals:ifa-deliver-in-out-detail' }, // [Tab] 取引履歴 > [Menu] 入出庫明細 > [C] 入出庫明細
    { menuId: 'SUB020302_0301', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-balance-information:ifa-security-cash-balance-lookup' }, // [Tab] 残高情報 > [Menu] 証券・金銭・残高照会 > [C] 証券・金銭・残高照会
    { menuId: 'SUB020302_0302', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-balance-information:ifa-margin-position-list-domestic' }, // [Tab] 残高情報 > [Menu] 信用建玉一覧（国内） > [C] 信用建玉一覧（国内）
    { menuId: 'SUB020302_0303', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-balance-information:ifa-margin-position-list-foreign' }, // [Tab] 残高情報 > [Menu] 信用建玉一覧（外国） > [C] 信用建玉一覧（外国）
    { menuId: 'SUB020302_0401', url: '/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch', target: 'tab-trade-trend:ifa-trade-trend-search' }, // [Tab] 取引動向> [Menu] 取引動向 > [C] 取引動向検索
    // [Side] 仲介業メニュー > 全顧客メニュー > 顧客振込先金融機関口座
    { menuId: 'SUB020303', url: '/brokerageMenu/wholeCustomer/customerDestinationBankAccount', target: 'tab-ifa-customer-destination-bank-account' }, // [Tab] 顧客振込先金融機関口座 > [C] 顧客振込先金融機関口座
    // [Side] 仲介業メニュー > 全顧客メニュー > 接触履歴（入力）検索
    { menuId: 'SUB020304', url: '/brokerageMenu/wholeCustomer/inquirySearchForManager', target: 'tab-ifa-inquiry-search-for-manager' }, // [Tab] 接触履歴（入力）検索 > [C] 接触履歴（入力）検索
    // [Side] 仲介業メニュー > IPOPO
    { menuId: 'SUB0204_01', url: '/brokerageMenu/ipoPo', target: 'tab-ifa-ipo-Po-brand-list' }, // [Tab] IPO/PO銘柄一覧 > [C] IPO/PO銘柄一覧
    { menuId: 'SUB0204_02', url: '/brokerageMenu/ipoPo', target: 'tab-ifa-bb-apply-list' }, // [Tab] BB申込一覧 >C] BB申込一覧
    // [Side] 仲介業メニュー > 手数料・報酬 > 担当顧客別手数料一覧
    { menuId: 'SUB020501-01', url: '/brokerageMenu/commFee/repCustomerCommList', target: 'tab-ifa-rep-customer-comm-list' }, // [Tab] 担当顧客別手数料一覧 > [C] 担当顧客別手数料一覧
    // [Side] 仲介業メニュー > 手数料・報酬 > 手数料・報酬
    { menuId: 'SUB020502-01', url: '/brokerageMenu/commFee/commFee', target: 'tab-ifa-comm-fee' }, // [Tab] 手数料・報酬 > [C] 手数料・報酬
    // [Side] 仲介業メニュー > 手数料・報酬 > 信託報酬
    { menuId: 'SUB020503-01', url: '/brokerageMenu/commFee/trustFee', target: 'tab-ifa-trust-fee' }, // [Tab] 信託報酬 > [C] 信託報酬
    // [Side] 仲介業メニュー > 手数料・報酬 > SBIラップ管理報酬
    { menuId: 'SUB020504-01', url: '/brokerageMenu/commFee/sbiWrapManageFee', target: 'tab-ifa-sbi-wrap-manage-fee' }, // [Tab] SBIラップ管理報酬 > [C] SBIラップ管理報酬
    // [Side] 仲介業メニュー > 手数料・報酬 > レベルフィー
    { menuId: 'SUB020505-01', url: '/brokerageMenu/commFee/levelFee', target: 'tab-ifa-level-fee' }, // [Tab] レベルフィー > [C] レベルフィー

    // [Side] 仲介業メニュー > 各種申請・報告
    { menuId: 'SUB0209-99', url: '/brokerageMenu/allSortsApplyReport', target: 'tab-ifa-all-sorts-apply-report' }, // [Tab] 各種申請・報告 > [C] 各種申請・報告

    // [Side] 仲介業メニュー > 共同募集
    { menuId: 'SUB0206_01-01', url: '/brokerageMenu/jointSubscript', target: 'tab-ifa-joint-subscript-customer-manage' }, // [TAB] 共同募集 > [C] 共同募集　顧客管理
    { menuId: 'SUB0206_02-01', url: '/brokerageMenu/jointSubscript', target: 'tab-ifa-joint-subscript-trade-search' }, // [TAB] 共同募集 > [C] 共同募集　取引
    { menuId: 'SUB0206_03-01', url: '/brokerageMenu/jointSubscript', target: 'tab-ifa-joint-subscript-trust-fee' }, // [TAB] 共同募集 > [C] 共同募集　信託報酬
    { menuId: 'SUB0206_04-01', url: '/brokerageMenu/jointSubscript', target: 'tab-ifa-joint-subscript-security-cash-balance-lookup' }, // [TAB] 共同募集 > [C] 共同募集　証券・金銭残高照会

    // [Side] 仲介業メニュー > 共同店舗
    { menuId: 'SUB0208_01', url: '/brokerageMenu/jointMarketTradeSearch', target: 'tab-ifa-joint-market-trade-search' }, // [TAB] 共同店舗　> [C] 共同店舗　取引検索
    { menuId: 'SUB0208_02', url: '/brokerageMenu/jointMarketTrustFee', target: 'tab-ifa-joint-market-trust-fee' }, // [Tab] 共同店舗　> [C] 共同店舗　信託報酬

    // [Side] コンプライアンス通信 > コンプライアンス通信
    { menuId: 'SUB0302-01', url: '/complianceReport', target: 'tab-ifa-compliance-letter' }, // [Tab] コンプライアンス通信 > [C] コンプライアンス通信

    // [Side] 内部管理責任者メニュー > マンスリー実施項目
    { menuId: 'SUB0401_01', url: '/internalAdminMenu/monthlyImplementationItem', target: 'tab-ifa-compliance-report-view-status-lookup-internal-admin' }, // [Tab] コンプライアンス通信閲覧状況照会 > [C] コンプライアンス通信閲覧状況照会(内部管理責任者用)
    { menuId: 'SUB0401_02', url: '/internalAdminMenu/monthlyImplementationItem', target: 'tab-ifa-self-inspect-blotter' }, // [Tab] 自己点検記録簿 > [C] 自己点検記録簿
    // [Side] 内部管理責任者メニュー > 帳票取得
    { menuId: 'SUB0402_01', url: '/internalAdminMenu/formAcquire', target: 'tab-ifa-brokerage-sub-ledger-acquire' }, // [Tab] 仲介業補助簿取得 > [C] 仲介業補助簿取得
    { menuId: 'SUB0402_02', url: '/internalAdminMenu/formAcquire', target: 'tab-ifa-contract-note-customer-ledger-acquire' }, // [Tab] 取引日記帳・顧客勘定元帳取得 > [C] 取引日記帳・顧客勘定元帳取得
    // [Side] 内部管理責任者メニュー > 年度別口座数・報酬額照会
    { menuId: 'SUB0406-01', url: '/internalAdminMenu/byYearAccountQuantityFeeAmountLookup', target: 'tab-ifa-by-year-account-quantity-fee-amount-lookup' }, // [Tab] 年度別口座数・報酬額照会 > [C] 年度別口座数・報酬額照会
    // [Side] 内部管理責任者メニュー > 個人情報管理
    { menuId: 'SUB0403-01', url: '/internalAdminMenu/personalInfoManage', target: 'tab-ifa-personal-info-manage-ledger-list' }, // [Tab] 個人情報管理台帳一覧 > [C] 個人情報管理台帳一覧
    // [Side] 内部管理責任者メニュー > 認証用メールアドレス変更
    { menuId: 'SUB0404-01', url: '/internalAdminMenu/authMailAddressChange', target: 'tab-ifa-auth-mail-address-change-list' }, // [Tab] 認証用メールアドレス変更一覧 > [C] 認証用メールアドレス変更一覧
    // [Side] 内部管理責任者メニュー > 支払通知書ダウンロード
    { menuId: 'SUB0405-01', url: '/internalAdminMenu/payNotificationDocDownload', target: 'tab-ifa-pay-notification-doc-download' }, // [Tab] 支払通知書ダウンロード > [C] 支払通知書ダウンロード
    // [Side] 内部管理責任者メニュー > 各種申請・報告(管理者向け)
    { menuId: 'SUB0408-99', url: '/internalAdminMenu/complianceRelatedReport', target: 'tab-ifa-compliance-related-report' }, // [Tab] 各種申請・報告(管理者向け) > [C] 各種申請・報告(管理者向け)
    // [Side] 内部管理責任者メニュー > 月末口座数
    { menuId: 'SUB0407_01', url: '/internalAdminMenu/monthCustomerNum', target: 'tab-ifa-month-customer-num' }, // [Tab] 月末口座数 > [C] 月末口座数
    // [Side] 社員用メニュー > Information登録
    { menuId: 'SUB0501_01', url: '/companyEmployeeMenu/infoRegister', target: 'tab-ifa-info-register-lookup' }, // [Tab] Information登録 > [C] Information登録照会
    // [Side] 社員用メニュー > 取引データ管理
    { menuId: 'SUB0504_02', url: '/companyEmployeeMenu/tradeDataManage', target: 'tab-ifa-edeliv-agreement-data-register' }, // [Tab] 電子交付同意データ登録
    { menuId: 'SUB0504_04', url: '/companyEmployeeMenu/tradeDataManage', target: 'tab-ifa-foreign-bond-buy-amount-input-list' }, // [Tab] 外債買付代金入力 > [C] 外債買付代金入力情報登録
    // [Side] 社員用メニュー > コンプライアンス通信
    { menuId: 'SUB0505_01', url: '/companyEmployeeMenu/complianceReport', target: 'tab-ifa-compliance-report-info-register-manager' }, // [Tab] コンプライアンス通信情報登録 > [C] コンプライアンス通信情報登録（管理者用）
    { menuId: 'SUB0505_02', url: '/companyEmployeeMenu/complianceReport', target: 'tab-ifa-compliance-report-view-status-lookup-manager' }, // [Tab] コンプライアンス通信閲覧状況照会 > [C] コンプライアンス通信閲覧状況照会（管理者用）
    { menuId: 'SUB0505_03', url: '/companyEmployeeMenu/complianceReport', target: 'tab-ifa-compliance-report-broker-block-view-exclude-setting' }, // [Tab] コンプライアンス通信仲介業者一括閲覧不要設定 > [C] コンプライアンス通信仲介業者一括閲覧不要設定
    // [Side] 社員用メニュー > 自己点検
    { menuId: 'SUB0506_01', url: '/companyEmployeeMenu/selfInspect', target: 'tab-ifa-self-inspect-blotter-confirm-manager' }, // [TAB] 自己点検記録簿確認 > [C] 自己点検記録簿確認（管理者用）
    { menuId: 'SUB0506_02', url: '/companyEmployeeMenu/selfInspect', target: 'tab-ifa-self-inspect-item-manage' }, // [TAB] 自己点検項目管理 > [C] 自己点検項目管理
    // [Side] 社員用メニュー > 目安箱
    { menuId: 'SUB0511_01', url: '/companyEmployeeMenu/suggestionBox', target: 'tab-ifa-suggestion-box-personal' }, // [Tab] 仲介業者からの要望確認 > [C] 仲介業者からの要望確認
    { menuId: 'SUB0511_02', url: '/companyEmployeeMenu/suggestionBox', target: 'tab-ifa-suggestion-box-common' }, // [Tab] 皆様からの要望 > [C] 皆様からの要望
    // [Side] 社員用メニュー > 共同募集
    { menuId: 'SUB0513_01', url: '/companyEmployeeMenu/jointContract', target: 'tab-ifa-joint-contract-master' }, // [Tab] 共同募集 > [C] 共同募集契約設定

    // [Side] システム管理メニュー > ログイン者管理
    { menuId: 'SUB0601_01', url: '/systemManageMenu/loginUserManage', target: 'tab-ifa-login-user-manage-manager-lookup' }, // [Tab] ログイン者管理（管理者用） > [C] ログイン者管理（管理者用）照会
    // [Side] システム管理メニュー > SBI証券からのご連絡登録
    { menuId: 'SUB0602-01', url: '/systemManageMenu/sbiSecurityNotificationRegister', target: 'tab-ifa-portal-notification-manager-lookup' }, // [Tab] SBI証券からのご連絡登登録 > [C] SBI証券からのご連絡一覧

    // [Navbar] 目安箱
    { menuId: 'SUB00_01', url: '/navbar/suggestionBox', target: 'tab-ifa-suggestion-box-personal' }, // [Tab] あなたの要望 > [C] あなたの要望
    { menuId: 'SUB00_02', url: '/navbar/suggestionBox', target: 'tab-ifa-suggestion-box-common' } // [Tab] 皆様からの要望 > [C] 皆様からの要望
  ]
}
