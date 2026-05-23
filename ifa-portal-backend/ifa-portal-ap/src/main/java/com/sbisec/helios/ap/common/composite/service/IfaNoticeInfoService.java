package com.sbisec.helios.ap.common.composite.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
public interface IfaNoticeInfoService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaNoticeInfoA001RequestDto
     * Dto レスポンス：IfaNoticeInfoA001ResponseDto
     * model リクエスト：IfaNoticeInfoA001RequestModel
     * model レスポンス：IfaNoticeInfoA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNoticeInfoA002DtoResponse> displayA002(IfaNoticeInfoA002DtoRequest dtoReq)
            throws Exception;

}
