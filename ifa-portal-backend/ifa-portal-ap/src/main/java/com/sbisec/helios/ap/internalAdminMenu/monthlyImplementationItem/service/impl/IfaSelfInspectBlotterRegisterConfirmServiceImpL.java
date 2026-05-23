package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.annotation.dao.CordysTransactional;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaSelfInspectBlotterRegisterConfirmDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmSelfAssessment;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.IfaSelfInspectBlotterRegisterConfirmService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util.IfaSelfInspectBlotterUtil;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@Component(value = "cmpIfaSelfInspectBlotterRegisterConfirmService")
public class IfaSelfInspectBlotterRegisterConfirmServiceImpL implements IfaSelfInspectBlotterRegisterConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSelfInspectBlotterRegisterConfirmServiceImpL.class);
    
    /** 回答理由：正答 */
    private static final String ANSWER_REASON_CORRECT = "1";
    
    /** 回答理由：誤答 */
    private static final String ANSWER_REASON_INCORRECT = "0";
    
    /**ReasonRequiredFlag */
    private static final String REASON_REQUIRED_FLAG_NOT_REQUIRED = "0";
    
    /** 回答回数1 */
    private static final String ANSWER_COUNT_1 = "1";
    
    /** リマインド自己点検 */
    private static final String REMINDER_SELFCHECK = "REMINDER_SELFCHECK";
    
    /** リマインド自己点検開始日付 */
    private static final String REMINDER_SELFCHECK_START_DATE = "REMINDER_SELFCHECK_START_DATE";
    
    /** エラーメッセージID:処理失敗 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** エラーメッセージ用パラメータ */
    private static final String ERRORS_MESSAGE_PARAM = "自己点検記録簿の登録";
    
    /** INFOメッセージ：登録成功 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";
    
    /** INFOメッセージ：更新成功 */
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";
    
    /** INFOメッセージ用パラメータ */
    private static final String INFO_MESSAGE_PARAM = "自己点検項目";
    
    @Autowired
    private IfaSelfInspectBlotterRegisterConfirmDao dao;
    
    @Autowired
    private IfaSelfInspectBlotterUtil util;
    
    @Autowired
    private IfaDateUtil dateUtil;
    
    /**
     * アクションID：A003
     * アクション名：登録
     * Dto リクエスト：IfaSelfInspectBlotterRegisterConfirmA003RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterRegisterConfirmA003ResponseDto
     * model リクエスト：IfaSelfInspectBlotterRegisterConfirmSql001RequestModel
     * model レスポンス：IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @CordysTransactional
    public DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto> registerA003(
            IfaSelfInspectBlotterRegisterConfirmA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectBlotterRegisterConfirmServiceImplL.registerA003");
        }
        
        // 返却用メッセージID
        String messageId = "";
        

        // 自己点検リストに以下の項目を編集し追加する。
        boolean isFirst = true; // 1件目判定用フラグ
        Integer nextSelfCheckId = null; // シーケンス
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String brokerCode = util.editBrokerCode(userAccount);
        
        List<IfaSelfInspectBlotterRegisterConfirmSelfAssessment> selfAssessmentList = dtoReq.getSelfAssessmentList();
        for (IfaSelfInspectBlotterRegisterConfirmSelfAssessment selfAssessment : selfAssessmentList) {
            
            // 自己点検リスト.回答結果（自己点検リスト.理由必須フラグ ＝ "0"（理由不要）のみ実施）
            if (REASON_REQUIRED_FLAG_NOT_REQUIRED.equals(selfAssessment.getReasonRequiredFlag())) {
                if (Objects.equals(selfAssessment.getAnswer(), selfAssessment.getConfirmation())) {
                    // 確認 = 回答の場合：回答結果に"1"（正答）を設定する。
                    selfAssessment.setAnswerResult(ANSWER_REASON_CORRECT);
                } else {
                    // 確認 ≠ 回答の場合：回答結果に"0"（誤答）を設定する。
                    selfAssessment.setAnswerResult(ANSWER_REASON_INCORRECT);
                }
            }
            
            // 自己点検リスト.回答回数
            if (StringUtil.isNullOrEmpty(selfAssessment.getAnswerCount())) {
                // 回答回数 ＝ """"の場合、回答回数に"１"を設定する。
                selfAssessment.setAnswerCount(ANSWER_COUNT_1);
                
                // １件目の場合自己点検テーブルにデータを追加する。
                if (isFirst) {
                    // シーケンスを取得する。（新規登録時のみシーケンスを取得する）
                    DataList<IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel> selSql001Resp = dao
                            .selectIfaSelfInspectBlotterRegisterConfirmSql001();
                    nextSelfCheckId = selSql001Resp.getDataList().get(0).getNextSelfCheckId();
                    
                    IfaSelfInspectBlotterRegisterConfirmSql002RequestModel insSql002Req = new IfaSelfInspectBlotterRegisterConfirmSql002RequestModel();
                    insSql002Req.setNextSelfCheckId(nextSelfCheckId);
                    insSql002Req.setRegisterDate(dtoReq.getRegisterDate());
                    insSql002Req.setUserId(userAccount.getUserId());
                    insSql002Req.setBrokerCode(brokerCode);
                    try {
                        int insSql002Resp = dao.insertIfaSelfInspectBlotterRegisterConfirmSql002(insSql002Req);
                        if (insSql002Resp != 1) {
                            // 異常終了：エラーメッセージを設定し、処理終了する。
                            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                    ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                            new String[] { ERRORS_MESSAGE_PARAM }));
                        }
                    } catch (Exception e) {
                        return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                        new String[] { ERRORS_MESSAGE_PARAM }));
                        
                    }
                    isFirst = false;
                }
                
                // 自己点検確認テーブルのデータを登録する（INSERT）。
                IfaSelfInspectBlotterRegisterConfirmSql003RequestModel insSql003Req = new IfaSelfInspectBlotterRegisterConfirmSql003RequestModel();
                insSql003Req.setNextSelfCheckId(nextSelfCheckId);
                insSql003Req.setSelfCheckItemId(Integer.parseInt(selfAssessment.getSelfCheckItemId()));
                insSql003Req.setConfirmation(selfAssessment.getConfirmation());
                insSql003Req.setAnswerReason(selfAssessment.getAnswerReason());
                insSql003Req.setAnswerCount(Integer.parseInt(selfAssessment.getAnswerCount()));
                insSql003Req.setAnswerResult(selfAssessment.getAnswerResult());
                insSql003Req.setUserId(userAccount.getUserId());
                try {
                    int insSql003Resp = dao.insertIfaSelfInspectBlotterRegisterConfirmSql003(insSql003Req);
                    if (insSql003Resp != 1) {
                        // 異常終了：エラーメッセージを設定し、処理終了する。
                        return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                        new String[] { ERRORS_MESSAGE_PARAM }));
                    }
                } catch (Exception e) {
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                            ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                    new String[] { ERRORS_MESSAGE_PARAM }));
                    
                }
                
                messageId = INFO_INSERT_COMPLETED;
                
            } else {
                // 回答回数 ＝ 値ありの場合、回答回数に回答回数 + "1"を設定する。
                int answerCount = Integer.parseInt(selfAssessment.getAnswerCount());
                answerCount = answerCount + 1;
                selfAssessment.setAnswerCount(Integer.toString(answerCount));
                
                // 自己点検確認テーブルのデータを更新する（UPDATE）。
                IfaSelfInspectBlotterRegisterConfirmSql004RequestModel updSql004Req = new IfaSelfInspectBlotterRegisterConfirmSql004RequestModel();
                updSql004Req.setSelfCheckItemId(Integer.parseInt(selfAssessment.getSelfCheckItemId()));
                updSql004Req.setRegisterDate(dtoReq.getRegisterDate());
                updSql004Req.setBrokerCode(brokerCode);
                updSql004Req.setAnswerReason(selfAssessment.getAnswerReason());
                updSql004Req.setAnswerCount(Integer.parseInt(selfAssessment.getAnswerCount()));
                updSql004Req.setUserId(userAccount.getUserId());
                try {
                    int updSql004Resp = dao.updateIfaSelfInspectBlotterRegisterConfirmSql004(updSql004Req);
                    if (updSql004Resp != 1) {
                        // 異常終了：エラーメッセージを設定し、処理終了する。
                        return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                        new String[] { ERRORS_MESSAGE_PARAM }));
                    }
                } catch (Exception e) {
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                            ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                    new String[] { ERRORS_MESSAGE_PARAM }));
                    
                }
                
                messageId = INFO_UPDATE_COMPLETED;
            }
        }
        
        // 自己点検記録未読flgを設定する。
        // リマインド自己点検を取得する。
        Boolean userNeedsToReadMonthlySelfcheck = null;
        String reminderSelfcheck = callSql005(REMINDER_SELFCHECK);
        String reminderSelfcheckStartDate = callSql005(REMINDER_SELFCHECK_START_DATE);
        
        // ユーザ共通情報.権限コードがリマインド自己点検に存在しない場合、自己点検記録未読flgにfalseを設定する。
        if (reminderSelfcheck != null && !reminderSelfcheck.contains(userAccount.getPrivId())) {
            userNeedsToReadMonthlySelfcheck = false;
        }
        
        // システム日付（DD） < リマインド自己点検開始日付の場合、自己点検記録未読flgにfalseを設定する。
        String systemDateDd = dateUtil.format("DD");
        if (Integer.parseInt(systemDateDd) < Integer.parseInt(reminderSelfcheckStartDate)) {
            userNeedsToReadMonthlySelfcheck = false;
        }
        
        // 今月未読通信件数を取得する。
        IfaSelfInspectBlotterRegisterConfirmSql006RequestModel selSql006Req = new IfaSelfInspectBlotterRegisterConfirmSql006RequestModel();
        selSql006Req.setBrokerCode(userAccount.getBrokerCode());
        DataList<IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel> selSql006Resp = dao
                .selectIfaSelfInspectBlotterRegisterConfirmSql006(selSql006Req);
        
        if (selSql006Resp != null && !CollectionUtils.isEmpty(selSql006Resp.getDataList())
                && Integer.parseInt(selSql006Resp.getDataList().get(0).getRowCount()) > 0) {
            // 件数 > 0の場合、自己点検記録未読flgにtrueを設定する。
            userNeedsToReadMonthlySelfcheck = true;
        } else {
            // 上記以外の場合、自己点検記録未読flgにfalseを設定する。
            userNeedsToReadMonthlySelfcheck = false;
        }
        
        // レスポンス
        IfaSelfInspectBlotterRegisterConfirmA003ResponseDto dtoResInner = new IfaSelfInspectBlotterRegisterConfirmA003ResponseDto();
        dtoResInner.setRegisterDate(dtoReq.getRegisterDate());
        dtoResInner.setSelfAssessmentList(selfAssessmentList);
        dtoResInner.setUserNeedsToReadMonthlySelfcheck(userNeedsToReadMonthlySelfcheck);
        
        List<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto> dtoResInnerList = new ArrayList<>();
        dtoResInnerList.add(dtoResInner);
        
        DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto> dtoRes = new DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto>();
        dtoRes = IfaCommonUtil.createDataList(dtoResInnerList, ErrorLevel.INFO, messageId,
                IfaCommonUtil.getMessage(messageId, new String[] { INFO_MESSAGE_PARAM }));
        
        return dtoRes;
    }
    
    /**
     * SQL005を呼び出し、仲介業システム値（TB_MED_SYSTEM_VARIABLE）テーブルの情報を取得する。
     *
     * @param key キー
     * @return 値
     * @throws Exception 例外
     */
    private String callSql005(String key) throws Exception {
        
        IfaSelfInspectBlotterRegisterConfirmSql005RequestModel selSql005Req = new IfaSelfInspectBlotterRegisterConfirmSql005RequestModel();
        selSql005Req.setVarName(key);
        DataList<IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel> selSql005Resp = dao
                .selectIfaSelfInspectBlotterRegisterConfirmSql005(selSql005Req);
        return selSql005Resp.getDataList().get(0).getVarValue();
        
    }
    
}
