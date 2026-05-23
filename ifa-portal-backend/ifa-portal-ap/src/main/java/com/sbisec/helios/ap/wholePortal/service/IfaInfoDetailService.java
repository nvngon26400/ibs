package com.sbisec.helios.ap.wholePortal.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aResponseDto;

/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaInfoDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInfoDetailA001RequestDto
     * Dto レスポンス：IfaInfoDetailA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoDetailA001ResponseDto> initializeA001(
            IfaInfoDetailA001RequestDto dtoReq
    ) throws Exception;


    /**
     * アクションID：A03a
     * アクション名：ダウンロード
     * Dto リクエスト：IfaInfoDetailA003aRequestDto
     * Dto レスポンス：IfaInfoDetailA003aResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @param fwSessionId フレームワークセッションID
     * @return ダウンロードファイル名
     * @exception Exception システムエラー
     */
    public DataList<IfaInfoDetailA003aResponseDto> downloadA003a(
            IfaInfoDetailA003aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception;

}
