package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticStockOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticStockOrderConfirmService;
import com.sbisec.helios.ap.common.enums.DomesticStockTrade;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderOutData;

import io.netty.util.internal.StringUtil;

/**
 * 画面ID：SUB0202_0208-01_3
 * 画面名：国内株式注文確認
 *
 * @author
 */
@Component(value = "cmpIfaDomesticStockOrderConfirmService")
public class IfaDomesticStockOrderConfirmServiceImpL implements IfaDomesticStockOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderConfirmServiceImpL.class);
    
    @Autowired
    private IfaDomesticStockOrderConfirmDao dao;
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct027 fct027;
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 媒介不可エラー */
    public static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 取引停止口座エラー */
    public static final String ERRORS_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    public static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    public static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    public static final String ERRORS_INFORMATIONOCCURS = "errors.cmn.information.occurs";
    
    public static final String ERRORS_ORDEREXECUTION = "errors.cmn.orderExecution.failed";
    
    /** 取引停止口座 */
    private static final String STOPTRADEACCOUNT = "1";
    
    /** 権限あり */
    private static final String AUTHORIZED = "1";
    
    /**媒介可否 */
    private static final String MEDIATEPROPRIETY = "1";
    
    /** 国コード*/
    private static final String COUNTRYCD = "99";
    
    /** 証券金銭種別 */
    private static final String TRADECD = "01";
    
    /**通貨コード */
    private static final String CURRENCYCODE = "999";
    
    /** 注意情報エラー有無 */
    private static final String NOTEINFOERRFLAG = "1";
    
    /**お知らせエラー有無 */
    private static final String NOTELIMITERRFLAG = "1";
    
    /** OFF */
    private static final String OFF = "0";
    
    /** 国内外国区分 */
    private static final String KOKUNAIGAIKBN = "0";
    
    /** 国内外国区分 */
    private static final String SECURITYTYPE = "1 ";
    
    /** コンプラチェック種類 */
    private static final String COMPLACHECKKIND = "1";
    
    /** 判定結果 */
    private static final String JUDGEMENTRESULT0 = "0";
    
    private static final String JUDGEMENTRESULT1 = "1";
    
    private static final String JUDGEMENTRESULT2 = "2";
    
    private static final String REGKBN = "1";
    
    /** API001 取得件数が0件の場合のメッセージ   */
    public static final String INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 注文発注後の注文データが更新できませんでした。注文は完了しています。*/
    private static final String WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** 区分定義.ドメインID_契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** APIタイプEC-GW */
    private static final String API_TYPE_ECGW = "EC-GW";
    
    private enum OrderKind {
        
        // 通常/逆指値
        NORMALPRICE_LIMITREVERSE("1"),
        // OCO
        OCO("2"),
        // IFD
        IFD("3"),
        // IFDOCO
        IFDOCO("4");
        
        private String key;
        
        private OrderKind(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderConfirmA001RequestDto
     * Dto レスポンス：IfaDomesticStockOrderConfirmA001ResponseDto
     * model リクエスト：IfaDomesticStockOrderConfirmSql002RequestModel
     * model レスポンス：IfaDomesticStockOrderConfirmSql002ResponseModel
     *
     * @param dtoA001aReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaDomesticStockOrderConfirmA001aRequestDto dtoA001aReq) throws Exception {
        
        DataList<IfaDomesticStockOrderConfirmA001aResponseDto> dtoRes = new DataList<IfaDomesticStockOrderConfirmA001aResponseDto>();
        List<IfaDomesticStockOrderConfirmA001aResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderConfirmA001aResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderConfirmServiceImplL.orderPlacementA001a");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 顧客共通情報.部店コードをセット
        fct001Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct001Req.setAccountNumber(cc.getAccountNumber());
        
        //  ①   利用者の口座に対する権限チェックを行う。
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無==1(権限あり)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), AUTHORIZED)) {
            // 顧客情報を格納し次の処理へ
        } else {
            // 利用者の口座に対する権限チェックを行う。
            // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS, IfaCommonUtil
                    .getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // FCT001.取引停止フラグ==1（取引停止口座）の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOPTRADEACCOUNT)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ⓶口座の契約締結前交付書面コードのチェック　および共同募集顧客(共募顧客)のチェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 顧客共通情報.部店コードをセット
        fct003Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別をセット
        fct003Req.setProductCd(TRADECD);
        // リクエストパラメータ.取引種別をセット
        fct003Req.setTradeCd(dtoA001aReq.getTradeCd());
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        
        //②取引コース媒介可否チェックを行う。
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), MEDIATEPROPRIETY)) {
            //取引可：次の処理へ。      
        } else {
            //上記以外：取引不可エラーを返す。
            String codeName = codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "2", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE, IfaCommonUtil
                            .getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE, new String[] { codeName }));
            return dtoRes;
        }
        
        //③口座の取引制限チェックを行う。
        InputFct021Dto fct021Req = new InputFct021Dto();
        //顧客共通情報.部店コードをセット
        fct021Req.setButenCode(cc.getButenCode());
        //顧客共通情報.口座番号をセット
        fct021Req.setAccountNumber(cc.getAccountNumber());
        //証券金銭種別をセット
        fct021Req.setProductCd(TRADECD);
        //取引種別をセット
        fct021Req.setTradeCd(dtoA001aReq.getTradeCd());
        //国籍コードをセット
        fct021Req.setCountryCd(COUNTRYCD);
        //通貨コードをセット
        fct021Req.setCurrencyCode(CURRENCYCODE);
        //選択市場をセット
        fct021Req.setTradeRestrictChkMarket(dtoA001aReq.getMarket());
        
        OutputFct021Dto fct021Res = fct021.doCheck(fct021Req);
        
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), NOTEINFOERRFLAG)) {
            String codeName = codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "2", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_NOTICEERRORCHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] { codeName }));
            return dtoRes;
            
        } else if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), NOTELIMITERRFLAG)) {
            
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONCHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
            return dtoRes;
            
        } else if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), NOTELIMITERRFLAG)
                && (StringUtils.equals(dtoA001aReq.getNoticeInfoAlertConfirm(), OFF)
                        || StringUtil.isNullOrEmpty(dtoA001aReq.getNoticeInfoAlertConfirm()))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONOCCURS,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONOCCURS));
            return dtoRes;
            
        } else if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), NOTELIMITERRFLAG)
                && (StringUtils.equals(dtoA001aReq.getNoticeAlertConfirm(), OFF)
                        || StringUtil.isNullOrEmpty(dtoA001aReq.getNoticeAlertConfirm()))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONOCCURS,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONOCCURS));
            return dtoRes;
        }
        
        //④現物買付の場合、コンプラランクチェックを行う。
        if (StringUtils.equals(dtoA001aReq.getTradeCd(), DomesticStockTrade.STOCK_BUY.getId())) {
            InputFct006Dto fct006Req = new InputFct006Dto();
            
            //顧客共通情報.部店コードをセット
            fct006Req.setButenCode(cc.getButenCode());
            //顧客共通情報.口座番号をセット
            fct006Req.setAccountNumber(cc.getAccountNumber());
            //国内外国区分をセット
            fct006Req.setBrDomesticFgnInd(KOKUNAIGAIKBN);
            //商品区分をセット
            fct006Req.setBrBrandInd(SECURITYTYPE);
            // 銘柄コード１
            fct006Req.setBrandCode1(dtoA001aReq.getBrandCode());
            // 勧誘区分
            fct006Req.setInvitationType(dtoA001aReq.getKanyuKbn());
            // 受注方法
            fct006Req.setOrderMethod(dtoA001aReq.getOrdersHouhou());
            // コンプラチェック種類
            fct006Req.setComplaCheckKind(COMPLACHECKKIND);
            
            OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);
            
            //④現物買付の場合、コンプラランクチェックを行う。
            if (StringUtils.equals(fct006Res.getJudgementResult(), JUDGEMENTRESULT0)) {
                //FCT006.判定結果=0：ノーマル：次の処理へ
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JUDGEMENTRESULT1)) {
                //FCT006.判定結果=1：アラート
                if ((StringUtils.equals(fct006Res.getChkBoxLabel(), dtoA001aReq.getCheckBoxWording())
                        && StringUtils.equals(dtoA001aReq.getConfirm(), OFF))
                        || !StringUtils.equals(fct006Res.getChkBoxLabel(), dtoA001aReq.getCheckBoxWording())) {
                    // FTC006.コンプラランクチェック.チェックボックス文言と同じチェックボックスがOFF　または　非表示：エラーを返す。
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONOCCURS,
                            IfaCommonUtil.getMessage(ERRORS_INFORMATIONOCCURS));
                    return dtoRes;
                }
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JUDGEMENTRESULT2)) {
                
                //FCT006.判定結果=2：エラー：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getMessageId(),
                        IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                return dtoRes;
            } else {
                
                //FCT006.判定結果=上記以外：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getReturnCode(),
                        fct006Res.getErrMessage());
                return dtoRes;
            }
        }
        
        //⑤銘柄の取引注意情報を取得する。
        InputFct027Dto fct027Req = new InputFct027Dto();
        //銘柄コードをセット
        fct027Req.setBrandCode(dtoA001aReq.getBrandCode());
        
        OutputFct027Dto fct027Res = fct027.getData(fct027Req);
        
        // 取引注意情報（銘柄）確認チェックボックス=OFF　または　非表示：エラーを返す。
        if (StringUtils.equals(fct027Res.getRegKbn(), REGKBN)) {
            if (StringUtils.equals(dtoA001aReq.getTradeNoticeInfoConfirm(), OFF)
                    || StringUtil.isNullOrEmpty(dtoA001aReq.getTradeNoticeInfoConfirm())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_INFORMATIONOCCURS,
                        IfaCommonUtil.getMessage(ERRORS_INFORMATIONOCCURS));
                return dtoRes;
            }
        }
        
        //⑥発注前に注文内容を国内株式注文テーブルへ記録する。
        IfaDomesticStockOrderConfirmSql001RequestModel insSql001Req = new IfaDomesticStockOrderConfirmSql001RequestModel();
        
        //部店コード
        insSql001Req.setButenCode(cc.getButenCode());
        //口座番号
        insSql001Req.setAccountNumber(cc.getAccountNumber());
        //顧客ID
        insSql001Req.setKokyakuId(cc.getCustomerCode());
        //特定口座区分
        //■リクエスト.口座=総合口座　の場合
        if (StringUtils.equals(dtoA001aReq.getAccount(), " ")) {
            //顧客共通情報.特定口座区分
            insSql001Req.setTokuteiKouzaKbn(cc.getSpecificAccountType());
            //■リクエスト.口座=ジュニアNISA口座　の場合
        } else if (StringUtils.equals(dtoA001aReq.getAccount(), "1")) {
            //顧客共通情報.ジュニア特定口座区分
            insSql001Req.setTokuteiKouzaKbn(cc.getJrTokuteiKouzaKbn());
        }
        //銘柄コード
        insSql001Req.setBrandCode(dtoA001aReq.getBrandCode());
        //市場
        insSql001Req.setMarket(dtoA001aReq.getMarket());
        //注文状況
        insSql001Req.setOrderStatus("0");
        //取引種別
        insSql001Req.setTradeCd(dtoA001aReq.getTradeCd());
        //数量
        insSql001Req.setQuantity(dtoA001aReq.getQuantity());
        //有効期限
        //■期間.期間条件=当日中の場合
        if (StringUtils.equals(dtoA001aReq.getPeriodTerms(), "0")) {
            //"△△△△△△△△"
            insSql001Req.setYukoKigen("        ");
            //■上記以外
        } else {
            //期間.日付　YYYYMMDD形式
            insSql001Req.setYukoKigen(DateFormatUtil.dateFormatToYmdNoSign(dtoA001aReq.getLimit()));
        }
        //預り区分
        insSql001Req.setDepositType(dtoA001aReq.getDepositType());
        //弁済期限
        insSql001Req.setPaymentDeadline(null);
        //返済方法
        insSql001Req.setRepayWay(null);
        //返済順序
        insSql001Req.setRepayOrder(null);
        //注文種別
        insSql001Req.setOrderKind(dtoA001aReq.getOrderKind());
        //注文種別（一覧）
        //■リクエスト.注文種別=通常/逆指値　の場合
        if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
            //■リクエスト.通常/逆指値.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "3")) {
                //1:逆指値注文
                insSql001Req.setOrderClassification("1");
                //■上記以外
            } else {
                //0:通常注文
                insSql001Req.setOrderClassification("0");
            }
            //■リクエスト.注文種別=OCO　の場合
        } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
            //2:OCO注文
            insSql001Req.setOrderClassification("2");
            //■リクエスト.注文種別=IFD　または　IFDOCOの場合
        } else if ((StringUtils.equals(dtoA001aReq.getOrderKind(), "3"))
                || (StringUtils.equals(dtoA001aReq.getOrderKind(), "4"))) {
            //■リクエスト.IFD1.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "3")) {
                //4:IFD1注文（逆指値）
                insSql001Req.setOrderClassification("4");
                //■上記以外
            } else {
                //3:IFD1注文（通常）
                insSql001Req.setOrderClassification("3");
            }
        }
        //勧誘区分
        insSql001Req.setKanyuKbn(dtoA001aReq.getKanyuKbn());
        //受注方法
        insSql001Req.setOrdersHouhou(dtoA001aReq.getOrdersHouhou());
        //確認項目.インサイダー確認
        insSql001Req.setInsiderCheck(dtoA001aReq.getCheckInsider());
        //確認項目.SOR確認
        insSql001Req.setSorCheck(dtoA001aReq.getCheckSor());
        //アラート内容確認.コンプラチェックワーニング確認
        //■リクエスト.取引種別=現物買付　の場合
        if (StringUtils.equals(dtoA001aReq.getTradeCd(), "1")) {
            //■コンプラランクチェック.チェックボックス文言=△・◇ワーニング申請は「申請・承認済」であることを確認済　の場合
            if (StringUtils.equals(dtoA001aReq.getCheckBoxWording(), "1")) {
                //アラート内容確認.コンプラランクチェック確認　チェックボックスOFF:0、ON:1
                insSql001Req.setComplaCheckWarning(dtoA001aReq.getConfirm());
                
                //■コンプラランクチェック.チェックボックス文言=勧誘なし　の場合
            } else if (StringUtils.equals(dtoA001aReq.getCheckBoxWording(), "2")) {
                //2
                insSql001Req.setComplaCheckWarning("2");
                //■上記以外
            } else {
                //0
                insSql001Req.setComplaCheckWarning("0");
            }
            //■上記以外
        } else {
            //NULL
            insSql001Req.setComplaCheckWarning(null);
        }
        //資金性格区分
        insSql001Req.setUaiQaFundCharacter(dtoA001aReq.getComplianceCheckUseFundCharacter());
        //ユーザーＩＤ
        UserAccount ua = IfaCommonUtil.getUserAccount();
        insSql001Req.setUserId(ua.getMedUsers().getCcsUserId());
        //取消ユーザーID
        insSql001Req.setTorikeshiUserId(null);
        //手数料区分
        insSql001Req.setTesuuryouKbn(
                codeListService.convertKeyToExtKey(PRE_CONTRACT_DOC_CODE, API_TYPE_ECGW, cc.getCustomerAttribute()));
        //訂正区分
        insSql001Req.setTeiseiKbn(null);
        
        if ((StringUtils.equals(dtoA001aReq.getOrderKind(), "1"))
                || (StringUtils.equals(dtoA001aReq.getOrderKind(), "2"))) {
            //自動注文種別
            insSql001Req.setAutomaticOrderClassification(null);
            //RBE注文種別
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=指値　または　成行の場合
                if ((StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "1"))
                        || (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "2"))) {
                    //ALL"△"：通常注文
                    insSql001Req.setRbeChumonShubetsu("   ");
                }
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "3")) {
                    //"SLO"：逆指値注文
                    insSql001Req.setRbeChumonShubetsu("SLO");
                }
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //"OCO"：OCO注文
                insSql001Req.setRbeChumonShubetsu("OCO");
            }
            
            //指成区分
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //通常/逆指値.執行条件
                insSql001Req.setSashinariKbn(dtoA001aReq.getSasinariJyouken());
                //■注文種別=OCOの場合    
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //OCO1.執行条件
                insSql001Req.setSashinariKbn(dtoA001aReq.getOco1SasinariJyouken());
            }
            
            //指値
            //■注文種別=通常/逆指値の場合
            insSql001Req.setSashine("0000000000");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=指値　の場合
                if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "1")) {
                    //通常/逆指値.注文単価（指値)
                    insSql001Req.setSashine(dtoA001aReq.getPrice());
                    //■通常/逆指値.執行方法=逆指値　の場合    
                } else if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "3")) {
                    //■通常/逆指値.執行方法（逆指値）=指値　の場合
                    if (StringUtils.equals(dtoA001aReq.getGyakusasiHouhou(), "1")) {
                        //通常/逆指値.注文単価（逆指値/指値）
                        insSql001Req.setSashine(dtoA001aReq.getPrice());
                    }
                }
                //■注文種別=OCOの場合    
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //OCO1.注文単価
                insSql001Req.setSashine(dtoA001aReq.getOco1Price());
            }
            
            //トリガ発動ゾーン
            //■注文種別=通常/逆指値の場合
            insSql001Req.setTriggerZone(" ");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "3")) {
                    //■取引種別=現物買付
                    if (StringUtils.equals(dtoA001aReq.getTradeCd(), "1")) {
                        //"0"：以上
                        insSql001Req.setTriggerZone("0");
                        //■取引種別=現物売却
                    } else if (StringUtils.equals(dtoA001aReq.getTradeCd(), "2")) {
                        //"1"：以下
                        insSql001Req.setTriggerZone("1");
                    }
                }
                // ■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //■取引種別=現物買付
                if (StringUtils.equals(dtoA001aReq.getTradeCd(), "1")) {
                    //"0"：以上
                    insSql001Req.setTriggerZone("0");
                    //■取引種別=現物売却
                } else if (StringUtils.equals(dtoA001aReq.getTradeCd(), "2")) {
                    //"1"：以下
                    insSql001Req.setTriggerZone("1");
                }
            }
            
            //トリガ値段
            //■注文種別=通常/逆指値の場合
            insSql001Req.setTriggerNedan("0000000000");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001aReq.getSasinariHouhou(), "3")) {
                    //通常/逆指値.発火条件価格（逆指値）
                    insSql001Req.setTriggerNedan(dtoA001aReq.getTriggerPrice());
                }
                // ■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //OCO2.発火条件価格（逆指値）
                insSql001Req.setTriggerNedan(dtoA001aReq.getOco2TriggerPrice());
            }
            
            //OCO指成区分
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //"△"
                insSql001Req.setOcoSasinariKbn(" ");
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //OCO2.執行条件（逆指値）
                insSql001Req.setOcoSasinariKbn(dtoA001aReq.getOco2GyakusasiJyouken());
            }
            
            //OCO指値
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "1")) {
                //API入力値が△の場合はnull
                insSql001Req.setOcoSashine(null);
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "2")) {
                //OCO2.注文単価
                insSql001Req.setOcoSashine(dtoA001aReq.getOco2Price());
            }
            
            //DONE指成区分
            insSql001Req.setDoneSasinariKbn(null);
            //DONE指値
            insSql001Req.setDoneSashine(null);
            //DONERBE注文種別
            insSql001Req.setDoneRbeOrderKind(null);
            //DONEトリガ発動ゾーン
            insSql001Req.setDoneTriggerZone(null);
            //DONEトリガ値段
            insSql001Req.setDoneTriggerNedan(null);
            //DONEOCO指成区分
            insSql001Req.setDoneOcoSasinariKbn(null);
            //DONEOCO指値
            insSql001Req.setDoneOcoSashine(null);
            //DONE有効期限
            insSql001Req.setDoneYuukouKigen(null);
            
            //譲渡益税区分
            //■取引種別=現物買付
            if (StringUtils.equals(dtoA001aReq.getTradeCd(), "1")) {
                //"△"
                insSql001Req.setJoutoekizeiKbn(" ");
                //■取引種別=現物売却
            } else if (StringUtils.equals(dtoA001aReq.getTradeCd(), "2")) {
                //"2"：申告分離
                insSql001Req.setJoutoekizeiKbn("2");
            }
            
        } else if ((StringUtils.equals(dtoA001aReq.getOrderKind(), "3"))
                || (StringUtils.equals(dtoA001aReq.getOrderKind(), "4"))) {
            //自動注文種別
            insSql001Req.setAutomaticOrderClassification("IF  ");
            //RBE注文種別
            //■IFD1.執行方法=指値　または　成行の場合
            if ((StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "1"))
                    || (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "2"))) {
                //ALL"△"：通常注文
                insSql001Req.setRbeChumonShubetsu("   ");
                //■IFD1.執行方法=逆指値　の場合
            } else if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "3")) {
                //"SLO"：逆指値注文
                insSql001Req.setRbeChumonShubetsu("SLO");
            }
            
            //指成区分
            insSql001Req.setSashinariKbn(dtoA001aReq.getIfd1SasinariJyouken());
            
            //指値
            //■IFD1.執行方法=指値　の場合
            insSql001Req.setSashine("0000000000");
            if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "1")) {
                //IFD1.注文単価（指値）
                insSql001Req.setSashine(dtoA001aReq.getIfd1Price());
                //■IFD1.執行方法=逆指値　の場合
            } else if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "3")) {
                //■IFD1.執行方法（逆指値）=指値　の場合
                if (StringUtils.equals(dtoA001aReq.getIfd1GyakusasiHouhou(), "1")) {
                    //IFD1.注文単価（逆指値/指値）
                    insSql001Req.setSashine(dtoA001aReq.getIfd1Price());
                }
            }
            
            //トリガ発動ゾーン
            //■IFD1.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "3")) {
                //"0"：以上
                insSql001Req.setTriggerZone("0");
                //■上記以外
            } else {
                //"△"：指定なし
                insSql001Req.setTriggerZone(" ");
            }
            
            //トリガ値段
            //■IFD1.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001aReq.getIfd1SasinariHouhou(), "3")) {
                //IFD1.発火条件価格（逆指値）
                insSql001Req.setTriggerNedan(dtoA001aReq.getIfd1TriggerPrice());
                //■上記以外
            } else {
                //"0000000000"
                insSql001Req.setTriggerNedan("0000000000");
            }
            
            //OCO指成区分
            insSql001Req.setOcoSasinariKbn(" ");
            //OCO指値
            insSql001Req.setOcoSashine(null);
            
            //DONE指成区分
            //■注文種別=IFDの場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "3")) {
                //IFD2.執行条件
                insSql001Req.setDoneSasinariKbn(dtoA001aReq.getIfd2SasinariJyouken());
                //■注文種別=IFDOCO
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //OCO1.執行条件
                insSql001Req.setDoneSasinariKbn(dtoA001aReq.getOco1SasinariJyouken());
            }
            
            //DONE指値
            //■注文種別=IFDの場合
            insSql001Req.setDoneSashine("0000000000");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=指値　の場合
                if (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "1")) {
                    //IFD2.注文単価
                    insSql001Req.setDoneSashine(dtoA001aReq.getIfd2Price());
                    //■IFD2.執行方法=逆指値　の場合
                } else if (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "3")) {
                    //■IFD2.執行方法（逆指値）=指値　の場合
                    if (StringUtils.equals(dtoA001aReq.getIfd2GyakusasiHouhou(), "1")) {
                        //IFD2.注文単価
                        insSql001Req.setDoneSashine(dtoA001aReq.getIfd2Price());
                    }
                }
                //■注文種別=IFDOCO
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //■OCO1執行方法=指値　の場合
                if (StringUtils.equals(dtoA001aReq.getOco1SasinariHouhou(), "1")) {
                    //OCO1.注文単価
                    insSql001Req.setDoneSashine(dtoA001aReq.getOco1Price());
                }
            }
            
            //DONERBE注文種別
            //■注文種別=IFDの場合
            insSql001Req.setDoneRbeOrderKind("   ");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=指値　または　成行の場合
                if ((StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "1"))
                        || (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "2"))) {
                    //ALL"△"：通常注文
                    insSql001Req.setDoneRbeOrderKind("   ");
                    //■IFD2.執行方法=逆指値　の場合
                } else if (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "3")) {
                    //"SLO"：逆指値注文
                    insSql001Req.setDoneRbeOrderKind("SLO");
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //"OCO"：DONE注文がOCO注文
                insSql001Req.setDoneRbeOrderKind("OCO");
            }
            
            //DONEトリガ発動ゾーン
            //■注文種別=IFDの場合
            insSql001Req.setDoneTriggerZone(" ");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "3")) {
                    //"1"：以下
                    insSql001Req.setDoneTriggerZone("1");
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //"1"：以下
                insSql001Req.setDoneTriggerZone("1");
            }
            
            //DONEトリガ値段
            //■注文種別=IFDの場合
            insSql001Req.setDoneTriggerNedan("0000000000");
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001aReq.getIfd2SasinariHouhou(), "3")) {
                    //IFD2.発火条件価格（逆指値）
                    insSql001Req.setDoneTriggerNedan(dtoA001aReq.getIfd2TriggerPrice());
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //OCO2.発火条件価格（逆指値）
                insSql001Req.setDoneTriggerNedan(dtoA001aReq.getOco2TriggerPrice());
            }
            
            //DONEOCO指成区分
            //■注文種別=IFDOCOの場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //OCO2.執行条件
                insSql001Req.setDoneOcoSasinariKbn(dtoA001aReq.getOco2GyakusasiJyouken());
                //■上記以外
            } else {
                //"△"
                insSql001Req.setDoneOcoSasinariKbn(" ");
            }
            
            //DONEOCO指値
            //■注文種別=IFDOCOの場合
            if (StringUtils.equals(dtoA001aReq.getOrderKind(), "4")) {
                //OCO2.注文単価
                insSql001Req.setDoneOcoSashine(dtoA001aReq.getOco2Price());
                //■上記以外
            } else {
                //"0000000000"
                insSql001Req.setDoneOcoSashine("0000000000");
            }
            
            //DONE有効期限
            //■IFD2.期間.期間条件=当日中の場合
            if (StringUtils.equals(dtoA001aReq.getIfd2PeriodTerms(), "0")) {
                //"△△△△△△△△"
                insSql001Req.setDoneYuukouKigen("        ");
                //■上記以外
            } else {
                //IFD2.期間.日付　YYYYMMDD形式
                insSql001Req.setDoneYuukouKigen(DateFormatUtil.dateFormatToYmdNoSign(dtoA001aReq.getIfd2Limit()));
            }
            
            //譲渡益税区分
            //■取引種別=現物買付
            if (StringUtils.equals(dtoA001aReq.getTradeCd(), "1")) {
                //"△"
                insSql001Req.setJoutoekizeiKbn(" ");
                //■取引種別=現物売却
            } else if (StringUtils.equals(dtoA001aReq.getTradeCd(), "2")) {
                //"2"：申告分離
                insSql001Req.setJoutoekizeiKbn("2");
            }
            
        }
        
        //EC受注番号訂正時
        insSql001Req.setEcWhentheOrderNumbercorrected(null);
        //RBE注文ステータス
        insSql001Req.setRebOrderStatus(null);
        //商品区分
        insSql001Req.setSecurityType(null);
        //EC受注番号
        insSql001Req.setEcOrderNo(null);
        //受注日
        insSql001Req.setOrderDate(null);
        //受注時刻
        insSql001Req.setOrderTime(null);
        //種別
        insSql001Req.setShubetu(null);
        //エラーコード
        insSql001Req.setCode(null);
        //エラーメッセージ
        insSql001Req.setErrMessage(null);
        //与信チェック用時価
        insSql001Req.setEstimatePrice(null);
        //約定金額（概算）
        insSql001Req.setAmount(null);
        //手数料（概算）
        insSql001Req.setCommission(null);
        //消費税（概算）
        insSql001Req.setConsumptionTax(null);
        //譲渡益税（概算）
        insSql001Req.setCapitalGainTax(null);
        //精算金額（概算）
        insSql001Req.setNetAmount(null);
        //諸経費
        insSql001Req.setCost(null);
        //約定予定日
        insSql001Req.setContractDate(null);
        //受渡予定日
        insSql001Req.setDeliveryDate(null);
        //受付有効期限
        insSql001Req.setAcceptLimit(null);
        //DONE 受付有効期限
        insSql001Req.setDoneAcceptLimit(null);
        //手数料区分（採用）
        insSql001Req.setComIdR(null);
        //売却可能数量
        insSql001Req.setAcPosition(null);
        //注文後の売却可能数量
        insSql001Req.setAcPositionAfter(null);
        //買付可能金額
        insSql001Req.setAcBalance(null);
        //注文後の買付可能金額
        insSql001Req.setAcBalanceAfter(null);
        //注文入力市場
        insSql001Req.setOrderedMarket(null);
        //取引不足額
        insSql001Req.setTradeDeficitAmount(null);
        //ISA買付可能枠
        insSql001Req.setIsaBuyLimit(null);
        //ジュニアNISA振替金額
        insSql001Req.setJrnisaTransferAmount(null);
        //SOR連携区分
        insSql001Req.setSorLinkKbn(null);
        //決済可能数量
        insSql001Req.setUnclosedQuantity(null);
        //注文後の決済可能数量
        insSql001Req.setUnclosedQuantityAfter(null);
        //建玉余力
        insSql001Req.setMarginCapability(null);
        //注文後の建玉余力
        insSql001Req.setMarginCapabilityAfter(null);
        //維持率
        insSql001Req.setRetentionRate(null);
        //注文後維持率
        insSql001Req.setActualGrntRateAfter(null);
        //適用金利
        insSql001Req.setApplicableInterestRate(null);
        //適用貸株料
        insSql001Req.setApplicableStockLendingFees(null);
        //プレミアム料
        insSql001Req.setPremium(null);
        //Ｔポイント
        insSql001Req.setTpoint(null);
        //利用後のＴポイント
        insSql001Req.setTpointAfter(null);
        //仲介業者コード
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        //仲介業者営業員コード
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        //作成者
        insSql001Req.setCreateUser(ua.getUserId());
        //更新者
        insSql001Req.setUpdatedBy(ua.getUserId());
        //CCS送付日
        insSql001Req.setCssDateofdelivery(null);
        
        try {
            dao.insertIfaDomesticStockOrderConfirmSql001(insSql001Req);
        } catch (Exception e) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_FRS_PREORDEREXECUTION_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }
        
        IfaDomesticStockOrderConfirmA001aResponseDto resA001aDto = new IfaDomesticStockOrderConfirmA001aResponseDto();
        // レスポンス.IFA注文番号をセット
        resA001aDto.setIfaOrderNo(insSql001Req.getIfaOrderNo());
        
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resA001aDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderConfirmA001bRequestDto
     * Dto レスポンス：IfaDomesticStockOrderConfirmA001bResponseDto
     * model リクエスト：IfaDomesticStockOrderConfirmSql002RequestModel
     * model レスポンス：IfaDomesticStockOrderConfirmSql002ResponseModel
     *
     * @param dtoA001bReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaDomesticStockOrderConfirmA001bRequestDto dtoA001bReq) throws Exception {
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaDomesticStockOrderConfirmA001bResponseDto> dtoRes = new DataList<IfaDomesticStockOrderConfirmA001bResponseDto>();
        List<IfaDomesticStockOrderConfirmA001bResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderConfirmA001bResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderConfirmServiceImplL.orderPlacementA001b");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //⑦ 注文種別に応じて、注文確認を行う。
        StockPlaceOrderOutData api001Res = new StockPlaceOrderOutData();
        StockPlaceOrderAutoOutData api002Res = new StockPlaceOrderAutoOutData();
        String apiError = "";
        if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.OCO.key)) {
            
            //通常/逆指値、OCO注文：国内株式注文APIの呼出し。
            StockPlaceOrderInData stockPlaceOrderInData = new StockPlaceOrderInData();
            //トランザクションID
            stockPlaceOrderInData.setTransactionId("00000000000000000000000000000000");
            //通番
            stockPlaceOrderInData.setCommandNum("0000000");
            //年月日
            stockPlaceOrderInData.setCommandDate("00000000");
            //部店コード3桁
            stockPlaceOrderInData.setButenCd(cc.getButenCode());
            //口座番号7桁
            stockPlaceOrderInData.setKozaNo(cc.getAccountNumber());
            //アカウントID
            stockPlaceOrderInData.setAccountId("00000000000");
            //アカウント毎の連番
            stockPlaceOrderInData.setNumber("0000000");
            //オリジン
            stockPlaceOrderInData.setOrigin("0");
            //銘柄コード
            stockPlaceOrderInData.setBrandCd(dtoA001bReq.getBrandCode());
            //売買区分
            stockPlaceOrderInData.setTradeKbn(dtoA001bReq.getTradeCd());
            //注文区分
            stockPlaceOrderInData.setQuantity(dtoA001bReq.getQuantity());
            
            //指成区分
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //通常/逆指値.執行条件
                stockPlaceOrderInData.setSasinariKbn(dtoA001bReq.getSasinariJyouken());
                //■注文種別=OCOの場合    
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //OCO1.執行条件
                stockPlaceOrderInData.setSasinariKbn(dtoA001bReq.getOco1SasinariJyouken());
            }
            
            //指値
            //■注文種別=通常/逆指値の場合
            stockPlaceOrderInData.setPrice("0000000000");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=指値　の場合
                if (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "1")) {
                    //通常/逆指値.注文単価（指値)
                    stockPlaceOrderInData.setPrice(dtoA001bReq.getPrice());
                    //■通常/逆指値.執行方法=逆指値　の場合    
                } else if (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "3")) {
                    //■通常/逆指値.執行方法（逆指値）=指値　の場合
                    if (StringUtils.equals(dtoA001bReq.getGyakusasiHouhou(), "1")) {
                        //通常/逆指値.注文単価（逆指値/指値）
                        stockPlaceOrderInData.setPrice(dtoA001bReq.getPrice());
                    }
                }
                //■注文種別=OCOの場合    
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //OCO1.注文単価
                stockPlaceOrderInData.setPrice(dtoA001bReq.getOco1Price());
            }
            
            //受渡方法
            //■取引種別=現物買付
            if (StringUtils.equals(dtoA001bReq.getTradeCd(), "1")) {
                //"1"：保護預り
                stockPlaceOrderInData.setUkewHoho("1");
                //■取引種別=現物売却
            } else if (StringUtils.equals(dtoA001bReq.getTradeCd(), "2")) {
                //"1"：預り
                stockPlaceOrderInData.setUkewHoho("1");
            }
            
            //市場
            stockPlaceOrderInData.setMarket(dtoA001bReq.getMarket());
            //譲渡益税区分
            //■取引種別=現物買付
            if (StringUtils.equals(dtoA001bReq.getTradeCd(), "1")) {
                //"△"
                stockPlaceOrderInData.setJoZeiKbn(" ");
                //■取引種別=現物売却
            } else if (StringUtils.equals(dtoA001bReq.getTradeCd(), "2")) {
                //"2"：申告分離
                stockPlaceOrderInData.setJoZeiKbn("2");
            }
            
            //非特定預り売買区分
            stockPlaceOrderInData.setHitokuteiTradeKbn(dtoA001bReq.getDepositType());
            //弁済期限
            stockPlaceOrderInData.setPaymentLimit(" ");
            
            //有効期限
            //■期間.期間条件=当日中の場合
            if (StringUtils.equals(dtoA001bReq.getPeriodTerms(), "0")) {
                //"△△△△△△△△"
                stockPlaceOrderInData.setLimit("        ");
                //■上記以外
            } else {
                //期間.日付　YYYYMMDD形式
                stockPlaceOrderInData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoA001bReq.getLimit()));
            }
            
            //摘要
            stockPlaceOrderInData.setSummary("                              ");
            //決済方法区分
            stockPlaceOrderInData.setPaymentKbn(" ");
            //決済方法
            stockPlaceOrderInData.setPaymentMethod("          ");
            //振込先銀行区分
            stockPlaceOrderInData.setBankKbn(" ");
            //振込先銀行名
            stockPlaceOrderInData.setBankName("                    ");
            //受付経路区分
            stockPlaceOrderInData.setCallcenterKbn("0");
            //ユーザーID
            stockPlaceOrderInData.setUserId(ua.getMedUsers().getCcsUserId());
            //ベティング区分
            stockPlaceOrderInData.setVettingKbn(" ");
            //与信チェック用時価
            stockPlaceOrderInData.setCheckPrice("          ");
            //手数料区分
            stockPlaceOrderInData.setComId(cc.getCustomerAttribute());
            //余力チェック区分
            stockPlaceOrderInData.setCheckId(" ");
            
            //RBE注文種別
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=指値　または　成行の場合
                if ((StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "1"))
                        || (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "2"))) {
                    //ALL"△"：通常注文
                    stockPlaceOrderInData.setRbeOrderKind("   ");
                }
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "3")) {
                    //"SLO"：逆指値注文
                    stockPlaceOrderInData.setRbeOrderKind("SLO");
                }
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //"OCO"：OCO注文
                stockPlaceOrderInData.setRbeOrderKind("OCO");
            }
            
            //トリガ発動ゾーン
            //■注文種別=通常/逆指値の場合
            stockPlaceOrderInData.setTriggerZone(" ");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "3")) {
                    //■取引種別=現物買付
                    if (StringUtils.equals(dtoA001bReq.getTradeCd(), "1")) {
                        //"0"：以上
                        stockPlaceOrderInData.setTriggerZone("0");
                        //■取引種別=現物売却
                    } else if (StringUtils.equals(dtoA001bReq.getTradeCd(), "2")) {
                        //"1"：以下
                        stockPlaceOrderInData.setTriggerZone("1");
                    }
                }
                // ■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //■取引種別=現物買付
                if (StringUtils.equals(dtoA001bReq.getTradeCd(), "1")) {
                    //"0"：以上
                    stockPlaceOrderInData.setTriggerZone("0");
                    //■取引種別=現物売却
                } else if (StringUtils.equals(dtoA001bReq.getTradeCd(), "2")) {
                    //"1"：以下
                    stockPlaceOrderInData.setTriggerZone("1");
                }
            }
            
            //トリガ値段
            //■注文種別=通常/逆指値の場合
            stockPlaceOrderInData.setTriggerPrice("0000000000");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //■通常/逆指値.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001bReq.getSasinariHouhou(), "3")) {
                    //通常/逆指値.発火条件価格（逆指値）
                    stockPlaceOrderInData.setTriggerPrice(dtoA001bReq.getTriggerPrice());
                }
                // ■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //OCO2.発火条件価格（逆指値）
                stockPlaceOrderInData.setTriggerPrice(dtoA001bReq.getOco2TriggerPrice());
            }
            
            //OCO指成区分
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //"△"
                stockPlaceOrderInData.setOcoSasinariKbn(" ");
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //OCO2.執行条件（逆指値）
                stockPlaceOrderInData.setOcoSasinariKbn(dtoA001bReq.getOco2GyakusasiJyouken());
            }
            
            //OCO指値
            //■注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "1")) {
                //ALL"△"
                stockPlaceOrderInData.setOcoPrice("          ");
                //■注文種別=OCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "2")) {
                //OCO2.注文単価
                stockPlaceOrderInData.setOcoPrice(dtoA001bReq.getOco2Price());
            }
            
            //IPアドレス
            stockPlaceOrderInData.setIpAddress("ifap");
            
            //SOR受注時板乗せ市場区分
            //■市場=A:当社優先市場／SOR　の場合
            if (StringUtils.equals(dtoA001bReq.getMarket(), "A")) {
                //"tse":SOR-東証板乗せ注文
                stockPlaceOrderInData.setSorLastMarket("tse");
                //■市場=上記以外
            } else {
                //"△△△"：SOR対象外
                stockPlaceOrderInData.setSorLastMarket("   ");
            }
            
            //引継ぎID
            stockPlaceOrderInData.setTransId(" ");
            //媒介
            stockPlaceOrderInData.setIntermediary("1");
                       
            try {
                api001Res = apiwrapper.stockPlaceOrder(stockPlaceOrderInData);
                if (api001Res != null) {
                    // APIレスポンスチェック
                    apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
                    if (apiErrorUtil.isFatal()) {
                        apiError = "1";
                    }
                }
            } catch (Exception ae) {
                // システムエラーの場合、DBに受注日を書き込んでエラーレスポンスを返す
                IfaDomesticStockOrderConfirmSql002RequestModel insSql002Req = new IfaDomesticStockOrderConfirmSql002RequestModel();
                insSql002Req.setUpdatedBy(ua.getUserId());
                insSql002Req.setIfaOrderNo(dtoA001bReq.getIfaOrderNo());
                dao.updateIfaDomesticStockOrderConfirmSql002b(insSql002Req);

                dtoRes = IfaCommonUtil.createDataList(
                        List.of(),
                        ErrorLevel.FATAL,
                        ERRORS_ORDEREXECUTION,
                        IfaCommonUtil.getMessage(ERRORS_ORDEREXECUTION)
                );
                return dtoRes;
            }
                      
        } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFD.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFDOCO.key)) {
            
            //IFD、IFDOCO注文　　　：自動注文(国内株式現物・信用)新規注文受付APIの呼出し。
            StockPlaceOrderAutoInData stockPlaceOrderAutoInData = new StockPlaceOrderAutoInData();
            //トランザクションID
            stockPlaceOrderAutoInData.setTransactionId("00000000000000000000000000000000");
            //通番
            stockPlaceOrderAutoInData.setCommandNum("0000000");
            //年月日
            stockPlaceOrderAutoInData.setCommandDate("00000000");
            //部店コード３桁
            stockPlaceOrderAutoInData.setButenCd(cc.getButenCode());
            //口座番号７桁
            stockPlaceOrderAutoInData.setKozaNo(cc.getAccountNumber());
            //アカウントID
            stockPlaceOrderAutoInData.setAccountId("00000000000");
            //アカウント毎の連番
            stockPlaceOrderAutoInData.setNumber("0000000");
            //オリジン
            stockPlaceOrderAutoInData.setOrigin("0");
            //銘柄コード
            stockPlaceOrderAutoInData.setBrandCd(dtoA001bReq.getBrandCode());
            //売買区分
            stockPlaceOrderAutoInData.setTradeKbn(dtoA001bReq.getTradeCd());
            //注文株数
            stockPlaceOrderAutoInData.setQuantity(dtoA001bReq.getQuantity());
            //指成区分
            stockPlaceOrderAutoInData.setSasinariKbn(dtoA001bReq.getIfd1SasinariJyouken());
            
            //指値
            //■IFD1.執行方法=指値　の場合
            stockPlaceOrderAutoInData.setPrice("0000000000");
            if (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "1")) {
                //IFD1.注文単価（指値）
                stockPlaceOrderAutoInData.setPrice(dtoA001bReq.getIfd1Price());
                //■IFD1.執行方法=逆指値　の場合
            } else if (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "3")) {
                //■IFD1.執行方法（逆指値）=指値　の場合
                if (StringUtils.equals(dtoA001bReq.getIfd1GyakusasiHouhou(), "1")) {
                    //IFD1.注文単価（逆指値/指値）
                    stockPlaceOrderAutoInData.setPrice(dtoA001bReq.getIfd1Price());
                }
            }
            
            //受渡方法
            stockPlaceOrderAutoInData.setUkewHoho("1");
            //市場
            stockPlaceOrderAutoInData.setMarket(dtoA001bReq.getMarket());
            //譲渡益税区分
            //■取引種別=現物買付
            if (StringUtils.equals(dtoA001bReq.getTradeCd(), "1")) {
                //"△"
                stockPlaceOrderAutoInData.setJoZeiKbn(" ");
                //■取引種別=現物売却
            } else if (StringUtils.equals(dtoA001bReq.getTradeCd(), "2")) {
                //"2"：申告分離
                stockPlaceOrderAutoInData.setJoZeiKbn("2");
            }
            
            //非特定預り売買区分
            stockPlaceOrderAutoInData.setHitokuteiTradeKbn(dtoA001bReq.getDepositType());
            //弁済期限
            stockPlaceOrderAutoInData.setPaymentLimit(" ");
            //有効期限
            //■注文期間=当日中の場合
            if (StringUtils.equals(dtoA001bReq.getPeriodTerms(), "0")) {
                //"△△△△△△△△"
                stockPlaceOrderAutoInData.setLimit("        ");
                //■上記以外
            } else {
                //期間（カレンダー）　YYYYMMDD形式
                stockPlaceOrderAutoInData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoA001bReq.getLimit()));
            }
            
            //摘要
            stockPlaceOrderAutoInData.setSummary("                              ");
            //決済方法区分
            stockPlaceOrderAutoInData.setPaymentKbn(" ");
            //決済方法
            stockPlaceOrderAutoInData.setPaymentMethod("          ");
            //振込先銀行区分
            stockPlaceOrderAutoInData.setBankKbn(" ");
            //振込先銀行名
            stockPlaceOrderAutoInData.setBankName("                    ");
            //受付経路区分（旧コールセンター区分）
            stockPlaceOrderAutoInData.setCallcenterKbn("0");
            //ユーザーＩＤ
            stockPlaceOrderAutoInData.setUserId(ua.getCcsUserId());
            //ベティング区分
            stockPlaceOrderAutoInData.setVettingKbn(" ");
            //与信チェック用時価
            stockPlaceOrderAutoInData.setCheckPrice("          ");
            //手数料区分
            stockPlaceOrderAutoInData.setComId(cc.getCustomerAttribute());
            //余力チェック区分
            stockPlaceOrderAutoInData.setCheckId(" ");
            
            //RBE注文種別
            //■IFD1.執行方法=指値　または　成行の場合
            if ((StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "1"))
                    || (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "2"))) {
                //ALL"△"：通常注文
                stockPlaceOrderAutoInData.setRbeOrderKind("   ");
                //■IFD1.執行方法=逆指値　の場合
            } else if (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "3")) {
                //"SLO"：逆指値注文
                stockPlaceOrderAutoInData.setRbeOrderKind("SLO");
            }
            
            //トリガ発動ゾーン
            //■IFD1.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "3")) {
                //"0"：以上
                stockPlaceOrderAutoInData.setTriggerZone("0");
                //■上記以外
            } else {
                //"△"：指定なし
                stockPlaceOrderAutoInData.setTriggerZone(" ");
            }
            
            //トリガ値段
            //■IFD1.執行方法=逆指値　の場合
            if (StringUtils.equals(dtoA001bReq.getIfd1SasinariHouhou(), "3")) {
                //IFD1.発火条件価格（逆指値）
                stockPlaceOrderAutoInData.setTriggerPrice(dtoA001bReq.getIfd1TriggerPrice());
                //■上記以外
            } else {
                //"0000000000"
                stockPlaceOrderAutoInData.setTriggerPrice("0000000000");
            }
            
            //OCO指成区分
            stockPlaceOrderAutoInData.setOcoSasinariKbn(" ");
            //OCO指値
            stockPlaceOrderAutoInData.setOcoPrice("          ");
            //自動注文種別
            stockPlaceOrderAutoInData.setAutoOrderKind("IF  ");
            
            //DONE指成区分
            //■注文種別=IFDの場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "3")) {
                //IFD2.執行条件
                stockPlaceOrderAutoInData.setDoneSasinariKbn(dtoA001bReq.getIfd2SasinariJyouken());
                //■注文種別=IFDOCO
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //OCO1.執行条件
                stockPlaceOrderAutoInData.setDoneSasinariKbn(dtoA001bReq.getOco1SasinariJyouken());
            }
            
            //DONE指値
            //■注文種別=IFDの場合
            stockPlaceOrderAutoInData.setDonePrice("0000000000");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=指値　の場合
                if (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "1")) {
                    //IFD2.注文単価
                    stockPlaceOrderAutoInData.setDonePrice(dtoA001bReq.getIfd2Price());
                    //■IFD2.執行方法=逆指値　の場合
                } else if (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "3")) {
                    //■IFD2.執行方法（逆指値）=指値　の場合
                    if (StringUtils.equals(dtoA001bReq.getIfd2GyakusasiHouhou(), "1")) {
                        //IFD2.注文単価
                        stockPlaceOrderAutoInData.setDonePrice(dtoA001bReq.getIfd2Price());
                    }
                }
                //■注文種別=IFDOCO
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //■OCO1執行方法=指値　の場合
                if (StringUtils.equals(dtoA001bReq.getOco1SasinariHouhou(), "1")) {
                    //OCO1.注文単価
                    stockPlaceOrderAutoInData.setDonePrice(dtoA001bReq.getOco1Price());
                }
            }
            
            //DONE有効期限
            //■IFD2.期間.期間条件=当日中の場合
            if (StringUtils.equals(dtoA001bReq.getIfd2PeriodTerms(), "0")) {
                //"△△△△△△△△"
                stockPlaceOrderAutoInData.setDoneLimit("        ");
                //■上記以外
            } else {
                //IFD2.期間.日付　YYYYMMDD形式
                stockPlaceOrderAutoInData
                        .setDoneLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoA001bReq.getIfd2Limit()));
            }
            
            //DONERBE注文種別
            //■注文種別=IFDの場合
            stockPlaceOrderAutoInData.setDoneRbeOrderKind("   ");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=指値　または　成行の場合
                if ((StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "1"))
                        || (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "2"))) {
                    //ALL"△"：通常注文
                    stockPlaceOrderAutoInData.setDoneRbeOrderKind("   ");
                    //■IFD2.執行方法=逆指値　の場合
                } else if (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "3")) {
                    //"SLO"：逆指値注文
                    stockPlaceOrderAutoInData.setDoneRbeOrderKind("SLO");
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //"OCO"：DONE注文がOCO注文
                stockPlaceOrderAutoInData.setDoneRbeOrderKind("OCO");
            }
            
            //DONEトリガ発動ゾーン
            //■注文種別=IFDの場合
            stockPlaceOrderAutoInData.setDoneTriggerZone(" ");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "3")) {
                    //"1"：以下
                    stockPlaceOrderAutoInData.setDoneTriggerZone("1");
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //"1"：以下
                stockPlaceOrderAutoInData.setDoneTriggerZone("1");
            }
            
            //DONEトリガ値段
            //■注文種別=IFDの場合
            stockPlaceOrderAutoInData.setDoneTriggerPrice("0000000000");
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "3")) {
                //■IFD2.執行方法=逆指値　の場合
                if (StringUtils.equals(dtoA001bReq.getIfd2SasinariHouhou(), "3")) {
                    //IFD2.発火条件価格（逆指値）
                    stockPlaceOrderAutoInData.setDoneTriggerPrice(dtoA001bReq.getIfd2TriggerPrice());
                }
                //■注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //OCO2.発火条件価格（逆指値）
                stockPlaceOrderAutoInData.setDoneTriggerPrice(dtoA001bReq.getOco2TriggerPrice());
            }
            
            //DONEOCO指成区分
            //■注文種別=IFDOCOの場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //OCO2.執行条件
                stockPlaceOrderAutoInData.setDoneOcoSasinariKbn(dtoA001bReq.getOco2GyakusasiJyouken());
                //■上記以外
            } else {
                //"△"
                stockPlaceOrderAutoInData.setDoneOcoSasinariKbn(" ");
            }
            
            //DONEOCO指値
            //■注文種別=IFDOCOの場合
            if (StringUtils.equals(dtoA001bReq.getOrderKind(), "4")) {
                //OCO2.注文単価
                stockPlaceOrderAutoInData.setDoneOcoPrice(dtoA001bReq.getOco2Price());
                //■上記以外
            } else {
                //"0000000000"
                stockPlaceOrderAutoInData.setDoneOcoPrice("0000000000");
            }
            
            //IPアドレス
            stockPlaceOrderAutoInData.setIpAddress("ifap");
            //引継ぎID
            stockPlaceOrderAutoInData.setTransId(" ");
            //媒介
            stockPlaceOrderAutoInData.setIntermediary("1");
            //新規約定日
            stockPlaceOrderAutoInData.setOpenTradeDate("        ");
            //新規単価
            stockPlaceOrderAutoInData.setOpenPrice("            ");
            //一般信用売弁済期限年月日区分
            stockPlaceOrderAutoInData.setIppanMgPaymentKbn(" ");
            //一般信用売弁済期限年月日数
            stockPlaceOrderAutoInData.setIppanMgPaymentLimit("  ");
            StockPlaceOrderAutoIn stockPlaceOrderAutoIn = new StockPlaceOrderAutoIn();
            stockPlaceOrderAutoIn.setIndata(stockPlaceOrderAutoInData);
            
            try {
                api002Res = apiwrapper.stockPlaceOrderAuto(stockPlaceOrderAutoIn);
                if (api002Res != null) {
                    // APIレスポンスチェック
                    apiErrorUtil.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage());
                    if (apiErrorUtil.isFatal()) {
                        apiError = "1";
                    }
                }
            } catch (Exception ae) {
                // システムエラーの場合、DBに受注日を書き込んでエラーレスポンスを返す
                IfaDomesticStockOrderConfirmSql002RequestModel insSql002Req = new IfaDomesticStockOrderConfirmSql002RequestModel();
                insSql002Req.setUpdatedBy(ua.getUserId());
                insSql002Req.setIfaOrderNo(dtoA001bReq.getIfaOrderNo());
                dao.updateIfaDomesticStockOrderConfirmSql002b(insSql002Req);

                dtoRes = IfaCommonUtil.createDataList(
                        List.of(),
                        ErrorLevel.FATAL,
                        ERRORS_ORDEREXECUTION,
                        IfaCommonUtil.getMessage(ERRORS_ORDEREXECUTION)
                );
                return dtoRes;
            }
            
        }
        
        //⑧発注後に注文内容を国内株式注文テーブルへ記録する。
        IfaDomesticStockOrderConfirmSql002RequestModel insSql002Req = new IfaDomesticStockOrderConfirmSql002RequestModel();
        
        if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.OCO.key)) {
            //商品区分
            insSql002Req.setSecurityType(api001Res.getOrderType());
            //EC受注番号
            insSql002Req.setEcOrderNo(api001Res.getOrderNum());
            //受注日
            insSql002Req.setOrderDate(api001Res.getAcceptDate());
            //受注時刻
            insSql002Req.setOrderTime(api001Res.getAcceptTime());
            //種別
            insSql002Req.setShubetu(api001Res.getShubetu());
            //エラーコード
            insSql002Req.setCode(api001Res.getCode());
            //エラーメッセージ
            insSql002Req.setErrMessage(api001Res.getMessage());
            //与信チェック用時価
            insSql002Req.setEstimatePrice(api001Res.getEstimatePrice());
            //約定金額（概算）
            insSql002Req.setAmount(api001Res.getAmount());
            //手数料（概算）
            insSql002Req.setCommission(api001Res.getCommission());
            //消費税（概算）
            insSql002Req.setConsumptionTax(api001Res.getConsumptionTax());
            //譲渡益税（概算）
            insSql002Req.setCapitalGainTax(api001Res.getCapitalGainTax());
            //精算金額（概算）
            insSql002Req.setNetAmount(api001Res.getNetAmount());
            //諸経費
            insSql002Req.setCost(null);
            //約定予定日
            insSql002Req.setContractDate(api001Res.getTradeDate());
            //受渡予定日
            insSql002Req.setDeliveryDate(api001Res.getSettlementDate());
            //受付有効期限
            insSql002Req.setAcceptLimit(api001Res.getAcceptLimit());
            //DONE 受付有効期限
            insSql002Req.setDoneAcceptLimit(null);
            // 手            //手数料区分（採用）
            insSql002Req.setComIdR(api001Res.getComIdR());
            //売却可能数量
            insSql002Req.setAcPosition(api001Res.getAcPosition());
            //注文後の売却可能数量
            insSql002Req.setAcPositionAfter(api001Res.getAcPositionAfter());
            //買付可能金額
            insSql002Req.setAcBalance(api001Res.getAcBalance());
            //注文後の買付可能金額
            insSql002Req.setAcBalanceAfter(api001Res.getAcBalanceAfter());
            //注文入力市場
            insSql002Req.setOrderedMarket(api001Res.getOrderedMarket());
            //取引不足額
            insSql002Req.setTradeDeficitAmount(api001Res.getTradeDeficitAmount());
            //ISA買付可能枠
            insSql002Req.setIsaBuyLimit(api001Res.getIsaBuyLimit());
            //ジュニアNISA振替金額
            insSql002Req.setJrnisaTransferAmount(api001Res.getJrnisaTransferAmount());
            //SOR連携区分
            insSql002Req.setSorLinkKbn(api001Res.getSorLinkKbn());
            //決済可能数量
            insSql002Req.setUnclosedQuantity(null);
            //注文後の決済可能数量
            insSql002Req.setUnclosedQuantityAfter(null);
            //建玉余力
            insSql002Req.setMarginCapability(null);
            //注文後の建玉余力
            insSql002Req.setMarginCapabilityAfter(null);
            //維持率
            insSql002Req.setRetentionRate(null);
            //注文後維持率
            insSql002Req.setActualGrntRateAfter(null);
            //適用金利
            insSql002Req.setApplicableInterestRate(null);
            //適用貸株料
            insSql002Req.setApplicableStockLendingFees(null);
            //プレミアム料
            insSql002Req.setPremium(null);
        } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFD.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFDOCO.key)) {
            //商品区分
            insSql002Req.setSecurityType(api002Res.getOrderType());
            //EC受注番号
            insSql002Req.setEcOrderNo(api002Res.getOrderNum());
            //受注日
            insSql002Req.setOrderDate(api002Res.getAcceptDate());
            //受注時刻
            insSql002Req.setOrderTime(api002Res.getAcceptTime());
            //種別
            insSql002Req.setShubetu(api002Res.getShubetu());
            //エラーコード
            insSql002Req.setCode(api002Res.getCode());
            //エラーメッセージ
            insSql002Req.setErrMessage(api002Res.getMessage());
            //与信チェック用時価
            insSql002Req.setEstimatePrice(api002Res.getEstimatePrice());
            //約定金額（概算）
            insSql002Req.setAmount(api002Res.getAmount());
            //手数料（概算）
            insSql002Req.setCommission(api002Res.getCommission());
            //消費税（概算）
            insSql002Req.setConsumptionTax(api002Res.getConsumptionTax());
            //譲渡益税（概算）
            insSql002Req.setCapitalGainTax(api002Res.getCapitalGainTax());
            //精算金額（概算）
            insSql002Req.setNetAmount(api002Res.getNetAmount());
            //諸経費
            insSql002Req.setCost(api002Res.getCost());
            //約定予定日
            insSql002Req.setContractDate(api002Res.getTradeDate());
            //受渡予定日
            insSql002Req.setDeliveryDate(api002Res.getSettlementDate());
            //受付有効期限
            insSql002Req.setAcceptLimit(api002Res.getAcceptLimit());
            //DONE 受付有効期限
            insSql002Req.setDoneAcceptLimit(api002Res.getDoneAcceptLimit());
            //手数料区分（採用）
            insSql002Req.setComIdR(api002Res.getComIdR());
            //売却可能数量
            insSql002Req.setAcPosition(api002Res.getAcPosition());
            //注文後の売却可能数量
            insSql002Req.setAcPositionAfter(api002Res.getAcPositionAfter());
            //買付可能金額
            insSql002Req.setAcBalance(api002Res.getAcBalance());
            //注文後の買付可能金額
            insSql002Req.setAcBalanceAfter(api002Res.getAcBalanceAfter());
            //注文入力市場
            insSql002Req.setOrderedMarket(api002Res.getOrderedMarket());
            //取引不足額
            insSql002Req.setTradeDeficitAmount(api002Res.getTradeDeficitAmount());
            //ISA買付可能枠
            insSql002Req.setIsaBuyLimit(api002Res.getIsaBuyLimit());
            //ジュニアNISA振替金額
            insSql002Req.setJrnisaTransferAmount(api002Res.getJrnisaTransferAmount());
            //SOR連携区分
            insSql002Req.setSorLinkKbn(api002Res.getSorLinkKbn());
            //決済可能数量
            insSql002Req.setUnclosedQuantity(api002Res.getUnclosedQuantity());
            //注文後の決済可能数量
            insSql002Req.setUnclosedQuantityAfter(api002Res.getUnclosedQuantityAfter());
            //建玉余力
            insSql002Req.setMarginCapability(api002Res.getMarginCapability());
            //注文後の建玉余力
            insSql002Req.setMarginCapabilityAfter(api002Res.getMarginCapabilityAfter());
            //維持率
            insSql002Req.setRetentionRate(api002Res.getActualGrntRate());
            //注文後維持率
            insSql002Req.setActualGrntRateAfter(api002Res.getActualGrntRateAfter());
            //適用金利
            insSql002Req.setApplicableInterestRate(api002Res.getAppInterestRates());
            //適用貸株料
            insSql002Req.setApplicableStockLendingFees(api002Res.getAppLendingStock());
            //プレミアム料
            insSql002Req.setPremium(api002Res.getPremium());
            //Ｔポイント
            insSql002Req.setTpoint(null);
            //利用後のＴポイント
            insSql002Req.setTpointAfter(null);
        }
        
        //更新者
        insSql002Req.setUpdatedBy(ua.getUserId());
        insSql002Req.setIfaOrderNo(dtoA001bReq.getIfaOrderNo());
        insSql002Req.setApiError(apiError);
        
        try {
            dao.updateIfaDomesticStockOrderConfirmSql002(insSql002Req);
        } catch (Exception e) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED,
                    IfaCommonUtil.getMessage(WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED));
        }
        
        if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.OCO.key)) {
            if (apiErrorUtil.isFatal()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ORDEREXECUTION,
                        IfaCommonUtil.getMessage(ERRORS_ORDEREXECUTION,
                                new String[] { api001Res.getCode().trim(), api001Res.getMessage().trim() }));
                return dtoRes;
            }
        } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFD.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFDOCO.key)) {
            if (apiErrorUtil.isFatal()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ORDEREXECUTION,
                        IfaCommonUtil.getMessage(ERRORS_ORDEREXECUTION,
                                new String[] { api002Res.getCode().trim(), api002Res.getMessage().trim() }));
                return dtoRes;
            }
        }
        
        IfaDomesticStockOrderConfirmA001bResponseDto resA001bDto = new IfaDomesticStockOrderConfirmA001bResponseDto();
        if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.OCO.key)) {
            // EC受注番号
            resA001bDto.setEcOrderNo(api001Res.getOrderNum());
            // ジュニアNISA振替金額
            resA001bDto.setJrnisaTransferAmount(api001Res.getJrnisaTransferAmount());
            // 見積単価
            resA001bDto.setQuoteUnitPrice(api001Res.getEstimatePrice());
            // 約定金額
            resA001bDto.setContractAmount(api001Res.getAmount());
            // 手数料/諸費用
            resA001bDto.setCharge(api001Res.getCommission());
            // 消費税
            resA001bDto.setConsumptionTax(api001Res.getConsumptionTax());
            // 讓渡益税
            resA001bDto.setYieldTax(api001Res.getCapitalGainTax());
            // 精算金額
            resA001bDto.setSettlementAmount(api001Res.getNetAmount());
            // 投資可能枠
            resA001bDto.setInvestableAmount(api001Res.getIsaBuyLimit());
            // 約定予定日
            resA001bDto.setContractDate(api001Res.getTradeDate());
            // 受渡予定日
            resA001bDto.setDeliveryDate(api001Res.getSettlementDate());
            // 受注日時
            resA001bDto.setOrderDayTime(api001Res.getAcceptDate() + " " + api001Res.getAcceptTime());
            // 注文入力市場
            resA001bDto.setOrderedMarket(api001Res.getOrderedMarket());
            
        } else if (StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFD.key)
                || StringUtils.equals(dtoA001bReq.getOrderKind(), OrderKind.IFDOCO.key)) {
            // EC受注番号
            resA001bDto.setEcOrderNo(api002Res.getOrderNum());
            // ジュニアNISA振替金額
            resA001bDto.setJrnisaTransferAmount(api002Res.getJrnisaTransferAmount());
            // 見積単価
            resA001bDto.setQuoteUnitPrice(api002Res.getEstimatePrice());
            // 約定金額
            resA001bDto.setContractAmount(api002Res.getAmount());
            // 手数料/諸費用
            resA001bDto.setCharge(api002Res.getCommission());
            // 消費税
            resA001bDto.setConsumptionTax(api002Res.getConsumptionTax());
            // 讓渡益税
            resA001bDto.setYieldTax(api002Res.getCapitalGainTax());
            // 精算金額
            resA001bDto.setSettlementAmount(api002Res.getNetAmount());
            // 投資可能枠
            resA001bDto.setInvestableAmount(api002Res.getIsaBuyLimit());
            // 約定予定日
            resA001bDto.setContractDate(api002Res.getTradeDate());
            // 受渡予定日
            resA001bDto.setDeliveryDate(api002Res.getSettlementDate());
            // 受注日時
            resA001bDto.setOrderDayTime(api002Res.getAcceptDate() + " " + api002Res.getAcceptTime());
            // 注文入力市場
            resA001bDto.setOrderedMarket(api002Res.getOrderedMarket());
        }
        
        resDtoList.add(resA001bDto);
        
        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }
        
        dtoRes = apiErrorUtil.createDataList(resDtoList, "");
        return dtoRes;
    }
}
