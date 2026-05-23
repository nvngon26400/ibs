package com.sbisec.helios.ap.suggestionBox.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonRegisterDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonRegisterA007bRequestDto;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxCommonRegisterService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/23 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxCommonRegisterService")
public class IfaSuggestionBoxCommonRegisterServiceImpL implements IfaSuggestionBoxCommonRegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonRegisterServiceImpL.class);

	@Autowired
	private IfaSuggestionBoxCommonRegisterDao dao;

	@Autowired
	private IfaSuggestionBoxCommonRegisterServiceImpLEx serviceImpLEx;

	public DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> selectA007() throws Exception {

		DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> res = new DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel>();

		res = dao.selectIfaSuggestionBoxCommonRegisterSql001();

		return res;

	}

	/**
	 * アクションID：A007b
	 * アクション名：更新
	 * Dto リクエスト：IfaSuggestionBoxCommonRegisterA007bDtoRequest
	 * Dto レスポンス：String
	 * model リクエスト：RequestModel
	 * model レスポンス：String
	 *
	 * @param dtoReq リクエストパラメータ
	 * @return レスポンスパラメータ
	 * @exception Exception 初期化処理で例外が発生した場合
	 */
	public DataList<String> registA007b(
			IfaSuggestionBoxCommonRegisterA007bRequestDto dtoReq, String fileDirectory) throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("IfaSuggestionBoxCommonRegisterServiceImpL.registA007b");
		}

		// --------------------------------
		// DB更新/登録 SQL003～SQL005
		// --------------------------------

		DataList<String> res = new DataList<String>();

		try {
			res = serviceImpLEx.registA007bEx(dtoReq, fileDirectory);
		} catch (IfaSuggestionBoxException ex) {
			String message = IfaCommonUtil.getMessage(ex.getMessageKey(), new String[] { ex.getMessageParam() });
			res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ex.getMessageKey(), message);
		}
		return res;
	}
}
