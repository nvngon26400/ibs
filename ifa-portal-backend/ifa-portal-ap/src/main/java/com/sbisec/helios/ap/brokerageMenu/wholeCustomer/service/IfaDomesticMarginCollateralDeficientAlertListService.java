package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginCollateralDeficientAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginCollateralDeficientAlertListA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_01-02
 * 画面名：国内信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/11 新規作成
 */
public interface IfaDomesticMarginCollateralDeficientAlertListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDomesticMarginCollateralDeficientAlertListA002RequestDto
     * Dto レスポンス：IfaDomesticMarginCollateralDeficientAlertListA002ResponseDto
     * model リクエスト：IfaDomesticMarginCollateralDeficientAlertListA002RequestModel
     * model レスポンス：IfaDomesticMarginCollateralDeficientAlertListA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginCollateralDeficientAlertListA002ResponseDto> displayA002(IfaDomesticMarginCollateralDeficientAlertListA002RequestDto dtoReq)
            throws Exception;

}
