package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBrandListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBrandListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBrandListA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBrandListA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBrandListA007ApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-01 画面名：投信積立設定済銘柄一覧
 * 
 * @author nicksen.li
 *
 *         2025/04/02 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-01", screenNumber = "")
public class IfaMutualFundAccumulateSettingBrandListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListInitializeA001
     * アクションID：A001 
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingBrandListA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBrandListA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBrandListA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBrandListA001ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListInitializeA001")
    public String initializeA001(@RequestBody IfaMutualFundAccumulateSettingBrandListA001ApiRequest apiReq)
            throws Exception {

        IfaMutualFundAccumulateSettingBrandListA001RequestDto appReq = new IfaMutualFundAccumulateSettingBrandListA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBrandListService", "initializeA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListInitializeA002
     * アクションID：A002 
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingBrandListA002ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBrandListA002ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBrandListA002RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBrandListA002ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListInitializeA002")
    public String initializeA002(@RequestBody IfaMutualFundAccumulateSettingBrandListA002ApiRequest apiReq)
            throws Exception {

        IfaMutualFundAccumulateSettingBrandListA002RequestDto appReq = new IfaMutualFundAccumulateSettingBrandListA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBrandListService", "initializeA002",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListAddA003
     * アクションID：A003 
     * アクション名：追加
     * APIリクエスト：IfaMutualFundAccumulateSettingBrandListA003ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBrandListA003ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBrandListA003RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBrandListA003ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListAddA003")
    public String addA003(@RequestBody IfaMutualFundAccumulateSettingBrandListA003ApiRequest apiReq)
            throws Exception {

        IfaMutualFundAccumulateSettingBrandListA003RequestDto appReq = new IfaMutualFundAccumulateSettingBrandListA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBrandListService", "addA003",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListAddA004
     * アクションID：A004 
     * アクション名：設定変更
     * APIリクエスト：IfaMutualFundAccumulateSettingBrandListA004ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBrandListA004ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBrandListA004RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBrandListA004ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListChangeA004")
    public String changeA004(@RequestBody IfaMutualFundAccumulateSettingBrandListA004ApiRequest apiReq)
            throws Exception {

        IfaMutualFundAccumulateSettingBrandListA004RequestDto appReq = new IfaMutualFundAccumulateSettingBrandListA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBrandListService", "changeA004",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListBulkChangeA007
     * アクションID：A007 
     * アクション名：設定一括変更
     * APIリクエスト：IfaMutualFundAccumulateSettingBrandListA007ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBrandListA007ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBrandListA007RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBrandListA007ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBrandListBulkChangeA007")
    public String bulkChangeA007(@RequestBody IfaMutualFundAccumulateSettingBrandListA007ApiRequest apiReq)
            throws Exception {

        IfaMutualFundAccumulateSettingBrandListA007RequestDto appReq = new IfaMutualFundAccumulateSettingBrandListA007RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBrandListService", "bulkChangeA007",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
