package com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.controller;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA003aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA003aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form.IfaPayNotificationDocDownloadA003bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0405-01", screenNumber = "")
public class IfaPayNotificationDocDownloadController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}] */
    private static final String ERRORS_BROKERCODE_NOT_LENGTH = "errors.brokerCodeNotLength";
    
    /** {0}ToにはFromと同日以降の日付を指定して下さい。 */
    private static final String ERRORS_DATE_SPECIFY_FROM_TO = "errors.date.specifyFromTo";
    
    // --------------------------------
    // ファイルダウンロード情報
    // --------------------------------  
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CONTENT_TYPE = "application/octet-stream;";
    
    // --------------------------------
    // エンコーディング
    // --------------------------------  
    private static final String ENCODING_1 = "ms932";
    
    private static final String ENCODING_2 = "iso-8859-1";
    
    private static final String URL_ENCODING_TARGET = "#";
    
    private static final String URL_ENCODING_REPLACEMNET = "%23";
    
    /**
     * アクセス：/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaPayNotificationDocDownloadA001ApiRequest
     * APIレスポンス：IfaPayNotificationDocDownloadA001ApiResponse
     * DTOリクエスト：IfaPayNotificationDocDownloadA001DtoRequest
     * DTOレスポンス：IfaPayNotificationDocDownloadA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadInitializeA001")
    public String initializeA001(@RequestBody IfaPayNotificationDocDownloadA001ApiRequest apiReq) throws Exception {
        
        IfaPayNotificationDocDownloadA001DtoRequest appReq = new IfaPayNotificationDocDownloadA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaPayNotificationDocDownloadA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaPayNotificationDocDownloadService", "initializeA001",
                new TypeReference<DataList<IfaPayNotificationDocDownloadA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaPayNotificationDocDownloadA001ApiResponse> apiRes = new DataList<IfaPayNotificationDocDownloadA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * アクセス：/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadDisplayA002
    * アクションID：A002
    * アクション名：表示
    * APIリクエスト：IfaPayNotificationDocDownloadA002ApiRequest
    * APIレスポンス：IfaPayNotificationDocDownloadA002ApiResponse
    * DTOリクエスト：IfaPayNotificationDocDownloadA002DtoRequest
    * DTOレスポンス：IfaPayNotificationDocDownloadA002DtoResponse
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadDisplayA002")
    public String displayA002(@RequestBody IfaPayNotificationDocDownloadA002ApiRequest apiReq) throws Exception {
        
        DataList<IfaPayNotificationDocDownloadA002ApiResponse> apiRes = new DataList<IfaPayNotificationDocDownloadA002ApiResponse>();
        
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
        
        // 対象年月From < 対象年月To チェック 
        if (!DateUtil.isValidFromTo(apiReq.getTargetDateYmFrom(), apiReq.getTargetDateYmTo(), DateUtil.YYYYMM,
                DateUtil.SEPARATED_YYYYMM)) {
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_DATE_SPECIFY_FROM_TO,
                    getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] { "対象年月" }));
            return jc.toString(apiRes);
        }
        
        IfaPayNotificationDocDownloadA002DtoRequest appReq = new IfaPayNotificationDocDownloadA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        
        DataList<IfaPayNotificationDocDownloadA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaPayNotificationDocDownloadService", "displayA002",
                new TypeReference<DataList<IfaPayNotificationDocDownloadA002DtoResponse>>() {
                }, appReq);
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadPdfDownloadA003a
     * アクションID：A003a
     * アクション名：ダウンロード
     * APIリクエスト：IfaPayNotificationDocDownloadA003aApiRequest
     * APIレスポンス：IfaPayNotificationDocDownloadA003aApiResponse
     * DTOリクエスト：IfaPayNotificationDocDownloadA003aDtoRequest
     * DTOレスポンス：IfaPayNotificationDocDownloadA003aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadPdfDownloadA003a")
    public String pdfDownloadA003a(@RequestBody IfaPayNotificationDocDownloadA003aApiRequest apiReq) throws Exception {
        
        IfaPayNotificationDocDownloadA003aDtoRequest appReq = new IfaPayNotificationDocDownloadA003aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaPayNotificationDocDownloadA003aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaPayNotificationDocDownloadService", "pdfDownloadA003a",
                new TypeReference<DataList<IfaPayNotificationDocDownloadA003aDtoResponse>>() {
                }, appReq);
        
        DataList<IfaPayNotificationDocDownloadA003aApiResponse> apiRes = new DataList<IfaPayNotificationDocDownloadA003aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadPdfDownloadA003b
     * アクションID：A003b
     * アクション名：ダウンロード
     * APIリクエスト：IfaPayNotificationDocDownloadA003bApiRequest
     * APIレスポンス：IfaPayNotificationDocDownloadA003bApiResponse
     * DTOリクエスト：IfaPayNotificationDocDownloadA003bDtoRequest
     * DTOレスポンス：IfaPayNotificationDocDownloadA003bDtoResponse
     *
     * @param apiReq リクエスト
     * @param response レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/payNotificationDocDownload/ifaPayNotificationDocDownloadPdfDownloadA003b")
    public void pdfDownloadA003b(@RequestBody IfaPayNotificationDocDownloadA003bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        // 支払通知書ファイルダウンロード
        Path path = Paths.get(apiReq.getPdfFileName());    
        String fixedDownloadFileName = new String(path.getFileName().toString().getBytes(ENCODING_1), ENCODING_2);
        fixedDownloadFileName = fixedDownloadFileName.replace(URL_ENCODING_TARGET, URL_ENCODING_REPLACEMNET);
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + fixedDownloadFileName);
        
        IOUtils.copy(new FileInputStream(apiReq.getPdfFileName()), response.getOutputStream());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
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
            brokerCodeErrorMessage = getMessage(ERRORS_BROKERCODE_NOT_LENGTH,
                    new String[] { checkedBrokerCode.substring(0, checkedBrokerCode.length() - 2) });
        }
        
        return brokerCodeErrorMessage;
    }
}
