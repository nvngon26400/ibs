package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */
public interface IfaReleaseNoteUpdateService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaReleaseNoteUpdateA001RequestDto
     * Dto レスポンス：IfaReleaseNoteUpdateA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteUpdateA001ResponseDto> initializeA001(IfaReleaseNoteUpdateA001RequestDto dtoReq)
        throws Exception;

    /**
     * アクションID：A004
     * アクション名：ダウンロード
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<String> downloadA004() throws Exception;

    /**
     * アクションID：A009
     * アクション名：更新
     * Dto リクエスト：IfaReleaseNoteUpdateA009bRequestDto
     * Dto レスポンス：IfaReleaseNoteUpdateA009bResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteUpdateA009bResponseDto> updateA009b(IfaReleaseNoteUpdateA009bRequestDto dtoReq)
        throws Exception;

    /**
     * アクション名：ファイルディレクトリ情報を取得する
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<String> getReleaseNoteFileDir() throws Exception;

}
