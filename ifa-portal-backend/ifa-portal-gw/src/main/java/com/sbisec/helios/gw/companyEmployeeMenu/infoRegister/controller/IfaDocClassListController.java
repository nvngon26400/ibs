package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaDocClassListA007ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA004ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA004ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA005ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA005ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA006ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA006ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA007ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaDocClassListA007ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0501_02-01
 * 画面名：資料種別一覧
 *
 * @author SCSK
 *     2024/02/05 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_02-01", screenNumber = "")
public class IfaDocClassListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryListA001
     * アクションID：A001
     * アクション名：カテゴリ一覧
     * APIリクエスト：IfaDocClassListA001ApiRequest
     * Apiレスポンス：IfaDocClassListA001ApiResponse
     * Dtoリクエスト：IfaDocClassListA001RequestDto
     * Dtoレスポンス：IfaDocClassListA001ResponseDto
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ一覧取得処理で例外が発生した場合
     */
    @RequestMapping(value = "/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryListA001", method = {
            RequestMethod.POST })
    public String categoryListA001(@RequestBody IfaDocClassListA001ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA001RequestDto appReq = new IfaDocClassListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaDocClassListA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService",
                "categoryListA001", new TypeReference<DataList<IfaDocClassListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA001ApiResponse> apiRes = new DataList<IfaDocClassListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryRegistrationConfirmA002
     * アクションID：A002
     * アクション名：カテゴリ登録確認
     * APIリクエスト：IfaDocClassListA002ApiRequest
     * Dtoリクエスト：IfaDocClassListA002RequestDto
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録確認処理で例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryRegistrationConfirmA002")
    public String categoryRegistrationConfirmA002(@RequestBody IfaDocClassListA002ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA002RequestDto appReq = new IfaDocClassListA002RequestDto();
        
        if (!StringUtil.isNullOrEmpty(apiReq.getCategory())) {
            //環境依存文字チェック
            String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(apiReq.getCategory());
            if (!utf8Str.isEmpty()) {
                String message = IfaCommonUtil.getMessage("errors.specialWords", new String[] { "カテゴリ", "環境依存文字" });
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, "errors.specialWords",
                        message));
            }
        }

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaDocClassListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService", "categoryRegistrationConfirmA002",
                new TypeReference<DataList<IfaDocClassListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA002ApiResponse> apiRes = new DataList<IfaDocClassListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryRegistrationA003
     * アクションID：A003
     * アクション名：カテゴリ登録
     * APIリクエスト：IfaDocClassListA003ApiRequest
     * Apiレスポンス：IfaDocClassListA003ApiResponse
     * Dtoリクエスト：IfaDocClassListA003RequestDto
     * Dtoレスポンス：IfaDocClassListA003ResponseDto
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ登録処理で例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryRegistrationA003")
    public String categoryRegistrationA003(@RequestBody IfaDocClassListA003ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA003RequestDto appReq = new IfaDocClassListA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaDocClassListA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService",
                "categoryRegistrationA003", new TypeReference<DataList<IfaDocClassListA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA003ApiResponse> apiRes = new DataList<IfaDocClassListA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryUpdateConfirmA004
     * アクションID：A004
     * アクション名：カテゴリ更新確認
     * APIリクエスト：IfaDocClassListA004ApiRequest
     * Dtoリクエスト：IfaDocClassListA004DtoRequest
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新確認処理で例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryUpdateConfirmA004")
    public String categoryUpdateConfirmA004(@RequestBody IfaDocClassListA004ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA004RequestDto appReq = new IfaDocClassListA004RequestDto();
        
        for (IfaDocClassListA004ApiRequest.Category apiReqList : apiReq.getRegisterCategoryList()) {
            if (!StringUtil.isNullOrEmpty(apiReqList.getName())) {
                //環境依存文字チェック
                String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(apiReqList.getName());
                if (!utf8Str.isEmpty()) {
                    String message = IfaCommonUtil.getMessage("errors.specialWords", new String[] { "カテゴリ", "環境依存文字" });
                    return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, "errors.specialWords",
                            message));
                }
            }
        }
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaDocClassListA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService", "categoryUpdateConfirmA004",
                new TypeReference<DataList<IfaDocClassListA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA004ApiResponse> apiRes = new DataList<IfaDocClassListA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryUpdateA005
     * アクションID：A005
     * アクション名：カテゴリ更新
     * APIリクエスト：IfaDocClassListA005ApiRequest
     * Apiレスポンス：IfaDocClassListA005ApiResponse
     * Dtoリクエスト：IfaDocClassListA005RequestDto
     * Dtoレスポンス：IfaDocClassListA005ResponseDto
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ更新処理で例外が発生した場合

     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryUpdateA005")
    public String categoryUpdateA005(@RequestBody IfaDocClassListA005ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA005RequestDto appReq = new IfaDocClassListA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaDocClassListA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService",
                "categoryUpdateA005", new TypeReference<DataList<IfaDocClassListA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA005ApiResponse> apiRes = new DataList<IfaDocClassListA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryDeletionConfirmA006
     * アクションID：A006
     * アクション名：カテゴリ削除確認
     * APIリクエスト：IfaDocClassListA006ApiRequest
     * Dtoリクエスト：IfaDocClassListA006DtoRequest
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除確認処理で例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryDeletionConfirmA006")
    public String categoryDeletionConfirmA006(@RequestBody IfaDocClassListA006ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA006RequestDto appReq = new IfaDocClassListA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaDocClassListA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService", "categoryDeletionConfirmA006",
                new TypeReference<DataList<IfaDocClassListA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA006ApiResponse> apiRes = new DataList<IfaDocClassListA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryDeletionA007
     * アクションID：A007
     * アクション名：カテゴリ削除
     * APIリクエスト：IfaDocClassListA007ApiRequest
     * Apiレスポンス：IfaDocClassListA007ApiResponse
     * Dtoリクエスト：IfaDocClassListA007DtoRequest
     * Dtoレスポンス：IfaDocClassListA007DtoResponse
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exceptionException カテゴリ削除処理で例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaDocClassListCategoryDeletionA007")
    public String categoryDeletionA007(@RequestBody IfaDocClassListA007ApiRequest apiReq) throws Exception {
        
        IfaDocClassListA007RequestDto appReq = new IfaDocClassListA007RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDocClassListA007ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDocClassListService",
                "categoryDeletionA007", new TypeReference<DataList<IfaDocClassListA007ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDocClassListA007ApiResponse> apiRes = new DataList<IfaDocClassListA007ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
