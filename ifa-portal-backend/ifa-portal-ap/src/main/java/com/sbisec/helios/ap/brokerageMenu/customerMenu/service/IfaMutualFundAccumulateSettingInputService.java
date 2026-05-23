package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-02_1
 * 画面名：投信積立設定入力
 * 
 * @author nicksen.li
 *
 * 2025/04/14 新規作成
 */
public interface IfaMutualFundAccumulateSettingInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMutualFundAccumulateSettingInputA001RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingInputA001ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingInputA001RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto> initializeA001(IfaMutualFundAccumulateSettingInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A010
     * アクション名：設定確認
     * Dto リクエスト：IfaMutualFundAccumulateSettingInputA010RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingInputA010ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingInputA010RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingInputA010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto> settingConfirmA010(IfaMutualFundAccumulateSettingInputA010RequestDto dtoReq)
            throws Exception;

}
