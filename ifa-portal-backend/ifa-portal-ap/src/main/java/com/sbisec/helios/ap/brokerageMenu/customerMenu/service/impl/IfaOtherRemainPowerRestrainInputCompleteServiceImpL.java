package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOtherRemainPowerRestrainInputCompleteService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaOtherRemainPowerRestrainUtil;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;

/**
 * 画面ID：SUB0202_0110-01_3
 * 画面名：その他余力拘束注文完了

 * @author 大連 えん
    2025/02/28 新規作成
 */
@Component(value = "cmpIfaOtherRemainPowerRestrainInputCompleteService")
public class IfaOtherRemainPowerRestrainInputCompleteServiceImpL implements IfaOtherRemainPowerRestrainInputCompleteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherRemainPowerRestrainInputCompleteServiceImpL.class);

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;

    /**
     * その他余力拘束の共通部品
     */
    @Autowired
    private IfaOtherRemainPowerRestrainUtil ifaOrprUtil;

    /**
     * アクションID：A001 アクション名：初期化 Dto
     * リクエスト：IfaOtherRemainPowerRestrainInputCompleteA001RequestDto Dto
     * レスポンス：IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto> initializeA001(
            IfaOtherRemainPowerRestrainInputCompleteA001RequestDto dtoReq) throws Exception {

        List<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto> resDto = new ArrayList<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto>();
        IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto responseDto = new IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherRemainPowerRestrainInputCompleteServiceImpL.initializeA001");
        }

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        // API結果
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        // API001呼び出し
        api001OutData = queryAccountBalance(cc);
        // API処理結果確認
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }

        // 共通処理
        getResponseColumns(dtoReq, responseDto, api001OutData);

        resDto.add(responseDto);

        return apiErrorUtil.createDataList(resDto, null);
    }

    /**
     * 共通処理
     * @param cc 顧客共通情報
     * @param response レスポンス
     * @throws Exception エラー
     */
    private void getResponseColumns(IfaOtherRemainPowerRestrainInputCompleteA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto responseDto,
            QueryAccountBalanceOutData api001OutData) throws Exception {
        // 画面項目編集
        if (ifaOrprUtil.checkNotNullObjects(api001OutData)) {
            // 入力項目設定
            editResponseColumnsDirects(dtoReq, responseDto, api001OutData);
        }
    }

    /**
     * API001呼び出し処理
     * @param dtoReq A001リクエストDTO
     * @return API出力結果
     */
    private QueryAccountBalanceOutData queryAccountBalance(CustomerCommon cc) throws Exception {

        QueryAccountBalanceIn input = new QueryAccountBalanceIn();
        QueryAccountBalanceInData inData = new QueryAccountBalanceInData();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(ifaOrprUtil.createApiRequestAccountNo(cc.getAccountNumber()));

        input.setIndata(inData);

        return apiWrapper.queryAccountBalance(input);
    }

    /**
     * A001入力項目設定
     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void editResponseColumnsDirects(IfaOtherRemainPowerRestrainInputCompleteA001RequestDto dtoReq,
            IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto response,
            QueryAccountBalanceOutData api001OutData) {
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
        // 注文後の買付可能金額
        response.setBuyingPowerTotalAfter(dtoReq.getBuyingPowerTotalAfter());
        // EC受注番号
        response.setOrderNo(dtoReq.getOrderNo());
        // ジュニアNISA口座フラグ
        response.setJrNisageneralAccountFlag(dtoReq.getJrNisageneralAccountFlag());
        // 注文後NISA成長投資枠(総合NISA(成長投資枠）買付可能枠(当年))
        response.setIsaSeityoLimitAmountAfter(api001OutData.getIsaSeityoBuyLimit());
        // 注文後NISA成長投資枠(総合NISA(つみたて）買付可能枠(当年))
        response.setIsaTsumitateLimitAmountAfter(api001OutData.getIsaTsumitateBuyLimit());
    }
}
