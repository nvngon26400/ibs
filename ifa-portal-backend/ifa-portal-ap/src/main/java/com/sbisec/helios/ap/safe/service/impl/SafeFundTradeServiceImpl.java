package com.sbisec.helios.ap.safe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.safe.SafeOkHttpResponse;
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
import com.sbisec.helios.ap.safe.service.SafeAbstractBaseService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingInputConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingInputReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingReleaseReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiOut;

@Service
public class SafeFundTradeServiceImpl extends SafeAbstractBaseService implements SafeFundTradeService {

    private static final Logger LOG = LoggerFactory.getLogger(SafeFundTradeServiceImpl.class);

    private static final String BRANCH_OFFICE = "BRANCH_OFFICE";
    
    @Override
    public GetFundReserveSettingDataSummaryRes getSettinSummary(GetFundReserveSettingDataSummaryReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.getSettinSummary : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundReserveSettingDataSummaryRes resp = new GetFundReserveSettingDataSummaryRes();
        try {
            // convert the string into entity bean and return it.
            ReserveSettingSummaryApiOut safeApiOut = httpResp.getResponseData(ReserveSettingSummaryApiOut.class);
            resp.setReserveSettingSummaryApiOut(safeApiOut);

        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public GetFundFundDocReadHistoryBulkRes getBulkFundDocReadHistory(GetFundFundDocReadHistoryBulkReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.getBulkFundDocReadHistory : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundFundDocReadHistoryBulkRes resp = new GetFundFundDocReadHistoryBulkRes();
        try {
            // convert the string into entity bean and return it.
            FundDocReadHistoryBulkApiOut safeApiOut = httpResp.getResponseData(FundDocReadHistoryBulkApiOut.class);
            resp.setFundDocReadHistoryBulkApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public GetFundReserveSettingDataListRes getSettinDataList(GetFundReserveSettingDataListReq req) throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.getSettinDataList : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundReserveSettingDataListRes resp = new GetFundReserveSettingDataListRes();
        try {
            // convert the string into entity bean and return it.
            ReserveSettingDataListApiOut safeApiOut = httpResp.getResponseData(ReserveSettingDataListApiOut.class);
            resp.setReserveSettingDataListApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public GetFundTradeReserveSettingGetTradeTypeRes getSettinDataList(GetFundTradeReserveSettingGetTradeTypeReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.getSettinDataList : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundTradeReserveSettingGetTradeTypeRes resp = new GetFundTradeReserveSettingGetTradeTypeRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveCanTradeTypeApiOut safeApiOut = httpResp.getResponseData(FundReserveCanTradeTypeApiOut.class);
            resp.setFundReserveCanTradeTypeApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingInputConfirmRes reserveCreateConfirm(PostFundReserveSettingInputConfirmReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveCreateConfirm : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingInputConfirmRes resp = new PostFundReserveSettingInputConfirmRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingInputConfirmApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingInputConfirmApiOut.class);
            resp.setFundReserveSettingInputConfirmApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingInputReceptRes reserveCreateRecept(PostFundReserveSettingInputReceptReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveCreateRecept : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        req.getParameter().setRouteType(BRANCH_OFFICE);

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingInputReceptRes resp = new PostFundReserveSettingInputReceptRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingInputReceptApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingInputReceptApiOut.class);
            resp.setFundReserveSettingInputReceptApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingChangeConfirmRes reserveChangeConfirm(PostFundReserveSettingChangeConfirmReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveChangeConfirm : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingChangeConfirmRes resp = new PostFundReserveSettingChangeConfirmRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingChangeConfirmApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingChangeConfirmApiOut.class);
            resp.setFundReserveSettingChangeConfirmApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingChangeReceptRes reserveChangeRecept(PostFundReserveSettingChangeReceptReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveChangeRecept : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        req.getParameter().setRouteType(BRANCH_OFFICE);

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingChangeReceptRes resp = new PostFundReserveSettingChangeReceptRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingChangeReceptApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingChangeReceptApiOut.class);
            resp.setFundReserveSettingChangeReceptApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public GetFundReserveGetReserveSettingForBulkUpdateRes getReserveSettingForBulkUpdate(
            GetFundReserveGetReserveSettingForBulkUpdateReq req) throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.getReserveSettingForBulkUpdate : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundReserveGetReserveSettingForBulkUpdateRes resp = new GetFundReserveGetReserveSettingForBulkUpdateRes();
        try {
            // convert the string into entity bean and return it.
            ReserveSettingDataListApiOut safeApiOut = httpResp.getResponseData(ReserveSettingDataListApiOut.class);
            resp.setReserveSettingDataListApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingReleaseReceptRes reserveReleaseRecept(PostFundReserveSettingReleaseReceptReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveReleaseRecept : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        req.getParameter().setRouteType(BRANCH_OFFICE);

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingReleaseReceptRes resp = new PostFundReserveSettingReleaseReceptRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingReleaseReceptApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingReleaseReceptApiOut.class);
            resp.setFundReserveSettingReleaseReceptApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingBulkChangeConfirmRes reserveBulkChangeConfirm(PostFundReserveSettingBulkChangeConfirmReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveBulkChangeConfirm : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingBulkChangeConfirmRes resp = new PostFundReserveSettingBulkChangeConfirmRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingBulkChangeConfirmApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingBulkChangeConfirmApiOut.class);
            resp.setFundReserveSettingBulkChangeConfirmApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public PostFundReserveSettingBulkChangeReceptRes reserveBulkChangeRecept(PostFundReserveSettingBulkChangeReceptReq req)
            throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundTradeServiceImpl.reserveBulkChangeRecept : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        req.getParameter().setRouteType(BRANCH_OFFICE);

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        PostFundReserveSettingBulkChangeReceptRes resp = new PostFundReserveSettingBulkChangeReceptRes();
        try {
            // convert the string into entity bean and return it.
            FundReserveSettingBulkChangeReceptApiOut safeApiOut = httpResp
                    .getResponseData(FundReserveSettingBulkChangeReceptApiOut.class);
            resp.setFundReserveSettingBulkChangeReceptApiOut(safeApiOut);
        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

}
