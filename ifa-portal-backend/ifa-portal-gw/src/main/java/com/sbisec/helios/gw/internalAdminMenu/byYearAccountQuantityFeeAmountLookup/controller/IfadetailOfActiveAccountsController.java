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
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.util.IfaByYearAccountQuantityFeeAmountLookupA007CsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi007aRequest;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi007aResponse;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaByYearAccountQuantityFeeAmountLookupApi007bRequest;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/23
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0406-01", screenNumber = "77")
public class IfadetailOfActiveAccountsController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaByYearAccountQuantityFeeAmountLookupController.class);

    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME2 = "媒介口座明細";
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvOutputA007
     * アクションID：A007
     * アクション名：CSV出力
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi007aRequest
     * Api レスポンス：IfaByYearAccountQuantityFeeAmountLookupApi007aResponse
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA007RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvOutputA007")
    public String csvOutputA007(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi007aRequest apiReq) throws Exception {
        
        IfaByYearAccountQuantityFeeAmountLookupA007RequestDto appReq = new IfaByYearAccountQuantityFeeAmountLookupA007RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaByYearAccountQuantityFeeAmountLookupService", "csvOutputA007",
                new TypeReference<DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaByYearAccountQuantityFeeAmountLookupApi007aResponse> apiRes =
            new DataList<IfaByYearAccountQuantityFeeAmountLookupApi007aResponse>();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
        
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvDownloadA007
     * アクションID：A007
     * アクション名：CSV出力
     * Api リクエスト：IfaByYearAccountQuantityFeeAmountLookupApi007bRequest
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaByYearAccountQuantityFeeAmountLookupCsvDownloadA007")
    public void csvDownLoadA007(@RequestBody IfaByYearAccountQuantityFeeAmountLookupApi007bRequest apiReq,
        HttpServletResponse response) throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaByYearAccountQuantityFeeAmountLookupController.csvDownloadA007 >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME2), userAccount);
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis()) - start);
        
    }
    
    @Override
    protected String getCsvHeader() {

        return IfaByYearAccountQuantityFeeAmountLookupA007CsvOut.getHeaders();
    }

    @Override
    protected String getFirstViewName() {

        return null;

    }
}
