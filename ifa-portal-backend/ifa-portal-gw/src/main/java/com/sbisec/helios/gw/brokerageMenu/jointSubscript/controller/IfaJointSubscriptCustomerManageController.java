package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA006ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA006DownloadApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA007ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA007ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerManageA008ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * コントローラー
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0206_01-01", screenNumber = "17")
public class IfaJointSubscriptCustomerManageController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptCustomerManageA001ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA001ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA001DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptCustomerManageA001ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA001RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA001ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA001ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageSearchDisplayA002
     * アクションID：A002
     * アクション名：検索(表示)
     * APIリクエスト：IfaJointSubscriptCustomerManageA002ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA002ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA002DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA002DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageSearchDisplayA002")
    public String searchDisplayA002(@RequestBody IfaJointSubscriptCustomerManageA002ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA002RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA002ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "searchDisplayA002", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA002ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA002ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA002ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageConfirmApproveA005
     * アクションID：A005
     * アクション名：承認確認
     * APIリクエスト：IfaJointSubscriptCustomerManageA005ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA005ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA005DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA005DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageConfirmApproveA005")
    public String confirmApproveA005(@RequestBody IfaJointSubscriptCustomerManageA005ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA005RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA005ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "confirmApproveA005", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA005ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA005ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA005ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCsvOutputA006
     * アクションID：A006a
     * アクション名：CSV出力
     * APIリクエスト：IfaJointSubscriptCustomerManageA006ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA006ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA006DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA006DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCsvOutputA006")
    public String csvOutputA006a(@RequestBody IfaJointSubscriptCustomerManageA006ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA006RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA006ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "csvOutputA006",
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA006ResponseDto>>() {},
                    p_reqDto,
                    IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class)
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA006ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA006ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);
        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCsvDownloadA006
     * アクションID：A006b
     * アクション名：CSVダウンロード
     * APIリクエスト：brokerageMenu.customerListA005ApiRequest
     * Apiレスポンス：brokerageMenu.customerListA005ApiResponse
     * Dtoリクエスト：brokerageMenu.customerListA005DtoRequest
     * Dtoレスポンス：brokerageMenu.customerListA005DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @param response 画面へのレスポンスデータ
     * @exception Exception 例外
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCsvDownloadA006")
    public void csvDownloadA006b(@RequestBody IfaJointSubscriptCustomerManageA006DownloadApiRequest x_apiReq, HttpServletResponse x_response)
            throws Exception {

        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(x_response, x_apiReq.getCsvDownloadFile(), getCsvFileName("共募顧客検索"), IfaCommonUtil.getUserAccount());
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageConfirmDeleteA007
     * アクションID：A007
     * アクション名：削除確認
     * APIリクエスト：IfaJointSubscriptCustomerManageA007ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA007ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA007DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA007DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageConfirmDeleteA007")
    public String confirmDeleteA007(@RequestBody IfaJointSubscriptCustomerManageA007ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA007RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA007RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA007ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "confirmDeleteA007", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA007ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA007ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA007ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCustomerDetailA008
     * アクションID：A008
     * アクション名：顧客情報詳細
     * APIリクエスト：IfaJointSubscriptCustomerManageA008ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerManageA008ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerManageA008DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerManageA008DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerManageCustomerDetailA008")
    public String customerDetailA008(@RequestBody IfaJointSubscriptCustomerManageA008ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerManageA008RequestDto p_reqDto = new IfaJointSubscriptCustomerManageA008RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerManageA008ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerManageService",
                    "customerDetailA008", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerManageA008ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerManageA008ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerManageA008ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

    @Override
    protected String getCsvHeader() {
        return IfaJointSubscriptCustomerManageCsvOut.getHeaders();
    }
}
