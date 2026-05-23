package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202-0601-02_2 画面名：出金完了
 *
 * @author xin.huang
 */
public interface IfaWithdrawAcceptCompleteService extends Service {

    /**
     * アクションID：A002
     * アクション名：出金完了
     * Dtoレスポンス：IfaWithdrawAcceptCompleteA002ResponseDto
     * Dtoリクエスト：IfaWithdrawAcceptCompleteA002RequestDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金完了の際、例外が発生した場合
     */
    public DataList<IfaWithdrawAcceptCompleteA002ResponseDto> acceptCompleteA002(
            IfaWithdrawAcceptCompleteA002RequestDto dtoReq) throws Exception;
}
