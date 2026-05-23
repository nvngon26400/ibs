package com.sbisec.helios.ap.athena.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.AccountKind;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMultiGetCashDepositsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.MultiGetCashDepositsReq;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometCashBalanceService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CometCashBalanceServiceImpl extends AbstractBaseService implements CometCashBalanceService {

    private static final Logger LOG = LoggerFactory.getLogger(CometCashBalanceServiceImpl.class);

    @Override
    public ListScheduleCashBalancesResp listScheduleCashBalances(ListScheduleCashBalancesReq request)
            throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCashBalanceServiceImpl.listScheduleCashBalances : {}", hashCode());
        }

        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // Token not empty check
            String token = request.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }

            // AccountKind exist check
            String accountKind = request.getParameter().getAccountKind();
            if (!StringUtil.isNullOrEmpty(accountKind) && null == AccountKind.getById(accountKind)) {
                warnMsg = "AccountKind " + accountKind + " is not exists!";
                break;
            }
            // Days not empty check
            Integer days = request.getParameter().getDays();
            if (null == days) {
                warnMsg = "Days is null!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }

        String url = this.getUrl(CometApiUtil.getAcc_schedule_cash_balances());

        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListScheduleCashBalancesResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListScheduleCashBalancesResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返します
        return resp;
    }

    @Override
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(
            ListForeignScheduleCashBalancesReq request) throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCashBalanceServiceImpl.listForeignScheduleCashBalances : {}", hashCode());
        }

        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // Token not empty check
            String token = request.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // CurrencyCode exist check
            String currencyCode = request.getParameter().getCurrencyCode();
            if (!StringUtil.isNullOrEmpty(currencyCode) && null == CurrencyCode.getById(currencyCode)) {
                warnMsg = "CurrencyCode " + currencyCode + " is not exists!";
                break;
            }
            // AccountKind exist check
            String accountKind = request.getParameter().getAccountKind();
            if (!StringUtil.isNullOrEmpty(accountKind) && null == AccountKind.getById(accountKind)) {
                warnMsg = "AccountKind " + accountKind + " is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }

        String url = this.getUrl(CometApiUtil.getAcc_foreign_schedule_cash_balances());

        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListForeignScheduleCashBalancesResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListForeignScheduleCashBalancesResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返します
        return resp;
    }

    @Override
    public ListMultiGetCashDepositsResp multiGetCashDeposits(MultiGetCashDepositsReq request)  throws Exception {
        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCashBalanceServiceImpl.listForeignScheduleCashBalances : {}", hashCode());
        }

        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // Token not empty check
            String token = request.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // CurrencyCode exist check
            
            List<String> currencyCodes = request.getParameter().getCurrencyCodes();
            if (CollectionUtils.isEmpty(currencyCodes)) {
                warnMsg = "CurrencyCode is null or empty!";
                break;
            }
            List<String> errorCodes = currencyCodes.stream().filter(currencyCode -> CurrencyCode.getById(currencyCode) == null).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(errorCodes)) {
                warnMsg = "CurrencyCode " + errorCodes.toString() + " is not exists!";
                break;
            }
            // AccountKind exist check
            String accountKind = request.getParameter().getAccountKind();
            if (StringUtil.isNullOrEmpty(accountKind)) {
                warnMsg = "AccountKind is null or empty!";
                break;
            }
            if (null == AccountKind.getById(accountKind)) {
                warnMsg = "AccountKind " + accountKind + " is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.multi_get_cash_deposits());

        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListMultiGetCashDepositsResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListMultiGetCashDepositsResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返します
        return resp;
    }

}
