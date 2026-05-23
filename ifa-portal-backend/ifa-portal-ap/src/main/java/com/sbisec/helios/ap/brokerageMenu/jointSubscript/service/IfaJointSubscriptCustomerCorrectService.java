package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID：SUB0206_01-03
 * 画面名：共同募集 顧客修正
 * 2024/12/10 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaJointSubscriptCustomerCorrectService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerCorrectA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerCorrectA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002
     * アクション名：登録確認(新規登録入力)
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA002RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerCorrectA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA002ResponseDto> confirmCorrectA002(
            IfaJointSubscriptCustomerCorrectA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A004
     * アクション名：登録(新規登録確認)
     * Dto リクエスト：IfaJointSubscriptCustomerCorrectA004RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerCorrectA004ResponseDto> executeCorrectA004(
            IfaJointSubscriptCustomerCorrectA004RequestDto dtoReq) throws Exception;

}