package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202-0601-01 画面名：出金入力
 *
 * @author xin.huang
 */
public interface IfaWithdrawInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaWithdrawInputA001RequestDto
     * Dtoレスポンス：IfaWithdrawInputA001ResponseDto
     * 
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA001ResponseDto> initializeA001(IfaWithdrawInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：出金可能額を取得
     * Dtoリクエスト：IfaWithdrawInputA002RequestDto
     * Dtoレスポンス：IfaWithdrawInputA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金可能額を取得の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA002ResponseDto> selectAcBalanceA002(IfaWithdrawInputA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：出金確認
     * Dtoリクエスト：IfaWithdrawInputA003RequestDto
     * Dtoレスポンス：IfaWithdrawInputA003ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金確認の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA003ResponseDto> executeConfirmCheckA003(IfaWithdrawInputA003RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：出金取消確認check
     * Dtoリクエスト：IfaWithdrawInputA004RequestDto
     * Dtoレスポンス：IfaWithdrawInputA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金取消確認の際、例外が発生した場合
     */
    public DataList<IfaWithdrawInputA004ResponseDto> cancelConfirmCheckA004(IfaWithdrawInputA004RequestDto dtoReq)
            throws Exception;

}
