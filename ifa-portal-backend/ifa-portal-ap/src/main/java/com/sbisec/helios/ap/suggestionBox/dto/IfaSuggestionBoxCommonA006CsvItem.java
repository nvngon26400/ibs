package com.sbisec.helios.ap.suggestionBox.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaSuggestionBoxCommonA006CsvItem extends ModelBase {

   private static final long serialVersionUID = 1L;
    
	/** 皆様からの要望No. */
	private String sbcNo;

	/** 更新日. */
	private String updateDate;

	/** 登録日. */
	private String createDate;

	/** タイトル. */
	private String title;

	/** カテゴリ. */
	private String category;

	/** ステータス. */
	private String status;
	
	/** 要望内容. */
	private String suggestion;

}
