package com.sbisec.helios.ap.fasthelp.service;

import com.sbisec.helios.ap.fasthelp.protocol.ccs.GetCcsFastHelpInfoInsertDoReq;
import com.sbisec.helios.ap.fasthelp.protocol.ccs.GetCcsFastHelpInfoInsertDoRes;

public interface Ccs2FastHelpInfoInsertService {

    /**
     * CCS問合せ履歴登録、更新
     * POST:/CCS/api/FastHelpInfoInsert.do
     */
    public GetCcsFastHelpInfoInsertDoRes getCcsFastHelpInfoInsertDo(GetCcsFastHelpInfoInsertDoReq req) throws Exception;

}
