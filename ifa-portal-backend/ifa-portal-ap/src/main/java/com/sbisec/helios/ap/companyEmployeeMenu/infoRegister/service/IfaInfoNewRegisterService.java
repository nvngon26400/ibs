package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008aRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
public interface IfaInfoNewRegisterService extends Service {
    
    /**
     * アクションID：A008b
     * アクション名：登録
     * Dto リクエスト：IfaInfoNewRegisterA008bRequestDto
     * Dto レスポンス：IfaInfoNewRegisterA008bResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterA008bResponseDto> registerA008b(IfaInfoNewRegisterA008bRequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A008a
     * アクション名：登録
     * Dto リクエスト：IfaInfoNewRegisterA008bRequestDto
     * Dto レスポンス：String
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<String> registerA008a(IfaInfoNewRegisterA008aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoNewRegisterA001RequestDto
     * Dto レスポンス：IfaInfoNewRegisterA001ResponseDto
     * model リクエスト：IfaInfoNewRegisterA001RequestModel
     * model レスポンス：IfaInfoNewRegisterA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoNewRegisterA001ResponseDto> initializeA001(IfaInfoNewRegisterA001RequestDto dtoReq)
            throws Exception;
    
}
