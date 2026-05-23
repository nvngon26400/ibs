package com.sbisec.helios.ap.bizcommon.component;

import java.text.ParseException;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct007Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.common.constants.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT007 指定日取得 20230727
 *
 * @author base 鄒
 */
@Component
public class Fct007 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct007.class);
    
    @Autowired
    private Fct007Dao fct007Dao;
    
    private static final String CALENDAR_TYPE_0 = "0";
    
    private static final String CALENDAR_TYPE_1 = "1";
    
    public static final String MESSAGE_E001 = "日数には正数または負数を設定してください。";
    
    public static final String MESSAGE_E002 = "条件に該当する日付を取得できませんでした。";
    
    /**
     * FCT007指定日取得
     *
     * @param input リクエスト
     * @return outputFct007Dto
     * @throws Exception 
     */
    public OutputFct007Dto getData(InputFct007Dto input) throws Exception {
        
        // ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct007.getData");
        }
        
        //　①レスポンス項目を初期化する。
        OutputFct007Dto outputFct007Dto = new OutputFct007Dto();
        //　②リクエスト項目の入力チェックを行う。
        //　日数が0（ゼロ）の場合、エラーを返す。
        if (AppConstants.DAY_0 == input.getDay()) {
            // 日数が0（ゼロ）の場合、エラーを返す。
            outputFct007Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E001);
            outputFct007Dto.setErrMessage(MESSAGE_E001);
            return outputFct007Dto;
        }
        
        //③カレンダー区分に従って、レスポンスの指定日を算出する。 カレンダー区分 ＝ 0（証券営業日ベース） カレンダー区分 ＝ 1（カレンダーベース）
        String standardDate = DateUtil.format(input.getStandardDate(), DateUtil.YYYYMMDD);
        if (CALENDAR_TYPE_0.equals(input.getCalendarType())) {
            //　④証券営業日ベースの指定日を取得する。
            Fct007Sql001RequestModel fct007Sql001RequestModel = new Fct007Sql001RequestModel();
            fct007Sql001RequestModel.setDate(standardDate);
            fct007Sql001RequestModel.setNumberDay(input.getDay());
            // 証券営業日ベースの指定日を取得する
            Fct007Sql001ResponseModel fct007Sql001ResponseModel;
            fct007Sql001ResponseModel = fct007Dao.getDesignatedDate(fct007Sql001RequestModel);
            if (null != fct007Sql001ResponseModel && null != fct007Sql001ResponseModel.getBusinessDay()) {
                // 処理を終了して呼出元にレスポンスを返却する。
                outputFct007Dto.setDesignatedDate(DateUtil.parse(fct007Sql001ResponseModel.getBusinessDay(),DateUtil.YYYYMMDD));
            } else {
                // 上記以外：指定日取得失敗エラーを返す。
                outputFct007Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E002);
                outputFct007Dto.setErrMessage(MESSAGE_E002);
            }
        } else if (CALENDAR_TYPE_1.equals(input.getCalendarType())) {
            //　⑤カレンダーベースの指定日を算出する。 計算用.指定日 ← リクエスト.基準日 + リクエスト.日数
            //　算出成功：レスポンス.指定日に計算用.指定日をセットする。 処理を終了して呼出元にレスポンスを返却する。
            String businessDay = "";
            try {
                businessDay = DateUtil.format(
                        DateUtil.addDays(DateUtil.parse(standardDate, DateUtil.YYYYMMDD), input.getDay()),
                        DateUtil.YYYYMMDD);
            } catch (Exception e) {
                LOGGER.error("Fct007.getData standardDate Exception[{}]", e.getMessage());
                // 上記以外：指定日取得失敗エラーを返す。
                outputFct007Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E002);
                outputFct007Dto.setErrMessage(MESSAGE_E002);
                return outputFct007Dto;
            }
            if (!StringUtil.isNullOrEmpty(businessDay)) {
                outputFct007Dto.setDesignatedDate(DateUtil.parse(businessDay, DateUtil.YYYYMMDD));
            } else {
                // 上記以外：指定日取得失敗エラーを返す。
                outputFct007Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E002);
                outputFct007Dto.setErrMessage(MESSAGE_E002);
            }
        }
        return outputFct007Dto;
    }
}
