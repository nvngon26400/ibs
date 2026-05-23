package com.sbisec.helios.ap.suggestionBox.dto;

import java.util.List;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;
import lombok.Data;

@Data
public class IfaSuggestionBoxCommonA006aDtoResponse {

	/** 皆様からの要望一覧リスト .*/
	private List<IfaSuggestionBoxCommonSql002ResponseModel> suggestionList;

}
