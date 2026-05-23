package com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.controller;

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
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.util.IfaBrokerCustomerCsvOutput;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA005ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form.IfaMonthCustomerNumA005bApiRequest;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0407_01", screenNumber = "76")
public class IfaBrokerCustomerController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerCustomerController.class);
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** 仲介業者顧客_CSVファイルの接頭語 */
    private static final String BROKER_STAR_UPLOAD_CSV_FILE_NAME = "MONTH_CUSTOMER";
    
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaBrokerCustomerCsvOutputA005
     * アクションID：A005
     * アクション名：仲介業者顧客CSV出力
     * APIリクエスト：IfaMonthCustomerNumA005ApiRequest
     * APIレスポンス：IfaMonthCustomerNumA005ApiResponse
     * Dtoリクエスト：IfaMonthCustomerNumA005RequestDto
     * Dtoレスポンス：IfaMonthCustomerNumA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaBrokerCustomerCsvOutputA005")
    public String csvOutputA005(@RequestBody IfaMonthCustomerNumA005ApiRequest apiReq) throws Exception {
        
        DataList<IfaMonthCustomerNumA005ApiRequest> apiRes = new DataList<IfaMonthCustomerNumA005ApiRequest>();
        IfaMonthCustomerNumA005RequestDto appReq = new IfaMonthCustomerNumA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaMonthCustomerNumA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMonthCustomerNumService",
                "csvOutputA005", new TypeReference<DataList<IfaMonthCustomerNumA005ResponseDto>>() {
                }, appReq, fwSessionId);
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    
    /**
     * 
     * アクセス：/internalAdminMenu/monthCustomerNum/ifaBrokerCustomerCsvDownloadA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：ifaMonthCustomerNumA005bApiRequest
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthCustomerNum/ifaBrokerCustomerCsvDownloadA005")
    public void csvOutputA005b(@RequestBody IfaMonthCustomerNumA005bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaMonthCustomerNumController.csvOutputA005b >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // Star ファイル ダウンロード LF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(BROKER_STAR_UPLOAD_CSV_FILE_NAME),
                userAccount, null);
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
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
