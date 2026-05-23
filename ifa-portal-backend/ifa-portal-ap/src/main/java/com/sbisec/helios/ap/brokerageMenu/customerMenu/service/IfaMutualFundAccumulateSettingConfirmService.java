package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-02_2
 * 画面名：投信積立設定確認
 * 
 * @author nicksen.li
 *
 * 2025/04/20 新規作成
 */
public interface IfaMutualFundAccumulateSettingConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：設定登録
     * Dto リクエスト：IfaMutualFundAccumulateSettingConfirmA001RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingConfirmA001ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingConfirmA001RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingConfirmA001ResponseDto> confirmA001(IfaMutualFundAccumulateSettingConfirmA001RequestDto dtoReq)
            throws Exception;

}
