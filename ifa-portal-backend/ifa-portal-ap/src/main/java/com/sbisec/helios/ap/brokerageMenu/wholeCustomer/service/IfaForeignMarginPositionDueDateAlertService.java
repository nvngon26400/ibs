package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_02-02
 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/21 新規作成
 */
public interface IfaForeignMarginPositionDueDateAlertService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignMarginPositionDueDateAlertA002RequestDto
     * Dto レスポンス：IfaForeignMarginPositionDueDateAlertA002ResponseDto
     * model リクエスト：IfaForeignMarginPositionDueDateAlertA002RequestModel
     * model レスポンス：IfaForeignMarginPositionDueDateAlertA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto> displayA002(IfaForeignMarginPositionDueDateAlertA002RequestDto dtoReq)
            throws Exception;

}
