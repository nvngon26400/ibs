package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IfaBbApplyListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListA006ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListX002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyListX002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0204_02-01
 * 画面名：BB申込一覧
 *
 * @author BASE李
 *
 2024/03/14 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-01", screenNumber = "55")
public class IfaBbApplyListController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyListController.class);
    
    private JsonConverter jc = JsonConverter.getInstance();
    private static final String NRI_API = "NRI_API";
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "BB申込";
    
    @Override
    protected String getCsvHeader(String pattern) {
        return IfaBbApplyListCsvOut.getHeader(pattern);
    }
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyListDisplayX002
     * アクションID：X002
     * アクション名：表示
     * APIリクエスト：IfaBbApplyListX002ApiRequest
     * Apiレスポンス：IfaBbApplyListX002ApiResponse
     * Dtoリクエスト：IfaBbApplyListX002RequestDto
     * Dtoレスポンス：IfaBbApplyListX002ResponseDto
     *
     * @param apiReq リクエストBody
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyListDisplayX002")
    public String displayX002(@RequestBody IfaBbApplyListX002ApiRequest apiReq) throws Exception {

        IfaBbApplyListX002RequestDto appReq = new IfaBbApplyListX002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyListX002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyListService",
                "displayX002", new TypeReference<DataList<IfaBbApplyListX002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyListX002ApiResponse> apiRes = new DataList<IfaBbApplyListX002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyListCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力(サーバでファイル生成)
     * APIリクエスト：IfaBbApplyListA004ApiRequest
     * Apiレスポンス：IfaBbApplyListA004ApiResponse
     * Dtoリクエスト：IfaBbApplyListA004RequestDto
     * Dtoレスポンス：IfaBbApplyListA004ResponseDto
     *
     * @param apiReq リクエストBody
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyListCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaBbApplyListA004aApiRequest apiReq)throws Exception {

        IfaBbApplyListA004aRequestDto appReq = new IfaBbApplyListA004aRequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyListA004aResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyListService",
                "csvOutputA004", new TypeReference<DataList<IfaBbApplyListA004aResponseDto>>() {
                }, appReq, IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        DataList<IfaBbApplyListA004aApiResponse> apiRes = new DataList<IfaBbApplyListA004aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }


    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyListCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力(プラウザでダウンロード)
     * APIリクエスト：IfaBbApplyListA004ApiRequest
     * Apiレスポンス：IfaBbApplyListA004ApiResponse
     * Dtoリクエスト：IfaBbApplyListA004RequestDto
     * Dtoレスポンス：IfaBbApplyListA004ResponseDto
     *
     * @param apiReq リクエストBody
     * @param response httpレスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyListCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaBbApplyListA004bApiRequest apiReq, HttpServletResponse response) throws Exception {

        long start = System.currentTimeMillis();
        LOGGER.debug("IfaBbApplyListController.csvOutputA004b >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // Star ファイル ダウンロード CRLF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME), userAccount, apiReq.getPattern());


        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }


    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyListBbCancellationCorrectionA005
     * アクションID：A005
     * アクション名：BB取消訂正
     * APIリクエスト：IfaBbApplyListA005ApiRequest
     * Apiレスポンス：IfaBbApplyListA005ApiResponse
     * Dtoリクエスト：IfaBbApplyListA005RequestDto
     * Dtoレスポンス：IfaBbApplyListA005ResponseDto
     *
     * @param apiReq リクエストBody
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyListBbCancellationCorrectionA005")
    public String bbCancellationCorrectionA005(@RequestBody IfaBbApplyListA005ApiRequest apiReq) throws Exception {
        // ① サービス閉塞チェックで、サービス閉塞状態を判定する。
        Boolean status = ApiRequestUtil.invoke("serviceStatusService", "getServiceStatus",
                new TypeReference<Boolean>() {
                }, NRI_API);
        if (!status) {
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, RETURN_CODE_W0006, getMessage(RETURN_CODE_W0006, new String[] {})));
        }
        
        IfaBbApplyListA005RequestDto appReq = new IfaBbApplyListA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyListA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyListService",
                "bbCancellationCorrectionA005", new TypeReference<DataList<IfaBbApplyListA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyListA005ApiResponse> apiRes = new DataList<IfaBbApplyListA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyListSubscriptInputA006
     * アクションID：A006
     * アクション名：募集入力
     * APIリクエスト：IfaBbApplyListA006ApiRequest
     * Apiレスポンス：IfaBbApplyListA006ApiResponse
     * Dtoリクエスト：IfaBbApplyListA006RequestDto
     * Dtoレスポンス：IfaBbApplyListA006ResponseDto
     *
     * @param apiReq リクエストBody
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyListSubscriptInputA006")
    public String subscriptInputA006(@RequestBody IfaBbApplyListA006ApiRequest apiReq) throws Exception {
        // ① サービス閉塞チェックで、サービス閉塞状態を判定する。
        Boolean status = ApiRequestUtil.invoke("serviceStatusService", "getServiceStatus",
                new TypeReference<Boolean>() {
                }, NRI_API);
        if (!status) {
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, RETURN_CODE_W0006, getMessage(RETURN_CODE_W0006, new String[] {})));
        }
        
        IfaBbApplyListA006RequestDto appReq = new IfaBbApplyListA006RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyListA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyListService",
                "subscriptInputA006", new TypeReference<DataList<IfaBbApplyListA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyListA006ApiResponse> apiRes = new DataList<IfaBbApplyListA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

