package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007aResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */
public interface IfaReleaseNoteRegisterService extends Service {

    /**
     * アクションID：A007a
     * アクション名：登録
     * Dto レスポンス：IfaReleaseNoteRegisterA007aResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteRegisterA007aResponseDto> selectA007a() throws Exception;

    /**
     * アクションID：A007b
     * アクション名：登録
     * Dto リクエスト：IfaReleaseNoteRegisterA007bRequestDto
     * Dto レスポンス：IfaReleaseNoteRegisterA007bResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteRegisterA007bResponseDto> registerA007b(IfaReleaseNoteRegisterA007bRequestDto dtoReq)
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
