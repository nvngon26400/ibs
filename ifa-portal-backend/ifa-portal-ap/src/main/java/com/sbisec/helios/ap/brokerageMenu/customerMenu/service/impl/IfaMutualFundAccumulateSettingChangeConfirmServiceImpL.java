package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.google.common.base.Strings;
import com.sbibits.earth.model.DataList;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingChangeConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingChangeConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeReceptRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBaseOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeReceptApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeReceptApiOut;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;

/**
 * 画面ID：SUB0202_0403-03_2
 * 画面名：投信積立設定変更確認
 *
 * @author nicksen.li
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingChangeConfirmService")
public class IfaMutualFundAccumulateSettingChangeConfirmServiceImpL implements IfaMutualFundAccumulateSettingChangeConfirmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingChangeConfirmServiceImpL.class);

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
    private IfaMutualFundAccumulateSettingChangeConfirmDao ifaMutualFundAccumulateSettingChangeConfirmDao;

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
        // errors.fnd.selectedBrand.currencySelectionType
        ERRORS_CURRENCYSELECTIONTYPE("errors.fnd.selectedBrand.currencySelectionType"),
        // errors.fnd.selectedBrand.complexType
        ERRORS_COMPLEXTYPE("errors.fnd.selectedBrand.complexType"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICEERRORCHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // errors.cmn.information.occurs
        ERRORS_INFORMATION_OCCURS("errors.cmn.information.occurs"),
        // errors.cmn.settingExecutionChange.failed
        ERRORS_SETTINGEXECUTIONCHANGE_FAILED("errors.cmn.settingExecutionChange.failed"),
        // warnings.fnd.ReserveSettingExecutionChange.completed
        WARNINGS_RESERVESETTINGEXECUTIONCHANGE_COMPLETED("warnings.fnd.ReserveSettingExecutionChange.completed");

        private String key;

        private MessageId(String key) {
            this.key = key;
        }
    }

    @Override
    public DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto> settingChangeRegisterA002(
            IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingChangeConfirmServiceImpL.settingChangeRegisterA002");
        }

        DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto>();
        List<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto>();
        IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto a001Res = new IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto();

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
        InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
        inquiryMutualFundBrand.setNriCd(dtoReq.getMfkaisu() + dtoReq.getMfgo());
        inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);
        // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {
            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key));
                    return dtoRes;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_COMPLEXTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key));
                    return dtoRes;
                }
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
        // 銘柄コード１
        fct006Req.setBrandCode1(dtoReq.getMfkaisu());
        //　銘柄コード２
        fct006Req.setBrandCode2(dtoReq.getMfgo());
        //　勧誘区分
        fct006Req.setInvitationType(dtoReq.getKanyuKbn());
        //　受注方法
        fct006Req.setOrderMethod(dtoReq.getReceiveMethod());
        //　コンプラチェック種類
        fct006Req.setComplaCheckKind(PURCHASE_ORDER);
        OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);
        //FCT006.判定結果=0：ノーマル：次の処理へ
        if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
            //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
            //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
            if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                if (StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(), "0")
                        || StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(),
                                "")) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_INFORMATION_OCCURS.key, IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                    return dtoRes;
                }
            }
            //FCT006.判定結果=1：アラート：
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
            //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
            //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
            if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), "0")
                    || StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), "")) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
            //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
            //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
            if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                if (StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(), "0")
                        || StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(),
                                "")) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_INFORMATION_OCCURS.key, IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                    return dtoRes;
                }
            }
            //FCT006.判定結果=2：エラー：エラーを返す。
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getMessageId(),
                    IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            return dtoRes;
            
            //FCT006.判定結果=上記以外：エラーを返す。
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, "", fct006Res.getMessageId());
            return dtoRes;
        }

        /**
         * ⑥ 設定変更受付を行う。 
         * 積立設定変更受付APIの呼出し 
         * 正常：次の処理へ。 
         * エラー：エラーを返す。
         */
        PostFundReserveSettingChangeReceptReq api001Req = new PostFundReserveSettingChangeReceptReq();
        // token トークン
        api001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));

        FundReserveSettingChangeReceptApiIn api001In = new FundReserveSettingChangeReceptApiIn();
        // fundCode 協会コード
        api001In.setFundCode(dtoReq.getFundCode());
        // paymentMethod 決済方法
        api001In.setPrePaymentMethod("CASH");
        api001In.setPaymentMethod("CASH");

        // accountType 預り区分
        /** [ ]非NISA注文 */
        /** [H]NISA（成長投資枠） */
        /** [I]NISA（つみたて投資枠）  */
        /** [5]ジュニアNISA口座 特定/一般 */
        api001In.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(dtoReq.getAccountType()));


        // settingAmount 設定金額
        if (dtoReq.getSettingAmount() != null && dtoReq.getSettingAmount().trim().length() > 0) {
            BigDecimal settingAmount = new BigDecimal(dtoReq.getSettingAmount().trim());
            api001In.setSettingAmount(settingAmount);
        }

        // courseType コース区分
        /**
         * 1 毎日(EVERY_DAY) 
         * 2 毎週(EVERY_WEEK) 
         * 3 毎月(EVERY_MONTH) 
         * 4 複数日(MULTIPLE_DAYS)
         * 5 奇数月(BIMONTHLY)
         * 6 偶数月(BIMONTHLY)
         */
        api001In.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getSafeEnumByIfaValue(dtoReq.getCourseType()));

        // settingReserveDay 積立日付
        api001In.setSettingReserveDay(dtoReq.getSettingReserveDay());

        // settingReserveBimonthly 積立隔月設定
        /**
         * 1: 奇数月 
         * 2: 偶数月
         */
        if ("5".equals(dtoReq.getCourseType())) {
            api001In.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                    .getSafeEnumByIfaValue("1"));
        } else if ("6".equals(dtoReq.getCourseType())) {
            api001In.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                    .getSafeEnumByIfaValue("2"));
        } else {
            api001In.setSettingReserveBimonthly(null);
        }

        // settingReserveWeek 積立毎週設定
        /**
         * 1: MONDAY
         * 2: TUESDAY
         * 3: WEDNESDAY
         * 4: THURSDAY
         * 5: FRIDAY
         */
        api001In.setSettingReserveWeek(
                SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getSafeEnumByIfaValue(dtoReq.getSettingReserveWeek()));

        // settingReserveMultiDay 積立複数日設定
        if (dtoReq.getSettingReserveMultiDay() != null && dtoReq.getSettingReserveMultiDay().length() > 0) {
            api001In.setSettingReserveMultiDay(dtoReq.getSettingReserveMultiDay());
        } else {
            api001In.setSettingReserveMultiDay(null);
        }

        // nisaBarelyBuyingType NISA枠ぎりぎり注文
        /**
         * ■NISAぎりぎり注文チェック＝チェックありの場合 ”2” 
         * ■NISAぎりぎり注文チェック＝チェックなしの場合 ”1” 
         * ■上記以外 "0”
         */
        String ifaNisaBarelyBuyingTypeCode = null;
        if ("1".equals(dtoReq.getNisaBarelyBuyingTypeShow())) {
            ifaNisaBarelyBuyingTypeCode = dtoReq.getNisaBarelyBuyingType();
        } else {
            ifaNisaBarelyBuyingTypeCode = "0";
        }
        api001In.setNisaBarelyBuyingType(
                SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum.getSafeEnumByIfaValue(ifaNisaBarelyBuyingTypeCode));

        // taxShiftType 課税枠シフト注文
        /**
         * ■課税枠シフト注文チェック＝チェックありの場合 ”2” 
         * ■課税枠シフト注文文チェック＝チェックなしの場合 ”1” 
         * ■上記以外 "0”
         */
        String ifaTaxShiftTypeCode = null;
        if ("1".equals(dtoReq.getTaxShiftTypeShow())) {
            ifaTaxShiftTypeCode = dtoReq.getTaxShiftType();
        } else {
            ifaTaxShiftTypeCode = "0";
        }
        api001In.setTaxShiftType(
                SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum.getSafeEnumByIfaValue(ifaTaxShiftTypeCode));

        // settingBonusFlag ボーナス設定有無
        if ("1".equals(dtoReq.getSettingBonusFlag())) {
            // ■ボーナス設定＝するの場合
            // "true"
            api001In.setSettingBonusFlag(true);

            // settingBonusAmount ボーナス設定金額
            if (dtoReq.getSettingBonusAmount() != null && dtoReq.getSettingBonusAmount().trim().length() > 0) {
                BigDecimal settingBonusAmount = new BigDecimal(dtoReq.getSettingBonusAmount().trim());
                api001In.setSettingBonusAmount(settingBonusAmount);
            }

            // settingBonus1Month ボーナス１設定月
            if (dtoReq.getSettingBonus1Month() != null && dtoReq.getSettingBonus1Month().length() > 0) {
                api001In.setSettingBonus1Month(dtoReq.getSettingBonus1Month());
            } else {
                api001In.setSettingBonus1Month(null);
            }
            // settingBonus1Day ボーナス１設定日
            if (dtoReq.getSettingBonus1Day() != null && dtoReq.getSettingBonus1Day().length() > 0) {
                api001In.setSettingBonus1Day(dtoReq.getSettingBonus1Day());
            } else {
                api001In.setSettingBonus1Day(null);
            }

            // settingBonus2Month ボーナス２設定月
            if (dtoReq.getSettingBonus2Month() != null && dtoReq.getSettingBonus2Month().length() > 0) {
                api001In.setSettingBonus2Month(dtoReq.getSettingBonus2Month());
            } else {
                api001In.setSettingBonus2Month(null);
            }
            // settingBonus2Day ボーナス２設定日
            if (dtoReq.getSettingBonus2Day() != null && dtoReq.getSettingBonus2Day().length() > 0) {
                api001In.setSettingBonus2Day(dtoReq.getSettingBonus2Day());
            } else {
                api001In.setSettingBonus2Day(null);
            }
        } else {
            // ■ボーナス設定＝しないの場合
            // ”false”
            api001In.setSettingBonusFlag(false);
        }

        // routeType 受付経路区分
        // ”2" (BRANCH_OFFICE) ：支店
        api001In.setRouteType("BRANCH_OFFICE");

        // operatorId オペレーターID
        // ユーザ共通情報.CCSログイン用ID
        // オペレーターID
        api001In.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        api001Req.setParameter(api001In);
        PostFundReserveSettingChangeReceptRes api001Res = null;
        try {
            api001Res = safeFundTradeService.reserveChangeRecept(api001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e, MessageId.ERRORS_SETTINGEXECUTIONCHANGE_FAILED.key);
        }

        FundReserveSettingChangeReceptApiOut api001Out = api001Res.getFundReserveSettingChangeReceptApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api001Out);

        if(api001Out.getCreditCardCompany() != null) {
            // クレジットカード会社
            a001Res.setCreditCardCompany(api001Out.getCreditCardCompany().toString());
        }


        IfaMutualFundAccumulateSettingChangeData before = new IfaMutualFundAccumulateSettingChangeData();
        FundReserveSettingBaseOut apiBefore = api001Out.getReserveSettingChangeBefore();

        if (apiBefore != null) {
            /** 投資信託協会コード */
            before.setFundCode(apiBefore.getFundCode());
            /** 投資信託協会名（投資信託名） */
            before.setFundName(apiBefore.getFundName());
            /** 決済方法 */
            before.setPaymentMethod(
                    SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(apiBefore.getPaymentMethod()));

            /** 預り区分 */
            String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                    .getIfaValueBySafeEnum(apiBefore.getAccountType());
            before.setAccountType(ifaAccountTypeCode);

            /** 設定金額 */
            before.setSettingAmount(Optional.ofNullable(apiBefore.getSettingAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 1ヵ月あたりの設定金額（概算） */
            before.setOneMonthSumAmount(Optional.ofNullable(apiBefore.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** コース区分 */
            /**
             * EVERY_DAY 1
             * EVERY_WEEK 2
             * EVERY_MONTH 3
             * MULTIPLE_DAYS 4
             * BIMONTHLY && ODD_MONTH 5
             * BIMONTHLY && EVEN_MONTH 6
             */
            before.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(apiBefore.getCourseType(),
                    apiBefore.getSettingReserveBimonthly()));

            // 積立日付 API001
            before.setSettingReserveDay(apiBefore.getSettingReserveDay());

            // 積立隔月設定 API001
            String ifaSettingReserveBimonthly = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                    .getIfaValueBySafeEnum(apiBefore.getSettingReserveBimonthly());
            if (ifaSettingReserveBimonthly != null) {
                //奇数月 1
                //偶数月 2
                before.setSettingReserveBimonthly(ifaSettingReserveBimonthly);
            }

            // 積立毎週設定 API001
            /**
             * MONDAY 1 
             * TUESDAY 2 
             * WEDNESDAY 3 
             * THURSDAY 4 
             * FRIDAY 5
             */
            String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                    .getIfaValueBySafeEnum(apiBefore.getSettingReserveWeek());
            if (ifaSettingReserveWeek != null) {
                before.setSettingReserveWeek(ifaSettingReserveWeek);
            }

            // 積立複数日設定 API001
            if (apiBefore.getSettingReserveMultiDay() != null && apiBefore.getSettingReserveMultiDay().length() > 0) {
                before.setSettingReserveMultiDay(apiBefore.getSettingReserveMultiDay());
            } else {
                before.setSettingReserveMultiDay(null);
            }

            /** NISA枠ぎりぎり注文 */
            before.setNisaBarelyBuyingType(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiBefore.getNisaBarelyBuyingType()));

            /** 課税枠シフト注文 */
            before.setTaxShiftType(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiBefore.getTaxShiftType()));

            /** ボーナス設定有無 */
            if (apiBefore.isSettingBonusFlag()) {
                before.setSettingBonusFlag("1");

                /** ボーナス設定金額 */
                before.setSettingBonusAmount(Optional.ofNullable(apiBefore.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                /** ボーナス１設定月 */
                before.setSettingBonus1Month(apiBefore.getSettingBonus1Month());

                /** ボーナス１設定日 */
                before.setSettingBonus1Day(apiBefore.getSettingBonus1Day());

                /** ボーナス２設定月 */
                before.setSettingBonus2Month(apiBefore.getSettingBonus2Month());

                /** ボーナス２設定日 */
                before.setSettingBonus2Day(apiBefore.getSettingBonus2Day());
            } else {
                before.setSettingBonusFlag("2");
            }

            /** 次の買付予定日 */
            if (apiBefore.getPlanDate() != null && apiBefore.getPlanDate().length() == 8) {
                before.setPlanDate(LocalDate.parse(apiBefore.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日１ */
            if (apiBefore.getBonusPlanDate1() != null && apiBefore.getBonusPlanDate1().length() == 8) {
                before.setBonusPlanDate1(LocalDate.parse(apiBefore.getBonusPlanDate1(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日２ */
            if (apiBefore.getBonusPlanDate2() != null && apiBefore.getBonusPlanDate2().length() == 8) {
                before.setBonusPlanDate1(LocalDate.parse(apiBefore.getBonusPlanDate2(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            // ボーナス買付予定日
            before.setBonusPlanDate(IfaMutualFundAccumulateSettingUtil.getBonusPlanDate(apiBefore.getBonusPlanDate1(), apiBefore.getBonusPlanDate2()));

            /** 設定金額概算手数料 */
            before.setEstimateFundOrder(Optional.ofNullable(apiBefore.getEstimateFundOrder())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** ボーナス概算手数料 */
            before.setEstimateFundOrderBonus(Optional.ofNullable(apiBefore.getEstimateFundOrderBonus())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 設定金額の次回買付日 */
            if (apiBefore.getNextReserveDate() != null && apiBefore.getNextReserveDate().length() == 8) {
                before.setNextReserveDate(LocalDate.parse(apiBefore.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }
        }

        IfaMutualFundAccumulateSettingChangeData after = new IfaMutualFundAccumulateSettingChangeData();
        FundReserveSettingBaseOut apiAfter = api001Out.getReserveSettingChangeAfter();

        if (apiAfter != null) {
            /** 投資信託協会コード */
            after.setFundCode(apiAfter.getFundCode());
            a001Res.setFundCode(apiAfter.getFundCode());
            /** 投資信託協会名（投資信託名） */
            after.setFundName(apiAfter.getFundName());
            
            a001Res.setBrandName(apiAfter.getFundName());
            
            /** 決済方法 */
            after.setPaymentMethod(
                    SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(apiAfter.getPaymentMethod()));

            /** 預り区分 */
            String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                    .getIfaValueBySafeEnum(apiAfter.getAccountType());
            after.setAccountType(ifaAccountTypeCode);
            
            a001Res.setAccountType(ifaAccountTypeCode);
            
            if ("1".equals(dtoReq.getOpenedJnisa())) {
                a001Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "2"));
            } else {
                a001Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "1"));
            }

            /** 設定金額 */
            after.setSettingAmount(Optional.ofNullable(apiAfter.getSettingAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 1ヵ月あたりの設定金額（概算） */
            after.setOneMonthSumAmount(Optional.ofNullable(apiAfter.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** コース区分 */
            /**
             * EVERY_DAY 1
             * EVERY_WEEK 2
             * EVERY_MONTH 3
             * MULTIPLE_DAYS 4
             * BIMONTHLY && ODD_MONTH 5
             * BIMONTHLY && EVEN_MONTH 6
             */
            after.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(apiAfter.getCourseType(),
                    apiAfter.getSettingReserveBimonthly()));

            // 積立日付 API001
            after.setSettingReserveDay(apiAfter.getSettingReserveDay());

            // 積立隔月設定 API001
            String ifaSettingReserveBimonthly = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                    .getIfaValueBySafeEnum(apiAfter.getSettingReserveBimonthly());
            if (ifaSettingReserveBimonthly != null) {
                //奇数月 1
                //偶数月 2
                after.setSettingReserveBimonthly(ifaSettingReserveBimonthly);
            }

            // 積立毎週設定 API001
            /**
             * MONDAY 1 
             * TUESDAY 2 
             * WEDNESDAY 3 
             * THURSDAY 4 
             * FRIDAY 5
             */
            String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                    .getIfaValueBySafeEnum(apiAfter.getSettingReserveWeek());
            if (ifaSettingReserveWeek != null) {
                after.setSettingReserveWeek(ifaSettingReserveWeek);
            }

            // 積立複数日設定 API001
            if (apiAfter.getSettingReserveMultiDay() != null && apiAfter.getSettingReserveMultiDay().length() > 0) {
                after.setSettingReserveMultiDay(apiAfter.getSettingReserveMultiDay());
            } else {
                after.setSettingReserveMultiDay(null);
            }

            /** NISA枠ぎりぎり注文 */
            after.setNisaBarelyBuyingType(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiAfter.getNisaBarelyBuyingType()));

            /** 課税枠シフト注文 */
            after.setTaxShiftType(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiAfter.getTaxShiftType()));

            /** ボーナス設定有無 */
            if (apiAfter.isSettingBonusFlag()) {
                after.setSettingBonusFlag("1");

                /** ボーナス設定金額 */
                after.setSettingBonusAmount(Optional.ofNullable(apiAfter.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                /** ボーナス１設定月 */
                after.setSettingBonus1Month(apiAfter.getSettingBonus1Month());

                /** ボーナス１設定日 */
                after.setSettingBonus1Day(apiAfter.getSettingBonus1Day());

                /** ボーナス２設定月 */
                after.setSettingBonus2Month(apiAfter.getSettingBonus2Month());

                /** ボーナス２設定日 */
                after.setSettingBonus2Day(apiAfter.getSettingBonus2Day());
            } else {
                after.setSettingBonusFlag("2");
            }

            /** 次の買付予定日 */
            if (apiAfter.getPlanDate() != null && apiAfter.getPlanDate().length() == 8) {
                after.setPlanDate(LocalDate.parse(apiAfter.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日１ */
            if (apiAfter.getBonusPlanDate1() != null && apiAfter.getBonusPlanDate1().length() == 8) {
                after.setBonusPlanDate1(LocalDate.parse(apiAfter.getBonusPlanDate1(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日２ */
            if (apiAfter.getBonusPlanDate2() != null && apiAfter.getBonusPlanDate2().length() == 8) {
                after.setBonusPlanDate1(LocalDate.parse(apiAfter.getBonusPlanDate2(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            // ボーナス買付予定日
            after.setBonusPlanDate(IfaMutualFundAccumulateSettingUtil.getBonusPlanDate(apiAfter.getBonusPlanDate1(), apiAfter.getBonusPlanDate2()));

            /** 設定金額概算手数料 */
            after.setEstimateFundOrder(Optional.ofNullable(apiAfter.getEstimateFundOrder())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** ボーナス概算手数料 */
            after.setEstimateFundOrderBonus(Optional.ofNullable(apiAfter.getEstimateFundOrderBonus())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 設定金額の次回買付日 */
            if (apiAfter.getNextReserveDate() != null && apiAfter.getNextReserveDate().length() == 8) {
                after.setNextReserveDate(LocalDate.parse(apiAfter.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }
        }

        a001Res.setReserveSettingChangeBefore(before);
        a001Res.setReserveSettingChangeAfter(after);

        a001Res.setBrandCode(dtoReq.getMfkaisu() + "." + dtoReq.getMfgo());

        /** 旧ジュニアNISA口座開設有無. */
        a001Res.setOpenedJnisa(dtoReq.getOpenedJnisa());

        // 積立買付単位
        a001Res.setReserveOrderUnit(dtoReq.getReserveOrderUnit());

        /** 勧誘区分. */
        a001Res.setKanyuKbn(dtoReq.getKanyuKbn());

        /** 受付方法. */
        a001Res.setReceiveMethod(dtoReq.getReceiveMethod());

        /** 確認項目.ご注意事項. */
        a001Res.setCheckMadoAki(dtoReq.getCheckMadoAki());

        /** アラート内容確認.コンプラランクチェック確認. */
        a001Res.setComplianceRankCheckConfirm(dtoReq.getComplianceRankCheckConfirm());

        /** アラート内容確認.コンプラランクチェック開始基準確認. */
        a001Res.setComplianceRankCheckStartBaseConfirm(dtoReq.getComplianceRankCheckStartBaseConfirm());

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

        /** コンプラランクチェック.チェックボックス文言. */
        if (dtoReq.getComplianceRankCheck() != null && StringUtils.equals(dtoReq.getComplianceRankCheck().getInvitationCheck(), "1")) {
            a001Res.setInvitationCheck("1");
        } else if (dtoReq.getComplianceRankCheck() != null && StringUtils.equals(dtoReq.getComplianceRankCheck().getInvitationCheck(), "2")) {
            a001Res.setInvitationCheck("2");
        } else {
            a001Res.setInvitationCheck("");
        }

        /** 確認書受け入れアラート. */
        a001Res.setConfirmDocumentAlert(dtoReq.getConfirmDocumentAlert());

        // レスポンスをコントローラーに返却する。
        resDtoList.add(a001Res);

        /**
         * ⑦ 設定受付データの格納 
         * DB登録OK：次の処理へ 
         * DB登録NG：DB登録エラーを格納し次の処理へ。
         * insSql001Req
         * 
         * warnings.fnd.ReserveSettingExecutionChange.completed
         * 積立設定変更後の受付データを登録できませんでした。積立設定変更は完了しています。
         * 
         */

        IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel sql001ReqModel = new IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel();

        // 部店コード 顧客共通情報.部店コード
        sql001ReqModel.setButenCode(cc.getButenCode());
        // 口座番号 顧客共通情報.口座番号
        sql001ReqModel.setAccountNumber(cc.getAccountNumber());
        // ファンドコード（回数） リクエスト.ファンドコード（回数）
        sql001ReqModel.setFundCdKaisu(dtoReq.getMfkaisu());
        // ファンドコード（号） リクエスト.ファンドコード（号）
        sql001ReqModel.setFundCdGou(dtoReq.getMfgo());
        // 預り区分 API応答.預り区分
        sql001ReqModel.setAzukariKbn(a001Res.getReserveSettingChangeAfter().getAccountType());
        // 更新区分 '2'
        sql001ReqModel.setModifyKbn("2");
        // 決済方法 '1'
        sql001ReqModel.setPaymentMethod("1");
        // NISA枠ぎりぎり買付区分 API応答.NISA枠ぎりぎり注文
        sql001ReqModel.setNisaBarelyBuyingKbn(a001Res.getReserveSettingChangeAfter().getNisaBarelyBuyingType());
        // NISA枠超過時買付区分 API応答.課税枠シフト注文
        sql001ReqModel.setNisaExcessBuyingKbn(a001Res.getReserveSettingChangeAfter().getTaxShiftType());
        // 設定金額 API応答.設定金額
        sql001ReqModel.setSettingAmount(a001Res.getReserveSettingChangeAfter().getSettingAmount());
        // コース区分 API応答.コース区分
        String courseKbn = a001Res.getReserveSettingChangeAfter().getCourseType();
        sql001ReqModel.setCourseKbn("6".equals(courseKbn) ? "5" : courseKbn);
        // 積立日付 API応答.積立日付
        sql001ReqModel.setSettingReserveDd(a001Res.getReserveSettingChangeAfter().getSettingReserveDay());
        // 積立隔月設定 API応答.積立隔月設定
        sql001ReqModel.setSettingReserveBimonthly(a001Res.getReserveSettingChangeAfter().getSettingReserveBimonthly());
        // 積立毎週設定 API応答.積立毎週設定
        sql001ReqModel.setSettingReserveWeekly(a001Res.getReserveSettingChangeAfter().getSettingReserveWeek());
        // 積立複数日設定 API応答.積立複数日設定
        sql001ReqModel.setSettingReserveMultiday(a001Res.getReserveSettingChangeAfter().getSettingReserveMultiDay());
        // 1カ月あたりの積立金額 API応答.1ヵ月あたりの設定金額（概算）
        //ONE_MONTH_SUM_AMOUNT
        sql001ReqModel.setOneMonthSumAmount(a001Res.getReserveSettingChangeAfter().getOneMonthSumAmount());

        // ボーナス設定有無 API応答.ボーナス設定有無
        if ("1".equals(a001Res.getReserveSettingChangeAfter().getSettingBonusFlag())) {
            sql001ReqModel.setSettingBonusFlag("1");
            // ボーナス設定金額 API応答.ボーナス設定金額
            sql001ReqModel.setPaymentBonus(a001Res.getReserveSettingChangeAfter().getSettingBonusAmount());
            // ボーナス１設定月 API応答.ボーナス１設定月
            sql001ReqModel.setReserveMmB1(a001Res.getReserveSettingChangeAfter().getSettingBonus1Month());
            // ボーナス１設定日 API応答.ボーナス１設定日
            sql001ReqModel.setReserveDdB1(a001Res.getReserveSettingChangeAfter().getSettingBonus1Day());
            // ボーナス２設定月 API応答.ボーナス２設定月
            sql001ReqModel.setReserveMmB2(a001Res.getReserveSettingChangeAfter().getSettingBonus2Month());
            // ボーナス２設定日 API応答.ボーナス２設定日
            sql001ReqModel.setReserveDdB2(a001Res.getReserveSettingChangeAfter().getSettingBonus2Day());
            // ボーナス１買付予定日 API応答.ボーナス１買付予定日
            sql001ReqModel.setPlanDateBonus1(apiAfter.getBonusPlanDate1());
            // ボーナス２買付予定日 API応答.ボーナス２買付予定日
            sql001ReqModel.setPlanDateBonus2(apiAfter.getBonusPlanDate2());
        } else {
            sql001ReqModel.setSettingBonusFlag("0");
        }

        // 買付予定日 API応答.買付予定日
        sql001ReqModel.setPlanDate(apiAfter.getPlanDate());
        // 次回買付日 API応答.次回買付日
        sql001ReqModel.setNextReserveDate(apiAfter.getNextReserveDate());

        // 勧誘区分 リクエスト.勧誘区分
        sql001ReqModel.setKanyuKbn(a001Res.getKanyuKbn());
        // 受付方法 リクエスト.受付方法
        sql001ReqModel.setUketukeHouhou(a001Res.getReceiveMethod());


        // アラート内容確認.コンプラチェックワーニング確認 備考参照
        // 　■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
        // 　　アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
        // 　■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
        // 　　2
        // 　■上記以外
        // 　　0

        //■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
        if (StringUtils.equals(a001Res.getInvitationCheck(), "1")) {
            //アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
            if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), "0")) {
                sql001ReqModel.setCheckCompWrnAlert("0");
            } else if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), "1")) {
                sql001ReqModel.setCheckCompWrnAlert("1");
            }
            //■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
        } else if (StringUtils.equals(a001Res.getInvitationCheck(), "2")) {
            //2
            sql001ReqModel.setCheckCompWrnAlert("2");
            //■上記以外
        } else {
            //0
            sql001ReqModel.setCheckCompWrnAlert("0");
        }

        // 受付経路区分 ’2’
        sql001ReqModel.setReceptionRouteKbn("2");
        // オペレータＩＤ ユーザ共通情報.CCSログイン用ID
        sql001ReqModel.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // 仲介業者コード 顧客共通情報.仲介業者コード
        sql001ReqModel.setBrokerCode(cc.getBrokerCode());
        // 仲介業者営業員コード 顧客共通情報.仲介業者営業員コード
        sql001ReqModel.setBrokerChargeCode(cc.getBrokerChargeCode());
        // 削除区分 '2'
        sql001ReqModel.setDeleteKbn("0");
        // 作成者 ユーザ共通情報.ユーザーID
        sql001ReqModel.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        // 更新者 ユーザ共通情報.ユーザーID
        sql001ReqModel.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

        try {
            ifaMutualFundAccumulateSettingChangeConfirmDao.insertIfaMutualFundAccumulateSettingChangeConfirmSql001(sql001ReqModel);
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
         * ⑧ 投信積立設定完了画面を表示する。
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
