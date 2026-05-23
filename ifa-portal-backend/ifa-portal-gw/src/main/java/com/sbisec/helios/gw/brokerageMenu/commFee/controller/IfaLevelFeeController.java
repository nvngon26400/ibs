package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

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
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaLevelFeeCsvOut;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaLevelFeeA004bApiRequest;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020505-01", screenNumber = "78")
public class IfaLevelFeeController extends BaseController {

	@Autowired
    private IfaDateUtil ifaDateUtil;

    /** 区分.集計単位（日次/月次）.月次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";

    /** エラー.{0}は{1}以内を正しく入力して下さい。 */
    private static final String ERRORS_DATE_RANGE = "errors.dateRange";

    /** CSVファイル名 */
    private static final String CSV_FILE_NAME = "レベルフィー";

    /** JSONコンバータ */
    private JsonConverter jc = JsonConverter.getInstance();


    /**
     * アクセス：/brokerageMenu/commFee/ifaLevelFeeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaLevelFeeA001ApiRequest
     * Apiレスポンス：IfaLevelFeeA001ApiResponse
     * Dtoリクエスト：IfaLevelFeeA001RequestDto
     * Dtoレスポンス：IfaLevelFeeA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaLevelFeeInitializeA001")
    public String initializeA001(
            @RequestBody IfaLevelFeeA001ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaLevelFeeA001RequestDto appReq = new IfaLevelFeeA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaLevelFeeA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLevelFeeService",
                "initializeA001",
                new TypeReference<DataList<IfaLevelFeeA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaLevelFeeA001ApiResponse> apiRes = new DataList<IfaLevelFeeA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaLevelFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaLevelFeeA002ApiRequest
     * Apiレスポンス：IfaLevelFeeA002ApiResponse
     * Dtoリクエスト：IfaLevelFeeA002RequestDto
     * Dtoレスポンス：IfaLevelFeeA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaLevelFeeDisplayA002")
    public String displayA002(
            @RequestBody IfaLevelFeeA002ApiRequest apiReq
    ) throws Exception {

        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        DataList<IfaLevelFeeA002ResponseDto> apiResCheckPeriod = checkPeriodFromTo(apiReq.getDailyMonthlyCountingUnit(), apiReq.getPeriodYmInput());
        if (apiResCheckPeriod != null) {
            return jc.toString(apiResCheckPeriod);
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaLevelFeeA002RequestDto appReq = new IfaLevelFeeA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // 仲介業者コードをリストへ分離
        List<String> brokerCodeArray = Arrays.asList(StringUtils.split(apiReq.getBrokerCode(), ","));
        appReq.setBrokerCodeArray(brokerCodeArray);

        // 期間指定を2変数に分割
        appReq.setPeriodFrom(apiReq.getPeriodYmInput().get(0));
        appReq.setPeriodTo(apiReq.getPeriodYmInput().get(1));

        // サービスへ処理を移譲
        DataList<IfaLevelFeeA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLevelFeeService",
                "displayA002",
                new TypeReference<DataList<IfaLevelFeeA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaLevelFeeA002ApiResponse> apiRes = new DataList<IfaLevelFeeA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaLevelFeeCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaLevelFeeA004aApiRequest
     * Apiレスポンス：IfaLevelFeeA004aApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaLevelFeeCsvOutputA004a")
    public String csvOutputA004a(
            @RequestBody IfaLevelFeeA004aApiRequest apiReq
    ) throws Exception {

        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        DataList<IfaLevelFeeA004aApiResponse> apiResCheckPeriod = checkPeriodFromTo(apiReq.getDailyMonthlyCountingUnit(), apiReq.getPeriodYmInput());
        if (apiResCheckPeriod != null) {
            return jc.toString(apiResCheckPeriod);
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaLevelFeeA004aRequestDto appReq = new IfaLevelFeeA004aRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // 仲介業者コードをリストへ分離
        List<String> brokerCodeArray = Arrays.asList(StringUtils.split(apiReq.getBrokerCode(), ","));
        appReq.setBrokerCodeArray(brokerCodeArray);

        // 期間指定を2変数に分離
        appReq.setPeriodFrom(apiReq.getPeriodYmInput().get(0));
        appReq.setPeriodTo(apiReq.getPeriodYmInput().get(1));

        // フレームワークセッションIDを取得
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);

        // サービスへ処理を移譲
        DataList<IfaLevelFeeA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaLevelFeeService",
                "csvOutputA004a",
                new TypeReference<DataList<IfaLevelFeeA004aResponseDto>>() { },
                appReq,
                fwSessionId
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaLevelFeeA004aApiResponse> apiRes = new DataList<IfaLevelFeeA004aApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaLevelFeeCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * APIリクエスト：IfaLevelFeeA004bApiRequest
     * Apiレスポンス：IfaLevelFeeA004bApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaLevelFeeCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(
            @RequestBody IfaLevelFeeA004bApiRequest apiReq,
            HttpServletResponse response
    ) throws Exception {

        // A004aで作成したファイルをダウンロード
        doDownLoadCsvFile(
                response,
                apiReq.getCsvDownloadFile(),
                getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount(),
                apiReq.getPattern()
        );

    }

    /**
     * 期間指定_入力の関連項目チェック
     *
     * @param dailyMonthlyCountingUnit 集計単位(日次/月次)の別
     * @param paramYmInput [期間FROM, 期間TO]
     * @return OK：null、NG：エラーレスポンス
     * @exception Exception システムエラー
     */
    private <T> DataList<T> checkPeriodFromTo(String dailyMonthlyCountingUnit, List<String> periodYmInput) throws Exception {
        String fromDateStr = periodYmInput.get(0);
        String toDateStr = periodYmInput.get(1);
        
        // 集計単位が月次の場合
        if (StringUtils.equals(dailyMonthlyCountingUnit, DAILY_MONTHLY_COUNTING_UNIT_MONTHLY)) {
            fromDateStr = fromDateStr + "/01";
            toDateStr = toDateStr + "/01";
        }

        // 以下のいずれかの条件を満たす場合エラーを返却
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
        LocalDate fromDate = LocalDate.parse(fromDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate toDate = LocalDate.parse(toDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        if (toDate.minusMonths(6).compareTo(fromDate) >= 0) {
            DataList<T> apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_DATE_RANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATE_RANGE, new String[] {"手数料徴収日", "6ヶ月"})
            );
            return apiRes;
        }

        return null;
    }

    /**
     * CSVヘッダーを取得する
     * 権限3でCSVをダウンロードした際の記録用。
     *
     * @param pattern パターン
     * @return CSVヘッダー
     */
    @Override
    protected String getCsvHeader(String pattern) {
        return IfaLevelFeeCsvOut.getHeader(pattern);
    }

}
