package com.sbisec.helios.ap.bizcommon.component;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.dao.Fct018Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.common.enums.ForeignSurviceTimeCheck;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.util.DateUtil;

/**
 * 共通関数：FCT018
 * サービス時間チェック（外国）
 * @author　SCSK
 */

@Component
public class Fct018 {
    
    @Autowired
    private Fct018Dao dao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct018.class);
    
    /** エラーログ: 想定外エラー*/
    private static final String ERRORS_CMN_UNEXPECTED = "errors.unexpected";
    
    /**M_CODE種別：外国株式委託注文国別受付時間*/
    private static final String MCODE_FOREIGNSTOCKORDER = "53";
    
    /**M_CODE種別：外国株式振替時間*/
    private static final String MCODE_FOREIGNSTOCKTRANSFER = "62";
    
    /**M_CODEコード２：開始時間*/
    private static final String START_TIME = "START_TIME";
    
    /**M_CODEコード２：終了時間*/
    private static final String END_TIME = "END_TIME";
    
    /**判定結果：OK*/
    private static final String RESULT_OK = "OK";
    
    /**判定結果：NG*/
    private static final String RESULT_NG = "NG";
    
    /**LocalDate型変換フォーマット:時分*/
    private static final String HOUR_MINUTE = "H:mm";
    
    /**
     * FCT018　サービス時間チェック（外国）
     * @param input リクエスト
     * @return outputFct018Dto
     */
    public OutputFct018Dto doCheck(InputFct018Dto input) {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Fct018.doCheck");
        OutputFct018Dto output = new OutputFct018Dto();
        DataList<Fct018Sql001ResponseModel> selSql001Res = new DataList<Fct018Sql001ResponseModel>();
        
        //サービス時間チェック対象(外国)
        String foreignServiceHoursCheckTarget = input.getForeignServiceHoursCheckTarget();
        //注文種別
        String syubetu = null;
        
        //注文種別取得
        if (StringUtils.equals(foreignServiceHoursCheckTarget, ForeignSurviceTimeCheck.TRANSFER.getId())) {
            syubetu = MCODE_FOREIGNSTOCKTRANSFER;
        } else {
            syubetu = MCODE_FOREIGNSTOCKORDER;
        }
        
        //SQL001呼び出し
        String countryCode = input.getCountryCode();
        try {
            selSql001Res = getCode(syubetu, countryCode);
        } catch (Exception e) {
            LOGGER.error("Fct018.doCheck Exception[{}]", e.getMessage());
            e.printStackTrace();
        }
        
        //SQL001から取得するサービス開始の時間
        String startTime = null;
        //SQL001から取得するサービス終了の時間
        String endTime = null;
        
        for (Fct018Sql001ResponseModel time : selSql001Res.getDataList()) {
            if (StringUtils.equals(time.getCode2(), START_TIME)) {
                startTime = time.getName();
            }
            if (StringUtils.equals(time.getCode2(), END_TIME)) {
                endTime = time.getName();
            }
        }
        //業務上想定外の取得結果となった場合のエラー
        if (startTime == null || endTime == null) {
            throw new IfaRuntimeException(ERRORS_CMN_UNEXPECTED);
        }
        //現在時刻と取引開始・終了時間の設定(H:mm型)
        LocalTime currentTime = LocalTime.now(ZoneId.of("UTC+09:00"));
        LocalTime tradeStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern(HOUR_MINUTE));
        LocalTime tradeEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern(HOUR_MINUTE));
        //現在時刻がサービス時間内か判定
        if (currentTime.isBefore(tradeStartTime) || currentTime.isAfter(tradeEndTime) 
                || currentTime.equals(tradeEndTime)) {
            output.setProcessResult(RESULT_NG);
        } else {
            output.setProcessResult(RESULT_OK);
        }
        return output;
    }
    
    /**
     * SQL001を発行する
     * @param syubetu 注文種別
     * @param countrycode 注文種別
     * @return dao.selectFct018Sql001(sql001Req)　
     */
    private DataList<Fct018Sql001ResponseModel> getCode(String syubetu, String countrycode) throws Exception {
        
        Fct018Sql001RequestModel sql001Req = new Fct018Sql001RequestModel();
        //SQLリクエストの設定
        sql001Req.setSyubetu(syubetu);
        sql001Req.setCountryCode(countrycode);
        
        return dao.selectFct018Sql001(sql001Req);
    }
}
