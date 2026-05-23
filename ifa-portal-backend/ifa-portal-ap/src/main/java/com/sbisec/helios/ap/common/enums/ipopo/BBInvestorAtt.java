package com.sbisec.helios.ap.common.enums.ipopo;

public enum BBInvestorAtt {

    INDIVIDUAL("1", "個人"),
    CORPORATION("2", "事業法人"),
    REGIONAL_FINANCIAL_INSTITUTION("3", "地域金融機関"),
    OTHER_CORPORATION("4", "その他法人（学校法人等）");

    private final String id;
    private final String label;

    private BBInvestorAtt(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static BBInvestorAtt valueOfId(String id) {

        if (id == null)
            return null;

        BBInvestorAtt[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
