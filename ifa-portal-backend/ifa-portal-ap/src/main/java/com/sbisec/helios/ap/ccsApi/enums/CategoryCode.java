package com.sbisec.helios.ap.ccsApi.enums;

/**
 * カテゴリコード(大分類)の列挙型
 */
public enum CategoryCode {

    /** 00：ALL */
    CC_00("00"),
    /** 01：信用・株式注文履歴 */
    CC_01("01"),
    /** 02：投信注文履歴 */
    CC_02("02"),
    /** 03：CW注文履歴 */
    CC_03("03"),
    /** 04：その他注文履歴 */
    CC_04("04"),
    /** 05：積立買付履歴 */
    CC_05("05"),
    /** 06：問合せ */
    CC_06("06"),
    /** 07：PWリセット履歴 */
    CC_07("07"),
    /** 08：書類請求情報 */
    CC_08("08"),
    /** 09：入出金履歴 */
    CC_09("09"),
    /** 10：貸株履歴 */
    CC_10("10"),
    /** 11：定期売却履歴 */
    CC_11("11"),
    /** 12：ユーザーネーム変更履歴 */
    CC_12("12"),
    /** 13：貸株振替履歴 */
    CC_13("13");

    CategoryCode(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static CategoryCode getById(String id) {
        for (CategoryCode type : CategoryCode.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}