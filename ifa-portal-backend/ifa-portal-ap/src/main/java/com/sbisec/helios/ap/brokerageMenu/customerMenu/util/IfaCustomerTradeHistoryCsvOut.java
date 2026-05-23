package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryCsvItems;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
public class IfaCustomerTradeHistoryCsvOut extends CsvOutPutUtil {

    public IfaCustomerTradeHistoryCsvOut(ComplianceService comp) {
        super(comp);
    }
    
    @Override
    protected String getCsvHeader() {
        return getHeader();
    }
    
    public static String getHeader() {
        
        final StringBuilder strCsvHead = new StringBuilder(512);
        
        strCsvHead.append(DOUBLE_QUOTATION + "約定日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "受渡日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "商品区分" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "銘柄名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "内訳取引" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "内訳区分" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "預り/税" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引市場/理由" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "単価" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取得単価" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取得日" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "通貨" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "為替" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "数量" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "手数料" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "消費税" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "経過利子" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "実現損益" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "譲渡益税/源泉税" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "精算金額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取消区分" + DOUBLE_QUOTATION);

        return strCsvHead.toString();
    }
    
    @Override
    protected String getCsvLine(ModelBase modelBase) {
        IfaCustomerTradeHistoryCsvItems csvItems = (IfaCustomerTradeHistoryCsvItems) modelBase;
        
        StringBuilder strCsv = new StringBuilder();

        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getTradeDate()) + DOUBLE_QUOTATION); // 約定日
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getDeliveryDate()) + DOUBLE_QUOTATION); // 受渡日
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getSecurityType())) + DOUBLE_QUOTATION); // 商品区分
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getBrandCode()) + DOUBLE_QUOTATION); // 銘柄コード
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBrandName())) + DOUBLE_QUOTATION); // 銘柄名
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDetailsTrade())) + DOUBLE_QUOTATION); // 内訳取引
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDetailsType())) + DOUBLE_QUOTATION); // 内訳区分
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getDepositTax())) + DOUBLE_QUOTATION); // 預り/税
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getTradeMarketReason())) + DOUBLE_QUOTATION); // 取引市場/理由
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getPrice()) + DOUBLE_QUOTATION); // 単価
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getOpenPrice()) + DOUBLE_QUOTATION); // 取得単価
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAcquireDate()) + DOUBLE_QUOTATION); // 取得日
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getCurrency())) + DOUBLE_QUOTATION); // 通貨
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFx()) + DOUBLE_QUOTATION); // 為替
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getQuantity()) + DOUBLE_QUOTATION); // 数量
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getFee()) + DOUBLE_QUOTATION); // 手数料
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getConsumptionTax()) + DOUBLE_QUOTATION); // 消費税
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAccruedInterest()) + DOUBLE_QUOTATION); // 経過利子
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getRealProfitAndLoss()) + DOUBLE_QUOTATION); // 実現損益
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCapitalGainTax()) + DOUBLE_QUOTATION); // 譲渡益税/源泉税
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getAccurateAmount()) + DOUBLE_QUOTATION); // 精算金額
        strCsv.append(CSV_SEPARATER);
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(csvItems.getCancelFlg()) + DOUBLE_QUOTATION); // 取消区分

        return strCsv.toString();
    }
}
