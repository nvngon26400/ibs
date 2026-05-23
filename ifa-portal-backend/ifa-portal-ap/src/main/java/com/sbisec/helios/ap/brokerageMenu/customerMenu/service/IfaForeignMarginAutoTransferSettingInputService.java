package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingInputA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0306-01_1
 * 画面名：米株信用自動振替設定入力
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 */
public interface IfaForeignMarginAutoTransferSettingInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingInputA001DtoResponse
     * model リクエスト：IfaForeignMarginAutoTransferSettingInputA001RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingInputA001ResponseDto> initializeA001(
            IfaForeignMarginAutoTransferSettingInputA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：設定確認
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingInputA002DtoRequest
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingInputA002DtoResponse
     * model リクエスト：IfaForeignMarginAutoTransferSettingInputA002RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingInputA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingInputA002ResponseDto> settingConfirmA002(
            IfaForeignMarginAutoTransferSettingInputA002RequestDto dtoReq) throws Exception;
    
}
