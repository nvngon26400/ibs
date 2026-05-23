package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalRegisterDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006bRequestDto;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxPersonalRegisterService;
import com.sbibits.earth.util.StringUtil;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項 詳細
 *
 * @author SCSK神木
 * 2025/06/19 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxPersonalRegisterService")
public class IfaSuggestionBoxPersonalRegisterServiceImpL implements IfaSuggestionBoxPersonalRegisterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalRegisterServiceImpL.class);

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
    private IfaSuggestionBoxPersonalRegisterDao dao;


    /**
    * アクションID：A006
    * アクション名：登録
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    public DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> selectA006a(IfaSuggestionBoxPersonalRegisterA006aRequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalRegisterServiceImplL.initializeA001");}

        DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> res = new DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel>();

        res = dao.selectIfaSuggestionBoxPersonalRegisterSql001();

        return res;

    }


    /**
    * アクションID：A006b
    * アクション名：登録
    *
    * @param dtoReq リクエスト
    * @return {@code DataList<String> }
    * @throws Exception システムエラー
    */
    @Transactional(rollbackFor = Throwable.class)
    public DataList<String> insertA006b (IfaSuggestionBoxPersonalRegisterA006bRequestDto dtoReq) throws Exception {

    	DataList<String> res = new DataList<String>();


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalRegisterServiceImplL.registerA006b");}

        // ユーザ共通情報を取得
        String brokerId = IfaCommonUtil.getUserAccount().getBrokerId();
        String intermediaryEmpCd = IfaCommonUtil.getUserAccount().getEmployeeId();
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        // リクエストパラメータを設定
        String sbpNo = dtoReq.getSbpNo();
        String title = dtoReq.getTitle();
        String category = dtoReq.getCategory();
        String suggestion = dtoReq.getSuggestion();

        // 値ありなら添付ファイル(リネーム後ファイル名)を設定
        String registerFileName1 = StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName1()) ? null : dtoReq.getRegisterFileName1();
        String registerFileName2 = StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName2()) ? null : dtoReq.getRegisterFileName2();
        String registerFileName3 = StringUtil.isNullOrEmpty(dtoReq.getRegisterFileName3()) ? null : dtoReq.getRegisterFileName3();

        // 引数を設定
        IfaSuggestionBoxPersonalRegisterSql002RequestModel inSql002Req = new IfaSuggestionBoxPersonalRegisterSql002RequestModel();
        inSql002Req.setSbpNo(sbpNo);
        inSql002Req.setTitle(title);
        inSql002Req.setCategory(category);
        inSql002Req.setSuggestion(suggestion);
        inSql002Req.setRegisterFileName1(registerFileName1);
        inSql002Req.setRegisterFileName2(registerFileName2);
        inSql002Req.setRegisterFileName3(registerFileName3);
        inSql002Req.setBrokerCode(brokerId);
        inSql002Req.setIntermediaryEmpCd(intermediaryEmpCd);
        inSql002Req.setUserId(userId);

        try {
            dao.insertIfaSuggestionBoxPersonalRegisterSql002(inSql002Req);
        } catch (Exception e) {
        	res = IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    ERRORS_PROCESSINGFAILED,
                    REGISTERED_ERROR);
            return res;
        }

        res = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);

        return res;
    }

}
