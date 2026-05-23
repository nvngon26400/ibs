package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaKnockOutBrandHoldingListA004aDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA002ApiRequestCourseSelected;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaKnockOutBrandHoldingListA004bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/13 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_03-03", screenNumber = "")
public class IfaKnockOutBrandHoldingListController extends BaseController {
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** "{0}を選択してください。"*/
    private static final String ERRORS_SELECTED = "errors.selected";
    
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}]. */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";
    
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CONTENT_TYPE = "application/octet-stream;";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaKnockOutBrandHoldingListController.class);
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaKnockOutBrandHoldingListA002ApiRequest
     * Apiレスポンス：IfaKnockOutBrandHoldingListA002ApiResponse
     * Dtoリクエスト：IfaKnockOutBrandHoldingListA002DtoRequest
     * Dtoレスポンス：IfaKnockOutBrandHoldingListA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListDisplayA002")
    public String displayA002(@RequestBody IfaKnockOutBrandHoldingListA002ApiRequest apiReq) throws Exception {
        
        //==============================
        // リクエスト.取引コースのチェック
        //==============================
        Boolean isCourseSelected = false;
        for (IfaKnockOutBrandHoldingListA002ApiRequestCourseSelected course : apiReq.getCourseSelected()) {
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
        IfaKnockOutBrandHoldingListA002DtoRequest appReq = new IfaKnockOutBrandHoldingListA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaKnockOutBrandHoldingListA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaKnockOutBrandHoldingListService", "displayA002",
                new TypeReference<DataList<IfaKnockOutBrandHoldingListA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaKnockOutBrandHoldingListA002ApiResponse> apiRes = new DataList<IfaKnockOutBrandHoldingListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListGetPDFA004a
     * アクションID：A004
     * アクション名：PDF取得
     * APIリクエスト：IfaKnockOutBrandHoldingListA004aApiRequest
     * Apiレスポンス：IfaKnockOutBrandHoldingListA004aApiResponse
     *
     * @param apiReq リクエスト
     * @return json文字列
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListGetPDFA004a")
    public String getPdfA004a(@RequestBody IfaKnockOutBrandHoldingListA004aApiRequest apiReq) throws Exception {
        
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaKnockOutBrandHoldingListService",
        "getPdfA004a", new TypeReference<DataList<String>>() {
        }, new IfaKnockOutBrandHoldingListA004aDtoRequest());

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
            throw new FileNotFoundException("ifaKnockOutBrandHoldingListGetPDFA004a File does not exist or is not a file: " + filePath);
        }

        var apiRes = new IfaKnockOutBrandHoldingListA004aApiResponse(filePath);
        return jc.toString(
                IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
        
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListGetPDFA004b
     * アクションID：A004
     * アクション名：PDF取得
     * APIリクエスト：IfaKnockOutBrandHoldingListA004bApiRequest
     *
     * @param apiReq リクエスト
     * @param response HTTPレスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaKnockOutBrandHoldingListGetPDFA004b")
    public void getPdfA004b(@RequestBody IfaKnockOutBrandHoldingListA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
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
                LOGGER.debug("ifaKnockOutBrandHoldingListGetPDFA004b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }
    
    /**
    * 仲介業者コードが固定長4桁区切りになっているかのチェック（共通化されるまで仮）
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
