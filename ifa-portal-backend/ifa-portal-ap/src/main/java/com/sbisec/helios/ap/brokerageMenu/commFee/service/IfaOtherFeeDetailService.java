package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
public interface IfaOtherFeeDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaOtherFeeDetailA001RequestDto
     * Dto レスポンス：IfaOtherFeeDetailA001ResponseDto
     * model リクエスト：IfaOtherFeeDetailA001RequestModel
     * model レスポンス：IfaOtherFeeDetailA001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOtherFeeDetailA001ResponseDto> initialDisplayA001(IfaOtherFeeDetailA001RequestDto dtoReq)
            throws Exception;

}
