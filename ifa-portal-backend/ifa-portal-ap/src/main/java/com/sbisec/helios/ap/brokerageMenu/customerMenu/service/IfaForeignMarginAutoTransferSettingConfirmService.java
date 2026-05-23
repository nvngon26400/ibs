package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0306-01_2
 * 画面名：米株信用自動振替設定確認
 * @author 卞
 *
 * 2023/11/10 新規作成
 */
public interface IfaForeignMarginAutoTransferSettingConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：設定指示
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestDto
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto
     * model リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmA001aResponseDto> settingInstructionA001a(
            IfaForeignMarginAutoTransferSettingConfirmA001aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：設定指示
     * Dto リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestDto
     * Dto レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto
     * model リクエスト：IfaForeignMarginAutoTransferSettingConfirmA001RequestModel
     * model レスポンス：IfaForeignMarginAutoTransferSettingConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmA001ResponseDto> settingInstructionA001b(
            IfaForeignMarginAutoTransferSettingConfirmA001bRequestDto dtoReq) throws Exception;
    
}
