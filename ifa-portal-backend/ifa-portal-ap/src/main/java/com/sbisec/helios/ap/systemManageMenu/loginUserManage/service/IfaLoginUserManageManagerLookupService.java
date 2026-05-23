package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007ResponseDto;

/**
 * 画面ID：SUB0601_01-01
 * 画面名：ログイン者管理（管理者用）照会
 * @author <author-name>
 *
 * 2023/11/02 新規作成
 */
public interface IfaLoginUserManageManagerLookupService extends Service {
    
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaLoginUserManageManagerLookupA001RequestDto
     * Dto レスポンス：IfaLoginUserManageManagerLookupA001ResponseDto
     * model リクエスト：IfaLoginUserManageManagerLookupA001RequestModel
     * model レスポンス：IfaLoginUserManageManagerLookupA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA001ResponseDto> initialDisplayA001(
            IfaLoginUserManageManagerLookupA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：検索
     * Dto リクエスト：IfaLoginUserManageManagerLookupA002RequestDto
     * Dto レスポンス：IfaLoginUserManageManagerLookupA002ResponseDto
     * model リクエスト：IfaLoginUserManageManagerLookupA002RequestModel
     * model レスポンス：IfaLoginUserManageManagerLookupA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA002ResponseDto> searchA002(
            IfaLoginUserManageManagerLookupA002RequestDto dtoReq) throws Exception;
    
    
    /**
     * アクションID：A007
     * アクション名：削除
     * Dto リクエスト：IfaLoginUserManageManagerLookupA007DtoRequest
     * Dto レスポンス：IfaLoginUserManageManagerLookupA007DtoResponse
     * model リクエスト：IfaLoginUserManageManagerLookupA007RequestModel
     * model レスポンス：IfaLoginUserManageManagerLookupA007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA007ResponseDto> deleteA007(
            IfaLoginUserManageManagerLookupA007RequestDto dtoReq) throws Exception;
    
}
