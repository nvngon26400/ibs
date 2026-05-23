package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaCommFeeCsvOut;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaCommFeePattern;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaCommFeeA004bApiRequest;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020502-01", screenNumber = "")
public class IfaCommFeeController extends BaseController {

	@Autowired
    private IfaDateUtil ifaDateUtil;

    /** 区分.集計単位（日次/月次）.日次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_DAILY = "0";

    /** 区分.集計単位（日次/月次）.月次 */
    private static final String DAILY_MONTHLY_COUNTING_UNIT_MONTHLY = "1";

    /** エラー.{0}は{1}以内を正しく入力して下さい。 */
    private static final String ERRORS_DATE_RANGE = "errors.dateRange";

    /** 表示内容（手数料/報酬).手数料 */
    private static final String FEE_COMM_DISPLAY_CONTENT_FEE = "0";

    /** 表示内容（手数料/報酬).報酬 */
    private static final String FEE_COMM_DISPLAY_CONTENT_COMM = "1";

    /** JSONコンバータ */
    private JsonConverter jc = JsonConverter.getInstance();


    /**
     * アクセス：/brokerageMenu/commFee/ifaCommFeeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCommFeeA001ApiRequest
     * Apiレスポンス：IfaCommFeeA001ApiResponse
     * Dtoリクエスト：IfaCommFeeA001RequestDto
     * Dtoレスポンス：IfaCommFeeA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaCommFeeInitializeA001")
    public String initializeA001(
            @RequestBody IfaCommFeeA001ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCommFeeA001RequestDto appReq = new IfaCommFeeA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaCommFeeA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCommFeeService",
                "initializeA001",
                new TypeReference<DataList<IfaCommFeeA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCommFeeA001ApiResponse> apiRes = new DataList<IfaCommFeeA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaCommFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCommFeeA002ApiRequest
     * Apiレスポンス：IfaCommFeeA002ApiResponse
     * Dtoリクエスト：IfaCommFeeA002RequestDto
     * Dtoレスポンス：IfaCommFeeA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaCommFeeDisplayA002")
    public String displayA002(
            @RequestBody IfaCommFeeA002ApiRequest apiReq
    ) throws Exception {

        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        DataList<IfaCommFeeA002ResponseDto> apiResCheckPeriod
                = checkPeriodFromTo(apiReq.getDailyMonthlyCountingUnit(), apiReq.getPeriodYmInput());
        if (apiResCheckPeriod != null) {
            return jc.toString(apiResCheckPeriod);
        }

        // 仲介業者コードが4桁固定であることをチェックする
        DataList<IfaCommFeeA002ResponseDto> apiResCheckBrokerCodeList
                = checkBrokerCodeList(apiReq.getBrokerCode());
        if (apiResCheckBrokerCodeList != null) {
            return jc.toString(apiResCheckBrokerCodeList);
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCommFeeA002RequestDto appReq = new IfaCommFeeA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // 仲介業者コードをリストへ分離
        List<String> brokerCodeArray = Arrays.asList(StringUtils.split(apiReq.getBrokerCode(), ","));
        appReq.setBrokerCodeArray(brokerCodeArray);

        // 期間指定を2変数に分割
        appReq.setPeriodFrom(apiReq.getPeriodYmInput().get(0));
        appReq.setPeriodTo(apiReq.getPeriodYmInput().get(1));

        // サービスへ処理を移譲
        DataList<IfaCommFeeA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCommFeeService",
                "displayA002",
                new TypeReference<DataList<IfaCommFeeA002ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCommFeeA002ApiResponse> apiRes = new DataList<IfaCommFeeA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaCommFeeCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：IfaCommFeeA004aApiRequest
     * Apiレスポンス：IfaCommFeeA004aApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaCommFeeCsvOutputA004a")
    public String csvOutputA004a(
            @RequestBody IfaCommFeeA004aApiRequest apiReq
    ) throws Exception {

        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        DataList<IfaCommFeeA004aApiResponse> apiResCheckPeriod
                = checkPeriodFromTo(apiReq.getDailyMonthlyCountingUnit(), apiReq.getPeriodYmInput());
        if (apiResCheckPeriod != null) {
            return jc.toString(apiResCheckPeriod);
        }

        // 仲介業者コードが4桁固定であることをチェックする
        DataList<IfaCommFeeA004aResponseDto> apiResCheckBrokerCodeList
                = checkBrokerCodeList(apiReq.getBrokerCode());
        if (apiResCheckBrokerCodeList != null) {
            return jc.toString(apiResCheckBrokerCodeList);
        }

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaCommFeeA004aRequestDto appReq = new IfaCommFeeA004aRequestDto();
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
        DataList<IfaCommFeeA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaCommFeeService",
                "csvOutputA004a",
                new TypeReference<DataList<IfaCommFeeA004aResponseDto>>() { },
                appReq,
                fwSessionId
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaCommFeeA004aApiResponse> apiRes = new DataList<IfaCommFeeA004aApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/commFee/ifaCommFeeCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * APIリクエスト：IfaCommFeeA004bApiRequest
     * Apiレスポンス：IfaCommFeeA004bApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/commFee/ifaCommFeeCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(
            @RequestBody IfaCommFeeA004bApiRequest apiReq,
            HttpServletResponse response
    ) throws Exception {

        // パターンを取得
        IfaCommFeePattern pattern = IfaCommFeePattern.deserialize(apiReq.getPattern());

        // ファイル名を取得
        String fileName = "";
        if (StringUtils.equals(pattern.getDisplayContent(), FEE_COMM_DISPLAY_CONTENT_FEE)) {
            fileName = "手数料";
        } else if (StringUtils.equals(pattern.getDisplayContent(), FEE_COMM_DISPLAY_CONTENT_COMM)) {
            fileName = "報酬";
        }

        // A004aで作成したファイルをダウンロード
        doDownLoadCsvFile(
                response,
                apiReq.getCsvDownloadFile(),
                getCsvFileName(fileName),
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
    private <T> DataList<T> checkPeriodFromTo(
            String dailyMonthlyCountingUnit,
            List<String> periodYmInput
    ) throws Exception {

        // 集計単位が日次の場合
        if (StringUtils.equals(dailyMonthlyCountingUnit, DAILY_MONTHLY_COUNTING_UNIT_DAILY)) {

                // 以下のいずれかの条件を満たす場合エラーを返却
                // 1. リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が3ヶ月以上
                // 2. リクエスト.期間指定（From）がシステム日付-6ヶ月より小さい
                // 3. リクエスト.期間指定（To）がシステム日付-6ヶ月より小さい

                String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
                LocalDate now = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                LocalDate fromDate = LocalDate.parse(periodYmInput.get(0), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                LocalDate toDate = LocalDate.parse(periodYmInput.get(1), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                
                if (toDate.minusMonths(3).compareTo(fromDate) >= 0 || now.minusMonths(6).compareTo(fromDate) > 0
                || now.minusMonths(6).compareTo(toDate) > 0) {
                DataList<T> apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_DATE_RANGE,
                        IfaCommonUtil.getMessage(
                                ERRORS_DATE_RANGE,
                                new String[] {"期間指定", "過去6ヶ月の範囲で3ヶ月"}
                        )
                );

                return apiRes;
        }

        } else if (// 集計単位が月次の場合
                StringUtils.equals(dailyMonthlyCountingUnit, DAILY_MONTHLY_COUNTING_UNIT_MONTHLY)
        ) {
                LocalDate fromDate = LocalDate.parse(periodYmInput.get(0)  + "/01" , DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                LocalDate toDate = LocalDate.parse(periodYmInput.get(1)  + "/01" , DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        	
                // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が1年以上の場合エラーを返却
                if (toDate.minusYears(1).compareTo(fromDate) >= 0) {
                        DataList<T> apiRes = IfaCommonUtil.createDataList(
                                new ArrayList<>(),
                                ErrorLevel.FATAL,
                                ERRORS_DATE_RANGE,
                                IfaCommonUtil.getMessage(
                                        ERRORS_DATE_RANGE,
                                        new String[] {"期間指定", "1年"}
                                )
                        );

                        return apiRes;
                }
        }

        return null;
    }

    /**
     * CC005の仲介業者コードリストが全て4桁固定であることを確認
     * 値が空の場合はエラーにしない
     * 将来的に共通化される予定のため、メソッド内に閉じた実装とする
     * チェックリストに、このチェックが必要な旨記載あり
     *
     * @param brokerCodeListStr 仲介業者リスト
     * @return OK: null, NG: レスポンス用DataList
     */
    private <T> DataList<T> checkBrokerCodeList(String brokerCodeListStr) {
        final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";

        List<String> brokerCodeList = Arrays.asList(StringUtils.split(brokerCodeListStr, ","));
        List<String> safeBrokerCodeList = Optional.ofNullable(brokerCodeList).orElse(Collections.emptyList());

        // 仲介業者コードが4桁か入力チェック
        boolean isFourDigits = safeBrokerCodeList
                .stream()
                .allMatch(val -> val.length() == 4);

        if (isFourDigits == false) {
            return IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_BROKERCODE_NOTLENGTH,
                    IfaCommonUtil.getMessage(
                            ERRORS_BROKERCODE_NOTLENGTH,
                            new String[] {brokerCodeListStr}
                    )
            );
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
        return IfaCommFeeCsvOut.getHeader(pattern);
    }

}
