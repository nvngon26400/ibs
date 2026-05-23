package com.sbisec.helios.ap.bizcommon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 共通関数DTO：FCT031
 * @author base 熊
 */

@Data
public class OutputFct031Dto extends BaseOutputDto{

	//顧客コード
	private Integer customerCode;
	
	//部店コード
	private String butenCode;
	
	//口座番号
	private String accountNumber;
	
	//顧客名（漢字）
	private String customerNameKanji;
	
	//顧客名（カナ）
	private String customerNameKana;
	
	//コンプラランク
	private String compRank;
	
	//契約締結前交付書面コード
	private String customerAttribute;
	
	//契約締結前交付書面コード名
	private String customerAttributeName;
	
	//扱者コード
	private String dealerNumber;
	
	//生年月日
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+9")
	private Date corBirthFlg;
	
	//年齢
	private Integer age;
	
	//仲介業者コード
	private String brokerCode;
	
	//仲介業者営業員コード
	private String brokerChargeCode;
	
	//法人区分
	private String corporationType;
	
	//自宅電話番号(市外局番) 
	private String homePhoneNumberAreaCode;
	
	//自宅電話番号(市内局番)
	private String homePhoneNumberLocalAreaCode;
	
	//自宅電話番号(番号)
	private String homePhoneNumber;
	
	//携帯電話番号(上5桁) 
	private String phoneNumber1;
	
	//携帯電話番号(中5桁) 
	private String phoneNumber2;
	
	//携帯電話番号(下5桁)
	private String phoneNumber3;
	
	//代表者名(漢字)
	private String representativeNameKanji;
	
	//代表者名(カナ)
	private String representativeNameKana;
	
	//代理人名(漢字)
	private String agentNameKanji;
	
	//代理人名(カナ)
	private String agentNameKana;
	
	//代理人肩書区分
	private String titleOfAgeNtType;
	
	//特定口座区分
	private String specificAccount;
	
	//特定口座配当受入開始日
	private String specificAccountDividendStartDate;
	
	//特定口座配当受入終了日
	private String specificAccountDividendEndDate;
	
	//マル優適格者区分
	private String taxExemptQualifiedPersonType;
	
	//ISA契約区分
	private String isaContractType;
	
	//ジュニアISA契約区分
	private String jrIsaContractType;
	
	//ジュニアNISA口座番号
	private String jrNisaAccountNumber;
	
	//ジュニア特定口座区分
	private String jrSpecificAccount;
	
	//仲介業者名
	private String brokerName;
	
	//仲介業者支店名
	private String branchName;
	
	//仲介業者営業員名
	private String brokerChargeName;
	
	//注意情報エラー件数
	private Integer noteInfoErrorCount;
	
	//注意情報件数
	private Integer noteInfoCount;
	
	//未読の重要なお知らせによる取引制限エラー件数
	private Integer unreadImportantNoticeTransactionLimitErrorNumber;
	
	//未読の重要なお知らせによる取引制限件数
	private Integer unreadImportantNoticeTransactionLimitNumber;
	

}
