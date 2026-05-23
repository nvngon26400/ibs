package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessmentRequest;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0401_02-03", screenNumber = "")
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputController extends BaseController {
    
    /** 回答理由：誤答 */
    private static final String ANSWER_REASON_INCORRECT = "0";

    /** エラー.{0}を入力してください。 */
    private static final String ERRORS_REQUIRED = "errors.required";

    /** エラー.{0}は[{1}]を入力できません。ご確認ください。 */
    private static final String ERRORS_SPECIAL_WORDS = "errors.specialWords";

    /** エラー.{0}を正しく入力して下さい。 */
    private static final String ERRORS_ACCURATELY = "errors.accurately";

    /** エラー.{0}は{1}～{2}文字で入力してください。 */
    private static final String ERRORS_SIZE = "errors.size";

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterReplyConfirmReplyReasonInputCompleteA002
     * アクションID：A002
     * アクション名：完了
     * APIリクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiResponse
     * Dtoリクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto
     * Dtoレスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterReplyConfirmReplyReasonInputCompleteA002")
    public String completeA002(@RequestBody IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiRequest apiReq)
            throws Exception {
        
        IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto appReq = new IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto();
        
        // 必須入力チェック：『回答結果 = "0"（誤答）』で回答理由がブランク
        boolean hasAnswerReasonError = apiReq.getSelfAssessmentList().stream()
                .anyMatch(val -> 
                        StringUtils.equals(val.getAnswerResult(), ANSWER_REASON_INCORRECT)
                        && StringUtils.isEmpty(val.getAnswerReason())
                );

        if (hasAnswerReasonError) {
            return jc.toString(IfaCommonUtil.createDataList(
                    null,
                    ErrorLevel.FATAL,
                    ERRORS_REQUIRED,
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] {"回答理由"})
            ));
        }

        // 環境依存文字チェック
        String concatedAnswers = apiReq.getSelfAssessmentList().stream()
                .map(val -> val.getAnswerReason())
                .filter(val -> !StringUtils.isEmpty(val))
                .collect(Collectors.joining(","));

        String errorUtf8Str = UnicodeCheckUtil.getErrorUtf8Str(concatedAnswers);

        if (StringUtils.isNotEmpty(errorUtf8Str)) {
            return jc.toString(IfaCommonUtil.createDataList(
                    null,
                    ErrorLevel.FATAL,
                    ERRORS_SPECIAL_WORDS,
                    IfaCommonUtil.getMessage(ERRORS_SPECIAL_WORDS, new String[] {"回答理由", errorUtf8Str})
            ));
        }

        // 入力形式チェック：回答理由が数字のみ
        boolean isAllNumeric = apiReq.getSelfAssessmentList().stream()
                .anyMatch(assessment -> isNumeric(assessment.getAnswerReason()));

        if (isAllNumeric) {
            return jc.toString(IfaCommonUtil.createDataList(
                    null,
                    ErrorLevel.FATAL,
                    ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] {"回答理由"})
            ));
        }

        // 入力形式チェック：文字桁数超過入力の場合
        boolean isLongAnswerReason = apiReq.getSelfAssessmentList().stream()
        .anyMatch(assessment -> isOverLength(assessment.getAnswerReason()));

        if (isLongAnswerReason) {
            return jc.toString(IfaCommonUtil.createDataList(
                    null,
                    ErrorLevel.FATAL,
                    ERRORS_SIZE,
                    IfaCommonUtil.getMessage(ERRORS_SIZE, new String[] {"回答理由", "1", "127"})
            ));
        }

        // Beanコピー
        appReq.setRegisterDate(apiReq.getRegisterDate());
        List<IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment> appSelfAssessmentList = new ArrayList<>();
        for (IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessmentRequest apiSelfAssessment : apiReq
                .getSelfAssessmentList()) {
            IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment appSelfAssessment = new IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment();
            BeanUtils.copyProperties(appSelfAssessment, apiSelfAssessment);
            appSelfAssessmentList.add(appSelfAssessment);
        }
        appReq.setSelfAssessmentList(appSelfAssessmentList);
        
        DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectBlotterReplyConfirmReplyReasonInputService", "completeA002",
                new TypeReference<DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

         // 正規表現で数字のみで構成されているかチェック
        return Pattern.matches("^\\d+$", str); 
    }

    private boolean isOverLength(String str) {
        if (str == null) {
            return false;
        }
        return str.length() > 127;
    }

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
