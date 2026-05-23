package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawExecuteConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0601-02_1 画面名：出金確認
 *
 * @author xin.huang
 */
public interface IfaWithdrawExecuteConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：出金実行
     * Dtoレスポンス：IfaWithdrawExecuteConfirmA001ResponseDto
     * Dtoリクエスト：IfaWithdrawExecuteConfirmA001RequestDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金実行の際、例外が発生した場合
     */
    public DataList<IfaWithdrawExecuteConfirmA001ResponseDto> executeConfirmA001(
            IfaWithdrawExecuteConfirmA001RequestDto dtoReq) throws Exception;
}
