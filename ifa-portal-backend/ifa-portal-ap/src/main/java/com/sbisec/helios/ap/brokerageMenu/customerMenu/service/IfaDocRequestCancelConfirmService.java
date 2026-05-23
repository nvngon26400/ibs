package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestCancelConfirmA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0704-03_1 画面名：書類請求取消
 *
 * @author xin.huang
 */
public interface IfaDocRequestCancelConfirmService extends Service {

    /**
     * アクションID：A002
     * アクション名：書類請求取消
     * Dtoリクエスト：IfaDocRequestCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestCancelConfirmA002ResponseDto> cancelConfirmA002(IfaDocRequestCancelConfirmA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A005
     * アクション名：BM交付取消
     * Dtoリクエスト：IfaDocRequestCancelConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestCancelConfirmA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    public DataList<IfaDocRequestCancelConfirmA005ResponseDto> cancelConfirmA005(IfaDocRequestCancelConfirmA005RequestDto dtoReq)
            throws Exception;
}
