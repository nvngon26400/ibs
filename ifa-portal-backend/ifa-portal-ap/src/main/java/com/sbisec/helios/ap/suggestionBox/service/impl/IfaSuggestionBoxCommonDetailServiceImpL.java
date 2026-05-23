package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonDetailDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxCommonDetailService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;


@Component(value = "cmpIfaSuggestionBoxCommonDetailService")
public class IfaSuggestionBoxCommonDetailServiceImpL implements IfaSuggestionBoxCommonDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonDetailServiceImpL.class);

	@Autowired
	private IfaSuggestionBoxCommonDetailDao dao;

	/**
	 * Dto リクエスト：IfaSuggestionBoxCommonA001DtoRequest
	 * Dto レスポンス：IfaSuggestionBoxCommonA001DtoResponse
	 * model リクエスト：RequestModel
	 * model レスポンス：ResponseModel
	 *
	 * @param dtoReq リクエストパラメータ
	 * @return レスポンスパラメータ
	 * @exception Exception 初期化処理で例外が発生した場合
	 */
	public DataList<IfaSuggestionBoxCommonDetailA001ResponseDto> initializeA001(
			IfaSuggestionBoxCommonDetailA001RequestDto dtoReq) throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("IfaSuggestionBoxCommonServiceImpL.initializeA001");
		}

		IfaSuggestionBoxCommonDetailA001ResponseDto dtoResponse = new IfaSuggestionBoxCommonDetailA001ResponseDto();

		//SQLリクエストを作成
		IfaSuggestionBoxCommonDetailSql001RequestModel sql001Req = new IfaSuggestionBoxCommonDetailSql001RequestModel();

		sql001Req.setSbcNo(dtoReq.getSbcNo());

		//SQL実行結果
		List<IfaSuggestionBoxCommonDetailSql001ResponseModel> sql001ResultList = dao.selectIfaSugBoxCommonDetailSql001(sql001Req).getDataList();

		//質問内容格納
		IfaSuggestionBoxCommonDetailSql001ResponseModel sql001ResultFirst = sql001ResultList.get(0);

		dtoResponse.setTitle(sql001ResultFirst.getTitle());
		dtoResponse.setRegisterDate(sql001ResultFirst.getCreateDate());
		dtoResponse.setStatus(sql001ResultFirst.getStatus());
		dtoResponse.setSuggestion(sql001ResultFirst.getSuggestion());
		dtoResponse.setAttachFile1(sql001ResultFirst.getFile1());
		dtoResponse.setAttachFile2(sql001ResultFirst.getFile2());
		dtoResponse.setAttachFile3(sql001ResultFirst.getFile3());

		//回答内容格納
		List<IfaSuggestionBoxCommonDetailA001ResponseDto.answerList> answerListList = new ArrayList<>();

		for(IfaSuggestionBoxCommonDetailSql001ResponseModel sql001Result : sql001ResultList) {
			
			IfaSuggestionBoxCommonDetailA001ResponseDto.answerList answerList = new IfaSuggestionBoxCommonDetailA001ResponseDto.answerList();

			answerList.setAnswerNo(sql001Result.getSbcaNo());
			answerList.setAnswerRegisterDate(sql001Result.getAnswerDate());
			answerList.setAnswerUpdateDate(sql001Result.getAnswerUpdateDate());
			answerList.setAnswerContents(sql001Result.getAnswer());

			answerListList.add(answerList);

		}

		dtoResponse.setAnswerList(answerListList);

		// レスポンスＤＴＯList
		List<IfaSuggestionBoxCommonDetailA001ResponseDto> dtoResponseList = new ArrayList<IfaSuggestionBoxCommonDetailA001ResponseDto>();

		dtoResponseList.add(dtoResponse);

		// レスポンスDTODataList
		DataList<IfaSuggestionBoxCommonDetailA001ResponseDto>  dtoResDataList = IfaCommonUtil.createDataList(dtoResponseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

		// 画面側へレスポンスDTODataListを返却
		return dtoResDataList;

	}

}
