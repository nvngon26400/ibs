package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA013RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA013ResponseDto;

/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 *
 * 2023/11/09 新規作成
 */
public interface IfaLoginIdNewRegisterService extends Service {
    
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaLoginIdNewRegisterA001RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA001ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA001RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA001ResponseDto> initialDisplayA001(IfaLoginIdNewRegisterA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：所属権限更新
     * Dto リクエスト：IfaLoginIdNewRegisterA002RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA002ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA002RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA002ResponseDto> affiliationAuthorityUpdateA002(IfaLoginIdNewRegisterA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：本支名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA003RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA003ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA003RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA003ResponseDto> headOfficeNameUpdateA003(IfaLoginIdNewRegisterA003RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA004RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA004ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA004RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA004ResponseDto> brokerNameUpdateA004(IfaLoginIdNewRegisterA004RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA005RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA005ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA005RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA005ResponseDto> brokerBranchNameUpdateA005(IfaLoginIdNewRegisterA005RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：リセット
     * Dto リクエスト：IfaLoginIdNewRegisterA06RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA006ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA006RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA006ResponseDto> resetA006(IfaLoginIdNewRegisterA006RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A011
     * アクション名：登録（OK）
     * Dto リクエスト：IfaLoginIdNewRegisterA011RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA011ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterA011RequestModel
     * model レスポンス：IfaLoginIdNewRegisterA011ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA011ResponseDto> registrationOkA011(IfaLoginIdNewRegisterA011RequestDto dtoReq)
            throws Exception;

}
