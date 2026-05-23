package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID：SUB0206_01-02
 * 画面名：共同募集 顧客新規登録
 * 2024/12/10 新規作成
 * 
 * @author 大連 王永宝
 */
public interface IfaJointSubscriptCustomerNewRegisterService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerNewRegisterA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002
     * アクション名：登録確認(新規登録入力)
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA002RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA002ResponseDto> confirmRegisterA002(
            IfaJointSubscriptCustomerNewRegisterA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A004
     * アクション名：登録(新規登録確認)
     * Dto リクエスト：IfaJointSubscriptCustomerNewRegisterA004RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerNewRegisterA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerNewRegisterA004ResponseDto> executeRegisterA004(
            IfaJointSubscriptCustomerNewRegisterA004RequestDto dtoReq) throws Exception;

}
