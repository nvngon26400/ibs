package com.sbisec.helios.ap.suggestionBox.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDetailFromBrokerDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxPersonalDetailFromBrokerService;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Component(value = "cmpIfaSuggestionBoxPersonalDetailFromBrokerService")
public class IfaSuggestionBoxPersonalDetailFromBrokerServiceImpL implements IfaSuggestionBoxPersonalDetailFromBrokerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalDetailFromBrokerServiceImpL.class);

    @Autowired
    private IfaSuggestionBoxPersonalDetailFromBrokerServiceImpLEx serviceImpLEx;
    @Autowired
    private IfaSuggestionBoxPersonalDetailFromBrokerDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse
     * model リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001RequestModel
     * model レスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001ResponseModel
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse> initializeA001(
            IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest dtoReq
        ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalDetailFromBrokerServiceImpL.initializeA001");
        }

        IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel sql001Req = new IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel();
        sql001Req.setSbpNo(dtoReq.getSbpNo());

        // SQL001 要望を取得
        List<IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel> selSql001Res  = dao.selectIfaSuggestionBoxPersonalDetailFromBrokerSql001(sql001Req).getDataList();

        IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel sql002Req = new IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel();
        sql002Req.setSbpNo(dtoReq.getSbpNo());

        // SQL002 回答一覧を取得
        List<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel> selSql002Res  = dao.selectIfaSuggestionBoxPersonalDetailFromBrokerSql002(sql002Req).getDataList();

        List<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse> responseList = new ArrayList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse>();
        IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse response = new IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse();

        // レスポンスに要望をセット
        response.setSbpNo(selSql001Res.get(0).getSbpNo());
        response.setTitle(selSql001Res.get(0).getTitle());
        response.setStatus(selSql001Res.get(0).getStatus());
        response.setSuggestion(selSql001Res.get(0).getSuggestion());
        response.setAttachFile1(selSql001Res.get(0).getAttachFile1());
        response.setAttachFile2(selSql001Res.get(0).getAttachFile2());
        response.setAttachFile3(selSql001Res.get(0).getAttachFile3());
        response.setRegisterDate(selSql001Res.get(0).getRegisterDate());

        // レスポンスに回答一覧をセット
        response.setRegisteredAnswerList(selSql002Res);

        responseList.add(response);
        DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse> dtoRes = new DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse>();
        dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：登録
     * Dto リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest
     * Dto レスポンス：String
     * model リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel
     * model レスポンス：String
     * @param dtoReq リクエストDTO
     * @return res レスポンス
     * @exception Exception システムエラー
     * @see <reference item>
     */
    @Override
    public DataList<String> registerA006(
            IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest dtoReq
        ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalDetailFromBrokerServiceImpL.registerA006");
        }

        DataList<String> res = new DataList<String>();

        try {
            res = serviceImpLEx.registerA006(dtoReq);
        } catch (IfaSuggestionBoxException ex) {
            String message = IfaCommonUtil.getMessage(ex.getMessageKey(), new String[] { ex.getMessageParam() });
            res = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ex.getMessageKey(), message);
        }
        return res;
    }
}
