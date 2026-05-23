package com.sbisec.helios.ap.bizcommon.component;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.common.util.DateFormatUtil;

/**
 * 共通関数：FCT037
 * 国内株式注文条件取得
 *
 * @author SCSK
 */

@Component
public class Fct037 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct037.class);
    
    /** 数値（0～9、.、,）のみの文字列判定 */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9.,]+");
    
    /** 注文訂正ステータスが 「1:訂正中」 */
    private static final String ORDER_CORRECT_STATUS_CORRECT = "1";
    
    /** RBE注文ステータス 判定用定数　*/
    private static enum RbeOrderStatus {
        
        /** 通常注文 */
        NORMAL(" "),
        /** 発火済 */
        WORKING("1"),;
        
        /** 自動注文種別 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private RbeOrderStatus(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の設定
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /** 自動注文種別 判定用定数　*/
    private static enum AutoOrderClass {
        
        /** 通常注文/逆指注文/OCO注文 */
        NORMAL_STOP_OCO("    "),
        /** IFD子注文 */
        IFD_CHILD("DONE"),
        /** IFD親注文 */
        IFD_PARENT("IF  "),;
        
        /** 自動注文種別 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private AutoOrderClass(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の設定
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /** REB注文種別 判定用定数 */
    private static enum RbeOrderClass {
        
        /** 通常注文 */
        NORMAL("   "),
        /** 逆指値注文 */
        STOP("SLO"),
        /** OCO注文 */
        OCO("OCO"),;
        
        /** REB注文種別 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private RbeOrderClass(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の設定
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /** 直近トリガ発動ゾーン 判定用定数 */
    private static enum LatestTriggerZone {
        
        /** 指定なし */
        NONE(" "),
        /** 以上 */
        GREATER_EQUAL("0"),
        /** 以下 */
        LESS_EQUAL("1"),;
        
        /** 直近トリガ発動ゾーン 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private LatestTriggerZone(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の設定
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /** 指成区分 判定用定数 */
    private static enum SashinariKbn {
        
        /** 指値 */
        PRICE(" "),
        /** 寄指(Y) */
        YORISASHI("Z"),
        /** 引指(H) */
        HIKESASHI("I"),
        /** 不成(F) */
        HUNARI("F"),
        /** IOC指(I) */
        IOC_SASHI("P"),
        /** 成行 */
        NARIYUKI("N"),
        /** 寄成(Y) */
        YORINARI("Y"),
        /** 引成(H) */
        HIKENARI("H"),
        /** IOC成(I) */
        IOC_NARI("O"),;
        
        /** 指成区分 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private SashinariKbn(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の設定
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /** 条件文言 生成判定用定数 */
    private static enum ConditionsStringType {
        
        /** 条件文言１ */
        COND_STR_TYPE_01("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言２ */
        COND_STR_TYPE_02("現在値が%s%sになった時点で執行"),
        /** 条件文言３ */
        COND_STR_TYPE_03("現在値が%s%sになった時点で%sに訂正"),
        /** 条件文言４ */
        COND_STR_TYPE_04("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言５ */
        COND_STR_TYPE_05("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言６ */
        COND_STR_TYPE_06("現在値が%s%sになった時点で執行"),
        /** 条件文言７ */
        COND_STR_TYPE_07("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言８ */
        COND_STR_TYPE_08("現在値が%s%sになった時点で執行"),
        /** 条件文言９ */
        COND_STR_TYPE_09("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言１０ */
        COND_STR_TYPE_10("現在値が%s%sになった時点で%sに訂正"),
        /** 条件文言１１ */
        COND_STR_TYPE_11("現在値が%s%sになった時点で%sで執行"),
        /** 条件文言１２ */
        COND_STR_TYPE_12("現在値が%s%sになった時点で執行"),
        /** 条件文言１３ */
        COND_STR_TYPE_13("現在値が%s%sになった時点で%sに訂正"),;
        
        /** 条件文言 生成判定用数値 */
        private final String format;
        
        /**
         * 定数値の設定
         *
         * @param format 文言生成時のフォーマット
         */
        private ConditionsStringType(final String format) {
            
            this.format = format;
        }
        
        /**
         * 文言生成時のフォーマットの取得
         *
         * @return 文言生成時のフォーマット
         */
        public String getFormat() {
            
            return this.format;
        }
    }
    
    /**
     * 国内株式注文条件取得
     *
     * @param input リクエスト
     * @return レスポンス
     */
    public OutputFct037Dto getData(InputFct037Dto input) {
        
        // デバッグ　ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct037.getData");
        }
        
   
        // ①注文条件を算出する
        String orderConditions = "";
        if ((StringUtils.equals(AutoOrderClass.NORMAL_STOP_OCO.getStringValue(), input.getAutoOrderClass())
                || StringUtils.equals(AutoOrderClass.IFD_CHILD.getStringValue(), input.getAutoOrderClass())) || 
                input.getAutoOrderClass() == null) {
            // リクエスト.自動注文種別が 「"△△△△"：通常注文/逆指注文/OCO注文」  または  「"DONE"：IFD子注文」 の場合
            
            if (StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getRbeChumonShubetsu())) {
                // リクエスト.RBE注文種別が 「"△△△"：通常注文」 の場合
                // 注文条件：　""
                orderConditions = "";
            } else if (StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getRbeChumonShubetsu())) {
                // リクエスト.RBE注文種別が 「"SLO"：逆指値注文」 の場合
                
                if (StringUtils.equals(RbeOrderStatus.WORKING.getStringValue(), input.getRbeOrderStatus()) == false) {
                    // リクエスト.RBE注文ステータスが 「1：発火済」以外 の場合
                    
                    // ※条件文言①："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で" +  ③執行条件 +  "で執行"
                    String conditionStr = this.createConditionString(ConditionsStringType.COND_STR_TYPE_01,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()),
                            this.getExecutionCondition(input.getSashinariKbn(), input.getSashine()));
                    
                    if (StringUtils.equals(Fct037.ORDER_CORRECT_STATUS_CORRECT, input.getOrderCorrectStatus())
                            && StringUtils.equals(RbeOrderStatus.NORMAL.getStringValue(),
                                    input.getRbeOrderStatus()) == false) {
                        // リクエスト.注文訂正ステータスが 「1:訂正中である」 かつ (リクエスト.RBE注文ステータスが 「△:通常注文」以外) の場合
                        
                        // 注文条件：　"逆指値：(" +  条件文言① +  ")"
                        orderConditions = "逆指値：(" + conditionStr + ")";
                    } else {
                        // 上記以外の場合"
                        
                        // 注文条件：　"逆指値：" +  条件文言①
                        orderConditions = "逆指値：" + conditionStr;
                    }
                } else {
                    // リクエスト.RBE注文ステータスが「1:発火済」の場合
                    
                    // ※条件文言②："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で執行"
                    String conditionStr = this.createConditionString(ConditionsStringType.COND_STR_TYPE_02,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()));
                    
                    // 注文条件：　"逆指値執行済：" +  条件文言②
                    orderConditions = "逆指値執行済：" + conditionStr;
                }
            } else if (StringUtils.equals(RbeOrderClass.OCO.getStringValue(), input.getRbeChumonShubetsu())) {
                // リクエスト.RBE注文種別が「OCO：OCO注文」の場合
                
                // ※条件文言③："現在値が" +  ①直近トリガ値段(編集) + ②直近トリガ発動ゾーン(編集) +  "になった時点で" + ⑤OCO訂正条件 + "に訂正"
                String conditionStr = this.createConditionString(ConditionsStringType.COND_STR_TYPE_03,
                        this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                        this.getEditLatestTriggerZone(input.getLatestTriggerZone()), this.getOcoCorrectConditions(
                                input.getLatestOcoLimitMarketType(), input.getLatestOcoLimitlimitPrice()));
                
                if (StringUtils.equals(RbeOrderStatus.WORKING.getStringValue(), input.getRbeOrderStatus()) == false) {
                    // リクエスト.RBE注文ステータスが「1:発火済」以外の場合
                    
                    if (StringUtils.equals(Fct037.ORDER_CORRECT_STATUS_CORRECT, input.getOrderCorrectStatus())
                            && StringUtils.equals(RbeOrderStatus.NORMAL.getStringValue(),
                                    input.getRbeOrderStatus()) == false) {
                        // リクエスト.注文訂正ステータスが 「1:訂正中である」 かつ (リクエスト.RBE注文ステータスが「△:通常注文」以外)の場合
                        
                        // 注文条件：　"OCO：(" +  条件文言③ +  ")"
                        orderConditions = "OCO：(" + conditionStr + ")";
                    } else {
                        // 上記以外の場合
                        
                        // 注文条件：　"OCO：" +  条件文言③
                        orderConditions = "OCO：" + conditionStr;
                    }
                } else {
                    // リクエスト.RBE注文ステータスが「1:発火済」の場合
                    
                    // 注文条件：　"OCO執行済：" +  条件文言③
                    orderConditions = "OCO執行済：" + conditionStr;
                }
            }
        } else if (StringUtils.equals(AutoOrderClass.IFD_PARENT.getStringValue(), input.getAutoOrderClass())) {
            // リクエスト.自動注文種別が「IF△△:IFD親注文」の場合
            
            if (StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「△△△：通常注文」かつ リクエスト.DONE RBE注文種別が「△△△：通常注文」の場合
                // 注文条件："IFD2（通常）：" + ⑦DONE執行条件 + ⑨DONE 有効期限(編集)
                orderConditions = "IFD2（通常）："
                        + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                input.getDoneLimitPrice())
                        + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
            } else if (StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「△△△：通常注文」かつ リクエスト.DONE RBE注文種別が「SLO：逆指値注文」の場合
                
                // ※条件文言④："現在値が" +  ⑩DONE トリガ値段(編集) + ⑪DONE トリガ発動ゾーン(編集) + "になった時点で" + ⑦DONE執行条件 + "で執行"
                String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_04,
                        this.getEditDoneTriggerNedan(input.getDoneTriggerNedan()),
                        this.getEditDoneTriggerZone(input.getDoneTriggerZone()),
                        this.getDoneExecutionCondition(input.getDoneLimitMarketType(), input.getDoneLimitPrice()));
                
                // 注文条件："IFD2（逆指値）：" +  条件文言④ + ⑨DONE 有効期限(編集) 
                orderConditions = "IFD2（逆指値）：" + conditionString
                        + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
            } else if (StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「SLO：逆指値注文」かつ リクエスト.DONE RBE注文種別が「△△△：通常注文」の場合
                
                if (StringUtils.equals(RbeOrderStatus.WORKING.getStringValue(), input.getRbeOrderStatus()) == false) {
                    // リクエスト.RBE注文ステータスが「1:発火済」以外の場合
                    
                    // ※条件文言⑤："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で" +  ③執行条件 +  "で執行"
                    String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_05,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()),
                            this.getExecutionCondition(input.getSashinariKbn(), input.getSashine()));
                    
                    if (StringUtils.equals(Fct037.ORDER_CORRECT_STATUS_CORRECT, input.getOrderCorrectStatus())
                            && StringUtils.equals(RbeOrderStatus.NORMAL.getStringValue(),
                                    input.getRbeOrderStatus()) == false) {
                        // リクエスト.注文訂正ステータスが 「1:訂正中である」 かつ (リクエスト.RBE注文ステータスが「△:通常注文」以外)の場合
                        // 注文条件： "IFD1（逆指値）：(" +  条件文言⑤ +  ")" +  "<BR>" +
                        // "IFD2（通常）：" + ⑦DONE執行条件 + ⑨DONE 有効期限(編集)
                        orderConditions = "IFD1（逆指値）：(" + conditionString + ")" + "<BR>" + "IFD2（通常）："
                                + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                        input.getDoneLimitPrice())
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                    } else {
                        // 上記以外の場合
                        
                        // 注文条件： "IFD1（逆指値）：" +  条件文言⑤ +  "<BR>" +
                        // "IFD2（通常）：" + ⑦DONE執行条件 + ⑫DONE 有効期限（編集）
                        orderConditions = "IFD1（逆指値）：" + conditionString + "<BR>" + "IFD2（通常）："
                                + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                        input.getDoneLimitPrice())
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                    }
                } else {
                    // リクエスト.RBE注文ステータスが「1:発火済」の場合
                    
                    // ※条件文言⑥："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で執行"
                    String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_06,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()));
                    
                    // 注文条件： "IFD1（逆指値執行済）：" +  条件文言⑥ +  "<BR>" +
                    // "IFD2（通常）：" + ⑦DONE執行条件+  ⑨DONE 有効期限(編集)
                    orderConditions = "IFD1（逆指値執行済）：" + conditionString + "<BR>" + "IFD2（通常）："
                            + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                    input.getDoneLimitPrice())
                            + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                }
            } else if (StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「SLO：逆指値注文」かつ リクエスト.DONE RBE注文種別が「SLO：逆指値注文」の場合
                
                // ※条件文言⑨："現在値が" +  ⑩DONE トリガ値段(編集) +  ⑪DONE トリガ発動ゾーン(編集) + "になった時点で" + ⑦DONE執行条件 + "で執行"
                String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_09,
                        this.getEditDoneTriggerNedan(input.getDoneTriggerNedan()),
                        this.getEditDoneTriggerZone(input.getDoneTriggerZone()),
                        this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                input.getDoneLimitPrice()));
                
                if (StringUtils.equals(RbeOrderStatus.WORKING.getStringValue(), input.getRbeOrderStatus()) == false) {
                    // リクエスト.RBE注文ステータスが「1:発火済」以外の場合
                    
                    // ※条件文言⑦："現在値が" + ①直近トリガ値段(編集) + ②直近トリガ発動ゾーン(編集) + "になった時点で" + ③執行条件 + "で執行"
                    String conditionString2 = this.createConditionString(ConditionsStringType.COND_STR_TYPE_07,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()),
                            this.getExecutionCondition(input.getSashinariKbn(), input.getSashine()));
                    
                    if (StringUtils.equals(Fct037.ORDER_CORRECT_STATUS_CORRECT, input.getOrderCorrectStatus())
                            && StringUtils.equals(RbeOrderStatus.NORMAL.getStringValue(),
                                    input.getRbeOrderStatus()) == false) {
                        // リクエスト.注文訂正ステータスが 「1:訂正中である」 かつ (リクエスト.RBE注文ステータスが「△:通常注文」以外)の場合
                        
                        // 注文条件： "IFD1（逆指値）：(" +  条件文言⑦ +  ")" +  "<BR>" +
                        // "IFD2（逆指値）：" +  条件文言⑨ + ⑨DONE 有効期限(編集)
                        orderConditions = "IFD1（逆指値）：(" + conditionString2 + ")" + "<BR>" + "IFD2（逆指値）："
                                + conditionString
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                    } else {
                        // 上記以外の場合
                        // 注文条件：　　"IFD1（逆指値）：" +  条件文言⑦+  "<BR>" +
                        // "IFD2（逆指値）：" +  条件文言⑨ + ⑨DONE 有効期限(編集)
                        orderConditions = "IFD1（逆指値）：" + conditionString2 + "<BR>" + "IFD2（逆指値）：" + conditionString
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                    }
                } else {
                    // RBE注文ステータスが「1:発火済」の場合
                    
                    // ※条件文言⑧："現在値が" + ①直近トリガ値段(編集) + ②直近トリガ発動ゾーン(編集) + "になった時点で執行"
                    String conditionString2 = this.createConditionString(ConditionsStringType.COND_STR_TYPE_08,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()));
                    
                    // 注文条件：　"IFD1（逆指値執行済）：" +  条件文言⑧ +  "<BR>" +
                    // "IFD2（逆指値）：" +  条件文言⑨ + ⑨DONE 有効期限(編集)
                    orderConditions = "IFD1（逆指値執行済）：" + conditionString2 + "<BR>" + "IFD2（逆指値）：" + conditionString
                            + this.getEditDoneExpirationDate(input.getDoneExpirationDate());
                }
            } else if (StringUtils.equals(RbeOrderClass.NORMAL.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.OCO.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「△△△：通常注文」かつ リクエスト.DONE RBE注文種別が「OCO：OCO注文」の場合
                
                // ※条件文言⑩："現在値が" + ⑩DONE トリガ値段(編集) + ⑪DONE トリガ発動ゾーン(編集) + "になった時点で" + ⑫DONE OCO訂正条件 + "に訂正"
                String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_10,
                        this.getEditDoneTriggerNedan(input.getDoneTriggerNedan()),
                        this.getEditDoneTriggerZone(input.getDoneTriggerZone()), this.getDoneOcoCorrectConditions(
                                input.getDoneOcoLimitMarketType(), input.getDoneOcoLimitPrice()));
                
                // 注文条件：  "IFD2（OCO1）：" + ⑦DONE執行条件 + ⑨DONE 有効期限(編集) +  "<BR>" +
                // "IFD2（OCO2）：" +  条件文言⑩
                //
                orderConditions = "IFD2（OCO1）："
                        + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                input.getDoneLimitPrice())
                        + this.getEditDoneExpirationDate(input.getDoneExpirationDate()) + "<BR>"
                        + "IFD2（OCO2）：" + conditionString;
            } else if (StringUtils.equals(RbeOrderClass.STOP.getStringValue(), input.getRbeChumonShubetsu())
                    && StringUtils.equals(RbeOrderClass.OCO.getStringValue(), input.getDoneRbeOrderClass())) {
                // リクエスト.RBE注文種別が「SLO：逆指値注文」かつ リクエスト.DONE RBE注文種別が「OCO：OCO注文」の場合
                
                // ※条件文言⑬："現在値が" +  ⑩DONE トリガ値段(編集) + ⑪DONE トリガ発動ゾーン(編集) + "になった時点で" + ⑫DONE OCO訂正条件 + "に訂正"
                String conditionString = this.createConditionString(ConditionsStringType.COND_STR_TYPE_13,
                        this.getEditDoneTriggerNedan(input.getDoneTriggerNedan()),
                        this.getEditDoneTriggerZone(input.getDoneTriggerZone()), this.getDoneOcoCorrectConditions(
                                input.getDoneOcoLimitMarketType(), input.getDoneOcoLimitPrice()));
                
                if (StringUtils.equals(RbeOrderStatus.WORKING.getStringValue(), input.getRbeOrderStatus()) == false) {
                    // リクエスト.RBE注文ステータスが「1:発火済」以外の場合
                    
                    // ※条件文言⑪："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で" +  ③執行条件 +  "で執行"
                    String conditionString2 = this.createConditionString(ConditionsStringType.COND_STR_TYPE_11,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()),
                            this.getExecutionCondition(input.getSashinariKbn(), input.getSashine()));
                    
                    if (StringUtils.equals(Fct037.ORDER_CORRECT_STATUS_CORRECT, input.getOrderCorrectStatus())
                            && StringUtils.equals(RbeOrderStatus.NORMAL.getStringValue(),
                                    input.getRbeOrderStatus()) == false) {
                        // リクエスト.注文訂正ステータスが 「1:訂正中である」 かつ (リクエスト.RBE注文ステータスが「△:通常注文」以外)の場合
                        
                        // 注文条件：   "IFD1（逆指値）：(" +  条件文言⑪ +  ")" +  "<BR>" +
                        // "IFD2（OCO1）：" + ⑦DONE執行条件 +  ⑨DONE 有効期限(編集) +  "<BR>" +
                        // "IFD2（OCO2）：" +  条件文言⑬
                        orderConditions = "IFD1（逆指値）：(" + conditionString2 + ")" + "<BR>" + "IFD2（OCO1）："
                                + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                        input.getDoneLimitPrice())
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate())
                                + "<BR>" + "IFD2（OCO2）：" + conditionString;
                    } else {
                        // 上記以外の場合
                        
                        // 注文条件：   "IFD1（逆指値）：" +  条件文言⑪ +  "<BR>" +
                        // "IFD2（OCO1）：" + ⑦DONE執行条件 + ⑨DONE 有効期限(編集) +  "<BR>" +
                        // "IFD2（OCO2）：" +  条件文言⑬
                        orderConditions = "IFD1（逆指値）：" + conditionString2 + "<BR>" + "IFD2（OCO1）："
                                + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                        input.getDoneLimitPrice())
                                + this.getEditDoneExpirationDate(input.getDoneExpirationDate())
                                + "<BR>" + "IFD2（OCO2）：" + conditionString;
                    }
                } else {
                    // RBE注文ステータスが「1:発火済」の場合：
                    
                    // ※条件文言⑫："現在値が" +  ①直近トリガ値段(編集) +  ②直近トリガ発動ゾーン(編集) +  "になった時点で執行"
                    String conditionString2 = this.createConditionString(ConditionsStringType.COND_STR_TYPE_12,
                            this.getEditLatestTriggerNedan(input.getLatestTriggerNedan()),
                            this.getEditLatestTriggerZone(input.getLatestTriggerZone()));
                    
                    // 注文条件：   "IFD1（逆指値執行済）：" +  条件文言⑫ +  "<BR>" +
                    // "IFD2（OCO1）：" + ⑦DONE執行条件 + ⑨DONE 有効期限(編集) +  "<BR>" +
                    // "IFD2（OCO2）：" +  条件文言⑬
                    orderConditions = "IFD1（逆指値執行済）：" + conditionString2 + "<BR>" + "IFD2（OCO1）："
                            + this.getDoneExecutionCondition(input.getDoneLimitMarketType(),
                                    input.getDoneLimitPrice())
                            + this.getEditDoneExpirationDate(input.getDoneExpirationDate()) + "<BR>"
                            + "IFD2（OCO2）：" + conditionString;
                }
            }
        }
        
        // 自動注文種別が上記以外の場合：""
        
        // ②   レスポンスを返す。
        OutputFct037Dto resDto = new OutputFct037Dto();
        resDto.setOrderConditions(orderConditions);
        
        return resDto;
    }
    
    /**
     * ①直近トリガ値段(編集) を取得
     *
     * @param latestTriggerNedan 直近トリガ値段
     * @return 直近トリガ値段(編集)
     */
    private String getEditLatestTriggerNedan(String latestTriggerNedan) {
        
        String editNedan = null;
        
        // ①直近トリガ値段(編集)
        if (latestTriggerNedan == null || NUMBER_PATTERN.matcher(latestTriggerNedan).matches() == false) {
            // リクエスト.直近トリガ値段に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
            // "-"
            editNedan = "-";
        } else {
            // 上記以外の場合：
            // リクエスト.直近トリガ値段 +  "円"
            editNedan = latestTriggerNedan + "円";
        }
        
        return editNedan;
    }
    
    /**
     * ②直近トリガ発動ゾーン(編集) を取得
     *
     * @param latestTriggerZone 直近トリガ発動ゾーン
     * @return ②直近トリガ発動ゾーン(編集)
     */
    private String getEditLatestTriggerZone(String latestTriggerZone) {
        
        String editZone = "";
        
        // ②直近トリガ発動ゾーン(編集)
        //リクエスト.直近トリガ発動ゾーンを区分値変換。（区分.直近トリガ発動ゾーン.表示パターン1）
        if (StringUtils.equals(LatestTriggerZone.NONE.getStringValue(), latestTriggerZone)) {
            editZone = "指定なし";
        } else if (StringUtils.equals(LatestTriggerZone.GREATER_EQUAL.getStringValue(), latestTriggerZone)) {
            editZone = "以上";
        } else if (StringUtils.equals(LatestTriggerZone.LESS_EQUAL.getStringValue(), latestTriggerZone)) {
            editZone = "以下";
        }
        
        return editZone;
    }
    
    /**
     * ③執行条件の取得
     *
     * @param sashinariKbn 指成区分
     * @param sashine 指値
     * @return ③執行条件
     */
    private String getExecutionCondition(String sashinariKbn, String sashine) {
        
        String executionCondition = null;
        // ③執行条件
        if (StringUtils.equals(" ", sashinariKbn) || StringUtils.equals("Z", sashinariKbn)
                || StringUtils.equals("I", sashinariKbn) || StringUtils.equals("F", sashinariKbn)
                || StringUtils.equals("P", sashinariKbn)) {
            // リクエスト.指成区分が、 「'△':指値」、「'Z':寄指」、 「'I':引指」、「'Ｆ':不成」、「'P':IOC指」 のいずれかの場合：
            
            if (sashine == null || NUMBER_PATTERN.matcher(sashine).matches() == false) {
                // リクエスト.指値に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
                
                // ④指成区分(編集) + "/-円"
                executionCondition = this.getEditSashinariKbn(sashinariKbn) + "/-円";
            } else {
                // 上記以外の場合：
                
                // ④指成区分(編集) + "/" +  リクエスト.指値 + "円"
                executionCondition = this.getEditSashinariKbn(sashinariKbn) + "/" + sashine + "円";
            }
        } else {
            // 上記以外の場合：
            // ④指成区分(編集)
            executionCondition = this.getEditSashinariKbn(sashinariKbn);
        }
        
        return executionCondition;
    }
    
    /**
     * ④指成区分(編集)の取得
     *
     * @param sashinariKbn 指成区分
     * @return ④指成区分(編集)
     */
    private String getEditSashinariKbn(String sashinariKbn) {
        
        String editKbn = null;
        
        // ④指成区分(編集)
        // リクエスト.指成区分を区分値変換。（区分.指成区分.表示パターン1）
        if (StringUtils.equals(SashinariKbn.PRICE.getStringValue(), sashinariKbn)) {
            editKbn = "指値";
        } else if (StringUtils.equals(SashinariKbn.YORISASHI.getStringValue(), sashinariKbn)) {
            editKbn = "寄指(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKESASHI.getStringValue(), sashinariKbn)) {
            editKbn = "引指(H)";
        } else if (StringUtils.equals(SashinariKbn.HUNARI.getStringValue(), sashinariKbn)) {
            editKbn = "不成(F)";
        } else if (StringUtils.equals(SashinariKbn.IOC_SASHI.getStringValue(), sashinariKbn)) {
            editKbn = "IOC指(I)";
        } else if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), sashinariKbn)) {
            editKbn = "成行";
        } else if (StringUtils.equals(SashinariKbn.YORINARI.getStringValue(), sashinariKbn)) {
            editKbn = "寄成(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKENARI.getStringValue(), sashinariKbn)) {
            editKbn = "引成(H)";
        } else if (StringUtils.equals(SashinariKbn.IOC_NARI.getStringValue(), sashinariKbn)) {
            editKbn = "IOC成(I)";
        }
        
        return editKbn;
    }
    
    /**
     * ⑤OCO訂正条件の取得
     *
     * @param latestOcoLimitMarketType 直近OCO指成区分
     * @param latestOcoLimitlimitPrice 直近OCO値段
     * @return OCO訂正条件
     */
    private String getOcoCorrectConditions(String latestOcoLimitMarketType, String latestOcoLimitlimitPrice) {
        
        String ocoCorrectConditions = null;
        
        // ⑤OCO訂正条件
        if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), latestOcoLimitMarketType)) {
            // リクエスト.直近OCO指成区分が「'N':成行」の場合：
            // ⑥直近OCO指成区分(編集)
            ocoCorrectConditions = this.getEditLatestOcoLimitMarketType(latestOcoLimitMarketType);
        } else {
            // 上記以外の場合：
            
            if (latestOcoLimitlimitPrice == null || NUMBER_PATTERN.matcher(latestOcoLimitlimitPrice).matches() == false) {
                // リクエスト.直近OCO値段に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
                // ⑥直近OCO指成区分(編集) + "/-円"
                ocoCorrectConditions = this.getEditLatestOcoLimitMarketType(latestOcoLimitMarketType) + "/-円";
            } else {
                // 上記以外の場合：
                
                // ⑥直近OCO指成区分(編集) + "/" + リクエスト.直近OCO値段 + "円"
                ocoCorrectConditions = this.getEditLatestOcoLimitMarketType(latestOcoLimitMarketType) + "/"
                        + latestOcoLimitlimitPrice + "円";
            }
        }
        
        return ocoCorrectConditions;
    }
    
    /**
     * ⑥直近OCO指成区分(編集)の取得
     *
     * @param latestOcoLimitMarketType 直近OCO指成区分
     * @return OCO訂正条件
     */
    private String getEditLatestOcoLimitMarketType(String latestOcoLimitMarketType) {
        
        String editType = null;
        
        // リクエスト.直近OCO指成区分を区分値変換。（区分.指成区分.表示パターン1）
        if (StringUtils.equals(SashinariKbn.PRICE.getStringValue(), latestOcoLimitMarketType)) {
            editType = "指値";
        } else if (StringUtils.equals(SashinariKbn.YORISASHI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "寄指(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKESASHI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "引指(H)";
        } else if (StringUtils.equals(SashinariKbn.HUNARI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "不成(F)";
        } else if (StringUtils.equals(SashinariKbn.IOC_SASHI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "IOC指(I)";
        } else if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "成行";
        } else if (StringUtils.equals(SashinariKbn.YORINARI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "寄成(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKENARI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "引成(H)";
        } else if (StringUtils.equals(SashinariKbn.IOC_NARI.getStringValue(), latestOcoLimitMarketType)) {
            editType = "IOC成(I)";
        }
        return editType;
    }
    
    /**
     * ⑦DONE執行条件の取得
     *
     * @param doneLimitMarketType DONE 指成区分
     * @param latestOcoLimitlimitPrice 直近OCO値段
     * @param doneLimitPrice DONE 指値
     * @return DONE執行条件
     */
    private String getDoneExecutionCondition(String doneLimitMarketType, String doneLimitPrice) {
        
        String executionCondition = null;
        
        if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), doneLimitMarketType)
                || StringUtils.equals(SashinariKbn.HIKENARI.getStringValue(), doneLimitMarketType)) {
            // リクエスト.DONE 指成区分が「"N":成行」または「'H':引成」の場合：
            // ⑧DONE 指成区分(編集)
            
            executionCondition = this.getEditDoneLimitMarketType(doneLimitMarketType);
        } else {
            // 上記以外の場合：
            
            if (doneLimitPrice == null || NUMBER_PATTERN.matcher(doneLimitPrice).matches() == false) {
                // リクエスト.直近OCO値段に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
                // ⑧DONE 指成区分(編集) + "/-円"
                executionCondition = this.getEditDoneLimitMarketType(doneLimitMarketType) + "/-円";
            } else {
                // 上記以外の場合：
                // ⑧DONE 指成区分(編集) + "/" + リクエスト.DONE 指値 + "円"
                executionCondition = this.getEditDoneLimitMarketType(doneLimitMarketType) + "/" + doneLimitPrice + "円";
            }
        }
        return executionCondition;
    }
    
    /**
     * ⑥DONE指成区分(編集)の取得
     *
     * @param doneLimitMarketType DONE 指成区分
     * @return DONE指成区分(編集)
     */
    private String getEditDoneLimitMarketType(String doneLimitMarketType) {
        
        // ⑧DONE 指成区分(編集)
        String editType = null;
        
        // リクエスト.DONE 指成区分を区分値変換。（区分.指成区分.表示パターン1）
        if (StringUtils.equals(SashinariKbn.PRICE.getStringValue(), doneLimitMarketType)) {
            editType = "指値";
        } else if (StringUtils.equals(SashinariKbn.YORISASHI.getStringValue(), doneLimitMarketType)) {
            editType = "寄指(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKESASHI.getStringValue(), doneLimitMarketType)) {
            editType = "引指(H)";
        } else if (StringUtils.equals(SashinariKbn.HUNARI.getStringValue(), doneLimitMarketType)) {
            editType = "不成(F)";
        } else if (StringUtils.equals(SashinariKbn.IOC_SASHI.getStringValue(), doneLimitMarketType)) {
            editType = "IOC指(I)";
        } else if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), doneLimitMarketType)) {
            editType = "成行";
        } else if (StringUtils.equals(SashinariKbn.YORINARI.getStringValue(), doneLimitMarketType)) {
            editType = "寄成(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKENARI.getStringValue(), doneLimitMarketType)) {
            editType = "引成(H)";
        } else if (StringUtils.equals(SashinariKbn.IOC_NARI.getStringValue(), doneLimitMarketType)) {
            editType = "IOC成(I)";
        }
        return editType;
    }
    
    /**
     * ⑨DONE 有効期限(編集)の取得
     *
     * @param doneExpirationDate DONE 有効期限
     * @return DONE 有効期限(編集)
     */
    private String getEditDoneExpirationDate(String doneExpirationDate) {
        
        // ⑨DONE 有効期限(編集)
        String expirationDate = null;
        if (doneExpirationDate != null && doneExpirationDate.equals("        ")) {
            // 「リクエスト.DONE 有効期限」 ≠ null、かつ「リクエスト.DONE 有効期限」 ＝ "△△△△△△△△"の場合：
            
            return "";
        }
        if (doneExpirationDate != null && doneExpirationDate.length() >= 8) {
            // 「リクエスト.DONE 有効期限」 ≠ null、かつ 「リクエスト.DONE 有効期限」の長さ >=8 の場合：
            
            // リクエスト.DONE 有効期限
            try {
                // yy/mm/dd形式に変換する
                expirationDate = DateUtil.format(DateUtil.parse(doneExpirationDate, DateFormatUtil.YYYYMMDD), 
                        DateFormatUtil.SEPARATED_YYMMDD);
            } catch (Exception e) {
                expirationDate = doneExpirationDate;
            }
        } else {
            // 上記以外の場合：
            // "--/--/--"
            expirationDate = "--/--/--";
        }
        
        return String.format("、期間指定(%s)", expirationDate) ;
    }
    
    /**
     * ⑩DONE トリガ値段(編集) を取得
     *
     * @param doneTriggerNedan DONE トリガ値段
     * @return DONE トリガ値段(編集)
     */
    private String getEditDoneTriggerNedan(String doneTriggerNedan) {
        
        String editNedan = null;
        
        // ⑩DONE トリガ値段(編集)
        if (doneTriggerNedan == null || NUMBER_PATTERN.matcher(doneTriggerNedan).matches() == false) {
            // リクエスト.DONE トリガ値段に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
            
            // '-'
            editNedan = "-";
        } else {
            // 上記以外の場合：
            // リクエスト.DONE トリガ値段 +  "円"
            editNedan = doneTriggerNedan + "円";
        }
        
        return editNedan;
    }
    
    /**
     * ⑩DONE トリガ発動ゾーン(編集) を取得
     *
     * @param doneTriggerZone DONE トリガ発動ゾーン
     * @return DONE トリガ発動ゾーン(編集)
     */
    private String getEditDoneTriggerZone(String doneTriggerZone) {
        
        String editZone = "";
        
        // ⑩DONE トリガ発動ゾーン(編集)
        // リクエスト.DONE トリガ発動ゾーンを区分値変換。（区分.直近トリガ発動ゾーン.表示パターン1）
        if (StringUtils.equals(LatestTriggerZone.NONE.getStringValue(), doneTriggerZone)) {
            editZone = "指定なし";
        } else if (StringUtils.equals(LatestTriggerZone.GREATER_EQUAL.getStringValue(), doneTriggerZone)) {
            editZone = "以上";
        } else if (StringUtils.equals(LatestTriggerZone.LESS_EQUAL.getStringValue(), doneTriggerZone)) {
            editZone = "以下";
        }
        
        return editZone;
    }
    
    /**
     * ⑫DONE OCO訂正条件の取得
     *
     * @param doneOcoLimitMarketType DONE OCO指成区分
     * @param doneOcoLimitPrice DONE OCO指値
     * @return DONE OCO訂正条件
     */
    private String getDoneOcoCorrectConditions(String doneOcoLimitMarketType, String doneOcoLimitPrice) {
        
        // ⑫DONE OCO訂正条件
        String ocoCorrectConditions = null;
        
        if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), doneOcoLimitMarketType)) {
            // リクエスト.DONE OCO指成区分が「'N':成行」の場合：
            
            // ⑬DONE OCO指成区分(編集)
            ocoCorrectConditions = this.getEditDoneOcoLimitMarketType(doneOcoLimitMarketType);
        } else {
            // 上記以外の場合：
            if (doneOcoLimitPrice == null || NUMBER_PATTERN.matcher(doneOcoLimitPrice).matches() == false) {
                // リクエスト.DONE OCO指値に数字または'.'(小数点)または','(カンマ)以外の文字が含まれている場合：
                
                // ⑬DONE OCO指成区分(編集) + "/-円"
                ocoCorrectConditions = this.getEditDoneOcoLimitMarketType(doneOcoLimitMarketType) + "/-円";
            } else {
                // 上記以外の場合：
                
                // ⑬DONE OCO指成区分(編集)+ "/" + リクエスト.DONE OCO指値 + "円"
                ocoCorrectConditions = this.getEditDoneOcoLimitMarketType(doneOcoLimitMarketType) + "/"
                        + doneOcoLimitPrice + "円";
            }
        }
        
        return ocoCorrectConditions;
    }
    
    /**
     * ⑥DONE OCO指成区分(編集)の取得
     *
     * @param doneOcoLimitMarketType DONE OCO指成区分
     * @return DONE OCO指成区分(編集)
     */
    private String getEditDoneOcoLimitMarketType(String doneOcoLimitMarketType) {
        
        // ⑬DONE OCO指成区分(編集)
        String editType = null;
        
        // リクエスト.DONE OCO指成区分を区分値変換。（区分.指成区分.表示パターン1）
        if (StringUtils.equals(SashinariKbn.PRICE.getStringValue(), doneOcoLimitMarketType)) {
            editType = "指値";
        } else if (StringUtils.equals(SashinariKbn.YORISASHI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "寄指(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKESASHI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "引指(H)";
        } else if (StringUtils.equals(SashinariKbn.HUNARI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "不成(F)";
        } else if (StringUtils.equals(SashinariKbn.IOC_SASHI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "IOC指(I)";
        } else if (StringUtils.equals(SashinariKbn.NARIYUKI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "成行";
        } else if (StringUtils.equals(SashinariKbn.YORINARI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "寄成(Y)";
        } else if (StringUtils.equals(SashinariKbn.HIKENARI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "引成(H)";
        } else if (StringUtils.equals(SashinariKbn.IOC_NARI.getStringValue(), doneOcoLimitMarketType)) {
            editType = "IOC成(I)";
        }
        
        return editType;
    }
    
    /**
     * 条件文言の生成
     *
     * @param type 生成種別
     * @return 条件文言
     */
    private String createConditionString(ConditionsStringType type, String... param) {
        
        String rtnConditionString = "";
        
        switch (type) {
            case COND_STR_TYPE_01:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_02:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1]);
                break;
            case COND_STR_TYPE_03:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_04:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_05:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_06:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1]);
                break;
            case COND_STR_TYPE_07:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_08:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1]);
                break;
            case COND_STR_TYPE_09:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_10:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_11:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            case COND_STR_TYPE_12:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1]);
                break;
            case COND_STR_TYPE_13:
                rtnConditionString = String.format(type.getFormat(), param[0], param[1], param[2]);
                break;
            default:
                break;
        }
        
        return rtnConditionString;
    }
}
