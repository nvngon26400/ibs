package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT006
 * 
 * @author base 高
 */

@Data
public class InputFct006Dto {

	// 部店コード
	private String butenCode;

	// 口座番号
	private String accountNumber;

	// 国内外国区分
	private String brDomesticFgnInd;

	// 商品区分
	private String brBrandInd;

	// 銘柄コード１
	private String brandCode1;

	// 銘柄コード２
	private String brandCode2;
    
    // 国籍コード
    private String countryCode;

	// 勧誘区分
	private String invitationType;

	// 受注方法
	private String orderMethod;

	// コンプラチェック種類
	private String complaCheckKind;
	// 商品ランク
	private String productRank;

	// コンプラチェック用資金性格
	// private String complaCheckFundCharacter;

	// コンプラランク
	private String tcCompRank;

}
