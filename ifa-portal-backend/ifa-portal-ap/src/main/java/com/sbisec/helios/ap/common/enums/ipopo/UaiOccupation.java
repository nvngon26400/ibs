package com.sbisec.helios.ap.common.enums.ipopo;

public enum UaiOccupation {

	NULL("", "-", ""),
	PRACTITIONERS("A", "開業医", "0"),
	DOCTOR("B", "勤務医", "0"),
	LAWYER_ACCOUNTANT_TAXMAN("C", "弁護士会計士税理士", "0"),
	FREELANCER("D", "自由業", "0"),
	CIVIL_SERVANT_MANAGER("E", "公務員(管理職)", "0"),
	CIVIL_SERVANT_STAFF("F", "公務員(職員)", "0"),
	LISTED_COMPANY_DIRECTOR("G", "会社役員(上場・店頭公開企業)", "0"),
	UNLISTED_ENTERPRISES_MANAGER("H", "会社経営者(未公開企業)", "0"),
	UNLISTED_ENTERPRISES_DIRECTOR("I", "会社役員(未公開企業)", "0"),
	STAFF("J", "会社員", "0"),
	GROUP_UNION_LEGAL_PERSON("K", "団体組合諸法人役員", "0"),
	GROUP_UNION_STAFF("L", "団体組合諸法人職員", "0"),
	TEACHER("M", "教職員", "0"),
	SELF_SHOP_ASSISTANT("N", "商店サービス自営業", "0"),
	SHOP_ASSISTANT("O", "商店サービス従業員", "0"),
	AGRICULTURE_FISHERIES("P", "農林・漁業", "0"),
	UNEMPLOYED("Q", "無職", "0"),
	INDIVIDUAL_UNKNOWN("R", "不明", "0"),
	HOUSEWIFE("S", "無職／主婦", "0"),
	STUDENT("T", "無職／学生", "0"),
	RETIREE("U", "無職／定年退職", "0"),

	JAPAN_BANK("10", "日本銀行", "1"),
	CITY_BANK("11", "都市銀行", "1"),
	FUND_BANK("12", "信託銀行", "1"),
	LOCAL_BANK("13", "地方銀行", "1"),
	MUTUAL_BANK("14", "相互銀行(管理職)", "1"),
	AGRICULTURE_NAKAGANE("15", "農林中金", "1"),
	INDUSTRIAL_COMMERCIAL_NAKAGANE("16", "商工中金", "1"),
	TRUST_BANK("17", "長期信用銀行 ", "1"),
	FOREIGN_FINANCIAL_INSTITUTIONS("18", "外国金融機関 ", "1"),
	LIFE_INSURANCE("20", "生命保険", "1"),
	DAMAGE_INSURANCE("21", "損害保険", "1"),
	FOREIGN_INSURANCE_COMPANY("22", "外国保険会社", "1"),
	PREFECTURAL_SHINREN("30", "県信連", "1"),
	MUTUAL_AID_REN("31", "共済連", "1"),
	AGRICULTURAL_ASSOCIATION("32", "農協", "1"),
	FISHERIES_AGRICULTURE_ETC("33", "漁協・林協・その他", "1"),
	TRUST_VAULT("40", "信用金庫(含全信連)", "1"),
	TRUST_PORTFOLIO("41", "信用組合(含全組連)", "1"),
	LABOR_VAULT("42", "労働金庫(含連合会)", "1"),
	OTHER_FINANCIAL_INSTITUTIONS("43", "その他金融機関", "1"),
	GOVERNMENT_MUTUAL_AID_ASSOCIATION("50", "官公庁共済組合", "1"),
	OTHER_MUTUAL_AID_ASSOCIATION("51", "その他共済組合", "1"),
	LISTED_COMPANY("60", "上場会社", "1"),
	COUNTER_LISTED_COMPANY("61", "店頭上場会社", "1"),
	UNLISTED_COMPANY("62", "非上場会社", "1"),
	FOREIGN_BUSINESS_COMPANY("63", "外国事業会社", "1"),
	SCHOOL_CORPORATION("70", "学校法人", "1"),
	RELIGIOUS_CORPORATION("71", "宗教法人", "1"),
	MEDICAL_CORPORATION("72", "医療法人", "1"),
	HEALTH_INSURANCE_ASSOCIATION("73", "健康保険組合", "1"),
	FOUNDATION("74", "財団法人", "1"),
	OTHER_CORPORATIONS("75", "その他諸法人", "1"),
	FOREIGN_FUND("80", "外国ファンド", "1"),
	FOREIGN_PENSION_FUND_FOUNDATION("81", "外国年金基金財団等", "1"),
	OTHER_FOREIGN_CORPORATION("82", "その他外国法人", "1"),
	GOVERNMENT_AGENCIES("86", "公社公団等政府系機関", "1"),
	LOCAL_PUBLIC_ORGANIZATIONS("87", "地方公共団体", "1"),
	FOREIGN_GOVERNMENT_AGENCIES("88", "外国政府系機関", "1"),
	DOMESTIC_SECURITIES_COMPANY("90", "国内証券会社", "1"),
	FOREIGN_SECURITIES_COMPANY("91", "外国証券会社", "1"),
	SHAREHOLDING_ASSOCIATION("95", "持株会", "1"),
	FUND_TRUST_COMPANY("97", "投信委託会社", "1"),
	CORPORATION_UNKNOWN("98", "不明", "1");

	private final String id;
	private final String label;
	private final String code;

	private UaiOccupation(String id, String label, String code) {
		this.id = id;
		this.label = label;
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getCode() {
		return code;
	}

	public static UaiOccupation valueOfId(String id) {

		if (id == null)
			return NULL;

		UaiOccupation[] enums = values();

		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getId().equals(id))
				return enums[i];
		}

		return NULL;
	}
}