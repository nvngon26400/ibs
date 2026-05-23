package com.sbisec.helios.ap.athena.ifa.impl;

import java.util.List;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenReq;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileReq;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileResp;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailResp;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceReq;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisReq;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq.Parameter;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMultiGetCashDepositsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.MultiGetCashDepositsReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp;
import com.sbisec.helios.ap.athena.service.CometAccountService;
import com.sbisec.helios.ap.athena.service.CometCashBalanceService;
import com.sbisec.helios.ap.athena.service.CometCollateralSecuritiesService;
import com.sbisec.helios.ap.athena.service.CometCollateralSecuritiesTransferService;
import com.sbisec.helios.ap.athena.service.CometForeignStockMarginBalanceService;
import com.sbisec.helios.ap.athena.service.CometGetMarginPowerDetail;
import com.sbisec.helios.ap.athena.service.CometListForeignMarginScheduleCashBalances;
import com.sbisec.helios.ap.athena.service.CometMarginCashBalanceService;
import com.sbisec.helios.ap.athena.service.CometMarginPositionService;
import com.sbisec.helios.ap.athena.service.CometSecuritiesBalanceService;
import com.sbisec.helios.ap.athena.utils.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForeignAccountServiceImpl implements ForeignAccountService {
    
    @Autowired
    private CometCollateralSecuritiesTransferService cometCollateralSecuritiesTransferService;
    
    @Autowired
    private CometSecuritiesBalanceService cometSecuritiesBalanceService;
    
    @Autowired
    private CometForeignStockMarginBalanceService cometForeignStockMarginBalanceService;
    
    @Autowired
    private CometAccountService cometAccountService;
    
    @Autowired
    private CometCashBalanceService cometCashBalanceService;
    
    @Autowired
    private CometMarginPositionService cometMarginPositionService;
    
    @Autowired
    private CometCollateralSecuritiesService cometCollateralSecuritiesService;
    
    @Autowired
    private CometListForeignMarginScheduleCashBalances cometListForeignMarginScheduleCashBalances;
    
    @Autowired
    private CometGetMarginPowerDetail cometGetMarginPowerDetail;
    
    @Autowired
    private CometMarginCashBalanceService cometMarginCashBalanceService;
    
    @Override
    public ConfirmMarginTransferResp confirmMarginTransfer(String butenCode, String accountNo, String currencyCode,
            String cashTransferType, String amount) throws Exception {
        
        ConfirmMarginTransferReq request = new ConfirmMarginTransferReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo)); // token
        request.getParameter().setCurrencyCode(currencyCode); // 通貨コード
        request.getParameter().setCashTransferType(cashTransferType); // 振替区分
        request.getParameter().setAmount(amount); // 振替金額
        
        return cometCollateralSecuritiesTransferService.confirmMarginTransfer(request);
    }
    
    @Override
    public CreateMarginTransferResp createMarginTransfer(String butenCode, String accountNo, String requestId,
            String currencyCode, String cashTransferType, String amount) throws Exception {
        
        CreateMarginTransferReq request = new CreateMarginTransferReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo)); // token
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        request.getParameter().setCurrencyCode(currencyCode); // 通貨コード
        request.getParameter().setCashTransferType(cashTransferType); // 振替区分
        request.getParameter().setAmount(amount); // 振替金額
        
        return cometCollateralSecuritiesTransferService.createMarginTransfer(request);
    }
    
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
    @Override
    public GetSecuritiesBalanceResp getSecuritiesBalance(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode,
            String securitiesCode) throws Exception {
        
        GetSecuritiesBalanceReq getSecuritiesBalanceReq = new GetSecuritiesBalanceReq();
        getSecuritiesBalanceReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        getSecuritiesBalanceReq.getHeader()
                .setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        getSecuritiesBalanceReq.getParameter().setProductCode(productCode);
        getSecuritiesBalanceReq.getParameter().setCountryCode(countryCode);
        getSecuritiesBalanceReq.getParameter().setCurrencyCode(currencyCode);
        getSecuritiesBalanceReq.getParameter().setSpecificAccountCode(specificAccountCode);
        getSecuritiesBalanceReq.getParameter().setSecuritiesCode(securitiesCode);
        
        return cometSecuritiesBalanceService.getSecuritiesBalance(getSecuritiesBalanceReq);
    }
    
    @Override
    public ListSecuritiesBalancesResp listSecuritiesBalances(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode, Page page)
            throws Exception {
        
        ListSecuritiesBalancesReq listSecuritiesBalancesReq = new ListSecuritiesBalancesReq();
        listSecuritiesBalancesReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        listSecuritiesBalancesReq.getHeader()
                .setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        listSecuritiesBalancesReq.getParameter().setProductCode(productCode);
        listSecuritiesBalancesReq.getParameter().setCountryCode(countryCode);
        listSecuritiesBalancesReq.getParameter().setCurrencyCode(currencyCode);
        listSecuritiesBalancesReq.getParameter().setSpecificAccountCode(specificAccountCode);
        listSecuritiesBalancesReq.getParameter().setPage(page);
        
        return cometSecuritiesBalanceService.listSecuritiesBalances(listSecuritiesBalancesReq);
    }
    
    @Override
    public ListSecuritiesBalancesResp listSecuritiesBalancesMmf(String butenCode, String accountNo, String ticket,
            String productCode, String countryCode, String currencyCode, String specificAccountCode, Page page)
            throws Exception {
        
        ListSecuritiesBalancesReq listSecuritiesBalancesReq = new ListSecuritiesBalancesReq();
        listSecuritiesBalancesReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        listSecuritiesBalancesReq.getHeader()
                .setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        listSecuritiesBalancesReq.getParameter().setProductCode(productCode);
        listSecuritiesBalancesReq.getParameter().setCountryCode(countryCode);
        listSecuritiesBalancesReq.getParameter().setCurrencyCode(currencyCode);
        listSecuritiesBalancesReq.getParameter().setSpecificAccountCode(specificAccountCode);
        listSecuritiesBalancesReq.getParameter().setPage(page);
        
        return cometSecuritiesBalanceService.listSecuritiesBalances(listSecuritiesBalancesReq);
    }
    
    @Override
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(String butenCode, String accountNo,
        String currencyCode, String accountKind, Integer days) throws Exception {

    ListForeignScheduleCashBalancesReq request = new ListForeignScheduleCashBalancesReq();
    request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));// token
    request.getParameter().setCurrencyCode(currencyCode);// 通貨コード - ドル
    request.getParameter().setAccountKind(accountKind);// 口座分類 - 総合
    request.getParameter().setDays(days);// 取得日数

    return cometCashBalanceService.listForeignScheduleCashBalances(request);
}
    
    @Override
    public GetMarginPowerHeadlineResp getMarginPowerHeadline(String butenCode, String accountNumber, String countryCode)
            throws Exception {
        
        GetMarginPowerHeadlineReq request = new GetMarginPowerHeadlineReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setCountryCode(countryCode);
        
        return cometForeignStockMarginBalanceService.getMarginPowerHeadline(request);
    }
    
    public ListOperatorScopesRes listOperatorScopes(String butenCode, String accountNumber, String operatorId)
            throws Exception {
        
        ListOperatorScopesReq request = new ListOperatorScopesReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setOperatorId(operatorId);
        
        return cometAccountService.listOperatorScopes(request);
    }
    
    public MultiGetPossibleWithdrawalsRes multiGetPossibleWithdrawals(MultiGetPossibleWithdrawalsReq req)
            throws Exception {
        
        return cometAccountService.multiGetPossibleWithdrawals(req);
    }
    
    public GetJrNisaAccountStatusRes getJrNisaAccountStatus(GetJrNisaAccountStatusReq req) throws Exception {
        
        return cometAccountService.getJrNisaAccountStatus(req);
    }
    
    public GetAccountProfileResp getAccountProfile(String butenCode, String accountNo, String profileName)
            throws Exception {
        
        GetAccountProfileReq getAccountProfileReq = new GetAccountProfileReq();
        getAccountProfileReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        getAccountProfileReq.getParameter().setProfileName(profileName);
        
        return cometAccountService.getAccountProfile(getAccountProfileReq);
    }
    
    @Override
    public GetMarginPowerReferenceResp getMarginPowerReference(String butenCode, String accountNumber,
            String countryCode) throws Exception {
        
        GetMarginPowerReferenceReq getMarginPowerReferenceReq = new GetMarginPowerReferenceReq();
        getMarginPowerReferenceReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        getMarginPowerReferenceReq.getParameter().setCountryCode(countryCode);
        
        return cometForeignStockMarginBalanceService.getMarginPowerReference(getMarginPowerReferenceReq);
    }
    
    @Override
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(ListForeignScheduleCashBalancesReq req)
            throws Exception {
        
        return cometCashBalanceService.listForeignScheduleCashBalances(req);
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummary(GetMarginPositionSummaryReq req) throws Exception {
        
        return cometMarginPositionService.getMarginPositionSummary(req);
    }
    
    @Override
    public ListPossibleCollateralSecuritiesTransfersResp listPossibleCollateralSecuritiesTransfers(
            ListPossibleCollateralSecuritiesTransfersReq req) throws Exception {
        
        return cometCollateralSecuritiesService.listPossibleCollateralSecuritiesTransfers(req);
    }
    
    @Override
    public ListMarginPositionsResp listMarginPositions(String butenCode, String accountNumber, String countryCode,
            String securitiesCode, String buySellCode, String specificAccountCode, String marginCloseLimitType,
            String tradeDate, String frnTradeDate, String frnPositionPrice, String page) throws Exception {
        
        ListMarginPositionsReq request = new ListMarginPositionsReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        Parameter parameter = request.getParameter();
        parameter.setCountryCode(countryCode);
        parameter.setSecuritiesCode(securitiesCode);
        
        return cometMarginPositionService.listMarginPositions(request);
    }
    
    @Override
    public GetMarginPositionResp getMarginPosition(String countryCode, String securitiesCode, String buySellCode,
            String marginCloseLimitType, String specificAccountCode, String tradeDate, String frnTradeDate,
            String frnPositionPrice, String positionPrice, String butenCode, String accountNumber) throws Exception {
        
        GetMarginPositionReq request = new GetMarginPositionReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellCode);
        request.getParameter().setMarginCloseLimitType(marginCloseLimitType);
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        request.getParameter().setTradeDate(tradeDate);
        request.getParameter().setFrnTradeDate(frnTradeDate);
        request.getParameter().setFrnPositionPrice(frnPositionPrice);
        request.getParameter().setPositionPrice(positionPrice);
        
        return cometMarginPositionService.getMarginPosition(request);
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummary(String butenCode, String accountNo, String countryCode,
            String securitiesCode, String buySellCode, String marginCloseLimitType, String specificAccountCode,
            String tradeDate, String frnTradeDate,String ticket) throws Exception {
        
        GetMarginPositionSummaryReq request = new GetMarginPositionSummaryReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getHeader().setTicket(ticket);
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellCode);
        request.getParameter().setMarginCloseLimitType(marginCloseLimitType);
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        request.getParameter().setTradeDate(tradeDate);
        request.getParameter().setFrnTradeDate(frnTradeDate);
        
        return cometMarginPositionService.getMarginPositionSummary(request);
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummaryForRepayOrderConfirm(String butenCode, String accountNo,
            String countryCode, String securitiesCode, String buySellCode, String marginCloseLimitType,
            String specificAccountCode, String tradeDate, String frnTradeDate,String ticket) throws Exception {
        
        GetMarginPositionSummaryReq request = new GetMarginPositionSummaryReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getHeader().setTicket(ticket);
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellCode);
        request.getParameter().setMarginCloseLimitType(marginCloseLimitType);
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        request.getParameter().setTradeDate(tradeDate);
        request.getParameter().setFrnTradeDate(frnTradeDate);
        
        return cometMarginPositionService.getMarginPositionSummaryForRepayOrderConfirm(request);
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummaryForPositionDetails(String butenCode, String accountNo,
            String countryCode, String securitiesCode, String buySellCode, String marginCloseLimitType,
            String specificAccountCode, String tradeDate, String frnTradeDate) throws Exception {
        
        GetMarginPositionSummaryReq request = new GetMarginPositionSummaryReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellCode);
        request.getParameter().setMarginCloseLimitType(marginCloseLimitType);
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        request.getParameter().setTradeDate(tradeDate);
        request.getParameter().setFrnTradeDate(frnTradeDate);
        
        return cometMarginPositionService.getMarginPositionSummaryForPositionDetails(request);
    }
    
    @Override
    public GetMarginPowerSummaryResp getMarginPowerSummary(String butenCode, String accountNumber, String countryCode)
            throws Exception {
        
        GetMarginPowerSummaryReq request = new GetMarginPowerSummaryReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setCountryCode(countryCode);
        
        return cometForeignStockMarginBalanceService.getMarginPowerSummary(request);
    }
    
    @Override
    public ListForeignMarginScheduleCashBalancesResp listForeignMarginScheduleCashBalances(String butenCode,
            String accountNumber, String currencyCode, Integer days) throws Exception {
        
        ListForeignMarginScheduleCashBalancesReq listForeignMarginScheduleCashBalancesReq = new ListForeignMarginScheduleCashBalancesReq();
        listForeignMarginScheduleCashBalancesReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        listForeignMarginScheduleCashBalancesReq.getParameter().setCurrencyCode(currencyCode);
        listForeignMarginScheduleCashBalancesReq.getParameter().setDays(days);
        
        return cometListForeignMarginScheduleCashBalances
                .listForeignMarginScheduleCashBalances(listForeignMarginScheduleCashBalancesReq);
    }
    
    @Override
    public GetMarginPowerDetailResp getMarginPowerDetail(String butenCode, String accountNumber, String countryCode)
            throws Exception {
        
        GetMarginPowerDetailReq getMarginPowerDetailReq = new GetMarginPowerDetailReq();
        getMarginPowerDetailReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        getMarginPowerDetailReq.getParameter().setCountryCode(countryCode);
        
        return cometGetMarginPowerDetail.getMarginPowerDetail(getMarginPowerDetailReq);
    }
    
    @Override
    public ListDepositRateBasisResp listDepositRateBasis(String butenCode, String accountNumber, String countryCode)
            throws Exception {
        
        ListDepositRateBasisReq listDepositRateBasisReq = new ListDepositRateBasisReq();
        listDepositRateBasisReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        listDepositRateBasisReq.getParameter().setCountryCode(countryCode);
        
        return cometMarginCashBalanceService.listDepositRateBasis(listDepositRateBasisReq);
    }    
    
    @Override
    public ListScheduleCashBalancesResp listScheduleCashBalances(String butenCode, String accountNumber,
            String accountKind, Integer days) throws Exception {
        
        ListScheduleCashBalancesReq listScheduleCashBalancesReq = new ListScheduleCashBalancesReq();
        listScheduleCashBalancesReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        listScheduleCashBalancesReq.getParameter().setAccountKind(accountKind);
        listScheduleCashBalancesReq.getParameter().setDays(days);
        
        return cometCashBalanceService.listScheduleCashBalances(listScheduleCashBalancesReq);
    }
    
    
    @Override
    public CheckMarginAccountOpenResp checkMarginAccountOpen(String butenCode, String accountNo,
            String marginAccountType) throws Exception {
        
        CheckMarginAccountOpenReq request = new CheckMarginAccountOpenReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getParameter().setMarginAccountType(marginAccountType);
        
        return cometAccountService.checkMarginAccountOpen(request);
    }
    
    @Override
    public ConfirmCollateralSecuritiesTransferResp confirmCollateralSecuritiesTransfer(
            ConfirmCollateralSecuritiesTransferReq req) 
            throws Exception {
        
        return cometCollateralSecuritiesTransferService.confirmCollateralSecuritiesTransfer(req);
    }
    
    @Override
    public CreateCollateralSecuritiesTransferResp createCollateralSecuritiesTransfer(
            CreateCollateralSecuritiesTransferReq req) throws Exception {
        
        return cometCollateralSecuritiesTransferService.createCollateralSecuritiesTransfer(req);
    }

    @Override
    public ListMultiGetCashDepositsResp multiGetCashDeposits(String butenCode, String accountNumber, 
            String accountKind, Integer days, List<String> currencyCodes) throws Exception {
        MultiGetCashDepositsReq request = new MultiGetCashDepositsReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        request.getParameter().setAccountKind(accountKind);
        request.getParameter().setDays(days);
        request.getParameter().setCurrencyCodes(currencyCodes);
        return cometCashBalanceService.multiGetCashDeposits(request);
    }
}
