package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004ResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptPeriodMasterRegisterInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptPeriodMasterRegisterInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptPeriodMasterRegisterInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptPeriodMasterRegisterInputA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_01-04_1", screenNumber = "")
public class IfaSubscriptPeriodMasterRegisterInputController extends BaseController {

    /**　期間の大小関係に誤りがある */
    private static final String ERRORS_DATE_OVER_RANGE = "errors.dateOverRange";
    /**　指定した以前の日付を入力 */
    private static final String ERRORS_BEFORE_DATE = "errors.beforeDate";
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptPeriodMasterRegisterInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSubscriptPeriodMasterRegisterInputA001ApiRequest
     * Apiレスポンス：IfaSubscriptPeriodMasterRegisterInputA001ApiResponse
     * Dtoリクエスト：IfaSubscriptPeriodMasterRegisterInputA001RequestDto
     * Dtoレスポンス：IfaSubscriptPeriodMasterRegisterInputA001ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptPeriodMasterRegisterInputInitializeA001")
    public String initializeA001(
            @RequestBody IfaSubscriptPeriodMasterRegisterInputA001ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptPeriodMasterRegisterInputA001RequestDto appReq = new IfaSubscriptPeriodMasterRegisterInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptPeriodMasterRegisterInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptPeriodMasterRegisterInputService",
                "initializeA001",
                new TypeReference<DataList<IfaSubscriptPeriodMasterRegisterInputA001ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptPeriodMasterRegisterInputA001ApiResponse> apiRes = new DataList<IfaSubscriptPeriodMasterRegisterInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptPeriodMasterRegisterInputOkA004
     * アクションID：A004
     * アクション名：OK
     * APIリクエスト：IfaSubscriptPeriodMasterRegisterInputA004ApiRequest
     * Apiレスポンス：IfaSubscriptPeriodMasterRegisterInputA004ApiResponse
     * Dtoリクエスト：IfaSubscriptPeriodMasterRegisterInputA004RequestDto
     * Dtoレスポンス：IfaSubscriptPeriodMasterRegisterInputA004ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 更新成否
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptPeriodMasterRegisterInputOkA004")
    public String okA004(
            @RequestBody IfaSubscriptPeriodMasterRegisterInputA004ApiRequest apiReq
    ) throws Exception {

        // 入力チェック
        // No,9 で使う項目を取得
        String bbPeriodFrom = apiReq.getBbPeriodFrom();
        String bbPeriodTo = apiReq.getBbPeriodTo();
        
        // No,9 募集期間（FROM）>　募集期間（TO）のときエラー
        if (
                (!StringUtils.isEmpty(bbPeriodFrom) && bbPeriodFrom.length() > 0)
                && (!StringUtils.isEmpty(bbPeriodTo) && bbPeriodTo.length() > 0)
                && !DateUtil.isValidFromTo(
                        bbPeriodFrom,
                        bbPeriodTo,
                        DateUtil.YYYYMMDD
                        )
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes 
                     = new DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>();
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_OVER_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATE_OVER_RANGE, new String[] {"募集期間（FROM－TO）"})
                    );
                    
            return jc.toString(apiRes);
        }

        // No,13,16,17 で使う項目を取得
        String depositScheduleDate = apiReq.getDepositScheduleDate();
        String deliveryDueDate  = apiReq.getDeliveryDueDate();
        String listedDate = apiReq.getListedDate();
        
        // No,13 入金予定日（募集最終日）>受渡期日
        if (
                (!StringUtils.isEmpty(depositScheduleDate) && depositScheduleDate.length() > 0)
                && (!StringUtils.isEmpty(deliveryDueDate) && deliveryDueDate.length() > 0)
                && !DateUtil.isValidFromTo(
                        depositScheduleDate,
                        deliveryDueDate,
                        DateUtil.YYYYMMDD
                )
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes = new DataList<>();
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_BEFORE_DATE,
                    IfaCommonUtil.getMessage(
                            ERRORS_BEFORE_DATE,
                            new String[] {"入金予定日（募集最終日）", "受渡期日"}
                        )
                );

            return jc.toString(apiRes);
        }

        
        // No,16 入金予定日（募集最終日）≧　上場日（受渡期日）のときエラー
        if (
                (!StringUtils.isEmpty(depositScheduleDate) && depositScheduleDate.length() > 0) 
                && (!StringUtils.isEmpty(listedDate) && listedDate.length() > 0) 
                && (
                        listedDate.equals(depositScheduleDate)
                        || !DateUtil.isValidFromTo(
                                depositScheduleDate,
                                listedDate,
                                DateUtil.YYYYMMDD
                        )
                )
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes = new DataList<>();
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_OVER_RANGE,
                    IfaCommonUtil.getMessage(
                        ERRORS_DATE_OVER_RANGE,
                            new String[] {"入金予定日（募集最終日）と上場日（受渡期日）"}
                        )
                );

            return jc.toString(apiRes);
        }

        // No,17 受渡期日 > 上場日（受渡期日）のときエラー
        if (
                (!StringUtils.isEmpty(deliveryDueDate) && deliveryDueDate.length() > 0)
                && (!StringUtils.isEmpty(listedDate) && listedDate.length() > 0)
                && !DateUtil.isValidFromTo(
                        deliveryDueDate,
                        listedDate,
                        DateUtil.YYYYMMDD
                )
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes = new DataList<>();
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_OVER_RANGE,
                    IfaCommonUtil.getMessage(
                            ERRORS_DATE_OVER_RANGE,
                            new String[] {"受渡期日と上場日（受渡期日）"}
                    )
            );

            return jc.toString(apiRes);
        }
        
        // No,30 で使う項目を取得
        String ifaBookBuildingPresentationFrom = apiReq.getIfaBookBuildingPresentationFrom();
        String ifaBookBuildingPresentationTo = apiReq.getIfaBookBuildingPresentationTo();

        // No,30 IFA・BB申込期間（FROM）>　IFA・BB申込期間（TO）の時エラー
        if (
                (!StringUtils.isEmpty(ifaBookBuildingPresentationFrom) && ifaBookBuildingPresentationFrom.length() > 0)
                && (!StringUtils.isEmpty(ifaBookBuildingPresentationTo) && ifaBookBuildingPresentationTo.length() > 0)
                && !DateUtil.isValidFromTo(
                        ifaBookBuildingPresentationFrom,
                        ifaBookBuildingPresentationTo,
                        "yyyy/MM/dd HH:mm"
                )
        ) {
            DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes = new DataList<>();
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_OVER_RANGE,
                    IfaCommonUtil.getMessage(
                            ERRORS_DATE_OVER_RANGE,
                            new String[] {"IFA・BB申込期間（FROM－TO）"}
                    )
            );

            return jc.toString(apiRes);
        }
        

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptPeriodMasterRegisterInputA004RequestDto appReq = new IfaSubscriptPeriodMasterRegisterInputA004RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptPeriodMasterRegisterInputService",
                "okA004",
                new TypeReference<DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse> apiRes = new DataList<IfaSubscriptPeriodMasterRegisterInputA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
