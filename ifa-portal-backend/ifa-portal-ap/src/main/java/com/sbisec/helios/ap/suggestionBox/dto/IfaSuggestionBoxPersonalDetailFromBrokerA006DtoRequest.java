package com.sbisec.helios.ap.suggestionBox.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest {

    /** 要望No（数字） */
    private String sbpNo;

    /** ステータス（） */
    private String status;
    
    /** 登録済回答一覧 */
    private List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer> registeredAnswerList;

    /** 新規回答一覧 */
    private List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer> newAnswerList;

}
