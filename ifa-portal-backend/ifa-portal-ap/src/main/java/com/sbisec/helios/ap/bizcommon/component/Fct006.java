package com.sbisec.helios.ap.bizcommon.component;

import java.util.List;
import java.util.ArrayList;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct006Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql004RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql004ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql006RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql006ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT006 コンプラランクチェック
 * 
 * 
 * @author 高
 */

@Component
public class Fct006 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Fct006.class);

	public static final String CORPORATE_TYPE_0 = "0";
	public static final String CORPORATE_TYPE_1 = "1";
	public static final String CORPORATE_TYPE_2 = "01";
	public static final String CORPORATE_TYPE_3 = "2";
	public static final String CORPORATE_TYPE_5 = "3 ";
	public static final String CORPORATE_TYPE_6 = "6";
	public static final String START_CRITERIA_CONFIR_MSG_ID = "warnings.cmn.complianceRank.fundBrandRank6";
	public static final String ERR_MESSAGE_ID_1 = "E001";
	public static final String ERR_MESSAGE_ID_2 = "E002";
	public static final String ERR_MESSAGE_ID_3 = "E003";
	public static final String ERR_MESSAGE_ID_4 = "E004";
	public static final String ERR_MESSAGE_ID_1_MSG = "指定された口座情報が仲介業者顧客口座属性から取得できない";
	public static final String ERR_MESSAGE_ID_2_MSG = "指定された銘柄の商品ランクを取得できない";
	public static final String ERR_MESSAGE_ID_3_MSG = "コンプラマトリクス設定値を取得できない";
	public static final String ERR_MESSAGE_ID_4_MSG = "コンプラランクチェック結果を取得できない";
	public static final String ERR_MESSAGE_ID_5_MSG = "対象外エラー";
	
	/** 判定結果 2：エラー */
	public static final String ERROR_2 = "2";
	
	/** メッセージID 取引適合性の登録がありません。 */
	public static final String ERRORS_CMN_COMPLIANCERANK_NOENTRY = "errors.cmn.complianceRank.noEntry";
	
	private static final String SPACE1 = String. format ("%1s", "");
	
	/** 国内外国区分 */
	private static final String BR_DOMESTIC_FGN_IND_0 = "0";
	
    private static final String BR_DOMESTIC_FGN_IND_1 = "1";
    
	/** 商品区分 */
	private static final String BR_BRAND_IND_1 = "1 ";
    
    /** '59':コンプラランクチェック用国籍コード */
    private static final String M_CODE_CODETYPE_59 = "59";
	
	/** 国籍コード：マレーシア、シンガポール、タイ */
	List<String> targetCountryCodeList = List.of("MY", "SG", "TH");
	
	@Autowired
	private Fct006Dao dao;
    
    @Autowired
    MCodeService mcodeService;

	/**
	 * FCT006 コンプラランクチェック
	 * 
	 * @param input リクエスト
	 * @return outPutDto
	 */
	public OutputFct006Dto doCheck(InputFct006Dto input) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Fct006.doCheck");
		}
		//① 顧客のコンプラランクと資金性格を取得する。
		OutputFct006Dto outPutDto = new OutputFct006Dto();
		// リクエスト
		Fct006Sql001RequestModel fct006Sql001RequestModel = new Fct006Sql001RequestModel();
		// 部店コードをセット
		fct006Sql001RequestModel.setButenCode(input.getButenCode());
		// 口座番号をセット
		fct006Sql001RequestModel.setAccountNumber(input.getAccountNumber());
		// SQL001を呼ぶ
		List<Fct006Sql001ResponseModel> listFct006Sql001ResponseModel = dao.getComplianceRank(fct006Sql001RequestModel);
		// SQL001取得件数 != 1
		if (listFct006Sql001ResponseModel.size() != 1) {
		    throw new IfaRuntimeException("FCT006 E001: 指定された口座情報が仲介業者顧客口座属性から取得できない");
		}
        // 国籍コード（変換）を取得する。SQL005(MCodeServiceを利用)
        List<MCode> selSql005 = null;
        if (BR_DOMESTIC_FGN_IND_1.equals(input.getBrDomesticFgnInd())) {
            try {
                selSql005 = mcodeService.getMCodeList(M_CODE_CODETYPE_59, input.getCountryCode());
            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
            }
            if (selSql005 == null || selSql005.size() != 1) {
                outPutDto.setJudgementResult(ERROR_2);
                outPutDto.setMessageId(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
                outPutDto.setReturnCode(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
                outPutDto.setErrMessage(IfaCommonUtil.getMessage(ERRORS_CMN_COMPLIANCERANK_NOENTRY));
                return outPutDto;
            }
        }

		// ③ FLARE紐づけ用銘柄コードを取得する。
		List<Fct006Sql006ResponseModel> listFct006Sql006ResponseModel = new ArrayList<Fct006Sql006ResponseModel>();
		
		// リクエスト.国内外国区分が外国 かつ リクエスト.商品区分が株式、かつ、リクエスト.国籍コードがマレーシア、シンガポール、タイのいずれかの場合
        if (
				BR_DOMESTIC_FGN_IND_1.equals(input.getBrDomesticFgnInd())
				&& BR_BRAND_IND_1.equals(input.getBrBrandInd())
				&& targetCountryCodeList.contains(input.getCountryCode())
			) {
				// FLARE紐づけ用銘柄コードを取得する。
				// リクエスト
				Fct006Sql006RequestModel fct006Sql006RequestModel = new Fct006Sql006RequestModel();
				// 国籍コードをセット
				fct006Sql006RequestModel.setCountryCode(input.getCountryCode());
				// 銘柄コード１をセット
				fct006Sql006RequestModel.setBrandCode1(input.getBrandCode1());

				// SQL006を呼ぶ
				listFct006Sql006ResponseModel = dao.getComplianceRank6(fct006Sql006RequestModel);

				// SQL006取得件数 != 1
				// 取得件数1件：次の処理へ。
				if (listFct006Sql006ResponseModel.isEmpty() || listFct006Sql006ResponseModel.size() != 1) {
					// 上記以外：レスポンス項目をセットして処理を終了する。
					outPutDto.setJudgementResult(ERROR_2);
					outPutDto.setMessageId(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
					outPutDto.setReturnCode(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
					outPutDto.setErrMessage(IfaCommonUtil.getMessage(ERRORS_CMN_COMPLIANCERANK_NOENTRY));
					return outPutDto;
				}
		}


		//④ 銘柄の商品ランクを取得する。
		// リクエスト
		Fct006Sql002RequestModel fct006Sql002RequestModel = new Fct006Sql002RequestModel();
		// 国内外国区分をセット
		fct006Sql002RequestModel.setBrDomesticFgnInd(input.getBrDomesticFgnInd());
		// 商品区分をセット
		fct006Sql002RequestModel.setBrBrandInd(input.getBrBrandInd());
		// 銘柄コードをセット
		String brandCode1Input = input.getBrandCode1();
		// リクエスト.国内外国区分が0（国内）かつリクエスト.商品区分が1△（株式）
		if (BR_DOMESTIC_FGN_IND_0.equals(input.getBrDomesticFgnInd()) && BR_BRAND_IND_1.equals(input.getBrBrandInd())) {
		    if(!StringUtil.isNullOrEmpty(brandCode1Input)) {
		        StringBuilder brandCode1 = new StringBuilder(brandCode1Input);
	            if (brandCode1.length() == 4) {
	                // リクエスト.銘柄コード1が4桁しかない場合は、5桁目に'0'を付加して比較
	                brandCode1Input = String.valueOf(brandCode1.append("0"));
	            }else if (SPACE1.equals(String.valueOf(input.getBrandCode1().charAt(4)))) {
	                // リクエスト.銘柄コード1の5桁目が半角スペースの場合は'0'に置き換えて比較
	                brandCode1Input = String.valueOf(brandCode1.replace(4, 5, "0"));
	            }
		    }
		}
        // 外国株、外国投信の場合
        if (BR_DOMESTIC_FGN_IND_1.equals(input.getBrDomesticFgnInd())) {
            fct006Sql002RequestModel.setCountryCode(selSql005.get(0).getCode2());

			// リクエスト.商品区分が1△（株式） && SQL006.銘柄コードがセットされている場合
			if (BR_BRAND_IND_1.equals(input.getBrBrandInd())
				&& !listFct006Sql006ResponseModel.isEmpty() && listFct006Sql006ResponseModel.size() == 1) {
				// SQL006.銘柄コードをセット
				fct006Sql002RequestModel.setProductCode(listFct006Sql006ResponseModel.get(0).getProductCode());
			}
        }
        
		fct006Sql002RequestModel.setBrandCode1(brandCode1Input);
		fct006Sql002RequestModel.setBrandCode2(input.getBrandCode2());
		// SQL002を呼ぶ
		List<Fct006Sql002ResponseModel> listFct006Sql002ResponseModel = new ArrayList<Fct006Sql002ResponseModel>();
		
		// 外国株式 （暫定対応用のSQL002を呼び出す）
		if (BR_DOMESTIC_FGN_IND_1.equals(input.getBrDomesticFgnInd()) && BR_BRAND_IND_1.equals(input.getBrBrandInd())) {
			// リクエスト.国籍コードをセット
			fct006Sql002RequestModel.setRequestCountryCode(input.getCountryCode());
			listFct006Sql002ResponseModel = dao.getComplianceRank2ForeignStock(fct006Sql002RequestModel);
		// 外国株式以外
		} else {
			listFct006Sql002ResponseModel = dao.getComplianceRank2(fct006Sql002RequestModel);
		}

		
		// SQL002取得件数 != 1
		// 取得件数1件：次の処理へ。
		if (listFct006Sql002ResponseModel.size() != 1) {
		    // 上記以外：レスポンス項目をセットして処理を終了する。
		    outPutDto.setJudgementResult(ERROR_2);
		    outPutDto.setMessageId(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
		    outPutDto.setReturnCode(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
			outPutDto.setErrMessage(IfaCommonUtil.getMessage(ERRORS_CMN_COMPLIANCERANK_NOENTRY));
			return outPutDto;
		}

		//⑤ SQL001.A.法人区分とSQL001.B.資金性格区分によって「コンプラチェック用資金性格」を編集し、レスポンスをセットする。
		if (CORPORATE_TYPE_0.equals(listFct006Sql001ResponseModel.get(0).getCorporationKbn())) {
			// 法人区分＝0（個人）
			// SQL001.B.資金性格区分＝01（余裕資金）
			if (CORPORATE_TYPE_2.equals(listFct006Sql001ResponseModel.get(0).getUaiQaFundCharacter())) {
				// 資金性格区分＝01（余裕資金）：1（余裕資金）
				outPutDto.setComplaCheckFundCharacter(CORPORATE_TYPE_1);
			} else {
				// 上記以外：2（その他）
				outPutDto.setComplaCheckFundCharacter(CORPORATE_TYPE_3);
			}
		} else if (CORPORATE_TYPE_1.equals(listFct006Sql001ResponseModel.get(0).getCorporationKbn())) {
			// 法人区分＝1（法人）
			// (SQL001.B.資金性格区分＝1（余裕資金）
			if (listFct006Sql001ResponseModel.get(0).getUaiQaFundCharacter() != null
					&& CORPORATE_TYPE_1.equals(listFct006Sql001ResponseModel.get(0).getUaiQaFundCharacter().trim())) {
				// ・資金性格区分＝1（余裕資金）：1（余裕資金
				outPutDto.setComplaCheckFundCharacter(CORPORATE_TYPE_1);
			} else {
				// 上記以外：2（その他）
				outPutDto.setComplaCheckFundCharacter(CORPORATE_TYPE_3);
			}
		} else {
			throw new IfaRuntimeException(ERR_MESSAGE_ID_5_MSG);
		}
		//⑥ コンプラマトリクス設定値を取得する。
		// リクエスト
		Fct006Sql003RequestModel fct006Sql003RequestModel = new Fct006Sql003RequestModel();
		// SQL001.コンプラランクをセット
		fct006Sql003RequestModel.setTcCompRank(listFct006Sql001ResponseModel.get(0).getTcCompRank());
		// 勧誘区分をセット
		fct006Sql003RequestModel.setInvitationType(input.getInvitationType());
		// 算出値.コンプラチェック用資金性格をセット
		fct006Sql003RequestModel.setComplaCheckFundCharacter(outPutDto.getComplaCheckFundCharacter());
		// 受注方法をセット
		fct006Sql003RequestModel.setOrderMethod(input.getOrderMethod());
		String productRank = "";
		// SQL002取得件数 != 0
		if (listFct006Sql002ResponseModel.size() != 0) {
			// SQL002.商品ランクをセット
			productRank = listFct006Sql002ResponseModel.get(0).getProductRank();
		} else {
			// エラーメッセージ
			outPutDto.setErrMessage(ERR_MESSAGE_ID_2_MSG);
			outPutDto.setReturnCode(ERR_MESSAGE_ID_2);
			return outPutDto;
		}
		// SQL002.商品ランクをセットをSQL3にセット
		fct006Sql003RequestModel.setProductRank(listFct006Sql002ResponseModel.get(0).getProductRank());
		// SQL003を呼ぶ
		List<Fct006Sql003ResponseModel> listFct006Sql003ResponseModel = dao
				.getComplianceRank3(fct006Sql003RequestModel);
		// リクエスト
		Fct006Sql004RequestModel fct006Sql004RequestModel = new Fct006Sql004RequestModel();
		// SQL003取得件数 != 0
		if (listFct006Sql003ResponseModel.size() != 0) {
		    // 取得件数1件：次の処理へ。
			// SQL003.コンプラマトリクスをセット
			fct006Sql004RequestModel.setCompSts(listFct006Sql003ResponseModel.get(0).getCompSts());
		} else {
		    // 上記以外：レスポンス項目をセットして処理を終了する。
			// コンプラマトリクス初期値をセット
		    outPutDto.setJudgementResult(ERROR_2);
            outPutDto.setMessageId(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
            outPutDto.setReturnCode(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
            outPutDto.setErrMessage(IfaCommonUtil.getMessage(ERRORS_CMN_COMPLIANCERANK_NOENTRY));
			return outPutDto;

		}
		//⑦ コンプラチェック判定結果を取得する。
		// コンプラチェック種類
		fct006Sql004RequestModel.setChkType(input.getComplaCheckKind());
		// 勧誘区分をセット
		fct006Sql004RequestModel.setInvitationType(input.getInvitationType());

		// SQL004を呼ぶ
		List<Fct006Sql004ResponseModel> listFct006Sql004ResponseModel = dao
				.getComplianceRank4(fct006Sql004RequestModel);
		// SQL004取得件数 != 0
		if (listFct006Sql004ResponseModel.size() != 0) {
		    // 取得件数1件：次の処理へ。
			// SQL004の結果からレスポンスをセットする。
			// 判定結果にSQL004.チェック判定結果をセットする。
			outPutDto.setJudgementResult(listFct006Sql004ResponseModel.get(0).getChkResult());

			// メッセージIDにSQL004.メッセージIDをセットする。
			outPutDto.setMessageId(listFct006Sql004ResponseModel.get(0).getMessageId());
			// リクエスト.国内外国区分=国内 &&リクエスト.商品区分=投信&&((SQL004.チェック判定結果=ノーマル || アラート) && 商品ランク =
			// 6)
			if (CORPORATE_TYPE_0.equals(input.getBrDomesticFgnInd()) && CORPORATE_TYPE_5.equals(input.getBrBrandInd())
					&& (CORPORATE_TYPE_0.equals(listFct006Sql004ResponseModel.get(0).getChkResult())
							|| CORPORATE_TYPE_1.equals(listFct006Sql004ResponseModel.get(0).getChkResult()))
					&& productRank.equals(CORPORATE_TYPE_6)) {
				outPutDto.setStartCriteriaConfirmMsgId(START_CRITERIA_CONFIR_MSG_ID);
			}

			// チェックボックス文言にSQL004.チェックボックス文言をセットする。
			outPutDto.setChkBoxLabel(listFct006Sql004ResponseModel.get(0).getChkBoxLabel());

			return outPutDto;

		} else {
			// エラーメッセージ
		    outPutDto.setJudgementResult(ERROR_2);
            outPutDto.setMessageId(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
            outPutDto.setReturnCode(ERRORS_CMN_COMPLIANCERANK_NOENTRY);
            outPutDto.setErrMessage(IfaCommonUtil.getMessage(ERRORS_CMN_COMPLIANCERANK_NOENTRY));
			return outPutDto;

		}
	}

}
