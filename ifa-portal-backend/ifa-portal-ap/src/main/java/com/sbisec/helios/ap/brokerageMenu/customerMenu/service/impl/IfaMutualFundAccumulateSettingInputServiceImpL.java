package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001Response_AccountType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingInputService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingInputConfirmRes;
import com.sbisec.helios.ap.safe.service.SafeAccountService;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundProductService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiIn;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiOut;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundBasicInfoApiIn;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundBasicInfoApiOut;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiIn;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingInputConfirmApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingInputConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSumAmount;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiOut;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;

/**
 * 画面ID：SUB0202_0403-02_1
 * 画面名：投信積立設定入力
 *
 * @author nicksen.li
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingInputService")
public class IfaMutualFundAccumulateSettingInputServiceImpL implements IfaMutualFundAccumulateSettingInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingInputServiceImpL.class);

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct006 fct006;

    @Autowired
    private Fct017 fct017;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private SafeAccountService safeAccountService;

    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeFundProductService safeFundProductService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private MCodeService mcodeService;

    @Autowired
    private CodeListService codeListService;

    @Autowired
    private IfaMutualFundAccumulateSettingInputDao ifaMutualFundAccumulateSettingInputDao;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";
    
    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

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

    /** "1" */
    private static final String ONE = "1";

    /** 国籍コード */
    private static final String NATION_CODE = "99";

    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";

    /** "0":国内 */
    private static final String DOMESTIC = "0";

    /** "3△":投信 */
    private static final String MUTUALFUND = "3 ";

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
        // errors.fnd.selectedBrand.orderStopped
        ERRORS_ORDERSTOPPED("errors.fnd.selectedBrand.orderStopped"),
        //errors.fndReserve.depositnotExit
        ERRORS_FNDRESERVE_DEPOSITNOTEXIT("errors.fndReserve.depositnotExit"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICEERRORCHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // warnings.cmn.noticeWarningCheck
        WRANINGS_NOTICEWARNINGCHECK("warnings.cmn.noticeWarningCheck"),
        // warnings.cmm.informationCheck
        WARNINGS_INFORMATIONCHECK("warnings.cmm.informationCheck"),
        // warnings.fndReserve.confirmationCheck
        WARNINGS_FNDRESERVE_CONFIRMATIONCHECK("warnings.fndReserve.confirmationCheck"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CCSIDUNREGISTERED("errors.cmn.ccsid.unregistered");

        private String key;

        private MessageId(String key) {

            this.key = key;
        }
    }

    @Override
    public DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingInputA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingInputServiceImpL.initializeA001");
        }

        DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingInputA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingInputA001ResponseDto>();
        IfaMutualFundAccumulateSettingInputA001ResponseDto a001ResponseDto = new IfaMutualFundAccumulateSettingInputA001ResponseDto();
        String mFKaisu = dtoReq.getMfkaisu();
        String mFGo = dtoReq.getMfgo();
        a001ResponseDto.setListFlag(dtoReq.isListFlag());
        // ファンドコード（回数）
        a001ResponseDto.setMfkaisu(mFKaisu);
        // ファンドコード（号）
        a001ResponseDto.setMfgo(mFGo);
        // 協会コード
        a001ResponseDto.setFundCode(dtoReq.getFundCode());

        a001ResponseDto.setSource(dtoReq.getSource());
        a001ResponseDto.setStep(dtoReq.getStep());

        resDtoList.add(a001ResponseDto);

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        /**
         * ① 利用者の口座に対する権限チェックを行う。 
         * 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
         * 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
         * 上記以外：次の処理へ
         */
        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        /**
         * ② 投信積立設定(現金)有無を確認する。 
         *   投信積立設定(クレジット)有無を確認する。
         *   現金、クレジットともに件数＝0の場合：次の処理③へ。
         *   現金、クレジット何れかもしくは両方の件数＞0かつ遷移元が投信積立設定済銘柄一覧の場合：次の処理③へ。
         *   上記以外、アクションID:A011へ
         */

        IfaMutualFundAccumulateSettingInputSql001RequestModel Sql001Req = new IfaMutualFundAccumulateSettingInputSql001RequestModel();
        Sql001Req.setButenCode(cc.getButenCode());
        Sql001Req.setAccountNumber(cc.getAccountNumber());
        Sql001Req.setMfkaisu(mFKaisu);
        Sql001Req.setMfgo(mFGo);
        DataList<IfaMutualFundAccumulateSettingInputSql001ResponseModel> sql001ResultDataList = ifaMutualFundAccumulateSettingInputDao.selectIfaMutualFundAccumulateSettingInputSql001(Sql001Req);

        int sql001ResultCnt = 0;
        if(sql001ResultDataList != null && sql001ResultDataList.getDataList() != null) {
            List<IfaMutualFundAccumulateSettingInputSql001ResponseModel> dataList = sql001ResultDataList.getDataList();
            if (dataList != null && dataList.size() > 0){
                sql001ResultCnt = dataList.get(0).getRowCount();
            }
        }

        IfaMutualFundAccumulateSettingInputSql002RequestModel Sql002Req = new IfaMutualFundAccumulateSettingInputSql002RequestModel();
        Sql002Req.setButenCode(cc.getButenCode());
        Sql002Req.setAccountNumber(cc.getAccountNumber());
        Sql002Req.setMfkaisu(mFKaisu);
        Sql002Req.setMfgo(mFGo);
        DataList<IfaMutualFundAccumulateSettingInputSql002ResponseModel> sql002ResultDataList = ifaMutualFundAccumulateSettingInputDao.selectIfaMutualFundAccumulateSettingInputSql002(Sql002Req);

        int sql002ResultCnt = 0;
        if(sql002ResultDataList != null && sql002ResultDataList.getDataList() != null) {
            List<IfaMutualFundAccumulateSettingInputSql002ResponseModel> dataList = sql002ResultDataList.getDataList();
            if (dataList != null && dataList.size() > 0){
                sql002ResultCnt = dataList.get(0).getRowCount();
            }
        }

        if((sql001ResultCnt != 0 || sql002ResultCnt != 0) && !"SUB0202_0403".equals(dtoReq.getSource())) {
            // 現金、クレジット何れかもしくは両方の件数＞0かつ遷移元が投信積立設定済銘柄一覧の場合：次の処理③へ。
            // 上記以外、アクションID:A011へ
            // 投信積立設定済銘柄一覧画面を表示する。
            a001ResponseDto.setGoToPageActionId("SUB0202_0403");
            resDtoList.clear();
            resDtoList.add(a001ResponseDto);
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        /**
         * ③ 取引コース媒介可否チェックを行う。
         * 取引可：次の処理へ。
         * 上記以外：取引不可エラーを返す。
         */
        // ③取引コース媒介可否チェックを行う。（FCT003）
        // 以下を入力値としてFCT003を実行
        InputFct003Dto fct003Req = new InputFct003Dto();
        // ・顧客共通情報.部店コード
        fct003Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct003Req.setProductCd(DOMESTIC_MUTUALFUND);
        // ・レスポンス.取引種別
        fct003Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            // （区分値：9　＠表示パターン：1　）
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        /**
         * ④ 通貨選択型投信 または 複雑型投信チェックを行う。 
         * 銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：エラーを返す。 errors.fnd.selectedBrand.currencySelectionType   通貨選択型投信のため、IFAポータルからの取引はできません。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：エラーを返す。 errors.fnd.selectedBrand.complexType  複雑型投信のため、IFAポータルからの取引はできません。
         */
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
         * ⑥ 預り区分を取得する。
         *  SET_POSSIBLEの預り区分が存在する場合：次の処理へ
         *  SET_POSSIBLEの預り区分が存在しない場合：エラーを返す。
         *    errors.fndReserve.depositnotExit   追加設定が可能な預り区分がありません。
         */
        // 設定可能な預り区分の取得
        // /safe/fundTrade/fund/reserve/setting/get_trade_types
        GetFundTradeReserveSettingGetTradeTypeReq api002Req = new GetFundTradeReserveSettingGetTradeTypeReq();
        api002Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundReserveCanTradeTypeApiIn api002In = new FundReserveCanTradeTypeApiIn();
        api002In.setFundCode(dtoReq.getFundCode());
        api002Req.setParameter(api002In);
        GetFundTradeReserveSettingGetTradeTypeRes api002Res = null;

        try {
            api002Res = safeFundTradeService.getSettinDataList(api002Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundReserveCanTradeTypeApiOut fundReserveCanTradeTypeApiOut = api002Res.getFundReserveCanTradeTypeApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundReserveCanTradeTypeApiOut);

        String accountType = null;
        List<IfaMutualFundAccumulateSettingInputA001Response_AccountType> accountTypeObjectList = new ArrayList<>();

        // 各項目の状態によってコード値をセット(配列で保持)する
         // ■旧ジュニアNISA口座開設有無=""true""の場合
         /** openedJnisa    旧ジュニアNISA口座開設有無 */
        if (fundReserveCanTradeTypeApiOut.isOpenedJnisa()) {

            // 旧ジュニアNISA口座開設有無
            a001ResponseDto.setOpenedJnisa("1");

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNormalStatus())) {
                /** cashNormalStatus    現金_特定／一般 */
                // 　■現金_特定／一般=SET_POSSIBLEの場合
                // 　　1
                accountType = " ";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                // "総合口座−特定/一般"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "2"));
                accountTypeObjectList.add(accountTypeObject);
            }

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNisaGrowthStatus())) {
                /** cashNisaGrowthStatus    現金_NISA（成長投資枠） */
                // 　■現金_NISA（成長投資枠）=SET_POSSIBLEの場合
                // 　　9
                accountType = "H";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"総合口座－NISA（成長投資枠）"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "2"));
                accountTypeObjectList.add(accountTypeObject);
            }

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNisaReserveStatus())) {
                /** cashNisaReserveStatus   現金_NISA（つみたて投資枠） */
                // 　■現金_NISA（つみたて投資枠）=SET_POSSIBLEの場合
                // 　　10
                accountType = "I";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"総合口座－NISA（つみたて投資枠）"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "2"));
                accountTypeObjectList.add(accountTypeObject);
            }

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashJnisaNormalStatus())) {
                /** cashJnisaNormalStatus   現金_ジュニア特定／一般 */
                // 　■現金_ジュニア特定／一般=SET_POSSIBLEの場合
                // 　　5
                accountType = "5";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"ジュニアNISA口座－特定/一般"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "2"));
                accountTypeObjectList.add(accountTypeObject);
            }
        } else {

            // 旧ジュニアNISA口座開設有無
            a001ResponseDto.setOpenedJnisa("0");

            // ■上記以外
            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNormalStatus())) {
                /** cashNormalStatus    現金_特定／一般 */
                // 　■現金_特定／一般=SET_POSSIBLEの場合
                // 　　1
                accountType = " ";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"特定/一般"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "3"));
                accountTypeObjectList.add(accountTypeObject);
            }

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNisaGrowthStatus())) {
                /** cashNisaGrowthStatus    現金_NISA（成長投資枠） */
                // 　■現金_NISA（成長投資枠）=SET_POSSIBLEの場合
                // 　　9
                accountType = "H";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"NISA（成長投資枠）"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "3"));
                accountTypeObjectList.add(accountTypeObject);
            }

            if ("SET_POSSIBLE".equals(fundReserveCanTradeTypeApiOut.getCashNisaReserveStatus())) {
                /** cashNisaReserveStatus   現金_NISA（つみたて投資枠） */
                // 　■現金_NISA（つみたて投資枠）=SET_POSSIBLEの場合
                // 　　10
                accountType = "I";
                IfaMutualFundAccumulateSettingInputA001Response_AccountType accountTypeObject = new IfaMutualFundAccumulateSettingInputA001Response_AccountType();
                accountTypeObject.setKey(accountType);
                //"NISA（つみたて投資枠）"
                accountTypeObject.setValue(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "3"));
                accountTypeObjectList.add(accountTypeObject);
            }
        }

        if (accountType == null) {
            // errors.fndReserve.depositnotExit を返す
            // 追加設定が可能な預り区分がありません。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_FNDRESERVE_DEPOSITNOTEXIT.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_FNDRESERVE_DEPOSITNOTEXIT.key));
            return dtoRes;
        } else {
            a001ResponseDto.setAccountTypeOptions(accountTypeObjectList);
        }

        /** ⑦   投信基本を取得する。 */
        // 投信基本・詳細の取得
        // /safe/fundProduct/fund/basic
        GetFundBasicReq api003Req = new GetFundBasicReq();
        api003Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundBasicInfoApiIn api003In = new FundBasicInfoApiIn();
        api003In.setFundCode(dtoReq.getFundCode());
        api003Req.setParameter(api003In);
        GetFundBasicRes api003Res = null;

        try {
            api003Res = safeFundProductService.getBasicInfo(api003Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundBasicInfoApiOut api003Out= api003Res.getFundBasicInfoApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api003Out);

        /** 銘柄コード. */
        // ファンドコード（回数）
        a001ResponseDto.setMfkaisu(mFKaisu);
        // ファンドコード（号）
        a001ResponseDto.setMfgo(mFGo);

        // 銘柄名
        String fundName = Optional.ofNullable(api003Out.getFundName()).orElse("");
        a001ResponseDto.setFundName(fundName);

        // 基準価額
        /**
         * ■無効値（数値以外）の場合 ""-"" 
         * ■上記以外 基準価格+""円""
         */
        if (api003Out.getStandardPrice() == null) {
            a001ResponseDto.setStandardPriceStr("-");
        } else {
            a001ResponseDto.setStandardPriceStr(api003Out.getStandardPrice().toPlainString());
        }

        // 基準価額増減区分
        /**
         * ↑,↓,△" 
         * ＠取得パターン:1 ＠表示パターン:1
         */
        String tick = null;
        /**
         * 変更なし NO_CHANGE, 増加 INCREASE, 下降 DECLINE
         */
        if ("INCREASE".equals(api003Out.getPreviousStatus())) {
            // 1=↑
            tick = "1";
        } else if ("DECLINE".equals(api003Out.getPreviousStatus())) {
            // 2=↓
            tick = "2";
        } else {
            // △
            tick = " ";
        }
        a001ResponseDto.setTick(tick);

        // 基準価額単位
        /**
         * "/" +基準価額単位
         */
        if (api003Out.getStandardPriceUnit() != null) {
            a001ResponseDto.setStandardPriceUnitStr(api003Out.getStandardPriceUnit().toPlainString());
        }

        // 前日比
        /**
         * ■基準価額データがない場合 "-"を表示 
         * ■上記以外 前日比（+は赤、-は青0は黒）
         */
        String previousChangeStr;
        String previousChangeSign;
        if (api003Out.getStandardPrice() == null) {
            previousChangeStr = "-";
            previousChangeSign = "";
        } else {
            BigDecimal previousChange = api003Out.getPreviousChange();
            previousChangeStr = previousChange.toPlainString();
            if (previousChange.compareTo(new BigDecimal(0)) == 1) {
                previousChangeSign = "+";
                ;
            } else if (previousChange.compareTo(new BigDecimal(0)) == -1) {
                previousChangeSign = "-";
            } else {
                previousChangeSign = "0";
            }
        }
        a001ResponseDto.setPreviousChangeStr(previousChangeStr);
        a001ResponseDto.setPreviousChangeSign(previousChangeSign);

        // 前日比率
        /**
         * ■基準価額増減区分＝NO_CHANGEの場合 "（-%）"を表示 
         * ■上記以外 前日比率+"%"（+は赤、-は青、0は黒）
         */
        String previousRatio;
        if ("NO_CHANGE".equals(api003Out.getPreviousStatus())) {
            previousRatio = "-";
        } else {
            previousRatio = api003Out.getPreviousRatio();
        }
        a001ResponseDto.setPreviousRatio(previousRatio);

        // 純資産
        /**
         * ■純資産=値なし の場合 "-"を表示 
         * ■上記以外 純資産+"百万円"
         */
        String netAssetStr = "-";
        BigDecimal netAsset = api003Out.getNetAsset();
        if (netAsset != null) {
            netAssetStr = netAsset.toPlainString();
        }
        a001ResponseDto.setNetAssetStr(netAssetStr);

        // 基準価額日付
        /**
         * ■0桁文字の場合 "（--/--/-- 現在）"”を表示 
         * ■上記以外 "（"+基準価額日付+"現在） "
         */
        if (api003Out.getPriceDate() == null || api003Out.getPriceDate().trim().length() == 0) {
            a001ResponseDto.setPriceDate("（--/--/-- 現在）");
        } else {
            a001ResponseDto.setPriceDate("（" + api003Out.getPriceDate() + "現在）");
        }

        // ①   遷移元が国内投信購入・積立可能一覧画面の場合、国内投信購入・積立可能一覧画面に戻る。
        // ②   遷移元が投信積立設定済銘柄一覧画面の場合、投信積立設定済銘柄一覧画面に戻る。
        a001ResponseDto.setGoBackButton(dtoReq.getSource());

        // 決済方法 現金

        /**
         * NISA枠ぎりぎり注文
         * ■画面.預り区分を選択した場合 NISA（成長投資枠）、NISA（つみたて投資枠）、
         * 総合口座－NISA（成長投資枠）、総合口座－NISA（つみたて投資枠） 
         * ■積立買付単位＝０の場合 項目表示 
         * ■上記以外 項目非表示
         */

        // NISA枠ぎりぎり注文 コメント
        List<MCode> selSql003_1Res = mcodeService.getMCodeList("99", "01", "23");
        if (selSql003_1Res != null && 0 < selSql003_1Res.size()) {
            a001ResponseDto.setNisaBarelyBuyingTypeComment(selSql003_1Res.get(0).getName());
        }

        /**
         * 課税枠シフト注文
         * ■画面.預り区分を選択した場合 NISA（成長投資枠、 総合口座－NISA（成長投資枠） 
         * ■積立買付単位＝０の場合 項目表示 
         * ■上記以外 項目非表示
         */

        // 課税枠シフト注文 コメント
        List<MCode> selSql003_2Res = mcodeService.getMCodeList("99", "01", "24");
        if (selSql003_2Res != null && 0 < selSql003_2Res.size()) {
            a001ResponseDto.setTaxShiftTypeComment(selSql003_2Res.get(0).getName());
        }

        // 積立コース
        /**
         * ■画面.預り区分を選択した場合 NISA（つみたて投資枠）、総合口座－NISA（つみたて投資枠） ＠取得パターン:2 ＠表示パターン:2
         * ■上記以外 ＠取得パターン:1 ＠表示パターン:1"
         */

        /** ⑧   投信詳細を取得する。 */
        // 投信基本・詳細の取得
        // /safe/fundProduct/fund/detail
        /** reserveOrderUnit    積立買付単位 */
        GetFundDetailReq api004Req = new GetFundDetailReq();
        api004Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundDetailInfoApiIn api004In = new FundDetailInfoApiIn();
        api004In.setFundCode(dtoReq.getFundCode());
        api004Req.setParameter(api004In);
        GetFundDetailRes api004Res = null;

        try {
            api004Res = safeFundProductService.getDetailInfo(api004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundDetailInfoApiOut api004Out= api004Res.getFundDetailInfoApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api004Out);

        // 積立買付単位
        String reserveOrderUnit = Optional.ofNullable(api004Out.getReserveOrderUnit()).orElse("");
        a001ResponseDto.setReserveOrderUnit(reserveOrderUnit);

        // 最低申込金額
        String minApplyAmount = null;
        if ("0".equals(reserveOrderUnit)) {
            // '■積立買付単位＝０の場合'
            // ”100円”
            minApplyAmount = "100円";
        } else if ("1".equals(reserveOrderUnit)) {
            // ■積立買付単位＝１の場合
            // ”1万円”
            minApplyAmount = "1万円";
        } else if ("2".equals(reserveOrderUnit)) {
            // ■積立買付単位＝２の場合
            // ”1千円”
            minApplyAmount = "1千円";
        } else if ("3".equals(reserveOrderUnit)) {
            // ■積立買付単位＝３の場合
            // ”500円”
            minApplyAmount = "500円";
        }
        a001ResponseDto.setMinApplyAmount(minApplyAmount);

        // 申込単位
        String applyUnit;
        if ("1".equals(reserveOrderUnit)) {
            // ■積立買付単位＝１の場合
            //　”1千円”
            applyUnit = "1千円";
        } else {
            // ■上記以外
            //　”1円”
            applyUnit = "1円";
        }
        a001ResponseDto.setApplyUnit(applyUnit);

        /** ⑨   積立設定サマリを取得する。 */
        // 積立設定サマリ
        // /safe/fundTrade/fund/reserve/setting_data_summary
        GetFundReserveSettingDataSummaryReq api005Req = new GetFundReserveSettingDataSummaryReq();
        api005Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        api005Req.setParameter(new ReserveSettingSummaryApiIn());
        GetFundReserveSettingDataSummaryRes api005Res = null;

        try {
            api005Res = safeFundTradeService.getSettinSummary(api005Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingSummaryApiOut reserveSettingSummaryApiOut = api005Res.getReserveSettingSummaryApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingSummaryApiOut);

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneMonthSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneMonthSumAmountObj = reserveSettingSummaryApiOut.getOneMonthSumAmount();
        oneMonthSumAmount.setSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCashSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNormalSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneYearSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneYearSumAmountObj = reserveSettingSummaryApiOut.getOneYearSumAmount();
        oneYearSumAmount.setSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCashSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNormalSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        a001ResponseDto.setOneMonthSumAmount(oneMonthSumAmount);
        a001ResponseDto.setOneYearSumAmount(oneYearSumAmount);

        IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo bonusMonthSumAmountInfo = new IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo();
        // ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());
        // 特定／一般 ボーナス月設定の合計金額
        bonusMonthSumAmountInfo
                .setBonusSumAmountNormal(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNormal())
                        .orElse(new BigDecimal(0)).toPlainString());
        // NISA（成長投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaGrowth(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaGrowth()).orElse(new BigDecimal(0))
                        .toPlainString());
        // NISA（つみたて投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaReserve(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaReserve())
                        .orElse(new BigDecimal(0)).toPlainString());

        a001ResponseDto.setBonusMonthSettingInfo(bonusMonthSumAmountInfo);

        // ■1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額＝値ありの場合
        // 1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額÷12
        // ※ 小数点以下切り捨て
        BigDecimal oneYearLimitNisaReserveAmount = reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount();

        if (oneYearLimitNisaReserveAmount != null) {
            a001ResponseDto.setOneMonthLimitNisaReserveAmount(oneYearLimitNisaReserveAmount.divide(new BigDecimal(12))
                    .setScale(0, RoundingMode.DOWN).toPlainString());
        } else {
            a001ResponseDto.setOneMonthLimitNisaReserveAmount("0");
        }

        a001ResponseDto.setOneYearLimitNisaReserveAmount(
                Optional.ofNullable(reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

        // ボーナス月の設定金額
        a001ResponseDto.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());

        // NISA（つみたて投資枠）ボーナス月設定の合計金額
        a001ResponseDto.setBonusSumAmountNisaReserve(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaReserve())
                .orElse(new BigDecimal(0)).toPlainString());

        /** ⑩   ポイント_積立買付利用設定を取得する。 */
        // ポイント_積立買付利用設定取得
        // /safe/account/account/point/get_reserve_buy_setting
        GetAccountPointGetReserveBuySettingReq api006Req = new GetAccountPointGetReserveBuySettingReq();
        api006Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        api006Req.setParameter(new ReserveBuySettingGetApiIn());
        GetAccountPointGetReserveBuySettingRes api006Res = null;

        try {
            api006Res = safeAccountService.getReserveBuySetting(api006Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveBuySettingGetApiOut reserveBuySettingGetApiOut = api006Res.getReserveBuySettingGetApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveBuySettingGetApiOut);

        // ■ポイント利用設定が”利用ポイント指定”の場合 "毎月の利用上限："+ポイント利用上限+"ポイント"
        /**
            ■ポイント利用設定が”利用ポイント指定”の場合
            　"毎月の利用上限："+ポイント利用上限+"ポイント"
            ■ポイント利用設定が”全ポイント利用”の場合
            　”すべての利用可能ポイントを使う”
            ■ポイント利用設定が”設定登録なし”の場合
            　"利用しない"
            ■上記以外（ポイント利用設定の取得時にエラーが発生した場合も）
            　”確認できません”
         */
        if ("SOME_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            a001ResponseDto
                    .setPointUseUpperLimit(Optional.ofNullable(reserveBuySettingGetApiOut.getPointUseUpperLimit())
                            .orElse(new BigDecimal(0)).toPlainString());
            a001ResponseDto.setPointSetting("毎月の利用上限：" + "&{pointLimit}" + "ポイント");
        } else if ("ALL_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”全ポイント利用”の場合 ”すべての利用可能ポイントを使う”
            a001ResponseDto.setPointSetting("すべての利用可能ポイントを使う");
        } else if ("UNUSED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”設定登録なし”の場合 "利用しない"
            a001ResponseDto.setPointSetting("利用しない");
        } else {
            // ■上記以外（ポイント利用設定の取得時にエラーが発生した場合も） ”確認できません”
            a001ResponseDto.setPointSetting("確認できません");
        }

        // レスポンスをコントローラーに返却する。
        BeanUtils.copyProperties(a001ResponseDto, dtoRes);

        resDtoList.clear();
        resDtoList.add(a001ResponseDto);

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

    @Override
    public DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto> settingConfirmA010(
            IfaMutualFundAccumulateSettingInputA010RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingInputServiceImplL.settingConfirmA010");
        }

        DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto>();
        List<IfaMutualFundAccumulateSettingInputA010ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingInputA010ResponseDto>();
        IfaMutualFundAccumulateSettingInputA010ResponseDto a010Res = new IfaMutualFundAccumulateSettingInputA010ResponseDto();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        // 口座番号: 部店コード + "-" + 口座番号
        a010Res.setAccountNumber(cc.getButenCode() + "-" + org.apache.commons.lang3.StringUtils.leftPad(Optional.ofNullable(cc.getAccountNumber()).orElse(""), 7, "0"));
        // 個人・法人アイコン
        a010Res.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        a010Res.setCustomerName(cc.getCustomerNameKanji() + "(" + cc.getCustomerNameKana() + ")");

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
        ③   ユーザ共通情報.CCSログイン用IDのチェックを行う。
            未設定(Null または空文字）の場合：取引不可エラーを返す。
            上記以外：次の処理へ。
         */
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CCSIDUNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CCSIDUNREGISTERED.key));
        }

        /**
        ④   通貨選択型投信　または　複雑型投信チェックを行う。
            銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
            銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：エラーを返す。
            銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：エラーを返す。
         */
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

        a010Res.setMfkaisu(dtoReq.getMfkaisu());
        a010Res.setMfgo(dtoReq.getMfgo());
        a010Res.setBrandCode(dtoReq.getMfkaisu() + "." + dtoReq.getMfgo());

        /**
        ⑤   口座の取引制限チェックを行う。
            注意情報エラー有無="1"（あり）：エラーを返す。
            お知らせエラー有無="1"（あり）：エラーを返す。
            注意情報アラート有無="1"（あり）：アラート情報を格納する。
            お知らせアラート有無="1"（あり）：アラート情報を格納する。
            上記チェックを実施したら、次の処理へ。
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
        // 注意情報アラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), ONE)) {
            String codeName = codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            a010Res.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WRANINGS_NOTICEWARNINGCHECK.key, new String[] { codeName }));
            // お知らせアラート
        }
        // お知らせアラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), ONE)) {
            a010Res.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_INFORMATIONCHECK.key));
        }

        /**
         * ⑥ 確認書受け入れチェックを行う。 
         * 勧誘区分＝”勧誘あり”かつ受付方法＝店頭もしくは訪問：アラート情報を格納する。 
         * 上記チェックを実施したら、次の処理へ。
         */
        // warnings.fndReserve.confirmationCheck
        // 受付方法が店頭または訪問になっているため、確認書の受け入れが必要です。
        String kanyuKbn = dtoReq.getKanyuKbn();
        String receiveMethod = dtoReq.getReceiveMethod();
        // 1:勧誘あり;2:勧誘なし
        a010Res.setKanyuKbn(kanyuKbn);
        // 1:店頭,2:訪問,3:電話他
        a010Res.setReceiveMethod(receiveMethod);
        if ("1".equals(kanyuKbn) && ("1".equals(receiveMethod) || "2".equals(receiveMethod))) {
            a010Res.setConfirmDocumentAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_FNDRESERVE_CONFIRMATIONCHECK.key));
        }

        /**
         * ⑦ コンプラランクチェックを行う。 
         * FCT006.判定結果=0：ノーマル：FCT006.開始基準確認メッセージIDを格納し、次の処理へ
         * FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言 および
         * FCT006.開始基準確認メッセージIDを格納し、次の処理へ 
         * FCT006.判定結果=2：エラー：エラーを返す。
         * FCT006.判定結果=上記以外：エラーを返す。
         */
        IfaMutualFundAccumulateSettingInputComplianceRankCheck complianceRankCheck = new IfaMutualFundAccumulateSettingInputComplianceRankCheck();

        InputFct006Dto fct006Req = new InputFct006Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct006Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct006Req.setAccountNumber(cc.getAccountNumber());
        // 国内外国区分("0":国内)をセットする
        fct006Req.setBrDomesticFgnInd(DOMESTIC);
        // 商品区分("3△":投信)をセットする
        fct006Req.setBrBrandInd(MUTUALFUND);
        // 銘柄コード1(リクエスト.銘柄コード)をセットする
        fct006Req.setBrandCode1(dtoReq.getMfkaisu());
        String brandCode2 = dtoReq.getMfgo();
        fct006Req.setBrandCode2(brandCode2);
        // 勧誘区分(リクエスト.勧誘区分)をセットする
        fct006Req.setInvitationType(kanyuKbn);
        // 受注方法(パラメータ.受注方法)をセットする
        fct006Req.setOrderMethod(receiveMethod);
        // コンプラチェック種類("1":買付注文)をセットする
        fct006Req.setComplaCheckKind(PURCHASE_ORDER);
        // 共通関数FCT006を呼び出す
        OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);
        // if(FCT006.判定結果=0：ノーマル){
        if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
            // 開始基準確認メッセージ
            // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
            if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                complianceRankCheck.setStartCriteriaConfirmMsg(IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
            }

            // FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言　および　FCT006.開始基準確認メッセージIDを格納し、次の処理へ
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
            // メッセージ
            complianceRankCheck.setMessage(IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            // チェックボックス文言
            complianceRankCheck.setInvitationCheck(fct006Res.getChkBoxLabel());
            // 開始基準確認メッセージ
            // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
            if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                complianceRankCheck.setStartCriteriaConfirmMsg(IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
            }

            // FCT006.判定結果=2：エラー：エラーを返す。
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    fct006Res.getMessageId(), IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            return dtoRes;

            // FCT006.判定結果=上記以外：エラーを返す。
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    fct006Res.getMessageId(), IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            return dtoRes;
        }

        // コンプラランクチェック
        a010Res.setComplianceRankCheck(complianceRankCheck);

        /**
         * ⑧ 設定確認を行う。 
         * 積立設定入力確認APIの呼出し。 
         * 正常：次の処理へ。 
         * エラー：エラーを返す。
         */
        PostFundReserveSettingInputConfirmReq api007Req = new PostFundReserveSettingInputConfirmReq();
        api007Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));

        FundReserveSettingInputConfirmApiIn api007ApiIn = new FundReserveSettingInputConfirmApiIn();

        api007ApiIn.setFundCode(dtoReq.getFundCode());    // 協会コード
        api007ApiIn.setPaymentMethod("CASH");   // 決済方法

        // 預り区分
        /** [ ]非NISA注文 */
        /** [H]NISA（成長投資枠） */
        /** [I]NISA（つみたて投資枠）  */
        /** [5]ジュニアNISA口座 特定/一般 */
        api007ApiIn.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(dtoReq.getAccountType()));

        // 設定金額
        if (dtoReq.getSettingAmount() != null && dtoReq.getSettingAmount().trim().length() > 0) {
            BigDecimal settingAmount = new BigDecimal(dtoReq.getSettingAmount().trim());
            api007ApiIn.setSettingAmount(settingAmount);
        }

        // コース区分
        /**
         * 1 毎日(EVERY_DAY) 
         * 2 毎週(EVERY_WEEK) 
         * 3 毎月(EVERY_MONTH) 
         * 4 複数日(MULTIPLE_DAYS)
         * 5 奇数月(BIMONTHLY)
         * 6 偶数月(BIMONTHLY)
         */
        api007ApiIn.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getSafeEnumByIfaValue(dtoReq.getCourseType()));

        // 積立日付
        api007ApiIn.setSettingReserveDay(dtoReq.getSettingReserveDay());

        // 積立隔月設定
        /**
         * 1: 奇数月
         * 2: 偶数月
         */
        if ("5".equals(dtoReq.getCourseType())) {
            api007ApiIn.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("1"));
        } else if ("6".equals(dtoReq.getCourseType())) {
            api007ApiIn.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("2"));
        } else {
            api007ApiIn.setSettingReserveBimonthly(null);
        }

        // 積立毎週設定
        /**
         * 1: MONDAY
         * 2: TUESDAY
         * 3: WEDNESDAY
         * 4: THURSDAY
         * 5: FRIDAY
         */
        api007ApiIn.setSettingReserveWeek(SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getSafeEnumByIfaValue(dtoReq.getSettingReserveWeek()));

        // 積立複数日設定
        if(dtoReq.getSettingReserveMultiDay() != null && dtoReq.getSettingReserveMultiDay().length() > 0) {
            api007ApiIn.setSettingReserveMultiDay(dtoReq.getSettingReserveMultiDay());
        } else {
            api007ApiIn.setSettingReserveMultiDay(null);
        }

        // NISA枠ぎりぎり注文
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
        api007ApiIn.setNisaBarelyBuyingType(
                SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum.getSafeEnumByIfaValue(ifaNisaBarelyBuyingTypeCode));

        // 課税枠シフト注文
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
        api007ApiIn.setTaxShiftType(
                SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum.getSafeEnumByIfaValue(ifaTaxShiftTypeCode));


        // ボーナス設定有無
        if ("1".equals(dtoReq.getSettingBonusFlag())) {
            // ■ボーナス設定＝するの場合
            // 　"true"
            api007ApiIn.setSettingBonusFlag(true);

              // ボーナス設定金額
            if (dtoReq.getSettingBonusAmount() != null && dtoReq.getSettingBonusAmount().trim().length() > 0) {
                BigDecimal settingBonusAmount = new BigDecimal(dtoReq.getSettingBonusAmount().trim());
                api007ApiIn.setSettingBonusAmount(settingBonusAmount);
            }

            // ボーナス１設定月
            if(dtoReq.getSettingBonus1Month() != null && dtoReq.getSettingBonus1Month().length() > 0) {
                api007ApiIn.setSettingBonus1Month(dtoReq.getSettingBonus1Month());
            } else {
                api007ApiIn.setSettingBonus1Month(null);
            }
            // ボーナス１設定日
            if(dtoReq.getSettingBonus1Day() != null && dtoReq.getSettingBonus1Day().length() > 0) {
                api007ApiIn.setSettingBonus1Day(dtoReq.getSettingBonus1Day());
            } else {
                api007ApiIn.setSettingBonus1Day(null);
            }

            // ボーナス２設定月
            if(dtoReq.getSettingBonus2Month() != null && dtoReq.getSettingBonus2Month().length() > 0) {
                api007ApiIn.setSettingBonus2Month(dtoReq.getSettingBonus2Month());
            } else {
                api007ApiIn.setSettingBonus2Month(null);
            }
            // ボーナス２設定日
            if(dtoReq.getSettingBonus2Day() != null && dtoReq.getSettingBonus2Day().length() > 0) {
                api007ApiIn.setSettingBonus2Day(dtoReq.getSettingBonus2Day());
            } else {
                api007ApiIn.setSettingBonus2Day(null);
            }
        } else {
            // ■ボーナス設定＝しないの場合
            // 　”false”
            api007ApiIn.setSettingBonusFlag(false);
            
        }

        // 受付経路区分
        // ”2"　(BRANCH_OFFICE) ：支店
        api007ApiIn.setRouteType("BRANCH_OFFICE");

        // ユーザ共通情報.CCSログイン用ID
        // オペレーターID
        api007ApiIn.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        api007Req.setParameter(api007ApiIn);
        PostFundReserveSettingInputConfirmRes api007Res = null;

        try {
            api007Res = safeFundTradeService.reserveCreateConfirm(api007Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e, "errors.cmn.settingExecution.failed");
        }

        FundReserveSettingInputConfirmApiOut api007Out= api007Res.getFundReserveSettingInputConfirmApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api007Out);

        //API007  ファンド名
        a010Res.setBrandName(api007Out.getFundName());
        //API007  協会コード
        a010Res.setFundCode(api007Out.getFundCode());

        //リクエスト   銘柄コード
        a010Res.setMfkaisu(dtoReq.getMfkaisu());
        a010Res.setMfgo(dtoReq.getMfgo());

        // API007 決済方法
        /**
         * 現金 1 
         * クレジットカード 2
         */
        a010Res.setPaymentMethod(
                SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(api007Out.getPaymentMethod()));

        //API007  預り区分
        String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                .getIfaValueBySafeEnum(api007Out.getAccountType());
        a010Res.setAccountType(ifaAccountTypeCode);

        //リクエスト   旧ジュニアNISA口座開設有無
        if("1".equals(dtoReq.getOpenedJnisa())) {
            a010Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "2"));
        } else {
            a010Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "1"));
        }

        a010Res.setOpenedJnisa(dtoReq.getOpenedJnisa());

        // 積立買付単位
        a010Res.setReserveOrderUnit(dtoReq.getReserveOrderUnit());

        // API007  NISA枠ぎりぎり注文
        /**
         * USE 2
         * UNUSED 1
         * UNSUPPORTED 0
         */
        String ifaNisaBarelyBuyingType = SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                .getIfaValueBySafeEnum(api007Out.getNisaBarelyBuyingType());
        if (ifaNisaBarelyBuyingType == null || "0".equals(ifaNisaBarelyBuyingType)) {
            // ■上記以外
            // "0”
            a010Res.setNisaBarelyBuyingType("0");
            a010Res.setNisaBarelyBuyingTypeShow("0");
        } else {
            // ■NISAぎりぎり注文チェック＝チェックありの場合
            // ”2”
            // ■NISAぎりぎり注文チェック＝チェックなしの場合
            // ”1”
            a010Res.setNisaBarelyBuyingType(ifaNisaBarelyBuyingType);
            a010Res.setNisaBarelyBuyingTypeShow("1");
        }

        // API007  課税枠シフト注文
        /**
         * USE 2
         * UNUSED 1
         * UNSUPPORTED 0
         */
        String ifaTaxShiftType = SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                .getIfaValueBySafeEnum(api007Out.getTaxShiftType());
        if (ifaTaxShiftType == null || "0".equals(ifaTaxShiftType)) {
            // ■上記以外
            // "0”
            a010Res.setTaxShiftType("0");
            a010Res.setTaxShiftTypeShow("0");
        } else {
            // ■課税枠シフト注文チェック＝チェックありの場合
            // ”2”
            // ■課税枠シフト注文文チェック＝チェックなしの場合
            // ”1”
            a010Res.setTaxShiftType(ifaTaxShiftType);
            a010Res.setTaxShiftTypeShow("1");
        }

        // API007  コース区分
        /**
         * EVERY_DAY 1
         * EVERY_WEEK 2
         * EVERY_MONTH 3
         * MULTIPLE_DAYS 4
         * BIMONTHLY && ODD_MONTH 5
         * BIMONTHLY && EVEN_MONTH 6
         */
        a010Res.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(api007Out.getCourseType(),
                api007Out.getSettingReserveBimonthly()));

        // API007  設定金額
        if (api007Out.getSettingAmount() != null) {
            a010Res.setSettingAmount(api007Out.getSettingAmount().toPlainString());
        }
        // API007  設定金額概算手数料
        if (api007Out.getEstimateFundOrder() != null) {
            a010Res.setEstimateFundOrder(api007Out.getEstimateFundOrder().toPlainString());
        }

        // API007  積立日付
        a010Res.setSettingReserveDay(api007Out.getSettingReserveDay());

        // API007  積立隔月設定
        String ifaSettingReserveBimonthly = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                .getIfaValueBySafeEnum(api007Out.getSettingReserveBimonthly());
        if (ifaSettingReserveBimonthly != null) {
            //奇数月 1
            //偶数月 2
            a010Res.setSettingReserveBimonthly(ifaSettingReserveBimonthly);
        }

        // API007 積立毎週設定
        /**
         * MONDAY 1 
         * TUESDAY 2 
         * WEDNESDAY 3 
         * THURSDAY 4 
         * FRIDAY 5
         */
        String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                .getIfaValueBySafeEnum(api007Out.getSettingReserveWeek());
        if (ifaSettingReserveWeek != null) {
            a010Res.setSettingReserveWeek(ifaSettingReserveWeek);
        }

        // API007  積立複数日設定
        if(api007Out.getSettingReserveMultiDay() != null && api007Out.getSettingReserveMultiDay().length() > 0) {
            a010Res.setSettingReserveMultiDay(api007Out.getSettingReserveMultiDay());
        } else {
            a010Res.setSettingReserveMultiDay(null);
        }

        // API007  1ヵ月あたりの設定金額（概算）
        if (api007Out.getOneMonthSumAmount() != null) {
            a010Res.setOneMonthSumAmountStr(api007Out.getOneMonthSumAmount().toPlainString());
        }

        // API007 買付予定日
        String planDate = api007Out.getPlanDate();
        if (planDate != null && planDate.length() == 8) {
            a010Res.setPlanDate(LocalDate.parse(planDate, DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
                    .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
        }

        // API007  ボーナス設定有無
        if (api007Out.isSettingBonusFlag()) {
            // ■ボーナス設定＝するの場合
            // 　"true"
            a010Res.setSettingBonusFlag("1");

            // API007  ボーナス設定金額
            if (api007Out.getSettingBonusAmount() != null) {
                a010Res.setSettingBonusAmount(api007Out.getSettingBonusAmount().toPlainString());
            }

            // API007  ボーナス１設定月
            if(api007Out.getSettingBonus1Month() != null && api007Out.getSettingBonus1Month().length() > 0) {
                a010Res.setSettingBonus1Month(api007Out.getSettingBonus1Month());
            } else {
                a010Res.setSettingBonus1Month(null);
            }
            // API007  ボーナス１設定日
            if(api007Out.getSettingBonus1Day() != null && api007Out.getSettingBonus1Day().length() > 0) {
                a010Res.setSettingBonus1Day(api007Out.getSettingBonus1Day());
            } else {
                a010Res.setSettingBonus1Day(null);
            }

            // API007  ボーナス２設定月
            if(api007Out.getSettingBonus2Month() != null && api007Out.getSettingBonus2Month().length() > 0) {
                a010Res.setSettingBonus2Month(api007Out.getSettingBonus2Month());
            } else {
                a010Res.setSettingBonus2Month(null);
            }
            // API007  ボーナス２設定日
            if(api007Out.getSettingBonus2Day() != null && api007Out.getSettingBonus2Day().length() > 0) {
                a010Res.setSettingBonus2Day(api007Out.getSettingBonus2Day());
            } else {
                a010Res.setSettingBonus2Day(null);
            }

            // API007  ボーナス概算手数料
            if (api007Out.getEstimateFundOrderBonus() != null) {
                a010Res.setEstimateFundOrderBonus(api007Out.getEstimateFundOrderBonus().toPlainString());
            }

            // ボーナス買付予定日
            a010Res.setBonusPlanDate(IfaMutualFundAccumulateSettingUtil.getBonusPlanDate(api007Out.getBonusPlanDate1(),
                    api007Out.getBonusPlanDate2()));

        } else {
            // ■ボーナス設定＝しないの場合
            // 　”false”
            a010Res.setSettingBonusFlag("2");
            
        }

        // API007 次回買付日
        if (api007Out.getNextReserveDate() != null && api007Out.getNextReserveDate().length() == 8) {
            a010Res.setNextReserveDate(
                    LocalDate.parse(api007Out.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
                            .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
        }

        /**
         * ⑨ 投信積立設定確認画面を表示する。
         * 
         */
        // レスポンスをコントローラーに返却する。
        resDtoList.add(a010Res);
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
