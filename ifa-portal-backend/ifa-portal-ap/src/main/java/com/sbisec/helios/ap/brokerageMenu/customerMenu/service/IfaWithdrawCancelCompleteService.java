package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelCompleteA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawCancelCompleteA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0601-03_2 画面名：出金取消完了
 *
 * @author xin.huang
 */
public interface IfaWithdrawCancelCompleteService extends Service {

    /**
     * アクションID：A002
     * アクション名：出金取消完了
     * Dtoレスポンス：IfaWithdrawCancelCompleteA002ResponseDto
     * Dtoリクエスト：IfaWithdrawCancelCompleteA002RequestDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金取消完了の際、例外が発生した場合
     */
    public DataList<IfaWithdrawCancelCompleteA002ResponseDto> cancelCompleteA002(
            IfaWithdrawCancelCompleteA002RequestDto dtoReq) throws Exception;
}
