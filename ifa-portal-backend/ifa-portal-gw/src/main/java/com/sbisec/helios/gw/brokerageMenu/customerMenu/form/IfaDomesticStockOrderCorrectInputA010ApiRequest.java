package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0202_0208-03
 * 画面名：国内株式注文訂正入力
 * 2023/01/09 新規作成
 *
 * @author 齋藤
 */
@Data
public class IfaDomesticStockOrderCorrectInputA010ApiRequest {
    
    /** EC受注番号 */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
    /** 市場 */
    @NotEmpty(message = "市場")
    private String market;
    
    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** 訂正後市場 */
    @NotEmpty(message = "訂正後市場")
    private String afterCorrectMarket;

    /** 数量（数字） */
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 未約定数量（数字） */
    @NotEmpty(message = "未約定数量")
    @Size(max = 19, message = "未約定数量")
    private String unTradeQuantity;
    
    /** 注文種別 */
    @NotEmpty(message = "注文種別")
    private String orderKind;
    
    /** 取引種別 */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 期間 */
    @NotEmpty(message = "期間")
    private String period;
    
    /** 預り区分 */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 発火状態 */
    @NotEmpty(message = "発火状態")
    private String workingStatus;
    
    /** DONE状態 */
    @NotEmpty(message = "DONE状態")
    private String doneState;
    
    /** RBE注文ステータス */
    @NotEmpty(message = "RBE注文ステータス")
    private String rbeOrderStatus;
    
    /** 通常/逆指値.執行方法 */
    @NotEmpty(message = "通常/逆指値.執行方法")
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    @NotEmpty(message = "通常/逆指値.執行条件")
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数字） */
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）")
    @Size(max = 13, message = "通常/逆指値.発火条件価格（逆指値）")
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値） */
    @NotEmpty(message = "通常/逆指値.執行方法（逆指値）")
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数字） */
    @NotEmpty(message = "通常/逆指値.注文単価")
    @Size(max = 13, message = "通常/逆指値.注文単価")
    private String price;
    
    /** OCO1.執行方法 */
    @NotEmpty(message = "OCO1.執行方法")
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件 */
    @NotEmpty(message = "OCO1.執行条件")
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数字） */
    @NotEmpty(message = "OCO1.注文単価")
    @Size(max = 13, message = "OCO1.注文単価")
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数字） */
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）")
    @Size(max = 13, message = "OCO2.発火条件価格（逆指値）")
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値） */
    @NotEmpty(message = "OCO2.執行方法（逆指値）")
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    @NotEmpty(message = "OCO2.執行条件（逆指値）")
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数字） */
    @NotEmpty(message = "OCO2.注文単価")
    @Size(max = 13, message = "OCO2.注文単価")
    private String oco2Price;
    
    /** IFD1.執行方法 */
    @NotEmpty(message = "IFD1.執行方法")
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件 */
    @NotEmpty(message = "IFD1.執行条件")
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数字） */
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD1.発火条件価格（逆指値）")
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値） */
    @NotEmpty(message = "IFD1.執行方法（逆指値）")
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数字） */
    @NotEmpty(message = "IFD1.注文単価")
    @Size(max = 13, message = "IFD1.注文単価")
    private String ifd1Price;
    
    /** IFD2.期間.日付 */
    @NotEmpty(message = "IFD2.期間.日付")
    private String ifd2Limit;
    
    /** IFD2.執行方法 */
    @NotEmpty(message = "IFD2.執行方法")
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件 */
    @NotEmpty(message = "IFD2.執行条件")
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数字） */
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD2.発火条件価格（逆指値）")
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値） */
    @NotEmpty(message = "IFD2.執行方法（逆指値）")
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数字） */
    @NotEmpty(message = "IFD2.注文単価")
    @Size(max = 13, message = "IFD2.注文単価")
    private String ifd2Price;
    
    /** 訂正前価格 */
    @NotEmpty(message = "訂正前価格")
    private List<IfaDomesticStockOrderCorrectInputA010BeforeCorrectPriceApiRequest> beforeCorrectPrice;
    
    /** 勧誘区分 */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法 */
    @NotEmpty(message = "受注方法")
    private String receiveOrderType;
    
    /** 確認項目.インサイダー確認 */
    @NotEmpty(message = "確認項目.インサイダー確認")
    private String checkInsider;
    
    /** 確認項目.SOR確認 */
    @NotEmpty(message = "確認項目.SOR確認")
    private String checkSor;

    /** 手数料区分 */
    @NotEmpty(message = "手数料区分")
    private String tesuuryouKbn;
    
    /** 受注日時 */
    @NotEmpty(message = "受注日")
    private String orderDate;

    /** 訂正SOR注文区分 */
    @NotEmpty(message = "訂正SOR注文区分")
    private String correctSorOrderClassification;

}
