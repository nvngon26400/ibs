package com.sbisec.helios.gw.brokerageMenu.customerList.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListCsvUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.dto.SystemDateDtoRequest;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA005DownloadApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListA006ApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaDateUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本
 *
 * @author SCSK池田
 *
   2023/09/13 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0201_01-01", screenNumber = "31")
public class IfaCustomerListController extends BaseController {

    /** エラー.{0}には当日以前の日付を入力してください。 */
    private final static String ERRORS_DATE_SPECIFY_BEFORE_TODAY = "errors.date.specifyBeforeToday";
    
    /** エラー.{0}には前日以前の日付を入力してください。 */
    private final static String ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY = "errors.date.specifyBeforeYesterday";

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCustomerListA001ApiRequest
     * Apiレスポンス：IfaCustomerListA001ApiResponse
     * Dtoリクエスト：IfaCustomerListA001DtoRequest
     * Dtoレスポンス：IfaCustomerListA001DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListInitializeA001")
    public String initializeA001(@RequestBody IfaCustomerListA001ApiRequest apiReq) throws Exception {
        
        IfaCustomerListA001RequestDto appReq = new IfaCustomerListA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaCustomerListA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListService",
                "initializeA001", new TypeReference<DataList<IfaCustomerListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerListA001ApiResponse> apiRes = new DataList<IfaCustomerListA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCustomerListA002ApiRequest
     * Apiレスポンス：IfaCustomerListA002ApiResponse
     * Dtoリクエスト：IfaCustomerListA002DtoRequest
     * Dtoレスポンス：IfaCustomerListA002DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListDisplayA002")
    public String displayA002(@RequestBody IfaCustomerListA002ApiRequest apiReq) throws Exception {

        /* =================================================*/
        /* ドメインチェック外の単項目チェック                  */
        /* =================================================*/

        boolean checkFailed = false;
        String[] params = new String[] {};
        String returnCode = "";

        do {
            // 口座開設日(FROM)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getOpenAccountFrom())
                    && checkDateSpecifyBeforeYesterday(apiReq.getOpenAccountFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"口座開設日From"};

                break;
            }

            // 口座開設日(To)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getOpenAccountTo())
                    && checkDateSpecifyBeforeYesterday(apiReq.getOpenAccountTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"口座開設日To"};

                break;
            }
            
            // 最終約定日(FROM)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getLastTradeDateFrom())
                    && checkDateSpecifyBeforeYesterday(apiReq.getLastTradeDateFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params =  new String[] {"最終約定日From"};

                break;
            }

            // 最終約定日(TO)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getLastTradeDateTo())
                    && checkDateSpecifyBeforeYesterday(apiReq.getLastTradeDateTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"最終約定日To"};

                break;
            }

            // コース変更完了日(FROM)が入力されている場合、本日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getCourseChangeFinishDateFrom())
                    && checkDateSpecifyBeforeToday(apiReq.getCourseChangeFinishDateFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_TODAY;
                params = new String[] {"コース変更完了日From"};

                break;
            }

            // コース変更完了日(TO)が入力されている場合、本日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getCourseChangeFinishDateTo())
                    && checkDateSpecifyBeforeToday(apiReq.getCourseChangeFinishDateTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_TODAY;
                params = new String[] {"コース変更完了日To"};

                break;
            }

        } while(false);

        // 単項目チェックを通過しなかった場合、エラーレスポンスを返却する
        if (checkFailed) {
            DataList<IfaCustomerListA002ApiResponse> apiCheckErrorRes = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    returnCode,
                    IfaCommonUtil.getMessage(returnCode, params)
            );

            return jc.toString(apiCheckErrorRes);
        }

        IfaCustomerListA002RequestDto appReq = new IfaCustomerListA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaCustomerListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListService",
                "displayA002", new TypeReference<DataList<IfaCustomerListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerListA002ApiResponse> apiRes = new DataList<IfaCustomerListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerListA005ApiRequest
     * Apiレスポンス：IfaCustomerListA005ApiResponse
     * Dtoリクエスト：IfaCustomerListA005DtoRequest
     * Dtoレスポンス：IfaCustomerListA005DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListCsvOutputA005")
    public String csvOutputA005(@RequestBody IfaCustomerListA005ApiRequest apiReq) throws Exception {

        /* =================================================*/
        /* ドメインチェック外の単項目チェック                  */
        /* =================================================*/

        boolean checkFailed = false;
        String[] params = new String[] {};
        String returnCode = "";

        do {
            // 口座開設日(FROM)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getOpenAccountFrom())
                    && checkDateSpecifyBeforeYesterday(apiReq.getOpenAccountFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"口座開設日From"};

                break;
            }

            // 口座開設日(To)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getOpenAccountTo())
                    && checkDateSpecifyBeforeYesterday(apiReq.getOpenAccountTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"口座開設日To"};

                break;
            }
            
            // 最終約定日(FROM)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getLastTradeDateFrom())
                    && checkDateSpecifyBeforeYesterday(apiReq.getLastTradeDateFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params =  new String[] {"最終約定日From"};

                break;
            }

            // 最終約定日(TO)が入力されている場合、前日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getLastTradeDateTo())
                    && checkDateSpecifyBeforeYesterday(apiReq.getLastTradeDateTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY;
                params = new String[] {"最終約定日To"};

                break;
            }

            // コース変更完了日(FROM)が入力されている場合、本日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getCourseChangeFinishDateFrom())
                    && checkDateSpecifyBeforeToday(apiReq.getCourseChangeFinishDateFrom()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_TODAY;
                params = new String[] {"コース変更完了日From"};

                break;
            }

            // コース変更完了日(TO)が入力されている場合、本日以前チェック
            if (
                    StringUtils.isNotEmpty(apiReq.getCourseChangeFinishDateTo())
                    && checkDateSpecifyBeforeToday(apiReq.getCourseChangeFinishDateTo()) == false
            ) {
                checkFailed = true;
                returnCode = ERRORS_DATE_SPECIFY_BEFORE_TODAY;
                params = new String[] {"コース変更完了日To"};

                break;
            }

        } while(false);

        // 単項目チェックを通過しなかった場合、エラーレスポンスを返却する
        if (checkFailed) {
            DataList<IfaCustomerListA005ApiResponse> apiCheckErrorRes = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    returnCode,
                    IfaCommonUtil.getMessage(returnCode, params)
            );

            return jc.toString(apiCheckErrorRes);
        }
        
        IfaCustomerListA005RequestDto appReq = new IfaCustomerListA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaCustomerListA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListService",
                "csvOutputA005", new TypeReference<DataList<IfaCustomerListA005ResponseDto>>() {
                }, appReq, IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        DataList<IfaCustomerListA005ApiResponse> apiRes = new DataList<IfaCustomerListA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        if (apiRes.getTotalSize() > 0) {
            // 正常終了時、dataListは空なので、パターンのみのBeanをセットする
            // このパターンは権限3でダウンロード時に使用される。
            apiRes.setDataList(List.of(new IfaCustomerListA005ApiResponse(encodePattern(appReq))));
        }
        
        if (apiRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
            setStatusAndMessageToDataList(apiRes, true);
        }
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListCsvDownloadA005
     * アクションID：A005
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
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListCsvDownloadA005")
    public void csvDownloadA005(@RequestBody IfaCustomerListA005DownloadApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("基本"), IfaCommonUtil.getUserAccount(),
                apiReq.getPattern());
        
    }
    
    @Override
    protected String getCsvHeader(String pattern) {
        
        try {
            return IfaCustomerListCsvUtil.getHeaders(decodePattern(pattern));
        } catch (IOException e) {
            //例外処理
            throw new UnsupportedOperationException("failed to decode pattern", e);
        }
    }
    
    /**
     * アクセス：/brokerageMenu/customerList/ifaCustomerListBbApplicationA006
     * アクションID：A006
     * アクション名：BB申込
     * APIリクエスト：IfaCustomerListA006ApiRequest
     * Apiレスポンス：IfaCustomerListA006ApiResponse
     * Dtoリクエスト：IfaCustomerListA006DtoRequest
     * Dtoレスポンス：IfaCustomerListA006DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListBbApplicationA006")
    public String bbApplyA006(@RequestBody IfaCustomerListA006ApiRequest apiReq) throws Exception {
        
        IfaCustomerListA006RequestDto appReq = new IfaCustomerListA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaCustomerListA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListService",
                "bbApplyA006", new TypeReference<DataList<IfaCustomerListA006ResponseDto>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
    private String encodePattern(IfaCustomerListA005RequestDto dto) throws IOException {
        
        // dtoをjson化しbase64エンコードする
        return Base64.getEncoder().encodeToString(jc.toString(dto).getBytes());
    }
    
    private IfaCustomerListA005RequestDto decodePattern(String enc) throws IOException {
        
        // base64デコードした後dtoにデシリアライズする
        return jc.toObject(new String(Base64.getDecoder().decode(enc)), IfaCustomerListA005RequestDto.class);
    }

    /**
     * 単項目チェック：指定された日付が昨日以前であるか
     *
     * @param value チェック対象日付
     * @return OK：true, NG: false
     * @exception Exception システムエラー
     */
    private boolean checkDateSpecifyBeforeYesterday(String value) throws Exception {
        return checkDateSpecifyBeforeOffsetDate(value, -1L);
    }

    /**
     * 単項目チェック：指定された日付が本日以前であるか
     *
     * @param value チェック対象日付
     * @return OK：true, NG: false
     * @exception Exception システムエラー
     */
    private boolean checkDateSpecifyBeforeToday(String value) throws Exception {
        return checkDateSpecifyBeforeOffsetDate(value, 0L);
    }

    /**
     * 単項目チェック：指定日以前
     *
     * @param value チェック対象日付
     * @param offset 0: 本日, -1: 昨日, -n: n日前, 1: 翌日, n: n日後
     * @return  OK：true, NG: false
     * @exception Exception システムエラー
     */
    private boolean checkDateSpecifyBeforeOffsetDate(String value, Long offset) throws Exception {
        // 本日の日付をLocalDate型で取得
        // 可能ならば[システム共通情報.サーバ日時]を使用
        // [システム共通情報.サーバ日付]の取得に失敗した場合、システム日時を使用
        Date currentDate = new Date();

        SystemDateDtoRequest systemDateServiceReq = new SystemDateDtoRequest();
        DataList<Date> systemDateServiceRes = ApiRequestUtil.invoke(
                "systemDateService",
                "getSystemDate",
                new TypeReference<DataList<Date>>() {},
                systemDateServiceReq
        );

        if (CollectionUtils.isNotEmpty(systemDateServiceRes.getDataList())) {
            currentDate = systemDateServiceRes.get(0);
        }

        ZoneId tz = ZoneId.of("UTC+09:00");
        LocalDate currentLocalDate = currentDate.toInstant().atZone(tz).toLocalDate();

        // 基準日を本日 + offset日で計算
        LocalDate baseLocalDate = currentLocalDate.plusDays(offset);

        // チェック対象日付をLocalDate型に変換
        LocalDate targetLocalDate = LocalDate.parse(
                value,
                DateTimeFormatter.ofPattern(IfaDateUtil.SEPARATED_YYYYMMDD)
        );

        // チェック対象日付が基準日以前である場合trueを返却
        if (targetLocalDate.isBefore(baseLocalDate) || targetLocalDate.isEqual(baseLocalDate)) {
            return true;
        }

        return false;
    }

}
