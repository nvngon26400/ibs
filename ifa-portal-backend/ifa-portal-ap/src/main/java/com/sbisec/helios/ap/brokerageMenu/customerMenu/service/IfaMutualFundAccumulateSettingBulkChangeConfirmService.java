package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 * 
 * @author nicksen.li
 *
 * 2025/07/24 新規作成
 */
public interface IfaMutualFundAccumulateSettingBulkChangeConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：設定一括変更登録
     * Dto リクエスト：IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiRequestDto
     * Dto レスポンスIfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto> settingBulkChangeRegisterA001(IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto dtoReq)
            throws Exception;

}
