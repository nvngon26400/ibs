package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawAcceptCompleteA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaWithdrawAcceptCompleteService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

/**
 * 画面ID：SUB0202_0601-02_2 画面名：出金完了
 *
 * @author xin.huang
 */
@Component(value = "cmpIfaWithdrawAcceptCompleteService")
public class IfaWithdrawAcceptCompleteServiceImpL implements IfaWithdrawAcceptCompleteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaWithdrawAcceptCompleteServiceImpL.class);

    @Autowired
    private IfaDateUtil ifaDateUtil;

    @Autowired
    private Fct033 fct033;

    @Autowired
    private Fct007 fct007;

    /** カレンダー区分("0"（証券営業日ベース）) */
    private static final String CALENDARTYPE = "0";

    /** 営業日チェックフラグ.営業日 */
    private static final String BUSINESS_DAY_CHECK_FLAG_BUSINESS_DAY = "1";

    /** 日数 "1"（翌営業日を取得する場合） */
    private static final Integer ONE_DAY = 1;

    /** 日数 "2"（翌々営業日を取得する場合） */
    private static final Integer TWO_DAY = 2;

    /**
     * アクションID：A002
     * アクション名：出金完了
     * レスポンス：IfaWithdrawAcceptCompleteA002ResponseDto Dto
     * リクエスト：IfaWithdrawAcceptCompleteA002RequestDto Dto
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 出金完了の際、例外が発生した場合
     */
    public DataList<IfaWithdrawAcceptCompleteA002ResponseDto> acceptCompleteA002(
            IfaWithdrawAcceptCompleteA002RequestDto dtoReq) throws Exception {
        DataList<IfaWithdrawAcceptCompleteA002ResponseDto> dtoRes = new DataList<IfaWithdrawAcceptCompleteA002ResponseDto>();
        List<IfaWithdrawAcceptCompleteA002ResponseDto> resDtoList = new ArrayList<IfaWithdrawAcceptCompleteA002ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWithdrawAcceptCompleteServiceImpL.acceptCompleteA002");
        }

        // ①システム共通日付を取得する。
        Date currentDate = ifaDateUtil.getCurrentDate();

        // ②システム共通日付が営業日か非営業日かを営業日情報チェックで判定する。
        OutputFct033Dto outputFct003Dto = new OutputFct033Dto();
        InputFct033Dto inputFct003Dto = new InputFct033Dto();
        inputFct003Dto.setDate(currentDate);
        try {
            outputFct003Dto = fct033.doCheck(inputFct003Dto);
        } catch (Exception e) {
            LOGGER.warn("営業日情報チェックで判定がエラー:" + e.getMessage());
            // ⑤画面を閉じて、出金入力画面を表示する。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
            return dtoRes;
        }

        // ③翌営業日または翌々営業日の日付を取得する。
        // 日数 設定元
        Integer day = ONE_DAY;
        // 本日の時間16:00に取得
        LocalTime localTime = LocalTime.of(16, 00, 0);
        LocalDateTime todayLocalTime = LocalDateTime.of(LocalDate.now(), localTime);
        // システム共通日付が営業日かつ時刻が16:00より前の場合は翌営業日
        if (StringUtils.equals(outputFct003Dto.getBusinessDayCheckFlag(), BUSINESS_DAY_CHECK_FLAG_BUSINESS_DAY)
                && todayLocalTime.isAfter(LocalDateTime.now())) {
            day = ONE_DAY;
        } else {
            day = TWO_DAY;
        }
        InputFct007Dto inputFct007Dto = new InputFct007Dto();
        // 基準日(システム共通日付)をセットする
        inputFct007Dto.setStandardDate(currentDate);
        // カレンダー区分("0"（証券営業日ベース）)をセットする
        inputFct007Dto.setCalendarType(CALENDARTYPE);
        // 日数("1"または"2")をセットする
        inputFct007Dto.setDay(day);
        OutputFct007Dto outputFct007Dto = new OutputFct007Dto();
        try {
            outputFct007Dto = fct007.getData(inputFct007Dto);
        } catch (Exception e) {
            LOGGER.warn("翌営業日または翌々営業日の日付を取得がエラー:" + e.getMessage());
            // ⑤画面を閉じて、出金入力画面を表示する。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
            return dtoRes;
        }

        // ④取得した翌営業日または翌々営業日の日付を 「出金日として指定可能な直近の日付」として 画面遷移時パラメーターに設定する。
        IfaWithdrawAcceptCompleteA002ResponseDto resDto = new IfaWithdrawAcceptCompleteA002ResponseDto();
        // 出金日として指定可能な直近の日付
        String minPayDate = DateUtil.format(outputFct007Dto.getDesignatedDate(), DateUtil.SEPARATED_YYYYMMDD);
        resDto.setMinPayDate(minPayDate);
        resDtoList.add(resDto);
        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
}
