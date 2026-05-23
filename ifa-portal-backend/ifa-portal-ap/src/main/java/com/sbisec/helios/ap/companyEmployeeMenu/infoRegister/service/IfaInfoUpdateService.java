package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008aDtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008bDtoRequest;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */
public interface IfaInfoUpdateService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoUpdateA001DtoRequest
     * Dto レスポンス：IfaInfoUpdateA001DtoResponse
     * model リクエスト：IfaInfoUpdateA001RequestModel
     * model レスポンス：IfaInfoUpdateA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateA001DtoResponse> initializeA001(IfaInfoUpdateA001DtoRequest dtoReq)
            throws Exception;
            
    /**
     * アクションID：A002
     * アクション名：リセット
     * Dto リクエスト：IfaInfoUpdateA002DtoRequest
     * Dto レスポンス：IfaInfoUpdateA002DtoResponse
     * model リクエスト：IfaInfoUpdateA002RequestModel
     * model レスポンス：IfaInfoUpdateA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateA002DtoResponse> resetA002(IfaInfoUpdateA002DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A008a
     * アクション名：更新
     *
     * @param dtoReq リクエスト
     * @return SQL008.ファイルディレクトリ {@code DataList<String> }
     * @exception exception システムエラー
     */
    public DataList<String> updateA008a(IfaInfoUpdateA008aDtoRequest dtoReq)
            throws Exception;
    
    /**
    * SQL002.添付ファイル名を取得
    *
    * @param notificationId {@code String }
    * @return 添付ファイル名リスト {@code DataList<String> }
    * @throws Exception システムエラー
    */
    public DataList<String> getAttachFileA008b(String notificationId)
            throws Exception;

    /**
     * アクションID：A008b
     * アクション名：更新
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    public DataList<String> updateA008b(IfaInfoUpdateA008bDtoRequest dtoReq)
            throws Exception;
}
