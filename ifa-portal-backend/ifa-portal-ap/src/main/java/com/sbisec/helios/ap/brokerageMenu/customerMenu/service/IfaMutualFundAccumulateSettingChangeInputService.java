package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-03_1
 * 画面名：投信積立設定変更入力
 * 
 * @author nicksen.li
 *
 * 2025/04/16 新規作成
 */
public interface IfaMutualFundAccumulateSettingChangeInputService extends Service {

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
    public DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto> initializeA001(IfaMutualFundAccumulateSettingChangeInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A009
     * アクション名：設定確認
     * Dto リクエスト：IfaMutualFundAccumulateSettingChangeInputA009RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingChangeInputA009ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingChangeInputA009RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingChangeInputA009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto> settingConfirmA009(IfaMutualFundAccumulateSettingChangeInputA009RequestDto dtoReq)
            throws Exception;

}
