package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto;

import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql001ResponseModel;
import lombok.Data;

import java.util.List;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteEmployeeA001ResponseDto {

    /** リリースノート内容(社員用) */
    private List<IfaReleaseNoteEmployeeSql001ResponseModel> ifaReleaseNoteEmployeeList;

    /** 最小年数 */
    private String minYear;

    /** 最大年数 */
    private String maxYear;
}
