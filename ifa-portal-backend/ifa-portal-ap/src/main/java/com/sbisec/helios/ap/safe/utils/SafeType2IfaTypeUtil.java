package com.sbisec.helios.ap.safe.utils;

/**
 * Safe API ENUM と IFA VALUE 変換
 * 
 * @author nicksen.li
 * @date 04/22/2025
 */

public class SafeType2IfaTypeUtil {

    /** 決済方法 */
    public enum IfaPaymentMethod {

        /** 現金 */
        CASH("1", "CASH"),
        /** クレジットカード */
        CREDIT_CARD("2", "CREDIT_CARD"),;

        private final String value;
        private final String safeEnum;

        IfaPaymentMethod(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String paymentMethod) {

            for (IfaPaymentMethod ifaEnum : IfaPaymentMethod.values()) {
                if (ifaEnum.getSafeEnum().equals(paymentMethod)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaPaymentMethod ifaEnum : IfaPaymentMethod.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** 預り区分 */
    public enum IfaReserveTradeTypesEnum {

        /** NISA（成長投資枠） */
        NISA_GROWTH("H", "NISA_GROWTH"),

        /** NISA（つみたて投資枠） */
        NISA_RESERVE("I", "NISA_RESERVE"),

        /** 非NISA注文 */
        NORMAL(" ", "NORMAL"),

        /** ジュニアNISA口座 特定/一般 */
        JNISA_NORMAL("5", "JNISA_NORMAL"),;

        private final String value;
        private final String safeEnum;

        IfaReserveTradeTypesEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String reserveTradeTypesEnum) {

            for (IfaReserveTradeTypesEnum ifaSafeEnum : IfaReserveTradeTypesEnum.values()) {
                if (ifaSafeEnum.safeEnum.equals(reserveTradeTypesEnum)) {
                    return ifaSafeEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaReserveTradeTypesEnum ifaSafeEnum : IfaReserveTradeTypesEnum.values()) {
                if (ifaSafeEnum.getValue().equals(ifaString)) {
                    return ifaSafeEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** コース区分 */
    public enum IfaCourseTypeEnum {

        /** 毎日 */
        EVERY_DAY("1", "EVERY_DAY"),

        /** 毎週 */
        EVERY_WEEK("2", "EVERY_WEEK"),

        /** 毎月 */
        EVERY_MONTH("3", "EVERY_MONTH"),

        /** 複数日 */
        MULTIPLE_DAYS("4", "MULTIPLE_DAYS"),

        /** 隔月(5:奇数月,6:偶数月) */
        BIMONTHLY("5", "BIMONTHLY");

        private final String value;
        private final String safeEnum;

        IfaCourseTypeEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String courseTypeEnum) {

            for (IfaCourseTypeEnum ifaEnum : IfaCourseTypeEnum.values()) {
                if (ifaEnum.getSafeEnum().equals(courseTypeEnum)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getIfaValueBySafeEnum(String courseTypeEnum,
                String settingReserveBimonthlyEnum) {

            for (IfaCourseTypeEnum ifaEnum : IfaCourseTypeEnum.values()) {
                if (ifaEnum.getSafeEnum().equals(courseTypeEnum)) {
                    if ("ODD_MONTH".equals(settingReserveBimonthlyEnum)) {
                        return "5";
                    } else if ("EVEN_MONTH".equals(settingReserveBimonthlyEnum)) {
                        return "6";
                    } else {
                        return ifaEnum.getValue();
                    }
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaCourseTypeEnum ifaEnum : IfaCourseTypeEnum.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                } else if ("5".equals(ifaString) || "6".equals(ifaString)) {
                    return IfaCourseTypeEnum.BIMONTHLY.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** 積立隔月設定 */
    public enum IfaSettingReserveBimonthlyEnum {

        /** 奇数月 */
        ODD_MONTH("1", "ODD_MONTH"),

        /** 偶数月 */
        EVEN_MONTH("2", "EVEN_MONTH"),;

        private final String value;
        private final String safeEnum;

        IfaSettingReserveBimonthlyEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String settingReserveBimonthlyEnum) {

            for (IfaSettingReserveBimonthlyEnum ifaEnum : IfaSettingReserveBimonthlyEnum.values()) {
                if (ifaEnum.safeEnum.equals(settingReserveBimonthlyEnum)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaSettingReserveBimonthlyEnum ifaEnum : IfaSettingReserveBimonthlyEnum.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** 積立毎週設定 */
    public enum IfaSettingReserveWeeklyEnum {

        /** 月曜日 */
        MONDAY("1", "MONDAY"),

        /** 火曜日 */
        TUESDAY("2", "TUESDAY"),

        /** 水曜日 */
        WEDNESDAY("3", "WEDNESDAY"),

        /** 木曜日 */
        THURSDAY("4", "THURSDAY"),

        /** 金曜日 */
        FRIDAY("5", "FRIDAY"),;

        private final String value;
        private final String safeEnum;

        IfaSettingReserveWeeklyEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String settingReserveWeeklyEnum) {

            for (IfaSettingReserveWeeklyEnum ifaEnum : IfaSettingReserveWeeklyEnum.values()) {
                if (ifaEnum.getSafeEnum().equals(settingReserveWeeklyEnum)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaSettingReserveWeeklyEnum ifaEnum : IfaSettingReserveWeeklyEnum.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** NISA枠ぎりぎり買付区分 */
    public enum IfaNisaBuyableTypeEnum {

        /** 特定/一般積立設定の場合 */
        UNSUPPORTED("0", "UNSUPPORTED"),

        /** NISA枠での購入はしない */
        UNUSED("1", "UNUSED"),

        /** 一部でもNISA枠で買付する */
        USE("2", "USE"),;

        private final String value;
        private final String safeEnum;

        IfaNisaBuyableTypeEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String nisaBuyableTypeEnum) {

            for (IfaNisaBuyableTypeEnum ifaEnum : IfaNisaBuyableTypeEnum.values()) {
                if (ifaEnum.getSafeEnum().equals(nisaBuyableTypeEnum)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaNisaBuyableTypeEnum ifaEnum : IfaNisaBuyableTypeEnum.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

    /** NISA枠超過時買付区分 */
    public enum IfaNisaExcessBuyableTypeEnum {

        /** 特定/一般積立設定の場合 */
        UNSUPPORTED("0", "UNSUPPORTED"),

        /** 特定/一般口座で買付しない */
        UNUSED("1", "UNUSED"),

        /** 特定/一般口座で買付する */
        USE("2", "USE"),;

        private final String value;
        private final String safeEnum;

        IfaNisaExcessBuyableTypeEnum(final String value, final String safeEnum) {
            this.value = value;
            this.safeEnum = safeEnum;
        }

        public String getValue() {
            return value;
        }

        public String getSafeEnum() {
            return safeEnum;
        }

        public static String getIfaValueBySafeEnum(String nisaExcessBuyableTypeEnum) {

            for (IfaNisaExcessBuyableTypeEnum ifaEnum : IfaNisaExcessBuyableTypeEnum.values()) {
                if (ifaEnum.getSafeEnum().equals(nisaExcessBuyableTypeEnum)) {
                    return ifaEnum.getValue();
                }
            }

            return null;
        }

        public static String getSafeEnumByIfaValue(String ifaString) {

            for (IfaNisaExcessBuyableTypeEnum ifaEnum : IfaNisaExcessBuyableTypeEnum.values()) {
                if (ifaEnum.getValue().equals(ifaString)) {
                    return ifaEnum.getSafeEnum();
                }
            }

            return null;
        }
    }

}
