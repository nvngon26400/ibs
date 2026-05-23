package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct023;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct023Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql021ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql022ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.JpToushinOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TJpOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TbAdditionalOrderIfaBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.DailyCreditKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.DateKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.DoneOcoSasinariKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.DoneSasinariKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.DoneTriggerZone;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.JtoAzukariKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.JtoTradeKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.Market;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.OcoSasinariKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.OpenMarket;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.PaymentLimit;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.SasinariKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TjoTradeKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TriggerZone;
import com.sbisec.helios.ap.ccsApi.util.CcsApiErrUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import lombok.Data;

/**
 * 接触履歴の共通部品
 * 
 * @author趙韫慧
 */
@Component
public class IfaContactUtil {

    // 国内株式情報取得
    @Autowired
    private Fct027 fct027;
    // 国内投信銘柄マスタ取得
    @Autowired
    private Fct023 fct023;
    // DAO
    @Autowired
    private IfaContactDao ifaContactDao;

    /** 全角スペース */
    public static final String FULL_SPACE = "　";
    /** 半角スペース */
    public static final String HALF_SPACE = "  ";
    /** 円 */
    public static final String YEN = "円";
    /** 半角ハイフン */
    public static final String HALF_HYPHEN = "-";
    /** 全角ハイフン */
    public static final String FULL_HYPHEN = "－";

    /** ZERO:0 */ 
    public static final String ZERO = "0";

    /** 総合口座 */
    public static final String INTEGRATED_ACCOUNT = "総合口座";

    /** Vポイント適用日 */
    public static final String POINT_APPLICABLE_DATE = "2023/04/28 00:00:00";

    /** 参照元履歴区分:CCS履歴:1 */
    public static final String REFERENCE_KBN_1 = "1";
    /** 参照元履歴区分:問合せ履歴:2 */
    public static final String REFERENCE_KBN_2 = "2";
    /** 参照元履歴区分:書類請求当日履歴:3 */
    public static final String REFERENCE_KBN_3 = "3";
    /** 参照元履歴区分:国内注文当日履歴:4 */
    public static final String REFERENCE_KBN_4 = "4";
    /** 参照元履歴区分:外株委託当日履歴:7 */
    public static final String REFERENCE_KBN_7 = "7";
    /** 参照元履歴区分:外株店頭当日履歴:8 */
    public static final String REFERENCE_KBN_8 = "8";
    /** 参照元履歴区分:為替取引当日履歴:9 */
    public static final String REFERENCE_KBN_9 = "9";

    /** 中分類:その他余力拘束 */
    public static final String MID_CLASS = "その他余力拘束";

    /** 機能区分:接触履歴 */
    public static final String IFA_CONTACT = "ifaContact";
    /** 機能区分:接触履歴受付詳細 */
    public static final String IFA_CONTACT_ACCEPT_DETAIL = "ifaContactAcceptDetail";

    /** (T_JP_ORDER)注文種別 */
    public class OrderSyubetsu {
        /** '1'(通常/逆指値) */
        public static final String TJO_ORDERSYUBETSU_NORMAL_PRICE = "1";
        /** '2'(OCO注文) */
        public static final String TJO_ORDERSYUBETSU_OCO = "2";
        /** '3'(IFD注文) */
        public static final String TJO_ORDERSYUBETSU_IFD = "3";
        /** '4'(IFDOCO注文) */
        public static final String TJO_ORDERSYUBETSU_IFDOCO = "4";
    }

    /** RBE注文種別 */
    public class RbeOrderKind {
        /** 通常注文 */
        public static final String TJO_NORMAL = "   ";
        /** 逆指値注文 */
        public static final String TJO_STOP = "SLO";
    }
    /** 預り区分 */
    public class TjoAzukariKbn {
        /** 0 */
        public static final String TJO_AZUKARI_KBN_0 = "0";
        /** 5 */
        public static final String TJO_AZUKARI_KBN_5 = "5";
        /** 6 */
        public static final String TJO_AZUKARI_KBN_6 = "6";
        /** H */
        public static final String TJO_AZUKARI_KBN_H = "H";
        /** J */
        public static final String TJO_AZUKARI_KBN_J = "J";
        /** 4 */
        public static final String TJO_AZUKARI_KBN_4 = "4";
        /** 7 */
        public static final String TJO_AZUKARI_KBN_7 = "7";
    }

    /** DONERBE注文種別 */
    public class DoneRbeOrderKind {
        /** 通常注文 */
        public static final String TJO_DONE_NORMAL = "   ";
        /** 逆指値注文 */
        public static final String TJO_DONE_STOP = "SLO";
    }

    /** 特定口座区分 */
    public class SpecificKbn {
        /** 特定口座(代行納付) */
        public static final String TJO_PROXY_PAYMENT = "1";
        /** 特定口座(確定申告) */
        public static final String TJO_CONFIRM_DECLARATION = "2";
    }

    /** 注文状況 */
    public class OrderStatus {
        /** 新規(発注):0 */
        public static final String TJO_ORDER_STATUS_0 = "0";
        /** 訂正:1 */
        public static final String TJO_ORDER_STATUS_1 = "1";
        /** 取消:2 */
        public static final String TJO_ORDER_STATUS_2 = "2";
    }

    /** (JP_TOUSHIN_ORDER)ポイント種別 */
    public class PointType {
        /** 1 */
        public static final String POINTTYPE_1 = "1";
        /** 2 */
        public static final String POINTTYPE_2 = "2";
        /** 6 */
        public static final String POINTTYPE_6 = "6";
    }

    /** 乗換優遇区分 */
    public class NorikaeYuguKbn {
        /** 1 */
        public static final String NORIKAEYUGU_KBN = "1";
    }

    /** 取引種別 */
    public class TradeType {
        /** 購入(一般):0 */
        public static final String TRADE_TYPE_0 = "0";
        /** 買取(一般):3 */
        public static final String TRADE_TYPE_3 = "3";
        /** 解約(一般):7 */
        public static final String TRADE_TYPE_7 = "7";
        /** 購入(累投):1 */
        public static final String TRADE_TYPE_1 = "1";
        /** 買取(累投):4 */
        public static final String TRADE_TYPE_4 = "4";
        /** 全額買取:6 */
        public static final String TRADE_TYPE_6 = "6";
        /** 解約(累投):8 */
        public static final String TRADE_TYPE_8 = "8";
        /** 全額解約:10 */
        public static final String TRADE_TYPE_10 = "10";
    }

    /** (TB_ADDITIONAL_ORDER_IFA)拘束種別 */
    public class KousokuSyubetu {
        /** 4 */
        public static final String KOUSOKUSYUBETU_4 = "4";
        /** 5 */
        public static final String KOUSOKUSYUBETU_5 = "5";
        /** 6 */
        public static final String KOUSOKUSYUBETU_6 = "6";
        /** 7 */
        public static final String KOUSOKUSYUBETU_7 = "7";
    }

    /** 拘束区分 */
    public class KousokuKbn {
        /** 半角スペース */
        public static final String KOUSOKUKBN_SPACE = " ";
    }

    /** 特定預り売買区分 */
    public class TokuteiAzukariBaibaiKbn {
        /** 1 */
        public static final String TOKUTEIAZUKARIBAIBAIKBN_1 = "1";
    }

    /** 取消区分 */
    public class TorikeshiKbn {
        /** 取消:1 */
        public static final String TORIKESHIKBN_1 = "1";
    }

    /**
     * 内容を取得する.
     * 
     * @param tJpOrderBaseModel 編集用基本モデル
     * @param jrIsaContractType ジュニアISA契約区分
     * @param functionKbn       機能区分
     * @return contents 内容
     */
    public String getContentsFor002(TJpOrderBaseModel tJpOrderBaseModel, String jrIsaContractType, String functionKbn)
            throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 注文種別
        String orderSyubetsu = tJpOrderBaseModel.getOrderSyubetsu();
        // 市場
        String market = tJpOrderBaseModel.getMarket();
        // RBE注文種別
        String rbeOrderKind = tJpOrderBaseModel.getRbeOrderKind();
        // 指成区分
        String sasinariKbn = tJpOrderBaseModel.getSasinariKbn();
        // 指値
        String price = tJpOrderBaseModel.getPrice();
        // トリガ発動ゾーン
        String triggerZone = tJpOrderBaseModel.getTriggerZone();
        // トリガ値段
        String triggerPrice = tJpOrderBaseModel.getTriggerPrice();
        // OCO指成区分
        String ocoSasinariKbn = tJpOrderBaseModel.getOcoSasinariKbn();
        // OCO指値
        String ocoSashine = tJpOrderBaseModel.getOcoSashine();
        // 数量
        String quantity = tJpOrderBaseModel.getQuantity();
        // DONERBE注文種別
        String doneRbeOrderKind = tJpOrderBaseModel.getDoneRbeOrderKind();
        // DONE指値
        String doneSashine = tJpOrderBaseModel.getDoneSashine();
        // DONEトリガ値段
        String doneTriggerNedan = tJpOrderBaseModel.getDoneTriggerNedan();
        // DONEトリガ発動ゾーン
        String doneTriggerZone = tJpOrderBaseModel.getDoneTriggerZone();
        // DONE指成区分
        String doneSasinariKbn = tJpOrderBaseModel.getDoneSasinariKbn();
        // DONEOCO指成区分
        String doneOcoSasinariKbn = tJpOrderBaseModel.getDoneOcoSasinariKbn();
        // DONEOCO指値
        String doneOcoSashine = tJpOrderBaseModel.getDoneOcoSashine();
        // 預り区分
        String azukariKbn = tJpOrderBaseModel.getAzukariKbn();

        // 市場(内容作成用)
        String marketDisp = StringUtil.EMPTY_STRING;
        // 指成区分(内容作成用)
        String sasinariKbnDisp = StringUtil.EMPTY_STRING;
        // 指値(内容作成用)
        String priceDisp = StringUtil.EMPTY_STRING;
        // DONE指成区分(内容作成用)
        String doneSasinariKbnDisp = StringUtil.EMPTY_STRING;
        // DONE指値(内容作成用)
        String doneSashineDisp = StringUtil.EMPTY_STRING;
        // 特定預り売買区分(内容作成用)
        String buySellKbnDisp = StringUtil.EMPTY_STRING;
        // 注文株数(内容作成用)
        String quantityDisp = StringUtil.EMPTY_STRING;

        // 内容作成用の共通の項目を取得する
        // 銘柄コード(内容作成用)を取得する
        String brandCd = tJpOrderBaseModel.getBrandCd();
        // 銘柄名(内容作成用)を取得する
        // 銘柄の取引注意情報を取得する。
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コードをセット
        fct027Req.setBrandCode(brandCd);
        // 銘柄名(内容作成用)
        String brandNm = fct027.getData(fct027Req).getBrandName();

        // 注文株数(内容作成用)を取得する
        // '数量:'||trim(to_char(数量,'999,999,999,999,990'))
        quantityDisp = getQuantityDisp(quantity);

        // ユーザ名
        String userNm = tJpOrderBaseModel.getUserNm();

        if (OrderSyubetsu.TJO_ORDERSYUBETSU_NORMAL_PRICE.equals(orderSyubetsu)) {
            // 通常注文
            // 注文種別＝'1'(通常/逆指値)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor002(market, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.特定預り売買区分(内容作成用)を取得する
            buySellKbnDisp = getBuySellKbnDisp(azukariKbn, jrIsaContractType);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = 銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分
                contents = brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp)
                        + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp) + getDefaultVal(buySellKbnDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分+"  "+"＊媒介"
                contents = userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(buySellKbnDisp) + HALF_SPACE + "＊媒介";
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_OCO.equals(orderSyubetsu)) {
            // OCO注文
            // 注文種別＝'2'(OCO注文)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor002(market, orderSyubetsu);

            // 2.価格／OCO1(内容作成用)を取得する
            String priceOco1Disp = getPriceOco1Disp(price, sasinariKbn);

            // 3.条件／OCO2(内容作成用)を取得する
            String conditionOco2Disp = getConditionOco2Disp(triggerZone, triggerPrice, sasinariKbn, ocoSasinariKbn,
                    ocoSashine);

            // 4.特定預り売買区分(内容作成用)を取得する
            buySellKbnDisp = getBuySellKbnDisp(azukariKbn, jrIsaContractType);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = 銘柄コード+"　"+銘柄名+"　"+市場+"　"+価格／OCO1+"　"+条件／OCO2+"　"+注文株数+"　"+特定預り売買区分
                contents = brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp) + getDefaultVal(priceOco1Disp)
                        + getDefaultVal(conditionOco2Disp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(buySellKbnDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+価格／OCO1+"　"+条件／OCO2+"　"+注文株数+"　"+特定預り売買区分+"  "+"＊媒介"
                contents = userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(priceOco1Disp) + getDefaultVal(conditionOco2Disp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(buySellKbnDisp) + HALF_SPACE + "＊媒介";
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFD.equals(orderSyubetsu)) {
            // IFD注文
            // 注文種別＝'3'(IFD)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor002(market, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.特定預り売買区分(内容作成用)を取得する
            buySellKbnDisp = getBuySellKbnDisp(azukariKbn, jrIsaContractType);

            // 5.DONE指成区分(内容作成用)を取得する
            doneSasinariKbnDisp = getDoneSasinariKbnDisp(doneRbeOrderKind, doneSasinariKbn);

            // 6.DONE指値(内容作成用)を取得する
            doneSashineDisp = getDoneSashineDisp(doneRbeOrderKind, doneSashine, doneTriggerNedan, doneTriggerZone,
                    doneSasinariKbn);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = "IFD1:"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分+"<br>"
                // "IFD2:"+"　"+"株式売却"+"　"+DONE指成区分+"　"+DONE指値
                contents = "IFD1:" + brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(buySellKbnDisp) + "<br>" + "IFD2:" + FULL_SPACE + "株式売却"
                        + getDefaultVal(doneSasinariKbnDisp) + getDefaultVal(doneSashineDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = "IFD1:"+ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分+"  "+"＊媒介"+"<br>"
                // "IFD2:"+"　"+"株式売却"+"　"+DONE指成区分+"　"+DONE指値
                contents = "IFD1:" + userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm)
                        + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp)
                        + getDefaultVal(quantityDisp) + getDefaultVal(buySellKbnDisp) + HALF_SPACE + "＊媒介" + "<br>"
                        + "IFD2:" + FULL_SPACE + "株式売却" + getDefaultVal(doneSasinariKbnDisp)
                        + getDefaultVal(doneSashineDisp);
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFDOCO.equals(orderSyubetsu)) {
            // IFDOCO注文
            // 注文種別＝'4'(IFDOCO注文)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor002(market, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.特定預り売買区分(内容作成用)を取得する
            buySellKbnDisp = getBuySellKbnDisp(azukariKbn, jrIsaContractType);

            // 5.価格／OCO1(内容作成用)を取得する
            String donePriceOco1Disp = getDonePriceOco1Disp(doneSashine, doneSasinariKbn);

            // 6.条件／OCO2(内容作成用)を取得する
            String doneConditionOco2Disp = getDoneConditionOco2Disp(doneTriggerZone, doneTriggerNedan, doneSasinariKbn,
                    doneOcoSasinariKbn, doneOcoSashine);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = "IFD1:"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分+"<br>"
                // "IFD2:"+"　"+"株式売却"+"　"+価格／OCO1+"　"+条件／OCO2
                contents = "IFD1:" + brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(buySellKbnDisp) + "<br>" + "IFD2:" + FULL_SPACE + "株式売却"
                        + getDefaultVal(donePriceOco1Disp) + getDefaultVal(doneConditionOco2Disp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = "IFD1:"+ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+特定預り売買区分+"  "+"＊媒介"+"<br>"
                // "IFD2:"+"　"+"株式売却"+"　"+価格／OCO1+"　"+条件／OCO2
                contents = "IFD1:" + userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm)
                        + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp)
                        + getDefaultVal(quantityDisp) + getDefaultVal(buySellKbnDisp) + HALF_SPACE + "＊媒介" + "<br>"
                        + "IFD2:" + FULL_SPACE + "株式売却" + getDefaultVal(donePriceOco1Disp)
                        + getDefaultVal(doneConditionOco2Disp);
            }
        }
        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param tJpOrderBaseModel 編集用基本モデル
     * @param functionKbn       機能区分
     * @return contents 内容
     */
    public String getContentsFor003(TJpOrderBaseModel tJpOrderBaseModel, String functionKbn) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 注文種別
        String orderSyubetsu = tJpOrderBaseModel.getOrderSyubetsu();
        // 市場
        String market = tJpOrderBaseModel.getMarket();
        // 取引種別
        String tradeKbn = tJpOrderBaseModel.getTradeKbn();
        // 指成区分
        String sasinariKbn = tJpOrderBaseModel.getSasinariKbn();
        // RBE注文種別
        String rbeOrderKind = tJpOrderBaseModel.getRbeOrderKind();
        // 指値
        String price = tJpOrderBaseModel.getPrice();
        // トリガ発動ゾーン
        String triggerZone = tJpOrderBaseModel.getTriggerZone();
        // トリガ値段
        String triggerPrice = tJpOrderBaseModel.getTriggerPrice();
        // OCO指成区分
        String ocoSasinariKbn = tJpOrderBaseModel.getOcoSasinariKbn();
        // OCO指値
        String ocoSashine = tJpOrderBaseModel.getOcoSashine();
        // 数量
        String quantity = tJpOrderBaseModel.getQuantity();
        // 取得単価
        String openPrice = tJpOrderBaseModel.getOpenPrice();
        // 新規約定日
        String openTradeDate = tJpOrderBaseModel.getOpenTradeDate();
        // DONERBE注文種別
        String doneRbeOrderKind = tJpOrderBaseModel.getDoneRbeOrderKind();
        // DONE指値
        String doneSashine = tJpOrderBaseModel.getDoneSashine();
        // DONEトリガ値段
        String doneTriggerNedan = tJpOrderBaseModel.getDoneTriggerNedan();
        // DONEトリガ発動ゾーン
        String doneTriggerZone = tJpOrderBaseModel.getDoneTriggerZone();
        // DONE指成区分
        String doneSasinariKbn = tJpOrderBaseModel.getDoneSasinariKbn();
        // DONEOCO指成区分
        String doneOcoSasinariKbn = tJpOrderBaseModel.getDoneOcoSasinariKbn();
        // DONEOCO指値
        String doneOcoSashine = tJpOrderBaseModel.getDoneOcoSashine();
        // 新規市場
        String openMarket = tJpOrderBaseModel.getOpenMarket();

        // 市場(内容作成用)
        String marketDisp = StringUtil.EMPTY_STRING;
        // 指成区分(内容作成用)
        String sasinariKbnDisp = StringUtil.EMPTY_STRING;
        // 指値(内容作成用)
        String priceDisp = StringUtil.EMPTY_STRING;
        // 取得単価(内容作成用)
        String openPriceDisp = StringUtil.EMPTY_STRING;
        // 新規約定日(内容作成用)
        String openTradeDateDisp = StringUtil.EMPTY_STRING;
        // 返済買付/売却(内容作成用)
        String tradeKbnDisp = StringUtil.EMPTY_STRING;
        // DONE指成区分(内容作成用)
        String doneSasinariKbnDisp = StringUtil.EMPTY_STRING;
        // DONE指値(内容作成用)
        String doneSashineDisp = StringUtil.EMPTY_STRING;
        // 注文株数(内容作成用)
        String quantityDisp = StringUtil.EMPTY_STRING;
        // 建市場(内容作成用)
        String openMarketDisp = StringUtil.EMPTY_STRING;

        // 内容作成用の共通の項目を取得する
        // 銘柄コード(内容作成用)
        String brandCd = tJpOrderBaseModel.getBrandCd();
        // 銘柄名(内容作成用)を取得する
        // 銘柄の取引注意情報を取得する。
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コードをセット
        fct027Req.setBrandCode(brandCd);
        // 銘柄名(内容作成用)
        String brandNm = fct027.getData(fct027Req).getBrandName();

        // 注文株数(内容作成用)を取得する
        quantityDisp = getQuantityDisp(quantity);

        // ユーザ名
        String userNm = tJpOrderBaseModel.getUserNm();

        if (OrderSyubetsu.TJO_ORDERSYUBETSU_NORMAL_PRICE.equals(orderSyubetsu)) {
            // 通常注文
            // 注文種別＝'1'(通常/逆指値)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor003(market, tradeKbn, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.新規単価(内容作成用)を取得する
            openPriceDisp = getOpenPriceDisp(openPrice, tradeKbn);

            // 5.新規約定日(内容作成用)を取得する
            openTradeDateDisp = getOpenTradeDateDisp(openTradeDate, tradeKbn);

            // 6.建市場(内容作成用)を取得する
            openMarketDisp = getOpenMarketDisp(tradeKbn, openMarket);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = 銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+新規単価+"　"+新規約定日
                contents = brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp)
                        + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp) + getDefaultVal(openPriceDisp)
                        + getDefaultVal(openTradeDateDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+建市場+"　"+新規単価+"  "+新規約定日
                contents = userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(openMarketDisp) + getDefaultVal(openPriceDisp)
                        + getDefaultValWithFullSpace(openTradeDateDisp);
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_OCO.equals(orderSyubetsu)) {
            // OCO注文
            // 注文種別＝'2'(OCO注文)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor003(market, tradeKbn, orderSyubetsu);

            // 2.価格／OCO1(内容作成用)を取得する
            String priceOco1Disp = getPriceOco1Disp(price, sasinariKbn);

            // 3.条件／OCO2(内容作成用)を取得する
            String conditionOco2Disp = getConditionOco2Disp(triggerZone, triggerPrice, sasinariKbn, ocoSasinariKbn,
                    ocoSashine);

            // 4.新規単価(内容作成用)を取得する
            openPriceDisp = getOpenPriceDisp(openPrice, tradeKbn);

            // 5.新規約定日(内容作成用)を取得する
            openTradeDateDisp = getOpenTradeDateDisp(openTradeDate, tradeKbn);

            // 6.建市場(内容作成用)を取得する
            openMarketDisp = getOpenMarketDisp(tradeKbn, openMarket);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = 銘柄コード+"　"+銘柄名+"　"+市場+"　"+価格／OCO1+"　"+条件／OCO2+"　"+注文株数+"　"+新規単価+"　"+新規約定日
                contents = brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp) + getDefaultVal(priceOco1Disp)
                        + getDefaultVal(conditionOco2Disp) + getDefaultVal(quantityDisp) + getDefaultVal(openPriceDisp)
                        + getDefaultVal(openTradeDateDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+価格／OCO1+"　"+条件／OCO2+"　"+注文株数+"　"+建市場+"　"+新規単価+"  "+新規約定日
                contents = userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(priceOco1Disp) + getDefaultVal(conditionOco2Disp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(openMarketDisp) + getDefaultVal(openPriceDisp)
                        + getDefaultValWithFullSpace(openTradeDateDisp);
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFD.equals(orderSyubetsu)) {
            // IFD注文
            // 注文種別＝'3'(IFD)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor003(market, tradeKbn, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.新規単価(内容作成用)を取得する
            openPriceDisp = getOpenPriceDisp(openPrice, tradeKbn);

            // 5.新規約定日(内容作成用)を取得する
            openTradeDateDisp = getOpenTradeDateDisp(openTradeDate, tradeKbn);

            // 6.返済買付/売却(内容作成用)を取得する
            tradeKbnDisp = getTradeKbnDisp(tradeKbn);

            // 7.DONE指成区分(内容作成用)を取得する
            doneSasinariKbnDisp = getDoneSasinariKbnDisp(doneRbeOrderKind, doneSasinariKbn);

            // 8.DONE指値(内容作成用)を取得する
            doneSashineDisp = getDoneSashineDisp(doneRbeOrderKind, doneSashine, doneTriggerNedan, doneTriggerZone,
                    doneSasinariKbn);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = "IFD1:"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+新規単価+"  "+新規約定日+"<br>"
                // "IFD2:"+"　"+返済買付/売却+"　"+DONE指成区分+"　"+DONE指値
                contents = "IFD1:" + brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(openPriceDisp) + getDefaultValWithFullSpace(openTradeDateDisp) + "<br>"
                        + "IFD2:" + getDefaultVal(tradeKbnDisp) + getDefaultVal(doneSasinariKbnDisp)
                        + getDefaultVal(doneSashineDisp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                //  内容 ="IFD1:"+ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+新規単価+"　"+新規約定日+"<br>"
                // "IFD2:"+"　"+返済買付/売却+"　"+DONE指成区分+"　"+DONE指値
                contents = "IFD1:" + userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm)
                        + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp)
                        + getDefaultVal(quantityDisp) + getDefaultVal(openPriceDisp) + getDefaultVal(openTradeDateDisp)
                        + "<br>" + "IFD2:" + getDefaultVal(tradeKbnDisp) + getDefaultVal(doneSasinariKbnDisp)
                        + getDefaultVal(doneSashineDisp);
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFDOCO.equals(orderSyubetsu)) {
            // IFDOCO注文
            // 注文種別＝'4'(IFDOCO注文)の場合、

            // 1.市場(内容作成用)を取得する
            marketDisp = getMarketDispFor003(market, tradeKbn, orderSyubetsu);

            // 2.指成区分(内容作成用)を取得する
            sasinariKbnDisp = getSasinariKbnDisp(rbeOrderKind, sasinariKbn);

            // 3.指値(内容作成用)を取得する
            priceDisp = getPriceDisp(rbeOrderKind, price, triggerZone, triggerPrice, sasinariKbn);

            // 4.新規単価(内容作成用)を取得する
            openPriceDisp = getOpenPriceDisp(openPrice, tradeKbn);

            // 5.新規約定日(内容作成用)を取得する
            openTradeDateDisp = getOpenTradeDateDisp(openTradeDate, tradeKbn);

            // 6.返済買付/売却(内容作成用)を取得する
            tradeKbnDisp = getTradeKbnDisp(tradeKbn);

            // 7.価格／OCO1(内容作成用)を取得する
            String donePriceOco1Disp = getDonePriceOco1Disp(doneSashine, doneSasinariKbn);

            // 8.条件／OCO2(内容作成用)を取得する
            String doneConditionOco2Disp = getDoneConditionOco2Disp(doneTriggerZone, doneTriggerNedan, doneSasinariKbn,
                    doneOcoSasinariKbn, doneOcoSashine);

            if (IFA_CONTACT.equals(functionKbn)) {
                // 内容 = "IFD1:"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+新規単価+"  "+新規約定日+"<br>"
                // "IFD2:"+"　"+返済買付/売却+"　"+価格／OCO1+"　"+条件／OCO2
                contents = "IFD1:" + brandCd + getDefaultVal(brandNm) + getDefaultVal(marketDisp)
                        + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp) + getDefaultVal(quantityDisp)
                        + getDefaultVal(openPriceDisp) + getDefaultValWithFullSpace(openTradeDateDisp) + "<br>"
                        + "IFD2:" + getDefaultVal(tradeKbnDisp) + getDefaultVal(donePriceOco1Disp)
                        + getDefaultVal(doneConditionOco2Disp);
            } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
                // 内容 = "IFD1:"+ユーザ名+"　"+銘柄コード+"　"+銘柄名+"　"+市場+"　"+指成区分+"　"+指値+"　"+注文株数+"　"+新規単価+"　"+新規約定日+"<br>"
                // "IFD2:"+"　"+返済買付/売却+"　"+価格／OCO1+"　"+条件／OCO2
                contents = "IFD1:" + userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm)
                        + getDefaultVal(marketDisp) + getDefaultVal(sasinariKbnDisp) + getDefaultVal(priceDisp)
                        + getDefaultVal(quantityDisp) + getDefaultVal(openPriceDisp) + getDefaultVal(openTradeDateDisp)
                        + "<br>" + "IFD2:" + getDefaultVal(tradeKbnDisp) + getDefaultVal(donePriceOco1Disp)
                        + getDefaultVal(doneConditionOco2Disp);
            }
        }
        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param tJpOrderBaseModel 編集用基本モデル
     * @param functionKbn       機能区分
     * @return contents 内容
     */
    public String getContentsFor015(TJpOrderBaseModel tJpOrderBaseModel, String functionKbn) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.注文株数(内容作成用)を取得する
        // 注文株数(内容作成用)
        String quantityDisp = StringUtil.EMPTY_STRING;
        // 数量
        String quantity = tJpOrderBaseModel.getQuantity();
        // 注文株数(内容作成用)を取得する
        quantityDisp = getQuantityDisp(quantity);

        // 2.建市場(内容作成用)を取得する
        // 建市場(内容作成用)
        String orderedMarketDisp = StringUtil.EMPTY_STRING;
        // 新規市場
        String openMarket = tJpOrderBaseModel.getOpenMarket();
        // 建市場名
        String openMarketNm = StringUtil.EMPTY_STRING;
        if (OpenMarket.TKY.getId().equals(openMarket)) {
            // C.新規市場 = 'TKY'："東証"
            openMarketNm = OpenMarket.TKY.getLabel();
        } else if (OpenMarket.PTS.getId().equals(openMarket)) {
            // C.新規市場 = 'PTS'："PTS"
            openMarketNm = OpenMarket.PTS.getLabel();
        } else {
            // その他の場合、"-"
            openMarketNm = HALF_HYPHEN;
        }
        // 「"建市場:"+建市場名」と表示
        orderedMarketDisp = "建市場:" + openMarketNm;

        // 3.新規単価(内容作成用)を取得する
        // 新規単価(内容作成用)
        String openPriceDisp = StringUtil.EMPTY_STRING;
        // 取得単価
        String openPrice = tJpOrderBaseModel.getOpenPrice();
        if (!StringUtil.isNullOrEmpty(openPrice)) {
            // 取得単価≠NULLの場合:'新規単価:'||rtrim(rtrim(trim(to_char(C.取得単価/100,'9,999,999,999,990.99')),'0'),'.')
            openPriceDisp = "新規単価:" + openPrice;
        }

        // 4.新規約定日(内容作成用)を取得する
        // 新規約定日(内容作成用)
        String openTradeDateDisp = StringUtil.EMPTY_STRING;
        // 新規約定日
        String openTradeDate = tJpOrderBaseModel.getOpenTradeDate();
        if (!StringUtil.isNullOrEmpty(openTradeDate)) {
            // 新規約定日≠NULLの場合:'建日:'||to_char(新規約定日,'yyyy/mm/dd')
            openTradeDateDisp = "建日:" + openTradeDate;
        }

        // 5.特定預り売買区分(内容作成用)を取得する
        // 特定預り売買区分(内容作成用)
        String buySellKbnDisp = StringUtil.EMPTY_STRING;
        // 特定口座区分
        String specificKbn = tJpOrderBaseModel.getSpecificKbn();
        // 預り区分
        String azukariKbn = tJpOrderBaseModel.getAzukariKbn();
        if (SpecificKbn.TJO_PROXY_PAYMENT.equals(specificKbn)
                || SpecificKbn.TJO_CONFIRM_DECLARATION.equals(specificKbn)) {
            // 特定口座区分 = 1 または 2の場合、
            if (TjoAzukariKbn.TJO_AZUKARI_KBN_0.equals(azukariKbn)) {
                // 預り区分 = 0:"特定"
                buySellKbnDisp = "特定";
            } else {
                // 預り区分 ≠ 0:"一般"
                buySellKbnDisp = "一般";
            }
        } else {
            // その他の場合:""
            buySellKbnDisp = StringUtil.EMPTY_STRING;
        }

        // 6.銘柄コード(内容作成用)
        String brandCd = tJpOrderBaseModel.getBrandCd();

        // 7.銘柄名(内容作成用)を取得する
        // 銘柄の取引注意情報を取得する。
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コードを設定
        fct027Req.setBrandCode(brandCd);
        // 銘柄名(内容作成用)
        String brandNm = fct027.getData(fct027Req).getBrandName();

        // ユーザ名
        String userNm = tJpOrderBaseModel.getUserNm();

        if (IFA_CONTACT.equals(functionKbn)) {
            // 内容 = 銘柄コード+"　"+銘柄名+"　"+数量+"　"+建市場+"　"+新規単価+"　"+新規約定日+"　"+特定預り売買区分
            contents = brandCd + getDefaultVal(brandNm) + getDefaultVal(quantityDisp) + getDefaultVal(orderedMarketDisp)
                    + getDefaultVal(openPriceDisp) + getDefaultVal(openTradeDateDisp) + getDefaultVal(buySellKbnDisp);
        } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
            // 内容 = B.ユーザー名+"　"+A.銘柄コード+"　"+P.銘柄名+"　"+数量+"　"+建市場+"　"+新規単価+"　"+新規約定日+"　"+特定預り売買区分
            contents = userNm + getDefaultVal(brandCd) + getDefaultVal(brandNm) + getDefaultVal(quantityDisp)
                    + getDefaultVal(orderedMarketDisp) + getDefaultVal(openPriceDisp) + getDefaultVal(openTradeDateDisp)
                    + getDefaultVal(buySellKbnDisp);
        }

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param jpToushinOrderBaseModel 編集用基本モデル
     * @param jrIsaContractType       ジュニアISA契約区分
     * @param functionKbn             機能区分
     * @return contents 内容
     */
    public String getContentsFor016(JpToushinOrderBaseModel jpToushinOrderBaseModel, String jrIsaContractType,
            String functionKbn) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.銘柄コード(内容作成用)を取得する
        String brandCdDisp = StringUtil.EMPTY_STRING;
        // ファンドコード（回数）
        String fundCdKaisu = jpToushinOrderBaseModel.getFundCdKaisu();
        // ファンドコード（号）
        String fundCdGou = jpToushinOrderBaseModel.getFundCdGou();
        // ファンドコード（回数）||'-'||ファンドコード（号）
        brandCdDisp = fundCdKaisu + HALF_HYPHEN + fundCdGou;

        // 2.購入・解約の口数(内容作成用)を取得する
        String quantityDisp = StringUtil.EMPTY_STRING;
        // 口数
        String quantity = jpToushinOrderBaseModel.getQuantity();
        // '数量:'||trim(to_char(A.口数,'999,999,999,999,990'))
        quantityDisp = getQuantityDisp(quantity);

        // 3.乗換優遇区分(内容作成用)を取得する
        // 乗換優遇区分
        String norikaeYuguKbn = jpToushinOrderBaseModel.getNorikaeYuguKbn();
        // 乗換優遇区分(内容作成用)
        String norikaeYuguKbnDisp = getNorikaeYuguKbnDisp(norikaeYuguKbn);

        // 4.非特定預り売買区分(内容作成用)を取得する
        // 売買区分
        String tradeKbn = jpToushinOrderBaseModel.getTradeKbn();
        // 預り区分
        String azukariKbn = jpToushinOrderBaseModel.getAzukariKbn();
        // 特定口座区分
        String specificKbn = jpToushinOrderBaseModel.getSpecificKbn();
        // 非特定預り売買区分(内容作成用)
        String azukariKbnDisp = getAzukariKbnDisp(tradeKbn, azukariKbn, specificKbn, jrIsaContractType);

        // 5.ファンド正式名(内容作成用)を取得する
        // 銘柄情報を取得する。（FCT023）
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ファンド正式名を設定
        // ファンドコード（回数）+" "+trim(ファンドコード（号）)
        fct023Req.setNriCd(fundCdKaisu + " " + fundCdGou);
        // ファンド正式名(内容作成用)
        String fundOfficalName = fct023.getData(fct023Req).getFundOfficalName();

        // 6.ユーザー名(内容作成用)を取得する
        String userNm = jpToushinOrderBaseModel.getUserNm();

        if (IFA_CONTACT.equals(functionKbn)) {
            // 内容 = 銘柄コード+"　"+Q.ファンド正式名+"　"+非特定預り売買区分+"　"+購入・解約の口数+"　"+乗換優遇区分
            contents = brandCdDisp + getDefaultVal(fundOfficalName) + getDefaultVal(azukariKbnDisp)
                    + getDefaultVal(quantityDisp) + getDefaultVal(norikaeYuguKbnDisp);
        } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
            // 内容 = B.ユーザー名+"　"+銘柄コード+"　"+Q.ファンド正式名+"　"+非特定預り売買区分+"　"+購入・解約の口数+"　"+乗換優遇区分
            contents = userNm + getDefaultVal(brandCdDisp) + getDefaultVal(fundOfficalName)
                    + getDefaultVal(azukariKbnDisp) + getDefaultVal(quantityDisp) + getDefaultVal(norikaeYuguKbnDisp);
        }

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param jpToushinOrderBaseModel 編集用基本モデル
     * @param jrIsaContractType       ジュニアISA契約区分
     * @param maxInfoList             EC受注番号毎の最大情報
     * @param functionKbn             機能区分
     * @return contents 内容
     */
    public String getContentsFor017(JpToushinOrderBaseModel jpToushinOrderBaseModel, String jrIsaContractType,
            List<String> maxInfoList, String functionKbn) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.銘柄コード(内容作成用)を取得する
        String brandCdDisp = StringUtil.EMPTY_STRING;
        // ファンドコード（回数）
        String fundCdKaisu = jpToushinOrderBaseModel.getFundCdKaisu();
        // ファンドコード（号）
        String fundCdGou = jpToushinOrderBaseModel.getFundCdGou();
        // ファンドコード（回数）||'-'||ファンドコード（号）
        brandCdDisp = fundCdKaisu + HALF_HYPHEN + fundCdGou;

        // 2.精算金額(内容作成用)を取得する

        // 2.1精算金額（概算）(MAX)(精算金額作成用)を取得する
        // 精算金額（概算）(MAX)
        String netAmount = StringUtil.EMPTY_STRING;
        if (maxInfoList != null && CollectionUtils.isNotEmpty(maxInfoList)) {
            netAmount = maxInfoList.get(1);
        }

        // 2.2精算金額(内容作成用)を取得する
        // 精算金額(内容作成用)
        String netAmountDisp = StringUtil.EMPTY_STRING;
        if (!StringUtil.isNullOrEmpty(netAmount)) {
            // 精算金額（概算）がNULLでない場合、"金額:"||trim(to_char(max(A.精算金額（概算）),'999,999,999,999,990'))
            netAmountDisp = "金額:" + netAmount;
        }

        // 3.乗換優遇区分(内容作成用)を取得する
        // 乗換優遇区分
        String norikaeYuguKbn = jpToushinOrderBaseModel.getNorikaeYuguKbn();
        // 乗換優遇区分(内容作成用)
        String norikaeYuguKbnDisp = getNorikaeYuguKbnDisp(norikaeYuguKbn);

        // 4.非特定預り売買区分(内容作成用)を取得する
        String azukariKbnDisp = StringUtil.EMPTY_STRING;
        // 売買区分
        String tradeKbn = jpToushinOrderBaseModel.getTradeKbn();
        // 預り区分
        String azukariKbn = jpToushinOrderBaseModel.getAzukariKbn();
        // 特定口座区分
        String specificKbn = jpToushinOrderBaseModel.getSpecificKbn();
        if (JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
            // ジュニアISA契約区分＝'1'(契約済)の場合、
            if (JtoAzukariKbn.JTO_AZUKARI_KBN_H.getId().equals(azukariKbn)) {
                // 預り区分 = 'H':"総合口座－NISA預り(成長投資枠)"
                azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_H.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_I.getId().equals(azukariKbn)) {
                // 預り区分 = 'I':"総合口座－NISA預り(つみたて投資枠)"
                azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_I.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_J.getId().equals(azukariKbn)) {
                // 預り区分 = 'J':"ジュニアNISA口座－継続NISA"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_J.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_4.getId().equals(azukariKbn)) {
                // 預り区分 = '4':"総合口座－旧NISA"
                azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_4.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_8.getId().equals(azukariKbn)) {
                // 預り区分 = '8':"総合口座－旧つみたてNISA"
                azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_8.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_7.getId().equals(azukariKbn)) {
                // 預り区分 = '7':"ジュニアNISA口座－旧NISA"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_7.getLabel();
            } else {
                // 上記以外の場合、
                azukariKbnDisp = getAzukariKbnDisp(tradeKbn, azukariKbn, specificKbn, jrIsaContractType);
            }
        } else {
            // ジュニアISA契約区分≠'1'(契約済)の場合、
            if (JtoAzukariKbn.JTO_AZUKARI_KBN_H.getId().equals(azukariKbn)) {
                // 預り区分='H':"NISA預り(成長投資枠)"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_H.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_I.getId().equals(azukariKbn)) {
                // 預り区分='I':"NISA預り(つみたて投資枠)"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_I.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_4.getId().equals(azukariKbn)) {
                // 預り区分='4':"旧NISA"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_4.getLabel();
            } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_8.getId().equals(azukariKbn)) {
                // 預り区分='8':"旧つみたてNISA"
                azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_8.getLabel();
            } else {
                // 上記以外の場合、
                azukariKbnDisp = getAzukariKbnDisp(tradeKbn, azukariKbn, specificKbn, jrIsaContractType);
            }
        }

        // 5.ファンド正式名(内容作成用)を取得する
        // 銘柄情報を取得する。（FCT023）
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ファンド正式名を設定
        // ファンドコード（回数）+" "+trim(ファンドコード（号）)
        fct023Req.setNriCd(fundCdKaisu + " " + fundCdGou);
        // ファンド正式名(内容作成用)
        String fundOfficalName = fct023.getData(fct023Req).getFundOfficalName();

        // 6.ユーザー名(内容作成用)を取得する
        String userNm = jpToushinOrderBaseModel.getUserNm();

        // 7.利用ポイント(内容作成用)を取得する

        // 7.1ポイント(MAX)(利用ポイント作成用)を取得する
        // ポイント(MAX)
        String point = StringUtil.EMPTY_STRING;
        if (maxInfoList != null && CollectionUtils.isNotEmpty(maxInfoList)) {
            point = maxInfoList.get(0);
        }

        // 7.2利用ポイント(内容作成用)を取得する
        // 利用ポイント(内容作成用)
        String pointDisp = StringUtil.EMPTY_STRING;
        // ポイント種別
        String pointType = jpToushinOrderBaseModel.getPointType();
        if (StringUtil.isNullOrEmpty(point) || ZERO.equals(point)) {
            // ポイント＝nullまたは0の場合、表示しない
        } else {
            // 上記以外の場合、
            if (PointType.POINTTYPE_1.equals(pointType)) {
                // ポイント種別は'1'の場合、
                // システム日付YYYYMMDD
                String date = POINT_APPLICABLE_DATE;
                String createTime = jpToushinOrderBaseModel.getCreateTime();
                if (date.compareTo(createTime) > 0) {
                    // 登録日時(touroku_nichiji) < TO_DATE('2023/04/28 00:00:00','YYYY/MM/DD HH24:mi:SS')」の場合、
                    // '利用Tポイント:' || TRIM(TO_CHAR(MAX(A.ポイント),'999,999,999,999,990')) || 'ポイント'
                    pointDisp = "利用Tポイント:" + point + "ポイント";
                } else {
                    // 上記以外の場合、
                    // '利用Vポイント:' || TRIM(TO_CHAR(MAX(A.ポイント),'999,999,999,999,990')) || 'ポイント'
                    pointDisp = "利用Vポイント:" + point + "ポイント";
                }
            } else if (PointType.POINTTYPE_2.equals(pointType)) {
                // ポイント種別は'2'の場合、'利用Pontaポイント:' ||
                // TRIM(TO_CHAR(MAX(A.ポイント),'999,999,999,999,990')) || 'ポイント'
                pointDisp = "利用Pontaポイント:" + point + "ポイント";
            } else if (PointType.POINTTYPE_6.equals(pointType)) {
                // ポイント種別は'6'の場合、'利用タカシマヤポイント:' ||
                // TRIM(TO_CHAR(MAX(A.ポイント),'999,999,999,999,990')) || 'ポイント'
                pointDisp = "利用タカシマヤポイント:" + point + "ポイント";
            } else {
                // ポイント種別は上記以外の場合、'利用ポイント:' || TRIM(TO_CHAR(MAX(A.ポイント),'999,999,999,999,990')) || 'ポイント'"
                pointDisp = "利用ポイント:" + point + "ポイント";
            }
        }

        if (IFA_CONTACT.equals(functionKbn)) {
            // 内容 = 銘柄コード+"　"+Q.ファンド正式名+"　"+非特定預り売買区分+"　"+精算金額+"　"+乗換優遇区分+"　"+利用ポイント
            contents = brandCdDisp + getDefaultVal(fundOfficalName) + getDefaultVal(azukariKbnDisp)
                    + getDefaultVal(netAmountDisp) + getDefaultVal(norikaeYuguKbnDisp) + getDefaultVal(pointDisp);
        } else if (IFA_CONTACT_ACCEPT_DETAIL.equals(functionKbn)) {
            // 内容 = B.ユーザー名+"　"+銘柄コード+"　"+Q.ファンド正式名+"　"+非特定預り売買区分+"　"+精算金額+"　"+乗換優遇区分+"　"+利用ポイント
            contents = userNm + getDefaultVal(brandCdDisp) + getDefaultVal(fundOfficalName) + getDefaultVal(azukariKbnDisp)
                    + getDefaultVal(netAmountDisp) + getDefaultVal(norikaeYuguKbnDisp) + getDefaultVal(pointDisp);
        }

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param tbAdditionalOrderIfaBaseModel 編集用基本モデル
     * @param jrIsaContractType             ジュニアISA契約区分
     * @param maxInfoList                   トランザクションID毎の最大情報
     * @param functionKbn                   機能区分
     * @return contents 内容
     */
    public String getContentsFor018(TbAdditionalOrderIfaBaseModel tbAdditionalOrderIfaBaseModel,
            String jrIsaContractType, List<String> maxInfoList, String functionKbn) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.精算金額(内容作成用)を取得する

        // 1.1精算金額(MAX)(精算金額作成用)を取得する
        // 精算金額(MAX)
        String seisanKingaku = StringUtil.EMPTY_STRING;
        if (maxInfoList != null && CollectionUtils.isNotEmpty(maxInfoList)) {
            seisanKingaku = maxInfoList.get(1);
        }

        // 1.2精算金額(内容作成用)を取得する
        // 精算金額(内容作成用)
        String seisanKingakuDisp = StringUtil.EMPTY_STRING;
        if (!StringUtil.isNullOrEmpty(seisanKingaku) && !ZERO.equals(seisanKingaku)) {
            // 精算金額≠NULLおよび≠0:'拘束金額（買付余力）:'||trim(to_char(max(A.精算金額),'999,999,999,999,990'))
            seisanKingakuDisp = "拘束金額（買付余力）:" + seisanKingaku;
        }

        // 2.拘束金額（NISA）(内容作成用)を取得する

        // 2.1拘束金額表示名称(拘束金額（NISA）作成用)を取得する
        String kousokuSyubetuDisp = StringUtil.EMPTY_STRING;
        // 拘束種別
        String kousokuSyubetu = tbAdditionalOrderIfaBaseModel.getKousokuSyubetu();
        if (KousokuSyubetu.KOUSOKUSYUBETU_4.equals(kousokuSyubetu)) {
            // 拘束種別 = '4':"拘束金額(NISA成長投資枠):"
            kousokuSyubetuDisp = "拘束金額(NISA成長投資枠):";
        } else if (KousokuSyubetu.KOUSOKUSYUBETU_5.equals(kousokuSyubetu)) {
            // 拘束種別 = '5':"拘束金額(NISAつみたて投資枠):"
            kousokuSyubetuDisp = "拘束金額(NISAつみたて投資枠):";
        } else if (KousokuSyubetu.KOUSOKUSYUBETU_6.equals(kousokuSyubetu)) {
            // 拘束種別 = '6':"拘束金額(NISA成長投資枠):"
            kousokuSyubetuDisp = "拘束金額(NISA成長投資枠):";
        } else if (KousokuSyubetu.KOUSOKUSYUBETU_7.equals(kousokuSyubetu)) {
            // 拘束種別 = '7':"拘束金額(NISAつみたて投資枠):"
            kousokuSyubetuDisp = "拘束金額(NISAつみたて投資枠):";
        } else {
            // 上記以外の場合、
            if (!JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
                // ジュニアISA契約区分≠'1'(契約済)の場合:"旧NISA／旧つみたてNISA投資可能枠:"
                kousokuSyubetuDisp = "旧NISA／旧つみたてNISA投資可能枠:";
            } else {
                // ジュニアISA契約区分＝'1'(契約済)の場合:"NISA投資可能枠:"
                kousokuSyubetuDisp = "NISA投資可能枠:";
            }
        }

        // 2.2拘束金額（NISA）(MAX)(拘束金額（NISA）作成用)を取得する
        // 拘束金額（NISA）(MAX)
        String nisaKingaku = StringUtil.EMPTY_STRING;
        if (maxInfoList != null && CollectionUtils.isNotEmpty(maxInfoList)) {
            nisaKingaku = maxInfoList.get(0);
        }

        // 2.3拘束金額（NISA）(内容作成用)を取得する
        // 拘束金額（NISA）(内容作成用)
        String nisaKingakuDisp = StringUtil.EMPTY_STRING;
        if (!StringUtil.isNullOrEmpty(nisaKingaku) && !ZERO.equals(nisaKingaku)) {
            // 拘束金額（NISA）≠NULLおよび≠0:拘束金額表示名称（※）＋trim(to_char(max(A.拘束金額（NISA）),'999,999,999,999,990'))
            nisaKingakuDisp = kousokuSyubetuDisp + nisaKingaku;
        } else {
            // 上記以外の場合、""
            nisaKingakuDisp = StringUtil.EMPTY_STRING;
        }

        // 3.拘束区分(内容作成用)を取得する
        // 拘束区分(内容作成用)
        String kousokuKbnDisp = StringUtil.EMPTY_STRING;
        // 拘束区分
        String kousokuKbn = tbAdditionalOrderIfaBaseModel.getKousokuKbn();
        if (KousokuKbn.KOUSOKUKBN_SPACE.equals(kousokuKbn)) {
            // 拘束区分＝"△":"拘束区分:その他"
            kousokuKbnDisp = "拘束区分:その他";
        }

        // 4.口座区分(内容作成用)を取得する
        // 口座区分(内容作成用)
        String tokuteiAzukariBaibaiKbnDisp = StringUtil.EMPTY_STRING;
        // 特定預り売買区分
        String tokuteiAzukariBaibaiKbn = tbAdditionalOrderIfaBaseModel.getTokuteiAzukariBaibaiKbn();
        if (TokuteiAzukariBaibaiKbn.TOKUTEIAZUKARIBAIBAIKBN_1.equals(tokuteiAzukariBaibaiKbn)) {
            // 特定預り売買区分='1':"ジュニアNISA口座"
            tokuteiAzukariBaibaiKbnDisp = "ジュニアNISA口座";
        } else {
            // 特定預り売買区分が上記以外の場合:"総合口座"
            tokuteiAzukariBaibaiKbnDisp = "総合口座";
        }

        // 発注理由
        String hattyuRiyu = tbAdditionalOrderIfaBaseModel.getHattyuRiyu();

        // ユーザー名
        String userNm = tbAdditionalOrderIfaBaseModel.getUserNm();

        if (IFA_CONTACT.equals(functionKbn)) {
            // 内容 = 拘束区分+ "　"+精算金額+"　"+拘束金額（NISA)+"　"+trim(A.発注理由)+"　"+口座区分
            contents = kousokuKbnDisp + getDefaultVal(seisanKingakuDisp) + getDefaultVal(nisaKingakuDisp)
                    + getDefaultVal(hattyuRiyu) + getDefaultVal(tokuteiAzukariBaibaiKbnDisp);
        } else {
            // 内容 = B.ユーザー名+ "　"+拘束区分+ "　"+精算金額+"　"+拘束金額（NISA)+"　"+trim(A.発注理由)+"　"+口座区分
            contents = userNm + getDefaultVal(kousokuKbnDisp) + getDefaultVal(seisanKingakuDisp)
                    + getDefaultVal(nisaKingakuDisp) + getDefaultVal(hattyuRiyu)
                    + getDefaultVal(tokuteiAzukariBaibaiKbnDisp);
        }
        return contents;
    }

    /**
     * 取引種別名を取得する.
     * 
     * @param tradeKbn 取引種別
     * @return 取引種別名
     */
    public String getTradeKbnNm(String tradeKbn) throws Exception {

        // 取引種別名
        String tradeKbnNm = StringUtil.EMPTY_STRING;

        tradeKbnNm = TjoTradeKbn.valueOfId(tradeKbn) != null ? TjoTradeKbn.valueOfId(tradeKbn).getLabel()
                : StringUtil.EMPTY_STRING;

        return tradeKbnNm;
    }

    /**
     * 売買区分名を取得する.
     * 
     * @param tradeKbn 売買区分
     * @return 売買区分名
     */
    public String getSomeJtoTradeKbnNm(String tradeKbn) throws Exception {

        // 売買区分名
        String tradeKbnNm = StringUtil.EMPTY_STRING;

        if (JtoTradeKbn.TRADEKBN_U.getId().equals(tradeKbn) || JtoTradeKbn.TRADEKBN_K.getId().equals(tradeKbn)
                || JtoTradeKbn.TRADEKBN_V.getId().equals(tradeKbn)) {
            tradeKbnNm = JtoTradeKbn.valueOfId(tradeKbn) != null ? JtoTradeKbn.valueOfId(tradeKbn).getNormalLabel()
                    : StringUtil.EMPTY_STRING;
        }

        return tradeKbnNm;
    }

    /**
     * 売買区分名を取得する.
     * 
     * @param tradeKbn 売買区分
     * @return 売買区分名
     */
    public String getJtoTradeKbnNm(String tradeKbn) throws Exception {

        // 売買区分名
        String tradeKbnNm = StringUtil.EMPTY_STRING;

        tradeKbnNm = JtoTradeKbn.valueOfId(tradeKbn) != null ? JtoTradeKbn.valueOfId(tradeKbn).getLabel()
                : StringUtil.EMPTY_STRING;
        return tradeKbnNm;
    }

    /**
     * 指成区分(内容作成用)を取得する.
     * 
     * @param rbeOrderKind RBE注文種別
     * @param sasinariKbn  指成区分
     * @return sasinariKbnDisp 指成区分(内容作成用)
     */
    private String getSasinariKbnDisp(String rbeOrderKind, String sasinariKbn) throws Exception {

        // 指成区分(内容作成用)
        String sasinariKbnDisp = StringUtil.EMPTY_STRING;

        // 指成区分(内容作成用)を取得する
        if (RbeOrderKind.TJO_NORMAL.equals(rbeOrderKind)) {
            // RBE注文種別=' '(通常注文)の場合、
            // 'Z':"寄指(Y)"、'I':"引指(H)"、'F':"不成(F)"、'P':"IOC指(I)"、'N':"成行"、'Y':"寄成(Y)"、'H':"引成(H)"、'O':"IOC成(I)"、その他:"指値"
            sasinariKbnDisp = getSasinariKbnNm(sasinariKbn);
        } else if (RbeOrderKind.TJO_STOP.equals(rbeOrderKind)) {
            // RBE注文種別='SLO'逆指値注文の場合、""
            sasinariKbnDisp = StringUtil.EMPTY_STRING;
        }
        return sasinariKbnDisp;
    }

    /**
     * 指成区分名を取得する.
     * 
     * @param sasinariKbn 指成区分
     * @return 指成区分名
     */
    public String getSasinariKbnNm(String sasinariKbn) throws Exception {

        // 指成区分名
        String sasinariKbnDisp = StringUtil.EMPTY_STRING;

         // 'Z':"寄指(Y)"、'I':"引指(H)"、'F':"不成(F)"、'P':"IOC指(I)"、'N':"成行"、'Y':"寄成(Y)"、'H':"引成(H)"、'O':"IOC成(I)"、その他:"指値"
        sasinariKbnDisp = SasinariKbn.valueOfId(sasinariKbn) != null ? SasinariKbn.valueOfId(sasinariKbn).getLabel()
                : SasinariKbn.PRICE.getLabel();

        return sasinariKbnDisp;
    }

    /**
     * 指値(内容作成用)を取得する.
     * 
     * @param rbeOrderKind RBE注文種別
     * @param price        指値
     * @param triggerZone  トリガ発動ゾーン
     * @param triggerPrice トリガ値段
     * @param sasinariKbn  指成区分
     * @return sasinariKbnDisp 指値(内容作成用)
     */
    private String getPriceDisp(String rbeOrderKind, String price, String triggerZone, String triggerPrice,
            String sasinariKbn) throws Exception {

        // 指値(内容作成用)
        String priceDisp = StringUtil.EMPTY_STRING;

        // 1.トリガ発動ゾーン(指値作成用)を取得する
        String triggerZoneStr = TriggerZone.valueOfId(triggerZone) != null
                ? TriggerZone.valueOfId(triggerZone).getLabel()
                : StringUtil.EMPTY_STRING;

        // 2.指成区分(指値作成用)を取得する
        // 指成区分(指値作成用)
        String sasinariKbnStr = StringUtil.EMPTY_STRING;
        if (SasinariKbn.PRICE.getId().equals(sasinariKbn)) {
            // 指成区分＝' '（指値）の場合:rtrim(rtrim(trim(to_char(nvl(指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            sasinariKbnStr = price + YEN;
        } else if (SasinariKbn.HIKESASHI.getId().equals(sasinariKbn)) {
            // 指成区分＝"'I'"（引指）の場合、'引指(H)'||rtrim(rtrim(trim(to_char(nvl(指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            sasinariKbnStr = SasinariKbn.HIKESASHI.getLabel() + price + YEN;
        } else if (SasinariKbn.HUNARI.getId().equals(sasinariKbn)) {
            // 指成区分＝"'F'"（不成）の場合、'不成(F)'||rtrim(rtrim(trim(to_char(nvl(指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            sasinariKbnStr = SasinariKbn.HUNARI.getLabel() + price + YEN;
        } else if (SasinariKbn.NARIYUKI.getId().equals(sasinariKbn)) {
            // 指成区分が上記以外の場合、'N':"成行"
            sasinariKbnStr = SasinariKbn.NARIYUKI.getLabel();
        } else if (SasinariKbn.HIKENARI.getId().equals(sasinariKbn)) {
            // 指成区分が上記以外の場合、'H':"引成(H)"
            sasinariKbnStr = SasinariKbn.HIKENARI.getLabel();
        }

        // 3.指値(内容作成用)を取得する
        if (RbeOrderKind.TJO_NORMAL.equals(rbeOrderKind)) {
            // RBE注文種別=' '(通常注文)の場合、
            if (!StringUtil.isNullOrEmpty(price)) {
                // 指値がNULL以外の場合:'価格:'||rtrim(rtrim(trim(to_char(nvl(指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
                priceDisp = "価格:" + price + YEN;
            }
        } else if (RbeOrderKind.TJO_STOP.equals(rbeOrderKind)) {
            // RBE注文種別='SLO'逆指値注文の場合、
            // 価格:'||'現在値が'||rtrim(rtrim(trim(to_char(nvl(トリガ値段,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'||トリガ発動ゾーン※||'になった時点で'||指成区分※||'で執行'
            priceDisp = "価格:" + "現在値が" + triggerPrice + YEN + triggerZoneStr + "になった時点で" + sasinariKbnStr + "で執行";
        }

        return priceDisp;
    }

    /**
     * 特定預り売買区分(内容作成用)を取得する.
     * 
     * @param azukariKbn        預り区分
     * @param jrIsaContractType ジュニアISA契約区分
     * @return buySellKbnDisp 特定預り売買区分(内容作成用)
     */
    private String getBuySellKbnDisp(String azukariKbn, String jrIsaContractType) throws Exception {

        // 特定預り売買区分(内容作成用)
        String buySellKbnDisp = StringUtil.EMPTY_STRING;

        // 特定預り売買区分(内容作成用)を取得する
        if (JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
            // JrNISA口座開設済※の場合(ジュニアISA契約区分='1'(契約済)の場合、JrNISA口座開設済)、
            if (TjoAzukariKbn.TJO_AZUKARI_KBN_H.equals(azukariKbn)) {
                // H:"総合口座－NISA(成長投資枠)"
                buySellKbnDisp = "総合口座－NISA(成長投資枠)";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_4.equals(azukariKbn)) {
                // 4:"総合口座－旧NISA"
                buySellKbnDisp = "総合口座－旧NISA";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_0.equals(azukariKbn)) {
                // 0:"総合口座－特定"
                buySellKbnDisp = "総合口座－特定";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_J.equals(azukariKbn)) {
                // J:"ジュニアNISA口座－継続NISA"
                buySellKbnDisp = "ジュニアNISA口座－継続NISA";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_5.equals(azukariKbn)) {
                // 5:"ジュニアNISA口座－特定"
                buySellKbnDisp = "ジュニアNISA口座－特定";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_6.equals(azukariKbn)) {
                // 6:"ジュニアNISA口座－一般"
                buySellKbnDisp = "ジュニアNISA口座－一般";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_7.equals(azukariKbn)) {
                // 7:"ジュニアNISA口座－旧NISA"
                buySellKbnDisp = "ジュニアNISA口座－旧NISA";
            } else {
                // その他の場合:"総合口座－一般"
                buySellKbnDisp = "総合口座－一般";
            }
        } else {
            // JrNISA口座は上記以外の場合、
            if (TjoAzukariKbn.TJO_AZUKARI_KBN_H.equals(azukariKbn)) {
                // H:"NISA(成長投資枠)"
                buySellKbnDisp = "NISA(成長投資枠)";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_4.equals(azukariKbn)) {
                // 4:"旧NISA"
                buySellKbnDisp = "旧NISA";
            } else if (TjoAzukariKbn.TJO_AZUKARI_KBN_0.equals(azukariKbn)) {
                // 0:"特定"
                buySellKbnDisp = "特定";
            } else {
                // その他の場合:"一般"
                buySellKbnDisp = "一般";
            }
        }

        return buySellKbnDisp;
    }

    /**
     * OCO注文の「価格／OCO1」(内容作成用)を取得する.
     * 
     * @param price       指値
     * @param sasinariKbn 指成区分
     * @return priceOco1Disp OCO注文の「価格／OCO1」(内容作成用)
     */
    private String getPriceOco1Disp(String price, String sasinariKbn) throws Exception {

        // 価格／OCO1(内容作成用)
        String priceOco1Disp = StringUtil.EMPTY_STRING;

        // 1.指成区分(「価格／OCO1」作成用)を取得する
        String sasinariKbnStr = getSasinariKbnStr(sasinariKbn);

        // 2.価格／OCO1(内容作成用)を取得する
        // '価格／OCO1:'||指成区分※||'/'||rtrim(rtrim(trim(to_char(nvl(指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
        priceOco1Disp = "価格／OCO1:" + sasinariKbnStr + '/' + price + YEN;

        return priceOco1Disp;
    }

    /**
     * OCO注文の「条件／OCO2」(内容作成用)を取得する.
     * 
     * @param triggerZone    トリガ発動ゾーン
     * @param triggerPrice   トリガ値段
     * @param sasinariKbn    指成区分
     * @param ocoSasinariKbn OCO指成区分
     * @param ocoSashine     OCO指値
     * @return conditionOco2Disp OCO注文の「条件／OCO2」(内容作成用)
     */
    private String getConditionOco2Disp(String triggerZone, String triggerPrice, String sasinariKbn,
            String ocoSasinariKbn, String ocoSashine) throws Exception {

        // 条件／OCO2(内容作成用)
        String conditionOco2Disp = StringUtil.EMPTY_STRING;

        // 1.トリガ発動ゾーン(「条件／OCO2」作成用)を取得する
        String triggerZoneStr = TriggerZone.valueOfId(triggerZone) != null
                ? TriggerZone.valueOfId(triggerZone).getLabel()
                : StringUtil.EMPTY_STRING;

        // 2.指成区分(「条件／OCO2」作成用)を取得する
        String sasinariKbnStr = getSasinariKbnStr(sasinariKbn);

        // 3.OCO指成区分(「条件／OCO2」作成用)を取得する
        // OCO指成区分:'N':"成行"、'F':"不成(F)" 上記以外:""
        String ocoSasinariKbnStr = OcoSasinariKbn.valueOfId(ocoSasinariKbn) != null
                ? OcoSasinariKbn.valueOfId(ocoSasinariKbn).getLabel()
                : StringUtil.EMPTY_STRING;

        // 4.OCO指値(「条件／OCO2」作成用)を取得する
        // OCO指値(「条件／OCO2」作成用)
        String ocoSashineStr = StringUtil.EMPTY_STRING;
        if (OcoSasinariKbn.NARIYUKI.getId().equals(ocoSasinariKbn)) {
            // OCO指成区分が'N'の場合、""
            ocoSashineStr = StringUtil.EMPTY_STRING;
        } else {
            // 上記以外の場合、rtrim(rtrim(trim(to_char(nvl(OCO指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            ocoSashineStr = ocoSashine + YEN;
        }

        // 5.条件／OCO2(内容作成用)を取得する
        // '条件／OCO2:現在値が'||rtrim(rtrim(trim(to_char(nvl(トリガ値段,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'||トリガ発動ゾーン※||'になった時点でOCO1('||指成区分※||')を'||OCO指成区分※||OCO指値※||'に訂正'
        conditionOco2Disp = "条件／OCO2:現在値が" + triggerPrice + YEN + triggerZoneStr + "になった時点でOCO1(" + sasinariKbnStr
                + ")を" + ocoSasinariKbnStr + ocoSashineStr + "に訂正";

        return conditionOco2Disp;
    }

    /**
     * 指成区分(編集後)を取得する.
     * 
     * @param sasinariKbn 指成区分
     * @return 指成区分(編集後)
     */
    private String getSasinariKbnStr(String sasinariKbn) throws Exception {

        // 指成区分(編集後)
        String sasinariKbnStr = StringUtil.EMPTY_STRING;

        if (SasinariKbn.PRICE.getId().equals(sasinariKbn)) {
            // 指成区分:' ':"指値
            sasinariKbnStr = SasinariKbn.PRICE.getLabel();
        } else if (SasinariKbn.HUNARI.getId().equals(sasinariKbn)) {
            // 指成区分:'F':"不成(F)"
            sasinariKbnStr = SasinariKbn.HUNARI.getLabel();
        }

        return sasinariKbnStr;
    }

    /**
     * DONE指成区分(内容作成用)を取得する.
     * 
     * @param doneRbeOrderKind DONERBE注文種別
     * @param doneSasinariKbn  DONE指成区分
     * @return DONE指成区分(内容作成用)
     */
    private String getDoneSasinariKbnDisp(String doneRbeOrderKind, String doneSasinariKbn) throws Exception {

        // DONE指成区分(内容作成用)
        String doneSasinariKbnDisp = StringUtil.EMPTY_STRING;

        if (DoneRbeOrderKind.TJO_DONE_STOP.equals(doneRbeOrderKind)) {
            // DONERBE注文種別='SLO'(逆指値注文)の場合、""
            doneSasinariKbnDisp = StringUtil.EMPTY_STRING;
        } else {
            // DONERBE注文種別が上記以外の場合、
            if (DoneSasinariKbn.HIKESASHI.getId().equals(doneSasinariKbn)) {
                // 'I':"引指(H)"
                doneSasinariKbnDisp = DoneSasinariKbn.HIKESASHI.getLabel();
            } else if (DoneSasinariKbn.HUNARI.getId().equals(doneSasinariKbn)) {
                // 'F':"不成(F)"
                doneSasinariKbnDisp = DoneSasinariKbn.HUNARI.getLabel();
            } else {
                // その他:"指値"
                doneSasinariKbnDisp = DoneSasinariKbn.PRICE.getLabel();
            }
        }

        return doneSasinariKbnDisp;
    }

    /**
     * DONE指値(内容作成用)を取得する.
     * 
     * @param doneRbeOrderKind DONERBE注文種別
     * @param doneSashine      DONE指値
     * @param doneTriggerNedan DONEトリガ値段
     * @param doneTriggerZone  DONEトリガ発動ゾーン
     * @param doneSasinariKbn  DONE指成区分
     * @return DONE指値(内容作成用)
     */
    private String getDoneSashineDisp(String doneRbeOrderKind, String doneSashine, String doneTriggerNedan,
            String doneTriggerZone, String doneSasinariKbn) throws Exception {

        // DONE指値(内容作成用)
        String priceDisp = StringUtil.EMPTY_STRING;

        // 1.DONEトリガ発動ゾーン(DONE指値作成用)を取得する
        String doneTriggerZoneStr = DoneTriggerZone.valueOfId(doneTriggerZone) != null
                ? DoneTriggerZone.valueOfId(doneTriggerZone).getLabel()
                : StringUtil.EMPTY_STRING;

        // 2.DONE指成区分(DONE指値作成用)を取得する
        // DONE指値区分(DONE指値作成用)
        String doneSasinariKbnStr = StringUtil.EMPTY_STRING;
        if (DoneSasinariKbn.PRICE.getId().equals(doneSasinariKbn)) {
            // DONE指成区分＝' '（指値）の場合:rtrim(rtrim(trim(to_char(nvl(DONE指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            doneSasinariKbnStr = doneSashine + YEN;
        } else if (DoneSasinariKbn.HIKESASHI.getId().equals(doneSasinariKbn)) {
            // DONE指成区分＝"'I'"（引指）の場合、'引指(H)'||rtrim(rtrim(trim(to_char(nvl(DONE指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            doneSasinariKbnStr = DoneSasinariKbn.HIKESASHI.getLabel() + doneSashine + YEN;
        } else if (DoneSasinariKbn.HUNARI.getId().equals(doneSasinariKbn)) {
            // DONE指成区分＝"'F'"（不成）の場合、'不成(F)'||rtrim(rtrim(trim(to_char(nvl(DONE指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            doneSasinariKbnStr = DoneSasinariKbn.HUNARI.getLabel() + doneSashine + YEN;
        } else if (DoneSasinariKbn.NARIYUKI.getId().equals(doneSasinariKbn)) {
            // DONE指成区分が上記以外の場合、'N':"成行"
            doneSasinariKbnStr = DoneSasinariKbn.NARIYUKI.getLabel();
        } else if (DoneSasinariKbn.HIKENARI.getId().equals(doneSasinariKbn)) {
            // DONE指成区分が上記以外の場合、'H':"引成(H)"
            doneSasinariKbnStr = DoneSasinariKbn.HIKENARI.getLabel();
        }

        // 3.DONE指値(内容作成用)を取得する
        if (DoneRbeOrderKind.TJO_DONE_NORMAL.equals(doneRbeOrderKind)) {
            // DONERBE注文種別=' '(通常注文)の場合、
            if (!StringUtil.isNullOrEmpty(doneSashine)) {
                // DONE指値がNULL以外の場合:'価格:'||rtrim(rtrim(trim(to_char(nvl(DONE指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
                priceDisp = "価格:" + doneSashine + YEN;
            }
        } else if (DoneRbeOrderKind.TJO_DONE_STOP.equals(doneRbeOrderKind)) {
            // DONERBE注文種別='SLO'(逆指値注文)の場合、
            // "価格:現在値が"||rtrim(rtrim(trim(to_char(nvl(DONEトリガ値段,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'||DONEトリガ発動ゾーン※||'になった時点で'||DONE指成区分※||'で執行'
            priceDisp = "価格:現在値が" + doneTriggerNedan + YEN + doneTriggerZoneStr + "になった時点で" + doneSasinariKbnStr
                    + "で執行";
        }

        return priceDisp;
    }

    /**
     * IFDOCO注文の「価格／OCO1」(内容作成用)を取得する.
     * 
     * @param doneSashine       DONE指値
     * @param doneSasinariKbn   DONE指成区分
     * @param donePriceOco1Disp IFDOCO注文の「価格／OCO1」(内容作成用)
     * @return
     */
    private String getDonePriceOco1Disp(String doneSashine, String doneSasinariKbn) throws Exception {

        // 価格／OCO1(内容作成用)
        String donePriceOco1Disp = StringUtil.EMPTY_STRING;

        // 1.DONE指成区分(「価格／OCO1」作成用)を取得する
        String doneSasinariKbnStr = getDoneSasinariKbnStr(doneSasinariKbn);

        // 2.価格／OCO1(内容作成用)を取得する
        // '価格／OCO1:'||DONE指成区分※||'/'||rtrim(rtrim(trim(to_char(nvl(DONE指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
        donePriceOco1Disp = "価格／OCO1:" + doneSasinariKbnStr + "/" + doneSashine + YEN;

        return donePriceOco1Disp;
    }

    /**
     * IFDOCO注文の「条件／OCO2」(内容作成用)を取得する.
     * 
     * @param doneTriggerZone       DONEトリガ発動ゾーン
     * @param doneTriggerNedan      DONEトリガ値段
     * @param doneSasinariKbn       DONE指成区分
     * @param doneOcoSasinariKbn    DONEOCO指成区分
     * @param doneOcoSashine        DONEOCO指値
     * @param doneConditionOco2Disp IFDOCO注文の「条件／OCO2」(内容作成用)
     * @return
     */
    private String getDoneConditionOco2Disp(String doneTriggerZone, String doneTriggerNedan, String doneSasinariKbn,
            String doneOcoSasinariKbn, String doneOcoSashine) throws Exception {

        // 条件／OCO2(内容作成用)
        String doneConditionOco2Disp = StringUtil.EMPTY_STRING;

        // 1.DONEトリガ発動ゾーン(「条件／OCO2」作成用)を取得する
        String doneTriggerZoneStr = DoneTriggerZone.valueOfId(doneTriggerZone) != null
                ? DoneTriggerZone.valueOfId(doneTriggerZone).getLabel()
                : StringUtil.EMPTY_STRING;

        // 2.DONE指成区分(「条件／OCO2」作成用)を取得する
        String doneSasinariKbnStr = getDoneSasinariKbnStr(doneSasinariKbn);

        // 3.DONEOCO指成区分(「条件／OCO2」作成用)を取得する
        // DONEOCO指成区分(「条件／OCO2」作成用)
        String doneOcoSasinariKbnStr = StringUtil.EMPTY_STRING;
        if (DoneOcoSasinariKbn.NARIYUKI.getId().equals(doneOcoSasinariKbn)) {
            // DONEOCO指成区分:'N':"成行"
            doneOcoSasinariKbnStr = DoneOcoSasinariKbn.NARIYUKI.getLabel();
        } else if (DoneOcoSasinariKbn.HUNARI.getId().equals(doneOcoSasinariKbn)) {
            // DONEOCO指成区分:'F':"不成(F)"
            doneOcoSasinariKbnStr = DoneOcoSasinariKbn.HUNARI.getLabel();
        } else {
            // 上記以外:""
            doneOcoSasinariKbnStr = StringUtil.EMPTY_STRING;
        }

        // 4.DONEOCO指値(「条件／OCO2」作成用)を取得する
        // DONEOCO指値(「条件／OCO2」作成用)
        String doneOcoSashineStr = StringUtil.EMPTY_STRING;

        if (DoneOcoSasinariKbn.NARIYUKI.getId().equals(doneOcoSasinariKbn)) {
            // DONEOCO指成区分が'N'の場合、""
            doneOcoSashineStr = StringUtil.EMPTY_STRING;
        } else {
            // 上記以外の場合:rtrim(rtrim(trim(to_char(nvl(DONEOCO指値,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'
            doneOcoSashineStr = doneOcoSashine + YEN;
        }

        // 5.条件／OCO2(内容作成用)を取得する
        // '条件／OCO2:現在値が'||rtrim(rtrim(trim(to_char(nvl(DONEトリガ値段,'0'),'999,999,999,999,990.99')),'0'),'.')||'円'||DONEトリガ発動ゾーン※||'になった時点でOCO1('||DONE指成区分※||')を'||DONEOCO指成区分※||DONEOCO指値※||'に訂正'
        doneConditionOco2Disp = "条件／OCO2:現在値が" + doneTriggerNedan + YEN + doneTriggerZoneStr + "になった時点でOCO1("
                + doneSasinariKbnStr + ")を" + doneOcoSasinariKbnStr + doneOcoSashineStr + "に訂正";

        return doneConditionOco2Disp;
    }

    /**
     * DONE指成区分(編集後)を取得する.
     * 
     * @param doneSasinariKbn DONE指成区分
     * @return DONE指成区分(編集後)
     */
    private String getDoneSasinariKbnStr(String doneSasinariKbn) throws Exception {

        // DONE指成区分(編集後)
        String doneSasinariKbnStr = StringUtil.EMPTY_STRING;

        if (DoneSasinariKbn.PRICE.getId().equals(doneSasinariKbn)) {
            // DONE指成区分: ' ':"指値"
            doneSasinariKbnStr = DoneSasinariKbn.PRICE.getLabel();
        } else if (DoneSasinariKbn.HUNARI.getId().equals(doneSasinariKbn)) {
            // DONE指成区分: 'F':"不成(F)"
            doneSasinariKbnStr = DoneSasinariKbn.HUNARI.getLabel();
        }

        return doneSasinariKbnStr;
    }

    /**
     * 中分類を取得する.
     * 
     * @param tradeKbn         取引種別
     * @param paymentLimit     弁済期限
     * @param dailyCreditKbn   一日信用区分
     * @param paymentLimitDate 弁済期限年月日数
     * @param dateKbn          年月日区分
     * @return 中分類
     */
    public String getMidClass(String tradeKbn, String paymentLimit, String dailyCreditKbn, String paymentLimitDate,
            String dateKbn) throws Exception {

        // 中分類
        String midClass = StringUtil.EMPTY_STRING;

        // 1.取引種別(中分類作成用)を取得する
        String tradeKbnNm = getTradeKbnNm(tradeKbn);

        // 2.弁済期限(中分類作成用)を取得する
        String paymentLimitStr = StringUtil.EMPTY_STRING;
        if (PaymentLimit.SIX_MONTH.getId().equals(paymentLimit)
                || PaymentLimit.UNLIMITED.getId().equals(paymentLimit)) {
            // 弁済期限が '6'、'9'の場合、
            // 弁済期限 = '6' :"6ヶ月" '9' :"無期限"
            paymentLimitStr = PaymentLimit.valueOfId(paymentLimit) != null
                    ? PaymentLimit.valueOfId(paymentLimit).getLabel()
                    : StringUtil.EMPTY_STRING;
        } else {
            // 弁済期限が '6'、'9'以外の場合、
            if (DailyCreditKbn.LIMIT_DAY_TRADE.getId().equals(dailyCreditKbn)
                    || DailyCreditKbn.LIMIT_DAY_TRADE_H.getId().equals(dailyCreditKbn)) {
                // 一日信用区分が'1'、'2'の場合、
                paymentLimitStr = DailyCreditKbn.valueOfId(dailyCreditKbn) != null
                        ? DailyCreditKbn.valueOfId(dailyCreditKbn).getLabel()
                        : StringUtil.EMPTY_STRING;
            } else {
                // 一日信用区分が'1'、'2'以外の場合、
                if ("  ".equals(paymentLimitDate) || StringUtil.isNullOrEmpty(paymentLimitDate)) {
                    // 弁済期限年月日数 = '△△'又はnullの場合、"無期限"
                    paymentLimitStr = PaymentLimit.UNLIMITED.getLabel();
                } else {
                    // 弁済期限年月日数 ≠ '△△'の場合、

                    // 年月日区分
                    String dateKbnStr = DateKbn.valueOfId(dateKbn) != null ? DateKbn.valueOfId(dateKbn).getLabel()
                            : StringUtil.EMPTY_STRING;
                    // 弁済期限年月日数+年月日区分（D:日 M:ヶ月 Y:年）
                    paymentLimitStr = paymentLimitDate + dateKbnStr;
                }
            }
        }

        // 3.中分類を取得する
        // 中分類 = 取引種別+弁済期限
        midClass = tradeKbnNm + paymentLimitStr;

        return midClass;
    }

    /**
     * 市場(内容作成用)を取得する
     * 
     * @param market        市場
     * @param orderSyubetsu 注文種別
     * @return 市場(内容作成用)
     */
    private String getMarketDispFor002(String market, String orderSyubetsu) throws Exception {

        // 市場(内容作成用)
        String marketDisp = StringUtil.EMPTY_STRING;

        if (OrderSyubetsu.TJO_ORDERSYUBETSU_NORMAL_PRICE.equals(orderSyubetsu)) {
            // 注文種別＝'1'(通常/逆指値)の場合、 1.市場(内容作成用)を取得する
            // A:"SOR"、0:"東証"、2:"名証"、6:"福証"、7:"PTS"、8:"札証"、'H':"単元未満"
            marketDisp = Market.valueOfId(market) != null ? Market.valueOfId(market).getLabel()
                    : StringUtil.EMPTY_STRING;
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_OCO.equals(orderSyubetsu)) {
            // 注文種別＝'2'(OCO注文)の場合、
            // 1.市場(内容作成用)を取得する
            if (Market.TKY.getId().equals(market)) {
                // 0:"東証"
                marketDisp = Market.TKY.getLabel();
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFD.equals(orderSyubetsu)) {
            // 注文種別＝'3'(IFD)の場合、
            // 1.市場(内容作成用)を取得する
            if (Market.TKY.getId().equals(market) || Market.NGY.getId().equals(market)
                    || Market.FKO.getId().equals(market) || Market.SPR.getId().equals(market)) {
                // 0:"東証"、2:"名証"、6:"福証"、8:"札証"
                marketDisp = Market.valueOfId(market) != null ? Market.valueOfId(market).getLabel()
                        : StringUtil.EMPTY_STRING;
            }
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFDOCO.equals(orderSyubetsu)) {
            // 注文種別＝'4'(IFDOCO注文)の場合、
            // 1.市場(内容作成用)を取得する
            if (Market.TKY.getId().equals(market)) {
                // 0:"東証"
                marketDisp = Market.TKY.getLabel();
            }

        }
        return marketDisp;
    }

    /**
     * 市場(内容作成用)を取得する
     * 
     * @param market        市場
     * @param tradeKbn      取引種別
     * @param orderSyubetsu 注文種別
     * @return 市場(内容作成用)
     */
    private String getMarketDispFor003(String market, String tradeKbn, String orderSyubetsu) throws Exception {

        // 市場(内容作成用)
        String marketDisp = StringUtil.EMPTY_STRING;

        // 市場名
        String marketNm = StringUtil.EMPTY_STRING;

        if (OrderSyubetsu.TJO_ORDERSYUBETSU_NORMAL_PRICE.equals(orderSyubetsu)) {
            // 注文種別＝'1'(通常/逆指値)の場合、
            // 1.市場名を取得する
            if (Market.TKY.getId().equals(market) || Market.JNX.getId().equals(market) || Market.SOR.getId().equals(market)) {
                // 0:"東証"、7:"PTS"、A："SOR"
                marketNm = Market.valueOfId(market) != null ? Market.valueOfId(market).getLabel()
                        : StringUtil.EMPTY_STRING;
            }

            // 2.市場(内容作成用)を取得する
            marketDisp = getMarketStr(tradeKbn, marketNm);
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_OCO.equals(orderSyubetsu)) {
            // 注文種別＝'2'(OCO注文)の場合、
            // 1.市場名を取得する
            if (Market.TKY.getId().equals(market)) {
                // 0:"東証"
                marketNm = Market.TKY.getLabel();
            }

            // 2.市場(内容作成用)を取得する
            marketDisp = getMarketStr(tradeKbn, marketNm);
        } else if (OrderSyubetsu.TJO_ORDERSYUBETSU_IFD.equals(orderSyubetsu)
                || OrderSyubetsu.TJO_ORDERSYUBETSU_IFDOCO.equals(orderSyubetsu)) {
            // 注文種別＝'3'(IFD)または'4'(IFDOCO注文)の場合、 
            // 1.市場(内容作成用)を取得する
            if (Market.TKY.getId().equals(market)) {
                // 0:"東証"
                marketDisp = Market.TKY.getLabel();
            }

        }
        return marketDisp;
    }

    /**
     * 市場(内容作成用)を取得する.
     * 
     * @param tradeKbn 取引種別
     * @param marketNm 市場名
     * @return 市場(内容作成用)
     */
    private String getMarketStr(String tradeKbn, String marketNm) {

        // 市場(内容作成用)
        String getMarketStr = StringUtil.EMPTY_STRING;

        if (TjoTradeKbn.REPAYMENT_BUY.getId().equals(tradeKbn) || TjoTradeKbn.REPAYMENT_SALE.getId().equals(tradeKbn)) {
            // 取引種別が'5'(信用返済買)または'6'(信用返済売)の場合、「発注市場:市場」と表示
            if (!StringUtil.isNullOrEmpty(marketNm)) {
                getMarketStr = "発注市場:" + marketNm;
            }
        } else {
            // 上記以外の場合、区分値に対応する市場名をそのまま表示し
            getMarketStr = marketNm;
        }
        return getMarketStr;
    }

    /**
     * 建市場(内容作成用)を取得する.
     * 
     * @param tradeKbn   取引種別
     * @param openMarket 新規市場
     * @return 建市場(内容作成用)
     */
    private String getOpenMarketDisp(String tradeKbn, String openMarket) {

        // 建市場(内容作成用)
        String openMarketDisp = StringUtil.EMPTY_STRING;

        if (TjoTradeKbn.REPAYMENT_BUY.getId().equals(tradeKbn) || TjoTradeKbn.REPAYMENT_SALE.getId().equals(tradeKbn)) {
            // 取引種別が'5'(信用返済買)または'6'(信用返済売)の場合、「発注市場:市場」と表示

            // 1.建市場名(建市場作成用)を取得する.
            String openMarketNm = StringUtil.EMPTY_STRING;
            if ("TKY".equals(openMarket)) {
                // 新規市場 = TKYの場合、"東証"と表示
                openMarketNm = "東証";
            } else if ("PTS".equals(openMarket)) {
                // 新規市場 = PTSの場合、"PTS"と表示
                openMarketNm = "PTS";
            } else {
                openMarketNm = HALF_HYPHEN;
            }

            // 2.建市場名(建市場作成用)を取得する. 「建市場:建市場名」と表示
            openMarketDisp = "建市場:" + openMarketNm;
        } else {
            // 上記以外の場合、""
            openMarketDisp = StringUtil.EMPTY_STRING;
        }

        return openMarketDisp;
    }

    /**
     * 取得単価(内容作成用)を取得する.
     * 
     * @param openPrice 取得単価
     * @param tradeKbn  取引種別
     * @return 取得単価(内容作成用)
     */
    private String getOpenPriceDisp(String openPrice, String tradeKbn) throws Exception {

        // 取得単価(内容作成用)
        String openPriceDisp = StringUtil.EMPTY_STRING;

        if (StringUtil.isNullOrEmpty(openPrice)) {
            // 取得単価がNULLの場合、""
            openPriceDisp = StringUtil.EMPTY_STRING;
        } else if ("0".equals(openPrice) && (TjoTradeKbn.REPAYMENT_BUY.getId().equals(tradeKbn)
                || TjoTradeKbn.REPAYMENT_SALE.getId().equals(tradeKbn))) {
            // 取得単価が０且つ取引種別が'5'(信用返済買)または'6'(信用返済売)の場合、""
            openPriceDisp = StringUtil.EMPTY_STRING;
        } else {
            // その他:'新規単価、'||rtrim(rtrim(trim(to_char(取得単価/100,'9,999,999,999,990.99')),'0'),'.')
            openPriceDisp = "新規単価:" + openPrice;
        }

        return openPriceDisp;
    }

    /**
     * 新規約定日(内容作成用)を取得する.
     * 
     * @param openTradeDate 新規約定日
     * @param tradeKbn      取引種別
     * @return 取得単価(内容作成用)
     */
    private String getOpenTradeDateDisp(String openTradeDate, String tradeKbn) throws Exception {

        // 新規約定日(内容作成用)
        String openTradeDateDisp = StringUtil.EMPTY_STRING;

        if (StringUtil.isNullOrEmpty(openTradeDate)) {
            // 新規約定日がNULLの場合、
            if (TjoTradeKbn.REPAYMENT_BUY.getId().equals(tradeKbn)
                    || TjoTradeKbn.REPAYMENT_SALE.getId().equals(tradeKbn)) {
                // 取引種別が'5'(信用返済買)または'6'(信用返済売)の場合:"建日:"
                openTradeDateDisp = "建日:";
            } else {
                // その他の場合、""
                openTradeDateDisp = StringUtil.EMPTY_STRING;
            }
        } else {
            // その他の場合:'建日:'||to_char(新規約定日,'yyyy/mm/dd')
            openTradeDateDisp = "建日:" + openTradeDate;
        }

        return openTradeDateDisp;
    }

    /**
     * 返済買付/売却(内容作成用)を取得する.
     * 
     * @param tradeKbn      取引種別
     * @return 返済買付/売却(内容作成用)
     */
    private String getTradeKbnDisp(String tradeKbn) throws Exception {

        // 返済買付/売却(内容作成用)
        String tradeKbnDisp = StringUtil.EMPTY_STRING;

        if (TjoTradeKbn.NEW_BUY.getId().equals(tradeKbn)) {
            // 取引種別＝'3'(信用新規買)の場合、"返済売却"
            tradeKbnDisp = TjoTradeKbn.REPAYMENT_SALE.getLabel();
        } else if (TjoTradeKbn.NEW_SALE.getId().equals(tradeKbn)) {
            // 取引種別＝'4'(信用新規売)の場合、"返済買付"
            tradeKbnDisp = TjoTradeKbn.REPAYMENT_BUY.getLabel();
        } else {
            // 上記以外の場合、"返済"
            tradeKbnDisp = "返済";
        }

        return tradeKbnDisp;
    }

    /**
     * 非特定預り売買区分(内容作成用)を取得する.
     * 
     * @param tradeKbn          売買区分
     * @param azukariKbn        預り区分
     * @param specificKbn       特定口座区分
     * @param jrIsaContractType ジュニアISA契約区分
     * @return 非特定預り売買区分(内容作成用)
     */
    private String getAzukariKbnDisp(String tradeKbn, String azukariKbn, String specificKbn, String jrIsaContractType)
            throws Exception {

        // 非特定預り売買区分(内容作成用)
        String azukariKbnDisp = StringUtil.EMPTY_STRING;

        if (JrIsaContractType.CONTRACT.getId().equals(jrIsaContractType)) {
            // ジュニアISA契約区分='1'(契約済)の場合、

            if (JtoTradeKbn.TRADEKBN_K.getId().equals(tradeKbn)) {
                // 売買区分＝'K'(買（購入）)の場合、
                if (JtoAzukariKbn.JTO_AZUKARI_KBN_5.getId().equals(azukariKbn)
                        || JtoAzukariKbn.JTO_AZUKARI_KBN_6.getId().equals(azukariKbn)) {
                    // 預り区分 = '5','6' の場合、
                    azukariKbnDisp = getOthersOneAzukariKbnDisp(specificKbn);
                } else {
                    // 預り区分が上記以外の場合、
                    azukariKbnDisp = getOthersTwoAzukariKbnDisp(specificKbn);
                }
            } else {
                // 売買区分≠'K'(買（購入）)の場合、
                if (JtoAzukariKbn.JTO_AZUKARI_KBN_0.getId().equals(azukariKbn)) {
                    // 預り区分='0':"総合口座－特定"
                    azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_0.getLabel();
                } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_1.getId().equals(azukariKbn)) {
                    // 預り区分='1':"総合口座－一般"
                    azukariKbnDisp = INTEGRATED_ACCOUNT + FULL_HYPHEN + JtoAzukariKbn.JTO_AZUKARI_KBN_1.getLabel();
                } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_5.getId().equals(azukariKbn)) {
                    // 預り区分='5':"ジュニアNISA口座－特定"
                    azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_5.getLabel();
                } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_6.getId().equals(azukariKbn)) {
                    // 預り区分='6':"ジュニアNISA口座－一般"
                    azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_6.getLabel();
                } else {
                    // 預り区分が上記以外の場合、
                    azukariKbnDisp = getOthersTwoAzukariKbnDisp(specificKbn);
                }
            }
        } else {
            // ジュニアISA契約区分≠'1'(契約済)の場合、
            if (JtoTradeKbn.TRADEKBN_K.getId().equals(tradeKbn)) {
                // 売買区分＝'K'(買（購入）)の場合、
                azukariKbnDisp = getOthersThreeAzukariKbnDisp(specificKbn);
            } else {
                // 売買区分≠'K'(買（購入）)の場合、
                if (JtoAzukariKbn.JTO_AZUKARI_KBN_0.getId().equals(azukariKbn)) {
                    // 預り区分='0':"特定"
                    azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_0.getLabel();
                } else if (JtoAzukariKbn.JTO_AZUKARI_KBN_1.getId().equals(azukariKbn)) {
                    // 預り区分='1':"一般"
                    azukariKbnDisp = JtoAzukariKbn.JTO_AZUKARI_KBN_1.getLabel();
                } else {
                    // 預り区分が上記以外の場合:
                    azukariKbnDisp = getOthersThreeAzukariKbnDisp(specificKbn);
                }
            }
        }
        return azukariKbnDisp;
    }

    /**
     * 乗換優遇区分(内容作成用)を取得する.
     * 
     * @param norikaeYuguKbn 乗換優遇区分
     * @return 乗換優遇区分(内容作成用)
     */
    private String getNorikaeYuguKbnDisp(String norikaeYuguKbn) throws Exception {

        // 乗換優遇区分(内容作成用)
        String norikaeYuguKbnDisp = StringUtil.EMPTY_STRING;

        if (NorikaeYuguKbn.NORIKAEYUGU_KBN.equals(norikaeYuguKbn)) {
            // 乗換優遇区分 = '1':"乗換優遇"
            norikaeYuguKbnDisp = "乗換優遇";
        } else {
            // その他:""
            norikaeYuguKbnDisp = StringUtil.EMPTY_STRING;
        }

        return norikaeYuguKbnDisp;
    }

    /**
     * 非特定預り売買区分(内容作成用)を取得する.
     * 
     * @param specificKbn 特定口座区分
     * @return 非特定預り売買区分
     */
    private String getOthersOneAzukariKbnDisp(String specificKbn) throws Exception {

        // 非特定預り売買区分(内容作成用)
        String azukariKbnDisp = StringUtil.EMPTY_STRING;

        if (SpecificKbn.TJO_PROXY_PAYMENT.equals(specificKbn)
                || SpecificKbn.TJO_CONFIRM_DECLARATION.equals(specificKbn)) {
            // 特定口座区分＝'1'(特定口座(代行納付)、'2'(特定口座(確定申告))の場合、"ジュニアNISA口座－特定/一般"
            azukariKbnDisp = "ジュニアNISA口座－特定/一般";
        } else {
            // 上記以外の場合、"ジュニアNISA口座－一般"
            azukariKbnDisp = "ジュニアNISA口座－一般";
        }

        return azukariKbnDisp;
    }

    /**
     * 非特定預り売買区分(内容作成用)を取得する.
     * 
     * @param specificKbn 特定口座区分
     * @return 非特定預り売買区分
     */
    private String getOthersTwoAzukariKbnDisp(String specificKbn) throws Exception {

        // 非特定預り売買区分(内容作成用)
        String azukariKbnDisp = StringUtil.EMPTY_STRING;

        if (SpecificKbn.TJO_PROXY_PAYMENT.equals(specificKbn)
                || SpecificKbn.TJO_CONFIRM_DECLARATION.equals(specificKbn)) {
            // 特定口座区分＝'1'(特定口座(代行納付)、'2'(特定口座(確定申告))の場合、"総合口座－特定/一般"
            azukariKbnDisp = "総合口座－特定/一般";
        } else {
            // 上記以外の場合、"総合口座－一般"
            azukariKbnDisp = "総合口座－一般";
        }

        return azukariKbnDisp;
    }

    /**
     * 非特定預り売買区分(内容作成用)を取得する.
     * 
     * @param specificKbn 特定口座区分
     * @return 乗換優遇区分
     */
    private String getOthersThreeAzukariKbnDisp(String specificKbn) throws Exception {

        // 非特定預り売買区分(内容作成用)
        String azukariKbnDisp = StringUtil.EMPTY_STRING;

        if (SpecificKbn.TJO_PROXY_PAYMENT.equals(specificKbn)
                || SpecificKbn.TJO_CONFIRM_DECLARATION.equals(specificKbn)) {
            // 特定口座区分＝'1'(特定口座(代行納付)、'2'(特定口座(確定申告))の場合:"特定/一般"
            azukariKbnDisp = "特定/一般";
        } else {
            // 上記以外の場合:"一般"
            azukariKbnDisp = "一般";
        }

        return azukariKbnDisp;
    }

    /**
     * 数量を编辑する.
     * 
     * @param quantity 数量
     * @return 数量(編集後)
     */
    public String getQuantityDisp(String quantity) {

        String quantityDisp = StringUtil.EMPTY_STRING;

        if (!StringUtil.isNullOrEmpty(quantity)) {
            // NULL以外の場合:'数量:'|| 数量
            quantityDisp = "数量:" + quantity;
        }
        return quantityDisp;
    }

    /**
     * 文字列を编辑する.
     * 
     * @param val 文字列
     * @return 文字列(編集後)
     */
    public String getVal(String val) {

        String resultVal = StringUtil.isNullOrEmpty(val) ? StringUtil.EMPTY_STRING : val;

        return resultVal;
    }

    /**
     * 文字列を编辑する.
     * 
     * @param val 文字列
     * @return 文字列(編集後)
     */
    public String getDefaultVal(String val) {

        String resultVal = StringUtil.isNullOrEmpty(val) ? StringUtil.EMPTY_STRING : FULL_SPACE + val;

        return resultVal;
    }

    /**
     * 文字列を编辑する.
     * 
     * @param val 文字列
     * @return 文字列(編集後)
     */
    public String getDefaultValWithFullSpace(String val) {

        String resultVal = StringUtil.isNullOrEmpty(val) ? StringUtil.EMPTY_STRING : HALF_SPACE + val;

        return resultVal;
    }

    /**
     * 同一のIFA注文番号内で最大情報を取得する.
     * 
     * @return 同一のIFA注文番号内で最大情報
     */
    public Map<String, List<String>> getJtoMaxInfoMap() throws Exception {

        // IFA注文番号毎の最大情報
        Map<String, List<String>> maxInfoMap = new HashMap<>();

        // 同一のIFA注文番号内で最大情報を取得する。
        DataList<IfaContactSql021ResponseModel> selSql021Res = ifaContactDao.selectIfaContactSql021();

        if (selSql021Res != null && CollectionUtils.isNotEmpty(selSql021Res.getDataList())) {

            // 最大情報の初期化
            List<String> info = null;
            for (IfaContactSql021ResponseModel selSql013Info : selSql021Res.getDataList()) {
                if (selSql013Info != null) {
                    // 最大情報
                    info = new ArrayList<>();
                    // ポイント(MAX)
                    info.add(0, selSql013Info.getPoint());
                    // 精算金額（概算）(MAX)
                    info.add(1, selSql013Info.getNetAmount());
                    // IFA注文番号毎情報を設定する。
                    maxInfoMap.put(selSql013Info.getIfaOrderNo(), info);
                }
            }
        }

        return maxInfoMap;
    }

    /**
     * 同一のトランザクションID内で最大情報を取得する.
     * 
     * @return 同一のトランザクションID内で最大情報
     */
    public Map<String, List<String>> getTaoiMaxInfoMap() throws Exception {

        // トランザクションID毎の最大情報
        Map<String, List<String>> maxInfoMap = new HashMap<>();

        // 同一のトランザクションID内で最大情報を取得する。
        DataList<IfaContactSql022ResponseModel> selSql022Res = ifaContactDao.selectIfaContactSql022();

        if (selSql022Res != null && CollectionUtils.isNotEmpty(selSql022Res.getDataList())) {

            // 最大情報の初期化
            List<String> info = null;
            for (IfaContactSql022ResponseModel selSql022Info : selSql022Res.getDataList()) {
                if (selSql022Info != null) {
                    // 最大情報
                    info = new ArrayList<>();
                    // 拘束金額（NISA）(MAX)
                    info.add(0, selSql022Info.getNisaKingaku());
                    // 精算金額(MAX)
                    info.add(1, selSql022Info.getSeisanKingaku());
                    // トランザクションID毎情報を設定する。
                    maxInfoMap.put(selSql022Info.getTranId(), info);
                }
            }
        }
        return maxInfoMap;
    }

    /* ------------------- 以下は： CCS API関連処理 プライベートメソッド ------------------- */
    /** エラーメッセージ:接触履歴処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ：{1}) */
    public static final String ERRORS_CMN_CONTACTHISTORY_FAILED = "errors.cmn.contactHistory.failed";
    /** エラーメッセージ:APIの接続に失敗しました。しばらく時間をおいてからお試しください。(エラーコード：{0}) */
    public static final String ERRORS_CCS_APICONNECTIONFAILED = "errors.CCS.APIConnectionFailed";
    /** ワーニングメッセージ:ログインユーザーに紐づくCCSIDが未登録のため、CCS接触履歴を表示できません。 */
    public static final String WARNINGS_CMN_CONTACTHISTORY_UNAVAILABLE = "warnings.cmn.contactHistory.unavailable";

    /**
     * エラー時のメッセージ格納クラス
     */
    @Data
    public class ErrorResponseDto {
        /** エラー判定 */
        private ErrorLevel errorLevel;
        /** エラーメッセージID */
        private String errorMessageId;
        /** エラーメッセージ */
        private String errorMessage;
    }

    /**
     * CCS APIから、データリストを作成するメソッド。
     * @param x_dataList: 処理対象のデータリスト（例: IfaContactA001ResponseDto のリスト）
     * @param x_ccsApiErr: CCS API から取得したエラー情報（CcsApiErrUtil クラスのインスタンス）
     * @return DataList<T>: エラー情報を含むデータリスト。成功時はエラーレベルが SUCCESS になる。
     */
    public <T> DataList<T> createDataList(List<T> x_dataList, CcsApiErrUtil x_ccsApiErr) {
        // エラー情報が null の場合、正常終了として処理
        if (x_ccsApiErr == null) {
            return IfaCommonUtil.createDataList(x_dataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }
        // エラーレベルが接続エラーの場合、致命的なエラーとして処理
        if (x_ccsApiErr.getErrLevel() == -1) {
            return IfaCommonUtil.createDataList(x_dataList,
                    ErrorLevel.FATAL,
                    ERRORS_CCS_APICONNECTIONFAILED,
                    IfaCommonUtil.getMessage(ERRORS_CCS_APICONNECTIONFAILED, 
                            new String[] { "E9999" }));
        // エラーレベルがAPI OUTエラーの場合、エラー情報を表示
        } else if (x_ccsApiErr.getErrLevel() == 1) {
            return IfaCommonUtil.createDataList(x_dataList,
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CONTACTHISTORY_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CONTACTHISTORY_FAILED, 
                            new String[] { x_ccsApiErr.getErrId(), x_ccsApiErr.getErrMsg() }));
        // エラーレベルがワーニングの場合、ワーニング情報を表示
        } else if (x_ccsApiErr.getErrLevel() == 2) {
            return IfaCommonUtil.createDataList(x_dataList,
                    ErrorLevel.WARNING,
                    WARNINGS_CMN_CONTACTHISTORY_UNAVAILABLE,
                    IfaCommonUtil.getMessage(WARNINGS_CMN_CONTACTHISTORY_UNAVAILABLE));
        // エラーレベルが正常の場合、正常終了として処理
        } else {
            return IfaCommonUtil.createDataList(x_dataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }
    }

}