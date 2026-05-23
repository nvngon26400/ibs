package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaTrustFeeMultiSelectApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020503-01", screenNumber = "15")
public class IfaTrustFeeController extends BaseController {
    
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
    //  集計単位(日次/月次累計)
    // --------------------------------   
    /** 日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";
    
    /** 月次累計 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaTrustFeeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaTrustFeeA001ApiRequest
     * APIレスポンス：IfaTrustFeeA001ApiResponse
     * DTOリクエスト：IfaTrustFeeA001DtoRequest
     * DTOレスポンス：IfaTrustFeeA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaTrustFeeInitializeA001")
    public String initializeA001(@RequestBody IfaTrustFeeA001ApiRequest apiReq) throws Exception {
        
        IfaTrustFeeA001DtoRequest appReq = new IfaTrustFeeA001DtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaTrustFeeA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTrustFeeService", "initializeA001",
                new TypeReference<DataList<IfaTrustFeeA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaTrustFeeA001ApiResponse> apiRes = new DataList<IfaTrustFeeA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaTrustFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaTrustFeeA002ApiRequest
     * APIレスポンス：IfaTrustFeeA002ApiResponse
     * DTOリクエスト：IfaTrustFeeA002DtoRequest
     * DTOレスポンス：IfaTrustFeeA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaTrustFeeDisplayA002")
    public String displayA002(@RequestBody IfaTrustFeeA002ApiRequest apiReq) throws Exception {
        
        DataList<IfaTrustFeeA002ApiResponse> apiRes = new DataList<IfaTrustFeeA002ApiResponse>();
        
        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
        
        //　仲介業者コード4桁チェック（仲介業者コードリストの値がある場合のみ）
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
        
        //　取引コース存在チェック
        if (courseList == null || courseList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "取引コース" }));
            return jc.toString(apiRes);
        }
        
        //　期間指定（From）・期間指定（To）比較チェック
        String periodErrorMessage = checkPeriodComparison(apiReq.getPeriod(),
                apiReq.getDailyMonthlyCountingUnitTotal());
        if (!periodErrorMessage.isEmpty()) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATERANGE,
                    periodErrorMessage);
            return jc.toString(apiRes);
        }
        
        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());
        
        //　証券種別存在チェック
        if (securityClassList == null || securityClassList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "証券種別" }));
            return jc.toString(apiRes);
        }
        
        IfaTrustFeeA002DtoRequest appReq = new IfaTrustFeeA002DtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(apiReq.getPeriod().get(0));
        appReq.setPeriodTo(apiReq.getPeriod().get(1));
        appReq.setSecurityClassList(securityClassList);
        
        DataList<IfaTrustFeeA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTrustFeeService", "displayA002",
                new TypeReference<DataList<IfaTrustFeeA002DtoResponse>>() {
                }, appReq);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaTrustFeeCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaTrustFeeA004aApiRequest
     * APIレスポンス：IfaTrustFeeA004aApiResponse
     * DTOリクエスト：IfaTrustFeeA004aDtoRequest
     * DTOレスポンス：IfaTrustFeeA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaTrustFeeCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaTrustFeeA004aApiRequest apiReq) throws Exception {
        
        DataList<IfaTrustFeeA004aApiResponse> apiRes = new DataList<IfaTrustFeeA004aApiResponse>();
        
        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
        
        //　仲介業者コード4桁チェック（仲介業者コードリストの値がある場合のみ）
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
        
        //　取引コース存在チェック
        if (courseList == null || courseList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "取引コース" }));
            return jc.toString(apiRes);
        }
        
        //　期間指定（From）・期間指定（To）比較チェック
        String periodErrorMessage = checkPeriodComparison(apiReq.getPeriod(),
                apiReq.getDailyMonthlyCountingUnitTotal());
        if (!periodErrorMessage.isEmpty()) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATERANGE,
                    periodErrorMessage);
            return jc.toString(apiRes);
        }
        
        // 選択されている証券種別をリストに格納
        List<String> securityClassList = getSelectedItemList(apiReq.getSecurityClass());
        
        //　証券種別存在チェック
        if (securityClassList == null || securityClassList.size() < 1) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_SELECTED,
                    getMessage(ERRORS_SELECTED, new String[] { "証券種別" }));
            return jc.toString(apiRes);
        }
        
        IfaTrustFeeA004aDtoRequest appReq = new IfaTrustFeeA004aDtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(apiReq.getPeriod().get(0));
        appReq.setPeriodTo(apiReq.getPeriod().get(1));
        appReq.setSecurityClassList(securityClassList);
        
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        DataList<IfaTrustFeeA004aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTrustFeeService", "csvOutputA004a",
                new TypeReference<DataList<IfaTrustFeeA004aDtoResponse>>() {
                }, appReq, frameworkSessionId);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaTrustFeeCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * リクエスト：IfaTrustFeeA004bApiRequest
     * レスポンス：HttpServletResponse 
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaTrustFeeCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaTrustFeeA004bApiRequest apiReq, HttpServletResponse response)
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
        
        return IfaTrustFeeCsvOut.getHeader(pattern);
    }
    
    /**
     * カンマ区切りの文字列をリストに変換
     *
     * @param str 文字列
     * @param List  分割した文字列を格納したリスト
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
     * @param String エラーメッセージ（正常の場合は空文字が設定される）
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
     * @param List  選択された項目のみを格納したリスト 
     */
    List<String> getSelectedItemList(List<IfaTrustFeeMultiSelectApiRequest> multiSelectList) {
        
        List<String> selectedItemList = new ArrayList<String>();
        
        for (IfaTrustFeeMultiSelectApiRequest item : multiSelectList) {
            if (Boolean.parseBoolean(item.getIsSelected())) {
                selectedItemList.add(item.getId());
            }
        }
        
        return selectedItemList;
    }
    
    /**
     * 期間指定（From）・期間指定（To）比較チェック
     *
     * @param period 期間指定
     * @param dailyMonthlyCountingUnitTotal 集計単位(日次/月次累計)
     * @param String エラーメッセージ（正常の場合は空文字が設定される）
     */
    String checkPeriodComparison(List<String> period, String dailyMonthlyCountingUnitTotal) throws Exception {
        
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
