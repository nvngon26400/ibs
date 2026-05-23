package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaContactConfirmService extends Service {

    /**
     * アクションID:A002
     * アクション名:登録
     * リクエスト:IfaContactConfirmA002RequestDto
     * レスポンス:IfaContactConfirmA002ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactConfirmA002ResponseDto> insertA002(
            IfaContactConfirmA002RequestDto x_dtoReq) throws Exception;
}
