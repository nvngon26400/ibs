package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaInquirySearchForManagerCsvOut;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA006aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA006aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaInquirySearchForManagerA006bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020304-01", screenNumber = "79", ignoreCheck = true)
public class IfaInquirySearchForManagerController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInquirySearchForManagerController.class);
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "QUESTION_ANSWER_IFA_HIS";
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaInquirySearchForManagerA001ApiRequest
     * Apiレスポンス：IfaInquirySearchForManagerA001ApiResponse
     * Dtoリクエスト：IfaInquirySearchForManagerA001RequestDto
     * Dtoレスポンス：IfaInquirySearchForManagerA001ResponseDto
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerInitializeA001")
    public String initializeA001(@RequestBody IfaInquirySearchForManagerA001ApiRequest apiReq) throws Exception {
        
        IfaInquirySearchForManagerA001RequestDto appReq = new IfaInquirySearchForManagerA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInquirySearchForManagerA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInquirySearchForManagerService", "initializeA001", new TypeReference<DataList<IfaInquirySearchForManagerA001ResponseDto>> () {
        }, appReq);
        
        DataList<IfaInquirySearchForManagerA001ApiResponse> apiRes = new DataList<IfaInquirySearchForManagerA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCategoryChangeA002
     * アクションID：A002
     * アクション名：カテゴリ（中）取得
     * APIリクエスト：IfaInquirySearchForManagerA002ApiRequest
     * Apiレスポンス：IfaInquirySearchForManagerA002ApiResponse
     * Dtoリクエスト：IfaInquirySearchForManagerA002RequestDto
     * Dtoレスポンス：IfaInquirySearchForManagerA002ResponseDto
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCategoryChangeA002")
    public String categoryChangeA002(@RequestBody IfaInquirySearchForManagerA002ApiRequest apiReq) throws Exception {
        
        IfaInquirySearchForManagerA002RequestDto appReq = new IfaInquirySearchForManagerA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInquirySearchForManagerA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInquirySearchForManagerService", "categoryChangeA002", new TypeReference<DataList<IfaInquirySearchForManagerA002ResponseDto>> () {
        }, appReq);
        
        DataList<IfaInquirySearchForManagerA002ApiResponse> apiRes = new DataList<IfaInquirySearchForManagerA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCategoryChangeA003
     * アクションID：A003
     * アクション名：カテゴリ（小）取得
     * APIリクエスト：IfaInquirySearchForManagerA003ApiRequest
     * Apiレスポンス：IfaInquirySearchForManagerA003ApiResponse
     * Dtoリクエスト：IfaInquirySearchForManagerA003RequestDto
     * Dtoレスポンス：IfaInquirySearchForManagerA003ResponseDto
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCategoryChangeA003")
    public String categoryChangeA003(@RequestBody IfaInquirySearchForManagerA003ApiRequest apiReq) throws Exception {
        
        IfaInquirySearchForManagerA003RequestDto appReq = new IfaInquirySearchForManagerA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInquirySearchForManagerA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInquirySearchForManagerService", "categoryChangeA003", new TypeReference<DataList<IfaInquirySearchForManagerA003ResponseDto>> () {
        }, appReq);
        
        DataList<IfaInquirySearchForManagerA003ApiResponse> apiRes = new DataList<IfaInquirySearchForManagerA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerDisplayA004
     * アクションID：A004
     * アクション名：表示
     * APIリクエスト：IfaInquirySearchForManagerA004ApiRequest
     * Apiレスポンス：IfaInquirySearchForManagerA004ApiResponse
     * Dtoリクエスト：IfaInquirySearchForManagerA004RequestDto
     * Dtoレスポンス：IfaInquirySearchForManagerA004ResponseDto
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerDisplayA004")
    public String displayA004(@RequestBody IfaInquirySearchForManagerA004ApiRequest apiReq) throws Exception {
        
        IfaInquirySearchForManagerA004RequestDto appReq = new IfaInquirySearchForManagerA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInquirySearchForManagerA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInquirySearchForManagerService", "displayA004", new TypeReference<DataList<IfaInquirySearchForManagerA004ResponseDto>> () {
        }, appReq);
        
        DataList<IfaInquirySearchForManagerA004ApiResponse> apiRes = new DataList<IfaInquirySearchForManagerA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCsvOutputA006a
     * アクションID：A006a
     * アクション名：CSV出力
     * APIリクエスト：IfaInquirySearchForManagerA006aApiRequest
     * Apiレスポンス：IfaInquirySearchForManagerA006aApiResponse
     * Dtoリクエスト：IfaInquirySearchForManagerA006RequestDto
     * Dtoレスポンス：IfaInquirySearchForManagerA006ResponseDto
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCsvOutputA006a")
    public String csvOutputA006a(@RequestBody IfaInquirySearchForManagerA006aApiRequest apiReq) throws Exception {
        
        IfaInquirySearchForManagerA006RequestDto appReq = new IfaInquirySearchForManagerA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaInquirySearchForManagerA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaInquirySearchForManagerService", "csvOutputA006", new TypeReference<DataList<IfaInquirySearchForManagerA006ResponseDto>> () {
        }, appReq, fwSessionId);
        
        DataList<IfaInquirySearchForManagerA006aApiResponse> apiRes = new DataList<IfaInquirySearchForManagerA006aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCsvOutputA006b
     * アクションID：A006b
     * アクション名：CSV出力
     * APIリクエスト：IfaInquirySearchForManagerA006bApiRequest
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaInquirySearchForManagerCsvOutputA006b")
    public void csvOutputA006b(@RequestBody IfaInquirySearchForManagerA006bApiRequest apiReq, HttpServletResponse response) throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaInquirySearchForManagerController.csvOutputA006b >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME), userAccount);

        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }
    
    @Override
    protected String getCsvHeader() {

        return IfaInquirySearchForManagerCsvOut.getHeaders();
    }

    @Override
    protected String getFirstViewName() {

        return null;

    }
    
}
