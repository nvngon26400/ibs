package com.sbisec.helios.ap.ccsApi.service;

import com.sbisec.helios.ap.ccsApi.protocol.ShowSubContractHistoryIFAReq;
import com.sbisec.helios.ap.ccsApi.protocol.ShowSubContractHistoryIFARes;
import com.sbisec.helios.ap.ccsApi.protocol.ShowDealingDetailIFAReq;
import com.sbisec.helios.ap.ccsApi.protocol.ShowDealingDetailIFARes;

/**
 * CCS API サービスインターフェース
 * 各種CCS APIの呼び出しを定義する。
 */
public interface CcsApiService {

    /**
     * 接触履歴参照API
     * POST:/CCS/cs001/ShowSubContractHistoryIFA.do
     */
    public ShowSubContractHistoryIFARes calShowSubContractHistoryIFA(ShowSubContractHistoryIFAReq x_req) throws Exception;

    /**
     * 接触履歴詳細参照API
     * POST:/CCS/cs001/ShowDealingDetailIFA.do
     */
    public ShowDealingDetailIFARes calShowDealingDetailIFA(ShowDealingDetailIFAReq x_req) throws Exception;

}
