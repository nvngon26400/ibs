package com.sbisec.helios.gw.internalAdminMenu.formAcquire.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.util.LedgerClass;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA003aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA003aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaContractNoteCustomerLedgerAcquireA003bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0402_02-01
 * 画面名：取引日記帳・顧客勘定元帳取得
 *
 * @author SCSK
 2024/05/08 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0402_02-01", screenNumber = "")
public class IfaContractNoteCustomerLedgerAcquireController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** サービス名 */
    private static final String SERVICE_NAME = "cmpIfaContractNoteCustomerLedgerAcquireService";
    
    private static final String MSG_ERR_DOWNLOAD = "errors.operatePermitionCheck";
    
    /** 日付前後エラー */
    private static final String MSG_ERR_FROM_TO = "errors.date.specifyFromTo";
    
    /** 日付間隔エラー */
    private static final String MSG_ERR_DATE_RANGE = "errors.dateRange";
    
    /** 仲介業者コードフォーマットエラー */
    private static final String MSG_ERR_BC_LEN = "errors.brokerCodeNotLength";
    
    /** 仲介業者コード長 */
    private static final int BROKER_LEN = 4;
    
    /** from to 月間隔 */
    private static final long MAX_MONTHS = 3L;
    
    /**
     * アクセス：/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Apiレスポンス：IfaContractNoteCustomerLedgerAcquireA001ApiResponse
     * Dtoレスポンス：IfaContractNoteCustomerLedgerAcquireA001ResponseDto
     *
     * @param apiReq APIリクエスト
     * @return APIレスポンス
     * @exception Exception システム例外
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaContractNoteCustomerLedgerAcquireA001ApiRequest apiReq)
            throws Exception {
        
        IfaContractNoteCustomerLedgerAcquireA001RequestDto appReq = new IfaContractNoteCustomerLedgerAcquireA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaContractNoteCustomerLedgerAcquireA001ResponseDto> appRes = ApiRequestUtil.invoke(SERVICE_NAME,
                "initializeA001", new TypeReference<DataList<IfaContractNoteCustomerLedgerAcquireA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaContractNoteCustomerLedgerAcquireA001ApiResponse> apiRes = new DataList<IfaContractNoteCustomerLedgerAcquireA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaContractNoteCustomerLedgerAcquireA002ApiRequest
     * Apiレスポンス：IfaContractNoteCustomerLedgerAcquireA002ApiResponse
     * Dtoリクエスト：IfaContractNoteCustomerLedgerAcquireA002RequestDto
     * Dtoレスポンス：IfaContractNoteCustomerLedgerAcquireA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireDisplayA002")
    public String displayA002(@RequestBody @Validated IfaContractNoteCustomerLedgerAcquireA002ApiRequest apiReq)
            throws Exception {
        
        var validated = validateA002(apiReq);
        if (Objects.nonNull(validated)) {
            return jc.toString(validated);
        }
        
        IfaContractNoteCustomerLedgerAcquireA002RequestDto appReq = new IfaContractNoteCustomerLedgerAcquireA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaContractNoteCustomerLedgerAcquireA002ResponseDto> appRes = ApiRequestUtil.invoke(SERVICE_NAME,
                "displayA002", new TypeReference<DataList<IfaContractNoteCustomerLedgerAcquireA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaContractNoteCustomerLedgerAcquireA002ApiResponse> apiRes = new DataList<IfaContractNoteCustomerLedgerAcquireA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * A002項目バリデーションを行う
     *
     * @param apiReq APIリクエスト
     * @return エラー時はDataList、正常時はnull
     */
    private DataList<IfaContractNoteCustomerLedgerAcquireA002ResponseDto> validateA002(
            IfaContractNoteCustomerLedgerAcquireA002ApiRequest apiReq) {
        
        // 仲介業者コード
        List<String> reqBrokerCodes = StringUtils.isBlank(apiReq.getBrokerCode()) ? Collections.emptyList()
                : Arrays.asList(apiReq.getBrokerCode().split(","));
        var invalidBc = reqBrokerCodes.stream().filter(s -> s.length() != BROKER_LEN).findFirst().orElse(null);
        if (Objects.nonNull(invalidBc)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MSG_ERR_BC_LEN,
                    IfaCommonUtil.getMessage(MSG_ERR_BC_LEN, new String[] { invalidBc }));
        }
        
        // 作成日チェック
        var fromDate = LocalDate.parse(apiReq.getCreateDateFrom(), DateTimeFormatter.BASIC_ISO_DATE);
        var toDate = LocalDate.parse(apiReq.getCreateDateTo(), DateTimeFormatter.BASIC_ISO_DATE);
        if (toDate.isBefore(fromDate)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MSG_ERR_FROM_TO,
                    IfaCommonUtil.getMessage(MSG_ERR_FROM_TO, new String[] { "作成日" }));
        }
        if (toDate.minusMonths(MAX_MONTHS).compareTo(fromDate) > 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MSG_ERR_DATE_RANGE,
                    IfaCommonUtil.getMessage(MSG_ERR_DATE_RANGE, new String[] { "作成日に", "3ヶ月" }));
        }
        return null;
    }
    
    /**
     * アクセス：/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireGetPDFA003a
     * アクションID：A003
     * アクション名：ダウンロード(パス応答)
     * APIリクエスト：IfaContractNoteCustomerLedgerAcquireA003ApiRequest
     * Dtoリクエスト：IfaContractNoteCustomerLedgerAcquireA003RequestDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireGetPDFA003a")
    public String getPdfA003a(@RequestBody @Validated IfaContractNoteCustomerLedgerAcquireA003aApiRequest apiReq)
            throws Exception {
        
        var response = Optional.ofNullable(LedgerClass.valueOfCode(apiReq.getLedgerClass()))//
                .map(lc -> {
                    return Paths.get(apiReq.getFileDirectory(), lc.getDirName(), apiReq.getCreateDate(),
                            apiReq.getDownloadFileName());
                })//
                .filter(Files::isRegularFile)//
                .map(p -> new IfaContractNoteCustomerLedgerAcquireA003aApiResponse(p.toString()))//
                .orElse(null);
        
        if (Objects.isNull(response)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, MSG_ERR_DOWNLOAD,
                    IfaCommonUtil.getMessage(MSG_ERR_DOWNLOAD, new String[] { "PDFが不正", "ダウンロードが" })));
        }
        
        return jc.toString(IfaCommonUtil.createDataList(List.of(response), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.toString(), null));
    }
    
    /**
     * アクセス：/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireGetPDFA003b
     * アクションID：A003
     * アクション名：ダウンロード
     * APIリクエスト：IfaContractNoteCustomerLedgerAcquireA003ApiRequest
     * Dtoリクエスト：IfaContractNoteCustomerLedgerAcquireA003RequestDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaContractNoteCustomerLedgerAcquireGetPDFA003b")
    public ResponseEntity<?> getPdfA003b(
            @RequestBody @Validated IfaContractNoteCustomerLedgerAcquireA003bApiRequest apiReq) throws Exception {
        
        var path = Paths.get(apiReq.getPdfFileName());
        var fileName = Optional.ofNullable(path.getFileName()).map(Path::toString).orElse(null);
        if (Objects.nonNull(fileName)) {
            return ResponseEntity.ok()//
                    .contentType(MediaType.parseMediaType("application/pdf")) //
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(new UrlResource(path.toUri()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
