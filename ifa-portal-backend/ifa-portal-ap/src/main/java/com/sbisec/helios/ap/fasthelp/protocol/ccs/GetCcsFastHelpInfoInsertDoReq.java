package com.sbisec.helios.ap.fasthelp.protocol.ccs;

import com.sbisec.helios.ap.fasthelp.protocol.FastCcsBaseRequest;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoIn;
import com.sbisec.helios.ap.fasthelp.utils.FastCcsApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCcsFastHelpInfoInsertDoReq implements FastCcsBaseRequest{

    // headerとparameterインスタンス化
    private Header header = new Header();

    private CcsFastHelpInfoInsertDoIn parameter = new CcsFastHelpInfoInsertDoIn();

    @Data
    @NoArgsConstructor
    public class Header {
      
    }

    public String getFasthelpApiUrl() {
      return FastCcsApiUtil.getCcs_fastHelp_info_insert_do();
    }

}
