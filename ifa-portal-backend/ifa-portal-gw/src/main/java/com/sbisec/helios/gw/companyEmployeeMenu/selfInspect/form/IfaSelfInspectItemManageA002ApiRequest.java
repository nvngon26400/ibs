package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA002ApiRequest {

    /** 登録年月 */
    @DateTimeFormat(pattern = "yyyyMM")
    @JsonFormat(pattern = "yyyyMM")
    @NotEmpty(message = "登録年月")
    @Size(min = 6, max = 6, message = "登録年月")
    private String assignMonthList;

    /** 業者区分リスト */
    @NotEmpty(message = "業者区分リスト")
    private List<BrokerType> brokerTypeList;


    /**
     * A002APIリクエスト.業者区分クラス
     *
     * @author SCSK 江口
     */
    @Data
    public static class BrokerType {

        /** 業者区分（全角半角） */
        @NotEmpty(message = "業者区分")
        private String brokerType;

        /** 区分名称 */
        @NotEmpty(message = "区分名称")
        private String classificationName;

    }

}
