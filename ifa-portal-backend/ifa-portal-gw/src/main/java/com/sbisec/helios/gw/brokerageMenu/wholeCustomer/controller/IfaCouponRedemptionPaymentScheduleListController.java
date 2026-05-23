package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaCouponRedemptionPaymentScheduleListCsvOut;
import com.sbisec.helios.ap.common.dto.SystemDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCouponRedemptionPaymentScheduleListSelectedApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 2024/06/06 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0104-01", screenNumber = "19")
public class IfaCouponRedemptionPaymentScheduleListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** エラーメッセージ：取引コース */
    private static final String MSG_COURSE = "取引コース";
    
    /** エラーメッセージ：期間指定 */
    private static final String MSG_PERIOD = "期間指定";
    
    /** エラーメッセージ：当月～来月末 */
    private static final String MSG_CURRENT_MONTH_TO_NEXT_MONTH = "当月～来月末";
    
    /** ダウンロードファイルの接頭語 */
    private static final String PREFIX_CSV_FILE_NAME = "利金償還金支払予定一覧";
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCouponRedemptionPaymentScheduleListA001ApiRequest
     * Apiレスポンス：IfaCouponRedemptionPaymentScheduleListA001ApiResponse
     * Dtoリクエスト：IfaCouponRedemptionPaymentScheduleListA001DtoRequest
     * Dtoレスポンス：IfaCouponRedemptionPaymentScheduleListA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListInitializeA001")
    public String initializeA001(@RequestBody IfaCouponRedemptionPaymentScheduleListA001ApiRequest apiReq)
            throws Exception {
        
        IfaCouponRedemptionPaymentScheduleListA001DtoRequest appReq = new IfaCouponRedemptionPaymentScheduleListA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaCouponRedemptionPaymentScheduleListService", "initializeA001",
                new TypeReference<DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaCouponRedemptionPaymentScheduleListA001ApiResponse> apiRes = new DataList<IfaCouponRedemptionPaymentScheduleListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCouponRedemptionPaymentScheduleListA002ApiRequest
     * Apiレスポンス：IfaCouponRedemptionPaymentScheduleListA002ApiResponse
     * Dtoリクエスト：IfaCouponRedemptionPaymentScheduleListA002ApiRequest
     * Dtoレスポンス：IfaCouponRedemptionPaymentScheduleListA002ApiResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListDisplayA002")
    public String displayA002(@RequestBody IfaCouponRedemptionPaymentScheduleListA002ApiRequest apiReq)
            throws Exception {
        
        DataList<IfaCouponRedemptionPaymentScheduleListA002ApiResponse> apiRes = new DataList<IfaCouponRedemptionPaymentScheduleListA002ApiResponse>();
        
        // リクエスト.取引コースのチェック
        if (!checkCoursesSelected(apiReq.getCourse())) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA002ApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_SELECTED, IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { MSG_COURSE }));
            return jc.toString(apiRes);
        }
        
        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェック
        String periodFrom = apiReq.getPeriodYmFrom();
        String periodTo = apiReq.getPeriodYmTo();
        
        if (!checkPeriodFromTo(periodFrom, periodTo)) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA002ApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_DATE_SPECIFY_FROM_TO,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] { MSG_PERIOD }));
            return jc.toString(apiRes);
        }
        
        if (!checkPeriodSysdate(periodFrom, periodTo)) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA002ApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_DATE_RANGE, IfaCommonUtil.getMessage(ERRORS_DATE_RANGE,
                            new String[] { MSG_PERIOD, MSG_CURRENT_MONTH_TO_NEXT_MONTH }));
            return jc.toString(apiRes);
        }
        
        IfaCouponRedemptionPaymentScheduleListA002DtoRequest appReq = new IfaCouponRedemptionPaymentScheduleListA002DtoRequest();
        // データコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 仲介業者コードをリスト化して設定
        if (!apiReq.getBrokerCode().isBlank() && !apiReq.getBrokerCode().isEmpty()) {
            appReq.setBrokerCodeList(Arrays.asList(apiReq.getBrokerCode().split(",")));
        } else {
            appReq.setBrokerCodeList(null);
        }
        // 証券種別をリスト化して設定
        if (!apiReq.getSecuriytClass().isBlank() && !apiReq.getSecuriytClass().isEmpty()) {
            appReq.setSecuriytClassList(Arrays.asList(apiReq.getSecuriytClass().split(",")));
        } else {
            appReq.setSecuriytClassList(null);
        }
        DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaCouponRedemptionPaymentScheduleListService", "displayA002",
                new TypeReference<DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse>>() {
                }, appReq);
        
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListCsvOutputA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaCouponRedemptionPaymentScheduleListA004aApiRequest
     * Apiレスポンス：IfaCouponRedemptionPaymentScheduleListA004aApiResponse
     * Dtoリクエスト：IfaCouponRedemptionPaymentScheduleListA004DtoRequest
     * Dtoレスポンス：IfaCouponRedemptionPaymentScheduleListA004DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaCouponRedemptionPaymentScheduleListA004aApiRequest apiReq)
            throws Exception {
        
        DataList<IfaCouponRedemptionPaymentScheduleListA004aApiResponse> apiRes = new DataList<IfaCouponRedemptionPaymentScheduleListA004aApiResponse>();
        
        // リクエスト.取引コースのチェック
        if (!checkCoursesSelected(apiReq.getCourse())) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA004aApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_SELECTED, IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { MSG_COURSE }));
            return jc.toString(apiRes);
        }
        
        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェック
        String periodFrom = apiReq.getPeriodYmFrom();
        String periodTo = apiReq.getPeriodYmTo();
        
        if (!checkPeriodFromTo(periodFrom, periodTo)) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA004aApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_DATE_SPECIFY_FROM_TO,
                    IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] { MSG_PERIOD }));
            return jc.toString(apiRes);
        }
        
        if (!checkPeriodSysdate(periodFrom, periodTo)) {
            apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaCouponRedemptionPaymentScheduleListA004aApiResponse>(), ErrorLevel.FATAL,
                    ERRORS_DATE_RANGE, IfaCommonUtil.getMessage(ERRORS_DATE_RANGE,
                            new String[] { MSG_PERIOD, MSG_CURRENT_MONTH_TO_NEXT_MONTH }));
            return jc.toString(apiRes);
        }
        
        IfaCouponRedemptionPaymentScheduleListA004DtoRequest appReq = new IfaCouponRedemptionPaymentScheduleListA004DtoRequest();
        // データコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 仲介業者コードをリスト化して設定
        if (!apiReq.getBrokerCode().isBlank() && !apiReq.getBrokerCode().isEmpty()) {
            appReq.setBrokerCodeList(Arrays.asList(apiReq.getBrokerCode().split(",")));
        } else {
            appReq.setBrokerCodeList(null);
        }
        // 証券種別をリスト化して設定
        if (!apiReq.getSecuriytClass().isBlank() && !apiReq.getSecuriytClass().isEmpty()) {
            appReq.setSecuriytClassList(Arrays.asList(apiReq.getSecuriytClass().split(",")));
        } else {
            appReq.setSecuriytClassList(null);
        }
        
        // frameWorkSessionIDを取得
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        
        DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaCouponRedemptionPaymentScheduleListService", "csvOutputA004",
                new TypeReference<DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse>>() {
                }, appReq, fwSessionId);
        
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * APIリクエスト：IfaCouponRedemptionPaymentScheduleListA004bApiRequest
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaCouponRedemptionPaymentScheduleListCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaCouponRedemptionPaymentScheduleListA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        // 共通のダウンロード処理を実施
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // CSVファイル ダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(PREFIX_CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount());
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaCouponRedemptionPaymentScheduleListCsvOut.getHeaders();
    }
    
    /**
     * 取引コース選択判定
     * 一つでも選択されていれば正常,未選択の場合はエラーを返す
     *
     * @param courses 取引コース
     * @return boolean 正常：TRUE　異常：FALSE
     */
    private boolean checkCoursesSelected(List<IfaCouponRedemptionPaymentScheduleListSelectedApiRequest> courses) {
        
        if (!ObjectUtils.isEmpty(courses)) {
            for (IfaCouponRedemptionPaymentScheduleListSelectedApiRequest course : courses) {
                if (course.getIsSelected()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェック
     * 期間指定（From）と期間指定（To）の大小関係チェック
     *
     * @param reqPeriodYmFrom 期間指定（From）
     * @param reqPeriodYmTo 期間指定（To）
     * @return boolean 正常：TRUE　異常：FALSE
     * @throws Exception 例外
     */
    private boolean checkPeriodFromTo(String reqPeriodYmFrom, String reqPeriodYmTo) throws Exception {
        
        // フォーマッター
        DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter formatYmd = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        // リクエスト.期間指定（From）がリクエスト.期間指定（To）より大きい場合：メッセージを表示し、処理終了。
        // 期間指定をYYYYMMDD形式に変換
        String periodYmdFrom = formatYmd.format(formatInput.parse(reqPeriodYmFrom));
        String periodYmdTo = formatYmd.format(formatInput.parse(reqPeriodYmTo));
        
        if ((!StringUtils.isEmpty(periodYmdFrom) && periodYmdFrom.length() > 0)
                && (!StringUtils.isEmpty(periodYmdTo) && periodYmdTo.length() > 0)
                && !DateUtil.isValidFromTo(periodYmdFrom, periodYmdTo, DateUtil.YYYYMMDD)) {
            return false;
        }
        return true;
    }
    
    /**
     * リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェック
     * 期間指定（From）とシステム日付、期間指定（To）とシステム日付のチェック
     *
     * @param reqPeriodYmFrom 期間指定（From）
     * @param reqPeriodYmTo 期間指定（To）
     * @return boolean 正常：TRUE　異常：FALSE
     * @throws Exception 例外
     */
    private boolean checkPeriodSysdate(String reqPeriodYmFrom, String reqPeriodYmTo) throws Exception {
        
        // フォーマッター
        DateTimeFormatter formatInput = DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD);
        DateTimeFormatter formatYm = DateTimeFormatter.ofPattern(DateUtil.YYYYMM);
        
        // リクエスト.期間指定（From）がシステム日付の当月（YYYYMM）より小さい　または
        // リクエスト.期間指定（To）がシステム日付の来月(YYYYMM)より大きい場合：メッセージを表示し、処理終了。
        
        // システム日付, 1か月後のシステム日付を取得
        Date systemDate = new Date();
        SystemDateDtoRequest req = new SystemDateDtoRequest();
        DataList<Date> resp = ApiRequestUtil.invoke("systemDateService", "getSystemDate",
                new TypeReference<DataList<Date>>() {
                }, req);
        List<Date> dateList = resp.getDataList();
        if (!CollectionUtils.isEmpty(dateList)) {
            // データが取得できた場合、設定する
            systemDate = dateList.get(0);
        }
        
        LocalDate localdate = LocalDate.parse(DateUtil.format(systemDate, DateUtil.SEPARATED_YYYYMMDD), formatInput);
        String now = localdate.format(formatYm);
        String nextMonth = localdate.plusMonths(1).format(formatYm);
        
        // 期間指定をYYYYMM形式に変換
        String periodYmFrom = formatYm.format(formatInput.parse(reqPeriodYmFrom));
        String periodYmTo = formatYm.format(formatInput.parse(reqPeriodYmTo));
        
        if ((!now.equals(periodYmFrom) && DateUtil.isValidFromTo(periodYmFrom, now, DateUtil.YYYYMM))
                || (!nextMonth.equals(periodYmTo) && DateUtil.isValidFromTo(nextMonth, periodYmTo, DateUtil.YYYYMM))) {
            return false;
        }
        
        return true;
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
