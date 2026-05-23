package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenReq;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileReq;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileResp;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;

public interface CometAccountService extends com.sbibits.earth.service.Service {
    
    /**
     * オペレータ権限一覧取得API
     * 
     */
    public ListOperatorScopesRes listOperatorScopes(ListOperatorScopesReq req) throws Exception;
    
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
     * @throws Exception
     */
    public GetJrNisaAccountStatusRes getJrNisaAccountStatus(GetJrNisaAccountStatusReq req) throws Exception;
    
    /**
     * 口座情報サービス - アカウントプロファイル取得API.
     * 
     * @param getAccountProfileReq Httpリクエスト
     * @return GetAccountProfileResp アカウントプロファイル取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetAccountProfileReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetAccountProfileResp
     */
    public GetAccountProfileResp getAccountProfile(GetAccountProfileReq getAccountProfileReq) throws Exception;
    
    /**
     * 口座情報サービス - 信用口座開設判定API.
     * 
     * @param CheckMarginAccountOpenReq Httpリクエスト
     * @return CheckMarginAccountOpenResp 信用口座開設判定状況
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.CheckMarginAccountOpenReq
     * @see com.sbibits.horus.ap.athena.protocol.account.CheckMarginAccountOpenResp
     */
    public CheckMarginAccountOpenResp checkMarginAccountOpen(CheckMarginAccountOpenReq request) throws Exception;
}
