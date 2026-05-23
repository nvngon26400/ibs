package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0601-03_1 画面名：取消確認
 *
 * @author xin.huang
 */
public interface IfaWithdrawCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：取消実行
     * Dtoレスポンス：IfaWithdrawCancelConfirmA001ResponseDto
     * Dtoリクエスト：IfaWithdrawCancelConfirmA001RequestDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 取消実行の際、例外が発生した場合
     */
    public DataList<IfaWithdrawCancelConfirmA001ResponseDto> cancelConfirmA001(
            IfaWithdrawCancelConfirmA001RequestDto dtoReq) throws Exception;
}
