package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

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
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTrustFeeA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptTrustFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTrustFeeMultiSelectApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0206_03-01", screenNumber = "22")
public class IfaJointSubscriptTrustFeeController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    
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
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptTrustFeeA001ApiRequest
     * APIレスポンス：IfaJointSubscriptTrustFeeA001ApiResponse
     * DTOリクエスト：IfaJointSubscriptTrustFeeA001DtoRequest
     * DTOレスポンス：IfaJointSubscriptTrustFeeA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptTrustFeeA001ApiRequest apiReq) throws Exception {
        
        IfaJointSubscriptTrustFeeA001DtoRequest appReq = new IfaJointSubscriptTrustFeeA001DtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaJointSubscriptTrustFeeA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTrustFeeService", "initializeA001",
                new TypeReference<DataList<IfaJointSubscriptTrustFeeA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaJointSubscriptTrustFeeA001ApiResponse> apiRes = new DataList<IfaJointSubscriptTrustFeeA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJointSubscriptTrustFeeA002ApiRequest
     * APIレスポンス：IfaJointSubscriptTrustFeeA002ApiResponse
     * DTOリクエスト：IfaJointSubscriptTrustFeeA002DtoRequest
     * DTOレスポンス：IfaJointSubscriptTrustFeeA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeDisplayA002")
    public String displayA002(@RequestBody IfaJointSubscriptTrustFeeA002ApiRequest apiReq) throws Exception {
        
        DataList<IfaJointSubscriptTrustFeeA002ApiResponse> apiRes = new DataList<IfaJointSubscriptTrustFeeA002ApiResponse>();

        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
       
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
     
        IfaJointSubscriptTrustFeeA002DtoRequest appReq = new IfaJointSubscriptTrustFeeA002DtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(apiReq.getPeriod().get(0));
        appReq.setPeriodTo(apiReq.getPeriod().get(1));
        appReq.setSecurityClassList(securityClassList);
        
        DataList<IfaJointSubscriptTrustFeeA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTrustFeeService", "displayA002",
                new TypeReference<DataList<IfaJointSubscriptTrustFeeA002DtoResponse>>() {
                }, appReq);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaJointSubscriptTrustFeeA004aApiRequest
     * APIレスポンス：IfaJointSubscriptTrustFeeA004aApiResponse
     * DTOリクエスト：IfaJointSubscriptTrustFeeA004aDtoRequest
     * DTOレスポンス：IfaJointSubscriptTrustFeeA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaJointSubscriptTrustFeeA004aApiRequest apiReq) throws Exception {
        
        DataList<IfaJointSubscriptTrustFeeA004aApiResponse> apiRes = new DataList<IfaJointSubscriptTrustFeeA004aApiResponse>();

        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
             
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

        IfaJointSubscriptTrustFeeA004aDtoRequest appReq = new IfaJointSubscriptTrustFeeA004aDtoRequest();
        
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setCourseList(courseList);
        appReq.setPeriodFrom(apiReq.getPeriod().get(0));
        appReq.setPeriodTo(apiReq.getPeriod().get(1));
        appReq.setSecurityClassList(securityClassList);
        
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        DataList<IfaJointSubscriptTrustFeeA004aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTrustFeeService", "csvOutputA004a",
                new TypeReference<DataList<IfaJointSubscriptTrustFeeA004aDtoResponse>>() {
                }, appReq, frameworkSessionId);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * リクエスト：IfaJointSubscriptTrustFeeA004bApiRequest
     * レスポンス：HttpServletResponse 
     *
     * @param apiReq リクエスト
     * @param response httpレスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTrustFeeCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaJointSubscriptTrustFeeA004bApiRequest apiReq, HttpServletResponse response)
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
        
        return IfaJointSubscriptTrustFeeCsvOut.getHeader(pattern);
    }
    
    /**
     * カンマ区切りの文字列をリストに変換
     *
     * @param str 文字列
     * @return list  分割した文字列を格納したリス
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
     * 選択された項目のみをリストに格納して返却
     *
     * @param multiSelectList マルチ選択リスト
     * @return selectedItemList  選択された項目のみを格納したリスト 
     */
    List<String> getSelectedItemList(List<IfaJointSubscriptTrustFeeMultiSelectApiRequest> multiSelectList) {
        
        List<String> selectedItemList = new ArrayList<String>();
        
        for (IfaJointSubscriptTrustFeeMultiSelectApiRequest item : multiSelectList) {
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
     * @return periodErrorMessage エラーメッセージ（正常の場合は空文字が設定される）
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
