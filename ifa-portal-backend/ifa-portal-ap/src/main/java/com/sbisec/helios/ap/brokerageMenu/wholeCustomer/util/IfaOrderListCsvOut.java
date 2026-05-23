package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aResponseDtoOrderList;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 *
 * @author BASE李
 */
public class IfaOrderListCsvOut extends CsvOutPutUtil {
    
    /**
     *
     * @param comp コンプライアンス
     */
    public IfaOrderListCsvOut(ComplianceService comp) {
        
        super(comp);
        
    }
    
    @Override
    public String getCsvHeader(String empCodeAutoDispFlag) {
        StringBuilder csvHeader = new StringBuilder();
        csvHeader.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文番号" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "市場" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文状況" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "訂正/取消区分" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "取消理由" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "取引種別" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文種別" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "条件詳細" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "決済方法" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "預り区分" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "注文日時" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "期間" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "約定日時" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "数量" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "価格" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "現在値" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "ポイント種別" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "利用ポイント" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "分配金受取方法指定" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "積立コース" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "設定金額" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "ボーナス月の設定" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "NISA枠ぎりぎり注文" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "課税シフト注文" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "1カ月あたりの積立金額" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "次回発注予定日" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "勧誘区分" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "受注方法" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "英文開示銘柄" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "重要事項の説明" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "償還優遇枠" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "ワーニング申請取引" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "目論見書交付方法" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "乗換え勧誘" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "利益相反可能性の説明" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "販売手数料の利率等の説明" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "複数取引業者での手数料等明示" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "同一通貨/同一国の乗換" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "他社間投信乗換え勧誘" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "短期売却確認" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "償還前売却確認" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        csvHeader.append(DOUBLE_QUOTATION + "発注者" + DOUBLE_QUOTATION);
        csvHeader.append(CSV_SEPARATER);
        if ("0".equals(empCodeAutoDispFlag)) {
            csvHeader.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
            csvHeader.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
            csvHeader.append(CSV_SEPARATER);
        }
        csvHeader.append(DOUBLE_QUOTATION + "閲覧可能部店" + DOUBLE_QUOTATION);
        return csvHeader.toString();
    }
    
    /**
     * 注文一覧データをcvs形式に変換
     *
     * @param modelBase 注文一覧データ
     * @return String cvs形式注文一覧データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase, String empCodeAutoDispFlag) {
        IfaOrderListA005aResponseDtoOrderList ifaBbApplyListA004aResponseDtoBbApplyList = (IfaOrderListA005aResponseDtoOrderList) modelBase;
        StringBuilder csvBody = new StringBuilder();
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getButenCode()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getAccountNumber()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getCourse()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCustomerNameKana())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderNumber()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrandCode()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrandName())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getMarket())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderStatus())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCorrectCancleKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCancelReason())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getTradeCd())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getSecuriytClass())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getConditionDetails())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getSettlementMethod())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getDepositType())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderDate()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getPeriod()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getTradeTime()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getQuantity()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getPrice())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getCurrentPrice()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getPointType())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderPoint()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getDistributionReceiveMethodDesignation())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getAccumulateCourse())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getSettingAmount()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBonusMonthSetting())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getNisaBarelyBuyingKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getNisaExcessBuyingKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getOneMonthSumAmount()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getNextReserveDate()) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getKanyuKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getOrderMethod())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getEngPubType())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getImportantMatter())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getRedemptionIncentives())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getWarningApplyTrade())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getProspectus())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getSwitchingSolicitation())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getConflictOfInterestExplain())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getSalesCommRate())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getCheckFukusu())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getDouitsuTukaKuniKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getTashaNorikaeKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getTankiSellKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getShokanMaeKbn())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getOrder())) + DOUBLE_QUOTATION);
        csvBody.append(CSV_SEPARATER);
        if ("0".equals(empCodeAutoDispFlag)) {
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerageBranchCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerBranchName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(ifaBbApplyListA004aResponseDtoBbApplyList.getBrokerChargeCode()) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
            csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getEmployeeName())) + DOUBLE_QUOTATION);
            csvBody.append(CSV_SEPARATER);
        }
        csvBody.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(ifaBbApplyListA004aResponseDtoBbApplyList.getViewAblrButenCode())) + DOUBLE_QUOTATION);
        return csvBody.toString();
    }
    
    
    
}
