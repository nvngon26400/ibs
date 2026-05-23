package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoResponse;

/**
 * 画面ID：SUB0601_01-03_1
 * 画面名：ログインID更新登録
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 */
public interface IfaLoginIdUpdateRegisterService extends Service {
    
    /**
     * アクションID：A001
     * アクション名:初期表示
     * Dto リクエスト：IfaLoginIdUpdateRegisterA016DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA016DtoResponse
     * model リクエスト：IfaLoginIdUpdateRegisterA016RequestModel
     * model レスポンス：IfaLoginIdUpdateRegisterA016ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA001DtoResponse> initialDisplayA001(
            IfaLoginIdUpdateRegisterA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名:所属権限更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA002DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA002DtoResponse> affiliationAuthorityUpdateA002(
            IfaLoginIdUpdateRegisterA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：本支名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA003DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA003DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA003DtoResponse> headOfficeNameUpdateA003(
            IfaLoginIdUpdateRegisterA003DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA004DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA004DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA004DtoResponse> brokerNameUpdateA004(
            IfaLoginIdUpdateRegisterA004DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA005DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA005DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA005DtoResponse> brokerBranchNameUpdateA005(
            IfaLoginIdUpdateRegisterA005DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：リセット
     * Dto リクエスト：IfaLoginIdUpdateRegisterA006DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA006DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA006DtoResponse> resetA006(IfaLoginIdUpdateRegisterA006DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A014
     * アクション名：更新（OK）
     * Dto リクエスト：IfaLoginIdUpdateRegisterA014DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA014DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA014DtoResponse> updateOkA014(IfaLoginIdUpdateRegisterA014DtoRequest dtoReq)
            throws Exception;
    
}
