package com.sbisec.helios.ap.wholePortal.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021ResponseDto;


/**
 * 画面ID：SUB01-01
 * 画面名：総合ポータル_ホーム

 * @author 池亀緑
 *
 */
public interface IfaWholePortalHomeService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaWholePortalHomeA001RequestDto
     * Dto レスポンス：IfaWholePortalHomeA011ResponseDto
     */
    public DataList<IfaWholePortalHomeA001ResponseDto> initializeA001(IfaWholePortalHomeA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A011
     * アクション名：債券満期償還
     * Dto リクエスト：wholePortalA011DtoRequest
     * Dto レスポンス：IfaWholePortalHomeA011ResponseDto
     */
    public DataList<IfaWholePortalHomeA011ResponseDto> bondMaturityRedemptionA011(
            IfaWholePortalHomeA011RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A018
     * アクション名：更新
     * Dto リクエスト：IfaWholePortalHomeA018RequestDto
     * Dto レスポンス：IfaWholePortalHomeA018ResponseDto
     */
    public DataList<IfaWholePortalHomeA018ResponseDto> updateA018(IfaWholePortalHomeA018RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A021
     * アクション名：検索表示
     * Dto リクエスト：IfaWholePortalHomeA021RequestDto
     * Dto レスポンス：IfaWholePortalHomeA021ResponseDto
     */
    public DataList<IfaWholePortalHomeA021ResponseDto> searchDisplayA021(IfaWholePortalHomeA021RequestDto dtoReq)
            throws Exception;

}
