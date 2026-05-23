package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmA001ApiRequest {

	/** 注文Sub番号（数字）. */
	@Digits(integer = 18, fraction = 0, message = "注文Sub番号")
	@NotEmpty(message = "注文Sub番号")
	@Pattern(regexp="0-9", message = "注文Sub番号")
	private String orderSubNumber;

}
