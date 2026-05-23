package com.sbisec.helios.gw.brokerageMenu.jointMarket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.util.IfaJointMarketTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTrustFeeMultiSelectApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0208_02-01
 * 画面名：共同店舗 信託報酬
 *
 * @author SBI大連 董
 *2024/12/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0208_02-01", screenNumber = "58")
public class IfaJointMarketTrustFeeController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}] */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";

    /** {0}を選択してください。 */
    private static final String ERRORS_SELECTED = "errors.selected";

    /** "{0}は{1}以内を正しく入力して下さい。" */
    private static final String ERRORS_DATERANGE = "errors.dateRange";

    // --------------------------------
    // 集計単位(日次/月次累計)
    // --------------------------------
    /** 日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";

    /** 月次累計 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";

    /**
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTrustFeeInitializeA001
     * アクションID：A001
     * アクション名：初期化 
     * APIリクエスト：IfaJointMarketTrustFeeA001
     * APIレスポンス：IfaJointMarketTrustFeeA001ApiResponse
     * DTOリクエスト：IfaJointMarketTrustFeeA001DtoRequest
     * DTOレスポンス：IfaJointMarketTrustFeeA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTrustFeeInitializeA001")
    public String initializeA001(@RequestBody IfaJointMarketTrustFeeA001ApiRequest apiReq) throws Exception {

        IfaJointMarketTrustFeeA001DtoRequest appReq = new IfaJointMarketTrustFeeA001DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJointMarketTrustFeeA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaJointMarketTrustFeeService", "initializeA001",
                new TypeReference<DataList<IfaJointMarketTrustFeeA001DtoResponse>>() {
                }, appReq);

        DataList<IfaJointMarketTrustFeeA001ApiResponse> apiRes = new DataList<IfaJointMarketTrustFeeA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTrustFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJointMarketTrustFeeA002ApiRequest
     * APIレスポンス：IfaJointMarketTrustFeeA002ApiResponse
     * DTOリクエスト：IfaJointMarketTrustFeeA002DtoRequest
     * DTOレスポンス：IfaJointMarketTrustFeeA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTrustFeeDisplayA002")
    public String displayA002(@RequestBody IfaJointMarketTrustFeeA002ApiRequest apiReq) throws Exception {

        DataList<IfaJointMarketTrustFeeA002ApiResponse> apiRes = new DataList<IfaJointMarketTrustFeeA002ApiResponse>();

        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());

        // 仲介業者コード4桁チェック（仲介業者コードリストの値がある場合のみ）
        if (brokerCodeList != null && brokerCodeList.size() > 0) {
            String brokerCodeErrorMessage = checkBrokerCodeFourDigits(brokerCodeList);
            if (!brokerCodeErrorMessage.isEmpty()) {
                apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH,
                        brokerCodeErrorMessage);
                return jc.toString(apiRes);
            }
        }

        // 選択されている取引コースをリストに格納
        List<String> courseList = getSelectedItemList(apiReq.getCourse());

        // 取引コース存在チェック
        if (courseList == null || courseList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "取引コース" }));
            return jc.toString(apiRes);
        }

        // 期間指定（From）・期間指定（To）比較チェック
        String periodErrorMessage = checkPeriodComparison(apiReq.getPeriod(),
                apiReq.getDailyMonthlyCountingUnitTotal());
        if (!periodErrorMessage.isEmpty()) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATERANGE,
                    periodErrorMessage);
            return jc.toString(apiRes);
        }

        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());

        // 証券種別存在チェック
        if (securityClassList == null || securityClassList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "証券種別" }));
            return jc.toString(apiRes);
        }
        // 画面で入力した期間指定項目を利用して、検索条件を設定する。
        String dateFrom = StringUtil.EMPTY_STRING;
        String dateTo = StringUtil.EMPTY_STRING;
        if (apiReq.getDailyMonthlyCountingUnitTotal().equals("0")) {
            // 集計単位は日次の場合
            dateFrom = DateUtil.dateFormat(apiReq.getPeriod().get(0));
            dateTo = DateUtil.dateFormat(apiReq.getPeriod().get(1));
        } else {
            // 集計単位は月次の場合
            dateFrom = DateUtil.dateFormat(apiReq.getPeriod().get(0), DateUtil.YYYYMM, DateUtil.SEPARATED_YYYYMM);
            dateFrom += "01";
            dateTo = DateUtil.dateFormat(apiReq.getPeriod().get(1), DateUtil.YYYYMM, DateUtil.SEPARATED_YYYYMM);
            dateTo += "31";
        }

        IfaJointMarketTrustFeeA002DtoRequest appReq = new IfaJointMarketTrustFeeA002DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(dateFrom);
        appReq.setPeriodTo(dateTo);
        appReq.setSecurityClassList(securityClassList);

        DataList<IfaJointMarketTrustFeeA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaJointMarketTrustFeeService", "displayA002",
                new TypeReference<DataList<IfaJointMarketTrustFeeA002DtoResponse>>() {
                }, appReq);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTrustFeeCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaTrustFeeA004aApiRequest
     * APIレスポンス：IfaTrustFeeA004aApiResponse
     * DTOリクエスト：IfaTrustFeeA004aDtoRequest
     * DTOレスポンス：IfaTrustFeeA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTrustFeeCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaJointMarketTrustFeeA004aApiRequest apiReq) throws Exception {

        DataList<IfaJointMarketTrustFeeA004aApiResponse> apiRes = new DataList<IfaJointMarketTrustFeeA004aApiResponse>();

        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());

        // 仲介業者コード4桁チェック（仲介業者コードリストの値がある場合のみ）
        if (brokerCodeList != null && brokerCodeList.size() > 0) {
            String brokerCodeErrorMessage = checkBrokerCodeFourDigits(brokerCodeList);
            if (!brokerCodeErrorMessage.isEmpty()) {
                apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH,
                        brokerCodeErrorMessage);
                return jc.toString(apiRes);
            }
        }

        // 選択されている取引コースをリストに格納
        List<String> courseList = getSelectedItemList(apiReq.getCourse());

        // 取引コース存在チェック
        if (courseList == null || courseList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "取引コース" }));
            return jc.toString(apiRes);
        }

        // 期間指定（From）・期間指定（To）比較チェック
        String periodErrorMessage = checkPeriodComparison(apiReq.getPeriod(),
                apiReq.getDailyMonthlyCountingUnitTotal());
        if (!periodErrorMessage.isEmpty()) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATERANGE,
                    periodErrorMessage);
            return jc.toString(apiRes);
        }

        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());

        // 証券種別存在チェック
        if (securityClassList == null || securityClassList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "証券種別" }));
            return jc.toString(apiRes);
        }

        // 画面で入力した期間指定項目を利用して、検索条件を設定する。
        String dateFrom = StringUtil.EMPTY_STRING;
        String dateTo = StringUtil.EMPTY_STRING;
        if (apiReq.getDailyMonthlyCountingUnitTotal().equals("0")) {
            // 集計単位は日次の場合
            dateFrom = DateUtil.dateFormat(apiReq.getPeriod().get(0));
            dateTo = DateUtil.dateFormat(apiReq.getPeriod().get(1));
        } else {
            // 集計単位は月次の場合
            dateFrom = DateUtil.dateFormat(apiReq.getPeriod().get(0), DateUtil.YYYYMM, DateUtil.SEPARATED_YYYYMM);
            dateFrom += "01";
            dateTo = DateUtil.dateFormat(apiReq.getPeriod().get(1), DateUtil.YYYYMM, DateUtil.SEPARATED_YYYYMM);
            dateTo += "31";
        }

        IfaJointMarketTrustFeeA004aDtoRequest appReq = new IfaJointMarketTrustFeeA004aDtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(dateFrom);
        appReq.setPeriodTo(dateTo);
        appReq.setSecurityClassList(securityClassList);

        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);

        DataList<IfaJointMarketTrustFeeA004aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaJointMarketTrustFeeService", "csvOutputA004a",
                new TypeReference<DataList<IfaJointMarketTrustFeeA004aDtoResponse>>() {
                }, appReq, frameworkSessionId);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTrustFeeCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * リクエスト：ifaJointMarketTrustFeeA004bApiRequest
     * レスポンス：HttpServletResponse
     *
     * @param apiReq リクエスト
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTrustFeeCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaJointMarketTrustFeeA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {

        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        String[] parts = StringUtils.split(apiReq.getCsvDownloadFile(), ",");
        String csvTmpFileName = parts[0]; // CSV一時ファイル名
        String fileName = parts[1]; // ファイル名

        // A004aで作成したファイルをダウンロード
        doDownLoadCsvFile(response, csvTmpFileName, getCsvFileName(fileName), IfaCommonUtil.getUserAccount(),
                apiReq.getPattern());
    }

    @Override
    protected String getFirstViewName() {

        return null;
    }

    @Override
    protected String getCsvHeader(String pattern) {

        return IfaJointMarketTrustFeeCsvOut.getHeader(pattern);
    }

    /**
     * カンマ区切りの文字列をリストに変換
     *
     * @param str 文字列
     * @return List 分割した文字列を格納したリスト
     */
    private List<String> splitStringToList(String str) {

        List<String> list = new ArrayList<String>();

        // null、空文字の場合は空のリストを返却
        if (str == null || str.isEmpty()) {
            return list;
        }

        // カンマ区切りで分割する
        String[] parts = str.split((","));
        for (String part : parts) {
            if (!ObjectUtils.isEmpty(part)) {
                list.add(part.trim());
            }
        }

        return list;
    }

    /**
     * 仲介業者コード4桁チェック
     *
     * @param brokerCodeList 仲介業者コードリスト
     * @return String エラーメッセージ（正常の場合は空文字が設定される）
     */
    private String checkBrokerCodeFourDigits(List<String> brokerCodeList) {

        String brokerCodeErrorMessage = StringUtil.EMPTY_STRING;
        String checkedBrokerCode = StringUtil.EMPTY_STRING;
        boolean isNotBrokerCodeFourDigits = false;

        // 仲介業者コード4桁チェック
        if (brokerCodeList != null && brokerCodeList.size() != 0) {
            for (String brokerCode : brokerCodeList) {
                if (brokerCode.length() != 4) {
                    isNotBrokerCodeFourDigits = true;
                    checkedBrokerCode += brokerCode + ", ";
                }
            }
        }

        if (isNotBrokerCodeFourDigits) {
            brokerCodeErrorMessage = getMessage(ERRORS_BROKERCODE_NOTLENGTH,
                    new String[] { checkedBrokerCode.substring(0, checkedBrokerCode.length() - 2) });
        }

        return brokerCodeErrorMessage;
    }

    /**
     * 選択された項目のみをリストに格納して返却
     *
     * @param List マルチ選択リスト
     * @return List 選択された項目のみを格納したリスト
     */
    private List<String> getSelectedItemList(List<IfaJointMarketTrustFeeMultiSelectApiRequest> multiSelectList) {

        List<String> selectedItemList = new ArrayList<String>();

        for (IfaJointMarketTrustFeeMultiSelectApiRequest item : multiSelectList) {
            if (Boolean.parseBoolean(item.getIsSelected())) {
                selectedItemList.add(item.getId());
            }
        }

        return selectedItemList;
    }

    /**
     * 期間指定（From）・期間指定（To）比較チェック
     *
     * @param period                        期間指定
     * @param dailyMonthlyCountingUnitTotal 集計単位(日次/月次累計)
     * @return String エラーメッセージ（正常の場合は空文字が設定される）
     * @throws Exception システムエラー
     */

    private String checkPeriodComparison(List<String> period, String dailyMonthlyCountingUnitTotal) throws Exception {

        boolean isPeriodComparisonCheck = false;
        String periodFrom = period.get(0);
        String periodTo = period.get(1);

        if (DAILY_MONTHLY_COUNTING_UNIT_DAILY.equals(dailyMonthlyCountingUnitTotal)) {
            isPeriodComparisonCheck = DateUtil.maximumXMonthsFromTo(periodFrom, periodTo, 6, DateUtil.YYYYMMDD,
                    DateUtil.SEPARATED_YYYYMMDD);
        } else if (DAILY_MONTHLY_COUNTING_UNIT_MONTHLY.equals(dailyMonthlyCountingUnitTotal)) {
            isPeriodComparisonCheck = DateUtil.maximumXMonthsFromTo(periodFrom, periodTo, 6, DateUtil.YYYYMM,
                    DateUtil.SEPARATED_YYYYMM);
        }

        String periodErrorMessage = StringUtil.EMPTY_STRING;

        if (!isPeriodComparisonCheck) {
            periodErrorMessage = getMessage(ERRORS_DATERANGE, new String[] { "期間指定", "6ヶ月" });
        }

        return periodErrorMessage;

    }
}
