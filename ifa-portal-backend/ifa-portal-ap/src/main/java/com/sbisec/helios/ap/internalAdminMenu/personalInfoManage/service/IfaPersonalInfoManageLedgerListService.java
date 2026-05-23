package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoResponse;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoResponse;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoResponse;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧
 *
 * @author 大崎 辰弥
 *
    2023/12/29 新規作成
 */
public interface IfaPersonalInfoManageLedgerListService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA001DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA001DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListA001RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListA001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA001DtoResponse> initializeA001(IfaPersonalInfoManageLedgerListA001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：表示
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA003DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA003DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListA003RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListA003ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA003DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA003DtoResponse> displayA003(IfaPersonalInfoManageLedgerListA003DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A005a
     * アクション名：CSV出力
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA005aDtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA005aDtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListA005aRequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListA005aResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA005aDtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse> csvOutputA005a(IfaPersonalInfoManageLedgerListA005aDtoRequest dtoReq, 
                String fwSessionId)
            throws Exception;
            
    /**
     * アクションID：A006
     * アクション名：OK
     * Dto リクエスト：IfaPersonalInfoManageLedgerListA006DtoRequest
     * Dto レスポンス：IfaPersonalInfoManageLedgerListA006DtoResponse
     * model リクエスト：IfaPersonalInfoManageLedgerListA006RequestModel
     * model レスポンス：IfaPersonalInfoManageLedgerListA006ResponseModel

     * @param dtoReq リクエスト
     * @return IfaPersonalInfoManageLedgerListA006DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListA006DtoResponse> okA006(IfaPersonalInfoManageLedgerListA006DtoRequest dtoReq)
            throws Exception;

}
