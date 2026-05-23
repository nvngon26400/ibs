package com.sbisec.helios.ap.brokerageMenu.customerList.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct031;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.broker.model.ImprintInquiryModel;
import com.sbisec.helios.ap.broker.service.ImprintInquiryService;
import com.sbisec.helios.ap.broker.util.PapyApiReturnCode;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.service.IfaImprintInquiryService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0201_01-02
 * 画面名：印影照会
 *
 * @author SCSK
    2024/03/22 新規作成
 */
@Component(value = "cmpIfaImprintInquiryService")
public class IfaImprintInquiryServiceImpl implements IfaImprintInquiryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaImprintInquiryServiceImpl.class);

    private static final char CHAR_ZERO = '0';

    private static final String SUCCESS_MESSAGE = "";

    /** 権限なし */
    private static final String NO_AUTHORIZED = "0";

    /** 入力した部店口座は存在しません */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";

    /** 顧客情報の取得が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    private static final String ERRORS_PROCESSINGFAILED_PARAM_0 = "顧客情報の取得";

    private static final String IFA_IDENTIFIER = "IFA_USER";

    /** PAPYに接続できません。 */
    private static final String ERRORS_PAPYAPICONNECTION = "errors.papyApiConnection";

    private static final String PAPY_WARN_087 = "PAPY_WARN_087";

    private static final String PAPY_WARN_088 = "PAPY_WARN_088";

    private static final String PAPY_ERROR_029 = "PAPY_ERROR_029";

    private static final String PAPY_WARN_087_MESSAGE = "IFAから部店「{0}」、口座番号「{1}」を照会するデータが存在しません。";

    private static final String PAPY_WARN_088_MESSAGE = "IFAから部店「{0}」、口座番号「{1}」を照会する帳票ファイルが存在しません。";

    private static final String PAPY_ERROR_029_MESSAGE = "[顧客帳票情報取得]にエラーが発生しました。";

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct031 fct031;

    @Autowired
    private ImprintInquiryService imprintInquiryService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaImprintInquiryA001DtoRequest
     * DTO レスポンス：IfaImprintInquiryA001DtoResponse
     * model リクエスト：IfaImprintInquiryA001RequestModel
     * model レスポンス：IfaImprintInquiryA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaImprintInquiryA001ResponseDto
     * @exception Exception 例外
     */
    @Override
    public DataList<IfaImprintInquiryA001ResponseDto> initializeA001(IfaImprintInquiryA001RequestDto dtoReq)
            throws Exception {

        DataList<IfaImprintInquiryA001ResponseDto> dtoRes = new DataList<IfaImprintInquiryA001ResponseDto>();
        List<IfaImprintInquiryA001ResponseDto> dtoResList = new ArrayList<IfaImprintInquiryA001ResponseDto>();
        IfaImprintInquiryA001ResponseDto ifaImprintInquiryA001ResponseDto = new IfaImprintInquiryA001ResponseDto();
        dtoResList.add(ifaImprintInquiryA001ResponseDto);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaImprintInquiryServiceImplL.initializeA001");
        }

        BeanUtils.copyProperties(ifaImprintInquiryA001ResponseDto, dtoReq);
        // 顧客情報の取得      
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();

        // 1.利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(accountNumber);
        inputFct001Dto.setButenCode(butenCode);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);

        // 対象顧客参照権限有無＝"0"（権限なし） エラー
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            return dtoRes;
        }

        // 2.顧客情報を取得する。
        InputFct031Dto inputFct031Dto = new InputFct031Dto();
        inputFct031Dto.setAccountNumber(accountNumber);
        inputFct031Dto.setButenCode(butenCode);
        OutputFct031Dto outputFct031Dto = fct031.getData(inputFct031Dto);

        // 顧客情報取得失敗エラー
        if (StringUtils.isNotBlank(outputFct031Dto.getReturnCode())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED,
                            new String[] { ERRORS_PROCESSINGFAILED_PARAM_0 }));
            return dtoRes;
        } else {
            ifaImprintInquiryA001ResponseDto.setCustomerNameKana(outputFct031Dto.getCustomerNameKana());
            ifaImprintInquiryA001ResponseDto.setCustomerNameKanji(outputFct031Dto.getCustomerNameKanji());
        }

        // 3.帳票ファイル取得APIの呼び出しを行う。
        DataList<ImprintInquiryModel> dataList = new DataList<ImprintInquiryModel>();

        dataList = imprintInquiryService.getImprintImage(IFA_IDENTIFIER, butenCode,
                StringUtil.fillLeft(accountNumber, CHAR_ZERO, 7));

        if (PapyApiReturnCode.PAPY_API_CONNECTION_ERROR_CODE.equals(dataList.getReturnCode())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PAPYAPICONNECTION,
                    IfaCommonUtil.getMessage(ERRORS_PAPYAPICONNECTION));
            return dtoRes;
        }

        // APIのエラーコード≠""の場合：エラーコード、エラーメッセージを格納する。
        if (!ObjectUtils.isEmpty(dataList.getTitle())) {
            if (PAPY_WARN_087.equals(dataList.getTitle())) {
                // 印影照会 帳票ファイルが存在しない場合
                ifaImprintInquiryA001ResponseDto.setCode(PAPY_WARN_087);
                ifaImprintInquiryA001ResponseDto
                        .setErrMessage(MessageFormat.format(PAPY_WARN_087_MESSAGE, butenCode, accountNumber));
            } else if (PAPY_WARN_088.equals(dataList.getTitle())) {
                // 印影照会 該当帳票ファイルが存在しない場合
                ifaImprintInquiryA001ResponseDto.setCode(PAPY_WARN_088);
                ifaImprintInquiryA001ResponseDto
                        .setErrMessage(MessageFormat.format(PAPY_WARN_088_MESSAGE, butenCode, accountNumber));
            } else if (PAPY_ERROR_029.equals(dataList.getTitle())) {
                // 印影照会 予想外の異常が発生する場合
                ifaImprintInquiryA001ResponseDto.setCode(PAPY_ERROR_029);
                ifaImprintInquiryA001ResponseDto.setErrMessage(PAPY_ERROR_029_MESSAGE);
            }
        } else {
            ifaImprintInquiryA001ResponseDto.setLedgerCode(dataList.getDataList().get(0).getReportCode());
            ifaImprintInquiryA001ResponseDto.setLedgerName(dataList.getDataList().get(0).getReportName());
            ifaImprintInquiryA001ResponseDto.setAcceptStandardDate(dataList.getDataList().get(0).getAcceptDate());
            ifaImprintInquiryA001ResponseDto.setLedgerFile(dataList.getDataList().get(0).getReportFile());
            ifaImprintInquiryA001ResponseDto.setWidth(dataList.getDataList().get(0).getWidth());
            ifaImprintInquiryA001ResponseDto.setHeight(dataList.getDataList().get(0).getHeight());
        }

        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return dtoRes;
    }

}
