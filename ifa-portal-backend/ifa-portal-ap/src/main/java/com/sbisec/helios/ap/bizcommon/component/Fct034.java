package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleCollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.model.InputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct034Dto;
import com.sbisec.helios.ap.common.service.CometCommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT034 代用預りチェック
 *
 * @author SCSK
 */

@SuppressWarnings("unused")
@Component
public class Fct034 {
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Fct034.class);
    
    /** ハイフン"-" */
    private static final String HYPHEN = "-";
    
    /** 商品コード */
    private static final String PRODUCTCODE = "FOREIGN_STOCK";
    
    /** 国コード */
    private static final String COUNTRYCODE = "US";
    
    /** 通貨コード */
    private static final String CURRENCYCODE = "USD";
    
    /**
     * 代用預りチェック
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception 代用預りチェック時に例外が発生した場合
     */
    public OutputFct034Dto doCheck(InputFct034Dto input) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            
            LOGGER.debug("Fct034.doCheck");
            
        }
        
        //　リクエストの必須パラメタチェック
        if (input.getButenCode() == null || input.getAccountNumber() == null || input.getBrandCode() == null
                || input.getDepositType() == null) {
            return new OutputFct034Dto();
        }
        
        OutputFct034Dto resDto = new OutputFct034Dto();
        
        // api001Inparameter初期化
        ListPossibleCollateralSecuritiesTransfersReq api001Input = new ListPossibleCollateralSecuritiesTransfersReq();
        
        // Api001返却想定データ初期化
        ListPossibleCollateralSecuritiesTransfersResp api001Output = new ListPossibleCollateralSecuritiesTransfersResp();
        
        // Header.tokenとしてリクエスト.部店コード + "-" + リクエスト.口座番号をセット
        api001Input.getHeader().setToken(RequestUtil.getToken(input.getButenCode(), input.getAccountNumber()));
        
        // 商品コードをセット
        api001Input.getParameter().setProductCode(PRODUCTCODE);
        
        // 国コードをセット
        api001Input.getParameter().setCountryCode(COUNTRYCODE);
        
        // 通貨コードをセット
        api001Input.getParameter().setCurrencyCode(CURRENCYCODE);
        
        try {
            
            // API001(代用有価証券振替可能一覧取得API)を呼び出し、振替指示入力（代用→保護）リストを取得する。
            api001Output = foreignAccountService.listPossibleCollateralSecuritiesTransfers(api001Input);
            
        } catch (Exception e) {
            
            // API001が正常終了しなかった場合
            // APIエラーの場合、下記を設定し、③の処理へ。
            LOGGER.error("Fct034.doCheck apiWrapper.ListPossibleCollateralSecuritiesTransfers Exception[{}]",
                    e.getMessage());

            if (e instanceof AthenaBusinessException) {
                String errorCode = ((AthenaBusinessException) e).getErrorCode();
                AthenaErrorMessageModel apiStatusModel = cometCommonService.getAthenaErrorCodeAndMessage(errorCode);
                ((AthenaBusinessException) e).setMessage(apiStatusModel.getErrorMessage());
            }
            
            throw e;
            // resDto.setReturnCode(((AthenaBusinessException) e).getErrorCode());
            
            // return resDto;
        }
        
        // ②対象の銘柄が代用預りであるか判定する。
        // ①で取得した振替指示入力（代用→保護）リストが空の場合
        if (api001Output.getCollateralTransfers().size() == 0) {
            
            resDto.setAlternativeDepositFlag("FALSE");
            
            // レスポンスを返却する
            return resDto;
            
        }
        
        // 代用預りフラグ ← "FALSE"
        resDto.setAlternativeDepositFlag("FALSE");
        
        // ⅰ.①で取得した振替指示入力（代用→保護）リストについて、
        // 下記の判定条件に適合するデータの有無を繰り返しチェックする。
        // ＜判定条件＞
        // リクエスト.銘柄コード ＝ 振替指示入力（代用→保護）リスト.銘柄コード
        // かつ
        // リクエスト.預り区分 ＝ 振替指示入力（代用→保護）リスト.預り区分
        for (PossibleCollateralSecuritiesTransfer str : api001Output.getCollateralTransfers()) {
            
            if (input.getBrandCode().equals(str.getSecurities().getSecuritiesCode())
                    && input.getDepositType().equals(str.getSpecificAccountCode())) {
                
                // ⅰの判定条件に適合するデータが存在する場合、下記を設定
                // 代用預りフラグ ← "TRUE"
                resDto.setAlternativeDepositFlag("TRUE");
                
                break;
                
            }
        }
        
        // ③処理を終了して呼出元にレスポンスを返却する。
        return resDto;
        
    }
}
