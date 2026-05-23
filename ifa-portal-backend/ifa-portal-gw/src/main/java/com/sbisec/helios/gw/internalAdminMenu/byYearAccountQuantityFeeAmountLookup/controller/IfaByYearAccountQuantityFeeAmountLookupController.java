package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.util.IfaByYearAccountQuantityFeeAmountLookupA004CsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi001Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi001Response;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi002Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi002Response;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi004aRequest;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi004aResponse;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi004bRequest;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi005Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi005Response;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0406-01", screenNumber = "")
public class IfaByYearAccountQuantityFeeAmountLookupController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaByYearAccountQuantityFeeAmountLookupController.class);
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME1 = "年度別口座数・報酬額";
    
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi001Request
     * Api レスポンス：IfaByYearAccountQuantityFeeAmountLookupApi001Response
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA001RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupInitializeA001")
    public String initializeA001(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi001Request apiReq) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupA001RequestDto appReq = new IfaByYearAccountQuantityFeeAmountLookupA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaByYearAccountQuantityFeeAmountLookupService", "initializeA001",
                new TypeReference<DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto>>() {
                }, appReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupApi001Response> apiRes =
            new DataList<IfaByYearAccountQuantityFeeAmountLookupApi001Response>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupDisplayA002
     * アクションID：A002
     * アクション名：年度別口座数・報酬額情報取得
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi002Request
     * Api レスポンス：IfaByYearAccountQuantityFeeAmountLookupApi002Response
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA002RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupDisplayA002")
    public String displayA002(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi002Request apiReq) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupA002RequestDto appReq = new IfaByYearAccountQuantityFeeAmountLookupA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaByYearAccountQuantityFeeAmountLookupService", "displayA002",
                new TypeReference<DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto>>() {
                }, appReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupApi002Response> apiRes =
            new DataList<IfaByYearAccountQuantityFeeAmountLookupApi002Response>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvOutputA004
     * アクションID：A004
     * アクション名：CSV出力
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi004aRequest
     * Api レスポンス：IfaByYearAccountQuantityFeeAmountLookupApi004aResponse
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA004RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvOutputA004")
    public String csvOutputA004(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi004aRequest apiReq) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupA004RequestDto appReq = new IfaByYearAccountQuantityFeeAmountLookupA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaByYearAccountQuantityFeeAmountLookupService", "csvOutputA004",
                new TypeReference<DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupApi004aResponse> apiRes =
            new DataList<IfaByYearAccountQuantityFeeAmountLookupApi004aResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
        
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvDownloadA004
     * アクションID：A004
     * アクション名：CSV出力
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi004bRequest
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvDownloadA004")
    public void csvDownLoadA004(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi004bRequest apiReq,
        HttpServletResponse response) throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupController.csvDownloadA004 >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME1), userAccount);
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis()) - start);
        
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupGetClosingMonthA005
     * アクションID：A005
     * アクション名：仲介業者決算月情報取得
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi005Request
     * Api レスポンス：IfaByYearAccountQuantityFeeAmountLookupApi005Response
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA005RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupGetClosingMonthA005")
    public String getClosingMonthA005(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi005Request apiReq) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupA005RequestDto appReq = new IfaByYearAccountQuantityFeeAmountLookupA005RequestDto();
        
     // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaByYearAccountQuantityFeeAmountLookupService", "getClosingMonthA005",
                new TypeReference<DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto>>() {
                }, appReq);

        DataList<IfaByYearAccountQuantityFeeAmountLookupApi005Response> apiRes =
            new DataList<IfaByYearAccountQuantityFeeAmountLookupApi005Response>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    @Override
    protected String getCsvHeader() {

        return IfaByYearAccountQuantityFeeAmountLookupA004CsvOut.getHeaders();
    }

    @Override
    protected String getFirstViewName() {

        return null;

    }
    
}
