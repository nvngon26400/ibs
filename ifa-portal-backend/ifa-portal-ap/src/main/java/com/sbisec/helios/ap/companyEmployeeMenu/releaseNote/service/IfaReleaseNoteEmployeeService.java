package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002ResponseDto;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
public interface IfaReleaseNoteEmployeeService extends Service{

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteEmployeeA001RequestDto
     * レスポンス：IfaReleaseNoteEmployeeA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteEmployeeA001ResponseDto> initializeA001(IfaReleaseNoteEmployeeA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示対象年選択
     * リクエスト：IfaReleaseNoteEmployeeA002RequestDto
     * レスポンス：IfaReleaseNoteEmployeeA002ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteEmployeeA002ResponseDto> selectDisplayYearA002(IfaReleaseNoteEmployeeA002RequestDto dtoReq)
            throws Exception;

}
