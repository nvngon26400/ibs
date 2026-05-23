package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBProductMasterModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBrandInfoListModel;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ipopo.BBGestureValue;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA001IpoPoBrandListApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaIpoPoBrandListA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0204_01-01
 * 画面名：IPOPO銘柄一覧
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_01-01", screenNumber = "")
public class IfaIpoPoBrandListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
        
    private static final Logger logger = LoggerFactory.getLogger(IfaIpoPoBrandListController.class);
    private static final String URGENT_STOP = "1";
    private static final String IS_APPLY_NEW = "1";
    private static final String APPLY_NEW = "1";
    private static final String NOT_APPLY_NEW = "0";
    // NRI_APIのステータスチェック用定数
    private static final String IPOPO_NRI_API = "NRI_API";
    
    /**
    * A001 初期化
    *
    * @param apiReq {@code IfaIpoPoBrandListA001ApiRequest }
    * @return {@code String}
    * @throws Exception 初期化処理で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaIpoPoBrandListInitializeA001")
    @ResponseBody
    @ResponseJson
    public String initializeA001(@Validated @RequestBody IfaIpoPoBrandListA001ApiRequest apiReq, 
            HttpServletRequest request) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaIpoPoBrandListController.initializeA001 >> {}", hashCode());

        DataList<IpopoBrandInfoListModel> dataList = new DataList<IpopoBrandInfoListModel>();

        // IPO/PO銘柄一覧を取得
        String dummy = null;
        dataList = ApiRequestUtil.invoke("IpopoBrandInfoListService", "getIpopoBrandInfoList",
                new TypeReference<DataList<IpopoBrandInfoListModel>>() {
                }, dummy);
        
        DataList<IfaIpoPoBrandListA001ApiResponse> apiRes = new DataList<IfaIpoPoBrandListA001ApiResponse>();
        
        // APIレスポンスにIPO/PO銘柄一覧データを設定
        setApiResponse(apiRes, dataList, request);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(apiRes);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        // 検索結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * A002 BB申込
    *
    * @param apiReq {@code IfaIpoPoBrandListA002ApiRequest }
    * @return {@code String}
    * @throws Exception BB申込で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaIpoPoBrandListBbApplicationA002")
    @ResponseBody
    @ResponseJson
    public String bbApplicationA002(@Validated @RequestBody IfaIpoPoBrandListA002ApiRequest apiReq, 
            HttpServletRequest request) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaIpoPoBrandListController.bbApplicationA002 >> {}", hashCode());
        
        DataList<IfaIpoPoBrandListA002ApiResponse> apiRes = new DataList<IfaIpoPoBrandListA002ApiResponse>();
        DataList<String> checkExistDataList = new DataList<String>();
        
        // サービス閉塞チェック、銘柄存在チェックを行う。
        checkExistDataList = checkExist(apiReq.getBbProductCode(), apiReq.getBbPresentationFrom(), APPLY_NEW, request);
        
        apiRes.setMessage(checkExistDataList.getMessage());
        apiRes.setErrorLevel(checkExistDataList.getErrorLevel());
        apiRes.setRequestedTime(checkExistDataList.getRequestedTime());
        apiRes.setReturnCode(checkExistDataList.getReturnCode());

        String resultJson = StringUtil.EMPTY_STRING;
        resultJson = jc.toString(apiRes);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return resultJson;
    }
    
    /**
    * A004 BB募集期間マスタ登録
    *
    * @param apiReq {@code IfaIpoPoBrandListA004ApiRequest }
    * @return {@code String}
    * @throws Exception BB申込で例外が発生した場合
    */
    @PostMapping("/brokerageMenu/ipoPo/ifaIpoPoBrandListBbSolicitationPeriodMasterRegistrationA004")
    @ResponseBody
    @ResponseJson
    public String bbSolicitationPeriodMasterRegistrationA004(
            @Validated @RequestBody IfaIpoPoBrandListA004ApiRequest apiReq, 
            HttpServletRequest request) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaIpoPoBrandListController.bbSolicitationPeriodMasterRegistrationA004 >> {}", hashCode());
        
        DataList<IfaIpoPoBrandListA004ApiResponse> apiRes = new DataList<IfaIpoPoBrandListA004ApiResponse>();
        DataList<String> checkExistDataList = new DataList<String>();
       
        // サービス閉塞チェック、銘柄存在チェックを行う。
        checkExistDataList = checkExist(apiReq.getBbProductCode(), 
                apiReq.getBbPresentationFrom(), NOT_APPLY_NEW, request);
       
        apiRes.setMessage(checkExistDataList.getMessage());
        apiRes.setErrorLevel(checkExistDataList.getErrorLevel());
        apiRes.setRequestedTime(checkExistDataList.getRequestedTime());
        apiRes.setReturnCode(checkExistDataList.getReturnCode());

        String resultJson = StringUtil.EMPTY_STRING;
        resultJson = jc.toString(apiRes);
        
        resultJson = jc.toString(apiRes);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return resultJson;
    }
    
    /**
    * サービス閉塞チェック、銘柄存在チェック行う。
    *
    * @param bbProductCodeParam {@code String }
    * @param bbPresentationFromParam {@code String } 
    * @param isApplyNew {@code String } 
    * @param request {@code HttpServletRequest } 
    * @return {@code DataList<String>}
    * @throws Exception サービス閉塞チェック・銘柄存在チェックで例外が発生した場合
    */
    private DataList<String> checkExist(String bbProductCodeParam, String bbPresentationFromParam, 
            String isApplyNew, HttpServletRequest request) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaIpoPoBrandListController.checkExist >> {}", hashCode());

        DataList<String> dataList = new DataList<String>();
        String returnCode = StringUtil.EMPTY_STRING;

        // BB申込の場合、NRI_API閉塞状態をチェックする
        if (IS_APPLY_NEW.equals(isApplyNew)) {
            Boolean status = ApiRequestUtil.invoke("serviceStatusService", "getServiceStatus",
                    new TypeReference<Boolean>() {
                    }, IPOPO_NRI_API);
            if (!status) {
                dataList.setErrorLevel(ErrorLevel.WARNING.getId());
                dataList.setMessage(getMessage(RETURN_CODE_W0006, new String[] {}));
                dataList.setRequestedTime(getFormattedRequestedTime(request));
                return dataList;
            }
        }

        // 期間指定デフォルト日付を取得
        String nowDate = DateUtil.format(new Date(), NOT_SEPARATED_YYYYMMDD_HHMM);

        // IPO/PO銘柄情報を取得
        BBProductMasterModel res = ApiRequestUtil.invoke("IpopoBrandInfoListService", "getBBProductMasterInfo",
                new TypeReference<BBProductMasterModel>() {
                }, bbProductCodeParam, bbPresentationFromParam);

        // 検索結果は「0」の場合
        if (res == null) {
            dataList.setMessage(getMessage(ERRORS_EMPTY_RECORD, new String[] { "" }));
            dataList.setErrorLevel(ErrorLevel.FATAL.getId());

            // returnCodeは0の場合、該当注文存在しない
            returnCode = "0";
            dataList.setReturnCode(returnCode);
            dataList.setRequestedTime(getFormattedRequestedTime(request));

            logger.debug("cost -> {}", (System.currentTimeMillis() - start));
            return dataList;
        }

        // BB申込の場合
        if (IS_APPLY_NEW.equals(isApplyNew)) {
            // ブックビルディング申込期間（開始） または ブックビルディング申込期間（終了）未登録の場合、
            if (res.getBbPresentationFrom() == null || res.getBbPresentationTo() == null) {
                dataList.setMessage(getMessage(ERRORS_PERIOD_INPUT_CHECK, new String[] { "IFA・BB申込期間", "申込" }));
                dataList.setErrorLevel(ErrorLevel.FATAL.getId());
                returnCode = "0";
                dataList.setReturnCode(returnCode);
                dataList.setRequestedTime(getFormattedRequestedTime(request));

                // logger.debug("resultJson -> {}", resultJson);
                logger.debug("cost -> {}", (System.currentTimeMillis() - start));
                return dataList;
            }

            // ブックビルディング申込期間（開始）
            String bbPresentationFrom = DateUtil.format(res.getBbPresentationFrom(), NOT_SEPARATED_YYYYMMDD_HHMM);
            // ブックビルディング申込期間（終了）
            String bbPresentationTo = DateUtil.format(res.getBbPresentationTo(), NOT_SEPARATED_YYYYMMDD_HHMM);

            // A.ブックビルディング申込期間（開始） > システム日付 または システム日付 > A.ブックビルディング申込期間（終了）の場合、
            if (nowDate.compareTo(bbPresentationFrom) < 0 || nowDate.compareTo(bbPresentationTo) > 0) {
                dataList.setMessage(getMessage(ERRORS_APPLY_TIME, new String[] { "申込" }));
                dataList.setErrorLevel(ErrorLevel.FATAL.getId());
                returnCode = "0";
                dataList.setReturnCode(returnCode);
                dataList.setRequestedTime(getFormattedRequestedTime(request));

                logger.debug("cost -> {}", (System.currentTimeMillis() - start));
                return dataList;

            // A.緊急入力停止 = 1(ON)の場合
            } else if (URGENT_STOP.equals(res.getBbUrgentStop())) {
                dataList.setMessage(getMessage(ERRORS_URGENT_STOP_CHECK, new String[] { "申込" }));
                dataList.setErrorLevel(ErrorLevel.FATAL.getId());
                returnCode = "0";
                dataList.setReturnCode(returnCode);
                dataList.setRequestedTime(getFormattedRequestedTime(request));

                logger.debug("cost -> {}", (System.currentTimeMillis() - start));
                return dataList;
            }

            // 仮条件チェックを行う
            boolean brandInfoCheckResult = brandInfoCheck(res.getBbGestureValue(), res.getBbPriceFrom(),
                    res.getBbPriceTo(), res.getBbPriceCut(), res.getBbDiscountFrom(), res.getBbDiscountTo(),
                    res.getBbDiscountCut());
            if (brandInfoCheckResult) {
                dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SECTION_ID, new String[] { "価格またはディスカウント率" }));
                dataList.setErrorLevel(ErrorLevel.FATAL.getId());
                returnCode = "0";
                dataList.setReturnCode(returnCode);
                dataList.setRequestedTime(getFormattedRequestedTime(request));

                logger.debug("cost -> {}", (System.currentTimeMillis() - start));
                return dataList;
            }
        }

        dataList.setReturnCode(returnCode);
        dataList.setRequestedTime(getFormattedRequestedTime(request));

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return dataList;
    }

    /**
    * 仮条件チェックを行う
    *
    * @param bbGestureValue {@code String }
    * @param bbPriceFrom {@code BigDecimal } 
    * @param bbPriceTo {@code BigDecimal } 
    * @param bbPriceCut {@code BigDecimal }  
    * @param bbDiscountFrom {@code BigDecimal }  
    * @param bbDiscountTo {@code BigDecimal }  
    * @param bbDiscountCut {@code BigDecimal } 
    * @return {@code DataList<String>}
    * @throws Exception 仮条件チェックで例外が発生した場合
    * */
    private boolean brandInfoCheck(String bbGestureValue, BigDecimal bbPriceFrom, BigDecimal bbPriceTo,
            BigDecimal bbPriceCut, BigDecimal bbDiscountFrom, BigDecimal bbDiscountTo, BigDecimal bbDiscountCut) {
        // 下記の項目はNULLがだったら、TRUEを設定する
        // 価格表示（開始）
        boolean bbPriceFromIsNull = bbPriceFrom == null;
        // 価格表示（終了）
        boolean bbPriceToIsNull = bbPriceTo == null;
        // 価格表示（刻み）
        boolean bbPriceCutIsNull = bbPriceCut == null;
        // ディスカウント率（開始）
        boolean bbDiscountFromIsNull = bbDiscountFrom == null;
        // ディスカウント率（終了）
        boolean bbDiscountToIsNull = bbDiscountTo == null;
        // ディスカウント率（刻み）
        boolean bbDiscountCutIsNull = bbDiscountCut == null;

        if (BBGestureValue.PRICE.getId().equals(bbGestureValue)) {
            // 発行価格区分は価格表示であり、価格表示（開始）か価格表示（終了）か価格表示（刻み）は空の場合
            if (bbPriceFromIsNull || bbPriceToIsNull || bbPriceCutIsNull) {
                return true;
            }
        } else {
            // 発行価格区分は価格表示であり、ディスカウント率（開始）かディスカウント率（終了）かディスカウント率（刻み）は空の場合
            if (bbDiscountFromIsNull || bbDiscountToIsNull || bbDiscountCutIsNull) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * レスポンス内容を設定する。
    *
    * @param apiRes {@code DataList<IfaIpoPoBrandListA001ApiResponse> }
    * @param modelList {@code DataList<IpopoBrandInfoListModel> }
    * @return {@code void}
    * @throws Exception レスポンス設定処理で例外が発生した場合
    */
    private void setApiResponse(DataList<IfaIpoPoBrandListA001ApiResponse> apiRes,
            DataList<IpopoBrandInfoListModel> modelList,
            HttpServletRequest request) throws Exception {
        
        List<IfaIpoPoBrandListA001ApiResponse> resList = new ArrayList<IfaIpoPoBrandListA001ApiResponse>();
        IfaIpoPoBrandListA001ApiResponse res = new IfaIpoPoBrandListA001ApiResponse();
        List<IfaIpoPoBrandListA001IpoPoBrandListApiResponse> ipoPoBrandList = 
                new ArrayList<IfaIpoPoBrandListA001IpoPoBrandListApiResponse>();
        
        for (IpopoBrandInfoListModel model : modelList.getDataList()) {
            
            IfaIpoPoBrandListA001IpoPoBrandListApiResponse ipoPoBrand = 
                    new IfaIpoPoBrandListA001IpoPoBrandListApiResponse();
            
            // レスポンスにSQLで取得したレコードを設定。
            ipoPoBrand.setBbIpoPoKbn(model.getBbIpoPoKbn());
            ipoPoBrand.setBbProductCode(model.getBbProductCode());
            ipoPoBrand.setAttachedBrand(model.getAttachedBrand());
            ipoPoBrand.setCigaretteShowFlag(model.getCigaretteShowFlag());
            ipoPoBrand.setEdelivOnlyFlag(model.getEdelivOnlyFlag());
            ipoPoBrand.setChangeBbPeriodFlag(model.getChangeBbPeriodFlag());
            ipoPoBrand.setChangePriceFlag(model.getChangePriceFlag());
            ipoPoBrand.setBbProductName(model.getBbProductName());
            ipoPoBrand.setMaxAllocation(model.getMaxAllocation());
            ipoPoBrand.setBbStock(model.getBbStock());
            ipoPoBrand.setBbPresentation(model.getBbPresentation());
            ipoPoBrand.setStatus(model.getStatus()); 
            ipoPoBrand.setBbPeriod(model.getBbPeriod());
            ipoPoBrand.setPaymentDate(model.getPaymentDate());
            ipoPoBrand.setBbUnitKbn(model.getBbUnitKbn());
            ipoPoBrand.setBbPresentationFrom(model.getBbPresentationFrom());
            ipoPoBrandList.add(ipoPoBrand);
            
            // レコード数がMaxRownumと同じになったら終了
            if (ipoPoBrandList.size() >= modelList.getMaxRownum()) {
                break;
            }
        }
        res.setIpoPoBrandList(ipoPoBrandList);
        resList.add(res);
        
        apiRes.setDataList(resList);
        
        // APIレスポンスにレスポンス情報を設定
        apiRes.setTotalSize(modelList.getTotalSize());
        apiRes.setMaxRownum(modelList.getMaxRownum());
        apiRes.setRequestedTime(getFormattedRequestedTime(request));
        apiRes.setErrorLevel(ErrorLevel.SUCCESS.getId());
        apiRes.setReturnCode(ErrorLevel.SUCCESS.name());
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
