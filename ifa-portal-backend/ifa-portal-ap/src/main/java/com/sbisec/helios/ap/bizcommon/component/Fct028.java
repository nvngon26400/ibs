package com.sbisec.helios.ap.bizcommon.component;

import java.math.BigDecimal;
import java.util.List;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.dao.Fct028Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT028 IFAP米株 余力チェック（数量）
 *
 * @author 鄒
 *
 */
@Component
public class Fct028 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct028.class);
    
    //売買区分(売却)
    private static final String TRADE_TYPE_SALE = "1";
    
    @Autowired
    private Fct028Dao fct028Dao;
    
    @Autowired
    private ForeignAccountService foreignAccountService;

    @Autowired
    private CometCommonService cometCommonService;
    
    private static final String SEC_TYPE_CODE_0 = "0";
    
    private static final String US = "US";
    
    private static final String USD = "USD";
    
    /**
     * IFAP米株 余力チェック（数量）
     *
     * @param input リクエスト
     * @return outputFct028Dto レポート
     */
    public OutputFct028Dto doCheck(InputFct028Dto input) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct028.doCheck");
        }
        
        OutputFct028Dto outputFct028Dto = new OutputFct028Dto();
        
        // ①リアルタイム余力の保有残高を算出する。
        // 外貨建商品保有証券情報初期化
        GetSecuritiesBalanceResp api001ResponseDto = null;
        try {
            // 外貨建商品保有証券取得API
            api001ResponseDto = foreignAccountService.getSecuritiesBalance(input.getButenCode(),
                    input.getAccountNumber(), "", SEC_TYPE_CODE_0, US, USD, input.getDepositType(), input.getBrandCode());
        } catch (Exception e) {
            LOGGER.error("Fct028.doCheck foreignAccountService.GetSecuritiesBalance Exception[{}]", e.getMessage());
            if (e instanceof AthenaBusinessException) {
                String errorCode = ((AthenaBusinessException) e).getErrorCode();
                // String message = ((AthenaBusinessException) e).getMessage();
                Integer statusCode = ((AthenaBusinessException) e).getStatusCode();

                // 404エラーの場合は、各項目に0を設定して正常終了
                if (statusCode == 404) {
                    outputFct028Dto.setAcPositionStockNumber(BigDecimal.ZERO);
                    outputFct028Dto.setRealtimeBalanceStockNumberSell(BigDecimal.ZERO);
                    outputFct028Dto.setCurrentDayOtcSellStockNumber(BigDecimal.ZERO);

                    return outputFct028Dto;
                } else {
                    AthenaErrorMessageModel apiStatusModel = cometCommonService.getAthenaErrorCodeAndMessage(errorCode);
                    ((AthenaBusinessException) e).setMessage(apiStatusModel.getErrorMessage());
                }
                // outputFct028Dto.setReturnCode(errorCode);
                // outputFct028Dto.setErrMessage(message);

                // AthenaErrorMessageModel apiStatusModel = cometCommonService.getAthenaErrorCodeAndMessage(errorCode);
                // outputFct028Dto.setApiStatusModel(apiStatusModel);

            }
            throw e;
        }
        
        // 保有株数、売却注文中を取得し、Comet売却可能数量を算出する（保有株数－売却注文中）
        BigDecimal acPositionStockNumber = new BigDecimal("0");
        if (null != api001ResponseDto && null != api001ResponseDto.getSecuritiesQuantity()
                && null != api001ResponseDto.getSellFixedOrderQuantity()) {
            acPositionStockNumber = new BigDecimal(api001ResponseDto.getSecuritiesQuantity())
                    .subtract(new BigDecimal(api001ResponseDto.getSellFixedOrderQuantity()));
        }
        // ②当日店頭注文（売却）情報を取得する。
        Fct028Sql001RequestModel fct028Sql001RequestModel = new Fct028Sql001RequestModel();
        fct028Sql001RequestModel.setButenCode(input.getButenCode());
        fct028Sql001RequestModel.setAccountNumber(input.getAccountNumber());
        fct028Sql001RequestModel.setBrandCode(input.getBrandCode());
        fct028Sql001RequestModel.setDepositType(input.getDepositType());
        // 店頭取引注文情報を取得する
        List<Fct028Sql001ResponseModel> fct028Sql001ResponseModelList = fct028Dao
                .getFct028Sql001(fct028Sql001RequestModel);
        
        if (null != fct028Sql001ResponseModelList && fct028Sql001ResponseModelList.size() != 0) {
            // 店頭注文情報が存在する場合
            if (!StringUtil.isNullOrEmpty(input.getOtcManagementNo())) {
                // 引数．店頭管理番号が存在する場合(店頭ワーニング受付、店頭ステータス訂正)
                // 当日店頭売却株数 = SQL001の戻り値．管理番号<>引数．店頭管理番号 のレコード のうち売買区分が売却のレコードの注文数量集計
                BigDecimal currentDayOtcSellStockNumber = new BigDecimal("0");
                for (Fct028Sql001ResponseModel fct028Sql001ResponseModel : fct028Sql001ResponseModelList) {
                    if ((!fct028Sql001ResponseModel.getOrderNo().equals(input.getOtcManagementNo()))
                            && fct028Sql001ResponseModel.getTradeType().equals(TRADE_TYPE_SALE)) {
                        currentDayOtcSellStockNumber = currentDayOtcSellStockNumber
                                .add(new BigDecimal(fct028Sql001ResponseModel.getOrderCount()));
                    }
                }
                outputFct028Dto.setCurrentDayOtcSellStockNumber(currentDayOtcSellStockNumber);
            } else {
                // 当日店頭売却株数 = SQL001の戻り値の全レコード のうち売買区分が売却のレコードの注文数量集計
                BigDecimal currentDayOtcSellStockNumber = new BigDecimal("0");
                for (Fct028Sql001ResponseModel fct028Sql001ResponseModel : fct028Sql001ResponseModelList) {
                    if (fct028Sql001ResponseModel.getTradeType().equals(TRADE_TYPE_SALE)) {
                        currentDayOtcSellStockNumber = currentDayOtcSellStockNumber
                                .add(new BigDecimal(fct028Sql001ResponseModel.getOrderCount()));
                    }
                }
                outputFct028Dto.setCurrentDayOtcSellStockNumber(currentDayOtcSellStockNumber);
            }
        } else {
            // 店頭注文情報が存在しない場合
            // 売却可能数量(株数) ＝ ①Comet売却可能数量
            outputFct028Dto.setAcPositionStockNumber(acPositionStockNumber);
            // リアルタイム残株数(売却) ＝ ①Comet売却可能数量
            outputFct028Dto.setRealtimeBalanceStockNumberSell(acPositionStockNumber);
            // 当日店頭売却株数 ＝ "0"
            outputFct028Dto.setCurrentDayOtcSellStockNumber(new BigDecimal("0"));
        }
        
        // ③米株余力（株数）を計算する。
        // 米株余力（株数）＝ ①Comet売却可能数量 － ②当日店頭売却株数
        BigDecimal stockNumber = acPositionStockNumber.subtract(outputFct028Dto.getCurrentDayOtcSellStockNumber());
        
        // ④下記の戻り値を設定して返す。
        // ・売却可能数量(株数) ＝ ③米株余力（株数）
        // ・リアルタイム残株数(売却) ＝ ①Comet売却可能数量
        // ・当日店頭売却株数 ＝ ②当日店頭売却株数
        outputFct028Dto.setAcPositionStockNumber(stockNumber);
        outputFct028Dto.setRealtimeBalanceStockNumberSell(acPositionStockNumber);
        outputFct028Dto.setCurrentDayOtcSellStockNumber(outputFct028Dto.getCurrentDayOtcSellStockNumber());
        
        return outputFct028Dto;
    }
}
