package com.sbisec.helios.ap.ccsApi.enums;

/**
 * 詳細区分の列挙型
 */
public enum BigClass {

    /** 注文 */
    BC_00("注文"),
    /** 受付のみ */
    BC_01("受付のみ"),
    /** 一部受付 */
    BC_02("一部受付"),
    /** 注文受付 */
    BC_03("注文受付"),
    /** 出金 */
    BC_04("出金"),
    /** 出金取消 */
    BC_05("出金取消"),
    /** 問合せ */
    BC_06("問合せ");

    BigClass(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static BigClass getById(String id) {
        for (BigClass type : BigClass.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}