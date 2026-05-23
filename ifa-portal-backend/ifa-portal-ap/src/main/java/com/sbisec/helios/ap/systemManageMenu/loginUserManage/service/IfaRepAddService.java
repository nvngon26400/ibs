package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0601_01-06_1
 * 画面名：担当者追加
 *
 * @author 島崎 聡士 2023/09/08 新規作成
 */
public interface IfaRepAddService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaRepAddA001DtoRequest
     * Dto レスポンス：IfaRepAddA001DtoResponse
     * model リクエスト：IfaRepAddA001RequestModel
     * model レスポンス：IfaRepAddA001ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA001DtoResponse> initialDisplayA001(IfaRepAddA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaRepAddA002DtoRequest
     * Dto レスポンス：IfaRepAddA002DtoResponse
     * model リクエスト：IfaRepAddA002RequestModel
     * model レスポンス：IfaRepAddA002ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA002DtoResponse> brokerNameUpdateA002(IfaRepAddA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaRepAddA003DtoRequest
     * Dto レスポンス：IfaRepAddA003DtoResponse
     * model リクエスト：IfaRepAddA003RequestModel
     * model レスポンス：IfaRepAddA003ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA003DtoResponse> brokerBranchNameUpdateA003(IfaRepAddA003DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：追加
     * Dto リクエスト：IfaRepAddA005DtoRequest
     * Dto レスポンス：IfaRepAddA005DtoResponse
     * model リクエスト：IfaRepAddA005RequestModel
     * model レスポンス：IfaRepAddA005ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA005DtoResponse> addA005(IfaRepAddA005DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：追加（OK）
     * Dto リクエスト：IfaRepAddA006DtoRequest
     * Dto レスポンス：IfaRepAddA006DtoResponse
     * model リクエスト：IfaRepAddA006RequestModel
     * model レスポンス：IfaRepAddA006ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA006DtoResponse> additionOkA006(IfaRepAddA006DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A008
     * アクション名：初期表示
     * Dto リクエスト：IfaRepAddA001DtoRequest
     * Dto レスポンス：IfaRepAddA001DtoResponse
     * model リクエスト：IfaRepAddA001RequestModel
     * model レスポンス：IfaRepAddA001ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA008DtoResponse> branchNameUpdateA008(IfaRepAddA008DtoRequest dtoReq) throws Exception;
}
