package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:市場コード
 * @author shuchen.xin
 * @date: 06/04/2021
 */
public enum MarketCode {
    US_NYSE("US_NYSE", "NYSE", "NYSE"), US_ARCA("US_ARCA", "NYSEArca", "NYSEArca"), US_AMEX("US_AMEX", "NYSE American", "NYSE American"),
    US_NASDAQ("US_NASDAQ", "ナスダック", "NASDAQ"), HK_MAIN("HK_MAIN", "メインボード", "MAIN"), HK_GEM("HK_GEM", "GEM",
            "GEM"), KR_KSE("KR_KSE", "韓国証券取引所", "KOSPI"), KR_KOSDAQ("KR_KOSDAQ", "韓国KOSDAQ", "KOSDAQ"), RU_MICEX(
                    "RU_MICEX", "ロシア MICEX", "MICEX"), VN_HOSE("VN_HOSE", "ホーチミン", "HOSE"), VN_HNX("VN_HNX", "ハノイ",
                            "HNX"), ID_IDX("ID_IDX", "インドネシア証券取引所", "IDX"), SG_MAIN("SG_MAIN", "シンガポール証券取引所",
                                    "SGX"), TH_SET("TH_SET", "タイ証券取引所", "SET"), MY_MAIN("MY_MAIN", "マレーシア証券取引所", "MYX")
                                                 , US_CBOE("US_CBOE", "Cboe BZX", "CBOE") , US_OTC("US_OTC", "OTC Market", "OTC Market");

		MarketCode(String id, String name, String ifaName) {
				this.id = id;
				this.name = name;
				this.ifaName = ifaName;
			}

		private String id;
		private String name;
		private String ifaName;

		public String getId() {
				return id;
		}

		public String getName() {
				return name;
		}

		public String getIfaName() {
				return ifaName;
		}

		public static MarketCode getById(String id) {
				if (ObjectUtils.isEmpty(id)) {
				return null;
		}

				MarketCode[] enums = values();

				for (int i = 0; i < enums.length; i++) {
						if (enums[i].getId().equals(id)) {
						return enums[i];
						}
					}

				return null;
		}

}