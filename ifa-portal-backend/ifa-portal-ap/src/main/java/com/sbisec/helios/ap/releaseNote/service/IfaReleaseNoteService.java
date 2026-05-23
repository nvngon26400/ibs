package com.sbisec.helios.ap.releaseNote.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
public interface IfaReleaseNoteService extends Service{

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteA001RequestDto
     * レスポンス：IfaReleaseNoteA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA001ResponseDto> initializeA001(IfaReleaseNoteA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示対象年選択
     * リクエスト：IfaReleaseNoteA002RequestDto
     * レスポンス：IfaReleaseNoteA002ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA002ResponseDto> selectDisplayYearA002(IfaReleaseNoteA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：次回表示フラグ更新
     * リクエスト：IfaReleaseNoteA003RequestDto
     * レスポンス：IfaReleaseNoteA003ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 次回表示フラグ更新処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteA003ResponseDto> updateNextDispFlgA003(IfaReleaseNoteA003RequestDto dtoReq)
            throws Exception;

}
