package com.sbisec.helios.gw.brokerageMenu.jointMarket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.util.IfaJointMarketTradeSearchCsvOut;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA004OutputApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA004OutputApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointMarket.form.IfaJointMarketTradeSearchA004DownloadApiRequest;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0208_01-01", screenNumber = "57")
public class IfaJointMarketTradeSearchController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointMarketTradeSearchController.class);

    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "共同店舗取引検索";

    /**
     * 
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTradeSearchInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Api リクエスト：IfaJointMarketTradeSearchA001ApiRequest
     * Api レスポンス：IfaJointMarketTradeSearchA001ApiResponse
     * Dto リクエスト：IfaJointMarketTradeSearchaA001RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTradeSearchInitializeA001")
    public String initializeA001(@RequestBody IfaJointMarketTradeSearchA001ApiRequest apiReq) throws Exception {
        IfaJointMarketTradeSearchA001RequestDto appReq = new IfaJointMarketTradeSearchA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJointMarketTradeSearchA001ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaJointMarketTradeSearchService", "initializeA001",
                new TypeReference<DataList<IfaJointMarketTradeSearchA001ResponseDto>>() {
                }, appReq);

        DataList<IfaJointMarketTradeSearchA001ApiResponse> apiRes =
            new DataList<IfaJointMarketTradeSearchA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTradeSearchDisplayA002
     * アクションID：A002
     * アクション名：表示
     * Api リクエスト：IfaJointMarketTradeSearchA002ApiRequest
     * Api レスポンス：IfaJointMarketTradeSearchA002ApiResponse
     * Dto リクエスト：IfaJointMarketTradeSearchaA002RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTradeSearchDisplayA002")
    public String displayA002(@RequestBody IfaJointMarketTradeSearchA002ApiRequest apiReq) throws Exception {

        IfaJointMarketTradeSearchA002RequestDto appReq = new IfaJointMarketTradeSearchA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJointMarketTradeSearchA002ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaJointMarketTradeSearchService", "displayA002",
                new TypeReference<DataList<IfaJointMarketTradeSearchA002ResponseDto>>() {
                }, appReq);

        DataList<IfaJointMarketTradeSearchA002ApiResponse> apiRes =
            new DataList<IfaJointMarketTradeSearchA002ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTradeSearchCsvOutputA004
     * アクションID：A004 アクション名：CSV出力 APIリクエスト：IfaJointMarketTradeSearchA004OutputApiRequest
     * Api レスポンス：IfaJointMarketTradeSearchA004OutputApiResponse
     * Dto リクエスト：IfaJointMarketTradeSearchA004RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTradeSearchCsvOutputA004")
    public String csvOutputA004(@RequestBody IfaJointMarketTradeSearchA004OutputApiRequest apiReq) throws Exception {

        IfaJointMarketTradeSearchA004RequestDto appReq = new IfaJointMarketTradeSearchA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaJointMarketTradeSearchA004ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaJointMarketTradeSearchService", "csvOutputA004",
                new TypeReference<DataList<IfaJointMarketTradeSearchA004ResponseDto>>() {
                }, appReq, fwSessionId);

        DataList<IfaJointMarketTradeSearchA004OutputApiResponse> apiRes =
            new DataList<IfaJointMarketTradeSearchA004OutputApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);

    }

    /**
     * 
     * アクセス：/brokerageMenu/jointMarket/ifaJointMarketTradeSearchCsvDownloadA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaJointMarketTradeSearchA004DownloadApiRequest
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointMarket/ifaJointMarketTradeSearchCsvDownloadA004")
    public void csvDownloadA004(@RequestBody IfaJointMarketTradeSearchA004DownloadApiRequest apiReq,
        HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaJointMarketTradeSearchController.csvDownloadA004 >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME), userAccount);

        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }

    @Override
    protected String getCsvHeader() {

        return IfaJointMarketTradeSearchCsvOut.getHeaders();
    }

    @Override
    protected String getFirstViewName() {

        return null;

    }
}
