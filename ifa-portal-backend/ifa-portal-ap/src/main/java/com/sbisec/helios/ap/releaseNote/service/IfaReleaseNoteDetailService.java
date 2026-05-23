package com.sbisec.helios.ap.releaseNote.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
public interface IfaReleaseNoteDetailService extends Service{

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteDetailA001RequestDto
     * レスポンス：IfaReleaseNoteDetailA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteDetailA001ResponseDto> initializeA001(IfaReleaseNoteDetailA001RequestDto dtoReq)
            throws Exception;
}
