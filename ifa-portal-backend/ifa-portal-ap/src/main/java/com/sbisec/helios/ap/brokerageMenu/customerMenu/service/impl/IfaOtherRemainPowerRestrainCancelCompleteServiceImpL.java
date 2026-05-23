package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOtherRemainPowerRestrainCancelCompleteService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;

/**
 * 画面ID：SUB0202_0110-02_2
 * 画面名：その他余力拘束注文取消完了

 * @author 大連 えん
    2025/02/28 新規作成
 */
@Component(value = "cmpIfaOtherRemainPowerRestrainCancelCompleteService")
public class IfaOtherRemainPowerRestrainCancelCompleteServiceImpL implements IfaOtherRemainPowerRestrainCancelCompleteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherRemainPowerRestrainCancelCompleteServiceImpL.class);

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto> initializeA001(
            IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto dtoReq) throws Exception {

        List<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto>();
        IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto responseDto = new IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainCancelCompleteServiceImpL.initializeA001");
        }

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        // 共通処理
        getResponseColumns(dtoReq, responseDto);

        resDto.add(responseDto);

        return apiErrorUtil.createDataList(resDto, null);
    }

    /**
     * 共通処理
     * @param cc 顧客共通情報
     * @param response レスポンス
     * @throws Exception エラー
     */
    private void getResponseColumns(IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto responseDto) throws Exception {
        // 画面項目編集
        CancelCompleteResponseColumnsDirects(dtoReq, responseDto);
    }

    /**
     * A001入力項目設定
     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void CancelCompleteResponseColumnsDirects(IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto response) {
        // 選択口座
        response.setAccountType(dtoReq.getAccountType());
        // 拘束種別
        response.setRestrainType(dtoReq.getRestrainType());
        // 拘束金額（買付余力）
        response.setNetAmount(dtoReq.getNetAmount());
        // 拘束金額（NISA成長投資枠）
        response.setIsaSeityoLimitAmount(dtoReq.getIsaSeityoLimitAmount());
        // 拘束金額（NISAつみたて投資枠）
        response.setIsaTsumitateLimitAmount(dtoReq.getIsaTsumitateLimitAmount());
        // 拘束期限
        response.setRestrainDateTo(dtoReq.getRestrainDateTo());
        // 拘束理由
        response.setRestrainReason(dtoReq.getRestrainReason());
        // 受注日時
        response.setAcceptDateTime(dtoReq.getAcceptDateTime());
        // 注文後買付余力
        response.setBuyingPowerTotalAfter(dtoReq.getBuyingPowerTotalAfter());
        // 注文後NISA成長投資枠(総合NISA(成長投資枠）買付可能枠(当年))
        response.setIsaSeityoLimitAmountAfter(dtoReq.getIsaSeityoLimitAmountAfter());
        // 注文後NISA成長投資枠(総合NISA(つみたて）買付可能枠(当年))
        response.setIsaTsumitateLimitAmountAfter(dtoReq.getIsaTsumitateLimitAmountAfter());
        // ジュニアNISA口座フラグ
        response.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());
    }

}
