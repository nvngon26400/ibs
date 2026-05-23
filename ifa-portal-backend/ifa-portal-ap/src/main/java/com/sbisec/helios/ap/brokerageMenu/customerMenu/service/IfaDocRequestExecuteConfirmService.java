package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestExecuteConfirmA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0704-02_1 画面名：書類請求確認
 *
 * @author xin.huang
 */
public interface IfaDocRequestExecuteConfirmService extends Service {

    /**
     * アクションID：A002
     * アクション名：書類請求登録
     * Dtoリクエスト：IfaDocRequestExecuteConfirmA002RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmA002ResponseDto> executeConfirmA002(
            IfaDocRequestExecuteConfirmA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A005
     * アクション名：BM交付情報登録
     * Dtoリクエスト：IfaDocRequestExecuteConfirmA005RequestDto
     * Dtoレスポンス：IfaDocRequestExecuteConfirmA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmA005ResponseDto> executeConfirmA005(
            IfaDocRequestExecuteConfirmA005RequestDto dtoReq) throws Exception;
}
