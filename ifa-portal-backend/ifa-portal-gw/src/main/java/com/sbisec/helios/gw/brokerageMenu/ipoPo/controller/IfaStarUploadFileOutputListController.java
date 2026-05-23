package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyInfoListModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoStarUploadCsvInfoListModel;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.ipopo.OrderStatus;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA002aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA002bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA002aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaStarUploadFileOutputListA001FileOutputListApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0204_02-05
 * 画面名：STARアップロードファイル出力一覧
 *
 * @author SCSK
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-05", screenNumber = StringUtil.EMPTY_STRING)
public class IfaStarUploadFileOutputListController extends BaseController {

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(IfaStarUploadFileOutputListController.class);
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "wkdcyup1256Z10";
    /** 機能ID */
    private static final String SYS_ID = "SUB0204_02-05";
    /** JsonConverter */
    private JsonConverter jc = JsonConverter.getInstance();

    /**
    * A001 初期化
    * 画面明細一覧を表示する。
    *
    * @param apiReq {@code IfaStarUploadFileOutputListA001ApiRequest }
    * @return {@code String}
    * @throws Exception 初期化処理で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaStarUploadFileOutputListInitializeA001")
    public String initializeA001(@RequestBody IfaStarUploadFileOutputListA001ApiRequest apiReq)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IpopoStarUploadFileController.initializeA001 >> {}", hashCode());

        String resultJson = StringUtil.EMPTY_STRING;

        // 権限処理（権限より、データを取得する） 
        // ユーザ情報を取得する
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // 権限
        String privId = userAccount.getPrivId();

        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            return resultJson;
        }
        // 画面表示一覧のデータを取得する
        DataList<IpopoBBApplyInfoListModel> dataList = getDataList();
        
        // Set error level and message
        setStatusAndMessageToDataList(dataList, false);
        
        if (dataList.isOverMaxRownum()) {
            String maxRownumDelimited = NumberFormat.getInstance(Locale.JAPAN).format(dataList.getMaxRownum());
            String totalSizeDelimited = NumberFormat.getInstance(Locale.JAPAN).format(dataList.getTotalSize());
            dataList.setMessage(IfaCommonUtil.getMessage(WARNINGS_CSV_OVER_MAX_ROWNUM,
                    new String[] { maxRownumDelimited, totalSizeDelimited }));
            dataList.setErrorLevel(ErrorLevel.WARNING.getId());
            dataList.setReturnCode(WARNINGS_CSV_OVER_MAX_ROWNUM);
        }
        if (dataList.getTotalSize() == 0) {
            dataList.setMessage(IfaCommonUtil.getMessage(INFO_ORDERED_DATA_NOT_EXIST, new String[] { "募集入力済" }));
            dataList.setErrorLevel(ErrorLevel.INFO.getId());
            dataList.setReturnCode(INFO_ORDERED_DATA_NOT_EXIST);
        }
        
        DataList<IfaStarUploadFileOutputListA001ApiResponse> apiResDataList = 
                new DataList<IfaStarUploadFileOutputListA001ApiResponse>();
        
        // レスポンス用にデータリストを作成する
        createResponseDataList(apiResDataList, dataList);
        
        // 戻り値をJsonのStringに変換
        resultJson = jc.toString(apiResDataList);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 検索結果をJsonで戻す
        return resultJson;
    }

    /** 
    * A002 ダウンロードボタン押下（CSV作成）
    *
    * @param apiReq {@code IfaStarUploadFileOutputListA002aApiRequest }
    * @param session {@code HttpSession }
    * @return {@code String}
    * @throws Exception CSV作成処理で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaStarUploadFileOutputListDownloadButtonPressA002a")
    @ResponseBody
    @ResponseJson
    public String downloadButtonPressA002a(
            @RequestBody IfaStarUploadFileOutputListA002aApiRequest apiReq, HttpSession session) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IpopoStarUploadFileController.downloadButtonPressA002a >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        String sessionId = session.getId();

        DataList<IpopoStarUploadCsvInfoListModel> dataList = new DataList<IpopoStarUploadCsvInfoListModel>();
        DataList<IfaStarUploadFileOutputListA002aApiResponse> apiResDataList = 
                new DataList<IfaStarUploadFileOutputListA002aApiResponse>();
        
        // 注文状態と時間より、レコードを取得する。
        int osCnt = ApiRequestUtil.invoke(
                "cmpIpopoBBApplyInfoListService", "getOrderStatusCount", new TypeReference<Integer>() {
                }, OrderStatus.OFFERING_INPUT.getId(), apiReq.getSysDate(), apiReq.getMinKey(), apiReq.getMaxKey());
        
        // 取得した件数と画面表示違いの場合、エラーとする
        if (osCnt != apiReq.getDataCnt()) {
            apiResDataList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_STARUPLOAD_DUPLICATE, IfaCommonUtil.getMessage(ERRORS_STARUPLOAD_DUPLICATE, new String[] { "" })); 
            
            String resultJson = jc.toString(apiResDataList);
            return resultJson;
        }
        // csv download
        dataList = ApiRequestUtil.invoke(
                "cmpIpopoBBApplyInfoListService", "ipopoStarUploadCsvDownload",
                new TypeReference<DataList<IpopoStarUploadCsvInfoListModel>>() {}, sessionId, userId, 
                SYS_ID, STAR_UPLOAD_FILE_DOWNLOAD_PATH, apiReq.getSysDate(), apiReq.getMinKey(), apiReq.getMaxKey());
        
        // 排他処理を行う。
        if (dataList.getTotalSize() <= 0) {
            apiResDataList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_STARUPLOAD_NONE, IfaCommonUtil.getMessage(ERRORS_STARUPLOAD_NONE, new String[] { "" }));
            
            String resultJson = jc.toString(apiResDataList);
            return resultJson;
        }
        
        setStatusAndMessageToDataList(dataList, true);
        
        // レスポンス用のデータリストにデータを設定する。
        apiResDataList.setTotalSize(dataList.getTotalSize());
        apiResDataList.setMaxRownum(dataList.getMaxRownum());
        apiResDataList.setOverMaxRownum(dataList.getTotalSize() > dataList.getMaxRownum());
        apiResDataList.setDataList(new ArrayList<>());
        apiResDataList.setMessage(dataList.getMessage());
        apiResDataList.setErrorLevel(dataList.getErrorLevel());
        apiResDataList.setRequestedTime(dataList.getRequestedTime());
        apiResDataList.setTitle(dataList.getTitle());
        apiResDataList.setReturnCode(dataList.getReturnCode());
        
        String resultJson = jc.toString(apiResDataList);
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return resultJson;
    }

    /**
    * A002 ダウンロードボタン押下（CSVダウンロード）
    *
    * @param apiReq {@code IfaStarUploadFileOutputListA002bApiRequest }
    * @param response {@code HttpServletResponse }
    * @throws Exception CSVダウンロード処理で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaStarUploadFileOutputListDownloadButtonPressA002b")
    @ResponseFile
    public void downloadButtonPressA002b(@RequestBody IfaStarUploadFileOutputListA002bApiRequest apiReq, 
            HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();
        logger.debug("IpopoStarUploadFileController.downloadButtonPressA002b >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // csv download
        String fileName = apiReq.getCsvDownloadFile()
                .substring(apiReq.getCsvDownloadFile().indexOf(STAR_UPLOAD_CSV_FILE_NAME));
        // Star ファイル ダウンロード CRLF
        doDownLoadStarCsvFile(response, apiReq.getCsvDownloadFile(), fileName, userAccount);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

    /**
    * 画面表示一覧のデータを取得する
    *

    * @return {@code DataList<IpopoBBApplyInfoListModel>}
    * @throws Exception 画面表示一覧データ取得処理で例外が発生した場合
    */
    private DataList<IpopoBBApplyInfoListModel> getDataList() throws Exception {
        // 情報取得
        DataList<IpopoBBApplyInfoListModel> dataList = new DataList<IpopoBBApplyInfoListModel>();
        dataList = ApiRequestUtil.invoke(
                "cmpIpopoBBApplyInfoListService", "getIpopoBBApplyInfoList",
                new TypeReference<DataList<IpopoBBApplyInfoListModel>>() {
                });
        return dataList;
    }
    
    /**
    * レスポンス用にデータリストを作成する
    *
    * @param resDataList {@code DataList<IfaStarUploadFileOutputListA001ApiResponse> }
    * @param dataList {@code DataList<IpopoBBApplyInfoListModel> }
    * @throws Exception レスポンスデータリスト作成処理で例外が発生した場合
    */
    private void createResponseDataList(
            DataList<IfaStarUploadFileOutputListA001ApiResponse> apiResDataList,
            DataList<IpopoBBApplyInfoListModel> dataList) throws Exception {
        
        // 変数
        List<IfaStarUploadFileOutputListA001ApiResponse> apiResponseList = 
                new ArrayList<IfaStarUploadFileOutputListA001ApiResponse>();
        IfaStarUploadFileOutputListA001ApiResponse apiResponse = new IfaStarUploadFileOutputListA001ApiResponse();
        List<IfaStarUploadFileOutputListA001FileOutputListApiResponse> fileOutputList = 
                new ArrayList<IfaStarUploadFileOutputListA001FileOutputListApiResponse>();
        boolean firstCountFlg = true;
        
        // レスポンス用のデータリストを設定
        for (IpopoBBApplyInfoListModel data : dataList.getDataList()) {
            IfaStarUploadFileOutputListA001FileOutputListApiResponse fileOutput = 
                    new IfaStarUploadFileOutputListA001FileOutputListApiResponse();
            
            fileOutput.setBbProductCode(data.getBbProductCode());
            fileOutput.setBbProductName(data.getBbProductName());
            fileOutput.setBrokerCode(data.getBrokerCode());
            fileOutput.setBrokerName(data.getBrokerName());
            fileOutput.setBranchCode(data.getBranchCode());
            fileOutput.setBranchName(data.getBranchName());
            fileOutput.setIntermediaryEmpCd(data.getIntermediaryEmpCd());
            fileOutput.setBrokerChargeName(data.getBrokerChargeName());
            fileOutput.setButenCode(data.getButenCode());
            fileOutput.setAccountNumber(data.getAccountNumber());
            fileOutput.setNameKanji(data.getNameKanji());
            fileOutput.setNameKana(data.getNameKana());
            fileOutput.setBbInvestorAttName(data.getBbInvestorAttName());
            fileOutput.setBbQuantity(data.getBbQuantity());
            fileOutput.setBbPrice(data.getBbPrice());
            fileOutput.setQuantitySairyou(data.getQuantitySairyou());
            fileOutput.setBbQuantityAlloc(data.getBbQuantityAlloc());
            fileOutput.setLotteryResult(data.getLotteryResult());
            fileOutput.setOrderStatus(data.getOrderStatus());
            fileOutput.setOrderCount(data.getOrderCount());
            fileOutput.setDepositType(data.getDepositType());
            fileOutput.setBbCreateDate(data.getBbCreateDate());
            fileOutput.setBbCreateUserName(data.getBbCreateUserName());   
            fileOutput.setBbCreateSectionName(data.getBbCreateSectionName());    
            fileOutput.setBbRemark(data.getBbRemark());
            fileOutput.setBbUpdateDate(data.getBbUpdateDate());
            apiResponse.setMaxKey(data.getUploadKey());        
            if (firstCountFlg) {
                apiResponse.setSysDate(data.getSysDate());
                apiResponse.setMinKey(data.getUploadKey());
                apiResponse.setDataCnt(data.getTotalRow());
                firstCountFlg = false;
            }
            fileOutputList.add(fileOutput); 
        }
        apiResponse.setFileOutputList(fileOutputList);
        apiResponseList.add(apiResponse);
        
        apiResDataList.setTotalSize(dataList.getTotalSize());
        apiResDataList.setMaxRownum(dataList.getMaxRownum());
        apiResDataList.setOverMaxRownum(dataList.getTotalSize() > dataList.getMaxRownum());
        apiResDataList.setDataList(apiResponseList);
        apiResDataList.setMessage(dataList.getMessage());
        apiResDataList.setErrorLevel(dataList.getErrorLevel());
        apiResDataList.setRequestedTime(dataList.getRequestedTime());
        apiResDataList.setTitle(dataList.getTitle());
        apiResDataList.setReturnCode(dataList.getReturnCode());
    }
    
}
