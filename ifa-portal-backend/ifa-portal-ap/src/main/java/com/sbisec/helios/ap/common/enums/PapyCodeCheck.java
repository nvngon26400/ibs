package com.sbisec.helios.ap.common.enums;

import java.util.ArrayList;
import java.util.List;

//カスタマーのカテゴリー
public interface PapyCodeCheck {

	//エラーのカテゴリ
	enum Error implements PapyCodeCheck{
		IFA_IDENTIFIER_ERROR("PAPY_WARN_086"),
		REQUIRED_ERROR("PAPY_WARN_034"),
		DIGITS_ERROR("PAPY_WARN_060"),
		FORMAT_ERROR("PAPY_WARN_058"),
		NO_REPORT_FILE("PAPY_WARN_088"),
		UNEXPECTED_ERROR("PAPY_ERROR_029");
		
		private final String id;
		private Error(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}

		private static final List<String> byCodeList = new ArrayList<String>();
		static {
			for(Error e : Error.values()) {
				if (byCodeList.contains(e.getId())) {
					throw new IllegalArgumentException("duplicate code: " + e.getId());
				}else {
					byCodeList.add(e.getId());
				}
			}
		}
		
		public static List<String> getCodeList() {
			return byCodeList;
		}
	}
	
	//インフォメーションのカテゴリ
	enum Info implements PapyCodeCheck{
		NO_REPORT_DATA("PAPY_WARN_087")
		;
		private final String id;
		
		private Info(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}

		private static final List<String> byCodeList = new ArrayList<String>();
		static {
			for(Info e : Info.values()) {
				if (byCodeList.contains(e.getId())) {
					throw new IllegalArgumentException("duplicate code: " + e.getId());
				}else {
					byCodeList.add(e.getId());
				}
			}
		}

		public static List<String> getCodeList() {
			return byCodeList;
		}

	}
}
