package com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dto.IfaOnLineAccountOpenA001RequestDto;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dto.IfaOnLineAccountOpenA001ResponseDto;

/**
 * 画面ID：SUB0207_0201
 * 画面名：オンライン口座開設
 *
 * @author SCSK 木村
 2025/02/06 新規作成
 */
public interface IfaOnLineAccountOpenService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOnLineAccountOpenA001RequestDto
     * Dto レスポンス：IfaOnLineAccountOpenA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaOnLineAccountOpenA001ResponseDto> initializeA001(
            IfaOnLineAccountOpenA001RequestDto dtoReq
    ) throws Exception;

}