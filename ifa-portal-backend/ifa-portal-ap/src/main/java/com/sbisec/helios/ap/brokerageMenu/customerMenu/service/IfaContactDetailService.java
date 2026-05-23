package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaContactDetailService extends Service {

    /**
     * アクションID:A001
     * アクション名:初期化
     * リクエスト:IfaContactDetailA001RequestDto
     * レスポンス:IfaContactDetailA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactDetailA001ResponseDto> initializeA001(
            IfaContactDetailA001RequestDto x_dtoReq) throws Exception;
}
