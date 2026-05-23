package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDetailDao;

import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql006RequestModel;

import org.apache.commons.beanutils.BeanUtils;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA007bRequestDto;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxPersonalDetailService;
import com.sbibits.earth.util.StringUtil;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項 詳細
 *
 * @author SCSK神木
 * 2025/06/19 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxPersonalDetailService")
public class IfaSuggestionBoxPersonalDetailServiceImpL implements IfaSuggestionBoxPersonalDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalDetailServiceImpL.class);

    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 正常終了 */
    private static final String SUCCESS_MESSAGE = "正常終了";

    /** エラーメッセージ: {0}が失敗しました。" */
    private static final String ERRORS_PROCESSINGFAILED= "errors.processingFailed";

    // --------------------------------
    // 変数定義
    // --------------------------------  
    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "あなたの要望の登録";


    @Autowired
    private IfaSuggestionBoxPersonalDetailDao dao;


    /**
    * アクションID：A001
    * アクション名：初期化
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto> initializeA001 (IfaSuggestionBoxPersonalDetailA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalDetailServiceImplL.initializeA001");}

        DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto> dtoResDataList = new DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto>();
        List<IfaSuggestionBoxPersonalDetailA001ResponseDto> resDtoList = new ArrayList<IfaSuggestionBoxPersonalDetailA001ResponseDto>();
        IfaSuggestionBoxPersonalDetailA001ResponseDto responseDto = new IfaSuggestionBoxPersonalDetailA001ResponseDto();


        // ユーザ共通情報を取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();


        // SQL001 要望取得
        IfaSuggestionBoxPersonalDetailSql001RequestModel selSql001Req = new IfaSuggestionBoxPersonalDetailSql001RequestModel();
        selSql001Req.setSbpNo(dtoReq.getSbpNo());

        List<IfaSuggestionBoxPersonalDetailSql001ResponseModel> selSql001Res = dao
                .selectIfaSuggestionBoxPersonalDetailSql001(selSql001Req).getDataList();


        // SQL002 回答取得
        IfaSuggestionBoxPersonalDetailSql002RequestModel selSql002Req = new IfaSuggestionBoxPersonalDetailSql002RequestModel();
        selSql002Req.setSbpNo(dtoReq.getSbpNo());

        List<IfaSuggestionBoxPersonalDetailSql002ResponseModel> selSql002Res = dao
                .selectIfaSuggestionBoxPersonalDetailSql002(selSql002Req).getDataList();


        // SQL003 回答既読更新
        IfaSuggestionBoxPersonalDetailSql003RequestModel upSql003Req = new IfaSuggestionBoxPersonalDetailSql003RequestModel();
        upSql003Req.setSbpNo(dtoReq.getSbpNo());
        upSql003Req.setUserId(userId);

        dao.updateIfaSuggestionBoxPersonalDetailSql003(upSql003Req);


        // SQL004 未読件数取得
        int sugBoxUnreadItems = getSugBoxUnreadItems(userId);


        // SQL001の結果をレスポンスDtoに格納
        IfaSuggestionBoxPersonalDetailSql001ResponseModel sql001Res = selSql001Res.get(0);

        responseDto.setSbpNo(sql001Res.getSbpNo());
        responseDto.setTitle(sql001Res.getTitle());
        responseDto.setCategory(sql001Res.getCategory());
        responseDto.setStatus(sql001Res.getStatus());
        responseDto.setSuggestion(sql001Res.getSuggestion());
        responseDto.setRegisteredAttachFile1(sql001Res.getRegisteredAttachFile1());
        responseDto.setRegisteredAttachFile2(sql001Res.getRegisteredAttachFile2());
        responseDto.setRegisteredAttachFile3(sql001Res.getRegisteredAttachFile3());

        // SQL002の結果をレスポンスDto.回答一覧に格納
        List<IfaSuggestionBoxPersonalDetailA001ResponseDto.answerList> answerListList = new ArrayList<>();

        for(IfaSuggestionBoxPersonalDetailSql002ResponseModel sql002Res : selSql002Res) {
        	IfaSuggestionBoxPersonalDetailA001ResponseDto.answerList answerList = new IfaSuggestionBoxPersonalDetailA001ResponseDto.answerList();
            answerList.setAnswerNo(sql002Res.getAnswerNo());
            answerList.setCreateTime(sql002Res.getCreateTime());
            answerList.setAnswerUpdateTime(sql002Res.getAnswerUpdateTime());
            answerList.setAnswerContents(sql002Res.getAnswerContents());
            answerListList.add(answerList);
        }
        responseDto.setAnswerList(answerListList);

        responseDto.setSugBoxUnreadItems(sugBoxUnreadItems);

        // ResponseDTOListへResponseDTOを格納
        resDtoList.add(responseDto);

        // 結果をDataListに設定する
        dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        return dtoResDataList;
    }


    /**
    * アクションID：A007b
    * アクション名：登録
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    @Transactional(rollbackFor = Throwable.class)
    public DataList<String> registerA007b (IfaSuggestionBoxPersonalDetailA007bRequestDto dtoReq) throws Exception {

        DataList<String> dtoResDataList = new DataList<String>();


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalDetailServiceImplL.registerA007b");}

        // ユーザ共通情報を取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        // SQL006の引数を設定
        IfaSuggestionBoxPersonalDetailSql006RequestModel upSql006Req = new IfaSuggestionBoxPersonalDetailSql006RequestModel();
        upSql006Req.setUserId(userId);
        upSql006Req.setSbpNo(dtoReq.getSbpNo());
        upSql006Req.setTitle(dtoReq.getTitle());
        upSql006Req.setCategory(dtoReq.getCategory());
        upSql006Req.setSuggestion(dtoReq.getSuggestion());
        upSql006Req.setAttachFile1(StringUtil.emptyToNull(dtoReq.getRegisterFileName1()));
        upSql006Req.setAttachFile2(StringUtil.emptyToNull(dtoReq.getRegisterFileName2()));
        upSql006Req.setAttachFile3(StringUtil.emptyToNull(dtoReq.getRegisterFileName3()));
        upSql006Req.setRegisteredAttachFile1DeleteFlag(dtoReq.getRegisteredAttachFile1DeleteFlag());
        upSql006Req.setRegisteredAttachFile2DeleteFlag(dtoReq.getRegisteredAttachFile2DeleteFlag());
        upSql006Req.setRegisteredAttachFile3DeleteFlag(dtoReq.getRegisteredAttachFile3DeleteFlag());

        int upRes = dao.updateIfaSuggestionBoxPersonalDetailSql006(upSql006Req);
        
        if(upRes == 0) {
            // 更新件数が0件の場合、エラーレベルにFATALを設定（エラーメッセージはController側でセットする）
            dtoResDataList = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, null, null);
        } else {
            dtoResDataList = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        }

        return dtoResDataList;
    }

    /**
     * 未読回答のある要望件数取得メソッド。
     *
     * @param userId ユーザID
     * @return 目安箱未読件数
     * @throws Exception 例外
     */
    @Override
    public Integer getSugBoxUnreadItems(String userId) throws Exception {

        LOGGER.debug("IfaSuggestionBoxPersonalDetailServiceImpl.getSugBoxUnreadItems:[{}]", userId);

        IfaSuggestionBoxPersonalDetailSql004RequestModel selSql004Req = new IfaSuggestionBoxPersonalDetailSql004RequestModel();
        selSql004Req.setUserId(userId);

        Integer sugBoxUnreadItemsRes = dao.selectIfaSuggestionBoxPersonalDetailSql004(selSql004Req);
        int sugBoxUnreadItems = (sugBoxUnreadItemsRes != null) ? sugBoxUnreadItemsRes : 0;

        return sugBoxUnreadItems;
    }
}
