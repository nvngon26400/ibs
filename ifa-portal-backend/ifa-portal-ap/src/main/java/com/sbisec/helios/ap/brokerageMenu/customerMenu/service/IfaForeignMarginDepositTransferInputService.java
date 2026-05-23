package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0304-01_1
 * 画面名：米株信用保証金振替入力
 *
 * @author SCSK
 *     2023/12/04 新規作成
 */
public interface IfaForeignMarginDepositTransferInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA001RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA001ResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferInputA001RequestModel
     * model レスポンス：IfaForeignMarginDepositTransferInputA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA001ResponseDto> initializeA001(IfaForeignMarginDepositTransferInputA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：口座選択
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA002RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA002ResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferInputA002RequestModel
     * model レスポンス：IfaForeignMarginDepositTransferInputA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA002ResponseDto> accountSelectionA002(IfaForeignMarginDepositTransferInputA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：結果を表示
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA003RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA003ResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferInputA003RequestModel
     * model レスポンス：IfaForeignMarginDepositTransferInputA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 結果表示処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA003ResponseDto> displayResultA003(IfaForeignMarginDepositTransferInputA003RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：振替確認
     * Dto リクエスト：IfaForeignMarginDepositTransferInputA004RequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferInputA004ResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferInputA004RequestModel
     * model レスポンス：IfaForeignMarginDepositTransferInputA004ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 振替確認処理で例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferInputA004ResponseDto> transferConfirmA004(IfaForeignMarginDepositTransferInputA004RequestDto dtoReq)
            throws Exception;

}
