package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_01-04
 * 画面名：米株信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/17 新規作成
 */
public interface IfaForeignMarginCollateralDeficientAlertListService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignMarginCollateralDeficientAlertListA002RequestDto
     * Dto レスポンス：IfaForeignMarginCollateralDeficientAlertListA002ResponseDto
     * model リクエスト：IfaForeignMarginCollateralDeficientAlertListA002RequestModel
     * model レスポンス：IfaForeignMarginCollateralDeficientAlertListA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto> displayA002(IfaForeignMarginCollateralDeficientAlertListA002RequestDto dtoReq)
            throws Exception;

}
