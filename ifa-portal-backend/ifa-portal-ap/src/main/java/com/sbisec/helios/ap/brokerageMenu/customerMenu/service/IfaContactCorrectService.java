package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaContactCorrectService extends Service {

    /**
     * アクションID:A001
     * アクション名:初期化
     * リクエスト:IfaContactCorrectA001RequestDto
     * レスポンス:IfaContactCorrectA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactCorrectA001ResponseDto> initializeA001(
            IfaContactCorrectA001RequestDto x_dtoReq) throws Exception;

    /**
     * アクションID:A003
     * アクション名:更新ボタン押下
     * リクエスト:IfaContactCorrectA003ResponseDto
     * レスポンス:IfaContactCorrectA003RequestDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactCorrectA003ResponseDto> updateA003(
            IfaContactCorrectA003RequestDto x_dtoReq) throws Exception;
}
