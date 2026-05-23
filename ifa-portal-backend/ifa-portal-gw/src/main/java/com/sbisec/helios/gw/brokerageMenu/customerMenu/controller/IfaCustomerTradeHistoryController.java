package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import com.sbisec.helios.gw.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaCustomerTradeHistoryCsvOut;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerTradeHistoryA004bApiRequest;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0109-01", screenNumber = "")
public class IfaCustomerTradeHistoryController extends BaseController {

    @Autowired
    private IfaDateUtil ifaDateUtil;

    /** CSVファイル名 */
    private static final String CSV_FILE_NAME = "取引履歴（顧客別）";

    /** JSONコンバータ */
    private JsonConverter jc = JsonConverter.getInstance();


    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerTradeHistoryInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCustomerTradeHistoryA001ApiRequest
     * Apiレスポンス：IfaCustomerTradeHistoryA001ApiResponse
     * Dtoリクエスト：IfaCustomerTradeHistoryA001RequestDto
     * Dtoレスポンス：IfaCustomerTradeHistoryA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerTradeHistoryInitializeA001")
    public String initializeA001(
            @RequestBody IfaCustomerTradeHistoryA001ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCustomerTradeHistoryA001RequestDto appReq = new IfaCustomerTradeHistoryA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaCustomerTradeHistoryA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerTradeHistoryService",
                "initializeA001",
                new TypeReference<DataList<IfaCustomerTradeHistoryA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCustomerTradeHistoryA001ApiResponse> apiRes = new DataList<IfaCustomerTradeHistoryA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerTradeHistoryDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCustomerTradeHistoryA002ApiRequest
     * Apiレスポンス：IfaCustomerTradeHistoryA002ApiResponse
     * Dtoリクエスト：IfaCustomerTradeHistoryA002RequestDto
     * Dtoレスポンス：IfaCustomerTradeHistoryA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerTradeHistoryDisplayA002")
    public String displayA002(
            @RequestBody IfaCustomerTradeHistoryA002ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCustomerTradeHistoryA002RequestDto appReq = new IfaCustomerTradeHistoryA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaCustomerTradeHistoryA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerTradeHistoryService",
                "displayA002",
                new TypeReference<DataList<IfaCustomerTradeHistoryA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCustomerTradeHistoryA002ApiResponse> apiRes = new DataList<IfaCustomerTradeHistoryA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerTradeHistoryCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerTradeHistoryA004aApiRequest
     * Apiレスポンス：IfaCustomerTradeHistoryA004aApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerTradeHistoryCsvOutputA004a")
    public String csvOutputA004a(
            @RequestBody IfaCustomerTradeHistoryA004aApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCustomerTradeHistoryA004aRequestDto appReq = new IfaCustomerTradeHistoryA004aRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // フレームワークセッションIDを取得
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);

        // サービスへ処理を移譲
        DataList<IfaCustomerTradeHistoryA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerTradeHistoryService",
                "csvOutputA004a",
                new TypeReference<DataList<IfaCustomerTradeHistoryA004aResponseDto>>() { },
                appReq,
                fwSessionId
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCustomerTradeHistoryA004aApiResponse> apiRes = new DataList<IfaCustomerTradeHistoryA004aApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerTradeHistoryCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerTradeHistoryA004bApiRequest
     * Apiレスポンス：IfaCustomerTradeHistoryA004bApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerTradeHistoryCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(
            @RequestBody IfaCustomerTradeHistoryA004bApiRequest apiReq,
            HttpServletResponse response
    ) throws Exception {

        // A004aで作成したファイルをダウンロード
        doDownLoadCsvFile(
                response,
                apiReq.getCsvDownloadFile(),
                getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount(),
                null,
                false // 認可情報に関わらず強制的にCSVダウンロードを行う｡
        );
    }

    /**
     * CSVヘッダーを取得する
     * 権限3でCSVをダウンロードした際の記録用。
     *
     * @return CSVヘッダー
     */
    @Override
    protected String getCsvHeader() {
        return IfaCustomerTradeHistoryCsvOut.getHeader();
    }

}
