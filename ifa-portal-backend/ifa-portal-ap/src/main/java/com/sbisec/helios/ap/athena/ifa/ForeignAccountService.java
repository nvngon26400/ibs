package com.sbisec.helios.ap.athena.ifa;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileResp;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailResp;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMultiGetCashDepositsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.MultiGetCashDepositsReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp;

import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;




/**
 * IFA Athena 口座情報 Service
 * 
 * @author shuchen.xin
 * @date 02/21/2022
 */
public interface ForeignAccountService extends com.sbibits.earth.service.Service {
    
    /**
     * 委託保証金振替確認API
     *
     * @param butenCode        「必須」 部店コード(3桁)
     * @param accountNo        「必須」 口座番号(最大7桁)
     * @param currencyCode     「非必須」 通貨コード
     *                         <li>Yen.：JPY</li>
     *                         <li>US Dollar.：USD</li>
     * @param cashTransferType 「必須」 振替区分
     *                         <li>信用取引保証金→預り金：CASH_WITHDRAWAL</li>
     *                         <li>預り金→信用取引保証金：CASH_DEPOSIT</li>
     * @param amount           「必須」振替金額(サイズ範囲: 09999999999999.9999)
     * @return 委託保証金振替確認情報取得
     * @throws Exception 異常
     */
    public ConfirmMarginTransferResp confirmMarginTransfer(String butenCode, String accountNo, String currencyCode,
            String cashTransferType, String amount) throws Exception;
    
    /**
     * 委託保証金振替登録API
     * 
     * @param butenCode        「必須」 部店コード(3桁)
     * @param accountNo        「必須」 口座番号(最大7桁)
     * @param requestId        「必須」 Request Id
     * @param currencyCode     「非必須」 通貨コード
     *                         <li>Yen.：JPY</li>
     *                         <li>US Dollar.：USD</li>
     * @param cashTransferType 「必須」 振替区分
     *                         <li>信用取引保証金→預り金：CASH_WITHDRAWAL</li>
     *                         <li>預り金→信用取引保証金：CASH_DEPOSIT</li>
     * @param amount           「必須」振替金額(サイズ範囲: 09999999999999.9999)
     * @return 委託保証金振替登録情報取得
     * @throws Exception 異常
     */
    public CreateMarginTransferResp createMarginTransfer(String butenCode, String accountNo, String requestId,
            String currencyCode, String cashTransferType, String amount) throws Exception;
    
    /**
     * 外貨建商品保有証券一覧取得
     * 
     * @param butenCode           「必須」 部店コード(3桁)
     * @param accountNo           「必須」 口座番号(最大7桁)
     * @param ticket              「必須」 チケット
     * @param productCode         「非必須」 enums-商品コード-ProductCode
     *                            <li>外国株式：FOREIGN_STOCK</li>
     *                            <li>外貨建MMF：FOREIGN_MMF</li>
     *                            <li>外国債券：FOREIGN_BOND</li>
     *                            <li>外国為替：FOREIGN_EXCHANGE</li>
     * @param countryCode         「非必須」 enums-国コード-CountryCode
     *                            <li>Japan.：JP</li>
     *                            <li>United States of America (the).：US</li>
     * @param currencyCode        「非必須」 enums-通貨コード-CurrencyCode
     *                            <li>Yen.：JPY</li>
     *                            <li>US Dollar.：USD</li>
     * @param specificAccountCode 「非必須」 enums-預り区分-SpecificAccount
     *                            <li>一般：GENERAL</li>
     *                            <li>特定：SPECIFIC</li>
     *                            <li>特定管理：SPECIFIC_MANAGED</li>
     *                            <li>NISA：NISA</li>
     *                            <li>Jr一般：JR_GENERAL</li>
     *                            <li>Jr特定：JR_SPECIFIC</li>
     *                            <li>JrNISA：JR_NISA</li>
     * @param page                「非必須」 ページング
     * @return
     * @throws Exception
     */
    public ListSecuritiesBalancesResp listSecuritiesBalances(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode, Page page)
            throws Exception;
    
    /**
     * 外貨建商品保有証券一覧（外貨建MMF用）取得
     * 
     * @param butenCode           「必須」 部店コード(3桁)
     * @param accountNo           「必須」 口座番号(最大7桁)
     * @param ticket              「必須」 チケット
     * @param productCode         「非必須」 enums-商品コード-ProductCode
     *                            <li>外国株式：FOREIGN_STOCK</li>
     *                            <li>外貨建MMF：FOREIGN_MMF</li>
     *                            <li>外国債券：FOREIGN_BOND</li>
     *                            <li>外国為替：FOREIGN_EXCHANGE</li>
     * @param countryCode         「非必須」 enums-国コード-CountryCode
     *                            <li>Japan.：JP</li>
     *                            <li>United States of America (the).：US</li>
     * @param currencyCode        「非必須」 enums-通貨コード-CurrencyCode
     *                            <li>Yen.：JPY</li>
     *                            <li>US Dollar.：USD</li>
     * @param specificAccountCode 「非必須」 enums-預り区分-SpecificAccount
     *                            <li>一般：GENERAL</li>
     *                            <li>特定：SPECIFIC</li>
     *                            <li>特定管理：SPECIFIC_MANAGED</li>
     *                            <li>NISA：NISA</li>
     *                            <li>Jr一般：JR_GENERAL</li>
     *                            <li>Jr特定：JR_SPECIFIC</li>
     *                            <li>JrNISA：JR_NISA</li>
     * @param page                「非必須」 ページング
     * @return
     * @throws Exception
     */
    public ListSecuritiesBalancesResp listSecuritiesBalancesMmf(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode, Page page)
            throws Exception;
    
    /**
     * 余力サービス - 外貨建商品保有証券取得
     * 
     * @param butenCode           「必須」 部店コード(3桁)
     * @param accountNo           「必須」 口座番号(最大7桁)
     * @param ticket              「必須」 チケット
     * @param productCode         「必須」 商品コード
     * @param countryCode         「必須」 国コード
     * @param currencyCode        「必須」 通貨コード
     * @param specificAccountCode 「必須」 預り区分
     * @param securitiesCode      「必須」 銘柄コード
     * @return 外貨建商品保有証券情報
     * @throws Exception 異常
     */
    public GetSecuritiesBalanceResp getSecuritiesBalance(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode,
            String securitiesCode) throws Exception;
    
    /**
     * 外国株式信用建余力取得
     * 
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNumber        「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @return 外国株式信用建余力情報
     * @throws Exception 異常
     * 
     */
    public GetMarginPowerHeadlineResp getMarginPowerHeadline(String butenCode, String accountNumber, String countryCode)
            throws Exception;
    
    /**
     * 余力サービス - 外国株式参考信用建余力取得
     * 
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNumber 「必須」 口座番号(最大7桁)
     * @param countryCode   「必須」 国コード
     * @return 参考信用建余力情報
     * @throws Exception 異常
     */
    public GetMarginPowerReferenceResp getMarginPowerReference(String butenCode, String accountNumber,
            String countryCode) throws Exception;
    
    /**
     * オペレータ権限一覧取得API
     * 
     */
    public ListOperatorScopesRes listOperatorScopes(String butenCode, String accountNumber, String operatorId)
            throws Exception;
    
    /**
     * 出金可能金額一括取得API
     * 
     */
    public MultiGetPossibleWithdrawalsRes multiGetPossibleWithdrawals(MultiGetPossibleWithdrawalsReq req)
            throws Exception;
    
    /**
     * 外貨建口座JrNISA口座開設ステータス取得
     * @param req
     * @return
     */
    public GetJrNisaAccountStatusRes getJrNisaAccountStatus(GetJrNisaAccountStatusReq req) throws Exception;
    
    /**
     * アカウントプロファイル取得
     * 
     * @param butenCode   「必須」 部店コード(3桁)
     * @param accountNo   「必須」 口座番号(最大7桁)
     * @param profileName 「必須」 アカウントプロファイル名
     *                    <li>アカウントモード：ACCOUNT_MODE</li>
     *                    <li>外貨建口座取引開設状況：TRADE_FSTOCK_US_PERMISSION</li>
     *                    <li>外国債券取引可否：TRADE_FXBOND_PERMISSION</li>
     * @return アカウントプロファイル情報取得 GetAccountProfileResp
     * @throws Exception 異常
     */
    public GetAccountProfileResp getAccountProfile(String butenCode, String accountNo, String profileName)
            throws Exception;
    
    /**
     * 余力サービス - 外貨金銭残高スケジュール取得API
     * 
     */
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(ListForeignScheduleCashBalancesReq req)
            throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API
     * 
     */
    public GetMarginPositionSummaryResp getMarginPositionSummary(GetMarginPositionSummaryReq req) throws Exception;
    
    /**
     * 余力サービス - 代用有価証券振替可能一覧取得API
     * 
     */
    public ListPossibleCollateralSecuritiesTransfersResp listPossibleCollateralSecuritiesTransfers(
            ListPossibleCollateralSecuritiesTransfersReq req) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉明細一覧取得API
     * 
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNo            「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @param securitiesCode       「条件付き必須」 銘柄コード 
     * @param buySellCode          「非必須」 売買区分
     * @param specificAccountCode  「非必須」 預り区分
     * @param marginCloseLimitType 「非必須」 信用期日
     * @param tradeDate            「非必須」 国内約定日 "yyyy-MM-dd"形式
     * @param frnTradeDate         「非必須」 現地約定日 "yyyy-MM-dd"形式
     * @param frnPositionPrice     「非必須」 新規建単価（外貨）
     * @param page                 「非必須」 ページング
     * @return 外国株式信用建玉明細一覧
     * @throws Exception
     */
    public ListMarginPositionsResp listMarginPositions(String butenCode, String accountNumber, String countryCode, String securitiesCode, String buySellCode, String specificAccountCode, String marginCloseLimitType, String tradeDate, String frnTradeDate, String frnPositionPrice, String page)
            throws Exception;
    
    /**
     * 外国株式信用建玉明細取得
     * 
     * @param countryCode          「必須」 国コード
     * @param securitiesCode       「必須」 銘柄コード
     * @param buySellCode          「必須」 売買区分
     * @param marginCloseLimitType 「必須」信用期日
     * @param specificAccountCode  「必須」 預り区分
     * @param tradeDate            「必須」 国内約定日 "yyyy-MM-dd"形式
     * @param frnTradeDate         「必須」 現地約定日 "yyyy-MM-dd"形式
     * @param frnPositionPrice     「必須」 新規建単価（外貨）サイズ範囲: 0-999999999999.9999
     * @param positionPrice        「必須」 新規建単価（円貨）サイズ範囲: 0-999999999999.9999
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNo            「必須」 口座番号(最大7桁)
     * @return 外国株式信用建玉明細
     * @throws Exception
     */
    public GetMarginPositionResp getMarginPosition(String countryCode, String securitiesCode, String buySellCode,
            String marginCloseLimitType, String specificAccountCode, String tradeDate, String frnTradeDate,
            String frnPositionPrice, String positionPrice, String butenCode, String accountNumber) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNo            「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @param securitiesCode       「必須」 銘柄コード
     * @param buySellCode          「必須」 売買区分
     * @param marginCloseLimitType 「必須」 信用期日
     * @param specificAccountCode  「必須」 預り区分
     * @param tradeDate            国内約定日(yyyy-MM-dd)
     * @param frnTradeDate         現地約定日(yyyy-MM-dd)
     * @param ticket               「必須」 チケット
     * @return 外国株式信用建玉サマリ
     * @throws Exception 異常
     */
    public GetMarginPositionSummaryResp getMarginPositionSummary(String butenCode, String accountNo, String countryCode,
            String securitiesCode, String buySellCode, String marginCloseLimitType, String specificAccountCode,
            String tradeDate, String frnTradeDate,String ticket) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 返済注文確認用.
     * 
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNo            「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @param securitiesCode       「必須」 銘柄コード
     * @param buySellCode          「必須」 売買区分
     * @param marginCloseLimitType 「必須」 信用期日
     * @param specificAccountCode  「必須」 預り区分
     * @param tradeDate            国内約定日(yyyy-MM-dd)
     * @param frnTradeDate         現地約定日(yyyy-MM-dd)
     * @param ticket               「必須」 チケット
     * @return 外国株式信用建玉サマリ
     * @throws Exception 異常
     */
    public GetMarginPositionSummaryResp getMarginPositionSummaryForRepayOrderConfirm(String butenCode, String accountNo,
            String countryCode, String securitiesCode, String buySellCode, String marginCloseLimitType,
            String specificAccountCode, String tradeDate, String frnTradeDate,String ticket) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 売買区分 区分値変換：区分.売買区分（建玉）
     * 
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNo            「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @param securitiesCode       「必須」 銘柄コード
     * @param buySellCode          「必須」 売買区分
     * @param marginCloseLimitType 「必須」 信用期日
     * @param specificAccountCode  「必須」 預り区分
     * @param tradeDate            国内約定日(yyyy-MM-dd)
     * @param frnTradeDate         現地約定日(yyyy-MM-dd)
     * @return 外国株式信用建玉サマリ
     * @throws Exception 異常
     */
    public GetMarginPositionSummaryResp getMarginPositionSummaryForPositionDetails(String butenCode, String accountNo,
            String countryCode, String securitiesCode, String buySellCode, String marginCloseLimitType,
            String specificAccountCode, String tradeDate, String frnTradeDate) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建余力サマリ取得API
    
     * @param butenCode            「必須」 部店コード(3桁)
     * @param accountNumber        「必須」 口座番号(最大7桁)
     * @param countryCode          「必須」 国コード
     * @return 外国株式信用建余力情報
     * @throws Exception 異常
     * 
     */
    public GetMarginPowerSummaryResp getMarginPowerSummary(String butenCode, String accountNumber, String countryCode)
            throws Exception;
    
    /**
     * 余力サービス - 外貨信用保証金残高スケジュール取得API
    
     * @param butenCode        「必須」 部店コード(3桁)
     * @param accountNumber    「必須」 口座番号(最大7桁)
     * @param currencyCode     「非必須」 通貨コード
     * @param days             「非必須」 取得日数
     * @return 外貨信用保証金残高スケジュール
     * @throws Exception 異常
     */
    public ListForeignMarginScheduleCashBalancesResp listForeignMarginScheduleCashBalances(String butenCode,
            String accountNumber, String currencyCode, Integer days) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建余力詳細取得API
    
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNumber 「必須」 口座番号(最大7桁)
     * @param countryCode   「必須」 国コード
     * @return 外国株式信用建余力詳細
     * @throws Exception 異常
     */
    public GetMarginPowerDetailResp getMarginPowerDetail(String butenCode, String accountNumber, String countryCode)
            throws Exception;
    
    /**
     * 余力サービス - リアルタイム委託保証金率取得API
    
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNumber 「必須」 口座番号(最大7桁)
     * @param countryCode   「必須」 国コード
     * @return リアルタイム委託保証金率情報
     * @throws Exception 異常
     */
    public ListDepositRateBasisResp listDepositRateBasis(String butenCode, String accountNumber, String countryCode)
            throws Exception;
    
    /**
     * 外貨金銭残高スケジュール取得
     * 
     * @param butenCode         「必須」 部店コード(3桁)
     * @param accountNo         「必須」 口座番号(最大7桁)
     * @param currencyCode      「非必須」 通貨コード
     *                          <li>Yen.：JPY</li>
     *                          <li>US Dollar.：USD</li>
     * @param accountKind       「非必須」 口座分類
     *                          <li>総合：GENERAL</li>
     *                          <li>ジュニアNISA：JR_NISA</li>
     * @param days              「非必須」取得日数
     * @return 金銭残高取得 ListForeignScheduleCashBalancesResp
     *         <p>
     *         当日非営業日の場合はnullに戻ります。
     *         </p>
     * @throws Exception 異常
     */
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(String butenCode, String accountNo,
            String currencyCode, String accountKind, Integer days) throws Exception;
    
    /**
     * 余力サービス - 円貨金銭残高スケジュール取得API
     *
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNumber 「必須」 口座番号(最大7桁)
     * @param accountKind   「非必須」 口座分類
     *                      <li>総合：{@code GENERAL} </li>
     *                      <li>ジュニアNISA：{@code JR_NISA}</li>
     * @param days          「必須」取得日数
     * @return 円貨金銭残高スケジュール情報
     * @throws Exception 異常
     */
    public ListScheduleCashBalancesResp listScheduleCashBalances(String butenCode, String accountNumber,
            String accountKind, Integer days) throws Exception;
    
    /**
     * 信用口座開設判定
     * 
     * @param butenCode         「必須」 部店コード(3桁)
     * @param accountNo         「必須」 口座番号(最大7桁)
     * @param marginAccountType 「非必須」 信用口座開設判定
     *                          <li>国内信用口座：DOMESTIC</li>
     *                          <li>外国信用口座：FOREIGN</li>
     * @return 信用口座開設判定結果 CheckMarginAccountOpenResp
     * @throws Exception 異常
     */
    public CheckMarginAccountOpenResp checkMarginAccountOpen(String butenCode, String accountNo,
            String marginAccountType) throws Exception;
    
    /**
     * 入出金・入出庫サービス - 代用有価証券振替確認API
     *
     * @param req 代用有価証券振替確認APIリクエスト
     * @return 代用有価証券振替情報
     * @throws Exception 異常
     */
    public ConfirmCollateralSecuritiesTransferResp confirmCollateralSecuritiesTransfer(
            ConfirmCollateralSecuritiesTransferReq req) throws Exception;
    
    /**
     * 入出金・入出庫サービス - 代用有価証券振替登録API
     *
     * @param req 代用有価証券振替登録APIリクエスト
     * @return 代用有価証券振替情報
     * @throws Exception 異常
     */
    public CreateCollateralSecuritiesTransferResp createCollateralSecuritiesTransfer(
            CreateCollateralSecuritiesTransferReq req) throws Exception;
    
    
    /**
     * 
     * @param butenCode 部店コード(3桁)
     * @param accountNumber 口座番号(最大7桁)
     * @param accountKind 口座分類
     * @param days 取得日数
     * @param currencyCodes 通貨コードリスト
     * @return
     * @throws Exception
     */
    public ListMultiGetCashDepositsResp multiGetCashDeposits(String butenCode, String accountNumber, 
            String accountKind, Integer days, List<String> currencyCodes)  throws Exception;
}
