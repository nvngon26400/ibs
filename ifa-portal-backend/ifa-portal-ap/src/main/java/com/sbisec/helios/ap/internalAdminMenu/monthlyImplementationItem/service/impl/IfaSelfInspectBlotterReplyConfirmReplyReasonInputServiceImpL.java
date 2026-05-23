package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.annotation.dao.CordysTransactional;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaSelfInspectBlotterReplyConfirmReplyReasonInputDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.IfaSelfInspectBlotterReplyConfirmReplyReasonInputService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util.IfaSelfInspectBlotterUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Component(value = "cmpIfaSelfInspectBlotterReplyConfirmReplyReasonInputService")
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputServiceImpL
        implements IfaSelfInspectBlotterReplyConfirmReplyReasonInputService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaSelfInspectBlotterReplyConfirmReplyReasonInputServiceImpL.class);
    
    /** 回答理由：誤答 */
    private static final String ANSWER_REASON_INCORRECT = "0";
    
    /** エラーメッセージID:処理失敗 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** エラーメッセージ用パラメータ */
    private static final String ERRORS_MESSAGE_PARAM = "自己点検記録簿の登録";
    
    /** INFOメッセージ：登録成功 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";
    
    /** INFOメッセージ用パラメータ */
    private static final String INFO_MESSAGE_PARAM = "自己点検項目";
    
    @Autowired
    private IfaSelfInspectBlotterReplyConfirmReplyReasonInputDao dao;
    
    @Autowired
    private IfaSelfInspectBlotterUtil util;
    
    /**
     * アクションID：A002
     * アクション名：完了
     * Dto リクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto
     * model リクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel
     * model レスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @CordysTransactional
    public DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto> completeA002(
            IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectBlotterReplyConfirmReplyReasonInputServiceImplL.completeA002");
        }
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        String brokerCode = util.editBrokerCode(userAccount);
        
        // 回答結果と回答理由をチェックを行う。
        for (IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment selfAssessment : dtoReq
                .getSelfAssessmentList()) {
            if (ANSWER_REASON_INCORRECT.equals(selfAssessment.getAnswerResult())
                    && !StringUtil.isNullOrEmpty(selfAssessment.getAnswerReason())) {
                // 回答結果 = "0"（誤答） かつ 回答理由 = ブランク以外：自己点検確認テーブルのデータを更新する。
                IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel updSql001Req = new IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel();
                updSql001Req.setSelfCheckItemId(Integer.valueOf(selfAssessment.getSelfCheckItemId()));
                updSql001Req.setRegisterDate(dtoReq.getRegisterDate());
                updSql001Req.setBrokerCode(brokerCode);
                updSql001Req.setAnswerReason(selfAssessment.getAnswerReason());
                updSql001Req.setUserId(userId);
                
                try {
                    int updSql001Resp = dao.updateIfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001(updSql001Req);
                    
                    if (updSql001Resp != 1) {
                        // 異常終了：エラーメッセージを設定する。
                        return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                        new String[] { ERRORS_MESSAGE_PARAM }));
                    }
                } catch (Exception e) {
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                                ERRORS_PROCESSING_FAILED, IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED,
                                        new String[] { ERRORS_MESSAGE_PARAM }));
                }
            }
        }
        
        // レスポンス
        DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto> dtoRes = new DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto>();
        List<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto> dtoResInnerList = new ArrayList<>();
        
        dtoRes = IfaCommonUtil.createDataList(dtoResInnerList, ErrorLevel.INFO, INFO_INSERT_COMPLETED,
                IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { INFO_MESSAGE_PARAM }));
        
        return dtoRes;
    }
    
}
