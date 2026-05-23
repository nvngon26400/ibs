package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticMutualFundOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.FundCancelOrder1InData;
import jp.co.sbisec.pcenter.dto.yanap.FundCancelOrder1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtDetail;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtOutData;

/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *     2023/11/27 新規作成
 */
@Component(value = "cmpIfaDomesticMutualFundOrderCancelConfirmService")
public class IfaDomesticMutualFundOrderCancelConfirmServiceImpL
        implements IfaDomesticMutualFundOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaDomesticMutualFundOrderCancelConfirmServiceImpL.class);
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Autowired
    private IfaDomesticMutualFundOrderCancelConfirmDao dao;
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 取引停止口座エラー値 */
    private static final String SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE = "1";
    
    /** ジュニアISA契約区分 判定値 */
    private static final String JR_ISA_CONTRACT_TYPE_VALUE = "1";
    
    /** 注文情報取得エラー */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 取消表示判定エラー */
    private static final String ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE = "errors.cmn.orderCancel.outOfService";
    
    /** 取引不可エラー */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** SQL001 登録エラー */
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** API002 エラー */
    private static final String ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    /** SQL002更新 エラー */
    private static final String WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED = "warnings.cmn.postOrderExecutionCancel.completed";
    
    /** CCSID未登録 エラー */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** API001.注文ステータス 判定値 */
    private static final String API001_ORDER_STATUS_VALUE = "1";
    
    /** API001.注文取消区分 判定値 */
    private static final String API001_CXL_ORDER_ID_VALUE = " ";
    
    /** API001.約定区分 判定値 */
    private static final String API001_EXEC_STATUS_VALUE = " ";
    
    /** API001.取消ステータス 判定値 */
    private static final String API001_CXL_STATUS_VALUE_ACCEPTED = "1";

    /** API001.取消ステータス 判定値 */
    private static final String API001_CXL_STATUS_VALUE_NOT_CANCELL = " ";
    
    /** API001.受付経路区分 定額売却注文 判定値 */
    private static final String API001_CALLCENTER_KBN_SELLING_VOLUME = "8";
    
    /** API001 受付経路区分の出力値 "F"：定率売却注文 */
    private static final String API001_CALLCENTER_KBN_SELL_FIXED = "F";
    
    /** API001 受付経路区分の出力値 "I"：期間指定売却注文 */
    private static final String API001_CALLCENTER_KBN_SELL_LIMIT = "I";
    
    /** API001.受付経路区分 積立買付注文 判定値 */
    private static final String API001_CALLCENTER_KBN_ACCUMULATION_BUY = "9";
    
    /** API001.受付経路区分 積立買付注文 - クレジットカード決済 判定値 */
    private static final String API001_CALLCENTER_KBN_CREDIT_CARDS = "C";
    
    /** API001.売買区分 売（解約） 判定値 */
    private static final String API001_BUY_SELL_SELL_CANCEL = "U";
    
    /** API001.売買区分 売（買取） 判定値 */
    private static final String API001_BUY_SELL_SELL_BUY = "V";
    
    /** API001.売買区分 買（購入） 判定値 */
    private static final String API001_BUY_SELL_BUY_SELL = "K";
    
    /** API001.売買区分 全解約 判定値 */
    private static final String API001_BUY_SELL_ALL_CANCEL = "A";
    
    /** API001.売買区分 全買取 判定値 */
    private static final String API001_BUY_SELL_ALL_BUY = "B";
    
    /** API001.商品区分 一般口 判定値 */
    private static final String API001_SEC_ID_GENERAL = "T";
    
    /** API001.商品区分 累投 判定値 */
    private static final String API001_SEC_ID_TOTAL = "Y";
    
    /** FCT003.媒介可否 判定値*/
    private static final String FCT003_MEDIATE_PROPRIETY_VALUE = "1";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：ドメインID */
    private static final String CODE_LIST_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示区分 */
    private static final String CODE_LIST_KEY = "4";
    
    /** 区分.対象取引（メッセージ表示用） 変換用：表示パターン */
    private static final String CODE_LIST_DISP_PATTERN = "1";
    
    /** 区分.分配金受取方法 変換用：ドメインID */
    private static final String CODE_LIST_DISTRIBUTION_RECEIVE_METHOD = "DISTRIBUTION_RECEIVE_METHOD";
    
    /** △ */
    private static final String SPACE = " ";

    /** API001とAPI002の口座番号のフォーマット(7桁0埋め) */
    private static final String KOZA_NO_FORMAT = "%7s";

    /** API001とAPI002のfrom,toのフォーマット(5桁0埋め) */
    private static final String REF_FROMTO_FORMAT = "%05d";

    /** API001の取得上限件数 */
    private static final String API001_GETNUM = "100";

    /** 検索番号指定FROM値の初期値 */
    private static final String FROM_DEFAULT = "1";

    /** 検索番号指定TO値の初期値 */
    private static final String TO_DEFAULT = "100";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaDomesticMutualFundOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaDomesticMutualFundOrderCancelConfirmA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderCancelConfirmServiceImplL.initializeA001");
        }
        
        DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto>();
        List<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto>();
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ① 共通関数FCT001の呼び出し
        //     利用者の口座に対する権限チェックを行う。
        //   パラメタ：部店コード   ⇒ 顧客共通情報.部店コード
        //                      口座番号       ⇒ 顧客共通情報.口座番号
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝"0"）：権限なしエラー(errors.butenAccountNotExist)を返す。
        // 取引停止口座（取引停止フラグ＝"1）：取引停止口座エラー（errors.cmn.selectedAccount.outOfService）を返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        } else if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ② API001（NRI_QueryFundOrderWebExt）を呼び出す。
        // 取消対象の注文を取得する。
        //   パラメタ：部店コード   ⇒ 顧客共通情報.部店コード(3桁)
        //                      口座番号         ⇒ 顧客共通情報.口座番号
        //                      商品区分         ⇒ "△"
        //                      約定注文区分     ⇒ "△"
        //                      検索番号指定FROM ⇒ n回目  (n-1)×100+1
        //                      検索番号指定TO   ⇒ n回目  n×100
        //                      取得口座区分     ⇒
        //                          ■顧客共通情報.ジュニアISA契約区分 ≠ "1":契約 の場合
        //                             "△":通常口座およびJrNISA口座の第一口座
        //                          ■顧客共通情報.ジュニアISA契約区分 = "1":契約 の場合
        //                             "2"：JrNISA口座(第一、第二口座両方)
        List<QueryFundOrderWebExtDetail> api001OutList = new ArrayList<QueryFundOrderWebExtDetail>();
        api001OutList = callQueryFundOrderWebExtDetail(FROM_DEFAULT, TO_DEFAULT, apiErrorUtil);
        if (api001OutList == null) {
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDER_NOTFOUND, IfaCommonUtil
                    .getMessage(IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDER_NOTFOUND));
        }
        
        // api001Out初期化
        QueryFundOrderWebExtDetail api001Out = null;
        
        // APIのレスポンスから、リクエストのEC受注番号をキーに取消対象の
        // 注文を取得する。
        if (api001OutList.size() > 0) {
            for (QueryFundOrderWebExtDetail tgApi001Out : api001OutList) {
                
                // EC注文番号が一致するデータを検索
                if (StringUtils.equals(tgApi001Out.getOrdeNo(), dtoReq.getEcOrderNo())) {
                    // 一致するときapi001Outにセット
                    api001Out = tgApi001Out;
                } else {
                    // 一致しなければループを回す
                    continue;
                }
            }
        }

        // API001で取得できない場合エラー
        if (api001Out == null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDER_NOTFOUND, IfaCommonUtil
            .getMessage(IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDER_NOTFOUND));
            return dtoRes;
        }

        // 画面定義書_SUB0202_0104-01_注文状況一覧
        // シート「別紙.注文明細_算出方法」を参照する。
        // 「●国内投信：取消表示判定」表のAPI002を本処理のAPIに読み替え、
        // 各項目の条件が一致して、「取消ボタン表示判定（算出）」が「1：表示」の場合に正常とする。
        // 【「取消ボタン表示判定（算出）」が「1：表示」 判定方法】
        if(!getMutualFundMutualFundCancelButtonDisplayJudgment(api001Out)) {
            
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ③ 共通関数FCT003の呼び出し
        // 取引コース媒介可否チェックを行う。
        //   パラメタ：部店コード    ⇒ 顧客共通情報.部店コード
        //         口座番号     ⇒ 顧客共通情報.口座番号
        //         証券金銭種別 ⇒ "06"：国内投信
        //         取引種別     ⇒ API001.取引種別
        // 【チェック内容】
        // 媒介可否リスト.媒介可否="1"（媒介可）を「取引可」、それ以外は「取引不可」（1レコードのみ返却される予定）
        // 取引可：次の処理へ。
        // 上記以外：取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す。
        // 
        // 【メッセージ】
        // {0}ができないコースです。
        // {0}：区分.対象取引（メッセージ表示用）（区分値：4  ＠表示パターン：1  ）
        //             ⇒ 固定値"4"でコード変換して埋込み文字を作成
        // 
        InputFct003Dto fct003Req = new InputFct003Dto();
        fct003Req.setButenCode(butenCode);
        fct003Req.setAccountNumber(accountNumber);
        fct003Req.setProductCd("06");
        fct003Req.setTradeCd(this.getTradeCd(api001Out.getCallcenterKbn(), api001Out.getBuySell(),
                                    api001Out.getSecId()));
        OutputFct003Dto fct003Res = fct003.doCheck(fct003Req);
        
        if (StringUtils.equals(fct003Res.getMediateProprietyList().get(0).getMediatePropriety(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.FCT003_MEDIATE_PROPRIETY_VALUE) == false) {
            
            String msgItem = codeListService.getValue(
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_MSG_DISPLAY_TARGET_TRADE,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_KEY,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_DISP_PATTERN);
            
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                            new String[] { msgItem }));
            return dtoRes;
        }


        IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto resDto = new IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto();
        // Trim処理
        // BeanUtil.trimStringFields(api001Out);

        // 銘柄コード
        //  ■ ファンドコード（号）に値あり
        //    ファンドコード（回数）+"."+ファンドコード（号）
        //  ■ 上記以外
        //    ファンドコード（回数）
        if (api001Out.getFundCodeSub().trim().length() == 0) {
            resDto.setBrandCode(api001Out.getFundCodeSerNo());
        } else {
            resDto.setBrandCode(api001Out.getFundCodeSerNo() + "." + api001Out.getFundCodeSub());
        }
        // 銘柄名
        resDto.setBrandName(api001Out.getSecName());
        // 取引種別
        resDto.setTradeCd(this.getTradeCd(api001Out.getCallcenterKbn(), api001Out.getBuySell().toUpperCase(),
                api001Out.getSecId().toUpperCase()));
        // 購入解約方法
        resDto.setBuyCancelMethod(api001Out.getBuySellMtd());
        // 金額
        resDto.setAmount(api001Out.getPayment());
        // 口数
        resDto.setUnit(api001Out.getQuantity());
        // 預り区分
        resDto.setDepositType(api001Out.getIsaKbn());
        // 利用ポイント
        resDto.setPoint(api001Out.getPointOrder());
        // 受注日時
        resDto.setOrderDayTime(api001Out.getInputDate());
        // EC受注番号
        resDto.setEcOrderNo(api001Out.getOrdeNo());
        // ファンドタイプ
        resDto.setFundType(api001Out.getSecId());
        // ファンドコード（回数）
        resDto.setFundCodeTimes(api001Out.getFundCodeSerNo());
        // ファンドコード（号）
        resDto.setFundCodeIssues(api001Out.getFundCodeSub());
        // 売買区分
        resDto.setTradeKbn(api001Out.getBuySell());
        // 乗換優遇区分
        resDto.setNorikaeYuguKbn(api001Out.getNorikaeYuguKbn());
        // 見積単価
        resDto.setQuoteUnitPrice(api001Out.getCtEstimatePrice());
        // 約定金額
        resDto.setContractAmount(api001Out.getCtAmount());
        // 手数料/諸費用
        resDto.setCharge(api001Out.getCtCommission());
        // 消費税
        resDto.setConsumptionTax(api001Out.getCtConsumptionTax());
        // 讓渡益税
        resDto.setYieldTax(api001Out.getCtCapitalGainTax());
        // 精算金額
        resDto.setSettlementAmount(api001Out.getCtNetAmount());
        // 約定予定日
        resDto.setContractDate(api001Out.getTradeDate());
        // 受渡予定日
        resDto.setDeliveryDate(api001Out.getSettlementDate());
        // 受注数量
        resDto.setOrderQuantity(api001Out.getQuantity());
        // ポイント種別
        resDto.setPointType(api001Out.getPointType());
        // 受付経路区分
        resDto.setCallcenterClassification(api001Out.getCallcenterKbn());
        // 分配金受取方法
        resDto.setDistributionReceiveMethod(codeListService.getValue(
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_DISTRIBUTION_RECEIVE_METHOD,
                api001Out.getReinvest()));
        // 受注日(YYYYMMDDだけを抽出)
        resDto.setOrderDate(api001Out.getInputDate().substring(0, 8));
        
        resDtoList.add(resDto);
    
        // ④ レスポンスを返す。
        dtoRes = apiErrorUtil.createDataList(resDtoList, "");
        return dtoRes;
        
    }
    
    /**
     * アクションID：A002
     * アクション名：注文取消（事前登録）
     * Dtoリクエスト：IfaDomesticMutualFundOrderCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto
     * model 1 リクエスト：IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel
     * model 1 レスポンス：IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel
     * model 2 リクエスト：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     * model 2 レスポンス：IfaDomesticMutualFundOrderCancelConfirmSql002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> orderCancellationA002a(
            IfaDomesticMutualFundOrderCancelConfirmA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderCancelConfirmServiceImplL.orderCancellationA002a");
            
        }
        
        DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>();
        List<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>();
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ① 共通関数FCT001の呼び出し
        //     利用者の口座に対する権限チェックを行う。
        //   パラメタ：部店コード   ⇒ 顧客共通情報.部店コード
        //                      口座番号       ⇒ 顧客共通情報.口座番号
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝""0""）：権限なしエラー(errors.butenAccountNotExist)を返す。
        // 取引停止口座（取引停止フラグ＝""1）：取引停止口座エラー（errors.cmn.selectedAccount.outOfService）を返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        } else if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.SELECTEDACCOUNT_OUTOFSERVICE_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ② 共通関数FCT003の呼び出し
        // 取引コース媒介可否チェックを行う。
        //   パラメタ：部店コード    ⇒ 顧客共通情報.部店コード
        //                      口座番号        ⇒ 顧客共通情報.口座番号
        //                      証券金銭種別 ⇒ ""06""：国内投信
        //                      取引種別        ⇒ API001.取引種別
        // 【チェック内容】
        // 媒介可否リスト.媒介可否=""1""（媒介可）を「取引可」、それ以外は「取引不可」（1レコードのみ返却される予定）
        // 取引可：次の処理へ。
        // 上記以外：取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す。
        InputFct003Dto fct003Req = new InputFct003Dto();
        fct003Req.setButenCode(butenCode);
        fct003Req.setAccountNumber(accountNumber);
        fct003Req.setProductCd("06");
        fct003Req.setTradeCd(dtoReq.getTradeCd());
        OutputFct003Dto fct003Res = fct003.doCheck(fct003Req);
        
        if (StringUtils.equals(fct003Res.getMediateProprietyList().get(0).getMediatePropriety(),
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.FCT003_MEDIATE_PROPRIETY_VALUE) == false) {
            
            String msgItem = codeListService.getValue(
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_MSG_DISPLAY_TARGET_TRADE,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_KEY,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.CODE_LIST_DISP_PATTERN);
            
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                            new String[] { msgItem }));
            return dtoRes;
        }

        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_CMN_CCSID_UNREGISTERED));
        }

        // 
        // ③  SQL001の呼び出し
        // 注文取消前に注文内容をDBへ記録する。
        IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel insSql001Req = new IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel();
        // 部店コード
        insSql001Req.setButenCode(butenCode);
        // 口座番号
        insSql001Req.setAccountNumber(accountNumber);
        // 顧客コード
        insSql001Req.setCustomerId(cc.getCustomerCode());
        // 預り区分
        insSql001Req.setDepositType(dtoReq.getDepositType());
        // 特定口座区分
        insSql001Req.setSpecificAccount(cc.getSpecificAccountType());
        // ジュニア特定口座区分
        insSql001Req.setJrSpecificAccount(cc.getJrTokuteiKouzaKbn());
        // ファンドタイプ
        insSql001Req.setFundType(dtoReq.getFundType());
        // ファンドコード（回数）
        insSql001Req.setFundCodeTimes(dtoReq.getFundCodeTimes());
        // ファンドコード（号）
        insSql001Req.setFundCodeIssues(dtoReq.getFundCodeIssues());
        // 取引種別
        insSql001Req.setTradeCd(dtoReq.getTradeCd());
        // 売買区分
        insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
        // 金額
        insSql001Req.setAmount(dtoReq.getAmount());
        // 口数
        insSql001Req.setUnit(dtoReq.getUnit());
        // 乗換優遇区分
        insSql001Req.setNorikaeYuguKbn(dtoReq.getNorikaeYuguKbn());
        // ポイント種別
        insSql001Req.setPointType(dtoReq.getPointType());
        // 利用ポイント
        insSql001Req.setPoint(dtoReq.getPoint());
        // EC受注番号
        insSql001Req.setEcOrderNo(dtoReq.getEcOrderNo());
        // 見積単価
        insSql001Req.setQuoteUnitPrice(dtoReq.getQuoteUnitPrice());
        // 約定金額
        insSql001Req.setContractAmount(dtoReq.getContractAmount());
        // 手数料/諸費用
        insSql001Req.setCharge(dtoReq.getCharge());
        // 消費税
        insSql001Req.setConsumptionTax(dtoReq.getConsumptionTax());
        // 讓渡益税
        insSql001Req.setYieldTax(dtoReq.getYieldTax());
        // 精算金額
        insSql001Req.setSettlementAmount(dtoReq.getSettlementAmount());
        // 約定予定日
        insSql001Req.setContractDate(dtoReq.getContractDate());
        // 受渡予定日
        insSql001Req.setDeliveryDate(dtoReq.getDeliveryDate());
        // 仲介業者コード
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        // 仲介業者営業員コード
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        // ユーザーID
        insSql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // 取消ユーザーID
        insSql001Req.setCancelUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // 購入解約方法
        insSql001Req.setBuyCancelMethod(dtoReq.getBuyCancelMethod());
        // 受注日
        insSql001Req.setOrderDate(dtoReq.getOrderDate());
        // 作成者
        insSql001Req.setCreatUserId(IfaCommonUtil.getUserAccount().getUserId());
        // 更新者
        insSql001Req.setUpdateUserId(IfaCommonUtil.getUserAccount().getUserId());

        
        IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel insSql001Res = null;
        
        try {
            insSql001Res = dao
                .insertIfaDomesticMutualFundOrderCancelConfirmSql001(insSql001Req);
        } catch (Exception e) {
            // ログはDaoImpLの方で出している
            insSql001Res = null;
        }
        
        // DB登録OK：次の処理へ。
        // DB登録NG：エラー(errors.frs.preOrderExecution.failed)を返す。
        if (insSql001Res == null) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_FRS_PREORDEREXECUTION_FAILED,
                    IfaCommonUtil.getMessage(
                            IfaDomesticMutualFundOrderCancelConfirmServiceImpL.ERRORS_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }

        IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto resDto = new IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto();
        // IFA注文番号
        resDto.setIfaOrderNumber(insSql001Res.getIfaOrderNo());
        // IFA注文サブ番号
        resDto.setIfaOrderSubNumber(insSql001Res.getIfaOrderSubNo());
        
        resDtoList.add(resDto);
        
        // ⑦ レスポンスを返す。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：注文取消（本登録）
     * Dtoリクエスト：IfaDomesticMutualFundOrderCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto
     * model 1 リクエスト：IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel
     * model 1 レスポンス：IfaDomesticMutualFundOrderCancelConfirmSql001ResponseModel
     * model 2 リクエスト：IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel
     * model 2 レスポンス：IfaDomesticMutualFundOrderCancelConfirmSql002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> orderCancellationA002b(
            IfaDomesticMutualFundOrderCancelConfirmA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderCancelConfirmServiceImplL.orderCancellationA002b");
        }
        
        DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>();
        List<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>();
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // ④ API002（'NRI_FundCancelOrder1）を呼び出す。
        // 注文取消を行う。
        //   パラメタ：トランザクションID   ⇒ ""
        //                      通番       ⇒ "0000000"
        //                      年月日   ⇒ YYYYMMDD（システム日付）
        //                      部店コード3桁 ⇒ 顧客共通情報.部店コード(先頭3桁)
        //                      口座番号7桁     ⇒ 顧客共通情報.口座番号
        //                      アカウントID   ⇒ "00000000000"
        //                      アカウント毎の連番   ⇒ "0000000"
        //                      オリジン   ⇒ "0"
        //                      商品区分   ⇒ A002.ファンドタイプ
        //                      EC受注番号(元注文) ⇒ A002.EC受注番号
        //                      取消受付経路区分     ⇒ "0"：支店
        //                      取消ユーザーＩＤ     ⇒ ユーザ共通情報.CCSログイン用ID
        
        FundCancelOrder1InData api002In = new FundCancelOrder1InData();
        api002In.setTransactionId("");
        api002In.setCommandNum("0000000");
        api002In.setCommandDate(ifaDateUtil.format(IfaDateUtil.YYYYMMDD));
        api002In.setButenCd(butenCode.substring(0, 3));
        api002In.setKozaNo(accountNumber);
        api002In.setAccountId("00000000000");
        api002In.setNumber("0000000");
        api002In.setOrigin("0");
        ;
        api002In.setOrderType(dtoReq.getFundType());
        api002In.setOrderNum(dtoReq.getEcOrderNo());
        api002In.setCxlCallcenterKbn("0");
        api002In.setCxlUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
        
        FundCancelOrder1OutData api002Out = new FundCancelOrder1OutData();
        boolean successApi002 = false;
        // APIエラーハンドラ
        ApiErrorUtil apiutil = new ApiErrorUtil();
        try {
            api002Out = apiwrapper.fundCancelOrder1(api002In);
            successApi002 = true;
            if (api002Out != null) {
                // APIレスポンスチェック
                apiutil.checkApiResponse(api002Out.getShubetu(), api002Out.getCode(), api002Out.getMessage());
                if (apiutil.isFatal()) {
                    // エラーの場合
                    successApi002 = false;
                }
            }
        } catch (Exception e) {
            // API呼び出しでエラーが発生した場合、DBに受注日を登録してエラーレスポンスを返却する。
            IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel updSql002Req = new IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel();
            // ユーザーID
            updSql002Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
            // IFA注文番号
            updSql002Req.setIfaOrderNumber(dtoReq.getIfaOrderNo());
            // IFA注文サブ番号
            updSql002Req.setIfaOrderSubNumber(dtoReq.getIfaOrderSubNo());

            try {
                dao.updateIfaDomesticMutualFundOrderCancelConfirmSql002b(updSql002Req);
                
            } finally {
                dtoRes = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED)
                );

                return dtoRes;
            }
        }
        

        //   正常、エラーいずれも次の処理へ。
        // ⑤  SQL002の呼び出し
        // 注文取消後に処理結果をDBへ記録する。
        IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel updSql002Req = new IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel();
        
        // API002の実行成否
        updSql002Req.setSuccessApi002(successApi002);
        if (successApi002) {
            // EC受付時刻
            updSql002Req.setAcceptTime(api002Out.getAcceptTime());            
        }
        // EC受付年月日
        updSql002Req.setAcceptDate(api002Out.getAcceptDate());
        // 種別
        updSql002Req.setShubetu(api002Out.getShubetu());
        // エラーコード
        updSql002Req.setErrCode(api002Out.getCode());
        // エラーメッセージ
        updSql002Req.setErrMessage(api002Out.getMessage());
        // ユーザーID
        updSql002Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        // IFA注文番号
        updSql002Req.setIfaOrderNumber(dtoReq.getIfaOrderNo());
        // IFA注文サブ番号
        updSql002Req.setIfaOrderSubNumber(dtoReq.getIfaOrderSubNo());
        
        
        int updSql002Res = 0;
        try {
            updSql002Res = dao.updateIfaDomesticMutualFundOrderCancelConfirmSql002(updSql002Req);
        } catch (Exception e) {
            updSql002Res = 0;
        }
        
        // DB更新OK：次の処理へ。
        // DB更新NG：DB更新エラーを格納し次の処理へ。
        //              ⇒ エラー時の処理は⑥に記載
        
        // 
        // ⑥ 以下の表示を行う。
        if (!successApi002) {
            //    APIエラー：エラー（errors.cmn.orderExecutionCancel.failed）を表示する。
            dtoRes = apiutil.createDataList(resDtoList, ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED);
            return dtoRes;
        }
        
        IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto resDto = new IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto();
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名
        resDto.setBrandName(dtoReq.getBrandName());
        // 取引種別
        resDto.setTradeCd(dtoReq.getTradeCd());
        // 金額
        resDto.setAmount(dtoReq.getAmount());
        // 口数
        resDto.setUnit(dtoReq.getUnit());
        // 預り区分
        resDto.setDepositType(dtoReq.getDepositType());
        // 利用ポイント
        resDto.setPoint(dtoReq.getPoint());
        // ポイント種別
        resDto.setPointType(dtoReq.getPointType());
        // 受付経路区分
        resDto.setCallcenterClassification(dtoReq.getCallcenterClassification());
        // 購入解約方法
        resDto.setBuyCancelMethod(dtoReq.getBuyCancelMethod());
        // 受注日時
        resDto.setOrderDayTime(dtoReq.getOrderDayTime());
        // EC受注番号
        resDto.setEcOrderNo(dtoReq.getEcOrderNo());
        
        resDtoList.add(resDto);
        
        if (updSql002Res == 0) {
            //    DB更新エラー：API正常の場合、DB更新エラー（warnings.cmn.postOrderExecutionCancel.completed）を表示する。
            // 
            // 【メッセージ】
            // 注文取消後の注文データが更新できませんでした。注文取消は完了しています。
            apiutil.addDbError(IfaDomesticMutualFundOrderCancelConfirmServiceImpL.WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED, null);
            
        } 
        // ⑦ レスポンスを返す。
        dtoRes = apiutil.createDataList(resDtoList, "");
        return dtoRes;
        
    }
    /**
     * API002呼び出し処理
     *
     * @param refFrom     検索番号指定From
     * @param refTo       検索番号指定To
     * @param execOrder   リクエスト区分
     * @param orderNo     EC受注番号
     * @param torihikiKbn 取引区分
     * @return API結果
     * 
     */
    
    private List<QueryFundOrderWebExtDetail> callQueryFundOrderWebExtDetail(String refFrom, String refTo, ApiErrorUtil apiErrorUtil) throws Exception {
        
        QueryFundOrderWebExtOutData result = new QueryFundOrderWebExtOutData();
        QueryFundOrderWebExtInData inData = new QueryFundOrderWebExtInData();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        String kozaNo = String.format(KOZA_NO_FORMAT, cc.getAccountNumber()).replace(" ", "0");
        inData.setKozaNo(kozaNo);
        // 商品区分
        inData.setSecId(SPACE);
        // 約定注文区分
        inData.setExecOrder(SPACE);
        // 検索番号指定FROM
        inData.setRefFrom(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom)));
        // 検索番号指定TO
        inData.setRefTo(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo)));
        // 取得口座区分
        String jrIsaContractType = cc.getJrIsaContractType();
        String accountGetKbn = (StringUtils.equals(jrIsaContractType,
                                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.JR_ISA_CONTRACT_TYPE_VALUE)) ? "2" : SPACE;
        inData.setAccountGetKbn(accountGetKbn);
        
        QueryFundOrderWebExtIn input = new QueryFundOrderWebExtIn();
        input.setIndata(inData);
        
        
        // NRI_APIから買付余力情報を取得する。
        result = apiwrapper.queryFundOrderWebExtNoConvert(input);
        if (apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            return null;
        }
        
        List<QueryFundOrderWebExtDetail> resultList = new ArrayList<QueryFundOrderWebExtDetail>();
        if (result.getQueryFundOrderWebData() != null) {
            resultList.addAll(result.getQueryFundOrderWebData());
        }
        
        // 取得件数が100件ならfromとtoの値を変えて再度自身を呼ぶ
        if (API001_GETNUM.equals(result.getTotalNumber())) {
            String refFromNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom) + 100);
            String refToNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo) + 100);
            List<QueryFundOrderWebExtDetail> next100Data = callQueryFundOrderWebExtDetail(refFromNext, refToNext, apiErrorUtil);
            if (next100Data != null) {
                resultList.addAll(next100Data);
            } else {
                return null;
            }
        }
        
        return resultList;
    }

    /**
     * 取引種別の取得
     *
     * @param callcenterKbn 受付経路区分
     * @param buySell 売買区分
     * @param secId 商品区分
     * @return 取引種別
     */
    private String getTradeCd(String callcenterKbn, String buySell, String secId) {
        
        String rtnTradeCd = "";
        
        if (StringUtils.equals(callcenterKbn,
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_CALLCENTER_KBN_SELLING_VOLUME) == true) {
            
            if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_CANCEL) == true) {
                
                rtnTradeCd = "9";
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_BUY) == true) {
                
                rtnTradeCd = "5";
            }
        } else if (StringUtils.equals(callcenterKbn,
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_CALLCENTER_KBN_SELL_FIXED) == true) {
            
            if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_CANCEL) == true) {
                
                rtnTradeCd = "14";
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_BUY) == true) {
                
                rtnTradeCd = "12";
            }
        } else if (StringUtils.equals(callcenterKbn,
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_CALLCENTER_KBN_SELL_LIMIT) == true) {
            
            if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_CANCEL) == true) {
                
                rtnTradeCd = "15";
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_BUY) == true) {
                
                rtnTradeCd = "13";
            }
        } else if (StringUtils.equals(callcenterKbn,
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_CALLCENTER_KBN_ACCUMULATION_BUY) == true) {
            
            rtnTradeCd = "2";
        } else if (StringUtils.equals(callcenterKbn,
                IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_CALLCENTER_KBN_CREDIT_CARDS) == true) {
            
            rtnTradeCd = "11";
        } else {
            if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_BUY_SELL) == true) {
                if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_GENERAL) == true) {
                    
                    rtnTradeCd = "0";
                } else if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_TOTAL) == true) {
                    
                    rtnTradeCd = "1";
                }
                
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_CANCEL) == true) {
                if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_GENERAL) == true) {
                    
                    rtnTradeCd = "7";
                } else if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_TOTAL) == true) {
                    
                    rtnTradeCd = "8";
                }
                
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_SELL_BUY) == true) {
                if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_GENERAL) == true) {
                    
                    rtnTradeCd = "3";
                } else if (StringUtils.equals(secId,
                        IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_SEC_ID_TOTAL) == true) {
                    
                    rtnTradeCd = "4";
                }
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_ALL_CANCEL) == true) {
                
                rtnTradeCd = "10";
            } else if (StringUtils.equals(buySell,
                    IfaDomesticMutualFundOrderCancelConfirmServiceImpL.API001_BUY_SELL_ALL_BUY) == true) {
                
                rtnTradeCd = "6";
            }
        }
        
        return rtnTradeCd;
    }

    /**
     * 国内投信:取消表示判定を取得
     *
     * @param api001res api001の出力
     * @return result 取消表示判定(算出) 表示のときTRUE, 非表示のときFALSE
     */
    private boolean getMutualFundMutualFundCancelButtonDisplayJudgment(QueryFundOrderWebExtDetail api001res) {
        
        boolean result = false;
        // 注文ステータス
        if (!StringUtils.equals(api001res.getOrderStatus(), API001_ORDER_STATUS_VALUE)) {
            return result;
        }
        // 注文取消区分
        if (!StringUtils.equals(api001res.getCxlOrderId(), API001_CXL_ORDER_ID_VALUE)) {
            return result;
        }
        // 約定区分
        if (!StringUtils.equals(api001res.getExecStatus(), API001_EXEC_STATUS_VALUE)) {
            return result;
        }
        // 取消ステータス
        if (StringUtils.equals(api001res.getCxlStatus(), API001_CXL_STATUS_VALUE_NOT_CANCELL)) {
            return true;
        }
        if (!StringUtils.equals(api001res.getCxlStatus(), API001_CXL_STATUS_VALUE_ACCEPTED)) {
            return result;
        }
        // 受付経路区分
        if (StringUtils.equals(api001res.getCallcenterKbn(), API001_CALLCENTER_KBN_SELLING_VOLUME)
                || StringUtils.equals(api001res.getCallcenterKbn(), API001_CALLCENTER_KBN_SELL_FIXED)
                || StringUtils.equals(api001res.getCallcenterKbn(), API001_CALLCENTER_KBN_SELL_LIMIT)
                || StringUtils.equals(api001res.getCallcenterKbn(), API001_CALLCENTER_KBN_ACCUMULATION_BUY)
                || StringUtils.equals(api001res.getCallcenterKbn(), API001_CALLCENTER_KBN_CREDIT_CARDS)) {
            return result;
        }
        result = true;
        return result;
    }

}
