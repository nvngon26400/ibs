package com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util.IfaBrokerCustomerCsvOutput;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA004ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA004bApiRequest;


/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0407_01", screenNumber = "")
public class IfaMonthCustomerNumController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** 月末口座数_CSVファイルの接頭語 */
    private static final String MONTH_STAR_UPLOAD_CSV_FILE_NAME = "MONTH_CUSTOMER_NUM";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMonthCustomerNumController.class);
    
    /**
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMonthCustomerNumA001ApiRequest
     * APIレスポンス：IfaMonthCustomerNumA001ApiResponse
     * DTOリクエスト：IfaMonthCustomerNumA001DtoRequest
     * DTOレスポンス：IfaMonthCustomerNumA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumInitializeA001")
    public String initializeA001(@RequestBody IfaMonthCustomerNumA001ApiRequest apiReq) throws Exception {
        
        IfaMonthCustomerNumA001RequestDto appReq = new IfaMonthCustomerNumA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMonthCustomerNumA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMonthCustomerNumService", "initializeA001",
                new TypeReference<DataList<IfaMonthCustomerNumA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMonthCustomerNumA001ApiResponse> apiRes = new DataList<IfaMonthCustomerNumA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaMonthCustomerNumA002ApiRequest
     * APIレスポンス：IfaMonthCustomerNumA002ApiResponse
     * DTOリクエスト：IfaMonthCustomerNumA002DtoRequest
     * DTOレスポンス：IfaMonthCustomerNumA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumDisplayA002")
    public String displayA002(@RequestBody IfaMonthCustomerNumA002ApiRequest apiReq) throws Exception {
        
        DataList<IfaMonthCustomerNumA002ApiResponse> apiRes = new DataList<IfaMonthCustomerNumA002ApiResponse>();
        
        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());
        
        IfaMonthCustomerNumA002RequestDto appReq = new IfaMonthCustomerNumA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);
        
        DataList<IfaMonthCustomerNumA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMonthCustomerNumService", "displayA002",
                new TypeReference<DataList<IfaMonthCustomerNumA002ResponseDto>>() {
                }, appReq);
        
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumCsvOutputA004
     * アクションID：A004
     * アクション名：月末口座数CSV出力
     * APIリクエスト：IfaMonthCustomerNumA004ApiRequest
     * APIレスポンス：IfaMonthCustomerNumA004ApiResponse
     * Dtoリクエスト：IfaMonthCustomerNumA004RequestDto
     * Dtoレスポンス：IfaMonthCustomerNumA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumCsvOutputA004")
    public String csvOutputA004(@RequestBody IfaMonthCustomerNumA004ApiRequest apiReq) throws Exception {

        DataList<IfaMonthCustomerNumA004ApiRequest> apiRes = new DataList<IfaMonthCustomerNumA004ApiRequest>();
        // 仲介業者コードをカンマ区切りで分割しリストに格納
        List<String> brokerCodeList = splitStringToList(apiReq.getBrokerCode());

        IfaMonthCustomerNumA004RequestDto appReq = new IfaMonthCustomerNumA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        appReq.setBrokerCodeList(brokerCodeList);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaMonthCustomerNumA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMonthCustomerNumService",
                "csvOutputA004", new TypeReference<DataList<IfaMonthCustomerNumA004ResponseDto>>() {
                }, appReq, fwSessionId);
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumCsvDownloadA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：ifaMonthCustomerNumA004bApiRequest
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaMonthCustomerNumCsvDownloadA004")
    public void csvOutputA004b(@RequestBody IfaMonthCustomerNumA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaMonthCustomerNumController.csvOutputA004b >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // Star ファイル ダウンロード LF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(MONTH_STAR_UPLOAD_CSV_FILE_NAME),
                userAccount, null);
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
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
    
    @Override
    protected String getCsvHeader() {

        return IfaBrokerCustomerCsvOutput.getHeaders();
    }

    @Override
    protected String getFirstViewName() {

        return null;

    }
}
