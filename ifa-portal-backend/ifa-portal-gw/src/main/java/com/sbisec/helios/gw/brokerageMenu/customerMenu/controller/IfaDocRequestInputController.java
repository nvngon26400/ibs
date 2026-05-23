package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDocRequestInputA014ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA007ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA007ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA008ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA009ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA009ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA013ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA013ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA014ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDocRequestInputA014ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0704-01 画面名：書類請求入力
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0704-01", screenNumber = "")
public class IfaDocRequestInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDocRequestInputA001ApiRequest
     * APIレスポンス：IfaDocRequestInputA001ApiResponse
     * Dtoリクエスト：IfaDocRequestInputA001RequestDto
     * Dtoレスポンス：IfaDocRequestInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestInputInitializeA001")
    public String initializeA001(@RequestBody IfaDocRequestInputA001ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA001RequestDto appReq = new IfaDocRequestInputA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "initializeA001", new TypeReference<DataList<IfaDocRequestInputA001ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA001ApiResponse> apiRes = new DataList<IfaDocRequestInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestInputSelectShoruiListA002
     * アクションID：A002
     * アクション名：分類選択-書類リスト取得
     * APIリクエスト：IfaDocRequestInputA002ApiRequest
     * Apiレスポンス：IfaDocRequestInputA002ApiResponse
     * Dtoリクエスト：IfaDocRequestInputA002RequestDto
     * Dtoレスポンス：IfaDocRequestInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類リスト取得の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestInputSelectShoruiListA002")
    public String selectShoruiListA002(@RequestBody IfaDocRequestInputA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA002RequestDto appReq = new IfaDocRequestInputA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "selectShoruiListA002", new TypeReference<DataList<IfaDocRequestInputA002ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA002ApiResponse> apiRes = new DataList<IfaDocRequestInputA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestInputSelectShoruiDateA003
     * アクションID：A003
     * アクション名：書類選択-書類データ取得
     * APIリクエスト：IfaDocRequestInputA003ApiRequest
     * Apiレスポンス：IfaDocRequestInputA003ApiResponse
     * Dtoリクエスト：IfaDocRequestInputA003RequestDto
     * Dtoレスポンス：IfaDocRequestInputA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類データ取得の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestInputSelectShoruiDateA003")
    public String selectShoruiDateA003(@RequestBody IfaDocRequestInputA003ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA003RequestDto appReq = new IfaDocRequestInputA003RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "selectShoruiDateA003", new TypeReference<DataList<IfaDocRequestInputA003ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA003ApiResponse> apiRes = new DataList<IfaDocRequestInputA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestInputSelectMFNameA005
     * アクションID：A005
     * アクション名：投信銘柄情報取得
     * APIリクエスト：IfaDocRequestInputA005ApiRequest
     * Apiレスポンス：IfaDocRequestInputA005ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA005RequestDto
     * Dtoレスポンス：IfaDocRequestInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 投信銘柄情報取得の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestInputSelectMFNameA005")
    public String selectMFNameA005(@RequestBody IfaDocRequestInputA005ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA005RequestDto appReq = new IfaDocRequestInputA005RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "selectMFNameA005", new TypeReference<DataList<IfaDocRequestInputA005ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA005ApiResponse> apiRes = new DataList<IfaDocRequestInputA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestExecuteConfirmA007
     * アクションID：A007
     * アクション名：書類請求確認
     * APIリクエスト：IfaDocRequestInputA007ApiRequest
     * Apiレスポンス：IfaDocRequestInputA007ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA007RequestDto
     * Dtoレスポンス：IfaDocRequestInputA007ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestExecuteConfirmA007")
    public String executeConfirmA007(@RequestBody IfaDocRequestInputA007ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA007RequestDto appReq = new IfaDocRequestInputA007RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA007ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "executeConfirmA007", new TypeReference<DataList<IfaDocRequestInputA007ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA007ApiResponse> apiRes = new DataList<IfaDocRequestInputA007ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestCancelConfirmA008
     * アクションID：A008
     * アクション名：書類請求取消
     * APIリクエスト：IfaDocRequestInputA008ApiRequest
     * Apiレスポンス：IfaDocRequestInputA008ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA008RequestDto
     * Dtoレスポンス：IfaDocRequestInputA008ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求取消の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestCancelConfirmA008")
    public String cancelConfirmA008(@RequestBody IfaDocRequestInputA008ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA008RequestDto appReq = new IfaDocRequestInputA008RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA008ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "cancelConfirmA008", new TypeReference<DataList<IfaDocRequestInputA008ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA008ApiResponse> apiRes = new DataList<IfaDocRequestInputA008ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocRequestDetailA009
     * アクションID：A009
     * アクション名：書類請求詳細
     * APIリクエスト：IfaDocRequestInputA009ApiRequest
     * Apiレスポンス：IfaDocRequestInputA009ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA009RequestDto
     * Dtoレスポンス：IfaDocRequestInputA009ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 書類請求詳細の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocRequestDetailA009")
    public String detailA009(@RequestBody IfaDocRequestInputA009ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA009RequestDto appReq = new IfaDocRequestInputA009RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA009ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "detailA009", new TypeReference<DataList<IfaDocRequestInputA009ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA009ApiResponse> apiRes = new DataList<IfaDocRequestInputA009ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocBMRequestCancelConfirmA013
     * アクションID：A013
     * アクション名：BM交付取消
     * APIリクエスト：IfaDocRequestInputA013ApiRequest
     * Apiレスポンス：IfaDocRequestInputA013ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA013RequestDto
     * Dtoレスポンス：IfaDocRequestInputA013ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付取消の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocBMRequestCancelConfirmA013")
    public String cancelConfirmA013(@RequestBody IfaDocRequestInputA013ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA013RequestDto appReq = new IfaDocRequestInputA013RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA013ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "cancelConfirmA013", new TypeReference<DataList<IfaDocRequestInputA013ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA013ApiResponse> apiRes = new DataList<IfaDocRequestInputA013ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDocBMRequestDetailA014
     * アクションID：A014
     * アクション名：BM交付詳細
     * APIリクエスト：IfaDocRequestInputA014ApiRequest
     * Apiレスポンス：IfaDocRequestInputA014ApiResponse
     * Dtoレスポンス：IfaDocRequestInputA014RequestDto
     * Dtoレスポンス：IfaDocRequestInputA014ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付詳細の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDocBMRequestDetailA014")
    public String detailA014(@RequestBody IfaDocRequestInputA014ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDocRequestInputA014RequestDto appReq = new IfaDocRequestInputA014RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaDocRequestInputA014ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocRequestInputService",
                "detailA014", new TypeReference<DataList<IfaDocRequestInputA014ResponseDto>>() {
                }, appReq);
        DataList<IfaDocRequestInputA014ApiResponse> apiRes = new DataList<IfaDocRequestInputA014ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
