package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;
import lombok.Data;

@Data
public class IfaSuggestionBoxCommonA001ApiResponse {


	private String sugBoxCommonScreenComment;

	private List<IfaSuggestionBoxCommonSql002ResponseModel> suggestionList;

}
