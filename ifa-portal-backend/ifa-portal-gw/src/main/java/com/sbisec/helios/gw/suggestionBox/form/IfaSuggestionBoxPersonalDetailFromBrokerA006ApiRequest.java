package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer;
import lombok.Data;


/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 *
 * @author SCSK山岸
 2025/07/25 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailFromBrokerA006ApiRequest {

    /** 要望No（数字） */
    @Size(max = 6, message = "要望No")
    @Pattern(regexp = "[0-9]*", message = "要望No")
    @NotEmpty(message = "要望No")
    private String sbpNo;

    /** ステータス（） */
    @Size(max = 1, message = "ステータス")
    @Pattern(regexp = "[0-9]*", message = "ステータス")
    @NotEmpty(message = "ステータス")
    private String status;
    
    /** 登録済回答一覧 */
    private List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer> registeredAnswerList;

    /** 新規回答一覧 */
    private List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer> newAnswerList;

}
