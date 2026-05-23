package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-03_2
 * 画面名：投信積立設定変更確認
 * 
 * @author nicksen.li
 *
 * 2025/04/20 新規作成
 */
public interface IfaMutualFundAccumulateSettingChangeConfirmService extends Service {

    /**
     * アクションID：A002
     * アクション名：設定変更登録
     * Dto リクエスト：IfaMutualFundAccumulateSettingChangeConfirmA002ApiRequestDto
     * Dto レスポンスIfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto
     * model リクエスト：IfaMutualFundAccumulateSettingChangeConfirmA002RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingChangeConfirmA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto> settingChangeRegisterA002(IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto dtoReq)
            throws Exception;

}
