package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct025;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticMutualFundOrderConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.FundPlaceOrder1InData;
import jp.co.sbisec.pcenter.dto.yanap.FundPlaceOrder1OutData;
import jp.co.sbisec.pcenter.dto.yanap.FundPlaceOrder2ExtInData;
import jp.co.sbisec.pcenter.dto.yanap.FundPlaceOrder2ExtOutData;

/**
 * 画面ID：SUB0202_0401-02_2
 * 画面名：国内投信注文確認
 * @author 松尾
 *
 * 2024/03/26 新規作成
 */
@Component(value = "cmpIfaDomesticMutualFundOrderConfirmService")
public class IfaDomesticMutualFundOrderConfirmServiceImpL implements IfaDomesticMutualFundOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderConfirmServiceImpL.class);
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct017 fct017;
    
    @Autowired
    private Fct025 fct025;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaDomesticMutualFundOrderConfirmDao dao;
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 取引停止口座エラー値 */
    private static final String SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE = "1";
    
    /** FCT003.媒介可否 判定値*/
    private static final String FCT003_MEDIATE_PROPRIETY_VALUE = "1";
    
    /** 取引不可エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** FCT017.銘柄リスト.NRIコード.書類コード.受入要否 不要 */
    private static final String FCT017_UNNECESSARY = "0";
    
    /** FCT017.銘柄リスト.NRIコード.書類コード.投信銘柄種別 通貨選択型 */
    private static final String FCT017_CURRENCYSELECT = "1";
    
    /** FCT017.銘柄リスト.NRIコード.書類コード.投信銘柄種別 複雑投信 */
    private static final String FCT017_COMPLEXMUTUALFUND = "2";
    
    /** 通貨選択型：強制注文対象エラー */
    private static final String ERRORS_FND_SELECTEDBRAND_CURRENCYSELECTIONTYPE = "errors.fnd.selectedBrand.currencySelectionType";
    
    /** 複雑投信：強制注文対象エラー */
    private static final String ERRORS_FND_SELECTEDBRAND_COMPLEXTYPE = "errors.fnd.selectedBrand.complexType";
    
    /** FCT025.銘柄リスト.E*TRADE扱い可否=0:不可 */
    private static final String FCT025_ETRADESERVICE_UNABLE = "0";
    
    /** FCT025.銘柄リスト.仲介業者扱可否=0:不可 */
    private static final String FCT025_BROKERSERVICE_UNABLE = "0";
    
    /** 銘柄ステータス取引不可エラー */
    private static final String ERRORS_FND_SELECTEDBRAND_NOTTRADEABLE = "errors.fnd.selectedBrand.notTradeable";
    
    /** 仲介業者取引不可エラー */
    private static final String ERRORS_FND_SELECTEDBRAND_OUTOFIFATRADE = "errors.fnd.selectedBrand.outOfIfaTrade";
    
    /** 区分.証券金銭種別 国内投信*/
    private static final String SECURITY_MONEY_CLASS_DOMESTICMUTUAL = "06";
    
    /** FCT021.注意情報エラー有無="1"（あり） */
    private static final String FCT021_NOTEINFOERRFLAG_ERROR_VALUE = "1";
    
    /** FCT021.お知らせエラー有無="1"（あり） */
    private static final String FCT021_NOTELIMITERRFlAG_ERROR_VALUE = "1";
    
    /** FCT021.注意情報アラート有無="1"（あり） */
    private static final String FCT021_NOTEINFOALERTFLAG_ERROR_VALUE = "1";
    
    /** FCT021.お知らせアラート有無="1" */
    private static final String FCT021_NOTELIMITALERTFLAG_ERROR_VALUE = "1";
    
    /** FCT021.国籍コード "99" */
    private static final String A001_FCT021_COUNTRYCODE = "99";
    
    /** FCT021.通貨コード "999" */
    private static final String A001_FCT021_CURRENCYCODE = "999";
    
    /** FCT006.判定結果=0：ノーマル */
    private static final String FCT006_JUDGEMENTRESULT_NOMAL = "0";
    
    /** FCT006.判定結果=1：アラート */
    private static final String FCT006_JUDGEMENTRESULT_ALERT = "1";
    
    /** FCT006.判定結果=2：エラー */
    private static final String FCT006_JUDGEMENTRESULT_ERROR = "2";
    
    /** 区分.国内外国区分 国内*/
    private static final String DOMESTIC_FOREIGN_TYPE_DOMESTIC = "0";
    
    /** 区分.商品区分　投信*/
    private static final String SECURITY_TYPE_MUTUALFUND = "3 ";
    
    /** 区分.コンプラチェック種類　買付注文*/
    private static final String COMPLA_CHECK_KIND_BUYORDER = "1";
    
    /** 注意情報エラー */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** お知らせエラー */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 注意情報/お知らせアラートエラー  */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：ドメインID */
    private static final String CODE_LIST_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示区分 */
    private static final String CODE_LIST_KEY = "4";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示パターン */
    private static final String CODE_LIST_DISP_PATTERN = "1";
    
    /** △ */
    private static final String SPACE = " ";
    
    /** 種別 正常終了 */
    private static final String SHUBETU_SUCCESS = "     ";
    
    /** 積立買付注文 */
    private static final String ACCUMULATE_BUY_ORDER = "9";
    
    /** 積立買付注文 - クレジットカード決済 */
    private static final String ACCUMULATE_BUY_ORDER_CREDITCARD = "C";
    
    /** SQL001 登録エラー */
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 区分.売却指定　金額指定 */
    private static final String DESIGNATE_AMOUNT = "1";
    
    /** 区分.売却指定　口数指定 */
    private static final String DESIGNATE_UNIT = "2";
    
    /** APIエラー */
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    /** DB更新エラー：API正常の場合 */
    private static final String WARNING_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** ファンドタイプ */
    public enum FundType {
        
        /**　一般 */
        GENERAL("1"),
        /**　累投 */
        TOTAL("2");
        
        private String key;
        
        FundType(String key) {
            
            this.key = key;
        }
    }
    
    /** 区分.取引種別（国内投信）　*/
    public enum TradeCd {
        
        /**　購入（一般） */
        BUY_GENERAL("0"),
        /**　購入（累投） */
        BUY_TOTAL("1"),
        /**　買取（一般） */
        PURCHASE_GENERAL("3"),
        /**　買取（累投） */
        PURCHASE_TOTAL("4"),
        /** 全額買取 */
        FULL_PURCHASE("6"),
        /**　解約（一般） */
        CANCELL_GENERAL("7"),
        /**　解約（累投） */
        CANCELL_TOTAL("8"),
        /** 全額解約 */
        FULL_CANSELL("10");
        
        private String key;
        
        TradeCd(String key) {
            
            this.key = key;
        }
    }
    
    /** チェックボックス */
    public enum CheckBox {
        
        /**　オン */
        ON("1"),
        /**　オフ */
        OFF("0"),
        /**　非表示 */
        NONDISPLAY("");
        
        private String key;
        
        CheckBox(String key) {
            
            this.key = key;
        }
    }
    
    /** 区分.口座区分 */
    public enum AccountType {
        
        /**　総合口座 */
        WHOLE(" "),
        /**　ジュニアNISA口座 */
        JRNISA("1");
        
        private String key;
        
        AccountType(String key) {
            
            this.key = key;
        }
    }
    
    /** 区分.売却指定 */
    public enum DesignatedForSale {
        
        /**　金額指定 */
        AMOUNT("1"),
        /**　口数指定 */
        UNIT("2");
        
        private String key;
        
        DesignatedForSale(String key) {
            
            this.key = key;
        }
    }
    
    /** 購入解約方法 */
    public enum BuyCancellMethod {
        
        /**　金額指定 */
        AMOUNT("1"),
        /**　口数指定 */
        UNIT("2"),
        /**　ALL指定 */
        ALL("3");
        
        private String key;
        
        BuyCancellMethod(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticMutualFundOrderConfirmA001aDtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderConfirmA001aDtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderConfirmSql001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaDomesticMutualFundOrderConfirmA001aRequestDto dtoReq) throws Exception {
        
        DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto>();
        List<IfaDomesticMutualFundOrderConfirmA001aResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderConfirmA001aResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDomesticMutualFundOrderConfirmServiceImplL.orderPlacementA001");
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 顧客共通情報.部店コードと顧客共通情報.口座番号を変数化
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //①   利用者の口座に対する権限チェックを行う。
        InputFct001Dto fct001InData = new InputFct001Dto();
        // 部店コード
        fct001InData.setButenCode(butenCode);
        // 口座番号
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        //対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { butenCode, accountNumber }));
            return dtoRes;
            //取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        } else if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        //上記以外：次の処理へ
        
        //②   取引コース媒介可否チェックを行う。
        //上記以外：取引不可エラーを返す。
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード
        fct003Req.setButenCode(butenCode);
        // 口座番号
        fct003Req.setAccountNumber(accountNumber);
        // 証券金銭種別
        fct003Req.setProductCd(SECURITY_MONEY_CLASS_DOMESTICMUTUAL);
        // 取引種別
        fct003Req.setTradeCd(dtoReq.getTradeCd());
        OutputFct003Dto fct003Res = fct003.doCheck(fct003Req);
        
        //取引可：次の処理へ。
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), FCT003_MEDIATE_PROPRIETY_VALUE) == false) {
            String msgItem = codeListService.getValue(CODE_LIST_MSG_DISPLAY_TARGET_TRADE, CODE_LIST_KEY,
                    CODE_LIST_DISP_PATTERN);
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] { msgItem }));
            return dtoRes;
        }
        
        //③   買いの場合、通貨選択型投信　または　複雑型投信チェックを行う。
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)) {
            InputFct017Dto fct017Req = new InputFct017Dto();
            // 部店コード
            fct017Req.setButenCode(butenCode);
            // 口座番号
            fct017Req.setAccountNumber(accountNumber);
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
            // 照会投信銘柄リスト.NRIコード
            inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
            fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
            OutputFct017Dto fct017Res = fct017.getData(fct017Req);
            
            //銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
            if (!(StringUtils.equals(fct017Res.getBrandList().get(0).getAcceptanceNecessity(), FCT017_UNNECESSARY))) {
                //銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：強制注文対象エラーを返す。
                if (StringUtils.equals(fct017Res.getBrandList().get(0).getMutualFundBrandClass(),
                        FCT017_CURRENCYSELECT)) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            ERRORS_FND_SELECTEDBRAND_CURRENCYSELECTIONTYPE,
                            IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_CURRENCYSELECTIONTYPE));
                    return dtoRes;
                    //銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：強制注文対象エラーを返す。
                } else if (StringUtils.equals(fct017Res.getBrandList().get(0).getMutualFundBrandClass(),
                        FCT017_COMPLEXMUTUALFUND)) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            ERRORS_FND_SELECTEDBRAND_COMPLEXTYPE,
                            IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_COMPLEXTYPE));
                    return dtoRes;
                }
            }
        }
        
        //④   買いの場合、Indigo銘柄設定チェックを行う。
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)) {
            InputFct025Dto fct025Req = new InputFct025Dto();
            // 仲介業者コード
            fct025Req.setBrokerCode(IfaCommonUtil.getUserAccount().getBrokerCode());
            Brand brand = new Brand();
            List<Brand> brandList = new ArrayList<>();
            // 銘柄リスト.NRIコード
            brand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            brandList.add(brand);
            fct025Req.setBrandList(brandList);
            OutputFct025Dto fct025Res = fct025.doCheck(fct025Req);
            
            //銘柄リスト.E*TRADE扱い可否=0:不可：銘柄ステータス取引不可エラーを返す。
            if (StringUtils.equals(fct025Res.getBrandList().get(0).getIsEtradeService(), FCT025_ETRADESERVICE_UNABLE)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        ERRORS_FND_SELECTEDBRAND_NOTTRADEABLE,
                        IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_NOTTRADEABLE));
                return dtoRes;
                //銘柄リスト.仲介業者扱可否=0:不可：仲介業者取引不可エラーを返す。
            } else if (StringUtils.equals(fct025Res.getBrandList().get(0).getIsBrokerService(),
                    FCT025_BROKERSERVICE_UNABLE)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        ERRORS_FND_SELECTEDBRAND_OUTOFIFATRADE,
                        IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_OUTOFIFATRADE));
                return dtoRes;
            }
            //上記以外は次の処理へ。
        }
        
        //⑤   口座の取引制限チェックを行う。
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 部店コード
        fct021Req.setButenCode(butenCode);
        // 口座番号
        fct021Req.setAccountNumber(accountNumber);
        // 証券金銭種別
        fct021Req.setProductCd(SECURITY_MONEY_CLASS_DOMESTICMUTUAL);
        // 取引種別
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        // 国籍コード
        fct021Req.setCountryCd(A001_FCT021_COUNTRYCODE);
        // 通貨コード
        fct021Req.setCurrencyCode(A001_FCT021_CURRENCYCODE);
        OutputFct021Dto fct021Res = fct021.doCheck(fct021Req);
        //注意情報エラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), FCT021_NOTEINFOERRFLAG_ERROR_VALUE)) {
            String msgItem = codeListService.getValue(CODE_LIST_MSG_DISPLAY_TARGET_TRADE, CODE_LIST_KEY,
                    CODE_LIST_DISP_PATTERN);
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_NOTICEERRORCHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] { msgItem }));
            return dtoRes;
            //お知らせエラー有無="1"（あり）：エラーを返す。
        } else if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), FCT021_NOTELIMITERRFlAG_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONCHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
            return dtoRes;
            
            //注意情報アラート有無="1"（あり）：
            //注意情報アラート確認チェックボックス=ON：次の処理へ
            //注意情報アラート確認チェックボックス=OFF　または　非表示：エラーを返す。
        } else if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), FCT021_NOTEINFOALERTFLAG_ERROR_VALUE)) {
            if (StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), CheckBox.OFF.key)
                    || StringUtils.equals(dtoReq.getNoticeInfoAlertConfirm(), CheckBox.NONDISPLAY.key)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
            
            //お知らせアラート有無="1"（あり）：
            //お知らせアラート確認チェックボックス=ON：次の処理へ
            //お知らせアラート確認チェックボックス=OFF　または　非表示：エラーを返す。   
        } else if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), FCT021_NOTELIMITALERTFLAG_ERROR_VALUE)) {
            if (StringUtils.equals(dtoReq.getNoticeAlertConfirm(), CheckBox.OFF.key)
                    || StringUtils.equals(dtoReq.getNoticeAlertConfirm(), CheckBox.NONDISPLAY.key)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        //上記チェックを実施したら、次の処理へ。
        
        //⑥   買いの場合、コンプラランクチェックを行う。
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)) {
            InputFct006Dto fct006Req = new InputFct006Dto();
            // 部店コード
            fct006Req.setButenCode(butenCode);
            // 口座番号
            fct006Req.setAccountNumber(accountNumber);
            // 国内外国区分
            fct006Req.setBrDomesticFgnInd(DOMESTIC_FOREIGN_TYPE_DOMESTIC);
            // 商品区分
            fct006Req.setBrBrandInd(SECURITY_TYPE_MUTUALFUND);
            // 銘柄コード１
            fct006Req.setBrandCode1(dtoReq.getFundCodeTimes());
            //　銘柄コード２
            fct006Req.setBrandCode2(dtoReq.getFundCodeIssues());
            //　勧誘区分
            fct006Req.setInvitationType(dtoReq.getKanyuKbn());
            //　受注方法
            fct006Req.setOrderMethod(dtoReq.getReceiveOrderType());
            //　コンプラチェック種類
            fct006Req.setComplaCheckKind(COMPLA_CHECK_KIND_BUYORDER);
            OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);
            //FCT006.判定結果=0：ノーマル：次の処理へ
            if (StringUtils.equals(fct006Res.getJudgementResult(), FCT006_JUDGEMENTRESULT_NOMAL)) {
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
                if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                    if (StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(), CheckBox.OFF.key)
                            || StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(),
                                    CheckBox.NONDISPLAY.key)) {
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                        return dtoRes;
                    }
                }
                //FCT006.判定結果=1：アラート：
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), FCT006_JUDGEMENTRESULT_ALERT)) {
                //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがON：次の処理へ
                //FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
                if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), CheckBox.OFF.key)
                        || StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), CheckBox.NONDISPLAY.key)) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_OCCURS,
                            IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                    return dtoRes;
                }
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがON：次の処理へ
                //FTC006.開始基準確認メッセージIDがある場合、コンプラランクチェック開始基準確認チェックボックスがOFF　または非表示：エラーを返す。
                if (!(Strings.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId()))) {
                    if (StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(), CheckBox.OFF.key)
                            || StringUtils.equals(dtoReq.getComplianceRankCheckStartBaseConfirm(),
                                    CheckBox.NONDISPLAY.key)) {
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                        return dtoRes;
                    }
                }
                //FCT006.判定結果=2：エラー：エラーを返す。
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), FCT006_JUDGEMENTRESULT_ERROR)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getMessageId(),
                        IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                return dtoRes;
                
                //FCT006.判定結果=上記以外：エラーを返す。
            } else {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, "", fct006Res.getMessageId());
                return dtoRes;
            }
        }
        
        //⑧   発注前に注文内容をDBへ記録する。
        IfaDomesticMutualFundOrderConfirmSql001RequestModel insSql001Req = new IfaDomesticMutualFundOrderConfirmSql001RequestModel();
        //部店コード
        insSql001Req.setButenCode(butenCode);
        //口座番号
        insSql001Req.setAccountNumber(accountNumber);
        //顧客ID
        insSql001Req.setKokyakuId(cc.getCustomerCode());
        //特定口座区分
        //■リクエスト.口座=総合口座　の場合
        if (StringUtils.equals(dtoReq.getAccountType(), AccountType.WHOLE.key)) {
            //顧客共通情報.特定口座区分
            insSql001Req.setTokuteiKouzaKbn(cc.getSpecificAccountType());
            
            //■リクエスト.口座=ジュニアNISA口座　の場合
        } else if (StringUtils.equals(dtoReq.getAccountType(), AccountType.JRNISA.key)) {
            //顧客共通情報.ジュニア特定口座区分
            insSql001Req.setTokuteiKouzaKbn(cc.getJrTokuteiKouzaKbn());
        }
        //ファンドタイプ
        insSql001Req.setFundType(dtoReq.getFundType());
        //ファンドコード（回数）
        insSql001Req.setFundCodeTimes(dtoReq.getFundCodeTimes());
        //ファンドコード（号）
        insSql001Req.setFundCodeIssues(dtoReq.getFundCodeIssues());
        //取引種別
        insSql001Req.setTradeCd(dtoReq.getTradeCd());
        //売買区分
        insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
        //口数
        //■取引種別=購入（一般）　または
        //解約（一般）または
        //買取（一般）または
        //（取引種別=解約（累投）または　買取（累投））　かつ　売却指定=口数指定の場合　セット
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_GENERAL.key)
                || ((StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_TOTAL.key)
                        || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_TOTAL.key))
                        && StringUtils.equals(dtoReq.getSellDesignated(), DesignatedForSale.UNIT.key))) {
            insSql001Req.setUnit(dtoReq.getUnit());
            
            //■上記以外
            //NULL
        } else {
            insSql001Req.setUnit(null);
        }
        
        //金額
        //■（取引種別=解約（累投）または 買取（累投））かつ　　売却指定=金額指定　または
        //取引種別=購入（累投）の場合　セット
        if (((StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_TOTAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_TOTAL.key))
                && StringUtils.equals(dtoReq.getSellDesignated(), DesignatedForSale.AMOUNT.key))
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)) {
            insSql001Req.setAmount(dtoReq.getAmount());
            
            //■上記以外
        } else {
            //NULL
            insSql001Req.setAmount(null);
        }
        //乗換優遇区分
        insSql001Req.setNorikaeYuguKbn(dtoReq.getNorikaeYuguKbn());
        //分配金受取方法
        insSql001Req.setDistributionReceiveMethodWord(dtoReq.getDistributionReceiveMethodWord());
        //預り区分
        insSql001Req.setDepositType(dtoReq.getDepositType());
        //ポイント種別
        insSql001Req.setPointType(dtoReq.getPointType());
        //ポイント利用
        insSql001Req.setPointFlg(dtoReq.getPointFlg());
        //注文時ポイント
        insSql001Req.setOrderPoint(dtoReq.getPoint());
        //レバレッジ投資信託
        insSql001Req.setLeverageInvestTrust(dtoReq.getLeverageInvestTrust());
        //乗換勧誘
        insSql001Req.setNorikaeKanyuKbn(dtoReq.getNorikaeKanyuKbn());
        //同一通貨/同一国の乗換
        insSql001Req.setDouitsuTukaKuniKbn(dtoReq.getDouitsuTukaKuniKbn());
        //他社間投信乗換勧誘
        insSql001Req.setTashaNorikaeKbn(dtoReq.getTashaNorikaeKbn());
        //短期売却確認
        insSql001Req.setTankiSellKbn(dtoReq.getTankiSellKbn());
        //償還前売却確認
        insSql001Req.setShokanMaeKbn(dtoReq.getShokanMaeKbn());
        //勧誘区分
        insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        //受注方法
        insSql001Req.setReceiveOrderType(dtoReq.getReceiveOrderType());
        //目論見書の交付方法
        insSql001Req.setMokuromiKoufuKbn(dtoReq.getMokuromiKoufuKbn());
        //利益相反可能性の説明
        insSql001Req.setConflictOfInterestExplain(dtoReq.getConflictOfInterestExplain());
        //確認項目.目論見書補完書面の確認
        insSql001Req.setCheckMokuromi(dtoReq.getCheckMokuromi());
        //確認項目.窓空きファンドの注意事項に同意
        insSql001Req.setCheckMadoAki(dtoReq.getCheckMadoAki());
        //確認項目.費用について説明済
        insSql001Req.setCheckHiyou(dtoReq.getCheckHiyou());
        //確認項目.複数取引業者での手数料等明示済]
        insSql001Req.setCheckFukusu(dtoReq.getCheckFukusu());
        //アラート内容確認.コンプラチェックワーニング確認
        //■リクエスト.取引種別=購入（一般）または　購入（累投）　の場合
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)) {
            //■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
            if (StringUtils.equals(dtoReq.getInvitationCheck(), "1")) {
                //アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
                if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), CheckBox.OFF.key)) {
                    insSql001Req.setCheckCompWrnAlert("0");
                } else if (StringUtils.equals(dtoReq.getComplianceRankCheckConfirm(), CheckBox.ON.key)) {
                    insSql001Req.setCheckCompWrnAlert("1");
                }
                //■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
            } else if (StringUtils.equals(dtoReq.getInvitationCheck(), "2")) {
                //2
                insSql001Req.setCheckCompWrnAlert("2");
                //■上記以外
            } else {
                //0
                insSql001Req.setCheckCompWrnAlert("0");
            }
            //■上記以外
        } else {
            //NULL
            insSql001Req.setCheckCompWrnAlert(null);
        }
        //資金性格区分
        insSql001Req.setUaiQaFundCharacter(dtoReq.getFundCharacter());
        //ユーザーＩＤ
        insSql001Req.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        //仲介業者コード
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        //仲介業者営業員コード
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        //作成者
        insSql001Req.setCreateUser(IfaCommonUtil.getUserAccount().getUserId());
        //更新者
        insSql001Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
        
        try {
            dao.insertIfaDomesticMutualFundOrderConfirmSql001(insSql001Req);
        } catch (
        
        Exception e) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_FRS_PREORDEREXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }
        // レスポンス.IFA注文番号をセット
        IfaDomesticMutualFundOrderConfirmA001aResponseDto resDto = new IfaDomesticMutualFundOrderConfirmA001aResponseDto();
        resDto.setIfaOrderNo(insSql001Req.getIfaOrderNo());
        
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticMutualFundOrderConfirmA001bDtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderConfirmA001bDtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderConfirmSql001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaDomesticMutualFundOrderConfirmA001bRequestDto dtoReq) throws Exception {
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto>();
        List<IfaDomesticMutualFundOrderConfirmA001bResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderConfirmA001bResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDomesticMutualFundOrderConfirmServiceImplL.orderPlacementA001");
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //⑨   注文内容に応じて、注文発注を行う。
        FundPlaceOrder1OutData api002Out = new FundPlaceOrder1OutData();
        FundPlaceOrder2ExtOutData api003Out = new FundPlaceOrder2ExtOutData();
        //銘柄情報.ファンドタイプ=1:一般　の場合
        //投信（一般口）注文APIの呼出し。
        if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
            FundPlaceOrder1InData api002In = new FundPlaceOrder1InData();
            //トランザクションID
            api002In.setTransactionId("");
            //通番
            api002In.setCommandNum("0000000");
            //年月日
            api002In.setCommandDate("00000000");
            //部店コード3桁
            api002In.setButenCd(butenCode);
            //口座番号7桁
            api002In.setKozaNo(accountNumber);
            //アカウントID
            api002In.setAccountId("00000000000");
            //アカウント毎の連番
            api002In.setNumber("0000000");
            //オリジン
            api002In.setOrigin("0");
            //STAR-III用ファンドコード・回数（4桁）
            api002In.setKaisu(dtoReq.getFundCodeTimes());
            //STAR-III用ファンドコード・号
            api002In.setGou(dtoReq.getFundCodeIssues());
            //ﾌｧﾝﾄﾞ締切時間
            api002In.setFundClosedTime("0000");
            //売買区分
            api002In.setTradeKbn(dtoReq.getTradeKbn());
            //購入・解約の口数
            api002In.setQuantity(dtoReq.getUnit());
            //受渡方法
            api002In.setUkewHoho("1");
            //解約区分
            api002In.setKaiyakuKbn(SPACE);
            //手数料指定区分
            api002In.setComSiteiKbn("0");
            //手数料率(整数部)
            api002In.setComRate_1("00");
            //手数料率(小数部)
            api002In.setComRate_2("00");
            //乗換優遇区分
            //■取引種別=購入（累投）　または　購入（一般）　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)) {
                //リクエスト.乗換優遇区分
                api002In.setNorikaeYuguKbn(dtoReq.getNorikaeYuguKbn());
            } else {
                //"0"
                api002In.setNorikaeYuguKbn("0");
            }
            
            //スイッチング先のファンドコード（STAR-III用ファンドコードの回数のみ）
            api002In.setSwFundCd("0000");
            //決済方法区分
            api002In.setPaymentKbn(SPACE);
            //決済方法
            api002In.setPaymentMethod("          ");
            //振込先銀行区分
            api002In.setBankKbn(SPACE);
            //振込先銀行名
            api002In.setBankName("                    ");
            //受付経路区分
            api002In.setCallcenterKbn("0");
            //ユーザーＩＤ
            api002In.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
            //ベティング区分
            api002In.setVettingKbn(SPACE);
            //与信チェック用時価
            api002In.setCheckPrice("0000000000");
            //余力チェック区分
            api002In.setCheckId(SPACE);
            //マーケット発注日（締時間外）
            api002In.setExOrderDate("        ");
            //非特定預り売買区分
            api002In.setIsaKbn(dtoReq.getDepositType());
            //スイッチング先非特定預り売買区分
            api002In.setSwIsaKbn(SPACE);
            //目論見書チェック区分
            api002In.setDispatchId(dtoReq.getDispatchId());
            
            try {
                api002Out = apiwrapper.fundPlaceOrder1(api002In);
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api002Out.getShubetu(), api002Out.getCode(), api002Out.getMessage());

            } catch (Exception ae) {
                // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返却する。
                IfaDomesticMutualFundOrderConfirmSql002RequestModel updSql002Req = new IfaDomesticMutualFundOrderConfirmSql002RequestModel();
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

                try {
                    dao.updateIfaDomesticMutualFundOrderConfirmSql002b(updSql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDEREXECUTION_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED)
                    );

                    return dtoRes;
                }
            }
            //正常、エラーいずれも次の処理へ。
            
            //銘柄情報.ファンドタイプ=2:累投　の場合
            //投信（汎用累投）注文（口数売却拡張版）APIの呼出し。
        } else if (StringUtils.equals(dtoReq.getFundType(), FundType.TOTAL.key)) {
            FundPlaceOrder2ExtInData api003In = new FundPlaceOrder2ExtInData();
            //トランザクションID
            api003In.setTransactionId("");
            //通番
            api003In.setCommandNum("0000000");
            //年月日
            api003In.setCommandDate("00000000");
            //部店コード3桁
            api003In.setButenCd(butenCode);
            //口座番号7桁
            api003In.setKozaNo(accountNumber);
            //アカウントID
            api003In.setAccountId("00000000000");
            //アカウント毎の連番
            api003In.setNumber("0000000");
            //オリジン
            api003In.setOrigin("0");
            //STAR-III用ファンドコード・回数（4桁）
            api003In.setKaisu(dtoReq.getFundCodeTimes());
            //STAR-III用ファンドコード・号
            api003In.setGou(dtoReq.getFundCodeIssues());
            //ﾌｧﾝﾄﾞ締切時間
            api003In.setFundClosedTime("0000");
            //売買区分
            api003In.setTradeKbn(dtoReq.getTradeKbn());
            //購入解約方法
            //■取引種別=購入（累投）　または　
            //（取引種別=解約（累投）または　買取（累投））かつ　売却指定=金額指定　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)
                    || ((StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_TOTAL.key)
                            || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_TOTAL.key))
                            && StringUtils.equals(dtoReq.getSellDesignated(), DESIGNATE_AMOUNT))) {
                //"1"
                api003In.setBuySellMtd(BuyCancellMethod.AMOUNT.key);
                
                //■取引種別=購入（一般）　または
                //解約（一般）または
                //買取（一般）または
                //（取引種別=解約（累投）または　買取（累投））かつ　売却指定=口数指定　の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_GENERAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_GENERAL.key)
                    || ((StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCELL_TOTAL.key)
                            || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_TOTAL.key))
                            && StringUtils.equals(dtoReq.getSellDesignated(), DESIGNATE_UNIT))) {
                //"2"
                api003In.setBuySellMtd(BuyCancellMethod.UNIT.key);
                
                //■取引種別=全額買取　または
                //全額解約　の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.FULL_PURCHASE.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.FULL_CANSELL.key)) {
                //"3"
                api003In.setBuySellMtd(BuyCancellMethod.ALL.key);
            }
            
            //購入解約の金額・口数
            //■購入解約方法=口数指定　の場合
            if (StringUtils.equals(api003In.getBuySellMtd(), BuyCancellMethod.UNIT.key)) {
                //リクエスト.口数
                api003In.setPaymentQuantity(String.format("%11s", dtoReq.getUnit()).replace(" ", "0"));
                
                //■購入解約方法=金額指定　の場合
            } else if (StringUtils.equals(api003In.getBuySellMtd(), BuyCancellMethod.AMOUNT.key)) {
                //リクエスト.金額
                api003In.setPaymentQuantity(String.format("%11s", dtoReq.getAmount()).replace(" ", "0"));
                
                //■購入解約方法=ALL指定　の場合
            } else if (StringUtils.equals(api003In.getBuySellMtd(), BuyCancellMethod.ALL.key)) {
                //オール"0"
                api003In.setPaymentQuantity("00000000000");
            }
            
            //受渡方法
            api003In.setUkewHoho("1");
            //解約区分
            api003In.setKaiyakuKbn(SPACE);
            //手数料指定区分
            api003In.setComSiteiKbn("0");
            //手数料率(整数部)
            api003In.setComRate_1("00");
            //手数料率(小数部)
            api003In.setComRate_2("00");
            //乗換優遇区分
            //■取引種別=購入（累投）　または　購入（一般）　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_TOTAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)) {
                //リクエスト.乗換優遇区分
                api003In.setNorikaeYuguKbn(dtoReq.getNorikaeYuguKbn());
            } else {
                //"0"
                api003In.setNorikaeYuguKbn("0");
            }
            
            //スイッチング先のファンドコード（STAR-III用ファンドコードの回数のみ）
            api003In.setSwFundCd("0000");
            //決済方法区分
            api003In.setPaymentKbn(SPACE);
            //決済方法
            api003In.setPaymentMethod("          ");
            //振込先銀行区分
            api003In.setBankKbn(SPACE);
            //振込先銀行名
            api003In.setBankName("                    ");
            //受付経路区分
            api003In.setCallcenterKbn("0");
            //ユーザーＩＤ
            api003In.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
            //ベティング区分
            api003In.setVettingKbn(SPACE);
            //与信チェック用時価
            api003In.setCheckPrice("0000000000");
            //余力チェック区分
            api003In.setCheckId(SPACE);
            //マーケット発注日（締時間外）
            api003In.setExOrderDate("        ");
            //再投資停止指定
            api003In.setReinvest(dtoReq.getDistributionReceiveMethodWord());
            //非特定預り売買区分
            api003In.setIsaKbn(dtoReq.getDepositType());
            //スイッチング先非特定預り売買区分
            api003In.setSwIsaKbn(SPACE);
            //目論見書チェック区分
            api003In.setDispatchId(dtoReq.getDispatchId());
            //ポイント利用フラグ
            //※値がない場合は"0"
            if (Strings.isNullOrEmpty(dtoReq.getPointFlg())) {
                api003In.setPointFlg("0");
            } else {
                api003In.setPointFlg(dtoReq.getPointFlg());
            }
            //利用ポイント
            //※値がない場合はオール"0"
            api003In.setPointOrder(String.format("%8s", dtoReq.getPoint()).replace(" ", "0"));
            //ポイント種別
            //※値がない場合は"△"（内部コードは"0"）
            if (Strings.isNullOrEmpty(dtoReq.getPointType())) {
                api003In.setPointType("0");
            } else {
                api003In.setPointType(dtoReq.getPointType());
            }
            
            try {
                api003Out = apiwrapper.fundPlaceOrder2Ext(api003In);
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api003Out.getShubetu(), api003Out.getCode(), api003Out.getMessage());

            } catch (Exception ae) {
                // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返却する。
                IfaDomesticMutualFundOrderConfirmSql002RequestModel updSql002Req = new IfaDomesticMutualFundOrderConfirmSql002RequestModel();
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());

                try {
                    dao.updateIfaDomesticMutualFundOrderConfirmSql002b(updSql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDEREXECUTION_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED)
                    );

                    return dtoRes;
                }
            }
            //正常、エラーいずれも次の処理へ。
        }
        
        //⑩   発注後に注文内容をDBへ記録する。
        IfaDomesticMutualFundOrderConfirmSql002RequestModel updSql002Req = new IfaDomesticMutualFundOrderConfirmSql002RequestModel();
        // API結果を格納
        updSql002Req.setApiError(apiErrorUtil.isFatal());
        // API003リクエスト = falseで初期化
        updSql002Req.setApi003(false);
        //発注が正常の場合
        if (!apiErrorUtil.isFatal()) {
            //銘柄情報.ファンドタイプ=1:一般　の場合(API002)
            if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //商品区分
                updSql002Req.setSecurityType(api002Out.getOrderType());
                //EC受注番号
                updSql002Req.setEcOrderNo(api002Out.getOrderNum());
                //受注日
                updSql002Req.setAcceptDate(api002Out.getAcceptDate());
                //受注時刻
                updSql002Req.setOrderDayTime(api002Out.getAcceptTime());
                //種別
                updSql002Req.setShubetu(api002Out.getShubetu());
                //エラーコード
                updSql002Req.setCode(api002Out.getCode());
                //エラーメッセージ
                updSql002Req.setErrMessage(api002Out.getMessage());
                //与信チェック用時価
                updSql002Req.setEstimatePrice(api002Out.getEstimatePrice());
                //約定金額（概算）
                updSql002Req.setAmount(api002Out.getAmount());
                //手数料（概算）
                updSql002Req.setCommission(api002Out.getCommission());
                //消費税（概算）
                updSql002Req.setConsumptionTax(api002Out.getConsumptionTax());
                //譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api002Out.getCapitalGainTax());
                //精算金額（概算）
                updSql002Req.setNetAmount(api002Out.getNetAmount());
                //売却可能数量
                updSql002Req.setAcPosition(api002Out.getAcPosition());
                //注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api002Out.getAcPositionAfter());
                //買付可能金額
                updSql002Req.setAcBalance(api002Out.getAcBalance());
                //注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api002Out.getAcBalanceAfter());
                //約定予定日
                updSql002Req.setContractDate(api002Out.getTradeDate());
                //受渡予定日
                updSql002Req.setDeliveryDate(api002Out.getSettlementDate());
                //注文前のISA買付可能枠
                updSql002Req.setIsaBuyLimitBefore(api002Out.getIsaBuyLimitBefore());
                //注文後のISA買付可能枠
                updSql002Req.setIsaBuyLimitAfter(api002Out.getIsaBuyLimitAfter());
                //発注日
                updSql002Req.setOrderDate(api002Out.getOrderDate());
                //ファンド締切時刻
                updSql002Req.setFundCloseTime(api002Out.getFundClosedTime());
                //ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api002Out.getJrnisaTransferAmount());
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                
                //銘柄情報.ファンドタイプ=2:累投　の場合(API003)
            } else if (StringUtils.equals(dtoReq.getFundType(), FundType.TOTAL.key)) {
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //商品区分
                updSql002Req.setSecurityType(api003Out.getOrderType());
                //EC受注番号
                updSql002Req.setEcOrderNo(api003Out.getOrderNum());
                //受注日
                updSql002Req.setAcceptDate(api003Out.getAcceptDate());
                //受注時刻
                updSql002Req.setOrderDayTime(api003Out.getAcceptTime());
                //種別
                updSql002Req.setShubetu(api003Out.getShubetu());
                //エラーコード
                updSql002Req.setCode(api003Out.getCode());
                //エラーメッセージ
                updSql002Req.setErrMessage(api003Out.getMessage());
                //与信チェック用時価
                updSql002Req.setEstimatePrice(api003Out.getEstimatePrice());
                //約定金額（概算）
                updSql002Req.setAmount(api003Out.getAmount());
                //手数料（概算）
                updSql002Req.setCommission(api003Out.getCommission());
                //消費税（概算）
                updSql002Req.setConsumptionTax(api003Out.getConsumptionTax());
                //譲渡益税（概算）
                updSql002Req.setCapitalGainTax(api003Out.getCapitalGainTax());
                //精算金額（概算）
                updSql002Req.setNetAmount(api003Out.getNetAmount());
                //購入・解約の口数
                updSql002Req.setQuantity(api003Out.getQuantity());
                //売却可能数量
                updSql002Req.setAcPosition(api003Out.getAcPosition());
                //注文後の売却可能数量
                updSql002Req.setAcPositionAfter(api003Out.getAcPositionAfter());
                //買付可能金額
                updSql002Req.setAcBalance(api003Out.getAcBalance());
                //注文後の買付可能金額
                updSql002Req.setAcBalanceAfter(api003Out.getAcBalanceAfter());
                //約定予定日
                updSql002Req.setContractDate(api003Out.getTradeDate());
                //受渡予定日
                updSql002Req.setDeliveryDate(api003Out.getSettlementDate());
                //注文前のISA買付可能枠
                updSql002Req.setIsaBuyLimitBefore(api003Out.getIsaBuyLimitBefore());
                //注文後のISA買付可能枠
                updSql002Req.setIsaBuyLimitAfter(api003Out.getIsaBuyLimitAfter());
                //発注日
                updSql002Req.setOrderDate(api003Out.getOrderDate());
                //ファンド締切時刻
                updSql002Req.setFundCloseTime(api003Out.getFundClosedTime());
                //ジュニアNISA振替金額
                updSql002Req.setJrnisaTransferAmount(api003Out.getJrnisaTransferAmount());
                //ポイント
                updSql002Req.setPoint(api003Out.getPointFix());
                //利用後のポイント
                updSql002Req.setPointAfter(api003Out.getPointAfter());
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                // API003リクエスト = true
                updSql002Req.setApi003(true);
            }
            //発注がエラーの場合 
        } else {
            //銘柄情報.ファンドタイプ=1:一般　の場合(API002)
            if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //種別
                updSql002Req.setShubetu(api002Out.getShubetu());
                //エラーコード
                updSql002Req.setCode(api002Out.getCode());
                //エラーメッセージ
                updSql002Req.setErrMessage(api002Out.getMessage());
                //受注日
                updSql002Req.setAcceptDate(api002Out.getAcceptDate());
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                
                //銘柄情報.ファンドタイプ=2:累投　の場合(API003)
            } else if (StringUtils.equals(dtoReq.getFundType(), FundType.TOTAL.key)) {
                //IFA注文番号
                updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                //IFA注文サブ番号
                updSql002Req.setIfaOrderSubNo("1");
                //種別
                updSql002Req.setShubetu(api003Out.getShubetu());
                //エラーコード
                updSql002Req.setCode(api003Out.getCode());
                //エラーメッセージ
                updSql002Req.setErrMessage(api003Out.getMessage());
                //受注日
                updSql002Req.setAcceptDate(api003Out.getAcceptDate());
                //更新者
                updSql002Req.setUpdateUser(IfaCommonUtil.getUserAccount().getUserId());
                // API003リクエスト = true
                updSql002Req.setApi003(true);
            }
        }
        
        //DB更新OK：次の処理へ。
        //DB更新NG：DB更新エラーを格納し次の処理へ。
        Exception caughtException = null;
        try {
            dao.updateIfaDomesticMutualFundOrderConfirmSql002(updSql002Req);
        } catch (Exception e) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    WARNING_FRS_POSTORDEREXECUTION_COMPLETED,
                    IfaCommonUtil.getMessage(WARNING_FRS_POSTORDEREXECUTION_COMPLETED));
            caughtException = e;
        }
        
        // ⑥ 以下の表示を行う。
        if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
            if (apiErrorUtil.isFatal()) {
                //APIエラー：エラーを表示する。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_ORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED,
                                new String[] { api002Out.getCode(), api002Out.getMessage() }));
                return dtoRes;
            }
        }
        if (StringUtils.equals(dtoReq.getFundType(), FundType.TOTAL.key)) {
            if (apiErrorUtil.isFatal()) {
                //APIエラー：エラーを表示する。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_ORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED,
                                new String[] { api003Out.getCode(), api003Out.getMessage() }));
                return dtoRes;
            }
        }
        
        // DB更新エラー：API正常の場合、DB更新エラーを表示する。
        if (caughtException != null) {
            return dtoRes;
        }
        
        //レスポンスを返す。
        IfaDomesticMutualFundOrderConfirmA001bResponseDto resDto = new IfaDomesticMutualFundOrderConfirmA001bResponseDto();
        IfaDomesticMutualFundOrderConfirmA001aRequestDto a001aReq = new IfaDomesticMutualFundOrderConfirmA001aRequestDto();
        BeanUtils.copyProperties(a001aReq, dtoReq);
        
        //銘柄情報.ファンドタイプ=1:一般　の場合(API002)
        if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
            //EC受注番号
            resDto.setEcOrderNo(api002Out.getOrderNum());
            //種別
            resDto.setShubetu(api002Out.getShubetu());
            //エラーコード
            resDto.setCode(api002Out.getCode());
            //エラーメッセージ
            resDto.setErrMessage(api002Out.getMessage());
            //見積単価
            resDto.setQuoteUnitPrice(api002Out.getEstimatePrice());
            //約定金額
            resDto.setContractAmount(api002Out.getAmount());
            //手数料/諸費用
            resDto.setCharge(api002Out.getCommission());
            //注文後のNISA投資可能枠(YYYY年)
            resDto.setNisaInvestableQuote(api002Out.getIsaBuyLimitAfter());
            //消費税
            resDto.setConsumptionTax(api002Out.getConsumptionTax());
            //讓渡益税
            resDto.setYieldTax(api002Out.getCapitalGainTax());
            //精算金額
            resDto.setSettlementAmount(api002Out.getNetAmount());
            //約定予定日
            resDto.setContractDate(api002Out.getTradeDate());
            //受渡予定日
            resDto.setDeliveryDate(api002Out.getSettlementDate());
            //受注日
            resDto.setOrderDate(api002Out.getAcceptDate());
            //受注時刻
            resDto.setOrderDayTime(api002Out.getAcceptTime());
            //リクエスト内容
            resDto.setA001aRequest(a001aReq);
            
            //銘柄情報.ファンドタイプ=2:累投　の場合(API003)
        } else if (StringUtils.equals(dtoReq.getFundType(), FundType.TOTAL.key)) {
            //EC受注番号
            resDto.setEcOrderNo(api003Out.getOrderNum());
            //種別
            resDto.setShubetu(api003Out.getShubetu());
            //エラーコード
            resDto.setCode(api003Out.getCode());
            //エラーメッセージ
            resDto.setErrMessage(api003Out.getMessage());
            //見積単価
            resDto.setQuoteUnitPrice(api003Out.getEstimatePrice());
            //約定金額
            resDto.setContractAmount(api003Out.getAmount());
            //手数料/諸費用
            resDto.setCharge(api003Out.getCommission());
            //注文後のNISA投資可能枠(YYYY年)
            resDto.setNisaInvestableQuote(api003Out.getIsaBuyLimitAfter());
            //消費税
            resDto.setConsumptionTax(api003Out.getConsumptionTax());
            //讓渡益税
            resDto.setYieldTax(api003Out.getCapitalGainTax());
            //精算金額
            resDto.setSettlementAmount(api003Out.getNetAmount());
            //約定予定日
            resDto.setContractDate(api003Out.getTradeDate());
            //受渡予定日
            resDto.setDeliveryDate(api003Out.getSettlementDate());
            //受注日
            resDto.setOrderDate(api003Out.getAcceptDate());
            //受注時刻
            resDto.setOrderDayTime(api003Out.getAcceptTime());
            //受注数量
            resDto.setOrderQuantity(api003Out.getQuantity());
            //確定利用ポイント
            resDto.setConfirmPoint(api003Out.getPointFix());
            //リクエスト内容
            resDto.setA001aRequest(a001aReq);
        }
        resDtoList.add(resDto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, "");
        return dtoRes;
    }
}
