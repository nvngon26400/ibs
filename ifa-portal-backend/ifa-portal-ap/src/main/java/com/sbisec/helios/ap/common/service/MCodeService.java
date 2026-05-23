package com.sbisec.helios.ap.common.service;

import java.util.List;

import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.service.Service;

/**
 * コードマスタサービス
 *
 * @author SCSK
 *
 */
public interface MCodeService extends Service {
    
    public List<MCode> getMCodeList(String codeType) throws Exception;
    
    public List<MCode> getMCodeList(String codeType, String code1) throws Exception;
    
    public List<MCode> getMCodeList(String codeType, String code1, String code2) throws Exception;
}
