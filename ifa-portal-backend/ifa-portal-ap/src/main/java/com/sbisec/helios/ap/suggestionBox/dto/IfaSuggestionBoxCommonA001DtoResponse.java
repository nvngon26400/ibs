package com.sbisec.helios.ap.suggestionBox.dto;

import java.util.List;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;
import lombok.Data;

@Data
public class IfaSuggestionBoxCommonA001DtoResponse {

    /** 画面コメント. */
    private String sugBoxCommonScreenComment;

    /** 皆様からの要望一覧リスト .*/
    private List<IfaSuggestionBoxCommonSql002ResponseModel> suggestionList;

}
