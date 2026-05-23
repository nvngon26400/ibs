package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonUpdateDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql001ResponseModel;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA009bRequestDto;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;

import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxCommonUpdateService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxCommonUpdateService")
public class IfaSuggestionBoxCommonUpdateServiceImpL implements IfaSuggestionBoxCommonUpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonUpdateServiceImpL.class);

    @Autowired
    private IfaSuggestionBoxCommonUpdateDao dao;

    @Autowired
    private IfaSuggestionBoxCommonUpdateServiceImpLEx serviceImpLEx;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSuggestionBoxCommonUpdateA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonUpdateA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaSuggestionBoxCommonUpdateA001ResponseDto> initializeA001(
            IfaSuggestionBoxCommonUpdateA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonUpdateServiceImpL.initializeA001");
        }

		// --------------------------------
        // SQL001.皆様からの要望詳細取得
        // --------------------------------
		
		// SQLリクエストのセット
		IfaSuggestionBoxCommonUpdateSql001RequestModel sql001Req = new IfaSuggestionBoxCommonUpdateSql001RequestModel();
        sql001Req.setSbcNo(dtoReq.getSbcNo());

		// SQL001の呼び出し
        List<IfaSuggestionBoxCommonUpdateSql001ResponseModel> sql001ResultList = dao.selectIfaSuggestionBoxCommonUpdateSql001(sql001Req).getDataList();
        
		// 回答一覧以外の項目をセット
        IfaSuggestionBoxCommonUpdateSql001ResponseModel sql001ResultFirst = sql001ResultList.get(0);        
        IfaSuggestionBoxCommonUpdateA001ResponseDto dtoResponse = new IfaSuggestionBoxCommonUpdateA001ResponseDto();
        BeanUtils.copyProperties(dtoResponse, sql001ResultFirst);

		// 回答一覧をセット
        List<IfaSuggestionBoxCommonUpdateA001ResponseDto.answerList> answerListList = new ArrayList<>();

        for(IfaSuggestionBoxCommonUpdateSql001ResponseModel sql001Result : sql001ResultList) {
            if(!StringUtils.isEmpty(sql001Result.getAnswerNo())) {

                IfaSuggestionBoxCommonUpdateA001ResponseDto.answerList answerList = new IfaSuggestionBoxCommonUpdateA001ResponseDto.answerList();

                BeanUtils.copyProperties(answerList, sql001Result);
                answerListList.add(answerList);
            }
        }

        dtoResponse.setAnswerList(answerListList);

		// レスポンスの返却
        List<IfaSuggestionBoxCommonUpdateA001ResponseDto> dtoResponseList = new ArrayList<IfaSuggestionBoxCommonUpdateA001ResponseDto>();
        dtoResponseList.add(dtoResponse);

        DataList<IfaSuggestionBoxCommonUpdateA001ResponseDto>  dtoResDataList = IfaCommonUtil.createDataList(dtoResponseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

        return dtoResDataList;

    }

    /**
     * アクションID：A009b
     * アクション名：更新
     * Dto リクエスト：IfaSuggestionBoxCommonUpdateA009bDtoRequest
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<String> updateA009b(
            IfaSuggestionBoxCommonUpdateA009bRequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonUpdateServiceImpL.updateA009b");
        }
        
		// --------------------------------
        // DB更新/登録 SQL003～SQL005, ファイル削除
        // --------------------------------

        DataList<String> res = new DataList<String>();
        
        try {
            res = serviceImpLEx.updateA009bEx(dtoReq);
        } catch (IfaSuggestionBoxException ex) {
            String message = IfaCommonUtil.getMessage(ex.getMessageKey(), new String[] { ex.getMessageParam() });
            res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ex.getMessageKey(), message);
        }
        return res;
    }
}
