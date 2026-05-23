package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author BASE 丁
 2024/06/20 新規作成
 */
public interface IfaDomesticMutualFundOrderOtherInfoService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundOrderOtherInfoA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderOtherInfoA001ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderOtherInfoA001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderOtherInfoA001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto> initializeA001(IfaDomesticMutualFundOrderOtherInfoA001RequestDto dtoReq)
            throws Exception;

}
