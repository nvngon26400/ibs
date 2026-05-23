package com.sbisec.helios.ap.bizcommon.component;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct021Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT021 取引制限チェック
 *
 * @author SCSK
 */

@Component
public class Fct021 {
    
    @Autowired
    private Fct021Dao dao;
    
    // 証券金銭種別（国内株式）
    public static final String DOMESTIC_STOCK = "01";
    
    // 取引種別(1:現物買付)
    public static final String TRADE_CODE_1 = "1";
    
    // 取引種別(2:現物売却)
    public static final String TRADE_CODE_2 = "2";
    
    // 取引種別(3:信用新規買)
    public static final String TRADE_CODE_3 = "3";
    
    // 取引種別(4:信用新規売)
    public static final String TRADE_CODE_4 = "4";
    
    // 取引種別(5:信用返済買)
    public static final String TRADE_CODE_5 = "5";
    
    // 取引種別(6:信用返済売)
    public static final String TRADE_CODE_6 = "6";
    
    // 取引種別(7:現渡)
    public static final String TRADE_CODE_7 = "7";
    
    // 取引種別(8:現引)
    public static final String TRADE_CODE_8 = "8";
    
    // 弁済期限 (A)
    public static final String PAYMENT_LIMIT_A = "A";
    
    // 弁済期限 (D)
    public static final String PAYMENT_LIMIT_D = "D";
    
    //一日信用フラグ(1:一日信用)
    public static final String ONE_DAY_CREDIT_FLAG_1 = "1";
    
    //一日信用フラグ(0:一日信用でない)
    public static final String ONE_DAY_CREDIT_FLAG_0 = "0";
    
    // あり
    public static final String EXIST = "1";
    
    // なし
    public static final String NONE = "0";
    
    // ログ
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct021.class);
    
    /**
    * 利用者顧客参照権限チェック
    *
    * @param input 共通関数の入力
    * @return OutputFct001Dto
    */
    public OutputFct021Dto doCheck(InputFct021Dto input) throws Exception {
        
        //開始ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct021.doCheck");
        }
        
        //返却値の初期化
        OutputFct021Dto outputFct021Dto = new OutputFct021Dto();
        
        //必須項目対する入力値チェック、NULLの場合空DTOを返却
        if (input == null || input.getAccountNumber() == null || input.getButenCode() == null
                || input.getProductCd() == null || input.getTradeCd() == null || input.getCountryCd() == null
                || input.getCurrencyCode() == null) {
            return outputFct021Dto;
        }
        
        //条件付き必須項目対する入力値チェック
        //選択市場
        //（証券金銭種別が01:国内株式、かつ取引種別が1:現物買付、2:現物売却、3:信用新規買、4:信用新規売、5:信用返済買、6:信用返済売） 以外の場合は指定不要
        if (DOMESTIC_STOCK.equals(input.getProductCd())
                && (TRADE_CODE_1.equals(input.getTradeCd()) || TRADE_CODE_2.equals(input.getTradeCd())
                        || TRADE_CODE_3.equals(input.getTradeCd()) || TRADE_CODE_4.equals(input.getTradeCd())
                        || TRADE_CODE_5.equals(input.getTradeCd()) || TRADE_CODE_6.equals(input.getTradeCd()))) {
            if (input.getTradeRestrictChkMarket() == null) {
                return outputFct021Dto;
            }
        } else {
            input.setTradeRestrictChkMarket(null);
        }
        
        //弁済期限
        //（証券金銭種別が01:国内株式、かつ取引種別が3:信用新規買、4:信用新規売、5:信用返済買、6:信用返済売、7:現渡、8:現引） 以外の場合は指定不要
        if (DOMESTIC_STOCK.equals(input.getProductCd())
                && (TRADE_CODE_3.equals(input.getTradeCd()) || TRADE_CODE_4.equals(input.getTradeCd())
                        || TRADE_CODE_5.equals(input.getTradeCd()) || TRADE_CODE_6.equals(input.getTradeCd())
                        || TRADE_CODE_7.equals(input.getTradeCd()) || TRADE_CODE_8.equals(input.getTradeCd()))) {
            if (input.getPaymentLimit() == null) {
                return outputFct021Dto;
            }
        } else {
            input.setPaymentLimit(null);
        }
        
        //SQL001request modelの設定
        Fct021Sql001RequestModel fct021Sql001RequestModel = new Fct021Sql001RequestModel();
        fct021Sql001RequestModel.setProductCd(input.getProductCd());
        fct021Sql001RequestModel.setTradeCd(input.getTradeCd());
        fct021Sql001RequestModel.setCountryCd(input.getCountryCd());
        fct021Sql001RequestModel.setCurrencyCode(input.getCurrencyCode());
        fct021Sql001RequestModel.setTradeRestrictChkMarket(input.getTradeRestrictChkMarket());
        //一日信用フラグ変換処理
        if (DOMESTIC_STOCK.equals(input.getTradeRestrictChkMarket())
                && (TRADE_CODE_3.equals(input.getTradeCd()) || TRADE_CODE_4.equals(input.getTradeCd()))
                && PAYMENT_LIMIT_D.equals(input.getPaymentLimit())) {
            //一日信用
            fct021Sql001RequestModel.setOneDayCreditFlag(ONE_DAY_CREDIT_FLAG_1);
        } else if (DOMESTIC_STOCK.equals(input.getTradeRestrictChkMarket())
                && (TRADE_CODE_5.equals(input.getTradeCd()) || TRADE_CODE_6.equals(input.getTradeCd())
                        || TRADE_CODE_7.equals(input.getTradeCd()) || TRADE_CODE_8.equals(input.getTradeCd()))
                && PAYMENT_LIMIT_A.equals(input.getPaymentLimit())) {
            //一日信用
            fct021Sql001RequestModel.setOneDayCreditFlag(ONE_DAY_CREDIT_FLAG_1);
        } else {
            //一日信用でない
            fct021Sql001RequestModel.setOneDayCreditFlag(ONE_DAY_CREDIT_FLAG_0);
        }
        
        //①指定された証券金銭種別、取引種別、国籍コード、通貨コード、取引制限判別市場、一日信用フラグの取引制限設定値を取得する。
        List<Fct021Sql001ResponseModel> fct021Sql001ResponseModel = dao.getFct021Sql001(fct021Sql001RequestModel);
        //取得件数1件以上：次の処理へ。
        //上記以外：レスポンス項目すべてに0（なし）を設定し、処理を終了して呼出元にレスポンスを返却する。
        if (fct021Sql001ResponseModel.size() < 1) {
            outputFct021Dto.setNoteInfoAlertFlag(NONE);
            outputFct021Dto.setNoteInfoErrFlag(NONE);
            outputFct021Dto.setNoteLimitAlertFlag(NONE);
            outputFct021Dto.setNoteLimitErrFlag(NONE);
            return outputFct021Dto;
        }
        
        //SQL002request modelの設定
        Fct021Sql002RequestModel fct021Sql002RequestModel = new Fct021Sql002RequestModel();
        fct021Sql002RequestModel.setAccountNumber(input.getAccountNumber());
        fct021Sql002RequestModel.setButenCode(input.getButenCode());
        fct021Sql002RequestModel.setFct021Sql001ResponseModel(fct021Sql001ResponseModel);
        //②該当顧客の注意情報・未読の重要なお知らせによる取引制限件数を取得する。
        List<Fct021Sql002ResponseModel> fct021Sql002ResponseModel = dao.getFct021Sql002(fct021Sql002RequestModel);
        //取得件数1件以上：次の処理へ。
        //上記以外：顧客情報取得失敗エラーを返す。
        if (fct021Sql002ResponseModel.size() < 1) {
            outputFct021Dto.setReturnCode(BaseOutputDto.RETURN_CODE_E001);
            return outputFct021Dto;
        }
        //③SQL002で取得した件数よりレスポンス項目.注意情報エラー有無を編集する。
        if (fct021Sql002ResponseModel.get(0).getNoteInfoErrFlag() > 0) {
            //■B.注意情報エラー件数＞0
            // 1（あり）
            outputFct021Dto.setNoteInfoErrFlag(EXIST);
        } else {
            //■上記以外
            // 0（なし）
            outputFct021Dto.setNoteInfoErrFlag(NONE);
        }
        
        //SQL002で取得した件数よりレスポンス項目.注意情報アラート有無を編集する。
        if (fct021Sql002ResponseModel.get(0).getNoteInfoAlertFlag() > 0) {
            //■C.注意情報アラート件数＞0
            // 1（あり）
            outputFct021Dto.setNoteInfoAlertFlag(EXIST);
        } else {
            //■上記以外
            // 0（なし）
            outputFct021Dto.setNoteInfoAlertFlag(NONE);
        }
        
        //SQL002で取得した件数よりレスポンス項目.お知らせエラー有無を編集する。
        if (fct021Sql002ResponseModel.get(0).getNoteLimitErrFlag() > 0) {
            //■D.未読の重要なお知らせによる取引制限エラー件数＞0
            // 1（あり）
            outputFct021Dto.setNoteLimitErrFlag(EXIST);
        } else {
            //■上記以外
            // 0（なし）
            outputFct021Dto.setNoteLimitErrFlag(NONE);
        }
        
        //SQL002で取得した件数よりレスポンス項目.お知らせアラート有無を編集する。
        if (fct021Sql002ResponseModel.get(0).getNoteLimitAlertFlag() > 0) {
            //■E.未読の重要なお知らせによる取引制限アラート件数＞0
            // 1（あり）
            outputFct021Dto.setNoteLimitAlertFlag(EXIST);
        } else {
            //■上記以外
            // 0（なし）
            outputFct021Dto.setNoteLimitAlertFlag(NONE);
        }
        
        //⑤処理を終了して呼出元にレスポンスを返却する。
        return outputFct021Dto;
    }
}
