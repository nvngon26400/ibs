package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaDomesticStockOrderCorrectConfirmA001ApiRequestBeforeCorrectPrice {

	/** 通常/逆指値.執行方法. */
	@NotEmpty(message = "通常/逆指値.執行方法")
	private String sasinariHouhou;

	/** 通常/逆指値.執行条件. */
	@NotEmpty(message = "通常/逆指値.執行条件")
	private String sasinariJyouken;

	/** 通常/逆指値.発火条件価格（逆指値）. */
	@NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）")
	private String triggerPrice;

	/** 通常/逆指値.発火条件価格（逆指値）ゾーン. */
	@NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）ゾーン")
	private String stopOrderPriceText2;

	/** 通常/逆指値.執行方法（逆指値）. */
	@NotEmpty(message = "通常/逆指値.執行方法（逆指値）")
	private String gyakusasiHouhou;

	/** 通常/逆指値.注文単価. */
	@NotEmpty(message = "通常/逆指値.注文単価")
	private String price;

	/** OCO1.執行方法. */
	@NotEmpty(message = "OCO1.執行方法")
	private String oco1SasinariHouhou;

	/** OCO1.執行条件. */
	@NotEmpty(message = "OCO1.執行条件")
	private String oco1SasinariJyouken;

	/** OCO1.注文単価. */
	@NotEmpty(message = "OCO1.注文単価")
	private String oco1Price;

	/** OCO2.発火条件価格（逆指値）. */
	@NotEmpty(message = "OCO2.発火条件価格（逆指値）")
	private String oco2TriggerPrice;

	/** OCO2.発火条件価格（逆指値）ゾーン. */
	@NotEmpty(message = "OCO2.発火条件価格（逆指値）ゾーン")
	private String oco2StopOrderPriceText2;

	/** OCO2.執行方法（逆指値）. */
	@NotEmpty(message = "OCO2.執行方法（逆指値）")
	private String oco2GyakusasiHouhou;

	/** OCO2.執行条件（逆指値）. */
	@NotEmpty(message = "OCO2.執行条件（逆指値）")
	private String oco2GyakusasiJyouken;

	/** OCO2.注文単価. */
	@NotEmpty(message = "OCO2.注文単価")
	private String oco2Price;

	/** IFD1.執行方法. */
	@NotEmpty(message = "IFD1.執行方法")
	private String ifd1SasinariHouhou;

	/** IFD1.執行条件. */
	@NotEmpty(message = "IFD1.執行条件")
	private String ifd1SasinariJyouken;

	/** IFD1.発火条件価格（逆指値）. */
	@NotEmpty(message = "IFD1.発火条件価格（逆指値）")
	private String ifd1TriggerPrice;

	/** IFD1.発火条件価格（逆指値）ゾーン. */
	@NotEmpty(message = "IFD1.発火条件価格（逆指値）ゾーン")
	private String ifd1StopOrderPriceText2;

	/** IFD1.執行方法（逆指値）. */
	@NotEmpty(message = "IFD1.執行方法（逆指値）")
	private String ifd1GyakusasiHouhou;

	/** IFD1.注文単価. */
	@NotEmpty(message = "IFD1.注文単価")
	private String ifd1Price;

	/** IFD2.期間.日付. */
	@NotEmpty(message = "IFD2.期間.日付")
	private String ifd2Limit;

	/** IFD2.執行方法. */
	@NotEmpty(message = "IFD2.執行方法")
	private String ifd2SasinariHouhou;

	/** IFD2.執行条件. */
	@NotEmpty(message = "IFD2.執行条件")
	private String ifd2SasinariJyouken;

	/** IFD2.発火条件価格（逆指値）. */
	@NotEmpty(message = "IFD2.発火条件価格（逆指値）")
	private String Ifd2TriggerPrice;

	/** IFD2.発火条件価格（逆指値）ゾーン. */
	@NotEmpty(message = "IFD2.発火条件価格（逆指値）ゾーン")
	private String ifd2StopOrderPriceText2;

	/** IFD2.執行方法（逆指値）. */
	@NotEmpty(message = "IFD2.執行方法（逆指値）")
	private String ifd2GyakusasiHouhou;

	/** IFD2.注文単価. */
	@NotEmpty(message = "IFD2.注文単価")
	private String ifd2Price;

}
