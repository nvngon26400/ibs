package com.sbisec.helios.gw.brokerageMenu.customerList.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA005aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsCommonRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListFuturesOptionsCsvUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListFuturesOptionsA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListFuturesOptionsA005aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListFuturesOptionsA005bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListFuturesOptionsCommonApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
   2024/05/29 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0201_03-01", screenNumber = "33")
public class IfaCustomerListFuturesOptionsController extends BaseController {

    @Override
    protected String getCsvHeader() {

        return IfaCustomerListFuturesOptionsCsvUtil.HEADER;
    }

    private JsonConverter jc = JsonConverter.getInstance();

    private static final String SERVICE_NAME = "cmpIfaCustomerListFuturesOptionsService";

    private static final String CSV_FILE_NAME = "先OP";

    private static final int BROKER_LEN = 4;

    /** 仲介業者コードフォーマットエラー */
    private static final String MSG_ERR_BC_LEN = "errors.brokerCodeNotLength";

    /**

     * アクセス：/brokerageMenu/customerList/ifaCustomerListFuturesOptionsDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCustomerListFuturesOptionsCommonApiRequest
     * Apiレスポンス：IfaCustomerListFuturesOptionsA002ApiResponse
     * Dtoリクエスト：IfaCustomerListFuturesOptionsA002RequestDto
     * Dtoレスポンス：IfaCustomerListFuturesOptionsA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListFuturesOptionsDisplayA002")
    public String diplayA002(@RequestBody IfaCustomerListFuturesOptionsCommonApiRequest apiReq)
            throws Exception {

        var checkRes = validateBrokerCode(apiReq.getBrokerCode());
        if (Objects.nonNull(checkRes)) {
            return jc.toString(checkRes);
        }

        var appReq = new IfaCustomerListFuturesOptionsCommonRequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "displayA002",
                new TypeReference<DataList<IfaCustomerListFuturesOptionsA002ResponseDto>>() {
                }, appReq);

        var apiRes = new DataList<IfaCustomerListFuturesOptionsA002ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * 仲介業者コード項目バリデーションを行う
     *
     * @param brokerCode 仲介業者コード
     * @return エラー時はDataList、正常時はnull
     */
    private DataList<?> validateBrokerCode(String brokerCode) {

        List<String> reqBrokerCodes = StringUtils.isBlank(brokerCode) ? Collections.emptyList()
                : Arrays.asList(brokerCode.split(","));
        return reqBrokerCodes.stream() //
                .filter(s -> s.length() != BROKER_LEN) //
                .findFirst() //
                .map(str -> IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MSG_ERR_BC_LEN,
                        IfaCommonUtil.getMessage(MSG_ERR_BC_LEN, new String[] { str })))//
                .orElse(null);
    }

    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListFuturesOptionsCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerListFuturesOptionsCommonApiRequest
     * Apiレスポンス：IfaCustomerListFuturesOptionsA005aApiResponse
     * Dtoリクエスト：IfaCustomerListFuturesOptionsCommonRequestDto
     * Dtoレスポンス：IfaCustomerListFuturesOptionsA005aResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListFuturesOptionsCsvOutputA005")
    public String csvOutputA005(@RequestBody IfaCustomerListFuturesOptionsCommonApiRequest apiReq)
            throws Exception {

        var checkRes = validateBrokerCode(apiReq.getBrokerCode());
        if (Objects.nonNull(checkRes)) {
            return jc.toString(checkRes);
        }
        var appReq = new IfaCustomerListFuturesOptionsCommonRequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "csvOutputA005",
                new TypeReference<DataList<IfaCustomerListFuturesOptionsA005aResponseDto>>() {
                }, appReq);

        var apiRes = new DataList<IfaCustomerListFuturesOptionsA005aApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListFuturesOptionsCsvDownloadA005
     * アクションID：A005
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaCustomerListFuturesOptionsA005bApiRequest
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListFuturesOptionsCsvDownloadA005")
    public void csvDownloadA005(@RequestBody IfaCustomerListFuturesOptionsA005bApiRequest apiReq,
            HttpServletResponse response) throws Exception {

        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount());

    }

    @Override
    protected String getFirstViewName() {

        return null;
    }
}
