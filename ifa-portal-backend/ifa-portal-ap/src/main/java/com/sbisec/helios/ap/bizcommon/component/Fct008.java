package com.sbisec.helios.ap.bizcommon.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct008Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct008Dto;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT008
 * 営業日リスト取得
 *
 * @author SCSK
 */

@Component
public class Fct008 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fct008.class);
    
    @Autowired
    private Fct008Dao dao;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    enum BrandCode {
        
        // 東証
        TKY("0"),
        // 名証
        NGY("2"),
        // 福証
        FKO("6"),
        // 札証
        SPR("8"),
        // 当社優先市場／SOR
        SOR("A"),
        // 単元未満
        DLU("H");
        
        private String key;
        
        private BrandCode(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * 注文入力において期間指定する際に、指定可能な注文期間を（営業日）を取得する。
     * 備考：有効期間は発注日（From）を含めて最長15営業日までとなるが、「期間指定」での指定日は（To）を指定するため、翌営業日からの14日間を取得する。
     *
     * @param inputFct008Dto インプットモデル
     * @return OutputFct008Dto　アウトプットモデル
     */
    public OutputFct008Dto getData(InputFct008Dto inputFct008Dto) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct008.getData");
        }
        //入力チェック
        if (inputFct008Dto  ==  null 
                || inputFct008Dto.getBrandCode() == null 
                || inputFct008Dto.getPeriodTargetMarket() == null) {
            return new OutputFct008Dto();
        }
        //期間対象市場設定
        if (!StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.TKY.key)
                && !StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.NGY.key)
                && !StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.FKO.key)
                && !StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.SPR.key)
                && !StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.SOR.key)
                && !StringUtils.equals(inputFct008Dto.getPeriodTargetMarket(), BrandCode.DLU.key)) {
            // 条件の選択市場以外の値が設定されていた場合、"A"：当社優先市場／SOR　として扱う
            inputFct008Dto.setPeriodTargetMarket(BrandCode.SOR.key);
        }
        OutputFct008Dto output  =  new OutputFct008Dto();
        //現在日付以降の営業日を取得する
        Fct008Sql001RequestModel requestModel1 = new Fct008Sql001RequestModel();
        List<String> businessDayList  =  dao.getBusinessDayList(requestModel1); 
        if (CollectionUtils.isEmpty(businessDayList) || businessDayList.size() != 16) { 
            throw new IfaRuntimeException("対象外エラー");
        }
        //優先市場を取得する
        Fct008Sql002RequestModel requestModel2 = new Fct008Sql002RequestModel();
        requestModel2.setBrandCode(inputFct008Dto.getBrandCode());
        Fct008Sql002ResponseModel sql002ResModel = dao.getMarket(requestModel2);
        
        //現在日付がカレンダーマスタに存在しない（非営業日）の場合
        String systemDateYearMonthDay = null;
        String systemDateHourMinute = null;
        try {
            systemDateYearMonthDay = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
            systemDateHourMinute = ifaDateUtil.format("HHmm");
        } catch (Exception e) {
            LOGGER.error("Fct008.getData getSystemDateException[{}]", "対象外エラー");
            throw new IfaRuntimeException("対象外エラー");
        }
        if (!businessDayList.contains(systemDateYearMonthDay)) {
            output.setBussiessDaylist(fetchDayList(businessDayList, 1, 14));
            return output;
        } else {
            // 現在日付がカレンダーマスタに存在する（営業日）の場合
            int currentTime = Integer.valueOf(systemDateHourMinute);
            Fct008Sql003RequestModel requestModel3 = new Fct008Sql003RequestModel();
            if (sql002ResModel == null) {
                requestModel3.setSql002ResSize(0);
            } else {
                requestModel3.setSql002ResSize(1);
                requestModel3.setIpmSeInvestmentsCode(sql002ResModel.getIpmSeInvestmentsCode());;
            }
            requestModel3.setPeriodTargetMarket(inputFct008Dto.getPeriodTargetMarket());
            
            
            Fct008Sql003ResponseModel Sql003ResponseModel = dao.getOverTime(requestModel3);
            String overTime = Sql003ResponseModel.getOverTime();
            overTime = LocalTime.parse(overTime, DateTimeFormatter.ofPattern("HH:mm"))
                    .format(DateTimeFormatter.ofPattern("HHmm"));
            if (currentTime < Integer.parseInt(overTime)) {
                //現在時刻 < SQL003.終了時刻の場合、2営業日～15営業日を取得する
                output.setBussiessDaylist(fetchDayList(businessDayList, 1, 14));
            } else {
                //現在時刻 > =  SQL003.終了時刻の場合、3営業日～16営業日を取得する
                output.setBussiessDaylist(fetchDayList(businessDayList, 2, 15));
            }
            return output;
        }
    }
    
    /**
     * 営業日リストを一部抽出する
     *
     * @param src 営業日リスト
     * @param start 最初に取得するインデックス
     * @param end 最後に取得するインデックス
     * @return List 抽出された営業日リスト
     */
    private List<Date> fetchDayList(List<String> src, int start, int end) {
        int size = src.size();
        int maxEnd = end < size ? end : size - 1;
        List<Date> result = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            for (int i = start; i <= maxEnd; i++) {
                result.add(format.parse(src.get(i)));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
