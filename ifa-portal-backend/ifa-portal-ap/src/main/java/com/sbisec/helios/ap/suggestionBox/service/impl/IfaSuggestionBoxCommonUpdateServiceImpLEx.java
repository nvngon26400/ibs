package com.sbisec.helios.ap.suggestionBox.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonUpdateDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql005RequestModel;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA009bRequestDto;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 * トランザクション分割のためExファイルを定義
 *
 2025/06/23 新規作成
 */

@Component(value = "cmpIfaSuggestionBoxCommonUpdateServiceEx")
public class IfaSuggestionBoxCommonUpdateServiceImpLEx {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonUpdateServiceImpL.class);

    // --------------------------------
    // 固定値
    // --------------------------------
    /** 確定フラグ：確定 */
    private static final String CONFIRM = "1";

    /** 確定フラグ：既存 */
    private static final String EXIST = "2";


    // --------------------------------
    // メッセージ
    // --------------------------------
    /** {0}を更新しました。 */
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";

    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "要望の更新";

    /** メッセージ用パラメータ 登録内容 */
    private static final String MESSAGE_PARAM_REGISTER = "皆様からの要望";


    @Autowired
    private IfaSuggestionBoxCommonUpdateDao dao;

    /**
     * A009b 更新 実処理
     *
     * @param req リクエストDTO
     * @return res レスポンス
     * @exception IfaSuggestionBoxException, Exception
     * @see <reference item>
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<String> updateA009bEx(
            IfaSuggestionBoxCommonUpdateA009bRequestDto dtoReq
        ) throws IfaSuggestionBoxException, Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxCommonUpdateServiceImpL.updateA009bEx");
        }

        DataList<String> res = new DataList<String>();
        
        int count = 0; // DB操作件数
        String sbcNo = dtoReq.getSbcNo();  // 要望No
        String userId = IfaCommonUtil.getUserAccount().getUserId(); // ユーザーID

		// --------------------------------
        // SQL003.要望更新
        // --------------------------------
        IfaSuggestionBoxCommonUpdateSql003RequestModel updSql003Req = new IfaSuggestionBoxCommonUpdateSql003RequestModel();
        BeanUtils.copyProperties(updSql003Req, dtoReq);
        updSql003Req.setUserId(userId); // ユーザ共通情報.ユーザーID

        try {
            count = dao.updateIfaSuggestionBoxCommonUpdateSql003(updSql003Req);
        } catch (Exception e) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
        }

        // 更新件数が1件でない場合
        if (count != 1) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
        }

		// --------------------------------
        // SQL004.回答更新
        // --------------------------------
        IfaSuggestionBoxCommonUpdateSql004RequestModel updSql004Req = new IfaSuggestionBoxCommonUpdateSql004RequestModel();
        
        for (IfaSuggestionBoxCommonUpdateA009bRequestDto.answerList answer : dtoReq.getAnswerList()) {
            // 回答確定フラグ=2(既存) かつ 回答一覧.回答内容 ≠ 回答一覧.回答内容(修正前) の場合、DBを更新する。
            if (EXIST.equals(answer.getAnswerConfirmFlg()) && !StringUtils.equals(answer.getAnswerContentsBeforeCorrect(), answer.getAnswerContents())) {
                BeanUtils.copyProperties(updSql004Req, answer); // 回答一覧
                updSql004Req.setSbcNo(sbcNo); // 皆様からの要望No
                updSql004Req.setUserId(userId); // ユーザ共通情報.ユーザーID

                try {
                    count = dao.updateIfaSuggestionBoxCommonUpdateSql004(updSql004Req);
                } catch (Exception e) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }
                
                // 更新件数が1件でない場合
                if (count != 1) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }
            }
        }

		// --------------------------------
        // SQL005.回答登録
        // --------------------------------
        IfaSuggestionBoxCommonUpdateSql005RequestModel insSql005Req = new IfaSuggestionBoxCommonUpdateSql005RequestModel();
        
        for (IfaSuggestionBoxCommonUpdateA009bRequestDto.answerList answer : dtoReq.getAnswerList()) {
            // 回答確定フラグ=1(確定) の回答を登録する。
            if (CONFIRM.equals(answer.getAnswerConfirmFlg())){
                BeanUtils.copyProperties(insSql005Req, answer); // 回答一覧
                insSql005Req.setSbcNo(sbcNo); // 皆様からの要望No
                insSql005Req.setUserId(userId); // ユーザ共通情報.ユーザーID

                try {
                    count = dao.insertIfaSuggestionBoxCommonUpdateSql005(insSql005Req);
                } catch (Exception e) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }

                // 登録件数が1件でない場合
                if (count != 1) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, REGISTERED_ERROR);
                }
            }
        }

        // 登録完了
        String message = IfaCommonUtil.getMessage(INFO_UPDATE_COMPLETED, new String[] { MESSAGE_PARAM_REGISTER });
        res = IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_UPDATE_COMPLETED, message);

        return res;
    }
}
