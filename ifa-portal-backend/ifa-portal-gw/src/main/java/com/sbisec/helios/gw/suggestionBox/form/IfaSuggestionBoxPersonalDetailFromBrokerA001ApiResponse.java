package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;

import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 *
 * @author SCSK山岸
 2025/07/25 新規作成
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaSuggestionBoxPersonalDetailFromBrokerA001ApiResponse {
    
    /** 要望No（数字） */
    private String sbpNo;
    
    /** タイトル（全角半角） */
    private String title;
    
    /** ステータス（数字） */
    private String status;
    
    /** 要望内容（全角半角） */
    private String suggestion;
    
    /** 添付ファイル1（全角半角） */
    private String attachFile1;
    
    /** 添付ファイル2（全角半角） */
    private String attachFile2;
    
    /** 添付ファイル3（全角半角） */
    private String attachFile3;
    
    /** 要望作成日時（数字） */
    private String registerDate;
    
    /** 登録済回答一覧. */
    private List<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel> registeredAnswerList;

}
