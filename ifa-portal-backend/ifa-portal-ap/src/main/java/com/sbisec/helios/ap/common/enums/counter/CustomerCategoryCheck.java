package com.sbisec.helios.ap.common.enums.counter;

import java.util.ArrayList;
import java.util.List;

//カスタマーのカテゴリー
public interface CustomerCategoryCheck {

	//エラーのカテゴリ
	enum Error implements CustomerCategoryCheck{
		BANKRUPTCY("19", "破産／差押口座"),
		FATCACONFIRMING("29", "ＦＡＴＣＡ米国示唆情報確認中"),
		FATCA("30", "ＦＡＴＣＡ米国人口座"),
		TRADESTOPING("40", "取引停止中"),
		ALLTRADESTOPING("45", "全取引停止中"),
		CLOSING("68", "閉鎖手続中"),
		SELF_CANOTCONFIRM("69", "法人本人確認不可"),
		UNDERAGESELF_CANOTCONFIRM("73", "未成年本人確認不可"),
		NOOPENF_ORACCOUNT("74", "外国証券口座未開設"),
		NOCUSTMERCARD("76", "顧客カード未入"),
		UNDERAGE_NOLOGIN("77", "未成年口座親権者未登録"),
		NOACCOUNTLOGIN("81", "口座属性未登録"),
		NOFIRSTLOGIN("82", "初回ログイン未"),
		FINANCEPERSON("85", "金融商品仲介業務従事"),
		NONRESIDENTS("97", "非居住者口座"),
		OPENINVESTIGATION("A8", "口座開設審査中"),
		NOHITOTUCONFIRM("B7", "本人確認書類未受入");
		
		private final String id;
		private final String label;
		private Error(String id, String label) {
			this.id = id;
			this.label = label;
		}
		
		public String getId() {
			return id;
		}

		public String getLabel() {
			return label;
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
	
	//ワーニングのカテゴリ
	enum Warning implements CustomerCategoryCheck{
		UNDERAGECHARGING("66", "未成年口座属性変更中"),
		COUNTRYLIMIT("70", "国籍取引制限あり"),
		NOWELCOMELETTER("95", "ウェルカムレター未着"),
		DUEDATESTOP("98", "期限付新規売買停止"),
		CANNOTCANCAT("B1", "連絡取れないため取引制限"),
		NOLOGINCONTENT("B2", "口座登録内容確認書未提出"),
		NOWL("C3", "WL受取データ未受入"),
		NOMYNUMBER("C5", "マイナンバー未受入（取引制限中）"),
		NOACTUALCONTROLLER("D5", "実質支配者申告書未受入"),
		USPRODUCTSTOP("D6", "米国株式売却停止");
		
		private final String id;
		private final String label;
		
		private Warning(String id, String label) {
			this.id = id;
			this.label = label;
		}
		
		public String getId() {
			return id;
		}

		public String getLabel() {
			return label;
		}
		
		private static final List<String> byCodeList = new ArrayList<String>();
		static {
			for(Warning e : Warning.values()) {
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
	
	//エラーのカテゴリ
	enum ErrorIpopo implements CustomerCategoryCheck{
		DEAD_CUSTOMER("20", "顧客死亡"),
		UNKNOWN_PLACE("21", "転居先不明"),
		BUY_IN_UNABLE("23", "売買制限中"),
		TRADESTOPING("40", "取引停止中"),
		BEST_EXECUTION_UNDELIVERED("43", "最良執行方針未交付"),
		ALLTRADESTOPING("45", "全取引停止中"),
		NOT_DELIVERED_BEFORE_CONCLUSION("62", "契約締結前交付書面集未交付"),
		IMPORTANT_BOOK_UNDELIVERED("65", "重要書類未交付"),
		LEGAL_NOT_CONFIRM("69", "法人本人確認不可"),
		LOG_IN_FINANCE_SERVICES("84", "登録金融機関勤務"),
		BLOCK_BROKER_MAJOR("85", "金融商品仲介業務従事"),
		SELF_NOT_CONFIREM("93", "本人確認未"),
		BIND_WEB("95", "ウェルカムレター未着"),
		TRADE_RESTRICTIONS("B1", "連絡取れないため取引制限"),
		IPOPO_STOPED("E3", "IPO・PO申込停止中"),
		PTS_SOR_STOPED("E5", "【現物・信用】PTS・SOR取引停止中"),
		PTS_SOR_STOPED_CREDIT("E6", "【信用】PTS・SOR取引停止中"),
		BANKRUPTCY_CHARGE("19", "破産／差押口座"),
		BAD_MONEY_NOT_IN("25", "差金未入金"),
		EXISTED_SEAT("28", "後見人届出口座"),
		SECURITY_CLUB_SERVICES("42", "証券会社勤務"),
		STOCK_OPTION("46", "ストックオプション専用口座"),
		CHANGEING_KOZA_UNDER_AGE("66", "未成年口座属性変更中"),
		LOCK_IN_HANDED("68", "閉鎖手続中"),
		UNABLE_CONFIRM_UNDER_AGE("73", "未成年本人確認不可"),
		CUSTOMER_NOT_IN("76", "顧客カード未入"),
		PARENT_NOT_LOGIN_UNDER_AGE("77", "未成年口座親権者未登録"),
		UNLOGIN_KOZA("81", "口座属性未登録"),
		NOT_FIRST_LOGIN("82", "初回ログイン未"),
		NOT_LOCALE_KOZA("97", "非居住者口座"),
		KOZA_NAIYOU_CONFIRM_UNSET("B2", "口座登録内容確認書未提出"),
		AMOUNT_OUTSTANDING("B6", "差金防止額未入金"),
		ORDER_BOOK_UNSET("C2", "注文発注補助者届出書未提出");
		
		private final String id;
		private final String label;
		private ErrorIpopo(String id, String label) {
			this.id = id;
			this.label = label;
		}
		
		public String getId() {
			return id;
		}

		public String getLabel() {
			return label;
		}

		private static final List<String> byCodeList = new ArrayList<String>();
		static {
			for(ErrorIpopo e : ErrorIpopo.values()) {
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
