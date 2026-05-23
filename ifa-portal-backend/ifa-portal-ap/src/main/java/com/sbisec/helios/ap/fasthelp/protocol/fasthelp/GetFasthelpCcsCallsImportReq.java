package com.sbisec.helios.ap.fasthelp.protocol.fasthelp;

import com.sbisec.helios.ap.fasthelp.protocol.FastCcsBaseRequest;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportIn;
import com.sbisec.helios.ap.fasthelp.utils.FastCcsApiUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFasthelpCcsCallsImportReq implements FastCcsBaseRequest {

    // headerとparameterインスタンス化
    private Header header = new Header();

    private FasthelpCcsCallsImportIn parameter = new FasthelpCcsCallsImportIn();

    @Data
    @NoArgsConstructor
    public class Header {
    }

    public String getFasthelpApiUrl() {
        return FastCcsApiUtil.getFasthelp_ccs_calls_import();
    }

}
