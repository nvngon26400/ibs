package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaDateUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA009ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA010ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectItemManageA012ResponseDto;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA009ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA009ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA010ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA010ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA012ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectItemManageA012ApiResponse;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0506_02-01", screenNumber = "")
public class IfaSelfInspectItemManageController extends BaseController {

    /** エラー.表示順が重複しています。 */
    private static final String ERRORS_EMP_LOGIN_USERS_EXISTS2 = "errors.emp.loginUsers.exist#2";

    /** エラー.登録年月には翌月を入力してください。 */
    private static final String ERRORS_COR_SELF_CHECK_COR_ENFORCE_DATE_NEXT_MONTH
            = "errors.corSelfCheck.corEnforceDate.nextMonth";

    /** エラー.チェック項目は[{1}]を入力できません。ご確認ください。 */
    private static final String ERRORS_SPECIAL_WORDS = "errors.specialWords";

    /** JSONコンバータ */
    private JsonConverter jc = JsonConverter.getInstance();

    /** DateUtil */
    @Autowired
    private IfaDateUtil ifaDateUtil;

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSelfInspectItemManageA001ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA001ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA001RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageInitializeA001")
    public String initializeA001(
            @RequestBody IfaSelfInspectItemManageA001ApiRequest apiReq
    ) throws Exception {

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "initializeA001",
                new TypeReference<DataList<IfaSelfInspectItemManageA001ResponseDto>>() { }
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA001ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);


        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageDisplayYearMonthChangeA002
     * アクションID：A002
     * アクション名：表示年月変更
     * APIリクエスト：IfaSelfInspectItemManageA002ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA002ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA002RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 表示年月変更に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageDisplayYearMonthChangeA002")
    public String displayYearMonthChangeA002(
            @RequestBody IfaSelfInspectItemManageA002ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSelfInspectItemManageA002RequestDto appReq = new IfaSelfInspectItemManageA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "displayYearMonthChangeA002",
                new TypeReference<DataList<IfaSelfInspectItemManageA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA002ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageSearchDisplayA003
     * アクションID：A003
     * アクション名：検索表示
     * APIリクエスト：IfaSelfInspectItemManageA003ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA003ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA003RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA003ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 検索表示に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageSearchDisplayA003")
    public String searchDisplayA003(
            @RequestBody IfaSelfInspectItemManageA003ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSelfInspectItemManageA003RequestDto appReq = new IfaSelfInspectItemManageA003RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "searchDisplayA003",
                new TypeReference<DataList<IfaSelfInspectItemManageA003ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA003ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageUpdateA009
     * アクションID：A009
     * アクション名：更新
     * APIリクエスト：IfaSelfInspectItemManageA009ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA009ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA009RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA009ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageUpdateA009")
    public String updateA009(
            @RequestBody IfaSelfInspectItemManageA009ApiRequest apiReq
    ) throws Exception {

        // 関連項目チェック(同一表示順の自己点検項目があればエラー)
        List<String> sortNumberList = Optional.ofNullable(apiReq)
                        .map(val -> val.getSelfAssessmentItemList())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(val -> val.getSortNumber())
                        .collect(Collectors.toList());

        if (sortNumberList.size() != sortNumberList.stream().distinct().count()) {
            DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_EMP_LOGIN_USERS_EXISTS2,
                    IfaCommonUtil.getMessage(ERRORS_EMP_LOGIN_USERS_EXISTS2, new String[] { "表示順" })
            );

            return jc.toString(apiRes);
        }

        // 環境依存文字チェック
        List<String> selfAssessmentItemNameList = Optional.ofNullable(apiReq)
                .map(val -> val.getSelfAssessmentItemList())
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> val.getSelfAssessmentItemName())
                .collect(Collectors.toList());
        
        for (String selfAssessmentItemName : selfAssessmentItemNameList) {
            String errorUtf8Str = UnicodeCheckUtil.getErrorUtf8Str(selfAssessmentItemName);
            if (!StringUtil.isNullOrEmpty(errorUtf8Str)) {
                DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_SPECIAL_WORDS,
                        IfaCommonUtil.getMessage(
                                ERRORS_SPECIAL_WORDS,
                                new String[] { "チェック項目", errorUtf8Str }
                        )
                );

                return jc.toString(apiRes);
            }
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSelfInspectItemManageA009RequestDto appReq = new IfaSelfInspectItemManageA009RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA009ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "updateA009",
                new TypeReference<DataList<IfaSelfInspectItemManageA009ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA009ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageDeleteA010
     * アクションID：A010
     * アクション名：削除
     * APIリクエスト：IfaSelfInspectItemManageA010ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA010ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA010RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA010ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 削除に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageDeleteA010")
    public String deleteA010(
            @RequestBody IfaSelfInspectItemManageA010ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSelfInspectItemManageA010RequestDto appReq = new IfaSelfInspectItemManageA010RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA010ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "deleteA010",
                new TypeReference<DataList<IfaSelfInspectItemManageA010ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA010ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA010ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageFileImportA012
     * アクションID：A012
     * アクション名：ファイル取込
     * APIリクエスト：IfaSelfInspectItemManageA012ApiRequest
     * Apiレスポンス：IfaSelfInspectItemManageA012ApiResponse
     * Dtoリクエスト：IfaSelfInspectItemManageA012RequestDto
     * Dtoレスポンス：IfaSelfInspectItemManageA012ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return ファイル取込に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectItemManageFileImportA012")
    public String fileImportA012(
            @RequestBody IfaSelfInspectItemManageA012ApiRequest apiReq
    ) throws Exception {

        // 関連項目チェック(登録年月の翌月チェック)
        LocalDateTime currentMonth = ifaDateUtil.getCurrentLocalDateTime(ZoneId.of("Asia/Tokyo"));
        LocalDateTime nextMonth = currentMonth.plusMonths(1L);
        String nextMonthStr = nextMonth.format(DateTimeFormatter.ofPattern(DateUtil.YYYYMM));

        for (IfaSelfInspectItemManageA012ApiRequest.SelfAssessmentItem selfAssessmentItem : apiReq.getSelfAssessmentItemList()) {
            if (!StringUtils.equals(selfAssessmentItem.getRegisterDate(), nextMonthStr)) {
                DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_COR_SELF_CHECK_COR_ENFORCE_DATE_NEXT_MONTH,
                        IfaCommonUtil.getMessage(ERRORS_COR_SELF_CHECK_COR_ENFORCE_DATE_NEXT_MONTH)
                );
    
                return jc.toString(apiRes);
            }
        }

        // 関連項目チェック(同一[業者区分, 表示順]の自己点検項目があればエラー)
        List<String> sortNumberBrokerTypeList = Optional.ofNullable(apiReq)
                        .map(val -> val.getSelfAssessmentItemList())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(val -> StringUtils.leftPad(val.getSortNumber(), 7, "0") + val.getBrokerType())
                        .collect(Collectors.toList());

        if (sortNumberBrokerTypeList.size() != sortNumberBrokerTypeList.stream().distinct().count()) {
            DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_EMP_LOGIN_USERS_EXISTS2,
                    IfaCommonUtil.getMessage(ERRORS_EMP_LOGIN_USERS_EXISTS2, new String[] { "表示順" })
            );

            return jc.toString(apiRes);
        }

        // 環境依存文字チェック
        List<String> selfAssessmentItemNameList = Optional.ofNullable(apiReq)
                .map(val -> val.getSelfAssessmentItemList())
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> val.getSelfAssessmentItemName())
                .collect(Collectors.toList());
        
        for (String selfAssessmentItemName : selfAssessmentItemNameList) {
            String errorUtf8Str = UnicodeCheckUtil.getErrorUtf8Str(selfAssessmentItemName);
            if (!StringUtil.isNullOrEmpty(errorUtf8Str)) {
                DataList<IfaSelfInspectItemManageA009ApiResponse> apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_SPECIAL_WORDS,
                        IfaCommonUtil.getMessage(
                                ERRORS_SPECIAL_WORDS,
                                new String[] { "チェック項目", errorUtf8Str }
                        )
                );

                return jc.toString(apiRes);
            }
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSelfInspectItemManageA012RequestDto appReq = new IfaSelfInspectItemManageA012RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSelfInspectItemManageA012ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectItemManageService",
                "fileImportA012",
                new TypeReference<DataList<IfaSelfInspectItemManageA012ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSelfInspectItemManageA012ApiResponse> apiRes = new DataList<IfaSelfInspectItemManageA012ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
