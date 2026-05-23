package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_02-01
 * 画面名：国内信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
public interface IfaDomesticMarginPositionDueDateAlertService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDomesticMarginPositionDueDateAlertA002RequestDto
     * Dto レスポンス：IfaDomesticMarginPositionDueDateAlertA002ResponseDto
     * model リクエスト：IfaDomesticMarginPositionDueDateAlertA002RequestModel
     * model レスポンス：IfaDomesticMarginPositionDueDateAlertA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto> displayA002(IfaDomesticMarginPositionDueDateAlertA002RequestDto dtoReq)
            throws Exception;

}
