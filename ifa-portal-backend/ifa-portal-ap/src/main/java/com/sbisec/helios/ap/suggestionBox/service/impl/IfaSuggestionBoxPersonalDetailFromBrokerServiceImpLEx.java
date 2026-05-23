package com.sbisec.helios.ap.suggestionBox.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDetailFromBrokerDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxException;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * トランザクション分割のためExファイルを定義
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Component(value = "cmpIfaSuggestionBoxPersonalDetailFromBrokerServiceEx")
public class IfaSuggestionBoxPersonalDetailFromBrokerServiceImpLEx {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalDetailFromBrokerServiceImpLEx.class);

    // --------------------------------
    // メッセージ
    // --------------------------------
    /** {0}を登録しました。 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";

    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";

    /** エラーメッセージ用パラメータ 回答修正 */
    private static final String MESSAGE_PARAM_ANS_UPDATE = "回答内容の修正";

    /** エラーメッセージ用パラメータ 回答登録 */
    private static final String MESSAGE_PARAM_ANS_INSERT = "回答内容の登録";

    /** エラーメッセージ用パラメータ 要望更新 */
    private static final String MESSAGE_PARAM_SUG_UPDATE = "ステータスおよび回答更新日時の更新";

    /** メッセージ用パラメータ 登録内容 */
    private static final String MESSAGE_PARAM_REGISTER = "ステータスおよび回答内容";

    // --------------------------------
    // 変数定義
    // --------------------------------
    /** 確定フラグ：確定 */
    private static final String CONFIRMED = "1";

    @Autowired
    private IfaSuggestionBoxPersonalDetailFromBrokerDao dao;

    /**
     * アクションID：A006
     * アクション名：登録 実処理
     * Dto リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest
     * Dto レスポンス：String
     * model リクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return res レスポンス
     * @exception Exception システムエラー
     * @see <reference item>
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public DataList<String> registerA006(
            IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest dtoReq
        ) throws IfaSuggestionBoxException, Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalDetailFromBrokerServiceImpLEx.registerA006");
        }

        DataList<String> res = new DataList<String>();
        // DB操作件数
        int count = 0;
        // 要望No
        String sbpNo = dtoReq.getSbpNo();
        // ユーザーID
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        // 登録済回答一覧の更新
        IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel updSql003Req = new IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel();
        List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer> registeredAnswerList = dtoReq.getRegisteredAnswerList();
        updSql003Req.setSbpNo(sbpNo);
        updSql003Req.setUserId(userId);
        for (IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestRegisteredAnswer answer : registeredAnswerList) {
            // 変更のある回答のみ更新する
            if (!StringUtils.equals(answer.getAnswerContents(), answer.getAnswerContentsBeforeCorrect())){
                updSql003Req.setAnswerContents(answer.getAnswerContents());
                updSql003Req.setAnswerNo(answer.getAnswerNo());

                try {
                    count = dao.updateIfaSuggestionBoxPersonalDetailFromBrokerSql003(updSql003Req);
                } catch (Exception e) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_ANS_UPDATE);
                }

                // 更新件数が1件でない場合
                if (count != 1) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_ANS_UPDATE);
                }
            }
        }

        // 新規回答一覧の登録
        IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel insSql004Req = new IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel();
        List<IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer> newAnswerList = dtoReq.getNewAnswerList();
        insSql004Req.setSbpNo(sbpNo);
        insSql004Req.setUserId(userId);
        for (IfaSuggestionBoxPersonalDetailFromBrokerDtoRequestNewAnswer answer : newAnswerList) {
            // 回答内容が空でない かつ 確定フラグが1 の回答のみ登録する
            if (!StringUtil.isNullOrEmpty(answer.getAnswerContents()) && CONFIRMED.equals(answer.getAnswerConfirmFlg())){
                insSql004Req.setAnswerContents(answer.getAnswerContents());

                try {
                    count = dao.insertIfaSuggestionBoxPersonalDetailFromBrokerSql004(insSql004Req);
                } catch (Exception e) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_ANS_INSERT);
                }

                // 登録件数が1件でない場合
                if (count != 1) {
                    throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_ANS_INSERT);
                }
            }
        }

        // 要望の更新
        IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel updSql005Req = new IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel();
        updSql005Req.setSbpNo(sbpNo);
        updSql005Req.setStatus(dtoReq.getStatus());
        updSql005Req.setUserId(userId);

        try {
            count = dao.updateIfaSuggestionBoxPersonalDetailFromBrokerSql005(updSql005Req);
        } catch (Exception e) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_SUG_UPDATE);
        }

        // 更新件数が1件でない場合
        if (count != 1) {
            throw new IfaSuggestionBoxException(ERRORS_PROCESSING_FAILED, MESSAGE_PARAM_SUG_UPDATE);
        }

        // 登録完了
        String message = IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { MESSAGE_PARAM_REGISTER });
        res = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, INFO_INSERT_COMPLETED, message);

        return res;
    }
}
