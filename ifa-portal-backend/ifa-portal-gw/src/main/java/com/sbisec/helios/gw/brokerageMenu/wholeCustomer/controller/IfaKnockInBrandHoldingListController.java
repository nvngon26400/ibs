package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockInBrandHoldingListA004aDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA002ApiRequestCourseSelected;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockInBrandHoldingListA004bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB020301_03-02
 * 画面名：ノックイン銘柄保有一覧
 *
 * @author SCSK 大崎
 2024/06/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_03-02", screenNumber = "")
public class IfaKnockInBrandHoldingListController extends BaseController {
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** "{0}を選択してください。"*/
    private static final String ERRORS_SELECTED = "errors.selected";
    
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}]. */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CONTENT_TYPE = "application/octet-stream;";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaKnockInBrandHoldingListController.class);
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaKnockInBrandHoldingListA002ApiRequest
     * Apiレスポンス：IfaKnockInBrandHoldingListA002ApiResponse
     * Dtoリクエスト：IfaKnockInBrandHoldingListA002DtoRequest
     * Dtoレスポンス：IfaKnockInBrandHoldingListA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListDisplayA002")
    @ResponseJson
    @ResponseBody
    public String displayA002(@RequestBody IfaKnockInBrandHoldingListA002ApiRequest apiReq) throws Exception {
        
        //==============================
        // リクエスト.取引コースのチェック
        //==============================
        Boolean isCourseSelected = false;
        for (IfaKnockInBrandHoldingListA002ApiRequestCourseSelected course : apiReq.getCourseSelected()) {
            if (course.getIsSelected()) {
                isCourseSelected = true;
            }
        }
        
        // リクエスト.取引コースが未設定の場合エラー返却
        if (!isCourseSelected) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_SELECTED,
                    IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { "取引コース" })));
        }
        
        //==============================
        // リクエスト.仲介業者コードのチェック
        //==============================
        if (!StringUtil.isNullOrEmpty(apiReq.getBrokerCode())) {
            String msgBrokerCode = this.validateBrokerCodeExcludeList(apiReq.getBrokerCode());
            if (!"".equals(msgBrokerCode)) {
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH,
                        msgBrokerCode));
            }
            
            List<String> brokerCodeList = new ArrayList<String>();
            String brokerCode = apiReq.getBrokerCode();
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
            apiReq.setBrokerCodeList(brokerCodeList);
        }
        
        //==============================
        // A002サービスの呼び出し
        //==============================
        IfaKnockInBrandHoldingListA002DtoRequest appReq = new IfaKnockInBrandHoldingListA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaKnockInBrandHoldingListA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaKnockInBrandHoldingListService", "displayA002",
                new TypeReference<DataList<IfaKnockInBrandHoldingListA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaKnockInBrandHoldingListA002ApiResponse> apiRes = new DataList<IfaKnockInBrandHoldingListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListGetPDFA004a
     * アクションID：A004
     * アクション名：PDF取得
     * APIリクエスト：IfaKnockInBrandHoldingListA004aApiRequest
     * Apiレスポンス：IfaKnockInBrandHoldingListA004aApiResponse
     *
     * @param apiReq リクエスト
     * @return JSON文字列
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListGetPDFA004a")
    public String getPdfA004a(@RequestBody IfaKnockInBrandHoldingListA004aApiRequest apiReq) throws Exception {
        
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaKnockInBrandHoldingListService",
        "getPdfA004a", new TypeReference<DataList<String>>() {
        }, new IfaKnockInBrandHoldingListA004aDtoRequest());

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        // リクエストのURLからファイル名部分だけ抜き出す
        String fileName = apiReq.getPdfNoticeUrl();
        if (fileName != null && !fileName.isEmpty()) {
            int lastSlashIndex = fileName.lastIndexOf("/");
            fileName = fileName.substring(lastSlashIndex + 1);
        }

        // PDFファイルパス　=　SQL002.ファイルディレクトリ　+　リクエスト.PDF通知URLのファイル名
        String filePath = fileDirectory + fileName;
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("ifaKnockInBrandHoldingListGetPDFA004a File does not exist or is not a file: " + filePath);
        }

        var apiRes = new IfaKnockInBrandHoldingListA004aApiResponse(filePath);
        return jc.toString(
                IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListGetPDFA004b
     * アクションID：A004
     * アクション名：PDF取得
     * APIリクエスト：IfaKnockInBrandHoldingListA004bApiRequest
     *
     * @param apiReq リクエスト
     * @param response HTTPレスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockInBrandHoldingListGetPDFA004b")
    public void getPdfA004b(@RequestBody IfaKnockInBrandHoldingListA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        //==============================
        // PDFファイルダウンロード
        //==============================
        try {
            String fmFileName = new File(apiReq.getPdfFileName()).getName();
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION,
                    HEADER_VALUE_ATTACHMENT + UriUtils.encode(fmFileName, "UTF-8"));
            
            IOUtils.copy(new FileInputStream(apiReq.getPdfFileName()), response.getOutputStream());
            
        } catch (FileNotFoundException e) {
            //例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ifaKnockInBrandHoldingListGetPDFA004b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }
    
    /**
    * 仲介業者コードが固定長4桁区切りになっているかのチェック
    *
    * @param brokerCode 仲介業者コード
    * @return String　エラーとなっている入力した仲介業者コード
    */
    private String validateBrokerCodeExcludeList(String brokerCodeExcludeList) {
        
        int strLength = 0; // 入力した仲介業者コードの長さ
        String msgBrokerCodeNotLength = ""; // 仲介業者の長さが4桁ではないmessage
        
        // 入力した仲介業者除外コードをリストに分ける
        List<String> brokerCodeList = new ArrayList<String>();
        brokerCodeList.addAll(Arrays.asList(brokerCodeExcludeList.split(",")));
        
        // 仲介業者を4桁で入力チェック
        for (String str : brokerCodeList) {
            strLength = str.length();
            if (strLength != 4) {
                msgBrokerCodeNotLength += str + ", ";
            }
        }
        if (!StringUtil.isNullOrEmpty(msgBrokerCodeNotLength)) {
            return getMessage(ERRORS_BROKERCODE_NOTLENGTH,
                    new String[] { msgBrokerCodeNotLength.substring(0, msgBrokerCodeNotLength.length() - 2) });
        }
        
        return "";
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
