package com.sbisec.helios.ap.bizcommon.util;

import java.text.ParseException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.util.dto.CorrectOrderStatusDto;
import com.sbisec.helios.ap.common.enums.AccountType;
import com.sbisec.helios.ap.common.enums.MarginTradeKbn;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;

/**
 * 訂正/取消ボタン表示の判定
 * 画面定義書_SUB0202_0104-01_注文状況一覧.xlsx シート「別紙.国内株式_注文ステータス」参照
 *
 * @author SCSK
 */
@Component
public class OrderStatusUtil {
    
    /** API:RBE注文ステータス 待機中 */
    private static final String RBE_ORDER_STATUS_WAITING = "2";
    
    /** API:RBE注文ステータス RBE未提出 */
    private static final String RBE_ORDER_STATUS_NOT_SUBMITTED = "3";
    
    /** API:RBE注文ステータス RBE応答結果待ち */
    private static final String RBE_ORDER_STATUS_WAITING_FOR_RESPONSE = "4";
    
    /** API:RBE訂正ステータス ACCEPTED */
    private static final String RBE_MOD_STATUS_ACCEPTED = "1";
    
    /** API:RBE訂正ステータス REJECTED */
    private static final String RBE_MOD_STATUS_REJECTED = "3";
    
    /** API:RBE訂正ステータス △ */
    private static final String RBE_MOD_STATUS_CLEAR = " ";
    
    /** API:RBE取消ステータス REJECTED */
    private static final String RBE_CXL_STATUS_REJECTED = "3";
    
    /** API:RBE取消ステータス △ */
    private static final String RBE_CXL_STATUS_CLEAR = " ";
    
    /** API:商品区分 株式 */
    private static final String SEC_ID_STOCK = "S";
    
    /** API:商品区分 現引現渡 */
    private static final String SEC_ID_GENHIKI = "G";
    
    /** API:訂正ステータス ACCEPTED */
    private static final String MOD_STATUS_ACCEPTED = "1";
    
    /** API:訂正ステータス REJECTED */
    private static final String MOD_STATUS_REJECTED = "3";
    
    /** API:訂正ステータス △ */
    private static final String MOD_STATUS_CLEAR = " ";
    
    /** API:取消ステータス REJECTED */
    private static final String CXL_STATUS_REJECTED = "3";
    
    /** API:取消ステータス △ */
    private static final String CXL_STATUS_CLEAR = " ";
    
    /** API:RBE注文種別 通常注文*/
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API:RBE注文種別 逆指値注文*/
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** API:RBE注文種別 OCO注文*/
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** 注文ステータス Accepted(訂正結果OK/訂正･取消受付け可の状態) */
    private static final String ORDER_STATUS_ACCEPTED = "1";
    
    /** 注文ステータス Deferred(訂正を受取り､未送出の状態) */
    private static final String ORDER_STATUS_DEFERRED = "2";
    
    /** 注文ステータス　商品区部=Gの際の固定値 */
    private static final String ORDER_STATUS_CLEAR = " ";
    
    /** WEB注文ステータスの出力値 "3":REJECTED */
    private static final String WEB_ORDER_STATUS_REJECTED = "3";
    
    /** API 強制区分の出力値 "0" */
    private static final String FORCEKBN_DEFAULT = "0";
    
    /** API 執行ステータスの出力値 "△":(有効) */
    private static final String REJECTSTATUS_ACTIVE = " ";
    
    /** API 約定ステータスの出力値 "2":全部出来 */
    private static final String EXEC_STATUS_ALL = "2";
    
    /** API RBE注文ステータス " ":通常注文 */
    private static final String RBE_ORDER_STATUS_NORMAL = " ";
    
    /** API RBE注文ステータス "1":発火済み */
    private static final String RBE_ORDER_STATUS_STOP = "1";
    
    /** 区分値:API.リクエスト区分.受注番号指定 */
    private static final String CODE_VAL_EXEC_ORDER_ORDER_NO = "2";
    
    /** 区分値:API.取引区分.現物信用注文明細（上場CW含まない） */
    private static final String CODE_VAL_TORIHIKI_KBN_STOCK_MARGIN_WO_CW = "1";
    
    /** 区分値:API.約定取得区分.約定明細取得 */
    private static final String CODE_VAL_TRADE_GET_KBN_GET_CONTRACT = "1";
    
    /** パディング長:検索番号指定(5桁) */
    private static final int PADDING_LENGTH_REF_NUMBER = 5;
    
    /** パディング長:銘柄コード(5桁) */
    private static final int PADDING_LENGTH_BRAND_CD = 5;
    
    /** パディング長:EC受注番号(6桁) */
    private static final int PADDING_LENGTH_EC_ORDER_NO = 6;
    
    /** パディング用Char'0'. */
    private static final char PADDING_CHAR_ZERO = '0';
    
    /** 区分値:API.弁済期限.一般信用売り-短期銘柄 */
    private static final String CODE_VAL_PAYMENT_LIMIT_SHORT_TERM = "A";
    
    /** 区分値:API.弁済期限.一般信用売り-長期在庫制限有り銘柄 */
    private static final String CODE_VAL_PAYMENT_LIMIT_LONG_TERM_INVENTORY_LIMITED = "B";
    
    /** API: 一般信用売弁済期限年月日区分の出力値 "△"：無期限 */
    private static final String CODE_VAL_IPPAN_MG_PAYMENTKBN_UNLIMITED = StringUtils.SPACE;
    
    /** 国内株式:信用取引区分 年 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_YEAR = "年";
    
    /** 国内株式:信用取引区分 ヶ月 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_MONTH = "ヶ月";
    
    /** 国内株式:信用取引区分 日 */
    private static final String DOMESTIC_MARGIN_TRADE_TERM_DAY = "日";
    
    @Autowired
    private NriApiService nriApiService;
    
    /**
     * 訂正ボタン表示判定結果株式統合注文一覧照会(ポイント)レスポンスを取得する.
     *
     * @param ecOrderNo EC受注番号
     * @return 訂正ボタン表示判定結果、株式統合注文一覧照会(ポイント)レスポンス
     * @throws Exception システムエラー
     */
    public CorrectOrderStatusDto isCorrectOrderWithApiResponse(String ecOrderNo) throws Exception {
        
        QueryStockUniteOrderPointOutData output = callNriQueryStockUniteOrderPoint(ecOrderNo);
        if (output == null || output.getReqOrderDataExe() == null || output.getReqOrderDataExe().isEmpty()) {
            return null;
        }
        final Optional<QueryStockUniteOrderPointOrd> optionalReqOrder = output.getReqOrderDataExe().stream()
                .filter(data -> StringUtils.equals(data.getOrderNo(), ecOrderNo)).findFirst();
        if (optionalReqOrder.isEmpty()) {
            return null;
        }
        return new CorrectOrderStatusDto(output, optionalReqOrder.filter(this::canCorrectOrder).isPresent());
    }

    /**
     * 訂正ボタン表示判定
     * 訂正ボタンが表示されるか否かを判定する
     *
     * @param reqOrderData API.注文部(point)
     * @return 訂正ボタン表示：true、非表示：false
     */
    public boolean canCorrectOrder(QueryStockUniteOrderPointOrd reqOrderData) {
        
        // 国内株式：注文状況
        if (reqOrderData.getSecId().equals(SEC_ID_STOCK) // 商品区分=S(株式)
                && (reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_NORMAL) // RBE注文種別 = "△△△"(通常注文)
                        || ((reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_SLO) // RBE注文種別 == "SLO"(逆指値注文)
                                || reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_OCO) // RBE注文種別 == "OCO"(OCO注文)
                        ) && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NORMAL) // RBE注文ステータス == "△"(通常注文)
                                || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_STOP) // RBE注文ステータス == "1" (発火済み)
                        ))) && reqOrderData.getForceKbn().equals(FORCEKBN_DEFAULT) // 強制区分 == "0"(通常)
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス　!= "3"(REJECTED)
                && (reqOrderData.getModStatus().equals(MOD_STATUS_ACCEPTED) // 訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_REJECTED) // 訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_CLEAR) // 訂正ステータス == "△"
                ) && (reqOrderData.getCxlStatus().equals(CXL_STATUS_REJECTED) // 取消ステータス == "3"(REJECTED)
                        || reqOrderData.getCxlStatus().equals(CXL_STATUS_CLEAR) // 取消ステータス == "△"
                )) {
            return true;
        }
        
        // 逆指値注文
        if (reqOrderData.getSecId().equals(SEC_ID_STOCK) // 商品区分=S(株式)
                && reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_SLO) // RBE注文種別 == "SLO"(逆指値注文)
                // RBE注文ステータス≠通常注文 かつ RBE注文ステータス≠発火済みは後続の条件より明らかなため省略
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス != "3"(REJECTED)
                && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING) // RBE注文ステータス == "2"(待機中)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NOT_SUBMITTED) // RBE注文ステータス == "3"(RBE未提出)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING_FOR_RESPONSE) // RBE注文ステータス == "4"(RBE応答結果待ち)
                ) && (reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_ACCEPTED) // RBE訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_REJECTED) // RBE訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_CLEAR) // RBE訂正ステータス == "△"
                ) && (reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_REJECTED) // RBE取消ステータス　== "3"(REJECTED)
                        || reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_CLEAR) // RBE取消ステータス == "△"
                )) {
            return true;
        }
        
        // OCO注文
        if (reqOrderData.getSecId().equals(SEC_ID_STOCK) // 商品区分=5(株式)
                && reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_OCO) // RBE注文種別 == "OCO"(OCO注文)
                // RBE注文ステータス≠通常注文 かつ RBE注文ステータス≠発火済みは後続の条件より明らかなため省略
                && reqOrderData.getForceKbn().equals(FORCEKBN_DEFAULT) // 強制区分 == "0"(通常)
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス != "3"(REJECTED)
                && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING) // RBE注文ステータス == "2"(待機中)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NOT_SUBMITTED) // RBE注文ステータス == "3"(RBE未提出)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING_FOR_RESPONSE) // RBE注文ステータス == "4"(RBE応答結果待ち)
                ) && (reqOrderData.getCxlStatus().equals(CXL_STATUS_REJECTED) // 取消ステータス == "3"(REJECTED)
                        || reqOrderData.getCxlStatus().equals(CXL_STATUS_CLEAR) // 取消ステータス == "△"
                ) && (reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_REJECTED) // RBE取消ステータス　== "3"(REJECTED)
                        || reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_CLEAR) // RBE取消ステータス == "△"
                ) && (reqOrderData.getModStatus().equals(MOD_STATUS_ACCEPTED) // 訂正ステータス　== "1"(ACCEPTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_REJECTED) // 訂正ステータス　== "3"(REJECTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_CLEAR) // 訂正ステータス == "△"
                ) && (reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_ACCEPTED) // RBE訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_REJECTED) // RBE訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_CLEAR) // RBE訂正ステータス == "△"
                )) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 取消ボタン表示判定結果と株式統合注文一覧照会(ポイント)レスポンスを取得する.
     *
     * @param ecOrderNo EC受注番号
     * @return 取消ボタン表示判定結果、株式統合注文一覧照会(ポイント)レスポンス
     * @throws Exception システムエラー
     */
    public CorrectOrderStatusDto isCancelOrderWithApiResponse(String ecOrderNo) throws Exception {
        
        QueryStockUniteOrderPointOutData output = callNriQueryStockUniteOrderPoint(ecOrderNo);
        if (output == null || output.getReqOrderDataExe() == null || output.getReqOrderDataExe().isEmpty()) {
            return null;
        }
        final Optional<QueryStockUniteOrderPointOrd> optionalReqOrder = output.getReqOrderDataExe().stream()
                .filter(data -> StringUtils.equals(data.getOrderNo(), ecOrderNo)).findFirst();
        if (optionalReqOrder.isEmpty()) {
            return null;
        }
        return new CorrectOrderStatusDto(output, optionalReqOrder.filter(this::canCancelOrder).isPresent());
    }

    /**
     * 取消ボタン表示判定
     * 取消ボタンが表示されるか否かを判定する
     *
     * @param reqOrderData API001.注文部(point)
     * @return 取消ボタン表示：true、非表示：false
     */
    public boolean canCancelOrder(QueryStockUniteOrderPointOrd reqOrderData) {
        
        // 国内株式：注文状況
        if (reqOrderData.getSecId().equals(SEC_ID_STOCK) // 商品区分=5(株式)
                && (reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_NORMAL) // RBE注文種別 = "△△△"(通常注文)
                        || ((reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_SLO) // RBE注文種別 == "SLO"(逆指値注文)
                                || reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_OCO) // RBE注文種別 == "OCO"(OCO注文)
                        ) && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NORMAL) // RBE注文ステータス == "△"(通常注文)
                                || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_STOP) // RBE注文ステータス == "1" (発火済み)
                        ))) && reqOrderData.getForceKbn().equals(FORCEKBN_DEFAULT) // 強制区分 == "0"(通常)
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス　!= "3"(REJECTED)
                && (reqOrderData.getModStatus().equals(MOD_STATUS_ACCEPTED) // 訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_REJECTED) // 訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_CLEAR) // 訂正ステータス == "△"
                ) && (reqOrderData.getCxlStatus().equals(CXL_STATUS_REJECTED) // 取消ステータス == "3"(REJECTED)
                        || reqOrderData.getCxlStatus().equals(CXL_STATUS_CLEAR) // 取消ステータス == "△"
                )) {
            return true;
        }
        
        // 逆指値注文
        if (reqOrderData.getSecId().equals(SEC_ID_STOCK) // 商品区分=5(株式)
                && reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_SLO) // RBE注文種別 == "SLO"(逆指値注文)
                // RBE注文ステータス≠通常注文 かつ RBE注文ステータス≠発火済みは後続の条件より明らかなため省略
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス != "3"(REJECTED)
                && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING) // RBE注文ステータス == "2"(待機中)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NOT_SUBMITTED) // RBE注文ステータス == "3"(RBE未提出)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING_FOR_RESPONSE) // RBE注文ステータス == "4"(RBE応答結果待ち)
                ) && (reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_ACCEPTED) // RBE訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_REJECTED) // RBE訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_CLEAR) // RBE訂正ステータス == "△"
                ) && (reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_REJECTED) // RBE取消ステータス　== "3"(REJECTED)
                        || reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_CLEAR) // RBE取消ステータス == "△"
                )) {
            return true;
        }
        
        // OCO注文
        if (SEC_ID_STOCK.equals(reqOrderData.getSecId()) // 商品区分=5(株式)
                && reqOrderData.getRbeOrderKind().equals(RBE_ORDER_KIND_OCO) // RBE注文種別 == "OCO"(OCO注文)
                // RBE注文ステータス≠通常注文 かつ RBE注文ステータス≠発火済みは後続の条件より明らかなため省略
                && reqOrderData.getForceKbn().equals(FORCEKBN_DEFAULT) // 強制区分 == "0"(通常)
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス = "△"(有効)
                && !reqOrderData.getExecStatus().equals(EXEC_STATUS_ALL) // 約定ステータス != "2"(全部出来)
                && isSameDate(reqOrderData) && !reqOrderData.getWebOrderStatus().equals(WEB_ORDER_STATUS_REJECTED) // WEB注文ステータス != "3"(REJECTED)
                && (reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING) // RBE注文ステータス == "2"(待機中)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_NOT_SUBMITTED) // RBE注文ステータス == "3"(RBE未提出)
                        || reqOrderData.getRbeOrderStatus().equals(RBE_ORDER_STATUS_WAITING_FOR_RESPONSE) // RBE注文ステータス == "4"(RBE応答結果待ち)
                ) && (reqOrderData.getCxlStatus().equals(CXL_STATUS_REJECTED) // 取消ステータス == "3"(REJECTED)
                        || reqOrderData.getCxlStatus().equals(CXL_STATUS_CLEAR) // 取消ステータス == "△"
                ) && (reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_REJECTED) // RBE取消ステータス　== "3"(REJECTED)
                        || reqOrderData.getRbeCxlStatus().equals(RBE_CXL_STATUS_CLEAR) // RBE取消ステータス == "△"
                ) && (reqOrderData.getModStatus().equals(MOD_STATUS_ACCEPTED) // 訂正ステータス　== "1"(ACCEPTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_REJECTED) // 訂正ステータス　== "3"(REJECTED)
                        || reqOrderData.getModStatus().equals(MOD_STATUS_CLEAR) // 訂正ステータス == "△"
                ) && (reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_ACCEPTED) // RBE訂正ステータス == "1"(ACCEPTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_REJECTED) // RBE訂正ステータス == "3"(REJECTED)
                        || reqOrderData.getRbeModStatus().equals(RBE_MOD_STATUS_CLEAR) // RBE訂正ステータス == "△"
                )) {
            return true;
        }
        
        // 現引・現渡注文
        if (reqOrderData.getSecId().equals(SEC_ID_GENHIKI) // 商品区分 == "G"(現引現渡)
                && reqOrderData.getRejectStatus().equals(REJECTSTATUS_ACTIVE) // 失効ステータス =="△"(有効)
                && (reqOrderData.getOrderStatus().equals(ORDER_STATUS_ACCEPTED) // 注文ステータス == "1"(Accepted)
                        || reqOrderData.getOrderStatus().equals(ORDER_STATUS_DEFERRED) // 注文ステータス == "2"(Deffered)
                        || reqOrderData.getOrderStatus().equals(ORDER_STATUS_CLEAR) // 注文ステータス == "△"
                ) && (reqOrderData.getCxlStatus().equals(CXL_STATUS_REJECTED) // 取消ステータス == "3"(Rejected)
                        || reqOrderData.getCxlStatus().equals(CXL_STATUS_CLEAR) // 取消ステータス == "△"
                ) && reqOrderData.getForceKbn().equals(FORCEKBN_DEFAULT) // 強制区分 = "0"(通常)
        ) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 信用取引区分を算出する.
     *
     * @param paymentLimit API.注文部.弁済期限
     * @param paymentKbn API.注文部.一般信用売弁済期限年月日区分
     * @param mgPaymentLimit API.注文部.一般信用売弁済期限年月日数
     * @return 信用取引区分
     */
    public String getMarginTradeType(String paymentLimit, String paymentKbn, String mgPaymentLimit) {
        
        MarginTradeKbn kbn = MarginTradeKbn.valueOfKbn(paymentLimit, paymentKbn);
        if (kbn == null) {
            return StringUtils.SPACE;
        }
        if (StringUtils.equalsAny(kbn.getPaymentLimit(), new String[] { CODE_VAL_PAYMENT_LIMIT_SHORT_TERM,
                CODE_VAL_PAYMENT_LIMIT_LONG_TERM_INVENTORY_LIMITED })) {
            // 弁済期限が"A"(一般信用売り-短期銘柄)、"B"(一般信用売り-長期在庫制限有り銘柄)の場合
            if (StringUtils.equals(kbn.getPaymentKbn(), CODE_VAL_IPPAN_MG_PAYMENTKBN_UNLIMITED)) {
                // 一般信用売弁済期限年月日区分が無期限の場合はそのまま出力する
                return kbn.getMarginTradeKbn();
            }
            // 一般信用売弁済期限年月日区分が無期限以外の場合は、一般信用売弁済期限年月日数を編集して設定する
            if (kbn == MarginTradeKbn.LONG_TERM_YEAR || kbn == MarginTradeKbn.SHORT_TERM_YEAR) {
                return Integer.valueOf(mgPaymentLimit.trim()).toString().concat(DOMESTIC_MARGIN_TRADE_TERM_YEAR);
            } else if (kbn == MarginTradeKbn.LONG_TERM_MONTH || kbn == MarginTradeKbn.SHORT_TERM_MONTH) {
                return Integer.valueOf(mgPaymentLimit.trim()).toString().concat(DOMESTIC_MARGIN_TRADE_TERM_MONTH);
            } else if (kbn == MarginTradeKbn.LONG_TERM_DAY || kbn == MarginTradeKbn.SHORT_TERM_DAY) {
                return Integer.valueOf(mgPaymentLimit.trim()).toString().concat(DOMESTIC_MARGIN_TRADE_TERM_DAY);
            }
        }
        // 弁済期限が"A"、"B"以外の場合
        return kbn.getMarginTradeKbn();
    }

    
    /**
     * 有効期限 ≧ マーケット発注日か判定します.
     *
     * @param reqOrderData API001.注文部(point)
     * @return マーケット発注日と有効期限が同一
     */
    private boolean isSameDate(QueryStockUniteOrderPointOrd reqOrderData) {
        
        try {
            return DateUtil.compareDatesByYMD(DateUtil.parse(reqOrderData.getMktOrderDate(), DateUtil.YYYYMMDD),
                    DateUtil.parse(reqOrderData.getLimit(), DateUtil.YYYYMMDD)) <= 0;
        } catch (ParseException e) {
            return false;
        }
    }

     /**
     * 株式統合注文一覧照会(ポイント) （NRI_QueryStockUniteOrderPoint）を呼び出す.
     *
     * @param ecOrderNo EC受注番号
     * @return 株式統合注文一覧照会(ポイント) レスポンス
     * @throws Exception システムエラー
     */
    private QueryStockUniteOrderPointOutData callNriQueryStockUniteOrderPoint(String ecOrderNo) throws Exception {
        
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        QueryStockUniteOrderPointInData inputData = new QueryStockUniteOrderPointInData();
        inputData.setButenCd(cc.getButenCode());
        inputData.setKozaNo(AccountUtil.formatToApi(cc.getAccountNumber()));
        inputData.setExecOrder(CODE_VAL_EXEC_ORDER_ORDER_NO);
        inputData.setRefFrom(StringUtils.repeat(StringUtils.SPACE, PADDING_LENGTH_REF_NUMBER));
        inputData.setRefTo(StringUtils.repeat(StringUtils.SPACE, PADDING_LENGTH_REF_NUMBER));
        inputData.setOrderNo(StringUtils.leftPad(ecOrderNo, PADDING_LENGTH_EC_ORDER_NO, PADDING_CHAR_ZERO));
        inputData.setTorihikiKbn(CODE_VAL_TORIHIKI_KBN_STOCK_MARGIN_WO_CW);
        inputData.setTradeGetKbn(CODE_VAL_TRADE_GET_KBN_GET_CONTRACT);
        inputData.setBrandCd(StringUtils.EMPTY);
        inputData.setAccountGetKbn(AccountType.WHOLE.getId());
        
        return nriApiService.queryStockUniteOrderPointForOrderStatusList(inputData, false);
    }
}
