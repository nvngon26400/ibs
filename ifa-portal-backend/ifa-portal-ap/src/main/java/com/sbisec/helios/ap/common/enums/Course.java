package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Course {

    ALL('0', "全て"),
    INTERNET('A', "インターネットコース"),
    CALLCENTER('B', "コールセンターコース"),
    DIRECT('C', "ダイレクトコース"),
    IFA('D', "IFAコース"),
    IFA_PLAN_A('E', "IFAコース（プランA）"),
    PLAN_A_COURSE('F', "プランAコース"),
    PLAN_B_COURSE('G', "プランBコース"),
    MEETING_TRANSACTION('H', "対面取引コース"),
	IFA_PLAN_A2('I', "IFAコース（プランΑｰ2）"),
	INTERNET_PLAN_C('J', "インターネットコース（プランC）");

    private final int id;
    private final String label;

    private Course(int id, String label) {

        this.id = id;
        this.label = label;
    }

    /**
     * Course selection entries in the CUSTOMER_ATTRIBUTE_MASTER are:
     * 0 NOT IN DB, will denote ALL (全て) 
     * A インターネットコース
	 * B コールセンターコース
	 * C ダイレクトコース
	 * D IFAコース
	 * E IFAコース（プランA）
	 * F プランAコース
	 * G プランBコース
	 * H 対面取引コース
	 * I IFAコース（プランA-2）
	 * J インターネットコース(プランC)
     * @return
     */
    public String getId() {
    	return Character.toString((char) id);
    }

    public String getLabel() {
        return label;
    }
}