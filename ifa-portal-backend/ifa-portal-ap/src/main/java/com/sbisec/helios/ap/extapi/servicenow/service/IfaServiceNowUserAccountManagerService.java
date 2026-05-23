package com.sbisec.helios.ap.extapi.servicenow.service;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA005RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA005ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA007RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA007ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA008RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA008ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA009RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA009ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * IfaServiceNowUserAccountManagerService サービス
 *
 * @author SCSK
 */
public interface IfaServiceNowUserAccountManagerService extends Service {
    
    /**
     * ログインID登録
     */
    public IfaServiceNowUserAccountManagerA005ResponseDto invokeA005(
            IfaServiceNowUserAccountManagerA005RequestDto dtoReq) throws Exception;
    
    /**
     * 申請情報項目一覧を取得
     */
    public IfaServiceNowUserAccountManagerA006ResponseDto invokeA006(
            IfaServiceNowUserAccountManagerA006RequestDto dtoReq) throws Exception;
    
    /**
     * ログインID更新
     */
    public IfaServiceNowUserAccountManagerA007ResponseDto invokeA007(
            IfaServiceNowUserAccountManagerA007RequestDto dtoReq) throws Exception;
    
    /**
     * ユーザ＆利用できるメニューを削除
     */
    public IfaServiceNowUserAccountManagerA008ResponseDto invokeA008(
            IfaServiceNowUserAccountManagerA008RequestDto dtoReq) throws Exception;
    
    /**
     * 権限一覧取得
     */
    public IfaServiceNowUserAccountManagerA009ResponseDto invokeA009(
            IfaServiceNowUserAccountManagerA009RequestDto dtoReq) throws Exception;

    /**
     * ログインID検索
     */
    public IfaServiceNowUserAccountManagerA014ResponseDto invokeA014(
            IfaServiceNowUserAccountManagerA014RequestDto dtoReq) throws Exception;
}
