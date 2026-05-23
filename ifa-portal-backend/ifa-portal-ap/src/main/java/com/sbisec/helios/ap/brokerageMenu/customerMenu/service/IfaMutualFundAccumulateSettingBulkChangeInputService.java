package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-05_1
 * 画面名：投信積立設定一括変更入力
 * 
 * @author nicksen.li
 *
 * 2025/07/21 新規作成
 */
public interface IfaMutualFundAccumulateSettingBulkChangeInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA001RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto> initializeA001(IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A005
     * アクション名：設定変更確認
     * Dto リクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA005RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto> settingChangeConfirmA005(IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto dtoReq)
            throws Exception;

}
