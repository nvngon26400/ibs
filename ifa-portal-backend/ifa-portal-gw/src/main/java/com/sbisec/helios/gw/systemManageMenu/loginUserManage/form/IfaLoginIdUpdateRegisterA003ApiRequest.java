package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

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
public class IfaLoginIdUpdateRegisterA003ApiRequest {

	/** 所属権限コード. */
	@NotEmpty(message = "所属権限コード")
	private String privId;

	/** 本支店コード. */
	@NotEmpty(message = "本支店コード")
	private String branchCode;

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 仲介業者支店コード（数字）. */
	@NotEmpty(message = "仲介業者支店コード")
	@Pattern(regexp="0-9", message = "仲介業者支店コード")
	@Size(max = 3, message = "仲介業者支店コード")
	private String subBrokerId;

}
