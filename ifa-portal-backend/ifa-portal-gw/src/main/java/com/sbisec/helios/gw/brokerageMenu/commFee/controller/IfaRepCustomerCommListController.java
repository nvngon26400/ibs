package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaRepCustomerCommListCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaRepCustomerCommListApiResponseListModel;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020501-01
 * 画面名：担当顧客別手数料一覧
 * 2024/06/10 新規作成
 *
 * @author 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020501-01", screenNumber = "46")
public class IfaRepCustomerCommListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** CSVファイル名:営業員毎 */
    private static final String CSV_FILE_NAME_PER_CHARGE = "担当顧客別手数料一覧・営業員毎";
    
    /** CSVファイル名:顧客毎 */
    private static final String CSV_FILE_NAME_PER_CUSTOMER = "担当顧客別手数料一覧・顧客毎";
    
    /** 集計単位:営業員毎 */
    private static final String CHARGE_CUSTOMER_COUNTING_UNIT_CHARGE = "0";
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaRepCustomerCommListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaRepCustomerCommListA001ApiRequest
     * Apiレスポンス：IfaRepCustomerCommListA001ApiResponse
     * Dtoリクエスト：IfaRepCustomerCommListA001RequestDto
     * Dtoレスポンス：IfaRepCustomerCommListA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaRepCustomerCommListInitializeA001")
    public String initializeA001(@RequestBody IfaRepCustomerCommListA001ApiRequest apiReq) throws Exception {
        
        final IfaRepCustomerCommListA001RequestDto appReq = new IfaRepCustomerCommListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        final DataList<IfaRepCustomerCommListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaRepCustomerCommListService", "initializeA001",
                new TypeReference<DataList<IfaRepCustomerCommListA001ResponseDto>>() {
                }, appReq);
        
        // 処理結果を返却する
        final DataList<IfaRepCustomerCommListA001ApiResponse> apiRes = new DataList<IfaRepCustomerCommListA001ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaRepCustomerCommListA001ApiResponse>>() {
                    }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaRepCustomerCommListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaRepCustomerCommListA002ApiRequest
     * Apiレスポンス：IfaRepCustomerCommListA002ApiResponse
     * Dtoリクエスト：IfaRepCustomerCommListA002RequestDto
     * Dtoレスポンス：IfaRepCustomerCommListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaRepCustomerCommListDisplayA002")
    public String displayA002(@RequestBody IfaRepCustomerCommListA002ApiRequest apiReq) throws Exception {
        
        IfaRepCustomerCommListA002RequestDto appReq = new IfaRepCustomerCommListA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaRepCustomerCommListA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaRepCustomerCommListService", "displayA002",
                new TypeReference<DataList<IfaRepCustomerCommListA002ResponseDto>>() {
                }, appReq);
        
        // 処理結果を返却する
        DataList<IfaRepCustomerCommListA002ApiResponse> apiRes = new DataList<IfaRepCustomerCommListA002ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            IfaRepCustomerCommListA002ApiResponse dataList = new IfaRepCustomerCommListA002ApiResponse();
            dataList.setRepCustomerCommList(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRepCustomerCommList()),
                            new TypeReference<List<IfaRepCustomerCommListApiResponseListModel>>() {
                            }));
            apiRes.setDataList(List.of(dataList));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaRepCustomerCommListCsvOutputA004a
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaRepCustomerCommListA004aApiRequest
     * Apiレスポンス：IfaRepCustomerCommListA004aApiResponse
     * Dtoリクエスト：IfaRepCustomerCommListA004aRequestDto
     * Dtoレスポンス：IfaRepCustomerCommListA004aResponseDto
     *
     * @param apiReq リクエスト
     * @param response 画面へのレスポンスデータ
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping(value = "/brokerageMenu/commFee/ifaRepCustomerCommListCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaRepCustomerCommListA004aApiRequest apiReq) throws Exception {
        
        IfaRepCustomerCommListA004aRequestDto appReq = new IfaRepCustomerCommListA004aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        
        DataList<IfaRepCustomerCommListA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaRepCustomerCommListService", "csvOutputA004",
                new TypeReference<DataList<IfaRepCustomerCommListA004aResponseDto>>() {
                }, appReq, fwSessionId);
        
        // 処理結果を返却する
        DataList<IfaRepCustomerCommListA004aApiResponse> apiRes = new DataList<IfaRepCustomerCommListA004aApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            IfaRepCustomerCommListA004aApiResponse dataList = new IfaRepCustomerCommListA004aApiResponse();
            dataList.setRepCustomerCommList(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRepCustomerCommList()),
                            new TypeReference<List<IfaRepCustomerCommListApiResponseListModel>>() {
                            }));
            apiRes.setDataList(List.of(dataList));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaRepCustomerCommListCsvOutputA004b
     * アクションID：A004
     * アクション名：CSV出力(ダウンロード)
     * APIリクエスト：IfaRepCustomerCommListA004bApiRequest
     *
     * @param apiReq リクエスト
     * @param response 画面へのレスポンスデータ
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping(value = "/brokerageMenu/commFee/ifaRepCustomerCommListCsvOutputA004b")
    public void csvOutputA004b(@RequestBody IfaRepCustomerCommListA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // 集計単位からCSVファイル名を判定する
        String csvFileName = StringUtils.EMPTY;

        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        String[] parts = StringUtils.split(apiReq.getCsvDownloadFile(), ",");
        String chargeCustomerCountingUnit = parts[0]; // 集計単位
        String csvTmpFileName = parts[1]; // CSV一時ファイル名

        if (CHARGE_CUSTOMER_COUNTING_UNIT_CHARGE.equals(chargeCustomerCountingUnit)) {
            csvFileName = CSV_FILE_NAME_PER_CHARGE;
        } else {
            csvFileName = CSV_FILE_NAME_PER_CUSTOMER;
        }
        
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, csvTmpFileName, getCsvFileName(csvFileName),
                IfaCommonUtil.getUserAccount(), chargeCustomerCountingUnit);
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        
        return IfaRepCustomerCommListCsvOut.getHeaders(pattern);
    }
}
