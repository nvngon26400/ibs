package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;

import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql002_3ResponseModel;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * 
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDisplayA002ApiResponse {

    /** 要望一覧 */
    private List<IfaSuggestionBoxPersonalSql002_3ResponseModel> requestList;

}
