package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA003ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA003ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA004ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterA004ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0401_02-01", screenNumber = "")
public class IfaSelfInspectBlotterController extends BaseController {

    /** 理由必須フラグ.理由不要 */
    private static final String REASON_REQUIRED_FLAG_NOT_REQUIRED = "0";

    /** 理由必須フラグ.理由必須 */
    private static final String REASON_REQUIRED_FLAG_REQUIRED = "1";

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
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSelfInspectBlotterA001ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterA001ApiResponse
     * Dtoリクエスト：IfaSelfInspectBlotterA001RequestDto
     * Dtoレスポンス：IfaSelfInspectBlotterA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaSelfInspectBlotterA001ApiRequest apiReq) throws Exception {
        
        IfaSelfInspectBlotterA001RequestDto appReq = new IfaSelfInspectBlotterA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSelfInspectBlotterA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSelfInspectBlotterService",
                "initializeA001", new TypeReference<DataList<IfaSelfInspectBlotterA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterA001ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterDisplayYearMonthChangeA002
    * アクションID：A002
    * アクション名：表示年月変更
    * APIリクエスト：IfaSelfInspectBlotterA002ApiRequest
    * Apiレスポンス：IfaSelfInspectBlotterA002ApiResponse
    * Dtoリクエスト：IfaSelfInspectBlotterA002RequestDto
    * Dtoレスポンス：IfaSelfInspectBlotterA002ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterDisplayYearMonthChangeA002")
    public String displayYearMonthChangeA002(@RequestBody IfaSelfInspectBlotterA002ApiRequest apiReq) throws Exception {
        
        IfaSelfInspectBlotterA002RequestDto appReq = new IfaSelfInspectBlotterA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSelfInspectBlotterA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSelfInspectBlotterService",
                "displayYearMonthChangeA002", new TypeReference<DataList<IfaSelfInspectBlotterA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterA002ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterTopTransitionA003
     * アクションID：A003
     * アクション名：TOP遷移
     * APIリクエスト：IfaSelfInspectBlotterA003ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterA003ApiResponse
     * Dtoリクエスト：IfaSelfInspectBlotterA003RequestDto
     * Dtoレスポンス：IfaSelfInspectBlotterA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterTopTransitionA003")
    public String topTransitionA003(@RequestBody IfaSelfInspectBlotterA003ApiRequest apiReq) throws Exception {
        
        IfaSelfInspectBlotterA003RequestDto appReq = new IfaSelfInspectBlotterA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSelfInspectBlotterA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSelfInspectBlotterService",
                "topTransitionA003", new TypeReference<DataList<IfaSelfInspectBlotterA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterA003ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterRegistrationConfirmA004
     * アクションID：A004
     * アクション名：TOP遷移
     * APIリクエスト：IfaSelfInspectBlotterA004ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterA004ApiResponse
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterRegistrationConfirmA004")
    public String confirmA004(@RequestBody IfaSelfInspectBlotterA004ApiRequest apiReq) throws Exception {
        
        // 必須入力チェック：理由必須フラグ ＝ "0"（理由不要）で確認がブランク
        boolean hasConfirmationRequiredError = apiReq.getSelfAssessmentList().stream()
                .anyMatch(val -> 
                        StringUtils.equals(val.getReasonRequiredFlag(), REASON_REQUIRED_FLAG_NOT_REQUIRED)
                        && StringUtils.isEmpty(val.getConfirmation())
                );

        if (hasConfirmationRequiredError) {
            DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_REQUIRED,
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] {"確認"})
            );
            return jc.toString(apiRes);
        }

        // 必須入力チェック：理由必須フラグ ＝ "1"（理由必須）で回答理由がブランク
        boolean hasAnswerRequiredError = apiReq.getSelfAssessmentList().stream()
                .anyMatch(val -> 
                        StringUtils.equals(val.getReasonRequiredFlag(), REASON_REQUIRED_FLAG_REQUIRED)
                        && StringUtils.isEmpty(val.getAnswerReason())
                );

        if (hasAnswerRequiredError) {
            DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_REQUIRED,
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] {"回答理由"})
            );
            return jc.toString(apiRes);
        }

        // 環境依存文字チェック
        String concatedAnswers = apiReq.getSelfAssessmentList().stream()
                .map(val -> val.getAnswerReason())
                .filter(val -> !StringUtils.isEmpty(val))
                .collect(Collectors.joining(","));

        String errorUtf8Str = UnicodeCheckUtil.getErrorUtf8Str(concatedAnswers);

        if (StringUtils.isNotEmpty(errorUtf8Str)) {
            DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_SPECIAL_WORDS,
                    IfaCommonUtil.getMessage(ERRORS_SPECIAL_WORDS, new String[] {"回答理由", errorUtf8Str})
            );
            return jc.toString(apiRes);
        }

        // 入力形式チェック：回答理由が数字のみ
        boolean isAllNumeric = apiReq.getSelfAssessmentList().stream()
                .anyMatch(assessment -> isNumeric(assessment.getAnswerReason()));

        if (isAllNumeric) {
            DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] {"回答理由"})
            );
            return jc.toString(apiRes);
        }

        // 入力形式チェック：文字桁数超過入力の場合
        boolean isLongAnswerReason = apiReq.getSelfAssessmentList().stream()
        .anyMatch(assessment -> isOverLength(assessment.getAnswerReason()));

        if (isLongAnswerReason) {
            DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_SIZE,
                    IfaCommonUtil.getMessage(ERRORS_SIZE, new String[] {"回答理由", "1", "127"})
            );
            return jc.toString(apiRes);
        }

        // 正常終了
        DataList<IfaSelfInspectBlotterA004ApiResponse> apiRes = IfaCommonUtil.createDataList(
                new ArrayList<IfaSelfInspectBlotterA004ApiResponse>(),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );
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
