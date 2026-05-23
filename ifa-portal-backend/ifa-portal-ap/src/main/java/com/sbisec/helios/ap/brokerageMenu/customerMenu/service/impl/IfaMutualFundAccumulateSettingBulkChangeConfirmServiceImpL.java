package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Strings;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto.Brand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001ListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingBulkChangeConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingDBProcessService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeReceptRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ChangeReserveSettingInfo;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBaseOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeReceptApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingChangeInfo;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingErrorInfo;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;

/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 *
 * @author nicksen.li
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingBulkChangeConfirmService")
public class IfaMutualFundAccumulateSettingBulkChangeConfirmServiceImpL implements IfaMutualFundAccumulateSettingBulkChangeConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingBulkChangeConfirmServiceImpL.class);

    @Autowired
    private CodeListService codeListService;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct006 fct006;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct017 fct017;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private IfaMutualFundAccumulateSettingDBProcessService ifaMutualFundAccumulateSettingDBProcessService;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 取引停止口座 */
    private static final String STOP_TRADE_ACCOUNT = "1";

    /** 区分値.証券金銭種別_国内投信 */
    private static final String DOMESTIC_MUTUALFUND = "06";

    /** 国内投信:取引種別 "2"：購入（積立） */
    private static final String MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE = "2";

    /** なし */
    private static final String NONE = "0";

    /** 受入要否.不要 */
    private static final String ACCEPTANCE_FALSE = "0";

    /** 投信銘柄種別.通貨選択型 */
    private static final String BRAND_CLASS_SELECT = "1";

    /** 投信銘柄種別.複雑投信*/
    private static final String BRAND_CLASS_COMPLICATED = "2";

    /** "3△":投信 */
    private static final String MUTUALFUND = "3 ";

    /** "1" */
    private static final String ONE = "1";

    /** 国籍コード */
    private static final String NATION_CODE = "99";

    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";

    /** "0":国内 */
    private static final String DOMESTIC = "0";

    /** "1":買付注文 */
    private static final String PURCHASE_ORDER = "1";

    // 判定結果
    private enum JudgementResult {

        // ノーマル
        NORMAL("0"),
        // アラート
        ALERT("1"),
        // エラー
        ERROR("2");

        private String key;

        private JudgementResult(String key) {

            this.key = key;
        }
    }

    // メッセージID
    private enum MessageId {

        // errors.butenAccountNotExist
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTEDACCOUNT_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.fnd.selectedBrands.currencySelectionTypeComplexType
        ERRORS_CURRENCYSELECTIONTYPE("errors.fnd.selectedBrands.currencySelectionTypeComplexType"),
        // errors.fnd.selectedBrands.complexType
        ERRORS_COMPLEXTYPE("errors.fnd.selectedBrands.complexType"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICEERRORCHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // errors.cmn.information.occurs
        ERRORS_INFORMATION_OCCURS("errors.cmn.information.occurs"),
        // errors.cmn.settingExecutionChange.failed
        ERRORS_SETTINGEXECUTIONCHANGE_FAILED("errors.cmn.settingExecutionChange.failed"),
        // errors.cmn.settingExecutionChanges.failed  【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
        ERRORS_SETTINGEXECUTIONCHANGES_FAILED("errors.cmn.settingExecutionChanges.failed"),
        // errors.cmn.settingExecutionChanges.failed#2  エラーコード：{0}<br>エラーメッセージ：{1}
        ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2("errors.cmn.settingExecutionChanges.failed#2"),
        // warnings.fnd.ReserveSettingExecutionChange.completed
        WARNINGS_RESERVESETTINGEXECUTIONCHANGE_COMPLETED("warnings.fnd.ReserveSettingExecutionChange.completed");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    @Override
    public DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto> settingBulkChangeRegisterA001(
            IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBulkChangeConfirmServiceImpL.settingBulkChangeRegisterA001");
        }

        DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto>();
        IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto a001Res = new IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto();

        List<IfaMutualFundAccumulateSettingBulkChangeInputData> bulkChangeConfirmList = dtoReq.getBulkChangeInputDataList();
        Map<String, IfaMutualFundAccumulateSettingBulkChangeInputData> inputDataMap = new HashMap<>();

        for(IfaMutualFundAccumulateSettingBulkChangeInputData inputData : bulkChangeConfirmList) {
            inputData.setBrandCode(inputData.getMfkaisu() + "." + inputData.getMfgo());
            String itemKey = inputData.getAccountType() + "#" + inputData.getMfkaisu() + "#" + inputData.getMfgo();
            inputDataMap.put(itemKey, inputData);
        }

        Map<String, String> fundCode2BrandCodeMap = new HashMap<>();
        Map<String, String> nriCode2BrandCodeMap = new HashMap<>();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        // 口座番号: 部店コード + "-" + 口座番号
        a001Res.setAccountNumber(cc.getButenCode() + "-" + org.apache.commons.lang3.StringUtils.leftPad(Optional.ofNullable(cc.getAccountNumber()).orElse(""), 7, "0"));
        // 個人・法人アイコン
        a001Res.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        a001Res.setCustomerName(cc.getCustomerNameKanji() + "(" + cc.getCustomerNameKana() + ")");

        /**
        ①   利用者の口座に対する権限チェックを行う。
            対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
            取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
            上記以外：次の処理へ
         */
        // ①利用者の口座に対する権限チェックを行う。
        // 以下を入力値としてFCT001を実行
        InputFct001Dto fct001Req = new InputFct001Dto();
        // ・顧客共通情報.部店コード
        fct001Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct001Req.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // if(FCT001.対象顧客参照権限有無＝"0"（権限なし）の場合)｛
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // 権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        // if(FCT001.取引停止フラグ＝"1"（取引停止口座））｛
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOP_TRADE_ACCOUNT)) {
            // 取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            return dtoRes;
        }

        /**
        ②   取引コース媒介可否チェックを行う。
            取引可：次の処理へ。
            上記以外：取引不可エラーを返す。
         */
        // ②取引コース媒介可否チェックを行う。
        // 以下を入力値としてFCT003を実行
        InputFct003Dto fct003Req = new InputFct003Dto();
        // ・顧客共通情報.部店コード
        fct003Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct003Req.setProductCd(DOMESTIC_MUTUALFUND);
        fct003Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        /**
         * ③ 通貨選択型投信 または 複雑型投信チェックを行う。 銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：強制注文対象エラーを返す。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：強制注文対象エラーを返す。
         */
        // ⑥買いの場合、通貨選択型投信 または 複雑型投信チェックを行う（FCT017）
        // 以下を入力値としてFCT017を実行
        InputFct017Dto fct017Req = new InputFct017Dto();
        // ・顧客共通情報.部店コード
        fct017Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct017Req.setAccountNumber(cc.getAccountNumber());
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
        InquiryMutualFundBrand inquiryMutualFundBrand = null;

        for(IfaMutualFundAccumulateSettingBulkChangeInputData changeDataInfo : bulkChangeConfirmList) {
            String nriCode = changeDataInfo.getMfkaisu() + changeDataInfo.getMfgo();

            // API003レスポンスの積立設定リスト　「銘柄コード」　を利用必要です。
            fundCode2BrandCodeMap.put(changeDataInfo.getFundCode(), changeDataInfo.getBrandCode());

            nriCode2BrandCodeMap.put(nriCode, changeDataInfo.getBrandCode());

            // FCT017を利用必要です。
            inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(nriCode);
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        }
        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);

        // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {
            Map<String, String> brandClassSelectMap = new HashMap<>();
            Map<String, String> brandClassComplicatedMap = new HashMap<>();

            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT) && brand.getNriCd() != null) {
                    brandClassSelectMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED) && brand.getNriCd() != null) {
                    brandClassComplicatedMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
            }

            if (!brandClassSelectMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassSelectMap, "VAL", "ASC") }));
                return dtoRes;
            }

            if (!brandClassComplicatedMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_COMPLEXTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassComplicatedMap, "VAL", "ASC") }));
                return dtoRes;
            }
        }

        /**
         * ④   口座の取引制限チェックを行う。
         * 注意情報エラー有無="1"（あり）：エラーを返す。
         * お知らせエラー有無="1"（あり）：エラーを返す。
         * 注意情報アラート有無="1"（あり）：
         * 注意情報アラート確認チェックボックス=ON：次の処理へ
         * 注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
         * お知らせアラート有無="1"（あり）：
         * お知らせアラート確認チェックボックス=ON：次の処理へ
         * お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。
         * 上記チェックを実施したら、次の処理へ。
         */
        // ⑥口座の取引制限チェックを行う（FCT021）
        // 以下を入力値としてFCT021を実行
        InputFct021Dto fct021Req = new InputFct021Dto();
        // ・顧客共通情報.部店コード
        fct021Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct021Req.setProductCd(DOMESTIC_MUTUALFUND);
        // ・取引種別
        fct021Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        // ・国籍コード
        fct021Req.setCountryCd(NATION_CODE);
        // ・通貨コード
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), ONE)) {
            String codeName = codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICEERRORCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICEERRORCHECK.key, new String[] { codeName }));
            return dtoRes;
        }
        // お知らせエラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), ONE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATIONCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATIONCHECK.key));
            return dtoRes;
        }

        //注意情報アラート有無="1"（あり）：
        //注意情報アラート確認チェックボックス=ON：次の処理へ
        //注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), "1")) {
            if (StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), "0")
                    || StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), "")) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
            
        }
        //お知らせアラート有無="1"（あり）：
        //お知らせアラート確認チェックボックス=ON：次の処理へ
        //お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。   
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), "1")) {
            if (StringUtils.equals(dtoReq.getNoticeAlertConfirm(), "0")
                    || StringUtils.equals(dtoReq.getNoticeAlertConfirm(), "")) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
        }
        //上記チェックを実施したら、次の処理へ。

        Map<String, List<String>> fct006ErrorMessageIdMap = new HashMap<>();
        Map<String, IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck> complianceRankCheckMap = new HashMap<>();

        /**
         * ⑤ コンプラランクチェックを行う。
         * FCT006.判定結果=0：ノーマル：次の処理へ
         * 　　FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
         * 　　FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
         * FCT006.判定結果=1：アラート：
         * 　　FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
         * 　　FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
         * 　　FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
         * 　　FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
         * FCT006.判定結果=2：エラー：エラーを返す。
         * FCT006.判定結果=上記以外：エラーを返す。
         */
        InputFct006Dto fct006Req = new InputFct006Dto();
        // 部店コード
        fct006Req.setButenCode(cc.getButenCode());
        // 口座番号
        fct006Req.setAccountNumber(cc.getAccountNumber());
        // 国内外国区分
        fct006Req.setBrDomesticFgnInd(DOMESTIC);
        // 商品区分
        fct006Req.setBrBrandInd(MUTUALFUND);
        //　勧誘区分
        fct006Req.setInvitationType(dtoReq.getKanyuKbn());
        //　受注方法
        fct006Req.setOrderMethod(dtoReq.getReceiveMethod());
        //　コンプラチェック種類
        fct006Req.setComplaCheckKind(PURCHASE_ORDER);

        for(IfaMutualFundAccumulateSettingBulkChangeInputData changeDataInfo : bulkChangeConfirmList) {
            // 銘柄コード１
            fct006Req.setBrandCode1(changeDataInfo.getMfkaisu());
            //　銘柄コード２
            fct006Req.setBrandCode2(changeDataInfo.getMfgo());

            OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);

            IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck = new IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck();

            //FCT006.判定結果=0：ノーマル：次の処理へ
            if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
                if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                    if (StringUtils.equals(changeDataInfo.getComplianceRankCheckStartBaseConfirm(), "0")
                            || StringUtils.equals(changeDataInfo.getComplianceRankCheckStartBaseConfirm(), "")) {
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                MessageId.ERRORS_INFORMATION_OCCURS.key, IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                        return dtoRes;
                    }
                    // 開始基準確認メッセージ
                    complianceRankCheck.setStartCriteriaConfirmMsg(
                            IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
                }
                //FCT006.判定結果=1：アラート：
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
                //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
                //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
                if (StringUtils.equals(changeDataInfo.getComplianceRankCheckConfirm(), "0")
                        || StringUtils.equals(changeDataInfo.getComplianceRankCheckConfirm(), "")) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_OCCURS.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                    return dtoRes;
                }

                // メッセージ
                complianceRankCheck.setMessage(IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                // チェックボックス文言
                complianceRankCheck.setInvitationCheck(fct006Res.getChkBoxLabel());

                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
                if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                    if (StringUtils.equals(changeDataInfo.getComplianceRankCheckStartBaseConfirm(), "0")
                            || StringUtils.equals(changeDataInfo.getComplianceRankCheckStartBaseConfirm(), "")) {
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                MessageId.ERRORS_INFORMATION_OCCURS.key, IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                        return dtoRes;
                    }

                    // 開始基準確認メッセージ
                    complianceRankCheck.setStartCriteriaConfirmMsg(
                            IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
                }

                //FCT006.判定結果=2：エラー：エラーを返す。
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {

                String messageId = fct006Res.getMessageId();

                if (!fct006ErrorMessageIdMap.containsKey(messageId)) {
                    List<String> fct006ErrorBrandCodeList = new ArrayList<>();
                    fct006ErrorBrandCodeList.add(changeDataInfo.getBrandCode());
                    fct006ErrorMessageIdMap.put(messageId, fct006ErrorBrandCodeList);
                } else {
                    List<String> fct006ErrorBrandCodeList = fct006ErrorMessageIdMap.get(messageId);
                    fct006ErrorBrandCodeList.add(changeDataInfo.getBrandCode());
                }

                //FCT006.判定結果=上記以外：エラーを返す。
            } else {

                String messageId = fct006Res.getMessageId();

                if (!fct006ErrorMessageIdMap.containsKey(messageId)) {
                    List<String> fct006ErrorBrandCodeList = new ArrayList<>();
                    fct006ErrorBrandCodeList.add(changeDataInfo.getBrandCode());
                    fct006ErrorMessageIdMap.put(messageId, fct006ErrorBrandCodeList);
                } else {
                    List<String> fct006ErrorBrandCodeList = fct006ErrorMessageIdMap.get(messageId);
                    fct006ErrorBrandCodeList.add(changeDataInfo.getBrandCode());
                }
            }

            if(complianceRankCheck.getInvitationCheck() != null || complianceRankCheck.getMessage() != null || complianceRankCheck.getStartCriteriaConfirmMsg() != null) {
                String itemKey = changeDataInfo.getAccountType() + "#" + changeDataInfo.getMfkaisu() + "#" + changeDataInfo.getMfgo();
                complianceRankCheckMap.put(itemKey, complianceRankCheck);
            }
        }

        if (!fct006ErrorMessageIdMap.isEmpty()) {
            String fct006ErrorMessages = null;

            for (String messageIdKey : fct006ErrorMessageIdMap.keySet()) {

                if (fct006ErrorMessages != null) {
                    fct006ErrorMessages += "<sep>";
                } else {
                    fct006ErrorMessages = "";
                }

                fct006ErrorMessages += IfaCommonUtil.getMessage(messageIdKey);
                List<String> itemBrands = fct006ErrorMessageIdMap.get(messageIdKey);
                String brandsStr = "";

                if (itemBrands != null) {
                    List<String> messageItems = itemBrands.stream().filter(e -> e != null).distinct().sorted()
                            .collect(Collectors.toList());

                    if (messageItems != null && messageItems.size() > 0) {
                        for (String item : messageItems) {
                            brandsStr += (item + "、");
                        }
                    }

                    if (!"".equals(brandsStr)) {
                        brandsStr = brandsStr.substring(0, brandsStr.length() - 1);
                    }

                    fct006ErrorMessages += ("<br>銘柄コード： " + brandsStr);
                }
            }

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, null, fct006ErrorMessages);
            return dtoRes;
        }

        PostFundReserveSettingBulkChangeReceptReq api001Req = new PostFundReserveSettingBulkChangeReceptReq();
        api001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundReserveSettingBulkChangeReceptApiIn api001ApiIn = new FundReserveSettingBulkChangeReceptApiIn();
        // ユーザ共通情報.CCSログイン用ID
        // オペレーターID
        api001ApiIn.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // 受付経路区分
        // ”2"　(BRANCH_OFFICE) ：支店
        api001ApiIn.setRouteType("BRANCH_OFFICE");

        List<ChangeReserveSettingInfo> reserveOrderList = new ArrayList<>();

        ChangeReserveSettingInfo apiInputData = null;
        for(IfaMutualFundAccumulateSettingBulkChangeInputData inputData : bulkChangeConfirmList ) {
            apiInputData = new ChangeReserveSettingInfo();
            // 協会コード
            apiInputData.setFundCode(inputData.getFundCode());
            // 決済方法
            apiInputData.setPaymentMethod("CASH");
            // 預り区分
            apiInputData.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(inputData.getAccountType()));
            // 設定金額
            BigDecimal settingAmount = new BigDecimal(inputData.getSettingAmount().trim());
            apiInputData.setSettingAmount(settingAmount);
            // コース区分
            /**
             * 1 毎日(EVERY_DAY) 
             * 2 毎週(EVERY_WEEK) 
             * 3 毎月(EVERY_MONTH) 
             * 4 複数日(MULTIPLE_DAYS)
             * 5 奇数月(BIMONTHLY)
             * 6 偶数月(BIMONTHLY)
             */
            apiInputData.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getSafeEnumByIfaValue(inputData.getCourseType()));
            // 積立日付
            apiInputData.setSettingReserveDay(inputData.getSettingReserveDay());
            // 積立隔月設定
            /**
             * 1: 奇数月
             * 2: 偶数月
             */
            if ("5".equals(inputData.getCourseType())) {
                apiInputData.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("1"));
            } else if ("6".equals(inputData.getCourseType())) {
                apiInputData.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("2"));
            } else {
                apiInputData.setSettingReserveBimonthly(null);
            }
            // 積立毎週設定
            /**
             * 1: MONDAY
             * 2: TUESDAY
             * 3: WEDNESDAY
             * 4: THURSDAY
             * 5: FRIDAY
             */
            apiInputData.setSettingReserveWeek(SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getSafeEnumByIfaValue(inputData.getSettingReserveWeek()));
            // 積立複数日設定
            if(inputData.getSettingReserveMultiDay() != null && inputData.getSettingReserveMultiDay().length() > 0) {
                apiInputData.setSettingReserveMultiDay(inputData.getSettingReserveMultiDay());
            } else {
                apiInputData.setSettingReserveMultiDay(null);
            }
            reserveOrderList.add(apiInputData);
        }

        api001ApiIn.setReserveOrderList(reserveOrderList);
        api001Req.setParameter(api001ApiIn);

        PostFundReserveSettingBulkChangeReceptRes api001Res = null;
        try {
            api001Res = safeFundTradeService.reserveBulkChangeRecept(api001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e, MessageId.ERRORS_SETTINGEXECUTIONCHANGE_FAILED.key);
        }

        FundReserveSettingBulkChangeReceptApiOut api001Out= api001Res.getFundReserveSettingBulkChangeReceptApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api001Out);

        List<IfaMutualFundAccumulateSettingBulkChangeConfirmData> bulkChangeList = new ArrayList<>();
        IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel sql001Req = new IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel();

        List<ReserveSettingErrorInfo> errorInfos = api001Out.getErrorInfos();

        if(errorInfos == null || errorInfos.isEmpty()) {
            List<ReserveSettingChangeInfo> reserveSettingList= api001Out.getReserveOrderList();

            if(!ObjectUtils.isEmpty(reserveSettingList)) {

                IfaMutualFundAccumulateSettingBulkChangeConfirmData bulkChangeConfirmData = null;
                List<IfaMutualFundAccumulateSettingBulkChangeConfirmSql001ListDetail> sql001ReqInsertDataList = new ArrayList<>();
                IfaMutualFundAccumulateSettingBulkChangeConfirmSql001ListDetail sql001ReqInsertData = null;

                for (ReserveSettingChangeInfo reserveSetting : reserveSettingList) {
                    bulkChangeConfirmData = new IfaMutualFundAccumulateSettingBulkChangeConfirmData();
                    IfaMutualFundAccumulateSettingBulkChangeData before = new IfaMutualFundAccumulateSettingBulkChangeData();
                    FundReserveSettingBaseOut apiBefore = reserveSetting.getReserveSettingChangeBefore();

                    if (apiBefore != null) {
                        // 銘柄名
                        /** 投資信託協会名（投資信託名） */
                        before.setFundName(apiBefore.getFundName());
                        // 協会コード
                        /** 投資信託協会コード */
                        before.setFundCode(apiBefore.getFundCode());
                        // 銘柄コード
                        before.setBrandCode(fundCode2BrandCodeMap.get(apiBefore.getFundCode()));
                        String brandCodeStr = before.getBrandCode();
                        int idx = brandCodeStr.indexOf(".");

                        if (idx >= 0) {
                            before.setMfkaisu(brandCodeStr.substring(0, idx));
                            before.setMfgo(brandCodeStr.substring(idx + 1));
                        }
                        /** 預り区分 */
                        String accountType = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(apiBefore.getAccountType());
                        // NISA（成長投資枠） "H"
                        // NISA（つみたて投資枠） "I"
                        // 特定/一般 " "
                        // Jr特定/Jr一般 "5"
                        before.setAccountType(accountType);
                        /** 設定金額 */
                        before.setSettingAmount(Optional.ofNullable(apiBefore.getSettingAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        /** 設定金額概算手数料 */
                        before.setEstimateFundOrder(Optional.ofNullable(apiBefore.getEstimateFundOrder())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 積立コース
                        /** コース区分 */
                        String courseType = apiBefore.getCourseType();

                        if (courseType != null) {
                            // ■積立コース＝毎日の場合
                            // "毎日"
                            if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                                before.setCourseType("1");
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎週の場合
                                before.setCourseType("2");
                                /** 積立毎週設定 */
                                String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                        .getIfaValueBySafeEnum(apiBefore.getSettingReserveWeek());
                                if (ifaSettingReserveWeek != null) {
                                    before.setSettingReserveWeek(ifaSettingReserveWeek);
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎月の場合
                                before.setCourseType("3");
                                /** 積立日付 */
                                before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝隔月の場合
                                if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.ODD_MONTH.getSafeEnum()
                                        .equals(apiBefore.getSettingReserveBimonthly())) {
                                    // 奇数月
                                    before.setCourseType("5");
                                    /** 積立隔月設定 */
                                    before.setSettingReserveBimonthly("1");
                                    /** 積立日付 */
                                    before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                                } else if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.EVEN_MONTH.getSafeEnum()
                                        .equals(apiBefore.getSettingReserveBimonthly())) {
                                    // 偶数月
                                    before.setCourseType("6");
                                    /** 積立隔月設定 */
                                    before.setSettingReserveBimonthly("2");
                                    /** 積立日付 */
                                    before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝複数日の場合
                                before.setCourseType("4");
                                /** 積立複数日設定 */
                                if (apiBefore.getSettingReserveMultiDay() != null) {
                                    before.setSettingReserveMultiDay(apiBefore.getSettingReserveMultiDay());
                                }
                            }
                        }
                        /** 1ヵ月あたりの設定金額（概算） */
                        before.setOneMonthSumAmount(Optional.ofNullable(apiBefore.getOneMonthSumAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 買付予定日
                        if (apiBefore.getPlanDate() != null && apiBefore.getPlanDate().length() == 8) {
                            before.setPlanDate(LocalDate.parse(apiBefore.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                        }
                    }
                    bulkChangeConfirmData.setReserveSettingChangeBefore(before);

                    IfaMutualFundAccumulateSettingBulkChangeData after = new IfaMutualFundAccumulateSettingBulkChangeData();
                    FundReserveSettingBaseOut apiAfter = reserveSetting.getReserveSettingChangeAfter();

                    sql001ReqInsertData = new IfaMutualFundAccumulateSettingBulkChangeConfirmSql001ListDetail();

                    if (apiAfter != null) {
                        // 部店コード 顧客共通情報.部店コード
                        sql001ReqInsertData.setButenCode(cc.getButenCode());
                        // 口座番号 顧客共通情報.口座番号
                        sql001ReqInsertData.setAccountNumber(cc.getAccountNumber());
                        // 銘柄名
                        /** 投資信託協会名（投資信託名） */
                        after.setFundName(apiAfter.getFundName());
                        // 協会コード
                        /** 投資信託協会コード */
                        after.setFundCode(apiAfter.getFundCode());
                        // 銘柄コード
                        after.setBrandCode(fundCode2BrandCodeMap.get(apiAfter.getFundCode()));
                        String brandCodeStr = after.getBrandCode();
                        int idx = brandCodeStr.indexOf(".");

                        if (idx >= 0) {
                            after.setMfkaisu(brandCodeStr.substring(0, idx));
                            after.setMfgo(brandCodeStr.substring(idx + 1));
                            // ファンドコード（回数） リクエスト.ファンドコード（回数）
                            sql001ReqInsertData.setFundCdKaisu(after.getMfkaisu());
                            // ファンドコード（号） リクエスト.ファンドコード（号）
                            sql001ReqInsertData.setFundCdGou(after.getMfgo());
                        }

                        /** 預り区分 */
                        String accountType = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(apiAfter.getAccountType());
                        // NISA（成長投資枠） "H"
                        // NISA（つみたて投資枠） "I"
                        // 特定/一般 " "
                        // Jr特定/Jr一般 "5"
                        after.setAccountType(accountType);
                        // 預り区分 API応答.預り区分
                        sql001ReqInsertData.setAzukariKbn(accountType);
                        /** NISA枠ぎりぎり注文 */
                        // NISA枠ぎりぎり買付区分 API応答.NISA枠ぎりぎり注文
                        sql001ReqInsertData.setNisaBarelyBuyingKbn(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                                .getIfaValueBySafeEnum(apiAfter.getNisaBarelyBuyingType()));
                        /** 課税枠シフト注文 */
                        // NISA枠超過時買付区分 API応答.課税枠シフト注文
                        sql001ReqInsertData.setNisaExcessBuyingKbn(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                                .getIfaValueBySafeEnum(apiAfter.getTaxShiftType()));
                        /** 設定金額 */
                        after.setSettingAmount(Optional.ofNullable(apiAfter.getSettingAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 設定金額 API応答.設定金額
                        sql001ReqInsertData.setSettingAmount(after.getSettingAmount());
                        /** 設定金額概算手数料 */
                        after.setEstimateFundOrder(Optional.ofNullable(apiAfter.getEstimateFundOrder())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 積立コース
                        /** コース区分 */
                        String courseType = apiAfter.getCourseType();

                        if (courseType != null) {
                            // ■積立コース＝毎日の場合
                            // "毎日"
                            if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                                after.setCourseType("1");
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎週の場合
                                after.setCourseType("2");
                                /** 積立毎週設定 */
                                String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                        .getIfaValueBySafeEnum(apiAfter.getSettingReserveWeek());

                                if (ifaSettingReserveWeek != null) {
                                    after.setSettingReserveWeek(ifaSettingReserveWeek);
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎月の場合
                                after.setCourseType("3");
                                /** 積立日付 */
                                after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝隔月の場合
                                if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.ODD_MONTH.getSafeEnum()
                                        .equals(apiAfter.getSettingReserveBimonthly())) {
                                    // 奇数月
                                    after.setCourseType("5");
                                    /** 積立隔月設定 */
                                    after.setSettingReserveBimonthly("1");
                                    /** 積立日付 */
                                    after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                                } else if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.EVEN_MONTH.getSafeEnum()
                                        .equals(apiAfter.getSettingReserveBimonthly())) {
                                    // 偶数月
                                    after.setCourseType("6");
                                    /** 積立隔月設定 */
                                    after.setSettingReserveBimonthly("2");
                                    /** 積立日付 */
                                    after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝複数日の場合
                                after.setCourseType("4");
                                /** 積立複数日設定 */
                                if (apiAfter.getSettingReserveMultiDay() != null) {
                                    after.setSettingReserveMultiDay(apiAfter.getSettingReserveMultiDay());
                                }
                            }
                            // コース区分 API応答.コース区分
                            String courseKbn = after.getCourseType();
                            sql001ReqInsertData.setCourseKbn("6".equals(courseKbn) ? "5" : courseKbn);
                            // 積立日付 API応答.積立日付
                            sql001ReqInsertData.setSettingReserveDd(after.getSettingReserveDay());
                            // 積立隔月設定 API応答.積立隔月設定
                            sql001ReqInsertData.setSettingReserveBimonthly(after.getSettingReserveBimonthly());
                            // 積立毎週設定 API応答.積立毎週設定
                            sql001ReqInsertData.setSettingReserveWeekly(after.getSettingReserveWeek());
                            // 積立複数日設定 API応答.積立複数日設定
                            sql001ReqInsertData.setSettingReserveMultiday(after.getSettingReserveMultiDay());
                        }

                        /** 1ヵ月あたりの設定金額（概算） */
                        after.setOneMonthSumAmount(Optional.ofNullable(apiAfter.getOneMonthSumAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 1カ月あたりの積立金額 API応答.1ヵ月あたりの設定金額（概算）
                        sql001ReqInsertData.setOneMonthSumAmount(after.getOneMonthSumAmount());

                        // 買付予定日
                        if (apiAfter.getPlanDate() != null && apiAfter.getPlanDate().length() == 8) {
                            after.setPlanDate(LocalDate.parse(apiAfter.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                            // 買付予定日 API応答.買付予定日
                            sql001ReqInsertData.setPlanDate(apiAfter.getPlanDate());
                        }

                        /** ボーナス設定有無 */
                        if (apiAfter.isSettingBonusFlag()) {
                            sql001ReqInsertData.setSettingBonusFlag("1");
                            /** ボーナス設定金額 */
                            after.setSettingBonusAmount(Optional.ofNullable(apiAfter.getSettingBonusAmount())
                                    .orElse(new BigDecimal(0)).toPlainString());
                            // ボーナス設定金額 API応答.ボーナス設定金額
                            sql001ReqInsertData.setPaymentBonus(after.getSettingBonusAmount());
                            /** ボーナス１設定月 */
                            // ボーナス１設定月 API応答.ボーナス１設定月
                            sql001ReqInsertData.setReserveMmB1(apiAfter.getSettingBonus1Month());
                            /** ボーナス１設定日 */
                            // ボーナス１設定日 API応答.ボーナス１設定日
                            sql001ReqInsertData.setReserveDdB1(apiAfter.getSettingBonus1Day());
                            /** ボーナス２設定月 */
                            // ボーナス２設定月 API応答.ボーナス２設定月
                            sql001ReqInsertData.setReserveMmB2(apiAfter.getSettingBonus2Month());
                            /** ボーナス２設定日 */
                            // ボーナス２設定日 API応答.ボーナス２設定日
                            sql001ReqInsertData.setReserveDdB2(apiAfter.getSettingBonus2Day());
                            // ボーナス１買付予定日 API応答.ボーナス１買付予定日
                            sql001ReqInsertData.setPlanDateBonus1(apiAfter.getBonusPlanDate1());
                            // ボーナス２買付予定日 API応答.ボーナス２買付予定日
                            sql001ReqInsertData.setPlanDateBonus2(apiAfter.getBonusPlanDate2());
                        } else {
                            sql001ReqInsertData.setSettingBonusFlag("0");
                        }

                        // 次回買付日 API応答.次回買付日
                        sql001ReqInsertData.setNextReserveDate(apiAfter.getNextReserveDate());
                        // 勧誘区分 リクエスト.勧誘区分
                        sql001ReqInsertData.setKanyuKbn(dtoReq.getKanyuKbn());
                        // 受付方法 リクエスト.受付方法
                        sql001ReqInsertData.setUketukeHouhou(dtoReq.getReceiveMethod());
                        // オペレータＩＤ ユーザ共通情報.CCSログイン用ID
                        sql001ReqInsertData.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
                        // 仲介業者コード 顧客共通情報.仲介業者コード
                        sql001ReqInsertData.setBrokerCode(cc.getBrokerCode());
                        // 仲介業者営業員コード 顧客共通情報.仲介業者営業員コード
                        sql001ReqInsertData.setIntermediaryEmpCd(cc.getBrokerChargeCode());
                        // 作成者 ユーザ共通情報.ユーザーID
                        sql001ReqInsertData.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
                        // 更新者 ユーザ共通情報.ユーザーID
                        sql001ReqInsertData.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

                        String itemKey = sql001ReqInsertData.getAzukariKbn() + "#" + sql001ReqInsertData.getFundCdKaisu()
                        + "#" + sql001ReqInsertData.getFundCdGou();
                        IfaMutualFundAccumulateSettingBulkChangeInputData inputData= inputDataMap.get(itemKey);

                        if(inputData != null) {
                            /** アラート内容確認.コンプラランクチェック確認. */
                            bulkChangeConfirmData.setComplianceRankCheckConfirm(inputData.getComplianceRankCheckConfirm());
                            /** アラート内容確認.コンプラランクチェック開始基準確認. */
                            bulkChangeConfirmData.setComplianceRankCheckStartBaseConfirm(
                                    inputData.getComplianceRankCheckStartBaseConfirm());
                            /** コンプラランクチェック.チェックボックス文言. */
                            inputData.setComplianceRankCheck(complianceRankCheckMap.get(itemKey));

                            if (inputData.getComplianceRankCheck() != null
                                    && StringUtils.equals(inputData.getComplianceRankCheck().getInvitationCheck(), "1")) {
                                bulkChangeConfirmData.setInvitationCheck("1");
                            } else if (inputData.getComplianceRankCheck() != null
                                    && StringUtils.equals(inputData.getComplianceRankCheck().getInvitationCheck(), "2")) {
                                bulkChangeConfirmData.setInvitationCheck("2");
                            } else {
                                bulkChangeConfirmData.setInvitationCheck("");
                            }
                            // アラート内容確認.コンプラチェックワーニング確認 備考参照
                            // 　■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
                            // 　　アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
                            // 　■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
                            // 　　2
                            // 　■上記以外
                            // 　　0
                            //■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
                            if (StringUtils.equals(bulkChangeConfirmData.getInvitationCheck(), "1")) {
                                //アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
                                if (StringUtils.equals(inputData.getComplianceRankCheckConfirm(), "0")) {
                                    sql001ReqInsertData.setCheckCompWrnAlert("0");
                                } else if (StringUtils.equals(inputData.getComplianceRankCheckConfirm(), "1")) {
                                    sql001ReqInsertData.setCheckCompWrnAlert("1");
                                }
                                //■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
                            } else if (StringUtils.equals(bulkChangeConfirmData.getInvitationCheck(), "2")) {
                                //2
                                sql001ReqInsertData.setCheckCompWrnAlert("2");
                                //■上記以外
                            } else {
                                //0
                                sql001ReqInsertData.setCheckCompWrnAlert("0");
                            }
                        }

                        sql001ReqInsertDataList.add(sql001ReqInsertData);
                    }

                    bulkChangeConfirmData.setReserveSettingChangeAfter(after);

                    bulkChangeConfirmData.setComplianceRankCheck(complianceRankCheckMap
                            .get(after.getAccountType() + "#" + after.getMfkaisu() + "#" + after.getMfgo()));

                    bulkChangeList.add(bulkChangeConfirmData);
                }

                sql001Req.setSql001DetailList(sql001ReqInsertDataList);
            }

            a001Res.setBulkChangeList(bulkChangeList);
        } else {

            int maxErrorCnt = 3;
            String apiAddMessge = "";
            /**
             * ⑥ 設定登録を行う。
             *  積立設定一括変更受付APIの呼出し。
             *    エラー：エラーを返す。
             *    errors.cmn.settingExecutionChange.failed
             *    設定変更処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) {0}:APIエラーコード {1}:APIエラーメッセージ
             *  正常かつエラー情報リストにエラー情報ありの場合：以下のエラーを返す。
             *    エラー情報リスト.ファンド名≠nullの場合
             *    errors.cmn.settingExecutionChanges.failed 
             *    【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
             *      {0}:エラー情報リスト.ファンド名 
             *      {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3 
             *      {2}:APIエラーコード 
             *      {3}:APIエラーメッセージ
             * 
             *    エラー情報リスト.ファンド名=nullの場合 
             *    errors.cmn.settingExecutionChanges.failed#2
             *    エラーコード：{0}<br>エラーメッセージ：{1}
             *      {0}:APIエラーコード 
             *      {1}:APIエラーメッセージ 
             *  ※最大3メッセージまで表示
             *  正常かつエラー情報リストにエラー情報なし：次の処理へ。
             */
            for (ReserveSettingErrorInfo reserveSettingErrorInfo : errorInfos) {
                maxErrorCnt--;
                // ※最大3メッセージまで表示
                if (maxErrorCnt < 0) {
                    break;
                }

                ErrorInfo errorInfo = reserveSettingErrorInfo.getErrorInfo();

                // エラーコードありの場合、
                if (errorInfo != null && !StringUtil.isNullOrEmpty(errorInfo.getCode())) {

                    String fundName = Optional.ofNullable(reserveSettingErrorInfo.getFundName()).orElse("");
                    String accountTypeName = Optional
                            .ofNullable(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE",
                                    SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                                            .getIfaValueBySafeEnum(reserveSettingErrorInfo.getAccountType()),
                                    "3"))
                            .orElse("");

                    IfaSafeErrorMessageSql001ResponseModel safeErrorInfoModel = safeCommonService
                            .getSafeErrorCodeAndMessage(errorInfo.getCode(), errorInfo.getErrorLevel(),
                                    errorInfo.getServiceType());

                    // エラーメッセージを取得できるの場合
                    if (safeErrorInfoModel != null) {

                        // エラー情報リスト.ファンド名≠nullの場合
                        if (!StringUtil.isNullOrEmpty(fundName)) {
                            // 【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
                            // {0}:エラー情報リスト.ファンド名
                            // {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3
                            // {2}:APIエラーコード
                            // {3}:APIエラーメッセージ
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(),
                                                    safeErrorInfoModel.getErrorMessage() })
                                    : String.join("<sep>", apiAddMessge,
                                            IfaCommonUtil
                                                    .getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                                            new String[] { fundName, accountTypeName,
                                                                    errorInfo.getCode(),
                                                                    safeErrorInfoModel.getErrorMessage() }));
                        } else {
                            // エラー情報リスト.ファンド名=nullの場合

                            // エラーコード：{0}<br>エラーメッセージ：{1}
                            // {0}:APIエラーコード
                            // {1}:APIエラーメッセージ
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { errorInfo.getCode(), safeErrorInfoModel.getErrorMessage() })
                                    : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                            MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key, new String[] {
                                                    errorInfo.getCode(), safeErrorInfoModel.getErrorMessage() }));
                        }
                    } else {
                        // エラーメッセージを取得できないの場合

                        // エラー情報リスト.ファンド名≠nullの場合
                        if (!StringUtil.isNullOrEmpty(fundName)) {
                            // 【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
                            // {0}:エラー情報リスト.ファンド名
                            // {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3
                            // {2}:APIエラーコード
                            // {3}:''
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(), "" })
                                    : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                            MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(), "" }));
                        } else {
                            // エラー情報リスト.ファンド名=nullの場合

                            // エラーコード：{0}<br>エラーメッセージ：{1}
                            // {0}:APIエラーコード
                            // {1}:''
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { errorInfo.getCode(), "" })
                                    : String.join("<sep>", apiAddMessge,
                                            IfaCommonUtil.getMessage(
                                                    MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                                    new String[] { errorInfo.getCode(), "" }));
                        }
                    }
                } else if (errorInfo != null && StringUtil.isNullOrEmpty(errorInfo.getCode()) && !StringUtil.isNullOrEmpty(errorInfo.getMessage())) {
                    // エラーコードなしの場合、エラーコード：{0}<br>エラーメッセージ：{1}
                    // {0}:''
                    // {1}:APIエラーメッセージ
                    String apiMessage = errorInfo.getMessage();
                    apiAddMessge = (maxErrorCnt == 2)
                            ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                    new String[] { "", apiMessage })
                            : String.join("<sep>", apiAddMessge,
                                    IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { "", apiMessage }));
                } else {
                    // その他の場合、エラーコード：{0}<br>エラーメッセージ：{1}
                    // {0}:''
                    // {1}:''
                    apiAddMessge = (maxErrorCnt == 2)
                            ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                    new String[] { "", "" })
                            : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                    MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key, new String[] { "", "" }));
                }
            }

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key, apiAddMessge);
            return dtoRes;
        }

        /** 旧ジュニアNISA口座開設有無. */
        a001Res.setOpenedJnisa(dtoReq.getOpenedJnisa());

        /** 勧誘区分. */
        a001Res.setKanyuKbn(dtoReq.getKanyuKbn());

        /** 受付方法. */
        a001Res.setReceiveMethod(dtoReq.getReceiveMethod());

        /** 確認項目.ご注意事項. */
        a001Res.setCheckMadoAki(dtoReq.getCheckMadoAki());

        /** アラート内容確認.注意情報アラート確認. */
        a001Res.setNoticeInfoAlertConfirm(dtoReq.getNoticeInfoAlertConfirm());

        /** アラート内容確認.お知らせアラート確認. */
        a001Res.setNoticeAlertConfirm(dtoReq.getNoticeAlertConfirm());

        /** アラート内容確認.確認書受け入れアラート確認. */
        a001Res.setConfirmDocumentAlertConfirm(dtoReq.getConfirmDocumentAlertConfirm());

        /** 注意情報アラート. */
        a001Res.setNoticeInfoAlert(dtoReq.getNoticeInfoAlert());

        /** お知らせアラート. */
        a001Res.setNoticeAlert(dtoReq.getNoticeAlert());

        /** 確認書受け入れアラート. */
        a001Res.setConfirmDocumentAlert(dtoReq.getConfirmDocumentAlert());

        // レスポンスをコントローラーに返却する。
        resDtoList.add(a001Res);

        try {
            ifaMutualFundAccumulateSettingDBProcessService.bulkChangeConfirmSql001(sql001Req);
        } catch (Exception e) {
            // レスポンスにエラーを設定する
            String sqlWarningMsg =  IfaCommonUtil.getMessage(MessageId.WARNINGS_RESERVESETTINGEXECUTIONCHANGE_COMPLETED.key);
            if(dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
                dtoRes.setMessage(dtoRes.getMessage() + "<sep>" + sqlWarningMsg);
            } else {
                dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
                dtoRes.setMessage(sqlWarningMsg);
            }
        }

        /**
         * ⑧ 投信積立設定一括変更完了画面を表示する。
         */
        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

}
