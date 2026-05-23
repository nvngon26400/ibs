package com.sbisec.helios.ap.bizcommon.component;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct033Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;

/**
 * 共通関数：FCT033
 * 営業日情報チェック
 *
 * @author SCSK
 */

@Component
public class Fct033 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct033.class);
    
    /** 営業日・非営業日区分:営業日 */
    private static final String CBUS_NON_BUSINESS_DAY = "2";
    /** 入出力レスポンス.営業日チェックフラグ：営業日 */
    private static final String BUSINESS_DAY_CHECK_BUSSINESS = "1";
    /** 入出力レスポンス.営業日チェックフラグ：非営業日 */
    private static final String BUSINESS_DAY_CHECK_NOT_BUSSINESS = "0";

    /** SQL001で営業日・非営業日区分が取得できなかった場合のエラーメッセージ */
    private static final String NOT_EXIST_CBUSINES_NONBUS_DIV = "日付を取得できませんでした。";
    
    
    @Autowired
    private Fct033Dao dao;
    
    /**
     * 営業日情報チェック
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception 営業日情報チェック時に例外が発生した場合
     */
    public OutputFct033Dto doCheck(InputFct033Dto input) throws Exception {
        
        // デバッグ　ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct033.doCheck");
        }
        
        //必須パラメタチェック
        if (input.getDate() == null) {
            return new OutputFct033Dto();
        }
        
        OutputFct033Dto resDto = null;
        
        // ①   入出力リクエスト.日付に該当する営業日・非営業日区分を取得する。
        // 該当する日付がカレンダーに無い場合、エラーを返す。
        Fct033Sql001RequestModel sql001Req = new Fct033Sql001RequestModel();
        sql001Req.setDate(DateFormatUtils.format(input.getDate(), "yyyyMMdd"));
        
        List<Fct033Sql001ResponseModel> sql001ResList = dao.getBusinessDayType(sql001Req);
        if (sql001ResList == null || sql001ResList.size() == 0) {
            resDto = new OutputFct033Dto();
            resDto.setReturnCode(BaseOutputDto.RETURN_CODE_E001);
            resDto.setErrMessage(Fct033.NOT_EXIST_CBUSINES_NONBUS_DIV);
            return resDto;
        }
        
        resDto = new OutputFct033Dto();
        Fct033Sql001ResponseModel sql001Res = sql001ResList.get(0);
        if (StringUtils.equals(Fct033.CBUS_NON_BUSINESS_DAY, sql001Res.getCBusNonbusDiv())) {
            // ② 営業日・非営業日区分='2'(営業日)の場合、
            
            // 入出力レスポンス.営業日チェックフラグを'1'(営業日)
            resDto.setBusinessDayCheckFlag(Fct033.BUSINESS_DAY_CHECK_BUSSINESS);
        } else {
            // 営業日・非営業日区分≠'2'(営業日)の場合、
            
            // 入出力レスポンス.営業日チェックフラグを'0'(非営業日)
            resDto.setBusinessDayCheckFlag(Fct033.BUSINESS_DAY_CHECK_NOT_BUSSINESS);
        }
        
        return resDto;
    }
}
