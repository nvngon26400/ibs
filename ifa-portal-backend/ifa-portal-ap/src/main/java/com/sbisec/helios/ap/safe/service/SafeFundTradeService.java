package com.sbisec.helios.ap.safe.service;

import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeConfirmRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeReceptRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeConfirmRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeReceptRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputConfirmRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputReceptRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingReleaseReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingReleaseReceptRes;

public interface SafeFundTradeService {

    /**
     * 積立一覧API 積立設定サマリーの取得
     * POST:/reserve/setting_data_summary
     * 
     */
    public GetFundReserveSettingDataSummaryRes getSettinSummary(GetFundReserveSettingDataSummaryReq req) throws Exception;

    /**
     * 投信閲覧履歴一括照会API 目論見書電子書面の一括取得
     * POST:/safe/fundTrade/fund/fund_doc_read_history/bulk
     * 
     */
    public GetFundFundDocReadHistoryBulkRes getBulkFundDocReadHistory(GetFundFundDocReadHistoryBulkReq req) throws Exception;

    /**
     * 積立一覧API 積立設定一覧の取得
     * POST:/safe/fundTrade/fund/trade/reserve/setting_data_list
     * 
     */
    public GetFundReserveSettingDataListRes getSettinDataList(GetFundReserveSettingDataListReq req) throws Exception;

    /**
     * 積立買付設定入力API 設定可能な預かり区分の取得
     * POST:/safe/fundTrade/fund/reserve/setting/get_trade_types
     * 
     */
    public GetFundTradeReserveSettingGetTradeTypeRes getSettinDataList(GetFundTradeReserveSettingGetTradeTypeReq req) throws Exception;

    /**
     * 積立買付設定入力API 積立買付設定の新規入力確認
     * POST:/safe/fundTrade/fund/reserve/setting/input/confirm
     * 
     */
    public PostFundReserveSettingInputConfirmRes reserveCreateConfirm(PostFundReserveSettingInputConfirmReq req) throws Exception;

    /**
     * 積立買付設定入力API 積立買付の新規設定受付確認
     * POST:/safe/fundTrade/fund/reserve/setting/input/recept
     * 
     */
    public PostFundReserveSettingInputReceptRes reserveCreateRecept(PostFundReserveSettingInputReceptReq req) throws Exception;

    /**
     * 積立買付設定変更API 積立買付設定変更による確認
     * POST:/safe/fundTrade/fund/reserve/setting/change/confirm
     * 
     */
    public PostFundReserveSettingChangeConfirmRes reserveChangeConfirm(PostFundReserveSettingChangeConfirmReq req) throws Exception;

    /**
     * 積立買付設定変更API 積立買付設定の変更
     * POST:/safe/fundTrade/fund/reserve/setting/change/recept
     * 
     */
    public PostFundReserveSettingChangeReceptRes reserveChangeRecept(PostFundReserveSettingChangeReceptReq req) throws Exception;

    /**
     * 積立一覧API 積立設定一覧の取得(一括変更用)
     * POST:/safe/fundTrade/fund/reserve/get-reserve-setting-for-bulk-update
     * 
     */
    public GetFundReserveGetReserveSettingForBulkUpdateRes getReserveSettingForBulkUpdate(GetFundReserveGetReserveSettingForBulkUpdateReq req)
            throws Exception;

    /**
     * 積立設定解除API 積立設定解除の受付
     * POST:/safe/fundTrade/fund/reserve/setting/release/recept
     * 
     */
    public PostFundReserveSettingReleaseReceptRes reserveReleaseRecept(PostFundReserveSettingReleaseReceptReq req) throws Exception;

    /**
     * 積立設定一括変更確認API 積立買付設定一括変更による確認
     * POST:/safe/fundTrade/fund/reserve/setting/bulk/change/confirm
     * 
     */
    public PostFundReserveSettingBulkChangeConfirmRes reserveBulkChangeConfirm(PostFundReserveSettingBulkChangeConfirmReq req) throws Exception;

    /**
     * 積立設定一括変更受付API 積立買付設定の一括変更
     * POST:/safe/fundTrade/fund/reserve/setting/bulk/change/recept
     * 
     */
    public PostFundReserveSettingBulkChangeReceptRes reserveBulkChangeRecept(PostFundReserveSettingBulkChangeReceptReq req) throws Exception;

}
