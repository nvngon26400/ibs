package com.sbisec.helios.gw.wholePortal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aResponseDto;
import com.sbisec.helios.ap.wholePortal.util.IfaCustomerAlertCsvOutputCsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.wholePortal.form.IfaCustomerAlertCsvOutputA002aApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaCustomerAlertCsvOutputA002aApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaCustomerAlertCsvOutputA002bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN01", id = "SUB01-02", screenNumber = "70")
public class IfaCustomerAlertCsvOutputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** CSVファイル名 */
    private static final String CSV_FILE_NAME = "顧客アラート通知";
    
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}]. */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";
    
    /**
     * アクセス：/wholePortal/ifaCustomerAlertCsvOutputCsvOutputA002a
     * アクションID：A002a
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerAlertCsvOutputA002aApiRequest
     * Apiレスポンス：IfaCustomerAlertCsvOutputA002aApiResponse
     * Dtoリクエスト：IfaCustomerAlertCsvOutputA002aRequestDto
     * Dtoレスポンス：IfaCustomerAlertCsvOutputA002aResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/wholePortal/ifaCustomerAlertCsvOutputCsvOutputA002a")
    public String csvOutputA002a(@RequestBody IfaCustomerAlertCsvOutputA002aApiRequest apiReq) throws Exception {
        
        IfaCustomerAlertCsvOutputA002aRequestDto appReq = new IfaCustomerAlertCsvOutputA002aRequestDto();
        
        // 仲介業者コード4桁チェック
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCodeList());
        boolean isBrokerCodeFourDigits = false;
        String brokerCodeErrorMessage = "";
        if (brokerCodeList != null && brokerCodeList.size() != 0) {
            for (String brokerCode : brokerCodeList) {
                if (brokerCode.length() != 4) {
                    isBrokerCodeFourDigits = true;
                    brokerCodeErrorMessage += brokerCode + ", ";
                }
            }
        }
        
        if (isBrokerCodeFourDigits) {
            // 4桁以外が含まれていた場合エラーを返す
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH,
                    getMessage(ERRORS_BROKERCODE_NOTLENGTH, new String[] {
                            brokerCodeErrorMessage.substring(0, brokerCodeErrorMessage.length() - 2) })));
        }
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeArrayList(brokerCodeList);
        
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        DataList<IfaCustomerAlertCsvOutputA002aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerAlertCsvOutputService", "csvOutputA002a",
                new TypeReference<DataList<IfaCustomerAlertCsvOutputA002aResponseDto>>() {
                }, appReq, frameworkSessionId);
        
        DataList<IfaCustomerAlertCsvOutputA002aApiResponse> apiRes = new DataList<IfaCustomerAlertCsvOutputA002aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/wholePortal/ifaCustomerAlertCsvOutputCsvOutputA002b
     * アクションID：A002b
     * アクション名：CSV出力（ダウンロード）
     * APIリクエスト：IfaCustomerAlertCsvOutputA002bApiRequest
     * Apiレスポンス：IfaCustomerAlertCsvOutputA002bApiResponse
     * Dtoリクエスト：IfaCustomerAlertCsvOutputA002bRequestDto
     * Dtoレスポンス：IfaCustomerAlertCsvOutputA002bResponseDto
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @PostMapping(value = "/wholePortal/ifaCustomerAlertCsvOutputCsvOutputA002b")
    public void csvOutputA002b(@RequestBody IfaCustomerAlertCsvOutputA002bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }

    @Override
    protected String getCsvHeader() {

        return IfaCustomerAlertCsvOutputCsvOut.getHeaders();
    }
    
    /**
     * カンマ区切りの文字列をリストに変換する。<br/>
     * null、空文字の場合は空のリストを返却する
     *
     * @param list カンマ区切り文字列
     * @return リスト
     */
    public List<String> splitStringToList(String list) {
        
        if (list == null || list.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> retList = new ArrayList<String>();
        retList.addAll(Arrays.asList(list.split(",")));
        return retList;
    }
}
