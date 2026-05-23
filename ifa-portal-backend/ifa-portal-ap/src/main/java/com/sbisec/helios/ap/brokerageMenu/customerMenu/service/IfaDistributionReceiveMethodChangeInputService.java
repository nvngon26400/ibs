package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010201-02_1
 * 画面名：分配金受取方法変更入力
 * 2023/11/28 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaDistributionReceiveMethodChangeInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDistributionReceiveMethodChangeInputA001RequestDto
     * Dto レスポンス：IfaDistributionReceiveMethodChangeInputA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 受取方法変更入力画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> initializeA001(
                IfaDistributionReceiveMethodChangeInputA001RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A002
     * アクション名：登録・変更
     * Dto リクエスト：IfaDistributionReceiveMethodChangeInputA002RequestDto
     * Dto レスポンス：IfaDistributionReceiveMethodChangeInputA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 登録・変更の成否
     * @exception Exception システムエラー
     */
    public DataList<IfaDistributionReceiveMethodChangeInputA002ResponseDto> registerChangeA002(
            IfaDistributionReceiveMethodChangeInputA002RequestDto dtoReq
    ) throws Exception;

}
