package com.sbisec.helios.ap.extapi.servicenow.service;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA010ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * IfaServiceNowMenuAndAclManagerService サービス
 *
 * @autor SCSK
 */
public interface IfaServiceNowMenuAndAclManagerService extends Service {
    
    /**
     * メニュー取得
     */
    public IfaServiceNowMenuAndAclManagerA010ResponseDto invokeA010(IfaServiceNowMenuAndAclManagerA010RequestDto dtoReq)
            throws Exception;
    
    /**
     * ユーザ利用できるメニューを削除
     */
    public IfaServiceNowMenuAndAclManagerA011ResponseDto invokeA011(IfaServiceNowMenuAndAclManagerA011RequestDto dtoReq)
            throws Exception;
    
    /**
     * ユーザ利用できるメニューを登録
     */
    public IfaServiceNowMenuAndAclManagerA012ResponseDto invokeA012(IfaServiceNowMenuAndAclManagerA012RequestDto dtoReq)
            throws Exception;
    
    /**
     * ユーザ利用可能メニュー一覧取得
     */
    public IfaServiceNowMenuAndAclManagerA013ResponseDto invokeA013(IfaServiceNowMenuAndAclManagerA013RequestDto dtoReq)
            throws Exception;
}
