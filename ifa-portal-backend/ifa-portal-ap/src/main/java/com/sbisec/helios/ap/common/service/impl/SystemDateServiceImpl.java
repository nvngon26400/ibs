package com.sbisec.helios.ap.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.dto.SystemDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.SystemDateService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SystemDateServiceの実装クラス
 */
@Component(value = "systemDateService")
public class SystemDateServiceImpl implements SystemDateService {
    
    //カレンダー区分（証券営業日ベース）
    private static final String CALENDAR_TYPE_0 = "0";
    
    //前営業日
    private static final Integer ONE_DAY_BEFORE = -1;
    
    // SystemDateMapperを使用する場合は、SystemDateDaoをインジェクトするように変更します。
    @Autowired
    private SystemDateDao systemDateDao;
    
    // FCT007をインジェクト
    @Autowired
    private Fct007 fct007;
    
    /**
     * システム日付を基準にして前営業日を取得します。
     *
     * @param req 前営業日を取得するためのリクエスト情報
     * @return 前営業日をYYYYMMDD形式の文字列で返します。
     * @throws Exception 汎用的な例外が発生した場合にスローされます。
     */
    @Override
    public String getPreviousBusinessDate(PreviousBusinessDateDtoRequest req) throws Exception {
        
        Date systemDate = systemDateDao.getSystemDate();
        
        //検索条件指定エリア.絞込期間fromに表示する日付を取得する。
        InputFct007Dto inputFct007Dto = new InputFct007Dto();
        //基準日 設定元：   システム日付
        inputFct007Dto.setStandardDate(systemDate);
        //カレンダー区分 設定元：   ０（証券営業日ベース）
        inputFct007Dto.setCalendarType(CALENDAR_TYPE_0);
        //日数  設定元：   ー1
        inputFct007Dto.setDay(ONE_DAY_BEFORE);
        
        OutputFct007Dto outputFct007Dto = fct007.getData(inputFct007Dto);
        
        // OutputFct007DtoからDate型の日付を取得して返す
        //String型で返す
        return DateUtil.format(outputFct007Dto.getDesignatedDate(), DateUtil.YYYYMMDD);
    }
    
    /**
     * システム日付を取得する。
     *
     * @param req リクエストDTO
     * @return システム日付
     * @throws Exception 例外
     */
    @Override
    public DataList<Date> getSystemDate(SystemDateDtoRequest req) throws Exception {

        List<Date> dateList = new ArrayList<>();
        try {
            Date systemDate = systemDateDao.getSystemDate();
            dateList.add(systemDate);
        } catch (Exception e) {
            // エラーが発生した際はサーバ日付を返却
            Date systemDate = new Date();
            dateList.add(systemDate);
        }
        return IfaCommonUtil.createDataList(dateList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
    }
}
